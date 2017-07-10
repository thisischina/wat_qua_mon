package com.hd.ibus.service;

import com.hd.ibus.pojo.User;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 * 可调用的接口
 */
public interface UserService {

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
    User selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param user
     * @return
     */
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
