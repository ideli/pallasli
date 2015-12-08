Ext.namespace("atwasoft.common.lib");

atwasoft.common.lib.getCxdialog = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : true,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record && record != null){
	    recordHandler(record);
	    win1.close();
	}else{
	    Ext.MessageBox.show({titel:'提示',msg:"请选择记录！",
		modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:300,closable:false});
	}
    };
};

atwasoft.common.lib.dialog = function(appkey,url,panel,params,fn){
    var title=params.title;
    var width=params.width;
    var height=params.height;

    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : true,
		minimizable :true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    iconCls : 'icon-common-cancel',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '保存',
		    iconCls : 'icon-common-aquery',
		    handler : function() {
			win1.items.get(0).submit();
		    }
		},{
		    text : '重置',
		    iconCls : 'icon-common-confirm',
		    handler : function(){
			win1.items.get(0).refresh();
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(){
	win1.close();
	fn();	
    };
};

atwasoft.common.lib.impdialog = function(appkey,url,panel,params,fn){
    var title=params.title;
    var width=params.width;
    var height=params.height;

    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : true,
		minimizable :true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    iconCls : 'icon-common-cancel',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '导入',
		    iconCls : 'icon-common-import',
		    handler : function() {
			win1.items.get(0).submit();
		    }
		},{
		    text : '重置',
		    iconCls : 'icon-common-confirm',
		    handler : function(){
			win1.items.get(0).refresh();
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(){
	win1.close();
	fn();	
    };
};

//Author:HCC 重写一个方法，接受到的事多条记录。需要length判断是否选中。
atwasoft.common.lib.getCxdialogByMoreRecord = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record.length && record.length != null){
	    recordHandler(record);
	    win1.close();
	}else{
	    Ext.MessageBox.show({titel:'提示',msg:"请选择记录！",
		modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:300,closable:false});
	}
    };
};

atwasoft.common.lib.getChkdialog = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : true,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record && record != null){
	    recordHandler(record);
	    win1.close();
	}
    };
};
/*确定对话框，不需验证*/
atwasoft.common.lib.getOkdialog = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.winRecordHandler(false);											
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	recordHandler(record);
	win1.close();
    };
};

/*归集入账日期检查弹出对话框调用方法*/
atwasoft.common.lib.getChkdialog_gj = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var gjdbm=params.gjdbm;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record && record != null){
	    var rq=record['rzrq'];
	    ConsoleGzAppDirect.Gz_Dw_Zwdate_Chk(rq,gjdbm,function(result,E){
		if(!result.success){
		    Ext.MessageBox.alert('提示',result.msg);
		}else{
		    recordHandler(record);
		    win1.close();
		}		
	    });
	}
    };
};

/*归集入账日期检查弹出对话框调用方法*/
atwasoft.common.lib.getChkdialog_bt = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var gjdbm=params.gjdbm;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record && record != null){
	    var rq=record['rzrq'];
	    ZfbtAppDirect.Bt_Dw_Zwdate_Chk(rq,gjdbm,function(result,E){
		if(!result.success){
		    Ext.MessageBox.alert('提示',result.msg);
		}else{
		    recordHandler(record);
		    win1.close();
		}		
	    });
	}
    };
};

/*财务特殊调账用户检查弹出对话框调用方法*/
atwasoft.common.lib.getChkdialog_cw = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var gjdbm=params.gjdbm;
    var recordHandler = function(record){
	returnvalue(record);
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record && record != null){
	    var item={};
	    item.qx=record['qx'];
	    item.uid=record['uid'];
	    item.userpas=record['userpas'];
	    item.chkrq=record['chkrq'];
	    Cw_pzglDirect.userchk(item,function(result, e){
		if (result && result.success) {
		    recordHandler(record);
		    win1.close();
		}
		else{
		    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			icon:Ext.Msg.ERROR,width:300,closable:false});
		}
	    });
	}
    };
};

