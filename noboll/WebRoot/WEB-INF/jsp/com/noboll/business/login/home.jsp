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
			<li><a href="logout.do"><span
					class="glyphicon glyphicon-new-window"></span> 退出</a></li>
		</ul>
	</div>
	<nav class="navbar navbar-default navbaro">
		<div class="container">
			<div class="navbar-header">
				<a href="home.do" class="navbar-brand"> <img
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
							<li><a id="requirementMyHref" class="click_a" href="javascript:void(0);"  url="business/requirement/toMyList.do">我的需求</a></li>
							<li><a id="requirementHref" class="click_a" href="javascript:void(0);"  url="business/requirement/toList.do">需求列表</a></li>
							<li><a id="requirementSearchHref" class="click_a" href="javascript:void(0);"  url="business/requirement/toSearchList.do">职位搜索</a></li>
						</ul>
					</li>
					<li class="active parent"><a href="###" id="dropdownMenu1"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">简历<span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a id="resumeHref" class="click_a" href="javascript:void(0);"  url="business/resume/toMyList.do">我的简历</a></li>
							<li><a id="resumeSearchHref" class="click_a" href="javascript:void(0);"  url="business/resume/toSearchList.do">简历搜索</a></li>
						</ul>
					</li>
					<li class="active parent"><a href="###" id="dropdownMenu1"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">面试 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a id="deliverManageHref" class="click_a" href="javascript:void(0);"  url="business/deliver/toManageList.do">投递管理-管理员</a></li>
							<li><a id="deliverMyHref" class="click_a" href="javascript:void(0);"  url="business/deliver/toMyList.do">我的投递</a></li>
							<li><a id="deliverCustomerHref" class="click_a" href="javascript:void(0);"  url="business/deliver/toCustomerList.do">投递管理-客户</a></li>
							<li><a id="interviewMyHref" class="click_a" href="javascript:void(0);"  url="business/interview/toMyList.do">我的面试</a></li>
							<li><a id="entrantHref" class="click_a" href="javascript:void(0);"  url="business/entrant/toList.do">入职记录</a></li>
						</ul>
					</li>
					<li class="active parent"><a href="###" id="dropdownMenu1"
						class="dropdown-toggle" data-toggle="dropdown" role="button"
						aria-haspopup="true" aria-expanded="false">系统管理 <span
							class="caret"></span></a>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a id="sysUserInfoHref" class="click_a" href="javascript:void(0);"  url="business/userInfo/toList.do">用户管理</a></li>
							<li><a id="sysCustomerHref" class="click_a" href="javascript:void(0);"  url="business/customer/toList.do">客户管理</a></li>
							<li><a id="sysHref" class="click_a" href="javascript:void(0);"  url="business/dictType/toList.do">数据字典</a></li>
							<li><a id="sysPositionHref" class="click_a" href="javascript:void(0);"  url="business/position/toTypeList.do">职位管理</a></li>
							<li><a id="sysLabelHref" class="click_a" href="javascript:void(0);"  url="business/label/toList.do">标签管理</a></li>
							<li><a id="sysEvaluateLabelHref" class="click_a" href="javascript:void(0);"  url="business/evaluateLabel/toList.do">评价标签管理</a></li>
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
		// 需求
		temp["requirementMyHref"]=[{name:"需求",url:""},{name:"我的需求",url:"business/requirement/toMyList.do"}];
		temp["requirementHref"]=[{name:"需求",url:""},{name:"需求列表",url:"business/requirement/toList.do"}];
		temp["requirementSearchHref"]=[{name:"需求",url:""},{name:"需求搜索",url:"business/requirement/toSearchList.do"}];
		// 简历
		temp["resumeHref"]=[{name:"简历",url:""},{name:"我的简历",url:"business/resume/toList.do"}];
		temp["resumeSearchHref"]=[{name:"简历",url:""},{name:"简历搜索",url:"business/resume/toSearchList.do"}];
		// 面试
		temp["deliverManageHref"]=[{name:"面试",url:""},{name:"投递管理-管理员",url:"business/deliver/toManageList.do"}];
		temp["deliverMyHref"]=[{name:"面试",url:""},{name:"我的投递",url:"business/deliver/toMyList.do"}];
		temp["deliverCustomerHref"]=[{name:"面试",url:""},{name:"投递管理-客户",url:"business/deliver/toCustomerList.do"}];
		temp["interviewMyHref"]=[{name:"面试",url:""},{name:"我的面试",url:"business/interview/toMyList.do"}];
		temp["entrantHref"]=[{name:"面试",url:""},{name:"入职记录",url:"business/entrant/toList.do"}];
		// 系统管理
		temp["sysUserInfoHref"]=[{name:"系统管理",url:""},{name:"用户管理",url:"business/userInfo/toList.do"}];
		temp["sysCustomerHref"]=[{name:"系统管理",url:""},{name:"客户管理",url:"business/customer/toList.do"}];
		temp["sysHref"]=[{name:"系统管理",url:""},{name:"数据字典",url:"business/dictType/toList.do"}];
		temp["sysPositionHref"]=[{name:"系统管理",url:""},{name:"职位管理",url:"business/position/toTypeList.do"}];
		temp["sysLabelHref"]=[{name:"系统管理",url:""},{name:"标签管理",url:"business/label/toList.do"}];
		temp["sysEvaluateLabelHref"]=[{name:"系统管理",url:""},{name:"评价标签管理",url:"business/evaluateLabel/toList.do"}];

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
		
		// 第一次进入页面加载
		$(document).ready(function(){
			var initMenu = '<c:out value="${initMenu}"/>';
			click($("#"+initMenu));
			
			// 根据权限处理菜单显示
			$(".click_a").each(function(){
				var url = $(this).attr("url");
				if(!tools.checkUrlPermission(url))
					$(this).parent().remove();
			})
			
			$("ul .parent").each(function(){
				// 没有有权限的子菜单，则隐藏此菜单
				if ($(this).find("ul li").length <= 0)
					$(this).remove();
		})
	})

	$(document).on("click", ".click_a", function() {
		click($(this));
	})

	$(".dropdown-toggle").on("click", function() {
		$(".dropdown-menu").hide();
		var ul = $(this).next();
		ul.show();
	})

	$(document).click(function() {
		$(".dropdown-menu").hide();
	})
</script>
</body>
</html>
