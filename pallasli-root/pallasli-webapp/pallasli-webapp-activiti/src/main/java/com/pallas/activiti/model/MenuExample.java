package com.pallas.activiti.model;

import java.util.ArrayList;
import java.util.List;

public class MenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNull() {
            addCriterion("PROJECT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andProjectNameIsNotNull() {
            addCriterion("PROJECT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andProjectNameEqualTo(String value) {
            addCriterion("PROJECT_NAME =", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotEqualTo(String value) {
            addCriterion("PROJECT_NAME <>", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThan(String value) {
            addCriterion("PROJECT_NAME >", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_NAME >=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThan(String value) {
            addCriterion("PROJECT_NAME <", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_NAME <=", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameLike(String value) {
            addCriterion("PROJECT_NAME like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotLike(String value) {
            addCriterion("PROJECT_NAME not like", value, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameIn(List<String> values) {
            addCriterion("PROJECT_NAME in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotIn(List<String> values) {
            addCriterion("PROJECT_NAME not in", values, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameBetween(String value1, String value2) {
            addCriterion("PROJECT_NAME between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andProjectNameNotBetween(String value1, String value2) {
            addCriterion("PROJECT_NAME not between", value1, value2, "projectName");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNull() {
            addCriterion("APP_KEY is null");
            return (Criteria) this;
        }

        public Criteria andAppKeyIsNotNull() {
            addCriterion("APP_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andAppKeyEqualTo(String value) {
            addCriterion("APP_KEY =", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotEqualTo(String value) {
            addCriterion("APP_KEY <>", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThan(String value) {
            addCriterion("APP_KEY >", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyGreaterThanOrEqualTo(String value) {
            addCriterion("APP_KEY >=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThan(String value) {
            addCriterion("APP_KEY <", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLessThanOrEqualTo(String value) {
            addCriterion("APP_KEY <=", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyLike(String value) {
            addCriterion("APP_KEY like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotLike(String value) {
            addCriterion("APP_KEY not like", value, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyIn(List<String> values) {
            addCriterion("APP_KEY in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotIn(List<String> values) {
            addCriterion("APP_KEY not in", values, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyBetween(String value1, String value2) {
            addCriterion("APP_KEY between", value1, value2, "appKey");
            return (Criteria) this;
        }

        public Criteria andAppKeyNotBetween(String value1, String value2) {
            addCriterion("APP_KEY not between", value1, value2, "appKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyIsNull() {
            addCriterion("MENU_KEY is null");
            return (Criteria) this;
        }

        public Criteria andMenuKeyIsNotNull() {
            addCriterion("MENU_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andMenuKeyEqualTo(String value) {
            addCriterion("MENU_KEY =", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyNotEqualTo(String value) {
            addCriterion("MENU_KEY <>", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyGreaterThan(String value) {
            addCriterion("MENU_KEY >", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_KEY >=", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyLessThan(String value) {
            addCriterion("MENU_KEY <", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyLessThanOrEqualTo(String value) {
            addCriterion("MENU_KEY <=", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyLike(String value) {
            addCriterion("MENU_KEY like", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyNotLike(String value) {
            addCriterion("MENU_KEY not like", value, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyIn(List<String> values) {
            addCriterion("MENU_KEY in", values, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyNotIn(List<String> values) {
            addCriterion("MENU_KEY not in", values, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyBetween(String value1, String value2) {
            addCriterion("MENU_KEY between", value1, value2, "menuKey");
            return (Criteria) this;
        }

        public Criteria andMenuKeyNotBetween(String value1, String value2) {
            addCriterion("MENU_KEY not between", value1, value2, "menuKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyIsNull() {
            addCriterion("PARENT_KEY is null");
            return (Criteria) this;
        }

        public Criteria andParentKeyIsNotNull() {
            addCriterion("PARENT_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andParentKeyEqualTo(String value) {
            addCriterion("PARENT_KEY =", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyNotEqualTo(String value) {
            addCriterion("PARENT_KEY <>", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyGreaterThan(String value) {
            addCriterion("PARENT_KEY >", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_KEY >=", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyLessThan(String value) {
            addCriterion("PARENT_KEY <", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyLessThanOrEqualTo(String value) {
            addCriterion("PARENT_KEY <=", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyLike(String value) {
            addCriterion("PARENT_KEY like", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyNotLike(String value) {
            addCriterion("PARENT_KEY not like", value, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyIn(List<String> values) {
            addCriterion("PARENT_KEY in", values, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyNotIn(List<String> values) {
            addCriterion("PARENT_KEY not in", values, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyBetween(String value1, String value2) {
            addCriterion("PARENT_KEY between", value1, value2, "parentKey");
            return (Criteria) this;
        }

        public Criteria andParentKeyNotBetween(String value1, String value2) {
            addCriterion("PARENT_KEY not between", value1, value2, "parentKey");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNull() {
            addCriterion("MENU_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMenuNameIsNotNull() {
            addCriterion("MENU_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNameEqualTo(String value) {
            addCriterion("MENU_NAME =", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotEqualTo(String value) {
            addCriterion("MENU_NAME <>", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThan(String value) {
            addCriterion("MENU_NAME >", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_NAME >=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThan(String value) {
            addCriterion("MENU_NAME <", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLessThanOrEqualTo(String value) {
            addCriterion("MENU_NAME <=", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameLike(String value) {
            addCriterion("MENU_NAME like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotLike(String value) {
            addCriterion("MENU_NAME not like", value, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameIn(List<String> values) {
            addCriterion("MENU_NAME in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotIn(List<String> values) {
            addCriterion("MENU_NAME not in", values, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameBetween(String value1, String value2) {
            addCriterion("MENU_NAME between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuNameNotBetween(String value1, String value2) {
            addCriterion("MENU_NAME not between", value1, value2, "menuName");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionIsNull() {
            addCriterion("MENU_CAPTION is null");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionIsNotNull() {
            addCriterion("MENU_CAPTION is not null");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionEqualTo(String value) {
            addCriterion("MENU_CAPTION =", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionNotEqualTo(String value) {
            addCriterion("MENU_CAPTION <>", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionGreaterThan(String value) {
            addCriterion("MENU_CAPTION >", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_CAPTION >=", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionLessThan(String value) {
            addCriterion("MENU_CAPTION <", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionLessThanOrEqualTo(String value) {
            addCriterion("MENU_CAPTION <=", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionLike(String value) {
            addCriterion("MENU_CAPTION like", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionNotLike(String value) {
            addCriterion("MENU_CAPTION not like", value, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionIn(List<String> values) {
            addCriterion("MENU_CAPTION in", values, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionNotIn(List<String> values) {
            addCriterion("MENU_CAPTION not in", values, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionBetween(String value1, String value2) {
            addCriterion("MENU_CAPTION between", value1, value2, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andMenuCaptionNotBetween(String value1, String value2) {
            addCriterion("MENU_CAPTION not between", value1, value2, "menuCaption");
            return (Criteria) this;
        }

        public Criteria andUrlPathIsNull() {
            addCriterion("URL_PATH is null");
            return (Criteria) this;
        }

        public Criteria andUrlPathIsNotNull() {
            addCriterion("URL_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andUrlPathEqualTo(String value) {
            addCriterion("URL_PATH =", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathNotEqualTo(String value) {
            addCriterion("URL_PATH <>", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathGreaterThan(String value) {
            addCriterion("URL_PATH >", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathGreaterThanOrEqualTo(String value) {
            addCriterion("URL_PATH >=", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathLessThan(String value) {
            addCriterion("URL_PATH <", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathLessThanOrEqualTo(String value) {
            addCriterion("URL_PATH <=", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathLike(String value) {
            addCriterion("URL_PATH like", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathNotLike(String value) {
            addCriterion("URL_PATH not like", value, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathIn(List<String> values) {
            addCriterion("URL_PATH in", values, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathNotIn(List<String> values) {
            addCriterion("URL_PATH not in", values, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathBetween(String value1, String value2) {
            addCriterion("URL_PATH between", value1, value2, "urlPath");
            return (Criteria) this;
        }

        public Criteria andUrlPathNotBetween(String value1, String value2) {
            addCriterion("URL_PATH not between", value1, value2, "urlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathIsNull() {
            addCriterion("CHILD_URL_PATH is null");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathIsNotNull() {
            addCriterion("CHILD_URL_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathEqualTo(String value) {
            addCriterion("CHILD_URL_PATH =", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathNotEqualTo(String value) {
            addCriterion("CHILD_URL_PATH <>", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathGreaterThan(String value) {
            addCriterion("CHILD_URL_PATH >", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathGreaterThanOrEqualTo(String value) {
            addCriterion("CHILD_URL_PATH >=", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathLessThan(String value) {
            addCriterion("CHILD_URL_PATH <", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathLessThanOrEqualTo(String value) {
            addCriterion("CHILD_URL_PATH <=", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathLike(String value) {
            addCriterion("CHILD_URL_PATH like", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathNotLike(String value) {
            addCriterion("CHILD_URL_PATH not like", value, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathIn(List<String> values) {
            addCriterion("CHILD_URL_PATH in", values, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathNotIn(List<String> values) {
            addCriterion("CHILD_URL_PATH not in", values, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathBetween(String value1, String value2) {
            addCriterion("CHILD_URL_PATH between", value1, value2, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andChildUrlPathNotBetween(String value1, String value2) {
            addCriterion("CHILD_URL_PATH not between", value1, value2, "childUrlPath");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameIsNull() {
            addCriterion("MENU_TABLE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameIsNotNull() {
            addCriterion("MENU_TABLE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameEqualTo(String value) {
            addCriterion("MENU_TABLE_NAME =", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameNotEqualTo(String value) {
            addCriterion("MENU_TABLE_NAME <>", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameGreaterThan(String value) {
            addCriterion("MENU_TABLE_NAME >", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_TABLE_NAME >=", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameLessThan(String value) {
            addCriterion("MENU_TABLE_NAME <", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameLessThanOrEqualTo(String value) {
            addCriterion("MENU_TABLE_NAME <=", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameLike(String value) {
            addCriterion("MENU_TABLE_NAME like", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameNotLike(String value) {
            addCriterion("MENU_TABLE_NAME not like", value, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameIn(List<String> values) {
            addCriterion("MENU_TABLE_NAME in", values, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameNotIn(List<String> values) {
            addCriterion("MENU_TABLE_NAME not in", values, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameBetween(String value1, String value2) {
            addCriterion("MENU_TABLE_NAME between", value1, value2, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuTableNameNotBetween(String value1, String value2) {
            addCriterion("MENU_TABLE_NAME not between", value1, value2, "menuTableName");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlIsNull() {
            addCriterion("MENU_WHERE_SQL is null");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlIsNotNull() {
            addCriterion("MENU_WHERE_SQL is not null");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlEqualTo(String value) {
            addCriterion("MENU_WHERE_SQL =", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlNotEqualTo(String value) {
            addCriterion("MENU_WHERE_SQL <>", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlGreaterThan(String value) {
            addCriterion("MENU_WHERE_SQL >", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_WHERE_SQL >=", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlLessThan(String value) {
            addCriterion("MENU_WHERE_SQL <", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlLessThanOrEqualTo(String value) {
            addCriterion("MENU_WHERE_SQL <=", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlLike(String value) {
            addCriterion("MENU_WHERE_SQL like", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlNotLike(String value) {
            addCriterion("MENU_WHERE_SQL not like", value, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlIn(List<String> values) {
            addCriterion("MENU_WHERE_SQL in", values, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlNotIn(List<String> values) {
            addCriterion("MENU_WHERE_SQL not in", values, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlBetween(String value1, String value2) {
            addCriterion("MENU_WHERE_SQL between", value1, value2, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuWhereSqlNotBetween(String value1, String value2) {
            addCriterion("MENU_WHERE_SQL not between", value1, value2, "menuWhereSql");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeIsNull() {
            addCriterion("MENU_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeIsNotNull() {
            addCriterion("MENU_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeEqualTo(String value) {
            addCriterion("MENU_TYPE_CODE =", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeNotEqualTo(String value) {
            addCriterion("MENU_TYPE_CODE <>", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeGreaterThan(String value) {
            addCriterion("MENU_TYPE_CODE >", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_TYPE_CODE >=", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeLessThan(String value) {
            addCriterion("MENU_TYPE_CODE <", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("MENU_TYPE_CODE <=", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeLike(String value) {
            addCriterion("MENU_TYPE_CODE like", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeNotLike(String value) {
            addCriterion("MENU_TYPE_CODE not like", value, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeIn(List<String> values) {
            addCriterion("MENU_TYPE_CODE in", values, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeNotIn(List<String> values) {
            addCriterion("MENU_TYPE_CODE not in", values, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeBetween(String value1, String value2) {
            addCriterion("MENU_TYPE_CODE between", value1, value2, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuTypeCodeNotBetween(String value1, String value2) {
            addCriterion("MENU_TYPE_CODE not between", value1, value2, "menuTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeIsNull() {
            addCriterion("PAGE_TYPE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeIsNotNull() {
            addCriterion("PAGE_TYPE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeEqualTo(String value) {
            addCriterion("PAGE_TYPE_CODE =", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeNotEqualTo(String value) {
            addCriterion("PAGE_TYPE_CODE <>", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeGreaterThan(String value) {
            addCriterion("PAGE_TYPE_CODE >", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_TYPE_CODE >=", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeLessThan(String value) {
            addCriterion("PAGE_TYPE_CODE <", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("PAGE_TYPE_CODE <=", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeLike(String value) {
            addCriterion("PAGE_TYPE_CODE like", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeNotLike(String value) {
            addCriterion("PAGE_TYPE_CODE not like", value, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeIn(List<String> values) {
            addCriterion("PAGE_TYPE_CODE in", values, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeNotIn(List<String> values) {
            addCriterion("PAGE_TYPE_CODE not in", values, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeBetween(String value1, String value2) {
            addCriterion("PAGE_TYPE_CODE between", value1, value2, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andPageTypeCodeNotBetween(String value1, String value2) {
            addCriterion("PAGE_TYPE_CODE not between", value1, value2, "pageTypeCode");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsIsNull() {
            addCriterion("MENU_CONFIGS is null");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsIsNotNull() {
            addCriterion("MENU_CONFIGS is not null");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsEqualTo(String value) {
            addCriterion("MENU_CONFIGS =", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsNotEqualTo(String value) {
            addCriterion("MENU_CONFIGS <>", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsGreaterThan(String value) {
            addCriterion("MENU_CONFIGS >", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsGreaterThanOrEqualTo(String value) {
            addCriterion("MENU_CONFIGS >=", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsLessThan(String value) {
            addCriterion("MENU_CONFIGS <", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsLessThanOrEqualTo(String value) {
            addCriterion("MENU_CONFIGS <=", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsLike(String value) {
            addCriterion("MENU_CONFIGS like", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsNotLike(String value) {
            addCriterion("MENU_CONFIGS not like", value, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsIn(List<String> values) {
            addCriterion("MENU_CONFIGS in", values, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsNotIn(List<String> values) {
            addCriterion("MENU_CONFIGS not in", values, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsBetween(String value1, String value2) {
            addCriterion("MENU_CONFIGS between", value1, value2, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andMenuConfigsNotBetween(String value1, String value2) {
            addCriterion("MENU_CONFIGS not between", value1, value2, "menuConfigs");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}