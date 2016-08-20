(function($){

	$.extend( $.validator, { 
	messages: {
			required: "<span style='color:red'>必输项</span>",
			email: "<span style='color:red'>邮箱格式无效</span>",
			url: "<span style='color:red'>URL无效</span>",
			date: "<span style='color:red'>日期格式无效</span>",
			number: "<span style='color:red'>无效数值</span>",
			digits: "<span style='color:red'>必须是数字</span>",
			creditcard: "<span style='color:red'>银行卡号无效</span>",
			equalTo: $.validator.format( "<span style='color:red'>需与{1}保持一致</span>"),
			maxlength: $.validator.format( "<span style='color:red'>长度不能超过 {0}个字符</span>" ),
			minlength: $.validator.format( "<span style='color:red'>长度不能少于 {0}个字符</span>" ),
			rangelength: $.validator.format( "<span style='color:red'>数值长度应在{0}和{1}之间</span>" ),
			range: $.validator.format( "<span style='color:red'>数值应在 {0}和{1}之间</span>" ),
			max: $.validator.format( "<span style='color:red'>数值不能大于{0}</span>" ),
			min: $.validator.format( "<span style='color:red'>数值不能小于{0}</span>" )
		} 
	}
	);
	
	
	$.validator.methods.range= function( value, element, param ) {
		var vstr=value+"";
		var tmpv=vstr.replace(/,/g,'');
		value=parseFloat(tmpv);
		return this.optional( element ) || ( value >= param[ 0 ] && value <= param[ 1 ] );
	};
	$.validator.methods.number=function( value, element ) {
		var vstr=value+"";
		var tmpv=vstr.replace(/,/g,'');
		value=parseFloat(tmpv);
		return this.optional( element ) || /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test( value );
	};
	
	$.validator.methods.equalTo= function( value, element, param ) { 
		var target = shineyue.getField( param[0] ); 
		if ( this.settings.onfocusout ) {
			target.off( ".validate-equalTo" ).on( "blur.validate-equalTo", function() {
				$( element ).valid(); 
			});
		}
		return value === target.val();
	}
	// 手机号码验证
	$.validator.addMethod("mobile", function(value, element) {
	    var length = value.length;
	    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
	    return this.optional(element) || (length == 11 && mobile.test(value));
	}, "<span style='color:red'>手机号码格式错误</span>");   

	// 电话号码验证   
	$.validator.addMethod("phone", function(value, element) {
	    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	    return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>电话号码格式错误</span>");

	// 邮政编码验证   
	$.validator.addMethod("zipcode", function(value, element) {
	    var tel = /^[0-9]{6}$/;
	    return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>邮政编码格式错误</span>");

	// QQ号码验证   
	$.validator.addMethod("qq", function(value, element) {
	    var tel = /^[1-9]\d{4,9}$/;
	    return this.optional(element) || (tel.test(value));
	}, "<span style='color:red'>qq号码格式错误</span>");

	// IP地址验证
	$.validator.addMethod("ip", function(value, element) {
	    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
	}, "<span style='color:red'>Ip地址格式错误</span>");

	// 字母和数字的验证
	$.validator.addMethod("chrnum", function(value, element) {
	    var chrnum = /^([a-zA-Z0-9]+)$/;
	    return this.optional(element) || (chrnum.test(value));
	}, "<span style='color:red'>只能输入数字和字母(字符A-Z, a-z, 0-9)</span>");

	// 中文的验证
	$.validator.addMethod("chinese", function(value, element) {
	    var chinese = /^[\u4e00-\u9fa5]+$/;
	    return this.optional(element) || (chinese.test(value));
	}, "<span style='color:red'>只能输入中文</span>");

	// 下拉框验证
	$.validator.addMethod("selectnone", function(value, element) {
	    return value == "请选择";
	}, "<span style='color:red'>必须选择一项</span>");

	// 字节长度验证
	$.validator.addMethod("byterangelength", function(value, element, param) {
	    var length = value.length;
	    for (var i = 0; i < value.length; i++) {
	        if (value.charCodeAt(i) > 127) {
	            length++;
	        }
	    }
	    return this.optional(element) || (length >= param[0] && length <= param[1]);
	}, $.validator.format("<span style='color:red'>请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)</span>"));

	
	// 下拉框验证
	$.validator.addMethod("numberPrecission", function(value, element, param) {
	    var chrnum = new RegExp("^\\d+(\\.\\d{"+param[0]+"})?$"); 
	    return this.optional(element) || (chrnum.test(value));
	}, $.validator.format("<span style='color:red'>数值要求小数点后保留{0}位数字</span>"));

	
})(jQuery);