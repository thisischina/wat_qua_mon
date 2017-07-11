package com.hd.ibus.service;

import com.hd.ibus.pojo.Unit;
import com.hd.ibus.pojo.User;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina 0030.
 * 可调用的接口
 */
public interface UnitService {

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
    DataGridResultInfo getNameCount(PageHelp help);

    /**
     * 按条件获取单个对象
     * @param pageHelp
     */
    Unit selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param unit
     * @return
     */
    void insertUnit(Unit unit);

    void updateUnit(Unit unit);

    void deleteUnit(Integer id);
}
