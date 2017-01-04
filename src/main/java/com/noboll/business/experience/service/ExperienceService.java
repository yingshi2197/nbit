package com.noboll.business.experience.service;

import java.util.List;

import com.noboll.business.experience.entity.Experience;
import com.noboll.core.base.service.BaseService;

public interface ExperienceService extends BaseService<Experience> {

	/**
	 * 批量为简历新增工作经历
	 */
	public void batchInsert(String resumeId,Experience[] experiences);

	/**
	 * 根据简历id删除简历工作经历关联
	 */
	public void deleteByResumeId(String resumeId);

	/**
	 * 得到简历的工作经历
	 */
	public List<Experience> getByResumeId(String resumeId);


}
