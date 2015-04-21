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
                          <!-- 题库管理 -->
                          <xdf:security funcUri="/quiz/index" method="GET" request="<%=request %>">
                              <li id="hd_menu_paper" >
                                  <h3><a href="${ctx}/quiz/index">题库管理</a></h3>
                                  <ul class="navSec">
                                      <xdf:security funcUri="/paper/mylist" method="GET" request="<%=request %>">
                                          
                                      </xdf:security>
                                      <xdf:security funcUri="/quiz/simul/getMineList/" method="GET" request="<%=request %>">
                                      		<li id="hd_menu_simul_list_mine" ><a href="${ctx}/quiz/simul/listMine">我的试卷</a></li>
									  </xdf:security>
                                      <xdf:security funcUri="/quiz/simul/getList/" method="GET" request="<%=request %>">
                                      		 <li id="hd_menu_simul_list" ><a href="${ctx}/quiz/simul/list">同步习题</a></li>
									  </xdf:security>
                                          
                                      <xdf:security funcUri="/paper/upload" method="GET" request="<%=request %>">
                                          
                                      </xdf:security>
                                      <xdf:security funcUri="/paper/batch" method="GET" request="<%=request %>">
                                          <!-- <li id="lmenu_upload">
                                          <a href="${ctx}/paper/batch">批量上传</a>
                                          </li> -->
                                      </xdf:security>
                                       <xdf:security funcUri="/paper/list" method="GET" request="<%=request %>">
                                         <li id="lmenu_paper_list">
                                             <a href="${ctx}/paper/list">综合试卷</a>
                                         </li>
                                       </xdf:security>
                                      <xdf:security funcUri="/paper/noeval" method="GET" request="<%=request %>">
<!--                                           <li id="lmenu_pending"> -->
<%--                                               <a href="${ctx}/paper/noeval">待处理试卷</a> --%>
<!--                                           </li> -->
                                      </xdf:security>
                                      <xdf:security funcUri="/paper/evaled" method="GET" request="<%=request %>">
