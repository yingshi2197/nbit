package com.noboll.business.deliver.service;


import java.util.List;

import com.noboll.business.deliver.entity.Deliver;
import com.noboll.core.base.service.BaseService;


public interface DeliverService extends BaseService<Deliver> {

	/**
	 * 投递简历
	 */
	public void saveDeliver(Deliver deliver);
	
	/**
	 * 根据需求id和用户id查找投递记录，防止重复投递
	 * @param requirementId 需求id
	 * @param userId 用户id
	 */
	public List<Deliver> getByRequirementAndUserId(String requirementId,String userId);
	
}
