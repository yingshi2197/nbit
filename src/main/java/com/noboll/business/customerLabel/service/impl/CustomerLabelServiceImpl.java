package com.noboll.business.customerLabel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.customerLabel.dao.CustomerLabelDao;
import com.noboll.business.customerLabel.entity.CustomerLabel;
import com.noboll.business.customerLabel.service.CustomerLabelService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.util.ArrayUtil;

@Service("customerLabelService")
public class CustomerLabelServiceImpl extends BaseServiceImpl<CustomerLabel> implements CustomerLabelService {
	@Resource
	private CustomerLabelDao customerLabelDao;
	
	@Override
	public BaseDao<CustomerLabel> getBaseDao() {
		return customerLabelDao;
	}
	
	/**
	 * 通过客户id查找
	 */
	public List<CustomerLabel> getByCustomerId(String customerId){
		return customerLabelDao.getByCustomerId(customerId);
	}
	
	/**
	 * 批量新增客户标签
	 */
	public void batchInsert(String customerId, String[] labels){
		List<CustomerLabel> list = new ArrayList<CustomerLabel>();
		String[] uniqueLabels = ArrayUtil.arrayUnique(labels);// 去重
		for (int i = 0; i < uniqueLabels.length; i++) {
			String  label = uniqueLabels[i];
			CustomerLabel customerLabel = new CustomerLabel();
			customerLabel.setCreateUserId(SystemContext.getLoginUser().getId());
			customerLabel.setLastModifyUserId(SystemContext.getLoginUser().getId());
			customerLabel.setCustomerId(customerId);
			customerLabel.setLabelId(label);
			list.add(customerLabel);
		}
		customerLabelDao.batchInsert(list);
	}
	
	/**
	 * 清除客户标签记录
	 */
	public void deleteByCustomerId(String customerId){
		customerLabelDao.deleteByCustomerId(customerId);
	}
	
}
