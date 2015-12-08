
//=================================================================
//	�ļ�����mixky.awsoft.lib.actions.js
//=================================================================
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
//=================================================================
//	�ļ�����mixky.awsoft.lib.class.js
//=================================================================
Ext.namespace("Mixky.awsoft.lib");
// 初始化客户端对象定义
Mixky.awsoft.lib.Class = {
	defaultProperties : [
	    {
	    	name:'f_i_parent', 
	    	text:'parent', 
	    	xeditor:'none', 
	    	note:'所属对象，对象的父对象。'
	    },{
	    	name:'f_class', 
	    	text:'Class', 
	    	xeditor:'none', 
	    	note:'对象类，描述对象的类型。'
	    },{
	    	name:'f_key', 
	    	text:'Key', 
	    	xeditor:'none', 
	    	note:'对象Key，唯一标识对象。'
	    },{
	    	name:'f_name', 
	    	text:'命名', 
	    	xeditor:'string', 
	    	note:'对象命名，一般为对象的英文名称。'
	    },{
	    	name:'f_caption', 
	    	text:'名称', 
	    	xeditor:'string', 
	    	note:'对象名称，一般为对象的中文名称。'
	    },{
	    	name:'f_config', 
	    	text:'配置', 
	    	xeditor:'jsonobject', 
	    	note:'对象配置，用JSON格式配置对象的相关参数，不同的对象有不同的解析参数。'
	    },{
	    	name:'f_note', 
	    	text:'说明', 
	    	xeditor:'textbox', 
	    	note:'对象说明，说明该对象的定义、用途等描述信息。'
	    }
	],
	modules : [],
	// 注册客户端对象定义
	registeModule : function(module){
		if(module){
			this.modules.push(module);
		}
	},
	// 获得客户端对象定义
	getModule : function(name){
		for(var i=0;i<this.modules.length;i++){
			if(this.modules[i].name == name){
				return this.modules[i];
			}
		}
	},
	// 设置操作是否可用
	setActionEnabled : function(cmp){
		for(a in Mixky.awsoft.lib.Actions){
			if(Mixky.awsoft.lib.Actions[a].initialConfig.isObjectRelated){
				Mixky.awsoft.lib.Actions[a].setDisabled(true);
			}
			if(Mixky.awsoft.lib.Actions[a].menu){
				Mixky.awsoft.lib.Actions[a].menu.items.removeAll();
			}
		}
		var oid = Mixky.awsoft.lib.Context.activatedObject;
		if(!oid){
			return;
		}
		// 设置通用菜单
		Mixky.awsoft.lib.Actions.Open.setDisabled(false);
		Mixky.awsoft.lib.Actions.Refresh.setDisabled(false);
		// 清除所有添加菜单项
		var addSubMenu = Ext.menu.MenuMgr.get('add-submenu');
		addSubMenu.removeAll();
		// 清除所有扩展菜单项
		var extendsMenu = Ext.menu.MenuMgr.get('extends-menu');
		extendsMenu.removeAll();
		// 获得模块定义
		var module = this.getModule(oid.mclass);
		if(!module){
			return;
		}
		// 设置“JSON”相关菜单
		if(module.jsonable){
			Mixky.awsoft.lib.Actions.Import.setDisabled(false);
			Mixky.awsoft.lib.Actions.Export.setDisabled(false);
			Mixky.awsoft.lib.Actions.ViewJSON.setDisabled(false);
		}
		// 设置“添加”菜单
		if(module.subModules && module.subModules.length > 0){
			Mixky.awsoft.lib.Actions.Add.setDisabled(false);
			for(var i=0;i<module.subModules.length;i++){
				var m = this.getModule(module.subModules[i]);
				if(m != undefined){
					addSubMenu.add({
						text : m.text,
						module : m,
						iconCls : m.iconCls,
						handler	: function(){
							var m = this.initialConfig.module;
							Mixky.awsoft.lib.addDesignObject(oid.key, m.name,function(newkey, mclass){
								if(MixkyApp.framework.outline.refresh()){
									Mixky.awsoft.lib.Context.activateObject({id:newkey, key:newkey, mclass:mclass});
								}
								MixkyApp.framework.openObject({id:newkey, key:newkey, mclass:mclass});
							});
						}
					});
				}
			}
		}
		// 设置删除菜单
		if(module.deletable){
			Mixky.awsoft.lib.Actions.Rename.setDisabled(false);
			Mixky.awsoft.lib.Actions.Delete.setDisabled(false);
		}
		// 设置复制菜单
		if(module.copyable){
			Mixky.awsoft.lib.Actions.Copy.setDisabled(false);
		}
		// 设置粘贴菜单
		if(Mixky.awsoft.lib.Context.clipboardObject){
			if(module.subModules){
				for(var i=0;i<module.subModules.length;i++){
					if(module.subModules[i] == 'all' || Mixky.awsoft.lib.Context.clipboardObject.mclass == module.subModules[i]){
						Mixky.awsoft.lib.Actions.Paste.setDisabled(false);
						break;
					}
				}
			}
		}
		// 设置扩展功能菜单
		if(module.extendsMenu && module.extendsMenu.length>0){
			Mixky.awsoft.lib.Actions.Extends.setDisabled(false);
			extendsMenu.add(module.extendsMenu);
		}
	}
};
//=================================================================
//	�ļ�����mixky.awsoft.lib.context.js
//=================================================================
Ext.namespace("Mixky.awsoft.lib");
// 初始化应用程序上下文
Mixky.awsoft.lib.Context = {};
// 切换客户端选中的对象
Mixky.awsoft.lib.Context.activateObject = function(oid, cmp){
	// oid ：描述客户端实例的基本信息{id,key,mclass}
	// cmp ：当前选中对象的容器实例
	if(!oid){
		Mixky.awsoft.lib.Context.activatedObject = undefined;
	}else if(Mixky.awsoft.lib.Context.activatedObject && 
			oid.key == Mixky.awsoft.lib.Context.activatedObject.key && 
			oid.mclass == Mixky.awsoft.lib.Context.activatedObject.mclass){
		return;
	}else{
		Mixky.awsoft.lib.Context.activatedObject = oid;
		Mixky.awsoft.lib.Class.setActionEnabled(cmp);
		MixkyApp.framework.activateObject(oid);
	}
};

