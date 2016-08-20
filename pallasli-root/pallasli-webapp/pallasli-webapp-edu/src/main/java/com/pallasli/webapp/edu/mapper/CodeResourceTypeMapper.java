package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.CodeResourceType;
import com.pallasli.webapp.edu.model.CodeResourceTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeResourceTypeMapper {
    int countByExample(CodeResourceTypeExample example);

    int deleteByExample(CodeResourceTypeExample example);

    int deleteByPrimaryKey(String code);

    int insert(CodeResourceType record);

    int insertSelective(CodeResourceType record);

    List<CodeResourceType> selectByExample(CodeResourceTypeExample example);

    CodeResourceType selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") CodeResourceType record, @Param("example") CodeResourceTypeExample example);

    int updateByExample(@Param("record") CodeResourceType record, @Param("example") CodeResourceTypeExample example);

    int updateByPrimaryKeySelective(CodeResourceType record);

    int updateByPrimaryKey(CodeResourceType record);
}