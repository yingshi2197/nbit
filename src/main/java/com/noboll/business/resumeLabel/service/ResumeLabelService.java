package com.noboll.business.resumeLabel.service;


import java.util.List;

import com.noboll.business.resumeLabel.entity.ResumeLabel;
import com.noboll.core.base.service.BaseService;


public interface ResumeLabelService extends BaseService<ResumeLabel> {

	/**
	 * 批量新增简历标签
	 */
	public void batchInsert(String resumeId, String[] labels);

	/**
	 * 清除简历标签记录
	 */
	public void deleteByResumeId(String resumeId);

	/**
	 * 通过简历id获取记录
	 */
	public List<ResumeLabel> getByResumeId(String resumeId);
	
}
