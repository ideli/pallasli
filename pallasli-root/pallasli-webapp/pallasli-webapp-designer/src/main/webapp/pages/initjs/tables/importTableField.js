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
					{xtype:"button",text:"加载表字段"},
					{xtype:"button",text:""},
					{xtype:"button",text:"保存数据库"}
			       
			       ]
		});
		
		
		var grid = new Pallasli.panel.Grid({
			xtype : "grid",
			region : "west",
			width : 1200,
			idProperty : "",
			thead : [
		    			{"dataIndex":"fieldName","text":"列名","flex":2,"align":"left"},
		    			{"dataIndex":"fieldCaption","text":"中文名","flex":2,"align":"left",
							field: {
				                xtype: 'textfield',
				                allowBlank: false
		    				}
						},
		    			{"dataIndex":"fieldLength","text":"长度","flex":2,"align":"left",
							field: {
				                xtype: 'textfield',
				                allowBlank: false
		    				}
						},
		    			{"dataIndex":"fieldPrecision","text":"精度","flex":2,"align":"left",
							field: {
				                xtype: 'textfield',
				                allowBlank: false
		    				}
						},
		    			{"dataIndex":"fieldType","text":"类型","flex":2,"align":"left",
							field: {
				                xtype: 'textfield',
				                allowBlank: false
		    				}
						},
		    			{"dataIndex":"fieldAllowblank","text":"允许空","flex":2,"align":"left",
							field: {
				                xtype: 'textfield',
				                allowBlank: false
		    				}
						},
		    			{"dataIndex":"fieldDefault","text":"默认值","flex":2,"align":"left",
							field: {
				                xtype: 'textfield',
				                allowBlank: false
		    				}
						},
						{"dataIndex":"id","text":"入库id","flex":1,"align":"left"},
						{"dataIndex":"projectName","text":"所属项目","hidden":true},
						{"dataIndex":"tableName","text":"所属表","hidden":true}
					],
			data : [],

		});
		new Pallasli.window.ViewPort({
			items : [ form,grid ]
		});

	})
})();