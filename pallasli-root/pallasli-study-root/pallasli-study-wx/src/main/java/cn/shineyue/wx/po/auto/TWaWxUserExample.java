package cn.shineyue.wx.po.auto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWaWxUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWaWxUserExample() {
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

        public Criteria andWxOpenidIsNull() {
            addCriterion("WX_OPENID is null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIsNotNull() {
            addCriterion("WX_OPENID is not null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidEqualTo(String value) {
            addCriterion("WX_OPENID =", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotEqualTo(String value) {
            addCriterion("WX_OPENID <>", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThan(String value) {
            addCriterion("WX_OPENID >", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("WX_OPENID >=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThan(String value) {
            addCriterion("WX_OPENID <", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThanOrEqualTo(String value) {
            addCriterion("WX_OPENID <=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIn(List<String> values) {
            addCriterion("WX_OPENID in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotIn(List<String> values) {
            addCriterion("WX_OPENID not in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidBetween(String value1, String value2) {
            addCriterion("WX_OPENID between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotBetween(String value1, String value2) {
            addCriterion("WX_OPENID not between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNull() {
            addCriterion("REG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNotNull() {
            addCriterion("REG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegDateEqualTo(Date value) {
            addCriterion("REG_DATE =", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotEqualTo(Date value) {
            addCriterion("REG_DATE <>", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThan(Date value) {
            addCriterion("REG_DATE >", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThanOrEqualTo(Date value) {
            addCriterion("REG_DATE >=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThan(Date value) {
            addCriterion("REG_DATE <", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThanOrEqualTo(Date value) {
            addCriterion("REG_DATE <=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateIn(List<Date> values) {
            addCriterion("REG_DATE in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotIn(List<Date> values) {
            addCriterion("REG_DATE not in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateBetween(Date value1, Date value2) {
            addCriterion("REG_DATE between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotBetween(Date value1, Date value2) {
            addCriterion("REG_DATE not between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andLockedIsNull() {
            addCriterion("LOCKED is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("LOCKED is not null");
            return (Criteria) this;
        }

        public Criteria andLockedEqualTo(Short value) {
            addCriterion("LOCKED =", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(Short value) {
            addCriterion("LOCKED <>", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(Short value) {
            addCriterion("LOCKED >", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(Short value) {
            addCriterion("LOCKED >=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(Short value) {
            addCriterion("LOCKED <", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(Short value) {
            addCriterion("LOCKED <=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedIn(List<Short> values) {
            addCriterion("LOCKED in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<Short> values) {
            addCriterion("LOCKED not in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(Short value1, Short value2) {
            addCriterion("LOCKED between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(Short value1, Short value2) {
            addCriterion("LOCKED not between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockOpenidIsNull() {
            addCriterion("LOCK_OPENID is null");
            return (Criteria) this;
        }

        public Criteria andLockOpenidIsNotNull() {
            addCriterion("LOCK_OPENID is not null");
            return (Criteria) this;
        }

        public Criteria andLockOpenidEqualTo(String value) {
            addCriterion("LOCK_OPENID =", value, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidNotEqualTo(String value) {
            addCriterion("LOCK_OPENID <>", value, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidGreaterThan(String value) {
            addCriterion("LOCK_OPENID >", value, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_OPENID >=", value, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidLessThan(String value) {
            addCriterion("LOCK_OPENID <", value, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidLessThanOrEqualTo(String value) {
            addCriterion("LOCK_OPENID <=", value, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidIn(List<String> values) {
            addCriterion("LOCK_OPENID in", values, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidNotIn(List<String> values) {
            addCriterion("LOCK_OPENID not in", values, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidBetween(String value1, String value2) {
            addCriterion("LOCK_OPENID between", value1, value2, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockOpenidNotBetween(String value1, String value2) {
            addCriterion("LOCK_OPENID not between", value1, value2, "lockOpenid");
            return (Criteria) this;
        }

        public Criteria andLockDateIsNull() {
            addCriterion("LOCK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLockDateIsNotNull() {
            addCriterion("LOCK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLockDateEqualTo(Date value) {
            addCriterion("LOCK_DATE =", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotEqualTo(Date value) {
            addCriterion("LOCK_DATE <>", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateGreaterThan(Date value) {
            addCriterion("LOCK_DATE >", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LOCK_DATE >=", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateLessThan(Date value) {
            addCriterion("LOCK_DATE <", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateLessThanOrEqualTo(Date value) {
            addCriterion("LOCK_DATE <=", value, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateIn(List<Date> values) {
            addCriterion("LOCK_DATE in", values, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotIn(List<Date> values) {
            addCriterion("LOCK_DATE not in", values, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateBetween(Date value1, Date value2) {
            addCriterion("LOCK_DATE between", value1, value2, "lockDate");
            return (Criteria) this;
        }

        public Criteria andLockDateNotBetween(Date value1, Date value2) {
            addCriterion("LOCK_DATE not between", value1, value2, "lockDate");
            return (Criteria) this;
        }

        public Criteria andWxStateIsNull() {
            addCriterion("WX_STATE is null");
            return (Criteria) this;
        }

        public Criteria andWxStateIsNotNull() {
            addCriterion("WX_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andWxStateEqualTo(Short value) {
            addCriterion("WX_STATE =", value, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateNotEqualTo(Short value) {
            addCriterion("WX_STATE <>", value, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateGreaterThan(Short value) {
            addCriterion("WX_STATE >", value, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateGreaterThanOrEqualTo(Short value) {
            addCriterion("WX_STATE >=", value, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateLessThan(Short value) {
            addCriterion("WX_STATE <", value, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateLessThanOrEqualTo(Short value) {
            addCriterion("WX_STATE <=", value, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateIn(List<Short> values) {
            addCriterion("WX_STATE in", values, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateNotIn(List<Short> values) {
            addCriterion("WX_STATE not in", values, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateBetween(Short value1, Short value2) {
            addCriterion("WX_STATE between", value1, value2, "wxState");
            return (Criteria) this;
        }

        public Criteria andWxStateNotBetween(Short value1, Short value2) {
            addCriterion("WX_STATE not between", value1, value2, "wxState");
            return (Criteria) this;
        }

        public Criteria andUnregDateIsNull() {
            addCriterion("UNREG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUnregDateIsNotNull() {
            addCriterion("UNREG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUnregDateEqualTo(Date value) {
            addCriterion("UNREG_DATE =", value, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateNotEqualTo(Date value) {
            addCriterion("UNREG_DATE <>", value, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateGreaterThan(Date value) {
            addCriterion("UNREG_DATE >", value, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UNREG_DATE >=", value, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateLessThan(Date value) {
            addCriterion("UNREG_DATE <", value, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateLessThanOrEqualTo(Date value) {
            addCriterion("UNREG_DATE <=", value, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateIn(List<Date> values) {
            addCriterion("UNREG_DATE in", values, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateNotIn(List<Date> values) {
            addCriterion("UNREG_DATE not in", values, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateBetween(Date value1, Date value2) {
            addCriterion("UNREG_DATE between", value1, value2, "unregDate");
            return (Criteria) this;
        }

        public Criteria andUnregDateNotBetween(Date value1, Date value2) {
            addCriterion("UNREG_DATE not between", value1, value2, "unregDate");
            return (Criteria) this;
        }

        public Criteria andWxCommentIsNull() {
            addCriterion("WX_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andWxCommentIsNotNull() {
            addCriterion("WX_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andWxCommentEqualTo(String value) {
            addCriterion("WX_COMMENT =", value, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentNotEqualTo(String value) {
            addCriterion("WX_COMMENT <>", value, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentGreaterThan(String value) {
            addCriterion("WX_COMMENT >", value, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentGreaterThanOrEqualTo(String value) {
            addCriterion("WX_COMMENT >=", value, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentLessThan(String value) {
            addCriterion("WX_COMMENT <", value, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentLessThanOrEqualTo(String value) {
            addCriterion("WX_COMMENT <=", value, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentIn(List<String> values) {
            addCriterion("WX_COMMENT in", values, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentNotIn(List<String> values) {
            addCriterion("WX_COMMENT not in", values, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentBetween(String value1, String value2) {
            addCriterion("WX_COMMENT between", value1, value2, "wxComment");
            return (Criteria) this;
        }

        public Criteria andWxCommentNotBetween(String value1, String value2) {
            addCriterion("WX_COMMENT not between", value1, value2, "wxComment");
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