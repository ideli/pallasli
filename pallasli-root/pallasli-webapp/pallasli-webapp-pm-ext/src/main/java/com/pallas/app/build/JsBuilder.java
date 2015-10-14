package com.pallas.app.build;

import com.google.gson.JsonFunction;
import com.google.gson.JsonObject;
import com.pallas.app.build.bean.Action;
import com.pallas.app.build.bean.Column;
import com.pallas.app.build.bean.View;

public class JsBuilder {
	public static View buildView(JsonObject viewJson) {
		Column c1 = new Column();
		c1.setF_caption("id");
		c1.setF_key("id");
		c1.setF_name("id");
		c1.setF_flex(1);
		c1.setF_type("1");

		Action a1 = new Action();
		a1.setF_caption("创建");
		a1.setF_key("btn_add");
		a1.setF_name("btn_add");
		JsonFunction jsFun = new JsonFunction(
				"Function Script:function(){alert(1);}");
		a1.setF_handler(jsFun);

		Column[] columns = new Column[1];
		Action[] actions = new Action[1];

		columns[0] = c1;
		actions[0] = a1;

		View v = new View();
		v.setF_actions(actions);
		v.setF_caption("流程定义管理");
		v.setF_columns(columns);
		v.setF_key("progress_definition");
		v.setF_name("progress_definition");
		return v;
	}
}
