Ext.define('Pallas.chart.data.LineChart', {
    alias: 'pallas.chart.data.line', 
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

    stack: null,
    xAxisIndex: 0,
    yAxisIndex: 0,
    smooth : false,
    symbol: null,         // 拐点图形类型
    symbolSize: 2,          // 拐点图形大小
    symbolRotate : null,  // 拐点图形旋转控制
    showAllSymbol: false,    // 标志图形默认只有主轴显示（随主轴标签间隔隐藏策略）
    //---------------配置属性---end-------------//
    

    constructor: function(owner, config) {
        var me = this;
        me.callParent(arguments); 
        
        me.itemStyle={
                normal: {
                    // color: 各异,
                    label: {
                        show: false
                        // formatter: 标签文本格式器，同Tooltip.formatter，不支持回调
                        // position: 默认自适应，水平布局为'top'，垂直布局为'right'，可选为
                        //           'inside'|'left'|'right'|'top'|'bottom'
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
                    },
                    lineStyle: {
                        width: 2,
                        type: 'solid',
                        shadowColor : 'rgba(0,0,0,0)', //默认透明
                        shadowBlur: 0,
                        shadowOffsetX: 0,
                        shadowOffsetY: 0
                    }
                },
                emphasis: {
                    // color: 各异,
                    label: {
                        show: false
                        // formatter: 标签文本格式器，同Tooltip.formatter，不支持回调
                        // position: 默认自适应，水平布局为'top'，垂直布局为'right'，可选为
                        //           'inside'|'left'|'right'|'top'|'bottom'
                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
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
            name:'邮件营销',
            type:'line',
            stack: '总量',
            symbol: 'none',
            itemStyle: {
                normal: {
                    areaStyle: {
                        // 区域图，纵向渐变填充
                        color : (function (){
                           // var zrColor = require('zrender/tool/color');
                          //  return zrColor.getLinearGradient(
                           //     0, 200, 0, 400,
                           //     [[0, 'rgba(255,0,0,0.8)'],[0.8, 'rgba(255,255,255,0.1)']]
                           // )
                        })()
                    }
                }
            },
            data:[
                120, 132, 301, 134, 
                {value:90, symbol:'droplet',symbolSize:5},
                230, 210
            ]
        };
    },
    init: Ext.emptyFn
});