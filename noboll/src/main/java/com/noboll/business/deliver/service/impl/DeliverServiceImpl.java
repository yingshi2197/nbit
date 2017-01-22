package com.noboll.business.deliver.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.deliver.dao.DeliverDao;
import com.noboll.business.deliver.entity.Deliver;
import com.noboll.business.deliver.service.DeliverService;
import com.noboll.business.dict.constant.DictConstant;
import com.noboll.business.dict.entity.Dict;
import com.noboll.business.dict.service.DictService;
import com.noboll.business.requirement.constant.RequirementConstant;
import com.noboll.business.requirement.entity.Requirement;
import com.noboll.business.requirement.service.RequirementService;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.StringUtil;

@Service("deliverService")
public class DeliverServiceImpl extends BaseServiceImpl<Deliver> implements DeliverService {
	@Resource
	private DeliverDao deliverDao;
	@Resource
	private RequirementService requirementService;
	@Resource
	private ResumeService resumeService;
	@Resource
	private DictService dictService;
	
	@Override
	public BaseDao<Deliver> getBaseDao() {
		return deliverDao;
	}
	
	/**
	 * 投递简历
	 */
	public void saveDeliver(Deliver deliver){
		if (StringUtil.isEmpty(deliver.getResumeId()))
			throw new BusinessException("请选择您要投递的简历！");
		if (StringUtil.isEmpty(deliver.getRequirementId()))
			throw new BusinessException("请选择您要投递的招聘需求！");
		
		
		// 其他业务逻辑判断：需求、简历是否存在、需求是否还在招聘等
		Resume resume = resumeService.getEntity(deliver.getResumeId());
		if (null == resume)
			throw new BusinessException("无效的简历！");
		Requirement requirement = requirementService.getEntity(deliver.getRequirementId());
		if (null == requirement || !RequirementConstant.REQUIREMENT_STATUS_FB.equals(requirement.getStatus()))
			throw new BusinessException("您投递的是无效的招聘需求或者此需求已经停止招聘！");
		// 已经投递过该需求、请勿重复投递
		List<Deliver> delivers = getByRequirementAndUserId(deliver.getRequirementId(),SystemContext.getLoginUser().getId());
		if (null != delivers && delivers.size()>0)
			throw new BusinessException("您已投递过该招聘需求，请勿重复投递！");
		
		deliver.setDeliverTime(new Date());// 简历投递时间
		// 投递状态：待确认
		Dict dict = dictService.getByCode(DictConstant.DICT_CODE_DELIVER_DAIQUEREN);
		deliver.setStatus(dict.getId());
		
		this.saveEntity(deliver);
	}
	
	
	/**
	 * 根据需求id和用户id查找投递记录，防止重复投递
	 */
	public List<Deliver> getByRequirementAndUserId(String requirementId,String userId){
		return deliverDao.getByRequirementAndUserId(requirementId,userId);
	}
	
	/**
	 * 更新投递记录的状态和面试状态
	 */
	public void updateStatus(Deliver deliver){
		deliverDao.updateStatus(deliver);
	}
	
}
