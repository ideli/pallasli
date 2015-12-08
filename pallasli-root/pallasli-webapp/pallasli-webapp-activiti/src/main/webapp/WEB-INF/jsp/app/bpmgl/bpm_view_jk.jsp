<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.google.gson.JsonObject"%>
<%@ page import="com.mixky.engine.authority.AuthorityDataService"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.view.View"%>
<%@ page import="com.mixky.engine.design.view.ViewDataService"%>
<%@ page import="com.mixky.engine.design.view.Column"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.module.DocumentType"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.toolkit.gson.JsonFunction"%>
<%@ page import="com.google.gson.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	String panelid = request.getParameter("panelid");
	String appkey = request.getParameter("appkey");
	View view = (View) request.getAttribute("view");
	User user = (User) request.getAttribute("user");
	List<Action> actions = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	JsonObject cfg = view.getF_config();
	// 支持自定义数据
	String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
	if (cfg != null && cfg.has("directFn")) {
		directFn = cfg.get("directFn").getAsString();
	}
	boolean autoLoad = true;
	if (cfg != null && cfg.has("autoLoad")) {
		autoLoad = cfg.get("autoLoad").getAsBoolean();
	}
	long userid = user.getId();
%>
<script language='javascript'>
//全局grid目的在弹出窗体得到此对象的引用
var grid;
//弹出窗体
function getWin(title, items) { 
	Ext.onReady(function(){
	Ext.QuickTips.init();
	var stateForm = new Ext.form.FormPanel({
               	labelAlign: 'right',
               	labelWidth: 60,
               	buttonAlign: 'center',
               	frame: true,
               	border: true,
               	modal:true,
				layout:'form',
				width:300,
                   	items: [{
							columnWidth:'1',
                       		layout: 'form',
                       		items: [{
                           		 xtype: 'datetimefield',
								 msgTarget:'under',
                           		 fieldLabel: '生效时间',
		                         emptyText: '--请选择--',
		                         name: 'validationTime',
		                         format: 'Y-m-d H:i:s',
		                         editable:false,
		                         width:200,
		                         allowBlank:true,
		                         validator:function(val){
                       					if (val.length!=0) {
                       						try{		           
							                     var date = new Date(val);
							                     var now = new Date();
							                     if (date < now) {
							                         return "选择日期要大于当前时间";
							                     }
							                     else {
							                         return true;
							                     }
                       						}catch(e){
                       							return "日期格式不正确";
                       						}
							             }else {
							                 return true;
							             }
                            	 },
                       		},{
	                             xtype: 'checkbox',
	                             boxLabel: '是否包含所有流程实例',
	                             hideLabels: true,
	                             name: 'includeAllInstances',
	                             value: 1,
	                             inputValue: 1,
	                             checked: false,
	                             width: 'auto'
                       		}]
                   	}],
               buttons: [{
                   text: '确定',
                   handler: function(){
            	    var timeStr = stateForm.getForm().findField('validationTime').getValue();
            	    var isValid = stateForm.getForm().findField('validationTime').validateValue(timeStr);
            	    
            	    if(!isValid){
            	    	return;
            	    }
            	    
            	    if(stateForm.getForm().isValid()){
                        var validationTime = stateForm.getForm().findField('validationTime').getValue();
                        var includeAllInstances = stateForm.getForm().findField('includeAllInstances').getValue();
						Ext.each(items,function(item){
							item.validationTime = validationTime;
							item.includeAllInstances = includeAllInstances;
						});
						BpmFlowDirect.activateOrSuspendProcessDefintion(items,function(result,e){
				                win.close();
								if(result.success){
									Ext.MessageBox.show({
										title:'提示',
										msg:result.msg,
										modal:true,
										buttons:Ext.Msg.OK,
				             			icon:Ext.Msg.INFO,
				             			width:250,
				             			closable:false,
				             			fn:function(){
				                           	grid.getBottomToolbar().moveFirst();
				                           	grid.getSelectionModel().clearSelections();
				      	    			}
									});
								}else{
									Ext.MessageBox.show({
										title:'提示',
										msg:result.msg,
										modal:true,
										buttons:Ext.Msg.OK,
				           				icon:Ext.Msg.ERROR,
				           				width:300,
				           				closable:false,
				           				fn:function(){
											grid.getSelectionModel().clearSelections()
				           				}
									});
								}
						});
               		}else{
               			return;
               		}
                   }
               },{
                   text: '取消',
                   handler: function(){
                       win.close();
                   }
            	}]
           });
       var win = new Ext.Window({
            title: title,
            border: false,
            modal: true,
            resizable: false,
            closeAction: 'close',
            items: [stateForm]
        });
       win.show();

	});
}


