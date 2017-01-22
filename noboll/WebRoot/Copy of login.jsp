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

<title>投递简历</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<spring:message code="jsp.include.listcssforback" />
<style type="text/css">
.form-horizontal .form-group {
  margin-right: -40px;
  margin-left: -60px;
}
</style>
</head>

<body>
	<div class="container" style="margin: auto">
			<form action="login.do" id="myform"
				class="form-horizontal" role="form" method="post">
				
				<div class="form-group">
					<label for="loginId" class="col-xs-2 control-label">账号</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="loginId"
							id="loginId" 	placeholder="请输入账号"	  maxlength="200" />
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-xs-2 control-label">密码</label>
					<div class="col-xs-4 form-control-1">
						<input type="password" class="form-control required" name="password"
							id="password" 	placeholder="请输入密码"	  maxlength="200" />
					</div>
				</div>
				
				<div class="tab-form-submit">
					<button type="submit" class="btn btn-success">
						提交
					</button>
					<button type="button" class="btn btn-warning"
						onclick="closeDialog();">
						取消
					</button>
				</div>
			</form>
	</div>

	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<spring:message code="jsp.include.listjsforback" />
	<script type="text/javascript">
		
		$(document)	.ready(
		function() {
			$("#myform").initForm({success:function(data){
				top.location.reload();
			}});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}

	</script>
</body>
</html>
