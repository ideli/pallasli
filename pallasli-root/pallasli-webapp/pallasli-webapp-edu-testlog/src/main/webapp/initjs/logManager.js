(function() {
	var m1 = {
		xtype : "treeitem",
		text : "m1",
		leaf : true,
		handler : function() {

		}
	};
	var m2 = {
		xtype : "treeitem",
		text : "m2",
		leaf : true,
		handler : function() {

		}
	};
	var m3 = {
		xtype : "treeitem",
		text : "m3",
		leaf : true,
		handler : function() {

		}
	};
	var m4 = {
		xtype : "treeitem",
		text : "m4",
		leaf : true,
		handler : function() {

		}
	};
	var m5 = {
		xtype : "treeitem",
		text : "m5",
		leaf : true,
		handler : function() {

		}
	};
	var m6 = {
		xtype : "treeitem",
		text : "m6",
		leaf : false,
		items : [ m1, m2 ]
	};
	var m7 = {
		xtype : "treeitem",
		text : "m7",
		leaf : false,
		items : [ m3, m6 ]
	};
	var grid = new Pallasli.tree.Tree({
		xtype : "tree",
		region : "west",
		width : 200,
		columnWidth : 2,
		pager : false,
		items : [ m5, m7 ]
	});

	new Pallasli.window.ViewPort({
		layout : "column",
		items : [ grid ]
	});
})();