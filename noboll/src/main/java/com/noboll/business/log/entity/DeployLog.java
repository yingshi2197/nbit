package com.noboll.business.log.entity;

import java.util.Date;

import com.noboll.core.base.entity.BaseEntity;

/**
 * DeployLog entity. @author MyEclipse Persistence Tools
 */

public class DeployLog extends BaseEntity  {

	// Fields

	private String projectId;
	private String version;
	private Date deployTime;
	private String status; // 1表示成功，0表示失败，2表示部署中，5表示未部署
	private String reason;
	private String source;
	private String target;
	private String path;
	private String description;
	private String deleteFlag;
	private String increase;
	private String start;
	private Date startTime;
	private String container;
	
	private String projectName;
	private String code;
	// Constructors

	// Property accessors

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDeployTime() {
		return this.deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIncrease() {
		return increase;
	}

	public void setIncrease(String increase) {
		this.increase = increase;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}
	
}