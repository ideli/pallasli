package designer.bean;

import java.util.List;

public class PageComp extends Comp {

	private List<PageComp> f_children;
	private String f_config;

	public List<PageComp> getF_children() {
		return f_children;
	}

	public void setF_children(List<PageComp> f_children) {
		this.f_children = f_children;
	}

	public String getF_config() {
		return f_config;
	}

	public void setF_config(String f_config) {
		this.f_config = f_config;
	}

}
