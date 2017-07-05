package com.hd.ibus.mapper;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.User;
import java.util.List;

import com.hd.ibus.pojo.vo.IbusUserVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.PageHelp;

public interface UserMapper {
    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();

    int findTotal(PageHelp help);

    List<User> listPage(PageHelp help);

    void insert(User user);

}