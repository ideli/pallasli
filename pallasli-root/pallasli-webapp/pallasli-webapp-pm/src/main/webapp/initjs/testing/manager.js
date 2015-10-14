(function() {
	var m001 = {
		xtype : "treeitem",
		text : "缺陷管理",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/testing/userManager.js",
		handler : function() {
			
		}
	};
	var m002 = {
		xtype : "treeitem",
		text : "测试用例",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/testing/department.js",
		handler : function() {

		}
	};
	var m003 = {
		xtype : "treeitem",
		text : "测试任务",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/testing/company.js",
		handler : function() {

		}
	};
	var m004 = {
		xtype : "treeitem",
		text : "自定义字段",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/testing/authorityManager.js",
		handler : function() {

		}
	};
	var m101 = {
		xtype : "treeitem",
		text : "测试管理",
		leaf : false,
		items : [ m001, m002, m003, m004]
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
