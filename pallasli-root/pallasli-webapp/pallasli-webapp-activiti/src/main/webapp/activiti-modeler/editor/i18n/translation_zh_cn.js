/**
 * @author nicolas.peters
 * 
 * Contains all strings for the default language (en-us).
 * Version 1 - 08/29/08
 */
if(!ORYX) var ORYX = {};

if(!ORYX.I18N) ORYX.I18N = {};

ORYX.I18N.Language = "zh_cn"; //Pattern <ISO language code>_<ISO country code> in lower case!

if(!ORYX.I18N.Oryx) ORYX.I18N.Oryx = {};

ORYX.I18N.Oryx.title		= "ATWASOFT";
ORYX.I18N.Oryx.noBackendDefined	= "注意! \n没有定义后端.\n 请求的模型不能加载。请使用保存插件加载配置.";
ORYX.I18N.Oryx.pleaseWait 	= "请稍等，正在加载中...";
ORYX.I18N.Oryx.notLoggedOn = "未登录";
ORYX.I18N.Oryx.editorOpenTimeout = "编辑器未启动，请检查，如果你禁止了弹出框，请允许弹出框显示.";

if(!ORYX.I18N.AddDocker) ORYX.I18N.AddDocker = {};

ORYX.I18N.AddDocker.group = "停靠";
ORYX.I18N.AddDocker.add = "增加停靠";
ORYX.I18N.AddDocker.addDesc = "点击增加停靠到边";
ORYX.I18N.AddDocker.del = "删除停靠";
ORYX.I18N.AddDocker.delDesc = "点击删除停靠";

if(!ORYX.I18N.Arrangement) ORYX.I18N.Arrangement = {};

ORYX.I18N.Arrangement.groupZ = "Z轴排序";
ORYX.I18N.Arrangement.btf = "显示在前";
ORYX.I18N.Arrangement.btfDesc = "显示在前";
ORYX.I18N.Arrangement.btb = "显示在后";
ORYX.I18N.Arrangement.btbDesc = "显示在后";
ORYX.I18N.Arrangement.bf = "前向显示";
ORYX.I18N.Arrangement.bfDesc = "前向显示";
ORYX.I18N.Arrangement.bb = "后向显示";
ORYX.I18N.Arrangement.bbDesc = "后台显示";
ORYX.I18N.Arrangement.groupA = "对齐";
ORYX.I18N.Arrangement.ab = "靠下对齐";
ORYX.I18N.Arrangement.abDesc = "下端";
ORYX.I18N.Arrangement.am = "靠中间对齐";
ORYX.I18N.Arrangement.amDesc = "中间";
ORYX.I18N.Arrangement.at = "靠上对齐";
ORYX.I18N.Arrangement.atDesc = "上端";
ORYX.I18N.Arrangement.al = "靠左对齐";
ORYX.I18N.Arrangement.alDesc = "左侧";
ORYX.I18N.Arrangement.ac = "居中对齐";
ORYX.I18N.Arrangement.acDesc = "居中";
ORYX.I18N.Arrangement.ar = "居右对齐";
ORYX.I18N.Arrangement.arDesc = "居右";
ORYX.I18N.Arrangement.as = "同尺寸对齐";
ORYX.I18N.Arrangement.asDesc = "同尺寸";

if(!ORYX.I18N.Edit) ORYX.I18N.Edit = {};

ORYX.I18N.Edit.group = "编辑";
ORYX.I18N.Edit.cut = "裁剪";
ORYX.I18N.Edit.cutDesc = "裁剪选区到剪贴板";
ORYX.I18N.Edit.copy = "拷贝";
ORYX.I18N.Edit.copyDesc = "拷贝选区到剪贴板";
ORYX.I18N.Edit.paste = "粘贴";
ORYX.I18N.Edit.pasteDesc = "粘贴到画布中";
ORYX.I18N.Edit.del = "删除";
ORYX.I18N.Edit.delDesc = "删除所有选中对象";

