package com.noboll.business.requirement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.requirement.constant.RequirementConstant;
import com.noboll.business.requirement.dao.RequirementDao;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.business.user.entity.User;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;

@Service("requirementService")
public class RequirementServiceImpl extends BaseServiceImpl<Requirement> implements RequirementService {
	@Resource
	private RequirementDao requirementDao;
	
	@Override
	public BaseDao<Requirement> getBaseDao() {
		return requirementDao;
	}

	@Override
	public void saveRequirement(Requirement requirement) {
		requirement.setCustomerId(((User)SystemContext.getLoginUser()).getCustomerId());
		if (!RequirementConstant.REQUIREMENT_STATUS_CG.equals(requirement.getStatus()) 
				&& !RequirementConstant.REQUIREMENT_STATUS_FB.equals(requirement.getStatus())) {// 新增的需求状态只能是草稿或者直接发布
			requirement.setStatus(RequirementConstant.REQUIREMENT_STATUS_CG);
		}
		this.saveEntity(requirement);
	}

	@Override
	public void updateRequirement(Requirement requirement) {
		this.updateEntity(requirement);
	}

	@Override
	public void publish(String id) {
		Requirement requirement = this.getEntity(id);
		if (!RequirementConstant.REQUIREMENT_STATUS_CG.equals(requirement.getStatus()))
			throw new BusinessException("当前需求状态下不允许发布需求！");
		requirement.setStatus(RequirementConstant.REQUIREMENT_STATUS_FB);
		requirementDao.updateStatus(requirement);
	}

	@Override
	public void finish(String id) {
		Requirement requirement = this.getEntity(id);
		if (!RequirementConstant.REQUIREMENT_STATUS_FB.equals(requirement.getStatus()))
			throw new BusinessException("当前需求状态下不允许结束需求！");
		requirement.setStatus(RequirementConstant.REQUIREMENT_STATUS_JS);
		requirementDao.updateStatus(requirement);
	}
	
	/**
	 * 根据用户id得到标签匹配出来的需求
	 * @param userId
	 * @return
	 */
	@Override
	public List<Requirement> getLabelMatchByUserId(String userId){
		return requirementDao.getLabelMatchByUserId(userId);
	}
	
	/**
	 * 根据需求id和用户id查找需求同时得到该用户对该需求的投递状态
	 */
	public Requirement getDeliverStatusById(String id,String userId){
		Requirement requirement = new Requirement();
		requirement.setId(id);
		requirement.setUserId(userId);
		List<Requirement> list = requirementDao.getEntity4Detail(requirement);
		if (null != list && list.size()>0) 
			return list.get(0);
		return null;
	}
	
}
