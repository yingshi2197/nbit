package com.noboll.business.evaluate.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.customerEvaluateLabel.service.CustomerEvaluateLabelService;
import com.noboll.business.evaluate.dao.EvaluateDao;
import com.noboll.business.evaluate.entity.Evaluate;
import com.noboll.business.evaluate.service.EvaluateService;
import com.noboll.business.resumeEvaluateLabel.service.ResumeEvaluateLabelService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.util.StringUtil;

@Service("evaluateService")
public class EvaluateServiceImpl extends BaseServiceImpl<Evaluate> implements EvaluateService {
	@Resource
	private EvaluateDao evaluateDao;
	@Resource
	private CustomerEvaluateLabelService customerEvaluateLabelService;
	@Resource
	private ResumeEvaluateLabelService resumeEvaluateLabelService;
	
	@Override
	public BaseDao<Evaluate> getBaseDao() {
		return evaluateDao;
	}
	
	/**
	 * 新增评价(客户对简历的评价)
	 */
	public void saveEvaluate4Resume(Evaluate evaluate){
		Evaluate evaluateData = this.getByResumeAndCustomerId(evaluate.getResumeId(), evaluate.getCustomerId());
		if (null == evaluateData) {
			this.saveEntity(evaluate);
		}else{// 对简历的评价
			evaluateData.setREvaluate(evaluate.getREvaluate());
			evaluateData.setREvaluateStar(evaluate.getREvaluateStar());
			this.updateEntity(evaluateData);
		}
		// 评价标签处理
		String label = evaluate.getLabel();
		if (!StringUtil.isEmpty(label)) {
			String[] labels = label.split(",");
			resumeEvaluateLabelService.batchInsert(evaluate.getId(),evaluate.getResumeId(),labels);
		}
	}
	/**
	 * 新增评价(简历对客户的评价)
	 */
	public void saveEvaluate4Customer(Evaluate evaluate){
		Evaluate evaluateData = this.getByResumeAndCustomerId(evaluate.getResumeId(), evaluate.getCustomerId());
		if (null == evaluateData) {
			this.saveEntity(evaluate);
		}else{// 对客户的评价  
			evaluateData.setCEvaluate(evaluate.getCEvaluate());
			evaluateData.setCEvaluateStar(evaluate.getCEvaluateStar());
			this.updateEntity(evaluateData);
		}
		// 评价标签处理
		String label = evaluate.getLabel();
		if (!StringUtil.isEmpty(label)) {
			String[] labels = label.split(",");
			customerEvaluateLabelService.batchInsert(evaluate.getId(),evaluate.getCustomerId(),labels);
		}
	}
	
	/**
	 * 根据简历id查找评价记录
	 */
	public List<Evaluate> getByResumeId(String resumeId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resumeId", resumeId);
		return evaluateDao.getAllEntity(param);
	}
	
	/**
	 * 根据简历id查找评价记录
	 */
	public List<Evaluate> getByCustomerId(String customerId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("customerId", customerId);
		return evaluateDao.getAllEntity(param);
	}

	/**
	 * 根据简历id和客户id查找评价记录
	 */
	public Evaluate getByResumeAndCustomerId(String resumeId,String customerId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resumeId", resumeId);
		param.put("customerId", customerId);
		List<Evaluate> evaluates= evaluateDao.getAllEntity(param);
		if (null != evaluates && evaluates.size() > 0)
			return evaluates.get(0);
		return null;
	}
	
}
