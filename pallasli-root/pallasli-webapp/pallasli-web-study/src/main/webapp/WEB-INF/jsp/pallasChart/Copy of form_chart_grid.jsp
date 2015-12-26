<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>from_chart_grid</title>
		<script type="text/javascript" src="../pallasli-web-js-css/scripts/include-ext.js?theme=classic"></script>
	 
		<script type="text/javascript" src="../pallasli-web-js-css/scripts/echart/esl/esl.js"></script>
		<script type="text/javascript" src="../pallasli-web-js-css/scripts/echart/Atwasoft.chart.js"></script>
		<script type="text/javascript" src="../pallasli-web-js-css/scripts/echart/Atwasoft.chart.Chart.js"></script>
  </head>
  <body >
		<script>
			Ext.onReady(function(){
				document.oncontextmenu=function(){return false};
				var formstore1 = new Ext.data.JsonStore({
					data: [{name:'201208',k:true},{name:'201206',k:false}],
					fields: ['name',  {name:'value',mapping:'k'}]
				});

				var chartform={
					xtype:'wachart',
					//type:'state',
					type:'pie',
					name:'chartform',
					id:'chartform',
					//store:formstore1,
					seriesData:[{name:'1月',value:true},{name:'2月',value:false}]
				}
				var text1={
					xtype:'textfield',
					anchor:'99%',
					fieldLabel:'证件号码'
				}
				var text2={
					xtype:'textfield',
					anchor:'99%',
					fieldLabel:'证件号码'
				}
				var text3={
					xtype:'label',
					anchor:'99%',
					text:'证件号码'
				}
				var text4={
					xtype:'textfield',
					anchor:'99%',
					fieldLabel:'证件号码'
				}
				var text5={
					xtype:'labelfield',
					anchor:'99%',
					text:'证件号码'
				}
				
				
				var store1 = new Ext.data.JsonStore({
					data: [{name:'201208',value:10},{name:'201206',value:20}],
					fields: ['name',  {name:'value', type: 'float',mapping:'k'}]
				});

				var store2 = new Ext.data.JsonStore({
					data: [{name:'201208',value:10,mc:'1'},{name:'201208',value:20,mc:'2'},{name:'201208',value:20,mc:'3'},{name:'201206',value:20,mc:'1'},{name:'201206',value:20,mc:'2'}],
					fields: ['name',  {name:'value', type: 'float',mapping:'k'},{name:'seriesName',mapping:'mc'}]
				});
				
				var chart1_Id=Ext.id();
				var chart2_Id=Ext.id();
				var chart3_Id=Ext.id();
				var chart4_Id=Ext.id();
				var chart1={
					xtype:'wachart',
					type:'pie',
					isFormField :true,
					id:chart1_Id,
					seriesName:'',
					store:store2,
					anchor:'30% 100%'
				}
				var chart2=new Atwasoft.chart.Chart({
					type:'bar',
					store:store2,
					id:chart2_Id,
					seriesName:'',
					anchor:'30% 100%'
				});
				var chart3={
					xtype:'wachart',
					type:'bar',
					seriesName:'',
					horizontalBar:true,
					store:store2,
					id:chart3_Id,
					anchor:'30% 100%'
				}
				var chart4={
					xtype:'wachart',
					type:'line',
					seriesName:'',
					store:store1,
					id:chart4_Id,
					anchor:'30% 100%'
				}
				
				var getItems=function(extComponent){
					var tmp={};
					var lenc=tmp.length;
					if(extComponent){
						if(extComponent.items&&extComponent.items.items){
							var len=extComponent.items.items.length;
							tmp={
								name:extComponent.name,
								value:extComponent.value,
								xtype:extComponent.xtype?extComponent.xtype:extComponent.defaultType
								};
							for(var i=0;i<len;i++){
								if(!tmp['children']){
									tmp['children']=[];
								}
								 tmp['children'][i]=getItems(extComponent.items.items[i]);
							}
						}else{
							tmp= {
								name:extComponent.name,
								value:extComponent.value,
								xtype:extComponent.xtype?extComponent.xtype:extComponent.defaultType
							}					
						}
					}
					return tmp;
				}
				function getCurNode(ret){
					var node;
					if(ret.xtype=='panel'){
						node=document.createElement('div');
						node.style.cssText="position:relative; width:1000px; height:200px;";
						node.innerHTML='ddd';
					}else if(ret.xtype=='formPanel'){
						node=document.createElement('div');
						node.style.cssText="position:relative; width:1000px; height:200px;";
						node.innerHTML='ddd';
					}else if(ret.xtype=='fieldSet'){
						node=document.createElement('div');
						node.style.cssText="position:relative; width:1000px; height:200px;";
						node.innerHTML='ddd';
					}else {
						node=document.createElement('div');
						node.style.cssText="position:relative; width:1000px; height:200px;";
						node.innerHTML='ddd';
					}
					return node;
				}
				
				function getNode(ret){
					var node;
					if(ret['children']){
						node=getCurNode(ret);
						console.log(node);
						for(var j=0;j<ret['children'].length;j++){
							var child=getNode(ret['children'][j]);
							node.appendChild(child);
						}
					}else{
						 node=getCurNode(ret);
					}	
					return node;
				}
				
				var formPanel=new Ext.form.FormPanel({
					title:'formPanel',
					height:220,
					collapsible:true,
					border:false,
					bbar:[{
						text:'收合',
						handler:function(){
							formPanel.collapse(true);
						}
					},'->',{
						text:'另存',
						handler:function(){
							var ret=getItems(mainPanel); len=ret.length;
							var node=getNode(ret);
							console.log(node);
							var jsIframe = document.createElement("iframe");
							//jsIframe.style.display = "none";//none iframe
							jsIframe.style.height='1000px';
							jsIframe.style.width='1000px';
							document.body.appendChild(jsIframe);
							with(window.frames[window.frames.length - 1]){
								document.open();
								var div=document.createElement('div');
								document.appendChild(div);
								div.appendChild(node);
								document.write(div.innerHTML);
								document.close();
							}
							
							var WordApp;
						    var Doc;
						    try{
								   WordApp = new ActiveXObject("Word.Application");
								   WordApp.Application.Visible=true; // show WORD?
									
									console.log(jsIframe);
								   window.frames[window.frames.length - 1].document.execCommand("SelectAll");
								   window.frames[window.frames.length - 1].document.execCommand("Copy");

								   Doc=WordApp.Documents.Add();
								   Doc.Activate();
								   Doc.Content.Paste();
								   Doc.Activate();
								   WordApp.DisplayAlerts=false;

								   try{ // maybe not saved
										   Doc.Close();
								   }catch(e){};

								   WordApp.DisplayAlerts=true;
								   WordApp.Quit();
						    }catch(e){ // maybe automation not permitted
								   alert("Cancled")
						    }
						}
					}],
					items:[
						{
							xtype:'fieldset',
							labelWidth:55,
							layout:'column',
							border:false,style : "margin:0px;padding-top:0px;padding-left:0px;padding-right:23px",
							items:[
								{xtype:'fieldset',layout:'form',columnWidth:0.33,labelWidth:55,border:false,items:[text1]},
								{xtype:'fieldset',layout:'form',columnWidth:0.33,labelWidth:55,border:false,items:[text2]},
								{xtype:'fieldset',style : "margin:0px;padding-top:0px;padding-bottom:0px;padding-left:0px;padding-right:23px",layout:'form',height:45,columnWidth:1,labelWidth:55,border:false,items:[chartform]},
								{xtype:'fieldset',layout:'form',columnWidth:0.33,labelWidth:55,border:false,items:[text3]},
								{xtype:'fieldset',layout:'form',columnWidth:0.33,labelWidth:55,border:false,items:[text4]}	
							]			
						}
					]
				});
				var gridPanel=new Ext.Panel({
					title:'gridPanel',
					height:300,
					border:true,
				});
				var chartPanel=new Ext.Panel({
					title:'chartPanel',
					height:300,
					layout:'anchor',
					border:true,
				});
				
				setTimeout(function(){
					gridPanel.setHeight(800);
				},2000);
				
				var headPanel=new Ext.Panel({
					title:'headPanel',
					height:100,
					border:true,
					region:'north'
				});
				var treePanel=new Ext.Panel({
					title:'treePanel',
					width:"20%",
					border:true,
					collapsible :true,
					region:'west'
				});
				mainPanel=new Ext.Panel({
					title:'mainPanel',
					id:"mainPanel",
					region:"center",
					layout:'anchor',
					border:true,
					autoScroll:true
				});
				
				chartPanel.add(chart1);
				chartPanel.add(chart2);
				chartPanel.add(chart3);
				chartPanel.add(chart4);
				
				mainPanel.on('afterlayout',function(){
				var chart_field1=Ext.getCmp(chart1_Id);
				var chart_field2=Ext.getCmp(chart2_Id);
				var chart_field3=Ext.getCmp(chart3_Id);
				var chart_field4=Ext.getCmp(chart4_Id);
				chart_field3.setGridPadding('60 20 20,90');
				chart_field4.setGridPadding('60 20 20,90');
				
				//chart_field1.show(true);
				chart_field1.on('pieSelected',function(param,data){
					//console.log(param);
					//console.log(data);
				});
				chart_field2.setStack({
					'1':'d','2':'d'
				})
				//chart_field2.show(true);
				//chart_field3.show(true);
				//chart_field4.show(true);
				
				chart_field1.on('click',function(param){
					//console.log(param);
				});
				chart_field2.on('click',function(param){
					//console.log(param);
				});
				chart_field3.on('click',function(param){
					//console.log(param);
				});
				chart_field4.on('click',function(param){
					//console.log(param);
				});
				
				
				chart_field1.on('contextmenu',function(param){
					//console.log(param);
				});
				
				chart_field2.setGrid({x:30,y:10,padding:'60 20 20,90'});
				//chart_field2.show(true);
				});
				mainPanel.add(formPanel);
				
				
				mainPanel.add(chartPanel);
				mainPanel.add(gridPanel);
				new Ext.Viewport({
					title:"",
					id:'ext-body',
					autoScroll:true,
					border:true,
					layout:'border',
					items:[headPanel,treePanel,mainPanel]
				});
				mainPanel.doLayout();
				//Ext.getCmp('chartform').show();
formPanel.getForm().findField('chartform').show();
				
				
			
				var chartOpen1=new Atwasoft.chart.Chart({
					type:'pie',
					store:store1,
					anchor:'50% 100%',
					toolbox:{show : true,x: 'right',y: 30,
						feature : {
							mark : true,
							dataView: {readOnly: true},
							enlarge:true,
							shrink:true,
							dragChart:true,
							saveAsImage:true,
							print:true,
							openWin:true,
							restore:true
						}
					}
				});
				var chartOpen2=new Atwasoft.chart.Chart({
					type:'pie',
					store:store1,
					anchor:'50% 100%'
				});
				Atwasoft.chart.openChartWin([chartOpen1,chartOpen2],{title:'test'});
				
			});
			/**
							
				function newWordDocumentFromServer()
				{
					unLoadWordDocument("mixkymsdoc", false);
					var url = getDocumentDownloadURI() + "?id=2";
					var documentpath = mixap_doc_root + "myword.doc";
					downloadDocument("mixkywebaccess", url, documentpath, "cookievalue");
					Sleep(this, 500);
					this.NextStep=function()
					{
						loadWordDocument("mixkymsdoc", documentpath, true, true, true);
					}
				}

				function saveWordDocumentToServer()
				{
					saveWordDocument("mixkymsdoc", "");
					Sleep(this, 500);
					this.NextStep=function()
					{
						unLoadWordDocument("mixkymsdoc", true);
						// upload it
						var url = getDocumentUploadURI() + "?si=< %=documentid% >&st=< %=panelKey% >&filetype=1&f&fn=myword.doc&u=< %=user.getF_caption()% >";
						// alert(url);
						var documentpath = mixap_doc_root + "myword.doc";
						uploadDocument("mixkywebaccess", url, documentpath, "");
					
						// load word document again
						loadWordDocument("mixkymsdoc", documentpath, true, true, true);
					}
				}

				function loadWordDocumentFromServer() {
					unLoadWordDocument("mixkymsdoc", false);
					var url = getDocumentDownloadURI() + "?id=< %=wordAttachmentId% >";
					var documentpath = mixap_doc_root + "myword.doc";
					downloadDocument("mixkywebaccess", url, documentpath, "cookievalue");
					Sleep(this, 500);
					this.NextStep=function()
					{
						loadWordDocument("mixkymsdoc", documentpath, true, true, true);
					}
				}
				var wordPanel = new Ext.Panel({
					autoScroll : true,
					border : false,
					//tbar : buttons,
					trackResetOnLoad : true,
					bodyStyle : "padding:10px;padding-left:0px;padding-right:23px",
					html:'<object classid="clsid:00460182-9E5E-11d5-B7C8-B8269041DD57" codebase="resources/ocx/mixky.doc.ocx#version=1.2" id="mixkymsdoc" width="100%" height="100%"><param name="BorderStyle" value="0"><param name="TitlebarColor" value="52479"><param name="TitlebarTextColor" value="0"><param name="Titlebar" value="0"><param name="Menubar" value="1"></object><object classid="clsid:7F949355-7E91-4877-8646-CA30C3BA98CB" codebase="resources/ocx/mixky.webaccess.ocx#version=1.0" id="mixapwebaccess" width="16" height="16"></object><object classid="clsid:7F949355-7E91-4877-8646-CA30C3BA98CB" codebase="mixap.webaccess.ocx#version=1.0" id="mixkywebaccess" width="16" height="16"></object>',
					tbar: buttons
				});

				**/
		</script>
  </body>
</html>
