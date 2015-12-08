Ext.namespace("Mixky.wasoft.lib");

Mixky.wasoft.lib.BpmAction = function(app, formKey,businessId,actionType, panelId,config) {
	
	config=config || {};
			    	
	var defaultConfig = {
		minWidth : 50
	};	
	
	var processBar;
	var beforeFn,submitFn,afterFn;	
		
	switch (actionType) {
	case 'BU1001':
		defaultConfig.text = '办理完成';
		defaultConfig.iconCls = 'icon-common-flow-processover';	
		
		defaultConfig.beforefn=function(callback){
			
			var panel=this;
			
			if (Ext.isDefined(panel.checkField)) {
				// 自定义表单校验函数校验失败
				if (panel.checkField(panel.form) === false) {
					callback(false);
					return;
				}
			}			
			
			if (Ext.isDefined(panel.formValidator)) {
				// 自定义表单校验函数校验失败
				if (panel.formValidator() === false) {
					callback(false);
					return;
				}
			}	
			
			if (Ext.isDefined(panel.bpmformValidator)) {
				// 自定义表单校验函数校验失败
				if (panel.bpmformValidator(panel.form) === false) {
					callback(false);
					return;
				}
			}			
					
			Ext.MessageBox.confirm('操作提示', '您确定要执行“办理完成”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar=panel.pb=Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	    callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
			var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
						
						if(Ext.isDefined(a.result.documentid)){						
							   panel.documentid = a.result.documentid;  
						}else if(Ext.isDefined(a.result.id)){
							   panel.documentid=a.result.id;	 						  
						}else{
							// console.log('---------not id-'+panel.documentid);
						}
						
						var id=panel.form.getForm().findField('id');
						if(id){
							id.setValue(panel.documentid);
						}				
																
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
						Ext.applyIf(formvalues,allFormData);		
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				Ext.applyIf(formvalues,allFormData);												
				callback(formvalues);		
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);	
			
				Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				         icon:Ext.Msg.INFO,width:250,closable:false});
				win.close();
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}			
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);	
			
			beforeFn=panel.beforefn || defaultConfig.beforefn;		
		    	
	    	        beforeFn.call(panel,function(isNext){
	    		
        	    		if(!isNext) return;    		
        	    		
        	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
        	        	
        	        	submitFn=panel.submitfn || defaultConfig.submitfn;
        	        	
        	        	var win = panel.findParentByType('window');
        	        	
        	        	afterFn= panel.afterfn || defaultConfig.afterfn;
        	        	
        	        	try{
        	        		submitFn.call(panel,function(data){
        	            		if(!data){
        	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
        	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
        	            			return;
        	            		}
        	            		
        	            		businessId=data.documentid || businessId || 0;
        	            		actionType=data.WF_Action || actionType;
        	            		
        	            		var nextUserList=data.WF_NextUserList || "";
        	            		var nextNodeId=data.WF_NextNodeId || "";
        	            		
        	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
        	            			data.WF_AutoRunFlag="1";
        	            		}   
        	            		
        	            		//console.log("sumbmit process data:",data);
        	            		
        	            		fn(data, formKey, businessId, actionType, function(result, e){
        	            				//console.log(result,e);
        	            				afterFn.call(panel,result,win);
        	            		});
        	            		
        	            	
        	            	}); 	
        	        	}catch(e){
        	        		//console.log(e.message);
        	        		Ext.MessageBox.hide();
        	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
        	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
        	        	}        	
        	    	
        	    	});	    	
	    	
		};	
		break;
	case 'BU1002':
		defaultConfig.text = '转他人处理';
		defaultConfig.iconCls = 'icon-common-flow-forward';
		
		defaultConfig.beforefn=function(callback){
			
			var panel=this;
			
			if (Ext.isDefined(panel.formValidator)) {
				// 自定义表单校验函数校验失败
				if (panel.formValidator() === false) {
					callback(false);
					return;
				}
			}	
			
			if (Ext.isDefined(panel.bpmformValidator)) {
				// 自定义表单校验函数校验失败
				if (panel.bpmformValidator(panel.form) === false) {
					callback(false);
					return;
				}
			}			
					
			Ext.MessageBox.confirm('操作提示', '您确定要执行“转他人处理”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	    callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag2 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
						
						if(Ext.isDefined(a.result.documentid)){						
							   panel.documentid = a.result.documentid;  
						}else if(Ext.isDefined(a.result.id)){
							   panel.documentid=a.result.id;	 						  
						}else{
							// console.log('---------not id-'+panel.documentid);
						}
						
						var id=panel.form.getForm().findField('id');
						if(id){
							id.setValue(panel.documentid);
						}				
																
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
						Ext.applyIf(formvalues,allFormData);
						formvalues.WF_NextNodeId=formvalues.WF_CurrentNodeid;
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				Ext.applyIf(formvalues,allFormData);
				formvalues.WF_NextNodeId=formvalues.WF_CurrentNodeid;
				
				callback(formvalues);		
			}
			
		};
						
						
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);					
			
			beforeFn=panel.beforefn2 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn2 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn2 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	case 'BU1003':
		defaultConfig.text = '返回给回退者';
		defaultConfig.iconCls = 'icon-common-flow-assistover';
		defaultConfig.beforefn=function(callback){
								
			Ext.MessageBox.confirm('操作提示', '您确定要执行“返回给回退者”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag3 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
						
						if(Ext.isDefined(a.result.documentid)){						
							   panel.documentid = a.result.documentid;  
						}else if(Ext.isDefined(a.result.id)){
							   panel.documentid=a.result.id;	 						  
						}else{
							 //console.log('---------not id-'+panel.documentid);
						}
						
						var id=panel.form.getForm().findField('id');
						if(id){
							id.setValue(panel.documentid);
						}				
						
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_NextNodeId=formvalues.WF_CurrentNodeid;
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_NextNodeId=formvalues.WF_CurrentNodeid;
				
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);	
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);			
			
			beforeFn=panel.beforefn3 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn3 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn3 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		
		break;
	case 'BU1004':
		defaultConfig.text = '回退上一环节';
		defaultConfig.iconCls = 'icon-common-flow-assistover';
		defaultConfig.beforefn=function(callback){
								
			Ext.MessageBox.confirm('操作提示', '您确定要执行“回退上一环节”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag4 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
					panel.form.getForm().submit({
					success : function(f,a){
												
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_Action='gotoprvuser';
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};		
									
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_Action='gotoprvuser';
											
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){			
				
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);					
			
			beforeFn=panel.beforefn4 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn4 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn4 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	case 'BU1005':
		defaultConfig.text = '回退首环节';
		defaultConfig.iconCls = 'icon-common-flow-turnback';
		defaultConfig.beforefn=function(callback){
								
			Ext.MessageBox.confirm('操作提示', '您确定要执行“回退首环节”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag5 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
						
						
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_Action="gotofirstnode";
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_Action="gotofirstnode";
				
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);				
			
			beforeFn=panel.beforefn5 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn5 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn5 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	case 'BU1006':
		defaultConfig.text = '结束流程';
		defaultConfig.iconCls = 'icon-common-flow-takeback';
		defaultConfig.beforefn=function(callback){
											
			Ext.MessageBox.confirm('操作提示', '您确定要执行“结束流程”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
			
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag6 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
																		
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_Action="gotoendprocess";						
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_Action="gotoendprocess";			
											
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);				
			
			beforeFn=panel.beforefn6 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn6 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn6 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}    
	            		
	            		data.WF_FORCE_END="1";
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	case 'BU1060':
		defaultConfig.text = '返回给转交者';
		defaultConfig.iconCls = 'icon-common-flow-request';
		defaultConfig.beforefn=function(callback){
							
			Ext.MessageBox.confirm('操作提示', '您确定要执行“返回给转交者"吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag7 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
								
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_Action='GoToBackReassignment';
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_Action='GoToBackReassignment';
											
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);					
			
			beforeFn=panel.beforefn7 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn7 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn7 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	case 'BU1061':
		defaultConfig.text = '提交给下一会签用户';
		defaultConfig.iconCls = 'icon-common-flow-stop';
		defaultConfig.beforefn=function(callback){
			
			var panel=this;
			
			if (Ext.isDefined(panel.formValidator)) {
				
				if (panel.formValidator() === false) {
					callback(false);
					return;
				}
			}	
			
			if (Ext.isDefined(panel.bpmformValidator)) {
				
				if (panel.bpmformValidator(panel.form) === false) {
					callback(false);
					return;
				}
			}			
					
			Ext.MessageBox.confirm('操作提示', '您确定要执行“提交给下一会签用户”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){			
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag8 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
						
						if(Ext.isDefined(a.result.documentid)){						
							   panel.documentid = a.result.documentid;  
						}else if(Ext.isDefined(a.result.id)){
							   panel.documentid=a.result.id;	 						  
						}else{
							 //console.log('---------not id-'+panel.documentid);
						}
						
						var id=form.getForm().findField('id');
						if(id){
							id.setValue(panel.documentid);
						}				
						
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_Action='GoToNextParallelUser';
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_Action='GoToNextParallelUser';
											
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);	
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);					
			
			beforeFn=panel.beforefn8 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn8 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn8 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	case 'BU1062':
		defaultConfig.text = '提交下一串行用户';
		defaultConfig.iconCls = 'icon-common-flow-archive';
		defaultConfig.beforefn=function(callback){
			
			var panel=this;
			
			if (Ext.isDefined(panel.formValidator)) {
				// 自定义表单校验函数校验失败
				if (panel.formValidator() === false) {
					callback(false);
					return;
				}
			}	
			
			if (Ext.isDefined(panel.bpmformValidator)) {
				// 自定义表单校验函数校验失败
				if (panel.bpmformValidator(panel.form) === false) {
					callback(false);
					return;
				}
			}			
					
			Ext.MessageBox.confirm('操作提示', '您确定要执行“提交下一串行用户”吗？', function(btn){
			    if(btn == 'yes'){
			    	processBar= Ext.MessageBox.show({title:'提示',wait:true,msg:"正在办理,请稍候...",
			        modal:true,icon:Ext.Msg.WARNING,width:250,closable:false});	
				    callback(true);
			    }else{
			    	callback(false);
			    }
		    }); 
		};
		
		defaultConfig.submitfn=function(callback){
		        var panel=this;
			var allFormData=panel.form.getForm().getFieldValues();
			var isFirstExecuteBusiness=panel.WF_ExecBusinessFlag2 || false;
			if("1"==allFormData.WF_NewDocFlag){
			    isFirstExecuteBusiness=true;
			}
			//console.log("isFirstExecuteBusiness:"+isFirstExecuteBusiness);
			if(isFirstExecuteBusiness){
				panel.form.getForm().submit({
					success : function(f,a){
						
						if(Ext.isDefined(a.result.documentid)){						
							   panel.documentid = a.result.documentid;  
						}else if(Ext.isDefined(a.result.id)){
							   panel.documentid=a.result.id;	 						  
						}else{
							 //console.log('---------not id-'+panel.documentid);
						}
						
						var id=panel.form.getForm().findField('id');
						if(id){
							id.setValue(panel.documentid);
						}				
						
						var formvalues={documentid:panel.documentid,id:panel.documentid};				
											
						var submitData=panel.getSubmitData();
						if(submitData){
							Ext.applyIf(formvalues,submitData);		
						}
						
						formvalues.WF_Action='GoToNextSerialUser';
													
						callback(formvalues);
					},
					failure : function(f, a){
						MixkyApp.showFormActionFail(f, a);
						if(processBar){
						   processBar.hide();
					    }
					}
				});
			}else{				
				
				var formvalues={documentid:panel.documentid,id:panel.documentid};				
				
				var submitData=panel.getSubmitData();
				if(submitData){
					Ext.applyIf(formvalues,submitData);		
				}
				
				formvalues.WF_Action='GoToNextSerialUser';
											
				callback(formvalues);										
				
			}
			
			
			
		};
		
		defaultConfig.afterfn=function(result,win){
			
			//console.log(this);
			
			if(processBar)processBar.hide();
			
			if(result && result.success){				
			
				//MixkyApp.showInfoMessage(result.msg);	
			    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			         icon:Ext.Msg.INFO,width:250,closable:false});
			    
				win.close();
				
				var module = MixkyApp.desktop.getCurrentModule();
				var openerId;
				if(module){
					var view = module.getCurrentView();
					if(view){
						openerId = view.getId();
					}
				}
				if(Ext.isDefined(openerId)){
					var opener = Ext.getCmp(openerId);
					if(Ext.isDefined(opener) && Ext.isDefined(opener.refresh)){
						opener.refresh();
					}
				}
				/*刷新桌面待办*/
				var sj = MixkyApp.getSubject(null,'sys-todo');
			    if(Ext.isDefined(sj)){
				    var p = Ext.getCmp('portlet-sys-todo');
				    p.refresh();
			    }
			}else{			
				Ext.MessageBox.show({title:'提示',msg:result.msg,
				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});    				
			}
		};
				
		
		defaultConfig.handler = function() {
			
			var panel=Ext.getCmp(panelId);
			if(!panel){
				Ext.MessageBox.show({title:'提示',msg:'panelId配置错误',
	  				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
				return;
			}
			
			if(config.isDocument){
				//console.log("进入文档判断，获取第一个标签");
				panel=panel.items.items[0];
			}
			
			//console.log(panel);				
			
			beforeFn=panel.beforefn9 || defaultConfig.beforefn;		
		    	
	    	beforeFn.call(panel,function(isNext){
	    		
	    		if(!isNext) return;    		
	    		
	    		var fn = eval(app.keyPrefix + 'AppDirect.runBpmProcess');
	        	
	        	submitFn=panel.submitfn9 || defaultConfig.submitfn;
	        	
	        	var win = panel.findParentByType('window');
	        	
	        	afterFn= panel.afterfn9 || defaultConfig.afterfn;
	        	
	        	try{
	        		 submitFn.call(panel,function(data){
	            		if(!data){
	            			Ext.MessageBox.show({title:'提示',msg:'未获取流程数据',
	           				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	            			return;
	            		}
	            		
	            		businessId=data.documentid || businessId || 0;
	            		actionType=data.WF_Action || actionType;
	            		
	            		var nextUserList=data.WF_NextUserList || "";
	            		var nextNodeId=data.WF_NextNodeId || "";
	            		
	            		if(actionType=='GoToNextNode' && !nextUserList && !nextNodeId){
	            			data.WF_AutoRunFlag="1";
	            		}   
	            		
	            		//console.log(data);
	            		
	            		fn(data, formKey, businessId, actionType, function(result, e){
	            				//console.log(result,e);
	            				afterFn.call(panel,result,win);
	            		});
	            		
	            	
	            	}); 	
	        	}catch(e){
	        		//console.log(e.message);
	        		Ext.MessageBox.hide();
	        		Ext.MessageBox.show({title:'提示',msg:'执行出错',
	      				 modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.ERROR,width:300,closable:false});
	        	}        	
	    	
	    	});	    	
	    	
		};	
		break;
	/*
	case 'BU1022':
		defaultConfig.text = '暂存文档';
		defaultConfig.iconCls = 'icon-common-flow-archive';
		break;
	case 'BU1023':
		defaultConfig.text = '保存为草稿';
		defaultConfig.iconCls = 'icon-common-flow-archive';
		break;
	case 'BU1024':
		defaultConfig.text = '拷贝回草稿箱';
		defaultConfig.iconCls = 'icon-common-flow-archive';
		break;
	case 'BU1190':
		defaultConfig.text = '打印处理单';
		defaultConfig.iconCls = 'icon-common-flow-archive';
		break;
	*/
	default:break;
	}	
	
	return new Ext.Action(Ext.apply(defaultConfig, config));
};