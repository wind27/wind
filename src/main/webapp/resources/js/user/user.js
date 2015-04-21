latte.xk.user = {
//---------------------------------------------------------
	init : function() {
		$("#primarySubs").click(latte.xk.user.primarySubsClickEvent);
		$("#juniorSubs").click(latte.xk.user.juniorSubsClickEvent);
		$("#seniorSubs").click(latte.xk.user.seniorSubsClickEvent);
		
		$("input[name='primarySubject']").click(latte.xk.user.primarySubjectClickEvent);
		$("input[name='juniorSubject']").click(latte.xk.user.juniorSubjectClickEvent);
		$("input[name='seniorSubject']").click(latte.xk.user.seniorSubjectClickEvent);
	},
		
		
	primarySubsClickEvent : function() {
		if($("#primarySubs").is(":checked")==true) {
			$("input[name='primarySubject']").each(function(){
				   $(this).prop("checked",true);
			});
		} else if($("#primarySubs").is(":checked")==false){
			$("input[name='primarySubject']").each(function(){
				   $(this).prop("checked",false);
			});
		}
	},
	juniorSubsClickEvent : function() {
		if($("#juniorSubs").is(":checked")==true) {
			$("input[name='juniorSubject']").each(function(){
				   $(this).prop("checked",true);
				});
		} else if($("#juniorSubs").is(":checked")==false) {
			$("input[name='juniorSubject']").each(function(){
				   $(this).prop("checked",false);
			});
		}
	},
	seniorSubsClickEvent : function() {
		if($("#seniorSubs").is(":checked")==true) {
			$("input[name='seniorSubject']").each(function(){
				   $(this).prop("checked",true);
			});
		} else if($("#seniorSubs").is("checked")==false){
			$("input[name='seniorSubject']").each(function(){
				   $(this).prop("checked",false);
			});
		}
	},
	
	primarySubjectClickEvent : function() {
		var flag = false;
		$("input[name='primarySubject']").each(function(){
			if($(this).is(":checked")==true) {
				flag = true;
				return;
			}
		});
		if(flag == false) {
			$("#primarySubs").prop("checked",false);
		} else {
			$("#primarySubs").prop("checked",true);
		}
	},
	juniorSubjectClickEvent : function() {
		var flag = false;
		$("input[name='juniorSubject']").each(function(){
			if($(this).is(":checked")==true) {
				flag = true;
				return;
			}
		});
		if(flag == false) {
			$("#juniorSubs").prop("checked",false);
		} else {
			$("#juniorSubs").prop("checked",true);
		}
	},
	seniorSubjectClickEvent : function() {
		var flag = false;
		$("input[name='seniorSubject']").each(function(){
			if($(this).is(":checked")==true) {
				flag = true;
				return;
			}
		});
		if(flag == false) {
			$("#seniorSubs").prop("checked",false);
		} else {
			$("#seniorSubs").prop("checked",true);
		}
	},
	//启用
	enablePerson : function (userId, username, groupId){
		var url = ctx + "/user/doEnablePerson?userId="+userId+"&groupId="+groupId;
		latte.xk.user.enableOrDisablePerson(url, 0, username);
		
	},
	//停用
	disablePerson : function (userId, username, groupId){
		var url = ctx + "/user/doDisablePerson?userId="+userId+"&groupId="+groupId;
		latte.xk.user.enableOrDisablePerson(url, 1, username);
	},
	//启停用户
	enableOrDisablePerson : function (url, type, username) {
		var enOrDis; 
		if(type==0) {
			enOrDis = "启用";
		} else {
			enOrDis = "停用";
		}
		util.dialog.defaultDialog("确认"+enOrDis+"该用户?", function(){
			$.ajax({
				type : "get",
				url :  url,
				dataType : "text",
				success : function(result) {
					if (result == true) {
						msg = "操作成功!";
					} else {
						msg = "操作失败!";
					}
		        	util.dialog.timerDialog(0.5,msg,function(){
		        		var pageNo = $("#currentPage").val();
		        		if(pageNo=='') {
		        			pageNo = 0;
		        		}
		        		latte.xk.user.getPersonList(pageNo);
		        	});
				}
			});
		}, function() {
			
		}, '姓名：'+username);
	},
	//重置密码
	updatepwd : function (loginUser, username, groupId){
		util.dialog.defaultDialog("确认要重置密码?", function(){
			$.ajax({
				type : "post",
				url :  ctx + "/user/updatepwd?loginUser="+loginUser,
				dataType : "text",
				success : function(result) {
					if (result == true) {
						msg = "密码已被重置为123456";
					} else {
						msg = "密码重置失败!";
					}
					art.dialog({
						lock:true,
						width:240,
						height:120,
				        id : 'disable_user',
				        title : '姓名：'+username,
				        content : msg,
				        okVal : '确认',
				        ok : function(){
				        }
				    });
				}
			});
		}, function() {
			
		}, '姓名：'+username);
	},
	resetUser : function (userId,groupId){
		window.top.location.href = ctx + "/user/openUpdatePerson?userId="+userId+"&groupId="+groupId;
	},
	//查看用户
	showPerson : function (userId,groupId){
		window.top.location.href = ctx + "/user/showPerson?userId="+userId+"&groupId="+groupId;
		
	},
	//修改用户
	updatePerson : function (userId, groupId){
		var name = $("#name").val();
		
		window.top.location.href = ctx + "/user/openUpdatePerson?userId="+userId+"&groupId="+groupId;
	},
	initPersonList : function() {
		$("#usermanage_personManage").addClass("current");
		$('#hd_menu_user').addClass('current');
		latte.xk.user.getFunc();
	},
	//-------------------------- add -------------------------------
	initAddPerson : function() {
		latte.xk.user.init();
		$("#usermanage_personAdd").addClass("current");
		$('#hd_menu_user').addClass('current');
		$("#name").blur(latte.xk.user.nameBlurEvent);
		$("#groupId").change(latte.xk.user.groupChangeEvent);
		$("#loginUser").blur(latte.xk.user.loginUserBlurEvent);
		$("#loginPass").blur(latte.xk.user.loginPassBlurEvent);
		$("#confirmpwd").keyup(latte.xk.user.confirmpwKeyupEvent);
		$("#phone").blur(latte.xk.user.phoneBlurEvent);
		$("#email").blur(latte.xk.user.emailBlurEvent);
		$("#qq").blur(latte.xk.user.qqBlurEvent);
	},
	valLoginUser : function(loginUser) {
		$.ajax({
			type : "get",
			async: false,
			url :   ctx + "/user/valLoginUser",
			dataType : "text",
			data : {
				'loginUser' : loginUser
			},
			success : function(result) {
				if (result == false) {
					$("#loginUser_label").html("&nbsp;&nbsp;&nbsp;&nbsp;用户名已存在");
					return;
				} else {
					$("#loginUser_label").html("");
					return;
				}
			}
		});
	}, 
	nameBlurEvent : function() {
		var name = $("input[name=name]").val();
		if(name.length<1 || name.length>20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			return ;
		} else if(name.indexOf('_')>=0) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;用户名不能包含'_'");
			return ;
		}
		$("#name_label").html("");
	},
	
	groupChangeEvent : function() {
		var groupId = $("#groupId").val();
		
		if(groupId == -1) {
			$("#groupId_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请选择用户所属角色");
			return ;
		}
		if(groupId == 1) {
			$("input[name='primarySubject']").each(function(){
				   $(this).prop("checked",true);
				   $(this).attr('disabled', true);
			});
			$("input[name='juniorSubject']").each(function(){
				   $(this).prop("checked",true);
				   $(this).attr('disabled', true);
			});
			$("input[name='seniorSubject']").each(function(){
				   $(this).prop("checked",true);
				   $(this).attr('disabled', true);
			});
			$("#primarySubs").prop("checked",true);
			$("#juniorSubs").prop("checked",true);
			$("#seniorSubs").prop("checked",true);
			
			$("#primarySubs").attr('disabled', true);
			$("#juniorSubs").attr('disabled', true);
			$("#seniorSubs").attr('disabled', true);
			
			$('#user_stats_start').prop("checked",true);
			$('#user_stats_stop').prop("checked",false);
			$("#user_stats_start").attr('disabled', true);
			$("#user_stats_stop").attr('disabled', true);
		} else {
			$("input[name='primarySubject']").each(function(){
				   $(this).attr('disabled', false);
			});
			$("input[name='juniorSubject']").each(function(){
				   $(this).attr('disabled', false);
			});
			$("input[name='seniorSubject']").each(function(){
				   $(this).attr('disabled', false);
			});
			$("#primarySubs").attr('disabled', false);
			$("#juniorSubs").attr('disabled', false);
			$("#seniorSubs").attr('disabled', false);
			$("#user_stats_start").attr('disabled', false);
			$("#user_stats_stop").attr('disabled', false);
		}
		$("#groupId_label").html("");
	},
	//
	loginUserBlurEvent : function() {
		var loginUser = $("input[name=loginUser]").val();
		var loginUserReg=/^[\w]{4,20}$/;
		if(loginUserReg.test(loginUser) == false) {
			$("#loginUser_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 4 和 20 之间，由包含字母、数字、下划线组成的字符");
			return ;
		}
		$("#loginUser_label").html("");
		latte.xk.user.valLoginUser(loginUser);
	},
	//
	loginPassBlurEvent : function() {
		var loginPass = $("input[name=loginPass]").val();
		var loginPassReg=/^[a-zA-Z0-9]{6,20}$/;
		if(loginPassReg.test(loginPass) == false) {
			$("#loginPass_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 6 和 20 之间，由字母、数字组成的字符");
			return ;
		}
		$("#loginPass_label").html("");
	},
	//
	confirmpwKeyupEvent : function() {
		var loginPass = $("input[name=loginPass]").val();
		var confirmpwd = $("input[name=confirmpwd]").val();
		if(loginPass != confirmpwd) {
			$("#confirmpwd_label").html("&nbsp;&nbsp;&nbsp;&nbsp;两次密码不相同");
			return ;
		}
		$("#confirmpwd_label").html("");
	},
	//
	phoneBlurEvent : function() {
		var phone = $("input[name=phone]").val();
		var phoneReg = /^(\d{11})$|^(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$|^(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$/;
		if(phone!="" && phoneReg.test(phone) == false) {
			$("#phone_label").html("&nbsp;&nbsp;&nbsp;&nbsp;电话号码格式不正确");
			return ;
		}
		$("#phone_label").html("");
	},
	//

	emailBlurEvent : function() {
		var email = $("input[name=email]").val();
		 var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		 if(email!="" && emailReg.test(email) == false){
			 $("#email_label").html("&nbsp;&nbsp;&nbsp;&nbsp;邮箱地址格式不正确");
			 return;
		 }
		if(email!="" && email.length > 50) {
			$("#email_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度小于 50 的邮箱地址");
			return ;
		}
		$("#email_label").html("");
	},
	//
	qqBlurEvent : function() {
		var qq = $("input[name=qq]").val();
		var qqReg = /^[0-9]{5,12}$/; 
		if(qq!="" && qqReg.test(qq) == false) {
			$("#qq_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 5 和 12 之间的数字");
			return ;
		}
		$("#qq_label").html("");
	},
	
	personAdd : function() {
		var flag = true;
		var name = $("input[name=name]").val();
		var groupId = $("#groupId").val();
		var loginUser = $("input[name=loginUser]").val();
		var loginPass = $("input[name=loginPass]").val();
		var confirmpwd = $("input[name=confirmpwd]").val();
		var phone = $("input[name=phone]").val();
		var email = $("input[name=email]").val();
		var qq = $("input[name=qq]").val();
		var stats = $('input:radio:checked').val();

		if(name.length<1 || name.length>20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			flag = false;
		} else if(name.indexOf('_')>=0) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;用户名不能包含'_'");
			flag = false;
		}

		var loginUserReg=/^[\w]{4,20}$/;
		if(loginUserReg.test(loginUser) == false) {
			$("#loginUser_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 4 和 20 之间，由包含字母、数字、下划线组成的字符");
			flag = false;
		}
		var loginPassReg=/^[a-zA-Z0-9]{6,20}$/;
		if(loginPassReg.test(loginPass) == false) {
			$("#loginPass_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 6 和 20 之间，由字母、数字组成的字符");
		}
		var confirmpwd = $("input[name=confirmpwd]").val();
		if(confirmpwd == '') {
			$("#confirmpwd_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 6 和 20 之间，由字母、数字组成的字符");
			flag = false;
		}
		if(loginPass != confirmpwd) {
			$("#confirmpwd_label").html("&nbsp;&nbsp;&nbsp;&nbsp;两次密码不相同");
			flag = false;
		}
		var phoneReg = /^(\d{11})$|^(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$|^(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$/;
		if(phone != '' && phoneReg.test(phone) == false) {
			$("#phone_label").html("&nbsp;&nbsp;&nbsp;&nbsp;电话号码格式不正确");
			flag = false;
		}
		 var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		 if(email != '' && emailReg.test(email) == false){
			 $("#email_label").html("&nbsp;&nbsp;&nbsp;&nbsp;邮箱地址格式不正确");
			 flag = false;
		 }
		if(email != '' && email.length > 50) {
			$("#email_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度小于 50 的邮箱地址");
			flag = false;
		}
		var qqReg = /^[0-9]{5,12}$/; 
		if(qq != '' && qqReg.test(qq) == false) {
			$("#qq_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 5 和 12 之间的数字");
			flag = false;
		}
		if(groupId == -1) {
			$("#groupId_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请选择用户所属角色");
			flag = false;
		}
		if(loginUser!='') {
			latte.xk.user.valLoginUser(loginUser);
		}
		if($("#loginUser_label").html() != '') {
			flag = false;
		}
		if(flag == false) {
			return ;
		}	
		
		var primarySubject = "";
	  	var items = $("input:checkbox[name=primarySubject]:checked");
	  	for (var i = 0; i < items.length; i++) {
	  		primarySubject = (primarySubject + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
	  	}
		
		var juniorSubject = "";
	  	items = $("input:checkbox[name=juniorSubject]:checked");
	  	for (var i = 0; i < items.length; i++) {
	  		juniorSubject = (juniorSubject + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
	  	}
	  	
	  	
		var seniorSubject = "";
	  	items = $("input:checkbox[name=seniorSubject]:checked");
	  	for (var i = 0; i < items.length; i++) {
	  		seniorSubject = (seniorSubject + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
	  	}
//	  	loginPass = hex_md5(loginPass);
	  	loginPass = hex_md5(loginPass+"#DsjW2014@xDf.AdMin.cn#");
	  	$.ajax({
			type : "post",
			async: false,
			url :  ctx + "/user/doAddPerson",
			dataType : "text",
			data : {
				'name' : name,
				'groupId' : groupId,
				'loginUser' : loginUser,
				'loginPass' : loginPass,
				'phone' : phone,
				'email' : email,
				'qq' : qq,
				'flag' : 1,
				'stats' : stats,
				'primarySubject' : primarySubject,
				'juniorSubject' : juniorSubject,
				'seniorSubject' : seniorSubject
			},
			success : function(result) {
				var msg;
				if (result == true) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					window.top.location.href = ctx + "/user/openAddPerson";
	        	});
			}
		});
	},
//----------------------------------------- update -----------------------------------------------------------
	initUserStage : function(primarySubs, juniorSubs, seniorSubs) {
		if(primarySubs) {
			$("#primarySubs").prop("checked", true);
		}
		if(juniorSubs) {
			$("#juniorSubs").prop("checked", true);
		}
		if(seniorSubs) {
			$("#seniorSubs").prop("checked", true);
		}
	},
	initUserSubject : function(selectedPrimarySubject, selectedJuniorSubject, 
			selectedSeniorSubject) {
		$("#usermanage_personManage").addClass("current");
		$('#hd_menu_user').addClass('current');
		for(var i=0; i<selectedJuniorSubject.length; i++){
			var subject = "juniorSubject_"+selectedJuniorSubject[i];
			$("#"+subject).attr('checked',true);
		};
		for(var i=0; i<selectedSeniorSubject.length; i++){
			var subject = "seniorSubject_"+selectedSeniorSubject[i];
			$("#"+subject).attr('checked',true);
		};
		for(var i=0; i<selectedPrimarySubject.length; i++){
			var subject = "primarySubject_"+selectedPrimarySubject[i];
			$("#"+subject).attr('checked',true);
		};
	},
	initUpdateUser : function(selectedPrimarySubject, selectedJuniorSubject, 
			selectedSeniorSubject) {
		latte.xk.user.init();
		$("#usermanage_personManage").addClass("current");
		$('#hd_menu_user').addClass('current');
		$("#name").blur(latte.xk.user.nameBlurEvent);
		$("#groupId").change(latte.xk.user.groupChangeEvent);
		$("#phone").blur(latte.xk.user.phoneBlurEvent);
		$("#email").blur(latte.xk.user.emailBlurEvent);
		$("#qq").blur(latte.xk.user.qqBlurEvent);
		latte.xk.user.groupChangeEvent();
		latte.xk.user.initUserSubject(selectedPrimarySubject, 
				selectedJuniorSubject, selectedSeniorSubject);
	},
	userUpdate : function() {
		var flag = true;
		var id = $("input[name=id]").val();
		var name = $("input[name=name]").val();
		var groupId = $("#groupId").val();
		var phone = $("input[name=phone]").val();
		var email = $("input[name=email]").val();
		var qq = $("input[name=qq]").val();
		var stats = $('#stats').val();
		var selectGroupId = $("#selectGroupId").val();

		if(name.length<1 || name.length>20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			flag = false;
		} else if(name.indexOf('_')>=0) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;用户名不能包含'_'");
			flag = false;
		}

		var phoneReg = /^(\d{11})$|^(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})$|^(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$|^(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})$/;
		if(phone != '' && phoneReg.test(phone) == false) {
			$("#phone_label").html("&nbsp;&nbsp;&nbsp;&nbsp;电话号码格式不正确");
			flag = false;
		}
		 var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		 if(email != '' && emailReg.test(email) == false){
			 $("#email_label").html("&nbsp;&nbsp;&nbsp;&nbsp;邮箱地址格式不正确");
			 flag = false;
		 }
		if(email != '' && email.length > 50) {
			$("#email_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度小于 50  的邮箱地址");
			flag = false;
		}
		var qqReg = /^[0-9]{5,12}$/; 
		if(qq != '' && qqReg.test(qq) == false) {
			$("#qq_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 5 和 12 之间的数字");
			flag = false;
		}
		
		if(flag == false) {
			return ;
		}	
		
		var primarySubject = "";
	  	var items = $("input:checkbox[name=primarySubject]:checked");
	  	for (var i = 0; i < items.length; i++) {
	  		primarySubject = (primarySubject + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
	  	}
		
		var juniorSubject = "";
	  	items = $("input:checkbox[name=juniorSubject]:checked");
	  	for (var i = 0; i < items.length; i++) {
	  		juniorSubject = (juniorSubject + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
	  	}
	  	
	  	
		var seniorSubject = "";
	  	items = $("input:checkbox[name=seniorSubject]:checked");
	  	for (var i = 0; i < items.length; i++) {
	  		seniorSubject = (seniorSubject + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
	  	}

	  	$.ajax({
			type : "post",
			async: false,
			url :  ctx + "/user/doUpdatePerson",
			dataType : "text",
			data : {
				'id' : id,
				'name' : name,
				'groupId' : groupId,
				'phone' : phone,
				'email' : email,
				'qq' : qq,
				'stats' : stats,
				'primarySubject' : primarySubject,
				'juniorSubject' : juniorSubject,
				'seniorSubject' : seniorSubject,
				'selectGroupId' : selectGroupId
			},
			success : function(data) {
				var msg;
				if (data.result == true) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					window.history.back(-1);
	        	});
			}
		});
	},
//分页------------------------------------------------------------------------------------------------------------------
	page : function() {
		var currentPage = $("#currentPage").val();
		var totalPage = $("#totalPage").val();
		var totalCount = $("#totalCount").val();
		$("#currentpageNo").val($("#currentPage").val());
	    if( totalPage > 1 ){
	    	$("#pagebar").show();
	    	$.fn.jpagebar({
	            renderTo : $("#pagebar"),
	            totalpage : totalPage,
	            totalcount : totalCount,
	            pagebarCssName : 'pagination2',
	            currentPage : currentPage,
	            onClickPage : function(pageNo) {
	                $.fn.setCurrentPage(this, pageNo);
	                latte.xk.user.getPersonList(pageNo);
	            }
	        });
	    } else {
	    	$("#pagebar").hide();
	    }
	},
	getPersonList : function(pageNo) {
		var groupId = $("#groupId").val();
		var	name = $("#name").val();
		var stats = $("#stats").val();
		
		$.ajax({
			type : "post",
			async: false,
			url :  ctx + "/user/toPersonList",
			dataType : "text",
			data : {
				'start' : pageNo,
				'limit' : 30,
				'groupId' : groupId,
				'stats' : stats,
				'name' : name.trim()
			},
			success : function(result) {
				$("#currentPage").val(result.currentPage);
	    	    $("#totalPage").val(result.totalPage);
	    	    $("#totalCount").val(result.totalCount);
	            $("#exportData").empty();
				if (result.adminVoList.length == 0) {
	            	$("#exportData").append("<tr style='line-height:30px;background:#FFEBE5;padding-left:12px;font-size: 12px;'><td colspan='12' style='text-align: left;'>搜索”"+qname.trim()+"“ ,获得约0条结果!</td></tr>");
	            } else {
					var tpl = $("#person-list-tpl").html();
					var personTBody = juicer(tpl, result);
					$("#exportData").append(personTBody);
				}
				latte.xk.user.page();
			}
		});
	},
	getFunc : function() {
		$.ajax({
			type : "get",
			async: false,
			url :  ctx + "/user/getGroup",
			dataType : "text",
			success : function(result) {
				var groupSelect = '<option value="-1" selected="selected">全部</option>';
				if(result.groupList.length>0) {
					for(var i=0; i<result.groupList.length; i++) {
						var group = result.groupList[i];
						groupSelect += '<option value="'+group.id +'">'+group.name+'</option>';
					}
				}
				$("#groupId").html(groupSelect);
			}
		});
	}
}