latte.func = {
//--------------------------- list func -----------------------
	getFunc : function(type, pageNo) {
		var parentCode = null;
		var parentName = null;
		var domain = $('#domain').val();
		var status = $("#status").val();
		if(!status) {
			status = 0;
		}
		
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
			$("#parentCode").val(parentCode);
			return ;
		}
		
		if(type== 1 && parentName == "请选择") {
			$("#parentCode").val(4);
			$("#parentModel").val((type-1)+"级:"+"权限");
		} else if(type == 2 && parentName == "请选择") {
			parentName = $("#firstPid").find("option:selected").text();
			parentCode = $("#firstPid").find("option:selected").val();
			$("#parentCode").val(parentCode);
			$("#parentModel").val((type-1)+"级:"+parentName);
		} else {
			$("#parentCode").val(parentCode);
			$("#parentCode").val(parentCode);
			$("#parentModel").val((type)+"级:"+parentName);
		}
		
		$.ajax({
			type : "get",
			async: false,
			url : ctx + "/auth/func/list",
			dataType : "text",
			data : {
				'start' : pageNo,
				'id' : parentCode,
				'domain' : domain,
				'status' : status
			},
			success: function(result, textStatus) {
				var funcInfo = null;
				if(type == 0) {
					funcInfo = '<option value="4" selected="selected">请选择</option>';
					$("#level").text('1');
					
				} else if(type == 1) {
					funcInfo = '<option value="-2" selected="selected">请选择</option>';
					$("#level").text('2');
				}
				$("#currentPage").val(result.currentPage);
	    	    $("#totalPage").val(result.totalPage);
	    	    $("#totalCount").val(result.totalCount);
	            $("#exportData").empty();
				if (result.funcList.length == 0) {
	            	$("#exportData").append("<tr style='line-height:30px;background:#FFEBE5;padding-left:12px;font-size: 12px;'><td align='left' colspan='12'>获得0条记录!</td></tr>");
	            } else {
					var funcTBody = '';
					for(var i=0; i<result.funcList.length; i++) {
						funcInfo = funcInfo + "<option value='"+result.funcList[i].id+"'>"+result.funcList[i].name+"</option>";
						var statsName;
						var operStats;
						var operStatsName;
						if(result.funcList[i].stats==0) {
							operStats = 1;
							statsName = '启用';
							operStatsName = '停用';
						} else {
							operStats = 0;
							statsName = '停用';
							operStatsName = '启用';
						}
						
						
						funcTBody += "<tr>";
						funcTBody += "<td>"+result.funcList[i].id+"</td>";
						funcTBody += "<td>"+result.funcList[i].name+"</td>";
						funcTBody += "<td>"+statsName+"</td>";
						if(result.funcList[i].remark == null) {
							funcTBody += "<td></td>";
						} else {
							funcTBody += "<td>"+result.funcList[i].remark+"</td>";
						}
						funcTBody += "<td>"+result.funcList[i].parentCode+"</td>";
						if(result.funcList[i]._order == null) {
							funcTBody += "<td></td>";
						} else {
							funcTBody += "<td>"+result.funcList[i]._order+"</td>";
						}
						funcTBody += "<td><a href='javascript:latte.func.enableOrDisable("+result.funcList[i].id+", "+operStats+", "+type+");'>"+operStatsName+"</a>&nbsp;&nbsp;";
						funcTBody += "<a href='"+ctx + "/auth/func/edit?id=" +result.funcList[i].id+"&type="+type+"'>编辑</a></td>";
						funcTBody += "</tr>";
					}
					$("#exportData").append(funcTBody);
	            	latte.func.page(type);
				}
				
				if(type == "0") {
					$("#firstPid").html(funcInfo);
				} else if(type == "1") {
					$("#secondPid").html(funcInfo);
				} else if(type == "2") {
					$("#thirdPid").html(funcInfo);
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
	                latte.func.getFunc(type, pageNo);
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
			latte.func.getFunc(0);
		} else {
			latte.func.getFunc(type);
		}
	},
	enableOrDisable : function(id, stats, type) {
		$.ajax({
			type : "post",
			async: false,
			url : ctx + "/auth/func/enableOrDisable",
			dataType : "text",
			data : {
				'id' : id,
				'stats' : stats,
				'type' : type
			},
			success : function(result) {
				if (result.success) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					latte.func.getFunc(type);
	        	});
			}
		});
	},
//--------------------------- add func -----------------------
	nameBlurEvent : function() {
		var name = $("#name").val();
		if(name.length>20 == null || name.length <2) {
			$("#name_label").html('&nbsp;&nbsp;&nbsp;&nbsp;名称必须为2~20字符');
			return ;
		}
		$("#name_label").html('');
	},
	_orderBlurEvent : function() {
		var _order = $("#_order").val();
		var numberReg = /^[0-9]{1,2}$/; 
		if(_order!="" && numberReg.test(_order) == false) {
			$("#_order_label").html("排序必须为1~2位数字");
			return ;
		}
		$("#_order_label").html('');
	},
	//添加新模块
	addFunc : function() {
		var flag = true;
		var name = $("#name").val();
		var parentCode = $("#parentCode").val();
		var domain = $('#domain').val();
		var _order = $('#_order').val();
		var stats = 0;
		
		
		if(domain == null || domain=="") {
			$("#domainValue_label").html('&nbsp;&nbsp;&nbsp;&nbsp;域不能为空');
			return ;
		}
		if(name.length>20 == null || name.length <2) {
			$("#name_label").html('&nbsp;&nbsp;&nbsp;&nbsp;名称必须为2~20字符');
			return ;
		}
		var numberReg = /^[0-9]{1,2}$/; 
		if(_order!="" && numberReg.test(_order) == false) {
			$("#_order_label").html("排序必须为1~2位数字");
			return ;
		}
		
		if(flag == false) {
			return;
		}
		
		$.ajax({
			type : "post",
			async: false,
			url : ctx + "/auth/func/add",
			dataType : "text",
			data : {
				'name' : name,
				'parentCode' : parentCode,
				'domain' : domain,
				'_order' : _order,
				'stats' : stats
			},
			success : function(result) {
				if (result.success) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					if(result.success) {
//						window.top.location.href = ctx + "/func/addFunc";
					}
	        	});
			}
		});
	},
