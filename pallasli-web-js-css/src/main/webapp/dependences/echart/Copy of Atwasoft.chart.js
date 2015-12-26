Ext.namespace("Atwasoft.chart");
var fileLocation = '/pallasli-web-js-css/scripts/echart/echarts-original-map';
require.config({
//        paths: {
//            'js': 'echart/esl/js'
//        },
//        packages: [
//                   {
//                       name: 'echarts',
//                       location: '../dependences/echarts-1.4.1/src',
//                       main: 'echarts'
//                   },
//                   {
//                       name: 'zrender',
//                       //location: 'http://ecomfe.github.io/zrender/src',
//                       location: '../dependences/zrender-1.1.2/src',
//                       main: 'zrender'
//                   }
//               ]
    paths:{ 
        echarts: fileLocation,
        'echarts/chart/line': fileLocation,
        'echarts/chart/bar': fileLocation,
        'echarts/chart/scatter': fileLocation,
        'echarts/chart/k': fileLocation,
        'echarts/chart/pie': fileLocation,
        'echarts/chart/radar': fileLocation,
        'echarts/chart/map': fileLocation,
        'echarts/chart/chord': fileLocation,
        'echarts/chart/force': fileLocation//,
       // 'echarts/chart/state': fileLocation
    }
});
Atwasoft.chart._initConfig_=function(){
	require(
		    [
		        'echarts',
		        //'echarts/chart/state',
		        'echarts/chart/line',
		        'echarts/chart/bar',
		        'echarts/chart/scatter',
		        'echarts/chart/pie',
		        'echarts/chart/map',
		        'echarts/chart/k',
		        'echarts/chart/force'
		    ],
		    function(ec) {
				Atwasoft.chart._echarts_ = ec;
		    	Atwasoft.chart._ecConfig_ = require('echarts/config');
		    	//Atwasoft.chart._ecConfig_.EVENT['OPEN_WIN']='openWin';
		    }
		);
}

Atwasoft.chart.IconPath={
	ENLARGE:"../lytchart/icon/enlarge.png",
	SHRINK:"../lytchart/icon/shrink.png",
	PRINT:"../lytchart/icon/print.png",
	DRAGCHART:"../lytchart/icon/dragChart.png",
	ENLARGE2:"../lytchart/icon/enlarge2.png",
	SHRINK2:"../lytchart/icon/shrink2.png",
	PRINT2:"../lytchart/icon/print2.png",
	DRAGCHART2:"../lytchart/icon/dragChart2.png",
	DRAGCHARTING:"../lytchart/icon/dragCharting.png",
	MAGICPIE:"../lytchart/icon/saveAs2.png"
}
Atwasoft.chart.Images={};
for(var i in Atwasoft.chart.IconPath){
	//Atwasoft.chart.Images[i]=new Image();
//	Atwasoft.chart.Images[i].src=Atwasoft.chart.IconPath[i];
}

Atwasoft.chart._initConfig_();


//不支持xtype配置方式charts参数
Atwasoft.chart.openChartWin= function(charts,wincfg){
			var panel=new Ext.Panel({
				layout:'anchor',
				autoScroll:true
			});
			win = new Ext.Window({
				id : Ext.id(),
				title : wincfg&&wincfg.title?wincfg.title:'' ,
				autoScroll:true,
				border : false,
				maximizable : false,
				minimizable : false,
				resizable : false,
				constrain : true,
				width : 800,
				height : 550,	
				closeAction: 'hide',
				items : panel
			});
			win.layout = 'fit';
			win.show();
			if(charts&&charts.length){
				for(var i=0;i<charts.length;i++){
					panel.add(charts[i]);
					charts[i].show();
				}
			}else if(charts){
				panel.add(charts);
				//charts.show();
			}
			return win;
}

