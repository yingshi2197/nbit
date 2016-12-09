package com.noboll.business.resume.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.position.service.PositionService;
import com.noboll.business.resume.dao.ResumeDao;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.business.resumeIntention.service.ResumeIntentionService;
import com.noboll.business.resumePosition.service.ResumePositionService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;
import com.noboll.core.util.StringUtil;

@Service("resumeService")
public class ResumeServiceImpl extends BaseServiceImpl<Resume> implements ResumeService {

	@Resource
	private ResumeDao resumeDao;
	@Resource
	private PositionService positionService;
	@Resource
	private ResumePositionService resumePositionService;
	@Resource
	private ResumeIntentionService resumeIntentionService;
	
	@Override
	public BaseDao<Resume> getBaseDao() {
		return resumeDao;
	}

	@Override
	public void saveResume(Resume resume) {
		super.saveEntity(resume);
		
		// 处理简历与求职岗位关联关系
		String positionIds =  resume.getPositionIds();
		if (!StringUtil.isEmpty(positionIds)) {
			String[] positions = positionIds.split(",");
			resumePositionService.batchInsert(resume.getId(),positions);
		}
		
		// 处理简历与意向地区关联关系
		String intentionIds = resume.getIntentionIds();
		if (!StringUtil.isEmpty(intentionIds)) {
			String[] intentions = intentionIds.split(",");
			resumeIntentionService.batchInsert(resume.getId(),intentions);
		}
		
	}

	@Override
	public void updateResume(Resume resume) {
		super.updateEntity(resume);
		
		// 处理简历与求职岗位关联关系
		String positionIds =  resume.getPositionIds();
		resumePositionService.deleteByResumeId(resume.getId());// 先全部物理删除，再批量新增
		if (!StringUtil.isEmpty(positionIds)) {
			String[] positions = positionIds.split(",");
			resumePositionService.batchInsert(resume.getId(),positions);
		}
		
		// 处理简历与意向地区关联关系
		resumeIntentionService.deleteByResumeId(resume.getId());// 先全部物理删除，再批量新增
		String intentionIds = resume.getIntentionIds();
		if (!StringUtil.isEmpty(intentionIds)) {
			String[] intentions = intentionIds.split(",");
			resumeIntentionService.batchInsert(resume.getId(),intentions);
		}
	}
	
}
