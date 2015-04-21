<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<link href="../../../resources/css/event/event.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="../../../resources/css/event/event-list.css"
	rel="stylesheet" type="text/css" media="all" />
	<link href="../../../resources/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="../../../resources/css/datepicker.css" rel="stylesheet"
	type="text/css" />
    <title>${page_title}</title>
<script type="text/javascript"
	src="../../../resources/js/usermngr/usermngr.js"></script>
	<script src="../../../resources/js/bootstrap-datepicker.js"></script>
<script src="../../../resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function() {
	$("#createDate").datepicker({format:"yyyy/mm/dd"});
	$("#endDate").datepicker({format:"yyyy/mm/dd"});
});</script>
</head>
<body>
	<!-- 头部 -->
	<%@include file="/WEB-INF/pages/event/header.jsp"%>
	<div class="content">
		<div class="main-content">
			<div class="content-detail" style="font-size:9px;">
				<div class="edit-panel">
					<dl>
						<dt>用户名：</dt>
						<dd>
							<input type="text" id="username"  />
						</dd>
					</dl>
					<dl>
						<dt>邮箱：</dt>
						<dd>
							<input type="text" id="email" />

						</dd>
					</dl>
					<dl>
						<dt>注册日期：</dt>
						<dd>
							<input type="text" id="createDate"  />

						</dd>
						<dd>
							<input type="text" id="endDate"  />

						</dd>
					</dl>
					<input type="button" onclick="search()" value="搜索"/>
				</div>
				<table class="event-list">
					<tr class="list-head">
						<th class="w100">用户名</th>
						<th class="w200">邮箱</th>
						<th class="w100">手机号</th>
						<th class="w100" style="width:119px;">注册日期</th>
						<th class="w200">最后登录时间</th>
<!-- 						<th class="w100">积分</th> -->
					</tr>
					<c:forEach items="${users}" var="user" varStatus="status">
						<tr>
							<td>${user.name}</td>
							<td>${user.email}</td>
							<td>${user.phone}</td>
							<td>${user.createDate}</td>
							<td>${user.accessDate}</td>
<!-- 							<td></td> -->
						</tr>
					</c:forEach>
				</table>
				.
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<!-- 底部 -->
	<%@include file="/WEB-INF/pages/commons/footer.jsp"%>


	<script type="text/template" id="q_single_type1">
	</script>

	<script type="text/javascript"
		src="../../../resources/js/event/event-list.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			latte.event.list.init();
		});
	</script>
</body>
</html>

