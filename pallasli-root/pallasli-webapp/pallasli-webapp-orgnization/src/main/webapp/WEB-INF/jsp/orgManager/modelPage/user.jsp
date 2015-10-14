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
			fieldLabel : '所属部门',
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
		var sexCombo = new Ext.form.ComboBox({
			name : 'sex',
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '0',
			fieldLabel : '性别',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : "99%"
		});

		var usertypeCombo = new Ext.form.ComboBox({
			name : 'usertype',
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '1',
			fieldLabel : '人员类型',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			readOnly : true,
			anchor : "99%"
		});

		var lockedCombo = new Ext.form.ComboBox({
			name : 'locked',
			store : new Ext.data.SimpleStore({
				fields : [],
				data : [ [] ]
			}),
			mode : 'local',
			triggerAction : 'all',
			valueField : 'value',
			displayField : 'text',
			value : '0',
			fieldLabel : '人员状态',
			emptyText : '请选择...',
			allowBlank : false,
			forceSelection : true,
			editable : false,
			typeAhead : true,
			anchor : "99%"
		});

		var addUserFormPanel = new Ext.form.FormPanel({
			name : 'addUserFormPanel',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 65,
			padding : 5,
			frame : false,
			border : false,
			bodyStyle : 'padding:5 5 0',
			items : [ {
				fieldLabel : '人员名称',
				name : 'username',
				allowBlank : false,
				anchor : '99%'
			}, comboxWithTree, {
				fieldLabel : '登录帐户',
				name : 'account',
				id : 'account',
				allowBlank : false,
				anchor : '99%'
			}, {
				fieldLabel : '密码',
				name : 'password',
				inputType : 'password',
				allowBlank : false,
				anchor : '99%'
			}, {
				fieldLabel : '确认密码',
				name : 'password1',
				inputType : 'password',
				allowBlank : false,
				anchor : '99%'
			}, usertypeCombo, lockedCombo, sexCombo, {
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
				name : 'userid',
				hidden : true
			}, {
				name : 'updatemode',
				hidden : true
			} ]
		});

		panel.add(addUserFormPanel);
	});
</script>
