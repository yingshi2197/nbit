package com.noboll.business.customerLabel.dao;

import java.util.List;

import com.noboll.business.customerLabel.entity.CustomerLabel;
import com.noboll.core.base.dao.BaseDao;

public interface CustomerLabelDao extends BaseDao<CustomerLabel>{

	/**
	 * 批量新增
	 */
	public void batchInsert(List<CustomerLabel> list);

	/**
	 * 清除客户标签记录
	 */
	public void deleteByCustomerId(String customerId);
	
	/**
	 * 得到客户的标签
	 */
	public List<CustomerLabel> getByCustomerId(String customerId);
	
}
