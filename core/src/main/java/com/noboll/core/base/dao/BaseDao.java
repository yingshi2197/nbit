package com.noboll.core.base.dao;

import java.util.List;
import java.util.Map;

import com.noboll.core.base.entity.BaseEntity;

public interface BaseDao<T extends BaseEntity> {
	public T getEntity(String id);
	
	public void saveEntity(T entity);
	
	public void updateEntity(T entity);
	
	public void deleteEntity(String id);
	
	public List<T> getAllEntity(Map<String,Object> param);
}
