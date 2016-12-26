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
    
    <title>简历搜索页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.formccforback" />
	<spring:message code="jsp.include.listcssforback"/>
	<link rel="stylesheet" href="http/business/login/css/public.css">
	<link href="http/business/search/search.css" rel="stylesheet">
	
  </head>
  
  <body>
	  <div class="form-horizontal" id="searchDiv" style="height:600px">
	  		<!-- 左侧：分类条件 begin -->
	  		<div class="col-xs-3">
	  		
	  		</div>
	  		<!-- 左侧：分类条件 end -->
	  		<!-- 右侧：列表 begin -->
	  		<div class="col-xs-9">
				<div class="row">
  					<form id="myform">
						<select class="search-Class select searchingSelect" placeholder="地区" dictCode="address" name="intentionName" style="display:inline;"></select>
						<select class="search-Class select searchingSelect" placeholder="工作年限" dictCode="work_life" name="yearsName" style="display:inline;"></select>
						<input placeholder="输入简历姓名或者求职岗位搜索" name="pyName" id="pyName" class="search-Class searchingTxt" type="text"/>
						<button class="searchingBtn" type="button" id="btnSearch" onclick="searchByButton();">搜
								索</button>
					</form> 
				</div>
				<div class="row">
					<div class="tab-content" id="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="longTop">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
								Hi，您目前在 <span id="span_address">全站</span> 搜索关键字 “ <b class=" cc20000"><span id="keyword"></span></b> ”，共找到 <span id="total"></span> 个内容。
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
    	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
    			$("#myform").initForm({});
		       	$("#table-javascript").initBootTable({
		       		method: 'post',
	                url: 'business/resume/searchList.do',
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
	                	{name:'简历详情',position:'row',css:"resumeDetail",a:'',func:unfinish,url:""}//简历详情
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                	{field: 'name',title: '姓名',align: 'center',valign: 'middle',searchable:true}, //名称
				                    {field: 'sexName',index:"sex",title: '性别',align: 'center',valign: 'middle'}, //性别
				                    {field: 'yearsName',title: '工作年限',align: 'center',valign: 'middle',searchable:true}, //工作年限
				                    {field: 'intentionNames',title: '意向地区',formatter:fmtPi,align: 'center',valign: 'middle',searchable:true}, //意向地区
				                    {field: 'positionNames',title: '求职岗位',formatter:fmtPi,align: 'center',valign: 'middle',searchable:true}, //求职岗位
				                    {field: 'address',index:'address',title: '住址',align: 'left',valign: 'top'},	//住址
				                    {field: 'degreeName',title: '学历',align: 'left',valign: 'top'},//描述
				                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top'}	//上传时间
				                  ]
	            	});
		    	});
		    	
		    	function searchByButton(){
		    		$("#table-javascript").search('search-Class','table-javascript','searchDiv');
		    	}
		    	
		    	function unfinish(){
		    		alert("暂未实现！");
		    	}
		    	
		    	/** 格式化显示简历的意向地区和求职岗位：多个换行显示 */
		    	function fmtPi(a,data){
		    		if(a){
		    			var str = a.replace(/,/g,'<br>');
		    			return "<span title=\""+a+"\">"+str+"<span>";
		    		}
		    		return "-";
		    	}
    	</script>
  </body>
</html>
