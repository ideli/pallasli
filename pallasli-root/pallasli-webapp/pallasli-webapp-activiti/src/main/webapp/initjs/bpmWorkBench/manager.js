(function() {
	var m001 = {
		xtype : "treeitem",
		text : "发起流程",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/start.js",
		handler : function() {
			
		}
	};
	var m002 = {
			xtype : "treeitem",
			text : "我的待办",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/task_doing.js",
			handler : function() {

			}
		};
	var m003 = {
			xtype : "treeitem",
			text : "我的待阅",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/task_reading.js",
			handler : function() {

			}
		};

	var m0001 = {
		xtype : "treeitem",
		text : "我申请的",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/myApplyedTask.js",
		handler : function() {
			
		}
	};
	var m0002 = {
			xtype : "treeitem",
			text : "我收到的",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/myReceivedTask.js",
			handler : function() {

			}
		};
	var m0003 = {
			xtype : "treeitem",
			text : "我办理的",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/myHandledTask.js",
			handler : function() {

			}
		};
	var m00003 = {
			xtype : "treeitem",
			text : "我的已办",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/task_done.js",
			handler : function() {

			}
		};
	var m004 = {
			text : "我参与的任务",
			leaf : false,
			items : [ m0001, m0002,m0003,m00003]
		};
	var m0004 = {
		xtype : "treeitem",
		text : "我申请的",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/myApplyedProcess.js",
		handler : function() {
			
		}
	};
	var m0005 = {
			xtype : "treeitem",
			text : "我办结的",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/myTerminatedProcess.js",
			handler : function() {

			}
		};
	var m0006 = {
			xtype : "treeitem",
			text : "我参与的",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/myInvolvementProcess.js",
			handler : function() {

			}
		};
	var m0007 = {
			xtype : "treeitem",
			text : "我管辖的",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/processOfMySubordinates.js",
			handler : function() {

			}
		};
	var m005 = {
			text : "我参与的流程",
			leaf : false,
			items : [ m0004, m0005,m0006,m0007]
		};

	var m006 = {
			xtype : "treeitem",
			text : "代理授权",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/delegate.js",
			handler : function() {

			}
		};

	var m007 = {
			xtype : "treeitem",
			text : "我的草稿",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/draft.js",
			handler : function() {

			}
		};
	var m008 = {
			xtype : "treeitem",
			text : "流程日志",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/log.js",
			handler : function() {

			}
		};
	var m009 = {
			xtype : "treeitem",
			text : "我的常用语",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmWorkBench/commonWords.js",
			handler : function() {

			}
		};

	var m101 = {
		xtype : "treeitem",
		text : "流程中心",
		leaf : false,
		items : [ m001, m002,m003,m004,m005,m006,m007,m008,m009]
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