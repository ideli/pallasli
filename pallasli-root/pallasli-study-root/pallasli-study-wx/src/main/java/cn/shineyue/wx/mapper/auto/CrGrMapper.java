package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.CrGr;
import cn.shineyue.wx.po.auto.CrGrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CrGrMapper {
    int countByExample(CrGrExample example);

    int deleteByExample(CrGrExample example);

    int deleteByPrimaryKey(String grbh);

    int insert(CrGr record);

    int insertSelective(CrGr record);

    List<CrGr> selectByExampleWithBLOBs(CrGrExample example);

    List<CrGr> selectByExample(CrGrExample example);

    CrGr selectByPrimaryKey(String grbh);

    int updateByExampleSelective(@Param("record") CrGr record, @Param("example") CrGrExample example);

    int updateByExampleWithBLOBs(@Param("record") CrGr record, @Param("example") CrGrExample example);

    int updateByExample(@Param("record") CrGr record, @Param("example") CrGrExample example);

    int updateByPrimaryKeySelective(CrGr record);

    int updateByPrimaryKeyWithBLOBs(CrGr record);

    int updateByPrimaryKey(CrGr record);
}