//--------------------------- update func -----------------------
	//更新模块
	doUpdateFunc : function() {
		var flag = true;
		var id = $("#id").val();
		var name = $("#name").val();
		var parentCode = $("#parentCode").val();
		var domain = $('#domain').val();
		var _order = $('#_order').val();
		var stats = 0;
		var remark = $('#remark').val();
		
		
		if(domain == null || domain=="") {
			$("#domainValue_label").html('&nbsp;&nbsp;&nbsp;&nbsp;域不能为空');
			return ;
		}
		if(name == null || name == "") {
			$("#name_label").html('名称不能为空');
			flag = false;
		}
		
		var numberReg = /^[0-9]+$/; 
		if(_order!="" && numberReg.test(_order) == false) {
			$("#_order_label").html("排序必须为数字");
			flag = false;
		}
		
		if(flag == false) {
			return;
		}
		
		$.ajax({
			type : "post",
			async: false,
			url : ctx + "/auth/func/update",
			dataType : "text",
			data : {
				'id' : id,
				'code' : id,
				'name' : name,
				'parentCode' : parentCode,
				'domain' : domain,
				'_order' : _order,
				'remark' : remark,
				'stats' : stats
			},
			success : function(result) {
				if (result.success) {
					msg = "操作成功";
				} else {
					msg = "操作失败";
				}
				util.dialog.timerDialog(0.5,msg,function(){
					if(result.success) {
						window.top.location.href = ctx + "/auth/func/index";
					}
	        	});
			}
		});
	},

	reset : function() {
		$("#id").val($('#func_id').val());
		$("#name").val($('#func_name').val());
		$("#parentCode").val($('#func_parentCode').val());
		$("#parentModel").val($('#func_parentModel').val());
		$("#_order").val($('#func_order').val());
	}
}