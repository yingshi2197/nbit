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
    
    <title>招聘管理系统-业务异常</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="业务异常">
	<meta http-equiv="description" content="业务异常">
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link type="text/css" rel="stylesheet" href="http/business/exception/css/prompt.css">
	
  </head>
  
  <body>  
  	
    	<spring:message code="jsp.include.basejs"/>
  </body>
  <script type="text/javascript">
  $(document).ready(function () {
  		tools.alert('<c:out value="${message}"></c:out>',null,function(){
  		if(window.location==window.top.location) {
  			window.top.location.reload();
  			window.close();
  		}else
  			window.top.location.reload();
  		});
  });
  
  </script>
</html>
