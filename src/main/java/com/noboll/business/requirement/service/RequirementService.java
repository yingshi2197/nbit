package com.noboll.business.requirement.service;


import java.util.List;

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

	/**
	 * 根据用户id得到标签匹配出来的需求
	 * @param userId
	 * @return
	 */
	public List<Requirement> getLabelMatchByUserId(String userId);
	
	/**
	 * 根据需求id和用户id查找需求同时得到该用户对该需求的投递状态
	 */
	public Requirement getDeliverStatusById(String id,String userId);
	
}
