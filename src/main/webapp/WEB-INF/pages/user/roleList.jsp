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

<script type="text/javascript" src="../../../resources/js/user/role.js"></script>	
<script type="text/javascript">
		$().ready(function() {
			latte.xk.role.initRoleList();
			var currentpageNo = $("#currentpageNo").val();
			latte.xk.role.getRoleList(currentpageNo);
		});
</script>
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
	        		角色管理
	        	</div>
	       		<div class="mainPart">	
					<div class="conBox">
						<input type="hidden" id="context" value="${static_ctx}"/>
						<input type="hidden" id="currentPage" />
						<input type="hidden" id="totalPage" />
						<input type="hidden" id="totalCount" />
	  					<div class="conFormList">
	  						<div class="conForm">
	               				<label>角色名：</label>
	               				<div class="info">
	                 					<input class="inputText" type="text" id="qroleName" />
	               				</div>
             				</div>
              				<div class="BtnBlue22">
                				<a id="btnSave" class="btnSave" href="javascript:latte.xk.role.getRoleList(1)">查询</a>
             				</div>
						</div>
					</div>
				</div>
  				<div class="conShow">
					<div class="list">
						<table class="listTable">
			                  <thead>
			                    <tr>
									<th>组名</th>
			                      	<th>组说明</th>
			                      	<th align="center" colspan="3">操作</th>
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
<script type="text/template" id="role-list-tpl">
{@each roleVoList as roleVo,index}
	{@if isSuper}
		<tr>
		<td>?{roleVo.role.name}</td>
		<td>?{roleVo.role.remark}</td>
		<td>
		<xdf:security request='<%=request%>' method='GET' funcUri='/user/showRole/'>
			<span style="color:#296FAD;margin-right:5px;">
				<a style="color:#296FAD;" href='javascript:latte.xk.role.showRole(?{roleVo.role.id});' class='usermanage_btn'>查看</a>
			</span>
		</xdf:security>
		{@if roleVo.superRole == false }
			<xdf:security request='<%=request%>' method='GET' funcUri='/user/openUpdateRole/'>
				<span style="color:#296FAD;margin-right:5px;">
					<a style="color:#296FAD;" href='javascript:latte.xk.role.editRole(?{roleVo.role.id});' class='usermanage_btn'>编辑</a>
				</span>
			</xdf:security>
			<xdf:security request='<%=request%>' method='GET' funcUri='/user/hasRoleInGroup/'>
				<span style="color:#296FAD;margin-right:5px;">
					<a style="color:#296FAD;" href='javascript:latte.xk.role.deleteRole(?{roleVo.role.id},"?{roleVo.role.name}");' class='usermanage_btn'>删除</a>
				</span>
			</xdf:security>
		{@/if}
		</td>
		</tr>
	{@else if roleVo.superRole == false }
		<tr>
		<td>?{roleVo.role.name}</td>
		<td>?{roleVo.role.remark}</td>
		<td>

		<xdf:security request='<%=request%>' method='GET' funcUri='/user/showRole/'>
			<span style="color:#296FAD;margin-right:5px;">
				<a style="color:#296FAD;" href='javascript:latte.xk.role.showRole(?{roleVo.role.id});' class='usermanage_btn'>查看</a>
			</span>
		</xdf:security>
		<xdf:security request='<%=request%>' method='GET' funcUri='/user/openUpdateRole/'>
			<span style="color:#296FAD;margin-right:5px;">
				<a style="color:#296FAD;" href='javascript:latte.xk.role.editRole(?{roleVo.role.id});' class='usermanage_btn'>编辑</a>
			</span>
		</xdf:security>
		<xdf:security request='<%=request%>' method='GET' funcUri='/user/hasRoleInGroup/'>
			<span style="color:#296FAD;margin-right:5px;">
				<a style="color:#296FAD;" href='javascript:latte.xk.role.deleteRole(?{roleVo.role.id},"?{roleVo.role.name}");' class='usermanage_btn'>删除</a>
			</span>
		</xdf:security>
		</td>
		</tr>
	{@/if}
{@/each}
</script>			
</body>