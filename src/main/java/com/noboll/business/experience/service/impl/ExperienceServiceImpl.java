package com.noboll.business.experience.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.experience.dao.ExperienceDao;
import com.noboll.business.experience.entity.Experience;
import com.noboll.business.experience.service.ExperienceService;
import com.noboll.context.SystemContext;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.util.StringUtil;

@Service("experienceService")
public class ExperienceServiceImpl extends BaseServiceImpl<Experience> implements ExperienceService {

	@Resource
	private ExperienceDao experienceDao;
	
	@Override
	public BaseDao<Experience> getBaseDao() {
		return experienceDao;
	}

	/**
	 * 批量为简历新增工作经历
	 */
	@Override
	public void batchInsert(String resumeId,Experience[] experiences){
		List<Experience> list = new ArrayList<Experience>();
		StringBuffer message = new StringBuffer();
		for (int i = 0; i < experiences.length; i++) {
			Experience  experience = experiences[i];
			if (StringUtil.isEmpty(experience.getPositionId()))
				message.append("第【"+(i+1)+"】行项目名称不能为空!");
			if (StringUtil.isEmpty(experience.getPositionId()))
				message.append("【"+experience.getName()+"】担任职务不能为空!");
			if (null == experience.getStartTime() || null == experience.getEndTime())
				message.append("【"+experience.getName()+"】开始时间或结束时间不能为空!");
			if (experience.getStartTime().getTime()>experience.getEndTime().getTime())
				message.append("【"+experience.getName()+"】开始时间不能大于结束时间!");
			if (StringUtil.isEmpty(experience.getDuty()))
				message.append("【"+experience.getName()+"】职责不能为空!");
			if (StringUtil.isEmpty(experience.getDescription()))
				message.append("【"+experience.getName()+"】项目描述不能为空!");
			experience.setCreateUserId(SystemContext.getLoginUser().getId());
			experience.setLastModifyUserId(SystemContext.getLoginUser().getId());
			experience.setResumeId(resumeId);
			list.add(experience);
		}
		experienceDao.batchInsert(list);
	}

	@Override
	public void deleteByResumeId(String resumeId) {
		experienceDao.deleteByResumeId(resumeId);
	}
	
	/**
	 * 得到简历的工作经历
	 */
	public List<Experience> getByResumeId(String resumeId){
		return experienceDao.getByResumeId(resumeId);
	}

}
