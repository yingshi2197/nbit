package com.noboll.business.resume.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class Resume extends BaseEntity {

	private static final long serialVersionUID = 7484052749571515649L;
	// Fields
	private String name;
	private String years;// 工作年限，数据字典
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joinTime;// 参加工作时间
	private String school;// 毕业院校
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date finishTime;// 毕业时间
	private String userId;// 对应系统用户id
	private String degree;// 学历，数据字典
	private String mobile;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	private String contact;// 紧急联系人
	private String contactMobile;// 紧急联系人电话
	private String address;
	private String pay;//期望薪资，来源数据字典
	private String native_;//籍贯，数据字典
	private String sex;// 性别，数据字典
	private String description;
	private String deleteFlag;

	// Constructors
	private String degreeName;//学历
	private String payName;//期望薪资
	private String nativeName;//籍贯
	private String sexName;//性别
	private String yearsName;//工作年限
	// Property accessors
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getNative_() {
		return native_;
	}
	public void setNative_(String native_) {
		this.native_ = native_;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getDegreeName() {
		return degreeName;
	}
	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getNativeName() {
		return nativeName;
	}
	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}
	public String getSexName() {
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getYearsName() {
		return yearsName;
	}
	public void setYearsName(String yearsName) {
		this.yearsName = yearsName;
	}

	

	
}