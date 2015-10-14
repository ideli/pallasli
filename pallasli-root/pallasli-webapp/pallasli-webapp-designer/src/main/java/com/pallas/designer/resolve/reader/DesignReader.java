package com.pallas.designer.resolve.reader;

import com.google.gson.JsonArray;
import com.pallas.designer.resolve.DesignObject;
import com.pallas.designer.resolve.model.Layout;

public abstract class DesignReader {

	public abstract DesignObject loadLayout();

	public abstract JsonArray getLayout(DesignObject layout);

	public abstract void doLayout(DesignObject layout);

	public abstract DesignObject[] getChildren(DesignObject parent);

	/**
	 * 转化字段
	 */
	public abstract void getField(DesignObject field);

	/**
	 * 转化面板
	 */
	public abstract void getPanel(DesignObject panel);

	/**
	 * 转化数据源
	 */
	public abstract void getStore(DesignObject store);

	/**
	 * 转化表格
	 */
	public abstract void getTable(DesignObject table);

	public Layout fromJsonToLayout(String json) {
		Layout l = new Layout();
		return l;
	}
}
