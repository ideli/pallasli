package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.GdDkZz;
import cn.shineyue.wx.po.auto.GdDkZzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GdDkZzMapper {
    int countByExample(GdDkZzExample example);

    int deleteByExample(GdDkZzExample example);

    int deleteByPrimaryKey(String e001);

    int insert(GdDkZz record);

    int insertSelective(GdDkZz record);

    List<GdDkZz> selectByExample(GdDkZzExample example);

    GdDkZz selectByPrimaryKey(String e001);

    int updateByExampleSelective(@Param("record") GdDkZz record, @Param("example") GdDkZzExample example);

    int updateByExample(@Param("record") GdDkZz record, @Param("example") GdDkZzExample example);

    int updateByPrimaryKeySelective(GdDkZz record);

    int updateByPrimaryKey(GdDkZz record);
}