Ext.define('MyDesktop.WordDocWindow', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'worddoc-win',

    init : function(){
        this.launcher = {
            text: 'word小记',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('worddoc-win');
        if(!win){
            win = desktop.createWindow({
                id: 'worddoc-win',
                title:'word小记',
                width:1200,
                height:540,
                iconCls: 'icon-upload',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {xtype: 'panel',
                    	id:"worddoc-win-panel",
            	        border: false,
        				layout:"fit",
            	        loader: {
            	            url: 'jsppage.do?url=worddoc/index&panelId=worddoc-win-panel',
            	            autoLoad:true,
            	            scripts:true
            	        }
                    }
                ]
            });
        }
        return win;
    },

    statics: {}
});

