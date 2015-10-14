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
			
			items:[{xtype:"textfield",fieldLabel:"选择表"},
					{xtype:"button",text:"引入分组"}
			       
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
		    			{"dataIndex":"fieldAllowBlank","text":"允许空","flex":2,"align":"left",
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
						{"dataIndex":"tableName","hidden":true}
					],
			data : [],

		});
		new Pallasli.window.ViewPort({
			items : [ form,grid ]
		});

	})
})();