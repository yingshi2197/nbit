package com.noboll.business.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.noboll.core.base.entity.BaseEntity;

public class User extends BaseEntity {
	
	private String name;
	
	private String role;
	
	private String loginId;
	
	private String password;
	
	private String formal;
	
	private List<String> menus=new ArrayList<String>();
	
	private String customerId;// 客户id

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

	public List<String> getMenus() {
		return menus;
	}

	public void setMenus(List<String> menus) {
		this.menus = menus;
	}

	public String getFormal() {
		return formal;
	}

	public void setFormal(String formal) {
		this.formal = formal;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
}
