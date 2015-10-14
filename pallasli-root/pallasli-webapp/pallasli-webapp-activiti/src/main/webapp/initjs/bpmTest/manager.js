(function() {
	var m001 = {
		xtype : "treeitem",
		text : "流程启动",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/bpmTest/start.js",
		handler : function() {
			
		}
	};
	var m002 = {
			xtype : "treeitem",
			text : "办理流程",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmTest/doing.js",
			handler : function() {

			}
		};
	var m003 = {
			xtype : "treeitem",
			text : "办结的流程",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmTest/finished.js",
			handler : function() {

			}
		};

	var m101 = {
		xtype : "treeitem",
		text : "流程测试",
		leaf : false,
		items : [ m001, m002,m003]
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