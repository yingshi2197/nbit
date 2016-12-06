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
  	
    <base href="<%=basePath%>">
    
    <title>demo</title>
	
  </head>
  
  <body>
	  <div class="container">
	  		<form action="demo/add.do" id="myform" class="form-horizontal" role="form" method="post" >
	          <div class="form-group">
			      <label for="name">姓名</label>
			      <input type="text" class="form-control required" name="name" id="name" 
			         placeholder="请输入姓名" />
			   </div>
			   <div class="form-group">
			      <label for="password">密码</label>
			      <input type="password" class="form-control required" name="password" id="password" 
			         placeholder="请输入密码"  />
			   </div>
			   <div class="form-group">
			      <label for="loginId">账号</label>
			      <input type="text" class="form-control required" name="loginId" id="loginId" 
			         placeholder="请输入名称"/>
			   </div>
			   <button type="submit" class="btn btn-default">提交</button>
	      </form>
	  </div>
	  
  </body>
</html>
