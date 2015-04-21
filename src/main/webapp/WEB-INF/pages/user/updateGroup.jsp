<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet">

<style type="text/css">
#roleList li {
	background-color: #eeeeee;
}

</style>
<script type="text/javascript" src="../../../resources/js/user/group.js"></script>
<script type="text/javascript">
var unSelectedRoleList;
var unSelectedRoleList;
	$().ready(function() {
		latte.xk.group.initUpdateGroup();
		unSelectedRoleList = $("#roleList_all option");
		selectedRoleList = $("#roleList_selected option");
	 });
	var cancelSelected = function () {
		latte.xk.group.cancelSelected(unSelectedRoleList, selectedRoleList);
	}
</script>
    <title>${page_title}</title>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/pages/commons/header.jsp" %>
	<div class="mainWrap">
	    <div class="mainIndex">
	    	 <%@include file="/WEB-INF/pages/user/left-menu.jsp" %>
	      	<div class="mainCon mainCon01">
	        	<div class="mainTitle">
	        		 编辑组
	        		 <div class="operBtn" style="padding:5px 0;float:right;margin-right:5px;font-size:12px;">
	               	<span id="btn_return"  class="BtnWhite22" onclick="javascript:window.history.back(-1);">
						<a href="javascript:void(0)" class="btnSave" ><span class="returnIco"></span>返回</a>
					</span>
				</div>
	        	</div>
				<div class=" content_main">
			   		<div class="mainPart">
			          <div class="conBox">
			            <div class="formCon">
			                <div class="formList"> 
			                  <label><em>*</em>组名称：</label>
			                  <div class="role_val">
			                  <input type="text" class="usermanageInput" name="name" id="name" size="35" value="${group.name }" disabled="disabled"/>
								</div>		
			                </div>
			                <div class="formList"> 
			                  <label>组说明：</label>
			                  <div class="role_val">
			                  <input type="text" name="remark" id="remark"
									class="inputText" size="35" value="${group.remark }" maxlength="120"/>
							  <label for="name" id="remark_label" class="error"></label>
								</div>		
			                </div>
			   				<div class="formList"> 
			                  <label>角色列表：</label>
			                  <div class="role_val">
								<select id="roleList_all" size="10" style="width:233px;">
									<c:forEach var="unSelectedRole" items="${unSelectedRoleList }">
										<option value="${unSelectedRole.id }" ondblclick="latte.xk.group.addRoleToGroup('${unSelectedRole.id}', '${unSelectedRole.name}');">${unSelectedRole.name }</option>
									</c:forEach>
								</select>
								</div>		
			                </div>
			                <div class="formList"> 
			                  <label>已选角色列表：</label>
			                  <div class="role_val">
								<select id="roleList_selected" size="10" style="width:233px;">
									<c:forEach var="selectedRole" items="${selectedRoleList }">
										<option value="${selectedRole.id }" ondblclick="latte.xk.group.delRoleFromGroup('${selectedRole.id}', '${selectedRole.name}');">${selectedRole.name }</option>
									</c:forEach>			                  
								</select>
							  </div>		
			                </div>
			              </div>
         		 		</div>
         		 	</div>
         		 	<input type="hidden" name="id" id="id" value="${group.id }" /> 
         		 	<input type="hidden" name="stats" id="stats" value="${group.stats }" /> 
         		 	<input type="hidden" name="type" id="type" value="${group.type }" /> 
         		 	<input type="hidden" name="groupname" id="groupname" value="${group.name }" />
         		 	<input type="hidden" name="domainId" id="domainId" value="${group.domainId }" />
         		 	
         		 	
         		 	
         		 	<input type="hidden" name="qgroupName" id="qgroupName" value="${qgroupName }" />
         		 	<input type="hidden" name="quserType" id="quserType" value="${quserType }" />
         		 	<input type="hidden" name="qpage" id="qpage" value="${qpage }" />
		          <div class="conBtnWrap conBtnWrap01">
		             <div class="BtnOrange30">
		             	<a id="btnSave" class="btnSave" href="javascript:latte.xk.group.groupUpdate()">确认</a>
		             </div> 
		             <div class="BtnGray30">
		             	<a id="btnSave" class="btnSave" href="javascript:cancelSelected('${group.remark }');">重置</a>
		             </div>
		          </div>
		</div>
	</div></div></div>
	<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>