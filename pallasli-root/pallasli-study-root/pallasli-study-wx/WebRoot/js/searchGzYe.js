$(document).on("pageinit", "#searchGzYePage", function() {
	$("#openid").val(getValFromUrl("openid"));

	$("form#searchGzYe").submit(function() {
		$.mobile.loading("show");
		$.ajax({
					url : "/WeiXin/rest/searchGzYe",
					type : "GET",					
					dataType : "json",
					contentType : "application/json", 
					data : $("form#searchGzYe").serialize(),
					success : function(response) {
						$.mobile.loading("hide");
						if(response.success == "true"){							
							$("#uname").val(response.uname);
							$("#grbh").val(response.grbh);
							$("#khrq").val(response.khrq);
							$("#grjce").val(response.grjce);
							$("#dwjce").val(response.dwjce);
							$("#gzye").val(response.gzye);
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
