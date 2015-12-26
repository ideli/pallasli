package com.shineyue.htmldesign.mapper;

import com.shineyue.htmldesign.model.ComponentType;
import com.shineyue.htmldesign.model.ComponentTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComponentTypeMapper {
    int countByExample(ComponentTypeExample example);

    int deleteByExample(ComponentTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ComponentType record);

    int insertSelective(ComponentType record);

    List<ComponentType> selectByExample(ComponentTypeExample example);

    ComponentType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ComponentType record, @Param("example") ComponentTypeExample example);

    int updateByExample(@Param("record") ComponentType record, @Param("example") ComponentTypeExample example);

    int updateByPrimaryKeySelective(ComponentType record);

    int updateByPrimaryKey(ComponentType record);
}