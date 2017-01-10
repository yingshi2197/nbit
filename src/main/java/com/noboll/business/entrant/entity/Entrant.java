package com.noboll.business.entrant.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.noboll.business.entrant.constant.EntrantConstant;
import com.noboll.core.base.entity.BaseEntity;

/**
 * 
* @ClassName: Entrant
* @Description: 入职表
* @author weicb
* @date 2017年01月10日 下午20:58:17
*
 */
public class Entrant extends BaseEntity  {

	
	private static final long serialVersionUID = 3457996175655027794L;
	
	private String resumeId;//简历id
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enterTime;//入职时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date leaveTime;//离职时间
	private String customerId;//客户id
	private String positionId;//职位id
	private String interviewId;//面试id
	private String pay;//薪资，加密
	private String status;//状态，0表示离职，1表示在职
	private String deliverId;//投递记录
	
	private String description;//描述
	private String deleteFlag;//删除标志,0表示正常，1表示删除
	
	// 扩展字段
	private String positionName;
	private String customerName;
	private String resumeName;
	private String statusName;
	
	public String getResumeId() {
		return resumeId;
	}
	public void setResumeId(String resumeId) {
		this.resumeId = resumeId;
	}
	public Date getEnterTime() {
		return enterTime;
	}
	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getResumeName() {
		return resumeName;
	}
	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}
	public String getStatusName() {
		if (EntrantConstant.STATUS_LEAVE.equals(status))
			statusName = EntrantConstant.STATUS_LEAVE_NAME;
		else if (EntrantConstant.STATUS_ENTER.equals(status))
			statusName =  EntrantConstant.STATUS_ENTER_NAME;
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
}