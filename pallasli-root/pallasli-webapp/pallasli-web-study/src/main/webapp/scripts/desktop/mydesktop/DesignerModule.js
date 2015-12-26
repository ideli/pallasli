Ext.define('MyDesktop.DesignerModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'designer-page',

    init : function(){
        this.launcher = {
            text: '上传',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("/pallasli-webapp-designer/home.do");
    },

    statics: {}
});

