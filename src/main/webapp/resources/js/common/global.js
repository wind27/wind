var latte = {
	quiz : {//题库管理
		upload : {},//试卷上传
		noeval : {},//待处理试卷
		paperEvaled : {},//已审核试卷
		paper : {},//试卷列表(待处理已审核整合)
		questionVf : {},//已审核题目
		questionErr : {},//出错题目
		statistics : {},//数据统计
		qust:{search:{}},
		simul : {
			common : {}
		}//已审核题目
	},
	knowledge : { //知识点管理
		module : {}
	},
	tmaterial : { //教材体系管理
		grade : {},
		directory: {},//分册管理
		relation:{}//语文关联
	},
	teacher:{},
	event : {
		create : {},// 创建活动
		list : {}	// 活动管理
	},
	board:{
		clip :{}
	},
	resource : {//资源库管理
		simul : {
			upload : {}
		},//同步习题
		editcommon : {},//编辑用公共组件
		clip:{},
		courseware:{},
		paper:{},
		material:{},
		resource:{},
		common:{},
		add :{},
		update :{},
		search:{
			clip:{},
			courseware:{},
			testPaper:{},
			material:{},
			resource:{}
		},
		pickclip : {}//挑选素材
	},
	customer : {
		school : {
			add : {},
			list : {}
		},
		region : {
			add : {},
			list : {}
		}
	},
	yonghu : {
		teacher : {},
		student : {},
		parent : {}
	},
	privates: {
		school: {

		},
		director: {

		}
	},
	xk:{
		clip:{},
		courseware:{},
		paper:{},
		material:{},
		resource:{},
		common:{},
		search:{
			clip:{},
			courseware:{},
			testPaper:{},
			material:{},
			resource:{}
		}
	},
	videocourse:{},//视频课程
	fileType:{
		'PPT' : '0',
		'PPTX' : '0',
		'DOC' : '1',
		'DOCX' : '1',
		'PDF' : '2',
		'PNG' : '3',
		'JPEG' : '3',
		'JPG' : '3',
		'BMP' : '3',
		'GIF' : '3',		
		'WMV' : '4',
		'AVI' : '4',
		'RM' : '4',
		'RMVB' : '4',
		'DAT' : '4',
		'ASF' : '4',
		'RAM' : '4',
		'MPG' : '4',
		'MPEG' : '4',
		'3GP' : '4',
		'MOV' : '4',
		'MP4' : '4',
		'M4V' : '4',
		'DVIX' : '4',
		'DV' : '4',
		'MKV' : '4',
		'FLV' : '4',
		'VOB' : '4',
		'QT' : '4',
		'CPK' : '4',
		/*'FLI' : '4',
		'FLC' : '4',
		'MOD' : '4',
		'SWF' : '4',
		'FLA' : '4',*/ 
		'MP3' : '5',
		'WAV' : '5',
		/*'WMA' : '5',*/
		'TXT' : '6',
		'SWF' : '7'
	},
	formatParent:{
		'PDF':'PDF',
		'PPT' : 'PPT',
		'WORD' : 'WORD',
		'PIC' : '图片',	
		'AUDIO' : '音频',
		'VIDEO' : '视频',
		'TXT' : '文本',
		'SWF' : 'FLASH'
	},
	paperType:[],
	//{
//		'-1':'不限', 
//		0:'高考真题',
//		1:'高考模拟', 
//		2:'期中考试',
//		3:'期末考试', 
//		4:'会考卷',
//		5:'月考卷',
//		6:'同步测试',
//		7:'单元测试', 
//		8:'竞赛题', 
//		9:'中考真题', 
//		10:'中考模拟', 
//		11:'其他' 
	//},
	tGrade:{
		1:'一年级',
		2:'二年级',
		3:'三年级',
		4:'四年级',
		5:'五年级',
		6:'六年级',
		7:'七年级',
		8:'八年级',
		9:'九年级',
		10:'一年级',
		11:'二年级',
		12:'三年级'
	},
	tArea:{},
	appFormat:{
		"0": "PPT",
		"1": "WORD",
		"2": "PDF",
		"3": "PIC",
		"4": "VIDEO",
		"5": "AUDIO",
		"6": "TXT",
		'7': 'SWF'
	},
	audience:{
		"0": "教师",
		"1": "学生",
		"2": "教师，学生"
	},
	dict : {
		difficulty:{
     		1 : '易',
     		2 : '中',
     		3 : '难',
     		4 : '极难'
     	},
     	mastery : {
     		1 : '基本',
     		2 : '普通',
     		3 : '熟练',
     		4 : '精通'
     	}
	},
	addParams:function(){
		if(arguments.length){
			var count = 0;
			for(var i = 0 ; i<arguments.length;i++){
				count+=arguments[i];
			}
			return count;
		}else{
			return 0;
		}
	},
	errorMap : {},//保存来自jquery.validate的信息
	ajaxError : function(XMLHttpRequest) {
		var errorCode =  XMLHttpRequest.status;
		var flag = true;
        if(errorCode){
            switch (errorCode) {
	            case 401:
	                window.location.href= ctx + "/portal/error?errorCode=401";
	                break;
	            case 403:
	                util.dialog.errorDialog("没有权限");
	                break;
	            default:
	                break;
            }
        }
        return flag;
	},
	checkTel:function ts(tel){
		  var telPattern1=/^1{1}[1-9]{1}\d{9}$/;
		  var telPattern2=/\d{3,4}-\d{7,8}-\d{1,4}$/;
		  var telPattern3=/\d{3,4}-\d{7,8}$/;
		  if(tel){
		    if(!telPattern1.test(tel) && !telPattern2.test(tel)&& !telPattern3.test(tel)){
		      return false;
		    }
		  }
		  return true;
	}
};

