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
	                url: 'business/resume/list.do',
	                addUrl:"",
	                striped: true,
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'修改',resume:'row',css:"edit",a:'',dialog:{url:"business/resume/toEdit.do",width:"850px",height:"500px"}}//修改	
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '姓名',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'sexName',index:"sex",title: '类型',align: 'center',valign: 'middle',searchable:true,type:'select:sex',selectCode:"id"}, //类型
				                    {field: 'mobile',title: '联系电话',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'major',title: '专业',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'addressName',index:'address',title: '工作地点',align: 'left',valign: 'top',searchable:true,type:'select:address',selectCode:"id"},	//描述
				                    {field: 'workLifeName',title: '年限',align: 'left',valign: 'top'},//描述
				                    {field: 'educationName',title: '学历',align: 'left',valign: 'top'},//描述
				                    {field: 'positionName',title: '申请职位',align: 'left',valign: 'top'},//描述
				                    {field: 'statusName',formatter:formateStatus,title: '状态',align: 'left',valign: 'top',searchable:true,type:'select:resume_status',selectCode:"id"},//描述
				                    
				                    {field: 'applyTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '申请时间',align: 'left',valign: 'top'}	//上传时间
				                   		                    
				                  ]
	            	});
		    	});
		    	
		    	function formateStatus(value) {
		    		if(value) {
		    			return value;
		    		}else {
		    			return "申请中";
		    		}
		    	}
		    	
    	</script>
  </body>
</html>
