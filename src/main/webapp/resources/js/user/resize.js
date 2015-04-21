var resizecontent = function(id, height, width) {
	if (height == null || typeof(height) == "undefined") {
		height = 600;
	}
	
	if (id == null || typeof(id) == "undefined") {
		id = "content";
	}	
	var contentHeight = $("#"+id).height() + 20;
//	var contentWidth = $("#" + id).width() +20;

	if(height != null && height > 0) {
		contentHeight = contentHeight > height?contentHeight:height;
	}
	
	var leftHeight = $("#left", parent.document).height();
	contentHeight = contentHeight > leftHeight?contentHeight:leftHeight;

//	if(width != null && width > 0) {
//		contentWidth = contentWidth > height?contentWidth:width;
//	}

//	var iframeHeight =$(window.parent.document).find("#testIframe").height();
//	var iframeWidth = $(window.parent.document).find("#testIframe").width();

    $(window.parent.document).find("#testIframe").height(contentHeight);
//    $(window.parent.document).find("#testIframe").width(contentWidth > iframeWidth ? contentWidth : iframeWidth);
};
/**
 * top.confirm 回调函数
 * @param fn
 */
function callbackConfirm(fn){
	eval(fn);
}

