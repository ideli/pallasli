package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class TinyMCEField extends IFieldEditor {

	public JsonObject getEditor(Field field){
		JsonObject json = new JsonObject();
        
		json.addProperty("xtype", "tinymce");
		json.addProperty("anchor", "100%");
		json.addProperty("name", field.getF_key());
		json.addProperty("hideLabel", true);
		
		json.addProperty("hideMode", "offsets");
		String tinymceSettings="{theme : 'advanced',"+
					"plugins: 'pagebreak,style,layer,table,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,noneditable,visualchars,nonbreaking,xhtmlxtras,template',"+
					"theme_advanced_buttons1 : 'newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect',"+
					"theme_advanced_buttons2 : 'cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor',"+
					"theme_advanced_buttons3 : 'tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|',"+
					"theme_advanced_buttons4 : 'insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak',"+
					"theme_advanced_toolbar_location : 'top',"+
					"theme_advanced_toolbar_align : 'left',"+
					"theme_advanced_statusbar_location : '',"+
					"theme_advanced_resizing : true,language:'zh-cn'}";
		json.add("tinymceSettings", new JsonFunction(tinymceSettings));
		json.addProperty("value", " ");
		
		JsonObjectTool.applyJson(json, field.getF_config());
		
		return json;
	}
}

