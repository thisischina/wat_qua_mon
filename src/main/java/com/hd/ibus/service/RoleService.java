package com.hd.ibus.service;

import com.hd.ibus.pojo.Role;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.PageHelp;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 * 可调用的接口
 */
public interface RoleService {
    /**
     * 获取分页查询信息
     * @param help
     * @param pageNow
     * @param pageSize
     * @return
     */
    public DataGridResultInfo findList(PageHelp help, Integer pageNow, Integer pageSize);

    /**
     * 获取对象
     * 根据传入对象的属性参数查询对象
     * @param help
     * @return Role
     */
    public Role getObject(PageHelp help);
}
