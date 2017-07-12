package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.EquipmentMapper;
import com.hd.ibus.pojo.Equipment;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.EquipmentService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina .
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Resource
    private EquipmentMapper equipmentMapper;

    private PageHelp pageHelp=PageHelp.getInstance();

    public DataGridResultInfo findList(PageHelp pageHelp,Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<Equipment> Equipments = equipmentMapper.select(pageHelp);
        Integer total = equipmentMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, Equipments);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getNameCount(PageHelp help){
        int count=equipmentMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public Equipment selectByKey(PageHelp help){
        Equipment u=equipmentMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param equipment
     * @return
     */
    public void insertEquipment(Equipment equipment){
        equipmentMapper.insert(equipment);

    }

    /**
     * 更新
     * @param equipment
     */
    public void updateEquipment(Equipment equipment){
        equipmentMapper.update(equipment);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteEquipment(Integer id){
        equipmentMapper.delete(id);
    }
}
