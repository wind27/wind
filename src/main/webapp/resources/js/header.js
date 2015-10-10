latte.header = {
		//验证密码是否正确
	oldPasswdIsCorrect : function () {
		var oldLoginPass = $("#login_dlg_old_password").val();
		
		if(oldLoginPass == '') {
			$("#old_passwd_msg").html("请输入当前密码");
			return ;
		}
		
//		oldLoginPass = hex_md5(oldLoginPass);
		oldLoginPass = hex_md5(oldLoginPass+"#DsjW2014@xDf.AdMin.cn#");
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/user/checkPasswd",
			dataType : "text",
			data : {
				'loginPass' : oldLoginPass
			},
			success : function(result) {
				if (result == 0) {
					$("#old_passwd_msg").html("");
				} else {
					$("#old_passwd_msg").html("原密码输入错误，请重新输入！");
				}
			}
		});
	},
		//判断新密码是否合法
	checkNewLoginPassIsCorrect : function () {
		var newLoginPass= $("#login_dlg_new_password").val();
		var reg = /^[A-Za-z0-9!@#\\\$%\^&\*\(\)_\-\+]{6,20}$/;
		
		if(newLoginPass == '') {
			$("#new_passwd_msg").html("请输入新密码");
			return ;
		}
		if(reg.test(newLoginPass)==false) {
			$("#new_passwd_msg").html("密码必须为6~20位字符");
			return ;
		}
		
		$("#new_passwd_msg").html("");
	},
	//判断确认密码是否正确
	confirmNewPasswdIsCorrect : function () {
		var newLoginPass= $("#login_dlg_new_password").val();
		var confirmLoginPass = $("#login_dlg_confirm_password").val();
		var flag = true;
		
		if(!confirmLoginPass) {
			$("#confirm_passwd_msg").html("请输入确认密码");
			flag = false;
		} else if(newLoginPass != confirmLoginPass) {
			$("#confirm_passwd_msg").html("两次输入密码不同，请重新输入！");
			flag = false;
		} else {
			$("#confirm_passwd_msg").html("");
		}
		if(flag == false) return ;
	},
		//修改密码
	updatePersonPwd : function (){	
		var reg = /^[A-Za-z0-9!@#\\\$%\^&\*\(\)_\-\+]{6,20}$/;
		
		var oldLoginPass = $("#login_dlg_old_password").val();
		var newLoginPass = $("#login_dlg_new_password").val();
		var confirmLoginPass = $("#login_dlg_confirm_password").val();
		
		
		var flag = true;
		
		if(!oldLoginPass) {
			$("#old_passwd_msg").html("请输入当前密码");
			flag = false;
		}
		
		if(!newLoginPass) {
			$("#new_passwd_msg").html("请输入新密码");
			flag = false;
		} else if(reg.test(newLoginPass)==false) {
			$("#new_password_msg").html("密码必须为6~20位字符");
			flag = false;
		} else {
			$("#new_password_msg").html("");
		}
		
		
		if(!confirmLoginPass) {
			$("#confirm_passwd_msg").html("请输入确认密码");
			flag = false;
		} else if(newLoginPass != confirmLoginPass) {
			$("#confirm_passwd_msg").html("两次输入密码不同，请重新输入！");
			flag = false;
		} else {
			$("#confirm_passwd_msg").html("");
		}
		
		if(flag == false) {
			return;
		}
		oldLoginPass = hex_md5(oldLoginPass+"#DsjW2014@xDf.AdMin.cn#");
		newLoginPass = hex_md5(newLoginPass+"#DsjW2014@xDf.AdMin.cn#");
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/user/checkPasswd",
			dataType : "text",
			data : {
				'loginPass' : oldLoginPass
			},
			success : function(result) {
				if (result == 0) {
					$("#msg").html("");
					$.ajax({
						contentType:"application/x-www-form-urlencoded; charset=UTF-8",
						url : ctx + "/user/doUpdatePwd",
						type:"post",
						dataType : "text",
						data : {
							'loginPass' : newLoginPass
						},
						success : function(code) {
							if(code == 1) {
				        		msg = "操作成功";
				        	} else {
				        		msg = "操作失败";
				        	}
				        	util.dialog.timerDialog(0.5,msg,function(){
				        		art.dialog.get('sys_setting').close();
				        	});
						},
						statusCode : {
							200 : function(data) {
								if (data != 1) {
									$("#login_dlg_confirm_password").html("密码更新失败");
								}
							},
							'default' : function() {
								$("#login_dlg_confirm_password").html("密码更新失败");
							}
						}
				  });
				} else {
					$("#old_passwd_msg").html("原密码输入错误，请重新输入！");
				}
			}
		});
	},
	getNotice : function() {
		var limit = 20;
		var pageNo = parseInt(($(".notify_list").length)/limit)+1;
		$.ajax({
			type : "get",
			url :  ctx + "/announcement/notice/system",
			data : {
				pageNo : pageNo,
				limit : limit
			},
			dataType : "text",
			success : function(result) {
				if (result.announcementList.length > 0) {
					var noticeMsg = '';
					for(var i=0; i<result.announcementList.length; i++) {
						var msg = result.announcementList[i];
						noticeMsg += '<div class="notify_list">';
						noticeMsg += '<div class="notify_title"><span>'+msg.title+'</span>  发送于：<span>'+new Date(msg.submitTime).toLocaleString()+'</span></div>';
						noticeMsg += '<div class="notify_con">';
						msg.content = msg.content.replace(/\n/g, '<br/>').replace(/ /g, '&nbsp;');
						noticeMsg += '<p>'+msg.content+'</p>';
						noticeMsg += '</div>';
						noticeMsg += '</div>';
					}
						
					if($(".notify_list").length > 0) {
						$("#notice_hasMore").before(noticeMsg);
					} else if(result.hasMore) {
						noticeMsg += '<div class="font_gray" style="padding:20px 0;text-align: center;" id="notice_hasMore">查看更早消息</div>';
						$(".notify").html(noticeMsg);
						$("#notice_hasMore").on('click', function() {
							latte.header.getNotice();
						});
					} else {
						$(".notify").html(noticeMsg);
					}

					if(result.hasMore == false) {
						$('#notice_hasMore').css('display', 'none');
					}
				}
			}
		});
	},
	//-----------------------------------消息通知-------------------------------
	checkNoReadNotice : function() {
		$.ajax({
			type : "get",
			url :  ctx + "/announcement/notice/noread",
			dataType : "text",
			success : function(result) {
				if(result.noReadmsgNum > 0) {
					if($(".BtnBlue30 a em").hasClass("shrink")==true) {
						$(".notify").css("display", "block");
						$(".BtnBlue30 a em").toggleClass("shrink");
					}
				}
			}
		});
	}
}
		
