package com.noboll.business.evaluateLabel.dao;

import java.util.List;
import java.util.Map;

import com.noboll.business.evaluateLabel.entity.EvaluateLabel;
import com.noboll.core.base.dao.BaseDao;

public interface EvaluateLabelDao extends BaseDao<EvaluateLabel>{

	/**
	 * 查找标签选择数据
	 */
	public List<EvaluateLabel> getChooseList(Map<String, Object> map);
	
}
