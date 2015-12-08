<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List"%>
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
<script type="text/javascript" src="libs/templates.js"></script>
<script language='javascript'>
//复制内容到剪切板功能
function copyToClipBoard(content){
	var clipBoardContent=content;
	if(window.clipboardData){
		window.clipboardData.clearData();
		window.clipboardData.setData("text",clipBoardContent);
	}else if(navigator.userAgent.indexOf('Opera')!=-1){
		window.location = clipBoardContent;
	}else if(window.netscape){
		try{
			netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
		}catch(e){
			alert('当前浏览器设置已关闭此功能,请新开一个浏览器，在浏览器地址样输入about:config回车，找到signed.applets.codebase_principal_support项，双击设置为true');
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
		if(!clip) return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
		if(!trans) return;
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes['@mozilla.org/supports-string;1'].createInstance(Components.interfaces.nsISupportsString);
		var copytext = clipBoardContent;
		str.data = copytext;
		trans.setTransferData('text/unicode',str,copytext.length*2);
		var clipid = Components.interfaces.nsIClipboard;
		if(!clip) return false;
		clip.setData(trans,null,clipid.kGlobalClipboard);
	}
	return true;
}


Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		directFn : BpmFlowDirect.getBpmProcessInstanceList,
    	paramOrder : ['limit','start','sort','dir','query','params'],
		baseParams:{limit:60,start:0,sort:'',dir:'',query:'',params:{}},
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields : ['id','processInstanceId','startTime','endTime','startUserId','startUserName','durationTime','name','processDefintionId','remark','businessKey']
	});
	var processKey=panel.initParams.processKey; 
	var appKey=panel.initParams.appKey;
	var processDefinitionId=panel.initParams.processDefinitionId;
	Ext.apply(store.baseParams.params, {'processKey':processKey,'appKey':appKey,'processDefinitionId':processDefinitionId});	
	
	
	var btnStateActive = new Ext.Action({
		text : '激活',
		iconCls : 'icon-common-apply',
		handler : function(){
			 var items=[];
			 var records = grid.getSelectedRecords();
			 if(records.length>0){
				 for(var i=0;i<records.length;i++){
					items.push({'processInstanceId':records[i].get('processInstanceId'),'activation':true});
				 }
				 BpmFlowDirect.activateOrSuspendProcessInstance(items,function(result,e){
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
	      	    			}
						});
					}else{
						var msgInfo = result.msg + " : " +result.info;
						Ext.MessageBox.show({
							title:'提示',
							msg:msgInfo,
							modal:true,
							buttons:Ext.Msg.OK,
            				icon:Ext.Msg.ERROR,
            				width:300,
            				closable:false,
            				fn:function(){
							
            				}
						});
					}
				 });
			 }else{
				 Ext.MessageBox.show({
					title:'提示',
					msg:"请选择需要激活的流程实例！",
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
			 var records = grid.getSelectedRecords();
			 if(records.length>0){
				 for(var i=0;i<records.length;i++){
					items.push({'processInstanceId':records[i].get('processInstanceId'),'activation':false});
				 }
				 BpmFlowDirect.activateOrSuspendProcessInstance(items,function(result,e){
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
	      	    			}
						});
					}else{
						var msgInfo = result.msg + " : " +result.info;
						Ext.MessageBox.show({
							title:'提示',
							msg:msgInfo,
							modal:true,
							buttons:Ext.Msg.OK,
            				icon:Ext.Msg.ERROR,
            				width:300,
            				closable:false,
            				fn:function(){
							
            				}
						});
					}
				 });
			 }else{
				 Ext.MessageBox.show({
					title:'提示',
					msg:"请选择需要激活的流程实例！",
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
	
	var btnProcessJob = new Ext.Action({
		text:'流程作业',
		iconCls : 'icon-common-refresh',
		handler:function(){
			var module = MixkyApp.desktop.getCurrentModule();
			if(module){
				module.closeView('waBpmZx.qryBpmJk.VBpmZy');
				module.openView('waBpmZx.qryBpmJk.VBpmZy',{'processKey':processKey,'processDefinitionId':processDefinitionId,'appKey':appKey});
			}
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
	
//流程实例详细说明tab窗体
var processInstanceDetails = new Ext.Action({
	
	handler:function(processInstanceId,startUserId){

		var detailstore = new Ext.data.DirectStore({
			pruneModifiedRecords : true,
			directFn : BpmFlowDirect.getProcessInstanceDetailInfo,
	    	paramOrder : ['params'],
			baseParams:{params:{}},
			root : 'results',
			totalProperty : 'totals',
			idProperty : 'id',
			fields : ['id','processInstanceId','startTime','endTime','startUserId','startUserName','durationTime','name','processDefintionId','remark','businessKey']
		});
		
		var activitystore = new Ext.data.DirectStore({
			pruneModifiedRecords : true,
			directFn : BpmFlowDirect.getProcessInstanceActivityDetailInfo,
	    	paramOrder : ['limit','start','sort','dir','query','params'],
			baseParams:{limit:60,start:0,sort:'',dir:'',query:'',params:{}},
			root : 'results',
			totalProperty : 'totals',
			idProperty : 'activityId',
			fields:['activityId','activityName','activityType','handlerIds','startTime','endTime','durationTime','taskId','remark','id','calledProcessInstanceId','executionId','processInstanceId','processDefinitionId']
		});
		
		var taskstore = new Ext.data.DirectStore({
			pruneModifiedRecords : true,
			directFn : BpmFlowDirect.getProcessInstanceTaskInfo,
	    	paramOrder : ['limit','start','sort','dir','query','params'],
			baseParams:{limit:60,start:0,sort:'',dir:'',query:'',params:{}},
			root : 'results',
			totalProperty : 'totals',
			idProperty : 'id',
			fields:['id','name','description','category','createTime','claimTime','endTime'
				,'durationTime','dueDate','handlerIds','handlerNames','remark','formKey','ownerIds'
				,'ownerNames','priority','processInstanceId','processDefinitionId','parentTaskId','taskDefinitionKey']
		});
		
		var variablestore = new Ext.data.DirectStore({
			pruneModifiedRecords : true,
			directFn : BpmFlowDirect.getProcessInstanceVariableInfo,
	    	paramOrder : ['limit','start','sort','dir','query','params'],
			baseParams:{limit:60,start:0,sort:'',dir:'',query:'',params:{}},
			root : 'results',
			totalProperty : 'totals',
			idProperty : 'taskId',
			fields:['name','typeName','value','processInstanceId','taskId','createTime','lastUpdatedTime','endTime']
		});
		
		var eventlogstore = new Ext.data.DirectStore({
			pruneModifiedRecords : true,
			directFn : BpmFlowDirect.getProcessInstanceEventLog,
	    	paramOrder : ['limit','start','sort','dir','query','params'],
			baseParams:{limit:60,start:0,sort:'',dir:'',query:'',params:{}},
			root : 'results',
			totalProperty : 'totals',
			idProperty : 'id',
			fields:['id','type','processDefinitionId','processInstanceId','executionId','taskId','timeStamp','userId','data','lockOwner','lockTime','processed']
		});
		
		var btnCopy = new Ext.Action({
			text:'复制',
			iconCls:'icon-common-add',
			hidden : false,
			handler:function(){
				if(copyText!=""){
					copyToClipBoard(copyText);
				}else{
					Ext.MessageBox.show({
						title:'提示',
						msg:"请选择复制内容的单元格！",
				    	modal:true,
				    	buttons:Ext.Msg.OK,
				    	icon:Ext.Msg.INFO,
				    	width:250,
				    	closable:false
				    });
				}
			}
		});
	
		var sm = new Ext.grid.CellSelectionModel(); 
		var menus = [btnCopy]; 
		var grid = new Ext.grid.EditorGridPanel({
			border : false,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			store:detailstore,
			loadMask: {msg:'正在装载...'},
			columns : [
				new Ext.PagingRowNumberer(),
			    {id : 'name', dataIndex : 'name', header : '实例名称',width:100},
			   	{id : 'remark', dataIndex : 'remark', header : '实例描述',width:100},
			    {id : 'startTime', dataIndex : 'startTime', header : '创建时间',width:150},
			    {id : 'endTime', dataIndex : 'endTime', header : '结束时间',width:150},
			    {id : 'startUserId', dataIndex : 'startUserId', header : '发起人ID',width:60},
			    {id : 'startUserName', dataIndex : 'startUserName', header : '发起人',width:60},
			    {id : 'durationTime', dataIndex : 'durationTime', header : '持续时间',width:60},
			    {id : 'id', dataIndex : 'id', header : '实例ID',width:100},
			    {id : 'businessKey', dataIndex : 'businessKey', header : '业务KEY',width:100}
			],
			sm:sm,
			autoExpandColumn:'remark',
			enableHdMenu:false,
			enableColumnMove:false,
			disableSelection:true,
			viewConfig:{
	 			columnsText:'显示的列',
	 			scrollOffset:30,
	 			sortAscText:'升序',
	 			sortDescText:'降序',
	 			//forceFit:true,
	 			scrollOffset:0,
   				getRowClass: function(record, index) {
					if(record.dirty){
						return 'mixky-grid-row-changed';
					}
		    	}

	    	},
 			listeners : {
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
								
								if(cellIndex==0){
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
	            pageSize: 20,
	            store: detailstore,
	            displayInfo: true,
	            items : [
	                '-',
	                '每页显示:',
	                new Ext.form.ComboBox({
	                    editable : false,
	                    triggerAction: 'all',
	                    width : 50,
	               		store : [2, 10, 20, 30, 50, 100, 200],
	               		value : 20,
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
		
	var activitysm = new Ext.grid.CellSelectionModel();	
	var activitygrid = new Ext.grid.EditorGridPanel({
			border : false,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			store:activitystore,
			loadMask: {msg:'正在装载...'},
			columns : [
				new Ext.PagingRowNumberer(),
			    {id : 'activityId', dataIndex : 'activityId', header : '活动编号',width:300},
				{id : 'activityName', dataIndex : 'activityName', header : '活动名称',width:60},
				{id : 'activityType', dataIndex : 'activityType', header : '活动类型',width:100},
				{id : 'handlerIds', dataIndex : 'handlerIds', header : '处理ID',width:100},
				{id : 'startTime', dataIndex : 'startTime', header : '开始时间',width:150},
				{id : 'endTime', dataIndex : 'endTime', header : '结束时间',width:150},
				{id : 'durationTime', dataIndex : 'durationTime', header : '持续时间',width:60},
				{id : 'taskId', dataIndex : 'taskId', header : '任务ID',width:300},
				{id : 'remark', dataIndex : 'remark', header : '任务描述',width:100}
			],
			sm:activitysm,
			autoExpandColumn:'remark',
			enableHdMenu:false,
			enableColumnMove:false,
			viewConfig:{
   				columnsText:'显示的列',
   				scrollOffset:30,
   				sortAscText:'升序',
   				sortDescText:'降序',
   				//forceFit:true,
   				getRowClass: function(record, index) {
					if(record.dirty){
						return 'mixky-grid-row-changed';
					}
		    	}
 			},
 			contextMenu : new Ext.menu.Menu({items:menus}),
 			listeners : {
 				'cellcontextmenu' : function(g, rowIndex,cellIndex, e){
 					var cell  = activitygrid.getSelectionModel().getSelectedCell();
 					if(cell == null){
	 					Ext.MessageBox.show({
							title:'提示',
							msg:"请选择需要复制的单元格！",
					    	modal:true,
					    	buttons:Ext.Msg.OK,
					    	icon:Ext.Msg.INFO,
					    	width:250,
					    	closable:false
					    });
	 					return;
 					}
 					var fieldName = activitygrid.getColumnModel().getDataIndex(cellIndex);
 					var record = activitygrid.store.getAt(rowIndex);
 					copyText = record.get(fieldName);
					g.contextMenu.showAt(e.getXY());
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
								
								if(cellIndex==0){
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
	            store: activitystore,
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
	               				activitygrid.getBottomToolbar().pageSize = c.getValue();
	               				activitygrid.getBottomToolbar().changePage(1);
	               			}
	                   	}
	           		})
	            ]
	        })
	});
	
	var tasksm = new Ext.grid.CellSelectionModel();
	var taskgrid = new Ext.grid.EditorGridPanel({
			border : false,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			store:taskstore,
			loadMask: {msg:'正在装载...'},
			columns : [
				new Ext.PagingRowNumberer(),
			    {id : 'id', dataIndex : 'id', header : '编号ID',width:300},
				{id : 'name', dataIndex : 'name', header : '任务名称',width:100},
				{id : 'description', dataIndex : 'description', header : '任务描述',width:100},
				{id : 'category', dataIndex : 'cetegory', header : '任务分类',width:100},
				{id : 'createTime', dataIndex : 'creatTime', header : '开始时间',width:150},
				{id : 'claimTime', dataIndex : 'claimTime', header : '申请时间',width:150},
				{id : 'endTime', dataIndex : 'endTime', header : '结束时间',width:150},
				{id : 'durationTime', dataIndex : 'durationTime', header : '持续时间',width:60},
				{id : 'dueDate', dataIndex : 'dueDate', header : '到期时间',width:150},
				{id : 'handlerIds', dataIndex : 'handlerIds', header : '处理编号',width:100},
				{id : 'handlerNames', dataIndex : 'handlerNames', header : '处理名称',width:100},
				{id : 'remark', dataIndex : 'remark', header : '任务备注',width:100},
				{id : 'formKey', dataIndex : 'formKey', header : 'FormKey',width:100},
				{id : 'ownerIds', dataIndex : 'ownerIds', header : '发起人ID',width:60},
				{id : 'ownerNames', dataIndex : 'ownerNames', header : '发起人名称',width:60},
				{id : 'priority', dataIndex : 'priority', header : '优先级',width:50},
				{id : 'parentTaskId', dataIndex : 'parentTaskId', header : '父任务ID',width:100},
				{id : 'taskDefinitionKey', dataIndex : 'taskDefinitionKey', header : '任务定义KEY',width:300}
			],
			sm:tasksm,
			autoExpandColumn:'remark',
			enableHdMenu:false,
			enableColumnMove:false,
			viewConfig:{
   				columnsText:'显示的列',
   				scrollOffset:30,
   				sortAscText:'升序',
   				sortDescText:'降序',
   				//forceFit:true,
   				getRowClass: function(record, index) {
					if(record.dirty){
						return 'mixky-grid-row-changed';
					}
		    	}
 			},
 			contextMenu : new Ext.menu.Menu({items:menus}),
 			listeners : {
 				'cellcontextmenu' : function(g, rowIndex,cellIndex, e){
 					var cell  = taskgrid.getSelectionModel().getSelectedCell();
 					if(cell == null){
	 					Ext.MessageBox.show({
							title:'提示',
							msg:"请选择需要复制的单元格！",
					    	modal:true,
					    	buttons:Ext.Msg.OK,
					    	icon:Ext.Msg.INFO,
					    	width:250,
					    	closable:false
					    });
	 					return;
 					}
 					var fieldName = taskgrid.getColumnModel().getDataIndex(cellIndex);
 					var record = tasktgrid.store.getAt(rowIndex);
 					copyText = record.get(fieldName);
					g.contextMenu.showAt(e.getXY());
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
								
								if(cellIndex==0){
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
	            store: taskstore,
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
	               				taskgrid.getBottomToolbar().pageSize = c.getValue();
	               				taskgrid.getBottomToolbar().changePage(1);
	               			}
	                   	}
	           		})
	            ]
	        })
	});
	
	var variablesm = new Ext.grid.CellSelectionModel();
	var variablegrid = new Ext.grid.EditorGridPanel({
			border : false,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			store:variablestore,
			loadMask: {msg:'正在装载...'},
			columns : [
				new Ext.PagingRowNumberer(),
				{id : 'name', dataIndex : 'name', header : '变量名称',width:300},
				{id : 'typeName', dataIndex : 'typeName', header : '类型名称',width:100},
				{id : 'value', dataIndex : 'value', header : '变量值',width:100},
				{id : 'taskId', dataIndex : 'taskId', header : '任务ID',width:300},
				{id : 'createTime', dataIndex : 'createTime', header : '创建时间',width:150},
				{id : 'lastUpdatedTime', dataIndex : 'lastUpdatedTime', header : '更新时间',width:150},
				{id : 'endTime', dataIndex : 'endTime', header : '结束时间',width:150}
			],
			sm:variablesm,
			autoExpandColumn:'remark',
			enableHdMenu:false,
			enableColumnMove:false,
			viewConfig:{
   				columnsText:'显示的列',
   				scrollOffset:30,
   				sortAscText:'升序',
   				sortDescText:'降序',
   				forceFit:true,
   				getRowClass: function(record, index) {
					if(record.dirty){
						return 'mixky-grid-row-changed';
					}
		    	}
 			},
 			contextMenu : new Ext.menu.Menu({items:menus}),
 			listeners : {
 				'cellcontextmenu' : function(g, rowIndex,cellIndex, e){
 					var cell  = variablegrid.getSelectionModel().getSelectedCell();
 					if(cell == null){
	 					Ext.MessageBox.show({
							title:'提示',
							msg:"请选择需要复制的单元格！",
					    	modal:true,
					    	buttons:Ext.Msg.OK,
					    	icon:Ext.Msg.INFO,
					    	width:250,
					    	closable:false
					    });
	 					return;
 					}
 					var fieldName = variablegrid.getColumnModel().getDataIndex(cellIndex);
 					var record = variablegrid.store.getAt(rowIndex);
 					copyText = record.get(fieldName);
					g.contextMenu.showAt(e.getXY());
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
								
								if(cellIndex==0){
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
	            store: variablestore,
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
	               				variablegrid.getBottomToolbar().pageSize = c.getValue();
	               				variablegrid.getBottomToolbar().changePage(1);
	               			}
	                   	}
	           		})
	            ]
	        })
	});

	
	var eventlogsm = new Ext.grid.CellSelectionModel();
	var eventloggrid = new Ext.grid.EditorGridPanel({
			border : false,
			stripeRows: true,
			lineBreak : false,
			cellSelect : true,
			store:eventlogstore,
			loadMask: {msg:'正在装载...'},
			columns : [
				new Ext.PagingRowNumberer(),
				{id : 'id', dataIndex : 'id', header : '编号ID',width:80},
				{id : 'timeStamp', dataIndex : 'timeStamp', header : '时间戳',width:150},
				{id : 'userId', dataIndex : 'userId', header : '用户ID',width:60},
				{id : 'data', dataIndex : 'data', header : '数据',width:500},
				{id : 'lockOwner', dataIndex : 'lockOwner', header : '加锁人',width:60},
				{id : 'lockTime', dataIndex : 'lockTime', header : '加锁时间',width:150},
				{id : 'processed', dataIndex : 'processed', header : '是否已执行',width:60},
				{id : 'type', dataIndex : 'type', header : '日志类型',width:150},
				{id : 'executionId', dataIndex : 'executionId', header : '执行ID',width:300},
				{id : 'taskId', dataIndex : 'taskId', header : '任务ID',width:300}
			],
			sm:eventlogsm,
			autoExpandColumn:'data',
			enableHdMenu:false,
			enableColumnMove:false,
			viewConfig:{
   				columnsText:'显示的列',
   				scrollOffset:30,
   				sortAscText:'升序',
   				sortDescText:'降序',
   				//forceFit:true,
   				getRowClass: function(record, index) {
					if(record.dirty){
						return 'mixky-grid-row-changed';
					}
		    	}
 			},
 			contextMenu : new Ext.menu.Menu({items:menus}),
 			listeners : {
 				'cellcontextmenu' : function(g, rowIndex,cellIndex, e){
 					var cell  = eventloggrid.getSelectionModel().getSelectedCell();
 					if(cell == null){
	 					Ext.MessageBox.show({
							title:'提示',
							msg:"请选择需要复制的单元格！",
					    	modal:true,
					    	buttons:Ext.Msg.OK,
					    	icon:Ext.Msg.INFO,
					    	width:250,
					    	closable:false
					    });
	 					return;
 					}
 					var fieldName = eventloggrid.getColumnModel().getDataIndex(cellIndex);
 					var record = eventloggrid.store.getAt(rowIndex);
 					copyText = record.get(fieldName);
					g.contextMenu.showAt(e.getXY());
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
								
								if(cellIndex==0){
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
	            store: eventlogstore,
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
	               				eventloggrid.getBottomToolbar().pageSize = c.getValue();
	               				eventloggrid.getBottomToolbar().changePage(1);
	               			}
	                   	}
	           		})
	            ]
	        })
	});
	
		var isActivityLoaded = false;
		var isTaskLoaded = false;
		var isVariableLoaded = false;
		var isEventLogLoaded = false;
		var isDiagramLoaded = false;
        var tabpanel = new Ext.TabPanel({
                activeTab: 0,
                tabPosition: 'top',
                enableTabScroll: true,
				deferredRender:false,
				border:false,
				resizeTabs:true,
				height:585,
				tabwidth:200,
                items: [{
                         title: '实例信息',
                         xtype: 'panel',
                         closable: false,
                         layout:'fit',
                         listeners: {
                             render:function(p){
		                				Ext.apply(detailstore.baseParams.params,{'processInstanceId':processInstanceId});
		                				 detailstore.load({
											callback:function(records,options,success){
												if(!success){
													Ext.MessageBox.show({
														title:'提示',
														msg:"加载数据失败",
												    	modal:true,
												    	buttons:Ext.Msg.OK,
												    	icon:Ext.Msg.INFO,
												    	width:250,
												    	closable:false
												    });
												}
											}
											
										});
                             }
                         },
						 items:[grid]
                     	}, {
                         		title: '活动信息',
                         		xtype: 'panel',
                         		closable: false,
                         		layout:'fit',
                         		listeners: {
                     				activate:function(p){
                     						if(!isActivityLoaded){
												Ext.apply(activitystore.baseParams.params,{'processInstanceId':processInstanceId});
												    isloaded = activitystore.load({
													callback:function(records,options,success){
														if(!success){
															Ext.MessageBox.show({
																title:'提示',
																msg:"加载数据失败",
														    	modal:true,
														    	buttons:Ext.Msg.OK,
														    	icon:Ext.Msg.INFO,
														    	width:250,
														    	closable:false
														    });
														}
														isActivityLoaded = true;
													}
												});
                     						}
                     				}
                         		},
                         items:[activitygrid]
                    	 },{
		                         title: '任务信息',
		                         xtype: 'panel',
		                         closable: false,
		                         layout:'fit',
		                         listeners: {
		                             activate:function(p){
                    		 				if(!isTaskLoaded){
		                    		 			Ext.apply(taskstore.baseParams.params,{'processInstanceId':processInstanceId});
												taskstore.load({
													callback:function(records,options,success){
														if(!success){
															Ext.MessageBox.show({
																title:'提示',
																msg:"加载数据失败",
														    	modal:true,
														    	buttons:Ext.Msg.OK,
														    	icon:Ext.Msg.INFO,
														    	width:250,
														    	closable:false
														    });
														}
														isTaskLoaded = true;
													}
												});
                    		 				}
		                             }
                        		 },
                         items:[taskgrid]
                     	 },{
		                         title: '变量信息',
		                         xtype: 'panel',
		                         closable: false,
		                         layout:'fit',
		                         listeners: {
		                             activate:function(p){
                     		 				if(!isVariableLoaded){
		                    		 			Ext.apply(variablestore.baseParams.params,{'processInstanceId':processInstanceId});
												variablestore.load({
													callback:function(records,options,success){
														if(!success){
															Ext.MessageBox.show({
																title:'提示',
																msg:"加载数据失败",
														    	modal:true,
														    	buttons:Ext.Msg.OK,
														    	icon:Ext.Msg.INFO,
														    	width:250,
														    	closable:false
														    });
														}
														isVariableLoaded = true;
													}
												});
                     		 				}
		                             }
		                         },
		                 items:[variablegrid]
						 },{
		                         title: '日志信息',
		                         xtype: 'panel',
		                         closable: false,
		                         layout:'fit',
		                         listeners: {
		                             activate:function(p){
							 				if(!isEventLogLoaded){
		                    		 			Ext.apply(eventlogstore.baseParams.params,{'processInstanceId':processInstanceId});
												eventlogstore.load({
													callback:function(records,options,success){
														if(!success){
															Ext.MessageBox.show({
																title:'提示',
																msg:"加载数据失败",
														    	modal:true,
														    	buttons:Ext.Msg.OK,
														    	icon:Ext.Msg.INFO,
														    	width:250,
														    	closable:false
														    });
														}
														isEventLogLoaded = true;
													}
												});
							 				}
		                             }
		                         },
		                 items:[eventloggrid]
						 },{
		                         title: '查看流程图',
		                         xtype: 'panel',
		                         closable: false,
		                         layout:'fit',
		                         listeners: {
		                            activate: function(p){
							 					if(!isDiagramLoaded){
									 				var params = {'user':startUserId,'processKey':processKey,'processInstanceId':processInstanceId};
									 				var el = p.getEl();
									 				el.mask('加载中...');
									 				BpmFlowDirect.getProcessDiagram(params,function(result,e){
				                             			if(result.success){
									 					    el.unmask();
					                             			var contextPath = <%=request.getContextPath()+"/"%>;
						                             		if(!p.get('imgBox'))return;
						                             		p.get('imgBox').getEl().dom.src = contextPath + result.src;
						                             		isDiagramLoaded = true;
				                             			}else{
				                             				el.unmask();
				                             				Ext.MessageBox.show({
																title:'提示',
																msg:"加载数据失败",
														    	modal:true,
														    	buttons:Ext.Msg.OK,
														    	icon:Ext.Msg.INFO,
														    	width:250,
														    	closable:false
														    });
				                             			}
				                             	    });
							 					}
						 			}
		                         },
		                         items:[
		                        	 {
		                        		 id:'imgBox',
		                        		 xtype:'box',
		                        		 width:1003,
		                        		 height:585,
		                        		 autoEl:{
		                        		 	tag:'img',
		                        		 	src: ''
		                        		 }
		                        	 }
		                         ]
				         }]
        });

		var win = new Ext.Window({
			title : '流程实例详细信息',
			closeAction:'close',
			width:1003,
			heigth:585,
			frame:true,
			border:false,
			resizable:false,
			layout:'fit',
			constrain:true,
			modal:true,
			items:[tabpanel]
		});
		win.show();
	}
});
    
   var tools = ['查找 ：', fieldQuery, '-','->',btnProcessJob,'-',btnStateActive,'-',btnStatePause,'-', btnRefresh]; 
   var sm = new Ext.grid.CheckboxSelectionModel();
   var grid = new Ext.grid.GridPanel({
        region:'center',
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm,
		    {id : 'name', dataIndex : 'name', header : '实例名称', width : 60},
		    {id : 'businessKey', dataIndex : 'businessKey', header : '业务KEY', width : 60},
		    {id : 'startTime', dataIndex : 'startTime', header : '创建时间', width : 100},
		    {id : 'endTime', dataIndex : 'endTime', header : '结束时间', width : 100},
		    {id : 'startUserId', dataIndex : 'startUserId', header : '发起人ID', width : 100},
		    {id : 'startUserName', dataIndex : 'startUserName', header : '发起人', width : 100},
		    {id : 'durationTime', dataIndex : 'durationTime', header : '持续时间', width : 100},
		    {id : 'processInstanceId', dataIndex : 'processInstanceId', header : '实例ID', width : 100},
		    {id : 'remark', dataIndex : 'remark', header : '实例描述', width : 180},
		],
		autoExpandColumn:'remark',
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
				var processInstanceId = store.getAt(rowIndex).get('processInstanceId');
				var startUserId = store.getAt(rowIndex).get('startUserId');
				processInstanceDetails.execute(processInstanceId,startUserId);
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
		items : [grid]
	});
	
	panel.add(ui);
	panel.doLayout();
	panel.refresh();
	panel.setAutoScroll(false);
	
	//
	
	
});
</script>