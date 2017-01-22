package com.noboll.plugin.message.listener;

import com.noboll.core.context.BaseContext;
import com.noboll.plugin.message.entity.Message;
import com.noboll.plugin.message.service.MessageService;


/**
 * 
* @ClassName: EmailTimerSendListener
* @Description: 邮件定时监听器
* @author vince.wei@noboll.com.cn
* @date 2015年6月26日 下午6:00:15
*0表示失败，1表示成功，2表示发送中，3表示待发送
 */
public class EmailSendListenerImp implements EmailSendListener {
	MessageService messageService=(MessageService) BaseContext.getBean("messageService");
	
	// 0表示失败，1表示成功，2表示发送中，3表示待发送
	public void before(Message message) {
		message.setStatus("2");
		messageService.updateEntity(message);
	}

	//状态，0表示失败，1表示成功，2表示发送中，3表示待发送
	public void after(Message message) {
		message.setStatus("1");// 邮件发送成功
		messageService.updateEntity(message);
	}

	//状态，0表示失败，1表示成功，2表示发送中，3表示待发送
	public void afterThrowable(Message message) {
		message.setStatus("0");// 邮件发送失败
		messageService.updateEntity(message);
	}

}
