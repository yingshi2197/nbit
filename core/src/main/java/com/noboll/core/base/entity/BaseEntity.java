package com.noboll.core.base.entity;

import java.io.Serializable;
import java.util.Date;

import com.noboll.core.annotation.CreatedBy;
import com.noboll.core.annotation.CreatedDate;
import com.noboll.core.annotation.LastModifiedBy;
import com.noboll.core.annotation.LastModifiedDate;

/**
 * 基础类
 * @author 00705
 *
 */
public abstract class BaseEntity implements Serializable {
	protected String id;	
	@CreatedDate
	protected Date createTime;
	@LastModifiedDate
	protected Date lastModifyTime;
	@CreatedBy
	protected String createUserId;
	@LastModifiedBy
	protected String lastModifyUserId;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId;
	}
}
