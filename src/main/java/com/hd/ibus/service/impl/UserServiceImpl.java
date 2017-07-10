package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.UserMapper;
import com.hd.ibus.pojo.User;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.AES;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<User> users = userMapper.select(pageHelp);
        Integer total = userMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, users);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getAccountCount(PageHelp help){
        int count=userMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public User selectByKey(PageHelp help){
        User u=userMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param user
     * @return
     */
    public void insertUser(User user){
        System.out.println("密前:"+user.getPassword());
        String account=user.getAccount();
        String password=user.getPassword();

        String passwordJM=AES.encryptGetStr(password,account);
        System.out.println("密后:"+passwordJM);
        user.setPassword(passwordJM);
        userMapper.insert(user);

    }

    /**
     * 更新
     * @param user
     */
    public void updateUser(User user){
        userMapper.update(user);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteUser(Integer id){
        userMapper.delete(id);
    }
}
