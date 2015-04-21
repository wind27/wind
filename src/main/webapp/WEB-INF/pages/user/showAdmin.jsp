<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
	latte.xk.user.initUserSubject(selectedPrimarySubject, 
			selectedJuniorSubject, selectedSeniorSubject);
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
	        		人员信息
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
		                <span class="showUserLabel">${admin.name }</span>
		              </div>
		              <div class="formList">
		              
		              
		                <label><em>*</em>所属组：</label>
		                <span class="showUserLabel">${group.name }</span>
		              </div>
		              <div class="formList">
		                <label><em>*</em>用户名：</label>
		                <span class="showUserLabel">${admin.loginUser }</span>
		              </div>
		              <div class="formList">
		                <label>电话：</label>
		                <span class="showUserLabel">${admin.phone }</span>
		              </div>
		              <div class="formList">
		                <label>Email：</label>
		                <span class="showUserLabel">${admin.email }</span>
		              </div>
		              <div class="formList">
		                <label>QQ：</label>
		                <span class="showUserLabel">${admin.qq }</span>
		              </div>
		              <div class="formList">
		                <label><em>*</em>状态：</label>
		                <span class="showUserLabel">
		                  	<c:choose>
		                  		<c:when test="${admin.stats == 0 }">启用</c:when>
		                  		<c:otherwise>停用</c:otherwise>
		                  	</c:choose>
		                </span>
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
		                      <c:if test="${primarySchool !=null && primarySchool.size() > 0}">
		                      <tr>
		                          <td>
		                            <div class="infoBox">
		                              <p class="infoLabel"><input class="inputCheckbox" type="checkbox" name="primarySubs" id="primarySubs" disabled="disabled">小学</p>
		                            </div>
		                          </td> 
		                          <td class="lineLast">
		                          <c:forEach var="primarySubject" items="${primarySchool }">
		                          	<div class="infoBox">
										<input class="inputCheckbox" type="checkbox" name="primarySubject" id="primarySubject_${primarySubject.id }" value="${primarySubject.id }" disabled="disabled">
									 	<p class="infoLabel">${primarySubject.name }</p>
		                            </div>
								  </c:forEach>
		                          </td>
		                        </tr>
		                        </c:if>
                       			 <c:if test="${juniorSchool !=null && juniorSchool.size() > 0 }">
		                        <tr>
		                          <td>
		                            <div class="infoBox">
		                              <p class="infoLabel"><input class="inputCheckbox" type="checkbox" name="juniorSubs" id="juniorSubs" disabled="disabled">初中</p>
		                            </div>
		                          </td> 
		                          <td class="lineLast">
		                          <c:forEach var="juniorSubject" items="${juniorSchool }">
		                          	<div class="infoBox">
										<input class="inputCheckbox" type="checkbox" name="juniorSubject" id="juniorSubject_${juniorSubject.id }" value="${juniorSubject.id }" disabled="disabled">
									 	<p class="infoLabel">${juniorSubject.name }</p>
		                            </div>
								  </c:forEach>
		                          </td>
		                        </tr>
		                        </c:if>
                        		<c:if test="${seniorSchool !=null && seniorSchool.size() > 0 }">
		                        <tr>
		                          <td>
		                            <div class="infoBox">
		                              	<p class="infoLabel"><input class="inputCheckbox" type="checkbox" name="seniorSubs" id="seniorSubs" disabled="disabled">高中</p>
		                            </div>
		                          </td> 
		                          <td class="lineLast">
		                          <c:forEach var="seniorSubject" items="${seniorSchool }">
		                            <div class="infoBox">
										<input class="inputCheckbox" type="checkbox" name="seniorSubject" id="seniorSubject_${seniorSubject.id }" value="${seniorSubject.id }" disabled="disabled">
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
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>