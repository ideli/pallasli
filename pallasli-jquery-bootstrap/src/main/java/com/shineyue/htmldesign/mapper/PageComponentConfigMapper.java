package com.shineyue.htmldesign.mapper;

import com.shineyue.htmldesign.model.PageComponentConfig;
import com.shineyue.htmldesign.model.PageComponentConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageComponentConfigMapper {
    int countByExample(PageComponentConfigExample example);

    int deleteByExample(PageComponentConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PageComponentConfig record);

    int insertSelective(PageComponentConfig record);

    List<PageComponentConfig> selectByExample(PageComponentConfigExample example);

    PageComponentConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PageComponentConfig record, @Param("example") PageComponentConfigExample example);

    int updateByExample(@Param("record") PageComponentConfig record, @Param("example") PageComponentConfigExample example);

    int updateByPrimaryKeySelective(PageComponentConfig record);

    int updateByPrimaryKey(PageComponentConfig record);
}