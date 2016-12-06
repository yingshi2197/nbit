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
	<link rel="stylesheet" href="http/business/login/css/public.css">
  </head>
  
  <body>
  	<div class="container">
	  		 <div class="table-responsive">
				<!-- <div class="panel-heading-choose">项目列表</div> -->
			  	<div id="searchDiv"></div>
				<table id="table-javascript" ></table>
	    	</div>
    	</div>
    	<spring:message code="jsp.include.basejs"/>
    	<spring:message code="jsp.include.listjsforback"/>
    	
    	<script type="text/javascript">
    		
    		function openAccess(data){
    			//alert(data.accessAddress);
    			window.open(data.accessAddress);
    		}
    		
    		$(document).ready(function () {
		       	$("#table-javascript").initBootTable({
		       		method: 'post',
		       		searchDiv:"searchDiv",
	                url: 'business/project/list.do',
	                addUrl:"",
	                striped: true,
	                permissionOperate:function(data){
	                	var op={};
	                	//alert("${formal}");
	                	if(data.formal=="1"&&"${formal}"=="0") {
	                		op["deploy"]="false";
	                	}
	                	return op;
	                }, 
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'新增',position:'top',select:"",css:"add",dialog:{url:"business/project/toAdd.do",width:"850px",height:"500px"}},//新增
	                	{name:'修改',position:'row',css:"edit",a:'',dialog:{url:"business/project/toEdit.do",width:"850px",height:"500px"}},//修改	
	                	{name:'部署',position:'row',css:"deploy",dialog:{url:"business/log/toAdd.do",width:"850px",height:"500px"}},//发布 
	                	{name:'部署日志',position:'row',css:"logs",a:'',dialog:{url:"business/log/toList.do",width:"850px",height:"500px"}},//修改	
	                	{name:'运行日志',position:'row',css:"catelog",a:'',dialog:{url:"business/project/toProjectLog.do",width:"850px",height:"400px"}},//修改
	                	{name:'访问',position:'row',css:"access",func:openAccess},//修改	       	
	                	{name:'删除',position:'row',css:"remove",type:"ajax",url:"business/project/remove.do"}//删除
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '名称',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'code',title: '编码',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'source',title: '源码地址',align: 'center',valign: 'middle'}, //名称
				                    {field: 'target',title: '部署地址',align: 'center',valign: 'middle'}, //名称
				                    {field: 'path',title: '静态资源',align: 'center',valign: 'middle'} //名称
				                   // {field: 'lastModifyTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '最后修改时间',align: 'center',valign: 'top'}	//上传时间
				                  ]
	            	});
		    	});
		    	
    	</script>
  </body>
</html>
