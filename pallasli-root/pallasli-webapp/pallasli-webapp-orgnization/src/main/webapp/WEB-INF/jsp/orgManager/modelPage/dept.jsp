<%@page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	Ext.onReady(function() {
		var panel = Ext.getCmp("${panelId}");
		var comboxWithTree = new Ext.form.ComboBox({
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			editable : false,
			value : ' ',
			emptyText : '请选择...',
			fieldLabel : '上级部门',
			anchor : '100%',
			mode : 'local',
			triggerAction : 'all',
			maxHeight : 390,
			// 下拉框的显示模板,addDeptTreeDiv作为显示下拉树的容器
			tpl : "<tpl for='.'><div style='height:390px'>"
					+ "<div id='addDeptTreeDiv'></div></div></tpl>",
			allowBlank : false,
			onSelect : Ext.emptyFn
		});
		var addDeptFormPanel = new Ext.form.FormPanel({
			name : 'addDeptFormPanel',
			defaultType : 'textfield',
			labelAlign : 'right',
			padding : 5,
			border : false,
			labelWidth : 70,
			frame : false,
			bodyStyle : 'padding:5 5 0',
			items : [ {
				fieldLabel : '部门名称',
				name : 'deptname',
				allowBlank : false,
				anchor : '99%'
			}, comboxWithTree, {
				fieldLabel : '业务对照码',
				name : 'customid',
				allowBlank : true,
				anchor : '99%'
			}, {
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
				name : 'deptid',
				hidden : true
			}, {
				name : 'parentid_old',
				hidden : true
			} ]
		});
		panel.add(addDeptFormPanel);
	});
</script>
