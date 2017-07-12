package com.hd.ibus.mapper;

import com.hd.ibus.pojo.Record;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordMapper {

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
    List<Record> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return Record
     */
    Record selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param record
     * @return int
     */
    void insert(Record record);

    /**
     * 更新
     * @param record
     */
    void update(Record record);

    int delete(Integer id);
}