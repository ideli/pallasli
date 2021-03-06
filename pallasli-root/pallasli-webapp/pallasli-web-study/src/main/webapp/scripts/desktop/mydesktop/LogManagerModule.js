Ext.define('MyDesktop.LogManagerModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'logManager-page',

    init : function(){
        this.launcher = {
            text: '消息管理',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("/pallasli-webapp-log/");
    },

    statics: {}
});

