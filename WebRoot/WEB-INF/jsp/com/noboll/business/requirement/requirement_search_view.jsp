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

<title>招聘详情</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<spring:message code="jsp.include.tagscssforback" /> 
<style type="text/css">
.form-horizontal .form-group {
  margin-right: -40px;
  margin-left: -60px;
}
</style>
<spring:message code="jsp.include.basecss"/> 
<link rel="stylesheet" href="http/business/login/css/public.css">
<link rel="stylesheet" href="http/business/requirement/detail/common.css" >
<link rel="stylesheet" href="http/business/requirement/detail/page.css" >
<link href="http/business/search/search.css" rel="stylesheet">
<link href="http/business/search/css/style.css" rel="stylesheet">
</head>

<body>
<body>
	<nav class="navbar navbar-default navbaro">
		<div class="container">
			<div class="navbar-header">
				<a href="home.do" class="navbar-brand"> <img
					src="http/business/login/images/nav-logo.png" class="logo-img"> <span
					class="logo-text">IT外包平台</span>
				</a>
			</div>
		</div>
	</nav>
	<div id="job-view-enterprise">
		<div class="wrap clearfix">
			<div class="main ">
				<div class="about-position">
					<div class="title-info">
						<h1 title="<c:out value="${requirement.positionName}"/>"><c:out value="${requirement.positionName}"/></h1>
						<h3>
							<c:out value="${requirement.customerName}"/>
						</h3>
						
						<span class="triangle"></span>
					</div>
					<!-- 已暂停职位 -->
					
					<div class="job-item">
						<div class="clearfix">
							<div class="job-title-left">
								<p class="job-item-title">薪资面议</p>
								<p class="basic-infor">
									<span> <i class="icons24 icons24-position"></i>
													<a href="javascript:void(0)"><c:out value="${requirement.addressName}"/></a>
									</span> <span> <i class="icons24 icons24-time"></i><fmt:formatDate value="${requirement.createTime}" pattern="yyyy-MM-dd"/></span>
								</p>
								<div class="job-qualifications">
									<span><c:out value="${requirement.levelName}"/></span><span>学历不限</span>
								</div>
							</div>
							<!-- 投递按钮 start -->
							<div class="right-control" id="deliverBtn">
								<div data-selector="fix-jobapply" class="fix-stop">
									<a href="javascript:;" class="btn-apply btn btn-warning" id="addDeliver" style="top: auto;display:none" onclick="addDeliver('<c:out value="${requirement.id}"/>');">我要投递</a>
									<a href="javascript:;" class="btn-apply btn btn-warning" id="viewDeliver" style="top: auto;display:none" onclick="viewDeliver('<c:out value="${requirement.deliverId}"/>');">投递详情</a>
								</div>
							</div>
						</div>
						<!-- tags -->
							<div class="tag-list" id="tags"></div>
						
					</div>
					<!-- 岗位描述 -->
					<div class="job-item main-message">
						<h3 class="job-title">岗位描述：</h3>
						<div class="content content-word">
							<c:out value="${requirement.description}"/>
						</div>
					</div>
					    <!--  民生银行在猎聘网职位页面不展示岗位要求和薪资福利字段内容 -->
					
					<!-- 岗位职责 -->
					<div class="job-item main-message">
						<h3 class="job-title">岗位职责：</h3>
						<div class="content">
							<c:out value="${requirement.duty}"/>
						</div>
					</div>
					
					<!-- 岗位要求 -->
					<div class="job-item main-message">
						<h3 class="job-title">岗位要求：</h3>
						<div class="content">
							<c:out value="${requirement.demand}"/>
						</div>
					</div>
					
					<!-- 企业介绍 -->
					
					<div class="job-resume-block " data-selector="job-resume-block"></div>
				</div>
				
			</div>
			<div class="side">
				
				<div class="right-blcok-post">
					<div class="right-post-top">
						<h3 class="job-title">职位发布者</h3>
						<div class="publisher-infor">
							<p>
								<img class="tinyFace circle" src="http/business/requirement/detail/55555f5128ee44a891961a1902c.gif">
							</p>
							<p class="publisher-name">
								<!-- <span>xxx</span> 
								<em class="muted">/ HR</em> -->
							</p>
							<p><c:out value="${requirement.customerName}"/></p>
							  <p>
