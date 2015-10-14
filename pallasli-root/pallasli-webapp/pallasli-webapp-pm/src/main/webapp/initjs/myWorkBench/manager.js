(function() {
	var m001 = {
		xtype : "treeitem",
		text : "新增任务",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/addTodo.js",
		handler : function() {
			
		}
	};
	var m002 = {
		xtype : "treeitem",
		text : "我的任务",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/todo.js",
		handler : function() {

		}
	};
	var m003 = {
		xtype : "treeitem",
		text : "我的bug",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/bug.js",
		handler : function() {

		}
	};
	var m004 = {
		xtype : "treeitem",
		text : "我的测试",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/test.js",
		handler : function() {

		}
	};
	var m005 = {
		xtype : "treeitem",
		text : "我的需求",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/story.js",
		handler : function() {

		}
	};
	var m006 = {
		xtype : "treeitem",
		text : "我的项目",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/project.js",
		handler : function() {

		}
	};
	var m007 = {
		xtype : "treeitem",
		text : "我的产品",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/product.js",
		handler : function() {

		}
	};
	var m008 = {
		xtype : "treeitem",
		text : "我的动态",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/dynamic.js",
		handler : function() {

		}
	};
	var m009 = {
		xtype : "treeitem",
		text : "我的营销",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/sale.js",
		handler : function() {

		}
	};
	var m010 = {
		xtype : "treeitem",
		text : "我的开发",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/development.js",
		handler : function() {

		}
	};
	var m011 = {
		xtype : "treeitem",
		text : "我的档案",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/profile.js",
		handler : function() {

		}
	};
	var m012 = {
		xtype : "treeitem",
		text : "我的设计",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/myWorkBench/design.js",
		handler : function() {

		}
	};

	var m101 = {
		xtype : "treeitem",
		text : "我的工作台",
		leaf : false,
		items : [ m001, m002, m003, m004, m005, m006, m007, m008, m009, m010,
				m011, m012 ]
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