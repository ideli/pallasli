package com.pallas.designer.resolve.reader;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.designer.resolve.DesignObject;
import com.pallas.designer.resolve.model.Layout;

public class DesignForExt4Reader extends DesignReader {

	@Override
	public JsonArray getLayout(DesignObject layout) {
		JsonArray layoutJson = new JsonArray();
		List<Layout> children = ((Layout) layout).getChildren();

		if (children != null && !children.isEmpty()) {

			for (Layout l : children) {

				JsonObject json = new JsonObject();
				json.addProperty("xtype", "panel");
				JsonArray items = getLayout(l);
				json.add("items", items);
				layoutJson.add(json);

			}
		} else {
			JsonObject json = new JsonObject();
			json.addProperty("xtype", "textfield");
			layoutJson.add(json);
		}
		return layoutJson;
	}

	@Override
	public void doLayout(DesignObject layout) {
		JsonArray layoutJson = getLayout(layout);
	}

	@Override
	public DesignObject[] getChildren(DesignObject parent) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void getField(DesignObject field) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void getPanel(DesignObject panel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void getStore(DesignObject store) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void getTable(DesignObject table) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DesignObject loadLayout() {
		DesignObject layout = new Layout();
		String json = com.pallasli.utils.JsonUtils.getJsonContent("/data/designer/pages/page1/layout.json");
		layout = fromJsonToLayout(json);
		return layout;
	}

}
