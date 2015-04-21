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

<script type="text/javascript" src="../../../resources/js/user/func.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#func_manage").addClass('current');
	latte.func.getFunc(0);
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
         	  编辑功能
         	 <div class="operBtn" style="padding:5px 0;float:right;margin-right:5px;font-size:12px;">
               	<span id="btn_return"  class="BtnWhite22" onclick="javascript:window.history.back(-1);">
					<a href="javascript:void(0)" class="btnSave" ><span class="returnIco"></span>返回</a>
				</span>
			</div>
        </div>
   		<div class="mainPart">
          <div class="conBox">
            <div class="formCon">
            	<font color="blue">功能类型</font>
				<hr style="margin: 10 0 10 0;">
                <div class="formList"> 
	               	 所属项目：
					<select id="domain" name="domain" style="width:150px;" onchange="latte.func.getFuncInfo(0);">
						<option value="1" selected="selected">运营平台</option>
					</select>
				</div>
				<font color="blue">功能等级选择</font>
				<hr style="margin: 10 0 10 0;">
                <div class="formList"> 
                  	  一级模块：
					<select id="firstPid" name="firstPid" onchange="latte.func.getFuncInfo(1);" style="width:233px;">
						<option value="-1" selected="selected">请选择</option>
					</select>
					二级模块：
					<select id="secondPid" name="secondPid" onchange="latte.func.getFuncInfo(2);" style="width:233px;">
						<option value="-2" selected="selected">请选择</option>
					</select>
                </div>
				<font color="blue">功能添加</font><br/>
				<hr style="margin: 10 0 10 0;">
				<div class="formList"> 
                  	ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
               		<input type="text" name="id" id="id" readonly="readonly" value="${func.id }"/>
               		<span id="id_label" class="error"></span>
                </div> 
				
				<div class="formList"> 
                  	名称&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
               		<input type="text" name="name" id="name" onblur="latte.func.nameBlurEvent();" value="${func.name }"/>
               		<span id="name_label" class="error"></span>
                </div>
                <div class="formList"> 
                  	排序&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
               		<input type="text" name="_order" id="_order" onblur="latte.func._orderBlurEvent();" value="${func._order }">
               		<span id="_order_label" class="error"></span>
                </div>
                
                 <div class="formList"> 
                  	所属项目&nbsp;&nbsp;&nbsp;：
					<select id="domain" name="domain" style="width:150px;" onchange="latte.func.getFuncInfo(0);">
						<option value="1" selected="selected">运营平台</option>
					</select>
                </div>
                <div class="formList"> 
                  	备注：
                  	<input type="text" name="remark" id="remark" value="${func.remark }">
                </div>
				<div class="formList"> 
                  	父模块&nbsp;&nbsp;&nbsp;：
                	<input type="text" name="parentModel" id="parentModel" readonly="readonly" value="${parentName }">
                </div>
                <div class="formList"> 
                  	父模块Id：
                  	<input type="text" name="parentCode" id="parentCode" readonly="readonly" value="${func.parentCode }">
                </div>
              </div>
      		 </div>
      		</div>
      		<input type="hidden" id="func_id" value="${func.id }"/>
      		<input type="hidden" id="func_name" value="${func.name }"/>
      		<input type="hidden" id="func_parentModel" value="${parentModel }"/>
      		<input type="hidden" id="func_parentCode" value="${func.parentCode}"/>
      		<input type="hidden" id="func_order" value="${func._order}"/>
	        <div class="conBtnWrap conBtnWrap01">
	        	<div class="BtnOrange30">
	            	<a id="btnSave" class="btnSave" href="javascript:latte.func.doUpdateFunc()">确认</a>
	            </div> 
	             <div class="BtnGray30">
              		<a id="btnSave" class="btnSave" href="javascript:latte.func.reset()">重置</a>
            	</div>   
	        </div>
		</div>
		</div>
	</div>
		<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>