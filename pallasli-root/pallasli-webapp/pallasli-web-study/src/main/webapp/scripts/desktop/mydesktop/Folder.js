Ext.define('MyDesktop.Folder', {
    extend: 'Ext.ux.desktop.Module',

    requires: ['MyDesktop.components.FolderWindow'],
    id: "folder",
    windowId: 'defaultWindow-0001',
    title:'',//文件夹标题

    init : function(){
    	var me=this;
        me.launcher = {
            text: me.title||"folder",
            iconCls:'icon-folder'
        };
    },

    createWindow : function(){
    	var me=this;
        var desktop = me.app.getDesktop();
        var win =desktop.getWindow(me.windowId);
        if(!win){
            win = desktop.createWindow({
                id: me.windowId,
                title: me.title||"folder",
                width:1200,
                height:540,
                iconCls: 'icon-folder',
                animCollapse:false,
                constrainHeader:true
           },MyDesktop.components.FolderWindow);
        }
        return win;
    }
});