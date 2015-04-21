<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${page_title}</title>

</head>

<body>
<div class="container">
  
  <%@include file="/WEB-INF/pages/commons/header.jsp" %>
  <div class="mainWrap">
    <div class="errorBox">
      <div class="errorLf"><img src="../../../resources/img/403.jpg" /></div>
      <div class="errorRt">  
        <p class="toLogIn">对不起，您没有操作权限 ! <a href="javascript:window.history.back(-1);">返回</a> </p>
      </div>
    </div>
  </div>
  <%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>
