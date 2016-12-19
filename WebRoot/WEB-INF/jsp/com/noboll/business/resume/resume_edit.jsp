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

<title>完善简历</title>

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
			<form action="business/resume/edit.do" id="myform"
				class="form-horizontal" role="form" method="post">
				<input type="hidden" name="id"
					value="<c:out value="${resume.id}" />" /> 
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">姓名</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="name"
							id="name" 	placeholder="请输入姓名"	 value="<c:out value="${resume.name}" />" maxlength="200" />
					</div>
					<label for="name" class="col-xs-2 control-label">求职岗位</label>
					<div class="col-xs-4 form-control-1">
						<input type="hidden" class="form-control required" name="positionIds" id="positionIds" value="<c:out value="${resume.positionIds}" />"/>
						<input type="text" class="form-control required" name="positionNames"
							id="positionNames" 	placeholder="请选择求职岗位"	 value="<c:out value="${resume.positionNames}" />" maxlength="200" />
						<a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="positionChoose"  chooseField="id,name" chooseId="positionIds" 
				         chooseValue="positionNames" chooseWidth="850px" chooseHeight="500px"></a>
					</div>
				</div>
				
				<div class="form-group">
					<label for="work_life" class="col-xs-2 control-label">工作年限</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="work_life" name="years"  dictValue="<c:out value="${resume.years}" />">
						</select>
					</div>
					
					<label for="education" class="col-xs-2 control-label">参加工作时间</label>
					<div class="col-xs-4 form-control-1">
						<input type="text"  class="form-control required date" name="joinTime" id="joinTime"  format="yyyy-MM-dd"
			             placeholder="请输入参加工作时间"
			             value="<fmt:formatDate value="${resume.joinTime}" type="both" pattern="yyyy-MM-dd"/>"  readonly/>
					</div>
				</div>
				
				<div class="form-group">
					<label for="school" class="col-xs-2 control-label">毕业院校</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="school"
							id="school" 	placeholder="请输入毕业院校"	 value="<c:out value="${resume.school}" />" maxlength="200" />
					</div>
					
					<%-- <label for="major" class="col-xs-2 control-label">专业</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required delSpace" name="major"
							id="major" 	placeholder="请输入专业"	 value="<c:out value="${resume.major}" />" maxlength="200" />
					</div> --%>
					<label for="school" class="col-xs-2 control-label">毕业时间</label>
					<div class="col-xs-4 form-control-1">
						<input type="text"  class="form-control required date" name="finishTime" id="finishTime"  format="yyyy-MM-dd"
			             placeholder="请输入毕业时间"
			             value="<fmt:formatDate value="${resume.finishTime}" type="both" pattern="yyyy-MM-dd"/>"  readonly/>
					</div>
					
				</div>
				<div class="form-group">
					
					<label for="education" class="col-xs-2 control-label">学历</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="degree" name="degree"  dictValue="<c:out value="${resume.degree}" />">
						</select>
					</div>
					
					<label for="mobile" class="col-xs-2 control-label">移动电话</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="mobile"
							id="mobile" 	placeholder="请输入移动电话"	 value="<c:out value="${resume.mobile}" />" maxlength="20" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="name" class="col-xs-2 control-label">意向地区</label>
					<div class="col-xs-4 form-control-1">
						<input type="hidden" class="form-control required" name="intentionIds" id="intentionIds" value="<c:out value="${resume.intentionIds}" />"/>
						<input type="text" class="form-control required" name="intentionNames"
							id="intentionNames" 	placeholder="请选择意向地区"	 value="<c:out value="${resume.intentionNames}" />" maxlength="200" />
						<a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="intentionChoose"  chooseField="id,name" chooseId="intentionIds" 
				         chooseValue="intentionNames" chooseWidth="850px" chooseHeight="500px"></a>
					</div>
					<label for="work_life" class="col-xs-2 control-label">期望薪资</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="pay" name="pay"  dictValue="<c:out value="${resume.pay}" />">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="birthday" class="col-xs-2 control-label">出生日期</label>
						<div class="col-xs-4 form-control-1">
	                 	  <input type="text"  class="form-control required date" name="birthday" id="birthday"  format="yyyy-MM-dd"
				             placeholder="请输入出生日期"
				             value="<fmt:formatDate value="${resume.birthday}" type="both" pattern="yyyy-MM-dd"/>"  readonly/>
	                   </div>
	                   
					<label for="name" class="col-xs-2 control-label">联系人</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required" name="contact"
							id="contact" 	placeholder="请输入联系人"	 value="<c:out value="${resume.contact}" />" maxlength="200" />
					</div>
				</div>
				
				<div class="form-group">
					<label for="sex" class="col-xs-2 control-label">联系人电话</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control required mobile" name="contactMobile"
							id="contactMobile" 	placeholder="请输入联系人电话"	 value="<c:out value="${resume.contactMobile}" />" maxlength="200" />
					</div>
					
					<label for="address" class="col-xs-2 control-label">住址</label>
					<div class="col-xs-4 form-control-1">
						<input class="form-control required"
							name="address" id="address"
							placeholder="请输入住址" value="<c:out value="${ resume.address}"/>" />
					</div>
				</div>
				
				<div class="form-group">
					
					<label for="native_" class="col-xs-2 control-label">籍贯</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select" dictCode="native" name="native_"  dictValue="<c:out value="${resume.native_}" />">
						</select>
					</div>
					
					<label for="sex" class="col-xs-2 control-label">性别</label>
					<div class="col-xs-4 form-control-1">
						<select class="form-control select required" dictCode="sex" name="sex"  dictValue="<c:out value="${resume.sex}" />"></select>
					</div>
				</div>
				
				
				
				<%-- <div class="form-group">
					<label class="col-xs-2 control-label">应聘岗位</label>
					<div class="col-xs-4 form-control-1">
						<input type="text" class="form-control "   readonly="readonly"
							value="<c:out value="${resume.positionName}" />" maxlength="100"/>
					</div>
				</div> --%>
				
				<div class="form-group">
					<div class="col-sm-offset-1 col-sm-11 form-control-1">
						<!-- 岗位描述-->
						<label for="description" class="control-label">自我简介</label>
						<textarea class="form-control required-2"
							name="description" id="description" rows="10" cols="6"
							placeholder="请输入自我简介"><c:out value="${ resume.description}"/></textarea>
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
