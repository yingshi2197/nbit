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
	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
    <title>岗位申请</title>
    <spring:message code="jsp.include.basecss" />
    <link rel="stylesheet" type="text/css" href="http/business/wx/css/layout.css">
    <link rel="stylesheet" type="text/css" href="http/business/wx/css/from_table.css">
    
    <style type="text/css">
    	.redStar{display: none;}
    	.label{display: none;}
    </style>
</head>
<body>
<!--个人信息-->
<div class="main-body">
    <form id="myform" class="form-information" action="wx/resume/add.do">
        <input type="hidden" name="id"
			value="<c:out value="${resume.id}" />" /> 
		<input type="hidden" name="positionId"
			value="<c:out value="${resume.positionId}" />" /> 
        <table class="form-table">
            <colgroup>
                <col width="25%" />
                <col width="75%" />
            </colgroup>
            <tbody>
                <tr>
                    <td><label for="name">姓名：<i class="font-red">*</i></label></td>
                    <td><label class="label">姓名</label><input type="text" id="name" name="name" class="from-input required" maxlength="200"/></td>
                </tr>
                <tr>
                    <td><label for="name">性别：<i class="font-red">*</i></label></td>
                    <td><label class="label">性别</label>
                    	<c:forEach items="${sex }" var="temp">
                    		<input type="radio" id="sex_<c:out value="${temp.id}"/>" name="sex" value="<c:out value="${temp.id}"/>"  class="required"  />
                        	<label for="sex_<c:out value="${temp.id}"/>"><c:out value="${temp.name}"/></label>
                    	</c:forEach>
                    </td>
                </tr>
                <tr>
                    <td><label for="birthday">出生日期：<i class="font-red">*</i></label></td>
                    <td><label class="label">出生日期</label><input type="date" id="birthday" name="birthday" class="from-input required"  format="yyyy-MM-dd"  /></td>
                </tr>
                 <tr>
                    <td><label for="mobile">联系电话：<i class="font-red">*</i></label></td>
                    <td><label class="label">联系电话</label><input type="text"  id="mobile" name="mobile" class="from-input required"  maxlength="20" /></td>
                </tr>
                <tr>
                    <td><label for="school">毕业院校：<i class="font-red">*</i></label></td>
                    <td><label class="label">毕业院校</label><input type="text" id="school" name="school" class="from-input required" maxlength="200"/></td>
                </tr>
                <tr>
                    <td><label for="major">专业：<i class="font-red">*</i></label></td>
                    <td><label class="label">专业</label><input type="text" id="major" name="major" class="from-input required"  maxlength="100" />
                      </td>
                </tr>
                <tr>
                    <td><label for="education">学历：<i class="font-red">*</i></label></td>
                    <td><label class="label">学历</label>
                    	<select id="education" class="from-input required" name="education" >
                            <option value="">请选择</option>
		                	<c:forEach items="${education }" var="temp">
		                		<option value="<c:out  value="${temp.id }"/>"  ><c:out  value="${temp.name }"/></option>
		                	</c:forEach>
                        </select>
                      </td>
                </tr>
                <tr>
                    <td><label for="workLife">工作年限：<i class="font-red">*</i></label></td>
                    <td><label class="label">工作年限</label>
                        <select id="workLife" class="from-input required" name="workLife">
                            <option value="">请选择</option>
		                	<c:forEach items="${workLife }" var="temp">
		                		<option value="<c:out  value="${temp.id }"/>"  ><c:out  value="${temp.name }"/></option>
		                	</c:forEach>
                        </select>
					</td>
                </tr>
                
                <tr>
                    <td><label for="email">电子邮箱：<i class="font-red">*</i></label></td>
                    <td><label class="label">电子邮箱</label><input type="text" id="email" name="email" class="from-input email required" maxlength="100"/></td>
                </tr>
                
                <tr>
                    <td><label for="address">工作地点：<i class="font-red">*</i></label></td>
                    <td><label class="label">工作地点</label>
                    	<select id="address" class="from-input required" name="address">
                            <option value="">请选择</option>
		                	<c:forEach items="${address }" var="temp">
		                		<option value="<c:out  value="${temp.id }"/>"  ><c:out  value="${temp.name }"/></option>
		                	</c:forEach>
                        </select>
					</td>
                </tr>
                
                <tr>
                    <td><label for="description">自我介绍：</label></td>
                </tr>
                <tr>
                    <td colspan="2"><label class="label">自我介绍</label><textarea id="description" name="description" rows="8" class="myself-area" maxlength="500"></textarea></td>
                </tr>
                <tr>
                    <td colspan="2" class="font-center">
                    <button type="submit" class="form-btn">保存并应聘</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>
<spring:message code="jsp.include.basejs" />
<spring:message code="jsp.include.formjsforback" />
</body>
<script type="text/javascript">

	$(document)	.ready(
		function() {
			$("#myform").initForm({wx:"1",close:function(){
				window.history.back();
			}});
			/* 
			$("#addressName").on("input",function(){
				setValue("addressName");
			})
			
			$("#educationName").on("input",function(){
				setValue("educationName");
			})
			
			$("#workLifeName").on("input",function(){
				setValue("workLifeName");
			}) */
		});
						
	function setValue(e) {
		var value=$("#"+e).val();
		var id=$("#"+e+"1 option[value='"+value+"']").attr("id");
		$("#"+e.replace("Name","")).val(id);
	}
</script>

</html>