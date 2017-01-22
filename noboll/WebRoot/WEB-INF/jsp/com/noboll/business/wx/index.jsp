<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <title>职位发布列表</title>
    <link rel="stylesheet" type="text/css" href="http/business/wx/css/layout.css">
    <link rel="stylesheet" type="text/css" href="http/business/wx/css/recruit.css">
    <link rel="stylesheet" type="text/css" href="http/common/css/eject.css">
</head>
<body>
<!-- 头部浮动层-->
<header id="header" class="main-header">
    <div class="search">
        <input type="text" class="search-input" id="searchName" placeholder="输入职位名字进行查找" />
        <input type="text" class="search-input" style="display: none;" id="searchNameOld" />
        <input type="hidden" id="limit" value="5"/>
        <input type="hidden" id="offset" value="0"/>
        <button type="button" class="search-button" id="searchButton">搜索</button>
    </div>
    <div class="choice">
        <form class="form-inline">
            <div class="form-group">
                <label for="post">职位</label>
                <select id="post" class="form-control">
                	<option value="">请选择</option>
                	<c:forEach items="${type }" var="temp">
                		<option value="<c:out  value="${temp.id }"/>"  ><c:out  value="${temp.name }"/></option>
                	</c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="add">地点</label>
                <select id="add" class="form-control">
                    <option value="">请选择</option>
                	<c:forEach items="${address }" var="temp">
                		<option value="<c:out  value="${temp.id }"/>"  ><c:out  value="${temp.name }"/></option>
                	</c:forEach>
                </select>
            </div>
        </form>
    </div>
    <div class="position-info">
        职位信息
    </div>
</header>
<!--职位信息-->
<div class="main-body" style="margin-bottom: 30px;">
    	<!--职位信息详情-->
	    <div class="details" >
	        <div id="details">
	        
	        </div>
	    
	    <!--点击加载-->
		    <div class="load">
		        <button type="button" id="load_more">点击加载更多职位</button>
		    </div>
	    </div>
    </div>

<!-- 活动按钮-->
<div class="footer-but ">
    <button type="button" class="but1" id="fenxiang">分享</button>
    <button type="button" id="apply">我要应聘</button>
