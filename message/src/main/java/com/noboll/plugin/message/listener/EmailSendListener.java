package com.noboll.plugin.message.listener;

import com.noboll.plugin.message.entity.Message;

/**
 * 
* @ClassName: EmailSendListener
* @Description: 邮件服务监听器接口,实现接口可对邮件发送前、发送成功、发送失败做出处理
* @author vince.wei@noboll.com.cn
* @date 2015年6月26日 下午5:59:45
*
 */
public interface EmailSendListener {
	/**
	 * 邮件发送以前做的操作
	 * @param emailInfo
	 */
	public abstract void before(Message message);
	/**
	 * 邮件发送成功的操作
	 * @param emailContext
	 */
	public abstract void after(Message message);

	/**
	 * 发送邮件出现异常时进行的错误处理
	 */
	public abstract void afterThrowable(Message message);
}
