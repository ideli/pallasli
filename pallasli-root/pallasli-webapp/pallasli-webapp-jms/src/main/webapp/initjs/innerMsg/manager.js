(function() { 
	var m1 = {
			xtype : "treeitem",
			text : "发信",
			leaf : true,
			handler : function() {

			}
		};
		var m2 = {
			xtype : "treeitem",
			text : "发信箱",
			leaf : true,
			handler : function() {

			}
		};
		var m3 = {
			xtype : "treeitem",
			text : "收信箱",
			leaf : true,
			handler : function() {

			}
		};
		 
		var m6 = {
			xtype : "treeitem",
			text : "站内信管理",
			leaf : false,
			items : [ m1, m2 ,m3]
		};
		var grid = new Pallasli.tree.Tree({
			xtype : "tree",
			region : "west",
			width : 200,
			columnWidth : 2,
			pager : false,
			items : [ m6 ]
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
		tbar:[{text:"清空收信箱"},{text:"搜索"},{text:"重置"}],
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
			header : '内容',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '信息类型',
			dataIndex : 'filename',
			width : 150
		},{
			header : '是否需要回复',
			dataIndex : 'filename',
			width : 150
		},{
			header : '是否已回复',
			dataIndex : 'filename',
			width : 150
		},{
			header : '标记为已读',
			dataIndex : 'filename',
			width : 150
		},{
			header : '回复',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [ [ {
			text : "r1c1",
			cls : "active"
		}, {
			text : "r1c2"
		}, {
			text : "r1c3"
		}, {
			text : "r1c4"
		}, {
			text : "r1c5"
		}, {
			text : "r1c6"
		}, {
			text : "r1c7"
		} ] ]
	});
	var fjx = new Pallasli.panel.Grid({
		xtype : "grid",
		tbar:[{text:"清空发信箱"},{text:"搜索"},{text:"重置"}],
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
			header : '内容',
			dataIndex : 'cssname',
			width : 150
		}, {
			header : '信息类型',
			dataIndex : 'filename',
			width : 150
		},{
			header : '是否已读',
			dataIndex : 'filename',
			width : 150
		},{
			header : '是否需要回复',
			dataIndex : 'filename',
			width : 150
		},{
			header : '是否已回复',
			dataIndex : 'filename',
			width : 150
		}, {
			header : '删除',
			dataIndex : 'accesspath'
		}],
		datastore : [ [ {
			text : "r1c1",
			cls : "active"
		}, {
			text : "r1c2"
		}, {
			text : "r1c3"
		}, {
			text : "r1c4"
		}, {
			text : "r1c5"
		}, {
			text : "r1c6"
		}, {
			text : "r1c7"
		} ] ]
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		items : [grid,fx, sjx ,fjx]
	});
})();