$(document).on("pageinit", "#calcDkjsPage", function() {

	$("form").submit(function() {
			var fwzjk = $("#fwzjk").val();
			var dke = fwzjk * 0.391;
						generate("<h2>最高可贷款额度:"
								+ dke + ",贷款年限最长为:30年</h2>","alert","bottomCenter");

		return false; // Prevent a form submit
	});
});
