/**
 * 
 * 注意引入special.css
 */
Ext.ux.TreeWindow = function(cfg) {
	Ext.apply(this, {

		win : null,
		// 根节点文本
		rootText : '',
		// 备选项链接
		dataUrl : null,
		// 链接附加参数
		baseParams : {},
		// 标题
		title : '',
		// 是否单选,单选可以选任何节点,多选只能选叶子
		single : false,
		// 是否展开所有
		expandAll : false,
		// 是否级联
		relation : false,
		/**
		 * 
		 * 单选模式nodes为一条记录,多选模式nodes为数组
		 * 
		 * @param {}
		 *            nodes
		 */
		onSubmit : function(nodes) {
		},

		show : function(cfg) {

			var self = this;

			var actTreeExp = new Ext.Action({

						iconCls : 'x-treeexp-icon',
						text : '展开',
						// hidden : self.expandAll,
						handler : function() {
							tree.getRootNode().expand(true);
							actTreeCol.setHidden(false)
							actTreeExp.setHidden(true);
						}
					})

			var actTreeCol = new Ext.Action({
						iconCls : 'x-treecol-icon',
						text : '折叠',
						// hidden : !this.expandAll,
						hidden : true,
						handler : function() {
							tree.getRootNode().collapse(true);
							actTreeExp.setHidden(false)
							actTreeCol.setHidden(true);
						}
					})
			var actOpenRel = new Ext.Action({
						iconCls : 'x-relationOpen-icon',
						text : '级联',
						hidden : this.relation,
						handler : function() {
							self.relation = true;
							actCloseRel.setHidden(false)
							actOpenRel.setHidden(true);
						}
					})
			var actCloseRel = new Ext.Action({
						iconCls : 'x-relationCancel-icon',
						text : '取消',
						hidden : !this.relation,
						handler : function() {
							self.relation = false;
							actOpenRel.setHidden(false)
							actCloseRel.setHidden(true);
						}
					})
			var tree = new Ext.tree.TreePanel({
						region : 'center',
						// useArrows : true,
						autoScroll : true,
						animate : true,
						// enableDD : true,
						containerScroll : true,
						rootVisible : true,
						// frame : true,
						root : new Ext.tree.AsyncTreeNode({
									expanded : true,
									text : self.rootText,
									id : '-1',
									loader : new Ext.tree.TreeLoader({
												dataUrl : self.dataUrl,
												baseParams : self.baseParams
											})
								}),

						bbar : [actTreeExp, actTreeCol, actOpenRel, actCloseRel],
						buttons : [{
							text : '确定',
							handler : function() {
								var nodes = self.single
										? tree.getSelectionModel()
												.getSelectedNode()
										: tree.getChecked();
								self.fireSubmit(nodes);
								self.win.close();
							}
						}, {
							text : '取消',
							handler : function() {
								self.win.close();
							}
						}],
						buttonAlign : 'center'
					});
			tree.on('checkchange', function(node, checked) {
						if (self.relation) {
							// check级联
							node.cascade(function(me) {
										/*
										 * if (!me.hasChildNodes) { return; }
										 */
										// 有checked属性
										if (me.attributes.checked === true
												|| me.attributes.checked === false) {
											var checkbox = me.getUI().checkbox;
											checkbox.checked = checked;
											me.attributes.checked = checked;
											if (checked) {
												me.getUI().addClass('treenode-check');
											} else {
												me.getUI().removeClass('treenode-check');
											}
											me.getOwnerTree().fireEvent('check', me, checked);
										}
									})
						} else {
							if (checked) {
								node.getUI().addClass('treenode-check');
							} else {
								node.getUI().removeClass('treenode-check');
							}
						}
					})
			tree.on('expandnode', function(node) {
						// expand级联
						if (self.relation) {
							node.expand(true);
						}
					})
			var _cfg = {
				layout : 'border',
				width : 600,
				height : 390,
				border : true,
				bodyBorder : true,
				closeAction : 'hide',
				maximizable : true,
				modal : 'true',
				items : [tree]
			};

			this.win = new Ext.Window(_cfg);
			tree.getRootNode().expand(true);
			this.win.show();

			this.win.setTitle(self.title, 'x-search-icon');

		},

		fireSubmit : function(nodes) {
			if (this.onSubmit) {
				this.onSubmit(nodes);
			}
		}
	});
	Ext.apply(this, cfg);

}
