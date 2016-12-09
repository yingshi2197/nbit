package com.noboll.business.resume.service;

import com.noboll.business.resume.entity.Resume;
import com.noboll.core.base.service.BaseService;

public interface ResumeService extends BaseService<Resume> {

	/**
	 * 新增简历
	 * @param resume
	 */
	public void saveResume(Resume resume);

	/**
	 * 修改简历
	 * @param resume
	 */
	public void updateResume(Resume resume);

}
