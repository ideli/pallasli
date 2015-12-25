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

			
			
			
function addStore(){	
			
			//===========================================================                          				
			var paging = new Ext.PagingToolbar({
						pageSize : 5,
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
	                          					name : 'physicalQuantity',
	                          					type : 'string'
	                          				}, {
	                          					name : 'englishNameOfSIUnit',
	                          					type : 'String'
	                          				},  {
	                          					name : 'symbolOfSIUnit',
	                          					type : 'string'
	                          				},  {
	                          					name : 'chineseNameOfSIUnit',
	                          					type : 'string'
	                          				} ]);
	                          				

			var sm = new Ext.grid.CheckboxSelectionModel({
						singleSelect : true
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


//---------------------------------------------------------------------------------

var store=new Ext.data.JsonStore({
//root:"response.responseText",

	//					totalProperty : 'totalCount',
	//					idProperty : 'id',
	//					fields : ["id", "englishConstantName", "chineseConstantName", "symbol", "expression", "valueInSIUnits", "error", "unit"],
	//					proxy : new Ext.data.HttpProxy({
	//								url : 'fundamentalConstants.ext?method=getAllInfo',
	//								baseParams : {
//
	//								}
	//							})
	
	
	
root:"rows",
data:data,
fields:[
{name:'id',mapping:'id'},
{name:'physicalQuantity',mapping:'physicalQuantity'},
{name:'englishNameOfSIUnit',mapping:'englishNameOfSIUnit'},
{name:'symbolOfSIUnit',mapping:'symbolOfSIUnit'},
{name:'chineseNameOfSIUnit',mapping:'chineseNameOfSIUnit'}
],
autoLoad:true

});


//==============================================================

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

//-------------------------------------------------------------
var record;
//===========================================================
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
			fieldLabel:'物理量',
			height:20,
			width:300,
			value:record.physicalQuantity,  
			readOnly:true
			},{
			xtype:'textarea',
			fieldLabel:'常用标识',
			height:60,
			width:300,
			value:record.symbolOfSIUnit,
			readOnly:true
			},{
			xtype:'textfield',
			fieldLabel:'单位英文名称',
			height:20,
			width:300,
			value:record.englishNameOfSIUnit,
			readOnly:true
			},{
			xtype:'textarea',
			fieldLabel:'单位中文名称',
			height:60,
			width:300,
			value:record.chineseNameOfSIUnit,
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
			fieldLabel:'物理量',
			height:20,
			width:300,
			name:'physicalQuantity',
			value:record.physicalQuantity
			},{
			xtype:'textfield',
			fieldLabel:'常用标识',
			height:20,
			width:300,
			name:'symbolOfSIUnit',
			value:record.symbolOfSIUnit
			},{
			xtype:'textarea',
			fieldLabel:'单位英文名称',
			height:60,
			width:300,
			name:'englishNameOfSIUnit',
			value:record.englishNameOfSIUnit
			},{
			xtype:'textarea',
			fieldLabel:'单位中文名称',
			height:60,
			width:300,
			name:'chineseNameOfSIUnit',
			value:record.chineseNameOfSIUnit
			},{
			
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
url:'derivedSIUnits.tab?method=save',
params:{
jsonData:Ext.encode(jsonData)
},
waitTitle:'',
waitMsg:'正在保存',
success:function(response, opts){
var obj=Ext.decode(response.responseText);


Ext.Msg.alert("提示","<font color='red' >保存成功</font>");
								alterWindow.close();
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

//============================================================
			var grid = new Ext.grid.EditorGridPanel({
				 title : "基本单位",
				height : 400,
				renderTo : 'grid-div',
				sm : sm,
				columns : [sm,{
							dataIndex : 'id',
							hidden:true
						},  {
							header : "物理量",
							width:150,
							dataIndex : "physicalQuantity",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})
						}, {
							header : "常用标识",
							width:150,
							dataIndex : "symbolOfSIUnit",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "单位英文名称",
							width:150,
							dataIndex : "englishNameOfSIUnit",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "单位中文名称",
							width:650,
							dataIndex : "chineseNameOfSIUnit",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						}],
				selModel : new Ext.grid.RowSelectionModel({
							singleSelect : true
						}),
				store : store,
				autoExpandColumn : 2,
				bbar : paging,
				tbar : [queryBut,'-',alterBut]

			});
			
			
				//grid.getStore().reload({
				//		params : {
				//		}
		//	});


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
