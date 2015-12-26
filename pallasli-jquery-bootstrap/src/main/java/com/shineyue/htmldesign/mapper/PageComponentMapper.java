package com.shineyue.htmldesign.mapper;

import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageComponentMapper {
    int countByExample(PageComponentExample example);

    int deleteByExample(PageComponentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PageComponent record);

    int insertSelective(PageComponent record);

    List<PageComponent> selectByExample(PageComponentExample example);

    PageComponent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PageComponent record, @Param("example") PageComponentExample example);

    int updateByExample(@Param("record") PageComponent record, @Param("example") PageComponentExample example);

    int updateByPrimaryKeySelective(PageComponent record);

    int updateByPrimaryKey(PageComponent record);
}