atwasoft.common.lib.getZtdialog = function(appkey,ischk, btn){
    WacwAppDirect.getZtChk(ischk,function(result,E){
	if (result&& !result.success) {
	    MixkyApp.desktop.closeModule('wacw', 'wacwpzgl');
	    MixkyApp.desktop.closeModule('wacw', 'wacwqmcl');
	    MixkyApp.desktop.closeModule('wacw', 'wacwzbgl');
	    MixkyApp.desktop.closeModule('wacw', 'wacwpjgl');
	    MixkyApp.desktop.closeModule('wacw', 'wacwreport');
	    MixkyApp.desktop.closeModule('wacw', 'wacwpzcx');
	    var  params={'title':'账套选择','width':300,'height':280};
	    var title=params.title;
	    var width=params.width;
	    var height=params.height;
	    var panel='ztdialog';
	    var url='app/common/ztml';
	    var recordHandler = function(record){
		var ztbh=record["ztbh"];
		var cznd=record["nd"]+record["yf"];
		WacwAppDirect.getZtxx(ztbh,cznd,function(result,e){});
	    };
	    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
		    appkey, 
		    panel, 
		    url, 
		    {
			title : title,
			width : width,
			height: height,
			closable : false,
			minimizable : true,
			modal :true,
			manager : MixkyApp.desktop.getManager(),
			bbar : [{
			    text : '关闭',
			    handler : function() {
				win1.close();												
			    }
			},'->',{
			    text : '确定',
			    handler : function() {
				// 获得回调参数
				var record = win1.items.get(0).getRecord();

				win1.winRecordHandler(record);

			    }
			}]
		    },
		    {},
		    params
	    );
	    // 视图调用接口
	    win1.winRecordHandler = function(record){
		if(record && record != null){
		    recordHandler(record);
		    win1.close();			  
		}
	    };
	}else{	
	    MixkyApp.desktop.settitlebar(result.cznd,result.cwzt,result.rjrq);
	}
    });
};

atwasoft.common.lib.getZjztdialog = function(appkey,ischk,btn){
    if(btn){
	btn.hideMenu();
    }
    WazjAppDirect.getZtChk(ischk,function(result,E){
	// alert(result.success);
	if (result&& !result.success) {
		        MixkyApp.desktop.closeModule('wazj', 'FM_CM_CAS');
		        MixkyApp.desktop.closeModule('wazj', 'mkFM_CM_CA');
			    MixkyApp.desktop.closeModule('wazj', 'mkFM_CM_CS');
			    MixkyApp.desktop.closeModule('wazj', 'mkFM_CM_HCM');
			    MixkyApp.desktop.closeModule('wazj', 'mkFM_CM_NM');
			    MixkyApp.desktop.closeModule('wazj', 'mkFM_CM_RM');
			    MixkyApp.desktop.closeModule('wazj', 'mkMC_BAM');
			    MixkyApp.desktop.closeModule('wazj', 'wazjbbsz');
			    MixkyApp.desktop.closeModule('wazj', 'wazjgzgl');
			    MixkyApp.desktop.closeModule('wazj', 'wazjreport');
			    MixkyApp.desktop.closeModule('wazj', 'wazjzjc');
			    MixkyApp.desktop.closeModule('wazj', 'wazjzjdb');
			    MixkyApp.desktop.closeModule('wazj', 'wazjzjjh');
			    MixkyApp.desktop.closeModule('wazj', 'wazjZjqs');
			    MixkyApp.desktop.closeModule('wazj', 'wazjztbg');
			    MixkyApp.desktop.closeModule('wazj', 'wazjztbg');
			    

	    var  params={'title':'账套选择','width':340,'height':280};
	    var title=params.title;
	    var width=params.width;
	    var height=params.height;
	    var panel='ztdialog';
	    var url='app/common/zttxml';
	    var recordHandler = function(record){
		var ztbh=record["ztbh"];
		var cznd=record["nd"]+record["yf"];
		WazjAppDirect.getZtxx(ztbh,cznd,function(result,e){
		    if(result.success){
			/*WazjAppDirect.im_txjc(ztbh,cznd,function(result,e){
			    if(result.msg==1){
				var win1 = MixkyApp.desktop.openWindowWithJspUrl('wazj','zjdltx','app/common/dltx', 
					{
				    title :'提醒信息',
				    width : 800,
				    height: 450,
				    closable : false,
				    minimizable : true,
				    modal :true,
				    manager : MixkyApp.desktop.getManager(),
				    bbar : ['->',{
					text : '确定',
					handler : function() {
					    // 获得回调参数
					    win1.close();
					}
				    }]
					});
			    }
			});*/
		    }
		});
	    };
	    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
		    appkey, 
		    panel, 
		    url, 
		    {
			title : title,
			width : width,
			height: height,
			closable : false,
			minimizable : true,
			modal :true,
			manager : MixkyApp.desktop.getManager(),
			bbar : [{
			    text : '关闭',
			    handler : function() {
				win1.close();												
			    }
			},'->',{
			    text : '确定',
			    handler : function() {
				// 获得回调参数
				var record = win1.items.get(0).getRecord();
				win1.winRecordHandler(record);
			    }
			}]
		    },
		    {},
		    params
	    );
	    // 视图调用接口
	    win1.winRecordHandler = function(record){
		if(record && record != null){
		    recordHandler(record);
		    win1.close();
		}
	    };
	     MixkyApp.desktop.settitlebar(result.cznd,result.cwzt,result.rjrq);
	}else{				  
	    if(btn){
		btn.showMenu();
	    }
	     MixkyApp.desktop.settitlebar(result.cznd,result.cwzt,result.rjrq);
	}
    });
};

