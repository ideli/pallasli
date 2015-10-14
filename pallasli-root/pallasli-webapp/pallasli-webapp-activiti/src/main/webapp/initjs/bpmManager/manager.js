(function() {
	var m001 = {
		xtype : "treeitem",
		text : "流程分类",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/bpmManager/processCatalog.js",
		handler : function() {
			
		}
	};
	var m002 = {
			xtype : "treeitem",
			text : "流程图管理",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/processDesignManager.js",
			handler : function() {

			}
		};
	var m003 = {
			xtype : "treeitem",
			text : "流程授权",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/processAuthority.js",
			handler : function() {

			}
		};

	var m0001 = {
		xtype : "treeitem",
		text : "流程信息",
		leaf : true,
		target : "workPage",
		url : "../engine/view.html?jsurl=../initjs/bpmManager/myApplyedTask.js",
		handler : function() {
			
		}
	};
	var m0002 = {
			xtype : "treeitem",
			text : "节点简介",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/myReceivedTask.js",
			handler : function() {

			}
		};
	var m0003 = {
			xtype : "treeitem",
			text : "节点设置",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/myHandledTask.js",
			handler : function() {

			}
		};
	var m0004 = {
			xtype : "treeitem",
			text : "人员设置",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/myApplyedProcess.js",
			handler : function() {
				
			}
		};
		var m0005 = {
				xtype : "treeitem",
				text : "表单设置",
				leaf : true,
				target : "workPage",
				url : "../engine/view.html?jsurl=../initjs/bpmManager/myTerminatedProcess.js",
				handler : function() {

				}
			};
		var m0006 = {
				xtype : "treeitem",
				text : "跳转设置",
				leaf : true,
				target : "workPage",
				url : "../engine/view.html?jsurl=../initjs/bpmManager/myInvolvementProcess.js",
				handler : function() {

				}
			};
		var m0007 = {
				xtype : "treeitem",
				text : "意见配置",
				leaf : true,
				target : "workPage",
				url : "../engine/view.html?jsurl=../initjs/bpmManager/processOfMySubordinates.js",
				handler : function() {

				}
			};
	var m0008 = {
			xtype : "treeitem",
			text : "按钮配置",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/task_done.js",
			handler : function() {

			}
		};
	var m004 = {
			text : "流程设置",
			leaf : false,
			items : [ m0001, m0002,m0003,m0004, m0005,m0006,m0007,m0008]
		};

	var m0009 = {
			xtype : "treeitem",
			text : "表单验证表达式",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/task_done.js",
			handler : function() {

			}
		};
	var m0010 = {
			xtype : "treeitem",
			text : "表单查询SQL集合",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/task_done.js",
			handler : function() {

			}
		};
	var m0011 = {
			xtype : "treeitem",
			text : "流程表单风格",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/task_done.js",
			handler : function() {

			}
		};
	var m005 = {
			text : "表单管理",
			leaf : false,
			items : [m0009,m0010,m0011 ]
		};

	var m006 = {
			xtype : "treeitem",
			text : "流水生成规则",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/businessKeyRule.js",
			handler : function() {

			}
		};

	var m007 = {
			xtype : "treeitem",
			text : "标题生成规则",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/taskTitleRule.js",
			handler : function() {

			}
		};
	var m008 = {
			xtype : "treeitem",
			text : "消息模板",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/messageModel.js",
			handler : function() {

			}
		};
	var m009 = {
			xtype : "treeitem",
			text : "常用语",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/commonWords.js",
			handler : function() {

			}
		};
	var m010 = {
			xtype : "treeitem",
			text : "脚本管理",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/scriptManager.js",
			handler : function() {

			}
		};
	var m011 = {
			xtype : "treeitem",
			text : "意见管理",
			leaf : true,
			target : "workPage",
			url : "../engine/view.html?jsurl=../initjs/bpmManager/optionManager.js",
			handler : function() {

			}
		};

	var m101 = {
		xtype : "treeitem",
		text : "流程管理",
		leaf : false,
		items : [ m001, m002,m003,m004,m005,m006,m007,m008,m009,m010,m011]
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