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
	  		<div class="col-xs-9">
				<div class="row"  style="padding-top:20px;">
  					<form id="myform">
						<select class="search-Class select searchingSelect" placeholder="地区" dictCode="address" name="intentionName" id="intentionName" style="display:inline;"></select>
<!-- 						<select class="search-Class select searchingSelect" placeholder="工作年限" dictCode="work_life" name="yearsName" style="display:inline;"></select> -->
						<input placeholder="输入简历姓名或者求职岗位搜索" name="pyName" id="pyName" class="search-Class searchingTxt" type="text" style="width:680px;"/>
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
						<button class="searchingBtn" type="button" id="btnSearch" onclick="searchByButton();">搜
								索</button>
					</form> 
				</div>
				<div class="row">
					<div class="tab-content" id="tab-content">
						<div role="tabpanel" class="tab-pane active" id="home">
							<div class="longTop">
								<span style="display:none" id="remindDiv">
									<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									Hi，您目前在<span id="span_address" class=" cc20000">所有地区</span><label id="keywordLabel">通过关键字 “<b class=" cc20000" id="keyword"></b> ”</label>搜索简历，共找到 <span id="total"></span> 个内容。
								</span>	
							</div>
							<div class="table-responsive">
								<table id="table-javascript" ></table>
								<div style="height:20px"></div>
					    	</div>
						</div>
					</div>
				</div>
			</div>
				<!-- 右侧：列表 end -->
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
	    			url:tools.joinUrl('business/resume/searchList.do'),
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
			                	{field: 'label',title:'label',visible:false}, 
			                	{field: 'name',title: '姓名',align: 'center',valign: 'middle',visible:false}, //名称
			                    {field: 'sexName',index:"sex",title: '性别',align: 'center',valign: 'middle',visible:false}, //性别
			                    {field: 'yearsName',title: '工作年限',align: 'center',valign: 'middle',visible:false}, //工作年限
			                    {field: 'intentionNames',title: '意向地区',formatter:fmtPi,align: 'center',valign: 'middle',visible:false}, //意向地区
			                    {field: 'positionNames',title: '求职岗位',formatter:fmtPi,align: 'center',valign: 'middle',visible:false}, //求职岗位
			                    {field: 'address',index:'address',title: '住址',align: 'left',valign: 'top',visible:false},	//住址
			                    {field: 'degreeName',title: '学历',align: 'left',valign: 'top',visible:false},//描述
			                    {field: 'createTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '创建时间',align: 'left',valign: 'top',visible:false},	//上传时间
			                    {field: 'content',formatter:formatContent,title:'id',visible:true}
			               ]
	      			}); 
	    	    });
    	    
    	    	function formatContent(a,data){
	    	    	var arr=[];
	    	    	arr.push("<div class=\"search_con\">");
	    			arr.push("<div class=\"col-md-9\" style=\"width=70%\">");
	    			arr.push("<div class=\"search_list_01\">"+data.name);
	    			arr.push("</div>");
	    			arr.push("<div class=\"search_list_02\">"); 
	    			arr.push("<span class=\"search_span\">"+data.sexName+"</span>");
	    			arr.push("<span class=\"search_span\">|</span>"); 
	    			arr.push("<span class=\"search_span\">"+data.degreeName+"</span>");
	    			arr.push("<span class=\"search_span\">|</span>");
	    			arr.push("<span class=\"search_span\">"+data.yearsName+"</span>");
	    			arr.push("<span class=\"search_span\">|</span>");
	    			arr.push("<span class=\"search_span\">求职岗位："+data.positionNames+"</span>");
	    			arr.push("</div>"); 
	    			arr.push("<div class=\"search_list_03\">");
	    			// 简历标签处理
	    			var tagObj = $("<span class=\"search_span\" id=\"tags_\""+(new Date().getTime()+"_tag_rn")+">ssssssss</span>");
	    			var label = data.label;
	    			var labelData = [];
	    			if(label)
	    				labelData = tools.jsonToObj(label);
	    			tagObj.initTags({
	    				selects:labelData,
	    				readonly:true//是否只读，不显示待选区域
	    			});
	    			arr.push(tagObj.html());
	    			arr.push("</div>");
	    			arr.push("<div class=\"search_list_03\">");
	    			arr.push("<span class=\"search_span\"> 地址："+data.address+"</span>");
	    			arr.push("<span class=\"search_span\"> | </span>"); 
	    			arr.push("<span class=\"search_span\"> 意向地区："+data.intentionNames+"</span>");
	    			arr.push("<span class=\"search_span\"> | </span>"); 
	    			arr.push("<span class=\"search_span\">更新时间："+tools.parseDate(data.lastModifyTime,"yyyy-MM-dd")+"</span>");
	    			arr.push("</div>");
	    			arr.push("</div>");
	    			
    				arr.push("<div class=\"col-md-3\"  style=\" line-height:80px; vertical-align:middle;\">");
	    			if(tools.checkUrlPermission("business/resume/toView.do"))
	    				arr.push("<a class=\"search_a\" title=\"查看详情\" href=\"javascript:void(0)\" onclick=\"viewResume('"+data.id+"')\"> 立即查看</a>");
	    			arr.push("</div>"); 
	    			arr.push("</div>");
					
	    			return arr.join("");
    	    	}
    	    
	    	    function viewResume(id){
	    			tools.dialog({
	    				name:"简历详情",
	    				url:"business/resume/toView.do?id="+id,
	    				width:"1100px",
	    				height:"500px",
	    				close:function(){
	    					$("#table-javascript").refresh();//刷新页面
	    				}
	    			});
	    		}
		    	
		    	function searchByButton(){
		    		$("#remindDiv").show();
		    		var keyword = $("#pyName").val();
		    		if(keyword){
		    			$("#keywordLabel").show();
		    			$("#keyword").html(keyword);
		    		}else{
		    			$("#keywordLabel").hide();
			    		$("#keyword").html("");
		    		}
		    		var intentionId = $("#intentionName").val();
		    		var intentionName = $("#intentionName").find("option:selected").text();
		    		if(intentionId){
		    			$("#span_address").html(intentionName);
		    		}else{
			    		$("#span_address").html("全部地区");
		    		}
		    		$("#table-javascript").search('search-Class','table-javascript','searchDiv');
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
