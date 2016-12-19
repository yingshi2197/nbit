package com.noboll.business.customer.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.noboll.business.customer.constant.CustomerConstant;
import com.noboll.core.base.entity.BaseEntity;

/**
 * 
* @ClassName: Customer
* @Description: 数据字典项表
* @author weicb
* @date 2016年12月19日 下午20:58:17
*
 */
public class Customer extends BaseEntity  {

	
	private static final long serialVersionUID = -1809932193673773493L;
	
	private String name;//名称
	private String status;//状态，0表示有效，1表示无效
	private String fund;//注册资金
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date foundTime;//成立时间
	private String industry;//行业，数据字典
	private String nature;//性质，数据字典
	private String scale;// 规模，数据字典
	private String address;//地址
	private String description;//描述
	private String deleteFlag;//删除标志,0表示正常，1表示删除
	
	// 扩展字段
	private String statusName;//状态
	private String industryName;//行业，数据字典
	private String natureName;//性质，数据字典
	private String scaleName;// 规模，数据字典
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund = fund;
	}
	public Date getFoundTime() {
		return foundTime;
	}
	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
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
	public String getStatusName() {
		if (CustomerConstant.STATUS_DISABLED.equals(status))
			statusName = CustomerConstant.STATUS_DISABLED_NAME;
		else if (CustomerConstant.STATUS_ENABLED.equals(status))
			statusName =  CustomerConstant.STATUS_ENABLED_NAME;
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getNatureName() {
		return natureName;
	}
	public void setNatureName(String natureName) {
		this.natureName = natureName;
	}
	public String getScaleName() {
		return scaleName;
	}
	public void setScaleName(String scaleName) {
		this.scaleName = scaleName;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
	
}