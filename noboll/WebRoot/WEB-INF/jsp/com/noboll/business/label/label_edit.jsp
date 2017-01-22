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
    
    <title>修改职位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.formccforback"/>
	
  </head>
  
  <body>
	  <div class="container">
	  	<div class="tab-form1">
	  		<form action="business/label/edit.do" id="myform" class="form-horizontal" role="form" method="post">
	          <input type="hidden"  name="id"  value="<c:out value="${label.id}" />" />
	          <div class="form-group" style="margin-top: 10px">
			      <label for="name" class="col-xs-2 control-label text-right">名称</label><!-- 名称 -->
			      <div class="col-xs-10 form-control-1">
				      <input type="text" class="form-control  required" name="name" id="name" 
				         placeholder="请输入名称"
				         value="<c:out value="${label.name}" />" maxlength="200"/>
			      </div>
		     </div>
		  	 <div class="form-group">
			      <label for="ename" class="col-xs-2 control-label text-right">英文名称</label><!-- 名称 -->
			      <div class="col-xs-10 form-control-1">
				      <input type="text" class="form-control english required" name="ename" id="ename" 
				         placeholder="请输入英文名称"
				         value="<c:out value="${label.ename}" />" maxlength="200"/>
			      </div>
			   </div>
			  <div class="form-group">
			      <label for="code" class="col-xs-2 control-label text-right">编码</label><!-- 编码 -->
			      <div class="col-xs-10 form-control-1">
			      <input type="text"  class="form-control  required english" name="code" id="code" 
			         placeholder="请输入编码"
			          value="<c:out value="${label.code}" />" maxlength="10"/>
			      </div>
			   </div>
			  <div class="form-group">
			      <label for="description" class="col-xs-2 control-label text-right" >描述</label><!-- 描述 -->
			      <div class="col-xs-10 form-control-1">
				      <textarea class="form-control" name="description" id="description" 
				         placeholder="请输入描述"><c:out value="${label.description}" /></textarea>
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
