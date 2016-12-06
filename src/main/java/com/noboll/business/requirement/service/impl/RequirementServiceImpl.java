package com.noboll.business.requirement.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.requirement.dao.RequirementDao;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("requirementService")
public class RequirementServiceImpl extends BaseServiceImpl<Requirement> implements RequirementService {
	@Resource
	private RequirementDao requirementDao;
	
	@Override
	public BaseDao<Requirement> getBaseDao() {
		return requirementDao;
	}
	
}
