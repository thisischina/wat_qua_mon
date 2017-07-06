package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.RoleMapper;
import com.hd.ibus.pojo.Role;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.RoleService;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleMapper;

    public DataGridResultInfo findList(PageHelp help, Integer pageNow, Integer pageSize) {
        return null;
    }

    public Role getObject(PageHelp help) {
        return roleMapper.getObjectByPageHelp(help);
    }
}
