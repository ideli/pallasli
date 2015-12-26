package com.shineyue.htmldesign.mapper;

import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageMapper {
    int countByExample(PageExample example);

    int deleteByExample(PageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Page record);

    int insertSelective(Page record);

    List<Page> selectByExample(PageExample example);

    Page selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Page record, @Param("example") PageExample example);

    int updateByExample(@Param("record") Page record, @Param("example") PageExample example);

    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKey(Page record);
}