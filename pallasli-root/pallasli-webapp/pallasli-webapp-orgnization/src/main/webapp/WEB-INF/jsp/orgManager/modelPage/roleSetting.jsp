<%@page contentType="text/html; charset=utf-8"%>
<script type="text/javascript">
	Ext.onReady(function() {
		var panel = Ext.getCmp("${panelId}");
		var operatorTab = new Ext.Panel({
			title : ' 经办（系统）权限授权',

		});

		var selectUserTab = new Ext.Panel({
			title : ' 选择人员',

		});

		var managerTab = new Ext.Panel({
			title : ' 授权（业务）权限授权',

		});

		var roleGrantTabPanel = new Ext.TabPanel({
			activeTab : 0,
			width : 600,
			height : 250,
			margins : '3 3 3 3',
			region : 'center',
			plain : true,// True表示为不渲染tab候选栏上背景容器图片
			defaults : {
				autoScroll : true
			}
		});

		roleGrantTabPanel.add(operatorTab);
		roleGrantTabPanel.add(managerTab);
		roleGrantTabPanel.add(selectUserTab);
		panel.add(roleGrantTabPanel);
	});
</script>