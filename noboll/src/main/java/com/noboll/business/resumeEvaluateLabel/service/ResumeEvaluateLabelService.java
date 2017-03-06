package com.noboll.business.resumeEvaluateLabel.service;


import java.util.List;

import com.noboll.business.resumeEvaluateLabel.entity.ResumeEvaluateLabel;
import com.noboll.core.base.service.BaseService;


public interface ResumeEvaluateLabelService extends BaseService<ResumeEvaluateLabel> {

	/**
	 * 通过客户id查找
	 */
	public List<ResumeEvaluateLabel> getByResumeId(String resumeId);
	
	/**
	 * 批量新增客户评价标签
	 */
	public void batchInsert(String evaluateId, String resumeId, String[] labels);

	/**
	 * 清除客户评价标签记录
	 */
	public void deleteByResumeId(String resumeId);
	
}
