package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.Page_Function;
import com.pallasli.webapp.edu.model.Page_FunctionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface Page_FunctionMapper {
    int countByExample(Page_FunctionExample example);

    int deleteByExample(Page_FunctionExample example);

    int insert(Page_Function record);

    int insertSelective(Page_Function record);

    List<Page_Function> selectByExample(Page_FunctionExample example);

    int updateByExampleSelective(@Param("record") Page_Function record, @Param("example") Page_FunctionExample example);

    int updateByExample(@Param("record") Page_Function record, @Param("example") Page_FunctionExample example);
}