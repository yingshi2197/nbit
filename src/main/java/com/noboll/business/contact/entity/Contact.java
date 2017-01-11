package com.noboll.business.contact.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * 
* @ClassName: Contact
* @Description: 客户联系人表
* @author weicb
* @date 2017年01月11日 下午21:28:17
*
 */
public class Contact extends BaseEntity  {

	private static final long serialVersionUID = -7774108441278771760L;
	
	private String name;//姓名
	private String phone;//电话
	private String customerId;//客户id
	private String positionId;//职位id
	private String sex;//性别，0表示男，1表示女
	
	private String description;//描述
	private String deleteFlag;//删除标志,0表示正常，1表示删除
	
	// 扩展字段
	private String positionName;
	private String customerName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	
	
	
}