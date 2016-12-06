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

<title>日志详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<spring:message code="jsp.include.listcssforback" />
<spring:message  code="jsp.include.tableccforback"/>
<style type="text/css">
body {
	padding-top: 30px;
}
</style>
</head>

<body>
	<div class="container" style="margin-top: 30px;">
		<div class="tab-form1">
		
			<div class="panel-heading-choose">最近10天日志</div>
			<table id="logTable" class="table  text-center">
				
			</table>
			
			<div class="col-xs-12 form-control-1 text-center">
				<span class="text-primary" style="color: red">*这里列出10天日志不一定有10天日志，如果不存在说明没有该日志</span>
			</div>
			
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
			var data=[];
			var port='${project.port}';
			if(port) {
				port=":"+port;
			}
			data.push('<tr class="trheadcss active">');
			data.push('<td colspan="3"><a target="_blank" href="http://${project.target}'+port+'/${project.code}/http/logs/catalina.log">今天日志</a></td>');
			data.push('</tr>')
			var temp=0;
			for(var i=1;i<10;i++) {
			    var now = new Date;
			    now.setDate(now.getDate() - i);
			    var str=now.format("yyyy-MM-dd");
			    if(temp==0||temp==3) {
			    	data.push('<tr class="trheadcss active">');
			    	temp=0;
			    }
				data.push('<td><a target="_blank" href="http://${project.target}'+port+'/${project.code}/http/logs/catalina.log.'+str+'">'+str+'日志</a></td>');
				temp++;
				if(temp==3) 
					data.push('</tr>');
			}
			$("#logTable").html(data.join(""));
		});
						
		function closeDialog() {
			tools.closeDialog();
		}

	</script>
</body>
</html>
