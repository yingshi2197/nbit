package com.noboll.quartz;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.noboll.business.log.entity.DeployLog;
import com.noboll.business.log.service.DeployLogService;

public class ProjectDeployTimer {
	@Resource
	private DeployLogService deployLogService;
	
	// 定时发布项目
	public void deployProject() {
		System.out.println("定时发布项目");
		List<DeployLog> list=deployLogService.queryDeployLogs(new Date(), "5");
		for(DeployLog dl:list) {
			deployLogService.deploy(dl,"timer");
		}
	}
	
}