Atwasoft.chart._defaultConfig={
		'state':{series:[]},
		'pie':{
			title:{ text:' ',x:'center',y:'top'},
			tooltip:{trigger: 'item',formatter :"{a} <br/>{b} : {c} ({d}%)"},
			legend:{orient : 'vertical' ,x:'left',y:'top'},
			toolbox:{show:true,x:'right',y:'top',
				feature : {
					mark : {show: false},
					dataView: {show: true,readOnly: false},
					//magicType: {show: true,type:['line', 'bar']},
					//enlarge:{show: false},
					//shrink:{show: false},
					//dragChart:{show: false},
					saveAsImage:{show: false},
					//print:{show: false},
					//openWin:{show: true},
					restore:{show: true}
				}
			},
			series:[],
			calculable:true
		},
		'line':{
			title:{text:' ',x:'center',y:'top'},
			tooltip:{trigger: 'axis'},
			legend:{orient:'horizontal',y:'bottom',x:'center'},
			toolbox:{show : true,x: 'right',y: 30,
				feature : {
					mark : {show: false},
					dataView: {show: true,readOnly: false},
					magicType: {show: true,type:['bar','pie']},
					enlarge:{show: false},
					shrink:{show: false},
					dragChart:{show: false},
					saveAsImage:{show: false},
					print:{show: false},
					//openWin:{show: true},
					restore:{show: true}
				}
			},
			series:[],
			grid:{},
			dataZoom:{show:true,y: 50},
			xAxis :[
				{
					type : 'category',
					position: 'bottom',
					axisTick : {show:true,length: 5,lineStyle: {color: 'red',type: 'solid', width: 2 }},
						axisLabel: {show:true, interval: 'auto',  rotate: 45, margin: 0,textStyle: {color: 'blue'}
					},
					data :[]
				}
			],
			yAxis : [
				{
					type : 'value',
					position: 'left',
					axisTick : {  show:true,length: 5, lineStyle: { color: 'green',type: 'solid',width: 2}},
						axisLabel :{show:true,interval: 'auto', rotate: 0,margin: 18,textStyle: {color: '#1e90ff'}
					}
				},
				{   type : 'value',precision: 1,splitNumber: 10,splitLine : {show: false}}
			]
		},
		'bar':{
			title:{text:' ',x:'center',y:'top'},
			tooltip:{trigger: 'axis'},
			legend:{orient:'horizontal',y:'bottom',x:'center'},
			toolbox:{show : true,x: 'right',y: 30,
				feature : {
					mark : {show: false},
					dataView: {show: true,readOnly: false},
					magicType: {show: true,type:['line','pie']},
					enlarge:{show: false},
					shrink:{show: false},
					dragChart:{show: false},
					saveAsImage:{show: false},
					print:{show: false},
					//openWin:{show: true},
					restore:{show: true}
				}
			},
			series:[],
			grid:{},
			dataZoom:{show:true,y: 50},
			xAxis :[
				{
					type : 'category',
					position: 'bottom',
					axisTick : {show:true,length: 5,lineStyle: {color: 'red',type: 'solid', width: 2 }},
						axisLabel: {show:true, interval: 'auto',  rotate: 45, margin: 0,textStyle: {color: 'blue'}
					},
					data :[]
				}
			],
			yAxis : [
				{
					type : 'value',
					position: 'left',
					axisTick : {  show:true,length: 5, lineStyle: { color: 'green',type: 'solid',width: 2}},
						axisLabel :{show:true,interval: 'auto', rotate: 0,margin: 18,textStyle: {color: '#1e90ff'}
					}
				},
				{   type : 'value',precision: 1,splitNumber: 10,splitLine : {show: false}}
			]
		},
		'map':{
			title:{text:' ',x:'center',y:'top'},
			legend:{},
			toolbox:{show : true,x: 'right',y: 30,
				feature : {
					mark : {show: false},
					dataView: {show: true,readOnly: false},
					enlarge:{show: false},
					shrink:{show: false},
					dragChart:{show: false},
					saveAsImage:{show: false},
					print:{show: false},
					//openWin:{show: true},
					restore:{show: true}
				}
			}, 
			tooltip : { trigger: 'item'},
			series:[],
			dataRange:{y: 'bottom',color:['red','lightgreen'],calculable : true,min:0,max:100}
		},
		'scatter':{
			 title : {text:' ',x:'center',y:'top'},
			tooltip : {
				trigger: 'item'
			},
			legend: {
				data:[]
			},
			toolbox:{show : true,x: 'right',y: 30,
				feature : {
					mark : {show: true},
					dataZoom : {show: true},
					enlarge:{show: false},
					shrink:{show: false},
					dragChart:{show: false},
					saveAsImage:{show: false},
					print:{show: false},
					//openWin:{show: true},
					restore:{show: true}
				}
			},
			xAxis : [
				{
					type : 'value',
					power: 1,
					precision: 2,
					scale:true,
					axisLabel : {
						formatter: '{value} cm'
					}
				}
			],
			yAxis : [
				{
					type : 'value',
					power: 1,
					precision: 2,
					scale:true,
					splitArea : {show : true}
				}
			],
			series : []			
		},
		'k':{
			title:{text:' ',x:'center',y:'top'},
			tooltip : {
				trigger: 'axis'
			},
			legend: {
				data:[]
			},
			toolbox: {
				show : true,
				feature : {
					mark : {show: true},
					dataZoom : {show: true},
					dataView : {show: true,readOnly: false},
					restore : {show: true},
					saveAsImage : {show: true}
				}
			},
			dataZoom : {
				show : true,
				realtime: true,
				start : 50,
				end : 100
			},
			xAxis : [
				{
					type : 'category',
					boundaryGap : true,
					data : []
				}
			],
			yAxis : [
				{
					type : 'value',
					scale:true,
					precision: 2,
					splitNumber: 9,
					boundaryGap: [0.05, 0.05],
					splitArea : {show : true}
				},
				{
					type : 'value',
					scale:true,
					splitNumber: 9,
					boundaryGap: [0.05, 0.05],
					splitArea : {show : true}
				}
			],
			series : []		
		},
		'force':{
			title:{text:' ',x:'center',y:'top'},
			legend:{},
			toolbox:{},
			series:[]			
		},
		'mixed':{
			title:{},
			xAxis:[],
			yAxis:[],
			legend:{},
			toolbox:{},
			series:[]		
		}		
}