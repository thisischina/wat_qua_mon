package com.hd.ibus.mapper;

import com.hd.ibus.pojo.User;
import java.util.List;
import com.hd.ibus.util.shenw.PageHelp;

public interface UserMapper {

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
    List<User> select(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param user
     * @return int
     */
    void insert(User user);

    int delete();
}