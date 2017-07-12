package com.hd.ibus.mapper;

import com.hd.ibus.pojo.Role;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
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
    List<Role> select(PageHelp help);

    /**
     * 根据条件查找获取单个对象
     * @param help
     * @return Role
     */
    Role selectByKey(PageHelp help);

    /**
     * 按条件查询记录数
     * @param help
     * @return
     */
    int paramCount(PageHelp help);

    /**
     * 添加
     * @param role
     * @return int
     */
    void insert(Role role);

    /**
     * 更新
     * @param role
     */
    void update(Role role);

    int delete(Integer id);
}