//$(document).ready(function(){
//    var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;
//    if(!isChrome) {
//       util.dialog.lockDialog("<img src='/static/images/chrome.png' style='margin:-8px 10px 0 -10px'> <a href='http://www.google.cn/intl/zh-CN/chrome/browser/' style='vertical-align:top'>请下载Chrome浏览器进行浏览</a>");
//    }
//});


$.ajaxSetup({
    url: "/",
    global: true,
    cache: false,
    converters: { "text json": function (jsonString) {
//        var res = JSON.parseWithDate(jsonString);
//        if (res && res.hasOwnProperty("d"))
//            res = res.d;
        return jsonString;
    } },
    dataFilter:function(data,type) {
    	if(!data)
    		return;
        var errorCode =  data.status;
        if(errorCode){
            switch (errorCode) {
                case 401:
                    window.location.href= ctx + "/portal/error?errorCode=401";
                    break;
                case 403:
                    util.dialog.errorDialog("没有权限");
                    break;
                default:
                    break;
            }
        }

        if (type == 'html' || type == 'script') {
            return data;
        } else {
            return eval('(' + data + ')');
        }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
        var errorCode =  XMLHttpRequest.status;
        if(errorCode){
            switch (errorCode) {
                case 401:
                	window.location.href= ctx + "/portal/error?errorCode=401";
//                    util.dialog.infoDialog("登陆超时", function() {window.location.href= "/";});
                    break;
                case 403:
                    util.dialog.errorDialog("没有权限");
                    break;
                default:
                    break;
            }
        }

//        console.log(XMLHttpRequest);
//        console.log(textStatus);
//        console.log(errorThrown);
//        util.dialog.errorDialog("服务器异常，请稍后再试!");
    }
});

