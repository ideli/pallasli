(function() {

	var m1 = {
			xtype : "treeitem",
			text : "t1",
			leaf : true,
			handler : function() {

			}
		};
		var m2 = {
			xtype : "treeitem",
			text : "t2",
			leaf : true,
			handler : function() {

			}
		};
		var m3 = {
			xtype : "treeitem",
			text : "t3",
			leaf : true,
			handler : function() {

			}
		};
		var m11 = {
				xtype : "treeitem",
				text : "v1",
				leaf : true,
				handler : function() {

				}
			};
			var m21 = {
				xtype : "treeitem",
				text : "v2",
				leaf : true,
				handler : function() {

				}
			};
			var m31 = {
				xtype : "treeitem",
				text : "v3",
				leaf : true,
				handler : function() {

				}
			};
			var m111 = {
					xtype : "treeitem",
					text : "q1",
					leaf : true,
					handler : function() {

					}
				};
				var m211 = {
					xtype : "treeitem",
					text : "q2",
					leaf : true,
					handler : function() {

					}
				};
				var m311 = {
					xtype : "treeitem",
					text : "q3",
					leaf : true,
					handler : function() {

					}
				};

		var m6 = {
			xtype : "treeitem",
			text : "表",
			leaf : false,
			items : [ m1, m2 ,m3]
		};
		var m7 = {
				xtype : "treeitem",
				text : "视图",
				leaf : false,
				items : [ m11, m21 ,m31]
			};
		var m8 = {
				xtype : "treeitem",
				text : "查询语句",
				leaf : false,
				items : [ m111, m211 ,m311]
			};
		var grid = new Pallasli.tree.Tree({
			xtype : "tree",
			region : "west",
			width : 200,
			columnWidth : 2,
			pager : false,
			items : [ m6 ,m7,m8]
		}); 

	var p1=new Pallasli.panel.Panel({
		xtype:"panel",
		title:"查询窗口",
		tbar:[{text:"执行"},{text:"保存"}]
	});
	var p2=new Pallasli.panel.Panel({
		xtype:"panel",
		title:"查询结果",
		tbar:[{text:"另存为"}]
		
	});
	
	
	var frame=new Pallasli.panel.Panel({
		width : 800,
		items:[p1,p2],
		region : "center",
		title:"frame"
	});
	new Pallasli.window.ViewPort({
		layout:"column",
		items : [ grid ,frame]
	});
})();