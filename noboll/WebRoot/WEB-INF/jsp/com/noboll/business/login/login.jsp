<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<spring:message code="jsp.include.charset" />
<spring:message code="jsp.include.equiv" />
<spring:message code="jsp.include.viewport" />

<base href="<%=basePath%>">

<title>IT外包平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<link rel="stylesheet" href="http/business/login/css/public.css">
<style type="text/css">
.form-horizontal .form-group{
    padding-right: 0px;
}
</style>
</head>

<body class="body-bg">
	<div id="Layer1"
		style="position:absolute; width:100%; height:100%; z-index:-1">
		<img src="http/business/login/images/bg.png" height="100%" width="100%" />
	</div>
	<div class="global">
		<div class="container">
			<div class="con-title">
				<img src="http/business/login/images/noboll-logo.png" alt="logo"> <span
					class="title-tex">IT外包平台</span>
			</div>
			<div class="login">
				<form action="login.do" id="myform"	class="form-horizontal" role="form" method="post">
					<div class="form-group">
						<label style="display: none;">用户名</label>
						<input name="loginId" id="user1" type="text" class="form-control user required"
							placeholder="请输入您的用户名">
					</div>
					<div class="form-group">
						<label style="display: none;">密码</label>
						<input name="password" id="password1" type="password"
							class="form-control password required" placeholder="请输入您的密码">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">登录</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="footer"> Copyright © 2006-2016 深圳市诺宝信息技术有限公司
		版权所有 翻版必究 </footer>
	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<script type="text/javascript">
		
		$(document)	.ready(function() {
			$("#myform").initForm({successMsg:"false",success:function(data){
				if(data.type=="1") {
					if(localStorage)
						localStorage.clear();
					top.location.href = "home.do";
				}
			}});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}

	</script>
</body>
</html>
