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
    
    <title>招聘管理系统-账号异常</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="账号异常">
	<meta http-equiv="description" content="账号异常">
	
	<spring:message code="jsp.include.basecss"/>
	
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link type="text/css" rel="stylesheet" href="http/business/exception/css/prompt.css">
	
  </head>
  
  <body>
  <nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="manage/home/index.do">招聘管理系统</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li>
						<h6 class="navbar-right">
							欢迎您，
							<c:out value="${user.sname }(${user.userName })"></c:out>
						</h6>
					</li>
				</ul>
			</div>
		</div>
	</nav>
  		  <div class="prompt_frame">
						<div class="prompt_container clearfloat">
								<div class="prompt_content_error"></div><!-- 图片 -->
								<div class="prompt_content_right">
									<div class="prompt_content_inside">
										您的请求已提交，系统返回如下信息：
										<div class="msgtitle errortitle"><c:out value="${message}" /></div>
										<div class="prompt_title">您可以尝试：</div>
										<div class="msgtitle"><a href="javascript:void(0)" onclick="toIndex()">返回首页</a></div>
										<div class="msglist">如有疑问请联系系统管理员</div>
									</div>
								</div>
				</div>
		</div>
  		  
    	<spring:message code="jsp.include.basejs"/>
  </body>
  <script type="text/javascript">
  //实现jsp中的basePath效果
  	function toIndex(){
  		top.location.href="<%=basePath%>manage/home/index.do";
  	}
  </script>
</html>
