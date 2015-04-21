latte.xk.role = {
	nameBlurEvent : function() {
		var name = $("input[name=name]").val();
		if(name == '' || name.length >20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			return ;
		}
		$("#name_label").html("");
		latte.xk.role.valRoleName(name);
	},
	//
	remarkBlurEvent : function() {
		var remark = $("input[name=remark]").val();
		if(remark.length > 30) {
			$("#remark_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 0 和 30 之间的字符");
			return ;
		}
		$("#remark_label").html("");
	},
	//
	checkFirstFuncSelected : function(firstId) {
		var firstFuncSelected = $("#funclist"+firstId).prop("checked");
		if(firstFuncSelected == false) {
			$("#second_"+firstId).find("li > div > div > span > input").each(
				function() {
					$(this).prop("checked", false);
				}		
			);
		} else {
			$("#second_"+firstId).find("li > div > div > span > input").each(
				function() {
					$(this).prop("checked", true);
				}		
			);
		}
	},
	//
	checkSecondFuncSelected : function(firstId, secondId) {
		var secondFuncSelected = $("#funclist"+secondId).prop("checked");
		if(secondFuncSelected) {
			$("#third_"+secondId).find("li > div > div > span > input").each(
				function() {
					$(this).prop("checked", true);
				}		
			);
			$("#funclist"+firstId).prop("checked", true);
		}
		if(!secondFuncSelected) {
			$("#third_"+secondId).find("li > div > div > span > input").each(
				function() {
					$(this).prop("checked", false);
				}		
			);
			
			//判断二层是否有选中
			var flag = false;
			$("#second_"+firstId).find("li > div > div > span > input").each(
					function() {
						if($(this).prop("checked")) {
							flag = true;
						}
					}		
			);
			if(flag == false) {
				$("#funclist"+firstId).prop("checked", false);
			}
		}
	},
	//
	checkThirdFuncSelected : function(firstId, secondId, thirdId) {
		var thirdFuncSelected = $("#funclist"+thirdId).prop("checked");
		if(thirdFuncSelected) {
			$("#funclist"+firstId).prop("checked", true);
			$("#funclist"+secondId).prop("checked", true);
			
			$("#third_"+secondId).find(".tree1NodeName").each(function() {
				if($(this).html().indexOf("查看")>=  0) {
					var first = $(this).prev().find('input:first');
					first.prop("checked", true);
				}
				
			});
		} else {
			//如果取消查看，则取消三级的所有功能
			var flag = false;
			$("#third_"+secondId).find(".tree1NodeName").each(function() {
				if($(this).html().indexOf("查看")>=  0) {
					var first = $(this).prev().find('input:first');
					flag = first.prop("checked");
				}
			});

			if(flag == false) {
				$("#third_"+secondId).find("li > div > div > span > input").each(function() {
					$(this).prop("checked", false);
				});
			}
			
			//判断三层是否有选中
			var flag = false;
			$("#third_"+secondId).find("li > div > div > span > input").each(
					function() {
						if($(this).prop("checked")) {
							flag = true;
						}
					}		
			);
			if(flag == false) {
				$("#funclist"+secondId).prop("checked", false);
			}
			//判断二层是否有选中
			flag = false;
			$("#second_"+firstId).find("li > div > div > span > input").each(
					function() {
						if($(this).prop("checked")) {
							flag = true;
						}
					}		
			);
			if(flag == false) {
				$("#funclist"+firstId).prop("checked", false);
			}
		}
	},
	//显示或隐藏权限功能树
	showOrHiddenTree : function(firstFuncId) {
		var secondUl = document.getElementById("second_"+firstFuncId);
		var firstImg = document.getElementById("first_"+firstFuncId);
		if(secondUl.style.display=='none') {
			secondUl.style.display='block';
			firstImg.className = ''; 
			firstImg.className = 'tree1Aarrow'; 
		} else {
			secondUl.style.display='none';
			firstImg.className = ''; 
			firstImg.className = 'tree1Active'; 
		}
	},
	valRoleName : function(name) {
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/user/valRoleName",
			dataType : "text",
			data : {
				'rolename' : name
			},
			success : function(result) {
				if (result == false) {
					$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;角色名称已存在");
					return;
				} else {
					if(name == '' || name.length >20) {
						$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
					} else {
						$("#name_label").html("");
					}
					return;
				}
			}
		});
	},
