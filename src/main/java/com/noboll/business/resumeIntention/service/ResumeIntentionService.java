package com.noboll.business.resumeIntention.service;

import com.noboll.business.resumeIntention.entity.ResumeIntention;
import com.noboll.core.base.service.BaseService;

public interface ResumeIntentionService extends BaseService<ResumeIntention> {

	/**
	 * 批量为简历新增意向地区
	 */
	public void batchInsert(String resumeId, String[] intentions);

	/**
	 * 根据简历id删除意向地区关联
	 * @param id
	 */
	public void deleteByResumeId(String resumeId);


}
