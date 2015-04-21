<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://koo-learn.com" prefix="xdf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${page_title}</title>
<script type="text/javascript">
		$(document).ready(function() {
			$('#hd_menu_auth').addClass('current');
		});
</script>
</head>

<body>
<!-- cantainer start-->
<div class="container">
  <%@include file="/WEB-INF/pages/commons/header.jsp" %>
  <div class="mainWrap">
    <div class="mainIndex">
    	<%@include file="/WEB-INF/pages/user/auth-left-menu.jsp" %>
      	<div class="mainCon">
		     <img src="../../../resources/img/logo/welcome-jiaoyan.jpg"/>
  		</div>
    </div>
  </div>
  <%@include file="/WEB-INF/pages/commons/footer.jsp" %>
 </div>
</body>
</html>
