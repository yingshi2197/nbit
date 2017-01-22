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

<title>需求详情</title>

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
				<!-- 需求信息 -->
				<table class="table table-bordered">
					<tbody>
						<tr role="row">
							<!-- 需求编码 -->
							<td class="col-xs-2 info text-right">需求编码</td>
							<td class="col-xs-4"><c:out value="${requirement.code}"/></td>
							<!-- 需求名称 -->
							<td class="col-xs-2 info text-right">需求名称</td>
							<td class="col-xs-4"><c:out value="${requirement.name}"/></td>
						</tr>
						<tr role="row">
							<!-- 地址 -->
							<td class="col-xs-2 info text-right">地址</td>
							<td class="col-xs-4"><c:out value="${requirement.addressName}"/></td>
							<!-- 职位 -->
							<td class="col-xs-2 info text-right">职位</td>
							<td class="col-xs-4"><c:out value="${requirement.positionName}"/></td>
						</tr>
						<tr role="row">
							<!-- 级别 -->
							<td class="col-xs-2 info text-right">级别</td>
							<td class="col-xs-4" colspan="3"><c:out value="${requirement.levelName}"/></td>
						</tr>
						<tr role="row">
							<!-- 岗位职责 -->
							<td class="col-xs-2 info text-right">岗位职责</td>
							<td class="col-xs-4" colspan="3"><c:out value="${requirement.duty}"/></td>
						</tr>
						<tr role="row">
							<!-- 岗位要求 -->
							<td class="col-xs-2 info text-right">岗位要求</td>
							<td class="col-xs-4" colspan="3"><c:out value="${requirement.demand}"/></td>
						</tr>
						<tr role="row">
							<!-- 岗位描述 -->
							<td class="col-xs-2 info text-right">岗位描述</td>
							<td class="col-xs-4" colspan="3"><c:out value="${requirement.description}"/></td>
						</tr>
				  	</tbody>
			  	</table>
				<!-- 需求信息 end-->
				
				<div class="tab-form-submit">
					<button type="button" class="btn btn-warning"
						onclick="closeDialog();">
						关闭
					</button>
				</div>
		</div>



	</div>

	<spring:message code="jsp.include.basejs" />
	<spring:message code="jsp.include.formjsforback" />
	<spring:message code="jsp.include.listjsforback" />
	<script type="text/javascript">
		
		$(document)	.ready(function() {
		});
						
		function closeDialog() {
			tools.closeDialog();
		}
		
	</script>
</body>
</html>
