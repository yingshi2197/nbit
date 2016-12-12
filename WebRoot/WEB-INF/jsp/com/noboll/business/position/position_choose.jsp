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
    
    <title><spring:message code="职位选择器"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	
  </head>
  
  <body>
  	<div class="container tab-form1">
	  		 <div class="table-responsive">
				<div class="panel-heading-choose"><spring:message code="职位选择器"/></div>
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
	                url: 'business/position/choose.do',
	                addUrl:"",
	                striped: true,
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:"选择",position:'top',select:'<c:out value="${type }" />',css:"select",func:function(data){tools.getDialogParent().setValue(data)},a:'',url:""} // 选择
	                ],
	                clickToSelect: true,
	                columns: [
				                	<c:choose>
				                		<c:when test="${type==1}">
				                		{field: 'statu_msb',checkbox: true},
				                		</c:when>
				                		<c:otherwise>
				                		{field: 'statu_msb',radio: true},
				                		</c:otherwise>
				                	</c:choose>
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '<spring:message code="名称"/>',align: 'left',valign: 'middle',sortable: false,searchable:true,filterLen:40,width:"36%"}, //名称
				                    {field: 'typeName',title: '<spring:message code="类型"/>',align: 'left',valign: 'middle',sortable: false,searchable:true,filterLen:40,width:"30%"}, //类型
				                    //{field: 'code',title: '<spring:message code="com.noboll.business.manage.dictionary.code"/>',align: 'left',valign: 'top',searchable:true},	//描述
				                    {field: 'description',title: '<spring:message code="描述"/>',align: 'left',valign: 'top'},	//描述	
				                    {field: 'fullName',title: '<spring:message code="fullName"/>',align: 'left',valign: 'top',visible:false}	//描述	
				                  ] 
	            	});
		    	});
		    	
    	</script>
  </body>
</html>
