package com.pallas.portal.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Menu {

	/** identifier field */
	private Long id;

	/** persistent field */
	private String projectName;

	/** persistent field */
	private String menuKey;

	/** persistent field */
	private String parentKey;

	/** persistent field */
	private String menuName;

	/** persistent field */
	private String menuCaption;

	/** nullable persistent field */
	private String urlPath;

	/** nullable persistent field */
	private String childUrlPath;

	/** nullable persistent field */
	private String menuTableName;

	/** nullable persistent field */
	private String menuWhereSql;

	/** persistent field */
	private String menuTypeCode;

	/** persistent field */
	private String pageTypeCode;

	/** nullable persistent field */
	private String menuConfigs;

	/** persistent field */
	private int version;

	/** full constructor */
	public Menu(Long id, String projectName, String menuKey, String parentKey,
			String menuName, String menuCaption, String urlPath,
			String childUrlPath, String menuTableName, String menuWhereSql,
			String menuTypeCode, String pageTypeCode, String menuConfigs,
			int version) {
		this.id = id;
		this.projectName = projectName;
		this.menuKey = menuKey;
		this.parentKey = parentKey;
		this.menuName = menuName;
		this.menuCaption = menuCaption;
		this.urlPath = urlPath;
		this.childUrlPath = childUrlPath;
		this.menuTableName = menuTableName;
		this.menuWhereSql = menuWhereSql;
		this.menuTypeCode = menuTypeCode;
		this.pageTypeCode = pageTypeCode;
		this.menuConfigs = menuConfigs;
		this.version = version;
	}

	/** default constructor */
	public Menu() {
	}

	/** minimal constructor */
	public Menu(Long id, String projectName, String menuKey, String parentKey,
			String menuName, String menuCaption, String menuTypeCode,
			String pageTypeCode, int version) {
		this.id = id;
		this.projectName = projectName;
		this.menuKey = menuKey;
		this.parentKey = parentKey;
		this.menuName = menuName;
		this.menuCaption = menuCaption;
		this.menuTypeCode = menuTypeCode;
		this.pageTypeCode = pageTypeCode;
		this.version = version;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMenuKey() {
		return this.menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}

	public String getParentKey() {
		return this.parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCaption() {
		return this.menuCaption;
	}

	public void setMenuCaption(String menuCaption) {
		this.menuCaption = menuCaption;
	}

	public String getUrlPath() {
		return this.urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getChildUrlPath() {
		return this.childUrlPath;
	}

	public void setChildUrlPath(String childUrlPath) {
		this.childUrlPath = childUrlPath;
	}

	public String getMenuTableName() {
		return this.menuTableName;
	}

	public void setMenuTableName(String menuTableName) {
		this.menuTableName = menuTableName;
	}

	public String getMenuWhereSql() {
		return this.menuWhereSql;
	}

	public void setMenuWhereSql(String menuWhereSql) {
		this.menuWhereSql = menuWhereSql;
	}

	public String getMenuTypeCode() {
		return this.menuTypeCode;
	}

	public void setMenuTypeCode(String menuTypeCode) {
		this.menuTypeCode = menuTypeCode;
	}

	public String getPageTypeCode() {
		return this.pageTypeCode;
	}

	public void setPageTypeCode(String pageTypeCode) {
		this.pageTypeCode = pageTypeCode;
	}

	public String getMenuConfigs() {
		return this.menuConfigs;
	}

	public void setMenuConfigs(String menuConfigs) {
		this.menuConfigs = menuConfigs;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).toString();
	}

}
