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
