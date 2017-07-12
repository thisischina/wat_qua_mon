package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.RoleMapper;
import com.hd.ibus.pojo.Role;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.RoleService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    private PageHelp pageHelp=PageHelp.getInstance();

    public DataGridResultInfo findList(PageHelp pageHelp,Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<Role> Roles = roleMapper.select(pageHelp);
        Integer total = roleMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, Roles);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getNameCount(PageHelp help){
        int count=roleMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public Role selectByKey(PageHelp help){
        Role u=roleMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param role
     * @return
     */
    public void insertRole(Role role){
        roleMapper.insert(role);

    }

    /**
     * 更新
     * @param role
     */
    public void updateRole(Role role){
        roleMapper.update(role);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRole(Integer id){
        roleMapper.delete(id);
    }
}
