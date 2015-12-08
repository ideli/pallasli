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

//主面板开始
Ext.onReady(function(){
	Ext.QuickTips.init();
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		directFn : BpmFlowDirect.findJobList,
    	paramOrder : ['limit','start','sort','dir','query','params'],
		baseParams:{limit:60, start:0,sort:'',dir:'',query:'',params:{}},
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields : ['id','processInstanceId','processDefinitionId','lockOwner','lockExpirationTime','duedate','exceptionMessage','exceptionStacktrace','retries','executionId','jobHandlerType','jobHandlerConfig']
	});
	var processKey=panel.initParams.processKey; 
	var appKey=panel.initParams.appKey;
	var processDefinitionId=panel.initParams.processDefinitionId;
	Ext.apply(store.baseParams.params, {'processKey':processKey,'appKey':appKey,'processDefinitionId':processDefinitionId});	
		  	
	var fp = new Ext.form.FormPanel({
		width:800,
		height:500,
		labelAlign:'right',
		labelWidth:80,
		border:false,
		bodyStyle:'padding:10px 40px 10px 40px',
		layout:'form',
		items:[
			{xtype:'panel',layout:'column',border:false,items:[
				{layout:'form',border:false,style:'margin-top:10px;',items:[
					{xtype:'textfield',disabled:true,fieldLabel:'作业ID',name:'id',width:250},
					{xtype:'textfield',disabled:true,fieldLabel:'执行ID',name:'executionId',width:250},
					{xtype:'textfield',disabled:true,fieldLabel:'锁过期时间',name:'lockExpirationTime',width:250},
					{xtype:'textfield',disabled:true,fieldLabel:'到期时间',name:'duedate',width:250},
					{xtype:'textfield',disabled:true,fieldLabel:'作业处理类型',name:'jobHandlerType',width:250}	
				]},
				{layout:'form',border:false,style:'margin-top:10px;',items:[
					{xtype:'textfield',disabled:true,fieldLabel:'加锁人',name:'lockOwner',width:80},
					{xtype:'textfield',disabled:true,fieldLabel:'次数',name:'retries',width:80}	
				]}
			]},
			{xtype:'textarea',disabled:true,fieldLabel:'作业处理配置',name:'jobHandlerConfig',anchor:"-10",height:50},
			{xtype:'textarea',disabled:true,fieldLabel:'异常信息',name:'exceptionMessage',anchor:"-10",height:50},
			{xtype:'textarea',disabled:true,fieldLabel:'异常堆栈信息',name:"exceptionStacktrace",anchor:"-10",height:200}
		]	
	});
	//流作业详细窗体
	var win = new Ext.Window({
			title : '流程作业详细信息',
			closeAction:'hide',
			width:800,
			heigth:500,
			frame:true,
			resizable:false,
			layout:'fit',
			constrain:true,
			modal:true,
			items:[fp]
		});
	
	var btnExecution = new Ext.Action({
		text : '执行',
		iconCls : 'icon-common-apply',
		handler : function(){
			 var items=[];
			 var records = grid.getSelectedRecords();
			 if(records.length>0){
				 for(var i=0;i<records.length;i++){
					items.push({'jobId':records[i].get('id'),'isDeleteJob':false});
				 }
				 BpmFlowDirect.executeOrDeleteJob(items,function(result,e){
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
					msg:"请选择需要执行的流程作业！",
			    	modal:true,
			    	buttons:Ext.Msg.OK,
			    	icon:Ext.Msg.INFO,
			    	width:250,
			    	closable:false
			    });
			 }
		}
	});
	var btnDeleteExecution = new Ext.Action({
		text : '删除',
		iconCls : 'icon-common-cancel',
		handler : function(){
			 var items=[];
			 var records = grid.getSelectedRecords();
			 if(records.length>0){
				 for(var i=0;i<records.length;i++){
					items.push({'jobId':records[i].get('id'),'isDeleteJob':true});
				 }
				 Ext.Msg.confirm("提示","是否确定要删除作业",function(btn){
					if(btn=='no')return;
					BpmFlowDirect.executeOrDeleteJob(items,function(result,e){
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
				});
			 }else{
				 Ext.MessageBox.show({
					title:'提示',
					msg:"请选择需要删除的流程作业！",
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
	
   var tools = ['查找 ：', fieldQuery, '-','->','-',btnExecution,'-',btnDeleteExecution,'-', btnRefresh]; 
   var sm = new Ext.grid.CheckboxSelectionModel();
   var grid = new Ext.grid.GridPanel({
        region:'center',
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm,
			{id : 'id', dataIndex : 'id', header : '编号ID', width : 135},
		    {id : 'lockOwner', dataIndex : 'lockOwner', header : '加锁人', width : 60},
		    {id : 'lockExpirationTime', dataIndex : 'lockExpirationTime', header : '锁过期时间',width : 135},
		    {id : 'duedate', dataIndex : 'duedate', header : '到期时间',width : 135},
		    {id : 'exceptionMessage', dataIndex : 'exceptionMessage', header : '异常信息', width : 135},
		    {id : 'exceptionStacktrace', dataIndex : 'exceptionStacktrace', header : '异常堆栈', width : 135},
		    {id : 'retries', dataIndex : 'retries', header : '次数', width : 60},
		    {id : 'executionId', dataIndex : 'executionId', header : '执行ID', width : 60},
		    {id : 'jobHandlerType', dataIndex : 'jobHandlerType', header : '作业处理类型', width : 60},
		    {id : 'jobHandlerConfig', dataIndex : 'jobHandlerConfig', header : '作业处理配置', width : 135} 
		],
		autoExpandColumn:'exceptionStacktrace',
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
				var record = grid.getSelectionModel().getSelected();
				fp.getForm().loadRecord(record);
				win.show();
			},
			'render':function(g){
					var store = g.getStore();
					var view = g.getView();
					grid.tip = new Ext.ToolTip({
						target:view.mainBody,
						delegate:'.x-grid3-cell',
						trackMouse:true,
						renderTo:document.body,
						width:300,
						autoScroll:true,
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
               				//Ext.apply(store.baseParams.params, {'start':0,'pageNumber':c.getValue()});	
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
});
</script>