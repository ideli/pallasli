<%@page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	Ext.onReady(function() {
		var panel = Ext.getCmp("${panelId}");
		var operatorTab = Ext.create('Ext.panel.Panel', {
			title : ' 选择角色',
			xtype : 'tab',

			layout : 'fit',
			border : false,
			closable : false,
			items : [ ]

		});

		var selectUserTab = Ext.create('Ext.panel.Panel', {
			title : '选择菜单',
			xtype : 'tab',

			layout : 'fit',
			border : false,
			closable : false,
			html : 'b' 

		});
		var roleGrantTabPanel = Ext.create('Ext.tab.Panel', {
			activeTab : 0,
			width : 600,
			height : 250,
			margins : '3 3 3 3',
			closable : false,
			//region : 'center',
			//plain : true,// True表示为不渲染tab候选栏上背景容器图片
			defaults : {
				autoScroll : true
			}
		});
		roleGrantTabPanel.add(operatorTab);
		roleGrantTabPanel.add(selectUserTab);
		panel.add(roleGrantTabPanel);
	});
</script>