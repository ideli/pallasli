Ext.namespace("Atwasoft.chart");
//var fileLocation = '/pallasli-web-js-css/scripts/echart/echarts-original-map';
var fileLocation = '../pallasli-web-js-css/scripts/echarts-2.0.0/build/echarts-original-map';
var zrenderLocation = '../pallasli-web-js-css/scripts/echarts-2.0.0/zrender-2.0.0/src';
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
        zrender: zrenderLocation,
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
};

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
};
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
};
function cloneObject(myObj){
	var myNewObj = {};
	if(Object.prototype.toString.call(myObj)=='[object Array]') { 
		myNewObj = [];
		var len=myObj.length;
		for(var i=0;i<len;i++){
			myNewObj[i] = cloneObject(myObj[i]);
		}
		return myNewObj;
	}
	if(typeof(myObj) != 'object'||myObj == null) { 
		return myObj;
	}
	for(var i in myObj){
		myNewObj[i] = cloneObject(myObj[i]);
	}
	return myNewObj;
}
Atwasoft.chart._echartsConfig= cloneObject(echartsConfig_copy);

Ext.apply(Atwasoft.chart._echartsConfig.line,{ 

});

Ext.apply(Atwasoft.chart._echartsConfig.bar,{  
	 
});

Ext.apply(Atwasoft.chart._echartsConfig.k,{
	 
});

Ext.apply(Atwasoft.chart._echartsConfig.scatter,{

});
Ext.apply(Atwasoft.chart._echartsConfig.map,{
	toolbox:{show : true,x: 'right',y: 30}, 
	dataRange:{y: 'bottom',color:['red','lightgreen'],calculable : true,min:0,max:100}

});
Atwasoft.chart._defaultConfig={
		'pie':Atwasoft.chart._echartsConfig.pie,
		'line':Atwasoft.chart._echartsConfig.line,
		'bar':Atwasoft.chart._echartsConfig.bar,
		'map':Atwasoft.chart._echartsConfig.map,
		'scatter':Atwasoft.chart._echartsConfig.scatter,
		'k':Atwasoft.chart._echartsConfig.k,
		'force':Atwasoft.chart._echartsConfig.force,
		'radar':Atwasoft.chart._echartsConfig.radar,
		'chord':Atwasoft.chart._echartsConfig.chord,

		'state':{series:[]},
		'mixed':{
			title:{},
			xAxis:[],
			yAxis:[],
			legend:{},
			toolbox:{},
			series:[]		
		}		
};