Ext
		.define(
				'Pallas.extDesigner.waapp.plugin.grid.RowEditing',
				{
					extend : 'Ext.grid.plugin.Editing',
					alias : 'plugin.rowediting',

					requires : [ 'Ext.grid.RowEditor' ],

					lockableScope : 'top',

					editStyle : 'row',

					autoCancel : true,

					errorSummary : true,

					constructor : function() {
						var me = this;

						me.callParent(arguments);

						if (!me.clicksToMoveEditor) {
							me.clicksToMoveEditor = me.clicksToEdit;
						}

						me.autoCancel = !!me.autoCancel;
					},
					destroy : function() {
						Ext.destroy(this.editor);
						this.callParent(arguments);
					},
					startEdit : function(record, columnHeader) {
						var me = this, editor = me.getEditor(), context;

						if (editor.beforeEdit() !== false) {
							context = me.callParent(arguments);
							if (context) {
								me.context = context;

								// If editing one side of a lockable grid,
								// cancel any edit on the other side.
								if (me.lockingPartner) {
									me.lockingPartner.cancelEdit();
								}
								editor.startEdit(context.record,
										context.column, context);
								return true;
							}
						}
						return false;
					},

					// @private
					cancelEdit : function() {
						var me = this;

						if (me.editing) {
							me.getEditor().cancelEdit();
							me.callParent(arguments);
							return;
						}
						// If we aren't editing, return true to allow the event
						// to bubble
						return true;
					},

					// @private
					completeEdit : function() {
						var me = this;

						if (me.editing && me.validateEdit()) {
							me.editing = false;
							me.fireEvent('edit', me, me.context);
						}
					},

					// @private
					validateEdit : function() {
						var me = this, editor = me.editor, context = me.context, record = context.record, newValues = {}, originalValues = {}, editors = editor
								.query('>[isFormField]'), e, eLen = editors.length, name, item;

						for (e = 0; e < eLen; e++) {
							item = editors[e];
							name = item.name;

							newValues[name] = item.getValue();
							originalValues[name] = record.get(name);
						}

						Ext.apply(context, {
							newValues : newValues,
							originalValues : originalValues
						});

						return me.callParent(arguments)
								&& me.getEditor().completeEdit();
					},

					// @private
					getEditor : function() {
						var me = this;

						if (!me.editor) {
							me.editor = me.initEditor();
						}
						return me.editor;
					},

					// @private
					initEditor : function() {
						return new Ext.grid.RowEditor(Ext.apply(this
								.initEditorConfig(), {
							'saveBtnText' : "确定",
							'cancelBtnText' : "取消"
						}));
					},

					initEditorConfig : function() {
						var me = this, grid = me.grid, view = me.view, headerCt = grid.headerCt, btns = [
								'saveBtnText', 'cancelBtnText', 'errorsText',
								'dirtyText' ], b, bLen = btns.length, cfg = {
							autoCancel : me.autoCancel,
							errorSummary : me.errorSummary,
							fields : headerCt.getGridColumns(),
							hidden : true,
							view : view,
							// keep a reference..
							editingPlugin : me
						}, item;

						for (b = 0; b < bLen; b++) {
							item = btns[b];

							if (Ext.isDefined(me[item])) {
								cfg[item] = me[item];
							}
						}
						return cfg;
					},

					// @private
					initEditTriggers : function() {
						var me = this, view = me.view, moveEditorEvent = me.clicksToMoveEditor === 1 ? 'click'
								: 'dblclick';

						me.callParent(arguments);

						if (me.clicksToMoveEditor !== me.clicksToEdit) {
							me.mon(view, 'cell' + moveEditorEvent,
									me.moveEditorByClick, me);
						}

						view.on({
							render : function() {
								me.mon(me.grid.headerCt, {
									scope : me,
									columnresize : me.onColumnResize,
									columnhide : me.onColumnHide,
									columnshow : me.onColumnShow
								});
							},
							single : true
						});
					},

					startEditByClick : function() {
						var me = this;
						if (!me.editing
								|| me.clicksToMoveEditor === me.clicksToEdit) {
							me.callParent(arguments);
						}
					},

					moveEditorByClick : function() {
						var me = this;
						if (me.editing) {
							me.superclass.onCellClick.apply(me, arguments);
						}
					},

					// @private
					onColumnAdd : function(ct, column) {
						if (column.isHeader) {
							var me = this, editor;

							me.initFieldAccessors(column);

							// Only inform the editor about a new column if the
							// editor has already been instantiated,
							// so do not use getEditor which instantiates the
							// editor if not present.
							editor = me.editor;
							if (editor && editor.onColumnAdd) {
								editor.onColumnAdd(column);
							}
						}
					},

					// @private
					onColumnRemove : function(ct, column) {
						if (column.isHeader) {
							var me = this, editor = me.getEditor();

							if (editor && editor.onColumnRemove) {
								editor.onColumnRemove(ct, column);
							}
							me.removeFieldAccessors(column);
						}
					},

					// @private
					onColumnResize : function(ct, column, width) {
						if (column.isHeader) {
							var me = this, editor = me.getEditor();

							if (editor && editor.onColumnResize) {
								editor.onColumnResize(column, width);
							}
						}
					},

					// @private
					onColumnHide : function(ct, column) {
						// no isHeader check here since its already a columnhide
						// event.
						var me = this, editor = me.getEditor();

						if (editor && editor.onColumnHide) {
							editor.onColumnHide(column);
						}
					},

					// @private
					onColumnShow : function(ct, column) {
						// no isHeader check here since its already a columnshow
						// event.
						var me = this, editor = me.getEditor();

						if (editor && editor.onColumnShow) {
							editor.onColumnShow(column);
						}
					},

					// @private
					onColumnMove : function(ct, column, fromIdx, toIdx) {
						// no isHeader check here since its already a columnmove
						// event.
						var me = this, editor = me.getEditor();

						// Inject field accessors on move because if the move
						// FROM the main headerCt and INTO a grouped header,
						// the accessors will have been deleted but not added.
						// They are added conditionally.
						me.initFieldAccessors(column);

						if (editor && editor.onColumnMove) {
							// Must adjust the toIdx to account for removal if
							// moving rightwards
							// because RowEditor.onColumnMove just calls
							// Container.move which does not do this.
							editor.onColumnMove(column, fromIdx, toIdx);
						}
					},

					// @private
					setColumnField : function(column, field) {
						var me = this, editor = me.getEditor();

						editor.removeField(column);
						me.callParent(arguments);
						me.getEditor().setField(column);
					}
				});
