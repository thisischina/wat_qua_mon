package com.hd.ibus.service;

import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.util.shenw.PageHelp;

/**
 * Created by ThinkPad on 2017-07-31.
 */
public interface HardworeService {

    public DataGridResultInfo hardworeList(PageHelp pageHelp,Integer pageNow);

    public void order(String dtuId, String orderStr);

}
