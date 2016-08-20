package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.UserRegister;
import com.pallasli.webapp.edu.model.UserRegisterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRegisterMapper {
    int countByExample(UserRegisterExample example);

    int deleteByExample(UserRegisterExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserRegister record);

    int insertSelective(UserRegister record);

    List<UserRegister> selectByExample(UserRegisterExample example);

    UserRegister selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRegister record, @Param("example") UserRegisterExample example);

    int updateByExample(@Param("record") UserRegister record, @Param("example") UserRegisterExample example);

    int updateByPrimaryKeySelective(UserRegister record);

    int updateByPrimaryKey(UserRegister record);
}