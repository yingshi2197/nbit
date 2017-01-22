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
    
    <title>字典类型</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	
  </head>
  
  <body>
    	<div class="container tab-form1">
		    <div class="table-responsive">
			    <div class="panel-heading-choose">字典类型列表</div>
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
	                url: 'business/dictType/choose.do',
	                addUrl:"",
	                striped: true,
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'选择',position:'top',select:"0",css:"select",func:function(data){tools.getDialogParent().setValue(data)},a:'',url:""} // 选择
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
	                    {field: 'name',title: '名称',align: 'center',valign: 'middle',sortable: false,searchable:true}, //名称
	                    {field: 'code',title: '编码',align: 'left',valign: 'top',sortable: false,searchable:true}, //编码
	                    {field: 'description',index:'description',title: '描述',align: 'left',valign: 'top'}	//描述			                    
	                  ]
	            	});
		    	});
		    	
    	</script>
  </body>
</html>
