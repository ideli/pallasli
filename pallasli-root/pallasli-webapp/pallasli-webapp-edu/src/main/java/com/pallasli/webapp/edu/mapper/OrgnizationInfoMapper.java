package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.OrgnizationInfo;
import com.pallasli.webapp.edu.model.OrgnizationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrgnizationInfoMapper {
    int countByExample(OrgnizationInfoExample example);

    int deleteByExample(OrgnizationInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(OrgnizationInfo record);

    int insertSelective(OrgnizationInfo record);

    List<OrgnizationInfo> selectByExample(OrgnizationInfoExample example);

    OrgnizationInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OrgnizationInfo record, @Param("example") OrgnizationInfoExample example);

    int updateByExample(@Param("record") OrgnizationInfo record, @Param("example") OrgnizationInfoExample example);

    int updateByPrimaryKeySelective(OrgnizationInfo record);

    int updateByPrimaryKey(OrgnizationInfo record);
}