/*重载Ext.form.Field.setReadOnly方法,field只读时改变fieldclass*/
Ext.form.Field.prototype.setReadOnly = function(readOnly){
        if(this.rendered){
            this.el.dom.readOnly = readOnly;
            if (readOnly) {
               this.el.addClass('x-form-readonly-field');
               //this.el.applyStyles('background:transparent; border-right: 0px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: #000000 0px solid;');
            }
            else{
            	this.el.removeClass('x-form-readonly-field');
            	//this.el.applyStyles('background:#fff url(/portal/dependences/extjs/ext-3.2.1/resources/images/default/form/text-bg.gif) repeat-x 0 0;border: 1px solid #B5B8C8;');
            }
        }
        this.readOnly = readOnly;
};
/*给TextField增加鼠标移入事件*/
Ext.form.TextField.prototype.initComponent =function(){
	    Ext.form.TextField.superclass.initComponent.call(this);
        this.addEvents( 
            'autosize',
            'keydown', 
            'keyup',     
            'keypress', 
            'mouseover'
        );
 };
 Ext.form.TextField.prototype.initEvents = function(){
        Ext.form.TextField.superclass.initEvents.call(this);
        if(this.validationEvent == 'keyup'){
            this.validationTask = new Ext.util.DelayedTask(this.validate, this);
            this.mon(this.el, 'keyup', this.filterValidation, this);
        }
        else if(this.validationEvent !== false && this.validationEvent != 'blur'){
        	this.mon(this.el, this.validationEvent, this.validate, this, {buffer: this.validationDelay});
        }
        if(this.selectOnFocus || this.emptyText){            
            this.mon(this.el, 'mousedown', this.onMouseDown, this);
            
            if(this.emptyText){
                this.applyEmptyText();
            }
        }
        if(this.maskRe || (this.vtype && this.disableKeyFilter !== true && (this.maskRe = Ext.form.VTypes[this.vtype+'Mask']))){
        	this.mon(this.el, 'keypress', this.filterKeys, this);
        }
        if(this.grow){
        	this.mon(this.el, 'keyup', this.onKeyUpBuffered, this, {buffer: 50});
			this.mon(this.el, 'click', this.autoSize, this);
        }
        if(this.inputType!='password'){
        	this.mon(this.el, 'mouseover', this.onMouseover, this);
        }
        if(this.enableKeyEvents){
            this.mon(this.el, {
                scope: this,
                keyup: this.onKeyUp,
                keydown: this.onKeyDown,
                keypress: this.onKeyPress
            });
        }
};
/*鼠标移入TextField后弹出TextField的值*/
Ext.form.TextField.prototype.onMouseover = function(){
        this.getEl().dom.setAttribute("ext:qtip", this.getValue());
};
/*重载Ext.form.TriggerField.updateEditState方法,trigger类型的field只读时改变fieldclass*/
Ext.form.TriggerField.prototype.updateEditState = function(){
       if(this.rendered){
            if (this.readOnly) {
                this.el.dom.readOnly = true;
                this.el.addClass('x-form-readonly-field');
                //this.getEl().applyStyles('background:transparent; border-right: 0px solid;border-top: 0px solid;border-left: 0px solid;border-bottom: #000000 0px solid;');
                this.mun(this.el, 'click', this.onTriggerClick, this);
                this.trigger.setDisplayed(false);
            } else {
            	//this.el.applyStyles('background:#fff url(/portal/dependences/extjs/ext-3.2.1/resources/images/default/form/text-bg.gif) repeat-x 0 0;border: 1px solid #B5B8C8;');
                if (!this.editable) {
                    this.el.dom.readOnly = true;
                    this.el.removeClass('x-form-readonly-field');
                    this.el.addClass('x-trigger-noedit');
                    this.mon(this.el, 'click', this.onTriggerClick, this);
                } else {
                    this.el.dom.readOnly = false;
                    this.el.removeClass('x-form-readonly-field');
                    this.el.removeClass('x-trigger-noedit');
                    this.mun(this.el, 'click', this.onTriggerClick, this);
                }
                this.trigger.setDisplayed(!this.hideTrigger);
            }
            this.onResize(this.width || this.wrap.getWidth());
        }
};
/*重载Ext.form.DateField.onTriggerClick方法,如果DateField只读将不能触发click事件*/
Ext.form.DateField.prototype.onTriggerClick = function(){
        if(this.readOnly || this.disabled){
            return;
        }
        if(this.menu == null){
            this.menu = new Ext.menu.DateMenu({
                hideOnClick: false,
                focusOnSelect: false
            });
        }
        this.onFocus();
        Ext.apply(this.menu.picker,  {
            minDate : this.minValue,
            maxDate : this.maxValue,
            disabledDatesRE : this.disabledDatesRE,
            disabledDatesText : this.disabledDatesText,
            disabledDays : this.disabledDays,
            disabledDaysText : this.disabledDaysText,
            format : this.format,
            showToday : this.showToday,
            minText : String.format(this.minText, this.formatDate(this.minValue)),
            maxText : String.format(this.maxText, this.formatDate(this.maxValue))
        });
        this.menu.picker.setValue(this.getValue() || new Date());
        this.menu.show(this.el, "tl-bl?");
        this.menuEvents('on');
 };
