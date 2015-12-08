
Ext.namespace("Atwa.editor");

Atwa.editor.LenovoText = Ext.extend(Ext.form.TextField,  {

    queryDelay : 500,
    
    queryParam : 'query',
    
    minChars : 1,
    
    minListWidth : 200,
    
    lazyInit : true,

    listClass : '',
    
    displayField : 'display',
    
    selectedClass : 'x-combo-selected',
    
    shadow : 'sides',

    listAlign : 'tl-bl?',

    maxHeight : 200,

    minHeight : 90,
    
    listWidth : 300,
    
    itemSeparator : ' ',
    
    url : '',
    
    directFn : undefined,
    
    params : {},

    // private
    initComponent : function(){
		Atwa.editor.LenovoText.superclass.initComponent.call(this);
        this.addEvents(
            'expand',

            'collapse',

            'beforeselect',

            'select',

            'beforequery'
        );
        var reader = new Ext.data.JsonReader({
			root		: 'results',
			totalProperty: 'rows',
			fields		:[this.displayField]
		});
        if(Ext.isDefined(this.directFn)){
            this.store = new Ext.data.Store({
    			proxy : new Ext.data.DirectProxy({
    				directFn : this.directFn,
    				paramsAsHash : true,
    				paramOrder : ['query','params']
    				//paramOrder : [this.queryParam]
    				
    			}),
    			//paramOrder : ['query','params'],
    			baseParams : {query:this.queryParam,params:this.params},
    			reader : reader
    		});
  
        }else{
            this.store = new Ext.data.Store({
    			proxy : new Ext.data.HttpProxy({
    				url : this.url
    			}),
    			reader : reader
    		});
        }
        if(!Ext.isDefined(this.initialConfig.minChars)){
            this.minChars = 0;
        }
        this.selectedIndex = -1;
	},
    // private
    initEvents : function(){
		Atwa.editor.LenovoText.superclass.initEvents.call(this);

        this.keyNav = new Ext.KeyNav(this.el, {
            "up" : function(e){
                this.inKeyMode = true;
                this.selectPrev();
            },

            "down" : function(e){
                this.inKeyMode = true;
                this.selectNext();
            },

            "enter" : function(e){
                this.onViewClick();
                this.delayedCheck = true;
                this.unsetDelayCheck.defer(10, this);
            },

            "esc" : function(e){
                this.collapse();
            },

            "tab" : function(e){
                this.onViewClick(false);
                return true;
            },

            scope : this,

            doRelay : function(foo, bar, hname){
                if(hname == 'down' || this.scope.isExpanded()){
                   return Ext.KeyNav.prototype.doRelay.apply(this, arguments);
                }
                return true;
            },

            forceKeyDown : true
        });
        this.queryDelay = Math.max(this.queryDelay || 250);
        this.dqTask = new Ext.util.DelayedTask(this.initQuery, this);
        if(this.editable !== false && !this.enableKeyEvents){
            this.mon(this.el, 'keyup', this.onKeyUp, this);
        }
    },

    // private
    onRender : function(ct, position){
    	Atwa.editor.LenovoText.superclass.onRender.call(this, ct, position);

        this.wrap = this.el.wrap({cls: 'x-form-field-wrap x-form-field-trigger-wrap'});
        if(!this.width){
            this.wrap.setWidth(this.el.getWidth());
        }
    	
        if(Ext.isGecko){
            this.el.dom.setAttribute('autocomplete', 'off');
        }

        if(!this.lazyInit){
            this.initList();
        }else{
            this.on('focus', this.initList, this, {single: true});
        }
    },
    
    // private
    initList : function(){
        if(!this.list){
            var cls = 'x-combo-list';

            this.list = new Ext.Layer({
                parentEl: this.getListParent(),
                shadow: this.shadow,
                cls: [cls, this.listClass].join(' '),
                constrain:false
            });

            var lw = this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth);
            this.list.setSize(lw, 0);
            this.list.swallowEvent('mousewheel');
            this.assetHeight = 0;
            if(this.syncFont !== false){
                this.list.setStyle('font-size', this.wrap.getStyle('font-size'));
            }
            if(this.title){
                this.header = this.list.createChild({cls:cls+'-hd', html: this.title});
                this.assetHeight += this.header.getHeight();
            }

            this.innerList = this.list.createChild({cls:cls+'-inner'});
            this.mon(this.innerList, 'mouseover', this.onViewOver, this);
            this.mon(this.innerList, 'mousemove', this.onViewMove, this);
            this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));

            if(!this.tpl){
                this.tpl = '<tpl for="."><div class="'+cls+'-item">{' + this.displayField + '}</div></tpl>';
            }

            this.view = new Ext.DataView({
                applyTo: this.innerList,
                tpl: this.tpl,
                singleSelect: true,
                selectedClass: this.selectedClass,
                itemSelector: this.itemSelector || '.' + cls + '-item',
                emptyText: this.listEmptyText
            });

            this.mon(this.view, 'click', this.onViewClick, this);

            this.bindStore(this.store, true);
        }
    },
    
    getStore : function(){
        return this.store;
    },

    getListParent : function() {
        return document.body;
    },
    
    // private
    onKeyUp : function(e){
        var k = e.getKey();
        if(this.editable !== false && (k == e.BACKSPACE || !e.isSpecialKey())){
            this.lastKey = k;
            this.dqTask.delay(this.queryDelay);
        }
        Atwa.editor.LenovoText.superclass.onKeyUp.call(this, e);
    },

    // private
    bindStore : function(store, initial){
        if(this.store && !initial){
            this.store.un('beforeload', this.onBeforeLoad, this);
            this.store.un('load', this.onLoad, this);
            this.store.un('exception', this.collapse, this);
            if(this.store !== store && this.store.autoDestroy){
                this.store.destroy();
            }
            if(!store){
                this.store = null;
                if(this.view){
                    this.view.bindStore(null);
                }
            }
        }
        if(store){
            this.store = Ext.StoreMgr.lookup(store);
            this.store.on({
                scope: this,
                beforeload: this.onBeforeLoad,
                load: this.onLoad,
                exception: this.collapse
            });

            if(this.view){
                this.view.bindStore(store);
            }
        }
    },
    
    // private
    onBeforeLoad : function(){
        if(!this.hasFocus){
            return;
        }
        this.innerList.update(this.loadingText ?
               '<div class="loading-indicator">'+this.loadingText+'</div>' : '');
        this.restrictHeight();
        this.selectedIndex = -1;
    },

    // private
    onLoad : function(){
        if(!this.hasFocus){
            return;
        }
        if(this.store.getCount() > 0){
            this.expand();
            this.restrictHeight();
            this.select(0, true);
        }else{
            this.onEmptyResults();
        }
        //this.el.focus();
    },

    // private
    onEmptyResults : function(){
        this.collapse();
    },
    
    // private
    validateBlur : function(){
        return !this.list || !this.list.isVisible();
    },
    
    // private
    initQuery : function(){
    	var q = this.getItemText();
    	if(q == '' || Ext.isEmpty(q)){
            this.collapse();
    		return;
    	}
        this.doQuery(this.getItemText());
    },
    
    doQuery : function(q){
        q = Ext.isEmpty(q) ? '' : q;
        var qe = {
            query: q,
            field: this,
            cancel:false
        };
        if(this.fireEvent('beforequery', qe)===false || qe.cancel){
            return false;
        }
        q = qe.query;
        var v_params=this.params;
        var v_params_new={};
        if(Ext.isDefined(v_params.panel)){
        	//alert(this.findParentByType('panel'));
        	if(Ext.isDefined(v_params.cs)){
        		var v_arrstr= new Array();
        		v_arrstr=v_params.cs.split(",");
        		//alert(v_arrstr.length);
        		var v_temp_params='{';
        		var v_cnt=0;
        		for(var i=0;i<v_arrstr.length;i++){
        			var v_temp_arr=new Array();
        			v_temp_arr=v_arrstr[i].split(":");
        			if(v_temp_arr.length==2){
        				var v_zd_value=v_params.panel.getFieldValue(v_temp_arr[0]);
        				if(Ext.isDefined(v_zd_value)){
	        				if(v_cnt==0){
	        					v_temp_params=v_temp_params + v_temp_arr[0] + ":'" + v_zd_value+"'";
	        					v_cnt=1;
	        				}else{
	        					v_temp_params=v_temp_params + ',' + v_temp_arr[0] + ":'" + v_zd_value+"'";
	        				}	        				
	        			}else{
	        				if(v_cnt==0){
	        					v_temp_params=v_temp_params + v_temp_arr[0] + ":'" + v_temp_arr[1]+"'";
	        					v_cnt=1;
	        				}else{
	        					v_temp_params=v_temp_params + ',' + v_temp_arr[0] + ":'" + v_temp_arr[1]+"'";
	        				}	
	        			}
        			}else{
        				var v_zd_value=v_params.panel.getFieldValue(v_temp_arr[0]);
        				if(Ext.isDefined(v_zd_value)){
	        				if(v_cnt==0){
	        					v_temp_params=v_temp_params + v_temp_arr[0] + ":'" + v_zd_value+"'";
	        					v_cnt=1;
	        				}else{
	        					v_temp_params=v_temp_params + ',' + v_temp_arr[0] + ":'" + v_zd_value+"'";
	        				}	        				
	        			}
        			}
        			
//        			if(Ext.isDefined(v_zd_value)){
//        				if(v_cnt==0){
//        					v_temp_params=v_temp_params + v_zdmc + ":'" + v_zd_value+"'";
//        					v_cnt=1;
//        				}else{
//        					v_temp_params=v_temp_params + ',' + v_zdmc + ":'" + v_zd_value+"'";
//        				}
//        				
//        			}
        		}
        		v_temp_params=v_temp_params + '}';
        		v_params_new=eval('('+v_temp_params+')');
        	}
        }
        
        if(q.length>=this.minChars){
           //this.store.baseParams[this.queryParam] = q;
           //this.store.baseParams[this.params] = {bm:'111'};
           //alert(this.params);
           var v_par={query:q,params:v_params_new};
           Ext.apply(this.store.baseParams,v_par);
           this.store.load();
           this.expand();
        }
    },

    // private
    unsetDelayCheck : function(){
        delete this.delayedCheck;
    },

    isExpanded : function(){
        return this.list && this.list.isVisible();
    },

    select : function(index, scrollIntoView){
        this.selectedIndex = index;
        this.view.select(index);
        if(scrollIntoView !== false){
            var el = this.view.getNode(index);
            if(el){
                this.innerList.scrollChildIntoView(el, false);
            }
        }
    },

    // private
    selectPrev : function(){
        var ct = this.store.getCount();
        if(ct > 0){
            if(this.selectedIndex == -1){
                this.select(0);
            }else if(this.selectedIndex !== 0){
                this.select(this.selectedIndex-1);
            }
        }
    },

    // private
    selectNext : function(){
        var ct = this.store.getCount();
        if(ct > 0){
            if(this.selectedIndex == -1){
                this.select(0);
            }else if(this.selectedIndex < ct-1){
                this.select(this.selectedIndex+1);
            }
        }
    },
    
    // private
    onViewMove : function(e, t){
        this.inKeyMode = false;
    },

    // private
    onViewOver : function(e, t){
        if(this.inKeyMode){ // prevent key nav and mouse over conflicts
            return;
        }
        var item = this.view.findItemFromChild(t);
        if(item){
            var index = this.view.indexOf(item);
            this.select(index, false);
        }
    },
    
    onViewClick : function(doFocus){
        var index = this.view.getSelectedIndexes()[0];
        var r = this.store.getAt(index);
        if(r){
            this.onSelect(r, index);
        }
        if(doFocus !== false){
            this.el.focus();
        }
    },
    
    // private
    onSelect : function(record, index){
        if(this.fireEvent('beforeselect', this, record, index) !== false){
            this.replaceItemText(record.data[this.displayField]);
            this.collapse();
            this.fireEvent('select', this, record, index);
        }
    },
    
    getItemText : function(){
    	var q = this.getRawValue();
    	var pos = this.el.dom.selectionStart;
    	var qb = q.substr(0, pos);
    	var indexb = qb.lastIndexOf(this.itemSeparator) + 1;
    	var qa = q.substr(pos);
    	var indexa = qa.indexOf(this.itemSeparator);
    	if(indexa < 0){
    		indexa = q.length;
    	}else{
    		indexa = pos + indexa;
    	}

    	return q.substr(indexb, indexa - indexb);
    },
    
    replaceItemText : function(v){
    	var q = this.getRawValue();
    	var pos = this.el.dom.selectionStart;
    	var qb = q.substr(0, pos);
    	var indexb = qb.lastIndexOf(this.itemSeparator) + 1;
    	var qa = q.substr(pos);
    	var indexa = qa.indexOf(this.itemSeparator);
    	if(indexa < 0){
    		indexa = q.length;
    	}else{
    		indexa = indexa + pos + 1;
    	}
    	
    	var t = q.substr(0, indexb) + v + this.itemSeparator + q.substr(indexa);
    	
    	this.el.dom.selectionStart = indexb + v.length + 1;
    	
    	this.setValue(t);
    },

    collapse : function(){
        if(!this.isExpanded()){
            return;
        }
        this.list.hide();
        Ext.getDoc().un('mousewheel', this.collapseIf, this);
        Ext.getDoc().un('mousedown', this.collapseIf, this);
        this.fireEvent('collapse', this);
    },

    // private
    collapseIf : function(e){
        if(!e.within(this.list)){
            this.collapse();
        }
    },

    expand : function(){
        if(this.isExpanded() || !this.hasFocus){
            return;
        }
        this.list.alignTo(this.wrap, this.listAlign);
        this.list.show();
        if(Ext.isGecko2){
            this.innerList.setOverflow('auto'); // necessary for FF 2.0/Mac
        }
        Ext.getDoc().on({
            scope: this,
            mousewheel: this.collapseIf,
            mousedown: this.collapseIf
        });
        this.fireEvent('expand', this);
    },
    
    // private
    restrictHeight : function(){
        this.innerList.dom.style.height = '';
        var inner = this.innerList.dom;
        var pad = this.list.getFrameWidth('tb')+this.assetHeight;
        var h = Math.max(inner.clientHeight, inner.offsetHeight, inner.scrollHeight);
        var ha = this.getPosition()[1]-Ext.getBody().getScroll().top;
        var hb = Ext.lib.Dom.getViewHeight()-ha-this.getSize().height;
        var space = Math.max(ha, hb, this.minHeight || 0)-this.list.shadowOffset-pad-5;
        h = Math.min(h, space, this.maxHeight);

        this.innerList.setHeight(h);
        this.list.beginUpdate();
        this.list.setHeight(h+pad);
        this.list.alignTo(this.wrap, this.listAlign);
        this.list.endUpdate();
    },

    // private
    onResize : function(w, h){
    	Atwa.editor.LenovoText.superclass.onResize.apply(this, arguments);
        if(this.list && !Ext.isDefined(this.listWidth)){
            var lw = Math.max(w, this.minListWidth);
            this.list.setWidth(lw);
            this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));
        }
    }
});

