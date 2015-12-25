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
         fields : ['id' ,'planetName', 'chineseName', 'equaterialDiameter' ,'eDUnit', 'meanDistanceFromSun', 'mDFSUnit', 'siderealPeriod', 'sPUnit'],
         sortInfo:{field:'id',direction:'DESC'},
         proxy: new Ext.data.HttpProxy({
         url: 'solarSystem.ext?method=getInfo'
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

var ne=[
	                          				{
	                          					name : 'id',
	                          					type : 'string'
	                          				}, {
	                          					name : 'planetName',
	                          					type : 'string'
	                          				}, {
	                          					name : 'chineseName',
	                          					type : 'String'
	                          				},  {
	                          					name : 'equaterialDiameter',
	                          					type : 'string'
	                          				},  {
	                          					name : 'eDUnit',
	                          					type : 'string'
	                          				},  {
	                          					name : 'meanDistanceFromSun',
	                          					type : 'string'
	                          				} ,  {
	                          					name : 'mDFSUnit',
	                          					type : 'string'
	                          				},  {
	                          					name : 'siderealPeriod',
	                          					type : 'string'
	                          				} ,  {
	                          					name : 'sPUnit',
	                          					type : 'string'
	                          				} ];
			var wpRecord = Ext.data.Record.create(ne);
	                          				
//===========================================================================
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
								}	
				else{return;}	
				
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
							value:record.planetName,
							readOnly:true
							},{
							xtype:'textfield',
							fieldLabel:'中文名称',
							height:20,
							width:300,
							value:record.chineseName,
							readOnly:true
							},{
							xtype:'textarea',
							fieldLabel:'行星直径',
							height:60,
							width:300,
							value:record.equaterialDiameter,
							readOnly:true
							},{
							xtype:'textarea',
							fieldLabel:'直径单位',
							height:60,
							width:300,
							value:record.eDUnit,
							readOnly:true
							},{
							xtype:'textarea',
							fieldLabel:'轨道平均半径',
							height:60,
							width:300,
							value:record.meanDistanceFromSun,
							readOnly:true
							},{
							xtype:'textarea',
							fieldLabel:'轨道单位',
							height:60,
							width:300,
							value:record.mDFSUnit,
							readOnly:true
							},{
							xtype:'textarea',
							fieldLabel:'公转周期',
							height:60,
							width:300,
							value:record.siderealPeriod,
							readOnly:true
							},{
							xtype:'textarea',
							fieldLabel:'公转周期单位',
							height:60,
							width:300,
							value:record.sPUnit,
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

//====================================================================
			function showAlterWindow(records){

				if(records.length>0){
				
					record = records[0].data;
				
								}	
				else{return;}	

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
								name:'planetName',
								value:record.planetName
								},{
								xtype:'textfield',
								fieldLabel:'英文名称',
								height:20,
								width:300,
								name:'chineseName',
								value:record.chineseName
								},{
								xtype:'textarea',
								fieldLabel:'行星直径',
								height:60,
								width:300,
								name:'equaterialDiameter',
								value:record.equaterialDiameter
								},{
								xtype:'textarea',
								fieldLabel:'直径单位',
								height:60,
								width:300,
								name:'eDUnit',
								value:record.eDUnit
								},{
								xtype:'textarea',
								fieldLabel:'轨道平均半径',
								height:60,
								width:300,
								name:'meanDistanceFromSun',
								value:record.meanDistanceFromSun
								},{
								xtype:'textarea',
								fieldLabel:'轨道单位',
								height:60,
								width:300,
								name:'mDFSUnit',
								value:record.mDFSUnit
								},{
								xtype:'textarea',
								fieldLabel:'公转周期',
								height:60,
								width:300,
								name:'siderealPeriod',
								value:record.siderealPeriod
								},{
								xtype:'textarea',
								fieldLabel:'公转周期单位',
								height:60,
								width:300,
								name:'sPUnit',
								value:record.sPUnit
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
											url:'solarSystem.tab?method=save',
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
							dataIndex : "planetName",
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
							header : "行星直径",
							width:150,
							dataIndex : "equaterialDiameter",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "直径单位",
							width:150,
							dataIndex : "eDUnit",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						}, {
							header : "轨道平均半径",
							width:150,
							dataIndex : "meanDistanceFromSun",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "轨道单位",
							width:150,
							dataIndex : "mDFSUnit",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "公转周期",
							width:150,
							dataIndex : "siderealPeriod",
							editor : new Ext.form.TextArea({
								allowBlank : true
							})

						},{
							header : "公转周期单位",
							width:150,
							dataIndex : "sPUnit",
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