if(!ORYX.I18N.EPCSupport) ORYX.I18N.EPCSupport = {};

ORYX.I18N.EPCSupport.group = "EPC";
ORYX.I18N.EPCSupport.exp = "导出EPC";
ORYX.I18N.EPCSupport.expDesc = "导出图形到EPML";
ORYX.I18N.EPCSupport.imp = "导入EPC";
ORYX.I18N.EPCSupport.impDesc = "导入EPML文件";
ORYX.I18N.EPCSupport.progressExp = "导出模型中...";
ORYX.I18N.EPCSupport.selectFile = "选择一个EPML (.empl)文件导入.";
ORYX.I18N.EPCSupport.file = "文件";
ORYX.I18N.EPCSupport.impPanel = "导入EPML文件";
ORYX.I18N.EPCSupport.impBtn = "导入";
ORYX.I18N.EPCSupport.close = "关闭";
ORYX.I18N.EPCSupport.error = "错误";
ORYX.I18N.EPCSupport.progressImp = "导入中...";

if(!ORYX.I18N.ERDFSupport) ORYX.I18N.ERDFSupport = {};

ORYX.I18N.ERDFSupport.exp = "导出ERDF";
ORYX.I18N.ERDFSupport.expDesc = "导出ERDF";
ORYX.I18N.ERDFSupport.imp = "从ERDF导入";
ORYX.I18N.ERDFSupport.impDesc = "从ERDF导入";
ORYX.I18N.ERDFSupport.impFailed = "请求导入ERDF失败.";
ORYX.I18N.ERDFSupport.impFailed2 = "导入发生错误! <br/>请检查错误消息: <br/><br/>";
ORYX.I18N.ERDFSupport.error = "错误";
ORYX.I18N.ERDFSupport.noCanvas = "XML文档没有画布结点!";
ORYX.I18N.ERDFSupport.noSS = "画布结点没有包含的模板集!";
ORYX.I18N.ERDFSupport.wrongSS = "模板集不适合当前编辑器!";
ORYX.I18N.ERDFSupport.selectFile = "选择ERDF (.xml)文件导入!";
ORYX.I18N.ERDFSupport.file = "文件";
ORYX.I18N.ERDFSupport.impERDF = "导入ERDF";
ORYX.I18N.ERDFSupport.impBtn = "导入";
ORYX.I18N.ERDFSupport.impProgress = "导入中...";
ORYX.I18N.ERDFSupport.close = "关闭";
ORYX.I18N.ERDFSupport.deprTitle = "确定导出eRDF?";
ORYX.I18N.ERDFSupport.deprText = "导出eRDF不推荐，因为未来版本不再支持，确定导出吗？";

if(!ORYX.I18N.jPDLSupport) ORYX.I18N.jPDLSupport = {};

ORYX.I18N.jPDLSupport.group = "ExecBPMN";
ORYX.I18N.jPDLSupport.exp = "导出jPDL";
ORYX.I18N.jPDLSupport.expDesc = "导出jPDL";
ORYX.I18N.jPDLSupport.imp = "导入jPDL";
ORYX.I18N.jPDLSupport.impDesc = "导入jPDL文件";
ORYX.I18N.jPDLSupport.impFailedReq = "导入jPDL失败.";
ORYX.I18N.jPDLSupport.impFailedJson = "转换jPDL失败.";
ORYX.I18N.jPDLSupport.impFailedJsonAbort = "导入中止.";
ORYX.I18N.jPDLSupport.loadSseQuestionTitle = "jBPM模板扩展需要加载"; 
ORYX.I18N.jPDLSupport.loadSseQuestionBody = "为了导入jPDL,模板扩展需要加载，你要继续吗?";
ORYX.I18N.jPDLSupport.expFailedReq = "导出模型失败.";
ORYX.I18N.jPDLSupport.expFailedXml = "导出jPDL失败: ";
ORYX.I18N.jPDLSupport.error = "错误";
ORYX.I18N.jPDLSupport.selectFile = "选择一个jPDL (.xml) 文件或类型导入!";
ORYX.I18N.jPDLSupport.file = "文件";
ORYX.I18N.jPDLSupport.impJPDL = "导入jPDL";
ORYX.I18N.jPDLSupport.impBtn = "导入";
ORYX.I18N.jPDLSupport.impProgress = "导入中...";
ORYX.I18N.jPDLSupport.close = "关闭";