var util = {
	ajax : function(options){
		options.error = function(data){
			var errorCode =  data.status;
			if(errorCode){
				switch (errorCode) {
				case 401:
                    window.location.href= ctx;
                    break;
                case 403:
                    util.dialog.errorDialog("没有权限");
                    break;
                default:
                    break;
				}
			}
		};
		$.ajax(options);
	},
	checkStatus : function(error){
		var errorCode = error.status;
		if(errorCode){
			switch (errorCode) {
			case 401:
                window.location.href= ctx;
                break;
            case 403:
                util.dialog.errorDialog("没有权限");
                break;
            default:
            	util.dialog.errorDialog(error.message);
                break;
			}
		}else{
			if(error.message){
				util.dialog.errorDialog(error.message);
			}
		}
	},
	date : {
		format3 : function(time){
			/** 格式化时间 */
		    var date = new Date(parseInt(time));
		    var year = date.getFullYear();
		    var month = date.getMonth() + 1;
		    var day = date.getDate();
		    var hour = date.getHours();
		    var minute = date.getMinutes();
		    var second = date.getSeconds();

		    month = ((month < 10) ? '0' : '') + month;
		    day = ((day < 10) ? '0' : '') + day;
		    hour = ((hour < 10) ? '0' : '') + hour;
		    minute = ((minute < 10) ? '0' : '') + minute;
		    second = ((second < 10) ? '0' : '') + second;

		    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
		},
		format2 : function(time){
			/** 格式化时间 */
		    var date = new Date(parseInt(time));
		    var year = date.getFullYear();
		    var month = date.getMonth() + 1;
		    var day = date.getDate();
		    var hour = date.getHours();
		    var minute = date.getMinutes();
		    var second = date.getSeconds();

		    month = ((month < 10) ? '0' : '') + month;
		    day = ((day < 10) ? '0' : '') + day;
		    hour = ((hour < 10) ? '0' : '') + hour;
		    minute = ((minute < 10) ? '0' : '') + minute;
		    second = ((second < 10) ? '0' : '') + second;

		    return year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
		},
		getDate1 : function(time){//截取日期
			if(!isNaN(time)){
				time = util.date.format(time);
			}
			var index =  time.indexOf(' ');
			if(index != -1)
				return time.substr(0, index);
			return time;
		},
        format : function(time, length){
            //length 1-3,对应时分秒3个参数
            /** 格式化时间 */
            var date = new Date(parseInt(time));
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();

            month = ((month < 10) ? '0' : '') + month;
            day = ((day < 10) ? '0' : '') + day;
            hour = ((hour < 10) ? '0' : '') + hour;
            minute = ((minute < 10) ? '0' : '') + minute;
            second = ((second < 10) ? '0' : '') + second;
            var str = hour;
            if(length && length >0 && length <4){
                if(length >= 2){
                    str += ':' + minute;
                }
                if(length == 3){
                    str += ':' + second;
                }
            }

            return year + '-' + month + '-' + day + ' ' + str;
        },
        getDate : function(time){//截取日期
            if(!isNaN(time)){
                time = util.date.format(time);
            }
            var index =  time.indexOf(' ');
            if(index != -1)
                return time.substr(0, index);
            return time;
        }
	},
	dict:{
		src:{
			//0:"教师",
			//1:"平台",
			//2:"用户"
			0:"教师",
			1:"教研人员"
		},
		phase:{
			0:'新授课',
			1:'复习课'
		},
		checkStatus:{
			0:'通过',
			1:'拒绝',
			2:'待审核',
			3:'审核中'
		},
		publishStatus:{
			0:'启用',
			1:'停用'
			
		},
		term:{
			0:'上学期',
			1:'下学期'
			
		}
	},
	
	getSeriesName:function(series){
		if(series!=null){
			return series.name;
		}else{
			return "";
		}
	},
	topicNames:function(topics){
		if(topics!=null){
			var topicNames="";
			for(var i=0;i<topics.size;i++){
				var topic=topics[i];
				topicNames+=topic.name+",";
			}
			return topicNames.substring(0, topicNames.lastIndexOf(","));
		}else{
			return "";
		}
	},
	src : function(src){
		return util.dict.src[src];
	},
	phase : function(phase){
		if(phase!=null){
			return util.dict.phase[phase];
		}else{
			return "";
		}
	},
	commentScore : function(score){
		var scoreInt = parseInt(score);
		var html='';
		for(var i=1;i<=5;i++){
			if(i<=scoreInt)
				html+='<em class="fullStar">★</em>';
			else{
				html+='<em>☆</em>';
			}
		}
		return html;
	},
	dialog : {
		messageDialog : function(message){
			art.dialog({
				lock:true,
				width:240,
				height:120,
		        id : 'msg_dialog',
		        title : '信息',
		        content : message,
		        okVal : '确认',
		        ok : function() {
		        }
		    });
		},
		messageAndRelocation : function(message,url){
			art.dialog({
				lock:true,
				width:240,
				height:120,
		        id : 'msg_dialog',
		        title : '信息',
		        content : message,
		        okVal : '确认',
		        ok : function() {
		        	window.location.href = url;
		        },
		        init: function() {
		        	$(this.DOM.close[0]).remove();
                },
                esc: false
		    });
		},
		errorDialog : function(message){
			if(!message)
				message = '操作不成功';
			art.dialog({
				lock:true,
				width:240,
				height:120,
		        id : 'error_dialog',
		        title : '信息',
		        content : message,
		        okVal : '确认',
		        ok : function() {}
		    });
		},
		errorMapDialog : function(){
			var errorMap = latte.errorMap;
			var content = '<div>';
			for(var index in errorMap){
				content += '<p style="text-align:left">'+errorMap[index]+'</p>';
			}
			content += '</div>';
			art.dialog({
				lock:true,
				width:320,
		        id : 'error_map_dialog',
		        title : '错误信息',
		        content : content,
		        okVal : '确认',
		        ok : function() {}
		    });
		},
		infoDialog : function(message,successCallback){//只有确定按钮的弹窗
			if(!message)
				message = '操作不成功';
			if(!successCallback){
				successCallback = function(){};
			}
			art.dialog({
				lock:true,
				width:300,
				height:100,
		        id : 'info_dialog',
		        title : '信息',
		        content : message,
		        okVal : '确认',
		        ok : successCallback
		    });
		},
		defaultDialog : function(content,successCallback,cancelCallback,title){
			if(!title)
				title = "信息";
			if(!successCallback){
				successCallback = function(){};
			}
			if(!cancelCallback){
				cancelCallback = function(){};
			}
			art.dialog({
				lock:true,
     			width:300,
     			height:120,
     	        id : 'default_dialog',
     	        title : title,
     	        content : content,
     	        okVal : '确认',
     	        ok : successCallback,
     	        cancelVal : '取消',
     	        cancel : cancelCallback
			});
		},
		timerDialog : function(time,content,callback,title){
			if(!title)
                title = "信息";
            var dialog = art.dialog({
                width:'auto',
                height:100,
                id : 'timer_dialog',
                title : title,
                content : content
            });
            setTimeout(function(){
        		dialog.close();
        		callback();
    		},time*1000);
		},
		timerTipsDialog : function(time,content,successCallback,cancelCallback,title){
			if(!title)
                title = "信息";
            art.dialog({
                width:200,
                height:100,
                id : 'timer_dialog',
                title : title,
                content : content,
                time:time,
                okVal : '确认',
                ok : successCallback,
                cancelVal : '取消',
                cancel : cancelCallback
            });
		},
        timerTipsDialogWithId : function(id,time,content,successCallback,cancelCallback,title){
            if(!title)
                title = "信息";
            art.dialog({
                width:200,
                height:100,
                id : id,
                title : title,
                content : content,
                time:time,
                okVal : '确认',
                ok : successCallback,
                cancelVal : '取消',
                cancel : cancelCallback
            });
        },
        lockDialog : function(message){
            if(!message)
                message = '禁止操作';
            art.dialog({
                lock:true,
                width:340,
                height:120,
                id : 'lock_dialog',
                title : '提示',
                content : message,
                init: function() {
                    $(this.DOM.close[0]).remove();
                },
                esc: false
            });
        }
	},
	getTail : function(fileName) {
		var strArr = fileName.split('.');
		var length = strArr.length;
		if(length>1){}
		return strArr[length-1];
	},
	getFormatParent:function(suffix){
		var name = latte.fileType[suffix.toUpperCase()];
		return name;
	},
	getFormatParentBySuffix:function(suffix){
	},
	getFormatChinese:function(code){
		return latte.formatParent[code];
	},
	getPaperType:function(code){
		for(var i=0;i<latte.paperType.length;i++){
			var pt=latte.paperType[i];
			if(pt.id==code){
				return pt.name;
			}
		}
//		return latte.paperType[code];
	},
	appFormatSearch:function(code){
		if(code!=-1){
			return latte.appFormat[code];
		}else{
			return "-1";
		}
	},
	audienceCode:function(code){
		return latte.audience[code];
	},
	gradeCode:function(code){
		return latte.tGrade[code];
	},
	areaCode:function(code){
		for(var i=0;i<latte.tArea.length;i++){
			var area=latte.tArea[i];
			if(area.dictCode==code){
				return area.dictName;
			}
		}
		
		//return latte.tArea[code];
	},
	termCode : function(code){
		return util.dict.term[code];
	},
	formatDate:function(submitTime){
		if(submitTime!=null){
			var time=submitTime.time;
			if(time!=null){
				return util.date.format(time);
			}else{
				return "";
			}
		}else{
			return "";
		}
	},
    formatTime:function(submitTime, length){
        if(submitTime!=null){
            var time=submitTime.time;
            if(time!=null){
                return util.date.format(time, length);
            }else{
                return "";
            }
        }else{
            return "";
        }

    },
	getCheckStatus:function(code){
		return util.dict.checkStatus[code];
	},
	getPublishStatus:function(code){
		return util.dict.publishStatus[code];
	},
	formatIndex : function(index){
		return parseInt(index) + 1;
	},
	isEmptyObject : function(obj){
	 
		    for(var key in obj){
		        return false;//
		    }
		    return true;
		 
	},
	fileSizeFormat:function(fileS){  
	       var fileSizeString = "";
	       if (fileS < 1024) {
	           fileSizeString = fileS+" B";
	       } else if (fileS < 1048576) {
	           fileSizeString = new Number(fileS / 1024).toFixed(1)  + " KB";
	       } else if (fileS < 1073741824) {
	           fileSizeString =  new Number(fileS / 1048576).toFixed(1) + " MB";
	       } else {
	           fileSizeString =  new Number(fileS/ 1073741824).toFixed(1) +" GB";
	       }
	       return fileSizeString;
	},
	getStaticCtx : function(){
		return ctx+'/static';
	},
	getCtx : function(){
		return ctx;
	},
	clearHtmlTag : function(str){
		return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
	},
	getNfsUrl : function(){
		return nfs_url;
	}, 
	getNfsUrlAudio : function(){
		return nfs_url_audio;
	},
	getNfsUrlVideo : function(){
		return nfs_url_video;
	},
	getNfsUrlDoc : function(){
		return nfs_url_doc;
	},
	convertShowText : function(code){
		return code=='-1'?'不限':code;
	},
	escapeCode : function(code){
		return code.replace(/[^0-9a-zA-Z\u4E00-\u9FA5\\(\s*)|(\s*$)]/g, '');
	},
	escapeSpace : function(code){
		return code.replace(/\s+/g,'');
	},
	jQstringify : function jQstringify( obj ) {
		var arr = [];
		$.each( obj, function( key, val ) {
		var next = key + ": ";
		next += $.isPlainObject( val ) ? printObj( val ) : val;
		arr.push( next );
		});
		return "{ " + arr.join( ", " ) + " }";
	},
	formatDifficulty : function(difficulty){
    	var result = latte.dict.difficulty[difficulty];
    	if(!result) result = '未设置';
    	return result;
    },
    formatDifficulty : function(difficulty){
    	var result = latte.dict.difficulty[difficulty];
    	if(!result) result = '未设置';
    	return result;
    },
    getCN : function(num){
		var str = '';
		switch (num) {
		case 1:
			str = '一';
			break;
		case 2:
			str = '二';
			break;
		case 3:
			str = '三';
			break;
		case 4:
			str = '四';
			break;
		case 5:
			str = '五';
			break;
		case 6:
			str = '六';
			break;
		case 7:
			str = '七';
			break;
		case 8:
			str = '八';
			break;
		case 9:
			str = '九';
			break;
		default:
			break;
		}
		return str;
	},
    getCNNumber : function(orgIndex){//orgIndex从零计数,当前仅判断100以内章
		var number = (parseInt(orgIndex) + 1) + '';
		var length = number.length;
		var cnnumber = '';
		var num = number.charAt(length-1);
		cnnumber = util.getCN(parseInt(num));
		if(length > 1){
			num = number.charAt(length-2);
			cnnumber = util.getCN(parseInt(num)) + '十' + cnnumber;
		}
		return cnnumber;
	},
	showErrors : function(errorMap){
		for(var name in errorMap){
    		var $obj =  $('input[name=\''+name+'\']').last();
    		if($obj.length == 0)
    			$obj = $('select[name=\''+name+'\']').last();
    		if($obj.length == 0)
    			$obj = $('textarea[name=\''+name+'\']').last();
    		
    		
    		var $parentDiv = $obj.parent('div');
    		if($parentDiv.length == 0)
    			$parentDiv = $obj.parents('div.info').first();
    		
    		$obj.nextAll('p').hide();
    		$parentDiv.nextAll('.error').remove();
    		$parentDiv.after('<label class="error" >'+latte.errorMap[name]+'</label>');
    		
    		$obj.one('focus',function(){
    			var $this = $(this);
    			$this.nextAll('p').show();
    			var $parentDiv = $this.parent('div');
        		if($parentDiv.length == 0)
        			$parentDiv = $this.parents('div.info').first();
        		$parentDiv.nextAll('.error').remove();
    		});
    	}
	},
	jsonp : {}
};

