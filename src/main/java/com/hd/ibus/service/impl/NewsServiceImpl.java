package com.hd.ibus.service.impl;

import com.hd.ibus.mapper.NewsMapper;
import com.hd.ibus.pojo.News;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.NewsService;
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
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    public DataGridResultInfo findList(PageHelp pageHelp, Integer pageNow) {
        PageBean pageBean=pageHelp.getPageBean();
        int pageSize=pageBean.getPageSize();

        //从新计算查询起始行
        pageBean.getStartRow(pageNow,pageSize);

        pageHelp.setPageBean(pageBean);

        List<News> Newss = newsMapper.select(pageHelp);
        Integer total = newsMapper.findTotal(pageHelp);

        DataGridResultInfo da=new DataGridResultInfo(total, Newss);

        da.setPageNow(pageNow);
        return da;
    }

    public  DataGridResultInfo getAccountCount(PageHelp help){
        int count=newsMapper.paramCount(help);
        System.out.println("查询用户存在个数:"+count);

        return new DataGridResultInfo(count, null);
    }

    public News selectByKey(PageHelp help){
        News u=newsMapper.selectByKey(help);
        return u;
    }

    /**
     * 添加
     * @param news
     * @return
     */
    public void insertNews(News news){
        newsMapper.insert(news);

    }

}
