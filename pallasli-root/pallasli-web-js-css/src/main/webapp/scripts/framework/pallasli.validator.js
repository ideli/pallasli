/**
(1)required:true 必输字段
(2)remote:"check.php" 使用ajax方法调用check.php验证输入值
(3)email:true 必须输入正确格式的电子邮件
(4)url:true 必须输入正确格式的网址
(5)date:true 必须输入正确格式的日期
(6)dateISO:true 必须输入正确格式的日期(ISO)，例如：2009-06-23，1998/01/22 只验证格式，不验证有效性
(7)number:true 必须输入合法的数字(负数，小数)
(8)digits:true 必须输入整数
(9)creditcard: 必须输入合法的信用卡号
(10)equalTo:"#field" 输入值必须和#field相同
(11)accept: 输入拥有合法后缀名的字符串（上传文件的后缀）
(12)maxlength:5 输入长度最多是5的字符串(汉字算一个字符)
(13)minlength:10 输入长度最小是10的字符串(汉字算一个字符)
(14)rangelength:[5,10] 输入长度必须介于 5 和 10 之间的字符串")(汉字算一个字符)
(15)range:[5,10] 输入值必须介于 5 和 10 之间
(16)max:5 输入值不能大于5
(17)min:10 输入值不能小于10



下面是默认的验证提示，官网有简体中文版的验证提示下载，或者通过jQuery.extend(jQuery.validator.messages自定义错误提示信息，
可以将网站的验证提示文本统一到一个文件里。

required: "This field is required.",
remote: "Please fix this field.",
email: "Please enter a valid email address.",
url: "Please enter a valid URL.",
date: "Please enter a valid date.",
dateISO: "Please enter a valid date (ISO).",
number: "Please enter a valid number.",
digits: "Please enter only digits",
creditcard: "Please enter a valid credit card number.",
equalTo: "Please enter the same value again.",
accept: "Please enter a value with a valid extension.",
maxlength: $.validator.format("Please enter no more than {0} characters."),
minlength: $.validator.format("Please enter at least {0} characters."),
rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
range: $.validator.format("Please enter a value between {0} and {1}."),
max: $.validator.format("Please enter a value less than or equal to {0}."),
min: $.validator.format("Please enter a value greater than or equal to {0}.")



required除了设置为true/false之外，还可以使用表达式或者函数，比如
$("#form2").validate({
	rules: {
		funcvalidate: {
			required: function() {return $("#password").val()!=""; }
		}
	},
	messages: {
		funcvalidate: {
			required: "有密码的情况下必填"
		}
	}
});


使用meta自定义验证信息

首先用JS设置meta
$("#form3").validate({ meta: "validate" });            

Html

email<input class="{validate:{required:true, email:true, 
messages:{required:'输入email地址', email:'你输入的不是有效的邮件地址'}}}"/>



使用meta可以将验证规则写在自定义的标签内，比如validate

JS设置meta
$().ready(function() {
    $.metadata.setType("attr", "validate");
    $("#form1").validate();
});

Html

Email<input id="email" name="email" 
validate="{required:true, email:true, messages:{required:'输入email地址', email:'你输入的不是有效的邮件地址'}}" />




自定义验证规则

对于复杂的验证，可以通过jQuery.validator.addMethod添加自定义的验证规则

官网提供的additional-methods.js里包含一些常用的验证方式，比如lettersonly，ziprange，nowhitespace等

例子
// 字符验证   
jQuery.validator.addMethod("userName", function(value, element) {
    return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "用户名只能包括中文字、英文字母、数字和下划线");   

//然后就可以使用这个规则了
$("#form1").validate({
    // 验证规则
    rules: {
        userName: {
            required: true,
            userName: true,
            rangelength: [5,10]
        }
    },
    //设置错误信息  
    messages: {
        userName: {
            required: "请填写用户名",
            rangelength: "用户名必须在5-10个字符之间" 
        }                
    },
});












Plugin methods
Name	Type

validate( options )	Returns: Validator
验证所选的FORM

valid( )	Returns: Boolean
检查是否验证通过

rules( )	Returns: Options
返回元素的验证规则

rules( "add", rules )	Returns: Options
增加验证规则

rules( "remove", rules )	Returns: Options
删除验证规则

removeAttrs( attributes )	Returns: Options
删除特殊属性并且返回他们

Custom selectors
Name	Type

:blank	Returns: Array <Element >
没有值的筛选器

:filled	Returns: Array <Element >
有值的筛选器

:unchecked	Returns: Array <Element >
没选择的元素的筛选器

Utilities

Name	Type

jQuery.format( template, argument , argumentN... )	Returns: String
用参数代替模板中的 {n}。

Validator

validate方法返回一个Validator对象, 它有很多方法， 让你能使用引发校验程序或者改变form的内容. 

下面只是列出常用的.

form( )	Returns: Boolean
验证form返回成功还是失败

element( element )	Returns: Boolean
验证单个元素是成功还是失败

resetForm( )	Returns: undefined
把前面验证的FORM恢复到验证前原来的状态

showErrors( errors )	Returns: undefined
显示特定的错误信息

built-in Validation methods

Name	Type

setDefaults( defaults )	Returns: undefined
改变默认的设置

addMethod( name, method, message )	Returns: undefined
添加一个新的验证方法. 必须包括名字，一个JAVASCRIPT方法和一个默认的信息

addClassRules( name, rules )	Returns: undefined
增加组合验证类型 

addClassRules( rules )	Returns: undefined
增加组合验证类型 

built-in Validation methods

Name	Type

required( )	Returns: Boolean
必填验证元素

required( dependency-expression )	Returns: Boolean
必填元素依赖于表达式的结果.

required( dependency-callback )	Returns: Boolean
必填元素依赖于回调函数的结果.

remote( url )	Returns: Boolean
请求远程校验。url通常是一个远程调用方法

minlength( length )	Returns: Boolean
设置最小长度

maxlength( length )	Returns: Boolean
设置最大长度

rangelength( range )	Returns: Boolean
设置一个长度范围[min,max]

min( value )	Returns: Boolean
设置最小值.

max( value )	Returns: Boolean
设置最大值.

range( range )	Returns: Boolean
设置值的范围

email( )	Returns: Boolean
验证电子邮箱格式

url( )	Returns: Boolean
验证连接格式

date( )	Returns: Boolean
验证日期格式(类似30/30/2008的格式，不验证日期准确性只验证格式)

dateISO( )	Returns: Boolean
研制ISO类型的日期格式

dateDE( )	Returns: Boolean
验证德式的日期格式（29.04.1994 or 1.1.2006）

number( )	Returns: Boolean
验证十进制数字（包括小数的）

numberDE( )	Returns: Boolean
Makes the element require a decimal number with german format.

digits( )	Returns: Boolean
验证整数

creditcard( )	Returns: Boolean
验证信用卡号

accept( extension )	Returns: Boolean
验证相同后缀名的字符串

equalTo( other )	Returns: Boolean
验证两个输入框的内容是否相同

自定义jquery-validate的验证行为

1: 自定义表单提交

设置submitHandler来自定义表单提交动作

$(".selector").validate({
    submitHandler: function(form) { alert("验证通过"); }
});

如果需要提交表单，可以调用
form.submit(); 或者$(form).ajaxSubmit();

2: 调试模式

将debug设置为true，表单不会提交，只进行检查，方便调试

$(".selector").validate({
   debug: true
})

3: 设置validate的默认值

使用setDefaults可以设置validate的默认值，比如默认所有表单验证都是在debug模式下进行

$.validator.setDefaults({
    debug: true
})

4: 某些元素不验证

设置ignore属性可以忽略某些元素不验证

$(".selector").validate({
   ignore: "ignore"
})

5: 验证时机

jquery.validate可以很方便的设置在什么时候触发验证动作

onsubmit: 提交时是否验证

$(".selector").validate({
   onsubmit: false
})

onfocusout: 失去焦点时验证(checkboxes/radio除外)

$(".selector").validate({
   onfocusout: false
})

onkeyup: 在keyup时验证

$(".selector").validate({
   onkeyup: false
})

onclick: 在checkboxes、radio点击时验证.

$(".selector").validate({
   onclick: false
})

6: 重写验证规则和验证提示信息

//重写max的的验证提示信息
$.validator.messages.max = jQuery.format("Your totals musn't exceed {0}!");

//重写equal方法
$.validator.methods.equal = function(value, element, param) {
	return value == param;
};

7: focusInvalid 是否把焦点聚焦在最后一个动作或者最近的一次出错上

$(".selector").validate({
   focusInvalid: false
})

8: focusCleanup

如果该属性设置为True, 那么控件获得焦点时，移除出错的class定义，隐藏错误信息，避免和 focusInvalid.一起用。

$(".selector").validate({
   focusCleanup: true
})

9: meta

设置meta来封装验证规则

$(".selector").validate({
   meta: "validate",
})

<script type="text/javascript"></script>
自定义错误消息的显示方式

默认情况下，验证提示信息用label元素来显示, 并且会添加css class, 通过css可以很方便设置出错控件以及错误信息的显示方式。

//输入控件验证出错 
form  input.error { border:solid 1px red;}

// 验证错误提示信息 
form label.error{width: 200px;margin-left: 10px; color: Red;}

如果想自定义显示方式，可以修改jquery.validate的默认显示方式

默认用label显示错误消息，可以通过errorElement属性来修改
errorElement: 错误消息的html标签

$(".selector").validate
   errorElement: "em"
})

可以在出错信息外用其他的元素包装一层。
wrapper: 错误消息的外层封装html标签

$(".selector").validate({
   wrapper: "li"
})

验证出错的css class默认是error，通过errorClass可以修改
errorClass: 验证出错时使用的css class

$(".selector").validate({
   errorClass: "invalid"
})

还自定义验证成功时的动作
success: 如果值是字符串，会当做一个css类，如果是一个函数，则执行该函数

$(".selector").validate({
	success: "valid"
})

或者

success: function(label) {
	label.html(" ").addClass("checked");
}

还可以把错误消息统一到一个容器显示
errorLabelContainer: 将错误消息统一到一个容器显示

$("#myform").validate({
   errorLabelContainer: "#messageBox"
})

默认情况下，错误消息是放在验证元素后面的，可以自定义错误消息的显示位置

$(".selector").validate({
  errorPlacement: function(error, element) {
     error.appendTo( element.parent("td").next("td") );
   }
})

更进一步可以定义一个组，把几个地方的出错信息统一放在一个地方，用error Placement控制把出错信息放在哪里
groups：定义一个组

$(".selector").validate({
  groups: {
    username: "fname lname"
  },
  errorPlacement: function(error, element) {
     if (element.attr("name") == "fname" || element.attr("name") == "lname" )
       error.insertAfter("#lastname");
     else
       error.insertAfter(element);
   }
 })

高亮显示
highlight: 高亮显示，默认是添加errorClass
unhighlight: 和highlight对应，反高亮显示

$(".selector").validate({
  highlight: function(element, errorClass) {
     $(element).addClass(errorClass);
     $(element.form).find("label[for=" + element.id + "]").addClass(errorClass);
  },
  unhighlight: function(element, errorClass) {
     $(element).removeClass(errorClass);
     $(element.form).find("label[for=" + element.id + "]").removeClass(errorClass);
  }
});

 
或者可以完全自定义错误显示
showErrors: 得到错误的显示句柄

$(".selector").validate({
   showErrors: function(errorMap, errorList) {
	$("#summary").html("Your form contains " + this.numberOfInvalids() 
		+ " errors, see details below.");
	this.defaultShowErrors();
   }
})
 

 
**/


