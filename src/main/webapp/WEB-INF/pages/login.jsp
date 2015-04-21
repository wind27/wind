<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
	<title>${page_title}</title>
    <script src="../../resources/js/MD5.js"></script>
    <script src="../../resources/js/messages_zh.js"></script>
  </head>

<body>
	<div class="container login_container">
		<!-- header start -->
		<div class="header">
			<div class="headCon">
				<h1 class="headLogo">
				<a title="" href=""><img src="${static_ctx}/resources/img/adapter/logo_<fmt:bundle basename="system"><fmt:message key="app.name"/></fmt:bundle>/login_logo.png" /> </a>
				</h1>
			</div>
		</div>
		<!-- header end -->
		<!-- main start -->
		<div class="mainWrap">
			<div class="mainIndex">
				<div class="loginBox">
					<div class="loginTitle">
						<p>教研管理系统</p>
					</div>
					<div class="loginCon">
						<form id="login_form" action="" method="post"  class="form-signin">
						<div class="loginList">
							<div class="loginForm">
								<div class="formBox">
									<span class="icoUers"></span>
									<input id="loginUser" class="inputText" type="text" name="loginUser" placeholder="用户名" class="required input-block-level"/>
								</div>
							</div>
						</div>
						<div class="loginList">
							<div class="loginForm">
								<div class="formBox">
									<span class="icoPsw"></span>
									<input id="loginPass" class="inputText" type="password" name="loginPass" placeholder="密码" class="required input-block-level"/>
								</div>
							</div>
						</div>
						 <div class="loginList">
				            <div class="loginForm loginForm01">
				              <div class="formBox">
				                <input id="validateCode" class="inputText" type="text" name="validateCode" placeholder="验证码" />
				              </div>
				            </div>
				            <div class="loginCode">
				              <a href="javascript:void(0);" class="infoCode" title="看不清，换一个"><img id="random_code" src="${static_ctx}/captchahtm"/></a>
				              <a href="javascript:void(0);" class="infoCut">看不清，换一个</a>
				            </div>
				      	</div>
				       	<div class="loginHint"></div>
						<div class="loginBtn">
							<input id="login_submit" type="button" value="登录" name="" />
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- main end -->
		<!-- footer start -->
		<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
		<!-- footer end -->
	</div>	
   	<script type="text/javascript" src="../../resources/js/login.js"></script>\
    <script type="text/javascript">
    $(document).ready(function() {
	 	latte.login.init();
	});
	</script>
  </body>
</html>
