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
		for (int i = 0; i < experiences.length; i++) {
			Experience  experience = experiences[i];
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
