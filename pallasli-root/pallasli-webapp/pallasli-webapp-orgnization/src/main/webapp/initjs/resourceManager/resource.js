(function(){

var form=new Pallasli.panel.Form({
			items : [ {
				fieldLabel : '菜单名称',
				name : 'menuname',
				allowBlank : false,
				anchor : '99%'
			}, {
				fieldLabel : '上级菜单',
				name : 'sortno',
				xtype : "combo",
				allowBlank : true,
				store:[],
				anchor : '99%'
			}, {
				fieldLabel : '请求地址',
				name : 'request',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '节点初始',
				name : 'sortno',
				xtype : "combo",
				allowBlank : true,
				store:[[ '0', '展开' ], [ '1', '闭合' ]],
				anchor : '99%'
			}, {
				fieldLabel : '图标CSS类',
				name : 'iconcls',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : 'Tab导航图标',
				name : 'icon',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面图标',
				name : 'shortcut',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面弹窗宽',
				xtype : 'numberfield',
				allowDecimals : false,
				allowNegative : false,
				name : 'width',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面弹窗高',
				name : 'height',
				xtype : 'numberfield',
				allowDecimals : false,
				allowNegative : false,
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面弹窗滚动条',
				name : 'sortno',
				xtype : "combo",
				allowBlank : true,
				store:[{value:"0",diaplay:"否"},{value:"0",diaplay:"是"}],
				anchor : '99%'
			}, {
				fieldLabel : '排序号',
				name : 'sortno',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '备注',
				name : 'remark',
				allowBlank : true,
				anchor : '99%'
			}, {
				name : 'parentid',
				hidden : true
			}, {
				name : 'windowmode',
				hidden : true
			}, {
				name : 'menuid',
				hidden : true
			}, {
				name : 'parentid_old',
				hidden : true
			}, {
				name : 'count',
				hidden : true
			}  ],
			fbar : [ {
				text : "保存"
			}, {
				text : "重填"
			} ]
		});
		
		new Pallasli.window.ViewPort({
			items : [ form ]
		});


})();