function getUnDeployWin(items){
	var stateForm = new Ext.form.FormPanel({
               	labelAlign: 'right',
               	labelWidth: 60,
               	buttonAlign: 'center',
               	frame: true,
               	border: true,
               	modal:true,
				layout:'form',
				width:300,
                items: [{
						columnWidth:'1',
                      		layout: 'form',
                      		items: [
                      		{
								xtype:'tbtext',
								text:'<center><font color=red>请注意：取消流程定义后不可恢复,是否确定！</font><center>',
								align:'center'
								
                      		}
                      		<%
                      			if(user.getF_type()==0){
                      		%>
                      		,{
	                            xtype: 'checkbox',
	                            boxLabel: '是否包含所有流程实例',
	                            hideLabels: true,
	                            name: 'includeAllInstances',
	                            value: 1,
	                            inputValue: 1,
	                            checked: false,
	                            width: 'auto'
                      		}
                      		<%
                                 }
                      		%>

                      		]
                   	}],
                buttons: [{
                   text: '确定',
                   handler: function(){   
            	    if(stateForm.getForm().isValid()){
                        var includeAllInstances = stateForm.getForm().findField('includeAllInstances').getValue();
						Ext.each(items,function(item){
							item.includeAllInstances = includeAllInstances;
						});
						
						BpmFlowDirect.unDeployProcessDefinition(items,function(result,e){
							win.close();
							if(result.success){
								Ext.MessageBox.show({
									title:'提示',
									msg:result.msg,
									modal:true,
									buttons:Ext.Msg.OK,
			             			icon:Ext.Msg.INFO,
			             			width:250,
			             			closable:false,
			             			fn:function(){
		                            	grid.getBottomToolbar().moveFirst();
		                            	grid.getSelectionModel().clearSelections();
			      	    			}
								});
							}else{
								Ext.MessageBox.show({
									title:'提示',
									msg:result.info,
									modal:true,
									buttons:Ext.Msg.OK,
		            				icon:Ext.Msg.ERROR,
		            				width:300,
		            				closable:false,
		            				fn:function(){
										grid.getSelectionModel().clearSelections();
		            				}
								});
							}
						});
               		}else{
               			return;
               		}
                   }
               },{
                   text: '取消',
                   handler: function(){
                       win.close();
                   }
            	}]
           });
	   var win = new Ext.Window({
            title: '取消流程发布',
            border: false,
            modal: true,
            resizable: false,
            closeAction: 'close',
            items: [stateForm]
        });
       win.show();
}


