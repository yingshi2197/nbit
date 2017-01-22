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

<title>电话面试</title>

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
			<form action="business/entrant/addUpdate.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="resumeId"
					value="<c:out value="${resume.id}" />" />
				<input type="hidden" name="id"
					value="<c:out value="${entrant.id}" />" />
				<input type="hidden" name="deliverId"
					value="<c:out value="${deliver.id}" />" />
				
				<div class="table-responsive">
					<div class="panel-heading-choose">简历信息</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="resume">
					<table class="table table-bordered">
						<tbody>
							<tr role="row">
								<!-- 姓名 -->
								<td class="col-xs-2 info text-right">姓名</td>
								<td class="col-xs-4"><c:out value="${resume.name}"/></td>
								<!-- 求职岗位 -->
								<td class="col-xs-2 info text-right">求职岗位</td>
								<td class="col-xs-4"><c:out value="${resume.positionNames}" /></td>
							</tr>
							<tr role="row">
								<!-- 工作年限-->
								<td class="col-xs-2 info text-right">工作年限</td>
								<td class="col-xs-4"><c:out value="${resume.yearsName}"/></td>
								<!-- 学历-->
								<td class="col-xs-2 info text-right">学历</td>
								<td class="col-xs-4"><c:out value="${resume.degreeName}" /></td>
							</tr>
							<tr role="row">
								<!-- 毕业院校-->
								<td class="col-xs-2 info text-right">毕业院校</td>
								<td class="col-xs-4"><c:out value="${resume.school}" /></td>
								<!-- 毕业时间 -->
								<td class="col-xs-2 info text-right">毕业时间</td>
								<td class="col-xs-4"><fmt:formatDate value="${resume.finishTime}" type="both" pattern="yyyy-MM-dd"/></td>
							</tr>
						</tbody>
					</table>
				 </div>
				 
				 <div class="table-responsive">
					<div class="panel-heading-choose">入职信息</div>
				</div>
				<div class="form-group">
					<label for="customerName" class="col-xs-2 control-label">客户</label>
					<div class="col-xs-4 form-control-1">
						<input type="text"  class="form-control required" name="customerName" id="customerName"
			             value="<c:out value="${deliver.customerName}"/>"  readonly/>
					</div>
					<label for="positionNames" class="col-xs-2 control-label">入职岗位</label>
					<div class="col-xs-4 form-control-1">
						<input type="hidden" class="form-control required" name="positionId" id="positionIds" value="<c:out value="${entrant.positionId}" />"/>
						<input type="text" class="form-control" name="positionName"
							id="positionNames" 	placeholder="请选择入职岗位"	 value="<c:out value="${entrant.positionName}" />" maxlength="200" readonly/>
						<a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="positionChoose"  chooseField="id,name" chooseId="positionIds" 
				         chooseValue="positionNames" chooseWidth="850px" chooseHeight="500px"></a>
					</div>
				</div>
				<div class="form-group">
					<label for="enterTime" class="col-xs-2 control-label">入职时间</label>
					<div class="col-xs-4 form-control-1">
                 	  <input type="text"  class="form-control required date" name="enterTime" id="enterTime"  format="yyyy-MM-dd"
			             placeholder="请输入入职时间"
			             value="<fmt:formatDate value="${entrant.enterTime}" type="both" pattern="yyyy-MM-dd"/>"  readonly/>
                   </div>
					<label for="pay" class="col-xs-2 control-label">薪资</label>
					<div class="col-xs-4 form-control-1">
                 	  <input type="text"  class="form-control required number" name="pay" id="pay" placeholder="请输入薪资" maxLength="6"
			             value="<c:out value="${entrant.pay}"/>" />
                   </div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 描述-->
						<label for="reason" class="control-label">描述</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入描述"><c:out value="${ entrant.description}"/></textarea>
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
