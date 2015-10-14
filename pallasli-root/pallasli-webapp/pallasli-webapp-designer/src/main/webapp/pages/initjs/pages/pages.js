(function() {

	$ready(function() {
		function getUrlParam(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg); // 匹配目标参数
			if (r != null)
				return unescape(r[2]);
			return null; // 返回参数值
		}
		var url = location.href;

		var grid = new Pallasli.panel.Grid({
			xtype : "grid",
			region : "west",
			width : 1200,
			idProperty : "",
			thead : [ {
				header : '页面英文名',
				dataIndex : 'fieldName',
				flex : 2
			}, {
				header : '页面中文名',
				dataIndex : 'fieldCaption',
				flex : 2
			}, {
				header : '标题',
				dataIndex : 'fieldLength',
				xtype : 'numbercolumn',
				format : '0,000',
				flex : 1
			}, {
				header : '页面类型',
				dataIndex : 'fieldPrecision',
				xtype : 'numbercolumn',
				format : '0,000',
				flex : 1
			}, {
				header : '子类型',
				dataIndex : 'fieldType',
				flex : 2
			}, {
				header : '字段组合',
				dataIndex : 'fieldAllowblank',
				flex : 1
			} ],
			data : [],

		});

		new Pallasli.window.ViewPort({
			items : [ grid ]
		});

	})
})();