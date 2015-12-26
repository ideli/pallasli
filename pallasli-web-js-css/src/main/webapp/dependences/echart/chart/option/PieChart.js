Ext.define('Pallas.chart.option.PieChart', {
    alias: 'pallas.chart.option.pie', 
	mixins: {
        observable: 'Ext.util.Observable'
    },

    
    //---------------配置属性---begin-------------//

	timeline : {
		data : [
            '2013-01-01', '2013-02-01', '2013-03-01', '2013-04-01', '2013-05-01',
            { name:'2013-06-01', symbol:'emptyStar6', symbolSize:8 },
            '2013-07-01', '2013-08-01', '2013-09-01', '2013-10-01', '2013-11-01',
            { name:'2013-12-01', symbol:'star6', symbolSize:8 }
        ],
        label : {
            formatter : function(s) {
                return s.slice(0, 7);
            }
        }
    },
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
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
	    legend : null
    },
    //---------------配置属性---end-------------//
    
    

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 

        me.option.legend={data:[]};
    },

    getOptionJson: function(){
        var me = this;
        
        
    	var optionJson={};
    	return Ext.apply(optionJson,me.option) ;
    },
    init: Ext.emptyFn
});