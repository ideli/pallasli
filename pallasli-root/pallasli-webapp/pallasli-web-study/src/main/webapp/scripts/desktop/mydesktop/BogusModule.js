/*!
* 伪造的菜单子项
*/

var windowIndex = 0;

Ext.define('MyDesktop.BogusModule', {
    extend: 'Ext.ux.desktop.Module',
    requires: [
           'MyDesktop.DesktopManager'
       ],
    init : function(){
    	var me=this;
        me.launcher = {
            text: 'Window '+(++windowIndex),
            iconCls:'bogus',
            handler : me.createWindow,
            scope: me,
            windowId:windowIndex
        };
    },

    createWindow : function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus'+src.windowId);
        win = MyDesktop.DesktopManager.openBogus(desktop,win,'bogus'+src.windowId,src.text);
        return win;
    }
});