(function() {

	$ready(function() {
		function getUrlParam(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
			var r = window.location.search.substr(1).match(reg); // 匹配目标参数
			if (r != null)
				return unescape(r[2]);
			return null; // 返回参数值
		}
		var url = location.href;
		

		var grid = new Pallasli.panel.Grid({
			xtype : "grid",
			region : "west",
			width : 1200,
			idProperty : "",
			thead : [{ header: '字段英文名', dataIndex: 'fieldName', flex: 2 },
				        { header: '字段中文名', dataIndex: 'fieldCaption', flex: 2},
	        { header: '长度', dataIndex: 'fieldLength', xtype: 'numbercolumn', format:'0,000', flex: 1 },
	        { header: '精度', dataIndex: 'fieldPrecision', xtype: 'numbercolumn', format:'0,000',flex: 1 },
	        { header: '类型', dataIndex: 'fieldType', flex: 2 },
	        { header: '允许空', dataIndex: 'fieldAllowblank', flex: 1 },
	        { header: '默认值', dataIndex: 'fieldDefault', flex: 2},
	        { dataIndex: 'projectName', hidden: true }
	     ],
	     tbar:[{text:'引入字段',handler:function(){ 
				document.getElementById(main.id).src="../engine/view.html?jsurl=../initjs/pages/importTableFieldToGroup.js";

               }
             },{
   			text:'增加'
             },{
   			text:'保存'
				}],
			data : [],

		});

		var main = new Pallasli.window.IFrame({
			name : "importTable",
			xtype : "iframe"
		});
		new Pallasli.window.ViewPort({
			items : [ grid,main ]
		});

	})
})();