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

<title>新增客户</title>

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
			<form action="business/customer/add.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">名称</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="name"
							id="name" 	placeholder="请输入名称" value="<c:out value="${customer.name}" />" maxlength="200" />
					</div>
					<label for="fund" class="col-xs-2 control-label">注册资金</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="fund"
							id="fund" 	placeholder="请输入注册资金" value="<c:out value="${customer.fund}" />" maxlength="40" />
					</div>
				</div>
				
				<div class="form-group">
					
					<label for="foundTime" class="col-xs-2 control-label">注册时间</label>
					<div class="col-xs-4 form-control-1">
						<input type="text"  class="form-control required date" name="foundTime" id="foundTime"  format="yyyy-MM-dd"
			             placeholder="请选择注册时间"
			             value="<fmt:formatDate value="${customer.foundTime}" type="both" pattern="yyyy-MM-dd"/>"  readonly/>
					</div>
					
					<label for="industry" class="col-xs-2 control-label">行业</label>
					<div class="col-xs-4 form-control-1">
						<input type="hidden" class="form-control required" name="industry" id="industry" value="<c:out value="${customer.industry}" />"/>
						<input type="text" class="form-control required" name="industryName"
							id="industryName" 	placeholder="请选择行业"	 value="<c:out value="${customer.industryName}" />" maxlength="200" readonly/>
						<a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="industryChoose"  chooseField="id,name" chooseId="industry" 
				         chooseValue="industryName" chooseWidth="850px" chooseHeight="500px"></a>
					</div>
				</div>
				
				<div class="form-group">
					<label for="nature" class="col-xs-2 control-label">性质</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="nature" name="nature"  
						dictValue="<c:out value="${customer.nature}" />">
						</select>
					</div>
					
					<label for="scale" class="col-xs-2 control-label">规模</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="scale" name="scale"  
						dictValue="<c:out value="${customer.scale}" />">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="address" class="col-xs-2 control-label">地址</label>
					<div class="col-xs-10 form-control-1">
						<!-- 地址-->
						<input class="form-control required"
							name="address" id="address"
							placeholder="请输入地址" value="<c:out value="${ customer.address}"/>" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 简介-->
						<label for="description" class="control-label">简介</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入客户简介"><c:out value="${ customer.description}"/></textarea>
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
		
		$(document)	.ready(
		function() {
			$("#myform").initForm({});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}

	</script>
</body>
</html>
