<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <base href="<%=basePath%>">
    
    <title>demo</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="demo">
	<meta http-equiv="description" content="demo">
	
	<link href="http/bootstrap-3.3.4/css/bootstrap.min.css" rel="stylesheet">

  </head>
  
  <body>
  		  <div class="panel panel-default">
		  <!-- Default panel contents -->
		  <div class="panel-heading">DEMO 列表页面</div>
		
		  <!-- Table -->
		  <table class="table">
		    	<tr>
	    			<td>id</td>
	    			<td>名字</td>
	    			<td>密码</td>
	    			<td>账号</td>
	    		</tr>
	    		<c:choose>
	    			<c:when test="${list==null}">
	    				<tr>
	    					<td colspan="4">没有数据</td>
	    				</tr>
	    			</c:when>
	    			<c:otherwise>
	    				<c:forEach items="${list}" var="data">
				    			<tr>
					    			 <td><c:out value="${data.id}" /></td>
					    			 <td><c:out value="${data.name}"/></td>
					    			 <td><c:out value="${data.password}"/></td>
					    			 <td><c:out value="${data.loginId}"/></td>
				    			 </tr>
			    		</c:forEach>
	    			</c:otherwise>
	    		</c:choose>
		  </table>
		</div>
  
    	<table>
    		
    		
    	</table>
    	
    	<script src="http/common/js/jquery-1.9.1.min.js"></script>
    	<script src="http/bootstrap-3.3.4/js/bootstrap.min.js"></script>
  </body>
</html>
