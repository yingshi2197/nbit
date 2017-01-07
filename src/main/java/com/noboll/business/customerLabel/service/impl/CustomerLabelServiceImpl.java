package com.noboll.business.customerLabel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.customerLabel.dao.CustomerLabelDao;
import com.noboll.business.customerLabel.entity.CustomerLabel;
import com.noboll.business.customerLabel.service.CustomerLabelService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("customerLabelService")
public class CustomerLabelServiceImpl extends BaseServiceImpl<CustomerLabel> implements CustomerLabelService {
	@Resource
	private CustomerLabelDao customerLabelDao;
	
	@Override
	public BaseDao<CustomerLabel> getBaseDao() {
		return customerLabelDao;
	}
	
}
