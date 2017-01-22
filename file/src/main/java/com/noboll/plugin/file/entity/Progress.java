package com.noboll.plugin.file.entity;

import com.noboll.core.base.entity.BaseEntity;

public class Progress extends BaseEntity  {
	// 
	private long pBytesRead;   // 已经上传的数量
	
	private long pContentLength;  // 总大小
	
	private int pItems;  // 文件总数量
	
	private double rate; //比率

	public long getpBytesRead() {
		return pBytesRead;
	}

	public void setpBytesRead(long pBytesRead) {
		this.pBytesRead = pBytesRead;
	}

	public long getpContentLength() {
		return pContentLength;
	}

	public void setpContentLength(long pContentLength) {
		this.pContentLength = pContentLength;
	}

	public int getpItems() {
		return pItems;
	}

	public void setpItems(int pItems) {
		this.pItems = pItems;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
}
