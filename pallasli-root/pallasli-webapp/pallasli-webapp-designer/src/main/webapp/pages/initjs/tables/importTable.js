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

		
		var form=new Pallasli.panel.Form({
			
			items:[{xtype:"textfield",fieldLabel:"数据库类型"},
					{xtype:"textfield",fieldLabel:"数据库实例"},
					{xtype:"textfield",fieldLabel:"ip"},
					{xtype:"textfield",fieldLabel:"端口"},
					{xtype:"textfield",fieldLabel:"所属项目"},
					{xtype:"textfield",fieldLabel:"用户名"},
					{xtype:"textfield",fieldLabel:"用户密码"},
					{xtype:"textfield",fieldLabel:""},
					{xtype:"button",text:"查询表"},
					{xtype:"button",text:"导入设计库"},
					{xtype:"button",text:""},
					{xtype:"button",text:"保存数据库"}
			       
			       ]
		});
		
		
		var grid = new Pallasli.panel.Grid({
			xtype : "grid",
			region : "west",
			width : 1200,
			idProperty : "",
			thead : [ {
				header : '所属项目',
				dataIndex : 'tableName',
				flex : 2
			},  {
				header : '表名',
				dataIndex : 'tableName',
				flex : 2
			},  {
				header : '中文名',
				dataIndex : 'tableName',
				flex : 2
			}, {
				header : '唯一标识',
				dataIndex : 'tableCaption',
				flex : 2,
				field : {
					xtype : 'textfield',
					allowBlank : false
				}
			}, {
				header : '入库id',
				dataIndex : 'tableKey',
				flex : 1
			} ],
			data : [],

		});
		new Pallasli.window.ViewPort({
			items : [ form,grid ]
		});

	})
})();