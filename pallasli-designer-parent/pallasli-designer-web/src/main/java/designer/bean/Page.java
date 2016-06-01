package designer.bean;

import java.util.List;

public class Page {
	List<PageComp> f_children;

	private String f_key;
	private String f_caption;
	private String f_url;
	private int f_layout;

	public List<PageComp> getF_children() {
		return f_children;
	}

	public void setF_children(List<PageComp> f_children) {
		this.f_children = f_children;
	}

	public String getF_key() {
		return f_key;
	}

	public void setF_key(String f_key) {
		this.f_key = f_key;
	}

	public String getF_caption() {
		return f_caption;
	}

	public void setF_caption(String f_caption) {
		this.f_caption = f_caption;
	}

	public String getF_url() {
		return f_url;
	}

	public void setF_url(String f_url) {
		this.f_url = f_url;
	}

	public int getF_layout() {
		return f_layout;
	}

	public void setF_layout(int f_layout) {
		this.f_layout = f_layout;
	}

}
