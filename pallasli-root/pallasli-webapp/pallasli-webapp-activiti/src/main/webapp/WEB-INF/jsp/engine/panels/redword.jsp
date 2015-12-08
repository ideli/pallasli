<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.zhuozhengsoft.pageoffice.*"%>
<%@ page import="com.mixky.engine.organization.dao.User"%>
<%@ page import="com.mixky.engine.design.DesignObjectLoader"%>
<%@ page import="com.mixky.engine.design.document.Panel"%>
<%@ page import="com.mixky.engine.certification.MixkyUserCertification"%>
<%@ page import="com.mixky.app.ApplicationParameters"%>
<%@ page import="com.mixky.common.database.JsonObjectDao"%>
<%@page import="com.mixky.engine.attachment.AttachmentManager"%>
<%@page import="com.mixky.engine.attachment.Attachment"%>
<%@page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.Action"%>
<%@ page import="com.mixky.engine.design.document.DocumentManager"%>
<%@ page import="com.mixky.engine.design.document.ObjectAuthority"%>
<%@ page import="com.mixky.system.ContextHolder"%> 

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0); 
    
    String panelid = request.getParameter("panelid");
	String documentid = request.getParameter("documentid");
	String appkey = request.getParameter("appkey");
	String panelKey = request.getParameter("panelkey");
	Panel panel = (Panel)request.getAttribute("panel");
	
	String fileName=documentid+"_redword.doc";//文件名称
    
	User user = (User)request.getAttribute("user");
	// 获得标签权限
	Map<String, ObjectAuthority> map = (Map<String, ObjectAuthority>)request.getAttribute("authmap");
	List<Panel> panels = new ArrayList<Panel>();
	panels.add(panel);
	List<ObjectAuthority> panelauths = DocumentManager.instance().getFilterObjectAuthority(map, panels, user);
	boolean canEdit = false;
	for (int i = 0; i < panelauths.size(); i++) {
		ObjectAuthority auth = panelauths.get(i);
		if (auth.hasAuth(ObjectAuthority.A_EDIT)) {
			canEdit = true;
		}
	}
	String redWordAttachmentId = "0";
	String wordAttachmentId = "0";
	List<Attachment> atts = AttachmentManager.instance().getDocumentFiles(Long.parseLong(documentid), panelKey.toUpperCase(), null);
	for (int i = 0; i < atts.size(); i++) {
		Attachment att = atts.get(i);
		if (att.getF_attach_type() == 1) {
			wordAttachmentId = String.valueOf(att.getId());
			//break;
		} else if (att.getF_attach_type() == 2) {
			redWordAttachmentId = String.valueOf(att.getId());
		}
	}
