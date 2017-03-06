package com.noboll.business.resumeEvaluateLabel.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * ResumeLabel entity. @author MyEclipse Persistence Tools
 */

public class ResumeEvaluateLabel extends BaseEntity  {

	
	private static final long serialVersionUID = -3823348229696738910L;
	// Fields
	private String resumeId;
	private String evaluateId;// 评价id
	private String evaluateLabelId;// 评价标签id
	private String deleteFlag;
	private String name;// 标签名称，辅助字段，供修改时标签选择器初始化用
	
	
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getEvaluateId() {
		return evaluateId;
	}
	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}
	public String getEvaluateLabelId() {
		return evaluateLabelId;
	}
	public void setEvaluateLabelId(String evaluateLabelId) {
		this.evaluateLabelId = evaluateLabelId;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

	
}