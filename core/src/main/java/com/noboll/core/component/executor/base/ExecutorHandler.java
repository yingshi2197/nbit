package com.noboll.core.component.executor.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.noboll.core.component.executor.callback.CallBack;
import com.noboll.core.component.executor.callback.ExecutorCallBack;
import com.noboll.core.component.executor.callback.RunnerCallBack;
import com.noboll.core.context.BaseContext;
import com.noboll.core.exception.BusinessException;


public class ExecutorHandler {
	// 默认线程数
	public static int THREAD_NUMBER=10;
	
	public static Object executeForResult(CallBack callback) {
		ExecutorCallBack executor=new ExecutorCallBack(null,BaseContext.getLoginUser(), callback);
		Object obj=null;
		ExecutorService executorService = Executors.newFixedThreadPool(1);	
		try {				
			Future<Object> future = executorService.submit(executor);
        	obj=future.get();
        }  catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(Thread.currentThread().getName()+"线程操作异常！");
		} finally{
			executorService.shutdown();
		}
		return obj;
	}
	
	// 多线程执行直接返回结果
	public static List<Object> executeForResult(List<Object> list,CallBack callback) {			
			return executeForResultNum(list, callback, 0);	
	 }
	
	// 多线程执行直接返回结果,自定义线程数
	public static List<Object> executeForResultNum(List<Object> list,CallBack callback,int num) {	
			num=num==0 ? THREAD_NUMBER : num;
			ExecutorService executorService = Executors.newFixedThreadPool(num);
			List<Future<Object>> resultList = new ArrayList<Future<Object>>();
			List<Object> result=new ArrayList<Object>();
			try {
				for(Object obj:list) {
					//System.out.println(Thread.currentThread().getName());
					ExecutorCallBack executor=new ExecutorCallBack(obj,BaseContext.getLoginUser(),callback);
					Future<Object> future = executorService.submit(executor);
					resultList.add(future);
				}
				for (Future<Object> fs : resultList) {		            
		            	result.add(fs.get());		            
		        }
			}  catch (Exception e) {
				e.printStackTrace();
				throw new BusinessException(Thread.currentThread().getName()+"线程操作异常！");
			} finally{
				executorService.shutdown();
			}
			return result;			
	 }
	
	public static void executeNoResult(CallBack callback) {
		ExecutorService executorService = Executors.newFixedThreadPool(1);		
		try {
			RunnerCallBack executor=new RunnerCallBack(null, BaseContext.getLoginUser(),callback);
			executorService.execute(executor);
        }  catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(Thread.currentThread().getName()+"线程操作异常！");
		} finally{
			executorService.shutdown();
		}
	}
	
	// 多线程执行不返回结果，线程后台执行
	public static void executeNoResult(List<Object> list,CallBack callback) {	
		executeNoResultNum(list, callback, 0);
	 }
	
	// 多线程执行不返回结果，线程后台执行
		public static void executeNoResultNum(List<Object> list,CallBack callback,int num) {	
				num=num==0 ? THREAD_NUMBER : num;
				ExecutorService executorService = Executors.newFixedThreadPool(num);
				try {
					for(Object obj:list) {
						//System.out.println(Thread.currentThread().getName());
						RunnerCallBack executor=new RunnerCallBack(obj, BaseContext.getLoginUser(),callback);
						executorService.execute(executor);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new BusinessException("线程操作异常！");
				} finally{
					executorService.shutdown();
				}
		 }
	
}
