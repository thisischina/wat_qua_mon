package com.hd.ibus.mapper;

import com.hd.ibus.pojo.News;
import java.util.List;

import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsMapper {
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
    List<News> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return News
     */
    News selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param news
     * @return int
     */
    void insert(News news);
}