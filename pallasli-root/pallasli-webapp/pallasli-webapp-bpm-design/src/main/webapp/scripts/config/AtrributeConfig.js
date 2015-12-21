Ext.define('Pallas.activitiDesigner.config.AtrributeConfig', {
	statics : {
		/**
		 * name,documentation,process_id, process_author,process_executable,
		 * process_version, process_namespace, prioritydefinition,
		 * tasklisteners, servicetaskclass, servicetaskexpression,
		 * servicetaskdelegateexpression, servicetaskresultvariable,
		 * servicetaskfields, scriptformat, scripttext, ruletask_class,
		 * ruletask_variables_input, ruletask_result, ruletask_rules,
		 * ruletask_exclude, text, callactivitycalledelement,
		 * callactivityinparameters, callactivityoutparameters,
		 * conditionsequenceflow, defaultflow, conditionalflow, cancelactivity,
		 * messageref, signalref, errorref, asynchronousdefinition,
		 * exclusivedefinition, executionlisteners, eventlisteners, overrideid,
		 * formkeydefinition, formproperties, isforcompensation,
		 * customformdefinition, initiator, usertaskassignment, looptype,
		 * multiinstance_sequential, multiinstance_cardinality,
		 * multiinstance_collection, multiinstance_variable,
		 * multiinstance_condition, mailtaskto, mailtaskfrom, mailtasksubject,
		 * mailtaskcc, mailtaskbcc, mailtasktext, mailtaskhtml, mailtaskcharset,
		 * duedatedefinition, timerdurationdefinition, timerdatedefinition,
		 * timercycledefinition
		 */

		baseCommonSource : {
			name : "",
			documentation : ""
		},

		processInstance : {
			baseSource : {
				overrideid : "",
				name : "",
				appkey : "",
				process_id : "",
				process_author : "",
				process_namespace : "",
				process_executable : "",
				process_version : "",
				documentation : "",
				executionlisteners : "",
				eventlisteners : ""
			}
		},
		startNoneEvent : {
			baseSource : {
				formkeydefinition : ""
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				form : {
					appkey : {},
					formkeydefinition : {},
					formproperties : {
						columnWidth : 1
					}
				},
				handler : {
					initiator : {
						columnWidth : 1
					}
				}

			}
		},
		userTask : {
			baseSource : {
				prioritydefinition : '',
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					prioritydefinition : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}

				},
				form : {
					newForm : {},
					appkey : {},
					formkeydefinition : {},
					formproperties : {
						columnWidth : 1
					}
				},
				time : {
					duedatedefinition : {},
					duedateValue : {}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}

			}
		},
		scriptTask : {
			baseSource : {
				scriptformat : '',
				scripttext : '',
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					scriptformat : {},
					scripttext : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		serviceTask : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					servicetaskclass : {},
					servicetaskexpression : {},
					servicetaskdelegateexpression : {},
					servicetaskresultvariable : {},
					servicetaskfields : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		businessRuleTask : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					ruletask_class : {},
					ruletask_variables_input : {},
					ruletask_result : {},
					ruletask_rules : {},
					ruletask_exclude : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},

		receiveTask : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		manualTask : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		mailTask : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				message : {
					mailtaskto : {},
					mailtaskfrom : {},
					mailtasksubject : {},
					mailtaskcc : {},
					mailtaskbcc : {},
					mailtasktext : {},
					mailtaskhtml : {},
					mailtaskcharset : {}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		subProcess : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					executionlisteners : {
						columnWidth : 1
					}

				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		eventSubProcess : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				executionlisteners : ''
			},
		},
		callActivity : {
			baseSource : {
				exclusivedefinition : '',
				asynchronousdefinition : '',
				isforcompensation : '',
				executionlisteners : ''
			},
			advanceSource : {
				basic : {
					name : {},
					documentation : {},
					prioritydefinition : {},
					exclusivedefinition : {},
					asynchronousdefinition : {},
					isforcompensation : {},
					callactivityinparameters : {},
					callactivityoutparameters : {},
					callactivitycalledelement : {},
					executionlisteners : {
						columnWidth : 1
					}
				},
				handler : {
					usertaskassignment : {
						columnWidth : 1
					},
					looptype : {},
					multiinstance_sequential : {},
					multiinstance_cardinality : {},
					multiinstance_collection : {},
					multiinstance_variable : {},
					multiinstance_condition : {}
				}
			}
		},
		startTimerEvent : {
			baseSource : {
				timerdurationdefinition : "",
				timerdatedefinition : "",
				timercycledefinition : "",
				executionlisteners : ""
			}
		},
		startMessageEvent : {
			baseSource : {
				messageref : "",
				executionlisteners : ""
			}
		},
		startErrorEvent : {
			baseSource : {
				errorref : "",
				executionlisteners : ""
			}
		},
		boundaryErrorEvent : {
			baseSource : {
				errorref : "",
				executionlisteners : ""
			}
		},
		boundaryTimerEvent : {
			baseSource : {
				cancelactivity : "",
				timerdurationdefinition : "",
				timerdatedefinition : "",
				timercycledefinition : "",
				executionlisteners : ""
			}
		},
		boundarySignalEvent : {
			baseSource : {
				cancelactivity : "",
				signalref : "",
				executionlisteners : ""
			}
		},
		boundaryMessageEvent : {
			baseSource : {
				cancelactivity : "",
				messageref : "",
				executionlisteners : ""
			}
		},
		catchTimerEvent : {
			baseSource : {
				timerdurationdefinition : "",
				timerdatedefinition : "",
				timercycledefinition : "",
				executionlisteners : ""
			}
		},
		catchSignalEvent : {
			baseSource : {
				signalref : "",
				executionlisteners : ""
			}
		},
		catchMessageEvent : {
			baseSource : {
				messageref : "",
				executionlisteners : ""
			}
		},
		throwNoneEvent : {
			baseSource : {
				executionlisteners : ""
			}
		},
		throwSignalEvent : {
			baseSource : {
				signalref : "",
				executionlisteners : ""
			}
		},
		endNoneEvent : {
			baseSource : {
				executionlisteners : ""
			}
		},
		endErrorEvent : {
			baseSource : {
				errorref : "",
				executionlisteners : ""
			}
		},
		sequenceFlow : {
			baseSource : {
				defaultflow : "",
				conditionalflow : "",
				conditionsequenceflow : "",
				executionlisteners : ""
			}
		},
		textAnnotation : {
			baseSource : {
				text : ""
			}
		},
		association : {},
		exclusiveGateway : {},
		parallelGateway : {},
		inclusiveGateway : {},
		eventGateway : {},
		pool : {},
		lane : {},

		baseSourceConfig : {
			newForm : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否重新定义表单'
			},
			appkey : {
				editor : {
					selectOnFocus : true,
					xtype : "combo"
				},
				displayName : '应用Key'
			},
			formkeydefinition : {
				editor : {
					selectOnFocus : true,
					xtype : "combo"
				},
				displayName : '表单Key'
			},
			process_id : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '流程标识KEY'
			},
			process_author : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '流程作者'
			},
			process_executable : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否能够执行'
			},
			process_version : {
				editor : {
					selectOnFocus : true,
					xtype : "numberfield"
				},
				displayName : '流程版本字符串 (仅文档)'
			},
			process_namespace : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '命名空间'
			},
			duedatedefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "绝对时间",
							"value" : "1"
						}, {
							"display" : "相对时间",
							"value" : "2"
						}, {
							"display" : "自定义表达式",
							"value" : "3"
						} ]
					}),
					editable : false,
					emptyText : "自定义优先级请录入100以内数值",
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '任务过期时间类型'
			},
			duedateValue : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '任务过期时间'
			},
			prioritydefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "轻微/10",
							"value" : "10"
						}, {
							"display" : "低/20",
							"value" : "20"
						}, {
							"display" : "普通/40",
							"value" : "40"
						}, {
							"display" : "重要/60",
							"value" : "60"
						}, {
							"display" : "非常重要/80",
							"value" : "80"
						} ]
					}),
					editable : true,
					emptyText : "自定义优先级请录入100以内数值",
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '任务优先级'
			},
			usertaskassignment : {
				editor : {
					selectOnFocus : true,
					comptype : "groupuserselect",
					xtype : "trigger"
				},
				displayName : '任务分配参与者'
			},
			formproperties : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "编号"
					}, {
						header : "名称"
					}, {
						header : "类型"
					}, {
						header : "表达式"
					}, {
						header : "变量"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '表单属性'
			},
			tasklisteners : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '任务监听'
			},
			servicetaskclass : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '任务实现类'
			},
			servicetaskexpression : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '任务表达式'
			},
			servicetaskdelegateexpression : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '任务代理表达式'
			},
			servicetaskresultvariable : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '任务结果变量名'
			},
			servicetaskfields : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "名称"
					}, {
						header : "字符串值"
					}, {
						header : "表达式"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '字段扩展'
			},
			scriptformat : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "javascript",
							"value" : "00"
						}, {
							"display" : "java",
							"value" : "01"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true

				},
				displayName : '脚本格式'
			},
			scripttext : {
				editor : {
					selectOnFocus : true,
					xtype : "textarea"
				},
				displayName : '任务脚本'
			},
			ruletask_class : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '规则任务class'
			},
			ruletask_variables_input : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '输入变量'
			},
			ruletask_result : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '结果变量'
			},
			ruletask_rules : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '规则'
			},
			ruletask_exclude : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '排除'
			},
			mailtaskto : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '接受者'
			},
			mailtaskfrom : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '发送者'
			},
			mailtasksubject : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '主题'
			},
			mailtaskcc : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '抄送'
			},
			mailtaskbcc : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '密送'
			},
			mailtasktext : {
				editor : {
					selectOnFocus : true,
					xtype : "textarea"
				},
				displayName : '内容'
			},
			mailtaskhtml : {
				editor : {
					selectOnFocus : true,
					xtype : "htmleditor"
				},
				displayName : 'html'
			},
			mailtaskcharset : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "utf-8",
							"value" : "1"
						}, {
							"display" : "gbk",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '编码格式字符集'
			},
			callactivitycalledelement : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "应用"
					}, {
						header : "流程key"
					}, {
						header : "流程名称"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '调用元素/流程'
			},
			callactivityinparameters : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "来源"
					}, {
						header : "来源表达式"
					}, {
						header : "目标"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '输入参数'
			},
			callactivityoutparameters : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "来源"
					}, {
						header : "来源表达式"
					}, {
						header : "目标"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '输出参数'
			},
			conditionsequenceflow : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '顺序流条件表达式'
			},
			defaultflow : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是-\\->",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否缺省路由'
			},
			conditionalflow : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是<>",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否条件路由'
			},
			cancelactivity : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '取消活动'
			},
			timerdurationdefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "北京时间",
							"value" : "1"
						}, {
							"display" : "东京时间",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '时间区间'
			},
			timerdatedefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "yyyy-mm-dd hh:MM:ss",
							"value" : "1"
						}, {
							"display" : "yyyy/mm/dd",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '时间格式'
			},
			timercycledefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "一年",
							"value" : "1"
						}, {
							"display" : "一个月",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '时间周期'
			},
			messageref : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '消息引用'
			},
			signalref : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '信号引用'
			},
			errorref : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '错误引用'
			},
			initiator : {
				editor : {
					selectOnFocus : true,
					comptype : "groupuserselect",
					xtype : "trigger"
				},
				displayName : '发起者'
			},
			text : {
				editor : {
					selectOnFocus : true,
					xtype : "textarea"
				},
				displayName : '文本'
			},
			asynchronousdefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否异步'
			},
			exclusivedefinition : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否排它'
			},
			executionlisteners : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "event"
					}, {
						header : "类class"
					}, {
						header : "表达式"
					}, {
						header : "代理表达式"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '执行监听器'
			},
			eventlisteners : {
				editor : {
					selectOnFocus : true,
					store : Ext.create('Ext.data.Store', {
						fields : [],
						data : [ {} ]
					}),
					columns : [ {
						header : "event"
					}, {
						header : "类class"
					}, {
						header : "代理表达式"
					}, {
						header : "实体类型"
					}, {
						header : "抛出信号/消息 error code"
					} ],
					xtype : "trigger",
					comptype : "grid"
				},
				displayName : '事件监听器'
			},
			customformdefinition : {
				editor : {
					selectOnFocus : true,
					xtype : "trigger"
				},
				displayName : '自定义表单'
			},
			looptype : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "否",
							"value" : "0"
						}, {
							"display" : "标准",
							"value" : "1"
						}, {
							"display" : "并行会签",
							"value" : "2"
						}, {
							"display" : "顺序会签",
							"value" : "3"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '循环类型'
			},
			multiinstance_sequential : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否顺序(多实例)'
			},
			multiinstance_cardinality : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '实例个数(多实例)'
			},
			multiinstance_collection : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '实例集合(多实例)'
			},
			multiinstance_variable : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '元素变量(多实例)'
			},
			multiinstance_condition : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '完成条件(多实例)'
			},
			isforcompensation : {
				editor : {
					store : Ext.create('Ext.data.Store', {
						fields : [ 'display', 'value' ],
						data : [ {
							"display" : "是",
							"value" : "1"
						}, {
							"display" : "否",
							"value" : "0"
						} ]
					}),
					editable : false,
					queryMode : 'local',
					displayField : 'display',
					valueField : 'value',
					xtype : "combo",
					selectOnFocus : true
				},
				displayName : '是否补偿'
			},
			overrideid : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '元素唯一标识'
			},
			name : {
				editor : {
					selectOnFocus : true,
					xtype : "textfield"
				},
				displayName : '名称'
			},
			documentation : {
				editor : {
					selectOnFocus : true,
					xtype : "textarea"
				},
				displayName : '描述'
			}

		}

	}
});
