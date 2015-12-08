
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.sysstate = function(){
	var state = {"name":"state","xtype":"hidden"};
	var onoffsj = {"xtype":"displayfield","anchor":"100%","name":"onoffsj","fieldLabel":"系统以已于[",labelSeparator:""};
	var sysfield = {"xtype":"displayfield","anchor":"100%","name":"sysfield","value":"] 开启"};
	var msg = {"xtype":"displayfield","anchor":"95%","name":"msg","value":"系统关闭后，所有在线用户将被强行退出，直到管理员用户重新开启系统方能登录！超级管理员能在系统关闭时登录系统，并能重新开启系统。"};
	
	var onlineuser =  new Ext.Button({"text":"在线用户","handler":Mixky.wasoft.lib.onlineusers});
	
    var btnoffsys =  new Ext.Button({"text":"关闭系统","handler":function(){
    	    Ext.MessageBox.show({title:'提示',wait:true,msg:"正在强退用户并关闭系统,请稍候...",
	        modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
    	    var item = {};
    	    item.state = form.getForm().findField('state').getValue();
    	    DesktopDirect.setOnOffSys(item,function(result,e){
				   if (result&&result.success) {
				    	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
	                    icon:Ext.Msg.INFO,width:250,closable:false});
				    	
				    	form.getForm().load({
				    		success : function(f, a){
				    			var field = f.findField('state');
				    			if(field){
				    				var statevalue = field.getValue();
				    				if(statevalue == 0){
				    					btnoffsys.setText('关闭系统');
				    				}else{
				    					btnoffsys.setText('打开系统');
				    				}
				    			}
				    		}
				    	});
			       }
			       else{
			            Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
		                icon:Ext.Msg.ERROR,width:250,closable:false});
			       }
		    });
    }});
	    
    var form = new Ext.form.FormPanel({
        autoScroll : true,
		layout:'form',
		border : false,
		fileUpload : true,
		trackResetOnLoad : true,
		bodyStyle : "padding:10px;padding-top:50px;padding-left:20px;padding-right:15px;overflow-x:hidden",
		api : {
			load :DesktopDirect.getSysState,
		},
		items : [{"xtype":"fieldset","title":"系统状态",style:"padding-top:25px;",
			     "items":[{"layout":"column","border":false,
			    	 "items": [{"layout":"form","border":false,height:30,labelWidth : 70,"style":"padding-left:0px","items":onoffsj},
			    	           {"layout":"form","border":false,height:30,"columnWidth":0.2,labelWidth : 1,"style":"padding-left:0px","items":sysfield},
			    	           {"layout":"form","border":false,height:30,"style":"padding-left:10px","items":onlineuser},
			    	           {"layout":"form","border":false,height:30,"style":"padding-left:10px","items":btnoffsys},state]},
			              {border: false, height:80, html:'<p>&nbsp;</p><p>&nbsp;</p><a>系统关闭后，所有在线用户将被强行退出，直到管理员用户重新开启系统方能登录！管理员用户能在系统关闭时登录系统，并能重新开启系统。</a>'}
                 ]}]
	});
    
    win = new Ext.Window({
        title : '系统状态管理',
        width :minwidth,
        height :minheight,
        modal : true,
        maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
        manager : MixkyApp.desktop.getManager(),
		layout : 'fit',
        items : form
    });
    win.show();
    form.getForm().load({
		success : function(f, a){
			var field = f.findField('state');
			if(field){
				var statevalue = field.getValue();
				if(statevalue == 0){
					btnoffsys.setText('关闭系统');
				}else{
					btnoffsys.setText('打开系统');
				}
			}
		}
	});
    
};