/** 修改juicer的默认配置 */
juicer.set({
    'tag::interpolateOpen' : '?{',
    'tag::noneencodeOpen' : '??{'
});
juicer.register('formatscore',util.commentScore);
juicer.register('srcCode',util.src);
juicer.register('getSeriesName',util.getSeriesName);
juicer.register('topicNames',util.topicNames);
juicer.register('phaseCode',util.phase);
juicer.register('getFormat',util.getFormatParentBySuffix);
juicer.register('timeFormat',util.formatDate);
juicer.register('fileTypeChinese',util.getFormatChinese);
juicer.register('getCheckStatus',util.getCheckStatus);
juicer.register('getPublishStatus',util.getPublishStatus);
juicer.register('getPaperType',util.getPaperType);
juicer.register('dateFormat',util.date.format);
juicer.register('fileSizeFormat',util.fileSizeFormat);
juicer.register('getStaticCtx',util.getStaticCtx);
juicer.register('getCtx',util.getCtx);
juicer.register('getNfsUrl',util.getNfsUrl);
juicer.register('audienceCode',util.audienceCode);
juicer.register('gradeCode',util.gradeCode);
juicer.register('areaCode',util.areaCode);
juicer.register('termCode',util.termCode);
juicer.register('formatText',util.convertShowText);
juicer.register('formatDifficulty', util.formatDifficulty);
juicer.register('formatMastery', util.formatMastery);
juicer.register('addParams', latte.addParams);
juicer.register('formatIndex',util.formatIndex);
juicer.register('getCNNumber',util.getCNNumber);
juicer.register('getDateTime',util.formatTime);