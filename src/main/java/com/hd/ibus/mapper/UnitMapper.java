package com.hd.ibus.mapper;

import java.util.List;
import com.hd.ibus.pojo.Unit;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitMapper {
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
    List<Unit> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return Unit
     */
    Unit selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param unit
     * @return int
     */
    void insert(Unit unit);

    /**
     * 更新
     * @param unit
     */
    void update(Unit unit);

    int delete(Integer id);
}