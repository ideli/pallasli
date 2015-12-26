package com.shineyue.htmldesign.mapper;

import com.shineyue.htmldesign.model.ComponentTypeConfig;
import com.shineyue.htmldesign.model.ComponentTypeConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComponentTypeConfigMapper {
    int countByExample(ComponentTypeConfigExample example);

    int deleteByExample(ComponentTypeConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ComponentTypeConfig record);

    int insertSelective(ComponentTypeConfig record);

    List<ComponentTypeConfig> selectByExample(ComponentTypeConfigExample example);

    ComponentTypeConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ComponentTypeConfig record, @Param("example") ComponentTypeConfigExample example);

    int updateByExample(@Param("record") ComponentTypeConfig record, @Param("example") ComponentTypeConfigExample example);

    int updateByPrimaryKeySelective(ComponentTypeConfig record);

    int updateByPrimaryKey(ComponentTypeConfig record);
}