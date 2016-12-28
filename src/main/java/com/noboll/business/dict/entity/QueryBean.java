package com.noboll.business.dict.entity;

import java.util.List;


/**
 * 
* @ClassName: QueryBean
* @Description: 搜索页左侧分类查询栏用到的bean
* @author weicb@noboll.com.cn
* @date 2016年12月27日 下午20:58:17
*
 */
@SuppressWarnings("rawtypes")
public class QueryBean  {

	private String code;
	private String name;
	private List	list;
	private String typeCode;
	
	public QueryBean() {
		super();
	}
	public QueryBean(String code, String name, List list) {
		super();
		this.code = code;
		this.name = name;
		this.list = list;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	
}