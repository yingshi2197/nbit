package com.noboll.business.label.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * label entity. @author MyEclipse Persistence Tools
 */

public class Label extends BaseEntity  {

	
	private static final long serialVersionUID = 5640861747786733082L;
	// Fields
	private String name;
	private String ename;
	private String code;
	private String description;
	private String deleteFlag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	

	
}