</div>
<spring:message code="jsp.include.basejs" />
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</body>
<script type="text/javascript">
	function loadData(condition) {
		var limit=$("#limit").val();
		var offset=$("#offset").val();
		var data={limit:limit,offset:offset,condition:tools.objToJson(condition||{})};
		tools.ajax({
			url:"wx/position/list.do",
			async: false,
			data:data,
			success:function(data) {
				if(data.code=="1") {
					var rows=data.obj;
					if(rows&&rows.length>0) {
						var temp=[];
						for(var i=0;i<rows.length;i++) {
							temp.push(createRow(rows[i]));
						}
						$("#details").append(temp.join(""));
						var offset=parseInt($("#offset").val());
						$("#offset").val(offset+rows.length);
					}else {
						tools.showDialog("无更多职位！");
					}
				}
			}
		})
	}
	
	function createRow(row) {
		var str='<div class="panel panel-default">'+
            '<div class="panel-header">'+row.name+
                '<div class="select-box"><input name="pname" type="checkbox" txt="'+row.name+'" value="'+row.id+'"></div>'+
            '</div>'+
            '<div class="panel-body">'+
                '<table class="table">'+
                    '<colgroup>'+
                        '<col width="50%">'+
                        '<col width="50%">'+
                    '</colgroup>'+
                    '<tbody>'+
                        '<tr>'+
                            '<td>工作地点：'+row.addressName+'</td>'+
                            '<td>职位类型：'+row.typeName+'</td>'+
                        '</tr>'+
                        '<tr>'+
                            '<td>工作年限：'+row.workLifeName+'</td>'+
                            '<td>招聘人数：'+row.num+'人</td>'+
                        '</tr>'+
                        '<tr class="p_more">'+
                            '<td colspan="2">'+
                                '岗位职责：'+row.duty+
                            '</td>'+
                        '</tr>'+
                        '<tr class="p_more">'+
                            '<td colspan="2">'+
                                '岗位要求：'+row.requirement+
                            '</td>'+
                        '</tr>'+
                        '<tr>'+
                            '<td colspan="2" class="p_down">&nbsp;</td>'+
                        '</tr>'+
                    '</tbody>'+
                '</table>'+
            '</div>'+
        '</div>';
        return str;
	}
	
	function search() {
			var id1=$("#post").val();
			//var id1=$("#post-list option[value='"+value1+"']").attr("id");
			var id2=$("#add").val();
			//var id2=$("#add-list option[value='"+value2+"']").attr("id");
			var data={};
			var name=$("#searchNameOld").val();
			if(id1) {
				data["typeName"]=id1;
			}
			if(id2) {
				data["addressName"]=id2;
			}
			if(name) {
				data["name"]=name;
			}
			//alert(tools.objToJson(data));
			loadData(data);
	}
	
	function init() {
		$("#offset").val(0);
		$("#limit").val(5);
		$("#details").html("");
		search();
	}
	
	function getCheckBoxValue() {
		var chk_value =[];
		var chk_txt=[];
		$('input[name="pname"]:checked').each(function(){
			chk_value.push($(this).val());
			chk_txt.push($(this).attr("txt"));
		}); 
		//alert(chk_value);
		//alert(chk_txt);
		if(chk_value.length>5) {
			tools.showDialog("最多同时申请5个职位！");
			return;
		}
		if(chk_value.length==0) {
			tools.showDialog("请至少选择一个职位！");
			return;
		}
		return {ids:chk_value,names:chk_txt};
	}
	
	$(document).ready(function(){
		init();
		$("#details").on("click",".p_down",function(){
			var obj=$(this).parents("table:eq(0)");
			$(obj).find(".p_more").show();
			$(this).removeClass("p_down").addClass("p_up");
		})
		
		$("#details").on("click",".p_up",function(){
			var obj=$(this).parents("table:eq(0)");
			$(obj).find(".p_more").hide();
			$(this).removeClass("p_up").addClass("p_down");
		})
	
		$("#load_more").on("click",function() {
			search();
		})
		
		$("#searchButton").on("click",function() {
			$("#searchNameOld").val($("#searchName").val());
			init();
		})
		
		$("#post").on("input",function(){
			init();
		})
		
		$("#add").on("input",function(){
		    init();
		})
		
		
		$("#apply").on("click",function() {
			var select=getCheckBoxValue();
			if(select) {
				var msg="";
				tools.ajax({
						url:"wx/position/checkPosition.do",
						async: false,
						data:{ids:select.ids.join(",")},
						success:function(data) {
							if(data) {
								if(data.msg) {
									msg=data.msg;
								}
							}
						}
					})
				if(msg) {
					for(var i=0;i<select.names.length;i++) {
						msg=msg.replace("{"+i+"}", "【"+select.names[i]+"】");
					}
					tools.showDialog(msg);
					init();
					return;
				}
				var ids=select.ids;
				if(ids) {
					window.open("wx/resume/toAdd.do?id="+ids);
				}
			}
		})
		
		$("#fenxiang").on("click",function(){
			tools.ajax({
				url:"wx/getToken.do",
				async: false,
				success:function(data) {
					wx.config({
					    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
					    appId: data.appId, // 必填，公众号的唯一标识
					    timestamp:data.timestamp , // 必填，生成签名的时间戳
					    nonceStr: data.rand, // 必填，生成签名的随机串
					    signature: data.signature,// 必填，签名，见附录1
					    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});
					var shareObj={
						title: '诺宝招聘', // 分享标题
					    link: data.url, // 分享链接
					    imgUrl: '', // 分享图标
					    success: function () { 
					        // 用户确认分享后执行的回调函数
					    },
					    cancel: function () { 
					        // 用户取消分享后执行的回调函数
					    }
					};
					wx.ready(function(){
						wx.onMenuShareTimeline(shareObj);
						wx.onMenuShareAppMessage(shareObj);
						wx.onMenuShareQQ(shareObj);
						wx.onMenuShareWeibo(shareObj);
						wx.onMenuShareQZone(shareObj); 
					});
				}
			})
		})
	});
	
</script>

</html>