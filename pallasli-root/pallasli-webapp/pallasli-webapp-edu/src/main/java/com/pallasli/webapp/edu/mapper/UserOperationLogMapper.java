package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.UserOperationLog;
import com.pallasli.webapp.edu.model.UserOperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOperationLogMapper {
    int countByExample(UserOperationLogExample example);

    int deleteByExample(UserOperationLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserOperationLog record);

    int insertSelective(UserOperationLog record);

    List<UserOperationLog> selectByExample(UserOperationLogExample example);

    UserOperationLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserOperationLog record, @Param("example") UserOperationLogExample example);

    int updateByExample(@Param("record") UserOperationLog record, @Param("example") UserOperationLogExample example);

    int updateByPrimaryKeySelective(UserOperationLog record);

    int updateByPrimaryKey(UserOperationLog record);
}