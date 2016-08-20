package com.pallasli.webapp.edu.mapper;

import com.pallasli.webapp.edu.model.AuthorityResource;
import com.pallasli.webapp.edu.model.AuthorityResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityResourceMapper {
    int countByExample(AuthorityResourceExample example);

    int deleteByExample(AuthorityResourceExample example);

    int insert(AuthorityResource record);

    int insertSelective(AuthorityResource record);

    List<AuthorityResource> selectByExample(AuthorityResourceExample example);

    int updateByExampleSelective(@Param("record") AuthorityResource record, @Param("example") AuthorityResourceExample example);

    int updateByExample(@Param("record") AuthorityResource record, @Param("example") AuthorityResourceExample example);
}