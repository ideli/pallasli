Ext.define('Pallas.chart.data.ForceChart', {

    alias: 'pallas.chart.data.force', 
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
 

    // 数据map到圆的半径的最小值和最大值
    minRadius : 10,
    maxRadius : 20,
    density : 1.0,
    attractiveness : 1.0,
    // 初始化的随机大小位置
    initSize : 300,
    // 向心力因子，越大向心力越大
    centripetal : 1,
    // 冷却因子
    coolDown : 0.99,
    linkSymbol: null,
    linkSymbolSize: [10, 15],
    draggable: true,
    // 分类里如果有样式会覆盖节点默认样式
    categories : [],
    //---------------配置属性---end-------------//
    

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 
        
        me.itemStyle={
                normal: {
                    // color: 各异,
                    label: {
                        show: false
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    nodeStyle : {
                        brushType : 'both',
                        color : '#f08c2e',
                        strokeColor : '#5182ab'
                    },
                    linkStyle : {
                        strokeColor : '#5182ab'
                    }
                },
                emphasis: {
                    // color: 各异,
                    label: {
                        show: false
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    nodeStyle : {},
                    linkStyle : {}
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
            type:'force',
            name : "人物关系",
            categories : [
                {
                    name: '人物',
                    itemStyle: {
                        normal: {
                            color : '#ff7f50'
                        }
                    }
                },
                {
                    name: '家人',
                    itemStyle: {
                        normal: {
                            color : '#87cdfa'
                        }
                    }
                },
                {
                    name:'朋友',
                    itemStyle: {
                        normal: {
                            color : '#9acd32'
                        }
                    }
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: true,
                        textStyle: {
                            color: '#800080'
                        }
                    },
                    nodeStyle : {
                        brushType : 'both',
                        strokeColor : 'rgba(255,215,0,0.4)',
                        lineWidth : 8
                    }
                },
                emphasis: {
                    label: {
                        show: false
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    nodeStyle : {
                        r: 30
                    },
                    linkStyle : {}
                }
            },
            minRadius : 15,
            maxRadius : 25,
            density : 0.05,
            attractiveness: 1.2,
            nodes:[
                {category:0, name: '乔布斯', value : 10},
                {category:1, name: '丽萨-乔布斯',value : 2},
                {category:1, name: '保罗-乔布斯',value : 3},
                {category:1, name: '克拉拉-乔布斯',value : 3},
                {category:1, name: '劳伦-鲍威尔',value : 7},
                {category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
                {category:2, name: '奥巴马',value : 8},
                {category:2, name: '比尔-盖茨',value : 9},
                {category:2, name: '乔纳森-艾夫',value : 4},
                {category:2, name: '蒂姆-库克',value : 4},
                {category:2, name: '龙-韦恩',value : 1},
            ],
            links : [
                {source : 1, target : 0, weight : 1},
                {source : 2, target : 0, weight : 2},
                {source : 3, target : 0, weight : 1},
                {source : 4, target : 0, weight : 2},
                {source : 5, target : 0, weight : 3},
                {source : 6, target : 0, weight : 6},
                {source : 7, target : 0, weight : 6},
                {source : 8, target : 0, weight : 1},
                {source : 9, target : 0, weight : 1},
                {source : 10, target : 0, weight : 1},
                {source : 3, target : 2, weight : 1},
                {source : 6, target : 2, weight : 1},
                {source : 6, target : 3, weight : 1},
                {source : 6, target : 4, weight : 1},
                {source : 6, target : 5, weight : 1},
                {source : 7, target : 6, weight : 6},
                {source : 7, target : 3, weight : 1},
                {source : 9, target : 6, weight : 1}
            ],
            markPoint : {
                symbol: 'emptyCircle',
                data : [
                    {name : '打酱油的标注', value : 100, x:'5%', y:'50%', symbolSize:32},
                    {name : '打酱油的标注', value : 100, x:'95%', y:'50%', symbolSize:32}
                ]
            }
        };
    },
    init: Ext.emptyFn
});