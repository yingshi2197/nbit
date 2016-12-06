package com.noboll.business.user.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.util.StringUtil;

public class RoleMenu extends BaseEntity {
	
	private String role;
	
	private String menu;
	
	private String formal;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getFormal() {
		return formal;
	}

	public void setFormal(String formal) {
		this.formal = formal;
	}
	
	public List<String> getAllMenu() {
		if(StringUtil.isEmpty(this.menu)) {
			return new ArrayList<String>();
		}else {
			String[] temp=this.menu.split(",");
			return Arrays.asList(temp);
		}
	}
	
}
