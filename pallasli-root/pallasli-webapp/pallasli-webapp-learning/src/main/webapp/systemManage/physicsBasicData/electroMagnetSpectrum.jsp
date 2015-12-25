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
			
	
//=================================================================
  var store = new Ext.data.JsonStore({
         root : 'data',
         totalProperty: 'totalCount',
         idProperty: 'id',
         fields : ['id' ,'englishName', 'chineseName', 'minWaveLength', 'maxWaveLength', 'waveLengthUnit'],
         sortInfo:{field:'id',direction:'DESC'},
         proxy: new Ext.data.HttpProxy({
         url: 'electromagneticSpectrum.ext?method=getInfo'
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
	                          					name : 'minWaveLength',
	                          					type : 'string'
	                          				}, {
	                          					name : 'maxWaveLength',
	                          					type : 'String'
	                          				},  {
	                          					name : 'waveLengthUnit',
	                          					type : 'string'
	                          				},  {
	                          					name : 'englishName',
	                          					type : 'string'
	                          				},  {
	                          					name : 'chineseName',
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
			fieldLabel:'英文名称',
			height:20,
			width:300,
			value:record.englishName,
			readOnly:true
			},{
			xtype:'textfield',
			fieldLabel:'英文名称',
			height:20,
			width:300,
			value:record.chineseName,
			readOnly:true
			},{
			xtype:'textarea',
			fieldLabel:'最小波长',
			height:60,
			width:300,
			value:record.minWaveLength,
			readOnly:true
			},{
			xtype:'textarea',
			fieldLabel:'最大波长',
			height:60,
			width:300,
			value:record.maxWaveLength,
			readOnly:true
			},{
			xtype:'textarea',
			fieldLabel:'波长单位',
			height:60,
			width:300,
			value:record.waveLengthUnit,
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
			fieldLabel:'英文名称',
			height:20,
			width:300,
			name:'englishName',
			value:record.englishName
			},{
			xtype:'textfield',
			fieldLabel:'中文名称',
			height:20,
			width:300,
			name:'chineseName',
			value:record.chineseName
			},{
			xtype:'textarea',
			fieldLabel:'最小波长',
			height:60,
			width:300,
			name:'minWaveLength',
			value:record.minWaveLength
			},{
			xtype:'textarea',
			fieldLabel:'最大波长',
			height:60,
			width:300,
			name:'maxWaveLength',
			value:record.maxWaveLength
			},{
			xtype:'textarea',
			fieldLabel:'波长单位',
			height:60,
			width:300,
			name:'waveLengthUnit',
			value:record.waveLengthUnit
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
url:'electromagneticSpectrum.tab?method=save',
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
							header : "英文名",
							width:150,
							dataIndex : "englishName",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})
						}, {
							header : "中文名",
							width:150,
							dataIndex : "chineseName",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})
						}, {
							header : "最小波长",
							width:150,
							dataIndex : "minWaveLength",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "最大波长",
							width:150,
							dataIndex : "maxWaveLength",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						}, {
							header : "波长单位",
							width:500,
							dataIndex : "waveLengthUnit",
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