%>
<script language='javascript'> 
  function ConvertFiles(templateId, fbstr, regstr) {
      //页面自刷新
      var csstr = "&id="+templateId+"&tablename=t_ql_app_senddoc_wordtemplate&fieldname=f_file&filename=<%=fileName%>&documentid=<%=documentid%>&panelKey=<%=panelKey%>&wordAttachmentId=<%=wordAttachmentId%>";
      document.getElementById("iframe1").contentWindow.location.href = "/OA/jsppage.do?url=app/mkoa/senddoc/FileMakerSingle" + csstr + "&fbstr=" + encodeURI(encodeURI(fbstr)) +"&regstr=" + regstr;
  }
        
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];

	var refreshAction = new Ext.Action({
		text : '刷新',
		iconCls : 'icon-sys-refresh',
		handler : function(){
			panel.removeAll();
            panel.doAutoLoad();
		}
	});
	
	var redWordSelectAction = new Ext.Action({
		text : '选择红头',
		handler : function(){
			redWordSelect();
		}
	});
	
	var buttons = [refreshAction, '->',redWordSelectAction];
	
    var form = new Ext.Panel({
	    autoScroll : true,
		border : false,
		trackResetOnLoad : true,
        bodyStyle : "padding:0px;padding-left:0px;padding-right:0px;overflow-x:hidden",
        tbar: buttons,
        manager : MixkyApp.desktop.getManager(),
	    html:"<iframe id='word' name='word' marginwidth='0' marginheight='0' width='100%' height='100%' scrolling='auto'  frameborder='0' src='/OA/jsppage.do?url=app/mkoa/senddoc/redword1&panelid=<%=panelid%>&documentid=<%=documentid%>&appkey=<%=appkey%>&panelkey=<%=panelKey%>'></iframe>" 
	});
	
	panel.submit = function(fn){
	    Ext.getDom('word').contentWindow.Save();
	    panel.hide();
	    panel.document.submitPanelOver(panel, fn);
	}
	
	//下载红头模板，准备套红头
	function downloadWordTemplateDocumentFrmSvr(templateId, flags) {  
	     var fbstr = '';
	     var regstr = '';
	     var fbarray = new Array();
		 var arrayfield = new Array();
		 var form = panel.document.get(0).get(0).getForm();
		 fbarray = flags.split(";");
		 var fbarraynum = fbarray.length;
		 if(fbarraynum > 0){
		    for (var i = 0; i < fbarraynum; i++) {
			   arrayfield[i] = fbarray[i].substring(0,fbarray[i].lastIndexOf("#"));
			   if(!form.findField(arrayfield[i])){
				  alert("未找到域："+arrayfield[i]+"，程序退出，请检查模板设置！")
				  return false;
			   }
			   else{
			       var fieldvalue = form.findField(arrayfield[i]).getValue();
			       var regvalue = fbarray[i].substring(fbarray[i].lastIndexOf("#")+1,fbarray[i].length);
			       if(fbstr == ''&& regstr == ''){
			          fbstr = fieldvalue;
			          regstr = regvalue;
			       }
			       else{
			         fbstr = fbstr + ';'+ fieldvalue;
			         regstr = regstr + ';'+ regvalue;
			       }
			   }	
			}
		 }
	     ConvertFiles(templateId, fbstr, regstr);
		 Ext.MessageBox.show({title:'提示',wait:true,msg:"正在生成红头文件,请稍候...",modal:true,
	     icon:Ext.Msg.WARNING,width:300,closable:false});
		 setTimeout(function(){
		    Ext.MessageBox.hide();
		    Ext.getDom('word').width = '100%';
		    Ext.getDom('word').height = '100%';
		    Ext.getDom('word').contentWindow.location.href='/OA/jsppage.do?url=app/mkoa/senddoc/redword1&panelid=<%=panelid%>&documentid=<%=documentid%>&appkey=<%=appkey%>&panelkey=<%=panelKey%>&type=1';	
		 },10000);
	}
	
	redWordSelect = function(){
        var win;
		if(!win){
		    win = new Ext.Window({
				title: '请选择红头模板',
				minimizable : false,
				maximizable : false,
				modal: true,
				shim:true,
				buttonAlign:"center",
				width:280,
				height:100,
				minHeight: 80,
				plain:true,
				footer:true,
				closable:true,
				manager : MixkyApp.desktop.getManager(),
				plugins: new function(){
			        this.init = function(win){
			             win.on('deactivate', function(){
				             this.setZIndex(99999999999999);
			             })
			        }
			    },
		
		        items: new Ext.form.ComboBox({
					width:260,
					name: 'F_DOC_TYPE',
					hiddenName: 'F_DOC_TYPE_HIDDEN',
					editable: false,
					triggerAction: 'all',
					mode: 'remote',
					valueField: 'value',
					displayField: 'display',
					allowBlank: false,
					store: Mixky.wasoft.lib.getDictionaryDoUrlStore(app.key,'app/mkoa/senddoc/word.template.get', {'F_TYPE':'<%=panelKey%>'}),
					params:{}
				}),
					
		        buttons: [{
		            text:'确定',
		            handler: function(){
		            	var value = win.items.get(0).getValue();
		            	var templateId = value.substring(0, value.indexOf('|'));
		            	var flags = value.substring(value.indexOf('|') + 1, value.length);
						var form = panel.document.get(0).get(0).getForm();
		            	form.findField('F_RED_FLAGS').setValue(flags);
		            	form.findField('F_RED_TMPLATE_ID').setValue(templateId);
		            	downloadWordTemplateDocumentFrmSvr(templateId, flags);
		            	win.hide();
		            }
		        },{
		            text: '取消',
		            handler: function(){
		                win.hide();
		                Ext.getDom('word').width = '100%';
		                Ext.getDom('word').height = '100%';
		            }
		        }]
		    });
		}
		win.show();
		//panel.hide();
		Ext.getDom('word').width = 0;
		Ext.getDom('word').height = 0;
   }
	
	panel.add(form);
    panel.doLayout();
    form.doLayout();
});
</script>
<body>
    <div style="width: 0px; height: 0px; overflow: hidden;">
        <iframe id="iframe1" name="iframe1" src=""></iframe>
    </div>
</body>