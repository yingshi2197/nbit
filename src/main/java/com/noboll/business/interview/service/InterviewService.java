package com.noboll.business.interview.service;


import com.noboll.business.interview.entity.Interview;
import com.noboll.business.resume.entity.Resume;
import com.noboll.core.base.service.BaseService;


public interface InterviewService extends BaseService<Interview> {

	/**
	 * 新增面试
	 */
	public void saveInterview(Interview interview);

	/**
	 * 修改面试
	 */
	public void updateInterview(Interview interview);

	/**
	 * 通过投递记录id得到电话面试记录
	 */
	public Interview getDhByDeliverId(String deliverId);

	/**
	 * 新增\修改电话面试记录
	 */
	public void saveUpdateDhInterview(Interview interview, Resume resume);

	/**
	 * 通过投递记录id得到现场面试记录
	 */
	public Interview getXcByDeliverId(String deliverId);

	/**
	 * 新增\修改电话现场记录
	 */
	public void saveUpdateXcInterview(Interview interview);


}
