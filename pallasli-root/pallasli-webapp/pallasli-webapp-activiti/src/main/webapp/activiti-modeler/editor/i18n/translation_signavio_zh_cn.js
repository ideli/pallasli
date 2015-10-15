ORYX.I18N.PropertyWindow.dateFormat = "d/m/y";

ORYX.I18N.View.East = "属性面板";
ORYX.I18N.View.West = "模型设计面板";

ORYX.I18N.Oryx.title	= "Activiti";
ORYX.I18N.Oryx.pleaseWait = "请等待，流程编辑器加载中...";
ORYX.I18N.Edit.cutDesc = "裁剪选区到剪贴板中";
ORYX.I18N.Edit.copyDesc = "拷贝选区到剪贴板中";
ORYX.I18N.Edit.pasteDesc = "粘贴到画板中";
ORYX.I18N.ERDFSupport.noCanvas = "XML文档中没有画布节点!";
ORYX.I18N.ERDFSupport.noSS = "流程编辑器面板结点没有模板集定义!";
ORYX.I18N.ERDFSupport.deprText = "不推荐导出到eRDF，添加未来版本不在支持，请尽量导出为JSON格式，你确定导出吗?";
ORYX.I18N.Save.pleaseWait = "请等待，保存中...";

ORYX.I18N.Save.saveAs = "另存为...";
ORYX.I18N.Save.saveAsDesc = "另存为...";
ORYX.I18N.Save.saveAsTitle = "另存为...";
ORYX.I18N.Save.savedAs = "保存成功";
ORYX.I18N.Save.savedDescription = "流程图已保存";
ORYX.I18N.Save.notAuthorized = "你未登录，请<a href='/p/login' target='_blank'>登录</a> 在新窗口中，以便你能够保存当前流程图."
ORYX.I18N.Save.transAborted = "保存时间过长.";
ORYX.I18N.Save.noRights = "你没有合适的权限保存模型.";
ORYX.I18N.Save.comFailed = "与服务器联系失败.";
ORYX.I18N.Save.failed = "保存流程图时发生错误，请再次尝试保存.";
ORYX.I18N.Save.exception = "当保存流程图发生错误.";
ORYX.I18N.Save.retrieveData = "请等待，数据在获取中.";

/** New Language Properties: 10.6.09*/
if(!ORYX.I18N.ShapeMenuPlugin) ORYX.I18N.ShapeMenuPlugin = {};
ORYX.I18N.ShapeMenuPlugin.morphMsg = "变换组件";
ORYX.I18N.ShapeMenuPlugin.morphWarningTitleMsg = "变换组件";
ORYX.I18N.ShapeMenuPlugin.morphWarningMsg = "当前有子组件包含，你确定改变吗?";

if (!Signavio) { var Signavio = {}; }
if (!Signavio.I18N) { Signavio.I18N = {} }
if (!Signavio.I18N.Editor) { Signavio.I18N.Editor = {} }

if (!Signavio.I18N.Editor.Linking) { Signavio.I18N.Editor.Linking = {} }
Signavio.I18N.Editor.Linking.CreateDiagram = "创建新流程图";
Signavio.I18N.Editor.Linking.UseDiagram = "使用已存在的流程图";
Signavio.I18N.Editor.Linking.UseLink = "使用WEB链接";
Signavio.I18N.Editor.Linking.Close = "关闭";
Signavio.I18N.Editor.Linking.Cancel = "取消";
Signavio.I18N.Editor.Linking.UseName = "采用流程图名称";
Signavio.I18N.Editor.Linking.UseNameHint = "使用链接流程图的名称替换当前模型元素名称({type}) .";
Signavio.I18N.Editor.Linking.CreateTitle = "新建链接";
Signavio.I18N.Editor.Linking.AlertSelectModel = "你必须选择一个模型.";
Signavio.I18N.Editor.Linking.ButtonLink = "链接流程图";
Signavio.I18N.Editor.Linking.LinkNoAccess = "你不能访问此流程图.";
Signavio.I18N.Editor.Linking.LinkUnavailable = "流程图不能使用.";
Signavio.I18N.Editor.Linking.RemoveLink = "删除链接";
Signavio.I18N.Editor.Linking.EditLink = "编辑链接";
Signavio.I18N.Editor.Linking.OpenLink = "打开";
Signavio.I18N.Editor.Linking.BrokenLink = "链接已中断!";
Signavio.I18N.Editor.Linking.PreviewTitle = "预览";

