package com.noboll.business.customerLabel.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * CustomerLabel entity. @author MyEclipse Persistence Tools
 */

public class CustomerLabel extends BaseEntity  {

	
	private static final long serialVersionUID = -3823348229696738910L;
	// Fields
	private String customerId;
	private String labelId;
	private String deleteFlag;
	private String name;// 标签名称，辅助字段，供修改时标签选择器初始化用
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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