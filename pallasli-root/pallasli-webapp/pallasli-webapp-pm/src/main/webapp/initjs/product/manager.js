(function() {
	var m001 = {
		xtype : "treeitem",
		text : "新增产品",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/addProduct.js",
		handler : function() {
			
		}
	};
	var m002 = {
		xtype : "treeitem",
		text : "产品列表",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/productList.js",
		handler : function() {

		}
	};
	var m003 = {
		xtype : "treeitem",
		text : "需求",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/story.js",
		handler : function() {

		}
	};
	var m004 = {
		xtype : "treeitem",
		text : "计划",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/plan.js",
		handler : function() {

		}
	};
	var m005 = {
		xtype : "treeitem",
		text : "发布",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/release.js",
		handler : function() {

		}
	};
	var m006 = {
		xtype : "treeitem",
		text : "路线图",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/roadmap.js",
		handler : function() {

		}
	};
	var m007 = {
		xtype : "treeitem",
		text : "文档",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/doc.js",
		handler : function() {

		}
	};
	var m008 = {
		xtype : "treeitem",
		text : "概况",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/info.js",
		handler : function() {

		}
	};
	var m009 = {
		xtype : "treeitem",
		text : "编辑",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/edit.js",
		handler : function() {

		}
	};
	var m010 = {
		xtype : "treeitem",
		text : "模块",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/product/modules.js",
		handler : function() {

		}
	}; 

	var m101 = {
		xtype : "treeitem",
		text : "我的产品",
		leaf : false,
		items : [ m001, m002, m003, m004, m005, m006, m007, m008, m009, m010 ]
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