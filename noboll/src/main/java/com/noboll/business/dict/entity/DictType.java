package com.noboll.business.dict.entity;

import java.util.Date;

import com.noboll.core.base.entity.BaseEntity;

/**
 * 
* @ClassName: SysDictionaryType
* @Description: 数据字典类型表
* @author kent.wang@noboll.com.cn
* @date 2015年4月29日 下午12:58:31
*
 */
public class DictType extends BaseEntity  {

	private static final long serialVersionUID = -5726551780728272421L;
	// Fields
	private String name;
	private String code;
	private String description;
	private String deleteFlag;
	
	// Constructors

	// Property accessors


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastModifyUserId() {
		return this.lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}