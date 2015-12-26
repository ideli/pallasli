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
				
				
				
				var chart1_Id=Ext.id();
				var chart1={
					xtype:'wachart',
					type:'mixed',
					id:chart1_Id,
					tooltip : {
						trigger: 'axis'
					},
					toolbox: {
						show : true,
						y: 'bottom',
						feature : {
							mark : true,
							dataView : {readOnly: false},
							magicType:['line', 'bar'],
							restore : true,
							saveAsImage : true
						}
					},
					calculable : true,
					legend: {
						data:['直接访问','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
					},
					xAxis : [
						{
							type : 'category',
							splitLine : {show : false},
							data : ['周一','周二','周三','周四','周五','周六','周日']
						}
					],
					yAxis : [
						{
							type : 'value',
							position: 'right',
							splitArea : {show : true}
						}
					],
					series : [
						{
							name:'直接访问',
							type:'bar',
							data:[320, 332, 301, 334, 390, 330, 320]
						},
						{
							name:'邮件营销',
							type:'bar',
							tooltip : {trigger: 'item'},
							stack: '广告',
							data:[120, 132, 101, 134, 90, 230, 210]
						},
						{
							name:'联盟广告',
							type:'bar',
							tooltip : {trigger: 'item'},
							stack: '广告',
							data:[220, 182, 191, 234, 290, 330, 310]
						},
						{
							name:'视频广告',
							type:'bar',
							tooltip : {trigger: 'item'},
							stack: '广告',
							data:[150, 232, 201, 154, 190, 330, 410]
						},

						{
							name:'搜索引擎细分',
							type:'pie',
							tooltip : {
								trigger: 'item',
								formatter: '{a} <br/>{b} : {c} ({d}%)'
							},
							center: [160,130],
							radius : [0, 50],
							itemStyle :{
								normal : {
									labelLine : {
										length : 20
									}
								}
							},
							data:[
								{value:1048, name:'百度'},
								{value:251, name:'谷歌'},
								{value:147, name:'必应'},
								{value:102, name:'其他'}
							]
						},
						
					],
					anchor:'100% 50%'
				};
				
				 var chart1=new Atwasoft.chart.Chart({
     type:'mixed',
     id:chart1_Id,
     tooltip : {
      trigger: 'axis'
     },
     toolbox: {
      show : true,
      y: 'bottom',
      feature : {
       mark : true,
       dataView : {readOnly: false},
       magicType:['line', 'bar'],
       restore : true,
       saveAsImage : true
      }
     },
     calculable : true,
     legend: {
      data:['直接访问','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
     },
     xAxis : [
      {
       type : 'category',
       splitLine : {show : false},
       data : ['周一','周二','周三','周四','周五','周六','周日']
      }
     ],
     yAxis : [
      {
       type : 'value',
       position: 'right',
       splitArea : {show : true}
      }
     ],
     series : [
      {
       name:'直接访问',
       type:'bar',
       data:[320, 332, 301, 334, 390, 330, 320]
      },
      {
       name:'邮件营销',
       type:'bar',
       tooltip : {trigger: 'item'},
       stack: '广告',
       data:[120, 132, 101, 134, 90, 230, 210]
      },
      {
       name:'联盟广告',
       type:'bar',
       tooltip : {trigger: 'item'},
       stack: '广告',
       data:[220, 182, 191, 234, 290, 330, 310]
      },
      {
       name:'视频广告',
       type:'bar',
       tooltip : {trigger: 'item'},
       stack: '广告',
       data:[150, 232, 201, 154, 190, 330, 410]
      },
      {
       name:'搜索引擎细分',
       type:'pie',
       tooltip : {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
       },
       center: [160,130],
       radius : [0, 50],
       itemStyle :{
        normal : {
         labelLine : {
          length : 20
         }
        }
       },
       data:[
        {value:1048, name:'百度'},
        {value:251, name:'谷歌'},
        {value:147, name:'必应'},
        {value:102, name:'其他'}
       ]
      },
      
     ],
     anchor:'100% 80%'
    });
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
					layout:"fit",
					region:"center",
					border:true,
					autoScroll:true
				});
				
				chartPanel.add(chart1);
				//var chart_field1=Ext.getCmp(chart1_Id);
				
				
				chart1.addSeries({
            name:'搜索引擎',
            type:'line',
            data:[862, 1018, 964, 1026, 1679, 1600, 1570]
        });
				
				
				//chart1.show(true);
				chart1.on('click',function(){});
				mainPanel.add(chartPanel);
				new Ext.Viewport({
					title:"",
					autoScroll:true,
					border:true,
					layout:'border',
					items:[headPanel,treePanel,mainPanel]
				});
			});
			
		</script>
  </body>
</html>
