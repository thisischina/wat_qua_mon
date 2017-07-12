package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.StationMapper;
import com.hd.ibus.pojo.Station;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.StationService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina on 2017/6/30 0030.
 */
@Service
public class StationServiceImpl implements StationService {
    @Resource
    private StationMapper stationMapper;

    private PageHelp pageHelp=PageHelp.getInstance();

    public DataGridResultInfo findList(PageHelp pageHelp,Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<Station> Stations = stationMapper.select(pageHelp);
        Integer total = stationMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, Stations);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getNameCount(PageHelp help){
        int count=stationMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public Station selectByKey(PageHelp help){
        Station u=stationMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param station
     * @return
     */
    public void insertStation(Station station){
        stationMapper.insert(station);

    }

    /**
     * 更新
     * @param station
     */
    public void updateStation(Station station){

        stationMapper.update(station);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteStation(Integer id){
        stationMapper.delete(id);
    }
}
