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
    
    <title>我的简历</title>
    
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
				<div class="panel-heading-choose">我的简历</div>
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
	                url: 'business/resume/myList.do',
	                addUrl:"",
	                striped: true,
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
                        {name:'新增简历',position:'top',select:"",css:"add",dialog:{url:"business/resume/toAdd.do",width:"1000px",height:"500px"}},//新增
	                	{name:'完善简历',resume:'row',css:"edit",a:'',dialog:{url:"business/resume/toEdit.do",width:"1000px",height:"500px"}}//修改	
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '姓名',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'sexName',index:"sex",title: '性别',align: 'center',valign: 'middle'}, //性别
				                    {field: 'mobile',title: '联系电话',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'yearsName',title: '工作年限',align: 'center',valign: 'middle',searchable:true}, //工作年限
				                    {field: 'address',index:'address',title: '住址',align: 'left',valign: 'top'},	//住址
				                    {field: 'degreeName',title: '学历',align: 'left',valign: 'top'},//描述
				                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top'}	//上传时间
				                  ]
	            	});
		    	});
		    	
    	</script>
  </body>
</html>
