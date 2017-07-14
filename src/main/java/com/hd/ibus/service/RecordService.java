package com.hd.ibus.service;

import com.hd.ibus.pojo.Record;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina .
 * 可调用的接口
 */
public interface RecordService {

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
    Record selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param record
     * @return
     */
    void insertRecord(Record record);

    void updateRecord(Record record);

    void deleteRecord(Integer id);
}
