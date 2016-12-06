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
    
    <title>修改数据字典</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.formccforback"/>
	
  </head>
  
  <body>
	  <div class="container">
	  	<div class="tab-form1">
	  		<form action="business/dict/edit.do" id="myform" class="form-horizontal" role="form" method="post">
	          <input type="hidden"  name="id"  value="<c:out value="${dictionary.id}" />" />
	          <div class="form-group" style="margin-top: 10px">
			      <label for="name" class="col-xs-2 control-label text-right">名称</label><!-- 名称 -->
			      <div class="col-xs-10 form-control-1">
				      <input type="text" class="form-control  required" name="name" id="name" 
				         placeholder="请输入名称"
				         value="<c:out value="${dictionary.name}" />" maxlength="200"/>
			      </div>
			   </div>
			  <div class="form-group">
			      <label for="typeId" class="col-xs-2 control-label text-right">类型</label><!-- 类型 -->
			      <input  id="chooseId" name="typeId" type="hidden"  value="<c:out value="${dictionary.typeId}" />"  />
			      <div class="col-xs-10 form-control-1">
			      	<input type="text" class="form-control  required" name="demoChoose" id="chooseValue" 
			        placeholder="请选择类型"
			         readonly="readonly"  value="<c:out value="${dictionary.typeName}" />">
			         <!-- a href="javascript:void(0)"  class='glyphicon glyphicon-search choose'  chooseCode="dictionaryTypeChoose"  chooseField="id,name" chooseId="chooseId" 
			         chooseValue="chooseValue" chooseWidth="1000px" chooseHeight="560px">
			         </a> -->
			      </div>
			   </div>
			  <div class="form-group">
			      <label for="code" class="col-xs-2 control-label text-right">编码</label><!-- 编码 -->
			      <div class="col-xs-10 form-control-1">
			      <input type="text"  class="form-control  required english" name="code" id="code" 
			         placeholder="请输入编码"
			          value="<c:out value="${dictionary.code}" />" maxlength="40"/>
			      </div>
			   </div>
			   <div class="form-group">
			      <label for="seq" class="col-xs-2 control-label text-right">序列</label><!-- 序列-->
			      <div class="col-xs-10 form-control-1">
			      <input type="text"  class="form-control  required number" name="seq" id="seq" 
			         placeholder="请输入序列"
			          value="<c:out value="${dictionary.seq}" />" maxlength="40"/>
			      </div>
			   </div>
			  <div class="form-group">
			      <label for="description" class="col-xs-2 control-label text-right" >描述</label><!-- 描述 -->
			      <div class="col-xs-10 form-control-1">
				      <textarea class="form-control" name="description" id="description" 
				         placeholder="请输入描述"><c:out value="${dictionary.description}" /></textarea>
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
