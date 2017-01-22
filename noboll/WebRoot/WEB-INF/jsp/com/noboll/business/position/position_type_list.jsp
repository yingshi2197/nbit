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
    
    <title>职位类型</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="职位类型">
	<meta http-equiv="description" content="职位类型">
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	<link rel="stylesheet" href="http/business/login/css/public.css">
	
  </head>
  
  <body>
  <div class="container">
  		 <div class="table-responsive ">
			<div class="panel-heading-choose">职位类型列表</div>
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
	                url: 'business/position/typeList.do',
	                addUrl:"",
	                striped: true,
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'修改',position:'row',css:"edit",a:'',dialog:{url:"business/dict/toEdit.do",width:"450px"}},//修改	         	
	                	{name:'子项列表',position:'row',css:"edit",a:'',dialog:{url:"business/position/toList.do?type={id}",height:"550px",width:"800px"}}//子项列表	         	
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},  //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '名称',align: 'center',valign: 'middle',sortable: false,searchable:true,filterLen:30}, //名称
				                    {field: 'code',index:'login_id',title: '编码',align: 'left',valign: 'top',filterLen:20},	//编码
				                    {field: 'description',index:'description',title: '描述',align: 'left',valign: 'top'}	//描述			                    
				                  ]
	            	});
		    	});
		    	
    	</script>
  </body>
</html>
