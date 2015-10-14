(function() {

	var m1 = {
			xtype : "treeitem",
			text : "配置",
			leaf : true,
			handler : function() {

			}
		};
		var m2 = {
			xtype : "treeitem",
			text : "发信",
			leaf : true,
			handler : function() {

			}
		};
		var m3 = {
				xtype : "treeitem",
				text : "发信箱",
				leaf : true,
				handler : function() {

				}
			};
		var m4 = {
				xtype : "treeitem",
				text : "收信箱",
				leaf : true,
				handler : function() {

				}
			};
		var m5 = {
				xtype : "treeitem",
				text : "发信箱",
				leaf : true,
				handler : function() {

				}
			};
		var m6 = {
				xtype : "treeitem",
				text : "草稿箱",
				leaf : true,
				handler : function() {

				}
			};
		 
		var m6 = {
			xtype : "treeitem",
			text : "垃圾箱",
			leaf : false,
			items : [ m1, m2 ,m3,m4,m5,m6]
		};
		var grid = new Pallasli.tree.Tree({
			xtype : "tree",
			region : "west",
			width : 200,
			columnWidth : 2,
			pager : false,
			items : [ m6 ]
		}); 
	var pz = new Pallasli.panel.Form({
		xtype:"form",
		layout:"column",
		items : [ {
			xtype : "textfield",
			fieldLabel : "账号名称"
		},  {
			xtype : "textfield",
			fieldLabel : "邮箱类型"
		},  {
			xtype : "textfield",
			fieldLabel : "邮箱地址"
		},  {
			xtype : "textfield",
			fieldLabel : "邮箱密码"
		},  {
			xtype : "htmleditor",
			fieldLabel : "stmp主机"
		} , {
			xtype : "htmleditor",
			fieldLabel : "stmp端口"
		} , {
			xtype : "htmleditor",
			fieldLabel : "pop主机"
		} , {
			xtype : "htmleditor",
			fieldLabel : "pop端口"
		} , {
			xtype : "checkbox",
			value:"1",text:"是",
			fieldLabel : "使用ssh"
		},{
			xtype : "checkbox",
			value:"1",text:"是",
			fieldLabel : "使用身份认证"
		},{
			xtype : "checkbox",
			value:"1",text:"是",
			fieldLabel : "下载附件"
		},{
			xtype : "checkbox",
			value:"1",text:"是",
			fieldLabel : "删除远程邮件"
		},{
			xtype : "closebtn",
			text : "测试"
		},{
			xtype : "closebtn",
			text : "关闭"
		},{
			xtype : "button",
			text : "保存"
		},  {
			xtype : "button",
			text : "重置"
		} ]
	});

	var fx = new Pallasli.panel.Form({
		xtype:"form",
		layout:"column",
		items : [ {
			xtype : "textfield",
			fieldLabel : "收信人"
		},  {
			xtype : "textfield",
			fieldLabel : "抄送"
		},  {
			xtype : "textfield",
			fieldLabel : "密送"
		},  {
			xtype : "textfield",
			fieldLabel : "标题"
		},  {
			xtype : "htmleditor",
			fieldLabel : "内容"
		} , {
			xtype : "checkbox",
			value:"1",text:"是",
			fieldLabel : "是否需要回复"
		},{
			xtype : "closebtn",
			text : "发送"
		},{
			xtype : "closebtn",
			text : "保存草稿"
		},{
			xtype : "closebtn",
			text : "添加附件"
		},{
			xtype : "closebtn",
			text : "关闭"
		},{
			xtype : "button",
			text : "保存"
		},  {
			xtype : "button",
			text : "重置"
		} ]
	});
	var sjx = new Pallasli.panel.Grid({
		xtype : "grid",
		tbar:[{text:"查看"},{text:"清空收信箱"},{text:"搜索"},{text:"重置"}],
		thead : [  {
			header : '发信人',
			dataIndex : 'iconid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '发信时间',
			dataIndex : 'previewpath',
			renderer : function(value) {
				//return '<img src=' + value + ' />';
			},
			width : 50
		}, {
			header : '主题',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '大小',
			dataIndex : 'filename',
			width : 150
		},{
			header : '状态',
			dataIndex : 'filename',
			width : 150
		},{
			header : '附件',
			dataIndex : 'filename',
			width : 150
		},{
			header : '已读',
			dataIndex : 'filename',
			width : 150
		},{
			header : '垃圾',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [ ]
	});
	var fjx = new Pallasli.panel.Grid({
		xtype : "grid",
		tbar:[{text:"查看"},{text:"清空发信箱"},{text:"搜索"},{text:"重置"}],
		thead : [  {
			header : '收信人',
			dataIndex : 'iconid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '发信时间',
			dataIndex : 'previewpath',
			renderer : function(value) {
				//return '<img src=' + value + ' />';
			},
			width : 50
		}, {
			header : '主题',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '大小',
			dataIndex : 'filename',
			width : 150
		},{
			header : '状态',
			dataIndex : 'filename',
			width : 150
		},{
			header : '附件',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [  ]
	});
	var cgx = new Pallasli.panel.Grid({
		xtype : "grid",
		tbar:[{text:"查看"},{text:"清空发信箱"},{text:"搜索"},{text:"重置"}],
		thead : [  {
			header : '收信人',
			dataIndex : 'iconid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '发信时间',
			dataIndex : 'previewpath',
			renderer : function(value) {
				//return '<img src=' + value + ' />';
			},
			width : 50
		}, {
			header : '主题',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '大小',
			dataIndex : 'filename',
			width : 150
		},{
			header : '状态',
			dataIndex : 'filename',
			width : 150
		},{
			header : '附件',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [  ]
	});
	var ljx = new Pallasli.panel.Grid({
		xtype : "grid",
		tbar:[{text:"查看"},{text:"清空收信箱"},{text:"搜索"},{text:"重置"}],
		thead : [  {
			header : '发信人',
			dataIndex : 'iconid',
			hidden : false,
			width : 80,
			sortable : true
		}, {
			header : '发信时间',
			dataIndex : 'previewpath',
			renderer : function(value) {
				//return '<img src=' + value + ' />';
			},
			width : 50
		}, {
			header : '主题',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '大小',
			dataIndex : 'filename',
			width : 150
		},{
			header : '状态',
			dataIndex : 'filename',
			width : 150
		},{
			header : '附件',
			dataIndex : 'filename',
			width : 150
		},{
			header : '已读',
			dataIndex : 'filename',
			width : 150
		},{
			header : '垃圾',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [ ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		items : [grid,pz,fx, sjx ,fjx,cgx,ljx]
	});
})();