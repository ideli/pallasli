/**
 * ux.ManagedTabControl
 * @author taoy
 */

var TabFactories = new ObjectRegistry(function(page){
	return true;
});

(function(){
	Ext.ns('Ext.ux.TabControl', 'Ext.ux.TabPage');
	
	var EMPTYFN = function(){};
	
	Ext.ux.TabControl = function(config) {
		var c = {
			activeTab: 0
		}
		Ext.apply(c, config);
		Ext.ux.TabControl.superclass.constructor.call(this, c);
	};
	
	Ext.extend(Ext.ux.TabControl, Ext.TabPanel, {
		pageService: null,
		// Events
		afterSave: EMPTYFN,
		
        /**
         * @function onComplete
         * 
         * @param {Object} tag
         * @param {int} count
         * @param {int} step
         */				
		onComplete: EMPTYFN,
		
		// Working state
		ws: new Object(),
		
		loadPages: function(tabFactories) {
			if (!tabFactories)
				tabFactories = TabFactories;
				
			var interceptedPage = null;
			for (var i = tabFactories.size() - 1; i >= 0 ; i--) {
				var item = tabFactories.get(i)();
				item.next = interceptedPage;
				interceptedPage = item;
			};
			this.pageService = interceptedPage;
			
			while (interceptedPage) {
				this.add(interceptedPage);
				if (interceptedPage.setTabControl)
					interceptedPage.setTabControl(this);
				interceptedPage.on("aftersave", this.doAfterSave, this);
				interceptedPage = interceptedPage.next;
			}
			
			if (this.pageService)
				this.setActiveTab(this.pageService);
				
			this.doLayout();
		},
		
		update: function(data) {
			this.items.each(function(item){	
				if (item.setData)
	            	item.setData(data);
	        });
		},
		
		save: function(tag) {
			this.ws[tag] = {
				count: 0,
				step: 0
			};
			var self = this;

			/*
			this.items.each(function(item){	
				if (item.save)
					self.ws[tag].count = self.ws[tag].count + 1;
	        });
	        		
			this.items.each(function(item){	
				if (item.save)
					item.save(tag);
	        });	
	        */	
			var page = this.pageService;
			while (page) {
				if (page.save)
					self.ws[tag].count = self.ws[tag].count + 1;
				page = page.next;
			}
			if (this.pageService)
				this.pageService.save(tag);	
									
		},
		
		doAfterSave: function(page, success, data, tag) {
			var result = null;
			if (this.afterSave)
				result = this.afterSave(page, success, data, tag);
				
			if (this.ws[tag]) {
				this.ws[tag].step = this.ws[tag].step + 1;
				if (this.onComplete) {
					this.onComplete(tag, success, this.ws[tag].count, this.ws[tag].step);
				}
			}
			
			return result;
		}
	});
	
	Ext.ux.TabPage = function(config) {
		var c = {
		}
		Ext.apply(c, config);
		Ext.ux.TabPage.superclass.constructor.call(this, c);
		this.addEvents(
	        /**
	         * @event afterdatasave
	         * Fires after the tab page is saved.
	         * @param {Ext.Component} this
	         * @param {Boolean} success
	         * @param {Array} data
	         */		
			'aftersave'
		);
	};
	
	Ext.extend(Ext.ux.TabPage, Ext.Panel, {
		next: null,
		onSave: EMPTYFN,
		
		save: function(tag) {
			if ((this.onSave) && (this.onSave != EMPTYFN))
				this.onSave(tag);
			else 
				this.fireAfterSave(false, null, tag);

		},
		
		fireAfterSave: function(success, data, tag) {
			var result = this.fireEvent('aftersave', this, success, data, tag);
			if (this.next) 
				this.next.save(tag);
				
		}

	});	
		
})()
