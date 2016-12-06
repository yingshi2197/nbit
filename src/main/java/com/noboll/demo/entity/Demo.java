package com.noboll.demo.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.noboll.core.base.entity.BaseEntity;

public class Demo extends BaseEntity {
	private String id;
	
	private String name;
	
	private String password;
	
	private String loginId;
	
	
	public static void main(String[] args) {
		List<Integer> data=new ArrayList<Integer>();
		for(int i=1;i<=100;i++) {
			data.add(i);
		}
		List<Integer> result=new ArrayList<Integer>();
		List<Integer> temp=new ArrayList<Integer>();
		List<Integer> result_=new ArrayList<Integer>();
		int k=1;
		while(data.size()>0) {
			for(int i=0;i<data.size();i++) {
				//System.out.println(k);
				if(k%4==0) {
					//System.out.println(k+":"+data.size());
					temp.add(i);
				}
				if (i==data.size()-1) {
					if(temp.size()>0) {
						Collections.reverse(temp);
						for(int j=0;j<temp.size();j++) {
							int ss=temp.get(j);
							int s=data.remove(ss);
							//System.out.println(s);
							result_.add(s);
						}
						Collections.reverse(result_);
						result.addAll(result_);
						result_=new ArrayList<Integer>();
					}
					temp=new ArrayList<Integer>();
					i=-1;
				}
				k++;
			}
		}
		for(int i:result) {
			System.out.print(i+",");
		}
		
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