atwasoft.common.lib.cxdialog = function(appkey,url,panel,params){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var result=params.result;
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : true,
		minimizable : true,
		modal :true,
		result:result,
		param:params,
		manager : MixkyApp.desktop.getManager()
	    },
	    {},
	    params
    );
};

atwasoft.common.lib.bpmdialog = function(appkey,url,panel,params){
	var ua = navigator.userAgent.toLowerCase();
    if(!Ext.isIE && ua.indexOf("rv:11.0") == -1){
	   atwasoft.common.lib.cxdialog(appkey,url,panel,params);
    }
    else{
		var swidth=screen.availWidth;
		var sheight=screen.availHeight;
		var wwidth=swidth-30;
		var wheight=sheight-30;
		var features =
		    'dialogWidth:'+wwidth+'px;' +
		    'dialogHeight:'+wheight+'px;' +
		    'dialogLeft:10px;' +
		    'dialogTop:10px;' +
		    'directories:no; localtion:no; menubar:no; status=no; toolbar=no;scrollbars:no;Resizeable=no;help:0;';
		var retval = window.showModalDialog(params.urlstr,window, features );
    }
};

atwasoft.common.lib.getCx1dialog = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var recordHandler = function(){
	returnvalue();
    };
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		buttons : [{
		    text : '另存为',
		    handler : function() {
			win1.close();												
		    }
		},
		{
		    text : '打印',
		    handler : function() {
			win1.close();												
		    }
		},
		{
		    text : '关闭',
		    handler : function() {
			win1.winRecordHandler();
		    }
		}]
	    },
	    {},
	    params
    );
    win1.winRecordHandler = function(){

	recordHandler();
	win1.close();
    };

};

/*管理类用户确认对话框*/
atwasoft.common.lib.getChkdialog_glqr = function(appkey,url,panel,params,returnvalue){
    var title=params.title;
    var width=params.width;
    var height=params.height;
    var recordHandler = function(record){
	returnvalue(record);
    }
    var win1 = MixkyApp.desktop.openWindowWithJspUrl(
	    appkey, 
	    panel, 
	    url, 
	    {
		title : title,
		width : width,
		height: height,
		closable : false,
		minimizable : true,
		modal :true,
		manager : MixkyApp.desktop.getManager(),
		bbar : [{
		    text : '关闭',
		    handler : function() {
			win1.close();												
		    }
		},'->',{
		    text : '确定',
		    handler : function() {
			// 获得回调参数
			var record = win1.items.get(0).getRecord();
			win1.winRecordHandler(record);
		    }
		}]
	    },
	    {},
	    params
    );
    // 视图调用接口
    win1.winRecordHandler = function(record){
	if(record && record != null){
	    var item={};
	    item.uname=record['uname'];
	    item.upas=record['upas'];
	    OrganizAppDirect.userchk(item,function(result, e){
		if (result && result.success) {
		    recordHandler(record);
		    win1.close();
		}
		else{
		    Ext.MessageBox.show({title:'提示',msg:result.msg,modal:true,buttons:Ext.Msg.OK,
			icon:Ext.Msg.ERROR,width:300,closable:false});
		}
	    });
	}
    };
};

//获得指定组织的上下级组织结构图
atwasoft.common.lib.OrgCharPanel =function(deptid){
    var item={};
    item.title='组织结构图';
    item.width=screen.width;
    item.height=screen.height*0.8;
    item.deptid=deptid;
    atwasoft.common.lib.cxdialog('OA','app/common/orgchar','orgchart1',item);
};

/*资金地图打开*/
atwasoft.common.lib.getZjMapdialog = function(appkey,ztbh){
	var ischk = true;
	WazjAppDirect.getMapZtChk(ischk,function(result,E){
		if (result&& !result.success) {
			atwasoft.common.lib.getZjztdialog('wazj',true,null); 
		}else{
			atwasoft.common.lib.mapdialog('wazj','url/zj/map','map',{'title':'北铁地图','width':screen.width,'height':screen.height*0.8});
		}
		
	});
	
};

