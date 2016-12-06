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
			<form action="business/project/add.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="id"
					value="<c:out value="${project.id}" />" /> 
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">项目名称</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required delSpace" name="name"
							id="name" 	placeholder="请输入项目名称"	 value="<c:out value="${project.name}" />" maxlength="200" />
					</div>

					<label for="code" class="col-xs-2 control-label">项目编码</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="code"
							id="code" 	placeholder="请输入项目编码"	 value="<c:out value="${project.code}" />" maxlength="10" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="source" class="col-xs-2 control-label">源码地址</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="source"
							id="source" 	placeholder="请输入源码地址"	 value="<c:out value="${project.source}" />" maxlength="400" />
					</div>

					<label for="target" class="col-xs-2 control-label">部署地址</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="target"
							id="target" 	placeholder="请输入部署地址"	 value="<c:out value="${project.target}" />" maxlength="400" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="container" class="col-xs-2 control-label">容器路径</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="container"
							id="container" 	placeholder="请输入容器路径"	 value="<c:out value="${container}" />" maxlength="400" />
					</div>
					
					<label for="port" class="col-xs-2 control-label">端口地址</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control digits" name="port"
							id="port" 	placeholder="请输入端口地址"	 value="<c:out value="${port}" />" maxlength="10" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="formal" class="col-xs-2 control-label">是否正式</label>
					<div class="col-xs-4 form-control-1">
						<input type="radio"  id="formal_1" name="formal" value="1"  class="required"  />
                        	<label for="formal_1">是</label>
                        <input type="radio"  id="formal_0" name="formal" value="0"  checked="checked"  class="required"  />
                        	<label for="formal_0">否</label>
					</div>
				
					<label for="path" class="col-xs-2 control-label">静态资源</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control" name="path"
							id="path" 	placeholder="请输入静态资源路径"	 value="<c:out value="${project.path}" />" maxlength="400" />
					</div>
				</div>
				
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 岗位描述-->
						<label for="description" class="control-label">项目描述</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入项目描述"><c:out value="${ project.description}"/></textarea>
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
