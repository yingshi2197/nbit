package com.noboll.business.resumeIntention.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class ResumeIntention extends BaseEntity {

	private static final long serialVersionUID = 8769740528942963282L;
	// Fields
	private String resumeId;
	private String intention;// 意向地区
	private String deleteFlag;
	
	private String intentionName;// 意向地区
	
	
	public ResumeIntention() {
		super();
	}
	public ResumeIntention(String resumeId, String intention) {
		super();
		this.resumeId = resumeId;
		this.intention = intention;
	}
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getIntention() {
		return intention;
	}
	public void setIntention(String intention) {
		this.intention = intention;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getIntentionName() {
		return intentionName;
	}
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	
	// Constructors
	
	// Property accessors
	

	

	
}