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