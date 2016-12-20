package com.noboll.business.user.entity;

import com.noboll.business.user.constant.UserInfoConstant;
import com.noboll.core.base.entity.BaseEntity;

public class UserInfo extends BaseEntity {
	
	private static final long serialVersionUID = -6172803828940075647L;
	
	private String name;
	private String role;// 暂时放这，简单系统没有做用户角色n:n在线维护功能
	private String phone;// 手机
	private String photo;// 头像
	private String sex;// 性别，0表示男，1表示女
	private String email;// 邮箱
	private String customerId;// 对应的客户id
	private String loginId;// 账号
	private String password;// 密码
	private String description;
	private String deleteFlag;// 删除标志,0表示正常，1表示删除
	
	// 扩展属性
	private String sexName;
	private String roleName;//角色名称
	private String customerName;//客户名称
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getSexName() {
		if (UserInfoConstant.SEX_MALE.equals(sex))
			sexName = UserInfoConstant.SEX_MALE_NAME;
		else if (UserInfoConstant.SEX_FEMALE.equals(sex))
			sexName =  UserInfoConstant.SEX_FEMALE_NAME;
		return sexName;
	}
	public void setSexName(String sexName) {
		this.sexName = sexName;
	}
	public String getRoleName() {
		if (UserInfoConstant.ROLE_APPLICANT.equals(role))
			roleName = UserInfoConstant.ROLE_APPLICANT_NAME;
		else if (UserInfoConstant.ROLE_CUSTOMER.equals(role))
			roleName =  UserInfoConstant.ROLE_CUSTOMER_NAME;
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	
}
