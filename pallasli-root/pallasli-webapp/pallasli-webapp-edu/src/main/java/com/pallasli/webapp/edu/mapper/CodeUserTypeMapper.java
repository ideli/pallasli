package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.CodeUserType;
import com.pallasli.webapp.edu.model.CodeUserTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeUserTypeMapper {
    int countByExample(CodeUserTypeExample example);

    int deleteByExample(CodeUserTypeExample example);

    int deleteByPrimaryKey(String code);

    int insert(CodeUserType record);

    int insertSelective(CodeUserType record);

    List<CodeUserType> selectByExample(CodeUserTypeExample example);

    CodeUserType selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") CodeUserType record, @Param("example") CodeUserTypeExample example);

    int updateByExample(@Param("record") CodeUserType record, @Param("example") CodeUserTypeExample example);

    int updateByPrimaryKeySelective(CodeUserType record);

    int updateByPrimaryKey(CodeUserType record);
}