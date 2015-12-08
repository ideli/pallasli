Ext
		.define(
				'Pallas.portal.waapp.desktop.webpage.Shortcuts',
				{
					extend : "Ext.util.Observable",

					initComponent : function() {
						var desktopEl = this.renderTo, btnHeight = 74, btnWidth = 64, btnPadding = 15, col = null, row = null, items = [], columnsrefreshWidth = 0;

						this.addEvents('columnsrefresh');

						this.menu = new Ext.menu.Menu(
								{
									items : [
											{
												iconCls : 'icon-common-open',
												text : '打开',
												handler : function(b) {
													b.parentMenu.button
															.handler();
												}
											},
											'-',
											{
												iconCls : 'icon-common-remove',
												text : '移除',
												scope : this,
												handler : function(b) {
													MixkyApp
															.removeShortcut(
																	b.parentMenu.button.btntype,
																	b.parentMenu.button.applicationkey,
																	b.parentMenu.button.key);
												}
											} ]
								});
						this.initColRow = function() {
							col = {
								index : 1,
								x : btnPadding
							};
							row = {
								index : 1,
								y : btnPadding
							};
							columnsrefreshWidth = btnPadding;
							this.fireEvent('columnsrefresh',
									columnsrefreshWidth);
						};
						this.initColRow();

						function isOverflow(y) {
							if (y > desktopEl.getHeight()) {
								return true;
							}
							return false;
						}

						this.addShortcut = function(config) {
							var div = desktopEl.createChild({
								tag : 'div',
								cls : 'ux-shortcut-item'
							}), btn = new Mixky.wasoft.desktop.ShortcutButton(
									Ext.apply(config, {
										text : Ext.util.Format.ellipsis(
												config.text, 25)
									}), div), menu = this.menu;
							// 系统图标

							// 右键菜单
							div.addListener('contextmenu', function(e) {
								menu.button = btn;
								menu.showAt(e.getXY());
							});
							items.push(btn);
							this.setXY(btn.container);

							return btn;
						};
						// add by zhangchang begin
						this.getButtonCmp = function(btntype, appkey, key) {
							for (var i = 0, len = items.length; i < len; i++) {
								if (items[i].btntype == btntype
										&& items[i].applicationkey == appkey
										&& items[i].key == key) {
									return items[i];
								}
							}
						};
						// add by zhangchang end

						this.removeShortcut = function(b) {
							var d = document.getElementById(b.container.id);

							b.destroy();
							d.parentNode.removeChild(d);

							var s = [];
							for (var i = 0, len = items.length; i < len; i++) {
								if (items[i] != b) {
									s.push(items[i]);
								}
							}
							items = s;

							this.handleUpdate();
						};

						this.handleUpdate = function() {
							this.initColRow();
							for (var i = 0, len = items.length; i < len; i++) {
								this.setXY(items[i].container);
							}
						};

						this.setXY = function(item) {
							var bottom = row.y + btnHeight, overflow = isOverflow(row.y
									+ btnHeight);

							if (overflow && bottom > (btnHeight + btnPadding)) {
								col = {
									index : col.index++,
									x : col.x + btnWidth + btnPadding
								};
								row = {
									index : 1,
									y : btnPadding
								};
							}

							item.setLeftTop(col.x, row.y);

							row.index++;
							row.y = row.y + btnHeight + btnPadding;
							if (columnsrefreshWidth != col.x + btnWidth
									+ btnPadding) {
								columnsrefreshWidth = col.x + btnWidth
										+ btnPadding;
								this.fireEvent('columnsrefresh',
										columnsrefreshWidth);
							}
						};

						Ext.EventManager.onWindowResize(this.handleUpdate,
								this, {
									delay : 500
								});

					}
				});