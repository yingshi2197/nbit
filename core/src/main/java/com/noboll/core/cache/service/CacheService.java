package com.noboll.core.cache.service;

import com.noboll.core.cache.entity.CacheOption;

public interface CacheService {
	/**
    * 判断是否包含key值
    * @param key 
    * @return 
    */
   public boolean contains(String key);

   /**
    * 存储
    * @param key 
    * @param value
    * @return 
    */
   public  boolean put(String key, Object value);

   /**
    * 存储，有时效
    * @param key 
    * @param value
    * @param option
    * @return 
    */
   public boolean put(String key, Object value, CacheOption option);

   /**
    * 根据key值获取对象
    * @param key 
    * @param type
    * @return 
    */
   public Object get(String key);

   /**
    * 删除key
    * @param key 
    * @return
    */
   public boolean remove(String key);
}
