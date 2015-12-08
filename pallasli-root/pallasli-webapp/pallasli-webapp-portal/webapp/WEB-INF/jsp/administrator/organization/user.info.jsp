<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.mixky.app.ApplicationParameters"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%
	String id = request.getParameter("id");
	User user = MixkyUserCertification.instance().getUserInfo(request);	
	long userid=user.getId();
%>
<script language='javascript'>
Ext.onReady(function(){
	var id = '<%=id%>';
	var chg=0;
	// 获得对象的属性列表
	var module = Mixky.awsoft.lib.Class.getModule("user");
	
	var panel = Ext.getCmp(id);
	panel.setTitle('基本信息');

	// 合并属性
	var properties = {};
	for(var i=0;i<module.properties.length;i++){
		var p = module.properties[i];
		properties[p.name] = p;
	}
	// 存储字段
	var fields = [{name:'rowstate', mapping:'rowstate'}];
	for(var n in properties){
		var f = {name:n, mapping:n};
		fields.push(f);
	}
	// 列表字段
	var columns = [new Ext.PagingRowNumberer()];
	for(n in module.propertyColumns){
		var col = {
			id : properties[n].name,
			dataIndex : properties[n].name,
			header : properties[n].text
		};
		if(properties[n].xeditor == 'selectkeymap'){
			col.renderer = function(bVal){
	        	var n = this.id;
	        	for(var i=0;i<properties[n].xconfig.datas.length;i++){
	        		if(bVal == properties[n].xconfig.datas[i][0]){
	        			return properties[n].xconfig.datas[i][1];
	        		}
	        	}
	        	return bVal;
	        }
		}
		Ext.apply(col, module.propertyColumns[n]);
		columns.push(col);
	}
	
	// 数据访问
	var store = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		paramOrder : ['limit','start','sort','dir','query'],
		baseParams : {limit:60, start:0, sort:'',dir:'', query:''},
		remoteSort : true,
		directFn : OrganizationDirect.getUserInfo,
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
			     if(<%=userid%>!=parseInt(records[0].get('id'),10)){
				      chg=2;
					  advanceForm.getForm().reset();
					  advanceForm.getForm().findField('f_name').setReadOnly(true);
					  panel.setFormfield(records[0]);
					  advanceWindow.setTitle('修改');
					  advanceWindow.show();	
			     }
			     else{
			        Ext.MessageBox.show({title:'提示',msg:"不能对自己的进行操作！",
			          modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			     }
			 }
			 else{
			      Ext.MessageBox.show({title:'提示',msg:"请选择需要修改的用户信息！",
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
			      if(<%=userid%>!=parseInt(record.get('id'),10)){
					      var item ={};
					      item.id = record.get('id');
						  item.f_name=record.get('f_name');
						  item.rowstate = 'del';
					      Ext.MessageBox.show({title:'危险操作提示',msg:'您确定要删除登录名为('+record.get('f_name')+')的用户信息吗？',modal:true,buttons:Ext.Msg.YESNO,
					       icon:Ext.Msg.QUESTION,width:350,closable:false,fn:function(btn){
					         if(btn == 'yes'){
					             OrganizationDirect.userDel(item,function(result, e){
					               if (result && result.success) {
					 	               Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
					                   icon:Ext.Msg.INFO,width:300,closable:false});
					                   panel.refresh();
					              }
					              else{
					                  Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
					                  icon:Ext.Msg.ERROR,width:300,closable:false});
					              }
					            })
					        }
					       }});  
				   }
			       else{
			             Ext.MessageBox.show({title:'提示',msg:"不能对自己的进行操作！",
			              modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
			       }							
			   }else{
			        Ext.MessageBox.show({title:'提示',msg:"请选择需要删除的用户信息！",
			        modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:300,closable:false});	
			   }
		}
	});

    var ResetPasswordAction = new Ext.Action({
		text:'重置密码',
		iconCls:'icon-common-refresh',
		handler : function(){
			var record = grid.getSelectionModel().getSelected();
			if(!record){
				return;
			}
			if(<%=userid%>!=parseInt(record.get('id'),10)){
				OrganizationDirect.resetUserPassword(parseInt(record.get('id'),10),function(result,e){
					if(result && result.success){
						Ext.Msg.alert('操作提示', '用户【' + record.get('f_name') + '】重置密码成功。');
					}else{
						Ext.Msg.alert('错误提示', '用户【' + record.get('f_name') + '】重置密码失败。');
					}
				});
			}
	        else{
	             Ext.MessageBox.show({title:'提示',msg:"不能对自己的进行操作！",
	              modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:250,closable:false});
	        }	
		}
	});

    var tools = ['查找 ：', fieldQuery, '-', AddAction, '-',ChgAction,'-',DelAction, '-', ResetPasswordAction];
    var menus = [AddAction, '-', ChgAction,'-',DelAction, '-', ResetPasswordAction];  
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
	
	// 数据访问
	var store1 = new Ext.data.DirectStore({
		pruneModifiedRecords : true,
		remoteSort : true,
		directFn : OrganizationDirect.getRoles,
		root : 'results',
		totalProperty : 'totals',
		idProperty : 'id',
		fields:["id","f_name","f_caption","f_note"]
	});
	
	var sm1 = new Ext.grid.CheckboxSelectionModel({checkOnly: true});
	
	var grid1 = new Ext.grid.GridPanel({
		border : false,
		stripeRows: true,
		lineBreak : false,
		cellSelect : true,
        loadMask: {msg:'正在装载...'},
		columns : [new Ext.PagingRowNumberer(),sm1,								
				{"id":"f_name","dataIndex":"f_name","header":"英文名称",width:90},
			    {"id":"f_caption","dataIndex":"f_caption","header":"岗位名称",width:200},
			    {"id":"f_note","dataIndex":"f_note","header":"备注",width:150}],
		autoExpandColumn:'f_note',
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
	
	var deptstore=new Ext.data.DirectStore({
			autoLoad : false,
			paramsAsHash: false,
			directFn : OrganizationDirect.getDepts,
			reader:new Ext.data.JsonReader({
				totalProperty:'totals',
				root:"results",				
				fields:[
					{name:'value'},
					{name:'display'}				
				]
			})
	});
	
	var id = {"name":"id","xtype":"hidden"};
	var rowstate = {"name":"rowstate","xtype":"hidden"};
	var f_dept_name = {"name":"f_dept_name","xtype":"hidden"};
	var f_role_id = {"name":"f_role_id","xtype":"hidden"};
	var f_gjdbm = {"name":"f_gjdbm","xtype":"hidden"};
	var f_yhbm = {"name":"f_yhbm","xtype":"hidden"};
	var f_cbwdbm = {"name":"f_cbwdbm","xtype":"hidden"};
	var dndata = {"name":"dndata","xtype":"hidden"};
	
	var f_name = {"xtype":"textfield","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"f_name","fieldLabel":"登录名","allowBlank":true,"maxLength":20,style:"ime-mode:disabled","labelStyle":"color:red"};
	var f_caption = {"xtype":"textfield","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"f_caption","fieldLabel":"姓 名","allowBlank":true,"maxLength":20,"labelStyle":"color:red"};
	var f_email = {"xtype":"textfield","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"f_email","fieldLabel":"E-mail","allowBlank":true,"maxLength":30};
	var f_cellphone = {"xtype":"textfield","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"f_cellphone","fieldLabel":"手机号","allowBlank":true,"maxLength":20};
	var f_type = {"xtype":"combo","anchor":"100%","name":"f_type","hiddenName":"f_type","fieldLabel":"成员类型","editable":false,"triggerAction":"all","mode":"local","valueField":"value","displayField":"display","allowBlank":"true","store":[[0, '开发人员'],[1,'普通用户'],[2,'系统管理员'],[3,'中心用户']]};
	var f_state = {"xtype":"combo","anchor":"100%","name":"f_state","hiddenName":"f_state","fieldLabel":"状 态","editable":false,"triggerAction":"all","mode":"local","valueField":"value","displayField":"display","allowBlank":"true","store":[[0, '正常'],[1,'已离职']]};
	var f_dept_id = {"xtype":"combo","anchor":"100%","name":"f_dept_id","hiddenName":"f_dept_id","fieldLabel":"所属部门","editable":false,"triggerAction":"all","mode":"remote","valueField":"value","displayField":"display","allowBlank":"true","store":deptstore,listeners:{'render':function(cb){cb.getStore().load();}},"labelStyle":"color:red"};
	var f_depttype = {"xtype":"combo","anchor":"100%","name":"f_depttype","hiddenName":"f_depttype","fieldLabel":"部门类型","editable":false,"triggerAction":"all","mode":"local","valueField":"value","displayField":"display","allowBlank":"true","store":[[0, '普通成员'],[1,'部门主管'],[2,'分管领导']]};
	var f_role_name = {"xtype":"trigger","anchor":"100%","name":"f_role_name","fieldLabel":"所属岗位",allowBlank:true,triggerClass:'x-form-search-trigger',"editable":false,onTriggerClick:function(){store1.load();advanceWindow1.show();},"labelStyle":"color:red"};
	var f_note = {"xtype":"textfield","anchor":"100%","inputValue":"","selectOnFocus":true,"name":"f_note","fieldLabel":"备 注","allowBlank":true,"maxLength":200};
	
	var f_gjdmc = {"xtype":"textfield","anchor":"99%","inputValue":"","selectOnFocus":true,"name":"f_gjdmc","fieldLabel":"归集点","allowBlank":true,readOnly:true,"labelStyle":"color:red"};
	var f_yhmc = {"xtype":"textfield","anchor":"99%","inputValue":"","selectOnFocus":true,"name":"f_yhmc","fieldLabel":"银 行","allowBlank":true,readOnly:true};
	var f_cbwdmc = {"xtype":"textfield","anchor":"99%","inputValue":"","selectOnFocus":true,"name":"f_cbwdmc","fieldLabel":"网 点","allowBlank":true,readOnly:true};
	
	var btnSjxz = new Ext.Button(
	    {id:"sjxz",xtype:"button","anchor":"99%",fieldLabel:"",allowDepress:false,height:28,style:{marginBottom:'10px'},text:'数据范围选择',
	     handler:function(){
	       panel.setScope();
	     }
	});
	
	var advanceForm = new Ext.form.FormPanel({
		padding : 5,
		autoScroll : true,
		tbar :['->',{
		    text : '签发CA证书',
			handler : function(){
			       var params = nameField.getValue();
			       if(params == ''){
			           Ext.MessageBox.show({title:'提示',msg:"没有用户名!",
		               modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
			           return true;
			       }
			       OrganizationDirect.chkUname(params.toString(),function(result,e){
				        if (result.success) {
				    	    Ext.MessageBox.show({title:'提示',msg:"必须先保存用户基本信息，才能签发证书！",modal:true,buttons:Ext.Msg.OK,
				    	    icon:Ext.Msg.INFO,width:350,closable:false});
				    	    return true;
				        }
				        else{
				           if(result.f_state == 1){
				              Ext.MessageBox.show({title:'提示',msg:"此用户已离职不能签发证书！",modal:true,buttons:Ext.Msg.OK,
				    	      icon:Ext.Msg.INFO,width:350,closable:false});
				    	       return true;
				           }
				           else{
				               panel.certsign();
				           }
				        }
				   });
			}
		}],
		items : [{"xtype":"fieldset",width:590,"title":"基本信息",style:"padding-top:5px;","items":[{"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_name}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_caption}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_dept_id}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_role_name}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_type}]},
		                                                                                  {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_depttype}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_state}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_email}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_cellphone}]},
		                                                                                 {"layout":"column","border":false,"items":[{"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"columnWidth":.99 ,"items":f_note},id,f_role_id,f_dept_name,f_gjdbm,f_yhbm,f_cbwdbm,rowstate,dndata]}]},
		         {"xtype":"fieldset",width:590,"title":"数据范围",style:"padding-top:5px;","items":[{"border":false,"layout":"form",height:30,"style":"padding-left:70px","items":btnSjxz},
		                                                                                 {"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"items":f_gjdmc},
		                                                                                 {"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"items":f_yhmc},
		                                                                                 {"border":false,"layout":"form","style":"padding-left:10px",labelWidth:55,"items":f_cbwdmc}]}
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
			       Mixky.lib.getNewTableRecordId('t_mk_sys_user', function(newId){
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
	
	var deptidField=advanceForm.getForm().findField('f_dept_id');
	if(Ext.isDefined(deptidField)){
	   deptidField.store.on('load',function(){	
		if(deptidField.store.getCount() > 0){	
		    if(deptidField.getValue()!=''){
			   deptidField.setValue(deptidField.getValue());
			}
		}
	  });
	}
	deptidField.on('select', function(c,r,i){
		advanceForm.getForm().findField('f_dept_name').setValue(deptidField.getRawValue());
	});
	
	var nameField=advanceForm.getForm().findField('f_name');
	
	if(Ext.isDefined(nameField)){
	   nameField.on('change', function(f,n,o){
	     	 var params=nameField.getValue();
	     	 if(params.trim()!=''){
	     	    OrganizationDirect.chkUname(params.toString(),function(result,E){
			        if (!result.success) {
			    	    Ext.MessageBox.show({title:'提示',msg:"登录名("+params+")已经有人使用！",modal:true,buttons:Ext.Msg.OK,
			    	    icon:Ext.Msg.INFO,width:250,closable:false,fn:function(){nameField.reset();nameField.focus(false,10);}});
			        }
			    });
			 }
	   }); 
	}
	
	var windows = new Ext.WindowGroup();
	var advanceWindow = new Ext.Window({
		width : 620,
		height : 590,
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
	
	var advanceWindow1 = new Ext.Window({
        width : 560,
		height : 520,
		maximizable : false,
		minimizable : false,
		resizable : false,
		constrain : true,
		title : '岗位选择',
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
						var roleid=advanceForm.getForm().findField('f_role_id');
						var rolename=advanceForm.getForm().findField('f_role_name');
						var records = grid1.getSelectedRecords();
						if(records.length > 0){
					        var v_roleid='';
					        var v_rolename='';
					        for(var i=0;i<records.length;i++){
					            if(v_roleid==''&&v_rolename==''){
					               v_roleid= records[i].get('id');
					               v_rolename=records[i].get('f_caption');
					            }
					            else{
					                v_roleid= v_roleid+';'+records[i].get('id');
					                v_rolename=v_rolename+';'+records[i].get('f_caption');
					            }
					        }
                            roleid.setValue(v_roleid);
                            rolename.setValue(v_rolename);  
                            advanceWindow1.hide();	
					     }
					}
				}]
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
		         if(str!=null&&str.trim()!=''){
	                rowEl.dom.setAttribute("ext:qtip", str); 
	             }
			}
			/*else{
			     var so = store.lastOptions;
	             var sop = so? so.params : null;
			     rowEl.dom.setAttribute("ext:qtip", ((sop && sop.start && sop.limit)? sop.start : 0)+ index + 1); 
			}*/
		} 
   });
   
    store1.on('load', function(t,records,o){
		if(records.length > 0) {
		   var rolevalue=advanceForm.getForm().findField('f_role_id').getValue();
		   if(rolevalue.trim()!=''){
				var arr=rolevalue.split(";");
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
	
	// 设置范围
	panel.setScope = function(){
	        var v_id=parseInt(advanceForm.getForm().findField('id').getValue(),10);
			var scopeTree = new Ext.tree.TreePanel({
				border : true,
				height : 250,
				autoScroll: true,
				rootVisible: false,
				root : {
					text : 'root',
					level : 0,
					bm : 'root'
				},
		        loader: new Ext.tree.TreeLoader({
		            directFn : UserScopeDirect.getOutline,
		        	paramOrder : ['bm', 'level', 'userid'],
		        	baseParams : {userid:v_id,level:0,bm:'root'},
		        	listeners : {
		        		'beforeload':function(loader, node){
		        			loader.baseParams.level = node.attributes.level;
		        			loader.baseParams.bm = node.attributes.bm;
		        		}
		        	}
				}),
				getSelectedRecords : function(){
				   return this.getChecked();
			    }
			});
			scopeTree.userid = v_id;
			scopeTree.on('checkchange', function(node, checked){
				if(node.noEvent){
					node.noEvent = false;
					return;
				}
				if(checked){
						switch(node.attributes.level){
							case 1:
							    for(var i=0;i<node.childNodes.length;i++){  //二级tree
									var child = node.childNodes[i];
									if(child.getUI().isChecked()){
										child.getUI().toggleCheck(false);
									}
									for(var j=0;j<child.childNodes.length;j++){  //三级tree
									    var child1 = child.childNodes[j];
									    if(child1.getUI().isChecked()){
											child1.getUI().toggleCheck(false);
										}
										for(var a=0;a<child1.childNodes.length;a++){  //四级tree
										    var child2 = child1.childNodes[a];
										    if(child2.getUI().isChecked()){
												child2.getUI().toggleCheck(false);
											}
											for(var b=0;b<child2.childNodes.length;b++){  //五级tree
											    var child3 = child2.childNodes[b];
											    if(child3.getUI().isChecked()){
													child3.getUI().toggleCheck(false);
												}
											}
										}
									}
								}
								node.collapse();
								break;
							case 2:
								for(var i=0;i<node.parentNode.childNodes.length;i++){
									var child = node.parentNode.childNodes[i];
									if(child.attributes.bm != node.attributes.bm){ //同级
										if(child.getUI().isChecked()){
											child.noEvent = true;
											child.getUI().toggleCheck(false);
										}
										child.collapse();
									}
									for(var j=0;j<child.childNodes.length;j++){ //三级tree
									    var child1 = child.childNodes[j];
									    if(child1.getUI().isChecked()){
											child1.getUI().toggleCheck(false);
										}
										for(var a=0;a<child1.childNodes.length;a++){  //四级tree
										    var child2 = child1.childNodes[a];
										    if(child2.getUI().isChecked()){
												child2.getUI().toggleCheck(false);
											}
											for(var b=0;b<child2.childNodes.length;b++){  //五级tree
											    var child3 = child2.childNodes[b];
											    if(child3.getUI().isChecked()){
													child3.getUI().toggleCheck(false);
												}
											}
										}
									}
								}
								node.collapse();
								break;
							case 3:
								node.expand();
								break;
							case 4:
								node.expand();
								break;
							case 5:
								break;
							}
				}else{
					  switch(node.attributes.level){
							case 1:
								node.expand();
								break;
							case 2:
								node.expand();
								break;
							case 3:
							    for(var i=0;i<node.childNodes.length;i++){ //四级tree
									var child = node.childNodes[i];
									if(child.getUI().isChecked()){
										child.getUI().toggleCheck(false);
									}
									for(var j=0;j<child.childNodes.length;j++){ //五级tree
									    var child1 = child.childNodes[j];
									    if(child1.getUI().isChecked()){
											child1.getUI().toggleCheck(false);
										}
									}
								}
								node.collapse();
								break;
							case 4:
							    for(var i=0;i<node.childNodes.length;i++){ //五级tree
									var child = node.childNodes[i];
									if(child.getUI().isChecked()){
										child.getUI().toggleCheck(false);
									}
								}
								node.collapse();
								break;
							case 5:
								break;
							}
				}
			});
			scopeTree.on('beforeexpandnode', function(node){
				switch(node.attributes.level){
				case 1:
					if(node.getUI().isChecked()){
						return false;
					}
					break;
				case 2:
					var hasSelected = false;
					for(var i=0;i<node.parentNode.childNodes.length;i++){
						if(node.parentNode.childNodes[i].getUI().isChecked()){
							hasSelected = true;
							break;
						}
					}
					if(hasSelected){
						return false;
					}
					break;
				case 3:
					if(!node.getUI().isChecked()){
						return false;
					}
					break;
				case 4:
					if(!node.getUI().isChecked()){
						return false;
					}
					break;
				}
			});
			scopeTree.on('render', function(){
				UserScopeDirect.getInitPath(scopeTree.userid, function(result, e){
					if(result && result.success){
						scopeTree.expandPath('/root/' + result.path, 'bm');
					}
				});
			});
			var win = new Ext.Window({
				title : '数据范围 选择',
				modal : true,
				height : 400,
				width : 400,
				layout : 'fit',
				items : scopeTree,
				buttonAlign : 'center',
				maximizable : false,
				minimizable : false,
				resizable : false,
				constrain : true,
				closable : false,
				manager : windows,
				bbar : [{
					text : '关闭',
					handler : function() {
						win.close();												
					}
				},'->',{
					text : '确定',
					handler : function() {
					    var v_gjdbmarr='',v_gjdmcarr='',v_yhbmarr='',v_yhmcarr='',v_cbwdbmarr='',v_cbwdmcarr='';
					    for(var i=0;i<scopeTree.getChecked().length;i++){
							var child = scopeTree.getChecked()[i];
							var level=child.attributes.level;
							if(level<=3){
							    if(v_gjdbmarr.trim()==''&&v_gjdmcarr.trim()==''){
							       v_gjdbmarr=child.attributes.bm;
					               v_gjdmcarr=child.attributes.text;
					            }
					            else{
					                v_gjdbmarr=v_gjdbmarr+';'+child.attributes.bm;
					                v_gjdmcarr=v_gjdmcarr+';'+child.attributes.text;
					            }
							}
							if(level==4){
							    if(v_yhbmarr.trim()==''&&v_yhmcarr.trim()==''){
							       v_yhbmarr=child.attributes.bm;
					               v_yhmcarr=child.attributes.text;
					            }
					            else{
					                v_yhbmarr=v_yhbmarr+';'+child.attributes.bm;
					                v_yhmcarr=v_yhmcarr+';'+child.attributes.text;
					            }
							}
							if(level==5){
							    if(v_cbwdbmarr.trim()==''&&v_cbwdmcarr.trim()==''){
							       v_cbwdbmarr=child.attributes.bm;
					               v_cbwdmcarr=child.attributes.text;
					            }
					            else{
					                v_cbwdbmarr=v_cbwdbmarr+';'+child.attributes.bm;
					                v_cbwdmcarr=v_cbwdmcarr+';'+child.attributes.text;
					            }
							}
							
						}
					    advanceForm.getForm().findField('f_gjdbm').setValue(v_gjdbmarr);
			            advanceForm.getForm().findField('f_gjdmc').setValue(v_gjdmcarr);
			            advanceForm.getForm().findField('f_yhbm').setValue(v_yhbmarr);
			            advanceForm.getForm().findField('f_yhmc').setValue(v_yhmcarr);
			            advanceForm.getForm().findField('f_cbwdbm').setValue(v_cbwdbmarr);
			            advanceForm.getForm().findField('f_cbwdmc').setValue(v_cbwdmcarr);
					    win.close();
					}
				}]
			});
			win.show();
	}
	
	/*签发CA证书*/
	panel.certsign = function(){
	     var idvalue = advanceForm.getForm().findField('id').getValue();
	     var namevalue = advanceForm.getForm().findField('f_name').getValue();
	     var dnvalue = advanceForm.getForm().findField('dndata').getValue();
	     var enrollpanel = new Ext.Panel({
	                title : '证书签发',
	                id : 'p-enroll',
					layout : 'fit',
					autoLoad : {
						url :  '/portal/jsppage.do',
						params : Ext.apply({url: 'ca/sign', panelid:'p-enroll',cert:'enrollcert',name:namevalue,id:idvalue,dndata:dnvalue}, {}),
						loadScripts : true,
						scripts	: true
					}
	          });
	          
	     var extendpanel = new Ext.Panel({
	                title : '证书延期',
	                id : 'p-extend',
					layout : 'fit',
					autoLoad : {
						url :  '/portal/jsppage.do',
						params : Ext.apply({url: 'ca/sign', panelid:'p-extend',cert:'extendcert',name:namevalue,id:idvalue}, {}),
						loadScripts : true,
						scripts	: true
					}
	          });
	     
	     var revokepanel = new Ext.Panel({
	                title : '证书作废',
	                id : 'p-revoke',
					layout : 'fit',
					autoLoad : {
						url :  '/portal/jsppage.do',
						params : Ext.apply({url: 'ca/sign', panelid:'p-revoke',cert:'revokecert',name:namevalue,id:idvalue}, {}),
						loadScripts : true,
						scripts	: true
					}
	          });
	     
	     var freezepanel = new Ext.Panel({
	                title : '证书冻结解冻',
	                id : 'p-freeze',
					layout : 'fit',
					autoLoad : {
						url :  '/portal/jsppage.do',
						params : Ext.apply({url: 'ca/sign', panelid:'p-freeze',cert:'freezecert',name:namevalue,id:idvalue}, {}),
						loadScripts : true,
						scripts	: true
					}
	          });
	          
	     var tabs = new Ext.TabPanel({
			 activeTab: 0,
			 deferredRender:true,  
             layoutOnTabChange:true,
			 animScroll:true,              //使用动画滚动效果  
             enableTabScroll:true,         //tab标签过宽时自动显示滚动条   
			 items: [enrollpanel,extendpanel,revokepanel,freezepanel]
			 });
	
	     var signwin = new Ext.Window({
				title : 'CA证书',
				modal : true,
				height : 420,
				width :660,
				layout : 'fit',
				items :  tabs,
				buttonAlign : 'center',
				resizable : false,
				closable : true,
				manager :  new Ext.WindowGroup()
			});
			signwin.show();
	}
				
	panel.emptyFormfield=function(){
		  advanceForm.getForm().findField('rowstate').setValue('add');		  
		  advanceForm.getForm().findField('id').setValue(0);
		  advanceForm.getForm().findField('f_name').setValue('');
		  advanceForm.getForm().findField('f_caption').setValue('');
		  advanceForm.getForm().findField('f_dept_id').setValue('');
		  advanceForm.getForm().findField('f_dept_name').setValue('');
		  advanceForm.getForm().findField('f_role_id').setValue('');
		  advanceForm.getForm().findField('f_role_name').setValue('');
	      advanceForm.getForm().findField('f_type').setValue(0);
	       advanceForm.getForm().findField('f_depttype').setValue(0);
		  advanceForm.getForm().findField('f_state').setValue(0);
		  advanceForm.getForm().findField('f_gjdbm').setValue('');
		  advanceForm.getForm().findField('f_gjdmc').setValue('');
		  advanceForm.getForm().findField('f_yhbm').setValue('');
		  advanceForm.getForm().findField('f_yhmc').setValue('');
		  advanceForm.getForm().findField('f_cbwdbm').setValue('');
		  advanceForm.getForm().findField('f_cbwdmc').setValue('');
		  advanceForm.getForm().findField('f_email').setValue('');
		  advanceForm.getForm().findField('f_cellphone').setValue('');
		  advanceForm.getForm().findField('f_note').setValue('');
		  advanceForm.getForm().findField('dndata').setValue('');
	}
	
	panel.setFormfield=function(records){
	      advanceForm.getForm().findField('rowstate').setValue('chg');		  
		  advanceForm.getForm().findField('id').setValue(records.get('id'));
		  advanceForm.getForm().findField('f_name').setValue(records.get('f_name'));
		  advanceForm.getForm().findField('f_caption').setValue(records.get('f_caption'));
		  advanceForm.getForm().findField('f_dept_id').setValue(records.get('f_dept_id'));
		  advanceForm.getForm().findField('f_dept_name').setValue(records.get('f_dept_name'));
		  advanceForm.getForm().findField('f_role_id').setValue(records.get('f_role_id'));
		  advanceForm.getForm().findField('f_role_name').setValue(records.get('f_role_name'));
		  var type=0;
		  var typestr=records.get('f_type');
		  if(typestr.trim()=='开发人员'){
		     type=0;
		  }
		  else if(typestr.trim()=='普通用户'){
		      type=1;
		  }
		  else if(typestr.trim()=='系统管理员'){
		      type=2;
		  }
		  else if(typestr.trim()=='中心用户'){
		      type=3;
		  }
		  else if(typestr.trim()=='单位用户'){
		      type=4;
		  }
		  else{
		      type=5;
		  }
	      advanceForm.getForm().findField('f_type').setValue(type);
	      advanceForm.getForm().findField('f_depttype').setValue(records.get('f_depttype'));
	      var state=0;
	      var statestr=records.get('f_state');
	      if(statestr.trim()=='正常'){
		      state=0;
		  }
		  else{
		      state=1;
		  }
		  advanceForm.getForm().findField('f_state').setValue(state);
		  advanceForm.getForm().findField('f_gjdbm').setValue(records.get('f_gjdbm'));
		  advanceForm.getForm().findField('f_gjdmc').setValue(records.get('f_gjdmc'));
		  advanceForm.getForm().findField('f_yhbm').setValue(records.get('f_yhbm'));
		  advanceForm.getForm().findField('f_yhmc').setValue(records.get('f_yhmc'));
		  advanceForm.getForm().findField('f_cbwdbm').setValue(records.get('f_cbwdbm'));
		  advanceForm.getForm().findField('f_cbwdmc').setValue(records.get('f_cbwdmc'));
		  advanceForm.getForm().findField('f_email').setValue(records.get('f_email'));
		  advanceForm.getForm().findField('f_cellphone').setValue(records.get('f_cellphone'));
		  advanceForm.getForm().findField('f_note').setValue(records.get('f_note'));
		  advanceForm.getForm().findField('dndata').setValue(records.get('dndata'));
	}
	
	var formValidator =function(){
	    var v_name=advanceForm.getForm().findField('f_name').getValue();
	    var v_caption=advanceForm.getForm().findField('f_caption').getValue();
	    var v_deptname=advanceForm.getForm().findField('f_dept_id').getRawValue();
	    var v_gjdmc=advanceForm.getForm().findField('f_gjdmc').getValue();
	    var v_role_name=advanceForm.getForm().findField('f_role_name').getValue();
	   
	    if(v_name.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"登录名不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    if(v_caption.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"姓名不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    if(v_deptname.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"所属部门不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    if(v_role_name.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"所属岗位不能为空!",
		   modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.WARNING,width:200,closable:false});
		   return false;
	    }
	    if(v_gjdmc.trim()== ""){
	       Ext.MessageBox.show({title:'提示',msg:"归集点不能为空!",
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
		   Ext.MessageBox.show({title:'提示',wait:true,msg:"正在进行用户信息保存,请稍候...",
           modal:true,icon:Ext.Msg.WARNING,width:300,closable:false});
           if(chg==1){
			   OrganizationDirect.userSave(params,function(result,e){
				   	if (result&&result.success) {
				    	Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
	                    icon:Ext.Msg.INFO,width:250,closable:false});
	                    advanceForm.getForm().reset();
	                    panel.emptyFormfield();
	                    panel.refresh();
			        }
			        else{
			            Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
		                icon:Ext.Msg.ERROR,width:250,closable:false});
			        }
			   });
			}
			else{
			    OrganizationDirect.userChg(params,function(result,e){
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
	advanceForm.doLayout();
	panel.refresh();
});
</script>