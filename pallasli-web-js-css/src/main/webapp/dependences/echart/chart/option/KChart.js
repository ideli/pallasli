Ext.define('Pallas.chart.option.KChart', {

    alias: 'pallas.chart.option.k', 
	mixins: {
        observable: 'Ext.util.Observable'
    },
    
    
    //---------------配置属性---begin-------------//
    option:{
    //	timeline : null,
	  //  title : null,
	  //  toolbox : null,
	  //  tooltip : null,
	   // legend : null,
	    //dataRange : null,
	   // dataZoom : null,
	   // grid : null,
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