package com.noboll.business.resumeLabel.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * CustomerLabel entity. @author MyEclipse Persistence Tools
 */

public class ResumeLabel extends BaseEntity  {

	
	private static final long serialVersionUID = -5928815291124820743L;
	// Fields
	private String resumeId;
	private String labelId;
	private String deleteFlag;
	
	
	private String name;// 标签名称，辅助字段，供简历修改时标签选择器初始化用
	
	
	
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
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