<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    
    <title>我的简历</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	<link rel="stylesheet" href="http/business/login/css/public.css">
		<link href="http/business/search/search.css" rel="stylesheet">
	<link href="http/business/search/css/style.css" rel="stylesheet">
	
  </head>
  
  <body>
	  <div class="form-horizontal">
		  		<!-- 左侧：列表 begin -->
		  		<div class="col-xs-9">
			  		<div class="table-responsive">
						<div class="panel-heading-choose">我的简历</div>
					  	<div id="searchDiv"></div>
						<table id="table-javascript" ></table>
			    	</div>
		  		</div>
				<!-- 左侧：列表 end -->
				<!-- 右侧：可能感兴趣的职位 begin -->
		  		<div class="col-xs-3">
		  			<div style="padding:5px 10px;border:#ddd 1px solid;border-radius:4px;margin:5% auto;font-size:12px">
		  			<li class="select-result">
						<h4><b>可能感兴趣的职位</b></h4>
					</li>
		  			<c:choose>
		  				<c:when test="${empty labelMatchRequirments}">
		  					<b>暂时没有匹配您的招聘需求，赶紧上传简历并贴上标签吧！</b>
		  				</c:when>
		  				<c:otherwise>
		  					<c:forEach  items="${ labelMatchRequirments}" var="data">
			    				<hr/>
		  						<div class="search_con" style="padding:5px 0 5px 15px">
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
		  					</c:forEach>
		  				</c:otherwise>
		  			</c:choose>
		  			</div>
		  		</div>
		  		<!-- 右侧：可能感兴趣的职位 end -->
	 	</div>
  	
    	<spring:message code="jsp.include.basejs"/>
    	<spring:message code="jsp.include.listjsforback"/>
    	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
		       	$("#table-javascript").initBootTable({
		       		method: 'post',
		       		searchDiv:"searchDiv",
	                url: 'business/resume/myList.do',
	                addUrl:"",
	                striped: true,
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
                        {name:'新增简历',position:'top',select:"",css:"add",dialog:{url:"business/resume/toAdd.do",width:"1100px",height:"500px"}},//新增
	                	{name:'完善简历',resume:'row',css:"edit",a:'',dialog:{url:"business/resume/toEdit.do",width:"1100px",height:"500px"}},//修改
	                	{name:'简历详情',position:'row',css:"resumeDetail",a:'',dialog:{url:"business/resume/toView.do",width:"1100px",height:"500px"}}//简历详情
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '姓名',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'sexName',index:"sex",title: '性别',align: 'center',valign: 'middle'}, //性别
				                    {field: 'mobile',title: '联系电话',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'yearsName',title: '工作年限',align: 'center',valign: 'middle',searchable:true}, //工作年限
				                    {field: 'address',index:'address',title: '住址',align: 'left',valign: 'top'},	//住址
				                    {field: 'degreeName',title: '学历',align: 'left',valign: 'top'},//描述
				                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top'}	//上传时间
				                  ]
	            	});
		    	});
    		  
    	    function addDeliver(id){
    			tools.dialog({
    				name:"投递简历",
    				url:"business/deliver/toAdd.do?id="+id,
    				width:"600px",
    				height:"320px",
    				close:function(){
    					$("#table-javascript").refresh();//刷新页面
    				}
    			});
    		}
    	</script>
  </body>
</html>
