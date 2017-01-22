package com.noboll.plugin.message.util;

import java.util.ArrayList;
import java.util.List;

import com.noboll.plugin.message.entity.Message;
import com.noboll.plugin.message.listener.EmailSendListenerImp;
import com.noboll.plugin.message.server.EmailServer;

public class MailUtil {
	// 发送邮件
	public static void sendEmail(List<Message> list){
		EmailServer es=new EmailServer();
		es.addEmailListener(new EmailSendListenerImp());
		es.sendEmail(list);
	}
	
	public static void sendEmail(Message message){
		List<Message> list=new ArrayList<Message>();
		list.add(message);
		EmailServer es=new EmailServer();
		es.addEmailListener(new EmailSendListenerImp());
		es.sendEmail(list);
	}
}
