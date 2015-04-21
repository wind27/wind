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
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript" src="../../../resources/js/user/group.js"></script>
<script type="text/javascript">
	function addRoleToGroup(id, name) {
		var obj = {
			'id' : id,
			'name' : name
		};
		add($("#roleList_selected"), obj, 0);
		del("roleList_all", obj, 0);
	}
	function delRoleFromGroup(id, name) {
		var obj = {
			'id' : id,
			'name' : name
		};
		add($("#roleList_all"), obj, 1);
		del("roleList_selected", obj);
	}
	
	function add(roleList, obj, type) {
		var params = "'" + obj.id +"', '" + obj.name + "'";
		if(type==0) {
			roleList.append("<option ondblclick=\"delRoleFromGroup("+params+");\" value="+obj.id+" >"+obj.name+ "</option>");
		} else {
			roleList.append("<option ondblclick=\"addRoleToGroup("+params+");\" value="+obj.id+" >"+obj.name+ "</option>");
		}
	}
	
	function del(roleListId, obj) {
		$("#"+roleListId+" option").each(function() {
			if($(this).val() == obj.id) {
				$(this).remove();
			}
		});
	}
	
	
	$().ready(function() {
		latte.xk.group.initAddGroup();
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
         	  添加组
        </div>
   		<div class="mainPart">
          <div class="conBox">
            <div class="formCon">
                <div class="formList"> 
                  <label><em>*</em>组名称：</label>
                  <div class="role_val">
                  <input type="text" name="name" id="name"
						class="inputText" 
						 maxlength="35" size="35" />
				  <label for="name" id="name_label" class="error"></label>
					</div>		
                </div>
                <div class="formList"> 
                  <label>组说明：</label>
                  <div class="role_val">
                  <input type="text" name="remark" id="remark"
						class="inputText" size="35" maxlength="120"/>
				  <label for="name" id="remark_label" class="error"></label>		
					</div>		
                </div>
                <div class="formList"> 
                  <label>角色列表：</label>
                  <div class="role_val">
                  
                  	<select id="roleList_all" size="10" style="width:233px;">
                  		<c:forEach var="role" items="${roleList }">
                  			<option value="${role.id }" ondblclick="addRoleToGroup('${role.id}', '${role.name}');">${role.name }</option>
                  		</c:forEach>
                  	</select>
                  
					</div>		
                </div>
                <div class="formList"> 
                  <label>已选角色列表：</label>
                  <div class="role_val">
					<select id="roleList_selected" size="10" style="width:233px;"></select>
                  
					</div>		
                </div>
              </div>
      		 		</div>
      		 	</div>
      		 	
      		 	<input id="groupname" name="groupname" type="hidden">
         <div class="conBtnWrap conBtnWrap01">
            <div class="BtnOrange30">
            	<a id="btnSave" class="btnSave" href="javascript:latte.xk.group.groupAdd()">确认</a>
            </div>    
         </div>
		</div>
		</div>
	</div>
		<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>