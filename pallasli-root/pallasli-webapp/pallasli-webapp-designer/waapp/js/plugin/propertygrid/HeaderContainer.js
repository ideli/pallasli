Ext
		.define(
				'Pallas.extDesigner.waapp.plugin.propertygrid.HeaderContainer',
				{

					extend : 'Ext.grid.header.Container',

					nameWidth : 115,

					nameText : 'Name',
					valueText : 'Value',
					dateFormat : 'm/j/Y',
					trueText : 'true',
					falseText : 'false',

					// @private
					nameColumnCls : Ext.baseCSSPrefix + 'grid-property-name',
					nameColumnInnerCls : Ext.baseCSSPrefix
							+ 'grid-cell-inner-property-name',

					constructor : function(grid, store) {
						var me = this;

						me.grid = grid;
						me.store = store;
						me
								.callParent([ {
									isRootHeader : true,
									enableColumnResize : Ext
											.isDefined(grid.enableColumnResize) ? grid.enableColumnResize
											: me.enableColumnResize,
									enableColumnMove : Ext
											.isDefined(grid.enableColumnMove) ? grid.enableColumnMove
											: me.enableColumnMove,
									items : [
											{
												header : me.nameText,
												width : grid.nameColumnWidth
														|| me.nameWidth,
												sortable : grid.sortableColumns,
												dataIndex : grid.nameField,
												renderer : Ext.Function.bind(
														me.renderProp, me),
												itemId : grid.nameField,
												menuDisabled : true,
												tdCls : me.nameColumnCls,
												innerCls : me.nameColumnInnerCls
											},
											{
												header : me.valueText,
												renderer : Ext.Function.bind(
														me.renderCell, me),
												getEditor : Ext.Function.bind(
														me.getCellEditor, me),
												sortable : grid.sortableColumns,
												flex : 1,
												fixed : true,
												dataIndex : grid.valueField,
												itemId : grid.valueField,
												menuDisabled : true
											}, {
												header : "hiddenValue",
												flex : 1,
												fixed : true,
												hidden : true,
												dataIndex : "hiddenValue",
												itemId : "hiddenValue",
												menuDisabled : true
											}, {
												header : "order",
												flex : 1,
												fixed : true,
												hidden : true,
												dataIndex : "order",
												itemId : "order",
												menuDisabled : true
											}, {
												header : " ",
												width : 10,
												fixed : true,
												menuDisabled : true
											} ]
								} ]);
					},

					getCellEditor : function(record) {
						return this.grid.getCellEditor(record, this);
					},

					// @private
					// Render a property name cell
					renderProp : function(v) {
						return this.getPropertyName(v);
					},

					// @private
					// Render a property value cell
					renderCell : function(val, meta, rec) {
						var me = this, grid = me.grid, renderer = grid
								.getConfig(rec.get(grid.nameField), 'renderer'), result = val;

						if (renderer) {
							return renderer.apply(me, arguments);
						}
						if (Ext.isDate(val)) {
							result = me.renderDate(val);
						} else if (Ext.isBoolean(val)) {
							result = me.renderBool(val);
						}
						return Ext.util.Format.htmlEncode(result);
					},

					// @private
					renderDate : Ext.util.Format.date,

					// @private
					renderBool : function(bVal) {
						return this[bVal ? 'trueText' : 'falseText'];
					},
					getPropertyName : function(name) {
						return this.grid.getConfig(name, 'displayName', name);
					}
				});
