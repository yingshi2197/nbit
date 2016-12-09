package com.noboll.business.resumePosition.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class ResumePosition extends BaseEntity {

	private static final long serialVersionUID = 8769740528942963282L;
	// Fields
	private String resumeId;
	private String positionId;// 岗位
	private String deleteFlag;
	
		
	private String positionName;// 岗位名称
	// Constructors
	
	// Property accessors
	public String getResumeId() {
		return resumeId;
	}
	public ResumePosition() {
		super();
	}
	public ResumePosition(String resumeId, String positionId) {
		super();
		this.resumeId = resumeId;
		this.positionId = positionId;
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