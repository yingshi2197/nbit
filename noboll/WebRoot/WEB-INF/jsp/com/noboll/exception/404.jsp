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
    
    <title>404错误</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="404">
	<meta http-equiv="description" content="404">
	
	<spring:message code="jsp.include.basecss"/>
	<!-- Bootstrap -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- Layout -->
    <link href="css/layout.css" rel="stylesheet" type="text/css">
  </head>
  
  <body class="manage-page-404bg">
  		  
    <c:out value="${message}" />
    <div class="manage-page-404">
   		<div class="manage-page-404-back"><a href="javascript:void(0)" onclick="toIndex()">返回管理系统</a></div>
    </div>
    <spring:message code="jsp.include.basejs"/>
    
  </body>
  <script type="text/javascript">
  //实现jsp中的basePath效果
  	function toIndex(){
  		top.location.href="<%=basePath%>manage/home/index.do";
  	}
  </script>
</html>
