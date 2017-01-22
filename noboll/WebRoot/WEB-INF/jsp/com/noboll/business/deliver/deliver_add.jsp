<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<spring:message code="jsp.include.charset"/>
    <spring:message code="jsp.include.equiv"/>
    <spring:message code="jsp.include.viewport"/>
    
    <base href="<%=basePath%>">
    
    <title>简历投递</title>
    <spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.formccforback"/>
	
  </head>
  
  <body>
	  <div class="container">
	  	<div class="tab-form1">
	  		<form action="business/deliver/add.do" id="myform" class="form-horizontal" role="form" method="post">
	          <div class="form-group" style="margin-top: 10px">
			      <label for="name" class="col-xs-2 control-label text-right">招聘需求</label><!-- 需求 -->
			      <div class="col-xs-10 form-control-1">
			      		<input type="hidden" name="requirementId" id="requirementId" value="<c:out value="${requirement.id}" />"/>
				      <input type="text" class="form-control  required" name="requirementName" id="requirementName" 
				         readonly="readonly"
				         value="<c:out value="${requirement.name}" />"/>
			      </div>
			  </div>
			   
			   <div class="form-group">
			      <label for="typeId" class="col-xs-2 control-label text-right">简历</label><!-- 简历 -->
			      <input  id="chooseId" name="resumeId" type="hidden"  value="<c:out value="${deliver.resumeId}" />"  />
			     <div class="col-xs-10 form-control-1">
			      <input type="text" class="form-control required" name="resumeName" id="chooseValue" 
			        placeholder="请选择简历"
			         readonly="readonly"  value="<c:out value="${deliver.resumeName}" />">
			         <a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="myResumeChoose"  chooseField="id,name" chooseId="chooseId" 
			         chooseValue="chooseValue" chooseWidth="1000px" chooseHeight="560px">
			         </a>
			     </div>
			   </div>
			   
			  <div class="form-group">
			      <label for="description" class="col-xs-2 control-label text-right" >描述</label><!-- 描述 -->
			      <div class="col-xs-10 form-control-1">
				      <textarea class="form-control" name="description" id="description" rows="4"
				         placeholder="请输入描述"><c:out value="${deliver.description}" /></textarea>
			   	  </div>
			    </div>
			    <div class="text-center tab-form-submit" >
				   <button type="submit" class="btn btn-success">提交</button><!-- 提交 -->
		           <button class="btn btn-warning"  type="button"  onclick="closeDialog();">取消</button><!-- 取消 -->
	      		</div>
	      </form>
	  	</div>
	  </div>
	  
    	<spring:message code="jsp.include.basejs"/>
    	<spring:message code="jsp.include.formjsforback"/>
    	
    	<script type="text/javascript">
    		
    		function closeDialog() {
    			tools.closeDialog();
    		}
    	
    		$("#myform").initForm({
    		});
    	</script>
  </body>
</html>
