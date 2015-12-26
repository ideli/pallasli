Ext.define('MyDesktop.DesktopManager', {

	init : function(){
        
    },

    openBogus: function (desktop,win,winId,winTitle) {

        if(!win){
            win = desktop.createWindow({
                id: winId,
                title:winTitle,
                width:640,
                height:480,
                html : '<p>Something useful would be in here.</p>',
                iconCls: 'bogus',
                animCollapse:false,
                constrainHeader:true
            });
        }
        win.show();
        return win;
    }
});

MyDesktop.DesktopManager=new MyDesktop.DesktopManager();