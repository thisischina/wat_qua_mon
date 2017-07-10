package com.hd.ibus.service;

import com.hd.ibus.pojo.Station;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 * 接口:监测站
 */
public interface StationService {

    /**
     * 列表
     * @param help
     * @return
     */
    DataGridResultInfo findList(PageHelp help,Integer pageNow);

    /**
     * 获取同一账号的数量
     * @param help
     * @return
     */
    DataGridResultInfo getNameCount(PageHelp help);

    /**
     * 按条件获取单个对象
     * @param pageHelp
     */
    Station selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param station
     * @return
     */
    void insertStation(Station station);

    void updateStation(Station station);

    void deleteStation(Integer id);
}
