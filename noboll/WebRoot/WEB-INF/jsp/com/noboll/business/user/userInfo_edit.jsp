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

<title>修改用户</title>

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
	<div class="container">
		<div class="tab-form1">
			<form action="business/userInfo/edit.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="id"
					value="<c:out value="${userInfo.id}" />" />
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">姓名</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="name"
							id="name" 	placeholder="请输入名称" value="<c:out value="${userInfo.name}" />" maxlength="200" />
					</div>
					<label for="nature" class="col-xs-2 control-label">性别</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control required" name="sex">
							<option value="">请选择</option>
							<option value="0" <c:if test="${userInfo.sex == '0' }">selected</c:if>>男</option>
							<option value="1" <c:if test="${userInfo.sex == '1' }">selected</c:if>>女</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="loginId" class="col-xs-2 control-label">账号</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required english" name="loginId"
							id="loginId" 	placeholder="请输入账号" value="<c:out value="${userInfo.loginId}" />" maxlength="200" />
					</div>
					<label for="password" class="col-xs-2 control-label">密码</label>
					<div class="col-xs-4 form-control-1">
						<input type="password" class="form-control required" name="password"
							id="password" 	placeholder="请输入密码" value="<c:out value="${userInfo.password}" />" maxlength="40" />
					</div>
				</div>
				<div class="form-group">
					<label for="phone" class="col-xs-2 control-label">电话</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required mobile" name="phone"
							id="phone" 	placeholder="请输入电话" value="<c:out value="${userInfo.phone}" />" maxlength="200" />
					</div>
					<label for="email" class="col-xs-2 control-label">邮箱</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required email" name="email"
							id="email" 	placeholder="请输入邮箱" value="<c:out value="${userInfo.email}" />" maxlength="40" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="role" class="col-xs-2 control-label">角色</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="role" name="role" id="role" dictValue="<c:out value="${userInfo.role}" />">
						</select>
					</div>
					
					<div id="customerDiv" style="display:none">
					<label for="customerName" class="col-xs-2 control-label">客户</label>
					<div class="col-xs-4 form-control-1">
						<input type="hidden" class="form-control" name="customerId" id="customerId" value="<c:out value="${userInfo.customerId}" />"/>
						<input type="text" class="form-control" name="customerName"
							id="customerName" 	placeholder="请选择关联客户"	 value="<c:out value="${userInfo.customerName}" />" maxlength="200" readonly/>
						<a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="customerChoose"  chooseField="id,name" chooseId="customerId" 
				         chooseValue="customerName" chooseWidth="850px" chooseHeight="500px"></a>
					</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 简介-->
						<label for="description" class="control-label">备注</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入备注"><c:out value="${ userInfo.description}"/></textarea>
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



	</div>

	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<spring:message code="jsp.include.listjsforback" />
	<script type="text/javascript">
		
		$(document)	.ready(function() {
			// 角色处理
			var role = '<c:out value="${userInfo.role}"/>';
			if("customer" == role){
				$("#customerDiv").show();
				$("#customerId").addClass("required");
				$("#customerId").siblings(".redStar").show();
			}else{
				$("#customerDiv").hide();
				$("#customerId").removeClass("required");
				$("#customerId").siblings(".redStar").hide();
			}
			
			$("#myform").initForm({});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}
		
		$("#role").change(function(){
			var value = $(this).val();
			if("customer" == value){
				$("#customerDiv").show();
				$("#customerId").addClass("required");
				$("#customerId").siblings(".redStar").show();
			}else{
				$("#customerDiv").hide();
				$("#customerId").removeClass("required");
				$("#customerId").siblings(".redStar").hide();
			}
		});

	</script>
</body>
</html>
