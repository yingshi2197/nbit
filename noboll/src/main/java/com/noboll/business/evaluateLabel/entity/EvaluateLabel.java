package com.noboll.business.evaluateLabel.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * label entity. @author MyEclipse Persistence Tools
 */

public class EvaluateLabel extends BaseEntity  {

	
	private static final long serialVersionUID = 5640861747786733082L;
	// Fields
	private String name;
	private String ename;
	private String code;
	private String type; // 类型，0客户/1求职者
	private Double score;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	

	
}