package com.hd.ibus.service;

import com.hd.ibus.pojo.MonitorData;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina .
 */
public interface MonitorDataService {

    /**
     * 列表
     * @param help
     * @param pageNow
     * @return
     */
    DataGridResultInfo findList(PageHelp help, Integer pageNow);

    /**
     * 获取同一账号的数量
     * @param help
     * @return
     */
    DataGridResultInfo getAccountCount(PageHelp help);

    /**
     * 按条件获取单个对象
     * @param pageHelp
     */
    MonitorData selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param monitorData
     * @return
     */
    void insertMonitorData(MonitorData monitorData);

    void updateMonitorData(MonitorData monitorData);

    void deleteMonitorData(Integer id);

}