<!-- 								<a class="btn btn-primary" href="javascript:;">立即沟通</a> -->
							  </p>
							
						</div>
						
						<div class="apply-check">
							<span> 
							        <em id="reqCount"></em>份
							<p class="muted">职位发布数</p>
							</span> 
							<span>
							        <em id="deliverCount"></em>份
							        <p class="muted">投递数</p>
							</span>
						</div>
						
						<div class="company-infor">
							<h3 class="job-title">企业简介</h3>
							<ul>
							    <li id="industryName"></li>
							    <li id="natureName"></li>
							    <li id="scaleName"></li>
							</ul>
						</div>
						
						<div class="company-infor">
			  			<h3 class="job-title">可能感兴趣的职位</h3>
			  			<c:choose>
			  				<c:when test="${empty labelMatchRequirments}">
			  					<b>暂时没有匹配您的招聘需求，赶紧上传简历并贴上标签吧！</b>
			  				</c:when>
			  				<c:otherwise>
			  					<c:forEach  items="${ labelMatchRequirments}" var="data">
			  						<div class="search_con" style="padding:5px 0 5px 25px">
						    			<div class="row">
							    			<div class="search_list_01"><c:out value="${data.positionName }"></c:out></div>
							    			<div class="search_list_02">
								    			<span class="search_span"><c:out value="${data.customerName }"></c:out></span>
								    			<span class="search_span">|</span> 
								    			<span class="search_span"><c:out value="${data.addressName }"></c:out></span>
								    			<span class="search_span">|</span>
								    			<span class="search_span"><c:out value="${data.levelName }"></c:out></span>
							    			</div> 
							    			<!-- <div class="search_list_03">
							    			</div> -->
							    			<div class="search_list_03">
								    			<span class="search_span">发布日期：<fmt:formatDate value="${data.createTime}" type="both" pattern="yyyy-MM-dd"/></span>
							    			</div>
						    			</div>      
						    			<div class="row"  style="line-height:60px; vertical-align:middle;">  
							    				<a class="search_a1" title="查看招聘详情" href="business/requirement/toSearchView.do?id=<c:out value="${data.id }"/>" target="_blank"> 立即查看</a>
							    				<a class="search_a1" title="投递简历" href="javascript:void(0)" onclick="addDeliver('<c:out value="${data.id }"></c:out>')"> 我要投递</a>
						    			</div>
					    			</div>
				    				<hr/>
			  					</c:forEach>
			  				</c:otherwise>
			  			</c:choose>
		  				</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<spring:message code="jsp.include.basejs" />
	<spring:message  code="jsp.include.tagsjsforback"/>
	<script type="text/javascript">
		
		$(document).ready(function() {
			// 标签
			var customerLabels = '<c:out value="${ customerLabels}" escapeXml="false"/>';
			var labelData = [];
			if(customerLabels)
				labelData = tools.jsonToObj(customerLabels);
			$("#tags").initTags({
				selects:labelData,
				readonly:true//是否只读，不显示待选区域
			});
			var deliverStatus = '<c:out value="${ requirement.deliverStatus}"/>';
			if(deliverStatus && "1" != deliverStatus && tools.checkUrlPermission("business/deliver/toAdd.do")){
				$("#viewDeliver").remove();
				$("#addDeliver").show();
			}else if(deliverStatus && "1" == deliverStatus && tools.checkUrlPermission("business/deliver/toView.do")){
				$("#addDeliver").remove();
				$("#viewDeliver").show();
			}else{
				$("#addDeliver").remove();
				$("#viewDeliver").remove();
			}
			
			// 企业简介
			tools.ajax({
 				url: "business/customer/getCustomer.do", 
				data: {id:'<c:out value="${ requirement.customerId}"/>'}, 
				async: false,
				success: function(data){
					if (data) {
						$("#industryName").html(data.industryName);
						$("#natureName").html(data.natureName);
						$("#scaleName").html(data.scaleName);
						$("#deliverCount").html(data.deliverCount);
						$("#reqCount").html(data.reqCount);
					}
	
				}	 				
 			})
				
		});
						
		function closeDialog() {
			tools.closeDialog();
		}
		
		function addDeliver(id){
			tools.dialog({
				name:"投递简历",
				url:"business/deliver/toAdd.do?id="+id,
				width:"600px",
				height:"320px",
				close:function(){
					window.location.reload();
				}
			});
		}
		
		function viewDeliver(id){
			tools.dialog({
				name:"投递详情",
				url:"business/deliver/toView.do?id="+id,
				width:"850px",
				height:"500px",
				close:function(){
				}
			});
		}
		
	</script>
</body>
</html>
