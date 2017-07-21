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
     * account作为加密参数
     * @param user
     */
    public void setNewPassword(User user){
        String account=user.getAccount();
        String password=user.getPassword();

        String passwordJM=AES.encryptGetStr(password,account);
        System.out.println("改密者id:"+user.getId()+"密前:"+user.getPassword()+"密后:"+passwordJM);
        user.setPassword(passwordJM);
    }

    /**
     * 添加
     * @param user
     * @return
     */
    public void insertUser(User user){
        setNewPassword(user);
        userMapper.insert(user);
    }

    public void updateUser(User user){
        userMapper.update(user);
    }


    public void updateUserPassword(User user){
        setNewPassword(user);
        userMapper.update(user);
    }

    public void deleteUser(Integer id){
        userMapper.delete(id);
    }

    public User login(PageHelp help){
        User user=(User)help.getObject();
        String account=user.getAccount();
        String password=user.getPassword();

        String passwordJM=AES.encryptGetStr(password,account);

        user.setPassword(passwordJM);
        help.setObject(user);

        User u=userMapper.login(help);

        if(u!=null){
            return u;
        }else{
            return null;
        }

    }
}