//=================================================================
//	�ļ�����mixky.awsoft.lib.js
//=================================================================
Ext.namespace("Mixky.awsoft.lib");

Mixky.awsoft.lib.showRenameWindow = function(fn, key){
	if(!key){
		var oid = Mixky.awsoft.lib.Context.activatedObject;
		if(!oid){
			alert('未指定修改键值的对象');
			return;
		}else{
			key = oid.key;
		}
	}
	
	var pKey = '';
	var index = key.lastIndexOf('.');
	if(index > 0){
		pKey = key.substr(0, index);
		key = key.substr(index + 1);
	}
	var panel = new Ext.form.FormPanel({
		labelWidth: 80,
        bodyStyle:'padding:5px',
		defaults: {
			  anchor: "100%"
		},
        defaultType: 'textfield',
		items:[{
			fieldLabel : 'Parent Key',
			name : 'parentKey',
			fieldClass : 'x-item-disabled',
			readOnly : true,
			value :pKey
		},{
			fieldLabel : 'Old Key',
			name : 'oldKey',
			fieldClass : 'x-item-disabled',
			readOnly : true,
			value : key
		},{
			fieldLabel : 'New Key',
			name : 'newKey',
			allowBlank : false,
			selectOnFocus:true,
			value : key
		}],
		isKeyChanged : function(){
			return this.getForm().findField('newKey').getValue() != this.getForm().findField('oldKey').getValue();
		},
		getNewKey : function(){
			return this.getForm().findField('newKey').getValue();
		},
		getOldKey : function(){
			var pKey = this.getForm().findField('parentKey').getValue();
			var key = this.getForm().findField('oldKey').getValue();
			if(pKey == ''){
				return key;
			}else{
				return pKey + '.' + key;
			}
		}
	});
	var selApi = {
    	onSelectedFn:function(value){
			if(panel.getForm().isValid()){
				if(!panel.isKeyChanged()){
					return true;
				}else{
					return fn(panel.getOldKey(), panel.getNewKey());
				}
			}else{
				return false;
			}
		}
	}
	var win = Mixky.lib.getFieldSelectorWindow({
		width:300,
		height:190,
		iconCls:'icon-administrator-rename',
		title:"修改设计对象键值窗口",
		items:panel
	}, selApi);
	win.show();
}

Mixky.awsoft.lib.addDesignObject = function(parentKey, mclass, fn){
	if(!parentKey){
		alert('未指定修改键值的对象');
	}
	var panel = new Ext.form.FormPanel({
		labelWidth: 80,
        bodyStyle:'padding:5px',
		defaults: {
			  anchor: "100%"
		},
        defaultType: 'textfield',
		items:[{
			fieldLabel : 'Parent Key',
			name : 'parentKey',
			fieldClass : 'x-item-disabled',
			readOnly : true,
			value :parentKey
		},{
			fieldLabel : 'Object Type',
			name : 'mclass',
			fieldClass : 'x-item-disabled',
			readOnly : true,
			value : mclass
		},{
			fieldLabel : 'Object Key',
			name : 'key',
			allowBlank : false,
			selectOnFocus:true,
			value : ''
		}]
	});
	var selApi = {
    	onSelectedFn:function(){
			if(panel.getForm().isValid()){
				var key = panel.getForm().findField('key').getValue();
				DesignObjectDirect.addObject(parentKey, mclass, key, function(result, e){
					if(result && result.success){
						fn(result.key, result.mclass);
					}else{
						alert('add object [' + parentKey + '].[' + mclass + '] failed');
					}
				});
				return true;
			}else{
				return false;
			}
		}
	}
	var win = Mixky.lib.getFieldSelectorWindow({
		width:300,
		height:190,
		iconCls:'icon-administrator-add',
		title:"创建设计对象窗口",
		items:panel
	}, selApi);
	win.show();
}
