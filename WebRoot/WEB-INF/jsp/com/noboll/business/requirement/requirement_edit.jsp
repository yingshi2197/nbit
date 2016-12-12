<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>修改岗位</title>

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
			<form action="business/requirement/edit.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="id"
					value="<c:out value="${requirement.id}" />" /> 
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">需求编码</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required delSpace english" name="code"
							id="code" 	placeholder="请输入需求编码"	 value="<c:out value="${requirement.code}" />" maxlength="40" />
					</div>
					<label for="name" class="col-xs-2 control-label">需求名称</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required delSpace" name="name"
							id="name" 	placeholder="请输入需求名称"	 value="<c:out value="${requirement.name}" />" maxlength="200" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="address" class="col-xs-2 control-label">地址</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="address" name="address"  dictValue="<c:out value="${requirement.address}" />">
						</select>
					</div>
					
					<label for="education" class="col-xs-2 control-label">职位</label>
					<div class="col-xs-4 form-control-1">
						<input type="hidden" class="form-control required" name="positionId" id="positionIds" value="<c:out value="${requirement.positionId}" />"/>
						<input type="text" class="form-control required" name="positionName"
							id="positionNames" 	placeholder="请选择求职岗位"	 value="<c:out value="${requirement.positionName}" />" maxlength="200" />
						<a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="positionChoose"  chooseField="id,name" chooseId="positionIds" 
				         chooseValue="positionNames" chooseWidth="850px" chooseHeight="500px"></a>
					</div>
				</div>
				
				<div class="form-group">
					<label for="education" class="col-xs-2 control-label">级别</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="requirement_level" name="level"  dictValue="<c:out value="${requirement.level}" />">
						</select>
					</div>
					
					<label for="num" class="col-xs-2 control-label">需求人数</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required number" name="num"
							id="num" 	placeholder="请输入需求人数"	 value="<c:out value="${requirement.num}" />"  max="999" min="1"/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="work_life" class="col-xs-2 control-label">周期</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="requirement_period" name="period"  dictValue="<c:out value="${requirement.period}" />">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 岗位职责-->
						<label for="duty" class="control-label">岗位职责</label>
						<textarea class="form-control required-2"
							name="duty" id="duty" rows="10" cols="6"
							placeholder="请输入岗位职责"><c:out value="${ requirement.duty}"/></textarea>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 岗位要求-->
						<label for="requirement" class="control-label">岗位要求</label>
						<textarea class="form-control required-2"
							name="demand" id="demand" rows="10" cols="6"
							placeholder="请输入岗位要求"><c:out value="${ requirement.demand}"/></textarea>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 岗位描述-->
						<label for="description" class="control-label">岗位描述</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入岗位描述"><c:out value="${ requirement.description}"/></textarea>
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
