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
			thead : [{ header: '数据表英文名', dataIndex: 'tableName', flex : 2 },
				        { header: '数据表中文名',  dataIndex: 'tableCaption', flex: 2,
	            field: {
                xtype: 'textfield',
                allowBlank: false
            } },
	        { header: '主键',  dataIndex: 'tableKey', flex: 1 } ],
	        tbar:[{
				"text":"导入数据表",
				"handler":function(){
					document.getElementById(main.id).src="../engine/view.html?jsurl=../initjs/tables/importTable.js";
				}
			},{
				"text":"导入数据库",
				"handler":"function(){}"
			},{
				"text":"增加",
				"handler":"function(){}"
			},{
				"text":"保存",
				"handler":"function(){}"
		}],
			data : [],

		});
		var main = new Pallasli.window.IFrame({
			name : "importTable",
			xtype : "iframe"
		});
		new Pallasli.window.ViewPort({
			items : [ grid,main ]
		});

	})
})();