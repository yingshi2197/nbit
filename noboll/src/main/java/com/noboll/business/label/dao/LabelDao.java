package com.noboll.business.label.dao;

import java.util.List;
import java.util.Map;

import com.noboll.business.label.entity.Label;
import com.noboll.core.base.dao.BaseDao;

public interface LabelDao extends BaseDao<Label>{

	/**
	 * 查找标签选择数据
	 */
	public List<Label> getChooseList(Map<String, Object> map);
	
}