Ext.reg('lenovotextfield', Atwa.editor.LenovoText);

Atwa.editor.LenovoText1 = Ext.extend(Ext.form.TextField,  {

    queryDelay : 500,
    
    queryParam : 'query',
    
    minChars : 1,
    
    minListWidth : 200,
    
    lazyInit : true,

    listClass : '',
    
    displayField : 'display',
    
    selectedClass : 'x-combo-selected',
    
    shadow : 'sides',

    listAlign : 'tl-bl?',

    maxHeight : 200,

    minHeight : 90,
    
    listWidth : 300,
    
    itemSeparator : ' ',
    
    url : '',
    
    directFn : undefined,
    
    pageSize :0,
    
    paramarry : {},
    

    // private
    initComponent : function(){
		Atwa.editor.LenovoText.superclass.initComponent.call(this);
        this.addEvents(
            'expand',

            'collapse',

            'beforeselect',

            'select',

            'beforequery'
        );
        var reader = new Ext.data.JsonReader({
			root		: 'results',
			totalProperty: 'rows',
			fields		:[this.displayField]
		});
        if(Ext.isDefined(this.directFn)){
            this.store = new Ext.data.Store({
    			proxy : new Ext.data.DirectProxy({
    				directFn : this.directFn,
    				paramsAsHash : true
    				//paramOrder : ['query','params']
    				//paramOrder : [this.queryParam]
    				
    			}),
    			//paramOrder : ['query','params'],
    			baseParams : {query:this.queryParam,params:this.params},
    			reader : reader
    		});
  
        }else{
            this.store = new Ext.data.Store({
    			proxy : new Ext.data.HttpProxy({
    				url : this.url
    			}),
    			reader : reader
    		});
        }
        if(!Ext.isDefined(this.initialConfig.minChars)){
            this.minChars = 0;
        }
        this.selectedIndex = -1;
	},
    // private
    initEvents : function(){
		Atwa.editor.LenovoText.superclass.initEvents.call(this);

        this.keyNav = new Ext.KeyNav(this.el, {
            "up" : function(e){
                this.inKeyMode = true;
                this.selectPrev();
            },

            "down" : function(e){
                this.inKeyMode = true;
                this.selectNext();
            },

            "enter" : function(e){
                this.onViewClick();
                this.delayedCheck = true;
                this.unsetDelayCheck.defer(10, this);
            },

            "esc" : function(e){
                this.collapse();
            },

            "tab" : function(e){
                this.onViewClick(false);
                return true;
            },

            scope : this,

            doRelay : function(foo, bar, hname){
                if(hname == 'down' || this.scope.isExpanded()){
                   return Ext.KeyNav.prototype.doRelay.apply(this, arguments);
                }
                return true;
            },

            forceKeyDown : true
        });
        this.queryDelay = Math.max(this.queryDelay || 250);
        this.dqTask = new Ext.util.DelayedTask(this.initQuery, this);
        if(this.editable !== false && !this.enableKeyEvents){
            this.mon(this.el, 'keyup', this.onKeyUp, this);
        }
    },

    // private
    onRender : function(ct, position){
    	Atwa.editor.LenovoText.superclass.onRender.call(this, ct, position);

        this.wrap = this.el.wrap({cls: 'x-form-field-wrap x-form-field-trigger-wrap'});
        if(!this.width){
            this.wrap.setWidth(this.el.getWidth());
        }
    	
        if(Ext.isGecko){
            this.el.dom.setAttribute('autocomplete', 'off');
        }

        if(!this.lazyInit){
            this.initList();
        }else{
            this.on('focus', this.initList, this, {single: true});
        }
    },
    
    // private
    initList : function(){
        if(!this.list){
            var cls = 'x-combo-list';

            this.list = new Ext.Layer({
                parentEl: this.getListParent(),
                shadow: this.shadow,
                cls: [cls, this.listClass].join(' '),
                constrain:false
            });

            var lw = this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth);
            this.list.setSize(lw, 0);
            this.list.swallowEvent('mousewheel');
            this.assetHeight = 0;
            if(this.syncFont !== false){
                this.list.setStyle('font-size', this.wrap.getStyle('font-size'));
            }
            if(this.title){
                this.header = this.list.createChild({cls:cls+'-hd', html: this.title});
                this.assetHeight += this.header.getHeight();
            }

            this.innerList = this.list.createChild({cls:cls+'-inner'});
            this.mon(this.innerList, 'mouseover', this.onViewOver, this);
            this.mon(this.innerList, 'mousemove', this.onViewMove, this);
            this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));
            
            if(this.pageSize){
                this.footer = this.list.createChild({cls:cls+'-ft'});
                this.pageTb = new Ext.PagingToolbar({
                    store: this.store,
                    pageSize: this.pageSize,
                    renderTo:this.footer
                });
                this.assetHeight += this.footer.getHeight();
            }
            if(!this.tpl){
                this.tpl = '<tpl for="."><div class="'+cls+'-item">{' + this.displayField + '}</div></tpl>';
            }

            this.view = new Ext.DataView({
                applyTo: this.innerList,
                tpl: this.tpl,
                singleSelect: true,
                selectedClass: this.selectedClass,
                itemSelector: this.itemSelector || '.' + cls + '-item',
                emptyText: this.listEmptyText
            });

            this.mon(this.view, 'click', this.onViewClick, this);

            this.bindStore(this.store, true);
        }
    },
    
    getStore : function(){
        return this.store;
    },

    getListParent : function() {
        return document.body;
    },
    
    // private
    onKeyUp : function(e){
        var k = e.getKey();
        if(this.editable !== false && (k == e.BACKSPACE || !e.isSpecialKey())){
            this.lastKey = k;
            this.dqTask.delay(this.queryDelay);
        }
        Atwa.editor.LenovoText.superclass.onKeyUp.call(this, e);
    },

    // private
    bindStore : function(store, initial){
        if(this.store && !initial){
            this.store.un('beforeload', this.onBeforeLoad, this);
            this.store.un('load', this.onLoad, this);
            this.store.un('exception', this.collapse, this);
            if(this.store !== store && this.store.autoDestroy){
                this.store.destroy();
            }
            if(!store){
                this.store = null;
                if(this.view){
                    this.view.bindStore(null);
                }
            }
        }
        if(store){
            this.store = Ext.StoreMgr.lookup(store);
            this.store.on({
                scope: this,
                beforeload: this.onBeforeLoad,
                load: this.onLoad,
                exception: this.collapse
            });

            if(this.view){
                this.view.bindStore(store);
            }
        }
    },
    
    // private
    onBeforeLoad : function(){
        if(!this.hasFocus){
            return;
        }
        this.innerList.update(this.loadingText ?
               '<div class="loading-indicator">'+this.loadingText+'</div>' : '');
        this.restrictHeight();
        this.selectedIndex = -1;
    },

    // private
    onLoad : function(){
        if(!this.hasFocus){
            return;
        }
        if(this.store.getCount() > 0){
            this.expand();
            this.restrictHeight();
            this.select(0, true);
        }else{
            this.onEmptyResults();
        }
        //this.el.focus();
    },

    // private
    onEmptyResults : function(){
        this.collapse();
    },
    
    // private
    validateBlur : function(){
        return !this.list || !this.list.isVisible();
    },
    
    // private
    initQuery : function(){
    	var q = this.getItemText();
    	if(q == '' || Ext.isEmpty(q)){
            this.collapse();
    		return;
    	}
        this.doQuery(this.getItemText());
    },
   
    doQuery : function(q){
        q = Ext.isEmpty(q) ? '' : q;
        var qe = {
            query: q,
            field: this,
            cancel:false
        };
        if(this.fireEvent('beforequery', qe)===false || qe.cancel){
            return false;
        }
        q = qe.query;
        var v_params=this.paramarry;
		var v_params_new={};
		if(Ext.isDefined(v_params.panel)){
			if(Ext.isDefined(v_params.cs)){
				var v_arrstr= new Array();
				v_arrstr=v_params.cs.split(",");
				var v_cnt=0;
				for(var i=0;i<v_arrstr.length;i++){
					var v_temp_arr=new Array();
					v_temp_arr=v_arrstr[i].split(":");
					v_params_new[v_temp_arr[0]]=v_params.panel.getFieldValue(v_temp_arr[0]);
				}
			}
		}
       
        if(q.length>=this.minChars){
           this.store.baseParams[this.queryParam] = q;

            Ext.apply(this.store.baseParams,v_params_new);
          // this.store.load();
           this.store.load({
                params: this.getParams(q)
            });

           this.expand();
        }
    },
    
    getParams : function(q){
        var p = {};
        //p[this.queryParam] = q;
        if(this.pageSize){
            p.start = 0;
            p.limit = this.pageSize;
        }
        return p;
    },

    // private
    unsetDelayCheck : function(){
        delete this.delayedCheck;
    },

    isExpanded : function(){
        return this.list && this.list.isVisible();
    },

    select : function(index, scrollIntoView){
        this.selectedIndex = index;
        this.view.select(index);
        if(scrollIntoView !== false){
            var el = this.view.getNode(index);
            if(el){
                this.innerList.scrollChildIntoView(el, false);
            }
        }
    },

    // private
    selectPrev : function(){
        var ct = this.store.getCount();
        if(ct > 0){
            if(this.selectedIndex == -1){
                this.select(0);
            }else if(this.selectedIndex !== 0){
                this.select(this.selectedIndex-1);
            }
        }
    },

    // private
    selectNext : function(){
        var ct = this.store.getCount();
        if(ct > 0){
            if(this.selectedIndex == -1){
                this.select(0);
            }else if(this.selectedIndex < ct-1){
                this.select(this.selectedIndex+1);
            }
        }
    },
    
    // private
    onViewMove : function(e, t){
        this.inKeyMode = false;
    },

    // private
    onViewOver : function(e, t){
        if(this.inKeyMode){ // prevent key nav and mouse over conflicts
            return;
        }
        var item = this.view.findItemFromChild(t);
        if(item){
            var index = this.view.indexOf(item);
            this.select(index, false);
        }
    },
    
    onViewClick : function(doFocus){
        var index = this.view.getSelectedIndexes()[0];
        var r = this.store.getAt(index);
        if(r){
            this.onSelect(r, index);
        }
        if(doFocus !== false){
            this.el.focus();
        }
    },
    
    // private
    onSelect : function(record, index){
        if(this.fireEvent('beforeselect', this, record, index) !== false){
            this.replaceItemText(record.data[this.displayField]);
            this.collapse();
            this.fireEvent('select', this, record, index);
        }
    },
    
    getItemText : function(){
    	var q = this.getRawValue();
    	var pos = this.el.dom.selectionStart;
    	var qb = q.substr(0, pos);
    	var indexb = qb.lastIndexOf(this.itemSeparator) + 1;
    	var qa = q.substr(pos);
    	var indexa = qa.indexOf(this.itemSeparator);
    	if(indexa < 0){
    		indexa = q.length;
    	}else{
    		indexa = pos + indexa;
    	}

    	return q.substr(indexb, indexa - indexb);
    },
    
    replaceItemText : function(v){
    	var q = this.getRawValue();
    	var pos = this.el.dom.selectionStart;
    	var qb = q.substr(0, pos);
    	var indexb = qb.lastIndexOf(this.itemSeparator) + 1;
    	var qa = q.substr(pos);
    	var indexa = qa.indexOf(this.itemSeparator);
    	if(indexa < 0){
    		indexa = q.length;
    	}else{
    		indexa = indexa + pos + 1;
    	}
    	
    	var t = q.substr(0, indexb) + v + this.itemSeparator + q.substr(indexa);
    	
    	this.el.dom.selectionStart = indexb + v.length + 1;
    	
    	this.setValue(t);
    },

    collapse : function(){
        if(!this.isExpanded()){
            return;
        }
        this.list.hide();
        Ext.getDoc().un('mousewheel', this.collapseIf, this);
        Ext.getDoc().un('mousedown', this.collapseIf, this);
        this.fireEvent('collapse', this);
    },

    // private
    collapseIf : function(e){
        if(!e.within(this.list)){
            this.collapse();
        }
    },

    expand : function(){
        if(this.isExpanded() || !this.hasFocus){
            return;
        }
        this.list.alignTo(this.wrap, this.listAlign);
        this.list.show();
        if(Ext.isGecko2){
            this.innerList.setOverflow('auto'); // necessary for FF 2.0/Mac
        }
        Ext.getDoc().on({
            scope: this,
            mousewheel: this.collapseIf,
            mousedown: this.collapseIf
        });
        this.fireEvent('expand', this);
    },
    
    // private
    restrictHeight : function(){
        this.innerList.dom.style.height = '';
        var inner = this.innerList.dom;
        var pad = this.list.getFrameWidth('tb')+this.assetHeight;
        var h = Math.max(inner.clientHeight, inner.offsetHeight, inner.scrollHeight);
        var ha = this.getPosition()[1]-Ext.getBody().getScroll().top;
        var hb = Ext.lib.Dom.getViewHeight()-ha-this.getSize().height;
        var space = Math.max(ha, hb, this.minHeight || 0)-this.list.shadowOffset-pad-5;
        h = Math.min(h, space, this.maxHeight);

        this.innerList.setHeight(h);
        this.list.beginUpdate();
        this.list.setHeight(h+pad);
        this.list.alignTo(this.wrap, this.listAlign);
        this.list.endUpdate();
    },

    // private
    onResize : function(w, h){
    	Atwa.editor.LenovoText.superclass.onResize.apply(this, arguments);
        if(this.list && !Ext.isDefined(this.listWidth)){
            var lw = Math.max(w, this.minListWidth);
            this.list.setWidth(lw);
            this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));
        }
    }
});

Ext.reg('lenovotextfield1', Atwa.editor.LenovoText1);