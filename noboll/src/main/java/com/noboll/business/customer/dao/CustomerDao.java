package com.noboll.business.customer.dao;

import com.noboll.business.customer.entity.Customer;
import com.noboll.core.base.dao.BaseDao;

public interface CustomerDao extends BaseDao<Customer>{

	/**
	 * 更新客户状态
	 */
	public void updateStatus(Customer customer);
	
}
