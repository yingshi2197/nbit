package com.noboll.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.noboll.core.base.entity.OperateMessage;
import com.noboll.core.base.entity.Page;
import com.noboll.core.base.entity.QueryParam;
import com.noboll.core.constants.SystemConstant;
import com.noboll.core.util.JsonUtil;
import com.noboll.core.util.NumberUtil;
import com.noboll.core.util.StringUtil;

public class InitUtil {
	
	public static Page initPage(HttpServletRequest request) {
		String limit=request.getParameter("limit");
		String offset=request.getParameter("offset");
		int start=StringUtil.isEmpty(offset) ? 0 : NumberUtil.parseInt(offset);
		int pageSize=StringUtil.isEmpty(limit) ? 10 : NumberUtil.parseInt(limit);
		int current=start%pageSize==0 ? start/pageSize+1 : start/pageSize+2 ;
		Page page=new Page(current,pageSize);
		return page;
	}
	
	public static QueryParam initQueryParam(HttpServletRequest request,String conditionParam) {
		String sort=request.getParameter("sort");
		String order=request.getParameter("order");
		String condition=request.getParameter(conditionParam);
		QueryParam queryParam=new QueryParam();
		if(!StringUtil.isEmpty(condition)) {
			Map<String,String> map=JsonUtil.jsonToObject(condition, Map.class);
			Map<String,Object> map1=new HashMap<String, Object>();
			Iterator<Entry<String,String>> it=map.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String,String> entry=it.next();
				String key=entry.getKey();
				String value=entry.getValue();
				map1.put(key, value);
			}
			queryParam.setMap(map1);
		}		
		if ((!StringUtil.isEmpty(order))&&(!StringUtil.isEmpty(sort))) {
			queryParam.addOrder(sort, order);
		}
		return queryParam;
	}
	
	public static QueryParam initQueryParam(HttpServletRequest request) {
		return initQueryParam(request,"condition");
	}
	
	public static OperateMessage sucessMessage(String msg) {
    	return new OperateMessage(SystemConstant.OPERATE_SUCCESS, msg);
    }
	
	public static OperateMessage errorMessage(String msg) {
    	return new OperateMessage(SystemConstant.OPERATE_ERROR, msg);
    }
}
