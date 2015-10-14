var email = {
	xtype : "textfield",
	type : "email",
	required : true,
	email : true,
	name : "Email",
	fieldLabel : "邮箱",
	emptyText : "请输入邮箱"
};
var password = {
	xtype :  "password",
	required : true,
	zipCode : true,
	minlength : 5,
	name : "password",
	fieldLabel : "密码",
	emptyText : "请输入密码"
};
var confirm_password = {
	xtype : "password",
	required : true,
	zipCode : true,
	minlength : 5,
	name : "confirm_password",
	fieldLabel : "密码",
	emptyText : "请确认密码"
};
divId = "tmp_div";
var btn = {
	text : "消息框",
	xtype : "button",
	icon:"user",
	size:"lg",
	type:"success",
	handler : function(e) {
		//new Pallasli.window.Message({}).error({width:300,height:200});
		alert(0)
		return;
		if (pallasli.validateForm(formName)) {
			pallasli.commitForm(formName, loadUrl, function(result) {
				console.log(result);
			});
		}
	}
};

var treePanel = new Pallasli.panel.tree.Tree({
	region : "west",
	width : 200,
	items : [ {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "字段设置"
	}, {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "接口设置"
	}, {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "页面设置"
	}, {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "系统设置"
	}, {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "组件定义"
	}, {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "预览效果"
	}, {
		url : "engine/view.html?jsurl=../initjs/fields.js",
		text : "切换空间"
	} ]
});

var form = new Pallasli.panel.Form({
	region : "west",
	xtype:"form",
	width:900,
	layout:"column",
	items : [ email, password, confirm_password, btn, {
		xtype : "checkbox",
		value:"1",text:"2",
		fieldLabel : "复选框"
	},{
		xtype : "radio",
		fieldLabel : "单选按钮"
	},  {
		xtype : "combo",
		fieldLabel : "下拉框"
	}, {
		xtype : "htmleditor",
		fieldLabel : "富文本"
	} , {
		xtype : "textarea",
		fieldLabel : "文本域"
	} , {
		xtype : "fileupload",
		fieldLabel : "上传"
	},  {
		xtype : "label",
		text : "标签"
	},  {
		xtype : "password",
		fieldLabel : "密码"
	},  {
		xtype : "time",
		fieldLabel : "时间"
	},  {
		xtype : "date",
		fieldLabel : "日期"
	},  {
		xtype : "datetime",
		fieldLabel : "日期时间"
	},  {
		xtype : "month",
		fieldLabel : "月份"
	},  {
		xtype : "week",
		fieldLabel : "周"
	},  {
		xtype : "color",
		text : "颜色选择器"
	},  {
		xtype : "tel",
		fieldLabel : "电话"
	},  {
		xtype : "url",
		fieldLabel : "url"
	},  {
		xtype : "hidden",
		fieldLabel : "隐藏"
	},  {
		xtype : "search",
		fieldLabel : "搜索"
	},  {
		xtype : "email",
		fieldLabel : "邮件"
	},  {
		xtype : "submit",
		text : "提交"
	},  {
		xtype : "reset",
		text : "重置"
	} ]
});

var grid = new Pallasli.panel.Grid({
	xtype : "grid", 
	theadGroup:[{text:"group1",colspan:3},{text:"group2"},{text:"group3",colspan:2},{text:"group4"}],
	thead:[{text:"col1"},{text:"col2"},{text:"col3"},{text:"col4"},{text:"col5"},{text:"col6"},{text:"col7"},],
	datastore:[
	           [{text:"r1c1",cls:"active"},{text:"r1c2"},{text:"r1c3"},{text:"r1c4"},{text:"r1c5"},{text:"r1c6"},{text:"r1c7"}],
	           [{text:"r2c1",cls:"success"},{text:"r2c2"},{text:"r2c3"},{text:"r2c4"},{text:"r2c5"},{text:"r2c6"},{text:"r2c7"}],
	           [{text:"r3c1",cls:"info"},{text:"r3c2"},{text:"r3c3"},{text:"r3c4"},{text:"r3c5"},{text:"r3c6"},{text:"r3c7"}],
	           [{text:"r4c1",cls:"danger"},{text:"r4c2"},{text:"r4c3"},{text:"r4c4"},{text:"r4c5"},{text:"r4c6"},{text:"r4c7"}]
				] 
});

var grid2 = new Pallasli.panel.Grid({
	xtype : "grid", 
	theadGroup:[{text:"group1",colspan:3},{text:"group2"},{text:"group3",colspan:2},{text:"group4"}],
	thead:[{text:"col1"},{text:"col2"},{text:"col3"},{text:"col4"},{text:"col5"},{text:"col6"},{text:"col7"},],
	datastore:[
	           [{text:"r1c1",cls:"active"},{text:"r1c2"},{text:"r1c3"},{text:"r1c4"},{text:"r1c5"},{text:"r1c6"},{text:"r1c7"}],
	           [{text:"r2c1",cls:"success"},{text:"r2c2"},{text:"r2c3"},{text:"r2c4"},{text:"r2c5"},{text:"r2c6"},{text:"r2c7"}],
	           [{text:"r3c1",cls:"info"},{text:"r3c2"},{text:"r3c3"},{text:"r3c4"},{text:"r3c5"},{text:"r3c6"},{text:"r3c7"}],
	           [{text:"r4c1",cls:"danger"},{text:"r4c2"},{text:"r4c3"},{text:"r4c4"},{text:"r4c5"},{text:"r4c6"},{text:"r4c7"}]
				] 
});

new Pallasli.window.ViewPort({
	//items : [treePanel, form,grid]
	items : [{xtype:"panel",layout:"row",items:[treePanel, form]},{xtype:"panel",layout:"row",items:[grid,grid2]}]
});