if(!ORYX.I18N.Save) ORYX.I18N.Save = {};

ORYX.I18N.Save.group = "文件";
ORYX.I18N.Save.save = "保存";
ORYX.I18N.Save.saveDesc = "保存";
ORYX.I18N.Save.saveAs = "另存为...";
ORYX.I18N.Save.saveAsDesc = "另存为...";
ORYX.I18N.Save.unsavedData = "有未保存数据，请离开前保存，否则你的数据未丢失!";
ORYX.I18N.Save.newProcess = "新增流程";
ORYX.I18N.Save.saveAsTitle = "另存为...";
ORYX.I18N.Save.saveBtn = "保存";
ORYX.I18N.Save.close = "关闭";
ORYX.I18N.Save.savedAs = "另存为";
ORYX.I18N.Save.saved = "保存成功!";
ORYX.I18N.Save.failed = "保存失败.";
ORYX.I18N.Save.noRights = "你没有权限编辑.";
ORYX.I18N.Save.saving = "保存";
ORYX.I18N.Save.saveAsHint = "流程图保存在:";

if(!ORYX.I18N.File) ORYX.I18N.File = {};

ORYX.I18N.File.group = "文件";
ORYX.I18N.File.print = "打印";
ORYX.I18N.File.printDesc = "打印当前模型";
ORYX.I18N.File.pdf = "导出为PDF";
ORYX.I18N.File.pdfDesc = "导出为PDF";
ORYX.I18N.File.info = "信息";
ORYX.I18N.File.infoDesc = "信息";
ORYX.I18N.File.genPDF = "生成PDF";
ORYX.I18N.File.genPDFFailed = "生成PDF失败.";
ORYX.I18N.File.printTitle = "打印";
ORYX.I18N.File.printMsg = "我们打印有些体验问题，我们推荐使用PDF导出打印流程图，你要继续打印吗?";

if(!ORYX.I18N.Grouping) ORYX.I18N.Grouping = {};

ORYX.I18N.Grouping.grouping = "组合";
ORYX.I18N.Grouping.group = "组合";
ORYX.I18N.Grouping.groupDesc = "分组所有选择的图形";
ORYX.I18N.Grouping.ungroup = "解组";
ORYX.I18N.Grouping.ungroupDesc = "删除所有选择的图形";

if(!ORYX.I18N.Loading) ORYX.I18N.Loading = {};

ORYX.I18N.Loading.waiting ="请等待...";

if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};

ORYX.I18N.PropertyWindow.name = "名称";
ORYX.I18N.PropertyWindow.value = "值";
ORYX.I18N.PropertyWindow.selected = "已选择";
ORYX.I18N.PropertyWindow.clickIcon = "点击图标";
ORYX.I18N.PropertyWindow.add = "添加";
ORYX.I18N.PropertyWindow.rem = "删除";
ORYX.I18N.PropertyWindow.complex = "复杂类型编辑";
ORYX.I18N.PropertyWindow.text = "文本编辑";
ORYX.I18N.PropertyWindow.ok = "确定";
ORYX.I18N.PropertyWindow.cancel = "取消";
ORYX.I18N.PropertyWindow.dateFormat = "m/d/y";

if(!ORYX.I18N.ShapeMenuPlugin) ORYX.I18N.ShapeMenuPlugin = {};

ORYX.I18N.ShapeMenuPlugin.drag = "拖拽";
ORYX.I18N.ShapeMenuPlugin.clickDrag = "点击或拖拽";
ORYX.I18N.ShapeMenuPlugin.morphMsg = "变形";

if(!ORYX.I18N.SyntaxChecker) ORYX.I18N.SyntaxChecker = {};

