Ext.define('Pallas.chart.option.ScatterChart', {
    alias: 'pallas.chart.option.scatter', 
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
            trigger: 'item'
        },
	   // legend : null,
	   // dataRange : null,
	   // dataZoom : null,
	  //  grid : null,
	    xAxis : [
			        {
			            type : 'value',
			            splitArea : {show : true}
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