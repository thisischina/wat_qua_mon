package com.hd.ibus.mapper;

import com.hd.ibus.pojo.Role;
import com.hd.ibus.util.PageHelp;

public interface RoleMapper {
    /**
     * 根据传入对象的属性参数查询对象
     * @param help
     * @return Role
     */
    Role getObjectByPageHelp(PageHelp help);

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}