<%@page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	Ext.onReady(function() {
		var panel = Ext.getCmp("${panelId}");
		var comboxWithTree = new Ext.form.ComboBox(
				{
					store : new Ext.data.SimpleStore({
						fields : [],
						data : [ [] ]
					}),
					editable : false,
					value : ' ',
					emptyText : '请选择...',
					fieldLabel : '所属部门',
					anchor : '100%',
					mode : 'local',
					triggerAction : 'all',
					maxHeight : 390,
					// 下拉框的显示模板,addDeptTreeDiv作为显示下拉树的容器
					tpl : "<tpl for='.'><div style='height:390px'><div id='addDeptTreeDiv'></div></div></tpl>",
					allowBlank : false,
					onSelect : Ext.emptyFn
				});

		var lockedCombo = new Ext.form.ComboBox({
			name : 'locked',
			hiddenName : 'locked',
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '0',
			fieldLabel : '角色状态',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : "99%"
		});

		var roletypeCombo = new Ext.form.ComboBox({
			name : 'roletype',
			hiddenName : 'roletype',
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			readOnly : true,
			value : '1',
			fieldLabel : '角色类型',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : "99%"
		});
		var addRoleFormPanel = new Ext.form.FormPanel({
			name : 'addRoleFormPanel',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 58,
			frame : false,
			bodyStyle : 'padding:5 5 0',
			items : [ {
				fieldLabel : '角色名称',
				name : 'rolename',
				allowBlank : false,
				anchor : '99%'
			}, comboxWithTree, roletypeCombo,
					lockedCombo, {
						fieldLabel : '备注',
						name : 'remark',
						allowBlank : true,
						anchor : '99%'
					}, {
						name : 'windowmode',
						hidden : true
					}, {
						name : 'deptid',
						hidden : true
					}, {
						name : 'deptid_old',
						hidden : true
					}, {
						name : 'roleid',
						hidden : true
					} ]
		});
		panel.add(addRoleFormPanel);
	});
</script>
