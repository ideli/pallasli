/*!
* 伪造的菜单父项
*/

/**
Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('Ext.ux.DataView', '../ux/DataView/');

Ext.require([
    'Ext.data.*',
    'Ext.util.*',
    'Ext.view.View',
    'Ext.ux.DataView.DragSelector',
    'Ext.ux.DataView.LabelEditor'
]);


    var store = Ext.create('Ext.data.Store', {
        model: 'ImageModel',
        proxy: {
            type: 'ajax',
            url: 'get-images.php',
            reader: {
                type: 'json',
                root: 'images'
            }
        }
    });
    store.load();

    Ext.create('Ext.Panel', {
        id: 'images-view',
        frame: true,
        collapsible: true,
        width: 535,
        renderTo: 'dataview-example',
        title: 'Simple DataView (0 items selected)',
        items: Ext.create('Ext.view.View', {
            store: store,
            tpl: [
                '<tpl for=".">',
                    '<div class="thumb-wrap" id="{name:stripTags}">',
                        '<div class="thumb"><img src="{url}" title="{name:htmlEncode}"></div>',
                        '<span class="x-editable">{shortName:htmlEncode}</span>',
                    '</div>',
                '</tpl>',
                '<div class="x-clear"></div>'
            ],
            multiSelect: true,
            height: 310,
            trackOver: true,
            overItemCls: 'x-item-over',
            itemSelector: 'div.thumb-wrap',
            emptyText: 'No images to display',
            plugins: [
                Ext.create('Ext.ux.DataView.DragSelector', {}),
                Ext.create('Ext.ux.DataView.LabelEditor', {dataIndex: 'name'})
            ],
            prepareData: function(data) {
                Ext.apply(data, {
                    shortName: Ext.util.Format.ellipsis(data.name, 15),
                    sizeString: Ext.util.Format.fileSize(data.size),
                    dateString: Ext.util.Format.date(data.lastmod, "m/d/Y g:i a")
                });
                return data;
            },
            listeners: {
                selectionchange: function(dv, nodes ){
                    var l = nodes.length,
                        s = l !== 1 ? 's' : '';
                    this.up('panel').setTitle('Simple DataView (' + l + ' item' + s + ' selected)');
                }
            }
        })
    });
**/

Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('Ext.ux.DataView', '/pallasli-web-js-css/scripts/ext/ux/DataView');
Ext.Loader.setPath('MySamples', 'scripts/samples');
Ext.define('MyDesktop.components.FolderWindow', {
    extend: 'Ext.window.Window',
    requires:['MyDesktop.components.FileModel','MyDesktop.components.ContextMenu',
                                                       'Ext.data.*',
                                                       'Ext.util.*',
                                                       'Ext.view.View',
                                                       'Ext.ux.DataView.DragSelector',
                                                       'Ext.ux.DataView.LabelEditor',
                                                       'MySamples.ExtSamplesData'
                                                   ],
    contextMenus:null,
    getTools: function(){
        return [{
            xtype: 'tool',
            type: 'gear',
            handler: function(e, target, header, tool){
                var portlet = header.ownerCt;
                portlet.setLoading('Loading...');
                Ext.defer(function() {
                    portlet.setLoading(false);
                }, 2000);
            }
        }];
    },

    initComponent: function(){
    	var me=this;
        var store = new Ext.data.Store({
               model: 'MyDesktop.components.FileModel',
               data:new MySamples.ExtSamplesData().data
           });
        	
        	  
	    var dataPrePanel=Ext.create('Ext.Panel', {
	    	layout:"fit",
	        frame: true,
	        collapsible: false,
	        title: 'Simple DataView (0 items selected)',
	        items: Ext.create('Ext.view.View', {
	            store: store,
	            tpl: [
	                '<tpl for=".">',
	                    '<div class="thumb-wrap" id="{name:stripTags}"  style="width:140px; heigh:100px; float:left; padding:5px;">',
	                        '<div class="thumb"><img src="{preImg}" title="{name:htmlEncode}"></div>',
	                        '<span class="x-editable">{shortName:htmlEncode}</span>',
	                    '</div>',
	                '</tpl>',
	                '<div class="x-clear"></div>'
	            ],
	            multiSelect: true,
	            trackOver: true,
	            overItemCls: 'x-item-over',
	            itemSelector: 'div.thumb-wrap',
	            emptyText: 'No images to display',
	            plugins: [
	                Ext.create('Ext.ux.DataView.DragSelector', {}),
	                Ext.create('Ext.ux.DataView.LabelEditor', {dataIndex: 'text'})
	            ],
	            prepareData: function(data) {
	                Ext.apply(data, {
	                    shortName: Ext.util.Format.ellipsis(data.text, 15),
	                    sizeString: Ext.util.Format.fileSize(data.size),
	                    dateString: Ext.util.Format.date(data.lastmod, "m/d/Y g:i a")
	                });
	                return data;
	            },
	            dealClick:function(type){
	            	if(type=='click'){
	                	console.log(1);
	            	}else if(type=='double'){
	                	console.log(3);
	            	}
	            },
            	task :new Ext.util.DelayedTask(),
	            listeners: {

	            	containerclick: function( ){
	                		//alert(0);
	                },
	                selectionchange: function(dv, nodes ){
	                    var l = nodes.length,
	                        s = l !== 1 ? 's' : '';
	                    this.up('panel').setTitle('Simple DataView (' + l + ' item' + s + ' selected)');
	                },
	                itemclick:function( view,record, item, index, e, eOpts ){
	                	this.task.delay(500, this.dealClick, this, ['click']);
	            	},
	            	itemcontextmenu:function( view,record, item, index, e, eOpts ){

	            		if(!me.contextMenus){
		            		me.contextMenus = new Ext.menu.Menu({ 
		            		    items: [ 
		            		        { 
		            		            text: 'open' 
		            		        }, 
		            		        { 
		            		            text: 'delete' 
		            		        } 
		            		    ] 
		            		}); 
	            		}
	            		me.contextMenus.showAt(e.xy[0],e.xy[1]);
	            		return;
	            		if(!me.contextMenus){
	            			me.contextMenus=new MyDesktop.components.ContextMenu(); 
	            		}
	            	},
	                itemdblclick:function( view,record, item, index, e, eOpts ){
	                	this.task.delay(500, this.dealClick, this, ['double']);
	            	}
	            }
	        })
	    });
            
        Ext.apply(this, {
            layout: {
                type: 'fit',
                padding: '0 5 5 5' // pad the layout from the window edges
            },
            items: [dataPrePanel]
        });
        this.on('beforeclose',function(){
        	if(me.contextMenus)me.contextMenus.close();
        	return true;
        });
        this.callParent(arguments);
    }
});