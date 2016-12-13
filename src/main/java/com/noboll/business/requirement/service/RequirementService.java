package com.noboll.business.requirement.service;


import com.noboll.business.requirement.entity.Requirement;
import com.noboll.core.base.service.BaseService;


public interface RequirementService extends BaseService<Requirement> {

	/**
	 * 新增需求
	 */
	public void saveRequirement(Requirement requirement);

	/**
	 * 修改需求
	 */
	public void updateRequirement(Requirement requirement);

	/**
	 * 发布需求
	 */
	public void publish(String id);
	
	/**
	 * 结束需求
	 */
	public void finish(String id);
	
}
