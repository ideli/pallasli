Ext.define('Pallas.chart.option.RadarChart', {
    alias: 'pallas.chart.option.radar', 
	mixins: {
        observable: 'Ext.util.Observable'
    },
    
    
    //---------------配置属性---begin-------------//
    option:{
    	//timeline : null,
	  //  title : null,
	   // toolbox : null,
	   // tooltip : null,
	   // legend : null,
	   // dataRange : null,
	   // dataZoom : null,
	   // grid : null,
	    polar : [
			        {
			            indicator : [
			                { text : '指标一' },
			                { text : '指标二' },
			                { text : '指标三' },
			                { text : '指标四' },
			                { text : '指标五' }
			            ],
			            center : ['25%',210],
			            radius : 150,
			            startAngle: 90,
			            splitNumber: 8,
			            name : {
			                formatter:'【{value}】',
			                textStyle: {color:'red'}
			            },
			            scale: true,
			            precision: 1,
			            //power: 100,
			            axisLine: {            // 坐标轴线
			                show: true,        // 默认显示，属性show控制显示与否
			                lineStyle: {       // 属性lineStyle控制线条样式
			                    color: 'green',
			                    width: 2,
			                    type: 'solid'
			                }
			            },
			            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
			                show: false,
			                // formatter: null,
			                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
			                    color: '#ccc'
			                }
			            },
			            splitArea : {
			                show : true,
			                areaStyle : {
			                    color: ['rgba(250,0,250,0.3)','rgba(0,200,200,0.3)']
			                }
			            },
			            splitLine : {
			                show : true,
			                lineStyle : {
			                    width : 2,
			                    color : 'yellow'
			                }
			            }
			        },
			        {
			            indicator : [
			                { text : '语文', max  : 150 },
			                { text : '数学', max  : 150 },
			                { text : '英语', max  : 150 },
			                { text : '物理', max  : 120 },
			                { text : '化学', max  : 108 },
			                { text : '生物', max  : 72 }
			            ],
			            center : ['75%', 210],
			            radius : 150
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