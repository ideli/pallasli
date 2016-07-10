package cn.shineyue.wx.po.auto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWaWxUserBlacklistExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWaWxUserBlacklistExample() {
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

        public Criteria andBlackDateIsNull() {
            addCriterion("BLACK_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBlackDateIsNotNull() {
            addCriterion("BLACK_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBlackDateEqualTo(Date value) {
            addCriterion("BLACK_DATE =", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateNotEqualTo(Date value) {
            addCriterion("BLACK_DATE <>", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateGreaterThan(Date value) {
            addCriterion("BLACK_DATE >", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateGreaterThanOrEqualTo(Date value) {
            addCriterion("BLACK_DATE >=", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateLessThan(Date value) {
            addCriterion("BLACK_DATE <", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateLessThanOrEqualTo(Date value) {
            addCriterion("BLACK_DATE <=", value, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateIn(List<Date> values) {
            addCriterion("BLACK_DATE in", values, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateNotIn(List<Date> values) {
            addCriterion("BLACK_DATE not in", values, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateBetween(Date value1, Date value2) {
            addCriterion("BLACK_DATE between", value1, value2, "blackDate");
            return (Criteria) this;
        }

        public Criteria andBlackDateNotBetween(Date value1, Date value2) {
            addCriterion("BLACK_DATE not between", value1, value2, "blackDate");
            return (Criteria) this;
        }

        public Criteria andToWhiteIsNull() {
            addCriterion("TO_WHITE is null");
            return (Criteria) this;
        }

        public Criteria andToWhiteIsNotNull() {
            addCriterion("TO_WHITE is not null");
            return (Criteria) this;
        }

        public Criteria andToWhiteEqualTo(Short value) {
            addCriterion("TO_WHITE =", value, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteNotEqualTo(Short value) {
            addCriterion("TO_WHITE <>", value, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteGreaterThan(Short value) {
            addCriterion("TO_WHITE >", value, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteGreaterThanOrEqualTo(Short value) {
            addCriterion("TO_WHITE >=", value, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteLessThan(Short value) {
            addCriterion("TO_WHITE <", value, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteLessThanOrEqualTo(Short value) {
            addCriterion("TO_WHITE <=", value, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteIn(List<Short> values) {
            addCriterion("TO_WHITE in", values, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteNotIn(List<Short> values) {
            addCriterion("TO_WHITE not in", values, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteBetween(Short value1, Short value2) {
            addCriterion("TO_WHITE between", value1, value2, "toWhite");
            return (Criteria) this;
        }

        public Criteria andToWhiteNotBetween(Short value1, Short value2) {
            addCriterion("TO_WHITE not between", value1, value2, "toWhite");
            return (Criteria) this;
        }

        public Criteria andWhiteDateIsNull() {
            addCriterion("WHITE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andWhiteDateIsNotNull() {
            addCriterion("WHITE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteDateEqualTo(Date value) {
            addCriterion("WHITE_DATE =", value, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateNotEqualTo(Date value) {
            addCriterion("WHITE_DATE <>", value, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateGreaterThan(Date value) {
            addCriterion("WHITE_DATE >", value, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateGreaterThanOrEqualTo(Date value) {
            addCriterion("WHITE_DATE >=", value, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateLessThan(Date value) {
            addCriterion("WHITE_DATE <", value, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateLessThanOrEqualTo(Date value) {
            addCriterion("WHITE_DATE <=", value, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateIn(List<Date> values) {
            addCriterion("WHITE_DATE in", values, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateNotIn(List<Date> values) {
            addCriterion("WHITE_DATE not in", values, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateBetween(Date value1, Date value2) {
            addCriterion("WHITE_DATE between", value1, value2, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteDateNotBetween(Date value1, Date value2) {
            addCriterion("WHITE_DATE not between", value1, value2, "whiteDate");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentIsNull() {
            addCriterion("WHITE_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentIsNotNull() {
            addCriterion("WHITE_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentEqualTo(String value) {
            addCriterion("WHITE_COMMENT =", value, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentNotEqualTo(String value) {
            addCriterion("WHITE_COMMENT <>", value, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentGreaterThan(String value) {
            addCriterion("WHITE_COMMENT >", value, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentGreaterThanOrEqualTo(String value) {
            addCriterion("WHITE_COMMENT >=", value, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentLessThan(String value) {
            addCriterion("WHITE_COMMENT <", value, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentLessThanOrEqualTo(String value) {
            addCriterion("WHITE_COMMENT <=", value, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentIn(List<String> values) {
            addCriterion("WHITE_COMMENT in", values, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentNotIn(List<String> values) {
            addCriterion("WHITE_COMMENT not in", values, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentBetween(String value1, String value2) {
            addCriterion("WHITE_COMMENT between", value1, value2, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteCommentNotBetween(String value1, String value2) {
            addCriterion("WHITE_COMMENT not between", value1, value2, "whiteComment");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridIsNull() {
            addCriterion("WHITE_USERID is null");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridIsNotNull() {
            addCriterion("WHITE_USERID is not null");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridEqualTo(String value) {
            addCriterion("WHITE_USERID =", value, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridNotEqualTo(String value) {
            addCriterion("WHITE_USERID <>", value, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridGreaterThan(String value) {
            addCriterion("WHITE_USERID >", value, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridGreaterThanOrEqualTo(String value) {
            addCriterion("WHITE_USERID >=", value, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridLessThan(String value) {
            addCriterion("WHITE_USERID <", value, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridLessThanOrEqualTo(String value) {
            addCriterion("WHITE_USERID <=", value, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridIn(List<String> values) {
            addCriterion("WHITE_USERID in", values, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridNotIn(List<String> values) {
            addCriterion("WHITE_USERID not in", values, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridBetween(String value1, String value2) {
            addCriterion("WHITE_USERID between", value1, value2, "whiteUserid");
            return (Criteria) this;
        }

        public Criteria andWhiteUseridNotBetween(String value1, String value2) {
            addCriterion("WHITE_USERID not between", value1, value2, "whiteUserid");
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