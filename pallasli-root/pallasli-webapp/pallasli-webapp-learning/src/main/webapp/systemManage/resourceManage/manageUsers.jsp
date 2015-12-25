<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>

		
		
		<script type="text/javascript">

	Ext.onReady(function(){
		var data={
			rows:${data}
		}
		addStore();

				/**
				 * 页面刷新
				 */
				function pageFresh() {
					window.location.reload();
				}
			
			
function addStore(){	

//---------------------------------------------------------------------------------

      var store = new Ext.data.JsonStore({
         root : 'data',
         totalProperty: 'totalCount',
         idProperty: 'id',
         fields : ['id' ,'username', 'chineseName','password'],
         sortInfo:{field:'id',direction:'DESC'},
         proxy: new Ext.data.HttpProxy({
         url: 'manageUsers.ext?method=getInfo'
     })
   });
   

//==============================================================
			
			//===========================================================                          				
			var paging = new Ext.PagingToolbar({
						pageSize : 10,
						store : store,
						displayInfo : true,

						beforePageText : '第',
						displayMsg : '显示 {0} - {1} 条记录，共 {2} 条记录'
					});
//---------------------------------------------------------------------------
//===========================================================================


	var wpRecord = Ext.data.Record.create( [
	                          				{
	                          					name : 'id',
	                          					type : 'string'
	                          				}, {
	                          					name : 'username',
	                          					type : 'string'
	                          				}, {
	                          					name : 'chineseName',
	                          					type : 'String'
	                          				}, {
	                          					name : 'password',
	                          					type : 'String'
	                          				}]);
	                          				
	var RefRecord = Ext.data.Record.create( [
	                          				{
	                          					name : 'id',
	                          					type : 'string'
	                          				}, {
	                          					name : 'usersId',
	                          					type : 'string'
	                          				}, {
	                          					name : 'rolesId',
	                          					type : 'String'
	                          				}]);
	                          				
			var sm = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true,
						hidden:true
					});
			sm.on('rowselect', function(sm_, rowIndex, record) {// 行选中的时候
			
						var records = sm.getSelections();

						for (var i = 0; i < records.length; i++) {
							var data = records[i].data;
						
						}


					}, this);
			sm.on('rowdeselect', function(sm_, rowIndex, record) {// 行未选中的时候
						var records = sm.getSelections();
						for (var i = 0; i < records.length; i++) {
							var data = records[i].data;
							
						}

					}, this);

//=====================================================================

var alterBut= new Ext.Action({
	                                id: 'alterButId',
	                                text: '修改',
	                                handler: function(){

	                                var record = sm.getSelections();
										showAlterWindow(record);
	                            	}
	                            });
var queryBut= new Ext.Action({ 
	                                id: 'queryButId',
	                                text: '查看',
	                                handler: function(){
	                                var record = sm.getSelections();
										showQueryWindow(record);
	                            	}
	                            });
var addBut= new Ext.Action({ 
	                                id: 'addButId',
	                                text: '添加',
	                                handler: function(){
										showAddWindow();
	                            	}
	                            });

var delBut= new Ext.Action({ 
	                                id: 'delButId',
	                                text: '删除',
	                                handler: function(){
	                                var records = sm.getSelections();
										deleteOne(records);
	                            	}
	                            });
	                            

var chooseRolesBut= new Ext.Action({ 
	                                id: 'chooseRolesButId',
	                                text: '选择角色',
	                                handler: function(){
	                                var records = sm.getSelections();
										showChooseRolesWindow(records);
	                            	}
	                            });
//-------------------------------------------------------------
var record;
//===========================================================

function showChooseRolesWindow(records){
if(records.length>0){
record = records[0].data;
				}	else{return;}	



   var storeRoles = new Ext.data.JsonStore({
         root : 'data',
         totalProperty: 'totalCount',
         idProperty: 'id',
         fields : ['id' ,'roleName', 'chineseName', 'description'],
         sortInfo:{field:'id',direction:'DESC'},
         proxy: new Ext.data.HttpProxy({
         url: 'manageRoles.ext?method=getInfo'
     })

   });
	var sms = new Ext.grid.CheckboxSelectionModel({
						singleSelect : false
					});
	sms.on('rowselect', function(sms_, rowIndex, record) {// 行选中的时候
			
						var records = sms.getSelections();

						for (var i = 0; i < records.length; i++) {
							var data = records[i].data;
						
						}


					}, this);
			sms.on('rowdeselect', function(sms_, rowIndex, record) {// 行未选中的时候
						var records = sms.getSelections();
						for (var i = 0; i < records.length; i++) {
							var data = records[i].data;
							
						}

					}, this);
	var refPaging = new Ext.PagingToolbar({
						pageSize : 2,
						store : storeRoles,
						displayInfo : true,

						beforePageText : '第',
						displayMsg : '显示 {0} - {1} 条记录，共 {2} 条记录'
					});
	var refRecord = Ext.data.Record.create( [
	                          				{
	                          					name : 'id',
	                          					type : 'string'
	                          				}, {
	                          					name : 'roleName',
	                          					type : 'string'
	                          				}, {
	                          					name : 'chineseName',
	                          					type : 'String'
	                          				},  {
	                          					name : 'description',
	                          					type : 'string'
	                          				}]);
	                          		

var rolesChooseBut= new Ext.Action({ 
	                                id: 'rolesChooseButId',
	                                text: '保存',
	                                handler: function(){
	                                var records = sms.getSelections();
	alert(records.length);									
for(var i=0;i<records.length;i++){
alert(Ext.encode(records[i].data));
}

	                            	}
	                            });
	                            
	   var refPanel = new Ext.grid.EditorGridPanel({
				height : 400,
				sm : sms,
				columns : [sms,{
							dataIndex : 'id',
							hidden:true
						},  {
							header : "角色",
							width:150,
							dataIndex : "roleName"
						}, {
							header : "名称",
							width:150,
							dataIndex : "chineseName"
						}, {
							header : "描述",
							width:200,
							dataIndex : "description"

						}],
				selModel : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : storeRoles,
				autoExpandColumn : 2,
				bbar : refPaging

			});                       				
	  var refWindow=new Ext.Window({
				title:'角色选择',
				height:'auto',
				width:600,
				forceFit : true,
				modal : true,
				items:[refPanel],
				style:{
				marginTop:'20px'
				},
				buttonAlign : 'center',
				buttons :[rolesChooseBut]
		});

		refWindow.show();
	                          				
	  	storeRoles.reload( {
							params : {
								start : 0,
								limit : 2
							}
						});

}




function deleteOne(records){

if(records.length>0){
record = records[0].data;
				}	else{return;}	
	Ext.MessageBox.show({
										title : '提示!',
										msg : '你确定要删除吗？',
										buttons : Ext.MessageBox.OKCANCEL,
										fn : function(btn) {
											if (btn == "ok") {
Ext.Ajax.request({
url:'manageUsers.ext?method=delete',
params:{
jsonData:Ext.encode(record)
},
waitTitle:'',
waitMsg:'正在删除',
success:function(response, opts){

Ext.Msg.alert("提示","<font color='red' >删除成功</font>");

								store.reload();
},
failure:function(){
Ext.Msg.alert("提示","<font color='red' >删除失败</font>");
}
});
}
}
});
}
function showQueryWindow(records){

if(records.length>0){
record = records[0].data;
				}	else{return;}	

			var queryPanel=new Ext.form.FormPanel({
			
			border:false,
			bodyStyle:'padding:40 40 40 40',
			items:[{
			xtype:'textfield',
			fieldLabel:'编号',
			height:20,
			width:300,
			value:record.id,
			readOnly:true
			},{
			xtype:'textfield',
			fieldLabel:'用户名',
			height:20,
			width:300,
			value:record.username,  
			readOnly:true
			},{
			xtype:'textfield',
			fieldLabel:'姓名',
			height:20,
			width:300,
			value:record.chineseName,
			readOnly:true
			},{
			
			xtype:'fieldset',
			border:false,
			buttonAlign : 'center',
						buttons : [{
							text : '<font color=green>关闭</font>',
							handler : function() {
								queryWindow.close();
							}
						}]
			
			}]
			
			});			
			
			
			
var queryWindow=new Ext.Window({
title:'查询详细信息',
height:'auto',
width:600,
forceFit : true,
modal : true,
items:[queryPanel],
style:{
marginTop:'20px'
}
});

queryWindow.show();
}


function showAlterWindow(records){

if(records.length>0){

record = records[0].data;

				}	else{return;}	

var oldPassword=new Ext.form.TextField({

			fieldLabel:'原密码',
			height:20,
			width:300,
			inputType:'password',
			name:'oldPassword'
			

});
var checkPassword=new Ext.form.TextField({

			fieldLabel:'确认密码',
			height:20,
			width:300,
			inputType:'password',
			name:'checkPassword'
			

});
var newPassword=new Ext.form.TextField({

			fieldLabel:'新密码',
			height:20,
			width:300,
			inputType:'password',
			name:'password'
			

});

				var alterPanel=new Ext.form.FormPanel({
			
			border:false,
			bodyStyle:'padding:40 40 40 40',
			items:[{
			xtype:'textfield',
			fieldLabel:'编号',
			height:20,
			width:300,
			name:'id',
			value:record.id,
			readOnly:true
			},{
			xtype:'textfield',
			fieldLabel:'用户名',
			height:20,
			width:300,
			name:'username',
			value:record.username
			},{
			xtype:'textfield',
			fieldLabel:'姓名',
			height:20,
			width:300,
			name:'chineseName',
			value:record.chineseName
			},oldPassword,newPassword,checkPassword,{
			xtype:'fieldset',
			border:false,
			buttonAlign : 'center',
						buttons : [{
							text : '<font color=green>保存</font>',
							id : 'spButton',
							scope : this,
							handler : function() {
							
var jsonData=alterPanel.getForm().getValues();

Ext.Ajax.request({
url:'manageUsers.ext?method=save',
params:{
jsonData:Ext.encode(jsonData)
},
waitTitle:'',
waitMsg:'正在保存',
success:function(response, opts){

Ext.Msg.alert("提示","<font color='red' >保存成功</font>");
								alterWindow.close();
								store.reload();
},
failure:function(){
Ext.Msg.alert("提示","<font color='red' >保存失败</font>");
}
});
							}
						}, {
							text : '<font color=green>关闭</font>',
							handler : function() {
								alterWindow.close();
							}
						}]
			
			}]
			
			});			
var alterWindow=new Ext.Window({
title:'修改信息',
height:'auto',
width:600,
forceFit : true,
modal : true,
items:[alterPanel],
style:{
marginTop:'20px'
}
});

alterWindow.show();

}


//---------------------------------------------------------
function showAddWindow(){

var checkPassword=new Ext.form.TextField({

			fieldLabel:'确认密码',
			height:20,
			width:300,
			inputType:'password',
			name:'checkPassword'
			

});
var newPassword=new Ext.form.TextField({

			fieldLabel:'输入密码',
			height:20,
			width:300,
			inputType:'password',
			name:'password'
			

});

				var addPanel=new Ext.form.FormPanel({
			
			border:false,
			bodyStyle:'padding:40 40 40 40',
			items:[{
			xtype:'textfield',
			fieldLabel:'用户名',
			height:20,
			width:300,
			name:'username'
			},{
			xtype:'textfield',
			fieldLabel:'姓名',
			height:20,
			width:300,
			name:'chineseName'
			},newPassword,checkPassword,{
			xtype:'fieldset',
			border:false,
			buttonAlign : 'center',
						buttons : [{
							text : '<font color=green>保存</font>',
							id : 'spButton',
							scope : this,
							handler : function() {
							
var jsonData=addPanel.getForm().getValues();

Ext.Ajax.request({
url:'manageUsers.ext?method=save',
params:{
jsonData:Ext.encode(jsonData)
},
waitTitle:'',
waitMsg:'正在保存',
success:function(response, opts){

Ext.Msg.alert("提示","<font color='red' >保存成功</font>");
								addWindow.close();
								store.reload();
},
failure:function(){
Ext.Msg.alert("提示","<font color='red' >保存失败</font>");
}
});
							}
						}, {
							text : '<font color=green>关闭</font>',
							handler : function() {
								addWindow.close();
							}
						}]
			
			}]
			
			});			
var addWindow=new Ext.Window({
title:'添加信息',
height:'auto',
width:600,
forceFit : true,
modal : true,
items:[addPanel],
style:{
marginTop:'20px'
}
});

addWindow.show();

}
//=============================================================
var dbltext=new Ext.form.Label({
html:"<font color='red'>双击进入查看信息</font>"
});
//============================================================
			var grid = new Ext.grid.EditorGridPanel({
				 title : "用户",
				height : 400,
				renderTo : 'grid-div',
				sm : sm,
				columns : [sm,{
							dataIndex : 'id',
							hidden:true
						},  {
							header : "用户名",
							width:150,
							dataIndex : "username"
						}, {
							header : "姓名",
							width:150,
							dataIndex : "chineseName"
						}, {
							header : "  ",
							width:750,
							dataIndex : "chineseName"
						}, {
							dataIndex : "password",
							hidden:true
						}],
				selModel : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : store,
				autoExpandColumn : 2,
				bbar : paging,
				tbar : [queryBut,'-',alterBut,'-',addBut,'-',delBut,'-',chooseRolesBut,dbltext]

			});
			
			grid.on("dblclick",function(){
	                    var record = sm.getSelections();
						showQueryWindow(record);
			
			});
				store.reload( {
							params : {
								start : 0,
								limit : 10
							}
						});

			//----------------------------------------------------------------------------------
			
			
			}

			
			
			
			
	});
	
		</script>
	</head>
	<body>
		<div id="grid-div" >

		</div>
	</body>
</html>
