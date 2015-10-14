<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script language='javascript'>
	Ext.onReady(function() {

		var panel = Ext.getCmp("${panelId}");

		var form = Ext.create("Ext.form.Panel", {
			items : [ {
				fieldLabel : "项目名称",
				xtype : "textfield"
			}, {
				fieldLabel : "项目代号",
				xtype : "textfield"
			}, {
				fieldLabel : "开始日期",
				xtype : "textfield"
			}, {
				fieldLabel : "结束日期",
				xtype : "textfield"
			}, {
				fieldLabel : "团队名称",
				xtype : "textfield"
			}, {
				fieldLabel : "关联产品",
				xtype : "textfield"
			}, {
				fieldLabel : "项目目标",
				xtype : "textfield"
			}, {
				fieldLabel : "项目描述",
				xtype : "textfield"
			}, {
				fieldLabel : "访问控制",
				xtype : "textfield"
			} ],
			fbar : [ {
				text : "保存"
			}, {
				text : "重填"
			} ]
		});
		var ui = Ext.create('Ext.Panel', {
			width : 500,
			border : false,
			layout : "fit",
			items : [ form ]
		});
		panel.add(ui);
	});
</script>
