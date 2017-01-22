package com.noboll.business.customer.service;


import com.noboll.business.customer.entity.Customer;
import com.noboll.core.base.service.BaseService;


public interface CustomerService extends BaseService<Customer> {

	/**
	 * 新增客户
	 */
	public void saveCustomer(Customer customer);

	/**
	 * 修改客户
	 */
	public void updateCustomer(Customer customer);

	/**
	 * 启用客户
	 */
	public void enable(String id);

	/**
	 * 禁用客户
	 */
	public void disable(String id);

}
