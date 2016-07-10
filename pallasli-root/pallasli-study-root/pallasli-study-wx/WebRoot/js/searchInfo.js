$(document).on("pageinit", "#searchInfoPage", function() {
	$("#openid").val(getValFromUrl("openid"));

	$("form#searchInfo").submit(function() {
		$.mobile.loading("show");
		$.ajax({
					url : "/WeiXin/rest/searchInfo",
					type : "GET",
					dataType : "json",
					contentType : "application/json", 
					data : $("form#searchInfo").serialize(),
					success : function(response) {
						$.mobile.loading("hide");
						if(response.success == "true"){							
							$("#uname").val(response.uname);
							$("#grbh").val(response.grbh);
							$("#cardNumber").val(response.cardNumber);
							$("#csrq").val(response.csrq);
							$("#grxb").val(response.grxb);
							$("#cydz").val(response.cydz);
							$("#sfdk").val(response.sfdk);
						}else{
							generate(response.msg,"alert","center");
						}
						console.log("success:" + response.success);
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

		return false; // Prevent a form submit
	});
});
