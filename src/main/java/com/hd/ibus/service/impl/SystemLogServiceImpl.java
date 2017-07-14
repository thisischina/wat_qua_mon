package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.SystemLogMapper;
import com.hd.ibus.pojo.SystemLog;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.SystemLogService;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.shenw.PageHelp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by github:thisischina .
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {
    @Resource
    private SystemLogMapper systemLogMapper;

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

//        List<SystemLog> SystemLogs = systemLogMapper.select(pageHelp);
//        Integer total = systemLogMapper.findTotal(pageHelp);

//        DataGridResultInfo da=new DataGridResultInfo(total, SystemLogs);
        DataGridResultInfo da=new DataGridResultInfo();
//        da.setPageNow(pageNow);
        return da;

    }

    public  DataGridResultInfo getAccountCount(PageHelp help){
//        int count=systemLogMapper.paramCount(help);
//        System.out.println("查询用户存在个数:"+count);

//        return new DataGridResultInfo(count, null);
        return new DataGridResultInfo();
    }

    public SystemLog selectByKey(PageHelp help){
//        SystemLog u=systemLogMapper.selectByKey(help);
        SystemLog u= new SystemLog();
        return u;
    }

}
