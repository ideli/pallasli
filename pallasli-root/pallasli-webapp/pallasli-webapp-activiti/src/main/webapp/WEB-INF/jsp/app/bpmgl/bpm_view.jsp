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
	View view = (View)request.getAttribute("view");
	User user = (User)request.getAttribute("user");
	List<Action> actions = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_buttons(), user);
	List<Column> columns = AuthorityDataService.instance().getDesignObjectsByUser(view.getF_columns(), user);
	JsonObject cfg = view.getF_config();
	// 支持自定义数据

	String directFn = "eval(app.keyPrefix + 'AppDirect.getViewResults')";
	if(cfg != null && cfg.has("directFn")){
		directFn = cfg.get("directFn").getAsString();
	}
	boolean autoLoad = true;
	if(cfg != null && cfg.has("autoLoad")){
		autoLoad = cfg.get("autoLoad").getAsBoolean();
	}
	long userid = user.getId();
%>
<script language='javascript'>


Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
    
	// 数据访问
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		directFn : BpmFlowDirect.getBpmFlowList,
    	paramOrder : ['limit','start','sort','dir','query','params'],
		baseParams:{limit:60, start:0, sort:'',dir:'',query:'',params:{}},
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		successProperty: 'success',
		fields : ['id','name','description','createTime','lastUpdateTime','deployment','deploymentTime','appKey','bpmbtn','processKey'],
		listeners: {
	        exception: function (proxy, type, action, options, response) {
	            MixkyApp.showInfoMessage(response.result.info);
	        }
    	}
	});
	
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
    				Ext.apply(this.baseParams,{'key':node.attributes['key']});
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
			    btnAdd.show();
			    btnCopy.show();
				var item = {};
				item.key = node.attributes.key;
				Ext.apply(store.baseParams.params, item);			
				grid.getBottomToolbar().moveFirst();
		    }
		    else{
		        btnAdd.hide();
		        btnCopy.hide();
		        var item = {};
				item.key = node.attributes.key;
				Ext.apply(store.baseParams.params, item);			
				grid.getBottomToolbar().moveFirst();
		    }
		 }
	});
	
	this.canelProcess = function(item){
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
                        item.includeAllInstances = includeAllInstances;
						win.close();
                        Ext.MessageBox.show({title:'提示',wait:true,msg:"正在取消发布的流程,请稍候...",
				    	modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
				    
				    	BpmFlowDirect.unDeployProcess(item, function(result, e){
					    	if(result.success){
					         	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				             	icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){
	                              	grid.getBottomToolbar().moveFirst();
	                              	grid.getSelectionModel().clearSelections();
				      	     	}});
				        	}
				        	else{
				            	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
				            	icon:Ext.Msg.ERROR,width:300,closable:false,fn:function(){}});
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
	
	 
	var btnAdd = new Ext.Action({
		text:'新建流程',
		iconCls:'icon-common-add',
		hidden : true,
		handler:function(){
		     var key = grid.parentId;
		     if(key.trim() != ''){
		         panel.createProcess(grid.parentId);
		     }
		     else{
		         Ext.MessageBox.show({title:'提示',msg:"请先选择左边应用！",
			     modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
		     }
		}
	});
	
	var btnCopy = new Ext.Action({
		text:'复制流程',
		iconCls:'icon-common-copy',
		hidden : true,
		handler:function(){
		    var records = grid.getSelectedRecords();
			if(records.length > 0){ 
			    if(records.length > 1){
			        Ext.MessageBox.show({title:'提示',msg:"只能选择一条流程进行复制！",
			        modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			    }
			    else{
			         var id = records[0].get('id'); 
			         var key = grid.parentId;
			         var name = records[0].get('name');
			         var description = records[0].get('description');
			         panel.copyProcess(id, key, name, description);
			    }
			}
			else{
			    Ext.MessageBox.show({title:'提示',msg:"请选择需要复制的流程！",
			    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			}
		}
	});
	
	var btnChg = new Ext.Action({
		text:'修改流程',
		iconCls:'icon-common-update',
		hidden : true,
		handler:function(){
		    var records = grid.getSelectedRecords();
			if(records.length > 0){ 
			     var id = records[0].get('id'); 
			     panel.chgProcess(id);
			}
			else{
			    Ext.MessageBox.show({title:'提示',msg:"请选择需要修改的流程！",
			    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			}
		}
	});
	
	var btnDelete = new Ext.Action({
		text : '删除流程',
		iconCls : 'icon-common-delete',
		handler : function(){
		    var records = grid.getSelectedRecords();
			if(records.length > 0){
				Ext.MessageBox.confirm('提示', '您确定要删除选中流程吗?', function(btn){
	    		if(btn == 'yes'){
	    			var v_source = '';
			    	var v_sourmc = '';
			    	for(var i=0;i<records.length;i++){
		            	if(v_source == ''){
		                	v_source = records[i].get('id');
		                	v_sourmc = records[i].get('name');
		           		}else{
		                	v_source = v_source + ';' + records[i].get('id');
		                	v_sourmc = v_sourmc + ';' + records[i].get('name');
		            	}
		        	}
			    	var item = {};
			    	item.source = v_source;
			    	item.sourmc = v_sourmc;
			    	Ext.MessageBox.show({title:'提示',wait:true,msg:"正在删除流程,请稍候...",
			    	modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
			    
			    	BpmFlowDirect.processDel(item, function(result, e){
				    	if(result.success){
				         	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			             	icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){
                              	grid.getBottomToolbar().moveFirst();
                              	
			      	     	}});
			        	}
			        	else{
			            	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			            	icon:Ext.Msg.ERROR,width:300,closable:false,fn:function(){
				            	grid.getBottomToolbar().moveFirst();
				            	
			            	}});
			        	}
			    	});
	    		
	    		}else{
	    			win.close();
	   	    	}
				});			    
			}
			else{
			    Ext.MessageBox.show({title:'提示',msg:"请选择需要删除的流程！",
			    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			}
		}
	});
	
	var btndeploy = new Ext.Action({
		text : '发布',
		iconCls : 'icon-common-apply',
		handler : function(){
		    var records = grid.getSelectedRecords();
			if(records.length > 0){ 
			    var v_source = '';
			    var v_sourmc = '';
			    for(var i=0;i<records.length;i++){
		            if(v_source == ''){
		                v_source = records[i].get('id');
		                v_sourmc = records[i].get('name');
		            }
		            else{
		                v_source = v_source + ';' + records[i].get('id');
		                v_sourmc = v_sourmc + ';' + records[i].get('name');
		            }
		        }
			    	var item = {};
			    	item.source = v_source;
			    	item.sourmc = v_sourmc;
			    
			    	Ext.MessageBox.show({title:'提示',wait:true,msg:"正在发布流程,请稍候...",
			    	modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
			    
			    	BpmFlowDirect.deployProcess(item, function(result, e){
				    	if(result.success){
				         	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			             	icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){
                              	grid.getBottomToolbar().moveFirst();
			      	     	}});
			        	}
			        	else{
			            	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			            	icon:Ext.Msg.ERROR,width:300,closable:false,fn:function(){}});
			        	}
			    	});
			}
			else{
			    Ext.MessageBox.show({title:'提示',msg:"请选择需要发布的流程！",
			    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			}
		}
	});
	
	var btnQxdeploy = new Ext.Action({
		text : '取消发布',
		iconCls : 'icon-common-cancel',
		handler : function(){
		    var records = grid.getSelectedRecords();
			if(records.length > 0){ 
			    var v_source = '';
			    var v_sourmc = '';
			    var deployedFlag = true;
			    for(var i=0;i<records.length;i++){
			    	if(!records[i].get('deployment')){
			    		deployedFlag = false;
			    		break;
			    	}
		            if(v_source == ''){
		                v_source = records[i].get('id');
		                v_sourmc = records[i].get('name');
		            }
		            else{
		                v_source = v_source + ';' + records[i].get('id');
		                v_sourmc = v_sourmc + ';' + records[i].get('name');
		            }
		        }
		        if(!deployedFlag){
			    	Ext.MessageBox.show({title:'提示',msg:"所选流程中包含'未发布'流程，不能取消发布!",
			     	modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
		        }else{
			    	var item = {};
			    	item.source = v_source;
			    	item.sourmc = v_sourmc;        	
			    	//调用取消流程窗体方法
			    	canelProcess(item);	
			    }
			}
			else{
			    Ext.MessageBox.show({title:'提示',msg:"请选择需要取消发布的流程！",
			    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			}
		}
	});
	
	var btnImp = new Ext.Action({
		text : '导入',
		iconCls : 'icon-common-import',
		handler : function(){
		    var item={};
		    item.title='流程导入';
		    item.width = minwidth;
		    item.height= minheight;
		    item.str = "说明：导入的文件必须是zip类型文件。";
		    item.submitDirect = "BpmFlowDirect.impProcess";
		    item.validator = "function(){ "+
				                   "var v_file=form.getForm().findField('f_files').getValue();"+
				                   "if(v_file.trim()== ''){"+
					                   "Ext.MessageBox.show({title:'提示',msg:'请选择需要导入的文件!',"+
					                   "modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:250,closable:false});"+
					                   "return false;"+
				                   "}"+
				                   "var name = v_file.substr(v_file.toString().trim().lastIndexOf('.')+1);"+
				                   "if(name.toString().trim() != 'zip' && name.toString().trim() != 'ZIP'){"+
									    "Ext.MessageBox.show({title:'提示',msg:'导入文件需是zip类型文件!',"+
									    "modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:250,closable:false});"+
									    "return false;"+
									"}"+
		                    "}";
		    atwasoft.common.lib.impdialog('<%=appkey%>','app/common/import','process_import',item,function(){
	             grid.getBottomToolbar().moveFirst();
	        });
		}
	});
	
	var btnExp = new Ext.Action({
		text : '导出',
		iconCls : 'icon-common-export',
		handler : function(){
		    var records = grid.getSelectedRecords();
		    var v_source = '';
			if(records.length > 0){ 
			    for(var i=0;i<records.length;i++){
		            if(v_source == ''){
		                v_source = records[i].get('id');
		            }
		            else{
		                v_source = v_source + ';' + records[i].get('id');
		            }
		        } 
			}
			
		    var url = app.url + '/jsppage.do?url=app/bpmgl/exportBpmProcess&source='+v_source;
            Mixky.lib.IframeManager.loadUrl(url);
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
    
   var tools = ['查找 ：', fieldQuery, '-','->',btnImp,btnExp,btndeploy,btnQxdeploy,btnAdd, btnCopy,btnDelete, '-', btnRefresh];
   var menus = [btnImp,btnExp,btndeploy, btnQxdeploy,btnAdd,btnCopy,btnDelete];  
	// 表格对象
   var sm = new Ext.grid.CheckboxSelectionModel();

   var grid = new Ext.grid.GridPanel({
        region:'center',
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm,
		    {id : 'name', dataIndex : 'name', header : '流程名称', width : 180},
		    {id : 'description', dataIndex : 'description', header : '描述', width : 100},
		    {id : 'createTime', dataIndex : 'createTime', header : '创建时间', width : 130},
		    {id : 'lastUpdateTime', dataIndex : 'lastUpdateTime', header : '更新时间', width : 130},
		    {id : 'deployment', dataIndex : 'deployment', header : '状态', width : 60, 
		         renderer:function renderLast(value, p, r){
		             var str = '未发布';
		             if(value){
		                str = '已发布';
		             }
			         return str;	
		         }
		     },
		     {id : 'deploymentTime', dataIndex : 'deploymentTime', header : '部署时间', width : 130},
		     {id : 'bpmbtn', dataIndex : 'bpmbtn', header : '查看已发布流程', width : 100,
		     	renderer:function (value,p,r){
		     		if(r.get("deployment")){
		     			var operStr = '<a href="javascript:viewDeployedBpm('+"'" +r.get("processKey")+"','" + r.get("appKey") + "'" +');" >查看</a>';
		     			return operStr;
         			}
         		},
         		css : "text-align:center;",
         		sortable : false
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
		contextMenu : new Ext.menu.Menu({items:menus}),
		listeners : {
			'rowcontextmenu' : function(g, rowIndex, e){
				g.getSelectionModel().selectRow(rowIndex);
				g.contextMenu.showAt(e.getXY());
			},
			'rowdblclick' : function(g, rowIndex, e){
			    var record = store.getAt(rowIndex);
			    btnChg.execute();
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
	
	panel.createProcess = function(key){
	        var item={};
		    item.title='新建流程';
		    item.width=minwidth;
		    item.height=minheight;
		    item.key = key;
		    atwasoft.common.lib.dialog('<%=appkey%>','app/bpmgl/bpm_creat','bpm_creat',item,function(result){
	             grid.getBottomToolbar().moveFirst();
	             showProcessView(result.id);
	        });
	}
	
	panel.copyProcess = function(id, key, name, description){
	        var item={};
		    item.title='复制流程';
		    item.width=minwidth;
		    item.height=minheight;
		    item.id = id;
		    item.key = key;
		    item.name = name;
		    item.description = description;
		    atwasoft.common.lib.dialog('<%=appkey%>','app/bpmgl/bpm_copy','bpm_copy',item,function(result){
	             grid.getBottomToolbar().moveFirst();
	             showProcessView(result.id);
	        });
	}
	
	panel.chgProcess = function(id){
	     showProcessView(id);
	}
	
  //弹出window对话框（全屏），展现流程编辑模型
   showProcessView = function(id){
       var width = screen.width;
       var height=screen.height;
       var url = app.url + '/service/editor?id='+id;
       if(window.showModalDialog){
       		window.showModalDialog(url,window,'dialogWidth:'+width+'px;dialogHeight:'+height+'px;dialogLeft:'+(screen.width-'+width+')/2+'px;dialogTop:'+(screen.height-'+height+')/2+'px;center:yes;help:no;resizable:no;status:no;scrollbars:no;location=no;');
       }else{
       		//Chrome新版本不支持showModalDialog
       		window.open(url,"流程设计器",'width='+width+',height='+height+',toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=yes');
       }
   };
	
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

viewDeployedBpm = function(processKey,appKey){
	var module = MixkyApp.desktop.getCurrentModule();
	if(module){
		module.closeView('waBpmZx.qryBpmJk.vBpmJk');
		module.openView('waBpmZx.qryBpmJk.vBpmJk',{'processKey':processKey,'appKey':appKey});
	}	
}
</script>