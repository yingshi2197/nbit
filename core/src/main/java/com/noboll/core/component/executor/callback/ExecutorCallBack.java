package com.noboll.core.component.executor.callback;

import java.util.concurrent.Callable;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.context.BaseContext;

/**
 * 
* @ClassName: ExecutorCallBack
* @Description: 多线程执行任务时的回调方法
* @author kent.wang@noboll.com.cn
* @date 2016年1月5日 下午2:34:29
*
 */
public  class ExecutorCallBack implements Callable<Object> {
	
	private CallBack callback;
	private Object obj;
	private BaseEntity user;
	
	public ExecutorCallBack(Object obj,BaseEntity user,CallBack callback) {
		this.callback=callback;
		this.obj=obj;
		this.user=user;
	}
	
	@Override
	public Object call() throws Exception {
		//System.out.println(Thread.currentThread().getName());
		BaseContext.setLoginUser(user);
		return callback.callback(obj);
	}
	
		
		
}
