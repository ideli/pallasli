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
				
				
				var store1 = new Ext.data.JsonStore({
					data: [{name:'保定市',k:10},{name:'沧州市',k:2},{name:'石家庄市',k:'如图'},{name:'沧州',k:20},{name:'河北',k:20}],
					fields: ['name',  {name:'value', mapping:'k'}]
				});
				
				var chart1_Id=Ext.id();
				var chart2_Id=Ext.id();
				var chart3_Id=Ext.id();
				var chart4_Id=Ext.id();
				var chart1={
					xtype:'wachart',
					type:'map',
					id:chart1_Id,
					store:store1,
					seriesName:'1',
					seriesMapType:'河北',
			dataRange:{y: 'bottom',noteText:['如图','啊','但','的'],color:['red','lightgreen'],calculable : false,splitNumber:4,min:0,max:3},
					
					anchor:'70% 70%'
				}
				var chart2=new Atwasoft.chart.Chart({
					type:'pie',
					store:store1,
					id:chart2_Id,
					anchor:'30% 50%'
				});
				var chart3={
					xtype:'wachart',
					type:'pie',
					store:store1,
					id:chart3_Id,
					anchor:'30% 50%'
				}
				var chart4={
					xtype:'wachart',
					type:'pie',
					store:store1,
					id:chart4_Id,
					anchor:'30% 50%'
				}
				
				var chartPanel=new Ext.Panel({
					title:'chartPanel',
					height:800,
					layout:'anchor',
					border:true,
				});
				
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
				var mainPanel=new Ext.Panel({
					title:'mainPanel',
					region:"center",
					layout:"fit",
					border:true,
					autoScroll:true
				});
				//chartPanel.add(chart2);
				//chartPanel.add(chart3);
				//chartPanel.add(chart4);
				mainPanel.on('afterlayout',function(){
				var chart_field1=Ext.getCmp(chart1_Id);
				//var chart_field2=Ext.getCmp(chart2_Id);
				///var chart_field3=Ext.getCmp(chart3_Id);
				//var chart_field4=Ext.getCmp(chart4_Id);
				//chart_field1.show(true);
				//chart_field2.show(true);
				//chart_field3.show(true);
				//chart_field4.show(true);
				//chart_field1.on('mapSelected',function(param){console.log(param)});
				});
				//setTimeout(function(){chartPanel.add(chart1);mainPanel.doLayout();},30);
				mainPanel.add(chartPanel); 
				var portal=new Ext.Viewport({
					title:"",
					autoScroll:true,
					border:true,
					layout:'border',
					items:[headPanel,treePanel,mainPanel]
				});
				portal.doLayout();
				chartPanel.add(chart1);
				
			});
			
		</script>
  </body>
</html>
