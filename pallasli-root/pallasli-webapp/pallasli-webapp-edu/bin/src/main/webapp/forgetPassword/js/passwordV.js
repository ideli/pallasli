$(function(){
	var getPasswordStrength =function(H){
		var D=(H.length);
		if(D>5)D=5;
		var F=H.replace(/[0-9]/g,"");
		var G=(H.length-F.length);
		if(G>3)G=3;
		var A=H.replace(/\W/g,"");
		var C=(H.length-A.length);
		if(C>3)C=3;
		var I=(H.length-H.replace(/[A-Z]/g,"").length);
		if(I>3)I=3;
		var E=D==1?10:((D*10)-20)+(G*10)+(C*15)+(I*10);
		if(E<0)E=0;
		else if(E>100)E=100;
		return E;
	};
	var loadImg = function(i){
		var img = new Image();
		img.src = "/getpassword/images/p"+i+".jpg";
		if(!img.complete)img.onload = function() {};
	};
	for(var i=0; i<4;){loadImg(i++);} 
	loadImg.updateSrc=function(i){
		resetPassword.strength.attr("src","/getpassword/images/p"+i+".jpg");
	};
	var resetPassword = {
		strength:$("#strength"),
		open : function(m,o){
			this[o].html(m);
			this[o+"V"].show();
		},
		validatePassword : function(){
			var v = this.password.val();
			if(v.replace(/\s/g,"") === ""){
				this.open("请输入密码","passwordM");
				return false;
			}
			this.passwordMV.hide();
			return true;
		},
		validateConfirmPassword : function(){
			var v = this.confirmPassword.val();
			if(v.replace(/\s/g,"") === ""){
				this.open("请输入确认密码","confirmPasswordM");
				return false;
			}
			if(v!==this.password.val()){
				this.open("两次密码不一致","confirmPasswordM");
				return false;
			}
			this.confirmPasswordMV.hide();
			return true;
		},
		init:function(){
			var t = ['password','confirmPassword'],k,j,self=this;
			for(k in t){
				j=t[k];
				this[j]=$("#"+j);
				this[j+"M"]=$("#"+j+"M");
				this[j+"MV"]=$("#"+j+"MV");
			}	
			this.password.keyup(function(){
				var t = Math.floor(getPasswordStrength (this.value)/10);
				if(t<=0)t=0;
				else if(t<5)t=1;
				else if(t<8)t=2;
				else t=3;
				loadImg.updateSrc(t);
			}).blur(function(){
				self.validatePassword();
			});
			this.confirmPassword.blur(function(){
				self.validateConfirmPassword();
			});
		}
	};
	resetPassword.init();

	$("#btn_step1").click(function(){
		if(resetPassword.validatePassword()===true&&resetPassword.validateConfirmPassword()===true){
			dg_block_layer.open("300","100","50","50","","/getpassword/codeValidate.html");
			$.ajax({
			   type: "POST",
			   url: "/findpassword/updatePassword.action?fdp.password="+resetPassword.password.val()+"&fdp.code="+$("#code").val(),
			   success: function(d){
			       dg_block_layer.close();
				   if(d=="0"){
				       findpassword.open("密码重置失败","passwordM");
				   }else if(d=="1"){
					   window.location.href="/getpassword/resetSuccess.html";
				   }else{
					   resetPassword.open("系统异常，请与管理员联系","passwordM");
				   }
			   },
			   error : function(){
			       dg_block_layer.close();
			       resetPassword.open("系统异常，请与管理员联系","passwordM");
			   }
			});
		}
	});
});