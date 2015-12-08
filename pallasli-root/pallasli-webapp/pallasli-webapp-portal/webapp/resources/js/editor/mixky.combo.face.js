/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */

Ext.namespace("Mixky.editor");

Mixky.editor.ComboFace = Ext.extend(Mixky.editor.PanelTriggerField, {

    confirm : true,
    
    minListWidth : 300,
    
    maxHeight : 300,
    
    minHeight : 300,
    
    resizable : false,
    
    selectMulti : false,
    
    valueAttribute : 'key',
    
    displayAttribute : 'text',
    
    valueSeparator : ',',
    
    checkWithParent : true,
    
    valueFieldName : undefined,
    
	setRealValue : function(value){
	    this.realValue = value;
		var valueFieldCmp = this.findValueFieldCmp();
		if(valueFieldCmp){
			valueFieldCmp.setValue(value);
		}
	    if(this.hiddenField){
	        this.hiddenField.value = value;
	    }
	},
	
	findValueFieldCmp : function(){
		if(Ext.isDefined(this.valueFieldName)){
			var fp = this.findParentByType('form');
			if(fp){
				return fp.getForm().findField(this.valueFieldName);
			}
		}
	},
    
    // private
    renderPanel : function(el){
		var field = this;
		
		var store = new Ext.data.JsonStore({
	        url: this.applicationkey + '/page.do?url=' + this.url,
	        root: 'images',
	        fields: ['name', 'url', 'value']
	    });
	    store.load();
	    
	    var tpl = new Ext.XTemplate(
    		'<tpl for=".">',
                '<div class="x-forum-thumb-wrap" id="{name}" img="{value}">',
    		    '<div class="x-forum-thumb"><img width=19px height=19px src="{url}" title="{name}"></div>',
    		    '</div>',
            '</tpl>',
            '<div class="x-clear"></div>'
    	);
		
		var faceView =  new Ext.DataView({
            store: store,
            tpl: tpl,
            autoHeight:true,
            multiSelect: false,
            overClass:'x-view-over',
            itemSelector:'div.x-forum-thumb-wrap',
            emptyText: 'No images to display',
            listeners: {
            	click: {
            		fn: function(dv,index,node,e){
						field.onSelect(node.getAttribute('img'), node.getAttribute('img'));
						field.collapse();
            		}
            	}
            }
        });
		
	    var panel = new Ext.Panel({
	        renderTo : el,
	        layout:'fit',
	        items:[new Ext.Panel({
	        	//height:150,
	        	autoHeight:true,
	            layout:'fit',
	        	items:[faceView]
	        })]
	    });
	    return panel;
    }

});
Ext.reg('comboface', Mixky.editor.ComboFace);