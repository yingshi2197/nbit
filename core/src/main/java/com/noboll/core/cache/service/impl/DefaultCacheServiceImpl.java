package com.noboll.core.cache.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.noboll.core.cache.entity.CacheOption;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.exception.BusinessException;

public class DefaultCacheServiceImpl implements CacheService {
	
	private  static  ConcurrentHashMap<String, Object> cache=new ConcurrentHashMap<String, Object>();
	
	public boolean contains(String key) {
		return cache.contains(key);
	}

	public boolean put(String key, Object value) {
		try{
			cache.put(key, value);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean put(String key, Object value, CacheOption option) {
		throw new BusinessException("请实现该方法");
	}

	public Object get(String key) {
		return cache.get(key);
	}

	@Override
	public boolean remove(String key) {
		try{
			cache.remove(key);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}
