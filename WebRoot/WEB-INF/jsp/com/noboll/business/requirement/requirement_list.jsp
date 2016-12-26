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
    
    <title>我的需求</title>
    
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
				<div class="panel-heading-choose">我的需求</div>
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
	                url: 'business/requirement/list.do',
	                addUrl:"",
	                striped: true,
	                permissionOperate:function(data){
	                	var op={};
	                	if(data.status!="1") {// 不为已发布
	                		op["finish"]="false";
	                	}
	                	if(data.status!="0") {// 不为草稿
	                		op["edit"]="false";
	                		op["remove"]="false";
	                		op["publish"]="false";
	                	}
	                	return op;
	                }, 
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'新增',position:'top',select:"",css:"add",dialog:{url:"business/requirement/toAdd.do",width:"850px",height:"500px"}},//新增
	                	{name:'修改',position:'row',css:"edit",a:'',dialog:{url:"business/requirement/toEdit.do",width:"850px",height:"500px"}},//修改	
	                	{name:'发布',position:'row',css:"publish",type:"ajax",url:"business/requirement/publish.do"},//发布 
	                	{name:'结束',position:'row',css:"finish",type:"ajax",url:"business/requirement/finish.do"},//结束 
	                	//{name:'申请',position:'row',css:"apply",a:'',dialog:{url:"business/resume/toAdd.do",width:"850px",height:"500px"}},//修改	       	
	                	{name:'删除',position:'row',css:"remove",type:"ajax",url:"business/requirement/remove.do"}//删除
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'code',title: '需求编码',align: 'center',valign: 'middle',sortable: true,searchable:true}, //编码
				                    {field: 'name',title: '名称',align: 'center',valign: 'middle',sortable: true,searchable:true}, //名称
				                    {field: 'addressName',index:'address',title: '地址',align: 'left',valign: 'top',searchable:true,type:'select:address',selectCode:"id"},	//描述
				                    {field: 'positionName',title: '职位',align: 'left',valign: 'top'},//职位
				                    {field: 'levelName',title: '级别',align: 'left',valign: 'top'},//级别
				                    {field: 'num',title: '需求人数',align: 'left',valign: 'top'},//需求人数
				                    {field: 'periodName',title: '周期',align: 'left',valign: 'top'},//周期
				                    {field: 'status',formatter:formateStatus,title: '状态',align: 'left',valign: 'top'},//状态
				                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top'}	//创建时间
				                  ]
	            	});
		    	});
		    	
		    	function formateStatus(value) {
		    		if("1"==value) {
		    			return "已发布";
		    		}else if("2"==value) {
		    			return "已结束";
		    		}else {
		    			return "草稿";
		    		}
		    	}
		    	
    	</script>
  </body>
</html>
