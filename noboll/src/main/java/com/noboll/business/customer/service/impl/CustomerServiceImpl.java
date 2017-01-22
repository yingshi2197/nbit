package com.noboll.business.customer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.contact.entity.Contact;
import com.noboll.business.contact.service.ContactService;
import com.noboll.business.customer.constant.CustomerConstant;
import com.noboll.business.customer.dao.CustomerDao;
import com.noboll.business.customer.entity.Customer;
import com.noboll.business.customer.service.CustomerService;
import com.noboll.business.customerLabel.service.CustomerLabelService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.JsonUtil;
import com.noboll.core.util.StringUtil;

@Service("customerService")
public class CustomerServiceImpl extends BaseServiceImpl<Customer>
		implements CustomerService {

	@Resource
	private CustomerDao customerDao;
	@Resource
	private CustomerLabelService customerLabelService;
	@Resource
	private ContactService contactService;
	
	@Override
	public BaseDao<Customer> getBaseDao() {
		return customerDao;
	}

	@Override
	public void saveCustomer(Customer customer) {
		this.saveEntity(customer);
		// 标签处理
		String label = customer.getLabel();
		if (!StringUtil.isEmpty(label)) {
			String[] labels = label.split(",");
			customerLabelService.batchInsert(customer.getId(),labels);
		}
		// 客户联系人
		String contactJson = customer.getContactJson();
		if(StringUtil.isEmpty(contactJson))
			throw new BusinessException("请至少添加一条联系人记录！");
		Contact[] contacts = JsonUtil.jsonToObject(contactJson, Contact[].class);
		contactService.batchInsert(customer.getId(),contacts);
	}

	@Override
	public void updateCustomer(Customer customer) {
		this.updateEntity(customer);

		// 标签处理
		customerLabelService.deleteByCustomerId(customer.getId());
		String label = customer.getLabel();
		if (!StringUtil.isEmpty(label)) {
			String[] labels = label.split(",");
			customerLabelService.batchInsert(customer.getId(),labels);
		}
		
		// 客户联系人
		contactService.deleteByCustomerId(customer.getId());
		String contactJson = customer.getContactJson();
		if(StringUtil.isEmpty(contactJson))
			throw new BusinessException("请至少添加一条联系人记录！");
		Contact[] contacts = JsonUtil.jsonToObject(contactJson, Contact[].class);
		contactService.batchInsert(customer.getId(),contacts);
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
