package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.TWaWxUserErrDetail;
import cn.shineyue.wx.po.auto.TWaWxUserErrDetailExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWaWxUserErrDetailMapper {
    int countByExample(TWaWxUserErrDetailExample example);

    int deleteByExample(TWaWxUserErrDetailExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(TWaWxUserErrDetail record);

    int insertSelective(TWaWxUserErrDetail record);

    List<TWaWxUserErrDetail> selectByExample(TWaWxUserErrDetailExample example);

    TWaWxUserErrDetail selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") TWaWxUserErrDetail record, @Param("example") TWaWxUserErrDetailExample example);

    int updateByExample(@Param("record") TWaWxUserErrDetail record, @Param("example") TWaWxUserErrDetailExample example);

    int updateByPrimaryKeySelective(TWaWxUserErrDetail record);

    int updateByPrimaryKey(TWaWxUserErrDetail record);
}