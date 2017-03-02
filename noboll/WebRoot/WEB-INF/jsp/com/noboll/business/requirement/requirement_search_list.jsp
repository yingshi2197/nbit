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
    
    <title>职位搜索页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.formccforback" />
	<spring:message code="jsp.include.listcssforback"/>
	<spring:message code="jsp.include.tagscssforback" /> 
	<link rel="stylesheet" href="http/business/login/css/public.css">
	<link href="http/business/search/search.css" rel="stylesheet">
	<link href="http/business/search/css/style.css" rel="stylesheet">
	
  </head>
  
  <body>
	  <div class="form-horizontal" id="searchDiv">
	  		<!-- 左侧：分类条件 begin -->
	  		<div class="col-xs-3">
		  		<ul class="search-select">
		  			<li class="select-result">
						<dl><b>已选择：</b></dl> 
						<dl class="selectr">  
							<dd class="select-no">暂时没有选择过滤条件</dd>
						</dl>
					</li>
			  		<c:forEach items="${conditionList }" var="condition" varStatus="index">
							<li class="select-list">
								<dl><b><c:out value="${condition.name }" /></b></dl>
								<dl id="select_<c:out value="${index.index+1 }" />" class="selectw" queryCode="<c:out value="${condition.code }" />">
									<dd queryCode="<c:out value="${condition.code }" />" queryValue="" class="select-all selected"><a href="javascript:void(0)">全部</a></dd>
									<c:forEach items="${condition.list }" var="item">
										<dd id="<c:out value="${item.code }" />" queryCode="<c:out value="${condition.code }" />" queryValue="<c:out value="${item.id }" />" typeCode="<c:out value="${item.typeCode }" />" code="<c:out value="${item.code }" />">
											<a href="javascript:void(0)"><c:out value="${item.name }" /></a>
										</dd>
									</c:forEach>
								</dl>
								<!-- 子项 --> 
								<c:forEach items="${condition.list }" var="item">
									<c:if test="${not empty item.children }">
										<dl id="select_<c:out value="${index.index+1 }" />_<c:out value="${item.code }" />" typeCode="<c:out value="${item.children.typeCode }" />" class="selectc select_<c:out value="${index.index+1 }" />" queryCode="<c:out value="${item.children.code }" />" style="display:none;padding:5px;border:#ddd 1px solid;border-radius:4px;">
											<dd queryCode="<c:out value="${item.children.code }" />" queryValue="" typeCode="<c:out value="${item.children.typeCode }" />" class="select-all" style="float:left;display:inline;margin:0 0 5px 5px;"><a href="javascript:void(0)">全部</a></dd>
											<c:forEach items="${item.children.list }" var="child">
												<dd queryCode="<c:out value="${item.children.code }" />" queryValue="<c:out value="${child.id }" />" typeCode="<c:out value="${child.typeCode }" />" ><a href="javascript:void(0)"><c:out value="${child.name }" /></a></dd>
											</c:forEach>
										</dl> 
									</c:if>
								</c:forEach>	
							</li>
			  		</c:forEach>
				</ul>
	  		</div>
	  		<!-- 左侧：分类条件 end -->
	  		<!-- 右侧：列表 begin -->
	  		<div class="col-xs-6">
				<div class="row" style="padding-top:20px;">
  					<form id="myform">
						<select placeholder="地区" class="search-Class select searchingSelect" dictCode="address" name="addressName" id="addressName" style="display:inline;"></select>
						<input placeholder="输入职位名称或者公司名称搜索" name="pcName" id="pcName" class="search-Class searchingTxt" type="text" style="width:388px;"/>
						<!-- 左侧查询条件 -->
						<c:forEach items="${conditionList }" var="condition" varStatus="index">
							<input type="hidden" name="<c:out value="${condition.code }" />" id="<c:out value="${condition.code }" />" class="search-Class searchingTxt"/>
							<c:forEach items="${condition.list }" var="item">
								<c:if test="${not empty item.children }">
									<input type="hidden" name="<c:out value="${item.children.code }" />" id="<c:out value="${item.children.code }" />" class="search-Class searchingTxt"/>
								</c:if>
							</c:forEach>
						</c:forEach>
						<!-- 左侧查询条件 end-->
						<button class="searchingBtn" type="button" id="btnSearch" onclick="searchByButton();">搜索</button>
					</form> 
				</div>
				<div class="row">
					<div class="tab-content" id="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="longTop">
								<span style="display:none" id="remindDiv">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									Hi，您目前在<span id="span_address" class=" cc20000">所有地区</span><label id="keywordLabel">通过关键字 “<b class=" cc20000" id="keyword"></b> ”</label>搜索招聘需求，共找到 <span id="total"></span> 个内容。
								</span>	
							</div>
							<div class="table-responsive"  style="min-height:500px;">
								<table id="table-javascript"></table>
					    	</div>
						</div>
					</div>
				</div>
				<!-- 右侧：列表 end -->
			</div>
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
							    			<span class="search_span">最近更新：<fmt:formatDate value="${data.lastModifyTime}" type="both" pattern="yyyy-MM-dd"/></span>
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
    	<spring:message code="jsp.include.formjsforback" />
    	<spring:message code="jsp.include.listjsforback"/>
    	<spring:message  code="jsp.include.tagsjsforback"/>
    	<script type="text/javascript" src="http/business/search/js/script.js"></script>
    	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
    			$("#myform").initForm({});
		       	 
	    		$("#table-javascript").initBootTable({
	    			method: 'post',
	    			url:tools.joinUrl('business/requirement/searchList.do'),
	    			striped: true,
	    			pagination: true,
	    			showHeader: false,
	    			sidePagination:"server",
	    			pageSize: 10,
	    			minimumCountColumns: 2,
	    			responseHandler: function (res) {//将搜索结果数量显示到页面中的标题
	    				var total=res.total;//总数量
	    				$("#total").html(total);
	    	            return res;
	    			},
	    			columns: [
			                	//{field: 'statu_msb',checkbox: true},   //复选框
			                	{field: 'id',title:'id',visible:false}, 
			                	{field: 'deliverStatus',title:'deliverStatus',visible:false}, 
			                	{field: 'deliverId',title:'deliverId',visible:false}, 
			                	{field: 'customerLabels',title:'customerLabels',visible:false}, 
			                    {field: 'code',title: '需求编码',align: 'center',valign: 'middle',visible:false}, //编码
//			                    {field: 'name',title: '需求名称',align: 'center',valign: 'middle',sortable: true,searchable:true}, //名称
			                    {field: 'customerName',title: '公司名称',align: 'center',valign: 'middle',visible:false}, //客户
			                    {field: 'addressName',index:'address',title: '地址',align: 'left',valign: 'top',visible:false},	//描述
			                    {field: 'positionName',title: '职位',align: 'left',valign: 'top',visible:false},//职位
			                    {field: 'levelName',title: '级别',align: 'left',valign: 'top',visible:false},//级别
// 			                    {field: 'num',title: '需求人数',align: 'left',valign: 'top'},//需求人数
// 			                    {field: 'periodName',title: '周期',align: 'left',valign: 'top'},//周期
			                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top',visible:false},	//创建时间
			                    {field: 'content',formatter:formatContent,title:'id',visible:true}
			               ]
	    			
          			});
	    	    });
	    	    
	    	    function formatContent(a,data){
	    	    	var arr=[];
	    	    	arr.push("<div class=\"search_con\">"); 
	    			arr.push("<div class=\"col-md-9\">");
	    			arr.push("<div class=\"search_list_01\">"+data.positionName+"</div>");
	    			arr.push("<div class=\"search_list_02\">");
	    			arr.push("<span class=\"search_span\">"+data.customerName+"</span>");
	    			arr.push("<span class=\"search_span\">|</span>"); 
	    			arr.push("<span class=\"search_span\">"+data.addressName+"</span>");
	    			arr.push("<span class=\"search_span\">|</span>");
	    			arr.push("<span class=\"search_span\">"+data.levelName+"</span>");
	    			arr.push("</div>"); 
	    			arr.push("<div class=\"search_list_03\">");
	    			// 客户标签处理
	    			var tagObj = $("<span class=\"search_span\" id=\"tags_\""+(new Date().getTime()+"_tag_cn")+">ssssssss</span>");
	    			var customerLabels = data.customerLabels;
	    			var labelData = [];
	    			if(customerLabels)
	    				labelData = tools.jsonToObj(customerLabels);
	    			tagObj.initTags({
	    				selects:labelData,
	    				readonly:true//是否只读，不显示待选区域
	    			});
	    			arr.push(tagObj.html());
	    			arr.push("</div>");
	    			arr.push("<div class=\"search_list_03\">");
	    			arr.push("<span class=\"search_span\">需求编码："+data.code+"</span>");
	    			/* arr.push("<span class=\"search_span\">| </span>");
	    			arr.push("<span class=\"search_span\">提交人："+data.createUserId+"</span>"); */
	    			arr.push("<span class=\"search_span\">| </span>"); 
	    			arr.push("<span class=\"search_span\">发布时间："+tools.parseDate(data.createTime,"yyyy-MM-dd")+"</span>");
	    			arr.push("</div>");
	    			arr.push("</div>");
	    			
	    			arr.push("<div class=\"col-md-3\"  style=\" line-height:80px; vertical-align:middle;\">");
	    			if(tools.checkUrlPermission("business/requirement/toSearchView.do")){
		    			arr.push("<div style=\"width:50%;float:left; white-space:nowrap;\">");
		    			arr.push("<a class=\"search_a\" title=\"招聘详情\" href=\"business/requirement/toSearchView.do?id="+data.id+"\" target=\"_blank\"> 查看</a>");
		    			arr.push("</div>");
	    			}
	    			if("1" != data.deliverStatus && tools.checkUrlPermission("business/deliver/toAdd.do")){
	    				arr.push("<div style=\"width:50%;float:left; white-space:nowrap;\">");
		    			arr.push("<a class=\"search_a\" title=\"我要投递\" href=\"javascript:void(0)\" onclick=\"addDeliver('"+data.id+"')\"> 我要投递</a>");
		    			arr.push("</div>");
	    			}
	    			if(data.deliverStatus=="1" && tools.checkUrlPermission("business/deliver/toView.do")){
	    				arr.push("<div style=\"width:50%;float:left; white-space:nowrap;\">");
		    			arr.push("<a class=\"search_a\" title=\"投递详情\" href=\"javascript:void(0)\" onclick=\"viewDeliver('"+data.deliverId+"')\"> 投递详情</a>");
		    			arr.push("</div>");
	    			}
	    			arr.push("</div>");
	    			arr.push("</div>");
					
	    			return arr.join("");
	    	    }
	    	    
	    	    function viewRequirement(id){
	    			tools.dialog({
	    				name:"招聘详情",
	    				url:"business/requirement/toView.do?id="+id,
	    				width:"1000px",
	    				height:"500px",
	    				close:function(){
	    					$("#table-javascript").refresh();//刷新页面
	    				}
	    			});
	    		}
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
	    	    function viewDeliver(id){
	    			tools.dialog({
	    				name:"投递详情",
	    				url:"business/deliver/toView.do?id="+id,
	    				width:"850px",
	    				height:"500px",
	    				close:function(){
	    					$("#table-javascript").refresh();//刷新页面
	    				}
	    			});
	    		}
	    	    
		    	
		    	function searchByButton(){
		    		$("#remindDiv").show();
		    		var keyword = $("#pcName").val();
		    		if(keyword){
		    			$("#keywordLabel").show();
		    			$("#keyword").html(keyword);
		    		}else{
		    			$("#keywordLabel").hide();
			    		$("#keyword").html("");
		    		}
		    		var addressId = $("#addressName").val();
		    		var addressName = $("#addressName").find("option:selected").text();
		    		if(addressId){
		    			$("#span_address").html(addressName);
		    		}else{
			    		$("#span_address").html("全部地区");
		    		}
		    		$("#table-javascript").search('search-Class','table-javascript','searchDiv');
		    	}
		    	
    	</script>
  </body>
</html>
