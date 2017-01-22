package com.noboll.core.base.entity;

public class OperateMessage {
	private String type;
	private String msg;
	private Object obj;
	
	public OperateMessage() {
		
	}
	
	public OperateMessage(String type,String msg) {
		this.type=type;
		this.msg=msg;				
	}
	
	public OperateMessage(String type,String msg,Object obj) {
		this.type=type;
		this.msg=msg;		
		this.obj=obj;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
