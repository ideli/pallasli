Ext.define('Pallas.chart.option.BarChart', {
    alias: 'pallas.chart.option.bar', 
	mixins: {
        observable: 'Ext.util.Observable'
    },
    
    //---------------配置属性---begin-------------//
    option:{
    	title : {
	    	x:'left',
	    	y:'top'
	    },
	    toolbox : {
	    	show:true,
	    	feature:{
	    	    mark : {
	    	        show : true,
	    	        title : {
	    	            mark : '辅助线开关',
	    	            markUndo : '删除辅助线',
	    	            markClear : '清空辅助线'
	    	        },
	    	        lineStyle : {
	    	            width : 2,
	    	            color : '#1e90ff',
	    	            type : 'dashed'
	    	        }
	    	    },
	    	    dataZoom : {
	    	        show : true,
	    	        title : {
	    	            dataZoom : '区域缩放',
	    	            dataZoomReset : '区域缩放后退'
	    	        }
	    	    },
	    	    dataView : {
	    	        show : true,
	    	        title : '数据视图',
	    	        readOnly: false,
	    	        lang : ['Data View', 'close', 'refresh']
	    	    },
	    	    magicType: {
	    	        show : true,
	    	        title : {
	    	            line : '折线图切换',
	    	            bar : '柱形图切换',
	    	            stack : '堆积',
	    	            tiled : '平铺'
	    	        },
	    	        type : ['line','bar','stack','tiled']
	    	    },
	    	    restore : {
	    	        show : true,
	    	        title : '还原'
	    	    },
	    	    saveAsImage : {
	    	        show : true,
	    	        title : '保存为图片',
	    	        type : 'png',
	    	        lang : ['点击保存'] 
	    	    }
	    	} 
	    },
        tooltip : {
            trigger: 'axis'
        },
	   // legend : null,
	   // dataRange : null,
	   // dataZoom : null,
	  //  grid : null,
	    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : ['周一','周二','周三','周四','周五','周六','周日']
			        }
			    ],
	    yAxis : [
			        {
			            type : 'value',
			            splitArea : {show : true}
			        }
			    ]
    },
    //---------------配置属性---end-------------//

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 
        
         
    },
    getOptionJson: function(){
        var me = this;
    	var optionJson={};
    	return Ext.apply(optionJson,me.option) ;
    },
    init: Ext.emptyFn
});