/**
	required:必输项验证
	remote: 调用后台方法进行验证
	email: 邮箱格式验证
	url: URL格式验证
	date:  日期格式格式验证
	dateISO:  ISO日期格式验证
	number:  数字格式验证
	digits:  小数格式验证
	creditcard:  信用卡号格式验证
	equalTo:  确认验证 值类型更改为数值
	maxlength:  最大长度验证
	minlength: 最小长度验证
	rangelength: 长度范围验证
	range: 取值范围验证
	max: 最大值验证
	min: 最小值验证
	mobile:手机号码格式错误 
	phone:电话号码验证
	zipCode:邮政编码格式验证
	qq:qq号码格式验证
	ip:Ip地址格式验证
	chrnum:只能输入数字和字母 
	chinese:只能输入中文 
	selectNone :必须选择一项  
	byteRangeLength: 请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节) 
 **/


$.extend( $.validator, { 
messages: {
		required: "<span style='color:red'>必输项</span>",
		remote: "Please fix this field.",
		email: "<span style='color:red'>邮箱格式无效</span>",
		url: "<span style='color:red'>URL无效</span>",
		date: "<span style='color:red'>日期格式无效</span>",
		dateISO: "<span style='color:red'>日期格式采用ISO标准</span>",
		number: "<span style='color:red'>无效数值</span>",
		digits: "<span style='color:red'>必须是数字</span>",
		creditcard: "<span style='color:red'>信用卡号无效</span>",
		equalTo: $.validator.format( "<span style='color:red'>需与{1}保持一致</span>"),
		maxlength: $.validator.format( "<span style='color:red'>长度不能超过 {0}个字符</span>" ),
		minlength: $.validator.format( "<span style='color:red'>长度不能少于 {0}个字符</span>" ),
		rangelength: $.validator.format( "<span style='color:red'>数值长度应在{0}和{1}之间</span>" ),
		range: $.validator.format( "<span style='color:red'>数值应在 {0}和{1}之间</span>" ),
		max: $.validator.format( "<span style='color:red'>数值不能大于{0}</span>" ),
		min: $.validator.format( "<span style='color:red'>数值不能小于{0}</span>" )
	}
});
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
$.validator.addMethod("zipCode", function(value, element) {
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
$.validator.addMethod("selectNone", function(value, element) {
    return value == "请选择";
}, "<span style='color:red'>必须选择一项</span>");

// 字节长度验证
$.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("<span style='color:red'>请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)</span>"));


$.validator.addMethod("equalTo", function( value, element, param ) {  
	 var target = $( param[0]  ); 
	 if( $('label[for='+$(param[0] ).attr("id")+']:not(.error)')){ 
		 arguments[2][1]=$('label[for='+$(param[0]).attr("id")+']:not(.error)').text();
	 }
	if ( this.settings.onfocusout ) {
		target.off( ".validate-equalTo" ).on( "blur.validate-equalTo", function() {
			$( element ).valid();
		});
	}
	return value === target.val();  
});