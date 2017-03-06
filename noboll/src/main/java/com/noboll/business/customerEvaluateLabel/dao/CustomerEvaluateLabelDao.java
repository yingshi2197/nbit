package com.noboll.business.customerEvaluateLabel.dao;

import java.util.List;

import com.noboll.business.customerEvaluateLabel.entity.CustomerEvaluateLabel;
import com.noboll.core.base.dao.BaseDao;

public interface CustomerEvaluateLabelDao extends BaseDao<CustomerEvaluateLabel>{

	/**
	 * 批量新增
	 */
	public void batchInsert(List<CustomerEvaluateLabel> list);

	/**
	 * 清除客户标签记录
	 */
	public void deleteByCustomerId(String customerId);
	
	/**
	 * 得到客户的标签
	 */
	public List<CustomerEvaluateLabel> getByCustomerId(String customerId);
	
}
