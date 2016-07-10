package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.TWaWxUserDetail;
import cn.shineyue.wx.po.auto.TWaWxUserDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWaWxUserDetailMapper {
    int countByExample(TWaWxUserDetailExample example);

    int deleteByExample(TWaWxUserDetailExample example);

    int deleteByPrimaryKey(String wxOpenid);

    int insert(TWaWxUserDetail record);

    int insertSelective(TWaWxUserDetail record);

    List<TWaWxUserDetail> selectByExample(TWaWxUserDetailExample example);

    TWaWxUserDetail selectByPrimaryKey(String wxOpenid);

    int updateByExampleSelective(@Param("record") TWaWxUserDetail record, @Param("example") TWaWxUserDetailExample example);

    int updateByExample(@Param("record") TWaWxUserDetail record, @Param("example") TWaWxUserDetailExample example);

    int updateByPrimaryKeySelective(TWaWxUserDetail record);

    int updateByPrimaryKey(TWaWxUserDetail record);
}