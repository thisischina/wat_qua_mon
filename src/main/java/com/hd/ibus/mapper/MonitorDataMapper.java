package cn.itcast.ssm.mapper2;

import cn.itcast.ssm.po2.MonitorData;
import cn.itcast.ssm.po2.MonitorDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorDataMapper {
    int countByExample(MonitorDataExample example);

    int deleteByExample(MonitorDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MonitorData record);

    int insertSelective(MonitorData record);

    List<MonitorData> selectByExample(MonitorDataExample example);

    MonitorData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MonitorData record, @Param("example") MonitorDataExample example);

    int updateByExample(@Param("record") MonitorData record, @Param("example") MonitorDataExample example);

    int updateByPrimaryKeySelective(MonitorData record);

    int updateByPrimaryKey(MonitorData record);
}