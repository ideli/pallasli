ExchangeProcessPage = Ext.extend(Redkit.Page, {
	
	initFormItems: function() {
		
	},
	
	initPage: function() {
		var self = this;
		var initialConfig = this.initialConfig;
		
		this.isNewMode = (initialConfig.mode.toLowerCase() == 'n');
		this.isReadMode = (initialConfig.mode.toLowerCase() == 'r');	
		
		this.setOwnerTabTitle(initialConfig.data.name);	
		
		this.fieldsetBordyStyle = Ext.isIE ? 'padding: 0px 0px 5px 20px;' : 'padding:5px 15px;';
		
	    var formPanel = new Ext.FormPanel({
	        
	        region: 'center',
	        url: initialConfig.linkSave,
	        border:false,        
	        bodyStyle:'padding:8px 8px 0',
	        readOnly: isReadMode, 
	        autoScroll: true,
	        defaults: {width: 400},        
	        
	        items: {
	            xtype:'fieldset',
	            title: '基本信息',
//	            border: false,
	            autoHeight: true,
	            collapsible: true,
	            labelAlign: 'left',
	            defaultType: isReadMode? 'displayfield':'textfield', 
  //            labelWidth: 75, // label settings here cascade unless overridden
	            bodyStyle: fieldsetBordyStyle,
		        defaults: {
		        	readOnly: isReadMode,
				    anchor:'98%'
		        },          
	        	items:
			        [{
			        	xtype: 'hidden',
			            name: 'id'          
			        },{ 	
			            fieldLabel: '名称',
			            name: 'name',            
			            allowBlank: false
			        },{ 	
			            fieldLabel: 'IP地址',
			            name: 'host'	
			        },{ 	
			        	xtype: isReadMode? 'displayfield':'numberfield',
			            fieldLabel: '端口',
			            name: 'port'
			        },{ 	
			            fieldLabel: '账号',
			            name: 'account'	
			        },{ 	
			            fieldLabel: '密码',
			            name: 'password'	
			        },{
			        	xtype: isReadMode? 'displayfield':'numberfield',
			        	fieldLabel: '部门',
			            name: 'departId'	   
			        },{ 	
			            fieldLabel: '描述',
			            name: 'comments'       			                     			            			            
			        }
			        ]
			       
			},
	
	        tbar: [{
		        	text: '关闭',
		        	iconCls: 'x-close-icon',
		        	handler: function() {
		        		self.close();
			        }
		        }, ' ',{
		        	text: '保存',
		        	iconCls: 'x-save-icon',
		        	disabled: isReadMode,
		        	hidden: isReadMode,
		        	handler: function() {
		        		//var name = formPanel.getForm().findField('name').getValue();
		        		formPanel.getForm().submit({
		        			url:initialConfig.linkSave, 
		        			waitMsg:'正在保存数据，请稍候...',
		        			success: function(form, action) {
		        			/*
		        				var responseData = Ext.decode(action.responseText);
		        				
		        				self.getWorkshop().openInTab({
		        					// self tab url code
		        					urlCode: initialConfig.uc,
		        					url: initialConfig.linkRefresh,
		        					params: {
		        						mode: 'e',
		        						id: responseData.msg
		        					}
		        				}, true);
		        			*/
	        					self.close();
		        			},
		        			failure: function(form, action) {
		        			}
		        		});
		        	}
		        }, '-', {
		        	text: '测试',
		        	handler: function() {
		        		var host = formPanel.getForm().findField('host').getValue();
		        		var port = formPanel.getForm().findField('port').getValue();
		        		
		        		var waitBox = Ext.MessageBox.wait('正在进行连接测试...', '请稍后');
						Ext.Ajax.request({
						   url: initialConfig.linkTest,
						   params: {
						   		host: host,
						   		port: port
						   },
						   success: function(response, options) {
						   		waitBox.hide();
						   		var responseData = Ext.decode(response.responseText);
						   		
						   		if (responseData.msg)
						   			Ext.MessageBox.alert('提示', responseData.msg);
						   		else if (responseData.success) 
						   			Ext.MessageBox.alert('提示', '连接测试成功');
						   		else
						   			Ext.MessageBox.alert('提示', '连接测试失败');
						   },
						   failure: function(response, options) {
						   		waitBox.hide();
						   		Ext.MessageBox.alert('错误', '连接测试失败'); 
						   }				   
						});
		        	}
		        }]

	    });  
	    
	
		this.viewport = new Ext.Viewport({
			layout: 'border',
			items: [this.formPanel]
		});   
		
		// 自动加载表单数据
		this.formPanel.getForm().loadRecord(new Ext.data.Record(this.initialConfig.data));
		// 消除自动加载数据造成的有效性检验标示
		this.formPanel.getForm().clearInvalid();
	}
});
