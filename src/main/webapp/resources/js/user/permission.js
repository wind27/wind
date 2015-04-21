latte.permission = {
//--------------------------- list funcResour -----------------------
	getFunc : function(type) {
		var parentCode = null;
		var parentName = null;
		var domain = $('#domain').val();
		var stats = 0;
	
		if(type == 0) {
			parentCode = 4;
			parentName = "权限";
		} else if(type == 1) {
			parentCode = $("#firstPid").val();
			parentName = $("#firstPid").find("option:selected").text();
			$("#secondPid").html('');
			$("#thirdPid").html('');
		} else if(type == 2) {
			parentCode = $("#secondPid").val();
			parentName = $("#secondPid").find("option:selected").text();
			$("#thirdPid").html('');
		} else if(type == 3) {
			parentCode = $("#thirdPid").val();
			parentName = $("#thirdPid").find("option:selected").text();
		}
	
		if(type== 1 && parentName == "请选择") {
			$("#parentCode").val(4);
			$("#parentModel").val((type-1)+"级:"+"运营门户");
		} else if(type == 2 && parentName == "请选择") {
			parentName = $("#firstPid").find("option:selected").text();
			parentCode = $("#firstPid").find("option:selected").val();
			$("#parentCode").val(parentCode);
			$("#parentModel").val((type-1)+"级:"+parentName);
		} else if(type == 3 && parentName == "请选择") {
			parentName = $("#secondePid").find("option:selected").text();
			parentCode = $("#secondePid").find("option:selected").val();
			$("#parentCode").val(parentCode);
			$("#parentModel").val((type-1)+"级:"+parentName);	
		} else {
			$("#parentCode").val(parentCode);
			$("#parentModel").val((type)+"级:"+parentName);
		}
	
		$("#funcId").val(parentCode);
		
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/auth/func/list",
			dataType : "text",
			data : {
				'id' : parentCode,
				'domain' : domain,
				'status' : stats
			},
			success : function(result) {
				var funcInfo = null;
				if(type == 0) {
					funcInfo = '<option value="4" selected="selected">请选择</option>';
				} else if(type == 1) {
					funcInfo = '<option value="-2" selected="selected">请选择</option>';
				} else if(type == 2) {
					funcInfo = '<option value="-3" selected="selected">请选择</option>';
				}
				if (result.funcList.length>0) {
					for(var i=0; i<result.funcList.length; i++) {
						funcInfo = funcInfo + "<option value='"+result.funcList[i].id+"'>"+result.funcList[i].name+"</option>";
					}
				}
				
				if(type == "0") {
					$("#firstPid").html(funcInfo);
				} else if(type == "1") {
					$("#secondPid").html(funcInfo);
					funcInfo = '<option value="-3" selected="selected">请选择</option>';
					$("#thirdPid").html(funcInfo);
				} else if(type == "2") {
					$("#thirdPid").html(funcInfo);
				}
			}
		});
	},
	getFuncResour : function(type, pageNo) {
		var id = null;
	
		if(type == 1) {
			id = $("#firstPid").val();
		} else if(type == 2) {
			id = $("#secondPid").val();
		} else if(type == 3) {
			id = $("#thirdPid").val();
		}
	
		$("#funcResourTBody").html('');
	
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/auth/permission/list",
			dataType : "text",
			data : {
				'funcId' : id,
				'start' : pageNo
			},
			success: function(result, textStatus) {
				if(type == 1) {
					$("#firstPid").val(result.funcId);
				} else if(type == 2) {
					$("#secondPid").val(result.funcId);
				} else if(type == 3) {
					$("#thirdPid").val(result.funcId);
				}
	    	    $("#currentPage").val(result.currentPage);
	    	    $("#totalPage").val(result.totalPage);
	    	    $("#totalCount").val(result.totalCount);
	            $("#exportData").empty();
	            if (result.permissionList.length == 0) {
	            	$("#exportData").append("<tr style='line-height:30px;background:#FFEBE5;padding-left:12px;font-size: 12px;'><td align='left' colspan='7'>获得0条记录!</td></tr>");
	            } else {
	            	var funcResour = '';
					for(var i=0; i<result.permissionList.length; i++) {
						
						var statsName;
						var operStats;
						var operStatsName;
						if(result.permissionList[i].stats == 1) {
							statsName = '启用';
							operStatsName = '停用';
							operStats = 0;
						} else if(result.permissionList[i].stats ==0) {
							statsName = '停用';
							operStatsName = '启用';
							operStats = 1;
						}
						
						funcResour = funcResour 
							+ "<tr>"
							+ "<td>"+result.permissionList[i].id+"</td>"
							+ "<td>"+result.permissionList[i].uri+"</td>"
							+ "<td>"+result.permissionList[i].funcId+"</td>"
							+ "<td>"+statsName+"</td>"
							+ "<td>"+result.permissionList[i].method+"</td>"
							+ "<td><a href='javascript:latte.permission.enableOrDisableFuncResour("+result.permissionList[i].id+", "+operStats+", "+ type +");'>"+operStatsName+"</a>&nbsp;&nbsp;"
							+ "<a href='"+ctx+"/auth/permission/edit?id="+result.permissionList[i].id+"&type="+type+"'>编辑</a></td>"
							+ "</tr>";
					}
	            	$("#exportData").append(funcResour);
	            	latte.permission.page(type);
	            }
	         }
		});
	},
	page : function(type) {
		var currentPage = $("#currentPage").val();
		var totalPage = $("#totalPage").val();
		var totalCount = $("#totalCount").val();
	
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
	                latte.permission.getFuncResour(type, pageNo);
	            }
	        });
	    } else {
	    	$("#pagebar").hide();
	    }
	},
	getFuncInfo : function(type) {
		if(type == 0) {
			$("#firstPid").html('<option value="4" selected="selected">请选择</option>');
			$("#secondPid").html('<option value="-2" selected="selected">请选择</option>');
			$("#thirdPid").html('<option value="-3" selected="selected">请选择</option>');
			latte.permission.getFunc(0);
		} else {
			latte.permission.getFunc(type);
		}
		latte.permission.getFuncResour(type);
	},
	enableOrDisableFuncResour : function(id, status, type) {
		$.ajax({
			type : "post",
			async: false,
			url : ctx + "/auth/permission/enableOrDisable",
			dataType : "text",
			data : {
				'id' : id,
				'status' : status
			},
			success : function(result) {
				if (result.success) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					latte.permission.getFunc(type);
					latte.permission.getFuncResour(type);
	        	});
			}
		});
	},
