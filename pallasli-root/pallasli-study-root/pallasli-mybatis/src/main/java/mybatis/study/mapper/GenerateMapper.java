package mybatis.study.mapper;

import java.util.List;
import mybatis.study.model.Generate;
import mybatis.study.model.GenerateExample;
import org.apache.ibatis.annotations.Param;

public interface GenerateMapper {
    int countByExample(GenerateExample example);

    int deleteByExample(GenerateExample example);

    int insert(Generate record);

    int insertSelective(Generate record);

    List<Generate> selectByExampleWithBLOBs(GenerateExample example);

    List<Generate> selectByExample(GenerateExample example);

    int updateByExampleSelective(@Param("record") Generate record, @Param("example") GenerateExample example);

    int updateByExampleWithBLOBs(@Param("record") Generate record, @Param("example") GenerateExample example);

    int updateByExample(@Param("record") Generate record, @Param("example") GenerateExample example);
}