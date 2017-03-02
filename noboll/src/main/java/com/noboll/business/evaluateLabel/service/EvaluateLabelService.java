package com.noboll.business.evaluateLabel.service;


import java.util.List;
import java.util.Map;

import com.noboll.business.evaluateLabel.entity.EvaluateLabel;
import com.noboll.core.base.service.BaseService;


public interface EvaluateLabelService extends BaseService<EvaluateLabel> {

	/**
	 * 查找标签选择数据
	 */
	public List<EvaluateLabel> getChooseList(Map<String, Object> map);
	
}