//--------------------------- add funcResour -----------------------
	uriBlurEvent : function() {
		var uri = $("#uri").val();
		if(uri.length >150 || uri.length<2) {
			$("#uri_label").html('&nbsp;&nbsp;&nbsp;&nbsp;uri 必须为2~150字符');
			return;
		}
		$("#uri_label").html('');
	},
	//添加新权限
	addFuncResour : function() {
		var funcId = $("#funcId").val();
		var uri = $("#uri").val();
		var method = $("#method").val();
		
		if(uri.length >150 || uri.length<2) {
			$("#uri_label").html('&nbsp;&nbsp;&nbsp;&nbsp;uri 必须为2~150字符');
			return;
		}

		$.ajax({
			type : "post",
			async: false,
			url : ctx + "/auth/permission/add",
			dataType : "text",
			data : {
				'uri' : uri,
				'funcId' : funcId,
				'method' : method,
				'status' : 0
			},
			success : function(result) {
				if (result.success) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					if(result.success) {
						window.top.location.href = ctx + "/auth/permission/add";
					}
	        	});
			}
		});
	},
//--------------------------- update funcResour -----------------------
	doUpdateFuncResour : function() {
		var uri = $('#uri').val();
		if(uri == null || uri == '') {
			$("#uri_label").html('uri 不能为空');
			return;
		}
		
		$.ajax({
			type : "post",
			async: false,
			url : ctx + "/auth/permission/update",
			dataType : "text",
			data : {
				'id' : $('#permission_id').val(),
				'uri' : $('#uri').val(),
				'method' : $("#method").val(),
				'funcId' : $("#funcId").val()
			},
			success : function(result) {
				if (result.success) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					window.top.location.href = ctx + "/auth/permission/index";
	        	});
			}
		});
	},
	reset : function() {
		$("#uri").val($('#permission_uri').val());
		$("#method").val($('#permission_method').val());
		$("#parentModel").val($('#permission_parentModel').val());
		$("#funcId").val($('#permission_funcId').val());
	}
}