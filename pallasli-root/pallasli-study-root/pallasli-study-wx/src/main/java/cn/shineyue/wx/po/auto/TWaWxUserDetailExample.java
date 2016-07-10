package cn.shineyue.wx.po.auto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TWaWxUserDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TWaWxUserDetailExample() {
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

        public Criteria andWxSubscribeIsNull() {
            addCriterion("WX_SUBSCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeIsNotNull() {
            addCriterion("WX_SUBSCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeEqualTo(Short value) {
            addCriterion("WX_SUBSCRIBE =", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeNotEqualTo(Short value) {
            addCriterion("WX_SUBSCRIBE <>", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeGreaterThan(Short value) {
            addCriterion("WX_SUBSCRIBE >", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeGreaterThanOrEqualTo(Short value) {
            addCriterion("WX_SUBSCRIBE >=", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeLessThan(Short value) {
            addCriterion("WX_SUBSCRIBE <", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeLessThanOrEqualTo(Short value) {
            addCriterion("WX_SUBSCRIBE <=", value, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeIn(List<Short> values) {
            addCriterion("WX_SUBSCRIBE in", values, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeNotIn(List<Short> values) {
            addCriterion("WX_SUBSCRIBE not in", values, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeBetween(Short value1, Short value2) {
            addCriterion("WX_SUBSCRIBE between", value1, value2, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeNotBetween(Short value1, Short value2) {
            addCriterion("WX_SUBSCRIBE not between", value1, value2, "wxSubscribe");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIsNull() {
            addCriterion("WX_NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIsNotNull() {
            addCriterion("WX_NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andWxNicknameEqualTo(String value) {
            addCriterion("WX_NICKNAME =", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotEqualTo(String value) {
            addCriterion("WX_NICKNAME <>", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameGreaterThan(String value) {
            addCriterion("WX_NICKNAME >", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("WX_NICKNAME >=", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLessThan(String value) {
            addCriterion("WX_NICKNAME <", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLessThanOrEqualTo(String value) {
            addCriterion("WX_NICKNAME <=", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIn(List<String> values) {
            addCriterion("WX_NICKNAME in", values, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotIn(List<String> values) {
            addCriterion("WX_NICKNAME not in", values, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameBetween(String value1, String value2) {
            addCriterion("WX_NICKNAME between", value1, value2, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotBetween(String value1, String value2) {
            addCriterion("WX_NICKNAME not between", value1, value2, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxSexIsNull() {
            addCriterion("WX_SEX is null");
            return (Criteria) this;
        }

        public Criteria andWxSexIsNotNull() {
            addCriterion("WX_SEX is not null");
            return (Criteria) this;
        }

        public Criteria andWxSexEqualTo(Short value) {
            addCriterion("WX_SEX =", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexNotEqualTo(Short value) {
            addCriterion("WX_SEX <>", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexGreaterThan(Short value) {
            addCriterion("WX_SEX >", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexGreaterThanOrEqualTo(Short value) {
            addCriterion("WX_SEX >=", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexLessThan(Short value) {
            addCriterion("WX_SEX <", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexLessThanOrEqualTo(Short value) {
            addCriterion("WX_SEX <=", value, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexIn(List<Short> values) {
            addCriterion("WX_SEX in", values, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexNotIn(List<Short> values) {
            addCriterion("WX_SEX not in", values, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexBetween(Short value1, Short value2) {
            addCriterion("WX_SEX between", value1, value2, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxSexNotBetween(Short value1, Short value2) {
            addCriterion("WX_SEX not between", value1, value2, "wxSex");
            return (Criteria) this;
        }

        public Criteria andWxLanguageIsNull() {
            addCriterion("WX_LANGUAGE is null");
            return (Criteria) this;
        }

        public Criteria andWxLanguageIsNotNull() {
            addCriterion("WX_LANGUAGE is not null");
            return (Criteria) this;
        }

        public Criteria andWxLanguageEqualTo(String value) {
            addCriterion("WX_LANGUAGE =", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotEqualTo(String value) {
            addCriterion("WX_LANGUAGE <>", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageGreaterThan(String value) {
            addCriterion("WX_LANGUAGE >", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("WX_LANGUAGE >=", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageLessThan(String value) {
            addCriterion("WX_LANGUAGE <", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageLessThanOrEqualTo(String value) {
            addCriterion("WX_LANGUAGE <=", value, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageIn(List<String> values) {
            addCriterion("WX_LANGUAGE in", values, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotIn(List<String> values) {
            addCriterion("WX_LANGUAGE not in", values, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageBetween(String value1, String value2) {
            addCriterion("WX_LANGUAGE between", value1, value2, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxLanguageNotBetween(String value1, String value2) {
            addCriterion("WX_LANGUAGE not between", value1, value2, "wxLanguage");
            return (Criteria) this;
        }

        public Criteria andWxCityIsNull() {
            addCriterion("WX_CITY is null");
            return (Criteria) this;
        }

        public Criteria andWxCityIsNotNull() {
            addCriterion("WX_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andWxCityEqualTo(String value) {
            addCriterion("WX_CITY =", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotEqualTo(String value) {
            addCriterion("WX_CITY <>", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityGreaterThan(String value) {
            addCriterion("WX_CITY >", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityGreaterThanOrEqualTo(String value) {
            addCriterion("WX_CITY >=", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLessThan(String value) {
            addCriterion("WX_CITY <", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityLessThanOrEqualTo(String value) {
            addCriterion("WX_CITY <=", value, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityIn(List<String> values) {
            addCriterion("WX_CITY in", values, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotIn(List<String> values) {
            addCriterion("WX_CITY not in", values, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityBetween(String value1, String value2) {
            addCriterion("WX_CITY between", value1, value2, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxCityNotBetween(String value1, String value2) {
            addCriterion("WX_CITY not between", value1, value2, "wxCity");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIsNull() {
            addCriterion("WX_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIsNotNull() {
            addCriterion("WX_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andWxProvinceEqualTo(String value) {
            addCriterion("WX_PROVINCE =", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotEqualTo(String value) {
            addCriterion("WX_PROVINCE <>", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceGreaterThan(String value) {
            addCriterion("WX_PROVINCE >", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("WX_PROVINCE >=", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLessThan(String value) {
            addCriterion("WX_PROVINCE <", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceLessThanOrEqualTo(String value) {
            addCriterion("WX_PROVINCE <=", value, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceIn(List<String> values) {
            addCriterion("WX_PROVINCE in", values, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotIn(List<String> values) {
            addCriterion("WX_PROVINCE not in", values, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceBetween(String value1, String value2) {
            addCriterion("WX_PROVINCE between", value1, value2, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxProvinceNotBetween(String value1, String value2) {
            addCriterion("WX_PROVINCE not between", value1, value2, "wxProvince");
            return (Criteria) this;
        }

        public Criteria andWxCountryIsNull() {
            addCriterion("WX_COUNTRY is null");
            return (Criteria) this;
        }

        public Criteria andWxCountryIsNotNull() {
            addCriterion("WX_COUNTRY is not null");
            return (Criteria) this;
        }

        public Criteria andWxCountryEqualTo(String value) {
            addCriterion("WX_COUNTRY =", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotEqualTo(String value) {
            addCriterion("WX_COUNTRY <>", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryGreaterThan(String value) {
            addCriterion("WX_COUNTRY >", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryGreaterThanOrEqualTo(String value) {
            addCriterion("WX_COUNTRY >=", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLessThan(String value) {
            addCriterion("WX_COUNTRY <", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryLessThanOrEqualTo(String value) {
            addCriterion("WX_COUNTRY <=", value, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryIn(List<String> values) {
            addCriterion("WX_COUNTRY in", values, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotIn(List<String> values) {
            addCriterion("WX_COUNTRY not in", values, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryBetween(String value1, String value2) {
            addCriterion("WX_COUNTRY between", value1, value2, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxCountryNotBetween(String value1, String value2) {
            addCriterion("WX_COUNTRY not between", value1, value2, "wxCountry");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIsNull() {
            addCriterion("WX_HEADIMGURL is null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIsNotNull() {
            addCriterion("WX_HEADIMGURL is not null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlEqualTo(String value) {
            addCriterion("WX_HEADIMGURL =", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotEqualTo(String value) {
            addCriterion("WX_HEADIMGURL <>", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlGreaterThan(String value) {
            addCriterion("WX_HEADIMGURL >", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("WX_HEADIMGURL >=", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLessThan(String value) {
            addCriterion("WX_HEADIMGURL <", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlLessThanOrEqualTo(String value) {
            addCriterion("WX_HEADIMGURL <=", value, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlIn(List<String> values) {
            addCriterion("WX_HEADIMGURL in", values, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotIn(List<String> values) {
            addCriterion("WX_HEADIMGURL not in", values, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlBetween(String value1, String value2) {
            addCriterion("WX_HEADIMGURL between", value1, value2, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgurlNotBetween(String value1, String value2) {
            addCriterion("WX_HEADIMGURL not between", value1, value2, "wxHeadimgurl");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIsNull() {
            addCriterion("WX_SUBSCRIBE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIsNotNull() {
            addCriterion("WX_SUBSCRIBE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeEqualTo(BigDecimal value) {
            addCriterion("WX_SUBSCRIBE_TIME =", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotEqualTo(BigDecimal value) {
            addCriterion("WX_SUBSCRIBE_TIME <>", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeGreaterThan(BigDecimal value) {
            addCriterion("WX_SUBSCRIBE_TIME >", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WX_SUBSCRIBE_TIME >=", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeLessThan(BigDecimal value) {
            addCriterion("WX_SUBSCRIBE_TIME <", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WX_SUBSCRIBE_TIME <=", value, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeIn(List<BigDecimal> values) {
            addCriterion("WX_SUBSCRIBE_TIME in", values, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotIn(List<BigDecimal> values) {
            addCriterion("WX_SUBSCRIBE_TIME not in", values, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WX_SUBSCRIBE_TIME between", value1, value2, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxSubscribeTimeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WX_SUBSCRIBE_TIME not between", value1, value2, "wxSubscribeTime");
            return (Criteria) this;
        }

        public Criteria andWxUnionidIsNull() {
            addCriterion("WX_UNIONID is null");
            return (Criteria) this;
        }

        public Criteria andWxUnionidIsNotNull() {
            addCriterion("WX_UNIONID is not null");
            return (Criteria) this;
        }

        public Criteria andWxUnionidEqualTo(String value) {
            addCriterion("WX_UNIONID =", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotEqualTo(String value) {
            addCriterion("WX_UNIONID <>", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidGreaterThan(String value) {
            addCriterion("WX_UNIONID >", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("WX_UNIONID >=", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidLessThan(String value) {
            addCriterion("WX_UNIONID <", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidLessThanOrEqualTo(String value) {
            addCriterion("WX_UNIONID <=", value, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidIn(List<String> values) {
            addCriterion("WX_UNIONID in", values, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotIn(List<String> values) {
            addCriterion("WX_UNIONID not in", values, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidBetween(String value1, String value2) {
            addCriterion("WX_UNIONID between", value1, value2, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxUnionidNotBetween(String value1, String value2) {
            addCriterion("WX_UNIONID not between", value1, value2, "wxUnionid");
            return (Criteria) this;
        }

        public Criteria andWxRemarkIsNull() {
            addCriterion("WX_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andWxRemarkIsNotNull() {
            addCriterion("WX_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andWxRemarkEqualTo(String value) {
            addCriterion("WX_REMARK =", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotEqualTo(String value) {
            addCriterion("WX_REMARK <>", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkGreaterThan(String value) {
            addCriterion("WX_REMARK >", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("WX_REMARK >=", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkLessThan(String value) {
            addCriterion("WX_REMARK <", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkLessThanOrEqualTo(String value) {
            addCriterion("WX_REMARK <=", value, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkIn(List<String> values) {
            addCriterion("WX_REMARK in", values, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotIn(List<String> values) {
            addCriterion("WX_REMARK not in", values, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkBetween(String value1, String value2) {
            addCriterion("WX_REMARK between", value1, value2, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxRemarkNotBetween(String value1, String value2) {
            addCriterion("WX_REMARK not between", value1, value2, "wxRemark");
            return (Criteria) this;
        }

        public Criteria andWxGroupidIsNull() {
            addCriterion("WX_GROUPID is null");
            return (Criteria) this;
        }

        public Criteria andWxGroupidIsNotNull() {
            addCriterion("WX_GROUPID is not null");
            return (Criteria) this;
        }

        public Criteria andWxGroupidEqualTo(Short value) {
            addCriterion("WX_GROUPID =", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidNotEqualTo(Short value) {
            addCriterion("WX_GROUPID <>", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidGreaterThan(Short value) {
            addCriterion("WX_GROUPID >", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidGreaterThanOrEqualTo(Short value) {
            addCriterion("WX_GROUPID >=", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidLessThan(Short value) {
            addCriterion("WX_GROUPID <", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidLessThanOrEqualTo(Short value) {
            addCriterion("WX_GROUPID <=", value, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidIn(List<Short> values) {
            addCriterion("WX_GROUPID in", values, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidNotIn(List<Short> values) {
            addCriterion("WX_GROUPID not in", values, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidBetween(Short value1, Short value2) {
            addCriterion("WX_GROUPID between", value1, value2, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxGroupidNotBetween(Short value1, Short value2) {
            addCriterion("WX_GROUPID not between", value1, value2, "wxGroupid");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessIsNull() {
            addCriterion("WX_IS_SUCCESS is null");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessIsNotNull() {
            addCriterion("WX_IS_SUCCESS is not null");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessEqualTo(Short value) {
            addCriterion("WX_IS_SUCCESS =", value, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessNotEqualTo(Short value) {
            addCriterion("WX_IS_SUCCESS <>", value, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessGreaterThan(Short value) {
            addCriterion("WX_IS_SUCCESS >", value, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessGreaterThanOrEqualTo(Short value) {
            addCriterion("WX_IS_SUCCESS >=", value, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessLessThan(Short value) {
            addCriterion("WX_IS_SUCCESS <", value, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessLessThanOrEqualTo(Short value) {
            addCriterion("WX_IS_SUCCESS <=", value, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessIn(List<Short> values) {
            addCriterion("WX_IS_SUCCESS in", values, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessNotIn(List<Short> values) {
            addCriterion("WX_IS_SUCCESS not in", values, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessBetween(Short value1, Short value2) {
            addCriterion("WX_IS_SUCCESS between", value1, value2, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxIsSuccessNotBetween(Short value1, Short value2) {
            addCriterion("WX_IS_SUCCESS not between", value1, value2, "wxIsSuccess");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgIsNull() {
            addCriterion("WX_FAIL_MSG is null");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgIsNotNull() {
            addCriterion("WX_FAIL_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgEqualTo(String value) {
            addCriterion("WX_FAIL_MSG =", value, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgNotEqualTo(String value) {
            addCriterion("WX_FAIL_MSG <>", value, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgGreaterThan(String value) {
            addCriterion("WX_FAIL_MSG >", value, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgGreaterThanOrEqualTo(String value) {
            addCriterion("WX_FAIL_MSG >=", value, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgLessThan(String value) {
            addCriterion("WX_FAIL_MSG <", value, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgLessThanOrEqualTo(String value) {
            addCriterion("WX_FAIL_MSG <=", value, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgIn(List<String> values) {
            addCriterion("WX_FAIL_MSG in", values, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgNotIn(List<String> values) {
            addCriterion("WX_FAIL_MSG not in", values, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgBetween(String value1, String value2) {
            addCriterion("WX_FAIL_MSG between", value1, value2, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxFailMsgNotBetween(String value1, String value2) {
            addCriterion("WX_FAIL_MSG not between", value1, value2, "wxFailMsg");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateIsNull() {
            addCriterion("WX_UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateIsNotNull() {
            addCriterion("WX_UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateEqualTo(Date value) {
            addCriterion("WX_UPDATE_DATE =", value, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateNotEqualTo(Date value) {
            addCriterion("WX_UPDATE_DATE <>", value, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateGreaterThan(Date value) {
            addCriterion("WX_UPDATE_DATE >", value, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("WX_UPDATE_DATE >=", value, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateLessThan(Date value) {
            addCriterion("WX_UPDATE_DATE <", value, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("WX_UPDATE_DATE <=", value, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateIn(List<Date> values) {
            addCriterion("WX_UPDATE_DATE in", values, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateNotIn(List<Date> values) {
            addCriterion("WX_UPDATE_DATE not in", values, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateBetween(Date value1, Date value2) {
            addCriterion("WX_UPDATE_DATE between", value1, value2, "wxUpdateDate");
            return (Criteria) this;
        }

        public Criteria andWxUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("WX_UPDATE_DATE not between", value1, value2, "wxUpdateDate");
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