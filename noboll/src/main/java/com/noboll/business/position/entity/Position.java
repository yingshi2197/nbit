package com.noboll.business.position.entity;

import com.noboll.core.base.entity.BaseEntity;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */

public class Position extends BaseEntity  {

	private static final long serialVersionUID = -7181864412657894207L;
	
	// Fields
	private String name;
	private String type;
	private String ename;
	private String code;
	private String description;
	private String deleteFlag;
	
	private String typeName;// 职位类型名称
	private String typeCode;// 职位类型编码
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	

	
}