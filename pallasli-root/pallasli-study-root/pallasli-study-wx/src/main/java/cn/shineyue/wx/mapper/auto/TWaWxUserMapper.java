package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.TWaWxUser;
import cn.shineyue.wx.po.auto.TWaWxUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWaWxUserMapper {
    int countByExample(TWaWxUserExample example);

    int deleteByExample(TWaWxUserExample example);

    int deleteByPrimaryKey(String grbh);

    int insert(TWaWxUser record);

    int insertSelective(TWaWxUser record);

    List<TWaWxUser> selectByExample(TWaWxUserExample example);

    TWaWxUser selectByPrimaryKey(String grbh);

    int updateByExampleSelective(@Param("record") TWaWxUser record, @Param("example") TWaWxUserExample example);

    int updateByExample(@Param("record") TWaWxUser record, @Param("example") TWaWxUserExample example);

    int updateByPrimaryKeySelective(TWaWxUser record);

    int updateByPrimaryKey(TWaWxUser record);
}