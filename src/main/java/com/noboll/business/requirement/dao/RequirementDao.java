package com.noboll.business.requirement.dao;

import com.noboll.business.requirement.entity.Requirement;
import com.noboll.core.base.dao.BaseDao;

public interface RequirementDao extends BaseDao<Requirement>{

	/**
	 * 更新需求状态
	 */
	public void updateStatus(Requirement requirement);
	
}
