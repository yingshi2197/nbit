package com.noboll.business.wx.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.noboll.core.base.entity.BaseEntity;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class WxResponseObject extends BaseEntity {
	private String code; // 1表示成功，0表示失败
	private String msg; // 成功或者失败的消息
	private Object obj; // 内容
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
