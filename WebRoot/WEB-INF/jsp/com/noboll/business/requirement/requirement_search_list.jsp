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
    
    <title>需求搜索页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.formccforback" />
	<spring:message code="jsp.include.listcssforback"/>
	<link rel="stylesheet" href="http/business/login/css/public.css">
	<link href="http/business/search/search.css" rel="stylesheet">
	<link href="http/business/search/css/style.css" rel="stylesheet">
	
  </head>
  
  <body>
	  <div class="form-horizontal" id="searchDiv" style="height:600px">
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
	  		<div class="col-xs-9">
				<div class="row" style="padding:20px;">
  					<form id="myform">
						<select class="search-Class select searchingSelect" dictCode="address" name="addressName" style="display:inline;"></select>
						<input placeholder="输入职位名称或者公司名称搜索" name="pcName" id="pcName" class="search-Class searchingTxt" type="text"/>
						<!-- 左侧查询条件 -->
						<c:forEach items="${conditionList }" var="condition" varStatus="index">
							<input type="hidden" name="<c:out value="${condition.code }" />" id="<c:out value="${condition.code }" />" class="search-Class searchingTxt"/>
							<c:forEach items="${condition.list }" var="item">
								<c:if test="${not empty item.children }">
									<input type="hidden" name="<c:out value="${item.children.code }" />" id="<c:out value="${item.children.code }" />" class="search-Class searchingTxt"/>
								</c:if>
							</c:forEach>
						</c:forEach>
						<button class="searchingBtn" type="button" id="btnSearch" onclick="searchByButton();">搜索</button>
					</form> 
				</div>
				<div class="row">
					<div class="tab-content" id="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="longTop">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								Hi，您目前在 <span id="span_address">深圳</span> 搜索关键字 “ <b class=" cc20000"><span id="keyword">关键字</span></b> ”，共找到 <span id="total">999</span> 个内容。
							</div>
							<div class="table-responsive">
								<table id="table-javascript" ></table>
					    	</div>
						</div>
					</div>
				</div>
				<!-- 右侧：列表 end -->
			</div>
		</div>
    	<spring:message code="jsp.include.basejs"/>
    	<spring:message code="jsp.include.formjsforback" />
    	<spring:message code="jsp.include.listjsforback"/>
    	<script type="text/javascript" src="http/business/search/js/script.js"></script>
    	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
    			$("#myform").initForm({});
		       	$("#table-javascript").initBootTable({
		       		method: 'post',
	                url: 'business/requirement/searchList.do',
	                addUrl:"",
	                striped: true,
	                permissionOperate:function(data){
	                	var op={};
	                	if(data.deliverStatus=="1") {
	                		op["deliver"]="false";
	                	}else{
	                		op["deliverDetail"]="false";
	                	}
	                	return op;
	                }, 
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	/* {name:'发布',position:'row',css:"publish",type:"ajax",url:"business/requirement/publish.do"},//发布 
	                	{name:'结束',position:'row',css:"finish",type:"ajax",url:"business/requirement/finish.do"},//结束  */
	                	{name:'投递简历',position:'row',css:"deliver",a:'',dialog:{url:"business/deliver/toAdd.do",width:"600px",height:"320px"}},//投递
	                	{name:'投递详情',position:'row',css:"deliverDetail",a:'',func:unfinish,url:""}//投递详情
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                	{field: 'deliverStatus',title:'deliverStatus',visible:false}, 
				                    {field: 'code',title: '需求编码',align: 'center',valign: 'middle',sortable: true,searchable:true}, //编码
// 				                    {field: 'name',title: '需求名称',align: 'center',valign: 'middle',sortable: true,searchable:true}, //名称
				                    {field: 'customerName',title: '公司名称',align: 'center',valign: 'middle',sortable: true,searchable:true}, //客户
				                    {field: 'addressName',index:'address',title: '地址',align: 'left',valign: 'top',searchable:true,type:'select:address',selectCode:"id"},	//描述
				                    {field: 'positionName',title: '职位',align: 'left',valign: 'top'},//职位
				                    {field: 'levelName',title: '级别',align: 'left',valign: 'top'},//级别
				                    {field: 'num',title: '需求人数',align: 'left',valign: 'top'},//需求人数
				                    {field: 'periodName',title: '周期',align: 'left',valign: 'top'},//周期
				                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top'}	//创建时间
				                  ]
	            	});
		    	});
		    	
		    	function searchByButton(){
		    		$("#table-javascript").search('search-Class','table-javascript','searchDiv');
		    	}
		    	
		    	function unfinish(){
		    		alert("暂未实现！");
		    	}
		    	
    	</script>
  </body>
</html>