<!--                                           <li id="lmenu_confirmed"> -->
<%--                                               <a href="${ctx}/paper/evaled">已审核试卷</a> --%>
<!--                                           </li> -->
                                      </xdf:security>
                                      <xdf:security funcUri="/qust/evaled" method="GET" request="<%=request %>">
                                          <li id="lmenu_qconfirmed">
                                              <a href="${ctx}/qust/evaled">试题管理</a>
                                          </li>
                                      </xdf:security>
                                      <xdf:security funcUri="/statistics/" method="GET" request="<%=request %>">
                                          <li id="lmenu_qstatistics">
                                              <h3><a href="javascript:void(0);">数据统计<em class="btnSec"></em></a></h3>
                                              <ul class="navThr">
                                                  <xdf:security funcUri="/statistics/upload_page" method="GET" request="<%=request %>">
                                                      <li id="stat_upload"><a href="${ctx}/statistics/upload_page">上传数量统计</a></li>
                                                  </xdf:security>
                                                  <xdf:security funcUri="/statistics/evaled_page" method="GET" request="<%=request %>">
                                                      <li id="stat_evaled"><a href="${ctx}/statistics/evaled_page">审核数量统计</a></li>
                                                  </xdf:security>
                                                  <xdf:security funcUri="/statistics/error_page" method="GET" request="<%=request %>">
                                                      <li id="stat_error"><a href="${ctx}/statistics/error_page">报错题目数量</a></li>
                                                  </xdf:security>
                                                  <xdf:security funcUri="/statistics/subject_page" method="GET" request="<%=request %>">
                                                      <li id="stat_subject"><a href="${ctx}/statistics/subject_page">学科题目数量</a></li>
                                                  </xdf:security>
                                                  <xdf:security funcUri="/statistics/knowledge_page" method="GET" request="<%=request %>">
                                                      <li id="stat_knowledge"><a href="${ctx}/statistics/knowledge_page">知识点题目数量</a></li>
                                                  </xdf:security>
                                                  <xdf:security funcUri="/statistics/mastery_page" method="GET" request="<%=request %>">
                                                      <li id="stat_mastery"><a href="${ctx}/statistics/mastery_page">题型难度统计</a></li>
                                                  </xdf:security>
                                              </ul>
                                          </li>
                                      </xdf:security>
                                  </ul>
                              </li>
                          </xdf:security>

                          <!-- 资源管理-->
                          <xdf:security request="<%=request %>" method="GET" funcUri="/res/resource/index">
                            <li id="hd_menu_resource" >
                                <h3><a href="${ctx}/res/resource/index">资源管理</a></h3>
                                <ul class="navSec">
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/res/resource/courseware">
                                        <li id="lmenu_add" >
                                            <%-- <a href="${ctx}/res/resource/courseware">添加资源</a> --%>
                                        </li>
                                    </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/res/resource/searchCourseware">
                                        <li id="lmenu_manage" >
                                            <%-- <a href="${ctx}/res/resource/searchCourseware">资源管理</a> --%>
                                        </li>
                                    </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/res/statistics">
                                     <li id="lmenu_statistics">
                                      <h3><a href="javascript:void(0);">资源统计<em class="btnSec"></em></a></h3>
                                      <ul class="navThr">
                                        <xdf:security request="<%=request %>" method="GET" funcUri="/res/statistics/page/amount">
                                            <li><a id="lmenu_amount" href="${ctx}/res/statistics/page/amount">资源总数统计</a></li>
                                        </xdf:security>
                                    <xdf:security request="<%=request %>" method="GET" funcUri="/res/statistics/page/stabysrc">
                                            <li><a id="lmenu_stabysrc" href="${ctx}/res/statistics/page/stabysrc">提交来源统计</a></li>
                                        </xdf:security>
                                        <xdf:security request="<%=request %>" method="GET" funcUri="/res/statistics/page/stabyuser">
                                            <li><a id="lmenu_stabyuser" href="${ctx}/res/statistics/page/stabyuser">提交用户统计</a></li>
                                        </xdf:security>
                                        <xdf:security request="<%=request %>" method="GET" funcUri="/res/statistics/page/check">
                                            <li><a id="lmenu_check" href="${ctx}/res/statistics/page/check">审核状态统计</a></li>
                                        </xdf:security>
                                        <xdf:security request="<%=request %>" method="GET" funcUri="/res/statistics/enable">
                                            <li><a id="lmenu_enable" href="${ctx}/res/statistics/enable">按主题/专题统计</a></li>
                                        </xdf:security>
                                      </ul>
                                    </li>
                                    </xdf:security>
                                    <li id="lmenu_select" >
                                     <xdf:security request="<%=request %>" method="GET" funcUri="/res/pickClip">
                                        <%-- <a href="${ctx}/res/pickClip">挑选素材</a> --%>
                                        </xdf:security>
                                    </li>

                                    <li>
                                        <a id="resrc_courseware" href="${ctx}/res/resource/searchCourseware">课件管理</a>
                                    </li>
                                    <li>
                                        <a id="resrc_resource" href="${ctx}/res/resource/searchClip">资料管理</a>
                                    </li>
                                </ul>
                            </li>
                        </xdf:security>

                          <!-- 媒资库管理 -->
                          <xdf:security funcUri="/media/index/" method="GET" request="<%=request %>">
                              <li id="hd_menu_media" >
                                  <h3><a>媒资库</a></h3>
                                  <ul class="navSec">
                                      <xdf:security funcUri="/media/edu/" method="GET" request="<%=request %>">
                                          <li id="hd_menu_edu" ><a href="${ctx}/media/edu">家庭教育</a></li>
                                      </xdf:security>
                                      <xdf:security funcUri="/media/info/" method="GET" request="<%=request %>">
                                          <li id="hd_menu_info" ><a href="${ctx}/media/info">教育资讯</a></li>
                                      </xdf:security>
                                  </ul>
                              </li>
                          </xdf:security>

                          <!-- 视频管理 -->
                          <xdf:security funcUri="/vcRouter/index/" method="GET" request="<%=request %>">
                              <li id="hd_menu_video" >
                                  <h3><a>视频管理</a></h3>
                                  <ul class="navSec">
                                      <li id="hd_menu_videocourse" ><a href="${ctx}/vcRouter/course">视频课程</a></li>
                                  </ul>
                              </li>
                          </xdf:security>

                          <!-- 公告管理 -->
                          <xdf:security funcUri="/announcementRouter/index/" method="GET" request="<%=request %>">
                              <li id="hd_menu_announcement" >
                                  <h3><a href="javascript:void(0)">公告管理</a></h3>
                                  <ul class="navSec">
                                      <li id="announce_app">
                                          <a href="${static_ctx}/announcementRouter/app">APP公告</a>
                                      </li>
                                      <xdf:security funcUri="/announcementRouter/system/" method="GET" request="<%=request %>">
                                      		<li id="announce_system">
                                              <a href="${static_ctx}/announcementRouter/system">系统通知</a>
                                      		</li>
                                      </xdf:security>
                                  </ul>
                              </li>
                          </xdf:security>

                          <!-- 反馈管理 -->
                          <xdf:security funcUri="/fbRouter/index/" method="GET" request="<%=request %>">
                              <li id="hd_menu_feedback" >
                                  <h3><a href="javascript:void(0)">反馈管理</a></h3>
                                  <ul class="navSec">
                                  	  <xdf:security funcUri="/fbRouter/user/" method="GET" request="<%=request %>">
	                                      <li id="fbRouter/user">
	                                              <a href="${static_ctx}/fbRouter/user">意见反馈</a>
	                                      </li>
                                      </xdf:security>
                                      <xdf:security funcUri="/fbRouter/report/" method="GET" request="<%=request %>">
                                          <li id="fbRouter/report">
                                              <a href="${static_ctx}/fbRouter/report">举报管理</a>
                                          </li>
                                      </xdf:security>
                                      <xdf:security funcUri="/vcRouter/comment/" method="GET" request="<%=request %>">
                                          <li id="vcRouter/comment">
                                              <a href="${static_ctx}/vcRouter/comment">评论管理</a>
                                          </li>
                                      </xdf:security>
                                  </ul>
                              </li>
                          </xdf:security>

                          <!-- 客户管理 -->
                          <xdf:security funcUri="/customer/index" method="GET" request="<%=request %>">
                              <li id="hd_menu_platform">
                                  <h3><a href="javascript:void(0)">客户管理</a></h3>
                                  <ul class="navSec">
                                      <li id="p_menu_add_customer">
                                          <h3><a href="javascript:void(0);">添加公立客户<em class="btnSec"></em></a></h3>
                                          <ul class="navThr">
                                              <xdf:security funcUri="/public/school/add" method="GET" request="<%=request %>">
                                                  <li><a id="p_menu_add_customer_sch" href="${ctx}/public/school/add">添加学校</a></li>
                                              </xdf:security>
                                              <xdf:security funcUri="/public/director/add" method="GET" request="<%=request %>">
                                                  <li><a id="p_menu_add_customer_reg" href="${ctx}/public/director/add">添加主管方</a></li>
                                              </xdf:security>
                                          </ul>
                                      </li>

                                      <xdf:security funcUri="/public/school/list" method="GET" request="<%=request %>">
                                          <li id="p_menu_list_customer_school" style="display: none;"><a href="${ctx}/public/school/list">公立客户列表</a></li>
                                          <input type="hidden" id="public_school_list"/>
                                      </xdf:security>
                                      <xdf:security funcUri="/public/director/list" method="GET" request="<%=request %>">
                                          <li id="p_menu_list_customer_director" style="display: none;"><a href="${ctx}/public/director/list">公立客户列表</a></li>
                                          <input type="hidden" id="public_director_list"/>
                                      </xdf:security>

                                      <li id="p_menu_add_private">
                                          <h3><a href="javascript:void(0);">添加集团客户<em class="btnSec"></em></a></h3>
                                          <ul class="navThr">
                                              <xdf:security funcUri="/private/school/add" method="GET" request="<%=request %>">
                                                  <li><a id="p_menu_add_private_sch" href="${ctx}/private/school/add">添加学校</a></li>
                                              </xdf:security>
                                              <xdf:security funcUri="/private/director/add" method="GET" request="<%=request %>">
                                                  <li><a id="p_menu_add_private_director" href="${ctx}/private/director/add">添加主管方</a></li>
                                              </xdf:security>
                                          </ul>
                                      </li>

                                      <xdf:security funcUri="/private/school/list" method="GET" request="<%=request %>">
                                          <li id="p_menu_list_privatecustomer_school" style="display: none;"><a href="${ctx }/private/school/list">集团客户列表</a></li>
                                          <input type="hidden" id="private_school_list"/>
                                      </xdf:security>
                                      <xdf:security funcUri="/private/director/list" method="GET" request="<%=request %>">
                                          <li id="p_menu_list_privatecustomer_director" style="display: none;"><a href="${ctx }/private/director/list">集团客户列表</a></li>
                                          <input type="hidden" id="private_director_list"/>
                                      </xdf:security>

                                  </ul>
                              </li>
                          </xdf:security>
							
                          <!-- 体系管理 -->
                          <xdf:security funcUri="/tixi/index" method="GET" request="<%=request %>">
                            <li id="hd_menu_tixi" >
                                <h3><a href="javascript:void(0)">体系管理</a></h3>
                                <ul class="navSec">
                                    <xdf:security funcUri="/knowledge" method="GET" request="<%=request %>">
                                        <li id="hd_menu_topic" ><a href="${ctx}/knowledge">知识点管理</a></li>
                                    </xdf:security>
                                    <xdf:security funcUri="/tmaterial" method="GET" request="<%=request %>">
                                        <li id="hd_menu_tmaterial" ><a href="${ctx}/tmaterial">教材体系管理</a></li>
                                    </xdf:security>
                                </ul>
                            </li>
                          </xdf:security>

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

                          <!-- 用户管理 -->
                          <xdf:security funcUri="/usermanage/index/" method="GET" request="<%=request %>">
                            <li id="hd_menu_yonghu" >
                                <h3><a>用户管理</a></h3>
                                <ul class="navSec">
                                <xdf:security funcUri="/usermanage/teacher/list" method="GET" request="<%=request %>">
                                    <li id="hd_menu_teacher" ><a href="${ctx}/usermanage/teacher/list">教职工管理</a></li>
                                </xdf:security>
                                <xdf:security funcUri="/usermanage/student/list" method="GET" request="<%=request %>">
                                    <li id="hd_menu_student" ><a href="${ctx}/usermanage/student/list">学生管理</a></li>
                                </xdf:security>
                                <xdf:security funcUri="/usermanage/parent/list" method="GET" request="<%=request %>">
                                    <li id="hd_menu_parent" ><a href="${ctx}/usermanage/parent/list">家长管理</a></li>
                                </xdf:security>
                                </ul>
                            </li>
                        </xdf:security>

                          <!-- 消息管理 -->
                          <%--
                        <li id="hd_menu_message" >
                            <h3><a href="javascript:void(0)">消息管理</a></h3>
                            <ul class="navSec">
                                <li id="announce_app">
                                    <a href="${static_ctx}/announcementRouter/app">APP公告</a>
                                </li>
                                <li id="announce_system">
                                    <a href="${static_ctx}/announcementRouter/system">系统公告</a>
                                </li>

                                <li id="fbRouter/user">
                                    <a href="${static_ctx}/fbRouter/user">用户反馈</a>
                                </li>
                                <li id="fbRouter/report">
                                    <a href="${static_ctx}/fbRouter/report">举报管理</a>
                                </li>
                                <li id="vcRouter/comment">
                                    <a href="${static_ctx}/vcRouter/comment">评论管理</a>
                                </li>

                                <xdf:security request="<%=request %>" method="GET" funcUri="/announcementRouter/system/">

                                </xdf:security>
                                <xdf:security request="<%=request %>" method="GET" funcUri="/announcementRouter/app/">

                                </xdf:security>
                                <xdf:security request="<%=request %>" method="GET" funcUri="/message/toNoticeList/">
                                <li id="message_notice">
                                    <a href="${static_ctx}/message/toNoticeList">公告管理(old)</a>
                                </li>
                                </xdf:security>
                                <xdf:security request="<%=request %>" method="GET" funcUri="/message/toFeedbackList/">
                                <li id="message_feedback">
                                    <a href="${static_ctx}/message/toFeedbackList">反馈消息(old)</a>
                                </li>
                                </xdf:security>
                            </ul>
                        </li>
                         --%>
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
