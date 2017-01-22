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

<title>简历详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<spring:message code="jsp.include.listcssforback" />
<spring:message code="jsp.include.tagscssforback" /> 
<link href="http/business/timeline/style.css" rel="stylesheet">
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
						<!-- 简历信息 -->
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
								<tr role="row">
									<!-- 标签-->
									<td class="col-xs-2 info text-right">标签</td>
									<td class="col-xs-4" colspan="3" id="myTags">
									</td>
								</tr>
							</tbody>
						</table>
						<!-- 简历信息 end-->
						<!-- 项目经验 begin-->
						<div class="table-responsive">
							<div class="panel-heading-choose">项目经验</div>
						</div>
						<section id="cd-timeline" class="cd-container">
							<c:forEach items="${experiences }" var="item" varStatus="index">
								<div class="cd-timeline-block">
									<div class="cd-timeline-img cd-picture text-center">
										<span class="cd-timeline-no"><c:out value="${index.index+1 }" /></span> 
									</div>
							
									<div class="cd-timeline-content">
										<div class="col-xs-3">
											<h2><c:out value="${item.name }" /> </h2>
											<h3 style="display:inline"><c:out value="${item.positionName }" /> </h3>
											<span class="cd-date" style="display:block">
												<fmt:formatDate value="${item.startTime}" type="both" pattern="yyyy-MM-dd"/>
												至
												<fmt:formatDate value="${item.endTime}" type="both" pattern="yyyy-MM-dd"/>
											</span>
										</div>
										<div class="col-xs-9">
											<p><b>职责</b>：<c:out value="${item.duty }" /></p>
											<p><b>详细描述</b>：<c:out value="${item.description }" /></p>
<!-- 											<a href="javascript:void(0)" class="cd-read-more" target="_blank">按钮</a> -->
										</div>
										
									</div>
								</div>
							</c:forEach>
						</section>
						<!-- 项目经验 end-->
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
	<spring:message  code="jsp.include.tagsjsforback"/>
	<script type="text/javascript">
		
		$(document)	.ready(function() {
			// 标签
			var resumeLabels = '<c:out value="${ resumeLabels}" escapeXml="false"/>';
			var labelData = [];
			if(resumeLabels)
				labelData = tools.jsonToObj(resumeLabels);
			$("#myTags").initTags({
				selects:labelData,
				readonly:true//是否只读，不显示待选区域
			});
		});
						
		function closeDialog() {
			tools.closeDialog();
		}
		
	</script>
</body>
</html>
