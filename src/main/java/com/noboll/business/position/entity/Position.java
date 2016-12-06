package com.noboll.business.position.entity;

import java.sql.Timestamp;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position extends BaseEntity  {

	// Fields
	private String name;
	private String type;
	private String requirement;
	private String duty;
	private Double salaryLow;
	private Double salaryHigh;
	private String address;
	private String status; //1表示发布，0表示未发布
	private Timestamp publishTime;
	private String education;
	private String workLife;
	private String description;
	private Integer num;
	private String deleteFlag;

	// Property accessors
	private String typeName;  // 职位类型
	private String addressName; // 地址
	private String educationName;// 学历
	private String workLifeName;// 工作年限

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRequirement() {
		return this.requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Double getSalaryLow() {
		return this.salaryLow;
	}

	public void setSalaryLow(Double salaryLow) {
		this.salaryLow = salaryLow;
	}

	public Double getSalaryHigh() {
		return this.salaryHigh;
	}

	public void setSalaryHigh(Double salaryHigh) {
		this.salaryHigh = salaryHigh;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getWorkLife() {
		return workLife;
	}

	public void setWorkLife(String workLife) {
		this.workLife = workLife;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getWorkLifeName() {
		return workLifeName;
	}

	public void setWorkLifeName(String workLifeName) {
		this.workLifeName = workLifeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}