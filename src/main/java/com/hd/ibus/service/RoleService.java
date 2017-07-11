package com.hd.ibus.service;

import com.hd.ibus.pojo.Role;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 * 可调用的接口
 * 角色表
 */
public interface RoleService {
    /**
     * 列表
     * @param help
     * @return
     */
    DataGridResultInfo findList(PageHelp help,Integer pageNow);

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
    Role selectByKey(PageHelp pageHelp);

    /**
     * 添加
     * @param role
     * @return
     */
    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Integer id);
}
