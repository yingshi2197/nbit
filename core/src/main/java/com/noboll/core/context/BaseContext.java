package com.noboll.core.context;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.constants.SystemConstant;

// 上下文基础类
public abstract class BaseContext<T extends BaseEntity> {
	
	private static ThreadLocal<BaseEntity> currentThread=new ThreadLocal<BaseEntity>();
	
	// 获取bean
	public static Object getBean(String name) {
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		return webApplicationContext.getBean(name);
	}
	// 获取webinfo路径
	public static String getWebPath() {
		String reg="/WEB-INF/";
		String path=BaseContext.class.getClassLoader().getResource("").getPath();
		String[] temp=path.split(reg);
		return temp[0]+reg;
	}
	
	public static String getWebRootPath() {
		String reg="/WEB-INF/";
		String path=BaseContext.class.getClassLoader().getResource("").getPath();
		String[] temp=path.split(reg);
		return temp[0];
	}
	
	// 获取请求
	public static HttpServletRequest getHttpServletRequest() {
		try{
			return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		}catch(Exception e) {
			return null;
		}
	}
	
	// 此方法使用于线程内部设置用户
	public static void setLoginUser(BaseEntity user) {
		if(null==user) {
			currentThread.remove();
			if(null!=getHttpServletRequest()){
				getHttpServletRequest().getSession().removeAttribute(SystemConstant.SESSION_USER);
			}
		}else {
			currentThread.set(user);
			if(null!=getHttpServletRequest()){
				getHttpServletRequest().getSession().setAttribute(SystemConstant.SESSION_USER, user);
			}
		}
	}
	
	
	// 此方法使用于线程内部获取用户
	public static BaseEntity getLoginUser() {
		return currentThread.get();
	}
		
}
