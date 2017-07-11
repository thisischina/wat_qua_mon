package com.hd.ibus.service;

import com.hd.ibus.pojo.Equipment;
import com.hd.ibus.pojo.Station;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina .
 * 设备
 */
public interface EquipmentService {

    /**
     * 列表
     * @param help
     * @return
     */
    DataGridResultInfo findList(PageHelp help, Integer pageNow);

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
    Equipment selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param equipment
     * @return
     */
    void insertEquipment(Equipment equipment);

    void updateEquipment(Equipment equipment);

    void deleteEquipment(Integer id);
}
