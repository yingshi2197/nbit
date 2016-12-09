package com.noboll.business.resumePosition.service;

import com.noboll.business.resumePosition.entity.ResumePosition;
import com.noboll.core.base.service.BaseService;

public interface ResumePositionService extends BaseService<ResumePosition> {

	/**
	 * 批量为简历新增意向职位
	 */
	public void batchInsert(String resumeId, String[] positions);

	/**
	 * 根据简历id删除简历意向职位关联
	 * @param id
	 */
	public void deleteByResumeId(String resumeId);


}
