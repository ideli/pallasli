package org.eclipse.birt.report.taglib;

//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//import java.util.TimeZone;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspTagException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.PageContext;
//import javax.servlet.jsp.tagext.BodyTagSupport;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import org.eclipse.birt.report.resource.BirtResources;
//import org.eclipse.birt.report.service.BirtReportServiceFactory;
//import org.eclipse.birt.report.service.ParameterDataTypeConverter;
//import org.eclipse.birt.report.service.api.IViewerReportDesignHandle;
//import org.eclipse.birt.report.service.api.InputOptions;
//import org.eclipse.birt.report.service.api.ParameterDefinition;
//import org.eclipse.birt.report.service.api.ParameterGroupDefinition;
//import org.eclipse.birt.report.service.api.ParameterSelectionChoice;
//import org.eclipse.birt.report.service.api.ReportServiceException;
//import org.eclipse.birt.report.taglib.component.ParamDefField;
//import org.eclipse.birt.report.taglib.component.ViewerField;
//import org.eclipse.birt.report.taglib.util.BirtTagUtil;
//import org.eclipse.birt.report.utility.BirtUtility;
//import org.eclipse.birt.report.utility.DataUtil;
//import org.eclipse.birt.report.utility.ParameterAccessor;
public class ParamDefTag {
	// public class ParamDefTag extends BodyTagSupport {
	// private static final long serialVersionUID = -1255870121526790060L;
	// private ParamDefField param;
	// private RequesterTag requesterTag;
	// private ViewerField viewer;
	// private ParameterDefinition paramDef;
	// private InputOptions options;
	// private boolean isLocale;
	// private Locale locale;
	// private TimeZone timeZone;
	// private String pattern;
	// private String valueString;
	// private List valueStringList;
	// private String displayTextString;
	// private String groupObjName;
	// private static final String IMPORT_FILES_ATTR = "IMPORT_FILES_FLAG";
	// private static final String ATTR_ID = "ID_";
	// private static final String ATTR_PARAM = "PARAM_";
	//
	// public ParamDefTag() {
	// this.isLocale = false;
	// }
	//
	// public void setPageContext(PageContext context) {
	// super.setPageContext(context);
	// this.param = new ParamDefField();
	// }
	//
	// public int doEndTag() throws JspException {
	// try {
	// if (__validate()) {
	// this.requesterTag = ((RequesterTag) TagSupport
	// .findAncestorWithClass(this, RequesterTag.class));
	//
	// if (this.requesterTag != null) {
	// this.viewer = this.requesterTag.viewer;
	// if (this.viewer.isCustom()) {
	// __beforeEndTag();
	// __process();
	// }
	// }
	// }
	// } catch (Exception e) {
	// __handleException(e);
	// }
	// return super.doEndTag();
	// }
	//
	// protected boolean __validate() throws Exception {
	// if (!(this.param.validate())) {
	// return false;
	// }
	//
	// Pattern p = Pattern.compile("^\\w+$");
	// Matcher m = p.matcher(this.param.getId());
	// if (!(m.find())) {
	// throw new JspTagException(
	// BirtResources
	// .getMessage("birt.viewer.taglib.INVALID_ATTR_ID"));
	// }
	//
	// if (this.pageContext.findAttribute("ID_" + this.param.getId()) != null) {
	// throw new JspTagException(
	// BirtResources
	// .getMessage("birt.viewer.taglib.ATTR_ID_DUPLICATE"));
	// }
	//
	// if (this.requesterTag == null) {
	// if (this.pageContext.findAttribute("PARAM_" + this.param.getName()) ==
	// null)
	// return true;
	// throw new JspTagException(BirtResources.getMessage(
	// "birt.viewer.taglib.PARAM_NAME_DUPLICATE",
	// new String[] { this.param.getName() }));
	// }
	//
	// if (this.requesterTag.getParameters().get(this.param.getName()) != null)
	// {
	// throw new JspTagException(BirtResources.getMessage(
	// "birt.viewer.taglib.PARAM_NAME_DUPLICATE",
	// new String[] { this.param.getName() }));
	// }
	//
	// return true;
	// }
	//
	// protected void __beforeEndTag() {
	// this.pageContext.setAttribute("ID_" + this.param.getId(),
	// this.param.getId());
	//
	// if (this.requesterTag == null) {
	// this.pageContext.setAttribute("PARAM_" + this.param.getName(),
	// this.param.getName());
	// }
	// }
	//
	// protected void __process() throws Exception {
	// if (this.viewer == null)
	// return;
	//
	// HttpServletRequest request = (HttpServletRequest) this.pageContext
	// .getRequest();
	//
	// this.locale = BirtTagUtil.getLocale(request, this.viewer.getLocale());
	// this.timeZone = BirtTagUtil.getTimeZone(request,
	// this.viewer.getTimeZone());
	//
	// this.options = new InputOptions();
	// this.options.setOption("request", request);
	// this.options.setOption("locale", this.locale);
	// this.options.setOption("timeZone", this.timeZone);
	// this.options.setOption("rtl", Boolean.valueOf(this.viewer.getRtl()));
	//
	// Collection paramDefList = this.viewer.getParameterDefList();
	// if (paramDefList == null) {
	// BirtReportServiceFactory.getReportService().setContext(
	// this.pageContext.getServletContext(), this.options);
	//
	// IViewerReportDesignHandle designHandle = BirtTagUtil
	// .getDesignHandle(request, this.viewer);
	//
	// this.viewer.setReportDesignHandle(designHandle);
	//
	// paramDefList = BirtReportServiceFactory.getReportService()
	// .getParameterDefinitions(designHandle, this.options, false);
	//
	// this.viewer.setParameterDefList(paramDefList);
	// }
	//
	// this.paramDef = BirtUtility.findParameterDefinition(paramDefList,
	// this.param.getName());
	//
	// if (this.paramDef == null) {
	// return;
	// }
	//
	// String dataType = ParameterDataTypeConverter
	// .convertDataType(this.paramDef.getDataType());
	//
	// this.pattern = this.param.getPattern();
	// if (this.pattern == null)
	// this.pattern = this.paramDef.getPattern();
	//
	// if ("true".equalsIgnoreCase(this.param.getIsLocale()))
	// this.isLocale = true;
	// else {
	// this.isLocale = false;
	// }
	//
	// if (this.param.getValue() != null) {
	// if (this.param.getValue() instanceof String) {
	// Object valueObj = DataUtil.validateWithPattern(
	// this.param.getName(), dataType, this.pattern,
	// (String) this.param.getValue(), this.locale,
	// this.timeZone, this.isLocale);
	//
	// if (this.paramDef.isMultiValue())
	// this.param.setValue(new Object[] { valueObj });
	// else
	// this.param.setValue(valueObj);
	// } else if ((this.paramDef.isMultiValue())
	// && (this.param.getValue() instanceof String[])) {
	// String[] sValues = (String[]) (String[]) this.param.getValue();
	// Object[] values = new Object[sValues.length];
	// for (int i = 0; i < sValues.length; ++i) {
	// Object valueObj = DataUtil.validateWithPattern(
	// this.param.getName(), dataType, this.pattern,
	// sValues[i], this.locale, this.timeZone,
	// this.isLocale);
	//
	// values[i] = valueObj;
	// }
	// this.param.setValue(values);
	// }
	// } else {
	// Object defaultValue = BirtReportServiceFactory.getReportService()
	// .getParameterDefaultValue(
	// this.viewer.getReportDesignHandle(),
	// this.param.getName(), this.options);
	//
	// if ((this.paramDef.isMultiValue())
	// && (!(defaultValue instanceof Object[]))) {
	// defaultValue = new Object[] { defaultValue };
	// }
	//
	// this.param.setValue(defaultValue);
	// }
	//
	// if (this.paramDef.isMultiValue()) {
	// this.valueStringList = new ArrayList();
	// Object[] values = (Object[]) (Object[]) this.param.getValue();
	// if (values != null) {
	// for (int i = 0; i < values.length; ++i) {
	// String value = DataUtil.getDisplayValue(values[i],
	// this.timeZone);
	// this.valueStringList.add(value);
	// }
	// }
	// } else {
	// this.valueString = DataUtil.getDisplayValue(this.param.getValue(),
	// this.timeZone);
	// if (this.valueString == null)
	// this.valueString = "";
	//
	// }
	//
	// this.displayTextString = this.param.getDisplayText();
	// if (this.displayTextString == null) {
	// Object obj = this.param.getValue();
	// if (obj != null) {
	// if (obj instanceof Object[]) {
	// Object[] objs = (Object[]) (Object[]) obj;
	// if (objs.length > 0)
	// obj = objs[0];
	// else
	// obj = null;
	// }
	//
	// this.displayTextString = DataUtil.getDisplayValue(dataType,
	// this.pattern, obj, this.locale, this.timeZone);
	// }
	// }
	//
	// if (this.displayTextString == null) {
	// this.displayTextString = "";
	// }
	//
	// if (this.param.getTitle() == null) {
	// this.param.setTitle(this.displayTextString);
	// }
	//
	// this.requesterTag.addParameter(this.param.getName(),
	// this.param.getValue());
	//
	// if (this.paramDef.isHidden()) {
	// __handleHidden();
	// } else {
	// switch (this.paramDef.getControlType()) {
	// case 0:
	// __handleTextBox();
	// break;
	// case 1:
	// __handleListBox();
	// break;
	// case 2:
	// __handleRadioButton();
	// break;
	// case 3:
	// __handleCheckBox();
	// }
	// }
	// }
	//
	// protected void __handleHidden() throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	//
	// boolean isNullValue = this.param.getValue() == null;
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + encParamId + "\" ");
	// if (!(isNullValue)) {
	// writer.write(" name=\"" + encParamName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.valueString) + "\" ");
	// }
	//
	// writer.write(" >\n");
	//
	// String displayTextId = encParamId + "_displayText";
	// String displayTextName = "__isdisplay__" + encParamName;
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + displayTextId + "\" ");
	// if (!(isNullValue)) {
	// writer.write(" name=\"" + displayTextName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.displayTextString)
	// + "\" ");
	// }
	//
	// writer.write(" >\n");
	// }
	//
	// protected void __handleGeneralDefinition() throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// if (this.param.getTitle() != null)
	// writer.write(" title=\"" + this.param.getTitle() + "\" ");
	//
	// if (this.param.getCssClass() != null)
	// writer.write(" class=\"" + this.param.getCssClass() + "\" ");
	//
	// if (this.param.getStyle() != null)
	// writer.write(" style=\"" + this.param.getStyle() + "\" ");
	// }
	//
	// protected void __handleTextBox() throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	//
	// boolean isNullValue = this.param.getValue() == null;
	//
	// String displayTextId = encParamId + "_displayText";
	// String displayTextName = "__isdisplay__" + encParamName;
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + displayTextId + "\" ");
	// if ((this.paramDef.isRequired()) || (!(isNullValue))) {
	// writer.write(" name=\"" + displayTextName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.displayTextString)
	// + "\" ");
	// }
	//
	// writer.write(" >\n");
	//
	// String valueId = encParamId + "_value";
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + valueId + "\" ");
	// writer.write(" name=\"" + encParamName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.valueString) + "\" ");
	//
	// writer.write(" >\n");
	//
	// String isLocaleId = encParamId + "_islocale";
	// writer.write("<input type=\"hidden\" id=\"" + isLocaleId
	// + "\" value=\"" + encParamName + "\" >\n");
	//
	// String patternId = encParamId + "_pattern";
	// String patternName = encParamName + "_format";
	// if (this.param.getPattern() != null) {
	// writer.write("<input type = 'hidden' id=\"" + patternId + "\" \n");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.param.getPattern())
	// + "\">\n");
	// }
	//
	// writer.write("\n<script language=\"JavaScript\">\n");
	// writer.write("function handleParam" + encParamId + "( )\n");
	// writer.write("{\n");
	// writer.write("var inputCtl = document.getElementById(\"" + encParamId
	// + "\");\n");
	//
	// writer.write("var valCtl = document.getElementById(\"" + valueId
	// + "\");\n");
	//
	// writer.write("var displayCtl = document.getElementById(\""
	// + displayTextId + "\");\n");
	//
	// writer.write("var localeCtl = document.getElementById(\"" + isLocaleId
	// + "\");\n");
	//
	// writer.write("var patternCtl = document.getElementById(\"" + patternId
	// + "\");\n");
	//
	// writer.write("displayCtl.value=inputCtl.value;\n");
	// writer.write("valCtl.value=inputCtl.value;\n");
	// writer.write("localeCtl.name='__islocale';\n");
	//
	// writer.write("if( patternCtl ) patternCtl.name=\"" + patternName
	// + "\";\n");
	//
	// writer.write("}\n");
	// writer.write("</script>\n");
	//
	// String controlType = (this.paramDef.concealValue()) ? "PASSWORD"
	// : "TEXT";
	// if (this.paramDef.isRequired()) {
	// writer.write("<input type=\"" + controlType + "\" ");
	// writer.write(" id=\"" + encParamId + "\" ");
	// __handleGeneralDefinition();
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.displayTextString)
	// + "\" ");
	//
	// writer.write(" onchange=\"handleParam" + encParamId + "( )\"");
	// writer.write(" >\n");
	// } else {
	// String nullValueId = encParamId + "_null";
	// String radioTextValueId = encParamId + "_radio_input";
	// String radioNullValueId = encParamId + "_radio_null";
	//
	// writer.write("\n<script language=\"JavaScript\">\n");
	// writer.write("function switchParam" + encParamId + "( flag )\n");
	// writer.write("{\n");
	// writer.write("var inputCtl = document.getElementById(\""
	// + encParamId + "\");\n");
	//
	// writer.write("var displayCtl = document.getElementById(\""
	// + displayTextId + "\");\n");
	//
	// writer.write("var nullCtl = document.getElementById(\""
	// + nullValueId + "\");\n");
	//
	// writer.write("var radioTextCtl = document.getElementById(\""
	// + radioTextValueId + "\");\n");
	//
	// writer.write("var radioNullCtl = document.getElementById(\""
	// + radioNullValueId + "\");\n");
	//
	// writer.write("if( flag ) \n");
	// writer.write("{\n");
	// writer.write("\tradioTextCtl.checked=true;\n");
	// writer.write("\tradioNullCtl.checked=false;\n");
	// writer.write("\tinputCtl.disabled=false;\n");
	// writer.write("\tnullCtl.name='';\n");
	// writer.write("\tdisplayCtl.name='" + displayTextName + "';\n");
	// writer.write("}\n");
	// writer.write("else\n");
	// writer.write("{\n");
	// writer.write("\tradioTextCtl.checked=false;\n");
	// writer.write("\tradioNullCtl.checked=true;\n");
	// writer.write("\tinputCtl.disabled=true;\n");
	// writer.write("\tnullCtl.name='__isnull';\n");
	//
	// writer.write("\tdisplayCtl.name='';\n");
	// writer.write("}\n");
	// writer.write("}\n");
	// writer.write("</script>\n");
	//
	// writer.write("<input type=\"hidden\" value=\"" + encParamName
	// + "\" id=\"" + nullValueId + "\"");
	//
	// if (isNullValue)
	// writer.write(" name=\"__isnull\"");
	//
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"radio\" id=\"" + radioTextValueId
	// + "\" ");
	//
	// writer.write(" onclick=\"switchParam" + encParamId + "( true )\"");
	// if (!(isNullValue))
	// writer.write(" checked ");
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"" + controlType + "\" ");
	// writer.write(" id=\"" + encParamId + "\" ");
	// __handleGeneralDefinition();
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.displayTextString)
	// + "\" ");
	//
	// writer.write(" onchange=\"handleParam" + encParamId + "( )\"");
	// if (isNullValue)
	// writer.write(" disabled = 'true' ");
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"radio\" id=\"" + radioNullValueId
	// + "\" ");
	//
	// writer.write(" onclick=\"switchParam" + encParamId + "( false )\"");
	//
	// if (isNullValue)
	// writer.write(" checked ");
	// writer.write(" >");
	// writer.write("<label id=\"" + radioNullValueId + "_label" + "\"");
	//
	// writer.write(" title=\"Null Value\"");
	// writer.write(" for=\"" + radioNullValueId + "\">");
	// writer.write("Null Value");
	// writer.write("</label>");
	// writer.write("</input>\n");
	// }
	// }
	//
	// protected void __handleListBox() throws Exception {
	// if ((this.paramDef.getGroup() != null)
	// && (this.paramDef.getGroup().cascade())) {
	// JspWriter writer = this.pageContext.getOut();
	//
	// if (this.pageContext.findAttribute("IMPORT_FILES_FLAG") == null) {
	// String baseURL = "/webcontent/";
	// if (this.viewer.getBaseURL() != null) {
	// baseURL = this.viewer.getBaseURL() + baseURL;
	// } else {
	// baseURL = ((HttpServletRequest) this.pageContext
	// .getRequest()).getContextPath() + baseURL;
	// }
	//
	// writer.write("\n<LINK REL=\"stylesheet\" HREF=\"" + baseURL
	// + "birt/styles/style.css\" TYPE=\"text/css\">\n");
	//
	// BirtTagUtil.writeExtScripts(writer, baseURL + "birt/ajax/",
	// new String[] { "lib/prototype.js", "lib/head.js",
	// "utility/Debug.js", "utility/Constants.js",
	// "utility/BirtUtility.js",
	// "utility/BirtPosition.js",
	// "core/BirtSoapRequest.js", "core/BirtEvent.js",
	// "taglib/CascadingParameter.js",
	// "taglib/ParameterGroup.js",
	// "taglib/ParameterDefinition.js",
	// "taglib/SoapResponseHelper.js",
	// "taglib/ProgressBar.js" });
	//
	// __createProgressBar(baseURL);
	//
	// BirtTagUtil
	// .writeScript(
	// writer,
	// "var progressBar = new ProgressBar( \"progressBar\",\"mask\" );Constants.nullValue = \"$${{((null))}}$$\";\n");
	//
	// this.pageContext
	// .setAttribute("IMPORT_FILES_FLAG", Boolean.TRUE);
	// }
	//
	// this.groupObjName = "group_" + this.viewer.getId() + "_"
	// + this.paramDef.getGroup().getName();
	// if (this.pageContext.findAttribute(this.groupObjName) == null) {
	// writer.write("<script  language=\"JavaScript\">var "
	// + this.groupObjName
	// + " = new ParameterGroup( );</script>\n");
	//
	// this.pageContext.setAttribute(this.groupObjName, Boolean.TRUE);
	// }
	//
	// Collection selectionList = getParameterSelectionListForCascadingGroup();
	// __handleCommonListBox(selectionList);
	// __handleCascadingListBox();
	// } else {
	// Collection selectionList = BirtReportServiceFactory
	// .getReportService().getParameterSelectionList(
	// this.viewer.getReportDesignHandle(), this.options,
	// this.param.getName());
	//
	// if (this.paramDef.isMultiValue())
	// __handleMultiListBox(selectionList);
	// else
	// __handleCommonListBox(selectionList);
	// }
	// }
	//
	// protected void __createProgressBar(String baseURL) throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// writer.write("<DIV ID=\"mask\" STYLE=\"display:none;position:absolute;z-index:200\">\n");
	//
	// writer.write("</DIV>\n");
	//
	// writer.write("<DIV ID=\"progressBar\" STYLE=\"display:none;position:absolute;z-index:300\">\n");
	//
	// writer.write("<TABLE WIDTH=\"250px\" CLASS=\"birtviewer_progressbar\" CELLSPACING=\"10px\">\n");
	//
	// writer.write("\t<TR>\n");
	// writer.write("\t\t<TD ALIGN=\"center\">\n");
	// writer.write("\t\t\t<B>"
	// + BirtResources.getMessage("birt.viewer.progressbar.prompt")
	// + "</B>\n");
	//
	// writer.write("\t\t</TD>\n");
	// writer.write("\t</TR>\n");
	// writer.write("\t<TR>\n");
	// writer.write("\t\t<TD ALIGN=\"center\">\n");
	// writer.write("\t\t\t<IMG SRC=\"" + baseURL
	// + "birt/images/Loading.gif\" ALT=\"Progress Bar Image\"/>\n");
	//
	// writer.write("\t\t</TD>\n");
	// writer.write("\t</TR>\n");
	// writer.write("\t<TR>\n");
	// writer.write("\t\t<TD ALIGN=\"center\">\n");
	// writer.write("\t\t\t<DIV ID=\"cancelTaskButton\" STYLE=\"display:block\">\n");
	//
	// writer.write("\t\t\t\t<TABLE WIDTH=\"100%\">\n");
	// writer.write("\t\t\t\t\t<TR>\n");
	// writer.write("\t\t\t\t\t\t<TD ALIGN=\"center\">\n");
	// writer.write("\t\t\t\t\t\t\t<INPUT TYPE=\"BUTTON\" VALUE=\""
	// + BirtResources.getMessage("birt.viewer.dialog.cancel")
	// + "\" \n");
	//
	// writer.write("\t\t\t\t\t\t\t\t\tTITLE=\""
	// + BirtResources.getMessage("birt.viewer.dialog.cancel")
	// + "\" \n");
	//
	// writer.write("\t\t\t\t\t\t\t\t\tCLASS=\"birtviewer_progressbar_button\"/>\n");
	// writer.write("\t\t\t\t\t\t</TD>\n");
	// writer.write("\t\t\t\t\t</TR>\n");
	// writer.write("\t\t\t\t</TABLE>\n");
	// writer.write("\t\t\t</DIV>\n");
	// writer.write("\t\t</TD>\n");
	// writer.write("\t</TR>\n");
	// writer.write("</TABLE>\n");
	// writer.write("</DIV>\n");
	// writer.write("<INPUT TYPE=\"HIDDEN\" ID=\"taskid\" VALUE=''/>\n");
	// }
	//
	// protected void __handleMultiListBox(Collection selectionList)
	// throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	//
	// String containerId = encParamId + "_container";
	// String displayTextName = "__isdisplay__" + encParamName;
	//
	// String content = "function handleParam" + encParamId + "( oCtl )\n"
	// + "{\n" + "  if( !oCtl ) return;\n"
	// + "  var container = document.getElementById(\"" + containerId
	// + "\");\n" + " while( container.childNodes.length > 0)\n"
	// + "{\n" + "  container.removeChild(container.firstChild);\n"
	// + "}\n" + "\n" + "  var options = oCtl.options;\n"
	// + "  for( var i = 0; i < options.length; i++ )\n" + "  {\n"
	// + "    if( !options[i].selected ) continue;\n" + "\n"
	// + "    var text = options[i].text;\n"
	// + "    var value = options[i].value;\n" + "\n"
	// + "  if( value == '" + "$${{((null))}}$$" + "')\n" + "    {\n"
	// + "      var oInput = document.createElement( 'input' );\n"
	// + "      oInput.type = 'hidden';\n" + "      oInput.name = '"
	// + "__isnull" + "';\n" + "      oInput.value = \""
	// + encParamName + "\";\n"
	// + "      container.appendChild( oInput );\n" + "    }\n" + "\n"
	// + "    var oInput = document.createElement( 'input' );\n"
	// + "    oInput.type = 'hidden';\n" + "    oInput.name = \""
	// + encParamName + "\";\n" + "    oInput.value = value;\n"
	// + "    container.appendChild( oInput );\n" + "\n"
	// + "    var oInput = document.createElement( 'input' );\n"
	// + "    oInput.type = 'hidden';\n" + "    oInput.name = \""
	// + displayTextName + "\";\n" + "    oInput.value = text;\n"
	// + "    container.appendChild( oInput );\n" + "  }\n";
	//
	// if (this.isLocale) {
	// content = content
	// +
	// "\n  var oInput = document.createElement( 'input' );\n  oInput.type = 'hidden';\n  oInput.name = \"__islocale\";\n  oInput.value = \""
	// + encParamName + "\";\n"
	// + "  container.appendChild( oInput );\n";
	// }
	//
	// content = content + "}\n";
	// BirtTagUtil.writeScript(writer, content);
	//
	// String onChange = "handleParam" + encParamId + "( this )";
	//
	// writer.write("<div id=\"" + containerId
	// + "\" style=\"display:none;\"></div>");
	//
	// writer.write("<select ");
	// writer.write(" id=\"" + encParamId + "\"");
	// __handleGeneralDefinition();
	// writer.write(" onchange=\"" + onChange + "\"");
	// writer.write(" multiple='true'");
	// writer.write(" >\n");
	//
	// makeOption(writer, selectionList, this.valueStringList);
	//
	// writer.write("</select>\n");
	//
	// BirtTagUtil.writeScript(writer,
	// "var selectCtl = document.getElementById(\"" + encParamId
	// + "\");\n" + "if( selectCtl.options.length > 8 )\n"
	// + "  selectCtl.size = 8;\n" + "else\n"
	// + "  selectCtl.size = selectCtl.options.length;\n"
	// + "handleParam" + encParamId + "( selectCtl );\n");
	// }
	//
	// private void makeOption(JspWriter writer, Collection items,
	// List selectedItems) throws IOException {
	// boolean nullValueFound = false;
	//
	// if (!(this.paramDef.isRequired())) {
	// BirtTagUtil.writeOption(writer, "", "",
	// DataUtil.contain(this.valueStringList, "", true));
	// }
	//
	// for (Iterator iter = items.iterator(); iter.hasNext();) {
	// ParameterSelectionChoice selectionItem = (ParameterSelectionChoice) iter
	// .next();
	//
	// Object value = selectionItem.getValue();
	// try {
	// value = DataUtil.convert(value, this.paramDef.getDataType());
	// } catch (Exception e) {
	// value = null;
	// }
	//
	// String displayValue = DataUtil
	// .getDisplayValue(value, this.timeZone);
	// if (value == null) {
	// nullValueFound = true;
	// }
	//
	// String label = selectionItem.getLabel();
	// if ((label == null) || (label.length() <= 0)) {
	// label = DataUtil.getDisplayValue(null, this.pattern, value,
	// this.locale, this.timeZone);
	// }
	//
	// label = (label != null) ? label : "";
	//
	// BirtTagUtil.writeOption(writer, label,
	// (displayValue == null) ? "$${{((null))}}$$" : displayValue,
	// DataUtil.contain(selectedItems, displayValue, true));
	// }
	//
	// if ((!(this.paramDef.isRequired())) && (!(nullValueFound))) {
	// BirtTagUtil.writeOption(writer, "Null Value", "$${{((null))}}$$",
	// DataUtil.contain(selectedItems, null, true));
	// }
	// }
	//
	// protected void __handleCommonListBox(Collection selectionList)
	// throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	//
	// String displayTextId = encParamId + "_displayText";
	// String displayTextName = "__isdisplay__" + encParamName;
	//
	// boolean isSelected = false;
	// boolean isNullValue = this.param.getValue() == null;
	// String valueId = encParamId + "_value";
	// String nullValueId = encParamId + "_null";
	//
	// String radioSelectId = encParamId + "_radio_select";
	// String radioTextId = encParamId + "_radio_input";
	// String inputTextId = encParamId + "_input";
	//
	// String isLocaleId = encParamId + "_islocale";
	// String patternId = encParamId + "_pattern";
	// String patternName = encParamName + "_format";
	//
	// if (!(this.paramDef.mustMatch())) {
	// BirtTagUtil.writeScript(writer, "function updateParam" + encParamId
	// + "( flag )\n" + "{\n"
	// + "var radioSelectCtl = document.getElementById(\""
	// + radioSelectId + "\");\n"
	// + "if( radioSelectCtl ) radioSelectCtl.checked = flag;\n"
	// + "var radioTextCtl = document.getElementById(\""
	// + radioTextId + "\");\n"
	// + "if( radioTextCtl ) radioTextCtl.checked = !flag;\n"
	// + "var selectCtl = document.getElementById(\"" + encParamId
	// + "\");\n"
	// + "if( selectCtl ) selectCtl.disabled = !flag;\n"
	// + "var inputCtl = document.getElementById(\"" + inputTextId
	// + "\");\n" + "if( inputCtl ) inputCtl.disabled = flag;\n"
	// + "var localeCtl = document.getElementById(\"" + isLocaleId
	// + "\");\n" + "if( localeCtl )\n" + "{\n" + "  if( flag )\n"
	// + "    localeCtl.name = '';\n" + "  else\n"
	// + "    localeCtl.name = \"" + "__islocale" + "\";\n"
	// + "}\n" + "if( flag )\n" + "{\n"
	// + "  if( selectCtl.selectedIndex >= 0 )\n"
	// + "    handleParam" + encParamId
	// + "( selectCtl.options[selectCtl.selectedIndex] );\n"
	// + "  else\n" + "  {\n"
	// + "    var nullCtl = document.getElementById(\""
	// + nullValueId + "\");\n"
	// + "    if( nullCtl ) nullCtl.name=\"" + "__isnull"
	// + "\";\n" + "    var valCtl = document.getElementById(\""
	// + valueId + "\");\n"
	// + "    if( valCtl ) valCtl.name = '';\n"
	// + "    if( valCtl ) valCtl.value = '';\n"
	// + "    var displayCtl = document.getElementById(\""
	// + displayTextId + "\");\n"
	// + "    if( displayCtl ) displayCtl.value = '';\n"
	// + "    if( displayCtl ) displayCtl.name = '';\n" + "  }\n"
	// + "}\n" + "else\n" + "{\n" + "  handleTextParam"
	// + encParamId + "( );\n" + "}\n" + "}\n"
	// + "function handleTextParam" + encParamId + "( )\n" + "{\n"
	// + "var inputCtl = document.getElementById(\"" + inputTextId
	// + "\");\n" + "var valCtl = document.getElementById(\""
	// + valueId + "\");\n" + "if( valCtl ) valCtl.name = \""
	// + encParamName + "\";\n"
	// + "if( valCtl ) valCtl.value = inputCtl.value;\n"
	// + "var displayCtl = document.getElementById(\""
	// + displayTextId + "\");\n"
	// + "if( displayCtl ) displayCtl.name = \"" + displayTextName
	// + "\";\n"
	// + "if( displayCtl ) displayCtl.value = inputCtl.value;\n"
	// + "var nullCtl = document.getElementById(\"" + nullValueId
	// + "\");\n" + "if( nullCtl ) nullCtl.name='';\n"
	// + "var localeCtl = document.getElementById(\"" + isLocaleId
	// + "\");\n" + "if( localeCtl ) localeCtl.name = \""
	// + "__islocale" + "\";\n" + "}\n"
	// + "function changeTextParam" + encParamId + "( )\n" + "{\n"
	// + "var patternCtl = document.getElementById(\"" + patternId
	// + "\");\n" + "if( patternCtl ) patternCtl.name = \""
	// + patternName + "\";\n" + "  handleTextParam" + encParamId
	// + "( );\n" + "}\n");
	// }
	//
	// BirtTagUtil.writeScript(writer, "function handleParam" + encParamId
	// + "( option )\n" + "{\n" + "if( !option ) return;\n"
	// + "var valCtl = document.getElementById(\"" + valueId
	// + "\");\n" + "var displayCtl = document.getElementById(\""
	// + displayTextId + "\");\n"
	// + "var nullCtl = document.getElementById(\"" + nullValueId
	// + "\");\n" + "var label = option.text;\n"
	// + "var value = option.value;\n" + "if( value == \""
	// + "$${{((null))}}$$" + "\")\n" + "{\n"
	// + "  if( nullCtl ) nullCtl.name=\"" + "__isnull" + "\";\n"
	// + "  if( valCtl ) valCtl.name = '';\n"
	// + "  if( valCtl ) valCtl.value = '';\n"
	// + "  if( displayCtl ) displayCtl.value = '';\n"
	// + "  if( displayCtl ) displayCtl.name = '';\n" + "}\n"
	// + "else\n" + "{\n" + "  if( nullCtl ) nullCtl.name='';\n"
	// + "  if( valCtl ) valCtl.name = \"" + encParamName + "\";\n"
	// + "  if( valCtl ) valCtl.value = value;\n"
	// + "  if( displayCtl ) displayCtl.name = \"" + displayTextName
	// + "\";\n" + "  if( displayCtl ) displayCtl.value = label;\n"
	// + "}\n" + "}\n");
	//
	// String onChange = "handleParam" + encParamId
	// + "( this.options[this.selectedIndex] )";
	// if (!(this.paramDef.mustMatch())) {
	// String onClick = "updateParam" + encParamId + "( true )";
	// writer.write("<input type=\"radio\" ");
	// writer.write(" id=\"" + radioSelectId + "\"");
	// writer.write(" onclick=\"" + onClick + "\"");
	// writer.write(" >\n");
	// }
	//
	// writer.write("<select ");
	// writer.write(" id=\"" + encParamId + "\"");
	// __handleGeneralDefinition();
	// writer.write(" onchange=\"" + onChange + "\"");
	// writer.write(" >\n");
	//
	// if (!(this.paramDef.isRequired())) {
	// if ((this.param.getValue() != null)
	// && (DataUtil.getString(this.param.getValue()).length() <= 0)) {
	// isSelected = true;
	// }
	// BirtTagUtil.writeOption(writer, "", "", isSelected);
	// }
	//
	// boolean nullValueFound = false;
	// for (Iterator iter = selectionList.iterator(); iter.hasNext();) {
	// ParameterSelectionChoice selectionItem = (ParameterSelectionChoice) iter
	// .next();
	//
	// Object value = selectionItem.getValue();
	// try {
	// value = DataUtil.convert(value, this.paramDef.getDataType());
	// } catch (Exception e) {
	// value = null;
	// }
	//
	// String displayValue = DataUtil
	// .getDisplayValue(value, this.timeZone);
	//
	// String label = selectionItem.getLabel();
	// if ((label == null) || (label.length() <= 0)) {
	// label = DataUtil.getDisplayValue(null, this.pattern, value,
	// this.locale, this.timeZone);
	// }
	//
	// if (value == null) {
	// nullValueFound = true;
	// if (label == null) {
	// label = "Null Value";
	// }
	// }
	//
	// label = (label != null) ? label : "";
	// boolean selected = false;
	// if (DataUtil.equals(displayValue, DataUtil.getDisplayValue(
	// this.param.getValue(), this.timeZone))) {
	// selected = true;
	// isSelected = true;
	// writer.write(" selected");
	// if (this.param.getDisplayText() == null) {
	// this.displayTextString = label;
	// } else {
	// label = this.param.getDisplayText();
	// }
	// }
	//
	// BirtTagUtil.writeOption(writer, label,
	// (displayValue == null) ? "$${{((null))}}$$" : displayValue,
	// selected);
	// }
	//
	// String defaultValueText = null;
	// if (!(isSelected)) {
	// Object defaultValue = BirtReportServiceFactory.getReportService()
	// .getParameterDefaultValue(
	// this.viewer.getReportDesignHandle(),
	// this.param.getName(), this.options);
	//
	// if (defaultValue == null) {
	// isNullValue = true;
	// } else {
	// isNullValue = false;
	// defaultValueText = DataUtil.getDisplayValue(defaultValue,
	// this.timeZone);
	// if ((this.valueString.equalsIgnoreCase(defaultValueText))
	// || (this.paramDef.mustMatch())) {
	// if (defaultValueText != null)
	// this.valueString = defaultValueText;
	//
	// String defaultDisplayText = DataUtil.getDisplayValue(null,
	// this.pattern, defaultValue, this.locale,
	// this.timeZone);
	//
	// if (defaultDisplayText != null)
	// this.displayTextString = defaultDisplayText;
	//
	// BirtTagUtil.writeOption(writer, this.displayTextString,
	// this.valueString, true);
	// isSelected = true;
	// }
	// }
	//
	// }
	//
	// if ((!(this.paramDef.isRequired())) && (!(nullValueFound))) {
	// BirtTagUtil.writeOption(writer, "Null Value", "$${{((null))}}$$",
	// isNullValue);
	//
	// isSelected = true;
	// }
	//
	// writer.write("</select>\n");
	//
	// if (!(this.paramDef.mustMatch())) {
	// writer.write("<input type = 'hidden' ");
	// writer.write(" id=\"" + isLocaleId + "\" ");
	// writer.write(" value=\"" + encParamName + "\" ");
	// writer.write(" >\n");
	//
	// if (this.param.getPattern() != null) {
	// writer.write("<input type = 'hidden' id=\"" + patternId + "\"");
	//
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.param.getPattern())
	// + "\">\n");
	// }
	//
	// String onClick = "updateParam" + encParamId + "( false );";
	// writer.write("<input type=\"radio\" ");
	// writer.write(" id=\"" + radioTextId + "\"");
	// writer.write(" onclick=\"" + onClick + "\"");
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"text\" ");
	// writer.write(" id=\"" + inputTextId + "\"");
	// if (!(isSelected)) {
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.displayTextString)
	// + "\"");
	// }
	//
	// writer.write(" onchange=\"changeTextParam" + encParamId + "( )\"");
	// writer.write(" >\n");
	//
	// writer.write("<script language=\"JavaScript\">updateParam"
	// + encParamId + "(" + isSelected + ");</script>\n");
	// }
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + displayTextId + "\" ");
	// if (!(isNullValue)) {
	// writer.write(" name=\"" + displayTextName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.displayTextString)
	// + "\" ");
	// }
	//
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + valueId + "\" ");
	// if (!(isNullValue)) {
	// writer.write(" name=\"" + encParamName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.valueString) + "\" ");
	// }
	//
	// writer.write(" >\n");
	//
	// if (!(this.paramDef.isRequired())) {
	// writer.write("<input type=\"hidden\" value=\"" + encParamName
	// + "\" id=\"" + nullValueId + "\"");
	//
	// if (isNullValue)
	// writer.write(" name=\"__isnull\"");
	//
	// writer.write(" >\n");
	// }
	//
	// if ((!(isSelected)) && (this.paramDef.mustMatch())) {
	// writer.write("\n<script language=\"JavaScript\">\n");
	// writer.write("var selectCtl = document.getElementById(\""
	// + encParamId + "\");\n");
	//
	// writer.write("if( selectCtl.selectedIndex >= 0 )\n");
	// writer.write("{\n");
	// if (defaultValueText != null) {
	// writer.write("  selectCtl.value = \"" + defaultValueText
	// + "\";\n");
	// }
	//
	// writer.write("  handleParam" + encParamId
	// + "( selectCtl.options[selectCtl.selectedIndex] );\n");
	//
	// writer.write("}\n");
	// writer.write("</script>\n");
	// }
	// }
	//
	// protected void __handleCascadingListBox() throws Exception {
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	// String inputTextId = encParamId + "_input";
	//
	// JspWriter writer = this.pageContext.getOut();
	//
	// BirtTagUtil.writeScript(writer,
	// "var param = new ParameterDefinition(\"" + encParamId + "\",\""
	// + encParamName + "\");\n" + "param.setRequired("
	// + this.paramDef.isRequired() + ");\n"
	// + this.groupObjName + ".addParameter( param );\n");
	//
	// ParameterGroupDefinition group = this.paramDef.getGroup();
	//
	// int index = group.getParameters().indexOf(this.paramDef);
	//
	// if (index == group.getParameterCount() - 1)
	// return;
	//
	// String casObj = "cas" + encParamId;
	// String namesObj = "names_" + encParamId;
	// writer.write("\n<script language=\"JavaScript\">\n");
	// writer.write("var " + namesObj + " = new Array( " + (index + 2)
	// + " );\n");
	//
	// for (int i = 0; i < index + 2; ++i) {
	// ParameterDefinition param = (ParameterDefinition) group
	// .getParameters().get(i);
	//
	// writer.write(namesObj + "[" + i + "] = \""
	// + ParameterAccessor.htmlEncode(param.getName()) + "\";\n");
	// }
	//
	// writer.write("var " + casObj + " = new CascadingParameter( \""
	// + this.viewer.getId() + "\", param, " + namesObj + ", "
	// + this.groupObjName + " );\n");
	//
	// writer.write("var selectCtl = document.getElementById(\"" + encParamId
	// + "\");\n");
	//
	// writer.write("selectCtl.onchange = function( ) { \n");
	// writer.write("var selectCtl = document.getElementById(\"" + encParamId
	// + "\");\n");
	//
	// writer.write("handleParam" + encParamId
	// + "( selectCtl.options[selectCtl.selectedIndex] );\n");
	//
	// writer.write("progressBar.setHandler(" + casObj + ");\n");
	// writer.write(casObj + ".process( ); };\n");
	// writer.write("var inputCtl = document.getElementById(\"" + inputTextId
	// + "\");\n");
	//
	// writer.write("if( inputCtl )\n");
	// writer.write("{\n");
	// writer.write("inputCtl.onchange = function( ) { \n");
	// writer.write("handleTextParam" + encParamId + "( );\n");
	// writer.write("progressBar.setHandler(" + casObj + ");\n");
	// writer.write(casObj + ".process( ); };\n");
	// writer.write("}\n");
	// writer.write("</script>\n");
	// }
	//
	// private Collection getParameterSelectionListForCascadingGroup()
	// throws ReportServiceException {
	// ParameterGroupDefinition group = this.paramDef.getGroup();
	//
	// int index = group.getParameters().indexOf(this.paramDef);
	// Object[] groupKeys = new Object[index];
	// for (int i = 0; i < index; ++i) {
	// ParameterDefinition def = (ParameterDefinition) group
	// .getParameters().get(i);
	//
	// String parameterName = def.getName();
	// groupKeys[i] = this.requesterTag.getParameters().get(parameterName);
	// }
	// return BirtReportServiceFactory.getReportService()
	// .getSelectionListForCascadingGroup(
	// this.viewer.getReportDesignHandle(), group.getName(),
	// groupKeys, this.options);
	// }
	//
	// protected void __handleRadioButton() throws Exception {
	// Collection selectionList = BirtReportServiceFactory.getReportService()
	// .getParameterSelectionList(this.viewer.getReportDesignHandle(),
	// this.options, this.param.getName());
	//
	// if ((selectionList == null) || (selectionList.size() <= 0))
	// return;
	//
	// JspWriter writer = this.pageContext.getOut();
	//
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	//
	// String displayTextId = encParamId + "_displayText";
	// String displayTextName = "__isdisplay__" + encParamName;
	//
	// String nullValueId = encParamId + "_null";
	// String radioNullValueId = encParamId + "_radio_null";
	//
	// String radioName = encParamId + "_radio";
	// String valueId = encParamId + "_value";
	// boolean isChecked = false;
	//
	// writer.write("\n<script language=\"JavaScript\">\n");
	// writer.write("function handleParam" + encParamId + "( e )\n");
	// writer.write("{\n");
	// writer.write("var obj;\n");
	// writer.write("if( window.event )\n");
	// writer.write("{\n");
	// writer.write("  obj = window.event.srcElement;\n");
	// writer.write("}\n");
	// writer.write("else\n");
	// writer.write("{\n");
	// writer.write("  if( e ) obj = e.target;\n");
	// writer.write("}\n");
	// writer.write("if( !obj ) return;\n");
	//
	// writer.write("var valCtl = document.getElementById(\"" + valueId
	// + "\");\n");
	//
	// writer.write("var displayCtl = document.getElementById(\""
	// + displayTextId + "\");\n");
	//
	// writer.write("var nullCtl = document.getElementById(\"" + nullValueId
	// + "\");\n");
	//
	// writer.write("if( obj.id == \"" + radioNullValueId + "\")\n");
	// writer.write("{\n");
	// writer.write("  if( nullCtl ) nullCtl.name=\"__isnull\";\n");
	//
	// writer.write("  valCtl.name = '';\n");
	// writer.write("  valCtl.value = '';\n");
	// writer.write("  displayCtl.value = '';\n");
	// writer.write("  displayCtl.name = '';\n");
	// writer.write("}\n");
	// writer.write("else\n");
	// writer.write("{\n");
	// writer.write("  if( nullCtl ) nullCtl.name='';\n");
	// writer.write("  valCtl.name = \"" + encParamName + "\";\n");
	// writer.write("  valCtl.value = obj.value;\n");
	// writer.write("  var labelCtl = document.getElementById( obj.id + \"_label\");\n");
	//
	// writer.write("  displayCtl.value = labelCtl.innerHTML;\n");
	// writer.write("  displayCtl.name = \"" + displayTextName + "\";\n");
	// writer.write("}\n");
	//
	// writer.write("}\n");
	// writer.write("</script>\n");
	//
	// String onClick = "handleParam" + encParamId + "( event )";
	//
	// int index = 0;
	// Iterator iter = selectionList.iterator();
	// while (true) {
	// ParameterSelectionChoice selectionItem;
	// Object value;
	// String displayValue;
	// while (true) {
	// if (!(iter.hasNext())) {
	// boolean isNullValue = this.param.getValue() == null;
	//
	// writer.write("<input type=\"hidden\" value=\""
	// + encParamName + "\" id=\"" + nullValueId + "\"");
	//
	// if (isNullValue)
	// writer.write(" name=\"__isnull\"");
	//
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"radio\" id=\""
	// + radioNullValueId + "\" ");
	//
	// writer.write(" name=\"" + radioName + "\"");
	// writer.write(" onclick=\"" + onClick + "\"");
	// if (isNullValue)
	// writer.write(" checked ");
	// writer.write(" >\n");
	// writer.write("<label id=\"" + radioNullValueId + "_label"
	// + "\"");
	//
	// writer.write(" title=\"Null Value\"");
	// writer.write(" for=\"" + radioNullValueId + "\">");
	// writer.write("Null Value");
	// writer.write("</label>");
	// writer.write("</input>");
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + displayTextId + "\" ");
	// if (isChecked) {
	// writer.write(" name=\"" + displayTextName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor
	// .htmlEncode(this.displayTextString)
	// + "\" ");
	// }
	//
	// writer.write(" >\n");
	//
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + valueId + "\" ");
	// if (isChecked) {
	// writer.write(" name=\"" + encParamName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor
	// .htmlEncode(this.valueString) + "\" ");
	// }
	//
	// writer.write(" >\n");
	// return;
	// }
	// selectionItem = (ParameterSelectionChoice) iter.next();
	//
	// value = selectionItem.getValue();
	// try {
	// value = DataUtil
	// .convert(value, this.paramDef.getDataType());
	// } catch (Exception e) {
	// value = null;
	// }
	//
	// displayValue = DataUtil.getDisplayValue(value, this.timeZone);
	// if (displayValue != null)
	// break;
	//
	// }
	//
	// String label = selectionItem.getLabel();
	// if ((label == null) || (label.length() <= 0)) {
	// label = DataUtil.getDisplayValue(null, this.pattern, value,
	// this.locale, this.timeZone);
	// }
	//
	// label = (label != null) ? ParameterAccessor.htmlEncode(label) : "";
	// String ctlId = encParamId + "_" + index;
	//
	// writer.write("<input type=\"radio\" ");
	// writer.write(" name=\"" + radioName + "\"");
	// writer.write(" id=\"" + ctlId + "\"");
	// __handleGeneralDefinition();
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(displayValue) + "\"");
	//
	// writer.write(" onclick=\"" + onClick + "\"");
	// if (displayValue.equalsIgnoreCase(DataUtil.getDisplayValue(
	// this.param.getValue(), this.timeZone))) {
	// isChecked = true;
	// writer.write(" checked");
	// if (this.param.getDisplayText() == null) {
	// this.displayTextString = label;
	// } else {
	// label = this.param.getDisplayText();
	// }
	// }
	// writer.write(" >");
	// writer.write("<label id=\"" + ctlId + "_label" + "\"");
	// writer.write(" title=\"" + label + "\"");
	// writer.write(" for=\"" + ctlId + "\">");
	// writer.write(label);
	// writer.write("</label>");
	// writer.write("</input>\n");
	//
	// ++index;
	// }
	// }
	//
	// protected void __handleCheckBox() throws Exception {
	// JspWriter writer = this.pageContext.getOut();
	//
	// String encParamId = ParameterAccessor.htmlEncode(this.param.getId());
	// String encParamName = ParameterAccessor
	// .htmlEncode(this.param.getName());
	//
	// Boolean bl = (Boolean) this.param.getValue();
	// boolean value = (bl != null) ? bl.booleanValue() : false;
	//
	// String valueId = encParamId + "_value";
	// writer.write("<input type=\"hidden\" ");
	// writer.write(" id=\"" + valueId + "\" ");
	// writer.write(" name=\"" + encParamName + "\" ");
	// writer.write(" value=\""
	// + ParameterAccessor.htmlEncode(this.valueString) + "\" ");
	//
	// writer.write(" >\n");
	//
	// String valCtl = "document.getElementById('" + valueId + "')";
	// String inputCtl = "document.getElementById('" + encParamId + "')";
	// String onClick = "var value = 'false';if( " + inputCtl
	// + ".checked ) value='true';" + valCtl + ".value = value;";
	//
	// writer.write("<input type=\"checkbox\" ");
	// if (this.param.getId() != null)
	// writer.write(" id=\"" + encParamId + "\"");
	// __handleGeneralDefinition();
	// writer.write(" onclick=\"" + onClick + "\"");
	// if (value)
	// writer.write(" checked ");
	//
	// writer.write(" >");
	// }
	//
	// protected void __handleException(Exception e) throws JspException {
	// JspWriter writer = this.pageContext.getOut();
	// try {
	// writer.write("<font color='red'>");
	// writer.write(e.getMessage());
	// writer.write("</font>");
	// } catch (IOException err) {
	// throw new JspException(err);
	// }
	// }
	//
	// public void setId(String id) {
	// this.param.setId(id);
	// }
	//
	// public void setName(String name) {
	// this.param.setName(name);
	// }
	//
	// public void setPattern(String pattern) {
	// this.param.setPattern(pattern);
	// }
	//
	// public void setValue(Object value) {
	// this.param.setValue(value);
	// }
	//
	// public void setDisplayText(String displayText) {
	// this.param.setDisplayText(displayText);
	// }
	//
	// public void setIsLocale(String isLocale) {
	// this.param.setIsLocale(isLocale);
	// }
	//
	// public void setTitle(String title) {
	// this.param.setTitle(title);
	// }
	//
	// public void setCssClass(String cssClass) {
	// this.param.setCssClass(cssClass);
	// }
	//
	// public void setStyle(String style) {
	// this.param.setStyle(style);
	// }
}