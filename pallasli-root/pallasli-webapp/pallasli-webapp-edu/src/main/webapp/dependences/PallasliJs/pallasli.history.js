History = function() {

	var iframe,mainDiv, list = [], index = 0;

	iframe = document.createElement('iframe');
	iframe.style.display = 'none';
	iframe.onload = function() {

	};
	document.body.appendChild(iframe);

	function push(data) {
		if (typeof data !== 'object')
			return;

		// if (typeof data.param == undefined || typeof data.func !==
		// 'function')
		// return;

		list[index] = data;
		updateIframe(true);
		pushing = true;
		index++;
	}

	function updateIframe() {
		iframe.src = '/pages/admin/blank.html?' + index;
	}

	function get(idx) {
		var item;
		if (idx != index) {
			item = list[idx];
			if (item) {
				pallasli.loadHtml(item.mainDiv,
						item.html_url, item.js_url, item.nav, true);
			}
		}

	}

	return {
		push : push,
		get : get
	};
}();