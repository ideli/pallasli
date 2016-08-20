package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.AreaCode;
import com.pallasli.webapp.edu.model.AreaCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaCodeMapper {
    int countByExample(AreaCodeExample example);

    int deleteByExample(AreaCodeExample example);

    int deleteByPrimaryKey(String code);

    int insert(AreaCode record);

    int insertSelective(AreaCode record);

    List<AreaCode> selectByExample(AreaCodeExample example);

    AreaCode selectByPrimaryKey(String code);

    int updateByExampleSelective(@Param("record") AreaCode record, @Param("example") AreaCodeExample example);

    int updateByExample(@Param("record") AreaCode record, @Param("example") AreaCodeExample example);

    int updateByPrimaryKeySelective(AreaCode record);

    int updateByPrimaryKey(AreaCode record);
}