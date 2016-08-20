package com.pallasli.webapp.edu.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserInfoExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserCaptionIsNull() {
            addCriterion("user_caption is null");
            return (Criteria) this;
        }

        public Criteria andUserCaptionIsNotNull() {
            addCriterion("user_caption is not null");
            return (Criteria) this;
        }

        public Criteria andUserCaptionEqualTo(String value) {
            addCriterion("user_caption =", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionNotEqualTo(String value) {
            addCriterion("user_caption <>", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionGreaterThan(String value) {
            addCriterion("user_caption >", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionGreaterThanOrEqualTo(String value) {
            addCriterion("user_caption >=", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionLessThan(String value) {
            addCriterion("user_caption <", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionLessThanOrEqualTo(String value) {
            addCriterion("user_caption <=", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionLike(String value) {
            addCriterion("user_caption like", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionNotLike(String value) {
            addCriterion("user_caption not like", value, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionIn(List<String> values) {
            addCriterion("user_caption in", values, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionNotIn(List<String> values) {
            addCriterion("user_caption not in", values, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionBetween(String value1, String value2) {
            addCriterion("user_caption between", value1, value2, "userCaption");
            return (Criteria) this;
        }

        public Criteria andUserCaptionNotBetween(String value1, String value2) {
            addCriterion("user_caption not between", value1, value2, "userCaption");
            return (Criteria) this;
        }

        public Criteria andIdcardsIsNull() {
            addCriterion("idcards is null");
            return (Criteria) this;
        }

        public Criteria andIdcardsIsNotNull() {
            addCriterion("idcards is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardsEqualTo(String value) {
            addCriterion("idcards =", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotEqualTo(String value) {
            addCriterion("idcards <>", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsGreaterThan(String value) {
            addCriterion("idcards >", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsGreaterThanOrEqualTo(String value) {
            addCriterion("idcards >=", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsLessThan(String value) {
            addCriterion("idcards <", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsLessThanOrEqualTo(String value) {
            addCriterion("idcards <=", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsLike(String value) {
            addCriterion("idcards like", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotLike(String value) {
            addCriterion("idcards not like", value, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsIn(List<String> values) {
            addCriterion("idcards in", values, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotIn(List<String> values) {
            addCriterion("idcards not in", values, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsBetween(String value1, String value2) {
            addCriterion("idcards between", value1, value2, "idcards");
            return (Criteria) this;
        }

        public Criteria andIdcardsNotBetween(String value1, String value2) {
            addCriterion("idcards not between", value1, value2, "idcards");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Byte value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Byte value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Byte value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Byte value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Byte value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Byte value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Byte> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Byte> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Byte value1, Byte value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Byte value1, Byte value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceIsNull() {
            addCriterion("birth_place is null");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceIsNotNull() {
            addCriterion("birth_place is not null");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceEqualTo(String value) {
            addCriterion("birth_place =", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotEqualTo(String value) {
            addCriterion("birth_place <>", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceGreaterThan(String value) {
            addCriterion("birth_place >", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("birth_place >=", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceLessThan(String value) {
            addCriterion("birth_place <", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceLessThanOrEqualTo(String value) {
            addCriterion("birth_place <=", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceLike(String value) {
            addCriterion("birth_place like", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotLike(String value) {
            addCriterion("birth_place not like", value, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceIn(List<String> values) {
            addCriterion("birth_place in", values, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotIn(List<String> values) {
            addCriterion("birth_place not in", values, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceBetween(String value1, String value2) {
            addCriterion("birth_place between", value1, value2, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andBirthPlaceNotBetween(String value1, String value2) {
            addCriterion("birth_place not between", value1, value2, "birthPlace");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andTelphoneIsNull() {
            addCriterion("telphone is null");
            return (Criteria) this;
        }

        public Criteria andTelphoneIsNotNull() {
            addCriterion("telphone is not null");
            return (Criteria) this;
        }

        public Criteria andTelphoneEqualTo(String value) {
            addCriterion("telphone =", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotEqualTo(String value) {
            addCriterion("telphone <>", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneGreaterThan(String value) {
            addCriterion("telphone >", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("telphone >=", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLessThan(String value) {
            addCriterion("telphone <", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLessThanOrEqualTo(String value) {
            addCriterion("telphone <=", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneLike(String value) {
            addCriterion("telphone like", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotLike(String value) {
            addCriterion("telphone not like", value, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneIn(List<String> values) {
            addCriterion("telphone in", values, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotIn(List<String> values) {
            addCriterion("telphone not in", values, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneBetween(String value1, String value2) {
            addCriterion("telphone between", value1, value2, "telphone");
            return (Criteria) this;
        }

        public Criteria andTelphoneNotBetween(String value1, String value2) {
            addCriterion("telphone not between", value1, value2, "telphone");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceIsNull() {
            addCriterion("registered_place is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceIsNotNull() {
            addCriterion("registered_place is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceEqualTo(String value) {
            addCriterion("registered_place =", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceNotEqualTo(String value) {
            addCriterion("registered_place <>", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceGreaterThan(String value) {
            addCriterion("registered_place >", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("registered_place >=", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceLessThan(String value) {
            addCriterion("registered_place <", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceLessThanOrEqualTo(String value) {
            addCriterion("registered_place <=", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceLike(String value) {
            addCriterion("registered_place like", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceNotLike(String value) {
            addCriterion("registered_place not like", value, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceIn(List<String> values) {
            addCriterion("registered_place in", values, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceNotIn(List<String> values) {
            addCriterion("registered_place not in", values, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceBetween(String value1, String value2) {
            addCriterion("registered_place between", value1, value2, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andRegisteredPlaceNotBetween(String value1, String value2) {
            addCriterion("registered_place not between", value1, value2, "registeredPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceIsNull() {
            addCriterion("present_place is null");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceIsNotNull() {
            addCriterion("present_place is not null");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceEqualTo(String value) {
            addCriterion("present_place =", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceNotEqualTo(String value) {
            addCriterion("present_place <>", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceGreaterThan(String value) {
            addCriterion("present_place >", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("present_place >=", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceLessThan(String value) {
            addCriterion("present_place <", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceLessThanOrEqualTo(String value) {
            addCriterion("present_place <=", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceLike(String value) {
            addCriterion("present_place like", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceNotLike(String value) {
            addCriterion("present_place not like", value, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceIn(List<String> values) {
            addCriterion("present_place in", values, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceNotIn(List<String> values) {
            addCriterion("present_place not in", values, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceBetween(String value1, String value2) {
            addCriterion("present_place between", value1, value2, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andPresentPlaceNotBetween(String value1, String value2) {
            addCriterion("present_place not between", value1, value2, "presentPlace");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
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