package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.MonitorDataMapper;
import com.hd.ibus.pojo.MonitorData;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.MonitorDataService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina .
 */

@Service
public class MonitorDataServiceImpl implements MonitorDataService {
    @Resource
    private MonitorDataMapper monitorDataMapper;

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<MonitorData> MonitorDatas = monitorDataMapper.select(pageHelp);
        Integer total = monitorDataMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, MonitorDatas);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getAccountCount(PageHelp help){
        int count=monitorDataMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public MonitorData selectByKey(PageHelp help){
        MonitorData u=monitorDataMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param monitorData
     * @return
     */
    public void insertMonitorData(MonitorData monitorData){
        monitorDataMapper.insert(monitorData);

    }

    /**
     * 更新
     * @param monitorData
     */
    public void updateMonitorData(MonitorData monitorData){
        monitorDataMapper.update(monitorData);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteMonitorData(Integer id){
        monitorDataMapper.delete(id);
    }
}
