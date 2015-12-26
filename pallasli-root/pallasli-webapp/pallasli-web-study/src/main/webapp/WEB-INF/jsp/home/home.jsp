<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工作平台</title>

<link rel="stylesheet" type="text/css"
	href="/pallasli-web-js-css/styles/desktop/desktop.css" />

<script type="text/javascript"
	src="/pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
<script type="text/javascript"
	src="<%=basePath%>/scripts/desktop/options-toolbar.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/extDirect/djn/djn-remote-call-support.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/extDirect/ejn/ejn-assert.js"></script>
<script type="text/javascript" src="<%=basePath%>/extDirect/api/api.js"></script>

<script type="text/javascript">
	Ext.Loader.setPath({
		'Ext.ux.desktop' : 'scripts/desktop/basejs',
		'MyDesktop' : 'scripts/desktop/mydesktop'
	});

	Ext.require('MyDesktop.App');
	Ext.require('MyDesktop.BogusModule');

	var myDesktopApp;
	Ext.onReady(function() {

		var JQueryMiniUIModule = {
			name : 'jQueryMiniUI',
			iconCls : 'miniui-shortcut',
			module : 'jQueryMiniUI-page'
		};
		var desigerModule = {
				name : '设计工具',
				iconCls : 'designer-shortcut',
				module : 'designer-page'
			};
		var pallasliDesigerModule = {
				name : 'bootstrap设计工具',
				iconCls : 'designer-shortcut',
				module : 'pallasli-designer-page'
			};
		var projectModule = {
			name : '项目管理',
			iconCls : 'projectManager-shortcut',
			module : 'projectManager-page'
		};
		var pallasChartModule = {
			name : '图表示例',
			iconCls : 'projectManager-shortcut',
			module : 'pallaschart-page'
		};
		var pallasJbpmModule = {
			name : '工作流管理',
			iconCls : 'bpm-shortcut',
			module : 'pallasjbpm-page'
		};
		var notepadModule = {
			name : 'Notepad',
			iconCls : 'notepad-shortcut',
			module : 'notepad'
		};
		var videoModule = {
			name : '视频About Ext JS',
			iconCls : 'video-shortcut',
			module : 'video'
		};
		var uploadModule = {
			name : '导入文件',
			iconCls : 'upload-shortcut',
			module : 'upload-win'
		};
		var summaryModule = {
			name : '学习总结',
			iconCls : 'upload-shortcut',
			module : 'summary-win'
		};
		var worddocModule = {
			name : 'Word小记',
			iconCls : 'upload-shortcut',
			module : 'worddoc-win'
		};
		var folder1Module = {
			name : 'folder',
			iconCls : 'folder-shortcut',
			module : 'folder-1'
		};
		var folder2Module = {
			name : 'Ext案例',
			iconCls : 'folder-shortcut',
			module : 'folder-2'
		};
		var databaseModule = {
				name : '数据库管理',
				iconCls : 'database-shortcut',
				module : 'databaseManager-page'
			};
		var orgnizationModule = {
				name : '组织结构管理',
				iconCls : 'projectManager-shortcut',
				module : 'orgnizationManager-page'
			};
		var serverManagerModule = {
				name : '系统管理',
				iconCls : 'projectManager-shortcut',
				module : 'serverManager-page'
			};
		var IMManagerModule = {
				name : '消息管理',
				iconCls : 'im-shortcut',
				module : 'pallasjms-page'
			};
		var logManagerModule = {
				name : '日志管理',
				iconCls : 'im-shortcut',
				module : 'logManager-page'
			};

		var tmpmodules = [];
		tmpmodules.push(new MyDesktop.SystemStatus());
		tmpmodules.push(new MyDesktop.UploadWindow());
		tmpmodules.push(new MyDesktop.VideoWindow());
		tmpmodules.push(new MyDesktop.SummaryWindow());
		tmpmodules.push(new MyDesktop.WordDocWindow());
		tmpmodules.push(new MyDesktop.Notepad());
		tmpmodules.push(new MyDesktop.BogusMenuModule());
		tmpmodules.push(new MyDesktop.BogusModule());
		tmpmodules.push(new MyDesktop.PallasliDesignerModule());
		tmpmodules.push(new MyDesktop.DesignerModule());
		tmpmodules.push(new MyDesktop.JQueryMiniUIModule());
		tmpmodules.push(new MyDesktop.ProjectManagerModule());
		tmpmodules.push(new MyDesktop.PallasChartModule());
		tmpmodules.push(new MyDesktop.PallasJbpmModule()); 
		tmpmodules.push(new MyDesktop.DatabaseManagerModule()); 
		tmpmodules.push(new MyDesktop.OrgnizationManagerModule()); 
		tmpmodules.push(new MyDesktop.ServerManagerModule()); 
		tmpmodules.push(new MyDesktop.PallasJmsModule()); 
		tmpmodules.push(new MyDesktop.LogManagerModule()); 
		

		tmpmodules.push(new MyDesktop.Folder({
			id : 'folder-1',
			windowId : 'folder-1'
		}));
		tmpmodules.push(new MyDesktop.Folder({
			title : 'Ext案例',
			id : 'folder-2',
			windowId : 'folder-2'
		}));

		myDesktopApp = new MyDesktop.App({
			shortcuts : [ desigerModule,pallasliDesigerModule, projectModule, 
					pallasJbpmModule, pallasChartModule,databaseModule,orgnizationModule,
					serverManagerModule,IMManagerModule, JQueryMiniUIModule,notepadModule,
					summaryModule, worddocModule, videoModule, uploadModule,
					folder1Module, folder2Module ,logManagerModule],
			quickStart : [ notepadModule, videoModule ],
			trayItems : [ {
				xtype : 'trayclock',
				flex : 1
			} ],
			modules : tmpmodules,
			startConfig : [ 'Settings', 'Logout' ],
			contextMenuConfigs : [ "ChangeSettings" ],
			taskbarCls : 'ux-desktop-black'
		});
	});
</script>
</head>
</html>
