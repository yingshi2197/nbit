package com.noboll.business.log.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.noboll.business.log.entity.DeployLog;
import com.noboll.core.base.service.BaseService;

public interface DeployLogService extends BaseService<DeployLog> {
	// type=timer 表示是定时部署，允许为空
	public void deploy(final DeployLog entity,String type);
	
	public void saveEntity(final DeployLog entity,MultipartFile file);
	
	public List<DeployLog> queryDeployLogs(Date date,String status);
}
