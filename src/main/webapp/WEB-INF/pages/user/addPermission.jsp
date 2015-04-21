<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript" src="../../../resources/js/user/permission.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#auth_add").addClass('current');
	latte.permission.getFunc(0);
});
</script>
<title>${page_title}</title>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/pages/commons/header.jsp" %>
<div class="mainWrap">
    <div class="mainIndex">
	  <%@include file="/WEB-INF/pages/user/auth-left-menu.jsp" %>
      <div class="mainCon">
        <div class="mainTitle">
         	  添加权限
        </div>
   		<div class="mainPart">
          <div class="conBox">
            <div class="formCon">
				<font color="blue">功能类型</font>
				<hr style="margin: 10 0 10 0;">
                <div class="formList"> 
	               	 所属项目：
					<select id="domain" name="domain" style="width:150px;" onchange="latte.permission.getFuncInfo(0);">
						<option value="1" selected="selected">运营平台</option>
					</select>
				</div>
				<font color="blue">功能等级选择</font>
				<hr style="margin: 10 0 10 0;">
                <div class="formList"> 
                  	  一级模块：
					<select id="firstPid" name="firstPid" onchange="latte.permission.getFuncInfo(1);" style="width:150px;">
						<option value="-1" selected="selected">请选择</option>
					</select>
					二级模块：
					<select id="secondPid" name="secondPid" onchange="latte.permission.getFuncInfo(2);" style="width:150px;">
						<option value="-2" selected="selected">请选择</option>
					</select>
					三级模块：
					<select id="thirdPid" name="thirdPid" onchange="latte.permission.getFuncInfo(3);" style="width:150px;">
						<option value="-3" selected="selected">请选择</option>
					</select>
                </div>
				<font color="blue">功能添加</font><br/>
				<hr style="margin: 10 0 10 0;">
				<div class="formList"> 
                  	URI&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
               		<input type="text" name="uri" id="uri" onblur="latte.permission.uriBlurEvent();"/>
               		<span id="uri_label" class="error"></span>
                </div>
				<div class="formList"> 
                  	方法&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
               		<select id="method" name="method" style="width : 135px;">
						<option value="GET" selected="selected">GET</option>
						<option value="POST">POST</option>
						<option value="DELETE">DELETE</option>
						<option value="PUT">PUT</option>
					</select>
                </div>
				<div class="formList"> 
                  	所属模块：
                	<input type="text" name="parentModel" id="parentModel" readonly="readonly">
                </div>
                <div class="formList"> 
                  	属模块Id：
                  	<input type="text" name="funcId" id="funcId" readonly="readonly">
                </div>
              </div>
      		 </div>
      		</div>
	        <div class="conBtnWrap conBtnWrap01">
	        	<div class="BtnOrange30">
	            	<a id="btnSave" class="btnSave" href="javascript:latte.permission.addFuncResour()">确认</a>
	            </div>    
	        </div>
		</div>
		</div>
	</div>
		<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>