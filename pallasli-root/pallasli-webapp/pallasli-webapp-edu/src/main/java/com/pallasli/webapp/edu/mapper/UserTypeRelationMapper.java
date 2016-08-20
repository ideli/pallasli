package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.UserTypeRelation;
import com.pallasli.webapp.edu.model.UserTypeRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTypeRelationMapper {
    int countByExample(UserTypeRelationExample example);

    int deleteByExample(UserTypeRelationExample example);

    int insert(UserTypeRelation record);

    int insertSelective(UserTypeRelation record);

    List<UserTypeRelation> selectByExample(UserTypeRelationExample example);

    int updateByExampleSelective(@Param("record") UserTypeRelation record, @Param("example") UserTypeRelationExample example);

    int updateByExample(@Param("record") UserTypeRelation record, @Param("example") UserTypeRelationExample example);
}