ORYX.I18N.SyntaxChecker.group = "核实";
ORYX.I18N.SyntaxChecker.name = "语法检查";
ORYX.I18N.SyntaxChecker.desc = "检查语法";
ORYX.I18N.SyntaxChecker.noErrors = "没有语法错误.";
ORYX.I18N.SyntaxChecker.invalid = "无效回答.";
ORYX.I18N.SyntaxChecker.checkingMessage = "检查中 ...";

if(!ORYX.I18N.Deployer) ORYX.I18N.Deployer = {};

ORYX.I18N.Deployer.group = "部署";
ORYX.I18N.Deployer.name = "部署";
ORYX.I18N.Deployer.desc = "部署到引擎";

if(!ORYX.I18N.Undo) ORYX.I18N.Undo = {};

ORYX.I18N.Undo.group = "撤销";
ORYX.I18N.Undo.undo = "撤销";
ORYX.I18N.Undo.undoDesc = "撤销最新动作";
ORYX.I18N.Undo.redo = "重做";
ORYX.I18N.Undo.redoDesc = "重做最新动作";

if(!ORYX.I18N.View) ORYX.I18N.View = {};

ORYX.I18N.View.group = "缩放";
ORYX.I18N.View.zoomIn = "缩小";
ORYX.I18N.View.zoomInDesc = "缩小到模型大小";
ORYX.I18N.View.zoomOut = "放大";
ORYX.I18N.View.zoomOutDesc = "放大到模型大小";
ORYX.I18N.View.zoomStandard = "标准缩放";
ORYX.I18N.View.zoomStandardDesc = "标准缩放";
ORYX.I18N.View.zoomFitToModel = "自适应模型";
ORYX.I18N.View.zoomFitToModelDesc = "自适应模型大小";

/** New Language Properties: 08.12.2008 */

ORYX.I18N.PropertyWindow.title = "模型元素属性";

if(!ORYX.I18N.ShapeRepository) ORYX.I18N.ShapeRepository = {};
ORYX.I18N.ShapeRepository.title = "设计元素库";

ORYX.I18N.Save.dialogDesciption = "请输入一个名称、描述和备注.";
ORYX.I18N.Save.dialogLabelTitle = "名称";
ORYX.I18N.Save.dialogLabelDesc = "描述";
ORYX.I18N.Save.dialogLabelType = "类型";
ORYX.I18N.Save.dialogLabelComment = "备注";

Activiti20.MessageBox.buttonText.yes = "是";
Activiti20.MessageBox.buttonText.no = "否";
Activiti20.MessageBox.buttonText.cancel = "取消";
Activiti20.MessageBox.buttonText.ok = "确定";

if(!ORYX.I18N.Perspective) ORYX.I18N.Perspective = {};
ORYX.I18N.Perspective.no = "非透视图"
ORYX.I18N.Perspective.noTip = "卸载当前透视图"

/** New Language Properties: 21.04.2009 */
ORYX.I18N.JSONSupport = {
    imp: {
        name: "从JSON中导入模型",
        desc: "从JSON中导入模型",
        group: "导出",
        selectFile: "选择JSON (.json) 文件或JSON 类型文件 导入!",
        file: "文件",
        btnImp: "导入",
        btnClose: "关闭",
        progress: "导入中 ...",
        syntaxError: "语法错误"
    },
    exp: {
        name: "导出JSON",
        desc: "导出当前模型为JSON",
        group: "导出"
    }
};

/** New Language Properties: 09.05.2009 */
if(!ORYX.I18N.JSONImport) ORYX.I18N.JSONImport = {};

ORYX.I18N.JSONImport.title = "JSON导入";
ORYX.I18N.JSONImport.wrongSS = "导入的模型文件({0}) 不匹配当前模型集({1})."

