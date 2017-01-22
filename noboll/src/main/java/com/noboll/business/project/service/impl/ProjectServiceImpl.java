package com.noboll.business.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.noboll.business.project.dao.ProjectDao;
import com.noboll.business.project.entity.Project;
import com.noboll.business.project.service.ProjectService;
import com.noboll.core.base.dao.BaseDao;
import com.noboll.core.base.service.impl.BaseServiceImpl;

@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project> implements ProjectService {

	@Resource
	private ProjectDao projectDao;
	
	public BaseDao<Project> getBaseDao() {
		return projectDao;
	}
	
}
