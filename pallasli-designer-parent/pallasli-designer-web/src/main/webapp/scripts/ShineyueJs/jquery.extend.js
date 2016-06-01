var $ready;

(function($) {
$ready = $(document).ready;
$.fn.setForm = function(jsonValue) {
	var obj = this;
	$.each(jsonValue, function(name, ival) {
		var $oinput = obj.find(":input[name=" + name + "]");
		if ($oinput.attr("type") == "radio"
				|| $oinput.attr("type") == "checkbox") {
			$oinput.each(function() {
				if (Object.prototype.toString.apply(ival) == '[object Array]') {
					// 是复选框，并且是数组
					for (var i = 0; i < ival.length; i++) {
						if ($(this).val() == ival[i])
							$(this).attr("checked", "checked");
					}
				} else {
					if ($(this).val() == ival)
						$(this).attr("checked", "checked");

				}
			});
		} else if ($oinput.attr("type") == "textarea") {
			// 多行文本框
			obj.find("[name=" + name + "]").html(ival);
		} else {
			obj.find("[name=" + name + "]").val(ival);
		}

	});
};
})(jQuery);
