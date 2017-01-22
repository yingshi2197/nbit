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

<title>新增部署</title>

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
			<form action="business/log/add.do" id="myform"
				class="form-horizontal" role="form" enctype="multipart/form-data" method="post">
				<input type="hidden" name="projectId" 	value="<c:out value="${log.projectId}" />" /> 
				<input type="hidden" name="code" value="<c:out value="${log.code}" />" />
				<div class="form-group">
					<label for="projectName" class="col-xs-2 control-label">项目名称</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="projectName"
							id="projectName"  readonly="readonly"	 value="<c:out value="${log.projectName}" />" maxlength="200" />
					</div>

					<label for="version" class="col-xs-2 control-label">项目版本</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="version"
							id="version" 	placeholder="请输入项目编码"	 value="<c:out value="${log.version}" />" maxlength="10" />
					</div>
				</div>
				<div class="form-group">
					<label for="source" class="col-xs-2 control-label">源码地址</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="source"
							id="source" 	placeholder="请输入源码地址"	 value="<c:out value="${log.source}" />" maxlength="400" />
					</div>

					<label for="target" class="col-xs-2 control-label">部署地址</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="target"
							id="target" 	placeholder="请输入部署地址"	 value="<c:out value="${log.target}" />" maxlength="400" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="container" class="col-xs-2 control-label">容器路径</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control  required" name="container"
							id="container" 	placeholder="请输入容器路径"	 value="<c:out value="${log.container}" />" maxlength="400" />
					</div>
				
					<label for="start" class="col-xs-2 control-label">是否重启</label>
					<div class="col-xs-4 form-control-1">
						<input type="radio"  id="sex_1" name="start" value="1"  class="required"  />
                        	<label for="sex_1">是</label>
                        <input type="radio"  id="sex_0" name="start" value="0"  class="required"  />
                        	<label for="sex_0">否</label>
					</div>
					
				</div>
				
				<div class="form-group">
					<label for="path" class="col-xs-2 control-label">静态资源</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control" name="path"
							id="path" 	placeholder="请输入静态资源路径"	 value="<c:out value="${log.path}" />" maxlength="400" />
					</div>
					
					<label for="startTime" class="col-xs-2 control-label">计划部署时间</label>
					<div class="col-xs-4 form-control-1">
						<input type="text"  class="form-control date" name="startTime" id="startTime"  format="yyyy-MM-dd HH:mm"
			             placeholder="请输入重启时间"
			             value="<fmt:formatDate value="${log.startTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>"  readonly/>
					</div>
				</div>
				
				
				
				<div class="form-group">
					<label class="col-sm-2 control-label">增量包</label>
					<div class="col-sm-10 form-control-1">
						<div class="panel panel-default">
							<div class="panel-body row">
								<input type="file" id="file" name="file" style="display: none;"
									onchange="fileName.value=value"> <label for="fileName" hidden>增量包</label>
								<div class="col-sm-6">
									<input name="fileName" class="form-control" 	placeholder="请选择增量包（zip）"
										readonly="readonly">
								</div>
								<input type=button value="浏览" onclick="file.click()" class="btn btn-info" />
							</div>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 岗位描述-->
						<label for="description" class="control-label">部署描述</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入项目描述"><c:out value="${ log.description}"/></textarea>
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
			$("#myform").initForm({});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}

	</script>
</body>
</html>
