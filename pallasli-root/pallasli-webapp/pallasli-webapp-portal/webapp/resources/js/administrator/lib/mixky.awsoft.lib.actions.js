Ext.namespace("Mixky.awsoft.lib");
// 初始化应用程序动作
Mixky.awsoft.lib.Actions = {};

Mixky.awsoft.lib.Actions.Help = new Ext.Action({
	text: '查看帮助',
    scale: 'large',
	iconAlign: 'top',
	iconCls:'icon-common-help'
});

// 切换界面外观
Mixky.awsoft.lib.switchTheme = function(){
	if(this.text == 'defalut'){
		Ext.util.CSS.swapStyleSheet('theme', '../dependences/ext/ext-3.2.1/resources/css/xcheme-blue.css');
	}else{
		Ext.util.CSS.swapStyleSheet('theme', '../resources/xtheme/css/xtheme-' + this.text + '.css');
	}
}

Mixky.awsoft.lib.Actions.Theme = new Ext.Action({
	text: '窗口风格',
    scale: 'large',
	iconAlign: 'top',
	iconCls:'icon-common-skin',
	xtype:'splitbutton',
	menu:[
	      {text:'default', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'black', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'calista', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'chocolate', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'darkgray', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'galdaka', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'gray', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'gray-extend', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'green', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'indigo', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'midnight', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'olive', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'peppermint', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'pink', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'purple', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'silverCherry', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'slate', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'slickness', handler:Mixky.awsoft.lib.switchTheme},
	      {text:'slickness2', handler:Mixky.awsoft.lib.switchTheme}
	]
});

Mixky.awsoft.lib.Actions.Exit = new Ext.Action({
	text: '退出系统',
    scale: 'large',
	iconAlign: 'top',
	iconCls:'icon-common-exit'
});

Mixky.awsoft.lib.Actions.Import = new Ext.Action({
	text: '导入',
	iconCls:'icon-common-import',
	isObjectRelated:true
});

Mixky.awsoft.lib.Actions.Export = new Ext.Action({
	text: '导出',
	iconCls:'icon-common-export',
	isObjectRelated:true
});

Mixky.awsoft.lib.Actions.ViewJSON = new Ext.Action({
	text: 'JSON',
	iconCls:'icon-common-json',
	isObjectRelated:true
});

Mixky.awsoft.lib.Actions.Refresh = new Ext.Action({
	text: '刷新',
	iconCls:'icon-common-refresh',
	isObjectRelated:true,
	handler:function(){
		MixkyApp.framework.outline.refresh();
	}
});

Mixky.awsoft.lib.Actions.Add = new Ext.Action({
	text:'添加对象',
	xtype:'splitbutton',
    iconCls: 'icon-common-add',
	iconAlign: 'top',
	arrowAlign:'right',
	rowspan:2,
	isObjectRelated:true,
	ignoreParentClicks : true,
	menu: new Ext.menu.Menu({id:'add-submenu'})
});

Mixky.awsoft.lib.Actions.Open = new Ext.Action({
	text: '打开',
	iconCls:'icon-common-open',
	isObjectRelated:true,
	handler:function(){
		var oid = Mixky.awsoft.lib.Context.activatedObject;
		if(!oid){
			alert('未指定打开对象');
		}else{
			MixkyApp.framework.outline.selectObject(oid);
			var cmp = MixkyApp.framework.workspace.openEditor(oid);
		}
	}
});

Mixky.awsoft.lib.Actions.Rename = new Ext.Action({
	text: '修改键值',
	iconCls:'icon-common-rename',
	isObjectRelated:true,
	handler:function(){
		Mixky.awsoft.lib.showRenameWindow(function(oldKey, newKey){
			DesignObjectDirect.renameObject(oldKey, newKey, function(result, e){
				if(result && result.success){
					var srckey = result.oldkey;
					var dstkey = result.newkey;
					var mclass = result.mclass;
					if(MixkyApp.framework.outline.renameObject(srckey, dstkey)){
						Mixky.awsoft.lib.Context.activateObject({id:dstkey, key:dstkey, mclass:mclass});
						if(MixkyApp.framework.workspace.renameObject(srckey)){
							MixkyApp.framework.openObject({id:dstkey, key:dstkey, mclass:mclass});
						}
					}
				}else{
					alert('rename object [' + oldName + '] to [' + newName + '] failed');
				}
			});
		});
	}
});

Mixky.awsoft.lib.Actions.Copy = new Ext.Action({
	text: '复制',
	iconCls:'icon-common-copy',
	isObjectRelated:true,
	handler:function(){
		var oid = Mixky.awsoft.lib.Context.activatedObject;
		if(!oid){
			alert('未指定复制对象');
			Mixky.awsoft.lib.Context.clipboardObject = undefined;
		}else{
			Mixky.awsoft.lib.Context.clipboardObject = oid;
		}
		// 刷新菜单
		Mixky.designtool.Class.setActionEnabled();
	}
});

