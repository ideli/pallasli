Ext.define('Pallas.extDesigner.utils.AllComponents', {
	"statics" : {
		"type" : "query",
		"module" : {
			"f_type" : 0,
			"f_i_documents" : {
				"classpath" : "document",
				"data" : [ "bpmTest.docAdd", "bpmTest.docSp" ]
			},
			"f_menus" : {
				"classpath" : "modulemenu",
				"data" : [ {
					"f_order" : 1,
					"f_forshortcut" : false,
					"f_i_view" : {
						"classpath" : "view",
						"data" : "bpmTest.qryBpmTest.vBpmTest"
					},
					"f_default" : false,
					"f_formenu" : true,
					"f_caption" : "流程测试",
					"f_name" : "mnuTest",
					"f_key" : "mnuTest",
					"f_class" : "modulemenu",
					"f_i_parent" : {
						"classpath" : "module",
						"data" : "bpmTest"
					}
				} ]
			},
			"f_i_querys" : {
				"classpath" : "query",
				"data" : [ "bpmTest.qryBpmTest" ]
			},
			"f_useflow" : false,
			"f_i_tables" : {
				"classpath" : "table",
				"data" : [ "bpmTest.test_bpm", "bpmTest.bm_a003" ]
			},
			"f_caption" : "流程测试",
			"f_name" : "bpmTest",
			"f_key" : "bpmTest",
			"f_class" : "module"
		},
		"query" : {
			"f_views" : {
				"classpath" : "view",
				"data" : [ {
					"f_single_select" : false,
					"f_page_size" : 0,
					"f_issummary" : false,
					"f_enable_favorite" : false,
					"f_buttons" : {
						"classpath" : "action",
						"data" : [ {
							"f_order" : 1,
							"f_default" : false,
							"f_handler" : "function(){ }",
							"f_key" : "btnAdd",
							"f_caption" : "增加",
							"f_name" : "btnAdd",
							"f_class" : "action",
							"f_i_parent" : {
								"classpath" : "view",
								"data" : "bpmTest.qryBpmTest.vBpmTest"
							}
						} ]
					},
					"f_columns" : {
						"classpath" : "column",
						"data" : [ {
							"f_type" : 3,
							"f_order" : 1,
							"f_width" : "100",
							"f_query_type" : 0,
							"f_orderable" : false,
							"f_key" : "id",
							"f_caption" : "id",
							"f_name" : "id",
							"f_class" : "column",
							"f_i_parent" : {
								"classpath" : "view",
								"data" : "bpmTest.qryBpmTest.vBpmTest"
							}
						} ]
					},
					"f_isquery" : false,
					"f_key" : "vBpmTest",
					"f_caption" : "流程仿真",
					"f_name" : "vBpmTest",
					"f_class" : "view",
					"f_i_parent" : {
						"classpath" : "query",
						"data" : "bpmTest.qryBpmTest"
					},
					"f_config" : {
						"directFn" : "BpmTestDirect.testCx"
					}
				} ]
			},
			"f_key" : "qryBpmTest",
			"f_caption" : "流程测试",
			"f_name" : "qryBpmTest",
			"f_class" : "query",
			"f_i_parent" : {
				"classpath" : "module",
				"data" : "bpmTest"
			}
		},
		"document" : {
			"f_panels" : {
				"classpath" : "panel",
				"data" : [ {
					"f_type" : 99,
					"f_order" : 1,
					"f_i_tableform" : {
						"classpath" : "tableform",
						"data" : "bpmTest.test_bpm.frmAdd"
					},
					"f_url" : "engine/panels/panel.bpmnewmutilform",
					"f_class" : "panel",
					"f_key" : "pAdd",
					"f_caption" : "增加",
					"f_i_parent" : {
						"classpath" : "document",
						"data" : "bpmTest.docAdd"
					},
					"f_name" : "pAdd",
					"f_config" : {
						"submitDirect" : "BpmTestDirect.testAdd"
					}
				} ]
			},
			"f_buttons" : {
				"classpath" : "action",
				"data" : [ {
					"f_order" : 1,
					"f_default" : false,
					"f_handler" : "function(){ }",
					"f_class" : "action",
					"f_key" : "btnAdd",
					"f_caption" : "存盘",
					"f_i_parent" : {
						"classpath" : "document",
						"data" : "bpmTest.docAdd"
					},
					"f_name" : "btnAdd"
				} ]
			},
			"f_i_table" : {
				"classpath" : "table",
				"data" : "bpmTest.test_bpm"
			},
			"f_class" : "document",
			"f_key" : "docAdd",
			"f_caption" : "增加",
			"f_i_parent" : {
				"classpath" : "module",
				"data" : "bpmTest"
			},
			"f_name" : "docAdd"
		},
		"table" : {
			"f_keyfield" : "ID",
			"f_fields" : {
				"classpath" : "field",
				"data" : [ {
					"f_order" : 1,
					"f_inputtype" : 4,
					"f_datatype_db" : 2,
					"f_allowblank" : false,
					"f_width" : 0,
					"f_height" : 0,
					"f_length" : 20,
					"f_top" : 0,
					"f_left" : 0,
					"f_datatype_java" : 2,
					"f_i_parent" : {
						"classpath" : "table",
						"data" : "bpmTest.test_bpm"
					},
					"f_caption" : "ID",
					"f_name" : "ID",
					"f_class" : "field",
					"f_key" : "ID"
				} ]
			},
			"f_forms" : {
				"classpath" : "tableform",
				"data" : [ {
					"f_columns" : 0,
					"f_width" : 0,
					"f_height" : 0,
					"f_fieldmap" : {
						"ID" : {
							"mode" : "1000"
						}
					},
					"f_i_parent" : {
						"classpath" : "table",
						"data" : "bpmTest.test_bpm"
					},
					"f_caption" : "基本信息",
					"f_name" : "frmAdd",
					"f_class" : "tableform",
					"f_key" : "frmAdd"
				} ]
			},
			"f_i_parent" : {
				"classpath" : "module",
				"data" : "bpmTest"
			},
			"f_caption" : "test_bpm",
			"f_name" : "test_bpm",
			"f_class" : "table",
			"f_key" : "test_bpm"
		},
	}
});
