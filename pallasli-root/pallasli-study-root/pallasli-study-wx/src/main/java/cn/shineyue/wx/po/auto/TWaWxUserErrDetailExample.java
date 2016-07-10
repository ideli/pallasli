package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWaWxUserErrDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWaWxUserErrDetailExample() {
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

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGrbhIsNull() {
            addCriterion("GRBH is null");
            return (Criteria) this;
        }

        public Criteria andGrbhIsNotNull() {
            addCriterion("GRBH is not null");
            return (Criteria) this;
        }

        public Criteria andGrbhEqualTo(String value) {
            addCriterion("GRBH =", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhNotEqualTo(String value) {
            addCriterion("GRBH <>", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhGreaterThan(String value) {
            addCriterion("GRBH >", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhGreaterThanOrEqualTo(String value) {
            addCriterion("GRBH >=", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhLessThan(String value) {
            addCriterion("GRBH <", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhLessThanOrEqualTo(String value) {
            addCriterion("GRBH <=", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhIn(List<String> values) {
            addCriterion("GRBH in", values, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhNotIn(List<String> values) {
            addCriterion("GRBH not in", values, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhBetween(String value1, String value2) {
            addCriterion("GRBH between", value1, value2, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhNotBetween(String value1, String value2) {
            addCriterion("GRBH not between", value1, value2, "grbh");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidIsNull() {
            addCriterion("WX_ERR_OPENID is null");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidIsNotNull() {
            addCriterion("WX_ERR_OPENID is not null");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidEqualTo(String value) {
            addCriterion("WX_ERR_OPENID =", value, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidNotEqualTo(String value) {
            addCriterion("WX_ERR_OPENID <>", value, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidGreaterThan(String value) {
            addCriterion("WX_ERR_OPENID >", value, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("WX_ERR_OPENID >=", value, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidLessThan(String value) {
            addCriterion("WX_ERR_OPENID <", value, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidLessThanOrEqualTo(String value) {
            addCriterion("WX_ERR_OPENID <=", value, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidIn(List<String> values) {
            addCriterion("WX_ERR_OPENID in", values, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidNotIn(List<String> values) {
            addCriterion("WX_ERR_OPENID not in", values, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidBetween(String value1, String value2) {
            addCriterion("WX_ERR_OPENID between", value1, value2, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andWxErrOpenidNotBetween(String value1, String value2) {
            addCriterion("WX_ERR_OPENID not between", value1, value2, "wxErrOpenid");
            return (Criteria) this;
        }

        public Criteria andRegErrDateIsNull() {
            addCriterion("REG_ERR_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegErrDateIsNotNull() {
            addCriterion("REG_ERR_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegErrDateEqualTo(Date value) {
            addCriterion("REG_ERR_DATE =", value, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateNotEqualTo(Date value) {
            addCriterion("REG_ERR_DATE <>", value, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateGreaterThan(Date value) {
            addCriterion("REG_ERR_DATE >", value, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateGreaterThanOrEqualTo(Date value) {
            addCriterion("REG_ERR_DATE >=", value, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateLessThan(Date value) {
            addCriterion("REG_ERR_DATE <", value, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateLessThanOrEqualTo(Date value) {
            addCriterion("REG_ERR_DATE <=", value, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateIn(List<Date> values) {
            addCriterion("REG_ERR_DATE in", values, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateNotIn(List<Date> values) {
            addCriterion("REG_ERR_DATE not in", values, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateBetween(Date value1, Date value2) {
            addCriterion("REG_ERR_DATE between", value1, value2, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andRegErrDateNotBetween(Date value1, Date value2) {
            addCriterion("REG_ERR_DATE not between", value1, value2, "regErrDate");
            return (Criteria) this;
        }

        public Criteria andToBlacklistIsNull() {
            addCriterion("TO_BLACKLIST is null");
            return (Criteria) this;
        }

        public Criteria andToBlacklistIsNotNull() {
            addCriterion("TO_BLACKLIST is not null");
            return (Criteria) this;
        }

        public Criteria andToBlacklistEqualTo(Short value) {
            addCriterion("TO_BLACKLIST =", value, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistNotEqualTo(Short value) {
            addCriterion("TO_BLACKLIST <>", value, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistGreaterThan(Short value) {
            addCriterion("TO_BLACKLIST >", value, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistGreaterThanOrEqualTo(Short value) {
            addCriterion("TO_BLACKLIST >=", value, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistLessThan(Short value) {
            addCriterion("TO_BLACKLIST <", value, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistLessThanOrEqualTo(Short value) {
            addCriterion("TO_BLACKLIST <=", value, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistIn(List<Short> values) {
            addCriterion("TO_BLACKLIST in", values, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistNotIn(List<Short> values) {
            addCriterion("TO_BLACKLIST not in", values, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistBetween(Short value1, Short value2) {
            addCriterion("TO_BLACKLIST between", value1, value2, "toBlacklist");
            return (Criteria) this;
        }

        public Criteria andToBlacklistNotBetween(Short value1, Short value2) {
            addCriterion("TO_BLACKLIST not between", value1, value2, "toBlacklist");
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