package com.noboll.core.cache.service.impl;

import javax.annotation.Resource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.noboll.core.cache.entity.CacheOption;
import com.noboll.core.cache.entity.ExpireType;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.SerializeUtil;

public class RedisCacheServiceImpl implements CacheService {
	
	@Resource
	private JedisPool jedisPool;
	
	public boolean contains(String key) {
		if (key == null) {
            throw new BusinessException("key值不能为空");
        }
        try {
        	Jedis jedis = jedisPool.getResource();
            byte[] kBytes = SerializeUtil.serialize(key);
            return jedis.exists(kBytes);
        }catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}

	public boolean put(String key, Object value) {
		return put(key, value, null);
	}

	public boolean put(String key, Object value, CacheOption option) {
		if (key == null || value == null) {
			 throw new BusinessException("key值不能为空");
        }
        try {
        	Jedis jedis = jedisPool.getResource();
            byte[] kBytes = SerializeUtil.serialize(key);
            byte[] vBytes = SerializeUtil.serialize(value);
            // code为1表示ok.
            long code = jedis.setnx(kBytes, vBytes);
            // 当option不为空时，设置过期时间
            if(option!=null)
            	setExpire(kBytes, option, jedis);
            return code==1;
        }catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}

	public Object get(String key) {
		if (key == null) {
			throw new BusinessException("key值不能为空");
        }
        try {
        	Jedis jedis = jedisPool.getResource();
            byte[] kBytes = SerializeUtil.serialize(key);
            byte[] vBytes = jedis.get(kBytes);
            if (vBytes == null) {
                return null;
            }
            return SerializeUtil.deserialize(vBytes);
        }catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

	public boolean remove(String key) {
		if (key == null) {
            throw new IllegalArgumentException("key值不能为空");
        }
        try {
        	Jedis jedis = jedisPool.getResource();
            byte[] kBytes = SerializeUtil.serialize(key);
            jedis.del(kBytes);
            return true;
        }catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}

	private void setExpire(byte[] kBytes,CacheOption option, Jedis jedis) {
        if (option.getExpireType().equals(ExpireType.SECONDS)) {
            int seconds = (int)option.getExpireTime()/1000;
            if(seconds > 0){
                jedis.expire(kBytes, seconds);
            }
        } else {
            jedis.expireAt(kBytes, option.getExpireTime());
        }
    }
	
}
