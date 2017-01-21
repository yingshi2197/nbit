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

<title>客户评价</title>

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
<link href="http/business/evaluate/css/star-rating.css" media="all"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="container">
		<div class="tab-form1">
			<form action="business/evaluate/cEvalute.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="resumeId"
					value="<c:out value="${evaluate.resumeId}" />" />
				<input type="hidden" name="customerId"
					value="<c:out value="${evaluate.customerId}" />" />
				
				<div style="text-align: center;">
					<font size="4"><b>客户评价</b></font>
					<span style="color: #014581">(请遵守当地法律法规，文明发言!)</span>
					<hr/>
				</div>
				
				<!-- 总分 -->
				<div class="form-group">
					<label for="cEvaluateStar" class="col-sm-2 control-label">总分：</label>
					<div class="col-sm-9 form-control-1">
						<input id="CEvaluateStar"
							name="CEvaluateStar" type="number" class="rating required required-2" min=0 max=5
							step=1 data-size="xs">
					</div>
				</div>
				
				<!-- 评价 -->
				<div class="form-group" style="margin-top: 20px;">
					<label for="cEvaluate" class="col-sm-2 control-label">评价：</label>
					<div class="col-sm-9 form-control-1">
						<textarea class="form-control required required-2" name="CEvaluate"
							id="CEvaluate" rows="3" cols="6" maxlength="400"
							placeholder="此次面试感觉如何，说一说吧！"></textarea>
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
	<script>
		$(document)	.ready(function() {
			$("#myform").initForm({});
		});
						
		
		function closeDialog() {
			tools.closeDialog();
		}
	</script>
	<script src="http/business/evaluate/js/star-rating.js" type="text/javascript"></script>
</body>
</html>
