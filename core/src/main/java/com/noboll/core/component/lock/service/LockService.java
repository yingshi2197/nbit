package com.noboll.core.component.lock.service;


public interface LockService {
	/** 
     * 获取锁  如果锁可用   立即返回true，  否则返回false  
     */  
   public boolean tryLockNoWait(String key);  
    /** 
     * 锁在给定的等待时间内空闲，则获取锁成功 返回true， 否则返回false  
     */  
    boolean tryLockWait(String key);     
    /** 
     * 释放锁 
     */  
    void unLock(String key);  
   
}
