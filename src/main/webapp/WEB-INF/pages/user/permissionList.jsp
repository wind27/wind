<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://koo-learn.com" prefix="xdf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${page_title}</title>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../../resources/css/pagebar.css" rel="stylesheet" type="text/css" media="all"/>
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="../../../resources/js/user/permission.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#auth_manage").addClass('current');
	latte.permission.getFunc(0);
	latte.permission.getFuncResour(1,1);
});
</script>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/pages/commons/header.jsp" %>
	<div class="mainWrap">
	    <div class="mainIndex">
	    	 <%@include file="/WEB-INF/pages/user/auth-left-menu.jsp" %>
	      	<div class="mainCon">
	        	<div class="mainTitle">
	        		权限管理
	        	</div>
	       		<div class="mainPart">	
					<div class="conBox">
					    <input type="hidden" id="context" value="${static_ctx}"/>
						<input type="hidden" id="currentPage" />
						<input type="hidden" id="totalPage" />
						<input type="hidden" id="totalCount" />
	  					<div class="conFormList">
	  						<font color="blue">功能类型</font>
							<hr style="margin: 10 0 10 0;" align="center" width="90%">
			                <div class="formList"> 
				               	 所属项目：
								<select id="domain" name="domain" style="width:150px;" onchange="latte.permission.getFuncInfo(0);">
									<option value="1" selected="selected">运营平台</option>
								</select>
								状态：	
								<select id="status" name="status" style="width:150px;" onchange="latte.permission.getFuncInfo(0);">
									<option value="0" selected="selected">启用</option>
									<option value="1">停用</option>
								</select>
							</div>
							<font color="blue">功能等级选择</font>
							<hr style="margin: 10 0 10 0;" align="center" width="90%">
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
<!-- 
			                <hr style="margin: 10 0 10 0;" align="center" width="90%">
              				<div class="BtnBlue22" style="float: right;margin: 0 50 0 0;">
                				<a id="btnSave" class="btnSave" href="javascript:latte.group.getGroupInfoForPage(1)">查询</a>
             				</div>
             				<hr style="margin: 0 0 10 20;" align="center" width="90%">
 -->			                
						</div>
					</div>
				</div>
				<div class="conShow">
					<div class="list">
						<table class="listTable">
			                  <thead>
			                    <tr>
									<th>ID</th>
									<th>URI</th>
									<th>功能模块ID</th>
									<th>状态</th>
									<th>方法</th>
									<th>操作</th>
			                    </tr>
			                  </thead>
			                  <tbody id="exportData"></tbody>
						</table>
						<div id="pagebar"></div>
  					</div>
  				</div>
  			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
	<script src="../../../resources/js/lib/jquery-ui.min.js"></script>
	<script src="../../../resources/js/lib/datetimepicker/jquery-ui-timepicker-addon.min.js"></script>
	<script src="../../../resources/js/lib/datetimepicker/jquery-ui-timepicker-zh-CN.js"></script>
	<script src="../../../resources/js/lib/jquery.pagebar.js" type="text/javascript"></script>
</body>