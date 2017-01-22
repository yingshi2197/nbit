package com.noboll.plugin.message.server;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.noboll.core.component.executor.base.ExecutorHandler;
import com.noboll.core.component.executor.callback.CallBack;
import com.noboll.core.exception.BusinessException;
import com.noboll.core.util.CipherUtil;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.StringUtil;
import com.noboll.plugin.message.entity.Message;
import com.noboll.plugin.message.listener.EmailSendListener;
import com.noboll.plugin.message.service.MessageService;

/**
 * 
 * @ClassName: EmailServer
 * @Description: 邮件服务类,真正的发送邮件
 * @author vince.wei@noboll.com.cn
 * @date 2015年6月25日 下午4:00:24
 *
 */
public class EmailServer {
	/**
	 * 邮件监听器
	 */
	private List<EmailSendListener> emailSendListeners = new ArrayList<EmailSendListener>();
	
	@Resource
	private MessageService messageService;

	/**初始化相关配置*/
	public HtmlEmail init(String userName)  {
		//====================从缓存中读取相关配置  BEGIN 
		String MAIL_SMTP_HOST = PropertiesUtil.getSettingValue("sys.mail.smtp.host");// 邮件服务器
		int MAIL_SMTP_PORT = Integer.parseInt(PropertiesUtil.getSettingValue("sys.mail.smtp.port"));// 邮件服务器端口
		String MAIL_SMTP_USER = PropertiesUtil.getSettingValue("sys.mail.smtp.user");// 邮件登录用户名
		String MAIL_SMTP_PASSWORD = PropertiesUtil.getSettingValue("sys.mail.smtp.password");// 上述登录用户对应的密码
		String encrypt=PropertiesUtil.getSettingValue("sys.mail.smtp.password.encrypt");
		if("true".equalsIgnoreCase(encrypt)) {
			MAIL_SMTP_PASSWORD=CipherUtil.decrypt(MAIL_SMTP_PASSWORD, CipherUtil.PASSWORD);
		}
		String MAIL_CHARSET = PropertiesUtil.getSettingValue("sys.mail.charset");// 邮件编码
		boolean MAIL_DEBUG ="true".equalsIgnoreCase(PropertiesUtil.getSettingValue("sys.mail.debug"));// 是否开启调试模式
		String MAIL_NICK =  StringUtil.isEmpty(userName) ? PropertiesUtil.getSettingValue("sys.mail.smtp.user.nick") : userName;// 发送邮箱收件人一栏邮件地址前面的昵称
		//====================从缓存中读取相关配置  END
		//====================实例化commons-eamil的html邮件对象 并设置相关初始化参数 BEGIN
		HtmlEmail email = new HtmlEmail();
		email.setHostName(MAIL_SMTP_HOST);// 邮件发送服务器
		email.setSmtpPort(MAIL_SMTP_PORT);// 邮件发送服务器端口
		email.setAuthenticator(new DefaultAuthenticator(MAIL_SMTP_USER,
				MAIL_SMTP_PASSWORD));// 登录认证
		email.setSSLOnConnect(true);//是否启用SSL
		email.setCharset(MAIL_CHARSET);//邮件编码
		email.setDebug(MAIL_DEBUG);//发送邮件是否开启debug模式
		String nick="";// 发件人昵称,为了防止乱码的操作
		try {
			nick = MimeUtility.encodeText(MAIL_NICK);
			email.setFrom(nick+"<"+MAIL_SMTP_USER+">");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("邮件初始化异常");
		}// 发件人必须和DefaultAuthenticator相同
		//====================实例化commons-eamil的html邮件对象 并设置相关初始化参数 END
		return email;
	}

	/**
	 * 发送邮件的方法
	 * 
	 * @Title: sendEmail
	 * @Description: 发送邮件的方法)
	 * @param @param emailInfo 邮件对象
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean sendEmail(final List list) {
		
		// 使用创建邮件发送任务
		new Thread(new Runnable() {
			public void run() {
				
			}
		}).start();
		ExecutorHandler.executeNoResult(list, new CallBack() {
			public Object callback(Object obj) {
				Message message=(Message) obj;
				
				if (StringUtil.isEmpty(message.getContent())) {// 没有邮件内容
					return false;
				}
				if (StringUtil.isEmpty(message.getReceiver())) {// 没有收件人
					return false;
				}
				
				doBefore(message);//发送前
				try {
					HtmlEmail email=init(message.getSendName());
					buildEmailMessage(message,email);
					email.send();// 发送邮件
					doAfter(message);//发送成功
				} catch (Exception e) {
					e.printStackTrace();
					// 标注失败状态和失败原因
					message.setStatus("0");
					message.setReason(e.getMessage());
					doAfterThrowable(message);//发送失败
				}
				return null;
			}
		});
		return true;
	}

	/**
	 * @throws MalformedURLException 
	 * 
	 * @Title: buildEmailMessage
	 * @Description: 生成email邮件)
	 * @param @param emailInfo
	 * @param @return
	 * @param @throws EmailException 设定文件
	 * @return HtmlEmail 返回类型
	 * @throws
	 */
	private HtmlEmail buildEmailMessage(Message message,HtmlEmail email)
			throws EmailException, MalformedURLException {
		// 接收人
		email.addTo(message.getReceiver());
		// 抄送人
		if(message.getCopyerList()!=null){
			for (String cc : message.getCopyerList()) {
				email.addCc(cc);
			}
		}
		// 邮件主题
		String subject=message.getTitle();
		email.setSubject(subject);
		
		// 邮件内容
		email.setHtmlMsg(message.getContent());
		
		return email;
	}
	
	/**添加邮件监听*/
	public void addEmailListener(EmailSendListener emailSendListener) {
		this.emailSendListeners.add(emailSendListener);
	}
	
	/**邮件发送前的监听*/
	private void doBefore(Message message) {
		for (EmailSendListener emailSendListener : this.emailSendListeners) {
			emailSendListener.before(message);
		}
	}
	/**邮件发送成功后的监听*/
	private void doAfter(Message message) {
		for (EmailSendListener emailSendListener : this.emailSendListeners) {
			emailSendListener.after(message);
		}
	}
	/**邮件发送失败后的监听*/
	private void doAfterThrowable(Message message) {
		for (EmailSendListener emailSendListener : this.emailSendListeners) {
			emailSendListener.afterThrowable(message);
		}
	}
}
