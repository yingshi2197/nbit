package com.noboll.business.customerLabel.service;


import java.util.List;

import com.noboll.business.customerLabel.entity.CustomerLabel;
import com.noboll.core.base.service.BaseService;


public interface CustomerLabelService extends BaseService<CustomerLabel> {

	/**
	 * 通过客户id查找
	 */
	public List<CustomerLabel> getByCustomerId(String customerId);
	
	/**
	 * 批量新增客户标签
	 */
	public void batchInsert(String customerId, String[] labels);

	/**
	 * 清除客户标签记录
	 */
	public void deleteByCustomerId(String customerId);
	
}
