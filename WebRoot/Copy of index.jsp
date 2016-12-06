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
<script type="text/javascript">	
if(localStorage)
	localStorage.clear();
</script>
</head>

<body>
	<div class="container" style="margin-top:-40px;">	
		<div><a id="logout" href="#">退出</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://192.168.1.32:8081/nexus/index.html#welcome">maven仓库</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://192.168.1.32/index.php">禅道</a>
		&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://192.168.1.88:8001/zabbix/index.php">服务器监控</a>
		</div>
		<nav class="navbar navbar-default">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="#">诺宝IT外包平台</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav" id="a_click">
		        <li class="active"><a id="projectHref" href="#" url="business/project/toList.do" >项目管理</a></li>
		      </ul>
		    </div>
		  </div>
		</nav>
	
	</div>
	<div id="content">
	
	</div>

	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<script type="text/javascript">
		function click(obj) {
			$(".active").removeClass("active");
			$(obj).parent().addClass("active");
			var url=$(obj).attr("url");
			$("#content").load(url);
		}
		
		click($("#projectHref"));
		
		
		$("#a_click a").on("click",function(){
			click($(this));
		})
		
		$("#logout").on("click",function() {
			tools.ajax({
				url:"logout.do",
				method:"POST",
				success:function(data) {
					top.location.reload();
				}
			})
		})
	</script>
</body>
</html>
