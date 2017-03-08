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
<spring:message code="jsp.include.tagscssforback" /> 
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
			<form id="myform"
				class="form-horizontal" role="form" method="post">
				<div class="panel-heading-choose">
					客户对简历的评价
					<div style="display:inline-block">
						<input id="REvaluateStar" 
							name="REvaluateStar" type="number" class="rating" min=0 max=5
							step=1 data-size="xxs" readonly="readonly" value="<c:out value="${evaluate.REvaluateStar}" />"/>
					</div>
				</div>
				
				<!--标签 -->
				<div class="form-group" style="margin-left:5px">
					<div class="col-sm-9 form-control-1" id="resumeTags"></div>
				</div>
				
				
				<!-- 评价 -->
				<div class="form-group" style="margin-top: 20px;margin-left:5px">
					<div class="col-sm-12 form-control-1">
						<textarea class="form-control" name="REvaluate"
							id="REvaluate" rows="3" cols="6" maxlength="400"
							placeholder="对求职者印象如何，说一说吧！"  readonly="readonly"><c:out value="${evaluate.REvaluate }"/></textarea>
					</div>
				</div>
				
				<div class="panel-heading-choose">
					简历对客户的评价
					<div style="display:inline-block">
						<input id="CEvaluateStar"
							name="CEvaluateStar" type="number" class="rating" min=0 max=5
							step=1 data-size="xxs" readonly="readonly" value="<c:out value="${evaluate.CEvaluateStar}" />"/>
					</div>
				</div>
				
				<div class="form-group" style="margin-left:5px">
					<div class="col-sm-9 form-control-1" id="customerTags"></div>
				</div>
				
				<!-- 评价 -->
				<div class="form-group" style="margin-top: 20px;margin-left:5px">
					<div class="col-sm-12 form-control-1">
						<textarea class="form-control" name="CEvaluate"
							id="CEvaluate" rows="3" cols="6" maxlength="400"
							placeholder="此次面试感觉如何，说一说吧！"  readonly="readonly"><c:out value="${evaluate.CEvaluate }"/></textarea>
					</div>
				</div>
				
			</form>
		</div>
	</div>

	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<spring:message code="jsp.include.listjsforback" />
	<spring:message  code="jsp.include.tagsjsforback"/>
	<script>
		$(document)	.ready(function() {
			$("#myform").initForm({});
			// 客户标签
			var customerLabels = '<c:out value="${ customerLabels}" escapeXml="false"/>';
			var labelData = [];
			if(customerLabels)
				customerLabelsData = tools.jsonToObj(customerLabels);
			$("#customerTags").initTags({
				selects:customerLabelsData,
				readonly:true//是否只读，不显示待选区域
			});
			// 简历标签
			var resumeLabels = '<c:out value="${ resumeLabels}" escapeXml="false"/>';
			var labelData = [];
			if(resumeLabels)
				resumeLabelsData = tools.jsonToObj(resumeLabels);
			$("#resumeTags").initTags({
				selects:resumeLabelsData,
				readonly:true//是否只读，不显示待选区域
			});
		});
						
		
		function closeDialog() {
			tools.closeDialog();
		}
	</script>
	<script src="http/business/evaluate/js/star-rating.js" type="text/javascript"></script>
</body>
</html>
