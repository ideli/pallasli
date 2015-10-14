[
		{
			xtype : 'buttongroup',
			columns : 2,
			defaults : {
				scale : 'small'
			},
			items : [ {
				xtype : 'splitbutton',
				text : '单位',
				iconCls : 'add16',
				handlerFn : function() {
					
					
				},
				menu : [ {
					text : '单位管理',
					handlerFn : function() { 
							store.proxy.url = 'data/design/modules/module1.json';
							store.load();
					}
				} ]
			}, {
				xtype : 'splitbutton',
				text : 'next',
				iconCls : 'add16',
				menu : [ {
					text : '个人管理',
					menu : [ {
						text : 'regular item 1'
					}, {
						text : 'regular item 2'
					}, {
						text : 'regular item 3'
					} ]
				} ]
			}, {
				text : '个人',
				iconCls : 'add16',
				handlerFn : function() { 
					store.proxy.url = 'data/design/modules/module2.json';
					store.load();
				}
			}, {
				text : 'Paste',
				iconCls : 'add16',
				menu : [ {
					text : 'regular item 1'
				}, {
					text : 'regular item 2'
				}, {
					text : 'regular item 3'
				} ]
			}, {
				text : 'Format',
				iconCls : 'add16'
			} ]
		},
		{
			xtype : 'buttongroup',
			style : 'margin-right:0px',
			columns : 5,
			defaults : {
				scale : 'small'
			},
			items : [
					{
						text : '组件定义',
						handlerFn : function() {

							window
									.open(basePath+'/jsppage.do?url=design/componentDefinition');
						},
						iconCls : 'add16'
					}, {
						text : 'Cut',
						iconCls : 'add16'
					}, {
						text : 'Copy',
						iconCls : 'add16'
					}, {
						text : 'Paste',
						iconCls : 'add16'
					}, {
						text : 'Format',
						iconCls : 'add16'
					} ]
		} ]