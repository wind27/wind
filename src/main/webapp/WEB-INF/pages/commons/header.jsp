<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
    body {background-color: #f5f5f5; -webkit-user-select: none;}
    .header-left, .header-right {width:30px;height:20px;line-height:20px;cursor: pointer;text-align: center;margin-top: 15px;}
    .header-left:after {content: url(/static/img/ico/ico_arrow_lf.png)}
    .header-right:before {content: url(/static/img/ico/ico_arrow_rt.png)}
    .headerBar {
        width: 640px;
        overflow: hidden;
        height: 500px;
    }
    .inline {display: inline-block!important; float: none!important;}
    .container .header .headOper .nav>li {margin: 0;}
</style>
<div id="header" class="header">
 	<div class="headIco">
      	<div class="headCon" style="position: relative">
		    <h1 class="headLogo inline" style="vertical-align: top;">
		      	<a href="${ctx}"><img src="${static_ctx}/static/img/adapter/logo_<fmt:bundle basename="system"><fmt:message key="app.name"/></fmt:bundle>/jiaoyan.png" /> </a>
		    </h1>
		    <!-- nav start -->
		    <div class="headOper inline">
                <div style="display: inline-flex;">
                    <div class="header-dir header-left"></div>
                    <div class="headerBar">
                      <ul class="nav nav-ul" style="width: auto;display: -webkit-box;position: relative;left:0;white-space: nowrap;">
                          <!-- 人员管理 -->
                          <xdf:security funcUri="/user/index/" method="GET" request="<%=request %>">
                            <li id="hd_menu_user" >
                                <h3><a href="javascript:void(0)">人员管理</a></h3>
                                <ul class="navSec">
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/user/toPersonList/">
                                        <li id="usermanage_personManage">
                                            <a href="${static_ctx}/user/toPersonList">人员管理</a>
                                        </li>
                                    </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/user/openAddPerson/">
                                        <li id="usermanage_personAdd">
                                            <a href="${static_ctx}/user/openAddPerson">添加人员</a>
                                        </li>
                                    </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/user/toRoleList/">
                                        <li id="usermanage_roleManage">
                                            <a href="${static_ctx}/user/toRoleList">角色管理</a>
                                        </li>
                                    </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/user/openAddRole/">
                                        <li id="usermanage_roleAdd">
                                            <a href="${static_ctx}/user/openAddRole">添加角色</a>
                                        </li>
                                    </xdf:security>

                                    <xdf:security request="<%=request %>" method="GET" funcUri="/user/toGroupList/">
                                        <li id="usermanage_groupManage">
                                            <a href="${static_ctx}/user/toGroupList">组管理</a>
                                        </li>
                                    </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/user/openAddGroup/">
                                        <li id="usermanage_groupAdd">
                                            <a href="${static_ctx}/user/openAddGroup">添加组</a>
                                        </li>
                                    </xdf:security>
                                </ul>
                            </li>
                        </xdf:security>


                      </ul>
                    </div>
                    <div class="header-dir header-right"></div>
                </div>
		    </div>
            <div class="user" style="position:absolute;top:8px;right:0;">
                <a id="headerUsername" class="userIco ellipsis" style="width: 98px;"></a>
                <ul class="userList">
                    <li><a id="hd_menu_setting" class="set" href="javascript:void(0);">设置</a></li>
                    <li><a id="hd_menu_logout" class="exit"  href="${ctx}/logout">退出</a></li>
                </ul>
                <input type="hidden" name="personid" id="personid" value="" />
            </div>
	    </div>
    </div>
	<!-- 系统通知 滑动框开始-->
<div class="popup_notify">
	<input id="islogin" type="hidden" value="${islogin }">
	<div class="notify" style="word-break:break-all;">
	</div>
	<div class="BtnBlue30" style=" float:right;">
		<a id="btnSave" class="btnSave" href="javascript:void(0)">系统通知 <em class="ico_btn unfold shrink"></em></a>
	</div>	  
</div>
</div>

<script>
    var headerMouseDown = false;
    $("#header").on("mousedown", ".header-dir", function(e) {
        headerMouseDown = true;
        var $this = $(this);
        $this.trigger("myevent");
    }).on("mouseup", ".header-dir", function(e) {
        headerMouseDown = false;
    }).on("myevent", ".header-dir", function(e) {
        var that = this;
        var handle = function () {
            if (headerMouseDown) {
                var n = 90;
                var $this = $(that);
                var isLeft = $this.hasClass("header-left");
                var step = isLeft ? n : -n;  // 每次移动的距离
                var ul = $(".nav-ul");
                var left = parseInt(ul.css("left"), 10);

                if (isLeft && left != 0) {
                    ul.css("left", (left + step) + "px");
                }

                var d = ul.width() - 630 > -left;
                if (!isLeft && d) {
                    ul.css("left", (left + step) + "px");
                }
                setTimeout(handle, 500);
            }
        };
        handle();
    });

</script>
