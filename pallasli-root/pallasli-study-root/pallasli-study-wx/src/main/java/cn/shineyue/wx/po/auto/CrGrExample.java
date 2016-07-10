package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CrGrExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CrGrExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andGrbhLike(String value) {
            addCriterion("GRBH like", value, "grbh");
            return (Criteria) this;
        }

        public Criteria andGrbhNotLike(String value) {
            addCriterion("GRBH not like", value, "grbh");
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

        public Criteria andDwbhIsNull() {
            addCriterion("DWBH is null");
            return (Criteria) this;
        }

        public Criteria andDwbhIsNotNull() {
            addCriterion("DWBH is not null");
            return (Criteria) this;
        }

        public Criteria andDwbhEqualTo(String value) {
            addCriterion("DWBH =", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhNotEqualTo(String value) {
            addCriterion("DWBH <>", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhGreaterThan(String value) {
            addCriterion("DWBH >", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhGreaterThanOrEqualTo(String value) {
            addCriterion("DWBH >=", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhLessThan(String value) {
            addCriterion("DWBH <", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhLessThanOrEqualTo(String value) {
            addCriterion("DWBH <=", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhLike(String value) {
            addCriterion("DWBH like", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhNotLike(String value) {
            addCriterion("DWBH not like", value, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhIn(List<String> values) {
            addCriterion("DWBH in", values, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhNotIn(List<String> values) {
            addCriterion("DWBH not in", values, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhBetween(String value1, String value2) {
            addCriterion("DWBH between", value1, value2, "dwbh");
            return (Criteria) this;
        }

        public Criteria andDwbhNotBetween(String value1, String value2) {
            addCriterion("DWBH not between", value1, value2, "dwbh");
            return (Criteria) this;
        }

        public Criteria andGrxmIsNull() {
            addCriterion("GRXM is null");
            return (Criteria) this;
        }

        public Criteria andGrxmIsNotNull() {
            addCriterion("GRXM is not null");
            return (Criteria) this;
        }

        public Criteria andGrxmEqualTo(String value) {
            addCriterion("GRXM =", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmNotEqualTo(String value) {
            addCriterion("GRXM <>", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmGreaterThan(String value) {
            addCriterion("GRXM >", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmGreaterThanOrEqualTo(String value) {
            addCriterion("GRXM >=", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmLessThan(String value) {
            addCriterion("GRXM <", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmLessThanOrEqualTo(String value) {
            addCriterion("GRXM <=", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmLike(String value) {
            addCriterion("GRXM like", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmNotLike(String value) {
            addCriterion("GRXM not like", value, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmIn(List<String> values) {
            addCriterion("GRXM in", values, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmNotIn(List<String> values) {
            addCriterion("GRXM not in", values, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmBetween(String value1, String value2) {
            addCriterion("GRXM between", value1, value2, "grxm");
            return (Criteria) this;
        }

        public Criteria andGrxmNotBetween(String value1, String value2) {
            addCriterion("GRXM not between", value1, value2, "grxm");
            return (Criteria) this;
        }

        public Criteria andXmqpIsNull() {
            addCriterion("XMQP is null");
            return (Criteria) this;
        }

        public Criteria andXmqpIsNotNull() {
            addCriterion("XMQP is not null");
            return (Criteria) this;
        }

        public Criteria andXmqpEqualTo(String value) {
            addCriterion("XMQP =", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpNotEqualTo(String value) {
            addCriterion("XMQP <>", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpGreaterThan(String value) {
            addCriterion("XMQP >", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpGreaterThanOrEqualTo(String value) {
            addCriterion("XMQP >=", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpLessThan(String value) {
            addCriterion("XMQP <", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpLessThanOrEqualTo(String value) {
            addCriterion("XMQP <=", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpLike(String value) {
            addCriterion("XMQP like", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpNotLike(String value) {
            addCriterion("XMQP not like", value, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpIn(List<String> values) {
            addCriterion("XMQP in", values, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpNotIn(List<String> values) {
            addCriterion("XMQP not in", values, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpBetween(String value1, String value2) {
            addCriterion("XMQP between", value1, value2, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmqpNotBetween(String value1, String value2) {
            addCriterion("XMQP not between", value1, value2, "xmqp");
            return (Criteria) this;
        }

        public Criteria andXmjpIsNull() {
            addCriterion("XMJP is null");
            return (Criteria) this;
        }

        public Criteria andXmjpIsNotNull() {
            addCriterion("XMJP is not null");
            return (Criteria) this;
        }

        public Criteria andXmjpEqualTo(String value) {
            addCriterion("XMJP =", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpNotEqualTo(String value) {
            addCriterion("XMJP <>", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpGreaterThan(String value) {
            addCriterion("XMJP >", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpGreaterThanOrEqualTo(String value) {
            addCriterion("XMJP >=", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpLessThan(String value) {
            addCriterion("XMJP <", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpLessThanOrEqualTo(String value) {
            addCriterion("XMJP <=", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpLike(String value) {
            addCriterion("XMJP like", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpNotLike(String value) {
            addCriterion("XMJP not like", value, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpIn(List<String> values) {
            addCriterion("XMJP in", values, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpNotIn(List<String> values) {
            addCriterion("XMJP not in", values, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpBetween(String value1, String value2) {
            addCriterion("XMJP between", value1, value2, "xmjp");
            return (Criteria) this;
        }

        public Criteria andXmjpNotBetween(String value1, String value2) {
            addCriterion("XMJP not between", value1, value2, "xmjp");
            return (Criteria) this;
        }

        public Criteria andZjlxIsNull() {
            addCriterion("ZJLX is null");
            return (Criteria) this;
        }

        public Criteria andZjlxIsNotNull() {
            addCriterion("ZJLX is not null");
            return (Criteria) this;
        }

        public Criteria andZjlxEqualTo(String value) {
            addCriterion("ZJLX =", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotEqualTo(String value) {
            addCriterion("ZJLX <>", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxGreaterThan(String value) {
            addCriterion("ZJLX >", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxGreaterThanOrEqualTo(String value) {
            addCriterion("ZJLX >=", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLessThan(String value) {
            addCriterion("ZJLX <", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLessThanOrEqualTo(String value) {
            addCriterion("ZJLX <=", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxLike(String value) {
            addCriterion("ZJLX like", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotLike(String value) {
            addCriterion("ZJLX not like", value, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxIn(List<String> values) {
            addCriterion("ZJLX in", values, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotIn(List<String> values) {
            addCriterion("ZJLX not in", values, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxBetween(String value1, String value2) {
            addCriterion("ZJLX between", value1, value2, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjlxNotBetween(String value1, String value2) {
            addCriterion("ZJLX not between", value1, value2, "zjlx");
            return (Criteria) this;
        }

        public Criteria andZjhmIsNull() {
            addCriterion("ZJHM is null");
            return (Criteria) this;
        }

        public Criteria andZjhmIsNotNull() {
            addCriterion("ZJHM is not null");
            return (Criteria) this;
        }

        public Criteria andZjhmEqualTo(String value) {
            addCriterion("ZJHM =", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotEqualTo(String value) {
            addCriterion("ZJHM <>", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmGreaterThan(String value) {
            addCriterion("ZJHM >", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmGreaterThanOrEqualTo(String value) {
            addCriterion("ZJHM >=", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLessThan(String value) {
            addCriterion("ZJHM <", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLessThanOrEqualTo(String value) {
            addCriterion("ZJHM <=", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmLike(String value) {
            addCriterion("ZJHM like", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotLike(String value) {
            addCriterion("ZJHM not like", value, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmIn(List<String> values) {
            addCriterion("ZJHM in", values, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotIn(List<String> values) {
            addCriterion("ZJHM not in", values, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmBetween(String value1, String value2) {
            addCriterion("ZJHM between", value1, value2, "zjhm");
            return (Criteria) this;
        }

        public Criteria andZjhmNotBetween(String value1, String value2) {
            addCriterion("ZJHM not between", value1, value2, "zjhm");
            return (Criteria) this;
        }

        public Criteria andXbIsNull() {
            addCriterion("XB is null");
            return (Criteria) this;
        }

        public Criteria andXbIsNotNull() {
            addCriterion("XB is not null");
            return (Criteria) this;
        }

        public Criteria andXbEqualTo(String value) {
            addCriterion("XB =", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotEqualTo(String value) {
            addCriterion("XB <>", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbGreaterThan(String value) {
            addCriterion("XB >", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbGreaterThanOrEqualTo(String value) {
            addCriterion("XB >=", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbLessThan(String value) {
            addCriterion("XB <", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbLessThanOrEqualTo(String value) {
            addCriterion("XB <=", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbLike(String value) {
            addCriterion("XB like", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotLike(String value) {
            addCriterion("XB not like", value, "xb");
            return (Criteria) this;
        }

        public Criteria andXbIn(List<String> values) {
            addCriterion("XB in", values, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotIn(List<String> values) {
            addCriterion("XB not in", values, "xb");
            return (Criteria) this;
        }

        public Criteria andXbBetween(String value1, String value2) {
            addCriterion("XB between", value1, value2, "xb");
            return (Criteria) this;
        }

        public Criteria andXbNotBetween(String value1, String value2) {
            addCriterion("XB not between", value1, value2, "xb");
            return (Criteria) this;
        }

        public Criteria andCsrqIsNull() {
            addCriterion("CSRQ is null");
            return (Criteria) this;
        }

        public Criteria andCsrqIsNotNull() {
            addCriterion("CSRQ is not null");
            return (Criteria) this;
        }

        public Criteria andCsrqEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ =", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ <>", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqGreaterThan(Date value) {
            addCriterionForJDBCDate("CSRQ >", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ >=", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLessThan(Date value) {
            addCriterionForJDBCDate("CSRQ <", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CSRQ <=", value, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqIn(List<Date> values) {
            addCriterionForJDBCDate("CSRQ in", values, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("CSRQ not in", values, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CSRQ between", value1, value2, "csrq");
            return (Criteria) this;
        }

        public Criteria andCsrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CSRQ not between", value1, value2, "csrq");
            return (Criteria) this;
        }

        public Criteria andGzrqIsNull() {
            addCriterion("GZRQ is null");
            return (Criteria) this;
        }

        public Criteria andGzrqIsNotNull() {
            addCriterion("GZRQ is not null");
            return (Criteria) this;
        }

        public Criteria andGzrqEqualTo(Date value) {
            addCriterionForJDBCDate("GZRQ =", value, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("GZRQ <>", value, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqGreaterThan(Date value) {
            addCriterionForJDBCDate("GZRQ >", value, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GZRQ >=", value, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqLessThan(Date value) {
            addCriterionForJDBCDate("GZRQ <", value, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("GZRQ <=", value, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqIn(List<Date> values) {
            addCriterionForJDBCDate("GZRQ in", values, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("GZRQ not in", values, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GZRQ between", value1, value2, "gzrq");
            return (Criteria) this;
        }

        public Criteria andGzrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("GZRQ not between", value1, value2, "gzrq");
            return (Criteria) this;
        }

        public Criteria andSzbmIsNull() {
            addCriterion("SZBM is null");
            return (Criteria) this;
        }

        public Criteria andSzbmIsNotNull() {
            addCriterion("SZBM is not null");
            return (Criteria) this;
        }

        public Criteria andSzbmEqualTo(String value) {
            addCriterion("SZBM =", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmNotEqualTo(String value) {
            addCriterion("SZBM <>", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmGreaterThan(String value) {
            addCriterion("SZBM >", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmGreaterThanOrEqualTo(String value) {
            addCriterion("SZBM >=", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmLessThan(String value) {
            addCriterion("SZBM <", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmLessThanOrEqualTo(String value) {
            addCriterion("SZBM <=", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmLike(String value) {
            addCriterion("SZBM like", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmNotLike(String value) {
            addCriterion("SZBM not like", value, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmIn(List<String> values) {
            addCriterion("SZBM in", values, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmNotIn(List<String> values) {
            addCriterion("SZBM not in", values, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmBetween(String value1, String value2) {
            addCriterion("SZBM between", value1, value2, "szbm");
            return (Criteria) this;
        }

        public Criteria andSzbmNotBetween(String value1, String value2) {
            addCriterion("SZBM not between", value1, value2, "szbm");
            return (Criteria) this;
        }

        public Criteria andYzbmIsNull() {
            addCriterion("YZBM is null");
            return (Criteria) this;
        }

        public Criteria andYzbmIsNotNull() {
            addCriterion("YZBM is not null");
            return (Criteria) this;
        }

        public Criteria andYzbmEqualTo(String value) {
            addCriterion("YZBM =", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotEqualTo(String value) {
            addCriterion("YZBM <>", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmGreaterThan(String value) {
            addCriterion("YZBM >", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmGreaterThanOrEqualTo(String value) {
            addCriterion("YZBM >=", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmLessThan(String value) {
            addCriterion("YZBM <", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmLessThanOrEqualTo(String value) {
            addCriterion("YZBM <=", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmLike(String value) {
            addCriterion("YZBM like", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotLike(String value) {
            addCriterion("YZBM not like", value, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmIn(List<String> values) {
            addCriterion("YZBM in", values, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotIn(List<String> values) {
            addCriterion("YZBM not in", values, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmBetween(String value1, String value2) {
            addCriterion("YZBM between", value1, value2, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYzbmNotBetween(String value1, String value2) {
            addCriterion("YZBM not between", value1, value2, "yzbm");
            return (Criteria) this;
        }

        public Criteria andYddhIsNull() {
            addCriterion("YDDH is null");
            return (Criteria) this;
        }

        public Criteria andYddhIsNotNull() {
            addCriterion("YDDH is not null");
            return (Criteria) this;
        }

        public Criteria andYddhEqualTo(String value) {
            addCriterion("YDDH =", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhNotEqualTo(String value) {
            addCriterion("YDDH <>", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhGreaterThan(String value) {
            addCriterion("YDDH >", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhGreaterThanOrEqualTo(String value) {
            addCriterion("YDDH >=", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhLessThan(String value) {
            addCriterion("YDDH <", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhLessThanOrEqualTo(String value) {
            addCriterion("YDDH <=", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhLike(String value) {
            addCriterion("YDDH like", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhNotLike(String value) {
            addCriterion("YDDH not like", value, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhIn(List<String> values) {
            addCriterion("YDDH in", values, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhNotIn(List<String> values) {
            addCriterion("YDDH not in", values, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhBetween(String value1, String value2) {
            addCriterion("YDDH between", value1, value2, "yddh");
            return (Criteria) this;
        }

        public Criteria andYddhNotBetween(String value1, String value2) {
            addCriterion("YDDH not between", value1, value2, "yddh");
            return (Criteria) this;
        }

        public Criteria andGddhIsNull() {
            addCriterion("GDDH is null");
            return (Criteria) this;
        }

        public Criteria andGddhIsNotNull() {
            addCriterion("GDDH is not null");
            return (Criteria) this;
        }

        public Criteria andGddhEqualTo(String value) {
            addCriterion("GDDH =", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhNotEqualTo(String value) {
            addCriterion("GDDH <>", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhGreaterThan(String value) {
            addCriterion("GDDH >", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhGreaterThanOrEqualTo(String value) {
            addCriterion("GDDH >=", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhLessThan(String value) {
            addCriterion("GDDH <", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhLessThanOrEqualTo(String value) {
            addCriterion("GDDH <=", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhLike(String value) {
            addCriterion("GDDH like", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhNotLike(String value) {
            addCriterion("GDDH not like", value, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhIn(List<String> values) {
            addCriterion("GDDH in", values, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhNotIn(List<String> values) {
            addCriterion("GDDH not in", values, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhBetween(String value1, String value2) {
            addCriterion("GDDH between", value1, value2, "gddh");
            return (Criteria) this;
        }

        public Criteria andGddhNotBetween(String value1, String value2) {
            addCriterion("GDDH not between", value1, value2, "gddh");
            return (Criteria) this;
        }

        public Criteria andTxdzIsNull() {
            addCriterion("TXDZ is null");
            return (Criteria) this;
        }

        public Criteria andTxdzIsNotNull() {
            addCriterion("TXDZ is not null");
            return (Criteria) this;
        }

        public Criteria andTxdzEqualTo(String value) {
            addCriterion("TXDZ =", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzNotEqualTo(String value) {
            addCriterion("TXDZ <>", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzGreaterThan(String value) {
            addCriterion("TXDZ >", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzGreaterThanOrEqualTo(String value) {
            addCriterion("TXDZ >=", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzLessThan(String value) {
            addCriterion("TXDZ <", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzLessThanOrEqualTo(String value) {
            addCriterion("TXDZ <=", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzLike(String value) {
            addCriterion("TXDZ like", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzNotLike(String value) {
            addCriterion("TXDZ not like", value, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzIn(List<String> values) {
            addCriterion("TXDZ in", values, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzNotIn(List<String> values) {
            addCriterion("TXDZ not in", values, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzBetween(String value1, String value2) {
            addCriterion("TXDZ between", value1, value2, "txdz");
            return (Criteria) this;
        }

        public Criteria andTxdzNotBetween(String value1, String value2) {
            addCriterion("TXDZ not between", value1, value2, "txdz");
            return (Criteria) this;
        }

        public Criteria andZyzgbmIsNull() {
            addCriterion("ZYZGBM is null");
            return (Criteria) this;
        }

        public Criteria andZyzgbmIsNotNull() {
            addCriterion("ZYZGBM is not null");
            return (Criteria) this;
        }

        public Criteria andZyzgbmEqualTo(String value) {
            addCriterion("ZYZGBM =", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmNotEqualTo(String value) {
            addCriterion("ZYZGBM <>", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmGreaterThan(String value) {
            addCriterion("ZYZGBM >", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmGreaterThanOrEqualTo(String value) {
            addCriterion("ZYZGBM >=", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmLessThan(String value) {
            addCriterion("ZYZGBM <", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmLessThanOrEqualTo(String value) {
            addCriterion("ZYZGBM <=", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmLike(String value) {
            addCriterion("ZYZGBM like", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmNotLike(String value) {
            addCriterion("ZYZGBM not like", value, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmIn(List<String> values) {
            addCriterion("ZYZGBM in", values, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmNotIn(List<String> values) {
            addCriterion("ZYZGBM not in", values, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmBetween(String value1, String value2) {
            addCriterion("ZYZGBM between", value1, value2, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZyzgbmNotBetween(String value1, String value2) {
            addCriterion("ZYZGBM not between", value1, value2, "zyzgbm");
            return (Criteria) this;
        }

        public Criteria andZcbmIsNull() {
            addCriterion("ZCBM is null");
            return (Criteria) this;
        }

        public Criteria andZcbmIsNotNull() {
            addCriterion("ZCBM is not null");
            return (Criteria) this;
        }

        public Criteria andZcbmEqualTo(String value) {
            addCriterion("ZCBM =", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmNotEqualTo(String value) {
            addCriterion("ZCBM <>", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmGreaterThan(String value) {
            addCriterion("ZCBM >", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmGreaterThanOrEqualTo(String value) {
            addCriterion("ZCBM >=", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmLessThan(String value) {
            addCriterion("ZCBM <", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmLessThanOrEqualTo(String value) {
            addCriterion("ZCBM <=", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmLike(String value) {
            addCriterion("ZCBM like", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmNotLike(String value) {
            addCriterion("ZCBM not like", value, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmIn(List<String> values) {
            addCriterion("ZCBM in", values, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmNotIn(List<String> values) {
            addCriterion("ZCBM not in", values, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmBetween(String value1, String value2) {
            addCriterion("ZCBM between", value1, value2, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZcbmNotBetween(String value1, String value2) {
            addCriterion("ZCBM not between", value1, value2, "zcbm");
            return (Criteria) this;
        }

        public Criteria andZwbmIsNull() {
            addCriterion("ZWBM is null");
            return (Criteria) this;
        }

        public Criteria andZwbmIsNotNull() {
            addCriterion("ZWBM is not null");
            return (Criteria) this;
        }

        public Criteria andZwbmEqualTo(String value) {
            addCriterion("ZWBM =", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmNotEqualTo(String value) {
            addCriterion("ZWBM <>", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmGreaterThan(String value) {
            addCriterion("ZWBM >", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmGreaterThanOrEqualTo(String value) {
            addCriterion("ZWBM >=", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmLessThan(String value) {
            addCriterion("ZWBM <", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmLessThanOrEqualTo(String value) {
            addCriterion("ZWBM <=", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmLike(String value) {
            addCriterion("ZWBM like", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmNotLike(String value) {
            addCriterion("ZWBM not like", value, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmIn(List<String> values) {
            addCriterion("ZWBM in", values, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmNotIn(List<String> values) {
            addCriterion("ZWBM not in", values, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmBetween(String value1, String value2) {
            addCriterion("ZWBM between", value1, value2, "zwbm");
            return (Criteria) this;
        }

        public Criteria andZwbmNotBetween(String value1, String value2) {
            addCriterion("ZWBM not between", value1, value2, "zwbm");
            return (Criteria) this;
        }

        public Criteria andXlbmIsNull() {
            addCriterion("XLBM is null");
            return (Criteria) this;
        }

        public Criteria andXlbmIsNotNull() {
            addCriterion("XLBM is not null");
            return (Criteria) this;
        }

        public Criteria andXlbmEqualTo(String value) {
            addCriterion("XLBM =", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmNotEqualTo(String value) {
            addCriterion("XLBM <>", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmGreaterThan(String value) {
            addCriterion("XLBM >", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmGreaterThanOrEqualTo(String value) {
            addCriterion("XLBM >=", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmLessThan(String value) {
            addCriterion("XLBM <", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmLessThanOrEqualTo(String value) {
            addCriterion("XLBM <=", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmLike(String value) {
            addCriterion("XLBM like", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmNotLike(String value) {
            addCriterion("XLBM not like", value, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmIn(List<String> values) {
            addCriterion("XLBM in", values, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmNotIn(List<String> values) {
            addCriterion("XLBM not in", values, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmBetween(String value1, String value2) {
            addCriterion("XLBM between", value1, value2, "xlbm");
            return (Criteria) this;
        }

        public Criteria andXlbmNotBetween(String value1, String value2) {
            addCriterion("XLBM not between", value1, value2, "xlbm");
            return (Criteria) this;
        }

        public Criteria andHyzkIsNull() {
            addCriterion("HYZK is null");
            return (Criteria) this;
        }

        public Criteria andHyzkIsNotNull() {
            addCriterion("HYZK is not null");
            return (Criteria) this;
        }

        public Criteria andHyzkEqualTo(String value) {
            addCriterion("HYZK =", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotEqualTo(String value) {
            addCriterion("HYZK <>", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkGreaterThan(String value) {
            addCriterion("HYZK >", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkGreaterThanOrEqualTo(String value) {
            addCriterion("HYZK >=", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkLessThan(String value) {
            addCriterion("HYZK <", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkLessThanOrEqualTo(String value) {
            addCriterion("HYZK <=", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkLike(String value) {
            addCriterion("HYZK like", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotLike(String value) {
            addCriterion("HYZK not like", value, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkIn(List<String> values) {
            addCriterion("HYZK in", values, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotIn(List<String> values) {
            addCriterion("HYZK not in", values, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkBetween(String value1, String value2) {
            addCriterion("HYZK between", value1, value2, "hyzk");
            return (Criteria) this;
        }

        public Criteria andHyzkNotBetween(String value1, String value2) {
            addCriterion("HYZK not between", value1, value2, "hyzk");
            return (Criteria) this;
        }

        public Criteria andPoxmIsNull() {
            addCriterion("POXM is null");
            return (Criteria) this;
        }

        public Criteria andPoxmIsNotNull() {
            addCriterion("POXM is not null");
            return (Criteria) this;
        }

        public Criteria andPoxmEqualTo(String value) {
            addCriterion("POXM =", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmNotEqualTo(String value) {
            addCriterion("POXM <>", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmGreaterThan(String value) {
            addCriterion("POXM >", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmGreaterThanOrEqualTo(String value) {
            addCriterion("POXM >=", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmLessThan(String value) {
            addCriterion("POXM <", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmLessThanOrEqualTo(String value) {
            addCriterion("POXM <=", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmLike(String value) {
            addCriterion("POXM like", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmNotLike(String value) {
            addCriterion("POXM not like", value, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmIn(List<String> values) {
            addCriterion("POXM in", values, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmNotIn(List<String> values) {
            addCriterion("POXM not in", values, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmBetween(String value1, String value2) {
            addCriterion("POXM between", value1, value2, "poxm");
            return (Criteria) this;
        }

        public Criteria andPoxmNotBetween(String value1, String value2) {
            addCriterion("POXM not between", value1, value2, "poxm");
            return (Criteria) this;
        }

        public Criteria andPozjlxIsNull() {
            addCriterion("POZJLX is null");
            return (Criteria) this;
        }

        public Criteria andPozjlxIsNotNull() {
            addCriterion("POZJLX is not null");
            return (Criteria) this;
        }

        public Criteria andPozjlxEqualTo(String value) {
            addCriterion("POZJLX =", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxNotEqualTo(String value) {
            addCriterion("POZJLX <>", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxGreaterThan(String value) {
            addCriterion("POZJLX >", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxGreaterThanOrEqualTo(String value) {
            addCriterion("POZJLX >=", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxLessThan(String value) {
            addCriterion("POZJLX <", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxLessThanOrEqualTo(String value) {
            addCriterion("POZJLX <=", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxLike(String value) {
            addCriterion("POZJLX like", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxNotLike(String value) {
            addCriterion("POZJLX not like", value, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxIn(List<String> values) {
            addCriterion("POZJLX in", values, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxNotIn(List<String> values) {
            addCriterion("POZJLX not in", values, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxBetween(String value1, String value2) {
            addCriterion("POZJLX between", value1, value2, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjlxNotBetween(String value1, String value2) {
            addCriterion("POZJLX not between", value1, value2, "pozjlx");
            return (Criteria) this;
        }

        public Criteria andPozjhmIsNull() {
            addCriterion("POZJHM is null");
            return (Criteria) this;
        }

        public Criteria andPozjhmIsNotNull() {
            addCriterion("POZJHM is not null");
            return (Criteria) this;
        }

        public Criteria andPozjhmEqualTo(String value) {
            addCriterion("POZJHM =", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmNotEqualTo(String value) {
            addCriterion("POZJHM <>", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmGreaterThan(String value) {
            addCriterion("POZJHM >", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmGreaterThanOrEqualTo(String value) {
            addCriterion("POZJHM >=", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmLessThan(String value) {
            addCriterion("POZJHM <", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmLessThanOrEqualTo(String value) {
            addCriterion("POZJHM <=", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmLike(String value) {
            addCriterion("POZJHM like", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmNotLike(String value) {
            addCriterion("POZJHM not like", value, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmIn(List<String> values) {
            addCriterion("POZJHM in", values, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmNotIn(List<String> values) {
            addCriterion("POZJHM not in", values, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmBetween(String value1, String value2) {
            addCriterion("POZJHM between", value1, value2, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andPozjhmNotBetween(String value1, String value2) {
            addCriterion("POZJHM not between", value1, value2, "pozjhm");
            return (Criteria) this;
        }

        public Criteria andZfqkIsNull() {
            addCriterion("ZFQK is null");
            return (Criteria) this;
        }

        public Criteria andZfqkIsNotNull() {
            addCriterion("ZFQK is not null");
            return (Criteria) this;
        }

        public Criteria andZfqkEqualTo(String value) {
            addCriterion("ZFQK =", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkNotEqualTo(String value) {
            addCriterion("ZFQK <>", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkGreaterThan(String value) {
            addCriterion("ZFQK >", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkGreaterThanOrEqualTo(String value) {
            addCriterion("ZFQK >=", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkLessThan(String value) {
            addCriterion("ZFQK <", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkLessThanOrEqualTo(String value) {
            addCriterion("ZFQK <=", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkLike(String value) {
            addCriterion("ZFQK like", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkNotLike(String value) {
            addCriterion("ZFQK not like", value, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkIn(List<String> values) {
            addCriterion("ZFQK in", values, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkNotIn(List<String> values) {
            addCriterion("ZFQK not in", values, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkBetween(String value1, String value2) {
            addCriterion("ZFQK between", value1, value2, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfqkNotBetween(String value1, String value2) {
            addCriterion("ZFQK not between", value1, value2, "zfqk");
            return (Criteria) this;
        }

        public Criteria andZfdkbzIsNull() {
            addCriterion("ZFDKBZ is null");
            return (Criteria) this;
        }

        public Criteria andZfdkbzIsNotNull() {
            addCriterion("ZFDKBZ is not null");
            return (Criteria) this;
        }

        public Criteria andZfdkbzEqualTo(String value) {
            addCriterion("ZFDKBZ =", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzNotEqualTo(String value) {
            addCriterion("ZFDKBZ <>", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzGreaterThan(String value) {
            addCriterion("ZFDKBZ >", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzGreaterThanOrEqualTo(String value) {
            addCriterion("ZFDKBZ >=", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzLessThan(String value) {
            addCriterion("ZFDKBZ <", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzLessThanOrEqualTo(String value) {
            addCriterion("ZFDKBZ <=", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzLike(String value) {
            addCriterion("ZFDKBZ like", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzNotLike(String value) {
            addCriterion("ZFDKBZ not like", value, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzIn(List<String> values) {
            addCriterion("ZFDKBZ in", values, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzNotIn(List<String> values) {
            addCriterion("ZFDKBZ not in", values, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzBetween(String value1, String value2) {
            addCriterion("ZFDKBZ between", value1, value2, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andZfdkbzNotBetween(String value1, String value2) {
            addCriterion("ZFDKBZ not between", value1, value2, "zfdkbz");
            return (Criteria) this;
        }

        public Criteria andCdgxbmIsNull() {
            addCriterion("CDGXBM is null");
            return (Criteria) this;
        }

        public Criteria andCdgxbmIsNotNull() {
            addCriterion("CDGXBM is not null");
            return (Criteria) this;
        }

        public Criteria andCdgxbmEqualTo(String value) {
            addCriterion("CDGXBM =", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmNotEqualTo(String value) {
            addCriterion("CDGXBM <>", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmGreaterThan(String value) {
            addCriterion("CDGXBM >", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmGreaterThanOrEqualTo(String value) {
            addCriterion("CDGXBM >=", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmLessThan(String value) {
            addCriterion("CDGXBM <", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmLessThanOrEqualTo(String value) {
            addCriterion("CDGXBM <=", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmLike(String value) {
            addCriterion("CDGXBM like", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmNotLike(String value) {
            addCriterion("CDGXBM not like", value, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmIn(List<String> values) {
            addCriterion("CDGXBM in", values, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmNotIn(List<String> values) {
            addCriterion("CDGXBM not in", values, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmBetween(String value1, String value2) {
            addCriterion("CDGXBM between", value1, value2, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andCdgxbmNotBetween(String value1, String value2) {
            addCriterion("CDGXBM not between", value1, value2, "cdgxbm");
            return (Criteria) this;
        }

        public Criteria andSbzhIsNull() {
            addCriterion("SBZH is null");
            return (Criteria) this;
        }

        public Criteria andSbzhIsNotNull() {
            addCriterion("SBZH is not null");
            return (Criteria) this;
        }

        public Criteria andSbzhEqualTo(String value) {
            addCriterion("SBZH =", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhNotEqualTo(String value) {
            addCriterion("SBZH <>", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhGreaterThan(String value) {
            addCriterion("SBZH >", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhGreaterThanOrEqualTo(String value) {
            addCriterion("SBZH >=", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhLessThan(String value) {
            addCriterion("SBZH <", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhLessThanOrEqualTo(String value) {
            addCriterion("SBZH <=", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhLike(String value) {
            addCriterion("SBZH like", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhNotLike(String value) {
            addCriterion("SBZH not like", value, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhIn(List<String> values) {
            addCriterion("SBZH in", values, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhNotIn(List<String> values) {
            addCriterion("SBZH not in", values, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhBetween(String value1, String value2) {
            addCriterion("SBZH between", value1, value2, "sbzh");
            return (Criteria) this;
        }

        public Criteria andSbzhNotBetween(String value1, String value2) {
            addCriterion("SBZH not between", value1, value2, "sbzh");
            return (Criteria) this;
        }

        public Criteria andGrysrIsNull() {
            addCriterion("GRYSR is null");
            return (Criteria) this;
        }

        public Criteria andGrysrIsNotNull() {
            addCriterion("GRYSR is not null");
            return (Criteria) this;
        }

        public Criteria andGrysrEqualTo(BigDecimal value) {
            addCriterion("GRYSR =", value, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrNotEqualTo(BigDecimal value) {
            addCriterion("GRYSR <>", value, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrGreaterThan(BigDecimal value) {
            addCriterion("GRYSR >", value, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GRYSR >=", value, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrLessThan(BigDecimal value) {
            addCriterion("GRYSR <", value, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GRYSR <=", value, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrIn(List<BigDecimal> values) {
            addCriterion("GRYSR in", values, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrNotIn(List<BigDecimal> values) {
            addCriterion("GRYSR not in", values, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GRYSR between", value1, value2, "grysr");
            return (Criteria) this;
        }

        public Criteria andGrysrNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GRYSR not between", value1, value2, "grysr");
            return (Criteria) this;
        }

        public Criteria andJtysrIsNull() {
            addCriterion("JTYSR is null");
            return (Criteria) this;
        }

        public Criteria andJtysrIsNotNull() {
            addCriterion("JTYSR is not null");
            return (Criteria) this;
        }

        public Criteria andJtysrEqualTo(BigDecimal value) {
            addCriterion("JTYSR =", value, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrNotEqualTo(BigDecimal value) {
            addCriterion("JTYSR <>", value, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrGreaterThan(BigDecimal value) {
            addCriterion("JTYSR >", value, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("JTYSR >=", value, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrLessThan(BigDecimal value) {
            addCriterion("JTYSR <", value, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrLessThanOrEqualTo(BigDecimal value) {
            addCriterion("JTYSR <=", value, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrIn(List<BigDecimal> values) {
            addCriterion("JTYSR in", values, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrNotIn(List<BigDecimal> values) {
            addCriterion("JTYSR not in", values, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JTYSR between", value1, value2, "jtysr");
            return (Criteria) this;
        }

        public Criteria andJtysrNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("JTYSR not between", value1, value2, "jtysr");
            return (Criteria) this;
        }

        public Criteria andXydjIsNull() {
            addCriterion("XYDJ is null");
            return (Criteria) this;
        }

        public Criteria andXydjIsNotNull() {
            addCriterion("XYDJ is not null");
            return (Criteria) this;
        }

        public Criteria andXydjEqualTo(String value) {
            addCriterion("XYDJ =", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjNotEqualTo(String value) {
            addCriterion("XYDJ <>", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjGreaterThan(String value) {
            addCriterion("XYDJ >", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjGreaterThanOrEqualTo(String value) {
            addCriterion("XYDJ >=", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjLessThan(String value) {
            addCriterion("XYDJ <", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjLessThanOrEqualTo(String value) {
            addCriterion("XYDJ <=", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjLike(String value) {
            addCriterion("XYDJ like", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjNotLike(String value) {
            addCriterion("XYDJ not like", value, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjIn(List<String> values) {
            addCriterion("XYDJ in", values, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjNotIn(List<String> values) {
            addCriterion("XYDJ not in", values, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjBetween(String value1, String value2) {
            addCriterion("XYDJ between", value1, value2, "xydj");
            return (Criteria) this;
        }

        public Criteria andXydjNotBetween(String value1, String value2) {
            addCriterion("XYDJ not between", value1, value2, "xydj");
            return (Criteria) this;
        }

        public Criteria andGjdbmIsNull() {
            addCriterion("GJDBM is null");
            return (Criteria) this;
        }

        public Criteria andGjdbmIsNotNull() {
            addCriterion("GJDBM is not null");
            return (Criteria) this;
        }

        public Criteria andGjdbmEqualTo(String value) {
            addCriterion("GJDBM =", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmNotEqualTo(String value) {
            addCriterion("GJDBM <>", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmGreaterThan(String value) {
            addCriterion("GJDBM >", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmGreaterThanOrEqualTo(String value) {
            addCriterion("GJDBM >=", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmLessThan(String value) {
            addCriterion("GJDBM <", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmLessThanOrEqualTo(String value) {
            addCriterion("GJDBM <=", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmLike(String value) {
            addCriterion("GJDBM like", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmNotLike(String value) {
            addCriterion("GJDBM not like", value, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmIn(List<String> values) {
            addCriterion("GJDBM in", values, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmNotIn(List<String> values) {
            addCriterion("GJDBM not in", values, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmBetween(String value1, String value2) {
            addCriterion("GJDBM between", value1, value2, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andGjdbmNotBetween(String value1, String value2) {
            addCriterion("GJDBM not between", value1, value2, "gjdbm");
            return (Criteria) this;
        }

        public Criteria andDjrqIsNull() {
            addCriterion("DJRQ is null");
            return (Criteria) this;
        }

        public Criteria andDjrqIsNotNull() {
            addCriterion("DJRQ is not null");
            return (Criteria) this;
        }

        public Criteria andDjrqEqualTo(Date value) {
            addCriterionForJDBCDate("DJRQ =", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("DJRQ <>", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqGreaterThan(Date value) {
            addCriterionForJDBCDate("DJRQ >", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DJRQ >=", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqLessThan(Date value) {
            addCriterionForJDBCDate("DJRQ <", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DJRQ <=", value, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqIn(List<Date> values) {
            addCriterionForJDBCDate("DJRQ in", values, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("DJRQ not in", values, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DJRQ between", value1, value2, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DJRQ not between", value1, value2, "djrq");
            return (Criteria) this;
        }

        public Criteria andDjsjIsNull() {
            addCriterion("DJSJ is null");
            return (Criteria) this;
        }

        public Criteria andDjsjIsNotNull() {
            addCriterion("DJSJ is not null");
            return (Criteria) this;
        }

        public Criteria andDjsjEqualTo(Date value) {
            addCriterionForJDBCDate("DJSJ =", value, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjNotEqualTo(Date value) {
            addCriterionForJDBCDate("DJSJ <>", value, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjGreaterThan(Date value) {
            addCriterionForJDBCDate("DJSJ >", value, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DJSJ >=", value, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjLessThan(Date value) {
            addCriterionForJDBCDate("DJSJ <", value, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DJSJ <=", value, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjIn(List<Date> values) {
            addCriterionForJDBCDate("DJSJ in", values, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjNotIn(List<Date> values) {
            addCriterionForJDBCDate("DJSJ not in", values, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DJSJ between", value1, value2, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjsjNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DJSJ not between", value1, value2, "djsj");
            return (Criteria) this;
        }

        public Criteria andDjczyIsNull() {
            addCriterion("DJCZY is null");
            return (Criteria) this;
        }

        public Criteria andDjczyIsNotNull() {
            addCriterion("DJCZY is not null");
            return (Criteria) this;
        }

        public Criteria andDjczyEqualTo(BigDecimal value) {
            addCriterion("DJCZY =", value, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyNotEqualTo(BigDecimal value) {
            addCriterion("DJCZY <>", value, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyGreaterThan(BigDecimal value) {
            addCriterion("DJCZY >", value, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DJCZY >=", value, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyLessThan(BigDecimal value) {
            addCriterion("DJCZY <", value, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DJCZY <=", value, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyIn(List<BigDecimal> values) {
            addCriterion("DJCZY in", values, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyNotIn(List<BigDecimal> values) {
            addCriterion("DJCZY not in", values, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DJCZY between", value1, value2, "djczy");
            return (Criteria) this;
        }

        public Criteria andDjczyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DJCZY not between", value1, value2, "djczy");
            return (Criteria) this;
        }

        public Criteria andGrztIsNull() {
            addCriterion("GRZT is null");
            return (Criteria) this;
        }

        public Criteria andGrztIsNotNull() {
            addCriterion("GRZT is not null");
            return (Criteria) this;
        }

        public Criteria andGrztEqualTo(String value) {
            addCriterion("GRZT =", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztNotEqualTo(String value) {
            addCriterion("GRZT <>", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztGreaterThan(String value) {
            addCriterion("GRZT >", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztGreaterThanOrEqualTo(String value) {
            addCriterion("GRZT >=", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztLessThan(String value) {
            addCriterion("GRZT <", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztLessThanOrEqualTo(String value) {
            addCriterion("GRZT <=", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztLike(String value) {
            addCriterion("GRZT like", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztNotLike(String value) {
            addCriterion("GRZT not like", value, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztIn(List<String> values) {
            addCriterion("GRZT in", values, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztNotIn(List<String> values) {
            addCriterion("GRZT not in", values, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztBetween(String value1, String value2) {
            addCriterion("GRZT between", value1, value2, "grzt");
            return (Criteria) this;
        }

        public Criteria andGrztNotBetween(String value1, String value2) {
            addCriterion("GRZT not between", value1, value2, "grzt");
            return (Criteria) this;
        }

        public Criteria andFPicturenameIsNull() {
            addCriterion("F_PICTURENAME is null");
            return (Criteria) this;
        }

        public Criteria andFPicturenameIsNotNull() {
            addCriterion("F_PICTURENAME is not null");
            return (Criteria) this;
        }

        public Criteria andFPicturenameEqualTo(String value) {
            addCriterion("F_PICTURENAME =", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameNotEqualTo(String value) {
            addCriterion("F_PICTURENAME <>", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameGreaterThan(String value) {
            addCriterion("F_PICTURENAME >", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameGreaterThanOrEqualTo(String value) {
            addCriterion("F_PICTURENAME >=", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameLessThan(String value) {
            addCriterion("F_PICTURENAME <", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameLessThanOrEqualTo(String value) {
            addCriterion("F_PICTURENAME <=", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameLike(String value) {
            addCriterion("F_PICTURENAME like", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameNotLike(String value) {
            addCriterion("F_PICTURENAME not like", value, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameIn(List<String> values) {
            addCriterion("F_PICTURENAME in", values, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameNotIn(List<String> values) {
            addCriterion("F_PICTURENAME not in", values, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameBetween(String value1, String value2) {
            addCriterion("F_PICTURENAME between", value1, value2, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andFPicturenameNotBetween(String value1, String value2) {
            addCriterion("F_PICTURENAME not between", value1, value2, "fPicturename");
            return (Criteria) this;
        }

        public Criteria andCymIsNull() {
            addCriterion("CYM is null");
            return (Criteria) this;
        }

        public Criteria andCymIsNotNull() {
            addCriterion("CYM is not null");
            return (Criteria) this;
        }

        public Criteria andCymEqualTo(String value) {
            addCriterion("CYM =", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotEqualTo(String value) {
            addCriterion("CYM <>", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymGreaterThan(String value) {
            addCriterion("CYM >", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymGreaterThanOrEqualTo(String value) {
            addCriterion("CYM >=", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymLessThan(String value) {
            addCriterion("CYM <", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymLessThanOrEqualTo(String value) {
            addCriterion("CYM <=", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymLike(String value) {
            addCriterion("CYM like", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotLike(String value) {
            addCriterion("CYM not like", value, "cym");
            return (Criteria) this;
        }

        public Criteria andCymIn(List<String> values) {
            addCriterion("CYM in", values, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotIn(List<String> values) {
            addCriterion("CYM not in", values, "cym");
            return (Criteria) this;
        }

        public Criteria andCymBetween(String value1, String value2) {
            addCriterion("CYM between", value1, value2, "cym");
            return (Criteria) this;
        }

        public Criteria andCymNotBetween(String value1, String value2) {
            addCriterion("CYM not between", value1, value2, "cym");
            return (Criteria) this;
        }

        public Criteria andYwmIsNull() {
            addCriterion("YWM is null");
            return (Criteria) this;
        }

        public Criteria andYwmIsNotNull() {
            addCriterion("YWM is not null");
            return (Criteria) this;
        }

        public Criteria andYwmEqualTo(String value) {
            addCriterion("YWM =", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmNotEqualTo(String value) {
            addCriterion("YWM <>", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmGreaterThan(String value) {
            addCriterion("YWM >", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmGreaterThanOrEqualTo(String value) {
            addCriterion("YWM >=", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmLessThan(String value) {
            addCriterion("YWM <", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmLessThanOrEqualTo(String value) {
            addCriterion("YWM <=", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmLike(String value) {
            addCriterion("YWM like", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmNotLike(String value) {
            addCriterion("YWM not like", value, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmIn(List<String> values) {
            addCriterion("YWM in", values, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmNotIn(List<String> values) {
            addCriterion("YWM not in", values, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmBetween(String value1, String value2) {
            addCriterion("YWM between", value1, value2, "ywm");
            return (Criteria) this;
        }

        public Criteria andYwmNotBetween(String value1, String value2) {
            addCriterion("YWM not between", value1, value2, "ywm");
            return (Criteria) this;
        }

        public Criteria andCsdIsNull() {
            addCriterion("CSD is null");
            return (Criteria) this;
        }

        public Criteria andCsdIsNotNull() {
            addCriterion("CSD is not null");
            return (Criteria) this;
        }

        public Criteria andCsdEqualTo(String value) {
            addCriterion("CSD =", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdNotEqualTo(String value) {
            addCriterion("CSD <>", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdGreaterThan(String value) {
            addCriterion("CSD >", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdGreaterThanOrEqualTo(String value) {
            addCriterion("CSD >=", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdLessThan(String value) {
            addCriterion("CSD <", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdLessThanOrEqualTo(String value) {
            addCriterion("CSD <=", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdLike(String value) {
            addCriterion("CSD like", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdNotLike(String value) {
            addCriterion("CSD not like", value, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdIn(List<String> values) {
            addCriterion("CSD in", values, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdNotIn(List<String> values) {
            addCriterion("CSD not in", values, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdBetween(String value1, String value2) {
            addCriterion("CSD between", value1, value2, "csd");
            return (Criteria) this;
        }

        public Criteria andCsdNotBetween(String value1, String value2) {
            addCriterion("CSD not between", value1, value2, "csd");
            return (Criteria) this;
        }

        public Criteria andMzIsNull() {
            addCriterion("MZ is null");
            return (Criteria) this;
        }

        public Criteria andMzIsNotNull() {
            addCriterion("MZ is not null");
            return (Criteria) this;
        }

        public Criteria andMzEqualTo(String value) {
            addCriterion("MZ =", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotEqualTo(String value) {
            addCriterion("MZ <>", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzGreaterThan(String value) {
            addCriterion("MZ >", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzGreaterThanOrEqualTo(String value) {
            addCriterion("MZ >=", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzLessThan(String value) {
            addCriterion("MZ <", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzLessThanOrEqualTo(String value) {
            addCriterion("MZ <=", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzLike(String value) {
            addCriterion("MZ like", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotLike(String value) {
            addCriterion("MZ not like", value, "mz");
            return (Criteria) this;
        }

        public Criteria andMzIn(List<String> values) {
            addCriterion("MZ in", values, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotIn(List<String> values) {
            addCriterion("MZ not in", values, "mz");
            return (Criteria) this;
        }

        public Criteria andMzBetween(String value1, String value2) {
            addCriterion("MZ between", value1, value2, "mz");
            return (Criteria) this;
        }

        public Criteria andMzNotBetween(String value1, String value2) {
            addCriterion("MZ not between", value1, value2, "mz");
            return (Criteria) this;
        }

        public Criteria andSgIsNull() {
            addCriterion("SG is null");
            return (Criteria) this;
        }

        public Criteria andSgIsNotNull() {
            addCriterion("SG is not null");
            return (Criteria) this;
        }

        public Criteria andSgEqualTo(BigDecimal value) {
            addCriterion("SG =", value, "sg");
            return (Criteria) this;
        }

        public Criteria andSgNotEqualTo(BigDecimal value) {
            addCriterion("SG <>", value, "sg");
            return (Criteria) this;
        }

        public Criteria andSgGreaterThan(BigDecimal value) {
            addCriterion("SG >", value, "sg");
            return (Criteria) this;
        }

        public Criteria andSgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SG >=", value, "sg");
            return (Criteria) this;
        }

        public Criteria andSgLessThan(BigDecimal value) {
            addCriterion("SG <", value, "sg");
            return (Criteria) this;
        }

        public Criteria andSgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SG <=", value, "sg");
            return (Criteria) this;
        }

        public Criteria andSgIn(List<BigDecimal> values) {
            addCriterion("SG in", values, "sg");
            return (Criteria) this;
        }

        public Criteria andSgNotIn(List<BigDecimal> values) {
            addCriterion("SG not in", values, "sg");
            return (Criteria) this;
        }

        public Criteria andSgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SG between", value1, value2, "sg");
            return (Criteria) this;
        }

        public Criteria andSgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SG not between", value1, value2, "sg");
            return (Criteria) this;
        }

        public Criteria andTzIsNull() {
            addCriterion("TZ is null");
            return (Criteria) this;
        }

        public Criteria andTzIsNotNull() {
            addCriterion("TZ is not null");
            return (Criteria) this;
        }

        public Criteria andTzEqualTo(BigDecimal value) {
            addCriterion("TZ =", value, "tz");
            return (Criteria) this;
        }

        public Criteria andTzNotEqualTo(BigDecimal value) {
            addCriterion("TZ <>", value, "tz");
            return (Criteria) this;
        }

        public Criteria andTzGreaterThan(BigDecimal value) {
            addCriterion("TZ >", value, "tz");
            return (Criteria) this;
        }

        public Criteria andTzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TZ >=", value, "tz");
            return (Criteria) this;
        }

        public Criteria andTzLessThan(BigDecimal value) {
            addCriterion("TZ <", value, "tz");
            return (Criteria) this;
        }

        public Criteria andTzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TZ <=", value, "tz");
            return (Criteria) this;
        }

        public Criteria andTzIn(List<BigDecimal> values) {
            addCriterion("TZ in", values, "tz");
            return (Criteria) this;
        }

        public Criteria andTzNotIn(List<BigDecimal> values) {
            addCriterion("TZ not in", values, "tz");
            return (Criteria) this;
        }

        public Criteria andTzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TZ between", value1, value2, "tz");
            return (Criteria) this;
        }

        public Criteria andTzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TZ not between", value1, value2, "tz");
            return (Criteria) this;
        }

        public Criteria andZyslIsNull() {
            addCriterion("ZYSL is null");
            return (Criteria) this;
        }

        public Criteria andZyslIsNotNull() {
            addCriterion("ZYSL is not null");
            return (Criteria) this;
        }

        public Criteria andZyslEqualTo(BigDecimal value) {
            addCriterion("ZYSL =", value, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslNotEqualTo(BigDecimal value) {
            addCriterion("ZYSL <>", value, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslGreaterThan(BigDecimal value) {
            addCriterion("ZYSL >", value, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ZYSL >=", value, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslLessThan(BigDecimal value) {
            addCriterion("ZYSL <", value, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ZYSL <=", value, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslIn(List<BigDecimal> values) {
            addCriterion("ZYSL in", values, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslNotIn(List<BigDecimal> values) {
            addCriterion("ZYSL not in", values, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZYSL between", value1, value2, "zysl");
            return (Criteria) this;
        }

        public Criteria andZyslNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ZYSL not between", value1, value2, "zysl");
            return (Criteria) this;
        }

        public Criteria andYyslIsNull() {
            addCriterion("YYSL is null");
            return (Criteria) this;
        }

        public Criteria andYyslIsNotNull() {
            addCriterion("YYSL is not null");
            return (Criteria) this;
        }

        public Criteria andYyslEqualTo(BigDecimal value) {
            addCriterion("YYSL =", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslNotEqualTo(BigDecimal value) {
            addCriterion("YYSL <>", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslGreaterThan(BigDecimal value) {
            addCriterion("YYSL >", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("YYSL >=", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslLessThan(BigDecimal value) {
            addCriterion("YYSL <", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslLessThanOrEqualTo(BigDecimal value) {
            addCriterion("YYSL <=", value, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslIn(List<BigDecimal> values) {
            addCriterion("YYSL in", values, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslNotIn(List<BigDecimal> values) {
            addCriterion("YYSL not in", values, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YYSL between", value1, value2, "yysl");
            return (Criteria) this;
        }

        public Criteria andYyslNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("YYSL not between", value1, value2, "yysl");
            return (Criteria) this;
        }

        public Criteria andJtdhIsNull() {
            addCriterion("JTDH is null");
            return (Criteria) this;
        }

        public Criteria andJtdhIsNotNull() {
            addCriterion("JTDH is not null");
            return (Criteria) this;
        }

        public Criteria andJtdhEqualTo(String value) {
            addCriterion("JTDH =", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhNotEqualTo(String value) {
            addCriterion("JTDH <>", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhGreaterThan(String value) {
            addCriterion("JTDH >", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhGreaterThanOrEqualTo(String value) {
            addCriterion("JTDH >=", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhLessThan(String value) {
            addCriterion("JTDH <", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhLessThanOrEqualTo(String value) {
            addCriterion("JTDH <=", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhLike(String value) {
            addCriterion("JTDH like", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhNotLike(String value) {
            addCriterion("JTDH not like", value, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhIn(List<String> values) {
            addCriterion("JTDH in", values, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhNotIn(List<String> values) {
            addCriterion("JTDH not in", values, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhBetween(String value1, String value2) {
            addCriterion("JTDH between", value1, value2, "jtdh");
            return (Criteria) this;
        }

        public Criteria andJtdhNotBetween(String value1, String value2) {
            addCriterion("JTDH not between", value1, value2, "jtdh");
            return (Criteria) this;
        }

        public Criteria andHjszdIsNull() {
            addCriterion("HJSZD is null");
            return (Criteria) this;
        }

        public Criteria andHjszdIsNotNull() {
            addCriterion("HJSZD is not null");
            return (Criteria) this;
        }

        public Criteria andHjszdEqualTo(String value) {
            addCriterion("HJSZD =", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdNotEqualTo(String value) {
            addCriterion("HJSZD <>", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdGreaterThan(String value) {
            addCriterion("HJSZD >", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdGreaterThanOrEqualTo(String value) {
            addCriterion("HJSZD >=", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdLessThan(String value) {
            addCriterion("HJSZD <", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdLessThanOrEqualTo(String value) {
            addCriterion("HJSZD <=", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdLike(String value) {
            addCriterion("HJSZD like", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdNotLike(String value) {
            addCriterion("HJSZD not like", value, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdIn(List<String> values) {
            addCriterion("HJSZD in", values, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdNotIn(List<String> values) {
            addCriterion("HJSZD not in", values, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdBetween(String value1, String value2) {
            addCriterion("HJSZD between", value1, value2, "hjszd");
            return (Criteria) this;
        }

        public Criteria andHjszdNotBetween(String value1, String value2) {
            addCriterion("HJSZD not between", value1, value2, "hjszd");
            return (Criteria) this;
        }

        public Criteria andDaszdIsNull() {
            addCriterion("DASZD is null");
            return (Criteria) this;
        }

        public Criteria andDaszdIsNotNull() {
            addCriterion("DASZD is not null");
            return (Criteria) this;
        }

        public Criteria andDaszdEqualTo(String value) {
            addCriterion("DASZD =", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdNotEqualTo(String value) {
            addCriterion("DASZD <>", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdGreaterThan(String value) {
            addCriterion("DASZD >", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdGreaterThanOrEqualTo(String value) {
            addCriterion("DASZD >=", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdLessThan(String value) {
            addCriterion("DASZD <", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdLessThanOrEqualTo(String value) {
            addCriterion("DASZD <=", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdLike(String value) {
            addCriterion("DASZD like", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdNotLike(String value) {
            addCriterion("DASZD not like", value, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdIn(List<String> values) {
            addCriterion("DASZD in", values, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdNotIn(List<String> values) {
            addCriterion("DASZD not in", values, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdBetween(String value1, String value2) {
            addCriterion("DASZD between", value1, value2, "daszd");
            return (Criteria) this;
        }

        public Criteria andDaszdNotBetween(String value1, String value2) {
            addCriterion("DASZD not between", value1, value2, "daszd");
            return (Criteria) this;
        }

        public Criteria andZzmmIsNull() {
            addCriterion("ZZMM is null");
            return (Criteria) this;
        }

        public Criteria andZzmmIsNotNull() {
            addCriterion("ZZMM is not null");
            return (Criteria) this;
        }

        public Criteria andZzmmEqualTo(String value) {
            addCriterion("ZZMM =", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotEqualTo(String value) {
            addCriterion("ZZMM <>", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmGreaterThan(String value) {
            addCriterion("ZZMM >", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmGreaterThanOrEqualTo(String value) {
            addCriterion("ZZMM >=", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLessThan(String value) {
            addCriterion("ZZMM <", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLessThanOrEqualTo(String value) {
            addCriterion("ZZMM <=", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmLike(String value) {
            addCriterion("ZZMM like", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotLike(String value) {
            addCriterion("ZZMM not like", value, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmIn(List<String> values) {
            addCriterion("ZZMM in", values, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotIn(List<String> values) {
            addCriterion("ZZMM not in", values, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmBetween(String value1, String value2) {
            addCriterion("ZZMM between", value1, value2, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZzmmNotBetween(String value1, String value2) {
            addCriterion("ZZMM not between", value1, value2, "zzmm");
            return (Criteria) this;
        }

        public Criteria andZjxyIsNull() {
            addCriterion("ZJXY is null");
            return (Criteria) this;
        }

        public Criteria andZjxyIsNotNull() {
            addCriterion("ZJXY is not null");
            return (Criteria) this;
        }

        public Criteria andZjxyEqualTo(String value) {
            addCriterion("ZJXY =", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyNotEqualTo(String value) {
            addCriterion("ZJXY <>", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyGreaterThan(String value) {
            addCriterion("ZJXY >", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyGreaterThanOrEqualTo(String value) {
            addCriterion("ZJXY >=", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyLessThan(String value) {
            addCriterion("ZJXY <", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyLessThanOrEqualTo(String value) {
            addCriterion("ZJXY <=", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyLike(String value) {
            addCriterion("ZJXY like", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyNotLike(String value) {
            addCriterion("ZJXY not like", value, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyIn(List<String> values) {
            addCriterion("ZJXY in", values, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyNotIn(List<String> values) {
            addCriterion("ZJXY not in", values, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyBetween(String value1, String value2) {
            addCriterion("ZJXY between", value1, value2, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZjxyNotBetween(String value1, String value2) {
            addCriterion("ZJXY not between", value1, value2, "zjxy");
            return (Criteria) this;
        }

        public Criteria andZyIsNull() {
            addCriterion("ZY is null");
            return (Criteria) this;
        }

        public Criteria andZyIsNotNull() {
            addCriterion("ZY is not null");
            return (Criteria) this;
        }

        public Criteria andZyEqualTo(String value) {
            addCriterion("ZY =", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyNotEqualTo(String value) {
            addCriterion("ZY <>", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyGreaterThan(String value) {
            addCriterion("ZY >", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyGreaterThanOrEqualTo(String value) {
            addCriterion("ZY >=", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyLessThan(String value) {
            addCriterion("ZY <", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyLessThanOrEqualTo(String value) {
            addCriterion("ZY <=", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyLike(String value) {
            addCriterion("ZY like", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyNotLike(String value) {
            addCriterion("ZY not like", value, "zy");
            return (Criteria) this;
        }

        public Criteria andZyIn(List<String> values) {
            addCriterion("ZY in", values, "zy");
            return (Criteria) this;
        }

        public Criteria andZyNotIn(List<String> values) {
            addCriterion("ZY not in", values, "zy");
            return (Criteria) this;
        }

        public Criteria andZyBetween(String value1, String value2) {
            addCriterion("ZY between", value1, value2, "zy");
            return (Criteria) this;
        }

        public Criteria andZyNotBetween(String value1, String value2) {
            addCriterion("ZY not between", value1, value2, "zy");
            return (Criteria) this;
        }

        public Criteria andByyxIsNull() {
            addCriterion("BYYX is null");
            return (Criteria) this;
        }

        public Criteria andByyxIsNotNull() {
            addCriterion("BYYX is not null");
            return (Criteria) this;
        }

        public Criteria andByyxEqualTo(String value) {
            addCriterion("BYYX =", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxNotEqualTo(String value) {
            addCriterion("BYYX <>", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxGreaterThan(String value) {
            addCriterion("BYYX >", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxGreaterThanOrEqualTo(String value) {
            addCriterion("BYYX >=", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxLessThan(String value) {
            addCriterion("BYYX <", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxLessThanOrEqualTo(String value) {
            addCriterion("BYYX <=", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxLike(String value) {
            addCriterion("BYYX like", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxNotLike(String value) {
            addCriterion("BYYX not like", value, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxIn(List<String> values) {
            addCriterion("BYYX in", values, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxNotIn(List<String> values) {
            addCriterion("BYYX not in", values, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxBetween(String value1, String value2) {
            addCriterion("BYYX between", value1, value2, "byyx");
            return (Criteria) this;
        }

        public Criteria andByyxNotBetween(String value1, String value2) {
            addCriterion("BYYX not between", value1, value2, "byyx");
            return (Criteria) this;
        }

        public Criteria andRzrqIsNull() {
            addCriterion("RZRQ is null");
            return (Criteria) this;
        }

        public Criteria andRzrqIsNotNull() {
            addCriterion("RZRQ is not null");
            return (Criteria) this;
        }

        public Criteria andRzrqEqualTo(Date value) {
            addCriterionForJDBCDate("RZRQ =", value, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("RZRQ <>", value, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqGreaterThan(Date value) {
            addCriterionForJDBCDate("RZRQ >", value, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("RZRQ >=", value, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqLessThan(Date value) {
            addCriterionForJDBCDate("RZRQ <", value, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("RZRQ <=", value, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqIn(List<Date> values) {
            addCriterionForJDBCDate("RZRQ in", values, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("RZRQ not in", values, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("RZRQ between", value1, value2, "rzrq");
            return (Criteria) this;
        }

        public Criteria andRzrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("RZRQ not between", value1, value2, "rzrq");
            return (Criteria) this;
        }

        public Criteria andJtzzIsNull() {
            addCriterion("JTZZ is null");
            return (Criteria) this;
        }

        public Criteria andJtzzIsNotNull() {
            addCriterion("JTZZ is not null");
            return (Criteria) this;
        }

        public Criteria andJtzzEqualTo(String value) {
            addCriterion("JTZZ =", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzNotEqualTo(String value) {
            addCriterion("JTZZ <>", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzGreaterThan(String value) {
            addCriterion("JTZZ >", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzGreaterThanOrEqualTo(String value) {
            addCriterion("JTZZ >=", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzLessThan(String value) {
            addCriterion("JTZZ <", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzLessThanOrEqualTo(String value) {
            addCriterion("JTZZ <=", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzLike(String value) {
            addCriterion("JTZZ like", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzNotLike(String value) {
            addCriterion("JTZZ not like", value, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzIn(List<String> values) {
            addCriterion("JTZZ in", values, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzNotIn(List<String> values) {
            addCriterion("JTZZ not in", values, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzBetween(String value1, String value2) {
            addCriterion("JTZZ between", value1, value2, "jtzz");
            return (Criteria) this;
        }

        public Criteria andJtzzNotBetween(String value1, String value2) {
            addCriterion("JTZZ not between", value1, value2, "jtzz");
            return (Criteria) this;
        }

        public Criteria andXzzIsNull() {
            addCriterion("XZZ is null");
            return (Criteria) this;
        }

        public Criteria andXzzIsNotNull() {
            addCriterion("XZZ is not null");
            return (Criteria) this;
        }

        public Criteria andXzzEqualTo(String value) {
            addCriterion("XZZ =", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzNotEqualTo(String value) {
            addCriterion("XZZ <>", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzGreaterThan(String value) {
            addCriterion("XZZ >", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzGreaterThanOrEqualTo(String value) {
            addCriterion("XZZ >=", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzLessThan(String value) {
            addCriterion("XZZ <", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzLessThanOrEqualTo(String value) {
            addCriterion("XZZ <=", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzLike(String value) {
            addCriterion("XZZ like", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzNotLike(String value) {
            addCriterion("XZZ not like", value, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzIn(List<String> values) {
            addCriterion("XZZ in", values, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzNotIn(List<String> values) {
            addCriterion("XZZ not in", values, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzBetween(String value1, String value2) {
            addCriterion("XZZ between", value1, value2, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzNotBetween(String value1, String value2) {
            addCriterion("XZZ not between", value1, value2, "xzz");
            return (Criteria) this;
        }

        public Criteria andXzzdhIsNull() {
            addCriterion("XZZDH is null");
            return (Criteria) this;
        }

        public Criteria andXzzdhIsNotNull() {
            addCriterion("XZZDH is not null");
            return (Criteria) this;
        }

        public Criteria andXzzdhEqualTo(String value) {
            addCriterion("XZZDH =", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhNotEqualTo(String value) {
            addCriterion("XZZDH <>", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhGreaterThan(String value) {
            addCriterion("XZZDH >", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhGreaterThanOrEqualTo(String value) {
            addCriterion("XZZDH >=", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhLessThan(String value) {
            addCriterion("XZZDH <", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhLessThanOrEqualTo(String value) {
            addCriterion("XZZDH <=", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhLike(String value) {
            addCriterion("XZZDH like", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhNotLike(String value) {
            addCriterion("XZZDH not like", value, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhIn(List<String> values) {
            addCriterion("XZZDH in", values, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhNotIn(List<String> values) {
            addCriterion("XZZDH not in", values, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhBetween(String value1, String value2) {
            addCriterion("XZZDH between", value1, value2, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andXzzdhNotBetween(String value1, String value2) {
            addCriterion("XZZDH not between", value1, value2, "xzzdh");
            return (Criteria) this;
        }

        public Criteria andJzhmIsNull() {
            addCriterion("JZHM is null");
            return (Criteria) this;
        }

        public Criteria andJzhmIsNotNull() {
            addCriterion("JZHM is not null");
            return (Criteria) this;
        }

        public Criteria andJzhmEqualTo(String value) {
            addCriterion("JZHM =", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmNotEqualTo(String value) {
            addCriterion("JZHM <>", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmGreaterThan(String value) {
            addCriterion("JZHM >", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmGreaterThanOrEqualTo(String value) {
            addCriterion("JZHM >=", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmLessThan(String value) {
            addCriterion("JZHM <", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmLessThanOrEqualTo(String value) {
            addCriterion("JZHM <=", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmLike(String value) {
            addCriterion("JZHM like", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmNotLike(String value) {
            addCriterion("JZHM not like", value, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmIn(List<String> values) {
            addCriterion("JZHM in", values, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmNotIn(List<String> values) {
            addCriterion("JZHM not in", values, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmBetween(String value1, String value2) {
            addCriterion("JZHM between", value1, value2, "jzhm");
            return (Criteria) this;
        }

        public Criteria andJzhmNotBetween(String value1, String value2) {
            addCriterion("JZHM not between", value1, value2, "jzhm");
            return (Criteria) this;
        }

        public Criteria andYzIsNull() {
            addCriterion("YZ is null");
            return (Criteria) this;
        }

        public Criteria andYzIsNotNull() {
            addCriterion("YZ is not null");
            return (Criteria) this;
        }

        public Criteria andYzEqualTo(String value) {
            addCriterion("YZ =", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzNotEqualTo(String value) {
            addCriterion("YZ <>", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzGreaterThan(String value) {
            addCriterion("YZ >", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzGreaterThanOrEqualTo(String value) {
            addCriterion("YZ >=", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzLessThan(String value) {
            addCriterion("YZ <", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzLessThanOrEqualTo(String value) {
            addCriterion("YZ <=", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzLike(String value) {
            addCriterion("YZ like", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzNotLike(String value) {
            addCriterion("YZ not like", value, "yz");
            return (Criteria) this;
        }

        public Criteria andYzIn(List<String> values) {
            addCriterion("YZ in", values, "yz");
            return (Criteria) this;
        }

        public Criteria andYzNotIn(List<String> values) {
            addCriterion("YZ not in", values, "yz");
            return (Criteria) this;
        }

        public Criteria andYzBetween(String value1, String value2) {
            addCriterion("YZ between", value1, value2, "yz");
            return (Criteria) this;
        }

        public Criteria andYzNotBetween(String value1, String value2) {
            addCriterion("YZ not between", value1, value2, "yz");
            return (Criteria) this;
        }

        public Criteria andWyspIsNull() {
            addCriterion("WYSP is null");
            return (Criteria) this;
        }

        public Criteria andWyspIsNotNull() {
            addCriterion("WYSP is not null");
            return (Criteria) this;
        }

        public Criteria andWyspEqualTo(String value) {
            addCriterion("WYSP =", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotEqualTo(String value) {
            addCriterion("WYSP <>", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspGreaterThan(String value) {
            addCriterion("WYSP >", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspGreaterThanOrEqualTo(String value) {
            addCriterion("WYSP >=", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspLessThan(String value) {
            addCriterion("WYSP <", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspLessThanOrEqualTo(String value) {
            addCriterion("WYSP <=", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspLike(String value) {
            addCriterion("WYSP like", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotLike(String value) {
            addCriterion("WYSP not like", value, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspIn(List<String> values) {
            addCriterion("WYSP in", values, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotIn(List<String> values) {
            addCriterion("WYSP not in", values, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspBetween(String value1, String value2) {
            addCriterion("WYSP between", value1, value2, "wysp");
            return (Criteria) this;
        }

        public Criteria andWyspNotBetween(String value1, String value2) {
            addCriterion("WYSP not between", value1, value2, "wysp");
            return (Criteria) this;
        }

        public Criteria andXxIsNull() {
            addCriterion("XX is null");
            return (Criteria) this;
        }

        public Criteria andXxIsNotNull() {
            addCriterion("XX is not null");
            return (Criteria) this;
        }

        public Criteria andXxEqualTo(String value) {
            addCriterion("XX =", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotEqualTo(String value) {
            addCriterion("XX <>", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxGreaterThan(String value) {
            addCriterion("XX >", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxGreaterThanOrEqualTo(String value) {
            addCriterion("XX >=", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxLessThan(String value) {
            addCriterion("XX <", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxLessThanOrEqualTo(String value) {
            addCriterion("XX <=", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxLike(String value) {
            addCriterion("XX like", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotLike(String value) {
            addCriterion("XX not like", value, "xx");
            return (Criteria) this;
        }

        public Criteria andXxIn(List<String> values) {
            addCriterion("XX in", values, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotIn(List<String> values) {
            addCriterion("XX not in", values, "xx");
            return (Criteria) this;
        }

        public Criteria andXxBetween(String value1, String value2) {
            addCriterion("XX between", value1, value2, "xx");
            return (Criteria) this;
        }

        public Criteria andXxNotBetween(String value1, String value2) {
            addCriterion("XX not between", value1, value2, "xx");
            return (Criteria) this;
        }

        public Criteria andAihIsNull() {
            addCriterion("AIH is null");
            return (Criteria) this;
        }

        public Criteria andAihIsNotNull() {
            addCriterion("AIH is not null");
            return (Criteria) this;
        }

        public Criteria andAihEqualTo(String value) {
            addCriterion("AIH =", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihNotEqualTo(String value) {
            addCriterion("AIH <>", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihGreaterThan(String value) {
            addCriterion("AIH >", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihGreaterThanOrEqualTo(String value) {
            addCriterion("AIH >=", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihLessThan(String value) {
            addCriterion("AIH <", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihLessThanOrEqualTo(String value) {
            addCriterion("AIH <=", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihLike(String value) {
            addCriterion("AIH like", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihNotLike(String value) {
            addCriterion("AIH not like", value, "aih");
            return (Criteria) this;
        }

        public Criteria andAihIn(List<String> values) {
            addCriterion("AIH in", values, "aih");
            return (Criteria) this;
        }

        public Criteria andAihNotIn(List<String> values) {
            addCriterion("AIH not in", values, "aih");
            return (Criteria) this;
        }

        public Criteria andAihBetween(String value1, String value2) {
            addCriterion("AIH between", value1, value2, "aih");
            return (Criteria) this;
        }

        public Criteria andAihNotBetween(String value1, String value2) {
            addCriterion("AIH not between", value1, value2, "aih");
            return (Criteria) this;
        }

        public Criteria andTcIsNull() {
            addCriterion("TC is null");
            return (Criteria) this;
        }

        public Criteria andTcIsNotNull() {
            addCriterion("TC is not null");
            return (Criteria) this;
        }

        public Criteria andTcEqualTo(String value) {
            addCriterion("TC =", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotEqualTo(String value) {
            addCriterion("TC <>", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcGreaterThan(String value) {
            addCriterion("TC >", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcGreaterThanOrEqualTo(String value) {
            addCriterion("TC >=", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcLessThan(String value) {
            addCriterion("TC <", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcLessThanOrEqualTo(String value) {
            addCriterion("TC <=", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcLike(String value) {
            addCriterion("TC like", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotLike(String value) {
            addCriterion("TC not like", value, "tc");
            return (Criteria) this;
        }

        public Criteria andTcIn(List<String> values) {
            addCriterion("TC in", values, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotIn(List<String> values) {
            addCriterion("TC not in", values, "tc");
            return (Criteria) this;
        }

        public Criteria andTcBetween(String value1, String value2) {
            addCriterion("TC between", value1, value2, "tc");
            return (Criteria) this;
        }

        public Criteria andTcNotBetween(String value1, String value2) {
            addCriterion("TC not between", value1, value2, "tc");
            return (Criteria) this;
        }

        public Criteria andMqgzztIsNull() {
            addCriterion("MQGZZT is null");
            return (Criteria) this;
        }

        public Criteria andMqgzztIsNotNull() {
            addCriterion("MQGZZT is not null");
            return (Criteria) this;
        }

        public Criteria andMqgzztEqualTo(String value) {
            addCriterion("MQGZZT =", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztNotEqualTo(String value) {
            addCriterion("MQGZZT <>", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztGreaterThan(String value) {
            addCriterion("MQGZZT >", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztGreaterThanOrEqualTo(String value) {
            addCriterion("MQGZZT >=", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztLessThan(String value) {
            addCriterion("MQGZZT <", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztLessThanOrEqualTo(String value) {
            addCriterion("MQGZZT <=", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztLike(String value) {
            addCriterion("MQGZZT like", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztNotLike(String value) {
            addCriterion("MQGZZT not like", value, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztIn(List<String> values) {
            addCriterion("MQGZZT in", values, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztNotIn(List<String> values) {
            addCriterion("MQGZZT not in", values, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztBetween(String value1, String value2) {
            addCriterion("MQGZZT between", value1, value2, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andMqgzztNotBetween(String value1, String value2) {
            addCriterion("MQGZZT not between", value1, value2, "mqgzzt");
            return (Criteria) this;
        }

        public Criteria andDzxxIsNull() {
            addCriterion("DZXX is null");
            return (Criteria) this;
        }

        public Criteria andDzxxIsNotNull() {
            addCriterion("DZXX is not null");
            return (Criteria) this;
        }

        public Criteria andDzxxEqualTo(String value) {
            addCriterion("DZXX =", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxNotEqualTo(String value) {
            addCriterion("DZXX <>", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxGreaterThan(String value) {
            addCriterion("DZXX >", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxGreaterThanOrEqualTo(String value) {
            addCriterion("DZXX >=", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxLessThan(String value) {
            addCriterion("DZXX <", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxLessThanOrEqualTo(String value) {
            addCriterion("DZXX <=", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxLike(String value) {
            addCriterion("DZXX like", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxNotLike(String value) {
            addCriterion("DZXX not like", value, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxIn(List<String> values) {
            addCriterion("DZXX in", values, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxNotIn(List<String> values) {
            addCriterion("DZXX not in", values, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxBetween(String value1, String value2) {
            addCriterion("DZXX between", value1, value2, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDzxxNotBetween(String value1, String value2) {
            addCriterion("DZXX not between", value1, value2, "dzxx");
            return (Criteria) this;
        }

        public Criteria andDlztIsNull() {
            addCriterion("DLZT is null");
            return (Criteria) this;
        }

        public Criteria andDlztIsNotNull() {
            addCriterion("DLZT is not null");
            return (Criteria) this;
        }

        public Criteria andDlztEqualTo(String value) {
            addCriterion("DLZT =", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztNotEqualTo(String value) {
            addCriterion("DLZT <>", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztGreaterThan(String value) {
            addCriterion("DLZT >", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztGreaterThanOrEqualTo(String value) {
            addCriterion("DLZT >=", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztLessThan(String value) {
            addCriterion("DLZT <", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztLessThanOrEqualTo(String value) {
            addCriterion("DLZT <=", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztLike(String value) {
            addCriterion("DLZT like", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztNotLike(String value) {
            addCriterion("DLZT not like", value, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztIn(List<String> values) {
            addCriterion("DLZT in", values, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztNotIn(List<String> values) {
            addCriterion("DLZT not in", values, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztBetween(String value1, String value2) {
            addCriterion("DLZT between", value1, value2, "dlzt");
            return (Criteria) this;
        }

        public Criteria andDlztNotBetween(String value1, String value2) {
            addCriterion("DLZT not between", value1, value2, "dlzt");
            return (Criteria) this;
        }

        public Criteria andGrlbbmIsNull() {
            addCriterion("GRLBBM is null");
            return (Criteria) this;
        }

        public Criteria andGrlbbmIsNotNull() {
            addCriterion("GRLBBM is not null");
            return (Criteria) this;
        }

        public Criteria andGrlbbmEqualTo(String value) {
            addCriterion("GRLBBM =", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmNotEqualTo(String value) {
            addCriterion("GRLBBM <>", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmGreaterThan(String value) {
            addCriterion("GRLBBM >", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmGreaterThanOrEqualTo(String value) {
            addCriterion("GRLBBM >=", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmLessThan(String value) {
            addCriterion("GRLBBM <", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmLessThanOrEqualTo(String value) {
            addCriterion("GRLBBM <=", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmLike(String value) {
            addCriterion("GRLBBM like", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmNotLike(String value) {
            addCriterion("GRLBBM not like", value, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmIn(List<String> values) {
            addCriterion("GRLBBM in", values, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmNotIn(List<String> values) {
            addCriterion("GRLBBM not in", values, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmBetween(String value1, String value2) {
            addCriterion("GRLBBM between", value1, value2, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andGrlbbmNotBetween(String value1, String value2) {
            addCriterion("GRLBBM not between", value1, value2, "grlbbm");
            return (Criteria) this;
        }

        public Criteria andMem01IsNull() {
            addCriterion("MEM01 is null");
            return (Criteria) this;
        }

        public Criteria andMem01IsNotNull() {
            addCriterion("MEM01 is not null");
            return (Criteria) this;
        }

        public Criteria andMem01EqualTo(String value) {
            addCriterion("MEM01 =", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01NotEqualTo(String value) {
            addCriterion("MEM01 <>", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01GreaterThan(String value) {
            addCriterion("MEM01 >", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01GreaterThanOrEqualTo(String value) {
            addCriterion("MEM01 >=", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01LessThan(String value) {
            addCriterion("MEM01 <", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01LessThanOrEqualTo(String value) {
            addCriterion("MEM01 <=", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01Like(String value) {
            addCriterion("MEM01 like", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01NotLike(String value) {
            addCriterion("MEM01 not like", value, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01In(List<String> values) {
            addCriterion("MEM01 in", values, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01NotIn(List<String> values) {
            addCriterion("MEM01 not in", values, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01Between(String value1, String value2) {
            addCriterion("MEM01 between", value1, value2, "mem01");
            return (Criteria) this;
        }

        public Criteria andMem01NotBetween(String value1, String value2) {
            addCriterion("MEM01 not between", value1, value2, "mem01");
            return (Criteria) this;
        }

        public Criteria andHklxIsNull() {
            addCriterion("HKLX is null");
            return (Criteria) this;
        }

        public Criteria andHklxIsNotNull() {
            addCriterion("HKLX is not null");
            return (Criteria) this;
        }

        public Criteria andHklxEqualTo(String value) {
            addCriterion("HKLX =", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxNotEqualTo(String value) {
            addCriterion("HKLX <>", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxGreaterThan(String value) {
            addCriterion("HKLX >", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxGreaterThanOrEqualTo(String value) {
            addCriterion("HKLX >=", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxLessThan(String value) {
            addCriterion("HKLX <", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxLessThanOrEqualTo(String value) {
            addCriterion("HKLX <=", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxLike(String value) {
            addCriterion("HKLX like", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxNotLike(String value) {
            addCriterion("HKLX not like", value, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxIn(List<String> values) {
            addCriterion("HKLX in", values, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxNotIn(List<String> values) {
            addCriterion("HKLX not in", values, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxBetween(String value1, String value2) {
            addCriterion("HKLX between", value1, value2, "hklx");
            return (Criteria) this;
        }

        public Criteria andHklxNotBetween(String value1, String value2) {
            addCriterion("HKLX not between", value1, value2, "hklx");
            return (Criteria) this;
        }

        public Criteria andPogjjbzIsNull() {
            addCriterion("POGJJBZ is null");
            return (Criteria) this;
        }

        public Criteria andPogjjbzIsNotNull() {
            addCriterion("POGJJBZ is not null");
            return (Criteria) this;
        }

        public Criteria andPogjjbzEqualTo(BigDecimal value) {
            addCriterion("POGJJBZ =", value, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzNotEqualTo(BigDecimal value) {
            addCriterion("POGJJBZ <>", value, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzGreaterThan(BigDecimal value) {
            addCriterion("POGJJBZ >", value, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("POGJJBZ >=", value, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzLessThan(BigDecimal value) {
            addCriterion("POGJJBZ <", value, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzLessThanOrEqualTo(BigDecimal value) {
            addCriterion("POGJJBZ <=", value, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzIn(List<BigDecimal> values) {
            addCriterion("POGJJBZ in", values, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzNotIn(List<BigDecimal> values) {
            addCriterion("POGJJBZ not in", values, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("POGJJBZ between", value1, value2, "pogjjbz");
            return (Criteria) this;
        }

        public Criteria andPogjjbzNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("POGJJBZ not between", value1, value2, "pogjjbz");
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