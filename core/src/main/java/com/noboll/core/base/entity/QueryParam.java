package com.noboll.core.base.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.noboll.core.constants.SystemConstant;
import com.noboll.core.util.StringUtil;

public class QueryParam {
	// 查询条件
	Map<String,Object> map=new HashMap<String, Object>();
	// 排序方式
	Map<String,String> orderMap=new HashMap<String, String>();
	
	private void initMap() {
		if(null==this.map)
			this.map=new HashMap<String, Object>();
	}
	
	private void initOrderMap() {
		if(null==this.orderMap)
			this.orderMap=new HashMap<String, String>();
	}
	
	public void addParam(String key,Object value) {
		initMap();
		this.map.put(key, value);
	}
	
	public void removeParam(String key) {
		initMap();
		this.map.remove(key);
	}
	
	public void addOrder(String key,String value) {
		initOrderMap();
		value=StringUtil.isEmpty(value) ? SystemConstant.ORDER_ASC : value;
		this.orderMap.put(key, value);
	}
	
	
	public void removeOrder(String key) {
		initOrderMap();
		this.orderMap.remove(key);
	}
	
	public List<Order> getOrderList() {
		List<Order> list=new ArrayList<Order>();
		Iterator<Entry<String,String>> it=this.orderMap.entrySet().iterator();
		while(it.hasNext()) {
			
			Entry<String,String> entry=it.next();
			Order order=Order.create(entry.getKey(), entry.getValue());
			list.add(order);
		}
		return list;
	}
	
	public Map<String, Object> getMap() {
		initMap();
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Map<String, String> getOrderMap() {
		initOrderMap();
		return orderMap;
	}

	public void setOrderMap(Map<String, String> orderMap) {
		this.orderMap = orderMap;
	}
}