if(!Signavio.I18N.Glossary_Support) { Signavio.I18N.Glossary_Support = {}; }
Signavio.I18N.Glossary_Support.renameEmpty = "没有字典项目";
Signavio.I18N.Glossary_Support.renameLoading = "查询中...";

/** New Language Properties: 08.09.2009*/
if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};
ORYX.I18N.PropertyWindow.PropertyTitle="属性集";
ORYX.I18N.PropertyWindow.oftenUsed = "主要属性";
ORYX.I18N.PropertyWindow.moreProps = "附属属性";

ORYX.I18N.PropertyWindow.btnOpen = "打开";
ORYX.I18N.PropertyWindow.btnRemove = "删除";
ORYX.I18N.PropertyWindow.btnEdit = "编辑";
ORYX.I18N.PropertyWindow.btnUp = "向上移动";
ORYX.I18N.PropertyWindow.btnDown = "向下移动";
ORYX.I18N.PropertyWindow.createNew = "新建";

if(!ORYX.I18N.PropertyWindow) ORYX.I18N.PropertyWindow = {};
ORYX.I18N.PropertyWindow.oftenUsed = "主要属性";
ORYX.I18N.PropertyWindow.moreProps = "附属属性";
ORYX.I18N.PropertyWindow.characteristicNr = "Cost &amp; Resource Analysis";
ORYX.I18N.PropertyWindow.meta = "自定义属性";

if(!ORYX.I18N.PropertyWindow.Category){ORYX.I18N.PropertyWindow.Category = {}}
ORYX.I18N.PropertyWindow.Category.popular = "主要属性";
ORYX.I18N.PropertyWindow.Category.characteristicnr = "Cost &amp; Resource Analysis";
ORYX.I18N.PropertyWindow.Category.others = "附属属性";
ORYX.I18N.PropertyWindow.Category.meta = "自定义属性";

ORYX.I18N.PropertyWindow.Category.custom={};
ORYX.I18N.PropertyWindow.Category.custom.basic='基本';
ORYX.I18N.PropertyWindow.Category.custom.form='表单';
ORYX.I18N.PropertyWindow.Category.custom.handler='参与者';
ORYX.I18N.PropertyWindow.Category.custom.time='时间';
ORYX.I18N.PropertyWindow.Category.custom.message='消息';
ORYX.I18N.PropertyWindow.Category.custom.extendProperty='扩展属性';
ORYX.I18N.PropertyWindow.Category.custom.index='绩效指标';

if(!ORYX.I18N.PropertyWindow.ListView) ORYX.I18N.PropertyWindow.ListView = {};
ORYX.I18N.PropertyWindow.ListView.title = "编辑: ";
ORYX.I18N.PropertyWindow.ListView.dataViewLabel = "已经存在此项目.";
ORYX.I18N.PropertyWindow.ListView.dataViewEmptyText = "没有列表项目.";
ORYX.I18N.PropertyWindow.ListView.addEntryLabel = "新增一个条目";
ORYX.I18N.PropertyWindow.ListView.buttonAdd = "新增";
ORYX.I18N.PropertyWindow.ListView.save = "保存";
ORYX.I18N.PropertyWindow.ListView.cancel = "取消";

if(!Signavio.I18N.Buttons) Signavio.I18N.Buttons = {};
Signavio.I18N.Buttons.save		= "保存";
Signavio.I18N.Buttons.cancel 	= "取消";
Signavio.I18N.Buttons.remove	= "移动";

if(!Signavio.I18N.btn) {Signavio.I18N.btn = {};}
Signavio.I18N.btn.btnEdit = "编辑";
Signavio.I18N.btn.btnRemove = "删除";
Signavio.I18N.btn.moveUp = "向上移动";
Signavio.I18N.btn.moveDown = "向下移动";

if(!Signavio.I18N.field) {Signavio.I18N.field = {};}
Signavio.I18N.field.Url = "URL";
Signavio.I18N.field.UrlLabel = "Label";
