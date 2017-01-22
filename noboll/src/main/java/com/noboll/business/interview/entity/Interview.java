package com.noboll.business.interview.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.noboll.core.base.entity.BaseEntity;

/**
 * 
* @ClassName: Interview
* @Description: 面试表
* @author weicb
* @date 2016年12月26日 下午20:58:17
*
 */
public class Interview extends BaseEntity  {

	
	private static final long serialVersionUID = -1809932193673773493L;
	
	private String name;//名称
	private String deliverId;//投递id
	private String userId;//用户
	private String customerId;//客户
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date time;//面试时间
	private String address;//面试地址
	private String interviewer;//面试官
	private String type;//面试类型，数据字典
	private String result;//面试结果，数据字典
	private String reason;//原因
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date nextTime;//下次面试时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enterTime;//入职日期
	private String description;//描述
	private String deleteFlag;//删除标志,0表示正常，1表示删除
	
	// 扩展字段
	private String typeName;//类型
	private String resultName;//结果
	private String resultCode;// 结果编码
	private String resumeId;//简历
	private String resumeName;//简历
	private String customerName;//客户
	private String positionName;//岗位
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getNextTime() {
		return nextTime;
	}
	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	
	
	
}