$(document).on("pageinit", "#doTqsqPage", function() {
	$("#openid").val(getValFromUrl("openid"));
	$.ajax({
					url : "/WeiXin/rest/doTqsqInit",
					type : "GET",
					dataType : "json",
					contentType : "application/json", 
					data : $("form#doTqsq").serialize(),
					success : function(response) {
						$.mobile.loading("hide");
						console.log("success:" + response.success);
						if("true" == response.success){
							$("#uname").val(response.uname);
							$("#cardNumber").val(response.cardNumber);
							$("#zhzt").val(response.zhzt);
							$("#zhye").val(response.zhye);
						}else{
							generate(response.msg,"alert","bottomCenter");
						}
					},
					error : function(errorThrown) {
						$.mobile.loading("hide");
						console.log("Error: "
								+ errorThrown);
						console.dir(errorThrown);
						generate("Error: "
								+ errorThrown,"alert","center");
					}
				});

	$("form").submit(function() {
		$.mobile.loading("show");
		$.ajax({
					url : "http://1.wasoft4weixin.sinaapp.com/rest/doTqsq",
					type : "GET",
					data : $("form#doTqsq").serialize(),
					success : function(response) {
						$.mobile.loading("hide");
						console.log("success:" + response.success);
						console.log("msg:" + response.msg);
						if("true" == response.success){
							var yyOkMsg = "请"+response.uname+"("+response.cardNumber+")先生/女士 在" + response.date + "到大厅" + response.windowId+"号柜台办理提取业务,预约号:" + response.yyId;
							generate("<h2>"+yyOkMsg+"</h2>","alert","bottomCenter");
						}else{
							generate(response.msg,"alert","bottomCenter");
						}
					},
					error : function(errorThrown) {
						$.mobile.loading("hide");
						console.log("Error: "
								+ errorThrown);
						console.dir(errorThrown);
						generate("Error: "
								+ errorThrown,"alert","bottomCenter");
					}
				});

		return false; // Prevent a form submit
	});
});
