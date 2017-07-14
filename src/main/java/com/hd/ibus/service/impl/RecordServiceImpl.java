package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.RecordMapper;
import com.hd.ibus.pojo.Record;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.RecordService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.AES;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina .
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<Record> Records = recordMapper.select(pageHelp);
        Integer total = recordMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, Records);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getAccountCount(PageHelp help){
        int count=recordMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public Record selectByKey(PageHelp help){
        Record u=recordMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param Record
     * @return
     */
    public void insertRecord(Record Record){
        recordMapper.insert(Record);

    }

    /**
     * 更新
     * @param Record
     */
    public void updateRecord(Record Record){
        recordMapper.update(Record);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteRecord(Integer id){
        recordMapper.delete(id);
    }
}
