Ext
		.define(
				'Pallas.extDesigner.waapp.utils.Classes',
				{
					"statics" : {
						datas : {
							action : {
								name : 'action',
								text : '操作按钮',
								note : '定义操作按钮',
								iconCls : 'icon-designer-action',
								orderable : true,
								properties : [ {
									name : 'f_icon',
									text : '图标',
									xeditor : 'string',
									note : '按钮的图标类名。'
								}, {
									name : 'f_handler',
									text : '操作函数',
									xeditor : 'textbox',
									note : '操作的客户端执行函数脚本。'
								}, {
									name : 'f_default',
									text : '默认操作',
									xeditor : 'boolean',
									note : '是否默认操作，在视图双击时指定执行的操作。'
								} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 70,
										header : '按钮名'
									},
									'f_caption' : {
										width : 70,
										header : '中文名'
									},
									'f_icon' : {
										width : 70
									},
									'f_default' : {
										width : 50
									},
									'f_handler' : {
										width : 200
									},
									'f_config' : {
										width : 110
									},
									'f_note' : {
										width : 150
									}
								}
							},
							authoritymap : {
								name : 'authorithmap',
								text : '文档权限映射',
								note : '',
								iconCls : 'icon-designer-authorithmap',
								properties : [ {
									name : 'f_state',
									text : '状态名',
									xeditor : 'string',
									note : '权限列表的状态。'
								}, {
									name : 'f_identity',
									text : '身份名',
									xeditor : 'string',
									note : '权限列表的身份。'
								}, {
									name : 'f_substate',
									text : '子状态',
									xeditor : 'string',
									note : '权限列表的子状态，用于对状态进行细分。'
								}, {
									name : 'f_authorities',
									text : '权限列表',
									xeditor : 'jsonobject',
									note : '记录文档中所有Key的权限列表。'
								} ]
							},
							column : {
								name : 'column',
								text : '视图列',
								note : '定义视图显示列',
								iconCls : 'icon-designer-column',
								orderable : true,
								properties : [
										{
											name : 'f_type',
											text : '列类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, 'none' ],
														[ 1, 'string' ],
														[ 2, 'dictionary' ],
														[ 3, 'integer' ],
														[ 4, 'float' ],
														[ 5, 'date' ],
														[ 6, 'datetime' ],
														[ 7, 'jsonobject' ],
														[ 8, 'icon' ],
														[ 9, 'file' ],
														[ 99, 'custom' ] ],
												readOnly : true
											},
											note : '显示字段对应的显示类型。'
										},
										{
											name : 'f_asname',
											text : '列别名',
											xeditor : 'string',
											note : '显示字段对应的显示类型。'
										},
										{
											name : 'f_query_type',
											text : '查询类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, 'none' ],
														[ 1, 'string' ],
														[ 2, 'number' ],
														[ 3, 'date' ],
														[ 4, 'dictionary' ],
														[ 5, 'checkbox' ],
														[ 99, 'custom' ] ],
												readOnly : true
											},
											note : '定义字段的查询类型。'
										},
										{
											name : 'f_summary_type',
											text : '汇总类型',
											xeditor : 'select',
											xconfig : {
												datas : [ 'none', 'sum',
														'count', 'max', 'min',
														'average' ],
												readOnly : true
											},
											note : '定义字段的汇总类型。'
										}, {
											name : 'f_orderable',
											text : '排序',
											xeditor : 'boolean',
											note : '定义字段是否支持排序。'
										}, {
											name : 'f_width',
											text : '列宽',
											xeditor : 'string',
											note : '定义字段的列宽度。'
										} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 100,
										header : '列名'
									},
									'f_asname' : {
										width : 100,
										header : '别名'
									},
									'f_caption' : {
										width : 100,
										header : '标题'
									},
									'f_type' : {
										width : 80
									},
									'f_query_type' : {
										width : 70
									},
									'f_summary_type' : {
										width : 70
									},
									'f_orderable' : {
										width : 50
									},
									'f_width' : {
										width : 50
									},
									'f_config' : {
										width : 150
									},
									'f_note' : {
										width : 150
									}
								}
							},
							dictionary : {
								name : 'dictionary',
								text : '字典数据',
								note : '系统定义字典数据',
								iconCls : 'icon-designer-dictionary',
								properties : [ {
									name : 'id',
									text : 'ID',
									xeditor : 'none',
									note : '字典ID，数据库中的唯一标识。'
								}, {
									name : 'rowstate',
									text : 'rowstate',
									xeditor : 'none',
									note : '编辑状态。'
								}, {
									name : 'f_name',
									text : '数据标识',
									xeditor : 'string',
									xconfig : {
										config : {
											maxLength : 50,
											allowBlank : false
										}
									},
									note : '数据标识。'
								}, {
									name : 'f_caption',
									text : '数据名称',
									xeditor : 'string',
									xconfig : {
										config : {
											maxLength : 50,
											allowBlank : false
										}
									},
									note : '字典数据中文名称。'
								}, {
									name : 'f_note',
									text : '备注',
									xeditor : 'textbox',
									note : '说明信息。'
								}, {
									name : 'type',
									note : '类型、角色或用户。'
								}, {
									name : 'f_order',
									note : '排序、自动维护。'
								} ],
								propertyColumns : {
									'f_name' : {
										width : 100,
										renderer : function(value, metaData,
												record) {
											var type = record.get("type");
											return Ext.String
													.format(
															"<div style='height:16px;padding-left:23px;background:transparent url(extDesigner/waapp/icon/designer/{0}.gif) no-repeat'> {1}</div>",
															type, value);
										}
									},
									'f_caption' : {
										width : 120
									},
									'f_note' : {
										width : 150
									}
								}
							},
							dictionaryfolder : {
								isSingle : true,
								name : 'dictionaryfolder',
								text : '字典管理',
								note : '管理系统字典数据定义',
								iconCls : 'icon-designer-dictionaryfolder',
								editors : [ 'designer/dictionary/dictionary.ui.do' ]
							},
							document : {
								name : 'document',
								text : '文档',
								note : '',
								iconCls : 'icon-designer-document',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'panel', 'action', 'state',
										'substate', 'identity', 'authoritymap' ],
								properties : [
										{
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '文档图标，指定文档的图标名称。'
										},
										{
											name : 'f_i_table',
											text : '数据表',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'table',
												selectInParent : true
											},
											note : '选择文档对应的数据表定义。'
										},
										{
											name : 'f_i_flow',
											text : '应用流程',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'flow',
												selectInParent : true
											},
											note : '选择文档的使用流程。'
										} ],
								editors : [
										'designer/designobject/properties.do',
										'designer/designobject/objectgrideditor.do?type=action',
										'designer/designobject/objectgrideditor.do?type=state',
										'designer/designobject/objectgrideditor.do?type=substate',
										'designer/designobject/objectgrideditor.do?type=identity',
										'designer/designobject/objectgrideditor.do?type=panel',
										'designer/document/document.authoritymap.do' ]
							},
							documentfolder : {
								name : 'documentfolder',
								text : '文档容器',
								note : '模块文档容器节点',
								iconCls : 'icon-designer-documentfolder',
								subModules : [ 'document' ]
							},
							documenttype : {
								name : 'documenttype',
								text : '文档类型',
								note : '',
								iconCls : 'icon-designer-documenttype',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'action' ],
								properties : [
										{
											name : 'f_type',
											text : '注册类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 1, '文档对象' ],
														[ 2, 'URL' ],
														[ 0, '自定义' ] ],
												readOnly : true
											},
											note : '文档注册类型，1：文档对象；2：URL；0：自定义'
										},
										{
											name : 'f_param',
											text : '注册参数',
											xeditor : 'string',
											note : '定义注册类型的相关参数（如：文档Key）。'
										},
										{
											name : 'f_title_column',
											text : '视图标题列名',
											xeditor : 'string',
											note : '定义文档类型对应的视图标题列名称，用于维护文档类型的收藏夹。'
										},
										{
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '定义文档类型的图标。'
										},
										{
											name : 'f_handler',
											text : '操作函数',
											xeditor : 'textbox',
											note : 'JS函数function(id)类型为自定义时使用该函数。'
										} ],
								editors : [
										'designer/designobject/properties.do',
										'designer/designobject/objectgrideditor.do?type=action' ]
							},
							documenttypefolder : {
								name : 'documenttypefolder',
								text : '文档类型注册容器',
								note : '模块文档类型注册容器节点',
								iconCls : 'icon-designer-documenttypefolder',
								subModules : [ 'documenttype' ]
							},
							field : {
								name : 'field',
								text : '字段',
								note : '数据表字段定义',
								iconCls : 'icon-designer-field',
								orderable : true,
								extendsMenu : [
										{
											text : '引入字段',
											iconCls : 'icon-designer-importfield',
											handler : function() {
												var grid = this.items.get(0);
												var key = grid.getParentKey();
												var type = grid.getType();
												DesignObjectDirect
														.buildTableField(
																key,
																type,
																function(
																		result,
																		e) {
																	grid
																			.getStore()
																			.reload();
																});
											}
										},
										'-',
										{
											text : '生成类代码',
											iconCls : 'icon-designer-class',
											handler : function() {
												var grid = this.items.get(0);
												var key = grid.getParentKey();
												Ext.Msg
														.prompt(
																'生成类代码[' + key
																		+ ']',
																'请输入类名:',
																function(btn,
																		text) {
																	if (btn == 'ok') {
																		var win = new Ext.Window(
																				{
																					width : 500,
																					height : 500,
																					modal : true,
																					layout : 'fit',
																					iconCls : 'icon-designer-class',
																					title : '['
																							+ key
																							+ '] 类脚本',
																					items : new Ext.Panel(
																							{
																								autoScroll : true,
																								autoLoad : {
																									url : 'designer/table/table.class.do?key='
																											+ key
																											+ '&classname='
																											+ text
																								}
																							})
																				});
																		win
																				.show();
																	}
																});

											}
										},
										'-',
										{
											text : '在数据库中创建',
											iconCls : 'icon-designtool-createtable',
											handler : function() {
												var grid = this.items.get(0);
												var key = grid.getParentKey();
												DesignObjectDirect
														.buildDBTableField(
																key,
																function(
																		result,
																		e) {
																	if (result.success) {
																		Ext.MessageBox
																				.alert(
																						"提示信息",
																						"创建表成功！");
																	} else {
																		Ext.MessageBox
																				.alert(
																						"提示信息",
																						"数据库中已存在该表！");
																	}
																});
												// alert(this.items.get(0).getXType());
											}
										} ],
								properties : [
										{
											name : 'f_datatype_db',
											text : 'DB类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 1, 'char' ],
														[ 2, 'long' ],
														[ 3, 'int' ],
														[ 4, 'datetime' ],
														[ 5, 'float' ],
														[ 6, 'clob' ],
														[ 7, 'blob' ],
														[ 0, 'none' ] ],
												readOnly : true
											},
											note : '字段对应数据库类型，none为无对应字段。'
										},
										{
											name : 'f_length',
											text : '长度',
											xeditor : 'number',
											note : '字段最大长度，0为不限制。'
										},
										{
											name : 'f_datatype_java',
											text : 'Java类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 1, 'String' ],
														[ 2, 'long' ],
														[ 3, 'int' ],
														[ 4, 'date' ],
														[ 5, 'float' ],
														[ 6, 'blob' ],
														[ 0, 'none' ] ],
												readOnly : true
											},
											note : '字段对应JAVA数据类型，none为无对应字段。'
										},
										{
											name : 'f_inputtype',
											text : '录入方式',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, 'none' ],
														[ 1, 'text' ],
														[ 2, 'textarea' ],
														[ 3, 'boolean' ],
														[ 4, 'number' ],
														[ 5, 'textbox' ],
														[ 6, 'date' ],
														[ 7, 'time' ],
														[ 8, 'datetime' ],
														[ 9, 'combox' ],
														[ 10, 'checkbox' ],
														[ 11, 'radiobox' ],
														[ 12, 'html' ],
														[ 13, 'file' ],
														[ 14, 'deptuser' ],
														[ 15, 'display' ],
														[ 16, 'organization' ],
														[ 17, 'opinion' ],
														[ 18, 'handsign' ],
														[ 19, 'comboview' ],
														[ 20, 'image' ],
														[ 21, 'grid' ],
														[ 99, 'custom' ] ],
												readOnly : true
											},
											note : '指定数据采集时的录入控件。'
										}, {
											name : 'f_allowblank',
											text : '允许为空',
											xeditor : 'boolean',
											note : '指定数据采集是否允许为空。'
										}, {
											name : 'f_default_value',
											text : '默认值',
											xeditor : 'string',
											note : '设置字段默认值。'
										} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 110,
										header : '字段名'
									},
									'f_caption' : {
										width : 110,
										header : '中文名'
									},
									'f_datatype_db' : {
										width : 70
									},
									'f_length' : {
										width : 50
									},
									'f_datatype_java' : {
										width : 70
									},
									'f_inputtype' : {
										width : 70
									},
									'f_allowblank' : {
										width : 70
									},
									'f_default_value' : {
										width : 80
									},
									'f_config' : {
										width : 150
									},
									'f_note' : {
										width : 150
									}
								}
							},
							group : {
								name : 'group',
								text : '分组',
								note : '',
								iconCls : 'icon-designer-group',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'group' ],
								properties : [
										{
											name : 'f_type',
											text : '类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, 'none' ],
														[ 1, '静态分组' ],
														[ 2, '字段分组' ],
														[ 3, '下级单位' ],
														[ 4, '下级用户' ],
														[ 5, '组织结构' ],
														[ 6, '组织结构用户' ],
														[ 7, '数据字典' ],
														[ 99, '自定义' ] ],
												readOnly : true
											},
											note : '指定查询分组类型。'
										},
										{
											name : 'f_field',
											text : '分组显示字段',
											xeditor : 'string',
											note : '定义分组显示字段。'
										},
										{
											name : 'f_valuefield',
											text : '分组值字段',
											xeditor : 'string',
											note : '定义分组值字段，如果值字段为空，则以显示字段作为参数值。'
										},
										{
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '指定分组项显示图标。'
										},
										{
											name : 'f_url',
											text : '解析类路径',
											xeditor : 'string',
											note : '指定分组的服务器端解析路径，类型为【自定义】时起作用。'
										} ],
								editors : [
										'designer/designobject/properties.do',
										'designer/designobject/objectgrideditor.do?type=action' ]
							},
							identity : {
								name : 'identity',
								text : '文档身份',
								note : '定义操作者的文档身份，用于控制文档权限。',
								iconCls : 'icon-designer-identity',
								extendsMenu : [ {
									text : '引入流程身份',
									iconCls : 'icon-designer-importflowidentitys',
									handler : function() {
										var grid = this.items.get(0);
										var key = grid.getParentKey();
										DesignObjectDirect
												.importFlowIdentities(key,
														function(result, e) {
															grid.getStore()
																	.reload();
														});
									}
								} ],
								properties : [
										{
											name : 'f_judge_source',
											text : '判定源',
											xeditor : 'select',
											xconfig : {
												datas : [ '|fieldname|',
														'userexpressions' ],
												readOnly : false
											},
											note : '指定身份的判定源。'
										},
										{
											name : 'f_judge_source_type',
											text : '类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, '当前用户' ],
														[ 1, '用户ID' ],
														[ 2, '用户名' ],
														[ 3, '登录名' ],
														[ 4, '部门ID' ],
														[ 5, '部门名' ],
														[ 6, '角色名' ],
														[ 7, '用户表达式' ],
														[ 8, '模块角色' ],
														[ 99, '自定义' ] ],
												readOnly : true
											},
											note : '指定身份判定条件的源类型。'
										},
										{
											name : 'f_operator',
											text : '判定操作',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [
														[ 0, 'MemberOf' ],
														[ 1, 'IncludeMember' ],
														[ 2, 'LeaderOf' ],
														[ 3, 'DirectLeaderOf' ],
														[ 4, 'UnderlingOf' ],
														[ 5,
																'DirectUnderlingOf' ],
														[ 6, 'SameDeptmentOf' ],
														[ 99, 'Custom' ] ],
												readOnly : true
											},
											note : '字段对应JAVA数据类型。'
										},
										{
											name : 'f_judge_target',
											text : '判定目标',
											xeditor : 'select',
											xconfig : {
												datas : [ '|fieldname|',
														'userexpressions' ],
												readOnly : false
											},
											note : '指定身份的判定目标。'
										},
										{
											name : 'f_judge_target_type',
											text : '类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, '当前用户' ],
														[ 1, '用户ID' ],
														[ 2, '用户名' ],
														[ 3, '登录名' ],
														[ 4, '部门ID' ],
														[ 5, '部门名' ],
														[ 6, '角色名' ],
														[ 7, '用户表达式' ],
														[ 8, '模块角色' ],
														[ 99, '自定义' ] ],
												readOnly : true
											},
											note : '指定身份判定条件的目标类型。'
										} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 70,
										header : '身份名'
									},
									'f_caption' : {
										width : 70,
										header : '中文名'
									},
									'f_judge_source' : {
										width : 120
									},
									'f_judge_source_type' : {
										width : 70
									},
									'f_operator' : {
										width : 100
									},
									'f_judge_target' : {
										width : 120
									},
									'f_judge_target_type' : {
										width : 70
									},
									'f_config' : {
										width : 150
									},
									'f_note' : {
										width : 150
									}
								}
							},
							module : {
								name : 'module',
								text : '应用模块',
								note : '',
								iconCls : 'icon-designer-module',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'table', 'query', 'panel',
										'document' ],
								editors : [ 'designer/designobject/properties.do' ],
								properties : [
										{
											name : 'f_type',
											text : '类型',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, '基础模块' ],
														[ 1, '标准模块' ],
														[ 2, '定制模块' ] ],
												readOnly : true
											},
											note : '模块类型，分为基础模块、标准模块和定制模块。'
										}, {
											name : 'f_url',
											text : '模块路径',
											xeditor : 'string',
											note : '模块路径，自定义模块的解析文件。'
										}, {
											name : 'f_version',
											text : '版本',
											xeditor : 'string',
											note : '模块版本，标记模块的版本号。'
										}, {
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '模块图标，指定模块的图标名称。'
										}, {
											name : 'f_useflow',
											text : '使用流程',
											xeditor : 'boolean',
											note : '表明模块是否使用流程。'
										} ]
							},
							moduleauthorityfolder : {
								name : 'moduleauthorityfolder',
								text : '权限配置',
								note : '模块设计对象权限配置定义',
								iconCls : 'icon-designer-moduleauthorityfolder',
								editors : [
										'designer/module/moduleauthority.do', ]
							},
							modulefolder : {
								name : 'modulefolder',
								text : '模块容器',
								note : '模块容器节点',
								iconCls : 'icon-designer-modulefolder',
								subModules : [ 'module' ]
							},
							modulemenu : {
								name : 'modulemenu',
								text : '模块菜单',
								note : '模块菜单定义',
								iconCls : 'icon-designer-modulemenu',
								orderable : true,
								properties : [
										{
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '模块菜单图标。'
										},
										{
											name : 'f_i_view',
											text : '相关视图',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'view',
												selectInParent : true
											},
											note : '定义模块菜单的相关视图。'
										},
										{
											name : 'f_i_group',
											text : '分组',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'group',
												selectInParent : true
											},
											note : '定义模块菜单的视图分组。'
										}, {
											name : 'f_subject_url',
											text : '桌面栏目路径',
											xeditor : 'string',
											note : '作为桌面栏目的打开路径。'
										}, {
											name : 'f_handler',
											text : '操作函数',
											xeditor : 'textbox',
											note : '操作的客户端执行函数脚本。'
										}, {
											name : 'f_default',
											text : '默认操作',
											xeditor : 'boolean',
											note : '是否默认操作，在视图双击时指定执行的操作。'
										}, {
											name : 'f_formenu',
											text : '作为菜单',
											xeditor : 'boolean',
											note : '是否作为界面或分组菜单显示。'
										}, {
											name : 'f_forshortcut',
											text : '作为快捷键',
											xeditor : 'boolean',
											note : '是否作为快捷键显示。'
										} ],
								propertyColumns : {
									'f_key' : {
										width : 100
									},
									'f_name' : {
										width : 100
									},
									'f_caption' : {
										width : 100
									},
									'f_icon' : {
										width : 50
									},
									'f_i_view' : {
										width : 150
									},
									'f_i_group' : {
										width : 90
									},
									'f_subject_url' : {
										width : 85
									},
									'f_handler' : {
										width : 100
									},
									'f_default' : {
										width : 60
									},
									'f_formenu' : {
										width : 60
									},
									'f_forshortcut' : {
										width : 70
									},
									'f_config' : {
										width : 110
									},
									'f_note' : {
										width : 150
									}
								},
								editors : [
										'designer/designobject/objectgrideditor.do?type=modulemenu', ]
							},
							modulemenufolder : {
								name : 'modulemenufolder',
								text : '模块菜单',
								note : '模块菜单容器节点',
								iconCls : 'icon-designer-modulemenufolder',
								subModules : [ 'modulemenu' ],
								editors : [
										'designer/designobject/objectgrideditor.do?type=modulemenu', ]
							},
							modulerole : {
								name : 'modulerole',
								text : '模块角色',
								note : '',
								iconCls : 'icon-designer-modulerole',
								jsonable : true,
								copyable : true,
								deletable : true,
								properties : [],
								editors : [ 'designer/designobject/properties.do' ]
							},
							moduleroleauthfolder : {
								isSingle : true,
								name : 'moduleroleauthfolder',
								text : '应用权限',
								note : '管理应用模块角色权限定义',
								iconCls : 'icon-designer-moduleroleauthfolder',
								editors : [ 'designer/module/moduleroleauth.list.do' ]
							},
							modulerolefolder : {
								name : 'modulerolefolder',
								text : '角色定义',
								note : '模块角色定义',
								iconCls : 'icon-designer-modulerolefolder',
								subModules : [ 'modulerole' ]
							},
							panel : {
								name : 'panel',
								text : '标签界面',
								note : '',
								iconCls : 'icon-designer-panel',
								jsonable : true,
								copyable : true,
								deletable : false,
								orderable : true,
								properties : [
										{
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '标签窗口的图标。'
										},
										{
											name : 'f_type',
											text : '类型',
											xeditor : 'selectkeymap',
											xconfig : {
												// datas:[[0,'普通表单'],[1,
												// '属性表单'],[2,'只读表格'],[3,'行编辑表格'],[4,'明细属性编辑表格'],[5,'背景图表单'],[6,'公文正文表单'],[7,'红头文件表单'],[8,'流程跟踪'],[9,'RTF文本编辑表单'],[99,'自定义表单']],
												datas : [ [ 0, '普通表单' ],
														[ 10, '流程表单' ],
														[ 2, '只读表格' ],
														[ 3, '行编辑表格' ],
														[ 5, '背景图表单' ],
														[ 6, '公文正文表单' ],
														[ 7, '红头文件表单' ],
														[ 9, 'RTF文本编辑表单' ],
														[ 99, '自定义表单' ] ],
												readOnly : true
											},
											note : '标签界面的类型。'
										},
										{
											name : 'f_url',
											text : '标签路径',
											xeditor : 'string',
											note : '标签的服务器端解析路径。'
										},
										{
											name : 'f_i_tableform',
											text : '相关表单',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'tableform',
												selectInParent : true
											},
											note : '定义路由的目标节点。'
										},
										{
											name : 'f_i_view',
											text : '相关视图',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'view',
												selectInParent : true
											},
											note : '定义路由的目标节点。'
										}, {
											name : 'f_custom_script',
											text : '附加JS脚本',
											xeditor : 'textbox',
											note : '编辑页面生成后附加输出的自定义JS脚本。'
										} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 110
									},
									'f_caption' : {
										width : 110
									},
									'f_icon' : {
										width : 70
									},
									'f_type' : {
										width : 80
									},
									'f_url' : {
										width : 120
									},
									'f_i_tableform' : {
										width : 80
									},
									'f_i_view' : {
										width : 80
									},
									'f_custom_script' : {
										width : 120
									},
									'f_config' : {
										width : 120
									},
									'f_note' : {
										width : 150
									}
								},
								editors : [
										'designer/designobject/properties.do',
										'designer/designobject/objectgrideditor.do?type=action' ]
							},
							panelfolder : {
								name : 'panelfolder',
								text : '标签界面容器',
								note : '模块标签界面容器节点',
								iconCls : 'icon-designer-panelfolder',
								subModules : [ 'panel' ]
							},
							portlet : {
								name : 'portlet',
								text : '桌面栏目',
								note : '',
								iconCls : 'icon-designer-portlet',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'action' ],
								properties : [ {
									name : 'f_url',
									text : 'URL',
									xeditor : 'string',
									note : '桌面栏目URL，如不填写则默认为视图栏目。'
								}, {
									name : 'f_viewkey',
									text : '视图KEY',
									xeditor : 'string',
									note : '定义视图桌面栏目对应的视图KEY。'
								}, {
									name : 'f_icon',
									text : '图标',
									xeditor : 'string',
									note : '定义文档类型的图标。'
								}, {
									name : 'f_refresh_interval',
									text : '刷新间隔',
									xeditor : 'number',
									note : '桌面栏目的自动刷新间隔秒数，0为不自动刷新。'
								} ],
								editors : [ 'designer/designobject//properties.do' ]
							},
							portletfolder : {
								name : 'portletfolder',
								text : '桌面栏目',
								note : '桌面栏目容器节点',
								iconCls : 'icon-designer-portletfolder',
								subModules : [ 'portlet' ]
							},
							query : {
								name : 'query',
								text : '查询',
								note : '',
								iconCls : 'icon-designer-query',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'view', 'group' ],
								properties : [ {
									name : 'f_from',
									text : 'From语句',
									xeditor : 'string',
									note : '定义查询检索依据中的From子句。'
								}, {
									name : 'f_where',
									text : 'Where语句',
									xeditor : 'string',
									note : '定义查询检索依据中的Where子句。'
								}, {
									name : 'f_groupby',
									text : 'GroupBy语句',
									xeditor : 'string',
									note : '定义查询检索依据中的Group By子句。'
								}, {
									name : 'f_orderby',
									text : 'OrderBy语句',
									xeditor : 'string',
									note : '定义查询检索依据中的Order By子句。'
								} ],
								extendsMenu : [ {
									text : '查看SQL',
									iconCls : 'icon-designtool-viewsql'
								} ],
								editors : [ 'designer/designobject/properties.do' ]
							},
							queryfolder : {
								name : 'queryfolder',
								text : '查询定义容器',
								note : '模块查询定义容器节点',
								iconCls : 'icon-designer-queryfolder',
								subModules : [ 'query' ]
							},
							state : {
								name : 'state',
								text : '文档状态',
								note : '定义文档状态',
								iconCls : 'icon-designer-state',
								extendsMenu : [ {
									text : '引入流程节点',
									iconCls : 'icon-designer-importnodes',
									handler : function() {
										var grid = this.items.get(0);
										var key = grid.getParentKey();
										DesignObjectDirect.importFlowStates(
												key, function(result, e) {
													grid.getStore().reload();
												});
									}
								} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 110,
										header : '状态名'
									},
									'f_caption' : {
										width : 110,
										header : '中文名'
									},
									'f_note' : {
										width : 150
									}
								}
							},
							substate : {
								name : 'substate',
								text : '文档子状态',
								note : '定义文档子状态',
								iconCls : 'icon-designer-substate',
								properties : [
										{
											name : 'f_source',
											text : '左操作值',
											xeditor : 'string',
											note : '子状态的左操作值，|field|或普通string值。'
										},
										{
											name : 'f_operator',
											text : '操作符',
											xeditor : 'selectkeymap',
											xconfig : {
												datas : [ [ 0, '=' ],
														[ 1, '>' ], [ 2, '<' ],
														[ 3, '>=' ],
														[ 4, '<=' ],
														[ 5, 'include' ] ],
												readOnly : true
											},
											note : '字段对应JAVA数据类型。'
										},
										{
											name : 'f_target',
											text : '右操作值',
											xeditor : 'string',
											note : '子状态的右操作值，|field|或普通string值。'
										} ],
								propertyColumns : {
									'f_key' : {
										width : 80
									},
									'f_name' : {
										width : 100,
										header : '状态名'
									},
									'f_caption' : {
										width : 100,
										header : '中文名'
									},
									'f_source' : {
										width : 120
									},
									'f_operator' : {
										width : 60
									},
									'f_target' : {
										width : 120
									},
									'f_note' : {
										width : 150
									}
								}
							},
							table : {
								name : 'table',
								text : '数据表',
								note : '模块数据表节点',
								iconCls : 'icon-designer-table',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'field' ],
								properties : [ {
									name : 'f_keyfield',
									text : '主键字段',
									xeditor : 'string',
									note : '数据表的关键字名，默认为[id]。'
								}, {
									name : 'f_titlefield',
									text : '标题字段',
									xeditor : 'string',
									note : '数据表的标题字段，默认为。'
								} ],
								editors : [
										'designer/designobject/properties.do',
										'designer/designobject/objectgrideditor.do?type=field',
										'designer/designobject/objectgriddetaileditor.do?type=tableform' ]
							},
							tablefolder : {
								name : 'tablefolder',
								text : '数据表容器',
								note : '模块数据表容器节点',
								iconCls : 'icon-designer-tablefolder',
								subModules : [ 'table' ]
							},
							tableform : {
								name : 'tableform',
								text : '表单',
								note : '数据表字段表单定义',
								iconCls : 'icon-designer-tableform',
								detailurl : 'designer/table/tableform.fieldmap.do',
								properties : [ {
									name : 'f_fieldmap',
									text : '表单字段',
									xeditor : 'textbox',
									note : '表单的字段列表定义。'
								}, {
									name : 'f_columns',
									text : '列数',
									xeditor : 'number',
									note : '表单显示的总列数。'
								}, {
									name : 'f_width',
									text : '宽度',
									xeditor : 'number',
									note : '背景表单宽度，单位：毫米。'
								}, {
									name : 'f_height',
									text : '高度',
									xeditor : 'number',
									note : '背景表单高度，单位：毫米。'
								} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 110,
										header : '表单名'
									},
									'f_caption' : {
										width : 110,
										header : '中文名'
									},
									'f_columns' : {
										width : 50
									},
									'f_width' : {
										width : 50
									},
									'f_height' : {
										width : 50
									},
									'f_config' : {
										width : 100
									},
									'f_note' : {
										width : 150
									}
								},
								extendsMenu : [ {
									text : '编辑背景窗口',
									iconCls : 'icon-common-edit',
									handler : function() {
										var grid = this.items.get(0).items
												.get(0);
										var sm = grid.getSelectionModel();
										var record = sm.getSelection();
										if (Ext.isDefined(record)) {
											var key = record[0].get('key');
											var caption = record[0]
													.get('f_caption');
											var win = new Ext.Window(
													{
														iconCls : 'icon-common-edit',
														title : '编辑 ' + caption
																+ ' 表单域显示',
														modal : true,
														maximizable : true,
														minimizable : false,
														minimizable : false,
														resizable : true,
														width : 700,
														height : 500,
														layout : 'fit',
														items : {
															id : 'form-bg-editor',
															autoScroll : true,
															layout : 'fit',
															autoLoad : {
																scripts : true,
																url : 'designer/table/tableform.bgeditor.do',
																params : {
																	key : key
																}
															}
														}
													});
											win.show();
										}
									}
								} ]
							},
							view : {
								name : 'view',
								text : '视图',
								note : '',
								iconCls : 'icon-designer-view',
								jsonable : true,
								copyable : true,
								deletable : true,
								subModules : [ 'column', 'action',
										'viewparameter' ],
								properties : [
										{
											name : 'f_icon',
											text : '图标',
											xeditor : 'string',
											note : '视图图标。'
										},
										{
											name : 'f_url',
											text : '视图路径',
											xeditor : 'string',
											note : '视图的打开路径。'
										},
										{
											name : 'f_keycolumn',
											text : '关键列',
											xeditor : 'string',
											note : '指定视图的唯一关键列名。'
										},
										{
											name : 'f_autoexpandcolumn',
											text : '自动扩展列',
											xeditor : 'string',
											note : '指定视图的自动扩展列名。'
										},
										{
											name : 'f_single_select',
											text : '选择单行',
											xeditor : 'boolean',
											note : '定义视图的列表选择模式。'
										},
										{
											name : 'f_page_size',
											text : '页记录数',
											xeditor : 'number',
											note : '定义视图列表的每页显示记录数。'
										},
										{
											name : 'f_isquery',
											text : '支持查询',
											xeditor : 'boolean',
											note : '视图是否支持查询。'
										},
										{
											name : 'f_issummary',
											text : '支持汇总',
											xeditor : 'boolean',
											note : '视图是否支持列汇总。'
										},
										{
											name : 'f_i_documenttype',
											text : '文档类型',
											xeditor : 'designobject',
											xconfig : {
												remoteRenderType : 'renderDesignObject',
												parentKey : '',
												mclass : 'documenttype',
												selectInParent : false
											},
											note : '选择视图列表对应的文档类型。'
										},
										{
											name : 'f_enable_favorite',
											text : '支持收藏',
											xeditor : 'boolean',
											note : '对于支持收藏的视图，需要定义文档类型。'
										},
										{
											name : 'f_title_field',
											text : '标题列名',
											xeditor : 'string',
											note : '指定视图的标题字段名，在收藏时将用该字段作为收藏项标题。'
										} ],
								extendsMenu : [ {
									text : '预览数据',
									iconCls : 'icon-designtool-viewdata'
								} ],
								editors : [
										'designer/designobject/properties.do',
										'designer/designobject/objectgrideditor.do?type=column',
										'designer/designobject/objectgrideditor.do?type=viewparameter',
										'designer/designobject/objectgrideditor.do?type=action' ]
							},
							viewparameter : {
								name : 'viewparameter',
								text : '视图参数',
								note : '定义视图所接受的参数。',
								iconCls : 'icon-designer-viewparameter',
								properties : [ {
									name : 'f_sql',
									text : '条件语句',
									xeditor : 'string',
									note : '定义参数的查询条件语句。'
								}, {
									name : 'f_nullsql',
									text : '空条件语句',
									xeditor : 'string',
									note : '定义参数为空时的条件语句。'
								} ],
								propertyColumns : {
									'f_key' : {
										width : 120
									},
									'f_name' : {
										width : 100,
										header : '参数名'
									},
									'f_caption' : {
										width : 100,
										header : '中文名'
									},
									'f_sql' : {
										width : 150
									},
									'f_nullsql' : {
										width : 150
									},
									'f_config' : {
										width : 120
									},
									'f_note' : {
										width : 150
									}
								}
							}
						}
					}
				});