<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- 
 -->
<link href="../../../resources/css/user/common.css" type="text/css" rel="stylesheet">
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="../../../resources/js/user/user.js"></script>
<script type="text/javascript">
$().ready(function() {
	latte.xk.user.initAddPerson();
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
         	  添加人员
        </div>
        <div class="mainPart">
          <div class="conBox">
            <div class="formCon">
              <div class="formList">
                <label><em>*</em>姓名：</label>
                <div class="info info01">
                <input name="name" class="inputText" id="name" size="35"  maxlength="35"/>
                <label id="name_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>所属组：</label>
                <div class="info info01">
                	<select name="groupId" id="groupId"  style="width:225px;" class="info add_inp usermanageSelect">
                		<option value="-1">请选择</option>
						<c:forEach var="group" items="${groupList }">
							<option value="${group.id }">${group.name }</option>
						</c:forEach>
					  </select>
					  <label id="groupId_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>用户名：</label>
                <div class="info info01">
                  <input name="loginUser" class="inputText" size="35"
								id="loginUser"  maxlength="35"/>
				  <label id="loginUser_label" class="error"></label>			
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>密码：</label>
                <div class="info info01">
                  <input name="loginPass" class="inputText" size="35" maxlength="35"
								id="loginPass" type="password"/>
				  <label id="loginPass_label" class="error"></label>				
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>确认密码：</label>
                <div class="info info01">
                  <input name="confirmpwd" class="inputText" size="35"  maxlength="35"
								id="confirmpwd" type="password"/><input type="hidden" name="type"
								value="1" /> <input type="hidden" name="flag" value="1" />
				 <label id="confirmpwd_label" class="error"></label>			
                </div>
              </div>
              <div class="formList">
                <label>电话：</label>
                <div class="info info01">
                  <input name="phone" class="inputText" size="35" id ="phone" maxlength="35"/>
                  <label id="phone_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label>Email：</label>
                <div class="info info01">
                  <input name="email" class="inputText" size="35" id = "email" maxlength="60"/>
                  <label id="email_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label>QQ：</label>
                <div class="info info01">
                  <input name="qq" class="inputText" size="35" id="qq" maxlength="35"/>
                  <label id="qq_label" class="error"></label>
                </div>
              </div>
              <div class="formList">
                <label><em>*</em>状态：</label>
                <div class="info">
                  <div class="infoBox">
                    <input type="radio" id="user_stats_start" checked="checked" name="stats" id="stats" class="inputRadio" value="0">
                    <p class="infoLabel">启用</p>
                  </div>
                  <div class="infoBox">
                    <input type="radio" id="user_stats_stop" name="stats" id="stats" class="inputRadio" value="1">
                    <p class="infoLabel">停用</p>
                  </div>
                </div>
              </div>
              <div class="formList">
                <label>权限数据：</label>
                <div class="info">
                  <div class="list list01">
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
                            	<input class="inputCheckbox" type="checkbox" name="primarySubs" id="primarySubs">
                              	<p class="infoLabel">小学</p>
                            </div>
                          </td> 
                          <td class="lineLast">
                          <c:forEach var="primarySubject" items="${primarySchool }">
                          	<div class="infoBox">
							 	<input class="inputCheckbox" type="checkbox" name="primarySubject" id="primarySubject" value="${primarySubject.id }">
							 	<p class="infoLabel">${primarySubject.name }</p>
                            </div>
						  </c:forEach>
                          </td>
                        </tr>
                        </c:if>
                        <c:if test="${juniorSchool !=null && juniorSchool.size() > 0  }">
                        <tr>
                          <td>
                            <div class="infoBox">
                            	<input class="inputCheckbox" type="checkbox" name="juniorSubs" id="juniorSubs">
                              	<p class="infoLabel">初中</p>
                            </div>
                          </td> 
                          <td class="lineLast">
                          <c:forEach var="juniorSubject" items="${juniorSchool }">
                          	<div class="infoBox">
							 	<input class="inputCheckbox" type="checkbox" name="juniorSubject" id="juniorSubject" value="${juniorSubject.id }">
							 	<p class="infoLabel">${juniorSubject.name }</p>
                            </div>
						  </c:forEach>
                          </td>
                        </tr>
                        </c:if>
                        <c:if test="${seniorSchool !=null && seniorSchool.size() > 0  }">
                        <tr>
                          <td>
                            <div class="infoBox">
                              	<input class="inputCheckbox" type="checkbox" name="seniorSubs" id="seniorSubs">
                              	<p class="infoLabel">高中</p>
                            </div>
                          </td> 
                          <td class="lineLast">
                          <c:forEach var="seniorSubject" items="${seniorSchool }">
                            <div class="infoBox">
								<input class="inputCheckbox" type="checkbox" name="seniorSubject" id="seniorSubject" value="${seniorSubject.id }">
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
            <div class="conBtnWrap conBtnWrap01">
             <div class="BtnOrange30">
               <a id="btnSave" class="btnSave" href="javascript:latte.xk.user.personAdd()">确认</a>
             </div>    
          </div>
          </div>
        </div></div></div></div>
   <%@include file="/WEB-INF/pages/commons/footer.jsp" %>     
</div>
</body>
</html>