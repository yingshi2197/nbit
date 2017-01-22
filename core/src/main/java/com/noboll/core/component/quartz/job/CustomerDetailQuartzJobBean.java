package com.noboll.core.component.quartz.job;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.noboll.core.exception.BusinessException;

public class CustomerDetailQuartzJobBean  extends QuartzJobBean  {
	
	 private String targetObject;  
	    private String targetMethod;  
	  
	    @Override  
	    protected void executeInternal(JobExecutionContext context)  
	            throws JobExecutionException {  
	        try {  
	        	SchedulerContext schCtx = context.getScheduler().getContext();
	            ApplicationContext ctx = (ApplicationContext)schCtx.get("applicationContext");
	            Object otargetObject = ctx.getBean(targetObject);  
	            Method m = null;  
                m = otargetObject.getClass().getMethod(targetMethod);  
                m.invoke(otargetObject);  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	            throw new BusinessException("定时器启动失败！");  
	        }  
	    }  
	  
	    public void setTargetObject(String targetObject) {  
	        this.targetObject = targetObject;  
	    }  
	  
	    public void setTargetMethod(String targetMethod) {  
	        this.targetMethod = targetMethod;  
	    }  
	
}