atwasoft.common.lib.mapdialog = function(appkey,url,panel,params){
	var title=params.title;
	var width=params.width;
	var height=params.height;
	var result=params.result;
	var win1 = MixkyApp.desktop.openWindowWithJspUrl(
			appkey, 
			panel, 
			url, 
			{
				title : title,
				width : width,
				height: height,
				closable : true,
				minimizable : true,
				modal :true,
				result:result,
				param:params,
				manager : MixkyApp.desktop.getManager()
			},
			{autoScroll:true},
			params
	);
}

//隐藏域调用直接打印
atwasoft.common.lib.DirectPrintPanel =function(reportFile,param,set){
    param = encodeURI(encodeURI(param));
    var appkey = Mixky.wasoft.cache.Applications['report'];
    var width = screen.width;
    var height=screen.height;
    var url = appkey.url + '/jsppage.do?url=app/report/printPanel&reportFile='+reportFile+'&param='+param+'&set='+set;
    if(!atwasoft.common.lib.DirectPrintPanel.el){
	var iframe = document.createElement("iframe");
	iframe.setAttribute("vspace","-300px");
	iframe.setAttribute("hspace","-800px");
	iframe.setAttribute("width","200px");
	iframe.setAttribute("height","700px");
	atwasoft.common.lib.DirectPrintPanel.el = Ext.getBody().appendChild(iframe);
    }
    atwasoft.common.lib.DirectPrintPanel.el.dom.src = url;
};

//弹出窗口展示打印报表以及功能按钮
atwasoft.common.lib.DirectShowReport = function(reportFile,param,title,width,height){
    param = encodeURI(param);
    MixkyApp.desktop.openWindowWithJspUrl(
	    'report', 
	    'reportpanel', 
	    'app/report/showReport', 
	    {title:title,width:width, height:height, modal:false}, 
	    {},
	    {reportFile:reportFile,param:param});
};

//弹出window对话框（全屏），展现打印预览
atwasoft.common.lib.DirectShowPreview = function(reportFile,param,set){
    param = encodeURI(param);
    var appkey = Mixky.wasoft.cache.Applications['report'];
    var width = screen.width;
    var height=screen.height;
    var url = appkey.url + '/jsppage.do?url=app/report/showPreview&width='+width+'&height='+height+'&reportFile='+reportFile+'&param='+param+'&set='+set;
    window.showModalDialog(url,window,'dialogWidth:'+width+'px;dialogHeight:'+height+'px;dialogLeft:'+(screen.width-'+width+')/2+'px;dialogTop:'+(screen.height-'+height+')/2+'px;center:yes;help:no;resizable:no;status:no;scrollbars:no;location=no;');
};

//财务报表隐藏域调用直接打印
atwasoft.common.lib.DirectPrintPanel_cw =function(bbid,date,dateType,param){
    param = encodeURI(encodeURI(param));
    var appkey = Mixky.wasoft.cache.Applications['report'];
    var width = screen.width;
    var height=screen.height;
    var url = appkey.url + '/jsppage.do?url=app/report/prtinPanel_cw&bbid=' + bbid +'&date='+date+'&dateType='+dateType+ '&param='+param;
    if(!atwasoft.common.lib.DirectPrintPanel_cw.el){
	var iframe = document.createElement("iframe");
	iframe.setAttribute("vspace","-300px");
	iframe.setAttribute("hspace","-800px");
	iframe.setAttribute("width","200px");
	iframe.setAttribute("height","700px");
	atwasoft.common.lib.DirectPrintPanel_cw.el = Ext.getBody().appendChild(iframe);
    }
    atwasoft.common.lib.DirectPrintPanel_cw.el.dom.src = url;
};

