package com.noboll.plugin.message.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.util.StringUtil;

public class Message extends BaseEntity {
	private String sendName;
	private String sender;
	private String receiver;
	private String copyer;
	private String title;
	private String content;
	private Date sendTime;
	private String reason;
	private String status;
	private String type;
	private String template;
	private String deleteFlag;
	
	
	public List<String> getCopyerList() {
		if(StringUtil.isEmpty(this.copyer)) {
			return new ArrayList<String>();
		}else {
			String[] temp=this.copyer.split(",");
			return Arrays.asList(temp);
		}
	}
	
	
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getCopyer() {
		return copyer;
	}
	public void setCopyer(String copyer) {
		this.copyer = copyer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getSendTime() {
		return sendTime;
	}


	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	public String getDeleteFlag() {
		return deleteFlag;
	}


	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
}