/*重载ECls.form.DateTimeField.onTriggerClick方法,如果ateTimeField只读将不能触发click事件*/
Cls.form.DateTimeField.prototype.onTriggerClick = function(){
        if(this.readOnly || this.disabled){
            return;
        }
        if(this.menu == null){
            this.menu = new Ext.menu.DateMenu({
                hideOnClick: false,
                focusOnSelect: false
            });
        }
        this.onFocus();
        Ext.apply(this.menu.picker,  {
            minDate : this.minValue,
            maxDate : this.maxValue,
            disabledDatesRE : this.disabledDatesRE,
            disabledDatesText : this.disabledDatesText,
            disabledDays : this.disabledDays,
            disabledDaysText : this.disabledDaysText,
            format : this.format,
            showToday : this.showToday,
            minText : String.format(this.minText, this.formatDate(this.minValue)),
            maxText : String.format(this.maxText, this.formatDate(this.maxValue))
        });
        this.menu.picker.setValue(this.getValue() || new Date());
        this.menu.show(this.el, "tl-bl?");
        this.menuEvents('on');
 };
 /*鼠标移入TriggerField后弹出TriggerField的值*/
Ext.form.TriggerField.prototype.onMouseover = function(){
        this.getEl().dom.setAttribute("ext:qtip", this.getRawValue());   
};
/*FormPanel增加labelPad属性,值为数字型,labelPad属性是label与输入栏之间距离*/
Ext.FormPanel.prototype.applySettings=function(c){
    var ct = c.ownerCt;
    Ext.applyIf(c, {
        labelAlign: ct.labelAlign,
        labelWidth: ct.labelWidth,
        labelPad:ct.labelPad,
        itemCls: ct.itemCls
    });
}; 
/*重载Ext.PagingToolbar.initComponent方法，去掉Ext.PagingToolbar刷新*/
Ext.PagingToolbar.prototype.initComponent = function(){
        var pagingItems = [this.first = new Ext.Toolbar.Button({
            tooltip: this.firstText,
            overflowText: this.firstText,
            iconCls: 'x-tbar-page-first',
            disabled: true,
            handler: this.moveFirst,
            scope: this
        }), this.prev = new Ext.Toolbar.Button({
            tooltip: this.prevText,
            overflowText: this.prevText,
            iconCls: 'x-tbar-page-prev',
            disabled: true,
            handler: this.movePrevious,
            scope: this
        }), '-', this.beforePageText,
        this.inputItem = new Ext.form.NumberField({
            cls: 'x-tbar-page-number',
            allowDecimals: false,
            allowNegative: false,
            enableKeyEvents: true,
            selectOnFocus: true,
            submitValue: false,
            listeners: {
                scope: this,
                keydown: this.onPagingKeyDown,
                blur: this.onPagingBlur
            }
        }), this.afterTextItem = new Ext.Toolbar.TextItem({
            text: String.format(this.afterPageText, 1)
        }), '-', this.next = new Ext.Toolbar.Button({
            tooltip: this.nextText,
            overflowText: this.nextText,
            iconCls: 'x-tbar-page-next',
            disabled: true,
            handler: this.moveNext,
            scope: this
        }), this.last = new Ext.Toolbar.Button({
            tooltip: this.lastText,
            overflowText: this.lastText,
            iconCls: 'x-tbar-page-last',
            disabled: true,
            handler: this.moveLast,
            scope: this
        }), this.refresh = new Ext.Toolbar.Button({
            tooltip: this.refreshText,
            overflowText: this.refreshText,
            iconCls: 'x-tbar-loading',
            handler: this.doRefresh,
			hidden :true,
            scope: this
        })];


        var userItems = this.items || this.buttons || [];
        if (this.prependButtons) {
            this.items = userItems.concat(pagingItems);
        }else{
            this.items = pagingItems.concat(userItems);
        }
        delete this.buttons;
        if(this.displayInfo){
            this.items.push('->');
            this.items.push(this.displayItem = new Ext.Toolbar.TextItem({}));
        }
        Ext.PagingToolbar.superclass.initComponent.call(this);
        this.addEvents(
            
            'change',
            
            'beforechange'
        );
        this.on('afterlayout', this.onFirstLayout, this, {single: true});
        this.cursor = 0;
        this.bindStore(this.store, true);
};
/*重载Ext.ux.ProgressBarPager.init方法，去掉Ext.ux.ProgressBarPager的click事件*/
Ext.ux.ProgressBarPager.prototype.init=function(parent){
	if(parent.displayInfo){
			this.parent = parent;
			var ind  = parent.items.indexOf(parent.displayItem);
			parent.remove(parent.displayItem, true);
			this.progressBar = new Ext.ProgressBar({
				text    : this.defaultText,
				width   : this.progBarWidth,
				animate :  this.defaultAnimCfg
			});					
		   
			parent.displayItem = this.progressBar;
			
			parent.add(parent.displayItem);	
			parent.doLayout();
			Ext.apply(parent, this.parentOverrides);		
			
			this.progressBar.on('render', function(pb) {
                pb.mon(pb.getEl().applyStyles('cursor:pointer'), '', this.handleProgressBarClick, this);
            }, this, {single: true});
						
		}
};
/*重载Ext.grid.GroupingView.doRender方法*/
Ext.grid.GroupingView.prototype.doRender = function(cs, rs, ds, startRow, colCount, stripe){
        if(rs.length < 1){
            return '';
        }

        if(!this.canGroup() || this.isUpdating){
            return Ext.grid.GroupingView.superclass.doRender.apply(this, arguments);
        }

        var groupField = this.getGroupField(),
            colIndex = this.cm.findColumnIndex(groupField),
            g,
            gstyle = 'width:' + this.getTotalWidth() + ';',
            cfg = this.cm.config[colIndex],
            groupRenderer = cfg.groupRenderer || cfg.renderer,
            prefix = this.showGroupName ? (cfg.groupName || cfg.header)+': ' : '',
            groups = [],
            curGroup, i, len, gid;
        for(i = 0, len = rs.length; i < len; i++){
            var rowIndex = startRow + i,
                r = rs[i],
                gvalue = r.data[groupField];
                g = this.getGroup(gvalue, r, groupRenderer, rowIndex, colIndex, ds);
                if(this.issummary){
	                if(i<(len-1)){
	                     prefix=prefix;
	                }
	                else{
	                	g='';
	                	prefix='';
	                }
                }
            if(!curGroup || curGroup.group != g){
                gid = this.constructId(gvalue, groupField, colIndex);
                // if state is defined use it, however state is in terms of expanded
                // so negate it, otherwise use the default.
                this.state[gid] = !(Ext.isDefined(this.state[gid]) ? !this.state[gid] : this.startCollapsed);
                curGroup = {
                    group: g,
                    gvalue: gvalue,
                    text: prefix + g,
                    groupId: gid,
                    startRow: rowIndex,
                    rs: [r],
                    cls: this.state[gid] ? '' : 'x-grid-group-collapsed',
                    style: gstyle
                };
                groups.push(curGroup);
            }else{
                curGroup.rs.push(r);
            }
            r._groupId = gid;
        }

        var buf = [];
        for(i = 0, len = groups.length; i < len; i++){
            g = groups[i];
            this.doGroupStart(buf, g, cs, ds, colCount);
            buf[buf.length] = Ext.grid.GroupingView.superclass.doRender.call(
                    this, cs, g.rs, ds, g.startRow, colCount, stripe);

            this.doGroupEnd(buf, g, cs, ds, colCount);
        }
        return buf.join('');
};
/*重载Ext.grid.GridView.handleHdDown方法，去掉gridview排序*/
Ext.grid.GridView.prototype.handleHdDown = function(e, t){
        if(Ext.fly(t).hasClass('x-grid3-hd-btn')){
            e.stopEvent();
            var hd = this.findHeaderCell(t);
            Ext.fly(hd).addClass('x-grid3-hd-menu-open');
            var index = this.getCellIndex(hd);
            this.hdCtxIndex = index;
            var ms = this.hmenu.items, cm = this.cm;
            ms.get('asc').setDisabled(!cm.isSortable(index));
            ms.get('desc').setDisabled(!cm.isSortable(index));
            if(!cm.isSortable(index)){
               ms.get('asc').hide();
               ms.get('desc').hide();
            }
            else{
            	ms.get('asc').show();
                ms.get('desc').show();
            }
            this.hmenu.on('hide', function(){
                Ext.fly(hd).removeClass('x-grid3-hd-menu-open');
            }, this, {single:true});
            this.hmenu.show(t, 'tl-bl?');
        }
};

