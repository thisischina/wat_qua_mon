package cn.itcast.ssm.mapper2;

import cn.itcast.ssm.po2.MonitorType;
import cn.itcast.ssm.po2.MonitorTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MonitorTypeMapper {
    int countByExample(MonitorTypeExample example);

    int deleteByExample(MonitorTypeExample example);

    int deleteByPrimaryKey(Integer typeId);

    int insert(MonitorType record);

    int insertSelective(MonitorType record);

    List<MonitorType> selectByExample(MonitorTypeExample example);

    MonitorType selectByPrimaryKey(Integer typeId);

    int updateByExampleSelective(@Param("record") MonitorType record, @Param("example") MonitorTypeExample example);

    int updateByExample(@Param("record") MonitorType record, @Param("example") MonitorTypeExample example);

    int updateByPrimaryKeySelective(MonitorType record);

    int updateByPrimaryKey(MonitorType record);
}