//---------------------------------- add ----------------------------------------------
	initAddRole : function() {
		$("#usermanage_roleAdd").addClass("current");
		$('#hd_menu_user').addClass('current');
		$("#name").blur(latte.xk.role.nameBlurEvent);
		$("#remark").blur(latte.xk.role.remarkBlurEvent);
	},
	//添加角色
	roleAdd : function() {
		var flag = true;
		var name = $("input[name=name]").val();
		var domainId = $("input[name=domainId]").val();
		var remark = $("input[name=remark]").val();
		if(name == '' || name.length >20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			flag = false;
		}
		if(remark.length > 30) {
			$("#remark_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 0 和 30 之间的字符");
			flag = false;
		}		
		if(name!='') {
			latte.xk.role.valRoleName(name);
		}
		if($("#name_label").html() != '') {
			flag = false;
		}
		if(flag == false) {
			return;
		}
		var funclists = "";
      	var items = $("input:checkbox[name=funclist]:checked");
      	for (var i = 0; i < items.length; i++) {
      	     funclists = (funclists + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
      	}
      	$.ajax({
			type : "post",
			async: false,
			url : ctx + "/user/doAddRole",
			dataType : "text",
			data : {
    			'name' : name,
    			'domainId' : domainId,
    			'remark' : remark,
    			'funclist' : funclists
    		},
			success : function(result) {
				var msg;
				if (result == true) {
					msg = "操作成功";
    			} else {
    				msg = "操作失败";
    			}
				util.dialog.timerDialog(0.5,msg,function(){
					$("input[name=name]").val("");
		    		$("input[name=remark]").val("");
		    		
		    		$("input:checkbox[name=funclist]:checked").each(function(){     
		    		    	$(this).removeAttr("checked");     
		    		});
	        	});
			}
		});
	}, 
	
//
	initShowRole : function() {
		$("#usermanage_roleManage").addClass("current");
		$('#hd_menu_user').addClass('current');
		$(":checkbox").attr('disabled', true);
	 	$(":checkbox").val(funcIds);
	},
//------------------------------------ list ------------------------------------------
	initRoleList : function() {
		$("#usermanage_roleManage").addClass("current");
		$('#hd_menu_user').addClass('current');
	},
	//
	toRoleListPage : function(start) {
		window.top.location.href = ctx + "/user/toRoleList?start="+start;
	},
	//
	deleteRole : function(roleId, rolename) {
		util.dialog.defaultDialog("确认要删除该角色?", function(){
			$.ajax({
				type : "get",
				url :  ctx + "/user/hasRoleInGroup?roleId="+roleId,
				dataType : "text",
				success : function(result) {
					if (result == false) {
						$.ajax({
							type : "get",
							async: false,
							url :  ctx + "/user/doDeleteRole?roleId="+roleId,
							dataType : "text",
							success : function(result) {
								var msg;
								if (result == true) {
									msg = "操作成功";
								} else {
									msg = "操作失败";
								}
								util.dialog.timerDialog(0.5,msg,function(){
									var currentPage = $("#currentPage").val();
									if(currentPage == '') {
										currentPage = 1;
									}
					        		latte.xk.role.getRoleList(currentPage);
					        	});
							}
						});
					} else {
						art.dialog({
							lock:true,
							width:240,
							height:120,
					        id : 'remove_role_from_group',
					        title : '角色名称：'+rolename,
					        content : "组中包含该角色，请先从组中移除该角色！",
					        okVal : '确认',
					        ok : function(){
					        }
					    });
					}
				}
			});
		}, function() {
			
		}, '角色名称：'+rolename);
	},
	showRole : function(roleId) {
		window.top.location.href = ctx + "/user/showRole?roleId="+roleId;
	},
	editRole : function(roleId) {
		window.top.location.href = ctx + "/user/openUpdateRole?roleId="+roleId;
	},
//-------------------------------- show ----------------------------
//-------------------------------- update ----------------------------
	initUpdateRole : function(funcIds) {
		$("#usermanage_roleManage").addClass("current");
		$('#hd_menu_user').addClass('current');
		$("#remark").blur(latte.xk.role.remarkBlurEvent);
		$(":checkbox").val(funcIds);
	},
	cancelSelected : function(oldremark) {
		var roleId = $("input[name=id]").val();
		window.top.location.href = ctx + "/user/openUpdateRole?roleId="+roleId;
	},

	roleUpdate : function() {
		var flag = true;
		var name = $("input[name=name]").val();
		var remark = $("input[name=remark]").val();
		var domainId = $("input[name=domainId]").val();
		
		if(remark.length > 30) {
			$("#remark_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 0 和 30 之间的字符");
			flag = false;
		}		
		
		if(flag == false) {
			return;
		}
		
		var funclists = "";
      	var items = $("input:checkbox[name=funclist]:checked");
      	for (var i = 0; i < items.length; i++) {
      	     funclists = (funclists + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
      	}
      	
      	$.ajax({
			type : "post",
			async: false,
			url : ctx + "/user/doUpdateRole",
			dataType : "text",
			data : {
    			'name' : name,
    			'remark' : remark,
    			'domainId' : domainId,
    			'funclist' : funclists,
				'id' : $("input[name=id]").val(),
				'stat' : $("input[name=stat]").val(),
				'flag' : $("input[name=flag]").val()
    		},
			success : function(result) {
				var msg;
				if (result.success == true) {
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
	                latte.xk.role.getRoleList(pageNo);
	            }
	        });
	    } else {
	    	$("#pagebar").hide();
	    }
	},
	getRoleList : function(pageNo) {
		var roleName = $("#qroleName").val();
		$.ajax({
			type : "post",
			async: false,
			url :  ctx + "/user/toRoleList",
			dataType : "text",
			data : {
				'start' : pageNo,
				'limit' : 30,
				'userType' : 1,
				'roleName' : roleName.trim()
			},
			success : function(result) {
				$("#currentPage").val(result.currentPage);
	    	    $("#totalPage").val(result.totalPage);
	    	    $("#totalCount").val(result.totalCount);
	            $("#exportData").empty();
				if (result.roleVoList.length == 0) {
					$("#exportData").append("<tr style='line-height:30px;background:#FFEBE5;padding-left:12px;font-size: 12px;'><td colspan='12' style='text-align: left;'>搜索”"+roleName.trim()+"“ ,获得约0条结果!</td></tr>");
	            } else {
	            	var tpl = $("#role-list-tpl").html();
	            	var roleTBody = juicer(tpl, result);
					$("#exportData").append(roleTBody);
				}
				latte.xk.role.page();
			}
		});
	}
}