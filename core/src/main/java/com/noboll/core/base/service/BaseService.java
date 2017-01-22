package com.noboll.core.base.service;

import java.util.List;
import java.util.Map;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;

public interface BaseService<T extends BaseEntity> {
	
	// 查询实体对象
	public T getEntity(String id);
	// 查询实体对象
	public void deleteEntity(String id);
	// 保存实体对象
	public void saveEntity(T entity);
	// 更新实体对象
	public void updateEntity(T entity);
	// 查找所有实体对象
	public List<T> getAllEntity(Map<String,Object> param);
	// 分页查找所有对象
	public Page<T> getPageList(String sqlId,QueryParam queryParam, Page<T> page);
	
}
