package com.noboll.plugin.message.job;

import java.util.List;

import javax.annotation.Resource;

import com.noboll.plugin.message.entity.Message;
import com.noboll.plugin.message.service.MessageService;
import com.noboll.plugin.message.util.MailUtil;

public class EmailSendTimer {
	@Resource
	private MessageService messageService;
	
	// 查询所有需要发送的邮件
	public void sendEmail() {
		List<Message> list = messageService.getAllEntity(null);
		MailUtil.sendEmail(list);
	}

}
