(function() {
	var m001 = {
		xtype : "treeitem",
		text : "产品文档库",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/document/productDocLib.js",
		handler : function() {
			
		}
	};
	var m002 = {
		xtype : "treeitem",
		text : "项目文档库",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/document/projectDocLib.js",
		handler : function() {

		}
	};

	var m101 = {
		xtype : "treeitem",
		text : "文档管理",
		leaf : false,
		items : [ m001, m002]
	};
	var header = new Pallasli.tree.Tree({
		xtype : "tree",
		region : "west",
		width : 200,
		items : [ m101 ]
	});

	var main = new Pallasli.window.IFrame({
		name : "workPage",
		xtype : "iframe"
	});

	new Pallasli.window.ViewPort({
		items : [ header, main ]
	});
})();