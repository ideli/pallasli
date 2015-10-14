package com.pallas.activiti.model;

public class Menu {
    private Long id;

    private String projectName;

    private String appKey;

    private String menuKey;

    private String parentKey;

    private String menuName;

    private String menuCaption;

    private String urlPath;

    private String childUrlPath;

    private String menuTableName;

    private String menuWhereSql;

    private String menuTypeCode;

    private String pageTypeCode;

    private String menuConfigs;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey == null ? null : menuKey.trim();
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey == null ? null : parentKey.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuCaption() {
        return menuCaption;
    }

    public void setMenuCaption(String menuCaption) {
        this.menuCaption = menuCaption == null ? null : menuCaption.trim();
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath == null ? null : urlPath.trim();
    }

    public String getChildUrlPath() {
        return childUrlPath;
    }

    public void setChildUrlPath(String childUrlPath) {
        this.childUrlPath = childUrlPath == null ? null : childUrlPath.trim();
    }

    public String getMenuTableName() {
        return menuTableName;
    }

    public void setMenuTableName(String menuTableName) {
        this.menuTableName = menuTableName == null ? null : menuTableName.trim();
    }

    public String getMenuWhereSql() {
        return menuWhereSql;
    }

    public void setMenuWhereSql(String menuWhereSql) {
        this.menuWhereSql = menuWhereSql == null ? null : menuWhereSql.trim();
    }

    public String getMenuTypeCode() {
        return menuTypeCode;
    }

    public void setMenuTypeCode(String menuTypeCode) {
        this.menuTypeCode = menuTypeCode == null ? null : menuTypeCode.trim();
    }

    public String getPageTypeCode() {
        return pageTypeCode;
    }

    public void setPageTypeCode(String pageTypeCode) {
        this.pageTypeCode = pageTypeCode == null ? null : pageTypeCode.trim();
    }

    public String getMenuConfigs() {
        return menuConfigs;
    }

    public void setMenuConfigs(String menuConfigs) {
        this.menuConfigs = menuConfigs == null ? null : menuConfigs.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}