package com.noboll.core.component.lock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.core.cache.service.CacheService;
import com.noboll.core.component.lock.service.LockService;
import com.noboll.core.exception.BusinessException;

@Service("lockService")
public class LockServiceImpl implements LockService {
	 // 单个锁的等待时间 
    private static final int DEFAULT_SINGLE_DELAY_TIME = 3;  
   
    @Resource
    private CacheService cacheService;
      
    /** 
     * 获取锁 如果锁可用 立即返回true， 否则返回false，不等待 
     *  
     * @return 
     */  
    @Override  
    public boolean tryLockNoWait(String key) {  
    	try {  
            if (null==cacheService.get(key)) {  
            	cacheService.put(key, key);  
            	//先不设置过期时间，防止一个事务过长导致并发引起的问题  
            	//CacheUtil.expire(key, DEFAULT_SINGLE_EXPIRE_TIME); 
                return true;  
            } else {
            	 return Boolean.FALSE;
            }
        } catch (Exception e) {  
            e.printStackTrace();
        }
        return Boolean.FALSE;  
    }  
  
    /** 
     * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false 
     *  
     * @param timeout 
     * @param unit 
     * @return 
     */  
    @Override  
    public boolean tryLockWait(String key) {  
        try {  
        	
        	long begin=System.currentTimeMillis();
            while(DEFAULT_SINGLE_DELAY_TIME*1000>(System.currentTimeMillis()-begin))  {
                if (cacheService.put(key, key)) {  
                    //先不设置过期时间，防止一个事务过长导致并发引起的问题  
                	//CacheUtil.expire(key, DEFAULT_SINGLE_EXPIRE_TIME);  
                    //成功获取锁，返回true  
                    return true;  
                } 
            } 
            return false;  
        } catch (Exception e) {  
            e.printStackTrace();
            return false; 
        }
    }  
  
    /** 
     * 释放锁 
     */  
    @Override  
    public void unLock(String key) {  
        try {  
        	cacheService.remove(key);  
        } catch (Exception e) {  
            throw new BusinessException("释放锁失败！");
        } 
    }  
  
    
}
