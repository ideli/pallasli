Ext.define('Pallas.chart.option.MapChart', {

    alias: 'pallas.chart.option.map', 
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
	  //  tooltip : null,
	  //  legend : null,
	  //  dataRange : null 
    },
    //---------------配置属性---end-------------//
    

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 
        
         
    },

    getOptionJson: function(){
    	
    	dataRangeStyle = [
//0
 {
     min: 0,
     max: 2000,
     formatter : function(v, v2){
         if (v2 < 1000) { return '低于' + v2}
         else if (v > 1000) { return '高于' + v}
         else { return '中' }
     }
 },
//1
 {
     orient: 'horizontal', // 'vertical'
     min: 0,
     max: 2000,
     formatter : '{value} 到 {value2}'
 },
//2
 {
     text:['高','低'],           // 文本，默认为数值文本
     min: 0,
     max: 2000
 },
//3
 {
     orient: 'horizontal', // 'vertical'
     text:['高','低'],           // 文本，默认为数值文本
     min: 0,
     max: 2000
 },
//4
 {
     splitNumber: 0,
     min: 0,
     max: 2000
 },
//5
 {
     orient: 'horizontal', // 'vertical'
     splitNumber: 0,
     min: 0,
     max: 2000
 },
//6
 {
     splitNumber: 0,
     text:['高','低'],            // 文本，默认为数值文本
     min: 0,
     max: 2000
 },
//7
 {
     orient: 'horizontal', // 'vertical'
     splitNumber: 0,
     text:['高','低'],            // 文本，默认为数值文本
     min: 0,
     max: 2000
 },
//8
 {
     calculable : true,
     min: 0,
     max: 2000,
     formatter : function(v) {
         if (v > 1500) {return v + ' (高)'}
         else if (v < 500) {return v + ' (低)'}
         else {return v + ' (中)'};
     }
 },
//9
 {
     orient: 'horizontal',      // 'vertical'
     calculable : true,
     min: 0,
     max: 2000
 },
//10
 {
     color:['red','yellow'],    //颜色 
     text:['高','低'],            // 文本，默认为数值文本
     calculable : true,
     min: 0,
     max: 2000
 },
//11
 {
     orient: 'horizontal',      // 'vertical'
     color:['red','yellow'],    //颜色 
     text:['高','低'],            // 文本，默认为数值文本
     calculable : true,
     min: 0,
     max: 2000
 }
];
    	
        var me = this;
        
        me.option.dataRange=dataRangeStyle[0];
    	var optionJson={};
    	return Ext.apply(optionJson,me.option) ;
    },
    init: Ext.emptyFn
});