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

<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0); 
    
    String filePath="";  
    String userName="";
   	String panelid = request.getParameter("panelid");
	String documentid = request.getParameter("documentid");
	String appkey = request.getParameter("appkey");
	String panelKey = request.getParameter("panelkey");
	Panel panel = (Panel)request.getAttribute("panel");
    User user = MixkyUserCertification.instance().getUserInfo(request);
    userName = user.getF_caption();
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
	
    List<Attachment> atts = AttachmentManager.instance().getDocumentFiles(Long.parseLong(documentid), panelKey.toUpperCase(), null);
	String wordAttachmentId = "0";
	String redWordAttachmentId = "0";
    
	for (int i = 0; i < atts.size(); i++) {
		Attachment att = atts.get(i);
		if (att.getF_attach_type() == 1) {
			wordAttachmentId = String.valueOf(att.getId());
			//break;
		} else if (att.getF_attach_type() == 2) {
			redWordAttachmentId = String.valueOf(att.getId());
		}
	}
    
    String fileName=documentid+"_redword.doc";//文件名称
     
    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
	poCtrl.setServerPage(request.getContextPath()+"/poserver.do"); //此行必须
	
	//poCtrl.setOfficeVendor(OfficeVendorType.WPSOffice);
	
	//添加自定义按钮
    poCtrl.setCustomMenuCaption("自定义菜单");
    poCtrl.addCustomMenuItem("显示痕迹", "ShowRevisions", true);
    poCtrl.addCustomMenuItem("隐藏痕迹", "HiddenRevisions", true);
    poCtrl.addCustomMenuItem("-", "", false);
    poCtrl.addCustomMenuItem("显示标题", "ShowTitle", true);
    poCtrl.addCustomMenuItem("-", "", false);
    poCtrl.addCustomMenuItem("领导签批", "InsertHandSign", true);
    poCtrl.addCustomMenuItem("插入印章", "InsertSeal", true);
    poCtrl.addCustomMenuItem("接受所有修订", "AcceptAllRevisions", true);
    poCtrl.addCustomMenuItem("-", "", false);
    poCtrl.addCustomMenuItem("分层显示手写批注", "ShowHandDrawDispBar", true);
   
    poCtrl.addCustomToolButton("保存", "Save", 1);
    poCtrl.addCustomToolButton("另存为", "SaveAs", 1);
    poCtrl.addCustomToolButton("选择红头", "redWordSelect", 3);
    poCtrl.addCustomToolButton("打印", "ShowPrintDlg", 6);
    poCtrl.addCustomToolButton("打印预览", "PrintPreview", 6);
    //poCtrl.addCustomToolButton("显示痕迹", "ShowRevisions", 5);
   // poCtrl.addCustomToolButton("隐藏痕迹", "HiddenRevisions", 5);
   // poCtrl.addCustomToolButton("插入印章/签名", "InsertSeal", 2);
   // poCtrl.addCustomToolButton("领导签批", "InsertHandSign", 3);
   // poCtrl.addCustomToolButton("接受所有修订", "AcceptAllRevisions", 5);
   // poCtrl.addCustomToolButton("分层显示手写批注", "ShowHandDrawDispBar", 7);
    poCtrl.addCustomToolButton("全屏/还原", "IsFullScreen", 4);
    poCtrl.addCustomToolButton("刷新", "refresh", 7);
   //设置保存页面
    poCtrl.setSaveFilePage(request.getContextPath()+"/jsppage.do?url=engine/panels/savefile&si="+documentid+"&st="+panelKey+"&ft=2&f&fn="+fileName+"&u="+user.getF_caption());
	//隐藏Office工具条
	poCtrl.setOfficeToolbars(true);
	//隐藏自定义工具栏
	poCtrl.setCustomToolbar(true);
	
	poCtrl.setMenubar(false);
	poCtrl.setTitlebar(false);
	poCtrl.setCaption(" ");
	poCtrl.setFileTitle(fileName);
	//poCtrl.setTimeSlice(30);
	System.out.println("======redWordAttachmentId======"+redWordAttachmentId);
	if(redWordAttachmentId.trim().equals("0")){
		  poCtrl.webCreateNew(userName,DocumentVersion.Word2003);
	}
    else{
       String path = getServletContext().getRealPath("doc")+ System.getProperty("file.separator")+user.getId()+System.getProperty("file.separator");
       File file = new File(path + fileName);
       if (!file.exists()) { //文件存在时
          AttachmentManager.instance().makeWord(wordAttachmentId, path, fileName);
       }
    }
    System.out.println("======path======"+request.getContextPath()+"/doc/"+ user.getId()+"/"+fileName);
	poCtrl.webOpen(request.getContextPath()+"/doc/"+ user.getId()+"/"+fileName, OpenModeType.docNormalEdit, userName);
	poCtrl.setTagId("PageOfficeCtrl1"); //此行必须	
