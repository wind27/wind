
var modify = function() {
	var url = "/modifyPassword";
	top.openWindow(url,"设置",600,400);
};
//生成左侧功能菜单。
var selectMenuCode;
var getMum = function (obj, name, index, currindex, secMenuNumber, secMenuUrl) {
	selectMenuCode = null;
	$(".nav_a").addClass("nav_b");
	$(".nav_a").removeClass("nav_a");
	$("#nav_b" + obj).removeClass("nav_b");
	$("#nav_b" + obj).addClass("nav_a");
	$("#breadcrumb").text("您的位置：" + name);
	$("#testIframe").attr("src", "");

	$("#left").removeClass();
	if (obj == 49 || obj == 59 || obj == 21) {
		$("#left").addClass("left1");
		} else {
		$("#left").addClass("left");
	}
	$("#secMenuId").val(obj);//设置当前一级菜单的主键
	
	$.ajax({
		type : "POST",
		url : ctx+"/portal/getSecMum/" + obj,
		dataType : 'text',
		success : function(result) {
			$("#left").empty();
			var str = eval('(' + result + ')');
			str = JSON.parse(str);
			var content = "";
			var num = 0;
			for ( var o in str) {
				content += "<div id='secMum"+ str[o].code  + "' class='subnav' onclick=\"javascript:getSecMum(" + str[o].code + ",'" + str[o].key + "','" + str[o].uri + "', '"+str[o].name + "', "+(num++) +");\">"
//				            + "<div class='subnav_b'><img src='" + portal + "/images/nav_b_02.gif' width='9' height='9' />"
				            + "</div>"
//						    + "<a href='javascript:void(0);' >" + str[o].name +"</a></div>";
			}
			$("#left").html(content);
			$("#testIframe").height($("#left").height);
			if (str != null && str.length > 0) {
				if(secMenuNumber != undefined && typeof(secMenuNumber) != "undefined"){
					getSecMum(str[secMenuNumber].code, str[secMenuNumber].keyName, secMenuUrl, str[secMenuNumber].name);
				}else{
					getSecMum(str[0].code, str[0].keyName, str[0].uri, str[0].name, 0);
				}
			}
		}
	});
};

var process = function (obj, key, uri) {
	$("#leftsearch").addClass("left_con");
};

// 生成左边条件框。
var getSecMum = function (obj, key, uri, name, order) {
	if (this.selectMenuCode != obj) {
		var breadcrumb = $("#breadcrumb").text();
		if (breadcrumb.indexOf(">") > -1) {
			breadcrumb = breadcrumb.substring(0, breadcrumb.indexOf(">"));
		}
		
		$("#secMenuNumber").val(order);//设置当前二级菜单的序号
		$("#breadcrumb").text(breadcrumb + " > " + name);

		this.selectMenuCode = obj;
		var search = $("#leftsearch");
		if (search) {
			search.remove();
		}

		gotoUrl(obj, uri, name);
		
		searchPage(obj, key, uri, 1);
		process(obj, key, uri);
	}
};

// 生成二级菜单。
var gotoUrl = function(obj, uri, name) {
	// 当前if修改左侧边框样式问题
	$(".left_tit").children("div").children("img").attr("src", portal + "/images/nav_b_02.gif");
	$(".left_tit").removeClass().addClass("subnav");
	$("#secMum" + obj).removeClass().addClass("left_tit");
	$("#secMum" + obj).children("div").children("img").attr("src",portal + "/images/nav_b_021.gif");
};
//------------
var searchPage = function(code, key, uri, startPage) {
	$.ajax({
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		url : ctx+"/controlType/getControlType",
		dataType : "text",
		type : "post",
		data : {
			"key" : key,
			"uri" : uri,
			"start":startPage
		},
		success : function(result) {
			if (result != null && result.indexOf("div") != -1) {
				$("#secMum" + code).children("div").children("img").attr("src",portal + "/images/nav_b_01.gif");
				$("#secMum" + code).after(result);
				$("#leftsearch").addClass("left_con");
			} else {
				$("#testIframe").attr("src", ctx + uri);
			}
		}
	});
};