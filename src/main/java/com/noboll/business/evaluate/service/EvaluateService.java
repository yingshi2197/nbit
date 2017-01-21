package com.noboll.business.evaluate.service;


import java.util.List;

import com.noboll.business.evaluate.entity.Evaluate;
import com.noboll.core.base.service.BaseService;


public interface EvaluateService extends BaseService<Evaluate> {

	/**
	 * 新增评价(客户对简历的评价)
	 */
	public void saveEvaluate4Resume(Evaluate evaluate);
	/**
	 * 新增评价(简历对客户的评价)
	 */
	public void saveEvaluate4Customer(Evaluate evaluate);
	
	/**
	 * 根据简历id查找评价记录
	 */
	public List<Evaluate> getByResumeId(String resumeId);
	
	/**
	 * 根据简历id查找评价记录
	 */
	public List<Evaluate> getByCustomerId(String customerId);

	/**
	 * 根据简历id和客户id查找评价记录
	 */
	public Evaluate getByResumeAndCustomerId(String resumeId,String customerId);
	
}