/*重载Ext.grid.GridView.initUI方法，改header为双击事件*/
Ext.grid.GridView.prototype.initUI = function(grid){
       grid.on('headerdblclick', this.onHeaderClick, this);
};

Ext.Component.prototype.onDisable = function(){
     this.getActionEl().addClass(this.disabledClass);
     if(!Ext.isIE){
        this.el.dom.disabled = true;
     }
};

Ext.tree.TreeEventModel.prototype.onNodeClick = function(e, node){
	node.ui.onClick(e);
	if(Ext.isMac){
       node.ui.onContextMenu(e);
	}
};

 Ext.grid.GridPanel.prototype.onClick = function(e){
        this.processEvent('click', e);
        if(Ext.isMac){
          // this.processEvent('dblclick', e);
           this.processEvent('contextmenu', e);
        }
 };
 Mixky.plugins.UploadButton.prototype.initComponent = function(){
	    Mixky.plugins.UploadButton.superclass.initComponent.call(this);
		this.on('afterrender', function(btn){
			var cfg = Ext.apply({button:this}, this.uploadConfig);
			setTimeout(function(){
				this.uploadPlugin = new Mixky.plugins.UploadButtonPlugin(cfg);
		   },500);
			
		});
 };
/*重写grid的Ext.grid.RowSelectionModel的onEditorKey按键事件，在grid中使回车功能等同于tab*/
Ext.override(Ext.grid.RowSelectionModel, {
        onEditorKey : function(field, e) {
            var k = e.getKey(), newCell, g = this.grid,l = g.lastEdit, ed = g.activeEditor;  
            var shift = e.shiftKey;
            if(ed){
            }else{
                ed=l;
            }
            if(Ext.isIE){
               if(k == e.BACKSPACE){
            	  if(ed){
	                if(g.getColumnModel().isCellEditable(ed.col,ed.row)){
	                	var c = g.getColumnModel().config[ed.col];
	                	var ed1 = c.editor.editable;
	                	var ed2 = c.editor.readOnly;
	                	var ed3 = c.editor.disabled;
	                	
	                	if(Ext.isDefined(ed1)||Ext.isDefined(ed2)||Ext.isDefined(ed3)){
	                		if(typeof ed1 != 'undefined'){
			                	if(!ed1){
			            	       e.stopEvent();
			                	}
	                		}
	                		if(typeof ed2 != 'undefined'||typeof ed3 != 'undefined'){
			                	if(ed2){
			            	       e.stopEvent();
			                	}
	                		}
	                		if(typeof ed3 != 'undefined'){
			                	if(ed3){
			            	       e.stopEvent();
			                	}
	                		}
	                	}
	                }
            	 }
               }
            }
            if (k == e.ENTER) {
                e.stopEvent();
                if(ed){
                   // ed.completeEdit();
                }
               // g.getColumnModel().isCellEditable();
                if (shift) {
                    newCell = g.walkCells(ed.row, ed.col - 1, -1,this.acceptsNav, this);
                } else {
                    newCell = g.walkCells(ed.row, ed.col + 1, 1,this.acceptsNav, this);
                }
            } else if (k == e.TAB) {
                e.stopEvent();
                ed.completeEdit();
                if (this.moveEditorOnEnter !== false) {
                    if (shift) {
                        newCell = g.walkCells(ed.row - 1, ed.col, -1,this.acceptsNav, this);
                    } else {
                        newCell = g.walkCells(ed.row, ed.col + 1, 1,this.acceptsNav, this);
                    }
                }
            }
            if (newCell) {
            	 r = newCell[0];
                 c = newCell[1];
                 if(l.row != r){
                     this.selectRow(r); 
                 }
                 setTimeout(function(){g.startEditing(r, c);},20);
            }
        }
 });

/*将extjs中的英文翻译提示成中文提示*/
Ext.ux.TabCloseMenu.prototype.closeTabText = '关闭标签';
Ext.ux.TabCloseMenu.prototype.closeOtherTabsText = '关闭其它标签';
Ext.ux.TabCloseMenu.prototype.closeAllTabsText = '关闭所有标签';
Ext.form.CheckboxGroup.prototype.blankText = '请对所列选项做出至少一项选择'; 
Ext.form.RadioGroup.prototype.blankText = '请对所列选项做出选择'; 
 