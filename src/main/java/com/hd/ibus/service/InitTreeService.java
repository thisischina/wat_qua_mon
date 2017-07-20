package com.hd.ibus.service;

import com.hd.ibus.pojo.Station;
import com.hd.ibus.result.TreeResultInfo;

import java.util.List;

/**
 * Created by ThinkPad on 2017-07-13.
 */
public interface InitTreeService {

    //查询所有监测站和监测设备
    List<TreeResultInfo> queryAll();

}
