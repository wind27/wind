<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://koo-learn.com" prefix="xdf" %>

<c:set var="static_ctx" value="<%=request.getContextPath() %>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="page_title"><fmt:bundle basename="system"><fmt:message key="app.title"/></fmt:bundle></c:set>

<script type="text/javascript" >
var ctx = '<%=request.getContextPath() %>';
var static_ctx = '<%=request.getContextPath() %>';
var loginUserName = '${loginUserName}';
// var personInfo='${userId}';
</script>