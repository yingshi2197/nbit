package com.noboll.core.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SessionException;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.noboll.core.base.entity.OperateMessage;
import com.noboll.core.constants.SystemConstant;
import com.noboll.core.util.JsonUtil;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.StringUtil;

public class BusinessExceptionHandler implements HandlerExceptionResolver {
	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception e) {
		
		   Map<String, Object> model = new HashMap<String, Object>();  
		   if(null!=request.getSession(false))
			   request.getSession(false).setAttribute("msb_token", null);
		   boolean ajax=StringUtil.isEmpty(request.getHeader("x-requested-with"));
		   // true表示不是ajax，false表示是ajax
		   if("1".endsWith(PropertiesUtil.getSettingValue("sys.config.exception.print"))) {
				e.printStackTrace();
			}
	       
		   if(ajax) {			   
		        // 根据不同错误转向不同页面  
		        if(e instanceof BusinessException) {  
		        	  model.put("message", e.getMessage());  
		        	  return new ModelAndView("exception/error-business", model);  
		        }else if(e instanceof SessionException) {  
		        	  model.put("message", e.getMessage());  
		        	  return new ModelAndView("exception/error-business", model);  
		        } else {  
		        	  model.put("message", "操作失败！");  
		        	  return new ModelAndView("exception/error-business", model);  
		        }  
		   }else {
	        	response.setCharacterEncoding("UTF-8");
	        	OperateMessage msg=new OperateMessage();
	        	if(e instanceof BusinessException) {  
		        	  msg=new OperateMessage(SystemConstant.OPERATE_ERROR, e.getMessage());
		        }else if(e instanceof SessionException) {  
		        	  msg=new OperateMessage(SystemConstant.OPERATE_ERROR, e.getMessage());
		        } else {  
		        	msg=new OperateMessage(SystemConstant.OPERATE_ERROR, "操作失败！");
		        }  
	        	
	            try {
					response.getWriter().write(JsonUtil.objToJson(msg));
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
			   return new ModelAndView(); 
		   }
	}
}
