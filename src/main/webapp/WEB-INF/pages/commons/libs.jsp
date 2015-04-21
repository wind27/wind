<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://koo-learn.com" prefix="xdf" %>
<%@page import="com.noriental.global.dict.QiniuConstant"%>

<c:set var="static_ctx" value="<%=request.getContextPath() %>"/>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="qiniu_host" value="<%=QiniuConstant.URL_PREFIX.HEAD_IMAGE %>"/>
<c:set var="page_title"><fmt:bundle basename="system"><fmt:message key="app.title"/></fmt:bundle></c:set>

<script type="text/javascript" >
var ctx = '<%=request.getContextPath() %>';
var static_ctx = '<%=request.getContextPath() %>';
var nfs_url = '<fmt:bundle basename="server"><fmt:message key="server.nfs.url"/></fmt:bundle>';
var nfs_url_doc = '<fmt:bundle basename="server"><fmt:message key="server.nfs.url.doc"/></fmt:bundle>';
var nfs_url_audio = '<fmt:bundle basename="server"><fmt:message key="server.nfs.url.audio"/></fmt:bundle>';
var nfs_url_video = '<fmt:bundle basename="server"><fmt:message key="server.nfs.url.video"/></fmt:bundle>';
var qiniu_host = '<%=QiniuConstant.URL_PREFIX.HEAD_IMAGE %>';
var loginUserName = '${loginUserName}';
// var personInfo='${userId}';
</script>