<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://koo-learn.com" prefix="xdf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../../resources/css/pagebar.css" rel="stylesheet" type="text/css" media="all"/>
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="../../../resources/js/user/user.js"></script>
<script type="text/javascript">
	$().ready(function() {
		latte.xk.user.initPersonList();
		var currentpageNo = $("#currentpageNo").val();
		latte.xk.user.getPersonList(currentpageNo);
	});
</script>
    <title>${page_title}</title>
</head>
<body>
<div class="container" >
<input type="hidden" id="currentpageNo" value="${pageNo }"/>
<%@include file="/WEB-INF/pages/commons/header.jsp" %>
	<div class="mainWrap">
	    <div class="mainIndex">
	    	 <%@include file="/WEB-INF/pages/user/left-menu.jsp" %>
	      	<div class="mainCon mainCon01">
	        	<div class="mainTitle">
	        		人员管理
	        	</div>
	       		<div class="mainPart">	
					<div class="conBox">
						<input type="hidden" id="context" value="${static_ctx}"/>
						<input type="hidden" id="currentPage" />
						<input type="hidden" id="totalPage" />
						<input type="hidden" id="totalCount" />
	  					<div class="conFormList">
	  						<label>所属组：</label>
	  						<select name="groupId" id="groupId" class="usermanageSelect" style="width: 190px;">
	  						</select>
	  						<label>状态：</label>
	  						<select name="stats" id="stats" class="usermanageSelect">
	  							<option value="-1" selected="selected">全部</option>
 								<option value="0">启用</option>
 								<option value="1">停用</option>
	  						</select>
	  						<div style="float: right; margin: 2px 110px 0 20px;">
		  						<div class="conForm">
		               				<label>姓名或用户名：</label>
		               				<div class="info">
		                 					<input class="inputText" type="text" id="name" />
		               				</div>
	             				</div>
		  						<div class="BtnBlue22">
	                				<a id="btnSave" class="btnSave" href="javascript:latte.xk.user.getPersonList(1)">查询</a>
	             				</div>
	  						</div>
						</div>
					</div>
				</div>
				<div class="conShow">
					<div class="list">
						<table class="listTable">
			                  <thead>
			                    <tr>
									<th>用户名</th>
			                      	<th>姓名</th>
			                      	<th>所属组</th>
			                      	<th>状态</th>
			                      	<th align="center">操作</th>
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
	
 <script type="text/template" id="person-list-tpl">

{@each adminVoList as adminVo,index}	
	<tr>
	<td>?{adminVo.admin.loginUser}</td>
	<td>?{adminVo.admin.name}</td>
	<td>?{adminVo.group.name}</td>
	{@if adminVo.admin.stats==0 }
		<td>启用</td>
	{@else}
		<td>停用</td>
	{@/if}
	<td>
	
	{@if isSuper }
		<xdf:security request='<%=request%>' method='GET' funcUri='/user/showPerson/'>
			<span style="color:#296FAD;margin-right:5px;">
				<a style="color:#296FAD;" href='javascript:latte.xk.user.showPerson(?{adminVo.admin.id},?{adminVo.group.id});' class='usermanage_btn'>查看</a>
			</span>
		</xdf:security>
		{@if adminVo.admin.id !=1 }
			<xdf:security request='<%=request%>' method='GET' funcUri='/user/openUpdatePerson/'>
				<span style="color:#296FAD;margin-right:5px;">
					<a style="color:#296FAD;" href='javascript:latte.xk.user.updatePerson(?{adminVo.admin.id},?{adminVo.group.id});' class='usermanage_btn'>编辑</a>
				</span>
			</xdf:security>
			{@if adminVo.admin.stats==0 }
				<xdf:security request='<%=request%>' method='GET' funcUri='/user/doDisablePerson/'>
					<span style="color:#296FAD;margin-right:5px;">
						<a style="color:#296FAD;" href='javascript:latte.xk.user.disablePerson(?{adminVo.admin.id},"?{adminVo.admin.name}",?{adminVo.group.id});' class='usermanage_btn'>停用</a>
					</span>
				</xdf:security>
			{@else}
				<xdf:security request='<%=request%>' method='GET' funcUri='/user/doEnablePerson/'>
					<span style="color:#296FAD;margin-right:5px;">
						<a style="color:#296FAD;" href='javascript:latte.xk.user.enablePerson(?{adminVo.admin.id},"?{adminVo.admin.name}",?{adminVo.group.id});' class='usermanage_btn'>启用</a>
					</span>
				</xdf:security>
			{@/if}
			<xdf:security request='<%=request%>' method='POST' funcUri='/user/updatepwd/'>
				<span style="color:#296FAD;margin-right:5px;">
					<a style="color:#296FAD;" href='javascript:latte.xk.user.updatepwd("?{adminVo.admin.loginUser}","?{adminVo.admin.name}",?{adminVo.group.id});' class='usermanage_btn'>重置密码</a>
				</span>
			</xdf:security>
		{@/if}
	{@else if adminVo.super == false }
		<xdf:security request='<%=request%>' method='GET' funcUri='/user/showPerson/'>
			<span style="color:#296FAD;margin-right:5px;">
				<a style="color:#296FAD;" href='javascript:latte.xk.user.showPerson(?{adminVo.admin.id},?{adminVo.group.id});' class='usermanage_btn'>查看</a>
			</span>
		</xdf:security>
		{@if adminVo.admin.id !=1 }
			<xdf:security request='<%=request%>' method='GET' funcUri='/user/openUpdatePerson/'>
				<span style="color:#296FAD;margin-right:5px;">
					<a style="color:#296FAD;" href='javascript:latte.xk.user.updatePerson(?{adminVo.admin.id},?{adminVo.group.id});' class='usermanage_btn'>编辑</a>
				</span>
			</xdf:security>
			{@if adminVo.admin.stats==0 }
				<xdf:security request='<%=request%>' method='GET' funcUri='/user/doDisablePerson/'>
					<span style="color:#296FAD;margin-right:5px;">
						<a style="color:#296FAD;" href='javascript:latte.xk.user.disablePerson(?{adminVo.admin.id},"?{adminVo.admin.name}", ?{adminVo.group.id });' class='usermanage_btn'>停用</a>
					</span>
				</xdf:security>
			{@else}
				<xdf:security request='<%=request%>' method='GET' funcUri='/user/doEnablePerson/'>
					<span style="color:#296FAD;margin-right:5px;">
						<a style="color:#296FAD;" href='javascript:latte.xk.user.enablePerson(?{adminVo.admin.id},"?{adminVo.admin.name}",?{adminVo.group.id });' class='usermanage_btn'>启用</a>
					</span>
				</xdf:security>
			{@/if}
			<xdf:security request='<%=request%>' method='POST' funcUri='/user/updatepwd/'>
				<span style="color:#296FAD;margin-right:5px;">
					<a style="color:#296FAD;" href='javascript:latte.xk.user.updatepwd("?{adminVo.admin.loginUser}","?{adminVo.admin.name}",?{adminVo.group.id });' class='usermanage_btn'>重置密码</a>
				</span>
			</xdf:security>
		{@/if}
	{@/if}
	</td>
	</tr>
	{@/each} 
</script>
</body>
</html>