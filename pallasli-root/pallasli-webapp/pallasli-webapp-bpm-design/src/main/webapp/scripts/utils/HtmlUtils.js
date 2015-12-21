Ext.define('Pallas.activitiDesigner.utils.HtmlUtils', {
	statics : {
		checkMouseIn : function(div) {
			var x = window.event.clientX;
			var y = window.event.clientY;
			var offsetL = div.offsetParent.offsetLeft;
			var offsetT = div.offsetParent.offsetTop;
			var clientW = div.offsetParent.clientWidth;
			var clientH = div.offsetParent.clientHeight;
			if (x > offsetL && x < (offsetL + clientW) && y > offsetT
					&& y < (offsetT + clientH + 53)) {
				return true;
			}
			return false;
		}
	}
});
