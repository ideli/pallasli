<%@ page contentType="text/html; charset=utf-8"%>

<%@page import="java.util.List"%>
<%@page import="com.google.gson.*"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%@page import="com.mixky.engine.design.*"%>
<%@page import="com.garage.xdatabase.cache.DataCacheFactory"%>
<%
	String id = request.getParameter("id");
	List<DesignObject> apps = DataCacheFactory.instance().getSerializeableObjects("application");
%>
<script language='javascript'>
Ext.onReady(function(){
	var id = '<%=id%>';
	var chg=0;

	var panel = Ext.getCmp(id);
	panel.setTitle('岗位管理');

	// 存储字段
	var fields = [
	    {name:'id', mapping:'id'},
		{name:'f_name', mapping:'f_name'},
		{name:'f_caption', mapping:'f_caption'},
		{name:'f_menu_id', mapping:'f_menu_id'},
		{name:'f_menu_name', mapping:'f_menu_name'},
		{name:'f_menu_caption', mapping:'f_menu_caption'},
<%
     if(apps != null){
			for(int i=0;i<apps.size();i++){
				DesignObject app = apps.get(i);
				out.print("{name:'f_"+app.getF_key().trim()+"_id',mapping:'f_"+app.getF_key().trim()+"_id'},\n");
				out.print("{name:'f_"+app.getF_key().trim()+"_name',mapping:'f_"+app.getF_key().trim()+"_name'},\n");
				out.print("{name:'f_"+app.getF_key().trim()+"_caption',mapping:'f_"+app.getF_key().trim()+"_caption'},\n");
			}
		}
%>
		{name:'f_note', mapping:'f_note'}
	];
	// 列表字段
	var columns = [new Ext.grid.RowNumberer(),{
		id : 'f_name',
		dataIndex : 'f_name',
		header : '英文名称',
		width:100
	},{
		id : 'f_caption',
		dataIndex : 'f_caption',
		width:120,
		header : '中文名称'
	},{
		id : 'f_menu_caption',
		dataIndex : 'f_menu_caption',
		header : '导航权限',
		width:250
	},
<%
     if(apps != null){
			DesignObjectFactory.instance().orderDesignObjectList(apps);
			for(int i=0;i<apps.size();i++){
				DesignObject app = apps.get(i);
				out.print("{id:'f_"+app.getF_key().trim()+"_caption',dataIndex:'f_"+app.getF_key().trim()+
				"_caption',header:'"+app.getF_caption().trim()+"权限',width:250},\n");
			}
		}
%>	
	{
		id : 'f_note',
		dataIndex : 'f_note',
		header : '备注',
		width:100
	}];
	
	// 数据访问
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		paramOrder : ['limit','start','sort','dir','query'],
		baseParams : {limit:60, start:0, sort:'',dir:'', query:''},
		remoteSort : true,
		directFn : OrganizationDirect.getRoleInfo,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields:fields
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
			store.load();
    	},
    	onTrigger2Click : function(){
        	this.setValue('');
			store.baseParams.query = '';
			store.load();
    	}
	})
	
    var AddAction = new Ext.Action({
		text:'增加',
		iconCls:'icon-common-add',
		handler:function(){
			chg=1;
			advanceForm.getForm().findField('f_name').setReadOnly(false);
		    panel.emptyFormfield();
			advanceWindow.setTitle('增加');
			advanceWindow.show();	
		}
	});
	var ChgAction = new Ext.Action({
		text:'修改',
		iconCls:'icon-common-update',
		handler:function(){
		    var records = grid.getSelectedRecords();
			 if(records.length > 0){
			      chg=2;
				  advanceForm.getForm().reset();
				  advanceForm.getForm().findField('f_name').setReadOnly(true);
				  panel.setFormfield(records[0]);
				  advanceWindow.setTitle('修改');
				  advanceWindow.show();	
			 }
			 else{
			      Ext.MessageBox.show({title:'提示',msg:"请选择需要修改的岗位信息！",
			      modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			 }
		}
	});
    var DelAction = new Ext.Action({
		text:'删除',
		iconCls:'icon-common-delete',
		handler:function(){
			 var records = grid.getSelectedRecords();
			 if(records.length > 0){
			      var record=records[0];
			      var item ={};
			      item.id = record.get('id');
				  item.f_name=record.get('f_name');
				  item.f_caption=record.get('f_caption');
				  item.f_menu_id=record.get('f_menu_id');
<%
     if(apps != null){
			DesignObjectFactory.instance().orderDesignObjectList(apps);
			for(int i=0;i<apps.size();i++){
				DesignObject app = apps.get(i);
				out.print("item.f_"+app.getF_key().trim()+"_id=record.get('f_"+app.getF_key().trim()+"_id');\n");
			}
	 }
%>	
				  item.rowstate = 'del';
			      Ext.MessageBox.show({title:'危险操作提示',msg:'您确定要删除英文名称为('+record.get('f_name')+')的岗位信息吗？',modal:true,buttons:Ext.Msg.YESNO,
			       icon:Ext.Msg.QUESTION,width:350,closable:false,fn:function(btn){
			         if(btn == 'yes'){
			             OrganizationDirect.roleDel(item,function(result, e){
			               if (result && result.success) {
			 	               Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			                   icon:Ext.Msg.INFO,width:250,closable:false});
			                   panel.refresh();
			              }
			              else{
			                  Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			                  icon:Ext.Msg.ERROR,width:250,closable:false});
			              }
			            })
			        }
			       }});  							
			   }else{
			        Ext.MessageBox.show({title:'提示',msg:"请选择需要删除的岗位信息！",
			        modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:300,closable:false});	
			   }
		}
	});

    var tools = ['查找 ：', fieldQuery, '-', AddAction, '-',ChgAction,'-',DelAction, '-'];
    var menus = [AddAction, '-', ChgAction,'-',DelAction, '-'];  
	// 表格对象
	var sm = new Ext.grid.RowSelectionModel({singleSelect : true});
	
	var grid = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : columns,
		autoExpandColumn:'f_note',
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
				ChgAction.execute();
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
  /*鼠标移动到列时显示列的值*/
   grid.on('mouseover',function(e){
		var index= grid.getView().findRowIndex(e.getTarget());  //获得行
		var record=store.getAt(index);
		var cols = grid.getColumnModel();
		if(record!=null){
		    var index1=grid.getView().findCellIndex(e.getTarget());
		    var rowEl=Ext.get(e.getTarget()); //把target转换为Ext.Element对象
		    if(index1>=1){
		         var str = record.get(cols.getColumnId(index1));
	             rowEl.dom.setAttribute("ext:qtip", str); 
			}
		} 
   });
   
   var id = {name:'id',xtype:'hidden'};
   var rowstate = {name:'rowstate',xtype:'hidden'};
   var f_oldmenu_id = {name:'f_oldmenu_id',xtype:'hidden'};
   var f_menu_id = {name:'f_menu_id',xtype:'hidden'};
   var f_menu_name = {name:'f_menu_name',xtype:'hidden'};
	
	var f_name = {xtype:'textfield',anchor:'100%',inputValue:'',selectOnFocus:true,name:'f_name',fieldLabel:'英文名称',allowBlank:true,maxLength:20,style:"ime-mode:disabled",labelStyle:'color:red'};
	var f_caption = {xtype:'textfield',anchor:'100%',inputValue:'',selectOnFocus:true,name:'f_caption',fieldLabel:'岗位名称',allowBlank:true,maxLength:20,labelStyle:'color:red'};
	var f_menu_caption = {xtype:'trigger',anchor:'100%',name:'f_menu_caption',fieldLabel:'导航权限',allowBlank:true,triggerClass:'x-form-search-trigger',editable:false,onTriggerClick:function(){store1.load();advanceWindow1.show();}};
	var f_note = {xtype:'textfield',anchor:'100%',inputValue:'',selectOnFocus:true,name:'f_note',fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',allowBlank:true,maxLength:200};
	<%
	if(apps != null){
		DesignObjectFactory.instance().orderDesignObjectList(apps);
		for(int i=0;i<apps.size();i++){
			DesignObject app = apps.get(i);
			out.print("var f_"+app.getF_key().trim()+"_id={xtype:'hidden',name:'f_"+app.getF_key().trim()+"_id'};\n");
			out.print("var f_"+app.getF_key().trim()+"_name={xtype:'hidden',name:'f_"+app.getF_key().trim()+"_name'};\n");
			out.print("var f_"+app.getF_key().trim()+"_caption={xtype:'trigger',anchor:'100%',name:'f_"+app.getF_key().trim()+"_caption',fieldLabel:'"+app.getF_caption().trim()+
			"权限',allowBlank:true,triggerClass:'x-form-search-trigger',editable:false,onTriggerClick:function(){advanceWindow2.setTitle('"+app.getF_caption().trim()+
			"权限选择');store2.baseParams.query='"+app.getF_key().trim()+"';store2.reload();advanceWindow2.show();}};\n");
		}
	}
	%>
	var advanceForm = new Ext.form.FormPanel({
		padding : 5,
		autoScroll : true,
		items : [{"xtype":"fieldset","title":"岗位信息",style:"padding-top:15px;","items":[{"layout":"form","border":false,height:30,"style":"padding-left:10px",labelWidth:105,"items":f_name},
		                                                                                 {"layout":"form","border":false,height:30,"style":"padding-left:10px",labelWidth:105,"items":f_caption},
		                                                                                 {"layout":"form","border":false,height:30,"style":"padding-left:10px",labelWidth:105,"items":f_note},id,f_menu_id,f_menu_name,rowstate,f_oldmenu_id]},
		  <%   
	    if(apps != null){
	        if(apps.size()>0){
	            String str="";
	            out.print("{xtype:'fieldset',title:'导航权限信息',style:'padding-top:15px;',items:[{border:false,layout:'form',height:30,style:'padding-left:10px',labelWidth:105,items:f_menu_caption}]},\n"); 
	            DesignObjectFactory.instance().orderDesignObjectList(apps);
	            str="{xtype:'fieldset',title:'应用权限信息',style:'padding-top:15px;',items:[";
				for(int i=0;i<apps.size();i++){
					DesignObject app = apps.get(i);
					if((i+1)==apps.size()){
					    str+="{border:false,layout:'form',height:30,style:'padding-left:10px',labelWidth:105,items:f_"+app.getF_key().trim()+"_caption},f_"+app.getF_key().trim()+"_id,f_"+app.getF_key().trim()+"_name\n"; 
					}
					else{
					    str+="{border:false,layout:'form',height:30,style:'padding-left:10px',labelWidth:105,items:f_"+app.getF_key().trim()+"_caption},f_"+app.getF_key().trim()+"_id,f_"+app.getF_key().trim()+"_name,\n"; 
					}
				}
				str+="]}";
				out.print(str);
	        }
			else{
			     out.print("{xtype:'fieldset',title:'导航权限信息',style:'padding-top:15px;',items:[{border:false,layout:'form',height:30,style:'padding-left:10px',labelWidth:105,items:f_menu_caption}]}\n"); 
			}
		}
		else{
		    out.print("{xtype:'fieldset',title:'导航权限信息',style:'padding-top:15px;',items:[{border:false,layout:'form',height:30,style:'padding-left:10px',labelWidth:105,items:f_menu_caption}]}\n"); 
		}
		  %>
		        ],
		bbar : [{
			text : '关闭',
			iconCls : 'icon-common-cancel',
			handler : function(){
				advanceWindow.hide();
			}
		},'->',{
			text : '保存',
			iconCls : 'icon-common-aquery',
			handler : function(){
			    if(chg==1){
			       Mixky.lib.getNewTableRecordId('t_mk_sys_role', function(newId){
						advanceForm.getForm().findField('id').setValue(newId);
						panel.submit();
				   });
			    }
			    else{
			        panel.submit();
			    }
			}
		}, {
			text : '重置',
			iconCls : 'icon-common-confirm',
			handler : function(){
			    if(chg==2){
			          var records = grid.getSelectedRecords();
			          advanceForm.getForm().reset();
			          panel.setFormfield(records[0]);
			    }
			    else{
				   advanceForm.getForm().reset();
				   panel.emptyFormfield();
				}
			}
		}]
	});
	
	var nameField=advanceForm.getForm().findField('f_name');
	
	if(Ext.isDefined(nameField)){
	   nameField.on('change', function(f,n,o){
	     	 var params=nameField.getValue();
	     	 if(params.trim()!=''){
	     	    OrganizationDirect.chkRole(params.toString(),function(result,E){
			        if (!result.success) {
			    	    Ext.MessageBox.show({title:'提示',msg:"英文名称("+params+")已经存在！",modal:true,buttons:Ext.Msg.OK,
			    	    icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){nameField.reset();nameField.focus(false,10);}});
			        }
			    });
			 }
	   }); 
	}
	
	var windows = new Ext.WindowGroup();
	var advanceWindow = new Ext.Window({
		width : 620,
		height : 520,
		maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
		modal : true,
		closeAction : 'hide',
		closable : false,
		layout : 'fit',
		manager : windows,
		items:[advanceForm]
	});
	
	// 数据访问
	var store1 = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		remoteSort : true,
		directFn : AuthorityDirect.getAllMenu,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields:["id","f_no","f_name","f_applicationkey","f_caption","f_note"]
	});
	
	store1.on('load', function(t,records,o){
	    sm1.clearSelections(false);
		if(records.length > 0) {
		   var menuvalue=advanceForm.getForm().findField('f_menu_id').getValue();
		   if(menuvalue.trim()!=''){
				var arr=menuvalue.split(";");
			    for(var j=0;j<arr.length;j++){
			       for(var i=0;i<store1.getCount();i++){
			           var record = store1.getAt(i);
				       if(parseInt(arr[j],10)==parseInt(record.get('id'),10)){
				          sm1.selectRow (i,true);
				       }
				   }
			    }
			}
			else{
			    sm1.clearSelections(false);
			}
		}
	});
	
	var sm1 = new Ext.grid.CheckboxSelectionModel({checkOnly: true});
	
	var grid1 = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm1,		
		        {"id":"f_no","dataIndex":"f_no","header":"编号",width:70},						
				{"id":"f_name","dataIndex":"f_name","header":"标识",width:110},
			    {"id":"f_applicationkey","dataIndex":"f_applicationkey","header":"应用系统标识",width:100},
			    {"id":"f_caption","dataIndex":"f_caption","header":"名称",width:120}],
		autoExpandColumn:'f_caption',
		enableHdMenu:false,
		enableColumnMove:false,
		store : store1,
		sm : sm1,
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
				
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		}
	});
	
	var advanceWindow1 = new Ext.Window({
        width : 500,
		height : 520,
		maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
		title : '导航权限选择',
		modal : true,
		closeAction : 'hide',
		layout : 'fit',
		closable : false,
		manager : windows,
        items : grid1,
        bbar : [{
					text : '关闭',
					handler : function() {
						advanceWindow1.hide();												
					}
				},'->',{
					text : '确定',
					handler : function() {
						var menuid=advanceForm.getForm().findField('f_menu_id');
						var menuname=advanceForm.getForm().findField('f_menu_name');
						var menucaption=advanceForm.getForm().findField('f_menu_caption');
						var records = grid1.getSelectedRecords();
						var v_menuid='';
				        var v_menuname='';
				        var v_menucaption='';
						if(records.length > 0){
					        for(var i=0;i<records.length;i++){
					            if(v_menuid==''&&v_menuname==''&&v_menucaption==''){
					                v_menuid= records[i].get('id');
					                v_menuname=records[i].get('f_name');
					                v_menucaption=records[i].get('f_caption');
					            }
					            else{
					                 v_menuid= v_menuid+';'+records[i].get('id');
					                 v_menuname=v_menuname+';'+records[i].get('f_name');
					                 v_menucaption=v_menucaption+';'+records[i].get('f_caption');
					            }
					           
					        }
					     }
					      menuid.setValue(v_menuid);
                          menuname.setValue(v_menuname);  
                          menucaption.setValue(v_menucaption);
                          advanceWindow1.hide();	
					}
				}]
    });
    
    // 数据访问
	var store2 = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		paramOrder : ['query'],
		baseParams : {query:''},
		remoteSort : true,
		directFn : AuthorityDirect.getAllModuleRole,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'f_key',
		fields:["f_key","f_applicationkey","f_module","f_modulerole"]
	});
	
	store2.on('load', function(t,records,o){
	    sm2.clearSelections(false);
		if(records.length > 0) {
		   var appkey_name='f_'+(store2.baseParams.query).trim()+'_name';
		   var appvalue=advanceForm.getForm().findField(appkey_name).getValue();
		   if(appvalue.trim()!=''){
				var arr=appvalue.split(";");
			    for(var j=0;j<arr.length;j++){
			       for(var i=0;i<store2.getCount();i++){
			           var record = store2.getAt(i);
				       if(arr[j].toString().trim()==record.get('f_key').toString().trim()){
				          sm2.selectRow (i,true);
				       }
				   }
			    }
			}
			else{
			    sm2.clearSelections(false);
			}
		}
	});
	
	var sm2 = new Ext.grid.CheckboxSelectionModel({checkOnly: true});
	
	var grid2 = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm2,	
		        {"id":"f_applicationkey","dataIndex":"f_applicationkey","header":"应用标识",width:80},	
		        {"id":"f_module","dataIndex":"f_module","header":"模块",width:110},						
				{"id":"f_modulerole","dataIndex":"f_modulerole","header":"模块角色",width:150}],
		autoExpandColumn:'f_modulerole',
		enableHdMenu:false,
		enableColumnMove:false,
		store : store2,
		sm : sm2,
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
				
			}
		},
		getSelectedRecords : function(){
			return this.getSelectionModel().getSelections();
		}
	});
	
	var advanceWindow2 = new Ext.Window({
        width : 480,
		height : 520,
		maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
		modal : true,
		closeAction : 'hide',
		layout : 'fit',
		closable : false,
		manager : windows,
        items : grid2,
        bbar : [{
					text : '关闭',
					handler : function() {
						advanceWindow2.hide();												
					}
				},'->',{
					text : '确定',
					handler : function() {
					    var appkey_name='f_'+(store2.baseParams.query).trim()+'_name';
					    var appkey_caption='f_'+(store2.baseParams.query).trim()+'_caption';
						var appname=advanceForm.getForm().findField(appkey_name);
						var appcaption=advanceForm.getForm().findField(appkey_caption);
						var records = grid2.getSelectedRecords();
						var v_appname='';
					    var v_appcaption='';
						if(records.length > 0){
					        for(var i=0;i<records.length;i++){
					            if(v_appname==''&&v_appcaption==''){
					                v_appname=records[i].get('f_key');
					                v_appcaption=records[i].get('f_modulerole');
					            }
					            else{
					                 v_appname=v_appname+';'+records[i].get('f_key');
					                 v_appcaption=v_appcaption+';'+records[i].get('f_modulerole');
					            }
					        }
					     }
					     appname.setValue(v_appname);  
                         appcaption.setValue(v_appcaption);
                         advanceWindow2.hide();	
					}
				}]
    });
	
	panel.emptyFormfield=function(){
	      advanceForm.getForm().findField('rowstate').setValue('add');		  
		  advanceForm.getForm().findField('id').setValue(0);
		  advanceForm.getForm().findField('f_name').setValue('');
		  advanceForm.getForm().findField('f_caption').setValue('');
		  advanceForm.getForm().findField('f_menu_id').setValue('');
		  advanceForm.getForm().findField('f_oldmenu_id').setValue('');
		  advanceForm.getForm().findField('f_menu_name').setValue('');
		  advanceForm.getForm().findField('f_menu_caption').setValue('');
<%
     if(apps != null){
		for(int i=0;i<apps.size();i++){
			DesignObject app = apps.get(i);
			out.print("advanceForm.getForm().findField('f_"+app.getF_key().trim()+"_id').setValue('');\n");
			out.print("advanceForm.getForm().findField('f_"+app.getF_key().trim()+"_name').setValue('');\n");
			out.print("advanceForm.getForm().findField('f_"+app.getF_key().trim()+"_caption').setValue('');\n");
		}
	 }
%>
		  advanceForm.getForm().findField('f_note').setValue('');
	}
	
	panel.setFormfield=function(records){
	      advanceForm.getForm().findField('rowstate').setValue('chg');		  
		  advanceForm.getForm().findField('id').setValue(records.get('id'));
		  advanceForm.getForm().findField('f_name').setValue(records.get('f_name').trim());
		  advanceForm.getForm().findField('f_caption').setValue(records.get('f_caption').trim());
		  advanceForm.getForm().findField('f_menu_id').setValue(records.get('f_menu_id').trim());
		  advanceForm.getForm().findField('f_oldmenu_id').setValue(records.get('f_menu_id').trim());
		  advanceForm.getForm().findField('f_menu_name').setValue(records.get('f_menu_name').trim());
		  advanceForm.getForm().findField('f_menu_caption').setValue(records.get('f_menu_caption').trim());
<%
     if(apps != null){
		for(int i=0;i<apps.size();i++){
			DesignObject app = apps.get(i);
			out.print("advanceForm.getForm().findField('f_"+app.getF_key().trim()+"_id').setValue(records.get('f_"+app.getF_key().trim()+"_id').trim());\n");
			out.print("advanceForm.getForm().findField('f_"+app.getF_key().trim()+"_name').setValue(records.get('f_"+app.getF_key().trim()+"_name').trim());\n");
			out.print("advanceForm.getForm().findField('f_"+app.getF_key().trim()+"_caption').setValue(records.get('f_"+app.getF_key().trim()+"_caption').trim());\n");
		}
	 }
%>		  
		  advanceForm.getForm().findField('f_note').setValue(records.get('f_note').trim());
	}
	
	var formValidator =function(){
	    var v_name=advanceForm.getForm().findField('f_name').getValue();
	    var v_caption=advanceForm.getForm().findField('f_caption').getValue();
	    if(v_name.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"英文名称不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    if(v_caption.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"岗位名称不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    return true;
	}
	
	panel.submit = function(){
		   if(!advanceForm.getForm().isValid()){
			   Ext.MessageBox.show({title:'提示',msg:"页面中存在非法数据，请检查！",
			   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:300,closable:false});
			   return;
		   }
		   if (Ext.isDefined(formValidator)) {
			   // 自定义表单校验函数校验失败
			   if (formValidator() === false) {
				   return;
			   }
		   }  
		   var params = advanceForm.getForm().getFieldValues();
		   Ext.MessageBox.show({title:'提示',wait:true,msg:"正在进行岗位信息保存,请稍候...",
           modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
           if(chg==1){
			   OrganizationDirect.roleSave(params,function(result,e){
				   	if (result&&result.success) {
				    	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
	                    icon:Ext.Msg.INFO,width:250,closable:false});
	                    advanceForm.getForm().reset();
	                    panel.refresh();
			        }
			        else{
			            Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
		                icon:Ext.Msg.ERROR,width:250,closable:false});
			        }
			   });
			}
			else{
			    OrganizationDirect.roleChg(params,function(result,e){
				   	if (result&&result.success) {
				    	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
	                    icon:Ext.Msg.INFO,width:250,closable:false});
	                    panel.refresh();
	                    advanceWindow.hide();
			        }
			        else{
			            Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
		                icon:Ext.Msg.ERROR,width:250,closable:false});
			        }
			   });
			}
	}
	// 刷新
	panel.refresh = function(){
		grid.getBottomToolbar().moveFirst();
	}
	panel.add(grid);
	panel.doLayout();
	panel.refresh();
});
</script>