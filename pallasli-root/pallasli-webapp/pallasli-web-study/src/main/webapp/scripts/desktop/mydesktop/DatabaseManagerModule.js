Ext.define('MyDesktop.DatabaseManagerModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'databaseManager-page',

    init : function(){
        this.launcher = {
            text: '上传',
            iconCls:'database.jpg'
        };
    },

    createWindow : function(){
    	window.open("/pallasli-webapp-dbanalysis/home.do");
    },

    statics: {}
});

