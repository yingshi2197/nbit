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

<title>诺宝IT外包平台</title>
<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<link href="http/business/home/css/layout.css" rel="stylesheet"
	type="text/css">
<!--[if lt IE 9]>
      <script src="http/component/privilege/js/html5shiv.js"></script>
      <script src="http/component/privilege/js/respond.js"></script>
    <![endif]-->
 <style>
		body {
		    padding-top: 0px;
		}
</style>

</head>

<body>
	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<script type="text/javascript">
		top.location.href = "toLogin.do";
	</script>
</body>
</html>
