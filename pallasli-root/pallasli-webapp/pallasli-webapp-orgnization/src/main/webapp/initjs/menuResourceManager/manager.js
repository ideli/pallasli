(function() {
	var m001 = {
		xtype : "treeitem",
		text : "菜单1",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/menuResourceManager/menuResourceManager.js",
		handler : function() {
			
		}
	};
	var m002 = {
			xtype : "treeitem",
			text : "菜单2",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/menuResourceManager/menuResourceManager.js",
			handler : function() {

			}
		};

	var m0009 = {
			xtype : "treeitem",
			text : "菜单3-1",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/menuResourceManager/menuResourceManager.js",
			handler : function() {

			}
		};
	var m0010 = {
			xtype : "treeitem",
			text : "菜单3-2",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/menuResourceManager/menuResourceManager.js",
			handler : function() {

			}
		};
	var m005 = {
			text : "表单3",
			leaf : false,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/menuResourceManager/menuResourceManager.js",
			items : [m0009,m0010 ]
		};

	var m101 = {
		xtype : "treeitem",
		text : "菜单资源",
		leaf : false,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/menuResourceManager/menuResourceManager.js",
		items : [ m001, m002,m005]
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