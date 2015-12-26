Ext.define('Pallas.chart.data.MapChart', {

    alias: 'pallas.chart.data.map', 
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

    
    
    mapType: 'china',   // 各省的mapType暂时都用中文
    mapLocation: {
       //  x : 'center' | 'left' | 'right' | 'x%' | {number},
        // y : 'center' | 'top' | 'bottom' | 'x%' | {number}
        //width    // 自适应
        // height   // 自适应
    },
    mapValueCalculation: 'sum', // 数值合并方式，默认加和，可选为：
                                   // 'sum' | 'average' | 'max' | 'min' 
    mapValuePrecision : 0,         // 地图数值计算结果小数精度
    showLegendSymbol : true,       // 显示图例颜色标识（系列标识的小圆点），存在legend时生效
    selectedMode: false,        // 选择模式，默认关闭，可选single，multiple
    hoverable: true,
    roam : false,               // 是否开启缩放及漫游模式
    scaleLimit : null,
    //---------------配置属性---end-------------//
    

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 
        
        me.itemStyle={
                normal: {
                    // color: 各异,
                    borderColor: 'rgba(0,0,0,0)',
                    borderWidth: 1,
                    areaStyle: {
                        color: '#ccc'
                    },
                    label: {
                        show: false,
                        textStyle: {
                            color: 'rgb(139,69,19)'
                        }
                    }
                },
                emphasis: {                 // 也是选中样式
                    // color: 各异,
                    borderColor: 'rgba(0,0,0,0)',
                    borderWidth: 1,
                    areaStyle: {
                        color: 'rgba(255,215,0,0.8)'
                    },
                    label: {
                        show: false,
                        textStyle: {
                            color: 'rgb(100,0,0)'
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
            name: 'Map',
            type: 'map',
            mapLocation: {
                x : 'left',
                y : 'top'
            },
            selectedMode: 'multiple',
            itemStyle: {
                normal: {
                    borderWidth:2,
                    borderColor:'lightgreen',
                    color: 'orange',
                    label: {
                        show: false
                    }
                },
                emphasis: {                 // 也是选中样式
                    borderWidth:2,
                    borderColor:'#fff',
                    color: '#32cd32',
                    label: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    }
                }
            },
            data:[
                 {
                     name: '广东',
                     value: Math.round(Math.random()*1000),
                     itemStyle: {
                        normal: {
                            color: '#32cd32',
                            label: {
                                show: true,
                                textStyle: {
                                    color: '#fff',
                                    fontSize: 15
                                }
                            }
                        },
                        emphasis: {                 // 也是选中样式
                            borderWidth:5,
                            borderColor:'yellow',
                            color: '#cd5c5c',
                            label: {
                                show: false,
                                textStyle: {
                                    color: 'blue'
                                }
                            }
                        }
                    }
                }
            ],
            markPoint : {
                itemStyle : {
                    normal:{
                        color:'skyblue'
                    }
                },
                data : [
                    {name : '天津', value : 350},
                    {name : '上海', value : 103}
                ]
            },
            geoCoord: {
                '上海': [121.4648,31.2891],
                '天津': [117.4219,39.4189]
            }
        };
    },
    init: Ext.emptyFn
});