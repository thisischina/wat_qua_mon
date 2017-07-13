package com.hd.ibus.mapper;

import com.hd.ibus.pojo.Station;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationMapper {
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
    List<Station> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return User
     */
    Station selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param station
     * @return int
     */
    void insert(Station station);

    /**
     * 更新
     * @param station
     */
    void update(Station station);

    int delete(Integer id);

    //查询所有的监测站luyan
    List<Station> queryAll();

}