package com.noboll.business.customer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.customer.constant.CustomerConstant;
import com.noboll.business.customer.dao.CustomerDao;
import com.noboll.business.customer.entity.Customer;
import com.noboll.business.customer.service.CustomerService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("customerService")
public class CustomerServiceImpl extends BaseServiceImpl<Customer>
		implements CustomerService {

	@Resource
	private CustomerDao customerDao;
	
	@Override
	public BaseDao<Customer> getBaseDao() {
		return customerDao;
	}

	@Override
	public void saveCustomer(Customer customer) {
		this.saveEntity(customer);
	}

	@Override
	public void updateCustomer(Customer customer) {
		this.updateEntity(customer);
	}

	@Override
	public void enable(String id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setStatus(CustomerConstant.STATUS_ENABLED);
		customerDao.updateStatus(customer);
	}

	@Override
	public void disable(String id) {
		Customer customer = new Customer();
		customer.setId(id);
		customer.setStatus(CustomerConstant.STATUS_DISABLED);
		customerDao.updateStatus(customer);
	}


}