Mixky.awsoft.lib.Actions.Paste = new Ext.Action({
	text: '粘贴',
	iconCls:'icon-common-paste',
	isObjectRelated:true,
	handler:function(){
		var srcoid = Mixky.awsoft.lib.Context.clipboardObject;
		var dstoid = Mixky.awsoft.lib.Context.activatedObject;
		if(!srcoid){
			alert('剪贴板为空');
			return;
		}
		if(!dstoid){
			alert('未指定粘贴对象');
			return;
		}
		Ext.Msg.prompt('粘帖对象', '请输入新对象键值:', function(btn, newkey){
		    if (btn == 'ok'){
		    	DesignObjectDirect.pasteObject(srcoid.key, dstoid.key, newkey, function(result, e){
					if(result && result.success){
						if(dstoid.mclass == srcoid.mclass){
							MixkyApp.framework.outlineTree.refreshParent();
						}else{
							MixkyApp.framework.outlineTree.refresh();
						}
						Mixky.awsoft.lib.Context.activateObject({id:result.key, key:result.key, mclass:result.mclass});
						MixkyApp.framework.openObject({id:result.key, key:result.key, mclass:result.mclass});
					}else{
						alert('paste object [' + srcoid.mclass + '][' + srcoid.key + '] to ' + '[' + dstoid.mclass + '][' + dstoid.key + '] failed');
					}
				});
		    }
		});
	}
});

Mixky.awsoft.lib.Actions.Delete = new Ext.Action({
	text: '删除',
	iconCls:'icon-common-delete',
	isObjectRelated:true,
	handler:function(){
		var oid = Mixky.awsoft.lib.Context.activatedObject;
		if(!oid){
			alert('未指定删除对象');
		}else{
			Ext.MessageBox.confirm('危险操作提示', '删除对象[' + oid.key + ']，该操作不可恢复，您确定吗？', function(btn){
				if(btn == 'yes'){
					DesignObjectDirect.deleteObject(oid.key, function(result, e){
						if(result && result.success){
							MixkyApp.framework.deleteObject(result.key);
						}
					});
				}
			});
		}
	}
});

Mixky.awsoft.lib.Actions.Save = new Ext.Action({
	text: '保存',
	iconCls:'icon-common-save',
	isObjectRelated:true,
	handler : function(){
		var panel = MixkyApp.framework.workspace.getActiveTab();
		if(!panel || panel.items.lenth == 0){
			return false;
		}
		MixkyApp.framework.workspace.savePanel(panel.get(0), true);
	}
});

Mixky.awsoft.lib.Actions.Apply = new Ext.Action({
	text: '应用',
	iconCls:'icon-common-apply',
	isObjectRelated:true,
	handler : function(){
		var panel = MixkyApp.framework.workspace.getActiveTab();
		if(!panel || panel.items == 0){
			return false;
		}
		if(panel.items.length == 1){
			MixkyApp.framework.workspace.savePanel(panel.get(0), false);
		}else{
			MixkyApp.framework.workspace.savePanel(panel.getActiveTab(), false);
		}
	}
});

//生成文件
Mixky.awsoft.lib.buildFile = function(){
	var text = this.text;
	BuilderDirect.buildJsFile(text, function(){
		MixkyApp.showInfoMessage('生成[' + text + ']文件完毕！');
	});
};

Mixky.awsoft.lib.Actions.BuildFiles = new Ext.Action({
	text: '生成文件',
	//scale: 'large',
	//iconAlign: 'top',
	//iconCls:'icon-common-build',
	xtype:'splitbutton',
	menu:[
	      {text:'all', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成所有客户端脚本'},
	      '-',
	      {text:'icon', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成图标样式CSS文件'},
	      {text:'menu', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户菜单缓存'},
	      {text:'dictionary', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户字典缓存'},
	      {text:'application', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成子系统描述文件'},
	      '-',
	      {text:'administratorclass', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成管理端对象格式描述'},
	      {text:'administratorlib', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成管理端LIB JS脚本'},
	      {text:'administratorframework', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成管理端FRAMEWORK JS脚本'},
	      '-',
	      {text:'protalcommonapp', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户COMMONAPP JS脚本'},
	      {text:'protallib', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户LIB JS脚本'},
	      {text:'protalframework', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户FRAMEWORK JS脚本'},
	      {text:'protalworkflow', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户WORKFLOW JS脚本'},
	      {text:'portaldesktop', handler:Mixky.awsoft.lib.buildFile, tooltip:'生成门户DESKTOP JS脚本'}
	]
});

Mixky.awsoft.lib.Actions.Search = new Ext.Action({
	text: '查找',
	iconCls:'icon-common-search'
});

Mixky.awsoft.lib.Actions.Extends = new Ext.Action({
	text:'扩展功能',
	xtype:'splitbutton',
    iconCls: 'icon-common-extend',
	isObjectRelated:true,
    iconAlign: 'top',
	arrowAlign:'right',
	rowspan:2,
	ignoreParentClicks : true,
	menu: new Ext.menu.Menu({id:'extends-menu'})
});



Mixky.awsoft.lib.Actions.SyncToApplication = new Ext.Action({
	text: '同步更新',
	//scale: 'large',
	//iconAlign: 'top',
	//iconCls:'icon-common-update',
	handler:function(){
		Ext.MessageBox.confirm('操作提示', '将中心服务器上改动的数据同步到各子应用系统中，您确定吗？', function(btn){
			if(btn == 'yes'){
				BuilderDirect.syncToApplication(function(result, e){
					if(result && result.success){
						MixkyApp.showInfoMessage('同步数据操作成功！');
					}
				});
			}
		});
	}
});


Mixky.awsoft.lib.Actions.SyncToIMServer = new Ext.Action({
	text: '同步到IM',
	//scale: 'large',
	//iconAlign: 'top',
	//iconCls:'icon-common-updateim',
	handler:function(){
		Ext.MessageBox.confirm('操作提示', '将组织结构同步到IM服务器，您确定吗？', function(btn){
			if(btn == 'yes'){
				MessagerAppDirect.syncToIMServer(function(result, e){
					if(result && result.success){
						MixkyApp.showInfoMessage('同步数据操作成功！');
					}
				});
			}
		});
	}
});