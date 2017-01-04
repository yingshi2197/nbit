package com.noboll.business.experience.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Experience entity. @author MyEclipse Persistence Tools
 */

public class Experience extends BaseEntity {

	private static final long serialVersionUID = -5646107239123918708L;
	// Fields
	private String name;
	private String resumeId;// 简历
	private String positionId;// 职位
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startTime;// 开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endTime;// 结束时间
	private String description;
	private String duty;// 职责
	private String deleteFlag;
	
	
	
	private String positionName;// 职位名称
	
	
	// Property accessors
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	
}