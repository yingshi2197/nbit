package com.noboll.business.resumeEvaluateLabel.dao;

import java.util.List;

import com.noboll.business.resumeEvaluateLabel.entity.ResumeEvaluateLabel;
import com.noboll.core.base.dao.BaseDao;

public interface ResumeEvaluateLabelDao extends BaseDao<ResumeEvaluateLabel>{

	/**
	 * 批量新增
	 */
	public void batchInsert(List<ResumeEvaluateLabel> list);

	/**
	 * 清除客户标签记录
	 */
	public void deleteByResumeId(String resumeId);
	
	/**
	 * 得到客户的标签
	 */
	public List<ResumeEvaluateLabel> getByResumeId(String resumeId);
	
}
