Ext.define('Pallas.chart.PieChart', {
	mixins: {
        observable: 'Ext.util.Observable'
    },
	constructor: function (config) {
        this.mixins.observable.constructor.call(this, config);
        this.init();
    },
    
    
    title:{
	    	text:"",
	    	link:null,
	    	subtext:"",
	    	sublink:null,
	    	x:"left",
	    	y:"top",
	    	textAlign:null,
	    	backgroundColor:"rgba(0,0,0,0)",
	    	borderColor:"#ccc",
	    	borderWidth:0,
	    	padding:5,
	    	itemGap:10,
	    	textStyle:{
		    	fontSize:18,
		    	fontWeight:"bolder",
		    	color:"#333"
	    	},
	    	subtextStyle:{
	    		color:"#aaa"
	    	}
    	},

    init: Ext.emptyFn
});