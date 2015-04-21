<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>	
<!doctype html>
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<title>${page_title}</title>
</head>

<body>
<div class="container">
  
  <%@include file="/WEB-INF/pages/commons/header.jsp" %>
  <div class="mainWrap">
  	<div class="errorBox">
      <div class="errorLf"><img src="../../../resources/img/500.jpg" /></div>
      <div class="errorRt">
        <h3>很抱歉<br>服务器出错了，已经有人在维修了。</h3>
      </div>
    </div>
  </div>
  <%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>
