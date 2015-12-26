Ext.define('MyDesktop.SummaryWindow', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'summary-win',

    init : function(){
        this.launcher = {
            text: '知识总结',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('summary-win');
        if(!win){
            win = desktop.createWindow({
                id: 'summary-win',
                title:'知识总结',
                width:1200,
                height:540,
                iconCls: 'icon-upload',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {xtype: 'panel',
                    	id:"summary-win-panel",
            	        border: false,
        				layout:"fit",
            	        loader: {
            	            url: 'jsppage.do?url=summary/index&panelId=summary-win-panel',
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

