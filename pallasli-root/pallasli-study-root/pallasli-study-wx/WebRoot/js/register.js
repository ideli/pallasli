$(document).on("pageinit", "#registrationPage", function() {
	$("#openid").val(getValFromUrl("openid"));

	$("form#register").submit(function() {
		$.mobile.loading("show");
		$.ajax({
					url : "/register。action",
					type : "POST",
					dataType : "json",
					contentType : "application/json;charset=utf-8", 
					data : $("form#register").serialize(),
					success : function(response) {
						$.mobile.loading("hide");
						console.log("success:" + response.success);
						console.log("msg:" + response.msg);
						generate(response.msg,"alert","bottomCenter");
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
