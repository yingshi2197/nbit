package com.noboll.intercept;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.noboll.business.user.entity.User;
import com.noboll.context.SystemContext;
import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.cache.service.CacheService;
import com.noboll.core.constants.SystemConstant;
import com.noboll.core.context.BaseContext;

/**
 * 
* @ClassName: MsbInterceptor
* @Description: 整个项目的拦截器
* @author kent.wang@noboll.com.cn
* @date 2015年5月28日 上午9:00:20
*
 */

public class CustomerInterceptor extends HandlerInterceptorAdapter {
	@Resource
	private SystemContext context;

	@Resource
	private CacheService cacheService;
	
	// 主要是针对app请求
	private List<String> ignoreList;
	
	public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
			
		   //SystemContext.setLoginUser((BaseEntity) request.getSession().getAttribute(SystemConstant.SESSION_USER));
		
		   //System.out.println("当前登录用户："+SystemContext.getLoginUser());
		   String uri=request.getRequestURI().replace(request.getContextPath()+"/", "");
		   if(!uri.contains("/wx/")) {
			   HandlerMethod hm=(HandlerMethod) handler;
			   // 验证当前请求方法是否是忽略名单
			   boolean isIgnore=this.getIgnoreList().contains(hm.getMethod().getName());
			   // 当不是登陆方法时需要验证当前登录用户
			   if(!isIgnore) {
				   if(null==SystemContext.getLoginUser()) {	
					   response.sendRedirect(request.getContextPath()+"/toLogin.do");  
					   return false;
				   }else {
					   User user=(User) SystemContext.getLoginUser();
					   if(user.getMenus().size()!=0) {
						   return user.getMenus().contains(uri);
					   }
				   }
			   }
		   }		   
		   return true;
    }  
	
	public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object o, ModelAndView mav)  
            throws Exception {  
        //System.out.println("postHandle");  
    }  
  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object o, Exception excptn)  
            throws Exception {  
        //System.out.println("afterCompletion");  
    }

	public List<String> getIgnoreList() {
		return ignoreList;
	}

	public void setIgnoreList(List<String> ignoreList) {
		this.ignoreList = ignoreList;
	}

	
    
}
