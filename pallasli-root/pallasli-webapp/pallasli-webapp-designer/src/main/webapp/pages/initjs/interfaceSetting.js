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
		if (getUrlParam("tableName")) {

			var grid = new Pallasli.panel.Grid({
				xtype : "grid",
				region : "west",
				width : 1200,
				idProperty : "",
				thead : [ 
					        { header: '字段英文名', dataIndex: 'fieldName', flex: 2 },
					        { header: '字段中文名', dataIndex: 'fieldCaption', flex: 2 },
					        { header: '长度', dataIndex: 'fieldLength', xtype: 'numbercolumn', format:'0,000', flex: 1 },
					        { header: '精度', dataIndex: 'fieldPrecision', xtype: 'numbercolumn', format:'0,000',flex: 1 },
					        { header: '类型', dataIndex: 'fieldType', flex: 2 },
					        { header: '允许空', dataIndex: 'fieldAllowblank', flex: 1},
					        { header: '默认值', dataIndex: 'fieldDefault', flex: 2 },
					        { header: '字段说明', dataIndex: 'COLUMN_COMMENTS', flex: 2 },
					        { header: '关联表', dataIndex: 'phone', flex: 2 },
					        { header: '关联字段', dataIndex: 'phone', flex: 2 }
					    ],
				data : [],

			});

			new Pallasli.window.ViewPort({
				items : [ grid ]
			});

			return;
		}
		var main = new Pallasli.window.IFrame({
			name : "workPage",
			xtype : "iframe"
		});

		$
				.ajax({
					url : "/pallasli-webapp-designer/fieldsetting/listtables",
					dataType : "json",
					success : function(data, success) {
						var result = {
							success : true,
							script : data
						};
						for (var i = 0; i < data.length; i++) {
							data[i].text = data[i].tableCaption;
							data[i].target = "workPage";
							data[i].leaf = true;
							data[i].url = "../engine/view.html?jsurl=../initjs/fieldSetting.js&tableName="
									+ data[i].tableName;
						}

						var header = new Pallasli.tree.Tree({
							xtype : "tree",
							region : "west",
							width : 200,
							items : [{
								text:"数据表",
								leaf:false,
								items:data
							}]
						});

						new Pallasli.window.ViewPort({
							items : [ header, main ]
						});
					},
					error : function(me, errorType, error) {
						var result = {
							success : false,
							msg : errorType + ":" + error.message,
							error : error
						};
						console.log(result);
					}
				});

	})
})();