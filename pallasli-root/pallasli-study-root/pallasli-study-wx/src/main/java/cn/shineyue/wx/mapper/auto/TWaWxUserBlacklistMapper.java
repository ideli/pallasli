package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.TWaWxUserBlacklist;
import cn.shineyue.wx.po.auto.TWaWxUserBlacklistExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWaWxUserBlacklistMapper {
    int countByExample(TWaWxUserBlacklistExample example);

    int deleteByExample(TWaWxUserBlacklistExample example);

    int deleteByPrimaryKey(String wxErrOpenid);

    int insert(TWaWxUserBlacklist record);

    int insertSelective(TWaWxUserBlacklist record);

    List<TWaWxUserBlacklist> selectByExample(TWaWxUserBlacklistExample example);

    TWaWxUserBlacklist selectByPrimaryKey(String wxErrOpenid);

    int updateByExampleSelective(@Param("record") TWaWxUserBlacklist record, @Param("example") TWaWxUserBlacklistExample example);

    int updateByExample(@Param("record") TWaWxUserBlacklist record, @Param("example") TWaWxUserBlacklistExample example);

    int updateByPrimaryKeySelective(TWaWxUserBlacklist record);

    int updateByPrimaryKey(TWaWxUserBlacklist record);
}