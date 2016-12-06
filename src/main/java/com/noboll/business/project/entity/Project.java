package com.noboll.business.project.entity;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.util.StringUtil;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */

public class Project extends BaseEntity {

	// Fields

	private String name;
	private String code;
	private String source;
	private String target;
	private String path;
	private String description;
	private String deleteFlag;
	private String container;
	private String port;
	private String formal;

	// Constructors

	// Property accessors
	public String getAccessAddress() {
		return "http://"+this.target+(StringUtil.isEmpty(this.port) ? "" : (":"+this.port))+"/"+this.code;
	}

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

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getContainer() {
		return container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFormal() {
		return formal;
	}

	public void setFormal(String formal) {
		this.formal = formal;
	}

	
}