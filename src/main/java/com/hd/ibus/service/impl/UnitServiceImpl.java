package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.UnitMapper;
import com.hd.ibus.pojo.Unit;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.UnitService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina.
 */
@Service
public class UnitServiceImpl implements UnitService {
    @Resource
    private UnitMapper unitMapper;

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<Unit> Units = unitMapper.select(pageHelp);
        Integer total = unitMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, Units);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getNameCount(PageHelp help){
        int count=unitMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public Unit selectByKey(PageHelp help){
        Unit u=unitMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param unit
     * @return
     */
    public void insertUnit(Unit unit){
        unitMapper.insert(unit);
    }

    /**
     * 更新
     * @param unit
     */
    public void updateUnit(Unit unit){
        unitMapper.update(unit);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteUnit(Integer id){
        unitMapper.delete(id);
    }
}
