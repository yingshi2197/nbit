package com.noboll.business.requirement.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Requirement entity. @author MyEclipse Persistence Tools
 * 招聘需求
 */
public class Requirement extends BaseEntity  {

	private static final long serialVersionUID = -1526117072772330182L;
	
	// Fields
	private String id;
	private String name;// 需求名称
	private String customerId;// 客户id
	private String code;// 需求编码
	private String level;// 级别，数据字典
	private Integer num;// 人数
	private String period;//周期，数据字典
	private String positionId; //职位，数据字典
	private String duty;//职责
	private String demand;//要求
	private String address;// 地点，来源数据字典
	private String description;//岗位描述
	private String deleteFlag;
	private String status;// 状态

	// Property accessors
	private String typeName;  // 职位类型
	private String addressName; // 地址
	private String periodName;// 周期名称
	private String levelName;// 级别名称
	private String positionName;//职位名称
	private String statusName;// 状态
	private String deliverStatus;// 是否已经投递，0代表没有，1代表已经投递过，用于需求搜索页(招聘浏览)中判断是否显示投递简历按钮
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getDeliverStatus() {
		return deliverStatus;
	}
	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
	
	

	
}