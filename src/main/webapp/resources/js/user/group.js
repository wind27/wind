latte.xk.group = {
	nameBlurEvent : function() {
		var name = $("input[name=name]").val();
		if(name == '' || name.length >20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			return ;
		}
		$("#name_label").html("");
		latte.xk.group.valGroupName(name);
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
	valGroupName : function(name) {
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/user/valGroupName",
			dataType : "text",
			data : {
				'groupname' : name
			},
			success : function(result) {
				if (result == false) {
					$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;组名称已存在");
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
	initAddGroup : function() {
		$("#usermanage_groupAdd").addClass("current");
		$('#hd_menu_user').addClass('current');
		$("#name").blur(latte.xk.group.nameBlurEvent);
		$("#remark").blur(latte.xk.group.remarkBlurEvent);
	},
	//添加组
	groupAdd : function() {
		var flag = true;
		var name = $("input[name=name]").val();
		var remark = $("input[name=remark]").val();
		var domainId = $("input[name=domainId]").val();
		if(name == '' || name.length >20) {
			$("#name_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 1 和 20 之间的字符");
			flag = false;
		}
		if(remark.length > 30) {
			$("#remark_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 0 和 30 之间的字符");
			flag = false;
		}		
		if(name!='') {
			latte.xk.group.valGroupName(name);
		}
		if($("#name_label").html() != '') {
			flag = false;
		}
		if(flag == false) {
			return;
		}
      	
      	var roleIds = "";
      	var items = $("#roleList_selected option");
      	for (var i = 0; i < items.length; i++) {
      		roleIds = (roleIds + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
      	}
      	$.ajax({
			type : "post",
			async: false,
			url : ctx + "/user/doAddGroup",
			dataType : "text",
			data : {
    			'name' : name,
    			'domainId' : domainId,
    			'remark' : remark,
    			'roleIds' : roleIds
    		},
			success : function(result) {
				var msg;
				if (result == true) {
					msg = "操作成功";
    			} else {
    				msg = "操作失败";
    			}
				util.dialog.timerDialog(0.5,msg,function(){
					window.top.location.href = ctx + "/user/openAddGroup";
	        	});
			}
		});
	}, 
	
//
	initShowGroup : function() {
		$("#usermanage_groupManage").addClass("current");
		$('#hd_menu_user').addClass('current');
	},
//------------------------------------ list ------------------------------------------
	initGroupList : function() {
		$("#usermanage_groupManage").addClass("current");
		$('#hd_menu_user').addClass('current');
	},
	//
	toGroupList : function(start) {
		window.top.location.href = ctx + "/user/toGroupList?start="+start;
	},
	//
	deleteGroup : function(groupId, groupname) {
		util.dialog.defaultDialog("确认要删除该组?", function(){
			$.ajax({
				type : "get",
				url :  ctx + "/user/hasPersonInGroup?groupId="+groupId,
				dataType : "text",
				success : function(result) {
					if (result == false) {
						$.ajax({
							type : "get",
							async: false,
							url :  ctx + "/user/doDeleteGroup?groupId="+groupId,
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
					        		latte.xk.group.getGroupList(currentPage);
					        	});
							}
						});
					} else {
						art.dialog({
							lock:true,
							width:240,
							height:120,
					        id : 'remove_user_from_group',
					        title : '组名称：'+groupname,
					        content : "请先移除该组下相关人员！",
					        okVal : '确认',
					        ok : function(){
					        }
					    });
					}
				}
			});
		}, function() {
			
		}, '组名称：'+groupname);
	},
	showGroup : function(groupId) {
		window.top.location.href = ctx + "/user/showGroup?groupId="+groupId;
	},
	editGroup : function(groupId) {
		window.top.location.href = ctx + "/user/openUpdateGroup?groupId="+groupId;
	
	},
//-------------------------------- show ----------------------------
//-------------------------------- update ----------------------------
	initUpdateGroup : function() {
		$("#usermanage_groupManage").addClass("current");
		$('#hd_menu_user').addClass('current');
		$("#remark").blur(latte.xk.group.remarkBlurEvent);
	},
	cancelSelected : function(groupId) {
		var groupId = $("input[name=id]").val();
		window.top.location.href = ctx + "/user/openUpdateGroup?groupId="+groupId;
	},

	groupUpdate : function() {
		var flag = true;
		var name = $("input[name=name]").val();
		var remark = $("input[name=remark]").val();
		
		if(remark.length > 30) {
			$("#remark_label").html("&nbsp;&nbsp;&nbsp;&nbsp;请输入 一个长度介于 0 和 30 之间的字符");
			flag = false;
		}		
		
		if(flag == false) {
			return;
		}
		
		var roleIds = "";
      	var items = $("#roleList_selected option");
      	for (var i = 0; i < items.length; i++) {
      		roleIds = (roleIds + items.get(i).value) + (((i + 1)== items.length) ? '':','); 
      	}
      	
      	$.ajax({
			type : "post",
			async: false,
			url : ctx + "/user/doUpdateGroup",
			dataType : "text",
			data : {
    			'name' : name,
    			'remark' : remark,
				'id' : $("input[name=id]").val(),
				'stats' : $("input[name=stats]").val(),
				'roleIds' : roleIds,
				'type' : $("input[name=type]").val()
    		},
			success : function(result) {
				var msg;
				if (result == true) {
					msg = "操作成功";
    			} else {
    				msg = "操作失败";
    			}
				util.dialog.timerDialog(0.5,msg,function(){  
					window.history.back(-1);
//					window.top.location.href = ctx + "/user/toGroupList?groupName="+$('#qgroupName').val()+"&start="+$('#qpage').val();
	        	});
			}
		});
	},

	addRoleToGroup : function(id, name) {
		var obj = {
			'id' : id,
			'name' : name
		};
		latte.xk.group.add($("#roleList_selected"), obj, 0);
		latte.xk.group.del("roleList_all", obj, 0);
	},
	delRoleFromGroup : function(id, name) {
		var obj = {
			'id' : id,
			'name' : name
		};
		latte.xk.group.add($("#roleList_all"), obj, 1);
		latte.xk.group.del("roleList_selected", obj);
	},
	
	add : function(roleList, obj, type) {
		var params = "'" + obj.id +"', '" + obj.name + "'";
		if(type==0) {
			roleList.append("<option ondblclick=\"latte.xk.group.delRoleFromGroup("+params+");\" value="+obj.id+" >"+obj.name+ "</option>");
		} else {
			roleList.append("<option ondblclick=\"latte.xk.group.addRoleToGroup("+params+");\" value="+obj.id+" >"+obj.name+ "</option>");
		}
	},
	
	del : function(roleListId, obj) {
		$("#"+roleListId+" option").each(function() {
			if($(this).val() == obj.id) {
				$(this).remove();
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
	                latte.xk.group.getGroupList(pageNo);
	            }
	        });
	    } else {
	    	$("#pagebar").hide();
	    }
	},
	getGroupList : function(pageNo) {
		var groupName = $("#qgroupName").val();
		$.ajax({
			type : "post",
			async: false,
			url :  ctx + "/user/toGroupList?start="+pageNo,
			dataType : "text",
			data : {
				'start' : pageNo,
				'limit' : 30,
				'userType' : 1,
				'groupName' : groupName.trim()
			},
			success : function(result) {
				$("#currentPage").val(result.currentPage);
	    	    $("#totalPage").val(result.totalPage);
	    	    $("#totalCount").val(result.totalCount);
	            $("#exportData").empty();
				if (result.groupVoList.length == 0) {
					$("#exportData").append("<tr style='line-height:30px;background:#FFEBE5;padding-left:12px;font-size: 12px;'><td colspan='12' style='text-align: left;'>搜索”"+groupName.trim()+"“ ,获得约0条结果!</td></tr>");
	            } else {
					var tpl = $("#group-list-tpl").html();
					var groupTBody = juicer(tpl, result);
					$("#exportData").append(groupTBody);
				}
				latte.xk.group.page();
			}
		});
	}
}