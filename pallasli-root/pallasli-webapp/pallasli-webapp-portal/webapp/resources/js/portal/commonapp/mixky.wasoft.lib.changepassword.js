
Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.ChangePassword = function(){
    var formPanel = new Ext.form.FormPanel({
		labelWidth: 100,
		frame:true,
		defaultType: 'textfield',
		bodyStyle : "padding:10px;padding-left:15px;padding-right:15px;padding-top:40px;overflow-x:hidden",
		items: [{
			xtype : 'textfield',
			fieldLabel: '原密码',
			name: 'srcpassword',
			inputType: 'password',
			anchor : '100%',
			allowBlank:false,
			labelStyle:"color:red",
			height:40
		},{
			xtype : 'textfield',
			fieldLabel: '新密码(必须是8至20位且包含字符和数字)',
			name: 'newpassword',
			inputType: 'password',
			anchor : '100%',
			allowBlank:false,
			labelStyle:"color:red",
			minLength : 8,
			minLengthText : '必须是8至20位且包含字符和数字',
			maxLength : 20,
			maskRe : new RegExp('[0-9 | A-Z | a-z]'),
			height:40
		},{
			xtype : 'textfield',
			fieldLabel: '新密码确认',
			name: 'newpassword2',
			inputType: 'password',
			anchor : '100%',
			allowBlank:false,
			labelStyle:"color:red",
			height:40
		}]
	});
    
    win = new Ext.Window({
        title : '修改密码',
        width :480,
        height :320,
        iconCls : 'icon-portal-password',
        shim : false,
        maximizable : false,
        minimizable : false,
        animCollapse :false,
        resizable :false,
        constrain : true,
        modal : true,
		layout : 'fit',
		manager : MixkyApp.desktop.getManager(),
        items : [
            formPanel
        ],
		buttons: [{
			text: '确认',
			handler: function(){
				var form = formPanel.getForm();
				if(form.isValid()){
					var srcpassword = form.findField('srcpassword').getValue();
					var newpassword = form.findField('newpassword').getValue();
					var newpassword2 = form.findField('newpassword2').getValue();
					
					if (/[0-9]+/.test(newpassword) && (/[a-z]+/.test(newpassword) || /[A-Z]+/.test(newpassword))) {
						if(newpassword == newpassword2){
							var notifyWin = MixkyApp.showWaitMessage("正在修改用户密码...");
							OrganizationDirect.changePassword(srcpassword, newpassword, function(result, e){
								if(result && result.success){
			        				notifyWin.setIconClass('x-icon-done');
			        				notifyWin.setTitle('完成');
			        				notifyWin.setMessage('用户密码修改完毕.');
									win.close();
								}else{
			        				notifyWin.setIconClass('x-icon-done');
			        				notifyWin.setTitle('错误');
			        				notifyWin.setMessage('用户密码修改失败.');
								}
								MixkyApp.hideNotification(notifyWin);
							});
						}else{
							MixkyApp.showErrorMessage("两次输入密码不一致");
						}
					}
     	     		else{
     	     			MixkyApp.showErrorMessage('输入的密码必须是8至20位且包含字符和数字！');;
     	     		}
				}
        	}
		},{
			text: '取消',
			handler: function(){
				win.close();
        	}
		}]
    });
    win.show();
    
    var npass = formPanel.getForm().findField('newpassword');
    
    if(Ext.isDefined(npass)){
    	npass.on('change', function(f,n,o){
 	     	var params = npass.getValue();
 	     	if(params.toString().trim() != ''){
     		   if (/[0-9]+/.test(params.toString().trim()) && 
     			   (/[a-z]+/.test(params.toString().trim()) || 
     			   /[A-Z]+/.test(params.toString().trim()))) {
 	   		   }
     		   else{
				   Ext.MessageBox.show({title:'提示',msg:"'输入的密码必须含字符和数字，请重新输入！",modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,
				   width:350,closable:false,fn:function(){npass.setValue('');npass.focus(false,10);}});
     		   }	
 	        }
 	   }); 
 	}
}