<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
#roleList li {
	background-color: #eeeeee;
}

ul {
list-style:square;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="../../../resources/js/user/group.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		latte.xk.group.initShowGroup();
	});
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
	         	  查看组
	         	<div class="operBtn" style="padding:5px 0;float:right;margin-right:5px;font-size:12px;">
	               	<span id="btn_return"  class="BtnWhite22" onclick="javascript:window.history.back(-1);">
						<a href="javascript:void(0)" class="btnSave" ><span class="returnIco"></span>返回</a>
					</span>
				</div>  
			</div>
		   	<div class="mainPart">
	          <div class="conBox">
	            <div class="formCon">
	                <div class="formList"> 
	                  <label><em>*</em>组名称：</label>
	                  ${group.name }
	                </div>
	                <div class="formList"> 
	                  <label>组说明：</label>
	                  <div class="role_val">
	                  <textarea rows="2" cols="80" disabled="disabled" style="border: 0;background-color: #ffffff;resize:none">${group.remark }</textarea>
						</div>		
                <div class="formList"> 
                  <label>角色列表：</label>
                  <div class="role_val">
					<select id="roleList_selected" size="10" style="width:200px;">
						<c:forEach var="selectedRole" items="${selectedRoleList }">
							<option value="${selectedRole.id }">${selectedRole.name }</option>
						</c:forEach>
					</select>
                  
					</div>		
                </div>
	              </div>
        		 </div>
   		 		</div>
   		 	</div>
   		 </div>
		</div>
		<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
	</div>
</div>
</body>
</html>