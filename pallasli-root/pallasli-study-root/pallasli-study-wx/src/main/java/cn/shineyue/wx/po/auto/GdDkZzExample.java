package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GdDkZzExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GdDkZzExample() {
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

        public Criteria andE001IsNull() {
            addCriterion("E001 is null");
            return (Criteria) this;
        }

        public Criteria andE001IsNotNull() {
            addCriterion("E001 is not null");
            return (Criteria) this;
        }

        public Criteria andE001EqualTo(String value) {
            addCriterion("E001 =", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001NotEqualTo(String value) {
            addCriterion("E001 <>", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001GreaterThan(String value) {
            addCriterion("E001 >", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001GreaterThanOrEqualTo(String value) {
            addCriterion("E001 >=", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001LessThan(String value) {
            addCriterion("E001 <", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001LessThanOrEqualTo(String value) {
            addCriterion("E001 <=", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001Like(String value) {
            addCriterion("E001 like", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001NotLike(String value) {
            addCriterion("E001 not like", value, "e001");
            return (Criteria) this;
        }

        public Criteria andE001In(List<String> values) {
            addCriterion("E001 in", values, "e001");
            return (Criteria) this;
        }

        public Criteria andE001NotIn(List<String> values) {
            addCriterion("E001 not in", values, "e001");
            return (Criteria) this;
        }

        public Criteria andE001Between(String value1, String value2) {
            addCriterion("E001 between", value1, value2, "e001");
            return (Criteria) this;
        }

        public Criteria andE001NotBetween(String value1, String value2) {
            addCriterion("E001 not between", value1, value2, "e001");
            return (Criteria) this;
        }

        public Criteria andG000IsNull() {
            addCriterion("G000 is null");
            return (Criteria) this;
        }

        public Criteria andG000IsNotNull() {
            addCriterion("G000 is not null");
            return (Criteria) this;
        }

        public Criteria andG000EqualTo(String value) {
            addCriterion("G000 =", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000NotEqualTo(String value) {
            addCriterion("G000 <>", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000GreaterThan(String value) {
            addCriterion("G000 >", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000GreaterThanOrEqualTo(String value) {
            addCriterion("G000 >=", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000LessThan(String value) {
            addCriterion("G000 <", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000LessThanOrEqualTo(String value) {
            addCriterion("G000 <=", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000Like(String value) {
            addCriterion("G000 like", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000NotLike(String value) {
            addCriterion("G000 not like", value, "g000");
            return (Criteria) this;
        }

        public Criteria andG000In(List<String> values) {
            addCriterion("G000 in", values, "g000");
            return (Criteria) this;
        }

        public Criteria andG000NotIn(List<String> values) {
            addCriterion("G000 not in", values, "g000");
            return (Criteria) this;
        }

        public Criteria andG000Between(String value1, String value2) {
            addCriterion("G000 between", value1, value2, "g000");
            return (Criteria) this;
        }

        public Criteria andG000NotBetween(String value1, String value2) {
            addCriterion("G000 not between", value1, value2, "g000");
            return (Criteria) this;
        }

        public Criteria andE021IsNull() {
            addCriterion("E021 is null");
            return (Criteria) this;
        }

        public Criteria andE021IsNotNull() {
            addCriterion("E021 is not null");
            return (Criteria) this;
        }

        public Criteria andE021EqualTo(String value) {
            addCriterion("E021 =", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021NotEqualTo(String value) {
            addCriterion("E021 <>", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021GreaterThan(String value) {
            addCriterion("E021 >", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021GreaterThanOrEqualTo(String value) {
            addCriterion("E021 >=", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021LessThan(String value) {
            addCriterion("E021 <", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021LessThanOrEqualTo(String value) {
            addCriterion("E021 <=", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021Like(String value) {
            addCriterion("E021 like", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021NotLike(String value) {
            addCriterion("E021 not like", value, "e021");
            return (Criteria) this;
        }

        public Criteria andE021In(List<String> values) {
            addCriterion("E021 in", values, "e021");
            return (Criteria) this;
        }

        public Criteria andE021NotIn(List<String> values) {
            addCriterion("E021 not in", values, "e021");
            return (Criteria) this;
        }

        public Criteria andE021Between(String value1, String value2) {
            addCriterion("E021 between", value1, value2, "e021");
            return (Criteria) this;
        }

        public Criteria andE021NotBetween(String value1, String value2) {
            addCriterion("E021 not between", value1, value2, "e021");
            return (Criteria) this;
        }

        public Criteria andE022IsNull() {
            addCriterion("E022 is null");
            return (Criteria) this;
        }

        public Criteria andE022IsNotNull() {
            addCriterion("E022 is not null");
            return (Criteria) this;
        }

        public Criteria andE022EqualTo(String value) {
            addCriterion("E022 =", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022NotEqualTo(String value) {
            addCriterion("E022 <>", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022GreaterThan(String value) {
            addCriterion("E022 >", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022GreaterThanOrEqualTo(String value) {
            addCriterion("E022 >=", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022LessThan(String value) {
            addCriterion("E022 <", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022LessThanOrEqualTo(String value) {
            addCriterion("E022 <=", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022Like(String value) {
            addCriterion("E022 like", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022NotLike(String value) {
            addCriterion("E022 not like", value, "e022");
            return (Criteria) this;
        }

        public Criteria andE022In(List<String> values) {
            addCriterion("E022 in", values, "e022");
            return (Criteria) this;
        }

        public Criteria andE022NotIn(List<String> values) {
            addCriterion("E022 not in", values, "e022");
            return (Criteria) this;
        }

        public Criteria andE022Between(String value1, String value2) {
            addCriterion("E022 between", value1, value2, "e022");
            return (Criteria) this;
        }

        public Criteria andE022NotBetween(String value1, String value2) {
            addCriterion("E022 not between", value1, value2, "e022");
            return (Criteria) this;
        }

        public Criteria andE023IsNull() {
            addCriterion("E023 is null");
            return (Criteria) this;
        }

        public Criteria andE023IsNotNull() {
            addCriterion("E023 is not null");
            return (Criteria) this;
        }

        public Criteria andE023EqualTo(BigDecimal value) {
            addCriterion("E023 =", value, "e023");
            return (Criteria) this;
        }

        public Criteria andE023NotEqualTo(BigDecimal value) {
            addCriterion("E023 <>", value, "e023");
            return (Criteria) this;
        }

        public Criteria andE023GreaterThan(BigDecimal value) {
            addCriterion("E023 >", value, "e023");
            return (Criteria) this;
        }

        public Criteria andE023GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E023 >=", value, "e023");
            return (Criteria) this;
        }

        public Criteria andE023LessThan(BigDecimal value) {
            addCriterion("E023 <", value, "e023");
            return (Criteria) this;
        }

        public Criteria andE023LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E023 <=", value, "e023");
            return (Criteria) this;
        }

        public Criteria andE023In(List<BigDecimal> values) {
            addCriterion("E023 in", values, "e023");
            return (Criteria) this;
        }

        public Criteria andE023NotIn(List<BigDecimal> values) {
            addCriterion("E023 not in", values, "e023");
            return (Criteria) this;
        }

        public Criteria andE023Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E023 between", value1, value2, "e023");
            return (Criteria) this;
        }

        public Criteria andE023NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E023 not between", value1, value2, "e023");
            return (Criteria) this;
        }

        public Criteria andE025IsNull() {
            addCriterion("E025 is null");
            return (Criteria) this;
        }

        public Criteria andE025IsNotNull() {
            addCriterion("E025 is not null");
            return (Criteria) this;
        }

        public Criteria andE025EqualTo(BigDecimal value) {
            addCriterion("E025 =", value, "e025");
            return (Criteria) this;
        }

        public Criteria andE025NotEqualTo(BigDecimal value) {
            addCriterion("E025 <>", value, "e025");
            return (Criteria) this;
        }

        public Criteria andE025GreaterThan(BigDecimal value) {
            addCriterion("E025 >", value, "e025");
            return (Criteria) this;
        }

        public Criteria andE025GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E025 >=", value, "e025");
            return (Criteria) this;
        }

        public Criteria andE025LessThan(BigDecimal value) {
            addCriterion("E025 <", value, "e025");
            return (Criteria) this;
        }

        public Criteria andE025LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E025 <=", value, "e025");
            return (Criteria) this;
        }

        public Criteria andE025In(List<BigDecimal> values) {
            addCriterion("E025 in", values, "e025");
            return (Criteria) this;
        }

        public Criteria andE025NotIn(List<BigDecimal> values) {
            addCriterion("E025 not in", values, "e025");
            return (Criteria) this;
        }

        public Criteria andE025Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E025 between", value1, value2, "e025");
            return (Criteria) this;
        }

        public Criteria andE025NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E025 not between", value1, value2, "e025");
            return (Criteria) this;
        }

        public Criteria andE026IsNull() {
            addCriterion("E026 is null");
            return (Criteria) this;
        }

        public Criteria andE026IsNotNull() {
            addCriterion("E026 is not null");
            return (Criteria) this;
        }

        public Criteria andE026EqualTo(BigDecimal value) {
            addCriterion("E026 =", value, "e026");
            return (Criteria) this;
        }

        public Criteria andE026NotEqualTo(BigDecimal value) {
            addCriterion("E026 <>", value, "e026");
            return (Criteria) this;
        }

        public Criteria andE026GreaterThan(BigDecimal value) {
            addCriterion("E026 >", value, "e026");
            return (Criteria) this;
        }

        public Criteria andE026GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E026 >=", value, "e026");
            return (Criteria) this;
        }

        public Criteria andE026LessThan(BigDecimal value) {
            addCriterion("E026 <", value, "e026");
            return (Criteria) this;
        }

        public Criteria andE026LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E026 <=", value, "e026");
            return (Criteria) this;
        }

        public Criteria andE026In(List<BigDecimal> values) {
            addCriterion("E026 in", values, "e026");
            return (Criteria) this;
        }

        public Criteria andE026NotIn(List<BigDecimal> values) {
            addCriterion("E026 not in", values, "e026");
            return (Criteria) this;
        }

        public Criteria andE026Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E026 between", value1, value2, "e026");
            return (Criteria) this;
        }

        public Criteria andE026NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E026 not between", value1, value2, "e026");
            return (Criteria) this;
        }

        public Criteria andE090IsNull() {
            addCriterion("E090 is null");
            return (Criteria) this;
        }

        public Criteria andE090IsNotNull() {
            addCriterion("E090 is not null");
            return (Criteria) this;
        }

        public Criteria andE090EqualTo(BigDecimal value) {
            addCriterion("E090 =", value, "e090");
            return (Criteria) this;
        }

        public Criteria andE090NotEqualTo(BigDecimal value) {
            addCriterion("E090 <>", value, "e090");
            return (Criteria) this;
        }

        public Criteria andE090GreaterThan(BigDecimal value) {
            addCriterion("E090 >", value, "e090");
            return (Criteria) this;
        }

        public Criteria andE090GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E090 >=", value, "e090");
            return (Criteria) this;
        }

        public Criteria andE090LessThan(BigDecimal value) {
            addCriterion("E090 <", value, "e090");
            return (Criteria) this;
        }

        public Criteria andE090LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E090 <=", value, "e090");
            return (Criteria) this;
        }

        public Criteria andE090In(List<BigDecimal> values) {
            addCriterion("E090 in", values, "e090");
            return (Criteria) this;
        }

        public Criteria andE090NotIn(List<BigDecimal> values) {
            addCriterion("E090 not in", values, "e090");
            return (Criteria) this;
        }

        public Criteria andE090Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E090 between", value1, value2, "e090");
            return (Criteria) this;
        }

        public Criteria andE090NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E090 not between", value1, value2, "e090");
            return (Criteria) this;
        }

        public Criteria andE027IsNull() {
            addCriterion("E027 is null");
            return (Criteria) this;
        }

        public Criteria andE027IsNotNull() {
            addCriterion("E027 is not null");
            return (Criteria) this;
        }

        public Criteria andE027EqualTo(BigDecimal value) {
            addCriterion("E027 =", value, "e027");
            return (Criteria) this;
        }

        public Criteria andE027NotEqualTo(BigDecimal value) {
            addCriterion("E027 <>", value, "e027");
            return (Criteria) this;
        }

        public Criteria andE027GreaterThan(BigDecimal value) {
            addCriterion("E027 >", value, "e027");
            return (Criteria) this;
        }

        public Criteria andE027GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E027 >=", value, "e027");
            return (Criteria) this;
        }

        public Criteria andE027LessThan(BigDecimal value) {
            addCriterion("E027 <", value, "e027");
            return (Criteria) this;
        }

        public Criteria andE027LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E027 <=", value, "e027");
            return (Criteria) this;
        }

        public Criteria andE027In(List<BigDecimal> values) {
            addCriterion("E027 in", values, "e027");
            return (Criteria) this;
        }

        public Criteria andE027NotIn(List<BigDecimal> values) {
            addCriterion("E027 not in", values, "e027");
            return (Criteria) this;
        }

        public Criteria andE027Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E027 between", value1, value2, "e027");
            return (Criteria) this;
        }

        public Criteria andE027NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E027 not between", value1, value2, "e027");
            return (Criteria) this;
        }

        public Criteria andE028IsNull() {
            addCriterion("E028 is null");
            return (Criteria) this;
        }

        public Criteria andE028IsNotNull() {
            addCriterion("E028 is not null");
            return (Criteria) this;
        }

        public Criteria andE028EqualTo(String value) {
            addCriterion("E028 =", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028NotEqualTo(String value) {
            addCriterion("E028 <>", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028GreaterThan(String value) {
            addCriterion("E028 >", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028GreaterThanOrEqualTo(String value) {
            addCriterion("E028 >=", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028LessThan(String value) {
            addCriterion("E028 <", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028LessThanOrEqualTo(String value) {
            addCriterion("E028 <=", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028Like(String value) {
            addCriterion("E028 like", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028NotLike(String value) {
            addCriterion("E028 not like", value, "e028");
            return (Criteria) this;
        }

        public Criteria andE028In(List<String> values) {
            addCriterion("E028 in", values, "e028");
            return (Criteria) this;
        }

        public Criteria andE028NotIn(List<String> values) {
            addCriterion("E028 not in", values, "e028");
            return (Criteria) this;
        }

        public Criteria andE028Between(String value1, String value2) {
            addCriterion("E028 between", value1, value2, "e028");
            return (Criteria) this;
        }

        public Criteria andE028NotBetween(String value1, String value2) {
            addCriterion("E028 not between", value1, value2, "e028");
            return (Criteria) this;
        }

        public Criteria andE029IsNull() {
            addCriterion("E029 is null");
            return (Criteria) this;
        }

        public Criteria andE029IsNotNull() {
            addCriterion("E029 is not null");
            return (Criteria) this;
        }

        public Criteria andE029EqualTo(Date value) {
            addCriterionForJDBCDate("E029 =", value, "e029");
            return (Criteria) this;
        }

        public Criteria andE029NotEqualTo(Date value) {
            addCriterionForJDBCDate("E029 <>", value, "e029");
            return (Criteria) this;
        }

        public Criteria andE029GreaterThan(Date value) {
            addCriterionForJDBCDate("E029 >", value, "e029");
            return (Criteria) this;
        }

        public Criteria andE029GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E029 >=", value, "e029");
            return (Criteria) this;
        }

        public Criteria andE029LessThan(Date value) {
            addCriterionForJDBCDate("E029 <", value, "e029");
            return (Criteria) this;
        }

        public Criteria andE029LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E029 <=", value, "e029");
            return (Criteria) this;
        }

        public Criteria andE029In(List<Date> values) {
            addCriterionForJDBCDate("E029 in", values, "e029");
            return (Criteria) this;
        }

        public Criteria andE029NotIn(List<Date> values) {
            addCriterionForJDBCDate("E029 not in", values, "e029");
            return (Criteria) this;
        }

        public Criteria andE029Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E029 between", value1, value2, "e029");
            return (Criteria) this;
        }

        public Criteria andE029NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E029 not between", value1, value2, "e029");
            return (Criteria) this;
        }

        public Criteria andE030IsNull() {
            addCriterion("E030 is null");
            return (Criteria) this;
        }

        public Criteria andE030IsNotNull() {
            addCriterion("E030 is not null");
            return (Criteria) this;
        }

        public Criteria andE030EqualTo(BigDecimal value) {
            addCriterion("E030 =", value, "e030");
            return (Criteria) this;
        }

        public Criteria andE030NotEqualTo(BigDecimal value) {
            addCriterion("E030 <>", value, "e030");
            return (Criteria) this;
        }

        public Criteria andE030GreaterThan(BigDecimal value) {
            addCriterion("E030 >", value, "e030");
            return (Criteria) this;
        }

        public Criteria andE030GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E030 >=", value, "e030");
            return (Criteria) this;
        }

        public Criteria andE030LessThan(BigDecimal value) {
            addCriterion("E030 <", value, "e030");
            return (Criteria) this;
        }

        public Criteria andE030LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E030 <=", value, "e030");
            return (Criteria) this;
        }

        public Criteria andE030In(List<BigDecimal> values) {
            addCriterion("E030 in", values, "e030");
            return (Criteria) this;
        }

        public Criteria andE030NotIn(List<BigDecimal> values) {
            addCriterion("E030 not in", values, "e030");
            return (Criteria) this;
        }

        public Criteria andE030Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E030 between", value1, value2, "e030");
            return (Criteria) this;
        }

        public Criteria andE030NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E030 not between", value1, value2, "e030");
            return (Criteria) this;
        }

        public Criteria andE031IsNull() {
            addCriterion("E031 is null");
            return (Criteria) this;
        }

        public Criteria andE031IsNotNull() {
            addCriterion("E031 is not null");
            return (Criteria) this;
        }

        public Criteria andE031EqualTo(BigDecimal value) {
            addCriterion("E031 =", value, "e031");
            return (Criteria) this;
        }

        public Criteria andE031NotEqualTo(BigDecimal value) {
            addCriterion("E031 <>", value, "e031");
            return (Criteria) this;
        }

        public Criteria andE031GreaterThan(BigDecimal value) {
            addCriterion("E031 >", value, "e031");
            return (Criteria) this;
        }

        public Criteria andE031GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E031 >=", value, "e031");
            return (Criteria) this;
        }

        public Criteria andE031LessThan(BigDecimal value) {
            addCriterion("E031 <", value, "e031");
            return (Criteria) this;
        }

        public Criteria andE031LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E031 <=", value, "e031");
            return (Criteria) this;
        }

        public Criteria andE031In(List<BigDecimal> values) {
            addCriterion("E031 in", values, "e031");
            return (Criteria) this;
        }

        public Criteria andE031NotIn(List<BigDecimal> values) {
            addCriterion("E031 not in", values, "e031");
            return (Criteria) this;
        }

        public Criteria andE031Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E031 between", value1, value2, "e031");
            return (Criteria) this;
        }

        public Criteria andE031NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E031 not between", value1, value2, "e031");
            return (Criteria) this;
        }

        public Criteria andE032IsNull() {
            addCriterion("E032 is null");
            return (Criteria) this;
        }

        public Criteria andE032IsNotNull() {
            addCriterion("E032 is not null");
            return (Criteria) this;
        }

        public Criteria andE032EqualTo(BigDecimal value) {
            addCriterion("E032 =", value, "e032");
            return (Criteria) this;
        }

        public Criteria andE032NotEqualTo(BigDecimal value) {
            addCriterion("E032 <>", value, "e032");
            return (Criteria) this;
        }

        public Criteria andE032GreaterThan(BigDecimal value) {
            addCriterion("E032 >", value, "e032");
            return (Criteria) this;
        }

        public Criteria andE032GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E032 >=", value, "e032");
            return (Criteria) this;
        }

        public Criteria andE032LessThan(BigDecimal value) {
            addCriterion("E032 <", value, "e032");
            return (Criteria) this;
        }

        public Criteria andE032LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E032 <=", value, "e032");
            return (Criteria) this;
        }

        public Criteria andE032In(List<BigDecimal> values) {
            addCriterion("E032 in", values, "e032");
            return (Criteria) this;
        }

        public Criteria andE032NotIn(List<BigDecimal> values) {
            addCriterion("E032 not in", values, "e032");
            return (Criteria) this;
        }

        public Criteria andE032Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E032 between", value1, value2, "e032");
            return (Criteria) this;
        }

        public Criteria andE032NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E032 not between", value1, value2, "e032");
            return (Criteria) this;
        }

        public Criteria andE033IsNull() {
            addCriterion("E033 is null");
            return (Criteria) this;
        }

        public Criteria andE033IsNotNull() {
            addCriterion("E033 is not null");
            return (Criteria) this;
        }

        public Criteria andE033EqualTo(BigDecimal value) {
            addCriterion("E033 =", value, "e033");
            return (Criteria) this;
        }

        public Criteria andE033NotEqualTo(BigDecimal value) {
            addCriterion("E033 <>", value, "e033");
            return (Criteria) this;
        }

        public Criteria andE033GreaterThan(BigDecimal value) {
            addCriterion("E033 >", value, "e033");
            return (Criteria) this;
        }

        public Criteria andE033GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E033 >=", value, "e033");
            return (Criteria) this;
        }

        public Criteria andE033LessThan(BigDecimal value) {
            addCriterion("E033 <", value, "e033");
            return (Criteria) this;
        }

        public Criteria andE033LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E033 <=", value, "e033");
            return (Criteria) this;
        }

        public Criteria andE033In(List<BigDecimal> values) {
            addCriterion("E033 in", values, "e033");
            return (Criteria) this;
        }

        public Criteria andE033NotIn(List<BigDecimal> values) {
            addCriterion("E033 not in", values, "e033");
            return (Criteria) this;
        }

        public Criteria andE033Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E033 between", value1, value2, "e033");
            return (Criteria) this;
        }

        public Criteria andE033NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E033 not between", value1, value2, "e033");
            return (Criteria) this;
        }

        public Criteria andE034IsNull() {
            addCriterion("E034 is null");
            return (Criteria) this;
        }

        public Criteria andE034IsNotNull() {
            addCriterion("E034 is not null");
            return (Criteria) this;
        }

        public Criteria andE034EqualTo(BigDecimal value) {
            addCriterion("E034 =", value, "e034");
            return (Criteria) this;
        }

        public Criteria andE034NotEqualTo(BigDecimal value) {
            addCriterion("E034 <>", value, "e034");
            return (Criteria) this;
        }

        public Criteria andE034GreaterThan(BigDecimal value) {
            addCriterion("E034 >", value, "e034");
            return (Criteria) this;
        }

        public Criteria andE034GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E034 >=", value, "e034");
            return (Criteria) this;
        }

        public Criteria andE034LessThan(BigDecimal value) {
            addCriterion("E034 <", value, "e034");
            return (Criteria) this;
        }

        public Criteria andE034LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E034 <=", value, "e034");
            return (Criteria) this;
        }

        public Criteria andE034In(List<BigDecimal> values) {
            addCriterion("E034 in", values, "e034");
            return (Criteria) this;
        }

        public Criteria andE034NotIn(List<BigDecimal> values) {
            addCriterion("E034 not in", values, "e034");
            return (Criteria) this;
        }

        public Criteria andE034Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E034 between", value1, value2, "e034");
            return (Criteria) this;
        }

        public Criteria andE034NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E034 not between", value1, value2, "e034");
            return (Criteria) this;
        }

        public Criteria andE035IsNull() {
            addCriterion("E035 is null");
            return (Criteria) this;
        }

        public Criteria andE035IsNotNull() {
            addCriterion("E035 is not null");
            return (Criteria) this;
        }

        public Criteria andE035EqualTo(BigDecimal value) {
            addCriterion("E035 =", value, "e035");
            return (Criteria) this;
        }

        public Criteria andE035NotEqualTo(BigDecimal value) {
            addCriterion("E035 <>", value, "e035");
            return (Criteria) this;
        }

        public Criteria andE035GreaterThan(BigDecimal value) {
            addCriterion("E035 >", value, "e035");
            return (Criteria) this;
        }

        public Criteria andE035GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E035 >=", value, "e035");
            return (Criteria) this;
        }

        public Criteria andE035LessThan(BigDecimal value) {
            addCriterion("E035 <", value, "e035");
            return (Criteria) this;
        }

        public Criteria andE035LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E035 <=", value, "e035");
            return (Criteria) this;
        }

        public Criteria andE035In(List<BigDecimal> values) {
            addCriterion("E035 in", values, "e035");
            return (Criteria) this;
        }

        public Criteria andE035NotIn(List<BigDecimal> values) {
            addCriterion("E035 not in", values, "e035");
            return (Criteria) this;
        }

        public Criteria andE035Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E035 between", value1, value2, "e035");
            return (Criteria) this;
        }

        public Criteria andE035NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E035 not between", value1, value2, "e035");
            return (Criteria) this;
        }

        public Criteria andE036IsNull() {
            addCriterion("E036 is null");
            return (Criteria) this;
        }

        public Criteria andE036IsNotNull() {
            addCriterion("E036 is not null");
            return (Criteria) this;
        }

        public Criteria andE036EqualTo(BigDecimal value) {
            addCriterion("E036 =", value, "e036");
            return (Criteria) this;
        }

        public Criteria andE036NotEqualTo(BigDecimal value) {
            addCriterion("E036 <>", value, "e036");
            return (Criteria) this;
        }

        public Criteria andE036GreaterThan(BigDecimal value) {
            addCriterion("E036 >", value, "e036");
            return (Criteria) this;
        }

        public Criteria andE036GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E036 >=", value, "e036");
            return (Criteria) this;
        }

        public Criteria andE036LessThan(BigDecimal value) {
            addCriterion("E036 <", value, "e036");
            return (Criteria) this;
        }

        public Criteria andE036LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E036 <=", value, "e036");
            return (Criteria) this;
        }

        public Criteria andE036In(List<BigDecimal> values) {
            addCriterion("E036 in", values, "e036");
            return (Criteria) this;
        }

        public Criteria andE036NotIn(List<BigDecimal> values) {
            addCriterion("E036 not in", values, "e036");
            return (Criteria) this;
        }

        public Criteria andE036Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E036 between", value1, value2, "e036");
            return (Criteria) this;
        }

        public Criteria andE036NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E036 not between", value1, value2, "e036");
            return (Criteria) this;
        }

        public Criteria andE037IsNull() {
            addCriterion("E037 is null");
            return (Criteria) this;
        }

        public Criteria andE037IsNotNull() {
            addCriterion("E037 is not null");
            return (Criteria) this;
        }

        public Criteria andE037EqualTo(BigDecimal value) {
            addCriterion("E037 =", value, "e037");
            return (Criteria) this;
        }

        public Criteria andE037NotEqualTo(BigDecimal value) {
            addCriterion("E037 <>", value, "e037");
            return (Criteria) this;
        }

        public Criteria andE037GreaterThan(BigDecimal value) {
            addCriterion("E037 >", value, "e037");
            return (Criteria) this;
        }

        public Criteria andE037GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E037 >=", value, "e037");
            return (Criteria) this;
        }

        public Criteria andE037LessThan(BigDecimal value) {
            addCriterion("E037 <", value, "e037");
            return (Criteria) this;
        }

        public Criteria andE037LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E037 <=", value, "e037");
            return (Criteria) this;
        }

        public Criteria andE037In(List<BigDecimal> values) {
            addCriterion("E037 in", values, "e037");
            return (Criteria) this;
        }

        public Criteria andE037NotIn(List<BigDecimal> values) {
            addCriterion("E037 not in", values, "e037");
            return (Criteria) this;
        }

        public Criteria andE037Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E037 between", value1, value2, "e037");
            return (Criteria) this;
        }

        public Criteria andE037NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E037 not between", value1, value2, "e037");
            return (Criteria) this;
        }

        public Criteria andE040IsNull() {
            addCriterion("E040 is null");
            return (Criteria) this;
        }

        public Criteria andE040IsNotNull() {
            addCriterion("E040 is not null");
            return (Criteria) this;
        }

        public Criteria andE040EqualTo(BigDecimal value) {
            addCriterion("E040 =", value, "e040");
            return (Criteria) this;
        }

        public Criteria andE040NotEqualTo(BigDecimal value) {
            addCriterion("E040 <>", value, "e040");
            return (Criteria) this;
        }

        public Criteria andE040GreaterThan(BigDecimal value) {
            addCriterion("E040 >", value, "e040");
            return (Criteria) this;
        }

        public Criteria andE040GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E040 >=", value, "e040");
            return (Criteria) this;
        }

        public Criteria andE040LessThan(BigDecimal value) {
            addCriterion("E040 <", value, "e040");
            return (Criteria) this;
        }

        public Criteria andE040LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E040 <=", value, "e040");
            return (Criteria) this;
        }

        public Criteria andE040In(List<BigDecimal> values) {
            addCriterion("E040 in", values, "e040");
            return (Criteria) this;
        }

        public Criteria andE040NotIn(List<BigDecimal> values) {
            addCriterion("E040 not in", values, "e040");
            return (Criteria) this;
        }

        public Criteria andE040Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E040 between", value1, value2, "e040");
            return (Criteria) this;
        }

        public Criteria andE040NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E040 not between", value1, value2, "e040");
            return (Criteria) this;
        }

        public Criteria andE041IsNull() {
            addCriterion("E041 is null");
            return (Criteria) this;
        }

        public Criteria andE041IsNotNull() {
            addCriterion("E041 is not null");
            return (Criteria) this;
        }

        public Criteria andE041EqualTo(Date value) {
            addCriterionForJDBCDate("E041 =", value, "e041");
            return (Criteria) this;
        }

        public Criteria andE041NotEqualTo(Date value) {
            addCriterionForJDBCDate("E041 <>", value, "e041");
            return (Criteria) this;
        }

        public Criteria andE041GreaterThan(Date value) {
            addCriterionForJDBCDate("E041 >", value, "e041");
            return (Criteria) this;
        }

        public Criteria andE041GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E041 >=", value, "e041");
            return (Criteria) this;
        }

        public Criteria andE041LessThan(Date value) {
            addCriterionForJDBCDate("E041 <", value, "e041");
            return (Criteria) this;
        }

        public Criteria andE041LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E041 <=", value, "e041");
            return (Criteria) this;
        }

        public Criteria andE041In(List<Date> values) {
            addCriterionForJDBCDate("E041 in", values, "e041");
            return (Criteria) this;
        }

        public Criteria andE041NotIn(List<Date> values) {
            addCriterionForJDBCDate("E041 not in", values, "e041");
            return (Criteria) this;
        }

        public Criteria andE041Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E041 between", value1, value2, "e041");
            return (Criteria) this;
        }

        public Criteria andE041NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E041 not between", value1, value2, "e041");
            return (Criteria) this;
        }

        public Criteria andE042IsNull() {
            addCriterion("E042 is null");
            return (Criteria) this;
        }

        public Criteria andE042IsNotNull() {
            addCriterion("E042 is not null");
            return (Criteria) this;
        }

        public Criteria andE042EqualTo(BigDecimal value) {
            addCriterion("E042 =", value, "e042");
            return (Criteria) this;
        }

        public Criteria andE042NotEqualTo(BigDecimal value) {
            addCriterion("E042 <>", value, "e042");
            return (Criteria) this;
        }

        public Criteria andE042GreaterThan(BigDecimal value) {
            addCriterion("E042 >", value, "e042");
            return (Criteria) this;
        }

        public Criteria andE042GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E042 >=", value, "e042");
            return (Criteria) this;
        }

        public Criteria andE042LessThan(BigDecimal value) {
            addCriterion("E042 <", value, "e042");
            return (Criteria) this;
        }

        public Criteria andE042LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E042 <=", value, "e042");
            return (Criteria) this;
        }

        public Criteria andE042In(List<BigDecimal> values) {
            addCriterion("E042 in", values, "e042");
            return (Criteria) this;
        }

        public Criteria andE042NotIn(List<BigDecimal> values) {
            addCriterion("E042 not in", values, "e042");
            return (Criteria) this;
        }

        public Criteria andE042Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E042 between", value1, value2, "e042");
            return (Criteria) this;
        }

        public Criteria andE042NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E042 not between", value1, value2, "e042");
            return (Criteria) this;
        }

        public Criteria andE043IsNull() {
            addCriterion("E043 is null");
            return (Criteria) this;
        }

        public Criteria andE043IsNotNull() {
            addCriterion("E043 is not null");
            return (Criteria) this;
        }

        public Criteria andE043EqualTo(String value) {
            addCriterion("E043 =", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043NotEqualTo(String value) {
            addCriterion("E043 <>", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043GreaterThan(String value) {
            addCriterion("E043 >", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043GreaterThanOrEqualTo(String value) {
            addCriterion("E043 >=", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043LessThan(String value) {
            addCriterion("E043 <", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043LessThanOrEqualTo(String value) {
            addCriterion("E043 <=", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043Like(String value) {
            addCriterion("E043 like", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043NotLike(String value) {
            addCriterion("E043 not like", value, "e043");
            return (Criteria) this;
        }

        public Criteria andE043In(List<String> values) {
            addCriterion("E043 in", values, "e043");
            return (Criteria) this;
        }

        public Criteria andE043NotIn(List<String> values) {
            addCriterion("E043 not in", values, "e043");
            return (Criteria) this;
        }

        public Criteria andE043Between(String value1, String value2) {
            addCriterion("E043 between", value1, value2, "e043");
            return (Criteria) this;
        }

        public Criteria andE043NotBetween(String value1, String value2) {
            addCriterion("E043 not between", value1, value2, "e043");
            return (Criteria) this;
        }

        public Criteria andE044IsNull() {
            addCriterion("E044 is null");
            return (Criteria) this;
        }

        public Criteria andE044IsNotNull() {
            addCriterion("E044 is not null");
            return (Criteria) this;
        }

        public Criteria andE044EqualTo(Date value) {
            addCriterionForJDBCDate("E044 =", value, "e044");
            return (Criteria) this;
        }

        public Criteria andE044NotEqualTo(Date value) {
            addCriterionForJDBCDate("E044 <>", value, "e044");
            return (Criteria) this;
        }

        public Criteria andE044GreaterThan(Date value) {
            addCriterionForJDBCDate("E044 >", value, "e044");
            return (Criteria) this;
        }

        public Criteria andE044GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E044 >=", value, "e044");
            return (Criteria) this;
        }

        public Criteria andE044LessThan(Date value) {
            addCriterionForJDBCDate("E044 <", value, "e044");
            return (Criteria) this;
        }

        public Criteria andE044LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E044 <=", value, "e044");
            return (Criteria) this;
        }

        public Criteria andE044In(List<Date> values) {
            addCriterionForJDBCDate("E044 in", values, "e044");
            return (Criteria) this;
        }

        public Criteria andE044NotIn(List<Date> values) {
            addCriterionForJDBCDate("E044 not in", values, "e044");
            return (Criteria) this;
        }

        public Criteria andE044Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E044 between", value1, value2, "e044");
            return (Criteria) this;
        }

        public Criteria andE044NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E044 not between", value1, value2, "e044");
            return (Criteria) this;
        }

        public Criteria andE045IsNull() {
            addCriterion("E045 is null");
            return (Criteria) this;
        }

        public Criteria andE045IsNotNull() {
            addCriterion("E045 is not null");
            return (Criteria) this;
        }

        public Criteria andE045EqualTo(BigDecimal value) {
            addCriterion("E045 =", value, "e045");
            return (Criteria) this;
        }

        public Criteria andE045NotEqualTo(BigDecimal value) {
            addCriterion("E045 <>", value, "e045");
            return (Criteria) this;
        }

        public Criteria andE045GreaterThan(BigDecimal value) {
            addCriterion("E045 >", value, "e045");
            return (Criteria) this;
        }

        public Criteria andE045GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E045 >=", value, "e045");
            return (Criteria) this;
        }

        public Criteria andE045LessThan(BigDecimal value) {
            addCriterion("E045 <", value, "e045");
            return (Criteria) this;
        }

        public Criteria andE045LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E045 <=", value, "e045");
            return (Criteria) this;
        }

        public Criteria andE045In(List<BigDecimal> values) {
            addCriterion("E045 in", values, "e045");
            return (Criteria) this;
        }

        public Criteria andE045NotIn(List<BigDecimal> values) {
            addCriterion("E045 not in", values, "e045");
            return (Criteria) this;
        }

        public Criteria andE045Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E045 between", value1, value2, "e045");
            return (Criteria) this;
        }

        public Criteria andE045NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E045 not between", value1, value2, "e045");
            return (Criteria) this;
        }

        public Criteria andE046IsNull() {
            addCriterion("E046 is null");
            return (Criteria) this;
        }

        public Criteria andE046IsNotNull() {
            addCriterion("E046 is not null");
            return (Criteria) this;
        }

        public Criteria andE046EqualTo(BigDecimal value) {
            addCriterion("E046 =", value, "e046");
            return (Criteria) this;
        }

        public Criteria andE046NotEqualTo(BigDecimal value) {
            addCriterion("E046 <>", value, "e046");
            return (Criteria) this;
        }

        public Criteria andE046GreaterThan(BigDecimal value) {
            addCriterion("E046 >", value, "e046");
            return (Criteria) this;
        }

        public Criteria andE046GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E046 >=", value, "e046");
            return (Criteria) this;
        }

        public Criteria andE046LessThan(BigDecimal value) {
            addCriterion("E046 <", value, "e046");
            return (Criteria) this;
        }

        public Criteria andE046LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E046 <=", value, "e046");
            return (Criteria) this;
        }

        public Criteria andE046In(List<BigDecimal> values) {
            addCriterion("E046 in", values, "e046");
            return (Criteria) this;
        }

        public Criteria andE046NotIn(List<BigDecimal> values) {
            addCriterion("E046 not in", values, "e046");
            return (Criteria) this;
        }

        public Criteria andE046Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E046 between", value1, value2, "e046");
            return (Criteria) this;
        }

        public Criteria andE046NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E046 not between", value1, value2, "e046");
            return (Criteria) this;
        }

        public Criteria andE047IsNull() {
            addCriterion("E047 is null");
            return (Criteria) this;
        }

        public Criteria andE047IsNotNull() {
            addCriterion("E047 is not null");
            return (Criteria) this;
        }

        public Criteria andE047EqualTo(BigDecimal value) {
            addCriterion("E047 =", value, "e047");
            return (Criteria) this;
        }

        public Criteria andE047NotEqualTo(BigDecimal value) {
            addCriterion("E047 <>", value, "e047");
            return (Criteria) this;
        }

        public Criteria andE047GreaterThan(BigDecimal value) {
            addCriterion("E047 >", value, "e047");
            return (Criteria) this;
        }

        public Criteria andE047GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E047 >=", value, "e047");
            return (Criteria) this;
        }

        public Criteria andE047LessThan(BigDecimal value) {
            addCriterion("E047 <", value, "e047");
            return (Criteria) this;
        }

        public Criteria andE047LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E047 <=", value, "e047");
            return (Criteria) this;
        }

        public Criteria andE047In(List<BigDecimal> values) {
            addCriterion("E047 in", values, "e047");
            return (Criteria) this;
        }

        public Criteria andE047NotIn(List<BigDecimal> values) {
            addCriterion("E047 not in", values, "e047");
            return (Criteria) this;
        }

        public Criteria andE047Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E047 between", value1, value2, "e047");
            return (Criteria) this;
        }

        public Criteria andE047NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E047 not between", value1, value2, "e047");
            return (Criteria) this;
        }

        public Criteria andE051IsNull() {
            addCriterion("E051 is null");
            return (Criteria) this;
        }

        public Criteria andE051IsNotNull() {
            addCriterion("E051 is not null");
            return (Criteria) this;
        }

        public Criteria andE051EqualTo(Date value) {
            addCriterionForJDBCDate("E051 =", value, "e051");
            return (Criteria) this;
        }

        public Criteria andE051NotEqualTo(Date value) {
            addCriterionForJDBCDate("E051 <>", value, "e051");
            return (Criteria) this;
        }

        public Criteria andE051GreaterThan(Date value) {
            addCriterionForJDBCDate("E051 >", value, "e051");
            return (Criteria) this;
        }

        public Criteria andE051GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E051 >=", value, "e051");
            return (Criteria) this;
        }

        public Criteria andE051LessThan(Date value) {
            addCriterionForJDBCDate("E051 <", value, "e051");
            return (Criteria) this;
        }

        public Criteria andE051LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E051 <=", value, "e051");
            return (Criteria) this;
        }

        public Criteria andE051In(List<Date> values) {
            addCriterionForJDBCDate("E051 in", values, "e051");
            return (Criteria) this;
        }

        public Criteria andE051NotIn(List<Date> values) {
            addCriterionForJDBCDate("E051 not in", values, "e051");
            return (Criteria) this;
        }

        public Criteria andE051Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E051 between", value1, value2, "e051");
            return (Criteria) this;
        }

        public Criteria andE051NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E051 not between", value1, value2, "e051");
            return (Criteria) this;
        }

        public Criteria andE053IsNull() {
            addCriterion("E053 is null");
            return (Criteria) this;
        }

        public Criteria andE053IsNotNull() {
            addCriterion("E053 is not null");
            return (Criteria) this;
        }

        public Criteria andE053EqualTo(BigDecimal value) {
            addCriterion("E053 =", value, "e053");
            return (Criteria) this;
        }

        public Criteria andE053NotEqualTo(BigDecimal value) {
            addCriterion("E053 <>", value, "e053");
            return (Criteria) this;
        }

        public Criteria andE053GreaterThan(BigDecimal value) {
            addCriterion("E053 >", value, "e053");
            return (Criteria) this;
        }

        public Criteria andE053GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E053 >=", value, "e053");
            return (Criteria) this;
        }

        public Criteria andE053LessThan(BigDecimal value) {
            addCriterion("E053 <", value, "e053");
            return (Criteria) this;
        }

        public Criteria andE053LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E053 <=", value, "e053");
            return (Criteria) this;
        }

        public Criteria andE053In(List<BigDecimal> values) {
            addCriterion("E053 in", values, "e053");
            return (Criteria) this;
        }

        public Criteria andE053NotIn(List<BigDecimal> values) {
            addCriterion("E053 not in", values, "e053");
            return (Criteria) this;
        }

        public Criteria andE053Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E053 between", value1, value2, "e053");
            return (Criteria) this;
        }

        public Criteria andE053NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E053 not between", value1, value2, "e053");
            return (Criteria) this;
        }

        public Criteria andE039IsNull() {
            addCriterion("E039 is null");
            return (Criteria) this;
        }

        public Criteria andE039IsNotNull() {
            addCriterion("E039 is not null");
            return (Criteria) this;
        }

        public Criteria andE039EqualTo(BigDecimal value) {
            addCriterion("E039 =", value, "e039");
            return (Criteria) this;
        }

        public Criteria andE039NotEqualTo(BigDecimal value) {
            addCriterion("E039 <>", value, "e039");
            return (Criteria) this;
        }

        public Criteria andE039GreaterThan(BigDecimal value) {
            addCriterion("E039 >", value, "e039");
            return (Criteria) this;
        }

        public Criteria andE039GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E039 >=", value, "e039");
            return (Criteria) this;
        }

        public Criteria andE039LessThan(BigDecimal value) {
            addCriterion("E039 <", value, "e039");
            return (Criteria) this;
        }

        public Criteria andE039LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E039 <=", value, "e039");
            return (Criteria) this;
        }

        public Criteria andE039In(List<BigDecimal> values) {
            addCriterion("E039 in", values, "e039");
            return (Criteria) this;
        }

        public Criteria andE039NotIn(List<BigDecimal> values) {
            addCriterion("E039 not in", values, "e039");
            return (Criteria) this;
        }

        public Criteria andE039Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E039 between", value1, value2, "e039");
            return (Criteria) this;
        }

        public Criteria andE039NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E039 not between", value1, value2, "e039");
            return (Criteria) this;
        }

        public Criteria andE054IsNull() {
            addCriterion("E054 is null");
            return (Criteria) this;
        }

        public Criteria andE054IsNotNull() {
            addCriterion("E054 is not null");
            return (Criteria) this;
        }

        public Criteria andE054EqualTo(String value) {
            addCriterion("E054 =", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054NotEqualTo(String value) {
            addCriterion("E054 <>", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054GreaterThan(String value) {
            addCriterion("E054 >", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054GreaterThanOrEqualTo(String value) {
            addCriterion("E054 >=", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054LessThan(String value) {
            addCriterion("E054 <", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054LessThanOrEqualTo(String value) {
            addCriterion("E054 <=", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054Like(String value) {
            addCriterion("E054 like", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054NotLike(String value) {
            addCriterion("E054 not like", value, "e054");
            return (Criteria) this;
        }

        public Criteria andE054In(List<String> values) {
            addCriterion("E054 in", values, "e054");
            return (Criteria) this;
        }

        public Criteria andE054NotIn(List<String> values) {
            addCriterion("E054 not in", values, "e054");
            return (Criteria) this;
        }

        public Criteria andE054Between(String value1, String value2) {
            addCriterion("E054 between", value1, value2, "e054");
            return (Criteria) this;
        }

        public Criteria andE054NotBetween(String value1, String value2) {
            addCriterion("E054 not between", value1, value2, "e054");
            return (Criteria) this;
        }

        public Criteria andE056IsNull() {
            addCriterion("E056 is null");
            return (Criteria) this;
        }

        public Criteria andE056IsNotNull() {
            addCriterion("E056 is not null");
            return (Criteria) this;
        }

        public Criteria andE056EqualTo(BigDecimal value) {
            addCriterion("E056 =", value, "e056");
            return (Criteria) this;
        }

        public Criteria andE056NotEqualTo(BigDecimal value) {
            addCriterion("E056 <>", value, "e056");
            return (Criteria) this;
        }

        public Criteria andE056GreaterThan(BigDecimal value) {
            addCriterion("E056 >", value, "e056");
            return (Criteria) this;
        }

        public Criteria andE056GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E056 >=", value, "e056");
            return (Criteria) this;
        }

        public Criteria andE056LessThan(BigDecimal value) {
            addCriterion("E056 <", value, "e056");
            return (Criteria) this;
        }

        public Criteria andE056LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E056 <=", value, "e056");
            return (Criteria) this;
        }

        public Criteria andE056In(List<BigDecimal> values) {
            addCriterion("E056 in", values, "e056");
            return (Criteria) this;
        }

        public Criteria andE056NotIn(List<BigDecimal> values) {
            addCriterion("E056 not in", values, "e056");
            return (Criteria) this;
        }

        public Criteria andE056Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E056 between", value1, value2, "e056");
            return (Criteria) this;
        }

        public Criteria andE056NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E056 not between", value1, value2, "e056");
            return (Criteria) this;
        }

        public Criteria andE057IsNull() {
            addCriterion("E057 is null");
            return (Criteria) this;
        }

        public Criteria andE057IsNotNull() {
            addCriterion("E057 is not null");
            return (Criteria) this;
        }

        public Criteria andE057EqualTo(BigDecimal value) {
            addCriterion("E057 =", value, "e057");
            return (Criteria) this;
        }

        public Criteria andE057NotEqualTo(BigDecimal value) {
            addCriterion("E057 <>", value, "e057");
            return (Criteria) this;
        }

        public Criteria andE057GreaterThan(BigDecimal value) {
            addCriterion("E057 >", value, "e057");
            return (Criteria) this;
        }

        public Criteria andE057GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E057 >=", value, "e057");
            return (Criteria) this;
        }

        public Criteria andE057LessThan(BigDecimal value) {
            addCriterion("E057 <", value, "e057");
            return (Criteria) this;
        }

        public Criteria andE057LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E057 <=", value, "e057");
            return (Criteria) this;
        }

        public Criteria andE057In(List<BigDecimal> values) {
            addCriterion("E057 in", values, "e057");
            return (Criteria) this;
        }

        public Criteria andE057NotIn(List<BigDecimal> values) {
            addCriterion("E057 not in", values, "e057");
            return (Criteria) this;
        }

        public Criteria andE057Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E057 between", value1, value2, "e057");
            return (Criteria) this;
        }

        public Criteria andE057NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E057 not between", value1, value2, "e057");
            return (Criteria) this;
        }

        public Criteria andE058IsNull() {
            addCriterion("E058 is null");
            return (Criteria) this;
        }

        public Criteria andE058IsNotNull() {
            addCriterion("E058 is not null");
            return (Criteria) this;
        }

        public Criteria andE058EqualTo(BigDecimal value) {
            addCriterion("E058 =", value, "e058");
            return (Criteria) this;
        }

        public Criteria andE058NotEqualTo(BigDecimal value) {
            addCriterion("E058 <>", value, "e058");
            return (Criteria) this;
        }

        public Criteria andE058GreaterThan(BigDecimal value) {
            addCriterion("E058 >", value, "e058");
            return (Criteria) this;
        }

        public Criteria andE058GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E058 >=", value, "e058");
            return (Criteria) this;
        }

        public Criteria andE058LessThan(BigDecimal value) {
            addCriterion("E058 <", value, "e058");
            return (Criteria) this;
        }

        public Criteria andE058LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E058 <=", value, "e058");
            return (Criteria) this;
        }

        public Criteria andE058In(List<BigDecimal> values) {
            addCriterion("E058 in", values, "e058");
            return (Criteria) this;
        }

        public Criteria andE058NotIn(List<BigDecimal> values) {
            addCriterion("E058 not in", values, "e058");
            return (Criteria) this;
        }

        public Criteria andE058Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E058 between", value1, value2, "e058");
            return (Criteria) this;
        }

        public Criteria andE058NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E058 not between", value1, value2, "e058");
            return (Criteria) this;
        }

        public Criteria andE059IsNull() {
            addCriterion("E059 is null");
            return (Criteria) this;
        }

        public Criteria andE059IsNotNull() {
            addCriterion("E059 is not null");
            return (Criteria) this;
        }

        public Criteria andE059EqualTo(String value) {
            addCriterion("E059 =", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059NotEqualTo(String value) {
            addCriterion("E059 <>", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059GreaterThan(String value) {
            addCriterion("E059 >", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059GreaterThanOrEqualTo(String value) {
            addCriterion("E059 >=", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059LessThan(String value) {
            addCriterion("E059 <", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059LessThanOrEqualTo(String value) {
            addCriterion("E059 <=", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059Like(String value) {
            addCriterion("E059 like", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059NotLike(String value) {
            addCriterion("E059 not like", value, "e059");
            return (Criteria) this;
        }

        public Criteria andE059In(List<String> values) {
            addCriterion("E059 in", values, "e059");
            return (Criteria) this;
        }

        public Criteria andE059NotIn(List<String> values) {
            addCriterion("E059 not in", values, "e059");
            return (Criteria) this;
        }

        public Criteria andE059Between(String value1, String value2) {
            addCriterion("E059 between", value1, value2, "e059");
            return (Criteria) this;
        }

        public Criteria andE059NotBetween(String value1, String value2) {
            addCriterion("E059 not between", value1, value2, "e059");
            return (Criteria) this;
        }

        public Criteria andE060IsNull() {
            addCriterion("E060 is null");
            return (Criteria) this;
        }

        public Criteria andE060IsNotNull() {
            addCriterion("E060 is not null");
            return (Criteria) this;
        }

        public Criteria andE060EqualTo(BigDecimal value) {
            addCriterion("E060 =", value, "e060");
            return (Criteria) this;
        }

        public Criteria andE060NotEqualTo(BigDecimal value) {
            addCriterion("E060 <>", value, "e060");
            return (Criteria) this;
        }

        public Criteria andE060GreaterThan(BigDecimal value) {
            addCriterion("E060 >", value, "e060");
            return (Criteria) this;
        }

        public Criteria andE060GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E060 >=", value, "e060");
            return (Criteria) this;
        }

        public Criteria andE060LessThan(BigDecimal value) {
            addCriterion("E060 <", value, "e060");
            return (Criteria) this;
        }

        public Criteria andE060LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E060 <=", value, "e060");
            return (Criteria) this;
        }

        public Criteria andE060In(List<BigDecimal> values) {
            addCriterion("E060 in", values, "e060");
            return (Criteria) this;
        }

        public Criteria andE060NotIn(List<BigDecimal> values) {
            addCriterion("E060 not in", values, "e060");
            return (Criteria) this;
        }

        public Criteria andE060Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E060 between", value1, value2, "e060");
            return (Criteria) this;
        }

        public Criteria andE060NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E060 not between", value1, value2, "e060");
            return (Criteria) this;
        }

        public Criteria andE061IsNull() {
            addCriterion("E061 is null");
            return (Criteria) this;
        }

        public Criteria andE061IsNotNull() {
            addCriterion("E061 is not null");
            return (Criteria) this;
        }

        public Criteria andE061EqualTo(Date value) {
            addCriterionForJDBCDate("E061 =", value, "e061");
            return (Criteria) this;
        }

        public Criteria andE061NotEqualTo(Date value) {
            addCriterionForJDBCDate("E061 <>", value, "e061");
            return (Criteria) this;
        }

        public Criteria andE061GreaterThan(Date value) {
            addCriterionForJDBCDate("E061 >", value, "e061");
            return (Criteria) this;
        }

        public Criteria andE061GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E061 >=", value, "e061");
            return (Criteria) this;
        }

        public Criteria andE061LessThan(Date value) {
            addCriterionForJDBCDate("E061 <", value, "e061");
            return (Criteria) this;
        }

        public Criteria andE061LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E061 <=", value, "e061");
            return (Criteria) this;
        }

        public Criteria andE061In(List<Date> values) {
            addCriterionForJDBCDate("E061 in", values, "e061");
            return (Criteria) this;
        }

        public Criteria andE061NotIn(List<Date> values) {
            addCriterionForJDBCDate("E061 not in", values, "e061");
            return (Criteria) this;
        }

        public Criteria andE061Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E061 between", value1, value2, "e061");
            return (Criteria) this;
        }

        public Criteria andE061NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E061 not between", value1, value2, "e061");
            return (Criteria) this;
        }

        public Criteria andE063IsNull() {
            addCriterion("E063 is null");
            return (Criteria) this;
        }

        public Criteria andE063IsNotNull() {
            addCriterion("E063 is not null");
            return (Criteria) this;
        }

        public Criteria andE063EqualTo(Date value) {
            addCriterionForJDBCDate("E063 =", value, "e063");
            return (Criteria) this;
        }

        public Criteria andE063NotEqualTo(Date value) {
            addCriterionForJDBCDate("E063 <>", value, "e063");
            return (Criteria) this;
        }

        public Criteria andE063GreaterThan(Date value) {
            addCriterionForJDBCDate("E063 >", value, "e063");
            return (Criteria) this;
        }

        public Criteria andE063GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E063 >=", value, "e063");
            return (Criteria) this;
        }

        public Criteria andE063LessThan(Date value) {
            addCriterionForJDBCDate("E063 <", value, "e063");
            return (Criteria) this;
        }

        public Criteria andE063LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E063 <=", value, "e063");
            return (Criteria) this;
        }

        public Criteria andE063In(List<Date> values) {
            addCriterionForJDBCDate("E063 in", values, "e063");
            return (Criteria) this;
        }

        public Criteria andE063NotIn(List<Date> values) {
            addCriterionForJDBCDate("E063 not in", values, "e063");
            return (Criteria) this;
        }

        public Criteria andE063Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E063 between", value1, value2, "e063");
            return (Criteria) this;
        }

        public Criteria andE063NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E063 not between", value1, value2, "e063");
            return (Criteria) this;
        }

        public Criteria andE065IsNull() {
            addCriterion("E065 is null");
            return (Criteria) this;
        }

        public Criteria andE065IsNotNull() {
            addCriterion("E065 is not null");
            return (Criteria) this;
        }

        public Criteria andE065EqualTo(BigDecimal value) {
            addCriterion("E065 =", value, "e065");
            return (Criteria) this;
        }

        public Criteria andE065NotEqualTo(BigDecimal value) {
            addCriterion("E065 <>", value, "e065");
            return (Criteria) this;
        }

        public Criteria andE065GreaterThan(BigDecimal value) {
            addCriterion("E065 >", value, "e065");
            return (Criteria) this;
        }

        public Criteria andE065GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E065 >=", value, "e065");
            return (Criteria) this;
        }

        public Criteria andE065LessThan(BigDecimal value) {
            addCriterion("E065 <", value, "e065");
            return (Criteria) this;
        }

        public Criteria andE065LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E065 <=", value, "e065");
            return (Criteria) this;
        }

        public Criteria andE065In(List<BigDecimal> values) {
            addCriterion("E065 in", values, "e065");
            return (Criteria) this;
        }

        public Criteria andE065NotIn(List<BigDecimal> values) {
            addCriterion("E065 not in", values, "e065");
            return (Criteria) this;
        }

        public Criteria andE065Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E065 between", value1, value2, "e065");
            return (Criteria) this;
        }

        public Criteria andE065NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E065 not between", value1, value2, "e065");
            return (Criteria) this;
        }

        public Criteria andE066IsNull() {
            addCriterion("E066 is null");
            return (Criteria) this;
        }

        public Criteria andE066IsNotNull() {
            addCriterion("E066 is not null");
            return (Criteria) this;
        }

        public Criteria andE066EqualTo(BigDecimal value) {
            addCriterion("E066 =", value, "e066");
            return (Criteria) this;
        }

        public Criteria andE066NotEqualTo(BigDecimal value) {
            addCriterion("E066 <>", value, "e066");
            return (Criteria) this;
        }

        public Criteria andE066GreaterThan(BigDecimal value) {
            addCriterion("E066 >", value, "e066");
            return (Criteria) this;
        }

        public Criteria andE066GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E066 >=", value, "e066");
            return (Criteria) this;
        }

        public Criteria andE066LessThan(BigDecimal value) {
            addCriterion("E066 <", value, "e066");
            return (Criteria) this;
        }

        public Criteria andE066LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E066 <=", value, "e066");
            return (Criteria) this;
        }

        public Criteria andE066In(List<BigDecimal> values) {
            addCriterion("E066 in", values, "e066");
            return (Criteria) this;
        }

        public Criteria andE066NotIn(List<BigDecimal> values) {
            addCriterion("E066 not in", values, "e066");
            return (Criteria) this;
        }

        public Criteria andE066Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E066 between", value1, value2, "e066");
            return (Criteria) this;
        }

        public Criteria andE066NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E066 not between", value1, value2, "e066");
            return (Criteria) this;
        }

        public Criteria andE067IsNull() {
            addCriterion("E067 is null");
            return (Criteria) this;
        }

        public Criteria andE067IsNotNull() {
            addCriterion("E067 is not null");
            return (Criteria) this;
        }

        public Criteria andE067EqualTo(BigDecimal value) {
            addCriterion("E067 =", value, "e067");
            return (Criteria) this;
        }

        public Criteria andE067NotEqualTo(BigDecimal value) {
            addCriterion("E067 <>", value, "e067");
            return (Criteria) this;
        }

        public Criteria andE067GreaterThan(BigDecimal value) {
            addCriterion("E067 >", value, "e067");
            return (Criteria) this;
        }

        public Criteria andE067GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E067 >=", value, "e067");
            return (Criteria) this;
        }

        public Criteria andE067LessThan(BigDecimal value) {
            addCriterion("E067 <", value, "e067");
            return (Criteria) this;
        }

        public Criteria andE067LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E067 <=", value, "e067");
            return (Criteria) this;
        }

        public Criteria andE067In(List<BigDecimal> values) {
            addCriterion("E067 in", values, "e067");
            return (Criteria) this;
        }

        public Criteria andE067NotIn(List<BigDecimal> values) {
            addCriterion("E067 not in", values, "e067");
            return (Criteria) this;
        }

        public Criteria andE067Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E067 between", value1, value2, "e067");
            return (Criteria) this;
        }

        public Criteria andE067NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E067 not between", value1, value2, "e067");
            return (Criteria) this;
        }

        public Criteria andE070IsNull() {
            addCriterion("E070 is null");
            return (Criteria) this;
        }

        public Criteria andE070IsNotNull() {
            addCriterion("E070 is not null");
            return (Criteria) this;
        }

        public Criteria andE070EqualTo(Date value) {
            addCriterionForJDBCDate("E070 =", value, "e070");
            return (Criteria) this;
        }

        public Criteria andE070NotEqualTo(Date value) {
            addCriterionForJDBCDate("E070 <>", value, "e070");
            return (Criteria) this;
        }

        public Criteria andE070GreaterThan(Date value) {
            addCriterionForJDBCDate("E070 >", value, "e070");
            return (Criteria) this;
        }

        public Criteria andE070GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E070 >=", value, "e070");
            return (Criteria) this;
        }

        public Criteria andE070LessThan(Date value) {
            addCriterionForJDBCDate("E070 <", value, "e070");
            return (Criteria) this;
        }

        public Criteria andE070LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E070 <=", value, "e070");
            return (Criteria) this;
        }

        public Criteria andE070In(List<Date> values) {
            addCriterionForJDBCDate("E070 in", values, "e070");
            return (Criteria) this;
        }

        public Criteria andE070NotIn(List<Date> values) {
            addCriterionForJDBCDate("E070 not in", values, "e070");
            return (Criteria) this;
        }

        public Criteria andE070Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E070 between", value1, value2, "e070");
            return (Criteria) this;
        }

        public Criteria andE070NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E070 not between", value1, value2, "e070");
            return (Criteria) this;
        }

        public Criteria andE071IsNull() {
            addCriterion("E071 is null");
            return (Criteria) this;
        }

        public Criteria andE071IsNotNull() {
            addCriterion("E071 is not null");
            return (Criteria) this;
        }

        public Criteria andE071EqualTo(BigDecimal value) {
            addCriterion("E071 =", value, "e071");
            return (Criteria) this;
        }

        public Criteria andE071NotEqualTo(BigDecimal value) {
            addCriterion("E071 <>", value, "e071");
            return (Criteria) this;
        }

        public Criteria andE071GreaterThan(BigDecimal value) {
            addCriterion("E071 >", value, "e071");
            return (Criteria) this;
        }

        public Criteria andE071GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E071 >=", value, "e071");
            return (Criteria) this;
        }

        public Criteria andE071LessThan(BigDecimal value) {
            addCriterion("E071 <", value, "e071");
            return (Criteria) this;
        }

        public Criteria andE071LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E071 <=", value, "e071");
            return (Criteria) this;
        }

        public Criteria andE071In(List<BigDecimal> values) {
            addCriterion("E071 in", values, "e071");
            return (Criteria) this;
        }

        public Criteria andE071NotIn(List<BigDecimal> values) {
            addCriterion("E071 not in", values, "e071");
            return (Criteria) this;
        }

        public Criteria andE071Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E071 between", value1, value2, "e071");
            return (Criteria) this;
        }

        public Criteria andE071NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E071 not between", value1, value2, "e071");
            return (Criteria) this;
        }

        public Criteria andE072IsNull() {
            addCriterion("E072 is null");
            return (Criteria) this;
        }

        public Criteria andE072IsNotNull() {
            addCriterion("E072 is not null");
            return (Criteria) this;
        }

        public Criteria andE072EqualTo(BigDecimal value) {
            addCriterion("E072 =", value, "e072");
            return (Criteria) this;
        }

        public Criteria andE072NotEqualTo(BigDecimal value) {
            addCriterion("E072 <>", value, "e072");
            return (Criteria) this;
        }

        public Criteria andE072GreaterThan(BigDecimal value) {
            addCriterion("E072 >", value, "e072");
            return (Criteria) this;
        }

        public Criteria andE072GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E072 >=", value, "e072");
            return (Criteria) this;
        }

        public Criteria andE072LessThan(BigDecimal value) {
            addCriterion("E072 <", value, "e072");
            return (Criteria) this;
        }

        public Criteria andE072LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E072 <=", value, "e072");
            return (Criteria) this;
        }

        public Criteria andE072In(List<BigDecimal> values) {
            addCriterion("E072 in", values, "e072");
            return (Criteria) this;
        }

        public Criteria andE072NotIn(List<BigDecimal> values) {
            addCriterion("E072 not in", values, "e072");
            return (Criteria) this;
        }

        public Criteria andE072Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E072 between", value1, value2, "e072");
            return (Criteria) this;
        }

        public Criteria andE072NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E072 not between", value1, value2, "e072");
            return (Criteria) this;
        }

        public Criteria andE073IsNull() {
            addCriterion("E073 is null");
            return (Criteria) this;
        }

        public Criteria andE073IsNotNull() {
            addCriterion("E073 is not null");
            return (Criteria) this;
        }

        public Criteria andE073EqualTo(BigDecimal value) {
            addCriterion("E073 =", value, "e073");
            return (Criteria) this;
        }

        public Criteria andE073NotEqualTo(BigDecimal value) {
            addCriterion("E073 <>", value, "e073");
            return (Criteria) this;
        }

        public Criteria andE073GreaterThan(BigDecimal value) {
            addCriterion("E073 >", value, "e073");
            return (Criteria) this;
        }

        public Criteria andE073GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E073 >=", value, "e073");
            return (Criteria) this;
        }

        public Criteria andE073LessThan(BigDecimal value) {
            addCriterion("E073 <", value, "e073");
            return (Criteria) this;
        }

        public Criteria andE073LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E073 <=", value, "e073");
            return (Criteria) this;
        }

        public Criteria andE073In(List<BigDecimal> values) {
            addCriterion("E073 in", values, "e073");
            return (Criteria) this;
        }

        public Criteria andE073NotIn(List<BigDecimal> values) {
            addCriterion("E073 not in", values, "e073");
            return (Criteria) this;
        }

        public Criteria andE073Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E073 between", value1, value2, "e073");
            return (Criteria) this;
        }

        public Criteria andE073NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E073 not between", value1, value2, "e073");
            return (Criteria) this;
        }

        public Criteria andE074IsNull() {
            addCriterion("E074 is null");
            return (Criteria) this;
        }

        public Criteria andE074IsNotNull() {
            addCriterion("E074 is not null");
            return (Criteria) this;
        }

        public Criteria andE074EqualTo(Date value) {
            addCriterionForJDBCDate("E074 =", value, "e074");
            return (Criteria) this;
        }

        public Criteria andE074NotEqualTo(Date value) {
            addCriterionForJDBCDate("E074 <>", value, "e074");
            return (Criteria) this;
        }

        public Criteria andE074GreaterThan(Date value) {
            addCriterionForJDBCDate("E074 >", value, "e074");
            return (Criteria) this;
        }

        public Criteria andE074GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E074 >=", value, "e074");
            return (Criteria) this;
        }

        public Criteria andE074LessThan(Date value) {
            addCriterionForJDBCDate("E074 <", value, "e074");
            return (Criteria) this;
        }

        public Criteria andE074LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E074 <=", value, "e074");
            return (Criteria) this;
        }

        public Criteria andE074In(List<Date> values) {
            addCriterionForJDBCDate("E074 in", values, "e074");
            return (Criteria) this;
        }

        public Criteria andE074NotIn(List<Date> values) {
            addCriterionForJDBCDate("E074 not in", values, "e074");
            return (Criteria) this;
        }

        public Criteria andE074Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E074 between", value1, value2, "e074");
            return (Criteria) this;
        }

        public Criteria andE074NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E074 not between", value1, value2, "e074");
            return (Criteria) this;
        }

        public Criteria andE079IsNull() {
            addCriterion("E079 is null");
            return (Criteria) this;
        }

        public Criteria andE079IsNotNull() {
            addCriterion("E079 is not null");
            return (Criteria) this;
        }

        public Criteria andE079EqualTo(BigDecimal value) {
            addCriterion("E079 =", value, "e079");
            return (Criteria) this;
        }

        public Criteria andE079NotEqualTo(BigDecimal value) {
            addCriterion("E079 <>", value, "e079");
            return (Criteria) this;
        }

        public Criteria andE079GreaterThan(BigDecimal value) {
            addCriterion("E079 >", value, "e079");
            return (Criteria) this;
        }

        public Criteria andE079GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E079 >=", value, "e079");
            return (Criteria) this;
        }

        public Criteria andE079LessThan(BigDecimal value) {
            addCriterion("E079 <", value, "e079");
            return (Criteria) this;
        }

        public Criteria andE079LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E079 <=", value, "e079");
            return (Criteria) this;
        }

        public Criteria andE079In(List<BigDecimal> values) {
            addCriterion("E079 in", values, "e079");
            return (Criteria) this;
        }

        public Criteria andE079NotIn(List<BigDecimal> values) {
            addCriterion("E079 not in", values, "e079");
            return (Criteria) this;
        }

        public Criteria andE079Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E079 between", value1, value2, "e079");
            return (Criteria) this;
        }

        public Criteria andE079NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E079 not between", value1, value2, "e079");
            return (Criteria) this;
        }

        public Criteria andE080IsNull() {
            addCriterion("E080 is null");
            return (Criteria) this;
        }

        public Criteria andE080IsNotNull() {
            addCriterion("E080 is not null");
            return (Criteria) this;
        }

        public Criteria andE080EqualTo(BigDecimal value) {
            addCriterion("E080 =", value, "e080");
            return (Criteria) this;
        }

        public Criteria andE080NotEqualTo(BigDecimal value) {
            addCriterion("E080 <>", value, "e080");
            return (Criteria) this;
        }

        public Criteria andE080GreaterThan(BigDecimal value) {
            addCriterion("E080 >", value, "e080");
            return (Criteria) this;
        }

        public Criteria andE080GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E080 >=", value, "e080");
            return (Criteria) this;
        }

        public Criteria andE080LessThan(BigDecimal value) {
            addCriterion("E080 <", value, "e080");
            return (Criteria) this;
        }

        public Criteria andE080LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E080 <=", value, "e080");
            return (Criteria) this;
        }

        public Criteria andE080In(List<BigDecimal> values) {
            addCriterion("E080 in", values, "e080");
            return (Criteria) this;
        }

        public Criteria andE080NotIn(List<BigDecimal> values) {
            addCriterion("E080 not in", values, "e080");
            return (Criteria) this;
        }

        public Criteria andE080Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E080 between", value1, value2, "e080");
            return (Criteria) this;
        }

        public Criteria andE080NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E080 not between", value1, value2, "e080");
            return (Criteria) this;
        }

        public Criteria andE095IsNull() {
            addCriterion("E095 is null");
            return (Criteria) this;
        }

        public Criteria andE095IsNotNull() {
            addCriterion("E095 is not null");
            return (Criteria) this;
        }

        public Criteria andE095EqualTo(BigDecimal value) {
            addCriterion("E095 =", value, "e095");
            return (Criteria) this;
        }

        public Criteria andE095NotEqualTo(BigDecimal value) {
            addCriterion("E095 <>", value, "e095");
            return (Criteria) this;
        }

        public Criteria andE095GreaterThan(BigDecimal value) {
            addCriterion("E095 >", value, "e095");
            return (Criteria) this;
        }

        public Criteria andE095GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E095 >=", value, "e095");
            return (Criteria) this;
        }

        public Criteria andE095LessThan(BigDecimal value) {
            addCriterion("E095 <", value, "e095");
            return (Criteria) this;
        }

        public Criteria andE095LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E095 <=", value, "e095");
            return (Criteria) this;
        }

        public Criteria andE095In(List<BigDecimal> values) {
            addCriterion("E095 in", values, "e095");
            return (Criteria) this;
        }

        public Criteria andE095NotIn(List<BigDecimal> values) {
            addCriterion("E095 not in", values, "e095");
            return (Criteria) this;
        }

        public Criteria andE095Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E095 between", value1, value2, "e095");
            return (Criteria) this;
        }

        public Criteria andE095NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E095 not between", value1, value2, "e095");
            return (Criteria) this;
        }

        public Criteria andE096IsNull() {
            addCriterion("E096 is null");
            return (Criteria) this;
        }

        public Criteria andE096IsNotNull() {
            addCriterion("E096 is not null");
            return (Criteria) this;
        }

        public Criteria andE096EqualTo(BigDecimal value) {
            addCriterion("E096 =", value, "e096");
            return (Criteria) this;
        }

        public Criteria andE096NotEqualTo(BigDecimal value) {
            addCriterion("E096 <>", value, "e096");
            return (Criteria) this;
        }

        public Criteria andE096GreaterThan(BigDecimal value) {
            addCriterion("E096 >", value, "e096");
            return (Criteria) this;
        }

        public Criteria andE096GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E096 >=", value, "e096");
            return (Criteria) this;
        }

        public Criteria andE096LessThan(BigDecimal value) {
            addCriterion("E096 <", value, "e096");
            return (Criteria) this;
        }

        public Criteria andE096LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E096 <=", value, "e096");
            return (Criteria) this;
        }

        public Criteria andE096In(List<BigDecimal> values) {
            addCriterion("E096 in", values, "e096");
            return (Criteria) this;
        }

        public Criteria andE096NotIn(List<BigDecimal> values) {
            addCriterion("E096 not in", values, "e096");
            return (Criteria) this;
        }

        public Criteria andE096Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E096 between", value1, value2, "e096");
            return (Criteria) this;
        }

        public Criteria andE096NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E096 not between", value1, value2, "e096");
            return (Criteria) this;
        }

        public Criteria andE097IsNull() {
            addCriterion("E097 is null");
            return (Criteria) this;
        }

        public Criteria andE097IsNotNull() {
            addCriterion("E097 is not null");
            return (Criteria) this;
        }

        public Criteria andE097EqualTo(BigDecimal value) {
            addCriterion("E097 =", value, "e097");
            return (Criteria) this;
        }

        public Criteria andE097NotEqualTo(BigDecimal value) {
            addCriterion("E097 <>", value, "e097");
            return (Criteria) this;
        }

        public Criteria andE097GreaterThan(BigDecimal value) {
            addCriterion("E097 >", value, "e097");
            return (Criteria) this;
        }

        public Criteria andE097GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E097 >=", value, "e097");
            return (Criteria) this;
        }

        public Criteria andE097LessThan(BigDecimal value) {
            addCriterion("E097 <", value, "e097");
            return (Criteria) this;
        }

        public Criteria andE097LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E097 <=", value, "e097");
            return (Criteria) this;
        }

        public Criteria andE097In(List<BigDecimal> values) {
            addCriterion("E097 in", values, "e097");
            return (Criteria) this;
        }

        public Criteria andE097NotIn(List<BigDecimal> values) {
            addCriterion("E097 not in", values, "e097");
            return (Criteria) this;
        }

        public Criteria andE097Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E097 between", value1, value2, "e097");
            return (Criteria) this;
        }

        public Criteria andE097NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E097 not between", value1, value2, "e097");
            return (Criteria) this;
        }

        public Criteria andE098IsNull() {
            addCriterion("E098 is null");
            return (Criteria) this;
        }

        public Criteria andE098IsNotNull() {
            addCriterion("E098 is not null");
            return (Criteria) this;
        }

        public Criteria andE098EqualTo(BigDecimal value) {
            addCriterion("E098 =", value, "e098");
            return (Criteria) this;
        }

        public Criteria andE098NotEqualTo(BigDecimal value) {
            addCriterion("E098 <>", value, "e098");
            return (Criteria) this;
        }

        public Criteria andE098GreaterThan(BigDecimal value) {
            addCriterion("E098 >", value, "e098");
            return (Criteria) this;
        }

        public Criteria andE098GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E098 >=", value, "e098");
            return (Criteria) this;
        }

        public Criteria andE098LessThan(BigDecimal value) {
            addCriterion("E098 <", value, "e098");
            return (Criteria) this;
        }

        public Criteria andE098LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E098 <=", value, "e098");
            return (Criteria) this;
        }

        public Criteria andE098In(List<BigDecimal> values) {
            addCriterion("E098 in", values, "e098");
            return (Criteria) this;
        }

        public Criteria andE098NotIn(List<BigDecimal> values) {
            addCriterion("E098 not in", values, "e098");
            return (Criteria) this;
        }

        public Criteria andE098Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E098 between", value1, value2, "e098");
            return (Criteria) this;
        }

        public Criteria andE098NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E098 not between", value1, value2, "e098");
            return (Criteria) this;
        }

        public Criteria andE109IsNull() {
            addCriterion("E109 is null");
            return (Criteria) this;
        }

        public Criteria andE109IsNotNull() {
            addCriterion("E109 is not null");
            return (Criteria) this;
        }

        public Criteria andE109EqualTo(Date value) {
            addCriterionForJDBCDate("E109 =", value, "e109");
            return (Criteria) this;
        }

        public Criteria andE109NotEqualTo(Date value) {
            addCriterionForJDBCDate("E109 <>", value, "e109");
            return (Criteria) this;
        }

        public Criteria andE109GreaterThan(Date value) {
            addCriterionForJDBCDate("E109 >", value, "e109");
            return (Criteria) this;
        }

        public Criteria andE109GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E109 >=", value, "e109");
            return (Criteria) this;
        }

        public Criteria andE109LessThan(Date value) {
            addCriterionForJDBCDate("E109 <", value, "e109");
            return (Criteria) this;
        }

        public Criteria andE109LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E109 <=", value, "e109");
            return (Criteria) this;
        }

        public Criteria andE109In(List<Date> values) {
            addCriterionForJDBCDate("E109 in", values, "e109");
            return (Criteria) this;
        }

        public Criteria andE109NotIn(List<Date> values) {
            addCriterionForJDBCDate("E109 not in", values, "e109");
            return (Criteria) this;
        }

        public Criteria andE109Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E109 between", value1, value2, "e109");
            return (Criteria) this;
        }

        public Criteria andE109NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E109 not between", value1, value2, "e109");
            return (Criteria) this;
        }

        public Criteria andE093IsNull() {
            addCriterion("E093 is null");
            return (Criteria) this;
        }

        public Criteria andE093IsNotNull() {
            addCriterion("E093 is not null");
            return (Criteria) this;
        }

        public Criteria andE093EqualTo(String value) {
            addCriterion("E093 =", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093NotEqualTo(String value) {
            addCriterion("E093 <>", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093GreaterThan(String value) {
            addCriterion("E093 >", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093GreaterThanOrEqualTo(String value) {
            addCriterion("E093 >=", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093LessThan(String value) {
            addCriterion("E093 <", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093LessThanOrEqualTo(String value) {
            addCriterion("E093 <=", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093Like(String value) {
            addCriterion("E093 like", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093NotLike(String value) {
            addCriterion("E093 not like", value, "e093");
            return (Criteria) this;
        }

        public Criteria andE093In(List<String> values) {
            addCriterion("E093 in", values, "e093");
            return (Criteria) this;
        }

        public Criteria andE093NotIn(List<String> values) {
            addCriterion("E093 not in", values, "e093");
            return (Criteria) this;
        }

        public Criteria andE093Between(String value1, String value2) {
            addCriterion("E093 between", value1, value2, "e093");
            return (Criteria) this;
        }

        public Criteria andE093NotBetween(String value1, String value2) {
            addCriterion("E093 not between", value1, value2, "e093");
            return (Criteria) this;
        }

        public Criteria andE110IsNull() {
            addCriterion("E110 is null");
            return (Criteria) this;
        }

        public Criteria andE110IsNotNull() {
            addCriterion("E110 is not null");
            return (Criteria) this;
        }

        public Criteria andE110EqualTo(BigDecimal value) {
            addCriterion("E110 =", value, "e110");
            return (Criteria) this;
        }

        public Criteria andE110NotEqualTo(BigDecimal value) {
            addCriterion("E110 <>", value, "e110");
            return (Criteria) this;
        }

        public Criteria andE110GreaterThan(BigDecimal value) {
            addCriterion("E110 >", value, "e110");
            return (Criteria) this;
        }

        public Criteria andE110GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E110 >=", value, "e110");
            return (Criteria) this;
        }

        public Criteria andE110LessThan(BigDecimal value) {
            addCriterion("E110 <", value, "e110");
            return (Criteria) this;
        }

        public Criteria andE110LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E110 <=", value, "e110");
            return (Criteria) this;
        }

        public Criteria andE110In(List<BigDecimal> values) {
            addCriterion("E110 in", values, "e110");
            return (Criteria) this;
        }

        public Criteria andE110NotIn(List<BigDecimal> values) {
            addCriterion("E110 not in", values, "e110");
            return (Criteria) this;
        }

        public Criteria andE110Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E110 between", value1, value2, "e110");
            return (Criteria) this;
        }

        public Criteria andE110NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E110 not between", value1, value2, "e110");
            return (Criteria) this;
        }

        public Criteria andE111IsNull() {
            addCriterion("E111 is null");
            return (Criteria) this;
        }

        public Criteria andE111IsNotNull() {
            addCriterion("E111 is not null");
            return (Criteria) this;
        }

        public Criteria andE111EqualTo(BigDecimal value) {
            addCriterion("E111 =", value, "e111");
            return (Criteria) this;
        }

        public Criteria andE111NotEqualTo(BigDecimal value) {
            addCriterion("E111 <>", value, "e111");
            return (Criteria) this;
        }

        public Criteria andE111GreaterThan(BigDecimal value) {
            addCriterion("E111 >", value, "e111");
            return (Criteria) this;
        }

        public Criteria andE111GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E111 >=", value, "e111");
            return (Criteria) this;
        }

        public Criteria andE111LessThan(BigDecimal value) {
            addCriterion("E111 <", value, "e111");
            return (Criteria) this;
        }

        public Criteria andE111LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E111 <=", value, "e111");
            return (Criteria) this;
        }

        public Criteria andE111In(List<BigDecimal> values) {
            addCriterion("E111 in", values, "e111");
            return (Criteria) this;
        }

        public Criteria andE111NotIn(List<BigDecimal> values) {
            addCriterion("E111 not in", values, "e111");
            return (Criteria) this;
        }

        public Criteria andE111Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E111 between", value1, value2, "e111");
            return (Criteria) this;
        }

        public Criteria andE111NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E111 not between", value1, value2, "e111");
            return (Criteria) this;
        }

        public Criteria andE184IsNull() {
            addCriterion("E184 is null");
            return (Criteria) this;
        }

        public Criteria andE184IsNotNull() {
            addCriterion("E184 is not null");
            return (Criteria) this;
        }

        public Criteria andE184EqualTo(BigDecimal value) {
            addCriterion("E184 =", value, "e184");
            return (Criteria) this;
        }

        public Criteria andE184NotEqualTo(BigDecimal value) {
            addCriterion("E184 <>", value, "e184");
            return (Criteria) this;
        }

        public Criteria andE184GreaterThan(BigDecimal value) {
            addCriterion("E184 >", value, "e184");
            return (Criteria) this;
        }

        public Criteria andE184GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E184 >=", value, "e184");
            return (Criteria) this;
        }

        public Criteria andE184LessThan(BigDecimal value) {
            addCriterion("E184 <", value, "e184");
            return (Criteria) this;
        }

        public Criteria andE184LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E184 <=", value, "e184");
            return (Criteria) this;
        }

        public Criteria andE184In(List<BigDecimal> values) {
            addCriterion("E184 in", values, "e184");
            return (Criteria) this;
        }

        public Criteria andE184NotIn(List<BigDecimal> values) {
            addCriterion("E184 not in", values, "e184");
            return (Criteria) this;
        }

        public Criteria andE184Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E184 between", value1, value2, "e184");
            return (Criteria) this;
        }

        public Criteria andE184NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E184 not between", value1, value2, "e184");
            return (Criteria) this;
        }

        public Criteria andE185IsNull() {
            addCriterion("E185 is null");
            return (Criteria) this;
        }

        public Criteria andE185IsNotNull() {
            addCriterion("E185 is not null");
            return (Criteria) this;
        }

        public Criteria andE185EqualTo(Date value) {
            addCriterionForJDBCDate("E185 =", value, "e185");
            return (Criteria) this;
        }

        public Criteria andE185NotEqualTo(Date value) {
            addCriterionForJDBCDate("E185 <>", value, "e185");
            return (Criteria) this;
        }

        public Criteria andE185GreaterThan(Date value) {
            addCriterionForJDBCDate("E185 >", value, "e185");
            return (Criteria) this;
        }

        public Criteria andE185GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E185 >=", value, "e185");
            return (Criteria) this;
        }

        public Criteria andE185LessThan(Date value) {
            addCriterionForJDBCDate("E185 <", value, "e185");
            return (Criteria) this;
        }

        public Criteria andE185LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E185 <=", value, "e185");
            return (Criteria) this;
        }

        public Criteria andE185In(List<Date> values) {
            addCriterionForJDBCDate("E185 in", values, "e185");
            return (Criteria) this;
        }

        public Criteria andE185NotIn(List<Date> values) {
            addCriterionForJDBCDate("E185 not in", values, "e185");
            return (Criteria) this;
        }

        public Criteria andE185Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E185 between", value1, value2, "e185");
            return (Criteria) this;
        }

        public Criteria andE185NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E185 not between", value1, value2, "e185");
            return (Criteria) this;
        }

        public Criteria andE183IsNull() {
            addCriterion("E183 is null");
            return (Criteria) this;
        }

        public Criteria andE183IsNotNull() {
            addCriterion("E183 is not null");
            return (Criteria) this;
        }

        public Criteria andE183EqualTo(String value) {
            addCriterion("E183 =", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183NotEqualTo(String value) {
            addCriterion("E183 <>", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183GreaterThan(String value) {
            addCriterion("E183 >", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183GreaterThanOrEqualTo(String value) {
            addCriterion("E183 >=", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183LessThan(String value) {
            addCriterion("E183 <", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183LessThanOrEqualTo(String value) {
            addCriterion("E183 <=", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183Like(String value) {
            addCriterion("E183 like", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183NotLike(String value) {
            addCriterion("E183 not like", value, "e183");
            return (Criteria) this;
        }

        public Criteria andE183In(List<String> values) {
            addCriterion("E183 in", values, "e183");
            return (Criteria) this;
        }

        public Criteria andE183NotIn(List<String> values) {
            addCriterion("E183 not in", values, "e183");
            return (Criteria) this;
        }

        public Criteria andE183Between(String value1, String value2) {
            addCriterion("E183 between", value1, value2, "e183");
            return (Criteria) this;
        }

        public Criteria andE183NotBetween(String value1, String value2) {
            addCriterion("E183 not between", value1, value2, "e183");
            return (Criteria) this;
        }

        public Criteria andE541IsNull() {
            addCriterion("E541 is null");
            return (Criteria) this;
        }

        public Criteria andE541IsNotNull() {
            addCriterion("E541 is not null");
            return (Criteria) this;
        }

        public Criteria andE541EqualTo(BigDecimal value) {
            addCriterion("E541 =", value, "e541");
            return (Criteria) this;
        }

        public Criteria andE541NotEqualTo(BigDecimal value) {
            addCriterion("E541 <>", value, "e541");
            return (Criteria) this;
        }

        public Criteria andE541GreaterThan(BigDecimal value) {
            addCriterion("E541 >", value, "e541");
            return (Criteria) this;
        }

        public Criteria andE541GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E541 >=", value, "e541");
            return (Criteria) this;
        }

        public Criteria andE541LessThan(BigDecimal value) {
            addCriterion("E541 <", value, "e541");
            return (Criteria) this;
        }

        public Criteria andE541LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E541 <=", value, "e541");
            return (Criteria) this;
        }

        public Criteria andE541In(List<BigDecimal> values) {
            addCriterion("E541 in", values, "e541");
            return (Criteria) this;
        }

        public Criteria andE541NotIn(List<BigDecimal> values) {
            addCriterion("E541 not in", values, "e541");
            return (Criteria) this;
        }

        public Criteria andE541Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E541 between", value1, value2, "e541");
            return (Criteria) this;
        }

        public Criteria andE541NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E541 not between", value1, value2, "e541");
            return (Criteria) this;
        }

        public Criteria andE539IsNull() {
            addCriterion("E539 is null");
            return (Criteria) this;
        }

        public Criteria andE539IsNotNull() {
            addCriterion("E539 is not null");
            return (Criteria) this;
        }

        public Criteria andE539EqualTo(BigDecimal value) {
            addCriterion("E539 =", value, "e539");
            return (Criteria) this;
        }

        public Criteria andE539NotEqualTo(BigDecimal value) {
            addCriterion("E539 <>", value, "e539");
            return (Criteria) this;
        }

        public Criteria andE539GreaterThan(BigDecimal value) {
            addCriterion("E539 >", value, "e539");
            return (Criteria) this;
        }

        public Criteria andE539GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E539 >=", value, "e539");
            return (Criteria) this;
        }

        public Criteria andE539LessThan(BigDecimal value) {
            addCriterion("E539 <", value, "e539");
            return (Criteria) this;
        }

        public Criteria andE539LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E539 <=", value, "e539");
            return (Criteria) this;
        }

        public Criteria andE539In(List<BigDecimal> values) {
            addCriterion("E539 in", values, "e539");
            return (Criteria) this;
        }

        public Criteria andE539NotIn(List<BigDecimal> values) {
            addCriterion("E539 not in", values, "e539");
            return (Criteria) this;
        }

        public Criteria andE539Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E539 between", value1, value2, "e539");
            return (Criteria) this;
        }

        public Criteria andE539NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E539 not between", value1, value2, "e539");
            return (Criteria) this;
        }

        public Criteria andE540IsNull() {
            addCriterion("E540 is null");
            return (Criteria) this;
        }

        public Criteria andE540IsNotNull() {
            addCriterion("E540 is not null");
            return (Criteria) this;
        }

        public Criteria andE540EqualTo(BigDecimal value) {
            addCriterion("E540 =", value, "e540");
            return (Criteria) this;
        }

        public Criteria andE540NotEqualTo(BigDecimal value) {
            addCriterion("E540 <>", value, "e540");
            return (Criteria) this;
        }

        public Criteria andE540GreaterThan(BigDecimal value) {
            addCriterion("E540 >", value, "e540");
            return (Criteria) this;
        }

        public Criteria andE540GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E540 >=", value, "e540");
            return (Criteria) this;
        }

        public Criteria andE540LessThan(BigDecimal value) {
            addCriterion("E540 <", value, "e540");
            return (Criteria) this;
        }

        public Criteria andE540LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E540 <=", value, "e540");
            return (Criteria) this;
        }

        public Criteria andE540In(List<BigDecimal> values) {
            addCriterion("E540 in", values, "e540");
            return (Criteria) this;
        }

        public Criteria andE540NotIn(List<BigDecimal> values) {
            addCriterion("E540 not in", values, "e540");
            return (Criteria) this;
        }

        public Criteria andE540Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E540 between", value1, value2, "e540");
            return (Criteria) this;
        }

        public Criteria andE540NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E540 not between", value1, value2, "e540");
            return (Criteria) this;
        }

        public Criteria andE284IsNull() {
            addCriterion("E284 is null");
            return (Criteria) this;
        }

        public Criteria andE284IsNotNull() {
            addCriterion("E284 is not null");
            return (Criteria) this;
        }

        public Criteria andE284EqualTo(BigDecimal value) {
            addCriterion("E284 =", value, "e284");
            return (Criteria) this;
        }

        public Criteria andE284NotEqualTo(BigDecimal value) {
            addCriterion("E284 <>", value, "e284");
            return (Criteria) this;
        }

        public Criteria andE284GreaterThan(BigDecimal value) {
            addCriterion("E284 >", value, "e284");
            return (Criteria) this;
        }

        public Criteria andE284GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E284 >=", value, "e284");
            return (Criteria) this;
        }

        public Criteria andE284LessThan(BigDecimal value) {
            addCriterion("E284 <", value, "e284");
            return (Criteria) this;
        }

        public Criteria andE284LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E284 <=", value, "e284");
            return (Criteria) this;
        }

        public Criteria andE284In(List<BigDecimal> values) {
            addCriterion("E284 in", values, "e284");
            return (Criteria) this;
        }

        public Criteria andE284NotIn(List<BigDecimal> values) {
            addCriterion("E284 not in", values, "e284");
            return (Criteria) this;
        }

        public Criteria andE284Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E284 between", value1, value2, "e284");
            return (Criteria) this;
        }

        public Criteria andE284NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E284 not between", value1, value2, "e284");
            return (Criteria) this;
        }

        public Criteria andE285IsNull() {
            addCriterion("E285 is null");
            return (Criteria) this;
        }

        public Criteria andE285IsNotNull() {
            addCriterion("E285 is not null");
            return (Criteria) this;
        }

        public Criteria andE285EqualTo(Date value) {
            addCriterionForJDBCDate("E285 =", value, "e285");
            return (Criteria) this;
        }

        public Criteria andE285NotEqualTo(Date value) {
            addCriterionForJDBCDate("E285 <>", value, "e285");
            return (Criteria) this;
        }

        public Criteria andE285GreaterThan(Date value) {
            addCriterionForJDBCDate("E285 >", value, "e285");
            return (Criteria) this;
        }

        public Criteria andE285GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E285 >=", value, "e285");
            return (Criteria) this;
        }

        public Criteria andE285LessThan(Date value) {
            addCriterionForJDBCDate("E285 <", value, "e285");
            return (Criteria) this;
        }

        public Criteria andE285LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E285 <=", value, "e285");
            return (Criteria) this;
        }

        public Criteria andE285In(List<Date> values) {
            addCriterionForJDBCDate("E285 in", values, "e285");
            return (Criteria) this;
        }

        public Criteria andE285NotIn(List<Date> values) {
            addCriterionForJDBCDate("E285 not in", values, "e285");
            return (Criteria) this;
        }

        public Criteria andE285Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E285 between", value1, value2, "e285");
            return (Criteria) this;
        }

        public Criteria andE285NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E285 not between", value1, value2, "e285");
            return (Criteria) this;
        }

        public Criteria andE290IsNull() {
            addCriterion("E290 is null");
            return (Criteria) this;
        }

        public Criteria andE290IsNotNull() {
            addCriterion("E290 is not null");
            return (Criteria) this;
        }

        public Criteria andE290EqualTo(Date value) {
            addCriterionForJDBCDate("E290 =", value, "e290");
            return (Criteria) this;
        }

        public Criteria andE290NotEqualTo(Date value) {
            addCriterionForJDBCDate("E290 <>", value, "e290");
            return (Criteria) this;
        }

        public Criteria andE290GreaterThan(Date value) {
            addCriterionForJDBCDate("E290 >", value, "e290");
            return (Criteria) this;
        }

        public Criteria andE290GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E290 >=", value, "e290");
            return (Criteria) this;
        }

        public Criteria andE290LessThan(Date value) {
            addCriterionForJDBCDate("E290 <", value, "e290");
            return (Criteria) this;
        }

        public Criteria andE290LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("E290 <=", value, "e290");
            return (Criteria) this;
        }

        public Criteria andE290In(List<Date> values) {
            addCriterionForJDBCDate("E290 in", values, "e290");
            return (Criteria) this;
        }

        public Criteria andE290NotIn(List<Date> values) {
            addCriterionForJDBCDate("E290 not in", values, "e290");
            return (Criteria) this;
        }

        public Criteria andE290Between(Date value1, Date value2) {
            addCriterionForJDBCDate("E290 between", value1, value2, "e290");
            return (Criteria) this;
        }

        public Criteria andE290NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("E290 not between", value1, value2, "e290");
            return (Criteria) this;
        }

        public Criteria andE283IsNull() {
            addCriterion("E283 is null");
            return (Criteria) this;
        }

        public Criteria andE283IsNotNull() {
            addCriterion("E283 is not null");
            return (Criteria) this;
        }

        public Criteria andE283EqualTo(String value) {
            addCriterion("E283 =", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283NotEqualTo(String value) {
            addCriterion("E283 <>", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283GreaterThan(String value) {
            addCriterion("E283 >", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283GreaterThanOrEqualTo(String value) {
            addCriterion("E283 >=", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283LessThan(String value) {
            addCriterion("E283 <", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283LessThanOrEqualTo(String value) {
            addCriterion("E283 <=", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283Like(String value) {
            addCriterion("E283 like", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283NotLike(String value) {
            addCriterion("E283 not like", value, "e283");
            return (Criteria) this;
        }

        public Criteria andE283In(List<String> values) {
            addCriterion("E283 in", values, "e283");
            return (Criteria) this;
        }

        public Criteria andE283NotIn(List<String> values) {
            addCriterion("E283 not in", values, "e283");
            return (Criteria) this;
        }

        public Criteria andE283Between(String value1, String value2) {
            addCriterion("E283 between", value1, value2, "e283");
            return (Criteria) this;
        }

        public Criteria andE283NotBetween(String value1, String value2) {
            addCriterion("E283 not between", value1, value2, "e283");
            return (Criteria) this;
        }

        public Criteria andA073IsNull() {
            addCriterion("A073 is null");
            return (Criteria) this;
        }

        public Criteria andA073IsNotNull() {
            addCriterion("A073 is not null");
            return (Criteria) this;
        }

        public Criteria andA073EqualTo(String value) {
            addCriterion("A073 =", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073NotEqualTo(String value) {
            addCriterion("A073 <>", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073GreaterThan(String value) {
            addCriterion("A073 >", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073GreaterThanOrEqualTo(String value) {
            addCriterion("A073 >=", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073LessThan(String value) {
            addCriterion("A073 <", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073LessThanOrEqualTo(String value) {
            addCriterion("A073 <=", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073Like(String value) {
            addCriterion("A073 like", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073NotLike(String value) {
            addCriterion("A073 not like", value, "a073");
            return (Criteria) this;
        }

        public Criteria andA073In(List<String> values) {
            addCriterion("A073 in", values, "a073");
            return (Criteria) this;
        }

        public Criteria andA073NotIn(List<String> values) {
            addCriterion("A073 not in", values, "a073");
            return (Criteria) this;
        }

        public Criteria andA073Between(String value1, String value2) {
            addCriterion("A073 between", value1, value2, "a073");
            return (Criteria) this;
        }

        public Criteria andA073NotBetween(String value1, String value2) {
            addCriterion("A073 not between", value1, value2, "a073");
            return (Criteria) this;
        }

        public Criteria andA075IsNull() {
            addCriterion("A075 is null");
            return (Criteria) this;
        }

        public Criteria andA075IsNotNull() {
            addCriterion("A075 is not null");
            return (Criteria) this;
        }

        public Criteria andA075EqualTo(String value) {
            addCriterion("A075 =", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075NotEqualTo(String value) {
            addCriterion("A075 <>", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075GreaterThan(String value) {
            addCriterion("A075 >", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075GreaterThanOrEqualTo(String value) {
            addCriterion("A075 >=", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075LessThan(String value) {
            addCriterion("A075 <", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075LessThanOrEqualTo(String value) {
            addCriterion("A075 <=", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075Like(String value) {
            addCriterion("A075 like", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075NotLike(String value) {
            addCriterion("A075 not like", value, "a075");
            return (Criteria) this;
        }

        public Criteria andA075In(List<String> values) {
            addCriterion("A075 in", values, "a075");
            return (Criteria) this;
        }

        public Criteria andA075NotIn(List<String> values) {
            addCriterion("A075 not in", values, "a075");
            return (Criteria) this;
        }

        public Criteria andA075Between(String value1, String value2) {
            addCriterion("A075 between", value1, value2, "a075");
            return (Criteria) this;
        }

        public Criteria andA075NotBetween(String value1, String value2) {
            addCriterion("A075 not between", value1, value2, "a075");
            return (Criteria) this;
        }

        public Criteria andP015IsNull() {
            addCriterion("P015 is null");
            return (Criteria) this;
        }

        public Criteria andP015IsNotNull() {
            addCriterion("P015 is not null");
            return (Criteria) this;
        }

        public Criteria andP015EqualTo(String value) {
            addCriterion("P015 =", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015NotEqualTo(String value) {
            addCriterion("P015 <>", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015GreaterThan(String value) {
            addCriterion("P015 >", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015GreaterThanOrEqualTo(String value) {
            addCriterion("P015 >=", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015LessThan(String value) {
            addCriterion("P015 <", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015LessThanOrEqualTo(String value) {
            addCriterion("P015 <=", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015Like(String value) {
            addCriterion("P015 like", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015NotLike(String value) {
            addCriterion("P015 not like", value, "p015");
            return (Criteria) this;
        }

        public Criteria andP015In(List<String> values) {
            addCriterion("P015 in", values, "p015");
            return (Criteria) this;
        }

        public Criteria andP015NotIn(List<String> values) {
            addCriterion("P015 not in", values, "p015");
            return (Criteria) this;
        }

        public Criteria andP015Between(String value1, String value2) {
            addCriterion("P015 between", value1, value2, "p015");
            return (Criteria) this;
        }

        public Criteria andP015NotBetween(String value1, String value2) {
            addCriterion("P015 not between", value1, value2, "p015");
            return (Criteria) this;
        }

        public Criteria andP017IsNull() {
            addCriterion("P017 is null");
            return (Criteria) this;
        }

        public Criteria andP017IsNotNull() {
            addCriterion("P017 is not null");
            return (Criteria) this;
        }

        public Criteria andP017EqualTo(String value) {
            addCriterion("P017 =", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017NotEqualTo(String value) {
            addCriterion("P017 <>", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017GreaterThan(String value) {
            addCriterion("P017 >", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017GreaterThanOrEqualTo(String value) {
            addCriterion("P017 >=", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017LessThan(String value) {
            addCriterion("P017 <", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017LessThanOrEqualTo(String value) {
            addCriterion("P017 <=", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017Like(String value) {
            addCriterion("P017 like", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017NotLike(String value) {
            addCriterion("P017 not like", value, "p017");
            return (Criteria) this;
        }

        public Criteria andP017In(List<String> values) {
            addCriterion("P017 in", values, "p017");
            return (Criteria) this;
        }

        public Criteria andP017NotIn(List<String> values) {
            addCriterion("P017 not in", values, "p017");
            return (Criteria) this;
        }

        public Criteria andP017Between(String value1, String value2) {
            addCriterion("P017 between", value1, value2, "p017");
            return (Criteria) this;
        }

        public Criteria andP017NotBetween(String value1, String value2) {
            addCriterion("P017 not between", value1, value2, "p017");
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

        public Criteria andWfPzidIsNull() {
            addCriterion("WF_PZID is null");
            return (Criteria) this;
        }

        public Criteria andWfPzidIsNotNull() {
            addCriterion("WF_PZID is not null");
            return (Criteria) this;
        }

        public Criteria andWfPzidEqualTo(BigDecimal value) {
            addCriterion("WF_PZID =", value, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidNotEqualTo(BigDecimal value) {
            addCriterion("WF_PZID <>", value, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidGreaterThan(BigDecimal value) {
            addCriterion("WF_PZID >", value, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WF_PZID >=", value, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidLessThan(BigDecimal value) {
            addCriterion("WF_PZID <", value, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WF_PZID <=", value, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidIn(List<BigDecimal> values) {
            addCriterion("WF_PZID in", values, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidNotIn(List<BigDecimal> values) {
            addCriterion("WF_PZID not in", values, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WF_PZID between", value1, value2, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfPzidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WF_PZID not between", value1, value2, "wfPzid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidIsNull() {
            addCriterion("WF_PROCESSUNID is null");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidIsNotNull() {
            addCriterion("WF_PROCESSUNID is not null");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidEqualTo(String value) {
            addCriterion("WF_PROCESSUNID =", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidNotEqualTo(String value) {
            addCriterion("WF_PROCESSUNID <>", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidGreaterThan(String value) {
            addCriterion("WF_PROCESSUNID >", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidGreaterThanOrEqualTo(String value) {
            addCriterion("WF_PROCESSUNID >=", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidLessThan(String value) {
            addCriterion("WF_PROCESSUNID <", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidLessThanOrEqualTo(String value) {
            addCriterion("WF_PROCESSUNID <=", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidLike(String value) {
            addCriterion("WF_PROCESSUNID like", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidNotLike(String value) {
            addCriterion("WF_PROCESSUNID not like", value, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidIn(List<String> values) {
            addCriterion("WF_PROCESSUNID in", values, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidNotIn(List<String> values) {
            addCriterion("WF_PROCESSUNID not in", values, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidBetween(String value1, String value2) {
            addCriterion("WF_PROCESSUNID between", value1, value2, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andWfProcessunidNotBetween(String value1, String value2) {
            addCriterion("WF_PROCESSUNID not between", value1, value2, "wfProcessunid");
            return (Criteria) this;
        }

        public Criteria andE053NameIsNull() {
            addCriterion("E053_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE053NameIsNotNull() {
            addCriterion("E053_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE053NameEqualTo(String value) {
            addCriterion("E053_NAME =", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameNotEqualTo(String value) {
            addCriterion("E053_NAME <>", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameGreaterThan(String value) {
            addCriterion("E053_NAME >", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameGreaterThanOrEqualTo(String value) {
            addCriterion("E053_NAME >=", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameLessThan(String value) {
            addCriterion("E053_NAME <", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameLessThanOrEqualTo(String value) {
            addCriterion("E053_NAME <=", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameLike(String value) {
            addCriterion("E053_NAME like", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameNotLike(String value) {
            addCriterion("E053_NAME not like", value, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameIn(List<String> values) {
            addCriterion("E053_NAME in", values, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameNotIn(List<String> values) {
            addCriterion("E053_NAME not in", values, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameBetween(String value1, String value2) {
            addCriterion("E053_NAME between", value1, value2, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE053NameNotBetween(String value1, String value2) {
            addCriterion("E053_NAME not between", value1, value2, "e053Name");
            return (Criteria) this;
        }

        public Criteria andE073NameIsNull() {
            addCriterion("E073_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE073NameIsNotNull() {
            addCriterion("E073_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE073NameEqualTo(String value) {
            addCriterion("E073_NAME =", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameNotEqualTo(String value) {
            addCriterion("E073_NAME <>", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameGreaterThan(String value) {
            addCriterion("E073_NAME >", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameGreaterThanOrEqualTo(String value) {
            addCriterion("E073_NAME >=", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameLessThan(String value) {
            addCriterion("E073_NAME <", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameLessThanOrEqualTo(String value) {
            addCriterion("E073_NAME <=", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameLike(String value) {
            addCriterion("E073_NAME like", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameNotLike(String value) {
            addCriterion("E073_NAME not like", value, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameIn(List<String> values) {
            addCriterion("E073_NAME in", values, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameNotIn(List<String> values) {
            addCriterion("E073_NAME not in", values, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameBetween(String value1, String value2) {
            addCriterion("E073_NAME between", value1, value2, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE073NameNotBetween(String value1, String value2) {
            addCriterion("E073_NAME not between", value1, value2, "e073Name");
            return (Criteria) this;
        }

        public Criteria andE080NameIsNull() {
            addCriterion("E080_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE080NameIsNotNull() {
            addCriterion("E080_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE080NameEqualTo(String value) {
            addCriterion("E080_NAME =", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameNotEqualTo(String value) {
            addCriterion("E080_NAME <>", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameGreaterThan(String value) {
            addCriterion("E080_NAME >", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameGreaterThanOrEqualTo(String value) {
            addCriterion("E080_NAME >=", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameLessThan(String value) {
            addCriterion("E080_NAME <", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameLessThanOrEqualTo(String value) {
            addCriterion("E080_NAME <=", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameLike(String value) {
            addCriterion("E080_NAME like", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameNotLike(String value) {
            addCriterion("E080_NAME not like", value, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameIn(List<String> values) {
            addCriterion("E080_NAME in", values, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameNotIn(List<String> values) {
            addCriterion("E080_NAME not in", values, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameBetween(String value1, String value2) {
            addCriterion("E080_NAME between", value1, value2, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE080NameNotBetween(String value1, String value2) {
            addCriterion("E080_NAME not between", value1, value2, "e080Name");
            return (Criteria) this;
        }

        public Criteria andE082NameIsNull() {
            addCriterion("E082_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE082NameIsNotNull() {
            addCriterion("E082_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE082NameEqualTo(String value) {
            addCriterion("E082_NAME =", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameNotEqualTo(String value) {
            addCriterion("E082_NAME <>", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameGreaterThan(String value) {
            addCriterion("E082_NAME >", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameGreaterThanOrEqualTo(String value) {
            addCriterion("E082_NAME >=", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameLessThan(String value) {
            addCriterion("E082_NAME <", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameLessThanOrEqualTo(String value) {
            addCriterion("E082_NAME <=", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameLike(String value) {
            addCriterion("E082_NAME like", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameNotLike(String value) {
            addCriterion("E082_NAME not like", value, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameIn(List<String> values) {
            addCriterion("E082_NAME in", values, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameNotIn(List<String> values) {
            addCriterion("E082_NAME not in", values, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameBetween(String value1, String value2) {
            addCriterion("E082_NAME between", value1, value2, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE082NameNotBetween(String value1, String value2) {
            addCriterion("E082_NAME not between", value1, value2, "e082Name");
            return (Criteria) this;
        }

        public Criteria andE084NameIsNull() {
            addCriterion("E084_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE084NameIsNotNull() {
            addCriterion("E084_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE084NameEqualTo(String value) {
            addCriterion("E084_NAME =", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameNotEqualTo(String value) {
            addCriterion("E084_NAME <>", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameGreaterThan(String value) {
            addCriterion("E084_NAME >", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameGreaterThanOrEqualTo(String value) {
            addCriterion("E084_NAME >=", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameLessThan(String value) {
            addCriterion("E084_NAME <", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameLessThanOrEqualTo(String value) {
            addCriterion("E084_NAME <=", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameLike(String value) {
            addCriterion("E084_NAME like", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameNotLike(String value) {
            addCriterion("E084_NAME not like", value, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameIn(List<String> values) {
            addCriterion("E084_NAME in", values, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameNotIn(List<String> values) {
            addCriterion("E084_NAME not in", values, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameBetween(String value1, String value2) {
            addCriterion("E084_NAME between", value1, value2, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE084NameNotBetween(String value1, String value2) {
            addCriterion("E084_NAME not between", value1, value2, "e084Name");
            return (Criteria) this;
        }

        public Criteria andE184NameIsNull() {
            addCriterion("E184_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE184NameIsNotNull() {
            addCriterion("E184_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE184NameEqualTo(String value) {
            addCriterion("E184_NAME =", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameNotEqualTo(String value) {
            addCriterion("E184_NAME <>", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameGreaterThan(String value) {
            addCriterion("E184_NAME >", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameGreaterThanOrEqualTo(String value) {
            addCriterion("E184_NAME >=", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameLessThan(String value) {
            addCriterion("E184_NAME <", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameLessThanOrEqualTo(String value) {
            addCriterion("E184_NAME <=", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameLike(String value) {
            addCriterion("E184_NAME like", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameNotLike(String value) {
            addCriterion("E184_NAME not like", value, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameIn(List<String> values) {
            addCriterion("E184_NAME in", values, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameNotIn(List<String> values) {
            addCriterion("E184_NAME not in", values, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameBetween(String value1, String value2) {
            addCriterion("E184_NAME between", value1, value2, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE184NameNotBetween(String value1, String value2) {
            addCriterion("E184_NAME not between", value1, value2, "e184Name");
            return (Criteria) this;
        }

        public Criteria andE284NameIsNull() {
            addCriterion("E284_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE284NameIsNotNull() {
            addCriterion("E284_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE284NameEqualTo(String value) {
            addCriterion("E284_NAME =", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameNotEqualTo(String value) {
            addCriterion("E284_NAME <>", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameGreaterThan(String value) {
            addCriterion("E284_NAME >", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameGreaterThanOrEqualTo(String value) {
            addCriterion("E284_NAME >=", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameLessThan(String value) {
            addCriterion("E284_NAME <", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameLessThanOrEqualTo(String value) {
            addCriterion("E284_NAME <=", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameLike(String value) {
            addCriterion("E284_NAME like", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameNotLike(String value) {
            addCriterion("E284_NAME not like", value, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameIn(List<String> values) {
            addCriterion("E284_NAME in", values, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameNotIn(List<String> values) {
            addCriterion("E284_NAME not in", values, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameBetween(String value1, String value2) {
            addCriterion("E284_NAME between", value1, value2, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE284NameNotBetween(String value1, String value2) {
            addCriterion("E284_NAME not between", value1, value2, "e284Name");
            return (Criteria) this;
        }

        public Criteria andE288NameIsNull() {
            addCriterion("E288_NAME is null");
            return (Criteria) this;
        }

        public Criteria andE288NameIsNotNull() {
            addCriterion("E288_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andE288NameEqualTo(String value) {
            addCriterion("E288_NAME =", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameNotEqualTo(String value) {
            addCriterion("E288_NAME <>", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameGreaterThan(String value) {
            addCriterion("E288_NAME >", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameGreaterThanOrEqualTo(String value) {
            addCriterion("E288_NAME >=", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameLessThan(String value) {
            addCriterion("E288_NAME <", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameLessThanOrEqualTo(String value) {
            addCriterion("E288_NAME <=", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameLike(String value) {
            addCriterion("E288_NAME like", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameNotLike(String value) {
            addCriterion("E288_NAME not like", value, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameIn(List<String> values) {
            addCriterion("E288_NAME in", values, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameNotIn(List<String> values) {
            addCriterion("E288_NAME not in", values, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameBetween(String value1, String value2) {
            addCriterion("E288_NAME between", value1, value2, "e288Name");
            return (Criteria) this;
        }

        public Criteria andE288NameNotBetween(String value1, String value2) {
            addCriterion("E288_NAME not between", value1, value2, "e288Name");
            return (Criteria) this;
        }

        public Criteria andEamIdIsNull() {
            addCriterion("EAM_ID is null");
            return (Criteria) this;
        }

        public Criteria andEamIdIsNotNull() {
            addCriterion("EAM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEamIdEqualTo(String value) {
            addCriterion("EAM_ID =", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdNotEqualTo(String value) {
            addCriterion("EAM_ID <>", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdGreaterThan(String value) {
            addCriterion("EAM_ID >", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdGreaterThanOrEqualTo(String value) {
            addCriterion("EAM_ID >=", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdLessThan(String value) {
            addCriterion("EAM_ID <", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdLessThanOrEqualTo(String value) {
            addCriterion("EAM_ID <=", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdLike(String value) {
            addCriterion("EAM_ID like", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdNotLike(String value) {
            addCriterion("EAM_ID not like", value, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdIn(List<String> values) {
            addCriterion("EAM_ID in", values, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdNotIn(List<String> values) {
            addCriterion("EAM_ID not in", values, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdBetween(String value1, String value2) {
            addCriterion("EAM_ID between", value1, value2, "eamId");
            return (Criteria) this;
        }

        public Criteria andEamIdNotBetween(String value1, String value2) {
            addCriterion("EAM_ID not between", value1, value2, "eamId");
            return (Criteria) this;
        }

        public Criteria andE024IsNull() {
            addCriterion("E024 is null");
            return (Criteria) this;
        }

        public Criteria andE024IsNotNull() {
            addCriterion("E024 is not null");
            return (Criteria) this;
        }

        public Criteria andE024EqualTo(BigDecimal value) {
            addCriterion("E024 =", value, "e024");
            return (Criteria) this;
        }

        public Criteria andE024NotEqualTo(BigDecimal value) {
            addCriterion("E024 <>", value, "e024");
            return (Criteria) this;
        }

        public Criteria andE024GreaterThan(BigDecimal value) {
            addCriterion("E024 >", value, "e024");
            return (Criteria) this;
        }

        public Criteria andE024GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E024 >=", value, "e024");
            return (Criteria) this;
        }

        public Criteria andE024LessThan(BigDecimal value) {
            addCriterion("E024 <", value, "e024");
            return (Criteria) this;
        }

        public Criteria andE024LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E024 <=", value, "e024");
            return (Criteria) this;
        }

        public Criteria andE024In(List<BigDecimal> values) {
            addCriterion("E024 in", values, "e024");
            return (Criteria) this;
        }

        public Criteria andE024NotIn(List<BigDecimal> values) {
            addCriterion("E024 not in", values, "e024");
            return (Criteria) this;
        }

        public Criteria andE024Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E024 between", value1, value2, "e024");
            return (Criteria) this;
        }

        public Criteria andE024NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E024 not between", value1, value2, "e024");
            return (Criteria) this;
        }

        public Criteria andE082IsNull() {
            addCriterion("E082 is null");
            return (Criteria) this;
        }

        public Criteria andE082IsNotNull() {
            addCriterion("E082 is not null");
            return (Criteria) this;
        }

        public Criteria andE082EqualTo(BigDecimal value) {
            addCriterion("E082 =", value, "e082");
            return (Criteria) this;
        }

        public Criteria andE082NotEqualTo(BigDecimal value) {
            addCriterion("E082 <>", value, "e082");
            return (Criteria) this;
        }

        public Criteria andE082GreaterThan(BigDecimal value) {
            addCriterion("E082 >", value, "e082");
            return (Criteria) this;
        }

        public Criteria andE082GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E082 >=", value, "e082");
            return (Criteria) this;
        }

        public Criteria andE082LessThan(BigDecimal value) {
            addCriterion("E082 <", value, "e082");
            return (Criteria) this;
        }

        public Criteria andE082LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E082 <=", value, "e082");
            return (Criteria) this;
        }

        public Criteria andE082In(List<BigDecimal> values) {
            addCriterion("E082 in", values, "e082");
            return (Criteria) this;
        }

        public Criteria andE082NotIn(List<BigDecimal> values) {
            addCriterion("E082 not in", values, "e082");
            return (Criteria) this;
        }

        public Criteria andE082Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E082 between", value1, value2, "e082");
            return (Criteria) this;
        }

        public Criteria andE082NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E082 not between", value1, value2, "e082");
            return (Criteria) this;
        }

        public Criteria andE084IsNull() {
            addCriterion("E084 is null");
            return (Criteria) this;
        }

        public Criteria andE084IsNotNull() {
            addCriterion("E084 is not null");
            return (Criteria) this;
        }

        public Criteria andE084EqualTo(BigDecimal value) {
            addCriterion("E084 =", value, "e084");
            return (Criteria) this;
        }

        public Criteria andE084NotEqualTo(BigDecimal value) {
            addCriterion("E084 <>", value, "e084");
            return (Criteria) this;
        }

        public Criteria andE084GreaterThan(BigDecimal value) {
            addCriterion("E084 >", value, "e084");
            return (Criteria) this;
        }

        public Criteria andE084GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E084 >=", value, "e084");
            return (Criteria) this;
        }

        public Criteria andE084LessThan(BigDecimal value) {
            addCriterion("E084 <", value, "e084");
            return (Criteria) this;
        }

        public Criteria andE084LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E084 <=", value, "e084");
            return (Criteria) this;
        }

        public Criteria andE084In(List<BigDecimal> values) {
            addCriterion("E084 in", values, "e084");
            return (Criteria) this;
        }

        public Criteria andE084NotIn(List<BigDecimal> values) {
            addCriterion("E084 not in", values, "e084");
            return (Criteria) this;
        }

        public Criteria andE084Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E084 between", value1, value2, "e084");
            return (Criteria) this;
        }

        public Criteria andE084NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E084 not between", value1, value2, "e084");
            return (Criteria) this;
        }

        public Criteria andE288IsNull() {
            addCriterion("E288 is null");
            return (Criteria) this;
        }

        public Criteria andE288IsNotNull() {
            addCriterion("E288 is not null");
            return (Criteria) this;
        }

        public Criteria andE288EqualTo(BigDecimal value) {
            addCriterion("E288 =", value, "e288");
            return (Criteria) this;
        }

        public Criteria andE288NotEqualTo(BigDecimal value) {
            addCriterion("E288 <>", value, "e288");
            return (Criteria) this;
        }

        public Criteria andE288GreaterThan(BigDecimal value) {
            addCriterion("E288 >", value, "e288");
            return (Criteria) this;
        }

        public Criteria andE288GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E288 >=", value, "e288");
            return (Criteria) this;
        }

        public Criteria andE288LessThan(BigDecimal value) {
            addCriterion("E288 <", value, "e288");
            return (Criteria) this;
        }

        public Criteria andE288LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E288 <=", value, "e288");
            return (Criteria) this;
        }

        public Criteria andE288In(List<BigDecimal> values) {
            addCriterion("E288 in", values, "e288");
            return (Criteria) this;
        }

        public Criteria andE288NotIn(List<BigDecimal> values) {
            addCriterion("E288 not in", values, "e288");
            return (Criteria) this;
        }

        public Criteria andE288Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E288 between", value1, value2, "e288");
            return (Criteria) this;
        }

        public Criteria andE288NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E288 not between", value1, value2, "e288");
            return (Criteria) this;
        }

        public Criteria andE083IsNull() {
            addCriterion("E083 is null");
            return (Criteria) this;
        }

        public Criteria andE083IsNotNull() {
            addCriterion("E083 is not null");
            return (Criteria) this;
        }

        public Criteria andE083EqualTo(BigDecimal value) {
            addCriterion("E083 =", value, "e083");
            return (Criteria) this;
        }

        public Criteria andE083NotEqualTo(BigDecimal value) {
            addCriterion("E083 <>", value, "e083");
            return (Criteria) this;
        }

        public Criteria andE083GreaterThan(BigDecimal value) {
            addCriterion("E083 >", value, "e083");
            return (Criteria) this;
        }

        public Criteria andE083GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E083 >=", value, "e083");
            return (Criteria) this;
        }

        public Criteria andE083LessThan(BigDecimal value) {
            addCriterion("E083 <", value, "e083");
            return (Criteria) this;
        }

        public Criteria andE083LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E083 <=", value, "e083");
            return (Criteria) this;
        }

        public Criteria andE083In(List<BigDecimal> values) {
            addCriterion("E083 in", values, "e083");
            return (Criteria) this;
        }

        public Criteria andE083NotIn(List<BigDecimal> values) {
            addCriterion("E083 not in", values, "e083");
            return (Criteria) this;
        }

        public Criteria andE083Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E083 between", value1, value2, "e083");
            return (Criteria) this;
        }

        public Criteria andE083NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E083 not between", value1, value2, "e083");
            return (Criteria) this;
        }

        public Criteria andE081IsNull() {
            addCriterion("E081 is null");
            return (Criteria) this;
        }

        public Criteria andE081IsNotNull() {
            addCriterion("E081 is not null");
            return (Criteria) this;
        }

        public Criteria andE081EqualTo(BigDecimal value) {
            addCriterion("E081 =", value, "e081");
            return (Criteria) this;
        }

        public Criteria andE081NotEqualTo(BigDecimal value) {
            addCriterion("E081 <>", value, "e081");
            return (Criteria) this;
        }

        public Criteria andE081GreaterThan(BigDecimal value) {
            addCriterion("E081 >", value, "e081");
            return (Criteria) this;
        }

        public Criteria andE081GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E081 >=", value, "e081");
            return (Criteria) this;
        }

        public Criteria andE081LessThan(BigDecimal value) {
            addCriterion("E081 <", value, "e081");
            return (Criteria) this;
        }

        public Criteria andE081LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E081 <=", value, "e081");
            return (Criteria) this;
        }

        public Criteria andE081In(List<BigDecimal> values) {
            addCriterion("E081 in", values, "e081");
            return (Criteria) this;
        }

        public Criteria andE081NotIn(List<BigDecimal> values) {
            addCriterion("E081 not in", values, "e081");
            return (Criteria) this;
        }

        public Criteria andE081Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E081 between", value1, value2, "e081");
            return (Criteria) this;
        }

        public Criteria andE081NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E081 not between", value1, value2, "e081");
            return (Criteria) this;
        }

        public Criteria andE068IsNull() {
            addCriterion("E068 is null");
            return (Criteria) this;
        }

        public Criteria andE068IsNotNull() {
            addCriterion("E068 is not null");
            return (Criteria) this;
        }

        public Criteria andE068EqualTo(BigDecimal value) {
            addCriterion("E068 =", value, "e068");
            return (Criteria) this;
        }

        public Criteria andE068NotEqualTo(BigDecimal value) {
            addCriterion("E068 <>", value, "e068");
            return (Criteria) this;
        }

        public Criteria andE068GreaterThan(BigDecimal value) {
            addCriterion("E068 >", value, "e068");
            return (Criteria) this;
        }

        public Criteria andE068GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("E068 >=", value, "e068");
            return (Criteria) this;
        }

        public Criteria andE068LessThan(BigDecimal value) {
            addCriterion("E068 <", value, "e068");
            return (Criteria) this;
        }

        public Criteria andE068LessThanOrEqualTo(BigDecimal value) {
            addCriterion("E068 <=", value, "e068");
            return (Criteria) this;
        }

        public Criteria andE068In(List<BigDecimal> values) {
            addCriterion("E068 in", values, "e068");
            return (Criteria) this;
        }

        public Criteria andE068NotIn(List<BigDecimal> values) {
            addCriterion("E068 not in", values, "e068");
            return (Criteria) this;
        }

        public Criteria andE068Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("E068 between", value1, value2, "e068");
            return (Criteria) this;
        }

        public Criteria andE068NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("E068 not between", value1, value2, "e068");
            return (Criteria) this;
        }

        public Criteria andE052IsNull() {
            addCriterion("E052 is null");
            return (Criteria) this;
        }

        public Criteria andE052IsNotNull() {
            addCriterion("E052 is not null");
            return (Criteria) this;
        }

        public Criteria andE052EqualTo(String value) {
            addCriterion("E052 =", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052NotEqualTo(String value) {
            addCriterion("E052 <>", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052GreaterThan(String value) {
            addCriterion("E052 >", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052GreaterThanOrEqualTo(String value) {
            addCriterion("E052 >=", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052LessThan(String value) {
            addCriterion("E052 <", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052LessThanOrEqualTo(String value) {
            addCriterion("E052 <=", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052Like(String value) {
            addCriterion("E052 like", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052NotLike(String value) {
            addCriterion("E052 not like", value, "e052");
            return (Criteria) this;
        }

        public Criteria andE052In(List<String> values) {
            addCriterion("E052 in", values, "e052");
            return (Criteria) this;
        }

        public Criteria andE052NotIn(List<String> values) {
            addCriterion("E052 not in", values, "e052");
            return (Criteria) this;
        }

        public Criteria andE052Between(String value1, String value2) {
            addCriterion("E052 between", value1, value2, "e052");
            return (Criteria) this;
        }

        public Criteria andE052NotBetween(String value1, String value2) {
            addCriterion("E052 not between", value1, value2, "e052");
            return (Criteria) this;
        }

        public Criteria andE102IsNull() {
            addCriterion("E102 is null");
            return (Criteria) this;
        }

        public Criteria andE102IsNotNull() {
            addCriterion("E102 is not null");
            return (Criteria) this;
        }

        public Criteria andE102EqualTo(String value) {
            addCriterion("E102 =", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102NotEqualTo(String value) {
            addCriterion("E102 <>", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102GreaterThan(String value) {
            addCriterion("E102 >", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102GreaterThanOrEqualTo(String value) {
            addCriterion("E102 >=", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102LessThan(String value) {
            addCriterion("E102 <", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102LessThanOrEqualTo(String value) {
            addCriterion("E102 <=", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102Like(String value) {
            addCriterion("E102 like", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102NotLike(String value) {
            addCriterion("E102 not like", value, "e102");
            return (Criteria) this;
        }

        public Criteria andE102In(List<String> values) {
            addCriterion("E102 in", values, "e102");
            return (Criteria) this;
        }

        public Criteria andE102NotIn(List<String> values) {
            addCriterion("E102 not in", values, "e102");
            return (Criteria) this;
        }

        public Criteria andE102Between(String value1, String value2) {
            addCriterion("E102 between", value1, value2, "e102");
            return (Criteria) this;
        }

        public Criteria andE102NotBetween(String value1, String value2) {
            addCriterion("E102 not between", value1, value2, "e102");
            return (Criteria) this;
        }

        public Criteria andE112IsNull() {
            addCriterion("E112 is null");
            return (Criteria) this;
        }

        public Criteria andE112IsNotNull() {
            addCriterion("E112 is not null");
            return (Criteria) this;
        }

        public Criteria andE112EqualTo(String value) {
            addCriterion("E112 =", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112NotEqualTo(String value) {
            addCriterion("E112 <>", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112GreaterThan(String value) {
            addCriterion("E112 >", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112GreaterThanOrEqualTo(String value) {
            addCriterion("E112 >=", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112LessThan(String value) {
            addCriterion("E112 <", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112LessThanOrEqualTo(String value) {
            addCriterion("E112 <=", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112Like(String value) {
            addCriterion("E112 like", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112NotLike(String value) {
            addCriterion("E112 not like", value, "e112");
            return (Criteria) this;
        }

        public Criteria andE112In(List<String> values) {
            addCriterion("E112 in", values, "e112");
            return (Criteria) this;
        }

        public Criteria andE112NotIn(List<String> values) {
            addCriterion("E112 not in", values, "e112");
            return (Criteria) this;
        }

        public Criteria andE112Between(String value1, String value2) {
            addCriterion("E112 between", value1, value2, "e112");
            return (Criteria) this;
        }

        public Criteria andE112NotBetween(String value1, String value2) {
            addCriterion("E112 not between", value1, value2, "e112");
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