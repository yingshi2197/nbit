<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<base href="<%=basePath%>">

<title><spring:message code="com.noboll.business.common.login" /></title>
<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<link href="http/component/privilege/css/layout.css" rel="stylesheet"
	type="text/css">
<!--[if lt IE 9]>
      <script src="http/component/privilege/js/html5shiv.js"></script>
      <script src="http/component/privilege/js/respond.js"></script>
    <![endif]-->
</head>

<body >
<div style="text-align: center;">
	<img alt="" src="http/business/home/images/weihu.jpg" >
	<h3 style="position: relative;top:-450px;left:-300px;color: #535862">noboll </br>诺宝IT外包平台</h3>
	<h3 style="position: relative;top:-325px;left:108px;color: #535862">大约1小时！</h3>
</div>
	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" /> 
	<script type="text/javascript">
	
	</script>
</body>
</html>
