package com.hd.ibus.mapper;

import com.hd.ibus.pojo.Equipment;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentMapper {
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
    List<Equipment> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return Equipment
     */
    Equipment selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param equipment
     * @return int
     */
    void insert(Equipment equipment);

    /**
     * 更新
     * @param equipment
     */
    void update(Equipment equipment);

    int delete(Integer id);

    //查询所有的设备信息luyan
    List<Equipment> queryAll();
}