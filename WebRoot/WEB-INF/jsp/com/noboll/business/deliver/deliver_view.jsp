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

<title>投递详情</title>

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
				<div role="tabpanel">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#deliver"
							aria-controls="home" role="tab" data-toggle="tab">投递信息 </a></li>
						<li role="presentation"><a href="#resume"
							aria-controls="profile" role="tab" data-toggle="tab">简历信息 </a></li>
						<li role="presentation"><a href="#requirement"
							aria-controls="profile" role="tab" data-toggle="tab">需求信息 </a></li>
						<c:if test="${interviewDh !=null }">
						<li role="presentation"><a href="#interviewDh"
							aria-controls="profile" role="tab" data-toggle="tab">电话面试 </a></li>
						</c:if>
						<c:if test="${interviewXc !=null }">
						<li role="presentation"><a href="#interviewXc"
							aria-controls="profile" role="tab" data-toggle="tab">现场面试 </a></li>
						</c:if>
						<li role="presentation"><a href="#entrant"
							aria-controls="profile" role="tab" data-toggle="tab">入职信息 </a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<!-- 投递信息 -->
						<div role="tabpanel" class="tab-pane active" id="deliver">
							<table class="table table-bordered">
								<tbody>
									<tr role="row">
										<!-- 投递时间 -->
										<td class="col-xs-2 info text-right">投递时间</td>
										<td class="col-xs-4"><fmt:formatDate value="${deliver.deliverTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
										<!-- 投递状态 -->
										<td class="col-xs-2 info text-right">投递状态</td>
										<td class="col-xs-4"><c:out value="${deliver.statusName}" /></td>
									</tr>
									<tr role="row">
										<!-- 面试状态 -->
										<td class="col-xs-2 info text-right">面试状态</td>
										<td class="col-xs-4"><c:out value="${deliver.interviewStatusName}"/></td>
										<!-- 描述 -->
										<td class="col-xs-2 info text-right">描述 </td>
										<td class="col-xs-4"><c:out value="${deliver.description}"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- 投递信息 end-->
						<!-- 简历信息 -->
						<div role="tabpanel" class="tab-pane" id="resume">
							<table class="table table-bordered">
								<tbody>
									<tr role="row">
										<!-- 姓名 -->
										<td class="col-xs-2 info text-right">姓名</td>
										<td class="col-xs-4"><c:out value="${resume.name}"/></td>
										<!-- 性别 -->
										<td class="col-xs-2 info text-right">性别</td>
										<td class="col-xs-4"><c:out value="${resume.sexName}"/></td>
									</tr>
									<tr role="row">
										<!-- 求职岗位 -->
										<td class="col-xs-2 info text-right">求职岗位</td>
										<td class="col-xs-4"><c:out value="${resume.positionNames}" /></td>
										<!-- 工作年限-->
										<td class="col-xs-2 info text-right">工作年限</td>
										<td class="col-xs-4"><c:out value="${resume.yearsName}"/></td>
									</tr>
									<tr role="row">
										<!-- 参加工作时间-->
										<td class="col-xs-2 info text-right">参加工作时间</td>
										<td class="col-xs-4"><fmt:formatDate value="${resume.joinTime}" type="both" pattern="yyyy-MM-dd"/></td>
										<!-- 毕业院校-->
										<td class="col-xs-2 info text-right">毕业院校</td>
										<td class="col-xs-4"><c:out value="${resume.school}" /></td>
									</tr>
									<tr role="row">
										<!-- 毕业时间 -->
										<td class="col-xs-2 info text-right">毕业时间</td>
										<td class="col-xs-4"><fmt:formatDate value="${resume.finishTime}" type="both" pattern="yyyy-MM-dd"/></td>
										<!-- 学历-->
										<td class="col-xs-2 info text-right">学历</td>
										<td class="col-xs-4"><c:out value="${resume.degreeName}" /></td>
									</tr>
									<tr role="row">
										<!-- 意向地区 -->
										<td class="col-xs-2 info text-right">意向地区</td>
										<td class="col-xs-4"><c:out value="${resume.intentionNames}"/></td>
										<!-- 期望薪资-->
										<td class="col-xs-2 info text-right">期望薪资</td>
										<td class="col-xs-4"><c:out value="${resume.payName}" /></td>
									</tr>
									<tr role="row">
										<!-- 出生日期 -->
										<td class="col-xs-2 info text-right">出生日期</td>
										<td class="col-xs-4"><fmt:formatDate value="${resume.birthday}" type="both" pattern="yyyy-MM-dd"/></td>
										<!-- 住址-->
										<td class="col-xs-2 info text-right">住址</td>
										<td class="col-xs-4"><c:out value="${ resume.address}"/></td>
									</tr>
									<tr role="row">
										<!-- 籍贯-->
										<td class="col-xs-2 info text-right">籍贯</td>
										<td class="col-xs-4" colspan="3"><c:out value="${resume.nativeName}"/></td>
									</tr>
									<tr role="row">
										<!-- 自我简介-->
										<td class="col-xs-2 info text-right">自我简介</td>
										<td class="col-xs-4" colspan="3"><c:out value="${ resume.description}"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- 简历信息 end-->
						<!-- 需求信息 -->
						<div role="tabpanel" class="tab-pane" id="requirement">
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
						</div>
						<!-- 需求信息 end-->
						<c:if test="${interviewDh !=null }">
						<!-- 电话面试 -->
						<div role="tabpanel" class="tab-pane" id="interviewDh">
							<table class="table table-bordered">
								<tbody>
									<tr role="row">
										<!-- 面试时间 -->
										<td class="col-xs-2 info text-right">面试时间</td>
										<td class="col-xs-4"><fmt:formatDate value="${interviewDh.time}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
										<!-- 面试官 -->
										<td class="col-xs-2 info text-right">面试官</td>
										<td class="col-xs-4"><c:out value="${interviewDh.interviewer}" /></td>
									</tr>
									<tr role="row">
										<!-- 面试结果-->
										<td class="col-xs-2 info text-right">面试结果</td>
										<td class="col-xs-4"><c:out value="${interviewDh.resultName}"/></td>
										<c:if test="${interviewDh.resultCode =='dhmstg' }">
											<!-- 预约面试时间-->
											<td class="col-xs-2 info text-right">预约面试时间</td>
											<td class="col-xs-4"><fmt:formatDate value="${interviewDh.nextTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
										</c:if>
									</tr>
									<tr role="row">
										<!-- 面试结果-->
										<td class="col-xs-2 info text-right">描述</td>
										<td class="col-xs-4"  colspan="3"><c:out value="${interviewDh.reason}"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- 电话面试 end-->
						</c:if>
						<c:if test="${interviewXc !=null }">
						<!-- 现场面试 -->
						<div role="tabpanel" class="tab-pane" id="interviewXc">
							<table class="table table-bordered">
								<tbody>
									<tr role="row">
										<!-- 面试时间 -->
										<td class="col-xs-2 info text-right">面试时间</td>
										<td class="col-xs-4"><fmt:formatDate value="${interviewXc.time}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
										<!-- 面试官 -->
										<td class="col-xs-2 info text-right">面试官</td>
										<td class="col-xs-4"><c:out value="${interviewXc.interviewer}" /></td>
									</tr>
									<tr role="row">
										<!-- 面试结果-->
										<td class="col-xs-2 info text-right">面试结果</td>
										<td class="col-xs-4"><c:out value="${interviewXc.resultName}"/></td>
										<c:if test="${interviewXc.resultCode =='xcmstg' }">
											<!-- 预约入职时间-->
											<td class="col-xs-2 info text-right">预约入职时间</td>
											<td class="col-xs-4"><fmt:formatDate value="${interviewXc.nextTime}" type="both" pattern="yyyy-MM-dd HH:mm"/></td>
										</c:if>
									</tr>
									<tr role="row">
										<!-- 面试结果-->
										<td class="col-xs-2 info text-right">描述</td>
										<td class="col-xs-4" colspan="3"><c:out value="${interviewXc.reason}"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- 现场面试 end-->
						</c:if>
						<!-- 入职信息 -->
						<div role="tabpanel" class="tab-pane" id="entrant">
						</div>
						<!-- 入职信息 end-->
				
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
