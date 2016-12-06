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
    
    <title>岗位</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<spring:message code="jsp.include.basecss"/>
	<spring:message code="jsp.include.listcssforback"/>
	
  </head>
  
  <body>
  	<div class="container">
	  		 <div class="table-responsive">
				<div class="panel-heading-choose">职位列表</div>
			  	<div id="searchDiv"></div>
				<table id="table-javascript" ></table>
	    	</div>
    	</div>
    	<spring:message code="jsp.include.basejs"/>
    	<spring:message code="jsp.include.listjsforback"/>
    	
    	<script type="text/javascript">
    		
    		$(document).ready(function () {
		       	$("#table-javascript").initBootTable({
		       		method: 'post',
		       		searchDiv:"searchDiv",
	                url: 'business/position/list.do',
	                addUrl:"",
	                striped: true,
	                permissionOperate:function(data){
	                	var op={};
	                	if(data.status=="1") {
	                		op["remove"]="false";
	                	}
	                	if(data.status=="0") {
	                		op["apply"]="false";
	                		op["finish"]="false";
	                	}
	                	if(data.status=="2") {
	                		op["apply"]="false";
	                		op["edit"]="false";
	                		op["remove"]="false";
	                		op["finish"]="false";
	                	}
	                	return op;
	                }, 
	                pagination: true,
	                sidePagination:"server",
	                pageSize: 10,
	                minimumCountColumns: 2,
	                rowButtons:[ // select 表示选择规则，1表示多选，0表示单选，空表示不选
	                	{name:'新增',position:'top',select:"",css:"add",dialog:{url:"business/position/toAdd.do",width:"850px",height:"500px"}},//新增
	                	{name:'修改',position:'row',css:"edit",a:'',dialog:{url:"business/position/toEdit.do",width:"850px",height:"500px"}},//修改	
	                	{name:'发布',position:'row',css:"publish",type:"ajax",url:"business/position/publish.do"},//发布 
	                	{name:'结束',position:'row',css:"finish",type:"ajax",url:"business/position/finish.do"},//结束 
	                	//{name:'申请',position:'row',css:"apply",a:'',dialog:{url:"business/resume/toAdd.do",width:"850px",height:"500px"}},//修改	       	
	                	{name:'删除',position:'row',css:"remove",type:"ajax",url:"business/position/remove.do"}//删除
	                ],
	                clickToSelect: true,
	                columns: [
				                	//{field: 'statu_msb',checkbox: true},   //复选框
				                	{field: 'id',title:'id',visible:false}, 
				                    {field: 'name',title: '名称',align: 'center',valign: 'middle',sortable: true,searchable:true}, //名称
				                    {field: 'typeName',index:"type",title: '类型',align: 'center',valign: 'middle',searchable:true,type:'select:position_type',selectCode:"id"}, //类型
				                    {field: 'addressName',index:'address',title: '地址',align: 'left',valign: 'top',searchable:true,type:'select:address',selectCode:"id"},	//描述
				                    {field: 'workLifeName',title: '年限',align: 'left',valign: 'top'},//描述
				                    {field: 'educationName',title: '学历',align: 'left',valign: 'top'},//描述
				                    {field: 'salaryLow',title: '薪资下限',align: 'left',valign: 'top'},//描述
				                    {field: 'salaryHigh',title: '薪资上限',align: 'left',valign: 'top'},//描述
				                    {field: 'status',formatter:formateStatus,title: '发布状态',align: 'left',valign: 'top'},//描述
				                    {field: 'publishTime',formatter:"date:yyyy-MM-dd hh:mm:ss",title: '发布时间',align: 'left',valign: 'top'}	//上传时间
				                   		                    
				                  ]
	            	});
		    	});
		    	
		    	function formateStatus(value) {
		    		if("1"==value) {
		    			return "发布";
		    		}else if("2"==value) {
		    			return "结束";
		    		}else {
		    			return "未发布";
		    		}
		    	}
		    	
    	</script>
  </body>
</html>
