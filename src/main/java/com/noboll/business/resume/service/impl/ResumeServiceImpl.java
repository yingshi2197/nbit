package com.noboll.business.resume.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.position.service.PositionService;
import com.noboll.business.resume.dao.ResumeDao;
import com.noboll.business.resume.entity.Resume;
import com.noboll.business.resume.service.ResumeService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("resumeService")
public class ResumeServiceImpl extends BaseServiceImpl<Resume> implements ResumeService {

	@Resource
	private ResumeDao resumeDao;
	@Resource
	private PositionService positionService;
	
	@Override
	public BaseDao<Resume> getBaseDao() {
		return resumeDao;
	}
	
}
