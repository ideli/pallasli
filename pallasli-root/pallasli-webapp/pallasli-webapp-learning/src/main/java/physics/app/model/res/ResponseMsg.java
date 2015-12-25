package physics.app.model.res;

import java.util.List;

public class ResponseMsg {
	private String data;
	private boolean success;
	private List<?> treeMenu;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<?> getTreeMenu() {
		return treeMenu;
	}

	public void setTreeMenu(List<?> treeMenu) {
		this.treeMenu = treeMenu;
	}
}
