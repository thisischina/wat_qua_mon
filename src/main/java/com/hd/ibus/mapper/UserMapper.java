package com.hd.ibus.mapper;

import com.hd.ibus.pojo.User;
import java.util.List;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

/**
 * Created by GitHub:thisischina .
 * 用户
 */

@Repository
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
     * 根据条件查找获取单个对象
     * @param help
     * @return User
     */
    User selectByKey(PageHelp help);

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

    /**
     * 更新
     * @param user
     */
    void update(User user);

    int delete(Integer id);

    /**
     * 登陆
     * @param help
     * @return
     */
    User login(PageHelp help);
}