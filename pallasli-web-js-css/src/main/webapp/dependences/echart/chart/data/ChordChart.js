Ext.define('Pallas.chart.data.ChordChart', {
    alias: 'pallas.chart.data.chord', 
	mixins: {
        observable: 'Ext.util.Observable'
    },
    
    
    //---------------配置属性---begin-------------//

    
    series : {
	    markPoint : null,//系列中的数据标注内容
	    markLine : null,//系列中的数据标线内容
	    data : null//系列中的数据内容数组
    },
    itemStyle : null,//图形样式，可设置图表内图形的默认样式和强调样式（悬浮时样式）
    lineStyle : null,//线条（线段）样式
    areaStyle : null,//区域填充样式
    textStyle : null,//文字样式
    renderAsImage : null,//可设为true或指定图片格式（png | jpeg），渲染为图片后实例依然可用（如setOption，resize等），但各种交互失效
    loadingOption : null,//过渡显示，loading（读取中）的选项
    backgroundColor : 'rgba(0,0,0,0)',//全图默认背景，默认为null，同'rgba(0,0,0,0)'
    color : ['#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
             '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0',
             '#1e90ff','#ff6347','#7b68ee','#00fa9a','#ffd700',
             '#6699FF','#ff6666','#3cb371','#b8860b','#30e0e0'],//默认颜色序列，循环使用
    symbolList : [
                  'circle', 'rectangle', 'triangle', 'diamond',
                  'emptyCircle', 'emptyRectangle', 'emptyTriangle', 'emptyDiamond'
                ],//默认标志图形类型列表，循环使用
    loadingText : 'Loading...',
    calculable : false,//是否启用拖拽重计算特性，默认关闭
    calculableColor : 'rgba(255,165,0,0.6)',//拖拽重计算提示边框颜色
    calculableHolderColor : '#ccc',//可计算占位提示颜色
    nameConnector : ' & ',//数据合并名字间连接符
    valueConnector : ' : ',//数据合并名字间连接符
    animation : true,//  true，是否启用图表初始化动画，默认开启，建议IE8-关闭
    addDataAnimation : true,//true，是否启用动态数据接口动画效果，默认开启，建议IE8-关闭
    animationThreshold : 2000,//2500，动画元素阀值，产生的图形原素超过2500不出动画，建议IE8-关闭
    animationDuration : 2000,//2000，动画时长，单位ms，支持多级控制
    animationEasing : 'ExponentialOut',//'BounceOut'，主元素的缓动效果，支持多级控制

    radius : ['65%', '75%'],
    center : ['50%', '50%'],
    padding : 2,
    sort : 'none', // can be 'none', 'ascending', 'descending'
    sortSub : 'none', // can be 'none', 'ascending', 'descending'
    startAngle : 90,
    clockWise : true,
    showScale : false,
    showScaleText : false,
    //---------------配置属性---end-------------//
    

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 
        
        me.itemStyle={
                normal : {
                    label : {
                        show : true,
                        rotate: false,
                        distance: 10
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    lineStyle : {
                        width : 0,
                        color : '#000'
                    },
                    chordStyle : {
                        lineStyle : {
                            width : 1,
                            color : '#999'
                        }
                    }
                },
                emphasis : {
                    lineStyle : {
                        width : 0,
                        color : '#000'
                    },
                    chordStyle : {
                        lineStyle : {
                            width : 1,
                            color : '#666'
                        }
                    }
                }
            };
        me.lineStyle={
        		
        };
        me.areaStyle={
        		
        };
        me.textStyle={
                decoration: 'none',
                fontFamily: 'Arial, Verdana, sans-serif',
                fontFamily2: '微软雅黑',    // IE8- 字体模糊并且，不支持不同字体混排，额外指定一份
                fontSize: 12,
                fontStyle: 'normal',
                fontWeight: 'normal'
           };
        me.loadingOption={
        		
        };
        me.backgroundColor={
        		
        };
        me.color={
        		
        };
        me.symbolList={
        		
        };
        me.renderAsImage={
        		
        };
        me.calculable={
        		
        };
        me.calculableColor={
        		
        };
        me.calculableHolderColor={
        		
        };
        me.nameConnector={
        		
        };
        me.valueConnector={
        		
        };
        me.animation={
        		
        };
        me.addDataAnimation={
        		
        };
        me.animationThreshold={
        		
        };
        me.animationDuration={
        		
        };
        me.animationEasing={
        		
        };   
    },

    getSeriesJson: function(){
    	return {
            type:'chord',
            radius : ['55%', '75%'],
            center : ['50%', '50%'],
            padding : 2,
            sort : 'descending', // can be 'none', 'ascending', 'descending'
            sortSub : 'descending', // can be 'none', 'ascending', 'descending'
            startAngle : 90,
            clockWise : false,
            showScale : true,
            showScaleText : true,
            itemStyle : {
                normal : {
                    lineStyle : {
                        width : 0,
                        color : '#000'
                    },
                    chordStyle : {
                        lineStyle : {
                            width : 1,
                            color : '#333'
                        }
                    },
                    label: {
                        show: true,
                        color: 'red'
                    }
                },
                emphasis : {
                    lineStyle : {
                        width : 0,
                        color : '#000'
                    },
                    chordStyle : {
                        lineStyle : {
                            width : 2,
                            color : 'black'
                        }
                    }
                }
            },
            data : [
                {
                    name : 'g1',
                    itemStyle : {
                        normal: {
                            color: 'rgba(255,30,30,0.5)',
                            lineStyle : {
                                width: 1,
                                color: 'green'
                            }
                        },
                        emphasis: {
                            color: 'yellow',
                            lineStyle : {
                                width: 2,
                                color: 'blue'
                            }
                        }
                    }
                },
                {name : 'g2'},
                {name : 'g3'},
                {name : 'g4'}
            ],
            matrix : [
                [11975,  5871, 8916, 2868],
                [ 1951, 10048, 2060, 6171],
                [ 8010, 16145, 8090, 8045],
                [ 1013,   990,  940, 6907]
            ],
            markPoint : {
                symbol: 'star',
                data : [
                    {name : '打酱油的标注', value : 100, x:'5%', y:'50%', symbolSize:32},
                    {name : '打酱油的标注', value : 100, x:'95%', y:'50%', symbolSize:32}
                ]
            }
       };
    },

    init: Ext.emptyFn
});