//主面板开始
Ext.onReady(function(){

	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		directFn : BpmFlowDirect.getBpmProcessDefinistionList,
    	paramOrder : ['limit','start','sort','dir','query','params'],
		baseParams:{limit:60, start:0, sort:'',dir:'',query:'',params:{}},
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields : ['id','name','description','deployDateTime','deploymentId','state','appKey','version','processKey']
	});
	
	var processKey=panel.initParams.processKey; 
	var appKey=panel.initParams.appKey;
	Ext.apply(store.baseParams.params, {'processKey':processKey,'appKey':appKey});
	
	var isTreeLoaded = false;
	var tree = new Ext.tree.TreePanel({
		region : 'west',
        width: 160,
        minSize: 150,
        maxSize: 400,
		split : true,
        collapsible: true,
        autoScroll:true,
        collapseMode:'mini',
        root: {
	    	text : '应用',
	    	key : ' ',
		},
        loader: new Ext.tree.TreeLoader({
            directFn : BpmFlowDirect.getAppModuleTree,
        	paramOrder : ['key',],
        	baseParams : {'key':' '},
        	preloadChildren : true,
        	listeners : {
        		'beforeload':function(loader, node){
        			var key='';
        			//加入appKey与树key的联动关系
        			if(!isTreeLoaded && appKey!=null && appKey!=''){
        				key = appKey;
        				isTreeLoaded = true;
        			}else{
        				key = node.attributes['key'];
        			}
    				Ext.apply(this.baseParams,{'key':key});
    				//Ext.apply(this.baseParams,{'key':node.attributes['key']});
        		},
        		'load':function(loader, node ,response){
	    			node.expand();
        			if(Ext.isDefined(tree.onLoadSelectKey)){
            			var sn = tree.getNodeById(tree.onLoadSelectKey);
            			if(sn){
                			sn.select();
                		}
            			tree.onLoadSelectKey = undefined;
            		}
        		}
        	}
		}),
		tools : [{
        	id : 'maximize',
        	qtip : '展开节点',
        	handler : function(){
        		tree.expandAll();
	        }
        },{
        	id : 'minimize',
        	qtip : '收合节点',
        	handler : function(){
	        	tree.collapseAll();
	        }
        },{
        	id : 'refresh',
        	qtip : '刷新选中菜单的下级菜单',
        	handler : function(){
        		tree.refresh();
	        }
        }],
        refresh : function(node){
	    	if(!node){
				node = this.getSelectionModel().getSelectedNode();
		    }
		    if(!node){
		    	node = this.getRootNode();
			}
		    node.attributes.children = undefined;
		    node.reload();
	    }
	});
	
	tree.getSelectionModel().on('selectionchange', function(sm, node){
		if(!node){
			grid.parentId = undefined;
		}else{
			grid.parentId = node.attributes.key;
			if((node.attributes.key).trim() != ''){
				var item = {};
				item.key = node.attributes.key;
				Ext.apply(store.baseParams.params, item);			
				grid.getBottomToolbar().moveFirst();
		    }
		    else{
		        var item = {};
				item.key = node.attributes.key;
				Ext.apply(store.baseParams.params, item);			
				grid.getBottomToolbar().moveFirst();
		    }
		 }
	});
	
	
	var btnQxdeploy = new Ext.Action({
		text : '删除流程',
		iconCls : 'icon-common-delete',
		handler : function(){
			var items=[];
			var selections = grid.getSelectionModel().getSelections();
			if(selections.length>0){
				for(var i=0;i<selections.length;i++){
	   				var record = selections[i];
   					items.push({
   						'deploymentId':record.get('deploymentId'),
   					});
   				}
				
				getUnDeployWin(items);
				
			}else{
				Ext.MessageBox.show({
					title:'提示',
					msg:"请选择需要删除的流程！",
			    	modal:true,
			    	buttons:Ext.Msg.OK,
			    	icon:Ext.Msg.INFO,
			    	width:250,
			    	closable:false
			    });
			}	
		}
	});
	
	
	
	var btnStateActive = new Ext.Action({
		text : '激活',
		iconCls : 'icon-common-apply',
		handler : function(){
			var items=[];
		    var activeNum=0;
		    var inactiveNum=0;
			var selections = grid.getSelectionModel().getSelections();
			if(selections.length>0){
				for(var i=0;i<selections.length;i++){
	   				var record = selections[i];
	   				if(record.get('state')=='2'){
	   					inactiveNum++;
	   					items.push({
	   						'appKey':record.get('appKey'),
	   						'processKey':record.get('processKey'),
	   						'processDefinitionId':record.get('id'),
	   						'activation':true
	   					});
	   				}else if(record.get('state')=='1'){
	   					activeNum++;
	   				}
	   			}
				if(activeNum == selections.length){
					Ext.MessageBox.show({
						title:'提示',
						msg:"请选择状态为暂停的流程",
			     		modal:true,
			     		buttons:Ext.Msg.OK,
			     		icon:Ext.Msg.INFO,
			     		width:250,
			     		closable:false
			     	});
					return;
				}else if (activeNum>0 && activeNum < selections.length){
						Ext.MessageBox.show({
							title:'提示',
							msg:"流程中包含"+activeNum+"条已激活流程将被忽略!",
				     		modal:true,
				     		buttons:Ext.Msg.OK,
				     		icon:Ext.Msg.INFO,
				     		width:250,
				     		closable:false,
				     		fn:function(){
								getWin('激活流程信息',items);
				     		}
				     	});
				}else if(inactiveNum>0){
					getWin('激活流程信息',items);
				}
			}else{
				Ext.MessageBox.show({
					title:'提示',
					msg:"请选择需要激活的流程！",
			    	modal:true,
			    	buttons:Ext.Msg.OK,
			    	icon:Ext.Msg.INFO,
			    	width:250,
			    	closable:false
			    });
			}	
		}
	});
	var btnStatePause = new Ext.Action({
		text : '暂停',
		iconCls : 'icon-common-cancel',
		handler : function(){
			var items=[];
		    var activeNum=0;
		    var inactiveNum=0;
			var selections = grid.getSelectionModel().getSelections();
			if(selections.length>0){
				for(var i=0;i<selections.length;i++){
	   				var record = selections[i];
	   				if(record.get('state')=='2'){
	   					inactiveNum++;
	   				}else if(record.get('state')=='1'){
	   					activeNum++;
	   					items.push({
	   						'appKey':record.get('appKey'),
	   						'processKey':record.get('processKey'),
	   						'processDefinitionId':record.get('id'),
	   						'activation':false
	   					});
	   				}
	   			}
				if(inactiveNum == selections.length){
					Ext.MessageBox.show({
						title:'提示',
						msg:"请选择需要暂停的流程",
			     		modal:true,
			     		buttons:Ext.Msg.OK,
			     		icon:Ext.Msg.INFO,
			     		width:250,
			     		closable:false
			     	});
					return;
				}else if(inactiveNum>0 && inactiveNum<selections.length){
						Ext.MessageBox.show({
							title:'提示',
							msg:"流程中包含"+inactiveNum+"条暂停流程将被忽略!",
				     		modal:true,
				     		buttons:Ext.Msg.OK,
				     		icon:Ext.Msg.INFO,
				     		width:250,
				     		closable:false,
				     		fn:function(){
								getWin('暂停流程信息',items);
				     		}
				     	});
				}else if(activeNum>0){
					getWin('暂停流程信息',items);
				}
				
			}else{
				Ext.MessageBox.show({
					title:'提示',
					msg:"请选择需要暂停的流程！",
			    	modal:true,
			    	buttons:Ext.Msg.OK,
			    	icon:Ext.Msg.INFO,
			    	width:250,
			    	closable:false
			    });
			}
		}
	});
	
	var btnRefresh = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-common-refresh',
		handler : function(){
			grid.getBottomToolbar().moveFirst();
		}
	});
	
	// 功能条
   var fieldQuery = new Ext.form.TwinTriggerField({
    	trigger1Class : 'x-form-search-trigger',
    	trigger2Class : 'x-form-clear-trigger',
    	width : 140,
    	enableKeyEvents : true,
    	listeners : {
    		specialkey: function(field, e){
		    	if (e.getKey() == e.ENTER) {
		    		field.onTrigger1Click();
		        }
        	}
    	},
    	onTrigger1Click : function(){
			store.baseParams.query = this.getValue();
			grid.getBottomToolbar().moveFirst();
    	},
    	onTrigger2Click : function(){
        	this.setValue('');
			store.baseParams.query = '';
			grid.getBottomToolbar().moveFirst();
    	}
	})
	
   var combo = new Ext.form.ComboBox({
	   store:new Ext.data.SimpleStore({
		   data:[
			   		['0','全部'],
			        ['1','激活'],
				   	['2','暂停'],
			    ],
		   fields:['value','text']
	   }),
	   width:80,
	   mode:'local',
	   triggerAction:'all',
	   emptyText:'全部',
	   valueField:'value',
	   displayField:'text',
	   value:'全部',
	   editable:false,
	   listeners:{
	   		select:function(combo,record,index){
	   				var state = combo.getValue();
	   				var item ={};
	   				if(state=="0"){
	   					state="";	   					
	   				}
	   				item.state = state;
		   			Ext.apply(store.baseParams.params, item);
	   				grid.getBottomToolbar().moveFirst(); 
	   				combo.emptyText = combo.getValue();
	   		}
	   }
	});
    
   var tools = ['查找 ：', fieldQuery, '-','流程状态:',combo,'->',btnQxdeploy,'-',btnStateActive,'-',btnStatePause,'-', btnRefresh]; 
	// 表格对象
   var sm = new Ext.grid.CheckboxSelectionModel();


   grid = new Ext.grid.GridPanel({
        region:'center',
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm,
		    {id : 'name', dataIndex : 'name', header : '流程名称', width : 180},
		    {id : 'description', dataIndex : 'description', header : '流程描述', width : 180},
		    {id : 'processKey', dataIndex : 'processKey', header : '流程KEY',width : 180},
		    {id : 'version', dataIndex : 'version', header : '版本号',width : 60},
		    {id : 'deployDateTime', dataIndex : 'deployDateTime', header : '发布时间', width : 135},
		    {id : 'state', dataIndex : 'state', header : '当前状态', width : 60, 
		         renderer:function renderLast(value, p, r){
		            var color ='red'; 
		    		var str = '暂停';
		            if(value=="1"){
		                str = '激活';
		                color='green';
		            }
		            return "<font color='"+color+"'>"+str+"</font>";
		         }
		    },
		    {
		      id:'oper',
		      header:'流程操作',
		      dataIndex:'state',
		      width:60,
		      renderer:function renderLast(value, p, r){
		    		 var color ='green'; 
		             var str = '激活';
		             var activation = true; 
		             if(value=="1"){
		                str = '暂停';
		                color='red';
		                activation=false;
		             }
		             var items = "[{'processKey':"+"'"+r.get('processKey')+"'"+",'appKey':"+"'"+r.get('appKey')+"'"+",'processDefinitionId':"+"'"+r.get('id')+"'"+",'activation':"+activation+"}]";
		             return '<a style="text-decoration:underline" href="javascript:getWin('+"'" +"流程"+str+"操作信息提示"+"'," + items +');">'+"<font color='"+color+"'>"+str+"</font>"+'</a>';
		         }
		    }
		    
		],
		autoExpandColumn:'description',
		enableHdMenu:false,
		enableColumnMove:false,
		store : store,
		sm : sm,
		tbar :tools,
		viewConfig:{
			getRowClass: function(record, index) {
				if(record.dirty){
					return 'mixky-grid-row-changed';
				}
		    }
		},
		listeners : {
			'rowdblclick' : function(g, rowIndex, e){
			    
				var record = store.getAt(rowIndex);
				var module = MixkyApp.desktop.getCurrentModule();
				if(module){
					module.closeView('waBpmZx.qryBpmJk.VBpmSL');
					module.openView('waBpmZx.qryBpmJk.VBpmSL',{'processKey':record.get('processKey'),'processDefinitionId':record.get('id'),'appKey':record.get('appKey')});
				}	
			},
			'render':function(g){
				var store = g.getStore();
				var view = g.getView();
				grid.tip = new Ext.ToolTip({
					target:view.mainBody,
					delegate:'.x-grid3-cell',
					trackMouse:true,
					renderTo:document.body,
					listeners:{
						beforeshow:function updateTipBody(tip){
							var rowIndex = view.findRowIndex(tip.triggerElement);
							var cellIndex = view.findCellIndex(tip.triggerElement);
							var cell = view.getCell(rowIndex,cellIndex);
							var cellVal = Ext.getDom(cell).childNodes[0].firstChild.nodeValue;
							cellVal = Ext.util.Format.trim(cellVal);
							if(Ext.isEmpty(cellVal)){
								return false;
								tip.destory();
							}
							
							if(cellIndex==0||cellIndex == 1){
								tip.body.dom.innerHTML = rowIndex+1;
							}else{
								if(cellVal != ""){
									tip.body.dom.innerHTML =cell.innerHTML;
								}
							}
						}
					}
				});
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		},
        bbar: new Ext.PagingToolbar({
        	firstText : '首页',
        	lastText : '尾页',
        	nextText : '下一页',
        	prevText : '上一页',
        	refreshText : '刷新',
        	beforePageText : '第',
        	afterPageText : '页，共 {0} 页',
        	displayMsg : '共 {2} 条，当前显示 {0} 到 {1} 条',
        	emptyMsg : '没有符合条件的数据',
            pageSize: 60,
            store: store,
            displayInfo: true,
            items : [
                '-',
                '每页显示:',
                new Ext.form.ComboBox({
                    editable : false,
                    triggerAction: 'all',
                    width : 50,
               		store : [2, 10, 20, 30, 50, 100, 200],
               		value : 60,
               		listeners : {
               			'select' : function(c, record, index){
               				grid.getBottomToolbar().pageSize = c.getValue();
               				grid.getBottomToolbar().changePage(1);
               			}
                   	}
           		})
            ]
        })
	});
 
   store.on('load', function(t,records,o){
  	   var so = store.lastOptions;
       var sop = so? so.params : null;
       var sonumber = ((sop && sop.start && sop.limit)? sop.start : 0)+ records.length;
       if(sonumber > 99 && sonumber < 1000){
       	grid.getColumnModel().setColumnWidth(0, 28);
       }
       else if(sonumber > 999 && sonumber < 10000){
       	grid.getColumnModel().setColumnWidth(0, 35);
       }
       else if(sonumber > 9999){
       	grid.getColumnModel().setColumnWidth(0, 42);
       }
       else{
           grid.getColumnModel().setColumnWidth(0, 23);
       }
	});
  

	panel.refresh = function(){
	    grid.getBottomToolbar().moveFirst();
	}
	
	var ui = new Ext.Panel({
		layout : 'border',
		border : false,
		items : [tree, grid]
	});
	panel.add(ui);
	panel.doLayout();
	tree.refresh();
	panel.refresh();
	panel.setAutoScroll(false);
}); 
</script>