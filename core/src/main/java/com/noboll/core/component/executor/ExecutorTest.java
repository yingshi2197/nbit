package com.noboll.core.component.executor;

import java.util.ArrayList;
import java.util.List;

import com.noboll.core.component.executor.base.ExecutorHandler;
import com.noboll.core.component.executor.callback.CallBack;

public class ExecutorTest {
	
	public static void test() {
		List<Object> list=new ArrayList<Object>();
		for(int i=0;i<50000;i++) {
			list.add(i);
		}
		final long time1=System.currentTimeMillis(); 
		ExecutorHandler.executeForResult(list,new CallBack() {
			public Object callback(Object obj) {
				if("10000".equals(""+obj)) {
					System.out.println("=======================");
				}
				System.out.println("test+"+obj);
				
				return null;
			}
		});
		ExecutorHandler.executeForResult(list,new CallBack() {
			public Object callback(Object obj) {
				if("10000".equals(""+obj)) {
					System.out.println("=======================");
				}
				System.out.println("test+"+obj);
				
				return null;
			}
		});
		long time2=System.currentTimeMillis(); 
		System.out.println(time2-time1);
	}
	
	public static void main(String[] args) {
			test();
	}
	
}
