Ext.define('MyDesktop.ProjectManagerModule', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.ArrayStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
    ],

    id:'projectManager-page',

    init : function(){
        this.launcher = {
            text: '上传',
            iconCls:'icon-upload'
        };
    },

    createWindow : function(){
    	window.open("/pallasli-webapp-pm/jsppage.do?url=bugfree/ext/index");
    },

    statics: {}
});

