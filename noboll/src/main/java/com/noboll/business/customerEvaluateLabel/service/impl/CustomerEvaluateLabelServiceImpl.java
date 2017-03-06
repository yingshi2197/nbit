package com.noboll.business.customerEvaluateLabel.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.customerEvaluateLabel.dao.CustomerEvaluateLabelDao;
import com.noboll.business.customerEvaluateLabel.entity.CustomerEvaluateLabel;
import com.noboll.business.customerEvaluateLabel.service.CustomerEvaluateLabelService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.util.ArrayUtil;

@Service("customerEvaluateLabelService")
public class CustomerEvaluateLabelServiceImpl extends BaseServiceImpl<CustomerEvaluateLabel> implements CustomerEvaluateLabelService {
	@Resource
	private CustomerEvaluateLabelDao customerEvaluateLabelDao;
	
	@Override
	public BaseDao<CustomerEvaluateLabel> getBaseDao() {
		return customerEvaluateLabelDao;
	}
	
	/**
	 * 通过客户id查找
	 */
	public List<CustomerEvaluateLabel> getByCustomerId(String customerId){
		return customerEvaluateLabelDao.getByCustomerId(customerId);
	}
	
	/**
	 * 批量新增客户标签
	 */
	public void batchInsert(String evaluateId, String customerId, String[] labels){
		List<CustomerEvaluateLabel> list = new ArrayList<CustomerEvaluateLabel>();
		String[] uniqueLabels = ArrayUtil.arrayUnique(labels);// 去重
		for (int i = 0; i < uniqueLabels.length; i++) {
			String  label = uniqueLabels[i];
			CustomerEvaluateLabel customerEvaluateLabel = new CustomerEvaluateLabel();
			customerEvaluateLabel.setCreateUserId(SystemContext.getLoginUser().getId());
			customerEvaluateLabel.setLastModifyUserId(SystemContext.getLoginUser().getId());
			customerEvaluateLabel.setEvaluateId(evaluateId);
			customerEvaluateLabel.setCustomerId(customerId);
			customerEvaluateLabel.setEvaluateLabelId(label);
			list.add(customerEvaluateLabel);
		}
		customerEvaluateLabelDao.batchInsert(list);	
	}
	
	/**
	 * 清除客户标签记录
	 */
	public void deleteByCustomerId(String customerId){
		customerEvaluateLabelDao.deleteByCustomerId(customerId);
	}
	
}