%>
<script language='javascript'> 
Ext.onReady(function(){
	var panel = Ext.getCmp('<%=panelid%>');
	var win = panel.findParentByType('window');
	var app = Mixky.wasoft.cache.Applications['<%=appkey%>'];
	
    var downloadRedWord = new Ext.Action({
		text : '下载文件',
		iconCls : 'icon-sys-download',
		handler : function(){
			location.href = "/OA/engine/file/sysdownload.do?tablename=T_MK_SYS_FILES&fieldname=F_CONTENT&id=<%=redWordAttachmentId%>&documentdbtype=none";
		}
	});
	
	/*var wordPanel = new Ext.Panel({
	   // applyTo : 'pageoffice',
	    autoScroll : false,
	    layout:'fit',
		border : false,
		trackResetOnLoad : true,
		manager : MixkyApp.desktop.getManager()
	});*/

	panel.submit = function(fn){
	    Save();
	    panel.hide();
	    panel.document.submitPanelOver(panel, fn);
	}
	
	panel.document.on('beforetabchange',function(t,p,c){
	    var isjx = true;
	    if(document.getElementById("PageOfficeCtrl1").IsDirty){
	        if(confirm('正文已修改尚未保存，你是否需对正文进行保存？')){
				  isjx = false;
				  Save();
		    }
	    }
	    return isjx;
	});
	
    refresh = function(){
        panel.removeAll();
        panel.doAutoLoad();
    }
    
    Save = function(){
        if(document.getElementById("PageOfficeCtrl1").IsDirty){
            document.getElementById("PageOfficeCtrl1").WebSave();
            panel.removeAll();
            panel.doAutoLoad()
        }
    }
    
    SaveAs = function(){
        document.getElementById("PageOfficeCtrl1").ShowDialog (3);
    }
    
    ShowPrintDlg = function(){
         document.getElementById("PageOfficeCtrl1").ShowDialog(4); //打印对话框
    }
     
    PrintPreview = function(){
         document.getElementById("PageOfficeCtrl1").PrintPreview(); 
    }
    
    ShowRevisions = function(){
        document.getElementById("PageOfficeCtrl1").ShowRevisions = true;
    }
    
    HiddenRevisions = function(){
        document.getElementById("PageOfficeCtrl1").ShowRevisions = false;
    }
    
    Show_HidRevisions = function(){
        document.getElementById("PageOfficeCtrl1").ShowRevisions = !document.getElementById("PageOfficeCtrl1").ShowRevisions;
    }

    //领导圈阅签字
    StartHandDraw = function(){
        document.getElementById("PageOfficeCtrl1").HandDraw.SetPenWidth(5);
        document.getElementById("PageOfficeCtrl1").HandDraw.Start();
    }

    //接受所有修订
    AcceptAllRevisions = function(){
        document.getElementById("PageOfficeCtrl1").AcceptAllRevisions();
    }

    //分层显示手写批注
    ShowHandDrawDispBar = function(){
        document.getElementById("PageOfficeCtrl1").HandDraw.ShowLayerBar();
    }

    //全屏/还原
   IsFullScreen = function(){
        document.getElementById("PageOfficeCtrl1").FullScreen = !document.getElementById("PageOfficeCtrl1").FullScreen;
    }


    //插入电子印章
   InsertSeal = function(){
        var zoomseal = document.getElementById("PageOfficeCtrl1").ZoomSeal;
        if (zoomseal != null)
            zoomseal.AddSeal();


    }
    // 签批
   InsertHandSign = function(){
        var zoomseal = document.getElementById("PageOfficeCtrl1").ZoomSeal;
        if (zoomseal != null)
            zoomseal.AddHandSign();
    }
     //下载红头模板，准备套红头
	function downloadWordTemplateDocumentFrmSvr(templateId) {
		
	}
    //选择红头模板
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
					fieldLabel: '请选择发文稿纸',
					editable: false,
					triggerAction: 'all',
					mode: 'remote',
					valueField: 'value',
					displayField: 'display',
					allowBlank: false,
					store: Mixky.wasoft.lib.getDictionaryDoUrlStore(app.key,'app/mkoa/senddoc/word.template.get', {'F_TYPE':'<%=panelKey%>'}),
					//dourl: 'app/mkoa/senddoc/word.template.get',
					params:{}
				}),
					
		        buttons: [{
		            text:'确定',
		            handler: function(){
		            	var value = win.items.get(0).getValue();
		            	var templateId = value.substring(0, value.indexOf('|'));
		            	//alert(templateId);
		            	var flags = value.substring(value.indexOf('|') + 1, value.length);
		            	
						var form = panel.document.items.get(0).items.get(0).getForm();
		            	//alert(flags);
		            	form.findField('F_RED_FLAGS').setValue(flags);
		            	form.findField('F_RED_TMPLATE_ID').setValue(templateId);
		            	//alert(templateId);
		            	downloadWordTemplateDocumentFrmSvr(templateId);
		            	//MixkyApp.desktop.openDocument('qlSendDoc.docSendDoc', 0, {'F_TYPE': win.items.get(0).getRawValue()});
		            	win.hide();
		            	panel.createRedWord();
		            }
		        },{
		            text: '取消',
		            handler: function(){
		                win.hide();
		                panel.show();
		            }
		        }]
		    });
		}
		win.show();
		panel.hide();
   }
	
   // panel.add(wordPanel);
	//panel.doLayout();
});
</script>
<body>
	    <div style="width:auto; height:700px;" id ="pageoffice">
	        <po:PageOfficeCtrl id="PageOfficeCtrl1">
	        </po:PageOfficeCtrl>
	    </div>
</body>