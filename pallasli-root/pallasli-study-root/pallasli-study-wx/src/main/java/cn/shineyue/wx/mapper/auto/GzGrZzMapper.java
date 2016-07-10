package cn.shineyue.wx.mapper.auto;

import cn.shineyue.wx.po.auto.GzGrZz;
import cn.shineyue.wx.po.auto.GzGrZzExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GzGrZzMapper {
    int countByExample(GzGrZzExample example);

    int deleteByExample(GzGrZzExample example);

    int deleteByPrimaryKey(String a000);

    int insert(GzGrZz record);

    int insertSelective(GzGrZz record);

    List<GzGrZz> selectByExample(GzGrZzExample example);

    GzGrZz selectByPrimaryKey(String a000);

    int updateByExampleSelective(@Param("record") GzGrZz record, @Param("example") GzGrZzExample example);

    int updateByExample(@Param("record") GzGrZz record, @Param("example") GzGrZzExample example);

    int updateByPrimaryKeySelective(GzGrZz record);

    int updateByPrimaryKey(GzGrZz record);
}