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

<title>IT外包平台</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<spring:message code="jsp.include.basecss" />
<spring:message code="jsp.include.formccforback" />
<link rel="stylesheet" href="http/business/login/css/public.css">
</head>
<header>
	<div class="container">
		<ul class="nav nav-pills navbar-right">
			<!-- <li><a target="_blank" href="http://192.168.1.32/index.php">禅道</a></li>
			<li><a target="_blank" href="http://192.168.1.32:8081/nexus/index.html#welcome">maven仓库</a></li>
			<li><a target="_blank" href="http://192.168.1.88:8001/zabbix/index.php">服务器监控</a></li> -->
			<li><a href="logout.do"><span
					class="glyphicon glyphicon-new-window"></span> 退出</a></li>
		</ul>
	</div>
	<nav class="navbar navbar-default navbaro">
		<div class="container">
			<div class="navbar-header">
				<a href="javascript:void(0);" class="navbar-brand"> <img
					src="http/business/login/images/nav-logo.png" class="logo-img"> <span
					class="logo-text">IT外包平台</span>
				</a>
			</div>
			<div class="collapse navbar-collapse fb-navbar">
				<ul class="nav navbar-nav">
					<li class="active parent"><a href="###" id="dropdownMenu1"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">需求 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a id="requirementHref" class="click_a" href="javascript:void(0);"  url="business/requirement/toList.do">我的需求</a></li>
							<li><a id="requirementSearchHref" class="click_a" href="javascript:void(0);"  url="business/requirement/toSearchList.do">需求搜索页</a></li>
						</ul>
					</li>
					<li class="active parent"><a href="###" id="dropdownMenu1"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">简历<span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a id="resumeHref" class="click_a" href="javascript:void(0);"  url="business/resume/toList.do">我的简历</a></li>
						</ul>
					</li>
					<li class="active parent"><a href="###" id="dropdownMenu1"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">系统管理 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a id="sysHref" class="click_a" href="javascript:void(0);"  url="business/dictType/toList.do">数据字典</a></li>
							<li><a id="sysPositionHref" class="click_a" href="javascript:void(0);"  url="business/position/toTypeList.do">职位管理</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>
<div>
	<div id="prevContent" class="nav-add container"></div>
    <div id="content" class="container">
	
	</div>
</div>

<spring:message code="jsp.include.basejs" />
<spring:message code="jsp.include.formjsforback" />
<script type="text/javascript">
		var temp=[];
		temp["requirementHref"]=[{name:"需求",url:""},{name:"我的需求",url:"business/requirement/toList.do"}];
		temp["requirementSearchHref"]=[{name:"需求",url:""},{name:"需求搜索页",url:"business/requirement/toSearchList.do"}];
		temp["resumeHref"]=[{name:"简历",url:""},{name:"我的简历",url:"business/resume/toList.do"}];
		temp["sysHref"]=[{name:"系统管理",url:""},{name:"数据字典",url:"business/dictType/toList.do"}];
		temp["sysPositionHref"]=[{name:"系统管理",url:""},{name:"职位管理",url:"business/position/toTypeList.do"}];

		function click(obj) {
			$(".active").removeClass("active");
			$(obj).parents(".parent:eq(0)").addClass("active");
			var url=$(obj).attr("url");
			if(!url)
				return;
			$("#content").load(url);
			var id=$(obj).attr("id");
			var t=temp[id];
			if(t){
				var str=['<ol class="breadcrumb">'];
        		for(var i=0;i<t.length;i++) {
        			var url=t[i].url;
        			var css=i==t.lenth-1 ? "click_a active" : "click_a";
        			str.push('<li><a href="javascript:void(0);" url="'+url+'" class="'+css+'">'+t[i].name+'</a></li>');
        		}
        		str.push("</ol>");
				$("#prevContent").html(str.join(""));
			}
		}
		
		click($("#projectHref"));
		
		$(document).on("click",".click_a",function(){
			click($(this));
		})
		
		$(".dropdown-toggle").on("click",function(){
			$(".dropdown-menu").hide();
			var ul=$(this).next();
			ul.show();
		})
		
		$(document).click(function(){
		    $(".dropdown-menu").hide();
		  })
		
	</script>
</body>
</html>
