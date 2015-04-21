<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${page_title}</title>
<link href="../../../resources/css/user/common.css" type="text/css" rel="stylesheet">
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="../../../resources/js/user/user.js"></script>

<script type="text/javascript">
$().ready(function() {
	var primarySubs = ${primarySubs};
	var juniorSubs = ${juniorSubs};
	var seniorSubs = ${seniorSubs};
	
	var selectedJuniorSubject = ${selectedJuniorSubject};
	var selectedSeniorSubject = ${selectedSeniorSubject};
	var selectedPrimarySubject = ${selectedPrimarySubject};
	
	latte.xk.user.initUserStage(primarySubs, juniorSubs, seniorSubs);
	
	latte.xk.user.initUpdateUser(selectedPrimarySubject, 
			selectedJuniorSubject, selectedSeniorSubject);
 });
</script>
</head>
<body>
<input type="hidden" name="id" value="${admin.id }" />
<div class="container" >
<%@include file="/WEB-INF/pages/commons/header.jsp" %>
<div class="mainWrap">
    <div class="mainIndex">
	  <%@include file="/WEB-INF/pages/user/left-menu.jsp" %>
      <div class="mainCon mainCon01">
        <div class="mainTitle">
         	 编辑人员
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
                <label><em>*</em>姓名：</label>
                <div class="info info01">
                <input name="name" class="inputText" id="name" size="35" maxlength="35" value="${admin.name }"/>
                <label for="name" id="name_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>所属组：</label>
                <div class="info info01">
                	<select name="groupId" id="groupId" class="info add_inp usermanageSelect" style="width:225px;">
						<c:if test="${!empty grouplist }">
							<c:forEach items="${grouplist }" var="group1">
								<c:choose>
									<c:when test="${group.id==group1.id }">
										<option value="${group1.id }" selected>${group1.name }</option>
									</c:when>
									<c:otherwise>
										<option value="${group1.id }">${group1.name }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</select>
					<label id="groupId_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>用户名：</label>
                <div class="info info01">
                  <input name="loginUser" class="inputText" size="35" readonly="readonly"
								id="loginUser" value="${admin.loginUser }" maxlength="20"/>
					<label for="name" id="loginUser_label" class="error"></label>			
                </div>
              </div>
              <div class="formList">
                <label>电话：</label>
                <div class="info info01">
                  <input name="phone" class="inputText" size="35" maxlength="35" id ="phone" value="${admin.phone }"/>
                  <label for="name" id="phone_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label>Email：</label>
                <div class="info info01">
                  <input name="email" class="inputText" size="35" maxlength="60" id = "email" value="${admin.email }"/>
                  <label for="name" id="email_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label>QQ：</label>
                <div class="info info01">
                  <input name="qq" class="inputText" size="35" maxlength="35" id="qq" value="${admin.qq }"/>
                  <label for="name" id="qq_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>状态：</label>
                <div class="info">
                  	<c:choose>
                  		<c:when test="${admin.stats == 0 }"><p class="infoLabel">启用</p></c:when>
                  		<c:otherwise><p class="infoLabel">停用</p></c:otherwise>
                  	</c:choose>
                </div>
              </div>
              <div class="formList">
                <label>权限数据：</label>
                <div class="info">
                  <div class="list">
                    <table class="listTable">
                      <thead>
                        <tr>
                          <th width="60">学段</th>
                          <th class="lineLast">学科</th>
                        </tr>
                      </thead>
                      <tbody>
                      <c:if test="${primarySchool !=null && primarySchool.size() > 0  }">
                      <tr>
                          <td>
                            <div class="infoBox">
                           		<p class="infoLabel"><input class="inputCheckbox" type="checkbox" name="primarySubs" id="primarySubs">小学</p>
                            </div>
                          </td> 
                          <td class="lineLast">
                          <c:forEach var="primarySubject" items="${primarySchool }">
                          	<div class="infoBox">
								<input class="inputCheckbox" type="checkbox" name="primarySubject" id="primarySubject_${primarySubject.id }" value="${primarySubject.id }">
							 	<p class="infoLabel">${primarySubject.name }</p>
                            </div>
						  </c:forEach>
                          </td>
                        </tr>
                        </c:if>
                        <c:if test="${juniorSchool !=null && juniorSchool.size() > 0   }">
                        <tr>
                          <td>
                            <div class="infoBox">
							  <p class="infoLabel"><input class="inputCheckbox" type="checkbox" name="juniorSubs" id="juniorSubs">初中</p>           	
                            </div>
                          </td> 
                          <td class="lineLast">
                          <c:forEach var="juniorSubject" items="${juniorSchool }">
                          	<div class="infoBox">
								<input class="inputCheckbox" type="checkbox" name="juniorSubject" id="juniorSubject_${juniorSubject.id }" value="${juniorSubject.id }">
							 	<p class="infoLabel">${juniorSubject.name }</p>
                            </div>
						  </c:forEach>
                          </td>
                        </tr>
                        </c:if>
                        <c:if test="${seniorSchool !=null && seniorSchool.size() > 0   }">
                        <tr>
                          <td>
                            <div class="infoBox">
                          		<p class="infoLabel"><input class="inputCheckbox" type="checkbox" name="seniorSubs" id="seniorSubs">高中</p>
                            </div>
                          </td> 
                          <td class="lineLast">
                          <c:forEach var="seniorSubject" items="${seniorSchool }">
                            <div class="infoBox">
								<input class="inputCheckbox" type="checkbox" name="seniorSubject" id="seniorSubject_${seniorSubject.id }" value="${seniorSubject.id }">
								<p class="infoLabel">${seniorSubject.name }</p>
                            </div>
						  </c:forEach>
                          </td>
                        </tr>
                        </c:if>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
		</div>
		<div class="conBtnWrap conBtnWrap01">
            <div class="BtnOrange30">
              <a id="btnSave" class="btnSave" href="javascript:latte.xk.user.userUpdate();">确认</a>
            </div>    
            <div class="BtnGray30">
              <a id="btnSave" class="btnSave" href="javascript:latte.xk.user.resetUser('${admin.id }', '${group.id }');">重置</a>
            </div>  
        </div>
		</div></div></div></div>
   	<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>