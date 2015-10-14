(function() {
	var m001 = {
		xtype : "treeitem",
		text : "新增项目",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/addProject.js",
		handler : function() {
			
		}
	};
	var m002 = {
		xtype : "treeitem",
		text : "任务",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/task.js",
		handler : function() {

		}
	};
	var m003 = {
		xtype : "treeitem",
		text : "需求",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/story.js",
		handler : function() {

		}
	};
	var m004 = {
		xtype : "treeitem",
		text : "bug",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/bug.js",
		handler : function() {

		}
	};
	var m005 = {
		xtype : "treeitem",
		text : "build",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/build.js",
		handler : function() {

		}
	};
	var m006 = {
		xtype : "treeitem",
		text : "燃尽图",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/burn.js",
		handler : function() {

		}
	};
	var m007 = {
		xtype : "treeitem",
		text : "团队",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/team.js",
		handler : function() {

		}
	};
	var m008 = {
		xtype : "treeitem",
		text : "文档",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/doc.js",
		handler : function() {

		}
	};
	var m009 = {
		xtype : "treeitem",
		text : "产品",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/project/product.js",
		handler : function() {

		}
	};
	var m010 = {
			xtype : "treeitem",
			text : "关联需求",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/project/linkStory.js",
			handler : function() {

			}
		}; 
	var m011 = {
			xtype : "treeitem",
			text : "概况",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/project/info.js",
			handler : function() {

			}
		}; 
	var m012 = {
			xtype : "treeitem",
			text : "编辑",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/project/edit.js",
			handler : function() {

			}
		}; 

	var m101 = {
		xtype : "treeitem",
		text : "我的产品",
		leaf : false,
		items : [ m001, m002, m003, m004, m005, m006, m007, m008, m009, m010, m011 , m012 ]
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