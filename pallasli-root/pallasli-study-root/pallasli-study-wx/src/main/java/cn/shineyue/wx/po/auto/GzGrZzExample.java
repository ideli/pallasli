package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GzGrZzExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GzGrZzExample() {
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

        public Criteria andA000IsNull() {
            addCriterion("A000 is null");
            return (Criteria) this;
        }

        public Criteria andA000IsNotNull() {
            addCriterion("A000 is not null");
            return (Criteria) this;
        }

        public Criteria andA000EqualTo(String value) {
            addCriterion("A000 =", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000NotEqualTo(String value) {
            addCriterion("A000 <>", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000GreaterThan(String value) {
            addCriterion("A000 >", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000GreaterThanOrEqualTo(String value) {
            addCriterion("A000 >=", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000LessThan(String value) {
            addCriterion("A000 <", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000LessThanOrEqualTo(String value) {
            addCriterion("A000 <=", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000Like(String value) {
            addCriterion("A000 like", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000NotLike(String value) {
            addCriterion("A000 not like", value, "a000");
            return (Criteria) this;
        }

        public Criteria andA000In(List<String> values) {
            addCriterion("A000 in", values, "a000");
            return (Criteria) this;
        }

        public Criteria andA000NotIn(List<String> values) {
            addCriterion("A000 not in", values, "a000");
            return (Criteria) this;
        }

        public Criteria andA000Between(String value1, String value2) {
            addCriterion("A000 between", value1, value2, "a000");
            return (Criteria) this;
        }

        public Criteria andA000NotBetween(String value1, String value2) {
            addCriterion("A000 not between", value1, value2, "a000");
            return (Criteria) this;
        }

        public Criteria andA001IsNull() {
            addCriterion("A001 is null");
            return (Criteria) this;
        }

        public Criteria andA001IsNotNull() {
            addCriterion("A001 is not null");
            return (Criteria) this;
        }

        public Criteria andA001EqualTo(String value) {
            addCriterion("A001 =", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001NotEqualTo(String value) {
            addCriterion("A001 <>", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001GreaterThan(String value) {
            addCriterion("A001 >", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001GreaterThanOrEqualTo(String value) {
            addCriterion("A001 >=", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001LessThan(String value) {
            addCriterion("A001 <", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001LessThanOrEqualTo(String value) {
            addCriterion("A001 <=", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001Like(String value) {
            addCriterion("A001 like", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001NotLike(String value) {
            addCriterion("A001 not like", value, "a001");
            return (Criteria) this;
        }

        public Criteria andA001In(List<String> values) {
            addCriterion("A001 in", values, "a001");
            return (Criteria) this;
        }

        public Criteria andA001NotIn(List<String> values) {
            addCriterion("A001 not in", values, "a001");
            return (Criteria) this;
        }

        public Criteria andA001Between(String value1, String value2) {
            addCriterion("A001 between", value1, value2, "a001");
            return (Criteria) this;
        }

        public Criteria andA001NotBetween(String value1, String value2) {
            addCriterion("A001 not between", value1, value2, "a001");
            return (Criteria) this;
        }

        public Criteria andA002IsNull() {
            addCriterion("A002 is null");
            return (Criteria) this;
        }

        public Criteria andA002IsNotNull() {
            addCriterion("A002 is not null");
            return (Criteria) this;
        }

        public Criteria andA002EqualTo(String value) {
            addCriterion("A002 =", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002NotEqualTo(String value) {
            addCriterion("A002 <>", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002GreaterThan(String value) {
            addCriterion("A002 >", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002GreaterThanOrEqualTo(String value) {
            addCriterion("A002 >=", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002LessThan(String value) {
            addCriterion("A002 <", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002LessThanOrEqualTo(String value) {
            addCriterion("A002 <=", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002Like(String value) {
            addCriterion("A002 like", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002NotLike(String value) {
            addCriterion("A002 not like", value, "a002");
            return (Criteria) this;
        }

        public Criteria andA002In(List<String> values) {
            addCriterion("A002 in", values, "a002");
            return (Criteria) this;
        }

        public Criteria andA002NotIn(List<String> values) {
            addCriterion("A002 not in", values, "a002");
            return (Criteria) this;
        }

        public Criteria andA002Between(String value1, String value2) {
            addCriterion("A002 between", value1, value2, "a002");
            return (Criteria) this;
        }

        public Criteria andA002NotBetween(String value1, String value2) {
            addCriterion("A002 not between", value1, value2, "a002");
            return (Criteria) this;
        }

        public Criteria andA003IsNull() {
            addCriterion("A003 is null");
            return (Criteria) this;
        }

        public Criteria andA003IsNotNull() {
            addCriterion("A003 is not null");
            return (Criteria) this;
        }

        public Criteria andA003EqualTo(String value) {
            addCriterion("A003 =", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003NotEqualTo(String value) {
            addCriterion("A003 <>", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003GreaterThan(String value) {
            addCriterion("A003 >", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003GreaterThanOrEqualTo(String value) {
            addCriterion("A003 >=", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003LessThan(String value) {
            addCriterion("A003 <", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003LessThanOrEqualTo(String value) {
            addCriterion("A003 <=", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003Like(String value) {
            addCriterion("A003 like", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003NotLike(String value) {
            addCriterion("A003 not like", value, "a003");
            return (Criteria) this;
        }

        public Criteria andA003In(List<String> values) {
            addCriterion("A003 in", values, "a003");
            return (Criteria) this;
        }

        public Criteria andA003NotIn(List<String> values) {
            addCriterion("A003 not in", values, "a003");
            return (Criteria) this;
        }

        public Criteria andA003Between(String value1, String value2) {
            addCriterion("A003 between", value1, value2, "a003");
            return (Criteria) this;
        }

        public Criteria andA003NotBetween(String value1, String value2) {
            addCriterion("A003 not between", value1, value2, "a003");
            return (Criteria) this;
        }

        public Criteria andA021IsNull() {
            addCriterion("A021 is null");
            return (Criteria) this;
        }

        public Criteria andA021IsNotNull() {
            addCriterion("A021 is not null");
            return (Criteria) this;
        }

        public Criteria andA021EqualTo(String value) {
            addCriterion("A021 =", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021NotEqualTo(String value) {
            addCriterion("A021 <>", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021GreaterThan(String value) {
            addCriterion("A021 >", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021GreaterThanOrEqualTo(String value) {
            addCriterion("A021 >=", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021LessThan(String value) {
            addCriterion("A021 <", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021LessThanOrEqualTo(String value) {
            addCriterion("A021 <=", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021Like(String value) {
            addCriterion("A021 like", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021NotLike(String value) {
            addCriterion("A021 not like", value, "a021");
            return (Criteria) this;
        }

        public Criteria andA021In(List<String> values) {
            addCriterion("A021 in", values, "a021");
            return (Criteria) this;
        }

        public Criteria andA021NotIn(List<String> values) {
            addCriterion("A021 not in", values, "a021");
            return (Criteria) this;
        }

        public Criteria andA021Between(String value1, String value2) {
            addCriterion("A021 between", value1, value2, "a021");
            return (Criteria) this;
        }

        public Criteria andA021NotBetween(String value1, String value2) {
            addCriterion("A021 not between", value1, value2, "a021");
            return (Criteria) this;
        }

        public Criteria andA008IsNull() {
            addCriterion("A008 is null");
            return (Criteria) this;
        }

        public Criteria andA008IsNotNull() {
            addCriterion("A008 is not null");
            return (Criteria) this;
        }

        public Criteria andA008EqualTo(String value) {
            addCriterion("A008 =", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008NotEqualTo(String value) {
            addCriterion("A008 <>", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008GreaterThan(String value) {
            addCriterion("A008 >", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008GreaterThanOrEqualTo(String value) {
            addCriterion("A008 >=", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008LessThan(String value) {
            addCriterion("A008 <", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008LessThanOrEqualTo(String value) {
            addCriterion("A008 <=", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008Like(String value) {
            addCriterion("A008 like", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008NotLike(String value) {
            addCriterion("A008 not like", value, "a008");
            return (Criteria) this;
        }

        public Criteria andA008In(List<String> values) {
            addCriterion("A008 in", values, "a008");
            return (Criteria) this;
        }

        public Criteria andA008NotIn(List<String> values) {
            addCriterion("A008 not in", values, "a008");
            return (Criteria) this;
        }

        public Criteria andA008Between(String value1, String value2) {
            addCriterion("A008 between", value1, value2, "a008");
            return (Criteria) this;
        }

        public Criteria andA008NotBetween(String value1, String value2) {
            addCriterion("A008 not between", value1, value2, "a008");
            return (Criteria) this;
        }

        public Criteria andA013IsNull() {
            addCriterion("A013 is null");
            return (Criteria) this;
        }

        public Criteria andA013IsNotNull() {
            addCriterion("A013 is not null");
            return (Criteria) this;
        }

        public Criteria andA013EqualTo(Date value) {
            addCriterionForJDBCDate("A013 =", value, "a013");
            return (Criteria) this;
        }

        public Criteria andA013NotEqualTo(Date value) {
            addCriterionForJDBCDate("A013 <>", value, "a013");
            return (Criteria) this;
        }

        public Criteria andA013GreaterThan(Date value) {
            addCriterionForJDBCDate("A013 >", value, "a013");
            return (Criteria) this;
        }

        public Criteria andA013GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A013 >=", value, "a013");
            return (Criteria) this;
        }

        public Criteria andA013LessThan(Date value) {
            addCriterionForJDBCDate("A013 <", value, "a013");
            return (Criteria) this;
        }

        public Criteria andA013LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A013 <=", value, "a013");
            return (Criteria) this;
        }

        public Criteria andA013In(List<Date> values) {
            addCriterionForJDBCDate("A013 in", values, "a013");
            return (Criteria) this;
        }

        public Criteria andA013NotIn(List<Date> values) {
            addCriterionForJDBCDate("A013 not in", values, "a013");
            return (Criteria) this;
        }

        public Criteria andA013Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A013 between", value1, value2, "a013");
            return (Criteria) this;
        }

        public Criteria andA013NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A013 not between", value1, value2, "a013");
            return (Criteria) this;
        }

        public Criteria andA014IsNull() {
            addCriterion("A014 is null");
            return (Criteria) this;
        }

        public Criteria andA014IsNotNull() {
            addCriterion("A014 is not null");
            return (Criteria) this;
        }

        public Criteria andA014EqualTo(Date value) {
            addCriterionForJDBCDate("A014 =", value, "a014");
            return (Criteria) this;
        }

        public Criteria andA014NotEqualTo(Date value) {
            addCriterionForJDBCDate("A014 <>", value, "a014");
            return (Criteria) this;
        }

        public Criteria andA014GreaterThan(Date value) {
            addCriterionForJDBCDate("A014 >", value, "a014");
            return (Criteria) this;
        }

        public Criteria andA014GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A014 >=", value, "a014");
            return (Criteria) this;
        }

        public Criteria andA014LessThan(Date value) {
            addCriterionForJDBCDate("A014 <", value, "a014");
            return (Criteria) this;
        }

        public Criteria andA014LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A014 <=", value, "a014");
            return (Criteria) this;
        }

        public Criteria andA014In(List<Date> values) {
            addCriterionForJDBCDate("A014 in", values, "a014");
            return (Criteria) this;
        }

        public Criteria andA014NotIn(List<Date> values) {
            addCriterionForJDBCDate("A014 not in", values, "a014");
            return (Criteria) this;
        }

        public Criteria andA014Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A014 between", value1, value2, "a014");
            return (Criteria) this;
        }

        public Criteria andA014NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A014 not between", value1, value2, "a014");
            return (Criteria) this;
        }

        public Criteria andA015IsNull() {
            addCriterion("A015 is null");
            return (Criteria) this;
        }

        public Criteria andA015IsNotNull() {
            addCriterion("A015 is not null");
            return (Criteria) this;
        }

        public Criteria andA015EqualTo(String value) {
            addCriterion("A015 =", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015NotEqualTo(String value) {
            addCriterion("A015 <>", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015GreaterThan(String value) {
            addCriterion("A015 >", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015GreaterThanOrEqualTo(String value) {
            addCriterion("A015 >=", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015LessThan(String value) {
            addCriterion("A015 <", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015LessThanOrEqualTo(String value) {
            addCriterion("A015 <=", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015Like(String value) {
            addCriterion("A015 like", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015NotLike(String value) {
            addCriterion("A015 not like", value, "a015");
            return (Criteria) this;
        }

        public Criteria andA015In(List<String> values) {
            addCriterion("A015 in", values, "a015");
            return (Criteria) this;
        }

        public Criteria andA015NotIn(List<String> values) {
            addCriterion("A015 not in", values, "a015");
            return (Criteria) this;
        }

        public Criteria andA015Between(String value1, String value2) {
            addCriterion("A015 between", value1, value2, "a015");
            return (Criteria) this;
        }

        public Criteria andA015NotBetween(String value1, String value2) {
            addCriterion("A015 not between", value1, value2, "a015");
            return (Criteria) this;
        }

        public Criteria andA019IsNull() {
            addCriterion("A019 is null");
            return (Criteria) this;
        }

        public Criteria andA019IsNotNull() {
            addCriterion("A019 is not null");
            return (Criteria) this;
        }

        public Criteria andA019EqualTo(Date value) {
            addCriterionForJDBCDate("A019 =", value, "a019");
            return (Criteria) this;
        }

        public Criteria andA019NotEqualTo(Date value) {
            addCriterionForJDBCDate("A019 <>", value, "a019");
            return (Criteria) this;
        }

        public Criteria andA019GreaterThan(Date value) {
            addCriterionForJDBCDate("A019 >", value, "a019");
            return (Criteria) this;
        }

        public Criteria andA019GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A019 >=", value, "a019");
            return (Criteria) this;
        }

        public Criteria andA019LessThan(Date value) {
            addCriterionForJDBCDate("A019 <", value, "a019");
            return (Criteria) this;
        }

        public Criteria andA019LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A019 <=", value, "a019");
            return (Criteria) this;
        }

        public Criteria andA019In(List<Date> values) {
            addCriterionForJDBCDate("A019 in", values, "a019");
            return (Criteria) this;
        }

        public Criteria andA019NotIn(List<Date> values) {
            addCriterionForJDBCDate("A019 not in", values, "a019");
            return (Criteria) this;
        }

        public Criteria andA019Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A019 between", value1, value2, "a019");
            return (Criteria) this;
        }

        public Criteria andA019NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A019 not between", value1, value2, "a019");
            return (Criteria) this;
        }

        public Criteria andA020IsNull() {
            addCriterion("A020 is null");
            return (Criteria) this;
        }

        public Criteria andA020IsNotNull() {
            addCriterion("A020 is not null");
            return (Criteria) this;
        }

        public Criteria andA020EqualTo(Date value) {
            addCriterionForJDBCDate("A020 =", value, "a020");
            return (Criteria) this;
        }

        public Criteria andA020NotEqualTo(Date value) {
            addCriterionForJDBCDate("A020 <>", value, "a020");
            return (Criteria) this;
        }

        public Criteria andA020GreaterThan(Date value) {
            addCriterionForJDBCDate("A020 >", value, "a020");
            return (Criteria) this;
        }

        public Criteria andA020GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A020 >=", value, "a020");
            return (Criteria) this;
        }

        public Criteria andA020LessThan(Date value) {
            addCriterionForJDBCDate("A020 <", value, "a020");
            return (Criteria) this;
        }

        public Criteria andA020LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A020 <=", value, "a020");
            return (Criteria) this;
        }

        public Criteria andA020In(List<Date> values) {
            addCriterionForJDBCDate("A020 in", values, "a020");
            return (Criteria) this;
        }

        public Criteria andA020NotIn(List<Date> values) {
            addCriterionForJDBCDate("A020 not in", values, "a020");
            return (Criteria) this;
        }

        public Criteria andA020Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A020 between", value1, value2, "a020");
            return (Criteria) this;
        }

        public Criteria andA020NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A020 not between", value1, value2, "a020");
            return (Criteria) this;
        }

        public Criteria andA097IsNull() {
            addCriterion("A097 is null");
            return (Criteria) this;
        }

        public Criteria andA097IsNotNull() {
            addCriterion("A097 is not null");
            return (Criteria) this;
        }

        public Criteria andA097EqualTo(String value) {
            addCriterion("A097 =", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097NotEqualTo(String value) {
            addCriterion("A097 <>", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097GreaterThan(String value) {
            addCriterion("A097 >", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097GreaterThanOrEqualTo(String value) {
            addCriterion("A097 >=", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097LessThan(String value) {
            addCriterion("A097 <", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097LessThanOrEqualTo(String value) {
            addCriterion("A097 <=", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097Like(String value) {
            addCriterion("A097 like", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097NotLike(String value) {
            addCriterion("A097 not like", value, "a097");
            return (Criteria) this;
        }

        public Criteria andA097In(List<String> values) {
            addCriterion("A097 in", values, "a097");
            return (Criteria) this;
        }

        public Criteria andA097NotIn(List<String> values) {
            addCriterion("A097 not in", values, "a097");
            return (Criteria) this;
        }

        public Criteria andA097Between(String value1, String value2) {
            addCriterion("A097 between", value1, value2, "a097");
            return (Criteria) this;
        }

        public Criteria andA097NotBetween(String value1, String value2) {
            addCriterion("A097 not between", value1, value2, "a097");
            return (Criteria) this;
        }

        public Criteria andA011IsNull() {
            addCriterion("A011 is null");
            return (Criteria) this;
        }

        public Criteria andA011IsNotNull() {
            addCriterion("A011 is not null");
            return (Criteria) this;
        }

        public Criteria andA011EqualTo(BigDecimal value) {
            addCriterion("A011 =", value, "a011");
            return (Criteria) this;
        }

        public Criteria andA011NotEqualTo(BigDecimal value) {
            addCriterion("A011 <>", value, "a011");
            return (Criteria) this;
        }

        public Criteria andA011GreaterThan(BigDecimal value) {
            addCriterion("A011 >", value, "a011");
            return (Criteria) this;
        }

        public Criteria andA011GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A011 >=", value, "a011");
            return (Criteria) this;
        }

        public Criteria andA011LessThan(BigDecimal value) {
            addCriterion("A011 <", value, "a011");
            return (Criteria) this;
        }

        public Criteria andA011LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A011 <=", value, "a011");
            return (Criteria) this;
        }

        public Criteria andA011In(List<BigDecimal> values) {
            addCriterion("A011 in", values, "a011");
            return (Criteria) this;
        }

        public Criteria andA011NotIn(List<BigDecimal> values) {
            addCriterion("A011 not in", values, "a011");
            return (Criteria) this;
        }

        public Criteria andA011Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A011 between", value1, value2, "a011");
            return (Criteria) this;
        }

        public Criteria andA011NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A011 not between", value1, value2, "a011");
            return (Criteria) this;
        }

        public Criteria andA034IsNull() {
            addCriterion("A034 is null");
            return (Criteria) this;
        }

        public Criteria andA034IsNotNull() {
            addCriterion("A034 is not null");
            return (Criteria) this;
        }

        public Criteria andA034EqualTo(BigDecimal value) {
            addCriterion("A034 =", value, "a034");
            return (Criteria) this;
        }

        public Criteria andA034NotEqualTo(BigDecimal value) {
            addCriterion("A034 <>", value, "a034");
            return (Criteria) this;
        }

        public Criteria andA034GreaterThan(BigDecimal value) {
            addCriterion("A034 >", value, "a034");
            return (Criteria) this;
        }

        public Criteria andA034GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A034 >=", value, "a034");
            return (Criteria) this;
        }

        public Criteria andA034LessThan(BigDecimal value) {
            addCriterion("A034 <", value, "a034");
            return (Criteria) this;
        }

        public Criteria andA034LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A034 <=", value, "a034");
            return (Criteria) this;
        }

        public Criteria andA034In(List<BigDecimal> values) {
            addCriterion("A034 in", values, "a034");
            return (Criteria) this;
        }

        public Criteria andA034NotIn(List<BigDecimal> values) {
            addCriterion("A034 not in", values, "a034");
            return (Criteria) this;
        }

        public Criteria andA034Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A034 between", value1, value2, "a034");
            return (Criteria) this;
        }

        public Criteria andA034NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A034 not between", value1, value2, "a034");
            return (Criteria) this;
        }

        public Criteria andA035IsNull() {
            addCriterion("A035 is null");
            return (Criteria) this;
        }

        public Criteria andA035IsNotNull() {
            addCriterion("A035 is not null");
            return (Criteria) this;
        }

        public Criteria andA035EqualTo(BigDecimal value) {
            addCriterion("A035 =", value, "a035");
            return (Criteria) this;
        }

        public Criteria andA035NotEqualTo(BigDecimal value) {
            addCriterion("A035 <>", value, "a035");
            return (Criteria) this;
        }

        public Criteria andA035GreaterThan(BigDecimal value) {
            addCriterion("A035 >", value, "a035");
            return (Criteria) this;
        }

        public Criteria andA035GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A035 >=", value, "a035");
            return (Criteria) this;
        }

        public Criteria andA035LessThan(BigDecimal value) {
            addCriterion("A035 <", value, "a035");
            return (Criteria) this;
        }

        public Criteria andA035LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A035 <=", value, "a035");
            return (Criteria) this;
        }

        public Criteria andA035In(List<BigDecimal> values) {
            addCriterion("A035 in", values, "a035");
            return (Criteria) this;
        }

        public Criteria andA035NotIn(List<BigDecimal> values) {
            addCriterion("A035 not in", values, "a035");
            return (Criteria) this;
        }

        public Criteria andA035Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A035 between", value1, value2, "a035");
            return (Criteria) this;
        }

        public Criteria andA035NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A035 not between", value1, value2, "a035");
            return (Criteria) this;
        }

        public Criteria andA102IsNull() {
            addCriterion("A102 is null");
            return (Criteria) this;
        }

        public Criteria andA102IsNotNull() {
            addCriterion("A102 is not null");
            return (Criteria) this;
        }

        public Criteria andA102EqualTo(BigDecimal value) {
            addCriterion("A102 =", value, "a102");
            return (Criteria) this;
        }

        public Criteria andA102NotEqualTo(BigDecimal value) {
            addCriterion("A102 <>", value, "a102");
            return (Criteria) this;
        }

        public Criteria andA102GreaterThan(BigDecimal value) {
            addCriterion("A102 >", value, "a102");
            return (Criteria) this;
        }

        public Criteria andA102GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A102 >=", value, "a102");
            return (Criteria) this;
        }

        public Criteria andA102LessThan(BigDecimal value) {
            addCriterion("A102 <", value, "a102");
            return (Criteria) this;
        }

        public Criteria andA102LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A102 <=", value, "a102");
            return (Criteria) this;
        }

        public Criteria andA102In(List<BigDecimal> values) {
            addCriterion("A102 in", values, "a102");
            return (Criteria) this;
        }

        public Criteria andA102NotIn(List<BigDecimal> values) {
            addCriterion("A102 not in", values, "a102");
            return (Criteria) this;
        }

        public Criteria andA102Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A102 between", value1, value2, "a102");
            return (Criteria) this;
        }

        public Criteria andA102NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A102 not between", value1, value2, "a102");
            return (Criteria) this;
        }

        public Criteria andA106IsNull() {
            addCriterion("A106 is null");
            return (Criteria) this;
        }

        public Criteria andA106IsNotNull() {
            addCriterion("A106 is not null");
            return (Criteria) this;
        }

        public Criteria andA106EqualTo(BigDecimal value) {
            addCriterion("A106 =", value, "a106");
            return (Criteria) this;
        }

        public Criteria andA106NotEqualTo(BigDecimal value) {
            addCriterion("A106 <>", value, "a106");
            return (Criteria) this;
        }

        public Criteria andA106GreaterThan(BigDecimal value) {
            addCriterion("A106 >", value, "a106");
            return (Criteria) this;
        }

        public Criteria andA106GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A106 >=", value, "a106");
            return (Criteria) this;
        }

        public Criteria andA106LessThan(BigDecimal value) {
            addCriterion("A106 <", value, "a106");
            return (Criteria) this;
        }

        public Criteria andA106LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A106 <=", value, "a106");
            return (Criteria) this;
        }

        public Criteria andA106In(List<BigDecimal> values) {
            addCriterion("A106 in", values, "a106");
            return (Criteria) this;
        }

        public Criteria andA106NotIn(List<BigDecimal> values) {
            addCriterion("A106 not in", values, "a106");
            return (Criteria) this;
        }

        public Criteria andA106Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A106 between", value1, value2, "a106");
            return (Criteria) this;
        }

        public Criteria andA106NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A106 not between", value1, value2, "a106");
            return (Criteria) this;
        }

        public Criteria andA036IsNull() {
            addCriterion("A036 is null");
            return (Criteria) this;
        }

        public Criteria andA036IsNotNull() {
            addCriterion("A036 is not null");
            return (Criteria) this;
        }

        public Criteria andA036EqualTo(BigDecimal value) {
            addCriterion("A036 =", value, "a036");
            return (Criteria) this;
        }

        public Criteria andA036NotEqualTo(BigDecimal value) {
            addCriterion("A036 <>", value, "a036");
            return (Criteria) this;
        }

        public Criteria andA036GreaterThan(BigDecimal value) {
            addCriterion("A036 >", value, "a036");
            return (Criteria) this;
        }

        public Criteria andA036GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A036 >=", value, "a036");
            return (Criteria) this;
        }

        public Criteria andA036LessThan(BigDecimal value) {
            addCriterion("A036 <", value, "a036");
            return (Criteria) this;
        }

        public Criteria andA036LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A036 <=", value, "a036");
            return (Criteria) this;
        }

        public Criteria andA036In(List<BigDecimal> values) {
            addCriterion("A036 in", values, "a036");
            return (Criteria) this;
        }

        public Criteria andA036NotIn(List<BigDecimal> values) {
            addCriterion("A036 not in", values, "a036");
            return (Criteria) this;
        }

        public Criteria andA036Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A036 between", value1, value2, "a036");
            return (Criteria) this;
        }

        public Criteria andA036NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A036 not between", value1, value2, "a036");
            return (Criteria) this;
        }

        public Criteria andA038IsNull() {
            addCriterion("A038 is null");
            return (Criteria) this;
        }

        public Criteria andA038IsNotNull() {
            addCriterion("A038 is not null");
            return (Criteria) this;
        }

        public Criteria andA038EqualTo(BigDecimal value) {
            addCriterion("A038 =", value, "a038");
            return (Criteria) this;
        }

        public Criteria andA038NotEqualTo(BigDecimal value) {
            addCriterion("A038 <>", value, "a038");
            return (Criteria) this;
        }

        public Criteria andA038GreaterThan(BigDecimal value) {
            addCriterion("A038 >", value, "a038");
            return (Criteria) this;
        }

        public Criteria andA038GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A038 >=", value, "a038");
            return (Criteria) this;
        }

        public Criteria andA038LessThan(BigDecimal value) {
            addCriterion("A038 <", value, "a038");
            return (Criteria) this;
        }

        public Criteria andA038LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A038 <=", value, "a038");
            return (Criteria) this;
        }

        public Criteria andA038In(List<BigDecimal> values) {
            addCriterion("A038 in", values, "a038");
            return (Criteria) this;
        }

        public Criteria andA038NotIn(List<BigDecimal> values) {
            addCriterion("A038 not in", values, "a038");
            return (Criteria) this;
        }

        public Criteria andA038Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A038 between", value1, value2, "a038");
            return (Criteria) this;
        }

        public Criteria andA038NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A038 not between", value1, value2, "a038");
            return (Criteria) this;
        }

        public Criteria andA039IsNull() {
            addCriterion("A039 is null");
            return (Criteria) this;
        }

        public Criteria andA039IsNotNull() {
            addCriterion("A039 is not null");
            return (Criteria) this;
        }

        public Criteria andA039EqualTo(BigDecimal value) {
            addCriterion("A039 =", value, "a039");
            return (Criteria) this;
        }

        public Criteria andA039NotEqualTo(BigDecimal value) {
            addCriterion("A039 <>", value, "a039");
            return (Criteria) this;
        }

        public Criteria andA039GreaterThan(BigDecimal value) {
            addCriterion("A039 >", value, "a039");
            return (Criteria) this;
        }

        public Criteria andA039GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A039 >=", value, "a039");
            return (Criteria) this;
        }

        public Criteria andA039LessThan(BigDecimal value) {
            addCriterion("A039 <", value, "a039");
            return (Criteria) this;
        }

        public Criteria andA039LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A039 <=", value, "a039");
            return (Criteria) this;
        }

        public Criteria andA039In(List<BigDecimal> values) {
            addCriterion("A039 in", values, "a039");
            return (Criteria) this;
        }

        public Criteria andA039NotIn(List<BigDecimal> values) {
            addCriterion("A039 not in", values, "a039");
            return (Criteria) this;
        }

        public Criteria andA039Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A039 between", value1, value2, "a039");
            return (Criteria) this;
        }

        public Criteria andA039NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A039 not between", value1, value2, "a039");
            return (Criteria) this;
        }

        public Criteria andA066IsNull() {
            addCriterion("A066 is null");
            return (Criteria) this;
        }

        public Criteria andA066IsNotNull() {
            addCriterion("A066 is not null");
            return (Criteria) this;
        }

        public Criteria andA066EqualTo(BigDecimal value) {
            addCriterion("A066 =", value, "a066");
            return (Criteria) this;
        }

        public Criteria andA066NotEqualTo(BigDecimal value) {
            addCriterion("A066 <>", value, "a066");
            return (Criteria) this;
        }

        public Criteria andA066GreaterThan(BigDecimal value) {
            addCriterion("A066 >", value, "a066");
            return (Criteria) this;
        }

        public Criteria andA066GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A066 >=", value, "a066");
            return (Criteria) this;
        }

        public Criteria andA066LessThan(BigDecimal value) {
            addCriterion("A066 <", value, "a066");
            return (Criteria) this;
        }

        public Criteria andA066LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A066 <=", value, "a066");
            return (Criteria) this;
        }

        public Criteria andA066In(List<BigDecimal> values) {
            addCriterion("A066 in", values, "a066");
            return (Criteria) this;
        }

        public Criteria andA066NotIn(List<BigDecimal> values) {
            addCriterion("A066 not in", values, "a066");
            return (Criteria) this;
        }

        public Criteria andA066Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A066 between", value1, value2, "a066");
            return (Criteria) this;
        }

        public Criteria andA066NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A066 not between", value1, value2, "a066");
            return (Criteria) this;
        }

        public Criteria andA067IsNull() {
            addCriterion("A067 is null");
            return (Criteria) this;
        }

        public Criteria andA067IsNotNull() {
            addCriterion("A067 is not null");
            return (Criteria) this;
        }

        public Criteria andA067EqualTo(BigDecimal value) {
            addCriterion("A067 =", value, "a067");
            return (Criteria) this;
        }

        public Criteria andA067NotEqualTo(BigDecimal value) {
            addCriterion("A067 <>", value, "a067");
            return (Criteria) this;
        }

        public Criteria andA067GreaterThan(BigDecimal value) {
            addCriterion("A067 >", value, "a067");
            return (Criteria) this;
        }

        public Criteria andA067GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A067 >=", value, "a067");
            return (Criteria) this;
        }

        public Criteria andA067LessThan(BigDecimal value) {
            addCriterion("A067 <", value, "a067");
            return (Criteria) this;
        }

        public Criteria andA067LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A067 <=", value, "a067");
            return (Criteria) this;
        }

        public Criteria andA067In(List<BigDecimal> values) {
            addCriterion("A067 in", values, "a067");
            return (Criteria) this;
        }

        public Criteria andA067NotIn(List<BigDecimal> values) {
            addCriterion("A067 not in", values, "a067");
            return (Criteria) this;
        }

        public Criteria andA067Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A067 between", value1, value2, "a067");
            return (Criteria) this;
        }

        public Criteria andA067NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A067 not between", value1, value2, "a067");
            return (Criteria) this;
        }

        public Criteria andA068IsNull() {
            addCriterion("A068 is null");
            return (Criteria) this;
        }

        public Criteria andA068IsNotNull() {
            addCriterion("A068 is not null");
            return (Criteria) this;
        }

        public Criteria andA068EqualTo(BigDecimal value) {
            addCriterion("A068 =", value, "a068");
            return (Criteria) this;
        }

        public Criteria andA068NotEqualTo(BigDecimal value) {
            addCriterion("A068 <>", value, "a068");
            return (Criteria) this;
        }

        public Criteria andA068GreaterThan(BigDecimal value) {
            addCriterion("A068 >", value, "a068");
            return (Criteria) this;
        }

        public Criteria andA068GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A068 >=", value, "a068");
            return (Criteria) this;
        }

        public Criteria andA068LessThan(BigDecimal value) {
            addCriterion("A068 <", value, "a068");
            return (Criteria) this;
        }

        public Criteria andA068LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A068 <=", value, "a068");
            return (Criteria) this;
        }

        public Criteria andA068In(List<BigDecimal> values) {
            addCriterion("A068 in", values, "a068");
            return (Criteria) this;
        }

        public Criteria andA068NotIn(List<BigDecimal> values) {
            addCriterion("A068 not in", values, "a068");
            return (Criteria) this;
        }

        public Criteria andA068Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A068 between", value1, value2, "a068");
            return (Criteria) this;
        }

        public Criteria andA068NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A068 not between", value1, value2, "a068");
            return (Criteria) this;
        }

        public Criteria andA061IsNull() {
            addCriterion("A061 is null");
            return (Criteria) this;
        }

        public Criteria andA061IsNotNull() {
            addCriterion("A061 is not null");
            return (Criteria) this;
        }

        public Criteria andA061EqualTo(BigDecimal value) {
            addCriterion("A061 =", value, "a061");
            return (Criteria) this;
        }

        public Criteria andA061NotEqualTo(BigDecimal value) {
            addCriterion("A061 <>", value, "a061");
            return (Criteria) this;
        }

        public Criteria andA061GreaterThan(BigDecimal value) {
            addCriterion("A061 >", value, "a061");
            return (Criteria) this;
        }

        public Criteria andA061GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A061 >=", value, "a061");
            return (Criteria) this;
        }

        public Criteria andA061LessThan(BigDecimal value) {
            addCriterion("A061 <", value, "a061");
            return (Criteria) this;
        }

        public Criteria andA061LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A061 <=", value, "a061");
            return (Criteria) this;
        }

        public Criteria andA061In(List<BigDecimal> values) {
            addCriterion("A061 in", values, "a061");
            return (Criteria) this;
        }

        public Criteria andA061NotIn(List<BigDecimal> values) {
            addCriterion("A061 not in", values, "a061");
            return (Criteria) this;
        }

        public Criteria andA061Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A061 between", value1, value2, "a061");
            return (Criteria) this;
        }

        public Criteria andA061NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A061 not between", value1, value2, "a061");
            return (Criteria) this;
        }

        public Criteria andA062IsNull() {
            addCriterion("A062 is null");
            return (Criteria) this;
        }

        public Criteria andA062IsNotNull() {
            addCriterion("A062 is not null");
            return (Criteria) this;
        }

        public Criteria andA062EqualTo(BigDecimal value) {
            addCriterion("A062 =", value, "a062");
            return (Criteria) this;
        }

        public Criteria andA062NotEqualTo(BigDecimal value) {
            addCriterion("A062 <>", value, "a062");
            return (Criteria) this;
        }

        public Criteria andA062GreaterThan(BigDecimal value) {
            addCriterion("A062 >", value, "a062");
            return (Criteria) this;
        }

        public Criteria andA062GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A062 >=", value, "a062");
            return (Criteria) this;
        }

        public Criteria andA062LessThan(BigDecimal value) {
            addCriterion("A062 <", value, "a062");
            return (Criteria) this;
        }

        public Criteria andA062LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A062 <=", value, "a062");
            return (Criteria) this;
        }

        public Criteria andA062In(List<BigDecimal> values) {
            addCriterion("A062 in", values, "a062");
            return (Criteria) this;
        }

        public Criteria andA062NotIn(List<BigDecimal> values) {
            addCriterion("A062 not in", values, "a062");
            return (Criteria) this;
        }

        public Criteria andA062Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A062 between", value1, value2, "a062");
            return (Criteria) this;
        }

        public Criteria andA062NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A062 not between", value1, value2, "a062");
            return (Criteria) this;
        }

        public Criteria andA040IsNull() {
            addCriterion("A040 is null");
            return (Criteria) this;
        }

        public Criteria andA040IsNotNull() {
            addCriterion("A040 is not null");
            return (Criteria) this;
        }

        public Criteria andA040EqualTo(BigDecimal value) {
            addCriterion("A040 =", value, "a040");
            return (Criteria) this;
        }

        public Criteria andA040NotEqualTo(BigDecimal value) {
            addCriterion("A040 <>", value, "a040");
            return (Criteria) this;
        }

        public Criteria andA040GreaterThan(BigDecimal value) {
            addCriterion("A040 >", value, "a040");
            return (Criteria) this;
        }

        public Criteria andA040GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A040 >=", value, "a040");
            return (Criteria) this;
        }

        public Criteria andA040LessThan(BigDecimal value) {
            addCriterion("A040 <", value, "a040");
            return (Criteria) this;
        }

        public Criteria andA040LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A040 <=", value, "a040");
            return (Criteria) this;
        }

        public Criteria andA040In(List<BigDecimal> values) {
            addCriterion("A040 in", values, "a040");
            return (Criteria) this;
        }

        public Criteria andA040NotIn(List<BigDecimal> values) {
            addCriterion("A040 not in", values, "a040");
            return (Criteria) this;
        }

        public Criteria andA040Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A040 between", value1, value2, "a040");
            return (Criteria) this;
        }

        public Criteria andA040NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A040 not between", value1, value2, "a040");
            return (Criteria) this;
        }

        public Criteria andA041IsNull() {
            addCriterion("A041 is null");
            return (Criteria) this;
        }

        public Criteria andA041IsNotNull() {
            addCriterion("A041 is not null");
            return (Criteria) this;
        }

        public Criteria andA041EqualTo(BigDecimal value) {
            addCriterion("A041 =", value, "a041");
            return (Criteria) this;
        }

        public Criteria andA041NotEqualTo(BigDecimal value) {
            addCriterion("A041 <>", value, "a041");
            return (Criteria) this;
        }

        public Criteria andA041GreaterThan(BigDecimal value) {
            addCriterion("A041 >", value, "a041");
            return (Criteria) this;
        }

        public Criteria andA041GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A041 >=", value, "a041");
            return (Criteria) this;
        }

        public Criteria andA041LessThan(BigDecimal value) {
            addCriterion("A041 <", value, "a041");
            return (Criteria) this;
        }

        public Criteria andA041LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A041 <=", value, "a041");
            return (Criteria) this;
        }

        public Criteria andA041In(List<BigDecimal> values) {
            addCriterion("A041 in", values, "a041");
            return (Criteria) this;
        }

        public Criteria andA041NotIn(List<BigDecimal> values) {
            addCriterion("A041 not in", values, "a041");
            return (Criteria) this;
        }

        public Criteria andA041Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A041 between", value1, value2, "a041");
            return (Criteria) this;
        }

        public Criteria andA041NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A041 not between", value1, value2, "a041");
            return (Criteria) this;
        }

        public Criteria andA042IsNull() {
            addCriterion("A042 is null");
            return (Criteria) this;
        }

        public Criteria andA042IsNotNull() {
            addCriterion("A042 is not null");
            return (Criteria) this;
        }

        public Criteria andA042EqualTo(BigDecimal value) {
            addCriterion("A042 =", value, "a042");
            return (Criteria) this;
        }

        public Criteria andA042NotEqualTo(BigDecimal value) {
            addCriterion("A042 <>", value, "a042");
            return (Criteria) this;
        }

        public Criteria andA042GreaterThan(BigDecimal value) {
            addCriterion("A042 >", value, "a042");
            return (Criteria) this;
        }

        public Criteria andA042GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A042 >=", value, "a042");
            return (Criteria) this;
        }

        public Criteria andA042LessThan(BigDecimal value) {
            addCriterion("A042 <", value, "a042");
            return (Criteria) this;
        }

        public Criteria andA042LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A042 <=", value, "a042");
            return (Criteria) this;
        }

        public Criteria andA042In(List<BigDecimal> values) {
            addCriterion("A042 in", values, "a042");
            return (Criteria) this;
        }

        public Criteria andA042NotIn(List<BigDecimal> values) {
            addCriterion("A042 not in", values, "a042");
            return (Criteria) this;
        }

        public Criteria andA042Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A042 between", value1, value2, "a042");
            return (Criteria) this;
        }

        public Criteria andA042NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A042 not between", value1, value2, "a042");
            return (Criteria) this;
        }

        public Criteria andA043IsNull() {
            addCriterion("A043 is null");
            return (Criteria) this;
        }

        public Criteria andA043IsNotNull() {
            addCriterion("A043 is not null");
            return (Criteria) this;
        }

        public Criteria andA043EqualTo(BigDecimal value) {
            addCriterion("A043 =", value, "a043");
            return (Criteria) this;
        }

        public Criteria andA043NotEqualTo(BigDecimal value) {
            addCriterion("A043 <>", value, "a043");
            return (Criteria) this;
        }

        public Criteria andA043GreaterThan(BigDecimal value) {
            addCriterion("A043 >", value, "a043");
            return (Criteria) this;
        }

        public Criteria andA043GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A043 >=", value, "a043");
            return (Criteria) this;
        }

        public Criteria andA043LessThan(BigDecimal value) {
            addCriterion("A043 <", value, "a043");
            return (Criteria) this;
        }

        public Criteria andA043LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A043 <=", value, "a043");
            return (Criteria) this;
        }

        public Criteria andA043In(List<BigDecimal> values) {
            addCriterion("A043 in", values, "a043");
            return (Criteria) this;
        }

        public Criteria andA043NotIn(List<BigDecimal> values) {
            addCriterion("A043 not in", values, "a043");
            return (Criteria) this;
        }

        public Criteria andA043Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A043 between", value1, value2, "a043");
            return (Criteria) this;
        }

        public Criteria andA043NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A043 not between", value1, value2, "a043");
            return (Criteria) this;
        }

        public Criteria andA044IsNull() {
            addCriterion("A044 is null");
            return (Criteria) this;
        }

        public Criteria andA044IsNotNull() {
            addCriterion("A044 is not null");
            return (Criteria) this;
        }

        public Criteria andA044EqualTo(BigDecimal value) {
            addCriterion("A044 =", value, "a044");
            return (Criteria) this;
        }

        public Criteria andA044NotEqualTo(BigDecimal value) {
            addCriterion("A044 <>", value, "a044");
            return (Criteria) this;
        }

        public Criteria andA044GreaterThan(BigDecimal value) {
            addCriterion("A044 >", value, "a044");
            return (Criteria) this;
        }

        public Criteria andA044GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A044 >=", value, "a044");
            return (Criteria) this;
        }

        public Criteria andA044LessThan(BigDecimal value) {
            addCriterion("A044 <", value, "a044");
            return (Criteria) this;
        }

        public Criteria andA044LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A044 <=", value, "a044");
            return (Criteria) this;
        }

        public Criteria andA044In(List<BigDecimal> values) {
            addCriterion("A044 in", values, "a044");
            return (Criteria) this;
        }

        public Criteria andA044NotIn(List<BigDecimal> values) {
            addCriterion("A044 not in", values, "a044");
            return (Criteria) this;
        }

        public Criteria andA044Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A044 between", value1, value2, "a044");
            return (Criteria) this;
        }

        public Criteria andA044NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A044 not between", value1, value2, "a044");
            return (Criteria) this;
        }

        public Criteria andA045IsNull() {
            addCriterion("A045 is null");
            return (Criteria) this;
        }

        public Criteria andA045IsNotNull() {
            addCriterion("A045 is not null");
            return (Criteria) this;
        }

        public Criteria andA045EqualTo(BigDecimal value) {
            addCriterion("A045 =", value, "a045");
            return (Criteria) this;
        }

        public Criteria andA045NotEqualTo(BigDecimal value) {
            addCriterion("A045 <>", value, "a045");
            return (Criteria) this;
        }

        public Criteria andA045GreaterThan(BigDecimal value) {
            addCriterion("A045 >", value, "a045");
            return (Criteria) this;
        }

        public Criteria andA045GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A045 >=", value, "a045");
            return (Criteria) this;
        }

        public Criteria andA045LessThan(BigDecimal value) {
            addCriterion("A045 <", value, "a045");
            return (Criteria) this;
        }

        public Criteria andA045LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A045 <=", value, "a045");
            return (Criteria) this;
        }

        public Criteria andA045In(List<BigDecimal> values) {
            addCriterion("A045 in", values, "a045");
            return (Criteria) this;
        }

        public Criteria andA045NotIn(List<BigDecimal> values) {
            addCriterion("A045 not in", values, "a045");
            return (Criteria) this;
        }

        public Criteria andA045Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A045 between", value1, value2, "a045");
            return (Criteria) this;
        }

        public Criteria andA045NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A045 not between", value1, value2, "a045");
            return (Criteria) this;
        }

        public Criteria andA048IsNull() {
            addCriterion("A048 is null");
            return (Criteria) this;
        }

        public Criteria andA048IsNotNull() {
            addCriterion("A048 is not null");
            return (Criteria) this;
        }

        public Criteria andA048EqualTo(BigDecimal value) {
            addCriterion("A048 =", value, "a048");
            return (Criteria) this;
        }

        public Criteria andA048NotEqualTo(BigDecimal value) {
            addCriterion("A048 <>", value, "a048");
            return (Criteria) this;
        }

        public Criteria andA048GreaterThan(BigDecimal value) {
            addCriterion("A048 >", value, "a048");
            return (Criteria) this;
        }

        public Criteria andA048GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A048 >=", value, "a048");
            return (Criteria) this;
        }

        public Criteria andA048LessThan(BigDecimal value) {
            addCriterion("A048 <", value, "a048");
            return (Criteria) this;
        }

        public Criteria andA048LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A048 <=", value, "a048");
            return (Criteria) this;
        }

        public Criteria andA048In(List<BigDecimal> values) {
            addCriterion("A048 in", values, "a048");
            return (Criteria) this;
        }

        public Criteria andA048NotIn(List<BigDecimal> values) {
            addCriterion("A048 not in", values, "a048");
            return (Criteria) this;
        }

        public Criteria andA048Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A048 between", value1, value2, "a048");
            return (Criteria) this;
        }

        public Criteria andA048NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A048 not between", value1, value2, "a048");
            return (Criteria) this;
        }

        public Criteria andA049IsNull() {
            addCriterion("A049 is null");
            return (Criteria) this;
        }

        public Criteria andA049IsNotNull() {
            addCriterion("A049 is not null");
            return (Criteria) this;
        }

        public Criteria andA049EqualTo(BigDecimal value) {
            addCriterion("A049 =", value, "a049");
            return (Criteria) this;
        }

        public Criteria andA049NotEqualTo(BigDecimal value) {
            addCriterion("A049 <>", value, "a049");
            return (Criteria) this;
        }

        public Criteria andA049GreaterThan(BigDecimal value) {
            addCriterion("A049 >", value, "a049");
            return (Criteria) this;
        }

        public Criteria andA049GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A049 >=", value, "a049");
            return (Criteria) this;
        }

        public Criteria andA049LessThan(BigDecimal value) {
            addCriterion("A049 <", value, "a049");
            return (Criteria) this;
        }

        public Criteria andA049LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A049 <=", value, "a049");
            return (Criteria) this;
        }

        public Criteria andA049In(List<BigDecimal> values) {
            addCriterion("A049 in", values, "a049");
            return (Criteria) this;
        }

        public Criteria andA049NotIn(List<BigDecimal> values) {
            addCriterion("A049 not in", values, "a049");
            return (Criteria) this;
        }

        public Criteria andA049Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A049 between", value1, value2, "a049");
            return (Criteria) this;
        }

        public Criteria andA049NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A049 not between", value1, value2, "a049");
            return (Criteria) this;
        }

        public Criteria andA051IsNull() {
            addCriterion("A051 is null");
            return (Criteria) this;
        }

        public Criteria andA051IsNotNull() {
            addCriterion("A051 is not null");
            return (Criteria) this;
        }

        public Criteria andA051EqualTo(BigDecimal value) {
            addCriterion("A051 =", value, "a051");
            return (Criteria) this;
        }

        public Criteria andA051NotEqualTo(BigDecimal value) {
            addCriterion("A051 <>", value, "a051");
            return (Criteria) this;
        }

        public Criteria andA051GreaterThan(BigDecimal value) {
            addCriterion("A051 >", value, "a051");
            return (Criteria) this;
        }

        public Criteria andA051GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A051 >=", value, "a051");
            return (Criteria) this;
        }

        public Criteria andA051LessThan(BigDecimal value) {
            addCriterion("A051 <", value, "a051");
            return (Criteria) this;
        }

        public Criteria andA051LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A051 <=", value, "a051");
            return (Criteria) this;
        }

        public Criteria andA051In(List<BigDecimal> values) {
            addCriterion("A051 in", values, "a051");
            return (Criteria) this;
        }

        public Criteria andA051NotIn(List<BigDecimal> values) {
            addCriterion("A051 not in", values, "a051");
            return (Criteria) this;
        }

        public Criteria andA051Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A051 between", value1, value2, "a051");
            return (Criteria) this;
        }

        public Criteria andA051NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A051 not between", value1, value2, "a051");
            return (Criteria) this;
        }

        public Criteria andA052IsNull() {
            addCriterion("A052 is null");
            return (Criteria) this;
        }

        public Criteria andA052IsNotNull() {
            addCriterion("A052 is not null");
            return (Criteria) this;
        }

        public Criteria andA052EqualTo(BigDecimal value) {
            addCriterion("A052 =", value, "a052");
            return (Criteria) this;
        }

        public Criteria andA052NotEqualTo(BigDecimal value) {
            addCriterion("A052 <>", value, "a052");
            return (Criteria) this;
        }

        public Criteria andA052GreaterThan(BigDecimal value) {
            addCriterion("A052 >", value, "a052");
            return (Criteria) this;
        }

        public Criteria andA052GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A052 >=", value, "a052");
            return (Criteria) this;
        }

        public Criteria andA052LessThan(BigDecimal value) {
            addCriterion("A052 <", value, "a052");
            return (Criteria) this;
        }

        public Criteria andA052LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A052 <=", value, "a052");
            return (Criteria) this;
        }

        public Criteria andA052In(List<BigDecimal> values) {
            addCriterion("A052 in", values, "a052");
            return (Criteria) this;
        }

        public Criteria andA052NotIn(List<BigDecimal> values) {
            addCriterion("A052 not in", values, "a052");
            return (Criteria) this;
        }

        public Criteria andA052Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A052 between", value1, value2, "a052");
            return (Criteria) this;
        }

        public Criteria andA052NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A052 not between", value1, value2, "a052");
            return (Criteria) this;
        }

        public Criteria andA053IsNull() {
            addCriterion("A053 is null");
            return (Criteria) this;
        }

        public Criteria andA053IsNotNull() {
            addCriterion("A053 is not null");
            return (Criteria) this;
        }

        public Criteria andA053EqualTo(String value) {
            addCriterion("A053 =", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053NotEqualTo(String value) {
            addCriterion("A053 <>", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053GreaterThan(String value) {
            addCriterion("A053 >", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053GreaterThanOrEqualTo(String value) {
            addCriterion("A053 >=", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053LessThan(String value) {
            addCriterion("A053 <", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053LessThanOrEqualTo(String value) {
            addCriterion("A053 <=", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053Like(String value) {
            addCriterion("A053 like", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053NotLike(String value) {
            addCriterion("A053 not like", value, "a053");
            return (Criteria) this;
        }

        public Criteria andA053In(List<String> values) {
            addCriterion("A053 in", values, "a053");
            return (Criteria) this;
        }

        public Criteria andA053NotIn(List<String> values) {
            addCriterion("A053 not in", values, "a053");
            return (Criteria) this;
        }

        public Criteria andA053Between(String value1, String value2) {
            addCriterion("A053 between", value1, value2, "a053");
            return (Criteria) this;
        }

        public Criteria andA053NotBetween(String value1, String value2) {
            addCriterion("A053 not between", value1, value2, "a053");
            return (Criteria) this;
        }

        public Criteria andA070IsNull() {
            addCriterion("A070 is null");
            return (Criteria) this;
        }

        public Criteria andA070IsNotNull() {
            addCriterion("A070 is not null");
            return (Criteria) this;
        }

        public Criteria andA070EqualTo(String value) {
            addCriterion("A070 =", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070NotEqualTo(String value) {
            addCriterion("A070 <>", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070GreaterThan(String value) {
            addCriterion("A070 >", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070GreaterThanOrEqualTo(String value) {
            addCriterion("A070 >=", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070LessThan(String value) {
            addCriterion("A070 <", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070LessThanOrEqualTo(String value) {
            addCriterion("A070 <=", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070Like(String value) {
            addCriterion("A070 like", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070NotLike(String value) {
            addCriterion("A070 not like", value, "a070");
            return (Criteria) this;
        }

        public Criteria andA070In(List<String> values) {
            addCriterion("A070 in", values, "a070");
            return (Criteria) this;
        }

        public Criteria andA070NotIn(List<String> values) {
            addCriterion("A070 not in", values, "a070");
            return (Criteria) this;
        }

        public Criteria andA070Between(String value1, String value2) {
            addCriterion("A070 between", value1, value2, "a070");
            return (Criteria) this;
        }

        public Criteria andA070NotBetween(String value1, String value2) {
            addCriterion("A070 not between", value1, value2, "a070");
            return (Criteria) this;
        }

        public Criteria andA077IsNull() {
            addCriterion("A077 is null");
            return (Criteria) this;
        }

        public Criteria andA077IsNotNull() {
            addCriterion("A077 is not null");
            return (Criteria) this;
        }

        public Criteria andA077EqualTo(String value) {
            addCriterion("A077 =", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077NotEqualTo(String value) {
            addCriterion("A077 <>", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077GreaterThan(String value) {
            addCriterion("A077 >", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077GreaterThanOrEqualTo(String value) {
            addCriterion("A077 >=", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077LessThan(String value) {
            addCriterion("A077 <", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077LessThanOrEqualTo(String value) {
            addCriterion("A077 <=", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077Like(String value) {
            addCriterion("A077 like", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077NotLike(String value) {
            addCriterion("A077 not like", value, "a077");
            return (Criteria) this;
        }

        public Criteria andA077In(List<String> values) {
            addCriterion("A077 in", values, "a077");
            return (Criteria) this;
        }

        public Criteria andA077NotIn(List<String> values) {
            addCriterion("A077 not in", values, "a077");
            return (Criteria) this;
        }

        public Criteria andA077Between(String value1, String value2) {
            addCriterion("A077 between", value1, value2, "a077");
            return (Criteria) this;
        }

        public Criteria andA077NotBetween(String value1, String value2) {
            addCriterion("A077 not between", value1, value2, "a077");
            return (Criteria) this;
        }

        public Criteria andA078IsNull() {
            addCriterion("A078 is null");
            return (Criteria) this;
        }

        public Criteria andA078IsNotNull() {
            addCriterion("A078 is not null");
            return (Criteria) this;
        }

        public Criteria andA078EqualTo(BigDecimal value) {
            addCriterion("A078 =", value, "a078");
            return (Criteria) this;
        }

        public Criteria andA078NotEqualTo(BigDecimal value) {
            addCriterion("A078 <>", value, "a078");
            return (Criteria) this;
        }

        public Criteria andA078GreaterThan(BigDecimal value) {
            addCriterion("A078 >", value, "a078");
            return (Criteria) this;
        }

        public Criteria andA078GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A078 >=", value, "a078");
            return (Criteria) this;
        }

        public Criteria andA078LessThan(BigDecimal value) {
            addCriterion("A078 <", value, "a078");
            return (Criteria) this;
        }

        public Criteria andA078LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A078 <=", value, "a078");
            return (Criteria) this;
        }

        public Criteria andA078In(List<BigDecimal> values) {
            addCriterion("A078 in", values, "a078");
            return (Criteria) this;
        }

        public Criteria andA078NotIn(List<BigDecimal> values) {
            addCriterion("A078 not in", values, "a078");
            return (Criteria) this;
        }

        public Criteria andA078Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078 between", value1, value2, "a078");
            return (Criteria) this;
        }

        public Criteria andA078NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078 not between", value1, value2, "a078");
            return (Criteria) this;
        }

        public Criteria andA084IsNull() {
            addCriterion("A084 is null");
            return (Criteria) this;
        }

        public Criteria andA084IsNotNull() {
            addCriterion("A084 is not null");
            return (Criteria) this;
        }

        public Criteria andA084EqualTo(BigDecimal value) {
            addCriterion("A084 =", value, "a084");
            return (Criteria) this;
        }

        public Criteria andA084NotEqualTo(BigDecimal value) {
            addCriterion("A084 <>", value, "a084");
            return (Criteria) this;
        }

        public Criteria andA084GreaterThan(BigDecimal value) {
            addCriterion("A084 >", value, "a084");
            return (Criteria) this;
        }

        public Criteria andA084GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A084 >=", value, "a084");
            return (Criteria) this;
        }

        public Criteria andA084LessThan(BigDecimal value) {
            addCriterion("A084 <", value, "a084");
            return (Criteria) this;
        }

        public Criteria andA084LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A084 <=", value, "a084");
            return (Criteria) this;
        }

        public Criteria andA084In(List<BigDecimal> values) {
            addCriterion("A084 in", values, "a084");
            return (Criteria) this;
        }

        public Criteria andA084NotIn(List<BigDecimal> values) {
            addCriterion("A084 not in", values, "a084");
            return (Criteria) this;
        }

        public Criteria andA084Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084 between", value1, value2, "a084");
            return (Criteria) this;
        }

        public Criteria andA084NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084 not between", value1, value2, "a084");
            return (Criteria) this;
        }

        public Criteria andA085IsNull() {
            addCriterion("A085 is null");
            return (Criteria) this;
        }

        public Criteria andA085IsNotNull() {
            addCriterion("A085 is not null");
            return (Criteria) this;
        }

        public Criteria andA085EqualTo(BigDecimal value) {
            addCriterion("A085 =", value, "a085");
            return (Criteria) this;
        }

        public Criteria andA085NotEqualTo(BigDecimal value) {
            addCriterion("A085 <>", value, "a085");
            return (Criteria) this;
        }

        public Criteria andA085GreaterThan(BigDecimal value) {
            addCriterion("A085 >", value, "a085");
            return (Criteria) this;
        }

        public Criteria andA085GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A085 >=", value, "a085");
            return (Criteria) this;
        }

        public Criteria andA085LessThan(BigDecimal value) {
            addCriterion("A085 <", value, "a085");
            return (Criteria) this;
        }

        public Criteria andA085LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A085 <=", value, "a085");
            return (Criteria) this;
        }

        public Criteria andA085In(List<BigDecimal> values) {
            addCriterion("A085 in", values, "a085");
            return (Criteria) this;
        }

        public Criteria andA085NotIn(List<BigDecimal> values) {
            addCriterion("A085 not in", values, "a085");
            return (Criteria) this;
        }

        public Criteria andA085Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085 between", value1, value2, "a085");
            return (Criteria) this;
        }

        public Criteria andA085NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085 not between", value1, value2, "a085");
            return (Criteria) this;
        }

        public Criteria andA0781IsNull() {
            addCriterion("A078_1 is null");
            return (Criteria) this;
        }

        public Criteria andA0781IsNotNull() {
            addCriterion("A078_1 is not null");
            return (Criteria) this;
        }

        public Criteria andA0781EqualTo(BigDecimal value) {
            addCriterion("A078_1 =", value, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781NotEqualTo(BigDecimal value) {
            addCriterion("A078_1 <>", value, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781GreaterThan(BigDecimal value) {
            addCriterion("A078_1 >", value, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A078_1 >=", value, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781LessThan(BigDecimal value) {
            addCriterion("A078_1 <", value, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A078_1 <=", value, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781In(List<BigDecimal> values) {
            addCriterion("A078_1 in", values, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781NotIn(List<BigDecimal> values) {
            addCriterion("A078_1 not in", values, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078_1 between", value1, value2, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0781NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078_1 not between", value1, value2, "a0781");
            return (Criteria) this;
        }

        public Criteria andA0841IsNull() {
            addCriterion("A084_1 is null");
            return (Criteria) this;
        }

        public Criteria andA0841IsNotNull() {
            addCriterion("A084_1 is not null");
            return (Criteria) this;
        }

        public Criteria andA0841EqualTo(BigDecimal value) {
            addCriterion("A084_1 =", value, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841NotEqualTo(BigDecimal value) {
            addCriterion("A084_1 <>", value, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841GreaterThan(BigDecimal value) {
            addCriterion("A084_1 >", value, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A084_1 >=", value, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841LessThan(BigDecimal value) {
            addCriterion("A084_1 <", value, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A084_1 <=", value, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841In(List<BigDecimal> values) {
            addCriterion("A084_1 in", values, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841NotIn(List<BigDecimal> values) {
            addCriterion("A084_1 not in", values, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084_1 between", value1, value2, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0841NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084_1 not between", value1, value2, "a0841");
            return (Criteria) this;
        }

        public Criteria andA0851IsNull() {
            addCriterion("A085_1 is null");
            return (Criteria) this;
        }

        public Criteria andA0851IsNotNull() {
            addCriterion("A085_1 is not null");
            return (Criteria) this;
        }

        public Criteria andA0851EqualTo(BigDecimal value) {
            addCriterion("A085_1 =", value, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851NotEqualTo(BigDecimal value) {
            addCriterion("A085_1 <>", value, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851GreaterThan(BigDecimal value) {
            addCriterion("A085_1 >", value, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A085_1 >=", value, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851LessThan(BigDecimal value) {
            addCriterion("A085_1 <", value, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A085_1 <=", value, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851In(List<BigDecimal> values) {
            addCriterion("A085_1 in", values, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851NotIn(List<BigDecimal> values) {
            addCriterion("A085_1 not in", values, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085_1 between", value1, value2, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0851NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085_1 not between", value1, value2, "a0851");
            return (Criteria) this;
        }

        public Criteria andA0782IsNull() {
            addCriterion("A078_2 is null");
            return (Criteria) this;
        }

        public Criteria andA0782IsNotNull() {
            addCriterion("A078_2 is not null");
            return (Criteria) this;
        }

        public Criteria andA0782EqualTo(BigDecimal value) {
            addCriterion("A078_2 =", value, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782NotEqualTo(BigDecimal value) {
            addCriterion("A078_2 <>", value, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782GreaterThan(BigDecimal value) {
            addCriterion("A078_2 >", value, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A078_2 >=", value, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782LessThan(BigDecimal value) {
            addCriterion("A078_2 <", value, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A078_2 <=", value, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782In(List<BigDecimal> values) {
            addCriterion("A078_2 in", values, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782NotIn(List<BigDecimal> values) {
            addCriterion("A078_2 not in", values, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078_2 between", value1, value2, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0782NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078_2 not between", value1, value2, "a0782");
            return (Criteria) this;
        }

        public Criteria andA0842IsNull() {
            addCriterion("A084_2 is null");
            return (Criteria) this;
        }

        public Criteria andA0842IsNotNull() {
            addCriterion("A084_2 is not null");
            return (Criteria) this;
        }

        public Criteria andA0842EqualTo(BigDecimal value) {
            addCriterion("A084_2 =", value, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842NotEqualTo(BigDecimal value) {
            addCriterion("A084_2 <>", value, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842GreaterThan(BigDecimal value) {
            addCriterion("A084_2 >", value, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A084_2 >=", value, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842LessThan(BigDecimal value) {
            addCriterion("A084_2 <", value, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A084_2 <=", value, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842In(List<BigDecimal> values) {
            addCriterion("A084_2 in", values, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842NotIn(List<BigDecimal> values) {
            addCriterion("A084_2 not in", values, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084_2 between", value1, value2, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0842NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084_2 not between", value1, value2, "a0842");
            return (Criteria) this;
        }

        public Criteria andA0852IsNull() {
            addCriterion("A085_2 is null");
            return (Criteria) this;
        }

        public Criteria andA0852IsNotNull() {
            addCriterion("A085_2 is not null");
            return (Criteria) this;
        }

        public Criteria andA0852EqualTo(BigDecimal value) {
            addCriterion("A085_2 =", value, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852NotEqualTo(BigDecimal value) {
            addCriterion("A085_2 <>", value, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852GreaterThan(BigDecimal value) {
            addCriterion("A085_2 >", value, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A085_2 >=", value, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852LessThan(BigDecimal value) {
            addCriterion("A085_2 <", value, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A085_2 <=", value, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852In(List<BigDecimal> values) {
            addCriterion("A085_2 in", values, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852NotIn(List<BigDecimal> values) {
            addCriterion("A085_2 not in", values, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085_2 between", value1, value2, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0852NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085_2 not between", value1, value2, "a0852");
            return (Criteria) this;
        }

        public Criteria andA0783IsNull() {
            addCriterion("A078_3 is null");
            return (Criteria) this;
        }

        public Criteria andA0783IsNotNull() {
            addCriterion("A078_3 is not null");
            return (Criteria) this;
        }

        public Criteria andA0783EqualTo(BigDecimal value) {
            addCriterion("A078_3 =", value, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783NotEqualTo(BigDecimal value) {
            addCriterion("A078_3 <>", value, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783GreaterThan(BigDecimal value) {
            addCriterion("A078_3 >", value, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A078_3 >=", value, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783LessThan(BigDecimal value) {
            addCriterion("A078_3 <", value, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A078_3 <=", value, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783In(List<BigDecimal> values) {
            addCriterion("A078_3 in", values, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783NotIn(List<BigDecimal> values) {
            addCriterion("A078_3 not in", values, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078_3 between", value1, value2, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0783NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A078_3 not between", value1, value2, "a0783");
            return (Criteria) this;
        }

        public Criteria andA0843IsNull() {
            addCriterion("A084_3 is null");
            return (Criteria) this;
        }

        public Criteria andA0843IsNotNull() {
            addCriterion("A084_3 is not null");
            return (Criteria) this;
        }

        public Criteria andA0843EqualTo(BigDecimal value) {
            addCriterion("A084_3 =", value, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843NotEqualTo(BigDecimal value) {
            addCriterion("A084_3 <>", value, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843GreaterThan(BigDecimal value) {
            addCriterion("A084_3 >", value, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A084_3 >=", value, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843LessThan(BigDecimal value) {
            addCriterion("A084_3 <", value, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A084_3 <=", value, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843In(List<BigDecimal> values) {
            addCriterion("A084_3 in", values, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843NotIn(List<BigDecimal> values) {
            addCriterion("A084_3 not in", values, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084_3 between", value1, value2, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0843NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A084_3 not between", value1, value2, "a0843");
            return (Criteria) this;
        }

        public Criteria andA0853IsNull() {
            addCriterion("A085_3 is null");
            return (Criteria) this;
        }

        public Criteria andA0853IsNotNull() {
            addCriterion("A085_3 is not null");
            return (Criteria) this;
        }

        public Criteria andA0853EqualTo(BigDecimal value) {
            addCriterion("A085_3 =", value, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853NotEqualTo(BigDecimal value) {
            addCriterion("A085_3 <>", value, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853GreaterThan(BigDecimal value) {
            addCriterion("A085_3 >", value, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A085_3 >=", value, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853LessThan(BigDecimal value) {
            addCriterion("A085_3 <", value, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A085_3 <=", value, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853In(List<BigDecimal> values) {
            addCriterion("A085_3 in", values, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853NotIn(List<BigDecimal> values) {
            addCriterion("A085_3 not in", values, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085_3 between", value1, value2, "a0853");
            return (Criteria) this;
        }

        public Criteria andA0853NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A085_3 not between", value1, value2, "a0853");
            return (Criteria) this;
        }

        public Criteria andA095IsNull() {
            addCriterion("A095 is null");
            return (Criteria) this;
        }

        public Criteria andA095IsNotNull() {
            addCriterion("A095 is not null");
            return (Criteria) this;
        }

        public Criteria andA095EqualTo(String value) {
            addCriterion("A095 =", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095NotEqualTo(String value) {
            addCriterion("A095 <>", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095GreaterThan(String value) {
            addCriterion("A095 >", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095GreaterThanOrEqualTo(String value) {
            addCriterion("A095 >=", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095LessThan(String value) {
            addCriterion("A095 <", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095LessThanOrEqualTo(String value) {
            addCriterion("A095 <=", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095Like(String value) {
            addCriterion("A095 like", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095NotLike(String value) {
            addCriterion("A095 not like", value, "a095");
            return (Criteria) this;
        }

        public Criteria andA095In(List<String> values) {
            addCriterion("A095 in", values, "a095");
            return (Criteria) this;
        }

        public Criteria andA095NotIn(List<String> values) {
            addCriterion("A095 not in", values, "a095");
            return (Criteria) this;
        }

        public Criteria andA095Between(String value1, String value2) {
            addCriterion("A095 between", value1, value2, "a095");
            return (Criteria) this;
        }

        public Criteria andA095NotBetween(String value1, String value2) {
            addCriterion("A095 not between", value1, value2, "a095");
            return (Criteria) this;
        }

        public Criteria andA100IsNull() {
            addCriterion("A100 is null");
            return (Criteria) this;
        }

        public Criteria andA100IsNotNull() {
            addCriterion("A100 is not null");
            return (Criteria) this;
        }

        public Criteria andA100EqualTo(String value) {
            addCriterion("A100 =", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100NotEqualTo(String value) {
            addCriterion("A100 <>", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100GreaterThan(String value) {
            addCriterion("A100 >", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100GreaterThanOrEqualTo(String value) {
            addCriterion("A100 >=", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100LessThan(String value) {
            addCriterion("A100 <", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100LessThanOrEqualTo(String value) {
            addCriterion("A100 <=", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100Like(String value) {
            addCriterion("A100 like", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100NotLike(String value) {
            addCriterion("A100 not like", value, "a100");
            return (Criteria) this;
        }

        public Criteria andA100In(List<String> values) {
            addCriterion("A100 in", values, "a100");
            return (Criteria) this;
        }

        public Criteria andA100NotIn(List<String> values) {
            addCriterion("A100 not in", values, "a100");
            return (Criteria) this;
        }

        public Criteria andA100Between(String value1, String value2) {
            addCriterion("A100 between", value1, value2, "a100");
            return (Criteria) this;
        }

        public Criteria andA100NotBetween(String value1, String value2) {
            addCriterion("A100 not between", value1, value2, "a100");
            return (Criteria) this;
        }

        public Criteria andA104IsNull() {
            addCriterion("A104 is null");
            return (Criteria) this;
        }

        public Criteria andA104IsNotNull() {
            addCriterion("A104 is not null");
            return (Criteria) this;
        }

        public Criteria andA104EqualTo(String value) {
            addCriterion("A104 =", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104NotEqualTo(String value) {
            addCriterion("A104 <>", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104GreaterThan(String value) {
            addCriterion("A104 >", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104GreaterThanOrEqualTo(String value) {
            addCriterion("A104 >=", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104LessThan(String value) {
            addCriterion("A104 <", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104LessThanOrEqualTo(String value) {
            addCriterion("A104 <=", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104Like(String value) {
            addCriterion("A104 like", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104NotLike(String value) {
            addCriterion("A104 not like", value, "a104");
            return (Criteria) this;
        }

        public Criteria andA104In(List<String> values) {
            addCriterion("A104 in", values, "a104");
            return (Criteria) this;
        }

        public Criteria andA104NotIn(List<String> values) {
            addCriterion("A104 not in", values, "a104");
            return (Criteria) this;
        }

        public Criteria andA104Between(String value1, String value2) {
            addCriterion("A104 between", value1, value2, "a104");
            return (Criteria) this;
        }

        public Criteria andA104NotBetween(String value1, String value2) {
            addCriterion("A104 not between", value1, value2, "a104");
            return (Criteria) this;
        }

        public Criteria andA114IsNull() {
            addCriterion("A114 is null");
            return (Criteria) this;
        }

        public Criteria andA114IsNotNull() {
            addCriterion("A114 is not null");
            return (Criteria) this;
        }

        public Criteria andA114EqualTo(String value) {
            addCriterion("A114 =", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114NotEqualTo(String value) {
            addCriterion("A114 <>", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114GreaterThan(String value) {
            addCriterion("A114 >", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114GreaterThanOrEqualTo(String value) {
            addCriterion("A114 >=", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114LessThan(String value) {
            addCriterion("A114 <", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114LessThanOrEqualTo(String value) {
            addCriterion("A114 <=", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114Like(String value) {
            addCriterion("A114 like", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114NotLike(String value) {
            addCriterion("A114 not like", value, "a114");
            return (Criteria) this;
        }

        public Criteria andA114In(List<String> values) {
            addCriterion("A114 in", values, "a114");
            return (Criteria) this;
        }

        public Criteria andA114NotIn(List<String> values) {
            addCriterion("A114 not in", values, "a114");
            return (Criteria) this;
        }

        public Criteria andA114Between(String value1, String value2) {
            addCriterion("A114 between", value1, value2, "a114");
            return (Criteria) this;
        }

        public Criteria andA114NotBetween(String value1, String value2) {
            addCriterion("A114 not between", value1, value2, "a114");
            return (Criteria) this;
        }

        public Criteria andA107IsNull() {
            addCriterion("A107 is null");
            return (Criteria) this;
        }

        public Criteria andA107IsNotNull() {
            addCriterion("A107 is not null");
            return (Criteria) this;
        }

        public Criteria andA107EqualTo(Date value) {
            addCriterionForJDBCDate("A107 =", value, "a107");
            return (Criteria) this;
        }

        public Criteria andA107NotEqualTo(Date value) {
            addCriterionForJDBCDate("A107 <>", value, "a107");
            return (Criteria) this;
        }

        public Criteria andA107GreaterThan(Date value) {
            addCriterionForJDBCDate("A107 >", value, "a107");
            return (Criteria) this;
        }

        public Criteria andA107GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A107 >=", value, "a107");
            return (Criteria) this;
        }

        public Criteria andA107LessThan(Date value) {
            addCriterionForJDBCDate("A107 <", value, "a107");
            return (Criteria) this;
        }

        public Criteria andA107LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A107 <=", value, "a107");
            return (Criteria) this;
        }

        public Criteria andA107In(List<Date> values) {
            addCriterionForJDBCDate("A107 in", values, "a107");
            return (Criteria) this;
        }

        public Criteria andA107NotIn(List<Date> values) {
            addCriterionForJDBCDate("A107 not in", values, "a107");
            return (Criteria) this;
        }

        public Criteria andA107Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A107 between", value1, value2, "a107");
            return (Criteria) this;
        }

        public Criteria andA107NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A107 not between", value1, value2, "a107");
            return (Criteria) this;
        }

        public Criteria andA118IsNull() {
            addCriterion("A118 is null");
            return (Criteria) this;
        }

        public Criteria andA118IsNotNull() {
            addCriterion("A118 is not null");
            return (Criteria) this;
        }

        public Criteria andA118EqualTo(Date value) {
            addCriterionForJDBCDate("A118 =", value, "a118");
            return (Criteria) this;
        }

        public Criteria andA118NotEqualTo(Date value) {
            addCriterionForJDBCDate("A118 <>", value, "a118");
            return (Criteria) this;
        }

        public Criteria andA118GreaterThan(Date value) {
            addCriterionForJDBCDate("A118 >", value, "a118");
            return (Criteria) this;
        }

        public Criteria andA118GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A118 >=", value, "a118");
            return (Criteria) this;
        }

        public Criteria andA118LessThan(Date value) {
            addCriterionForJDBCDate("A118 <", value, "a118");
            return (Criteria) this;
        }

        public Criteria andA118LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A118 <=", value, "a118");
            return (Criteria) this;
        }

        public Criteria andA118In(List<Date> values) {
            addCriterionForJDBCDate("A118 in", values, "a118");
            return (Criteria) this;
        }

        public Criteria andA118NotIn(List<Date> values) {
            addCriterionForJDBCDate("A118 not in", values, "a118");
            return (Criteria) this;
        }

        public Criteria andA118Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A118 between", value1, value2, "a118");
            return (Criteria) this;
        }

        public Criteria andA118NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A118 not between", value1, value2, "a118");
            return (Criteria) this;
        }

        public Criteria andA109IsNull() {
            addCriterion("A109 is null");
            return (Criteria) this;
        }

        public Criteria andA109IsNotNull() {
            addCriterion("A109 is not null");
            return (Criteria) this;
        }

        public Criteria andA109EqualTo(BigDecimal value) {
            addCriterion("A109 =", value, "a109");
            return (Criteria) this;
        }

        public Criteria andA109NotEqualTo(BigDecimal value) {
            addCriterion("A109 <>", value, "a109");
            return (Criteria) this;
        }

        public Criteria andA109GreaterThan(BigDecimal value) {
            addCriterion("A109 >", value, "a109");
            return (Criteria) this;
        }

        public Criteria andA109GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A109 >=", value, "a109");
            return (Criteria) this;
        }

        public Criteria andA109LessThan(BigDecimal value) {
            addCriterion("A109 <", value, "a109");
            return (Criteria) this;
        }

        public Criteria andA109LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A109 <=", value, "a109");
            return (Criteria) this;
        }

        public Criteria andA109In(List<BigDecimal> values) {
            addCriterion("A109 in", values, "a109");
            return (Criteria) this;
        }

        public Criteria andA109NotIn(List<BigDecimal> values) {
            addCriterion("A109 not in", values, "a109");
            return (Criteria) this;
        }

        public Criteria andA109Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A109 between", value1, value2, "a109");
            return (Criteria) this;
        }

        public Criteria andA109NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A109 not between", value1, value2, "a109");
            return (Criteria) this;
        }

        public Criteria andA166IsNull() {
            addCriterion("A166 is null");
            return (Criteria) this;
        }

        public Criteria andA166IsNotNull() {
            addCriterion("A166 is not null");
            return (Criteria) this;
        }

        public Criteria andA166EqualTo(BigDecimal value) {
            addCriterion("A166 =", value, "a166");
            return (Criteria) this;
        }

        public Criteria andA166NotEqualTo(BigDecimal value) {
            addCriterion("A166 <>", value, "a166");
            return (Criteria) this;
        }

        public Criteria andA166GreaterThan(BigDecimal value) {
            addCriterion("A166 >", value, "a166");
            return (Criteria) this;
        }

        public Criteria andA166GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A166 >=", value, "a166");
            return (Criteria) this;
        }

        public Criteria andA166LessThan(BigDecimal value) {
            addCriterion("A166 <", value, "a166");
            return (Criteria) this;
        }

        public Criteria andA166LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A166 <=", value, "a166");
            return (Criteria) this;
        }

        public Criteria andA166In(List<BigDecimal> values) {
            addCriterion("A166 in", values, "a166");
            return (Criteria) this;
        }

        public Criteria andA166NotIn(List<BigDecimal> values) {
            addCriterion("A166 not in", values, "a166");
            return (Criteria) this;
        }

        public Criteria andA166Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A166 between", value1, value2, "a166");
            return (Criteria) this;
        }

        public Criteria andA166NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A166 not between", value1, value2, "a166");
            return (Criteria) this;
        }

        public Criteria andA167IsNull() {
            addCriterion("A167 is null");
            return (Criteria) this;
        }

        public Criteria andA167IsNotNull() {
            addCriterion("A167 is not null");
            return (Criteria) this;
        }

        public Criteria andA167EqualTo(BigDecimal value) {
            addCriterion("A167 =", value, "a167");
            return (Criteria) this;
        }

        public Criteria andA167NotEqualTo(BigDecimal value) {
            addCriterion("A167 <>", value, "a167");
            return (Criteria) this;
        }

        public Criteria andA167GreaterThan(BigDecimal value) {
            addCriterion("A167 >", value, "a167");
            return (Criteria) this;
        }

        public Criteria andA167GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A167 >=", value, "a167");
            return (Criteria) this;
        }

        public Criteria andA167LessThan(BigDecimal value) {
            addCriterion("A167 <", value, "a167");
            return (Criteria) this;
        }

        public Criteria andA167LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A167 <=", value, "a167");
            return (Criteria) this;
        }

        public Criteria andA167In(List<BigDecimal> values) {
            addCriterion("A167 in", values, "a167");
            return (Criteria) this;
        }

        public Criteria andA167NotIn(List<BigDecimal> values) {
            addCriterion("A167 not in", values, "a167");
            return (Criteria) this;
        }

        public Criteria andA167Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A167 between", value1, value2, "a167");
            return (Criteria) this;
        }

        public Criteria andA167NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A167 not between", value1, value2, "a167");
            return (Criteria) this;
        }

        public Criteria andA197IsNull() {
            addCriterion("A197 is null");
            return (Criteria) this;
        }

        public Criteria andA197IsNotNull() {
            addCriterion("A197 is not null");
            return (Criteria) this;
        }

        public Criteria andA197EqualTo(BigDecimal value) {
            addCriterion("A197 =", value, "a197");
            return (Criteria) this;
        }

        public Criteria andA197NotEqualTo(BigDecimal value) {
            addCriterion("A197 <>", value, "a197");
            return (Criteria) this;
        }

        public Criteria andA197GreaterThan(BigDecimal value) {
            addCriterion("A197 >", value, "a197");
            return (Criteria) this;
        }

        public Criteria andA197GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A197 >=", value, "a197");
            return (Criteria) this;
        }

        public Criteria andA197LessThan(BigDecimal value) {
            addCriterion("A197 <", value, "a197");
            return (Criteria) this;
        }

        public Criteria andA197LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A197 <=", value, "a197");
            return (Criteria) this;
        }

        public Criteria andA197In(List<BigDecimal> values) {
            addCriterion("A197 in", values, "a197");
            return (Criteria) this;
        }

        public Criteria andA197NotIn(List<BigDecimal> values) {
            addCriterion("A197 not in", values, "a197");
            return (Criteria) this;
        }

        public Criteria andA197Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A197 between", value1, value2, "a197");
            return (Criteria) this;
        }

        public Criteria andA197NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A197 not between", value1, value2, "a197");
            return (Criteria) this;
        }

        public Criteria andA198IsNull() {
            addCriterion("A198 is null");
            return (Criteria) this;
        }

        public Criteria andA198IsNotNull() {
            addCriterion("A198 is not null");
            return (Criteria) this;
        }

        public Criteria andA198EqualTo(BigDecimal value) {
            addCriterion("A198 =", value, "a198");
            return (Criteria) this;
        }

        public Criteria andA198NotEqualTo(BigDecimal value) {
            addCriterion("A198 <>", value, "a198");
            return (Criteria) this;
        }

        public Criteria andA198GreaterThan(BigDecimal value) {
            addCriterion("A198 >", value, "a198");
            return (Criteria) this;
        }

        public Criteria andA198GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A198 >=", value, "a198");
            return (Criteria) this;
        }

        public Criteria andA198LessThan(BigDecimal value) {
            addCriterion("A198 <", value, "a198");
            return (Criteria) this;
        }

        public Criteria andA198LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A198 <=", value, "a198");
            return (Criteria) this;
        }

        public Criteria andA198In(List<BigDecimal> values) {
            addCriterion("A198 in", values, "a198");
            return (Criteria) this;
        }

        public Criteria andA198NotIn(List<BigDecimal> values) {
            addCriterion("A198 not in", values, "a198");
            return (Criteria) this;
        }

        public Criteria andA198Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A198 between", value1, value2, "a198");
            return (Criteria) this;
        }

        public Criteria andA198NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A198 not between", value1, value2, "a198");
            return (Criteria) this;
        }

        public Criteria andA201IsNull() {
            addCriterion("A201 is null");
            return (Criteria) this;
        }

        public Criteria andA201IsNotNull() {
            addCriterion("A201 is not null");
            return (Criteria) this;
        }

        public Criteria andA201EqualTo(BigDecimal value) {
            addCriterion("A201 =", value, "a201");
            return (Criteria) this;
        }

        public Criteria andA201NotEqualTo(BigDecimal value) {
            addCriterion("A201 <>", value, "a201");
            return (Criteria) this;
        }

        public Criteria andA201GreaterThan(BigDecimal value) {
            addCriterion("A201 >", value, "a201");
            return (Criteria) this;
        }

        public Criteria andA201GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A201 >=", value, "a201");
            return (Criteria) this;
        }

        public Criteria andA201LessThan(BigDecimal value) {
            addCriterion("A201 <", value, "a201");
            return (Criteria) this;
        }

        public Criteria andA201LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A201 <=", value, "a201");
            return (Criteria) this;
        }

        public Criteria andA201In(List<BigDecimal> values) {
            addCriterion("A201 in", values, "a201");
            return (Criteria) this;
        }

        public Criteria andA201NotIn(List<BigDecimal> values) {
            addCriterion("A201 not in", values, "a201");
            return (Criteria) this;
        }

        public Criteria andA201Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A201 between", value1, value2, "a201");
            return (Criteria) this;
        }

        public Criteria andA201NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A201 not between", value1, value2, "a201");
            return (Criteria) this;
        }

        public Criteria andA202IsNull() {
            addCriterion("A202 is null");
            return (Criteria) this;
        }

        public Criteria andA202IsNotNull() {
            addCriterion("A202 is not null");
            return (Criteria) this;
        }

        public Criteria andA202EqualTo(Date value) {
            addCriterionForJDBCDate("A202 =", value, "a202");
            return (Criteria) this;
        }

        public Criteria andA202NotEqualTo(Date value) {
            addCriterionForJDBCDate("A202 <>", value, "a202");
            return (Criteria) this;
        }

        public Criteria andA202GreaterThan(Date value) {
            addCriterionForJDBCDate("A202 >", value, "a202");
            return (Criteria) this;
        }

        public Criteria andA202GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A202 >=", value, "a202");
            return (Criteria) this;
        }

        public Criteria andA202LessThan(Date value) {
            addCriterionForJDBCDate("A202 <", value, "a202");
            return (Criteria) this;
        }

        public Criteria andA202LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A202 <=", value, "a202");
            return (Criteria) this;
        }

        public Criteria andA202In(List<Date> values) {
            addCriterionForJDBCDate("A202 in", values, "a202");
            return (Criteria) this;
        }

        public Criteria andA202NotIn(List<Date> values) {
            addCriterionForJDBCDate("A202 not in", values, "a202");
            return (Criteria) this;
        }

        public Criteria andA202Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A202 between", value1, value2, "a202");
            return (Criteria) this;
        }

        public Criteria andA202NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A202 not between", value1, value2, "a202");
            return (Criteria) this;
        }

        public Criteria andA203IsNull() {
            addCriterion("A203 is null");
            return (Criteria) this;
        }

        public Criteria andA203IsNotNull() {
            addCriterion("A203 is not null");
            return (Criteria) this;
        }

        public Criteria andA203EqualTo(Date value) {
            addCriterionForJDBCDate("A203 =", value, "a203");
            return (Criteria) this;
        }

        public Criteria andA203NotEqualTo(Date value) {
            addCriterionForJDBCDate("A203 <>", value, "a203");
            return (Criteria) this;
        }

        public Criteria andA203GreaterThan(Date value) {
            addCriterionForJDBCDate("A203 >", value, "a203");
            return (Criteria) this;
        }

        public Criteria andA203GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A203 >=", value, "a203");
            return (Criteria) this;
        }

        public Criteria andA203LessThan(Date value) {
            addCriterionForJDBCDate("A203 <", value, "a203");
            return (Criteria) this;
        }

        public Criteria andA203LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A203 <=", value, "a203");
            return (Criteria) this;
        }

        public Criteria andA203In(List<Date> values) {
            addCriterionForJDBCDate("A203 in", values, "a203");
            return (Criteria) this;
        }

        public Criteria andA203NotIn(List<Date> values) {
            addCriterionForJDBCDate("A203 not in", values, "a203");
            return (Criteria) this;
        }

        public Criteria andA203Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A203 between", value1, value2, "a203");
            return (Criteria) this;
        }

        public Criteria andA203NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A203 not between", value1, value2, "a203");
            return (Criteria) this;
        }

        public Criteria andA111IsNull() {
            addCriterion("A111 is null");
            return (Criteria) this;
        }

        public Criteria andA111IsNotNull() {
            addCriterion("A111 is not null");
            return (Criteria) this;
        }

        public Criteria andA111EqualTo(String value) {
            addCriterion("A111 =", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111NotEqualTo(String value) {
            addCriterion("A111 <>", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111GreaterThan(String value) {
            addCriterion("A111 >", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111GreaterThanOrEqualTo(String value) {
            addCriterion("A111 >=", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111LessThan(String value) {
            addCriterion("A111 <", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111LessThanOrEqualTo(String value) {
            addCriterion("A111 <=", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111Like(String value) {
            addCriterion("A111 like", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111NotLike(String value) {
            addCriterion("A111 not like", value, "a111");
            return (Criteria) this;
        }

        public Criteria andA111In(List<String> values) {
            addCriterion("A111 in", values, "a111");
            return (Criteria) this;
        }

        public Criteria andA111NotIn(List<String> values) {
            addCriterion("A111 not in", values, "a111");
            return (Criteria) this;
        }

        public Criteria andA111Between(String value1, String value2) {
            addCriterion("A111 between", value1, value2, "a111");
            return (Criteria) this;
        }

        public Criteria andA111NotBetween(String value1, String value2) {
            addCriterion("A111 not between", value1, value2, "a111");
            return (Criteria) this;
        }

        public Criteria andA093IsNull() {
            addCriterion("A093 is null");
            return (Criteria) this;
        }

        public Criteria andA093IsNotNull() {
            addCriterion("A093 is not null");
            return (Criteria) this;
        }

        public Criteria andA093EqualTo(String value) {
            addCriterion("A093 =", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093NotEqualTo(String value) {
            addCriterion("A093 <>", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093GreaterThan(String value) {
            addCriterion("A093 >", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093GreaterThanOrEqualTo(String value) {
            addCriterion("A093 >=", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093LessThan(String value) {
            addCriterion("A093 <", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093LessThanOrEqualTo(String value) {
            addCriterion("A093 <=", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093Like(String value) {
            addCriterion("A093 like", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093NotLike(String value) {
            addCriterion("A093 not like", value, "a093");
            return (Criteria) this;
        }

        public Criteria andA093In(List<String> values) {
            addCriterion("A093 in", values, "a093");
            return (Criteria) this;
        }

        public Criteria andA093NotIn(List<String> values) {
            addCriterion("A093 not in", values, "a093");
            return (Criteria) this;
        }

        public Criteria andA093Between(String value1, String value2) {
            addCriterion("A093 between", value1, value2, "a093");
            return (Criteria) this;
        }

        public Criteria andA093NotBetween(String value1, String value2) {
            addCriterion("A093 not between", value1, value2, "a093");
            return (Criteria) this;
        }

        public Criteria andA204IsNull() {
            addCriterion("A204 is null");
            return (Criteria) this;
        }

        public Criteria andA204IsNotNull() {
            addCriterion("A204 is not null");
            return (Criteria) this;
        }

        public Criteria andA204EqualTo(Date value) {
            addCriterionForJDBCDate("A204 =", value, "a204");
            return (Criteria) this;
        }

        public Criteria andA204NotEqualTo(Date value) {
            addCriterionForJDBCDate("A204 <>", value, "a204");
            return (Criteria) this;
        }

        public Criteria andA204GreaterThan(Date value) {
            addCriterionForJDBCDate("A204 >", value, "a204");
            return (Criteria) this;
        }

        public Criteria andA204GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A204 >=", value, "a204");
            return (Criteria) this;
        }

        public Criteria andA204LessThan(Date value) {
            addCriterionForJDBCDate("A204 <", value, "a204");
            return (Criteria) this;
        }

        public Criteria andA204LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("A204 <=", value, "a204");
            return (Criteria) this;
        }

        public Criteria andA204In(List<Date> values) {
            addCriterionForJDBCDate("A204 in", values, "a204");
            return (Criteria) this;
        }

        public Criteria andA204NotIn(List<Date> values) {
            addCriterionForJDBCDate("A204 not in", values, "a204");
            return (Criteria) this;
        }

        public Criteria andA204Between(Date value1, Date value2) {
            addCriterionForJDBCDate("A204 between", value1, value2, "a204");
            return (Criteria) this;
        }

        public Criteria andA204NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("A204 not between", value1, value2, "a204");
            return (Criteria) this;
        }

        public Criteria andA205IsNull() {
            addCriterion("A205 is null");
            return (Criteria) this;
        }

        public Criteria andA205IsNotNull() {
            addCriterion("A205 is not null");
            return (Criteria) this;
        }

        public Criteria andA205EqualTo(BigDecimal value) {
            addCriterion("A205 =", value, "a205");
            return (Criteria) this;
        }

        public Criteria andA205NotEqualTo(BigDecimal value) {
            addCriterion("A205 <>", value, "a205");
            return (Criteria) this;
        }

        public Criteria andA205GreaterThan(BigDecimal value) {
            addCriterion("A205 >", value, "a205");
            return (Criteria) this;
        }

        public Criteria andA205GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A205 >=", value, "a205");
            return (Criteria) this;
        }

        public Criteria andA205LessThan(BigDecimal value) {
            addCriterion("A205 <", value, "a205");
            return (Criteria) this;
        }

        public Criteria andA205LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A205 <=", value, "a205");
            return (Criteria) this;
        }

        public Criteria andA205In(List<BigDecimal> values) {
            addCriterion("A205 in", values, "a205");
            return (Criteria) this;
        }

        public Criteria andA205NotIn(List<BigDecimal> values) {
            addCriterion("A205 not in", values, "a205");
            return (Criteria) this;
        }

        public Criteria andA205Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A205 between", value1, value2, "a205");
            return (Criteria) this;
        }

        public Criteria andA205NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A205 not between", value1, value2, "a205");
            return (Criteria) this;
        }

        public Criteria andA206IsNull() {
            addCriterion("A206 is null");
            return (Criteria) this;
        }

        public Criteria andA206IsNotNull() {
            addCriterion("A206 is not null");
            return (Criteria) this;
        }

        public Criteria andA206EqualTo(BigDecimal value) {
            addCriterion("A206 =", value, "a206");
            return (Criteria) this;
        }

        public Criteria andA206NotEqualTo(BigDecimal value) {
            addCriterion("A206 <>", value, "a206");
            return (Criteria) this;
        }

        public Criteria andA206GreaterThan(BigDecimal value) {
            addCriterion("A206 >", value, "a206");
            return (Criteria) this;
        }

        public Criteria andA206GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A206 >=", value, "a206");
            return (Criteria) this;
        }

        public Criteria andA206LessThan(BigDecimal value) {
            addCriterion("A206 <", value, "a206");
            return (Criteria) this;
        }

        public Criteria andA206LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A206 <=", value, "a206");
            return (Criteria) this;
        }

        public Criteria andA206In(List<BigDecimal> values) {
            addCriterion("A206 in", values, "a206");
            return (Criteria) this;
        }

        public Criteria andA206NotIn(List<BigDecimal> values) {
            addCriterion("A206 not in", values, "a206");
            return (Criteria) this;
        }

        public Criteria andA206Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A206 between", value1, value2, "a206");
            return (Criteria) this;
        }

        public Criteria andA206NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A206 not between", value1, value2, "a206");
            return (Criteria) this;
        }

        public Criteria andA007IsNull() {
            addCriterion("A007 is null");
            return (Criteria) this;
        }

        public Criteria andA007IsNotNull() {
            addCriterion("A007 is not null");
            return (Criteria) this;
        }

        public Criteria andA007EqualTo(String value) {
            addCriterion("A007 =", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007NotEqualTo(String value) {
            addCriterion("A007 <>", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007GreaterThan(String value) {
            addCriterion("A007 >", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007GreaterThanOrEqualTo(String value) {
            addCriterion("A007 >=", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007LessThan(String value) {
            addCriterion("A007 <", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007LessThanOrEqualTo(String value) {
            addCriterion("A007 <=", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007Like(String value) {
            addCriterion("A007 like", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007NotLike(String value) {
            addCriterion("A007 not like", value, "a007");
            return (Criteria) this;
        }

        public Criteria andA007In(List<String> values) {
            addCriterion("A007 in", values, "a007");
            return (Criteria) this;
        }

        public Criteria andA007NotIn(List<String> values) {
            addCriterion("A007 not in", values, "a007");
            return (Criteria) this;
        }

        public Criteria andA007Between(String value1, String value2) {
            addCriterion("A007 between", value1, value2, "a007");
            return (Criteria) this;
        }

        public Criteria andA007NotBetween(String value1, String value2) {
            addCriterion("A007 not between", value1, value2, "a007");
            return (Criteria) this;
        }

        public Criteria andA2011IsNull() {
            addCriterion("A201_1 is null");
            return (Criteria) this;
        }

        public Criteria andA2011IsNotNull() {
            addCriterion("A201_1 is not null");
            return (Criteria) this;
        }

        public Criteria andA2011EqualTo(String value) {
            addCriterion("A201_1 =", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011NotEqualTo(String value) {
            addCriterion("A201_1 <>", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011GreaterThan(String value) {
            addCriterion("A201_1 >", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011GreaterThanOrEqualTo(String value) {
            addCriterion("A201_1 >=", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011LessThan(String value) {
            addCriterion("A201_1 <", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011LessThanOrEqualTo(String value) {
            addCriterion("A201_1 <=", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011Like(String value) {
            addCriterion("A201_1 like", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011NotLike(String value) {
            addCriterion("A201_1 not like", value, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011In(List<String> values) {
            addCriterion("A201_1 in", values, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011NotIn(List<String> values) {
            addCriterion("A201_1 not in", values, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011Between(String value1, String value2) {
            addCriterion("A201_1 between", value1, value2, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2011NotBetween(String value1, String value2) {
            addCriterion("A201_1 not between", value1, value2, "a2011");
            return (Criteria) this;
        }

        public Criteria andA2051IsNull() {
            addCriterion("A205_1 is null");
            return (Criteria) this;
        }

        public Criteria andA2051IsNotNull() {
            addCriterion("A205_1 is not null");
            return (Criteria) this;
        }

        public Criteria andA2051EqualTo(String value) {
            addCriterion("A205_1 =", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051NotEqualTo(String value) {
            addCriterion("A205_1 <>", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051GreaterThan(String value) {
            addCriterion("A205_1 >", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051GreaterThanOrEqualTo(String value) {
            addCriterion("A205_1 >=", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051LessThan(String value) {
            addCriterion("A205_1 <", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051LessThanOrEqualTo(String value) {
            addCriterion("A205_1 <=", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051Like(String value) {
            addCriterion("A205_1 like", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051NotLike(String value) {
            addCriterion("A205_1 not like", value, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051In(List<String> values) {
            addCriterion("A205_1 in", values, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051NotIn(List<String> values) {
            addCriterion("A205_1 not in", values, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051Between(String value1, String value2) {
            addCriterion("A205_1 between", value1, value2, "a2051");
            return (Criteria) this;
        }

        public Criteria andA2051NotBetween(String value1, String value2) {
            addCriterion("A205_1 not between", value1, value2, "a2051");
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

        public Criteria andTszgIsNull() {
            addCriterion("TSZG is null");
            return (Criteria) this;
        }

        public Criteria andTszgIsNotNull() {
            addCriterion("TSZG is not null");
            return (Criteria) this;
        }

        public Criteria andTszgEqualTo(BigDecimal value) {
            addCriterion("TSZG =", value, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgNotEqualTo(BigDecimal value) {
            addCriterion("TSZG <>", value, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgGreaterThan(BigDecimal value) {
            addCriterion("TSZG >", value, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TSZG >=", value, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgLessThan(BigDecimal value) {
            addCriterion("TSZG <", value, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TSZG <=", value, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgIn(List<BigDecimal> values) {
            addCriterion("TSZG in", values, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgNotIn(List<BigDecimal> values) {
            addCriterion("TSZG not in", values, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TSZG between", value1, value2, "tszg");
            return (Criteria) this;
        }

        public Criteria andTszgNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TSZG not between", value1, value2, "tszg");
            return (Criteria) this;
        }

        public Criteria andA037IsNull() {
            addCriterion("A037 is null");
            return (Criteria) this;
        }

        public Criteria andA037IsNotNull() {
            addCriterion("A037 is not null");
            return (Criteria) this;
        }

        public Criteria andA037EqualTo(BigDecimal value) {
            addCriterion("A037 =", value, "a037");
            return (Criteria) this;
        }

        public Criteria andA037NotEqualTo(BigDecimal value) {
            addCriterion("A037 <>", value, "a037");
            return (Criteria) this;
        }

        public Criteria andA037GreaterThan(BigDecimal value) {
            addCriterion("A037 >", value, "a037");
            return (Criteria) this;
        }

        public Criteria andA037GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("A037 >=", value, "a037");
            return (Criteria) this;
        }

        public Criteria andA037LessThan(BigDecimal value) {
            addCriterion("A037 <", value, "a037");
            return (Criteria) this;
        }

        public Criteria andA037LessThanOrEqualTo(BigDecimal value) {
            addCriterion("A037 <=", value, "a037");
            return (Criteria) this;
        }

        public Criteria andA037In(List<BigDecimal> values) {
            addCriterion("A037 in", values, "a037");
            return (Criteria) this;
        }

        public Criteria andA037NotIn(List<BigDecimal> values) {
            addCriterion("A037 not in", values, "a037");
            return (Criteria) this;
        }

        public Criteria andA037Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("A037 between", value1, value2, "a037");
            return (Criteria) this;
        }

        public Criteria andA037NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("A037 not between", value1, value2, "a037");
            return (Criteria) this;
        }

        public Criteria andSpztIsNull() {
            addCriterion("SPZT is null");
            return (Criteria) this;
        }

        public Criteria andSpztIsNotNull() {
            addCriterion("SPZT is not null");
            return (Criteria) this;
        }

        public Criteria andSpztEqualTo(BigDecimal value) {
            addCriterion("SPZT =", value, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztNotEqualTo(BigDecimal value) {
            addCriterion("SPZT <>", value, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztGreaterThan(BigDecimal value) {
            addCriterion("SPZT >", value, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SPZT >=", value, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztLessThan(BigDecimal value) {
            addCriterion("SPZT <", value, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SPZT <=", value, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztIn(List<BigDecimal> values) {
            addCriterion("SPZT in", values, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztNotIn(List<BigDecimal> values) {
            addCriterion("SPZT not in", values, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SPZT between", value1, value2, "spzt");
            return (Criteria) this;
        }

        public Criteria andSpztNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SPZT not between", value1, value2, "spzt");
            return (Criteria) this;
        }

        public Criteria andThrqIsNull() {
            addCriterion("THRQ is null");
            return (Criteria) this;
        }

        public Criteria andThrqIsNotNull() {
            addCriterion("THRQ is not null");
            return (Criteria) this;
        }

        public Criteria andThrqEqualTo(Date value) {
            addCriterionForJDBCDate("THRQ =", value, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqNotEqualTo(Date value) {
            addCriterionForJDBCDate("THRQ <>", value, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqGreaterThan(Date value) {
            addCriterionForJDBCDate("THRQ >", value, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("THRQ >=", value, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqLessThan(Date value) {
            addCriterionForJDBCDate("THRQ <", value, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("THRQ <=", value, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqIn(List<Date> values) {
            addCriterionForJDBCDate("THRQ in", values, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqNotIn(List<Date> values) {
            addCriterionForJDBCDate("THRQ not in", values, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("THRQ between", value1, value2, "thrq");
            return (Criteria) this;
        }

        public Criteria andThrqNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("THRQ not between", value1, value2, "thrq");
            return (Criteria) this;
        }

        public Criteria andThczyIsNull() {
            addCriterion("THCZY is null");
            return (Criteria) this;
        }

        public Criteria andThczyIsNotNull() {
            addCriterion("THCZY is not null");
            return (Criteria) this;
        }

        public Criteria andThczyEqualTo(BigDecimal value) {
            addCriterion("THCZY =", value, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyNotEqualTo(BigDecimal value) {
            addCriterion("THCZY <>", value, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyGreaterThan(BigDecimal value) {
            addCriterion("THCZY >", value, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("THCZY >=", value, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyLessThan(BigDecimal value) {
            addCriterion("THCZY <", value, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("THCZY <=", value, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyIn(List<BigDecimal> values) {
            addCriterion("THCZY in", values, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyNotIn(List<BigDecimal> values) {
            addCriterion("THCZY not in", values, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THCZY between", value1, value2, "thczy");
            return (Criteria) this;
        }

        public Criteria andThczyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("THCZY not between", value1, value2, "thczy");
            return (Criteria) this;
        }

        public Criteria andBpmidIsNull() {
            addCriterion("BPMID is null");
            return (Criteria) this;
        }

        public Criteria andBpmidIsNotNull() {
            addCriterion("BPMID is not null");
            return (Criteria) this;
        }

        public Criteria andBpmidEqualTo(BigDecimal value) {
            addCriterion("BPMID =", value, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidNotEqualTo(BigDecimal value) {
            addCriterion("BPMID <>", value, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidGreaterThan(BigDecimal value) {
            addCriterion("BPMID >", value, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BPMID >=", value, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidLessThan(BigDecimal value) {
            addCriterion("BPMID <", value, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BPMID <=", value, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidIn(List<BigDecimal> values) {
            addCriterion("BPMID in", values, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidNotIn(List<BigDecimal> values) {
            addCriterion("BPMID not in", values, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BPMID between", value1, value2, "bpmid");
            return (Criteria) this;
        }

        public Criteria andBpmidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BPMID not between", value1, value2, "bpmid");
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

        public Criteria andWfDocunidIsNull() {
            addCriterion("WF_DOCUNID is null");
            return (Criteria) this;
        }

        public Criteria andWfDocunidIsNotNull() {
            addCriterion("WF_DOCUNID is not null");
            return (Criteria) this;
        }

        public Criteria andWfDocunidEqualTo(String value) {
            addCriterion("WF_DOCUNID =", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidNotEqualTo(String value) {
            addCriterion("WF_DOCUNID <>", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidGreaterThan(String value) {
            addCriterion("WF_DOCUNID >", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidGreaterThanOrEqualTo(String value) {
            addCriterion("WF_DOCUNID >=", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidLessThan(String value) {
            addCriterion("WF_DOCUNID <", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidLessThanOrEqualTo(String value) {
            addCriterion("WF_DOCUNID <=", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidLike(String value) {
            addCriterion("WF_DOCUNID like", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidNotLike(String value) {
            addCriterion("WF_DOCUNID not like", value, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidIn(List<String> values) {
            addCriterion("WF_DOCUNID in", values, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidNotIn(List<String> values) {
            addCriterion("WF_DOCUNID not in", values, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidBetween(String value1, String value2) {
            addCriterion("WF_DOCUNID between", value1, value2, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andWfDocunidNotBetween(String value1, String value2) {
            addCriterion("WF_DOCUNID not between", value1, value2, "wfDocunid");
            return (Criteria) this;
        }

        public Criteria andThczy1IsNull() {
            addCriterion("THCZY_1 is null");
            return (Criteria) this;
        }

        public Criteria andThczy1IsNotNull() {
            addCriterion("THCZY_1 is not null");
            return (Criteria) this;
        }

        public Criteria andThczy1EqualTo(String value) {
            addCriterion("THCZY_1 =", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1NotEqualTo(String value) {
            addCriterion("THCZY_1 <>", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1GreaterThan(String value) {
            addCriterion("THCZY_1 >", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1GreaterThanOrEqualTo(String value) {
            addCriterion("THCZY_1 >=", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1LessThan(String value) {
            addCriterion("THCZY_1 <", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1LessThanOrEqualTo(String value) {
            addCriterion("THCZY_1 <=", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1Like(String value) {
            addCriterion("THCZY_1 like", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1NotLike(String value) {
            addCriterion("THCZY_1 not like", value, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1In(List<String> values) {
            addCriterion("THCZY_1 in", values, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1NotIn(List<String> values) {
            addCriterion("THCZY_1 not in", values, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1Between(String value1, String value2) {
            addCriterion("THCZY_1 between", value1, value2, "thczy1");
            return (Criteria) this;
        }

        public Criteria andThczy1NotBetween(String value1, String value2) {
            addCriterion("THCZY_1 not between", value1, value2, "thczy1");
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