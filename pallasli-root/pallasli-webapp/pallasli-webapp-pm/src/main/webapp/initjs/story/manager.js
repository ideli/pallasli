(function() {
	var m001 = {
		xtype : "treeitem",
		text : "用户管理",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/orgnization/userManager.js",
		handler : function() {
			
		}
	};
	var m002 = {
		xtype : "treeitem",
		text : "部门管理",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/orgnization/department.js",
		handler : function() {

		}
	};
	var m003 = {
		xtype : "treeitem",
		text : "公司管理",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/orgnization/company.js",
		handler : function() {

		}
	};
	var m004 = {
		xtype : "treeitem",
		text : "权限管理",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/orgnization/authorityManager.js",
		handler : function() {

		}
	};
	var m005 = {
		xtype : "treeitem",
		text : "组织动态",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/orgnization/dynamic.js",
		handler : function() {

		}
	};

	var m101 = {
		xtype : "treeitem",
		text : "组织结构",
		leaf : false,
		items : [ m001, m002, m003, m004, m005]
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
