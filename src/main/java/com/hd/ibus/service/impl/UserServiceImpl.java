package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.UserMapper;
import com.hd.ibus.pojo.User;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.UserService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PageHelp;
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

    public DataGridResultInfo getListAll(){
        List<User> list=userMapper.selectAll();

        return new DataGridResultInfo(1, list);
    };

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow, Integer pageSize) {
        pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW) : pageNow;
        pageSize = pageSize == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) : pageSize;
        pageHelp = pageHelp == null ? new PageHelp() : pageHelp;

        PageBean pageBean = new PageBean(pageNow, pageSize);
        pageHelp.setPageBean(pageBean);

         List<User> users = this.userMapper.listPage(pageHelp);
        Integer total = this.userMapper.findTotal(pageHelp);

        return new DataGridResultInfo(total, users);
    }
}
