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

<title>现场面试</title>

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
			<form action="business/interview/xcInterview.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="resumeId"
					value="<c:out value="${resume.id}" />" />
				<input type="hidden" name="id"
					value="<c:out value="${interview.id}" />" />
				<input type="hidden" name="deliverId"
					value="<c:out value="${deliver.id}" />" />
				
				<div class="table-responsive">
					<div class="panel-heading-choose">面试信息</div>
				</div>
				<div class="form-group">
					<label for="time" class="col-xs-2 control-label">面试时间</label>
					<div class="col-xs-4 form-control-1">
						<input type="text"  class="form-control required date" name="time" id="time"  format="yyyy-MM-dd HH:mm"
			             placeholder="请选择面试时间"
			             value="<fmt:formatDate value="${interview.time}" type="both" pattern="yyyy-MM-dd HH:mm"/>"  readonly/>
					</div>
					<label for="interviewer" class="col-xs-2 control-label">面试官</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="interviewer"
							id="interviewer" 	placeholder="请输入面试官" value="<c:out value="${interview.interviewer}" />" maxlength="200" />
					</div>
				</div>
				<div class="form-group">
					<label for="result" class="col-xs-2 control-label">面试结果</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="xcms" name="result" id="result"
						dictValue="<c:out value="${interview.result}" />">
						</select>
					</div>
					<div id="nextTimeDiv" style="display:none">
						<label for="nextTime" class="col-xs-2 control-label">预约入职时间</label>
						<div class="col-xs-4 form-control-1">
							<input type="text"  class="form-control required date" name="nextTime" id="nextTime"  format="yyyy-MM-dd HH:mm"
				             placeholder="请选择预约入职时间"
				             value="<fmt:formatDate value="${interview.nextTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>"  readonly/>
						</div>
					</div>
				</div>
				
				
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 描述-->
						<label for="reason" class="control-label">描述</label>
						<textarea class="form-control required-2"
							name="reason" id="reason" rows="10" cols="6"
							placeholder="请输入描述"><c:out value="${ interview.reason}"/></textarea>
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
			var result = '<c:out value="${interview.result}"/>';
			handlerResult(result);
			
			$("#myform").initForm({});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}
		
		$("#result").change(function(){
			var result = $(this).val();
			handlerResult(result);
		});
		
		
		function handlerResult(result){
			var allResult = tools.getValueForDict("xcms");
			$.each(allResult,function() {
				if(this.id == result){
					if("xcmstg" == this.code){// 通过
						$("#nextTimeDiv").show();
						$("#nextTime").addClass("required");
						$("#nextTime").siblings(".redStar").show();
					}else{//不通过
						$("#nextTimeDiv").hide();
						$("#nextTime").removeClass("required");
						$("#nextTime").siblings(".redStar").hide();
					}
				}
			});
		}

	</script>
</body>
</html>
