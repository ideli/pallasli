		
Ext.define('MyDesktop.components.ContextMenu', {
    extend: 'Ext.panel.Panel',
    initComponent: function(){
		toolbar = Ext.widget({
                xtype: 'toolbar',
                border: true,
                rtl: false,
                floating: true,
                fixed: true,
                preventFocusOnActivate: true,
                draggable: {
                    constrain: true
                },
                items: [ 
                         
                         
                         
                         
                         
                         {
                    xtype: 'button',
                    rtl: false,
                    hidden: !(Ext.repoDevMode || location.href.indexOf('qa.sencha.com') !== -1),
                    enableToggle: true,
                    pressed: false,
                    text: 'RTL',
                    margin: '0 5 0 0',
                    listeners: {
                        toggle: function(btn, pressed) {
                            if (pressed) {
                                setParam({ rtl: true });
                            } else {
                                removeParam('rtl');
                            }
                        }
                    }
                }, {
                    xtype: 'tool',
                    type: 'close',
                    rtl: false,
                    handler: function() {
                        toolbar.destroy();
                    }
                }],

                // Extra constraint margins within default constrain region of parentNode
                constraintInsets: '0 -' + (Ext.getScrollbarSize().width + 4) + ' 0 0'
            });
            toolbar.showAt(100,300);
         
            
            var constrainer = function() {
                toolbar.doConstrain();
            };
            
            Ext.EventManager.onWindowResize(constrainer);
            toolbar.on('destroy', function() { 
                Ext.EventManager.removeResizeListener(constrainer);
            });
            this.toolbar=toolbar;
            this.on("close",function(){
            	  this.toolbar.destroy();
            });
	}
});