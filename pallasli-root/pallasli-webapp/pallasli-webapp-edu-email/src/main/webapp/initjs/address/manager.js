(function() {
	var m1 = {
			xtype : "treeitem",
			text : "收信组/收信人",
			leaf : true,
			handler : function() {

			}
		};
		var m2 = {
			xtype : "treeitem",
			text : "全部",
			leaf : true,
			handler : function() {

			}
		};
		var m3 = {
				xtype : "treeitem",
				text : "高管组",
				leaf : true,
				handler : function() {

				}
			};
		var m4 = {
				xtype : "treeitem",
				text : "项目管理组",
				leaf : true,
				handler : function() {

				}
			};

		var m6 = {
			xtype : "treeitem",
			text : "通讯录管理",
			leaf : false,
			items : [ m1]
		};
		var m7 = {
				xtype : "treeitem",
				text : "收信人分组",
				leaf : false,
				items : [ m2 ,m3,m4]
			};
		var grid = new Pallasli.tree.Tree({
			xtype : "tree",
			region : "west",
			width : 200,
			columnWidth : 2,
			pager : false,
			items : [ m6 ]
		}); 
		var grid2 = new Pallasli.tree.Tree({
			xtype : "tree",
			region : "west",
			width : 200,
			columnWidth : 2,
			pager : false,
			items : [ m7 ]
		}); 
	    	var sxr = new Pallasli.panel.Grid({
	    		columnWidth:8,
	    		xtype : "grid",
	    		tbar:[{text:"增加组"},{text:"修改组"},{text:"删除组"},
	    		      {text:"增加联系人"},{text:"修改联系人"},{text:"删除联系人"},
	    		      {text:"导出"},{text:"导入"},{text:"搜索"}],
	    		thead : [  {
	    			header : '联系人',
	    			dataIndex : 'iconid',
	    			hidden : false,
	    			width : 80,
	    			sortable : true
	    		}, {
	    			header : '邮箱地址',
	    			dataIndex : 'previewpath',
	    			renderer : function(value) {
	    				//return '<img src=' + value + ' />';
	    			},
	    			width : 50
	    		}, {
	    			header : '联系电话',
	    			dataIndex : 'cssname',
	    			width : 150
	    		}, {
	    			header : '邮编',
	    			dataIndex : 'filename',
	    			width : 150
	    		},{
	    			header : '地址',
	    			dataIndex : 'filename',
	    			width : 150
	    		}],
	    		datastore : [ ]
	    	});
	    	new Pallasli.window.ViewPort({
	    		layout:"column",
	    		items : [grid,grid2,sxr]
	    	});
})();