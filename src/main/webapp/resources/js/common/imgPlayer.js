(function(P){
	var _this = null;
	_this = P.imgPlayer = {
		tpl : {
			imgTpl : null
		},
		data : {
			imgServer : null,
			imgList : [],
			imgMap : {},
			currImgWidth : 0,
			currImgHeight : 0,
			currImgIndex : 0,//默认从第一张开始,数组0开始计数
			pageNo : 0,
			pageSize : 15,
			totalPage : 0,
			totalCount : 0,
			maxIndex : 0,
			listHideDelay : 0//ms
		},
		init : function(){
			_this.tpl.imgTpl = juicer($('#img-tpl').html());
			_this.initEvent();
		},
		initEvent : function(){
			$('#img_list').on('click','.img-box',function(){
				var index = $(this).attr('data-index');
				_this.data.currImgIndex = index;
				_this.viewImage();
			});
			$('#curr_img_box').on('click','.prev',_this.prev);
			$('#curr_img_box').on('click','.next',_this.next);
			
			$('#img_list').on('click','.prev-page',_this.prevPage);
			$('#img_list').on('click','.next-page',_this.nextPage);
			
			$('#img_list').hover(
				function(){
					$(this).animate({'opacity' : 1},500);
				},
				function(){
					$(this).delay(3000).animate({'opacity' : 0},500);
				}
			);
//			listHideDelay
			
			$('#img_player_close').click(_this.close);
			$(window).resize(_this.resize);
		},
		play : function(options){
			//testInitData
			$('#img_player_mask').show();
			$('#img_player_container').show();
			if($('#img_player_container').is('hidden'))
				$('#img_player_container').show();
			
//			_this.data.imgServer = static_ctx + '/static/img/';
//			_this.data.imgList = ['Koala.jpg','Penguins.jpg','Tulips.jpg','Koala.jpg','Penguins.jpg','Tulips.jpg','Koala.jpg','Penguins.jpg','Tulips.jpg','Koala.jpg','Penguins.jpg','Tulips.jpg','Koala.jpg','Penguins.jpg','Tulips.jpg','Koala.jpg','Penguins.jpg','Tulips.jpg'];
//			options = {};
//			options.imgIndex = 1;
			
			/***
			 options = {
			 	imgServer : *,//img host
			 	imgList : *,//img array
			 	imgIndex : *,//selected img index of img array
			 	imgMd5 : *
			 };
			 */
			
			_this.data.imgServer = options.imgServer;
			_this.data.imgList = options.imgList;
			for(var index in  _this.data.imgList){
				_this.data.imgMap[ _this.data.imgList[index]] = index;
			}
			if(options){
				if(options.imgIndex){
					_this.data.currImgIndex = options.imgIndex;
				}else{
					_this.data.currImgIndex = parseInt(_this.data.imgMap[options.imgMd5]);
				}
			}
			
			
			$('#img_player_container').height(document.body.clientHeight - 100);//上下个50的间距,让图片和mask区分开
			
			_this.data.boxWidth = $('#curr_img_box').width();
			_this.data.boxHeight = $('#curr_img_box').height();
			
			
			
			_this.data.totalCount = _this.data.imgList.length;
			_this.data.totalPage = Math.floor(_this.data.totalCount / _this.data.pageSize) + 1;
			_this.data.maxIndex = _this.data.totalCount - 1;
			_this.viewImage();
		},
		preLoadImage : function(src, callback){
			var img = new Image();
			img.onload = function(){
				_this.data.currImgWidth = img.width;
				_this.data.currImgHeight = img.height;
				_this.resetImgPos();
				callback();
			};
			img.src = src;
		},
		prev : function(){
			if(_this.data.currImgIndex > 0){
				_this.data.currImgIndex--;
			}
			_this.viewImage();
		},
		next : function(){
			if(_this.data.currImgIndex < _this.data.maxIndex){
				_this.data.currImgIndex++;
			}
			_this.viewImage();
		},
		viewImage : function(){
			if(!_this.data.currImgIndex)
				_this.data.currImgIndex = 0;
			var src = _this.data.imgServer + _this.data.imgList[_this.data.currImgIndex];
			_this.preLoadImage(src, function(){
				$('#curr_img').attr('src', src);
				var $img = $('#img_list').find('.img-box').eq(_this.data.currImgIndex);
				$img.siblings('div').removeClass('current');
				$img.addClass('current');
				_this.data.pageNo = Math.ceil((_this.data.currImgIndex + 1) / _this.data.pageSize) - 1;//页码从0开始
				_this.loadImgList();
			});
		},
		prevPage : function(){
			if($(this).hasClass('disabled'))
				return;
			_this.data.pageNo--;
			_this.loadImgList();
		},
		nextPage : function(){
			if($(this).hasClass('disabled'))
				return;
			_this.data.pageNo++;
			_this.loadImgList();
		},
		loadImgList : function(){
			var currStartIndex = _this.data.pageNo * _this.data.pageSize;//当前页开始序号，从0开始 
			var currEndIndex = (_this.data.pageNo + 1) * _this.data.pageSize - 1;//当前页结束序号，<= totalCount
			if(currEndIndex >= _this.data.maxIndex)
				currEndIndex = _this.data.maxIndex;
			console.log(_this.data.totalPage);
			var renderData = {
				imgServer : _this.data.imgServer,
				list:_this.data.imgList,
				currIndex:_this.data.currImgIndex,
				showPageBtn : _this.data.totalPage > 1 ? true : false,
				currStartIndex : currStartIndex,
				currEndIndex : currEndIndex
			};
			
			$('#img_list').html(_this.tpl.imgTpl.render(renderData));
			if(_this.data.pageNo == 0){
				$('.prev-page').addClass('disabled');
			}else{
				$('.prev-page').removeClass('disabled');
			}
			if(_this.data.pageNo == (_this.data.totalPage - 1)){
				$('.next-page').addClass('disabled');
			}else{
				$('.next-page').removeClass('disabled');
			}
		},
		resize : function(){
			$('#img_player_container').height(document.body.clientHeight - 100);
			_this.data.boxWidth = $('#curr_img_box').width();
			_this.data.boxHeight = $('#curr_img_box').height();
			_this.resetImgPos();
		},
		resetImgPos : function(){
			var w = _this.data.currImgWidth;
			var h = _this.data.currImgHeight;
			var wRate = w/_this.data.boxWidth;
			var hRate = h/_this.data.boxHeight;
			var rate = wRate;
			if(hRate > wRate){
				rate = hRate; 
			}//确定缩放比例
			
			var imgWidth = w;
			var imgHeight = h;
			if(rate > 1){
				imgWidth = w/rate;
				imgHeight = h/rate;
			}
			
			var marginV = Math.floor((_this.data.boxWidth - imgWidth)/2);//竖直偏移
			var marginH = Math.floor((_this.data.boxHeight - imgHeight)/2);//横向偏移 
			
			$('#curr_img').css('width', imgWidth - 2);//-2为了调整因为添加border造成的宽度问题
			$('#curr_img').css('height', imgHeight - 2);
			$('#curr_img').css('margin', marginH + 'px ' + marginV + 'px');
		},
		close : function(){
			$('#img_player_mask').hide();
			$('#img_player_container').hide();
			$('#curr_img').attr('src', static_ctx + '/static/img/loading.gif');
			$('#img_list').empty();
		}
	};
}(latte));