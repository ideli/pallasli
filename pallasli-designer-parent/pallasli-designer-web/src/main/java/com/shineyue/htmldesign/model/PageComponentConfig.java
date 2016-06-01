package com.shineyue.htmldesign.model;

public class PageComponentConfig {
    private Integer id;

    private String configKey;

    private String configValue;

    private Integer pageComponentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public Integer getPageComponentId() {
        return pageComponentId;
    }

    public void setPageComponentId(Integer pageComponentId) {
        this.pageComponentId = pageComponentId;
    }
}