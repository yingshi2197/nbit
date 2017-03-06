package com.noboll.business.customerEvaluateLabel.service;


import java.util.List;

import com.noboll.business.customerEvaluateLabel.entity.CustomerEvaluateLabel;
import com.noboll.core.base.service.BaseService;


public interface CustomerEvaluateLabelService extends BaseService<CustomerEvaluateLabel> {

	/**
	 * 通过客户id查找
	 */
	public List<CustomerEvaluateLabel> getByCustomerId(String customerId);
	
	/**
	 * 批量新增客户评价标签
	 */
	public void batchInsert(String evaluateId, String customerId, String[] labels);

	/**
	 * 清除客户评价标签记录
	 */
	public void deleteByCustomerId(String customerId);
	
}