/** New Language Properties: 14.05.2009 */
if(!ORYX.I18N.RDFExport) ORYX.I18N.RDFExport = {};
ORYX.I18N.RDFExport.group = "导出";
ORYX.I18N.RDFExport.rdfExport = "导出为RDF";
ORYX.I18N.RDFExport.rdfExportDescription = "导出当前模型到RDF XML序列化";

/** New Language Properties: 15.05.2009*/
if(!ORYX.I18N.SyntaxChecker.BPMN) ORYX.I18N.SyntaxChecker.BPMN={};
ORYX.I18N.SyntaxChecker.BPMN_NO_SOURCE = "边必须有源.";
ORYX.I18N.SyntaxChecker.BPMN_NO_TARGET = "边必须有目标.";
ORYX.I18N.SyntaxChecker.BPMN_DIFFERENT_PROCESS = "源和目标节点必须在同一个流程中.";
ORYX.I18N.SyntaxChecker.BPMN_SAME_PROCESS = "源和目标节点必须在不同的池中.";
ORYX.I18N.SyntaxChecker.BPMN_FLOWOBJECT_NOT_CONTAINED_IN_PROCESS = "一个flow对象必须包含在一个流程中.";
ORYX.I18N.SyntaxChecker.BPMN_ENDEVENT_WITHOUT_INCOMING_CONTROL_FLOW = "结束事件必须有入流.";
ORYX.I18N.SyntaxChecker.BPMN_STARTEVENT_WITHOUT_OUTGOING_CONTROL_FLOW = "开始事件必须有出流.";
ORYX.I18N.SyntaxChecker.BPMN_STARTEVENT_WITH_INCOMING_CONTROL_FLOW = "开始事件不能有入流.";
ORYX.I18N.SyntaxChecker.BPMN_ATTACHEDINTERMEDIATEEVENT_WITH_INCOMING_CONTROL_FLOW = "附加的中间事件不能有入流.";
ORYX.I18N.SyntaxChecker.BPMN_ATTACHEDINTERMEDIATEEVENT_WITHOUT_OUTGOING_CONTROL_FLOW = "附加的中间事件必须有明确的出流.";
ORYX.I18N.SyntaxChecker.BPMN_ENDEVENT_WITH_OUTGOING_CONTROL_FLOW = "结束事件不能有出流.";
ORYX.I18N.SyntaxChecker.BPMN_EVENTBASEDGATEWAY_BADCONTINUATION = "基于事件的网关不能与网关或子流程连接.";
ORYX.I18N.SyntaxChecker.BPMN_NODE_NOT_ALLOWED = "结点类型不允许.";

if(!ORYX.I18N.SyntaxChecker.IBPMN) ORYX.I18N.SyntaxChecker.IBPMN={};
ORYX.I18N.SyntaxChecker.IBPMN_NO_ROLE_SET = "交互必须有发送者和接受者角色集";
ORYX.I18N.SyntaxChecker.IBPMN_NO_INCOMING_SEQFLOW = "此结点必须有入流.";
ORYX.I18N.SyntaxChecker.IBPMN_NO_OUTGOING_SEQFLOW = "此结点必须有出流.";

if(!ORYX.I18N.SyntaxChecker.InteractionNet) ORYX.I18N.SyntaxChecker.InteractionNet={};
ORYX.I18N.SyntaxChecker.InteractionNet_SENDER_NOT_SET = "发送者未设置";
ORYX.I18N.SyntaxChecker.InteractionNet_RECEIVER_NOT_SET = "接受者未设置";
ORYX.I18N.SyntaxChecker.InteractionNet_MESSAGETYPE_NOT_SET = "消息类型未设置";
ORYX.I18N.SyntaxChecker.InteractionNet_ROLE_NOT_SET = "角色未设置";

