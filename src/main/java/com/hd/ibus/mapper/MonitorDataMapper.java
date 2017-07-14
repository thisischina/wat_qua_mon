package com.hd.ibus.mapper;

import java.util.List;
import com.hd.ibus.pojo.MonitorData;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

/**
 * Created by GitHub:thisischina .
 */
@Repository
public interface MonitorDataMapper {
    /**
     * 查询
     * @param help
     * @return int
     */
    int findTotal(PageHelp help);

    /**
     * 查询
     * @param help
     * @return list
     */
    List<MonitorData> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return MonitorData
     */
    MonitorData selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param monitorData
     * @return int
     */
    void insert(MonitorData monitorData);

    /**
     * 更新
     * @param monitorData
     */
    void update(MonitorData monitorData);

    int delete(Integer id);
}