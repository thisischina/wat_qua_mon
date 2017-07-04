package com.hd.ibus.service;

import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.PageHelp;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 * 可调用的接口
 */
public interface UserService {
    public DataGridResultInfo getListAll();

    public DataGridResultInfo findList(PageHelp help, Integer pageNow, Integer pageSize);
}
