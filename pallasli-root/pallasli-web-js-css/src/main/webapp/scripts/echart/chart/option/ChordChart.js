Ext.define('Pallas.chart.option.ChordChart', {
    alias: 'pallas.chart.option.chord', 
	mixins: {
        observable: 'Ext.util.Observable'
    },
    
    
    //---------------配置属性---begin-------------//
    option:{
    	//timeline : null,
	   // title : null,
	   // toolbox : null,
	   // tooltip : null,
	   // legend : null,
	   // dataRange : null,
	   // dataZoom : null,
	   // grid : null,
	   // xAxis : null,
	   // yAxis : null,
	   // polar : null
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