package com.noboll.core.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.base.service.BaseService;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>  {
	
	@Resource
	private SqlSessionTemplate sqlSession;
	 

	public abstract BaseDao<T> getBaseDao();
	
	public T getEntity(String id) {
		return getBaseDao().getEntity(id);
	}

	public void saveEntity(T entity) {
		getBaseDao().saveEntity(entity);
	}
	
	public void deleteEntity(String id) {
		getBaseDao().deleteEntity(id);
	}

	public void updateEntity(T entity) {
		getBaseDao().updateEntity(entity);		
	}

	public List<T> getAllEntity(Map<String,Object> param) {
		return getBaseDao().getAllEntity(param);
	}
	
	public Page<T> getPageList(String sqlId,QueryParam queryParam, Page<T> page) {		
            PageBounds pageBounds = new PageBounds(page.getCurrent(), page.getPageSize());
            if(null!=queryParam&&null!=queryParam.getOrderList()&&queryParam.getOrderList().size()>0) {
            	pageBounds.setOrders(queryParam.getOrderList());
            }
            Map<String,Object> sqlParameter=null!=queryParam ? queryParam.getMap() : new HashMap<String,Object>();
            
            List<T> rows=sqlSession.selectList(sqlId,sqlParameter, pageBounds);
            
            PageList<T> pageList=(PageList<T>) rows;
            page.setRows(rows);
            page.setTotal(pageList.getPaginator().getTotalCount());
            return page;
    }
}
