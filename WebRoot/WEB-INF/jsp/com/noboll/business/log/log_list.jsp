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
    
    <title>岗位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	<style type="text/css">
		body {
			padding-top: 30px;
		}
</style>
  </head>
  
  <body>
  	<div class="container">
	  		 <div class="table-responsive">
				<div class="panel-heading-choose">部署日志</div>
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
		       		//searchDiv:"searchDiv",
	                url: 'business/log/list.do?projectId=<c:out value="${projectId}"/>',
	                addUrl:"",
	                striped: true,
	                /* permissionOperate:function(data){
	                	var op={};
	                	if(data.status=="1") {
	                		op["remove"]="false";
	                	}
	                	if(data.status=="0") {
	                		op["apply"]="false";
	                		op["finish"]="false";
	                	}
	                	if(data.status=="2") {
	                		op["apply"]="false";
	                		op["edit"]="false";
	                		op["remove"]="false";
	                		op["finish"]="false";
	                	}
	                	return op;
	                },  */
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'详情',position:'row',css:"logs",a:'',dialog:{url:"business/log/toView.do",width:"850px",height:"500px"}}//修改	  
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'version',title: '版本',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'start',title: '是否重启',formatter:formateStart,align: 'center',valign: 'middle'}, //名称
				                    {field: 'startTime',formatter:"date:yyyy-MM-dd hh:mm",title: '计划部署时间',align: 'center',valign: 'top'},	//上传时间
				                    {field: 'status',formatter:formateStatus,title: '状态',align: 'center',valign: 'middle',searchable:true}, //名称
				                    //{field: 'reason',title: '原因',align: 'center',valign: 'middle'}, //名称
				                    {field: 'deployTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '部署时间',align: 'center',valign: 'top'}	//上传时间
				                  ]
	            	});
		    	});
		    	
		    	function formateStatus(value) {
		    		if("1"==value) {
		    			return "成功";
		    		}else if("2"==value) {
		    			return "部署中";
		    		}else if("0"==value) {
		    			return "失败";
		    		}else if("5"==value) {
		    			return "待部署";
		    		}else {
		    			return "未知状态";
		    		}
		    	}
		    	
		    	function formateStart(value) {
		    		if("1"==value) {
		    			return "是";
		    		}else if("0"==value) {
		    			return "否";
		    		}else {
		    			return "未知状态";
		    		}
		    	}
    	</script>
  </body>
</html>
