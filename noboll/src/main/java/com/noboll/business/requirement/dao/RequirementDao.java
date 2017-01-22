package com.noboll.business.requirement.dao;

import java.util.List;

import com.noboll.business.requirement.entity.Requirement;
import com.noboll.core.base.dao.BaseDao;

public interface RequirementDao extends BaseDao<Requirement>{

	/**
	 * 更新需求状态
	 */
	public void updateStatus(Requirement requirement);

	/**
	 * 根据用户id得到标签匹配出来的需求
	 * @param userId
	 * @return
	 */
	public List<Requirement> getLabelMatchByUserId(String userId);
	
	/**
	 * 根据条件得到需求
	 */
	public List<Requirement> getEntity4Detail(Requirement requirement);
	
}