if(!ORYX.I18N.SyntaxChecker.EPC) ORYX.I18N.SyntaxChecker.EPC={};
ORYX.I18N.SyntaxChecker.EPC_NO_SOURCE = "每一个边必须有来源.";
ORYX.I18N.SyntaxChecker.EPC_NO_TARGET = "每一个边必须有目标.";
ORYX.I18N.SyntaxChecker.EPC_NOT_CONNECTED = "节点必须与边连接.";
ORYX.I18N.SyntaxChecker.EPC_NOT_CONNECTED_2 = "节点必须与更多的边连接.";
ORYX.I18N.SyntaxChecker.EPC_TOO_MANY_EDGES = "节点已经连接太多的边.";
ORYX.I18N.SyntaxChecker.EPC_NO_CORRECT_CONNECTOR = "节点没有正确连接.";
ORYX.I18N.SyntaxChecker.EPC_MANY_STARTS = "必须仅有一个开始事件.";
ORYX.I18N.SyntaxChecker.EPC_FUNCTION_AFTER_OR = "在分裂网关(spliiting OR/XOR)后必须没有功能.";
ORYX.I18N.SyntaxChecker.EPC_PI_AFTER_OR = "splitting OR/XOR后必须没有流程接口.";
ORYX.I18N.SyntaxChecker.EPC_FUNCTION_AFTER_FUNCTION =  "一个function后不能有另一个function.";
ORYX.I18N.SyntaxChecker.EPC_EVENT_AFTER_EVENT =  "事件后不能有事件.";
ORYX.I18N.SyntaxChecker.EPC_PI_AFTER_FUNCTION =  "function后不能有流程接口.";
ORYX.I18N.SyntaxChecker.EPC_FUNCTION_AFTER_PI =  "流程接口后不能有function.";
ORYX.I18N.SyntaxChecker.EPC_SOURCE_EQUALS_TARGET = "两个distinct结点必须有边连接."

if(!ORYX.I18N.SyntaxChecker.PetriNet) ORYX.I18N.SyntaxChecker.PetriNet={};
ORYX.I18N.SyntaxChecker.PetriNet_NOT_BIPARTITE = "图不是双向的";
ORYX.I18N.SyntaxChecker.PetriNet_NO_LABEL = "没有为转换设置标签";
ORYX.I18N.SyntaxChecker.PetriNet_NO_ID = "结点没有id";
ORYX.I18N.SyntaxChecker.PetriNet_SAME_SOURCE_AND_TARGET = "两个flow关联必须有同一个来源和目标";
ORYX.I18N.SyntaxChecker.PetriNet_NODE_NOT_SET = "一个节点没有flow关联设置";

/** New Language Properties: 02.06.2009*/
ORYX.I18N.Edge = "边线";
ORYX.I18N.Node = "节点";

/** New Language Properties: 03.06.2009*/
ORYX.I18N.SyntaxChecker.notice = "移动鼠标到红色十字图标看错误消息.";

/** New Language Properties: 05.06.2009*/
if(!ORYX.I18N.RESIZE) ORYX.I18N.RESIZE = {};
ORYX.I18N.RESIZE.tipGrow = "递增画布尺寸:";
ORYX.I18N.RESIZE.tipShrink = "递增画布尺寸:";
ORYX.I18N.RESIZE.N = "顶部";
ORYX.I18N.RESIZE.W = "左边";
ORYX.I18N.RESIZE.S ="下边";
ORYX.I18N.RESIZE.E ="右边";

/** New Language Properties: 15.07.2009*/
if(!ORYX.I18N.Layouting) ORYX.I18N.Layouting ={};
ORYX.I18N.Layouting.doing = "布局中...";

/** New Language Properties: 18.08.2009*/
ORYX.I18N.SyntaxChecker.MULT_ERRORS = "有多个错误";

/** New Language Properties: 08.09.2009*/
if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};
ORYX.I18N.PropertyWindow.oftenUsed = "常用";
ORYX.I18N.PropertyWindow.moreProps = "附属属性";

/** New Language Properties 01.10.2009 */
if(!ORYX.I18N.SyntaxChecker.BPMN2) ORYX.I18N.SyntaxChecker.BPMN2 = {};

