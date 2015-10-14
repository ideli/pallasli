<%@page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	Ext.onReady(function() {
		var panel = Ext.getCmp("${panelId}");
		comboxWithTree = new Ext.form.ComboBox({
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			editable : false,
			value : ' ',
			emptyText : '请选择...',
			fieldLabel : '上级菜单',
			anchor : '100%',
			mode : 'local',
			triggerAction : 'all',
			maxHeight : 390,
			// 下拉框的显示模板,addMenuTreeDiv作为显示下拉树的容器
			tpl : "<tpl for='.'><div style='height:390px'>"
					+ "<div id='addMenuTreeDiv'></div></div></tpl>",
			allowBlank : false,
			onSelect : Ext.emptyFn
		});
		// 监听下拉框的下拉展开事件
		comboxWithTree.on('expand', function() {
			// 将UI树挂到treeDiv容器
			addMenuTree.render('addMenuTreeDiv');
			// addMenuTree.root.expand(); //只是第一次下拉会加载数据
			addMenuTree.root.reload(); // 每次下拉都会加载数据

		});
		var EXPANDStore = new Ext.data.SimpleStore({
			fields : [ 'value', 'text' ],
			data : [ [ '0', '展开' ], [ '1', '闭合' ] ]
		});
		var expandedCombo = new Ext.form.ComboBox({
			name : 'expanded',
			hiddenName : 'expanded',
			store : EXPANDStore,
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '0',
			fieldLabel : '节点初始',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : "99%"
		});

		var scrollStore = new Ext.data.SimpleStore({
			fields : [ 'value', 'text' ],
			data : [ [ '0', '否' ], [ '1', '是' ] ]
		});

		var scrollCombo = new Ext.form.ComboBox({
			name : 'scrollbar',
			hiddenName : 'scrollbar',
			store : scrollStore,
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			//value : '0',
			fieldLabel : '桌面弹窗滚动条',
			emptyText : '请选择...',
			allowBlank : true,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : "99%"
		});
		addMenuFormPanel = new Ext.form.Panel({
			name : 'addMenuFormPanel',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 98,
			frame : false,
			bodyStyle : 'padding:5 5 0',
			items : [ {
				fieldLabel : '菜单名称',
				name : 'menuname',
				allowBlank : false,
				anchor : '99%'
			}, comboxWithTree, {
				fieldLabel : '请求地址',
				name : 'request',
				allowBlank : true,
				anchor : '99%'
			}, expandedCombo, {
				fieldLabel : '图标CSS类',
				name : 'iconcls',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : 'Tab导航图标',
				name : 'icon',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面图标',
				name : 'shortcut',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面弹窗宽',
				xtype : 'numberfield',
				allowDecimals : false,
				allowNegative : false,
				name : 'width',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '桌面弹窗高',
				name : 'height',
				xtype : 'numberfield',
				allowDecimals : false,
				allowNegative : false,
				allowBlank : true,
				anchor : '99%'
			}, scrollCombo, {
				fieldLabel : '排序号',
				name : 'sortno',
				allowBlank : true,
				anchor : '99%'
			}, {
				fieldLabel : '备注',
				name : 'remark',
				allowBlank : true,
				anchor : '99%'
			}, {
				name : 'parentid',
				hidden : true
			}, {
				name : 'windowmode',
				hidden : true
			}, {
				name : 'menuid',
				hidden : true
			}, {
				name : 'parentid_old',
				hidden : true
			}, {
				name : 'count',
				hidden : true
			} ]
		});
		panel.add(addMenuFormPanel);

	});
</script>