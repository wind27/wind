(function(P){
	var _this = P.login = {
		init : function(){
			$("#loginUser").focus();

			$('.loginCode').find('a').click(function(){
				_this.reloadImage();
			});
			
			validate = $("#login_form").validate({
				rules : {
					loginUser : {
						required : true,
						maxlength : 20
					},
					loginPass : {
						required : true,
						maxlength : 20
					}
				},
				focusInvalid:false
			});
			
			$("#login_submit").click(_this.commit);
			$('body').on('keydown','#login_form',function(e){
				var event = window.event || e;
				if(event.keyCode == 13){
					_this.commit();
				}
			});
		},
		commit : function(){
			var loginUser = $("#loginUser").val();
			var loginPass = $("#loginPass").val();
			var validateCode = $("#validateCode").val();

			if(!loginUser){
				_this.defaultError("用户名不能为空");return false;
			}else if(!loginPass){
				_this.defaultError("密码不能为空");return false;
			}else if (!validateCode) {
				_this.defaultError("验证码不能为空");
				return false;
			}
			loginPass = hex_md5(loginPass+"#DsjW2014@xDf.AdMin.cn#");
			
			$('#login_submit').prop('disabled',true);
			
			$.ajax({
				contentType:"application/x-www-form-urlencoded; charset=UTF-8",
				url : ctx + "/login",
				type:"post",
				dataType : "text",
				data : {
					'loginUser' : loginUser,
					'loginPass' : loginPass,
					'validateCode' : validateCode
				},
				success : function(result) {
					$('#login_submit').prop('disabled',false);
					if(result.success) {
						window.location.href = ctx + "/index";
						return ;
					} else {
						if (result.code == 1) {
							_this.defaultError("用户名或密码错误");
						} else if (result.code == 2) {
							_this.defaultError("账户已暂停");
						} else if (result.code == 3) {
							_this.defaultError("账户未授权");
						} else if (result.code == 4){
							_this.defaultError("用户已经登录，请在15分钟后再进行登录！");
						}else if (result.code == 5){
							_this.defaultError("验证码错误，请重新输入！");
						}else if (result.code == 6){
							_this.defaultError("未知异常！");
						}
					}
					$("#validateCode").val('');
					_this.reloadImage();
				}
			});
		},
		defaultError : function(message){
			$(".loginHint").html(message);
		},
		reloadImage : function(){ 
			$("#random_code").attr("src", ctx+"/captchahtm?time=" + new Date()); 
		} 
	};
}(latte));
