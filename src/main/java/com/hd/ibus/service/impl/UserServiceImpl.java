package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.UserMapper;
import com.hd.ibus.pojo.User;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.AES;
import com.hd.ibus.util.shenw.PageHelp;
import com.hd.ibus.util.PropertiesUtils;
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

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow, Integer pageSize) {
        pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW) : pageNow;
        pageSize = pageSize == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) : pageSize;
        pageHelp = PageHelp.getInstance();

        PageBean pageBean = new PageBean(pageNow, pageSize);
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

    public User selectByKey(PageHelp help){
        User u=userMapper.selectByKey(help);
        return u;
    }
}