/** login && common event **/
//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
$(document).ready(function(){
	//是否显示添加公立客户
	if($('#p_menu_add_customer').find('li').length==0) {
		$('#p_menu_add_customer').css('display', 'none');
	}
	//是否显示添加集团客户
	if($('#p_menu_add_private').find('li').length==0) {
		$('#p_menu_add_private').css('display', 'none');
	}

	if($('#public_school_list').length>0) {
		$('#p_menu_list_customer_school').css('display', 'block');
	} else if($('#public_director_list').length>0) {
		$('#p_menu_list_customer_director').css('display', 'block');
	}
	
	if($('#private_school_list').length>0) {
		$('#p_menu_list_privatecustomer_school').css('display', 'block');
	} else if($('#private_director_list').length>0) {
		$('#p_menu_list_privatecustomer_director').css('display', 'block');
	}
	
	
	
	
	
	if($('.header').length > 0){
		$("#headerUsername").html(loginUserName);
		$("#current_user_name").val(loginUserName);
		
		$('#hd_menu_setting').click(function(){
			var content = '<div class="login-dialog">';
			content += '<form>';
			content += '<dl><dt><em>*</em>原密码：</dt><dd><input id="login_dlg_old_password" name="old_passwd" type="password" onBlur="latte.header.oldPasswdIsCorrect();"/> </dd></dl>';
			content += '<dl style="line-height:20px;"><dt>&nbsp;</dt><dd><label id="old_passwd_msg" class="error"></label></dd></dl>';
			content += '<dl><dt><em>*</em>新密码：</dt><dd><input id="login_dlg_new_password" name="new_password" type="password" onBlur="latte.header.checkNewLoginPassIsCorrect();"/></dd></dl>';
			content += '<dl style="line-height:20px;"><dt>&nbsp;</dt><dd><label id="new_passwd_msg" class="error"></label></dd></dl>';
			content += '<dl><dt><em>*</em>确认密码：</dt><dd><input id="login_dlg_confirm_password" name="confirm_password" type="password" onBlur="latte.header.confirmNewPasswdIsCorrect();"/></dd></dl>';
			content += '<dl style="line-height:20px;"><dt>&nbsp;</dt><dd><label id="confirm_passwd_msg" class="error"></label></dd></dl>';
			content += '</form>';
			content += '</div>';
			
			art.dialog({
				lock:true,
				width:400,
		        id : "sys_setting",
		        title : '修改密码',
		        content : content,
		        okVal : '确认',
		        ok : function() {
		        	latte.header.updatePersonPwd();
		        	return false;
		        },
		        cancelVal : '取消',
		        cancel : function() {
		        }
		    });
		});
	}
	
	if($('.feedback-top').length > 0){
		$(window).scroll(function(){
			if ($(window).scrollTop()>100){
			 $('.feedback-top').show();
			 }
			 else
			 {
			  $('.feedback-top').hide();
			 }
		});
	}
	
	if($.validator){
		$.validator.setDefaults({
			debug: true,
			showErrors : function(errorMap){
				latte.errorMap = errorMap;
			}
		});
		
		$.validator.addMethod("tel",function(value,element,params){
			var telPattern = /1[3-8]+\d{9}/;
			var phoneReg = /^((0\d{2,3})-)(\d{7,8})(-(\d{1,}))?$/;
			if(value && !telPattern.test(value) && !phoneReg.test(value) ) {
				return false;
			}
			return true;
		},"请输入正确的联系方式");
		
	}
	if(art.dialog){
		(function (config) {
		    config['lock'] = true;
		})(art.dialog.defaults);
	}
});


//显示和隐藏消息
$(document).ready(function(){
	$("#btnSave").on("click", function(){
	    $(".notify").toggle();
	    $(".BtnBlue30 a em").toggleClass("shrink");
	});	
	
//	var islogin = $("#islogin").val();
//	if(islogin) {
//		latte.header.getNotice();
//		latte.header.checkNoReadNotice();
//		setInterval(function() {
//			latte.header.checkNoReadNotice();
//		}, 60000);
//	}
});