ORYX.I18N.SyntaxChecker.BPMN2_DATA_INPUT_WITH_INCOMING_DATA_ASSOCIATION = "数据输入必须没有任何入向数据关联.";
ORYX.I18N.SyntaxChecker.BPMN2_DATA_OUTPUT_WITH_OUTGOING_DATA_ASSOCIATION = "数据输出必须没有任何出向数据关联.";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_TARGET_WITH_TOO_MANY_INCOMING_SEQUENCE_FLOWS = "Targets of 基于事件的网关目标仅有一个入项.";

/** New Language Properties 02.10.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WITH_TOO_LESS_OUTGOING_SEQUENCE_FLOWS = "An Event-based Gateway must have two or more outgoing Sequence Flows.";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_EVENT_TARGET_CONTRADICTION = "If Message Intermediate Events are used in the configuration, then Receive Tasks must not be used and vice versa.";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WRONG_TRIGGER = "Only the following Intermediate Event triggers are valid: Message, Signal, Timer, Conditional and Multiple.";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WRONG_CONDITION_EXPRESSION = "The outgoing Sequence Flows of the Event Gateway must not have a condition expression.";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_NOT_INSTANTIATING = "The Gateway does not meet the conditions to instantiate the process. Please use a start event or an instantiating attribute for the gateway.";

/** New Language Properties 05.10.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAYDIRECTION_MIXED_FAILURE = "The Gateway must have both multiple incoming and outgoing Sequence Flows.";
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAYDIRECTION_CONVERGING_FAILURE = "The Gateway must have multiple incoming but most NOT have multiple outgoing Sequence Flows.";
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAYDIRECTION_DIVERGING_FAILURE = "The Gateway must NOT have multiple incoming but must have multiple outgoing Sequence Flows.";
ORYX.I18N.SyntaxChecker.BPMN2_GATEWAY_WITH_NO_OUTGOING_SEQUENCE_FLOW = "A Gateway must have a minimum of one outgoing Sequence Flow.";
ORYX.I18N.SyntaxChecker.BPMN2_RECEIVE_TASK_WITH_ATTACHED_EVENT = "Receive Tasks used in Event Gateway configurations must not have any attached Intermediate Events.";
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_SUBPROCESS_BAD_CONNECTION = "An Event Subprocess must not have any incoming or outgoing Sequence Flow.";

/** New Language Properties 13.10.2009 */
ORYX.I18N.SyntaxChecker.BPMN_MESSAGE_FLOW_NOT_CONNECTED = "消息流连接至少有一边.";

/** New Language Properties 24.11.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_TOO_MANY_INITIATING_MESSAGES = "A Choreography Activity may only have one initiating message.";
ORYX.I18N.SyntaxChecker.BPMN_MESSAGE_FLOW_NOT_ALLOWED = "消息流不允许.";

/** New Language Properties 27.11.2009 */
ORYX.I18N.SyntaxChecker.BPMN2_EVENT_BASED_WITH_TOO_LESS_INCOMING_SEQUENCE_FLOWS = "An Event-based Gateway that is not instantiating must have a minimum of one incoming Sequence Flow.";
ORYX.I18N.SyntaxChecker.BPMN2_TOO_FEW_INITIATING_PARTICIPANTS = "A Choreography Activity must have one initiating Participant (white).";
ORYX.I18N.SyntaxChecker.BPMN2_TOO_MANY_INITIATING_PARTICIPANTS = "A Choreography Acitivity must not have more than one initiating Participant (white)."

ORYX.I18N.SyntaxChecker.COMMUNICATION_AT_LEAST_TWO_PARTICIPANTS = "至少两个参与者才能交流.";
ORYX.I18N.SyntaxChecker.MESSAGEFLOW_START_MUST_BE_PARTICIPANT = "消息流源必须有一个参与者.";
ORYX.I18N.SyntaxChecker.MESSAGEFLOW_END_MUST_BE_PARTICIPANT = "消息流目标必须有一个参与者.";
ORYX.I18N.SyntaxChecker.CONV_LINK_CANNOT_CONNECT_CONV_NODES = "补偿链接必须使用参与者连接一个communication 或者子补偿节点.";
