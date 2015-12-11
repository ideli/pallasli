 
 
Ext.define('Pallas.Chart', {
    extend:'Ext.form.field.Base',
    alias: 'pallas.chart',
    requires: ['Ext.form.field.VTypes',
               'Pallas.chart.data.PieChart',
               'Pallas.chart.data.LineChart',
               'Pallas.chart.data.BarChart',
               'Pallas.chart.data.KChart',
               'Pallas.chart.data.ScatterChart',
               'Pallas.chart.data.ForceChart',
               'Pallas.chart.data.MapChart',
               'Pallas.chart.data.ChordChart',
               'Pallas.chart.data.RadarChart',
               'Pallas.chart.option.MixedChart',
               'Pallas.chart.option.PieChart',
               'Pallas.chart.option.LineChart',
               'Pallas.chart.option.BarChart',
               'Pallas.chart.option.KChart',
               'Pallas.chart.option.ScatterChart',
               'Pallas.chart.option.ForceChart',
               'Pallas.chart.option.MapChart',
               'Pallas.chart.option.ChordChart',
               'Pallas.chart.option.RadarChart'
               ],
	/*****配置属性**begin*******/
               
               
               
               timeline : null,//时间轴
               title : null,//标题
               toolbox : null,//提示框
               tooltip : null,//
               legend : null,//图例
               datRange : null,//值域选择
               dataZoom : null,//数据区域缩放。与toolbox.feature.dataZoom同步，仅对直角坐标系图表有效。
               grid : null,//直角坐标系内绘图网格
               xAxis : null,//横坐标轴
               yAxis : null,//纵坐标轴
               categoryAxis : null,//类目轴
               valueAxis : null,//数值轴
               polar : null,//极坐标           
               
               
    /*************begin**新确定*******************/
	type : 'pie',//图表类型，必须指定，默认饼图
    width : 300,//图表宽度，默认300
    height : 300,//图表高度，默认300
    anchor : "100% 100%",//高宽按百分比设置，默认铺满

	title : null,//标题
	/**title可配置属性
	{string} text	 ''	 主标题文本，'\n'指定换行
	{string} link	 ''	 主标题文本超链接
	{string} subtext	 ''	 副标题文本，'\n'指定换行
	{string} sublink	 ''	 副标题文本超链接
	{string | number} x	 'left'	 水平安放位置，默认为左侧，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
	{string | number} y	 'top'	 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
	{string} textAlign	 null	 水平对齐方式，默认根据x设置自动调整，可选为： left' | 'right' | 'center
	{color} backgroundColor	 'rgba(0,0,0,0)'	 标题背景颜色，默认透明
	{string} borderColor	 '#ccc'	 标题边框颜色
	{number} borderWidth	 0	 标题边框线宽，单位px，默认为0（无边框）
	{number | Array} padding	 5	 标题内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距，同css，见下图
	{number} itemGap	 10	 主副标题纵向间隔，单位px，默认为10
	{Object} textStyle	 {fontSize: 18,fontWeight: 'bolder', color: '#333'}	 主标题文本样式（详见textStyle）
	{Object} subtextStyle	 {color: '#aaa'}	 副标题文本样式（详见textStyle）
	**/
	dataRange : null,//
	toolbox : null,//工具栏
	dataZoom : null,//
	legend : null,//图例
	/**legend可配置的属性
	{string} orient	 'horizontal'	 布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'
	{string | number} x	 'center'	 水平安放位置，默认为全图居中，可选为：'center' | 'left' | 'right' | {number}（x坐标，单位px）
	{string | number} y	 'top'	 垂直安放位置，默认为全图顶端，可选为：'top' | 'bottom' | 'center' | {number}（y坐标，单位px）
	{color} backgroundColor	 'rgba(0,0,0,0)'	 图例背景颜色，默认透明
	{string} borderColor	 '#ccc'	 图例边框颜色
	{number} borderWidth	 0	 图例边框线宽，单位px，默认为0（无边框）
	{number | Array} padding	 5	 图例内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距，同css，见下图
	{number} itemGap	 10	 各个item之间的间隔，单位px，默认为10，横向布局时为水平间隔，纵向布局时为纵向间隔，见下图
	{Object} textStyle	 {color: '#333'}	 默认只设定了图例文字颜色（详见textStyle） ，更个性化的是，要指定文字颜色跟随图例，可设color为'auto'
	{boolean | string} selectedMode	 true	 选择模式，默认开启图例开关，可选single，multiple
	{Object} selected	 null	 配置默认选中状态，可配合LEGEND.SELECTED事件做动态数据载入，try this »
	{Array <string>} data	 [ ]	 图例内容数组，数组项通常为{string}，每一项代表一个系列的name。 
			使用根据该值索引series中同名系列所用的图表类型和itemStyle，如果索引不到，该item将默认为没启用状态。 
			如需个性化图例文字样式，可把数组项改为{Object}，格式为{name: {string}, textStyle: {Object}}
	
	 option = {
	    legend: {
	        orient: 'horizontal', // 'vertical'
	        x: 'right', // 'center' | 'left' | {number},
	        y: 'top', // 'center' | 'bottom' | {number}
	        backgroundColor: '#eee',
	        borderColor: 'rgba(178,34,34,0.8)',
	        borderWidth: 4,
	        padding: 10,    // [5, 10, 15, 20]
	        itemGap: 20,
	        textStyle: {color: 'red'},
	        selected: {
	            '降水量' : false
	        },
	        data: [
	            {
	                name:'蒸发量',
	                textStyle:{fontWeight:'bold', color:'green'}
	            },
	            '降水量','最高气温', '最低气温'
	        ]
	    },
	    xAxis :{
	        data : ['周一','周二','周三','周四','周五','周六','周日']
	    },
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} ml'
	            },
	            splitArea : {show : true}
	        },
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} °C'
	            },
	            splitLine : {show : false}
	        }
	    ],
	    series : [
	        {
	            name:'蒸发量',
	            type:'bar',
	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6]
	        },
	        {
	            name:'最高气温',
	            type:'line',
	            yAxisIndex: 1,
	            data:[11, 11, 15, 13, 12, 13, 10]
	        },
	        {
	            name:'降水量',
	            type:'bar',
	            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6]
	        }
	    ]
	};
	// 动态添加默认不显示的数据
	var ecConfig = require('echarts/config');
	myChart.on(ecConfig.EVENT.LEGEND_SELECTED, function(param){
	    var selected = param.selected;
	    var len;
	    var added;
	    if (selected['最低气温']) {
	        len = option.series.length;
	        added = false;
	        while (len--) {
	            if (option.series[len].name == '最低气温') {
	                // 已经添加
	                added = true;
	                break;
	            }
	        }
	        if (!added) {
	            myChart.showLoading({
	                text : '数据获取中',
	                effect: 'whirling'
	            });
	            setTimeout(function(){
	                option.series.push({
	                    name:'最低气温',
	                    type:'line',
	                    yAxisIndex: 1,
	                    data:[-2, 1, 2, 5, 3, 2, 0]
	                });
	                myChart.hideLoading();
	                myChart.setOption(option);
	            }, 2000)
	        } 
	    }
	});
	 
	 **/
    
    
    
	caption : null,//主标题
	subcaption : null,//副标题

	hideToolbox : null,//隐藏工具栏
	hideDataRange : null,//
	hideDataZoom : true,//
	hideLegend : false,//隐藏标尺
    
    
    /***************end**新确定********************/
	isFormField :true,
	series : null, 
	store : null,
	tooltip : null,
	grid : null, 
	color : null, 
	stack : null,
	xAxis : null,
	yAxis : null,
	horizontalBar:null,
	stack:null,
	
	/*****不可更改属性**begin*******/
	_supportType_ : ['line','bar','pie','map','scatter','k','force','mixed','radar','chord','state'],
	_option_  : null ,
	_myChart_ : null ,
	_dataCharts_:[],
	_optionChart_:{},

	/*************重写方法*****************begin********/
    onRender : function(ct, position){
        if(!this.el){
            var cfg = this.getAutoCreate();

            if(!cfg.name){
                cfg.name = this.name || this.id;
            }
            this.autoEl = cfg;
        }
        Pallas.Chart.superclass.onRender.call(this, ct, position);
		//this.show();
    },
	afterRender : function(){
        Ext.form.Field.superclass.afterRender.call(this);
    },

	syncShadow : function(){
        if(this.floating){
            this.el.sync(true);
        }
    },
	onResize : function(w, h){
		//this.show(true);
        this.syncShadow();
    },
	beforeDestroy : function( ){
    	this._myChart_.dispose();
    },
	getId : function(){
        return this.id || (this.id = 'ext-comp-' + this.getAutoId());
    },
	getName: function(){
        return this.rendered && this.el.dom.name ? this.el.dom.name : this.name || this.id || '';
    },
	getItemId : function(){
        return this.itemId || this.getId();
    },
	markInvalid : function(msg){
        if(!this.rendered || this.preventMark){ // not rendered
            return;
        }
        return;
    },

    clearInvalid : function(){
        if(!this.rendered || this.preventMark){ // not rendered
            return;
        }
        return;
    },
	getValue : function(){
        return this._option_;
    },
    setValue : function(v){
        this._option_ = v;
        return this;
    },

	/***************初始化********************begin******/
    constructor: function(owner, config) {
		var me=this;
        me.callParent(arguments); 
		
		if(me.series){
			if(!me._isArray_(me.series)){
				me.series=[me.series];
			}
		}
		var seriesLen=me.series?me.series.length:0;
		
		if(me.type!="mixed"){
			for(var i=0;i<seriesLen;i++){
				me.series[i].type=me.type;
			}
		}
		me._dataCharts_=[];
		for(var i=0;i<seriesLen;i++){
			var tmpChart=Ext.create('pallas.chart.data.'+me.series[i].type,{series:me.series[i]});	
			me._dataCharts_.push(tmpChart);	
		}


		me.addEvents(            
            'resize','render','destroy','beforedestroy'
        );
		me.isFormField=true,
		//me._initChart_();
		me.showCharts();
		
	},
	_initOptions_ : function(){
		var me=this;
		me._optionChart_=Ext.create('pallas.chart.option.'+me.type);	
		me._optionJson_=me._optionChart_.getOptionJson();
	},
	showCharts : function(){
		var me=this;
		var len=me._dataCharts_.length;
		me._seriesArray_=[];
		for(var i=0;i<len;i++){
			me._seriesArray_.push(me._dataCharts_[i].getSeriesJson());
		} 
		me._initOptions_();
		setTimeout(function(){ 
			me._canvasDiv_=Ext.getDom(me.getEl().dom.childNodes[0].childNodes[0]); 
			
			me._mychart_ = Atwasoft.chart._echarts_.init(me._canvasDiv_);
			console.log(me._optionJson_);
			me._mychart_.setOption(me._optionJson_);
			me._mychart_.setSeries(me._seriesArray_);
			
		},200);
		  
	},
	_initChart_:function(){
		if(!this._checkSupportType_(this.type)){
			alert('wachart组件必须配置合适的type属性'+this.type);
			return;
		}
		this._option_=this._clone_(Atwasoft.chart._defaultConfig[this.type]);
		this._option_.series=[];
		this._setSeries_();
		this._setTitle_();
		if(this.type!='radar'&&this.type!='chord'){
			this._setLegend_();			
		}
		this._setTooltip_();
		this._setToolbox_();
		this._setDataRange_();
		this._setDataZoom_();
		if(this.type=="line"||this.type=="bar"||this.type=="k"||this.type=="scatter"){
			this._setAxis_();
			this._setGrid_();
		}
		this._setColor_();
		this._setPolar_();
		this._initEvented_=false;
	},
	//工具方法
	_checkSupportType_ : function(type){
		var len=this._supportType_.length;
		for(var i=0;i<len;i++){
			if(this._supportType_[i]==type) return true;
		}
		return false;
	},
	_isArray_ : function(arr){
		return Object.prototype.toString.call(arr)=='[object Array]';
	},
	_isFunction_ : function(fn){
		return Object.prototype.toString.call(fn)=='[object Function]';
	},
	_clone_ : function(myObj){
		var myNewObj = {};
		if(this._isArray_(myObj)) { 
			myNewObj = [];
			var len=myObj.length;
			for(var i=0;i<len;i++){
				myNewObj[i] = this._clone_(myObj[i]);
			}
			return myNewObj;
		}
		if(typeof(myObj) != 'object'||myObj == null) { 
			return myObj;
		}
		for(var i in myObj){
			myNewObj[i] = this._clone_(myObj[i]);
		}
		return myNewObj;
	},
	_apply_:function(toObj,fromObj){
		if(fromObj&&!this._isFunction_(fromObj)){			
				if(typeof(fromObj) == 'object'&&!this._isArray_(fromObj)){
					if(!toObj)toObj={};
					Ext.apply(toObj,fromObj);
				}else if(this._isArray_(fromObj)){
					if(!toObj)toObj=[];
					var len=fromObj.length;
					for(var i=0;i<len;i++){
						this._apply_(toObj[i],fromObj[i]);
					}
				}else {
					 toObj=fromObj;
				}
		}
	},
	_startWith_:function(src,s){
		if(s==null||s==""||src.length==0||s.length>src.length)
		    return false;
		if(src.substr(0,s.length)==s)
			return true;
		else
			return false;
		return true;
	},
	//xy坐标系到pie数据的转换
	_getPieDataFromXYData_ : function(axisData,valueData){
		var pieData=[];
		var length=axisData.length;
		for(var i=0;i<length;i++){
			pieData[i]={value:valueData[i], name:axisData[i]};
		}
		return pieData;
	},
	//pie坐标系到x数据的转换
	_getCategoryDataFromPieData_ : function(pieData){
		var axisData=[];
		var len=pieData.length;
		for(var i=0;i<len;i++){
			axisData[i]=pieData[i].name;
		}
		return axisData;
	},
	//pie坐标系到x数据的转换
	_getValueDataFromPieData_ : function(pieData){
		var valueData=[];
		var len=pieData.length;
		for(var i=0;i<len;i++){
			valueData[i]=pieData[i].value;
		}
		return valueData;
	},	
	//待修改
	_getMin_ : function(valueData){
		var tmp=valueData[0].value;
		var len=valueData.length;
		for(var i=1;i<len;i++){
			if(valueData[i].value<tmp){tmp=valueData[i].value;};
		}
		return tmp;	
	},
	//待修改
	_getMax_ : function(valueData){
		var tmp=valueData[0].value;
		var len=valueData.length;
		for(var i=1;i<len;i++){
			if(valueData[i].value>tmp){tmp=valueData[i].value;};
		}
		return tmp;
	},
	
	//初始化内置方法
	_setTitle_:function(){	
		if(this.title){this._option_.title = this.title;}
		if(this.caption){this._option_.title.text=this.caption;}
		if(this.subcaption){this._option_.title.subtext=this.subcaption;}
	},
	_setPolar_:function(){
		if(this.polar){this._option_.polar = this.polar;}
	},
	_indexOfArray_:function(v,arr){
		var len=arr.length;
		for(var i=0;i<len;i++){
			if(v==arr[i])return i;
		}
		return -1;
	},
	_readStore_:function(){
		var seriesNames=[];
		if(this.store){
			var self=this;
			var s=this.store;
			records=[];
			records[0]=[];
			s.each(function(r){
				if(!r.data.seriesName){
					records[0][records[0].length]=self._clone_(r.data);
					seriesNames[0]='';
				}else{
					var i=self._indexOfArray_(r.data.seriesName.trim(),seriesNames);
					if(i==-1){i=seriesNames.length;seriesNames[i]=r.data.seriesName.trim();}
					if(!records[i])records[i]=[];
					records[i][records[i].length]=self._clone_(r.data);
				}
			});
		}
		var len=records.length;
		
		for(var i=0;i<len;i++){
			var pieData=records[i];
			if(this.type=='pie'||this.type=='map'||this.type=='state'){
				if(!this._option_.series[i])this._option_.series[i]={};
				this._option_.series[i].name=seriesNames[i];
				this._option_.series[i].type=this.type;
				this._option_.series[i].radius = '35%',
				this._option_.series[i].center= ['50%', '50%'],
				this._option_.series[i].data=records[i];
			}else{
				if(!this._option_.series[i])this._option_.series[i]={};
				this._option_.series[i].name=seriesNames[i];
				this._option_.series[i].type=this.type;
				this._option_.series[i].data=this._getValueDataFromPieData_(pieData);
				this._setXAxis_(this._getCategoryDataFromPieData_(pieData));
			}
		
		}
		
		this._setLegend_();
		return;
	},
	_readSeries_ : function(){
		if(this.series){
			this._option_.series=this.series;
		}else{
			this._option_.series=[{data:[]}];
		}
		if(this._option_.series.length==1){
			for(var i in this){	
				if(i!=null&&i!=""&&i.length>6&&i.substr(0,6)=='series'&&this[i]!=null){
					var seriesCfgName=i.substr(6);
					seriesCfgName=seriesCfgName.substr(0,1).toLowerCase()+seriesCfgName.substr(1);
					this._option_.series[0][seriesCfgName] = this[i];
				}
			}
		}

		var len=this._option_.series.length;
		for(var i=0;i<len;i++){
			if(!this._option_.series[i].type)this._option_.series[i].type=this.type;
			if(this._option_.series[i].type=='map'&&this._option_.series[i].mapType!='china'){
				this._option_.series[i].itemStyle={
	                normal:{label:{show:true}},
	                emphasis:{label:{show:true}}
	            };
			}
		}
	},
	_setSeries_ : function(){
		this._readSeries_();
	},
	_setDefaultLegendData_ : function(){ 
		if(!this._option_.legend){this._option_.legend={};}
		var option=this._option_;
		var series=option['series'];
		var len = series.length;
		var hasPie=false;
		for(var i=0;i<len;i++){
			if(series[i]['type']=='pie'){
				hasPie=true;
			}
		}
		option.legend.data=[];
		if(hasPie){
			for(var i=0;i<len;i++){
				if(series[i]['type']=='pie'){
					var seriesData=series[i]['data'];
					var length=seriesData.length;
					for(var j=0;j<length;j++){
						if(seriesData[j]['name']){
							option.legend.data.push(seriesData[j]['name']);
						}else{
							seriesData[j]['name']='';
						};
					}
				}
			}
		}else{
			for(var i=0;i<len;i++){ 
				var sreiestype=series[i]['type'];
				if(sreiestype=='force'){
					var categories=series[i]['categories'];
					var len2 =categories? categories.length:0;
					for(var j=0;j<len2;j++){
						if(categories[j]['name']){
							option.legend.data.push(categories[j]['name']);
						}else{
							categories[j]['name']='';
						};
					}
				}else{
					if(series[i]['name']){
						option.legend.data.push(series[i]['name']);
					}else{
						series[i]['name']='';
					};
				}
			}
		}
	},
	_setLegend_ : function(){
		if(this.type!='state')this._setDefaultLegendData_();
		if(!this.legend){
			this._apply_(this._option_.legend,this.legend);
		}
		if(this.hideLegend&&this._option_.legend){this._option_.legend.data=[];}
	},
	_addLegend_:function(series){
		if(!this.hideLegend){
			if(this.type=='pie'||series.type=='pie'){
				
			
			}else{
				this._option_.legend.data.push(series.name);
			
			}
		}
	},
	_setColor_ : function(){
		if(this.color){
			this._apply_(this._option_.color,this.color);
		}
	},
	_setTooltip_ : function(){
		if(this.tooltip){
			this._apply_(this._option_.tooltip,this.tooltip);
		}
	},
	_setToolbox_ : function(){
		if(this.toolbox){
			this._apply_(this._option_.toolbox,this.toolbox);
		}
		if(this.hideToolbox&&this._option_.toolbox){
			this._option_.toolbox.show=false;
		}
	},
	_setDataRange_ : function(){
		if(this.type!='scatter'){
			if(this.dataRange){
				this._apply_(this._option_.dataRange,this.dataRange);
			}
		}
	},
	_setDataZoom_ : function(){
		if(this.type!='scatter'){
			if(this.dataZoom){
				this._apply_(this._option_.dataZoom,this.dataZoom);
			}
			if(this.hideDataZoom&&this._option_.dataZoom){
				this._option_.dataZoom.show=false;
			}
		}
	},
	
	_setXAxisData_:function(axisData){
		var tmpData=[];
		var len;
		if(axisData){
			var xAxisData=this._option_.xAxis[0].data;
			if(!xAxisData){xAxisData=[];}
			var concatArray=xAxisData.concat(axisData);
			len=concatArray.length;
			for(var i=0;i<len-1;i++){
				var tmp=concatArray[i];
				if(tmp!=''){
					for(var j=i+1;j<len;j++){
						if(concatArray[j]==tmp){
							concatArray[j]='';
						}
					}
				}
			}
			
			for(var i=0;i<len;i++){
				var tmp=concatArray[i];
				if(tmp!=''){
					tmpData[tmpData.length]=tmp;
				}
			}
		}else{
			tmpData=this._option_.xAxis[0]?this._option_.xAxis[0].data:[];
			len=tmpData?tmpData.length:0;
		}
		for(var i=0;i<len-1;i++){
			var tmp=tmpData[i];
			for(var j=i+1;j<len;j++){
				if(tmpData[j]<tmp){
					tmpData[i]=tmpData[j];
					tmpData[j]=tmp;
					tmp=tmpData[i];
				}
			}
		}

		if(this._option_.xAxis[0])this._option_.xAxis[0].data=tmpData;
	},
	_setXAxis_ : function(axisData){
		if(!this._option_.xAxis){
			this._option_.xAxis=[];
		}
		if(this.xAxis){
			for(var i=0;i<this.xAxis.length;i++){
				if(!this._option_.xAxis[i])this._option_.xAxis[i]={};
				this._apply_(this._option_.xAxis[i],this.xAxis[i]);
			}
		}
		this._setXAxisData_(axisData);
	},
	_setYAxis_ : function(){
		if(!this._option_.yAxis){
			this._option_.yAxis=[];
		}
		if(this.yAxis){
			for(var i=0;i<this.yAxis.length;i++){
				if(!this._option_.yAxis[i])this._option_.yAxis[i]={};
				this._apply_(this._option_.yAxis[i],this.yAxis[i]);
			}
		}
	},
	_setAxis_:function(){
		if(this.type=='mixed'&&this._option_.series[0]&&this._option_.series[0].type!='map'){		
			this._setXAxis_();
			this._setYAxis_();
			if(this._option_.series[0].type=='scatter'){}
			this._setGrid_();
		}
		
		if(this.type=='bar'||this.type=='line'||this.type=='k'||this.type=='scatter'){
			this._setXAxis_();
			this._setYAxis_();
			if(this.type=='scatter'){
			this._option_.xAxis =this.xAxis;
			this._option_.yAxis = this.yAxis;
			}
			this._setGrid_();
		}
		if(this._axis_){
			
		}
	},
	_setGrid_ : function(){
		if((this.type=='mixed'&&this._option_.series[0]&&this._option_.series[0].type!='map')||this.type=='bar'||this.type=='line'||this.type=='k'||this.type=='scatter'){	
		
			if(this.grid){
				this._apply_(this._option_.grid,this.grid);
			}
		}
	},
	_setAreaForPieOrMap_:function(){
		var self=this;
		if(self.type=='pie'){
			var series=self._option_.series;
			var len=series.length;
			
			if(len==2){
			
			
			}
			/**
			
        	var pieData0=[];
        	var pieData1=[];
        	var stackMap={};
        	var pieDataMap={};
        	var len=valueData.length;
        	for(i=0;i<len;i++){
        		pieDataMap[valueData[i]['name']]=valueData[i]['value'];
        	}
    		for(var stackType in stack){
    			var len0=pieData0.length;
       		   pieData0[len0]={};
       		   pieData0[len0]['name']=stackType;
       		   var stackChildren=stack[stackType];
       		   var len1=stackChildren.length;
       		   var sum=0;
       		   for(j=0;j<len1;j++){
       			   var tmp_name=stackChildren[j];
       			   var tmp_value=pieDataMap[tmp_name];
       	   		   pieData1[pieData1.length]={name:tmp_name,value:tmp_value};
       	   		   sum+=parseFloat(tmp_value);
       		   }
       		   pieData0[len0]['value']=sum;
       		   
       	  	}
    		
    		option.calculable=false;
        	series=_addDataToSeries(series,"pie",pieData0,tjxm);
        	series=_addDataToSeries(series,"pie",pieData1,tjxm);
        	option['series']=series;
    		option.series[0].radius=[0, 70]; 		
    		option.series[1].radius=[110, 150];
			
			
			
			
			stack:{  
				'堆积类型1':['类型1','类型2',...],
				'堆积类型2':['类型3',...],
				.....
  			}
			_myChart_.on('legendSelected', function(param){
				var selects=param.selected;
				var pieData0=[];
				var pieData1=[];
				var stackMap={};
				var pieDataMap={};
				var len=pieData.length;
				for(i=0;i<len;i++){
					pieDataMap[pieData[i]['name']]=pieData[i]['value'];
				}
				for(var stackType in stack){
					   var len0=pieData0.length;
					   pieData0[len0]={};
					   pieData0[len0]['name']=stackType;
					   var stackChildren=stack[stackType];
					   var len1=stackChildren.length;
					   var sum=0;
					   for(j=0;j<len1;j++){
						   var tmp_name=stackChildren[j];
						   var tmp_value=pieDataMap[tmp_name];
						   pieData1[pieData1.length]={name:tmp_name,value:tmp_value};
						   if(selects[stackType]==undefined||selects[stackType]){
								if(selects[tmp_name]){
								   sum+=parseFloat(tmp_value);
								}
							}else{ 
								selects[tmp_name]=false;
							}
					   }
					   pieData0[len0]['value']=sum;
				}

				option.series[0].data=pieData0;
				option.series[1].data=pieData1;
				_myChart_.setSeries(option.series,true,true);
				
			})
			
			
			**/
			
			var r,x ;
			var width=self.getSize().width;
			var height=self.getSize().height;
			if(len==0){return;}
			else {
				var maxD=(width/len>height?height:width/len);
				r=maxD/2*0.5;x=width/len;
			}
			
			for(var i=0;i<len;i++){
				if(!self._option_.series[i].radius){
					self._option_.series[i].radius=[0, r];
				}
				if(!self._option_.series[i].center){
					self._option_.series[i].center=[x*(i+0.5), ];// 含title时
				}
			}
		}
	},
	_onMagicTypeChanged:function(param){
		var magicType=param.magicType;
		if(!magicType||this.type==magicType){
			this._initChart_();
		}else if(this.type!='pie'&&magicType!='pie'){
			var axisData;
			if(this.horizontalBar&&this.type=='bar'){
				axisData=this._option_.yAxis[0].data;
			}
			else{
				axisData=this._option_.xAxis[0].data;
			}
			var valueData=this._option_.series[0].data;	
			
			this.type=magicType;
			this._initChart_();
			this._option_.xAxis[0].data=axisData;
			this._option_.series[0].type=magicType;
			this._option_.series[0].data=valueData;
		}else if(magicType=='line'||magicType=='bar'){
			var pieData=this._option_.series[0].data;	
			
			this.type=magicType;
			this._initChart_();
			
			this._option_.xAxis[0].data=this._getCategoryDataFromPieData_(pieData);
			this._option_.series[0].type=magicType;
			this._option_.series[0].data=this._getValueDataFromPieData_(pieData);
		}else {
			var axisData;
			if(this.horizontalBar&&this.type=='bar'){
				axisData=this._option_.yAxis[0].data;
			}
			else{
				axisData=this._option_.xAxis[0].data;
			}
			var valueData=this._option_.series[0].data;	
			
			this.type=magicType;
			this._initChart_();
			this._option_.series[0].type=magicType;
			this._option_.series[0].data=this._getPieDataFromXYData_(axisData,valueData);
		}
			this.show();
	},
	
	/***********设置-------------begin**************/
	setGrid:function(grid,replace){
		if(replace){
			this._option_.grid=grid;
		}else{
			this._apply_(this._option_.grid,grid);		
		}
	},
	setGridPadding:function(padding){
		this._option_.grid.padding=padding;	
	},
	setTooltip:function(tooltip){
		this._apply_(this._option_.tooltip,tooltip);		
	},
	setToolbox:function(toolbox){
		this._apply_(this._option_.toolbox,toolbox);		
	},
	setDataZoom:function(DataZoom){
		this._apply_(this._option_.DataZoom,DataZoom);		
	},
	setDataRange:function(dataRange){
		this._apply_(this._option_.dataRange,dataRange);		
	},
	setLegend:function(legend){
		this._apply_(this._option_.legend,legend);		
	},
	setOption:function(option,replace){
		if(replace){
			this._option_=option;
		}else{
			this._apply_(this._option_,option);		
		}
	},
	setSeries:function(series,replace){
		if(replace){
			this.series=series;
			this._option_.series=series;
		}else{
			this._apply_(this.series,series);	
			this._apply_(this._option_.series,series);		
		}
		this._setLegend_();
	},
	addSeries:function(series){
		var len=this._option_.series.length;
		this._option_.series[len]=series;
		this.series[len]=series;
		if(this.type!='mixed'){
			this._option_.series[len].type=this.type;
		}
		this._addLegend_(series);
	},
	removeSeries:function(seriesName){
		var series_tmp=this.series;
		var series_new=[];
		for(var i=0;i<series_tmp.length;i++){
			if(seriesName!=series_tmp[i].name)series_new[series_new.length]=series_tmp[i];
		}
		this.setSeries(series_new,true);
		this.show();
	},
	
	setSeriesData :function(data){
		if((this.type=='line'||this.type=='bar')&&data.length>0){
			this._option_.xAxis[0].data=this._getCategoryDataFromPieData_(data);
			this._option_.series[0].data=this._getValueDataFromPieData_(data);
		}else {
			this._option_.series[0].data=data;
		}
	},
	setAxisData:function(data,xy){
		this._setXAxis_(data);
	},
	addAxisData:function(data,xy){
		this._setXAxisData_(data);
	},
	setStack:function(stack){
		this.stack=stack;
	},
	/**********事件-------------begin**************/
	
	on :function(name,fn){
		var self=this;
		if(this._myChart_){
			this._myChart_.on(name,fn);	
		}else{
			Ext.apply(this,name);
			setTimeout(function(){self.on(name,fn);},300);
		}
	},
	
	/***********显示--------------begin*************/
	_show_ : function(){
		var self=this;
		if(self._myChart_) {self._myChart_.clear();}
		self._showChartAfterRender_();
	},
	_showChartAfterRender_:function(){
		var self=this;
		self._canvasDiv_=Ext.getDom(self.getEl());
		if(self._canvasDiv_){
			self._canvasDiv_.style.position ="relative";
			self._canvasDiv_.style.cssFloat ="left";
			var parent=self.ownerCt;
			parent.setAutoScroll(true);
			var parentWidth,parentHeight;
			if(parent.getSize()&&parent.getSize().width){
				parentWidth=parent.getSize().width-10;
			}else{
				parentWidth=300;
			}
			if(parent.getSize()&&parent.getSize().height){
				parentHeight=parent.getSize().height-10;
			}else{
				parentHeight=300;
			}
			if(!self.getSize().width){
				if(self.anchor){
					var bfb=self.anchor.split(' ');
					self.setWidth((self.type=='state'?600:parentWidth*parseFloat(bfb[0])/100));
					self._canvasDiv_.style.width=(self.type=='state'?"600px":parentWidth*parseFloat(bfb[0])/100+"px");
				}else{
					self.setWidth((self.type=='state'?600:parentWidth));
					self._canvasDiv_.style.width=(self.type=='state'?"600px":parentWidth+"px");
				}
			}
			if(!self.getSize().height){
				if(self.anchor){
					var bfb=self.anchor.split(' ');
					if(bfb.length==1)bfb[1]='100%';
					self.setHeight((self.type=='state'?600:parentHeight*parseFloat(bfb[1])/100));
					self._canvasDiv_.style.height=(self.type=='state'?"40px":parentHeight*parseFloat(bfb[1])/100+"px");
				}else{
					self.setHeight((self.type=='state'?40:parentHeight));
					self._canvasDiv_.style.height=(self.type=='state'?"40px":parentHeight+"px");
				}
			}
			self._setAreaForPieOrMap_();
			self._showChartAfterInit_();
		}else{
			setTimeout(function(){self._showChartAfterRender_();},300);
		}
	},
	_showChartAfterInit_:function(){
		var self=this;
		if(Atwasoft.chart._echarts_){
			self._showChart_();
		}else{
			setTimeout(function(){self._showChartAfterInit_();},300);
		}
	},
	_showChart_:function(){
		var self=this;  
		self._myChart_ = Atwasoft.chart._echarts_.init(self._canvasDiv_);
		self._myChart_.setOption(self._option_, true);
		self.series=self._option_.series; 
		if(!self._initEvented_)self._initEvent_();
	},
	_initEvent_:function(){
		var self=this;
		self._myChart_.un(Atwasoft.chart._ecConfig_.EVENT.MAGIC_TYPE_CHANGED);
		self._myChart_.on(Atwasoft.chart._ecConfig_.EVENT.MAGIC_TYPE_CHANGED,function(param){
			self._onMagicTypeChanged(param);
		});
		self._myChart_.un(Atwasoft.chart._ecConfig_.EVENT.OPEN_WIN);
		self._myChart_.on(Atwasoft.chart._ecConfig_.EVENT.OPEN_WIN,function(param){
			var dest=new Atwasoft.chart.Chart({'xtype':self.xtype,type:self.type,series:self.series,anchor:'98% 98%'});
			Ext.copyTo(dest ,self,
			['title','caption','subcaption','isFormField',
			'tooltip','legend','hideLegend','color','stack','xAxis','yAxis',
			'toolbox','hideToolbox','dataRange','hideDataRange','dataZoom','hideDataZoom']);
			Atwasoft.chart.openChartWin(dest);
		});
		self._initEvented_=true;
	},
	show : function(reload){
		var self=this;
		var parent=self.ownerCt;
		if(parent&&parent.getHeight()){
			parent.setHeight(parent.getHeight());
		}
		if(parent&&parent.getWidth()){
			parent.setWidth(parent.getWidth());
		}
		if(self.store)self._readStore_();
		if(self.stack){
			var series=self._option_.series;
			var len=series.length;
			for(var i=0;i<len;i++){
				if(self.stack[series[i].name]){
					series[i].stack=self.stack[series[i].name];
				}
			}
		}
		
		if(self.horizontalBar==true&&this.type=='bar'){
			var tmp=self._option_.xAxis;
			self._option_.xAxis=self._option_.yAxis;
			self._option_.yAxis=tmp;
		}
		if(reload&&self._myChart_){
			self._myChart_.setOption(self._option_, true);
		}
		else{
			self._show_();
		}
	}
});
//Ext.reg('wachart', Atwasoft.chart.Chart );