atwasoft.common.lib.exportexcel = function(grid){
    try {   
	var xls = new ActiveXObject("Excel.Application");   
    }    
    catch (e) { 
	Ext.MessageBox.show({title:'提示',msg:"要打印该表，您必须安装Excel电子表格软件,同时浏览器须使用ActiveX控件,您的浏览器须允许执行控件。请点击【帮助】了解浏览器设置方法！",
	    modal:true,buttons:Ext.Msg.OK,icon:Ext.Msg.INFO,width:450,closable:false});  
    } 
    
    var i=0;

    xls.visible = true; //设置excel为可见    
    var xlBook = xls.Workbooks.Add;   
    var xlSheet = xlBook.Worksheets(1);   

    var cm = grid.getColumnModel();   
    var colCount = cm.getColumnCount();   
    var temp_obj = [];   
    //只下载没有隐藏的列(isHidden()为true表示隐藏,其他都为显示)    
    //临时数组,存放所有当前显示列的下标    
    for (i = 1; i < colCount; i++) {   
	if (cm.isHidden(i) == true) {   
	}   
	else {   
	    temp_obj.push(i);   
	}   
    }   
    for (i = 1; i <= temp_obj.length; i++) {   
	//显示列的列标题    
	xlSheet.Cells(1, i).Value = cm.getColumnHeader(temp_obj[i - 1]);   
	xlSheet.Cells(1,i).HorizontalAlignment = 3;
	xlSheet.Cells(1,i).VerticalAlignment = 2;
	if(cm.config[i].align!='right'){
	    xlSheet.Columns(i).NumberFormat="@";
	}
    }   
    var store = grid.getStore();   
    var recordCount = store.getCount();   
    var view = grid.getView();   
    for (i = 1; i <= recordCount; i++) {   
	for (j = 1; j <= temp_obj.length; j++) {   
	    //EXCEL数据从第二行开始,故row = i + 1;  
	    xlSheet.Cells(i + 1, j).Value = view.getCell(i - 1, temp_obj[j - 1]).innerText;   
	}   
    }   
    xlSheet.Columns.AutoFit;   
    xls.ActiveWindow.Zoom = 75;  
    xls.UserControl = true; //很重要,不能省略,不然会出问题 意思是excel交由用户控制    
    xls = null;   
    xlBook = null;   
    xlSheet = null;  
};
/*获得归集业务日期*/
atwasoft.common.lib.getYwrq = function(){
       try {   
    	   var ugjd = ApplicationInfo.gjd;
    	   if(ugjd != null && ugjd.trim()!=''){
			   if(ConsoleGzAppDirect.getGz_ywrq){
				   ConsoleGzAppDirect.getGz_ywrq(ugjd, function(result,e){
					   if(result && result.success) {
						   var rq = '业务日期：'+new Date(result.ywrq).format('Y年m月d日');
						   MixkyApp.desktop.setYwrq(rq);
					   }
				   });
			   }
    	   }
	    }    
	    catch(e){} 
}
/*获得贷款业务日期*/
atwasoft.common.lib.getDkrq = function(){
	try { 
		var ugjd = ApplicationInfo.gjd;
	    if(ugjd != null && ugjd.trim()!=''){
		   if(Ext.isDefined(CommonAppDirect.gd_Get_Zwdate)){
			   CommonAppDirect.gd_Get_Zwdate(ugjd, function(result,e){
				   if(result && result.success) {
					   if(result.sfrj==1){
						   var rq = '个人信贷日期：'+new Date(result.ksrq).format('Y年m月d日');
						   MixkyApp.desktop.setDkrq(rq); 
					   }
				   }
			   });
		   }
	    }
	}    
    catch (e){} 
}

atwasoft.common.lib.signData = function(strData, returnvalue){
    var recordHandler = function(record){
	returnvalue(record);
    };
    signData(strData);
    var signed = document.homeForm.msg_signed.value;

    DesktopDirect.signTrans(signed.toString(),function(result,e){
	recordHandler(result);
    });
};

/**
 * 发送办理消息信息
 * @param sendto		发送目标
 * @param info			待办消息标题
 * @param type		    0 需办理业务 1需阅读业务
 * @param appKey 		应用系统标识 不能为空
 * @param modelName 	应用模块名称 不能为空
 * @param documentKey	数据文档标识  如果type为1可以为空
 * @param dataId 		对应数据Id    如果没有业务数据值为0
 */
atwasoft.common.lib.info = function(sendto, info, type, appKey, modelName, documentKey, dataId, returnvalue){
    var recordHandler = function(record){
		if(Ext.isFunction(returnvalue)){
		    returnvalue(record);
		}
    };

    DesktopDirect.infoMsg(sendto, info.toString(),parseInt(type,10),appKey.toString(),modelName.toString(),documentKey.toString(),parseInt(dataId,10), function(result,e){
	   recordHandler(result);
    });
};

atwasoft.common.lib.infoMsg = function(obj){
    var sj = MixkyApp.getSubject(null,'sys-info');
    if(Ext.isDefined(sj)){
	var p = Ext.getCmp('portlet-sys-info');
	  p.refresh();
    }
    IM.notify("提示", obj.msg);
};

IM.regMethod("Portal.InfoMsg", atwasoft.common.lib.infoMsg);

/**
 *刷新桌面待办
 */
atwasoft.common.lib.todoMsg = function(obj){
    var sj = MixkyApp.getSubject(null,'sys-todo');
    if(Ext.isDefined(sj)){
	   var p = Ext.getCmp('portlet-sys-todo');
	   p.refresh();
    }
    IM.notify("提示", obj.msg);
};

IM.regMethod("Portal.todoMsg", atwasoft.common.lib.todoMsg);
