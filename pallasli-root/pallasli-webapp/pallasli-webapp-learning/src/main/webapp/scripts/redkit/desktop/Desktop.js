Redkit.app.Desktop = function(cfg) {
    Ext.apply(this, cfg);
    this.addEvents({
        'ready' : true,
        'beforeunload' : true
    });

    Ext.onReady(this.initDesktop, this);	
}

Ext.extend(Redkit.app.Desktop, Ext.util.Observable, {
	
	isReady: false,
	win: null,
	location: location,
	
    getStartConfig : function(){

    },	
	
	initDesktop : function(){
    	this.startConfig = this.startConfig || this.getStartConfig();

        this.init();

        Ext.EventManager.on(window, 'beforeunload', this.onUnload, this);
		this.fireEvent('ready', this);
        this.isReady = true;
    },
    
    init : Ext.emptyFn,
    
    onReady : function(fn, scope){
        if(!this.isReady){
            this.on('ready', fn, scope);
        }else{
            fn.call(scope, this);
        }
    },
    
    onUnload : function(e){
        if(this.fireEvent('beforeunload', this) === false){
            e.stopEvent();
        }
    },    
	
	showWindow: function(cfg) {
		if (!this.win) {
			var _iframePanel = new Ext.ux.ManagedIframePanel({
		        border: false,
				bodyBorder: false,
				loadMask: {
					msg : '正在加载页面，请稍候...'
				}
			});	
			
			var _cfg = {
				title: 'Untitiled',
	            layout: 'fit',
	            width: 400,
	            height: 300,
	            modal: true,
	            border: false,
	            bodyBorder: false, 
	            items: [_iframePanel]
			};
			
			
			Ext.apply(_cfg, cfg);	
			this.win = new Ext.Window(_cfg);
			this.win.iframePanel = _iframePanel;	
			
			this.win.on('close', this.onWindowClose, this);
		}
		this.win.show();
		
		if ((cfg)&&(cfg.url))
			this.win.iframePanel.setSrc(cfg.url);
	},
	
	closeWindow: function(reload, result) {
		if (this.win) {
			this.win._reload = reload;
			if (!result)
				result = "Ok";
			this.win._result = result;
			this.win.close();
		}
	},
	
	onWindowClose: function(win) {
		if (this.win) {
			if (this.win.callback) {
				this.win.callback(this.win._result);
			}
			if (this.win._reload) {
				// replace location.reload() for unknown reason.
				location.href = location.href;
			}
			this.win = null;
		}
	}
})