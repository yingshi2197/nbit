package com.noboll.core.component.executor.callback;

import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.context.BaseContext;


public class RunnerCallBack implements Runnable {
	
	private CallBack callback;
	private Object obj;
	private BaseEntity user;
	
	public RunnerCallBack(Object obj,BaseEntity user,CallBack callback) {
		this.callback=callback;
		this.obj=obj;
		this.user=user;
	}
	
	@Override
	public void run() {
		BaseContext.setLoginUser(user);
		callback.callback(obj);
	}
	
}
