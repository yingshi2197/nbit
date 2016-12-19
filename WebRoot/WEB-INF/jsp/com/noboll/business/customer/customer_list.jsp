<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <spring:message code="jsp.include.charset"/>
    <spring:message code="jsp.include.equiv"/>
    <spring:message code="jsp.include.viewport"/>
    
    <base href="<%=basePath%>">
    
    <title>客户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	<link rel="stylesheet" href="http/business/login/css/public.css">
	
  </head>
  
  <body>
  	<div class="container">
	  		 <div class="table-responsive">
				<div class="panel-heading-choose">客户管理</div>
			  	<div id="searchDiv"></div>
				<table id="table-javascript" ></table>
	    	</div>
    	</div>
    	<spring:message code="jsp.include.basejs"/>
    	<spring:message code="jsp.include.listjsforback"/>
    	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
		       	$("#table-javascript").initBootTable({
		       		method: 'post',
		       		searchDiv:"searchDiv",
	                url: 'business/customer/list.do',
	                addUrl:"",
	                striped: true,
	                permissionOperate:function(data){
	                	var op={};
	                	if("0"==data.status) {// 有效可以禁用
	                		op["enable"]="false";
	                	}else if("1"==data.status) {// 无效可以启用
	                		op["disable"]="false";
	                	}
	                	return op;
	                }, 
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'新增',position:'top',select:"",css:"add",dialog:{url:"business/customer/toAdd.do",width:"850px"}},//新增
	                	{name:'修改',position:'row',css:"edit",a:'',dialog:{url:"business/customer/toEdit.do",width:"850px"}},//修改	         	
	                	{name:'启用',position:'row',css:"enable",type:"ajax",url:"business/customer/enable.do"},//启用	         	
	                	{name:'禁用',position:'row',css:"disable",type:"ajax",url:"business/customer/disable.do"},//禁用	         	
	                	{name:'删除',position:'row',css:"remove",type:"ajax",url:"business/customer/remove.do"}//删除
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                	{field: 'status',title:'status',visible:false}, 
				                    {field: 'name',title: '名称',align: 'center',valign: 'middle',sortable: false,searchable:true}, //名称
				                    {field: 'industryName',index:'industryName',title: '行业',align: 'left',valign: 'top',searchable:true,type:'select:industry',selectCode:"id"},	//行业
				                    {field: 'natureName',index:'natureName',title: '性质',align: 'left',valign: 'top',searchable:true,type:'select:nature',selectCode:"id"},	//性质
				                    {field: 'scaleName',index:'scaleName',title: '规模',align: 'left',valign: 'top',searchable:true,type:'select:scale',selectCode:"id"},	//规模
				                    {field: 'fund',index:'fund',title: '注册资金',align: 'left',valign: 'top'},	//注册资金
				                    {field: 'statusName',index:'statusName',title: '状态',align: 'left',valign: 'top'}	//状态
				                  ]
	            	});
		    	});
    	</script>
  </body>
</html>
