package com.hd.ibus.util.shenw;

import com.hd.ibus.util.Config;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import org.springframework.ui.Model;

import java.io.Serializable;

/**
 * 分页工具类.
 * Created by GitHub:thisischina .
 */
public class PageHelp implements Serializable{
    private static PageHelp pageHelp;
    private PageHelp(){};

    public static PageHelp getInstance(){
        if(pageHelp==null){
            pageHelp=new PageHelp();
        }
        return pageHelp;
    }

    private static final long serialVersionUID = -3817231542610983296L;

    private Object object;

    private PageBean pageBean;

    private String selectStr;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }

    public String getSelectStr() {
        return selectStr;
    }

    public void setSelectStr(String selectStr) {
        this.selectStr = selectStr;
    }

    /**
     * 定义一个初始化方法
     * @param model
     * @param pageNow
     */
    public void getInit(Model model,Integer pageNow){
        if(pageNow!=null&&pageNow==0){
            //初始化
            pageNow = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW);
            Integer pageSize = PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) ;

            PageBean pageBean=new PageBean();

            pageBean.setPageNow(pageNow);
            pageBean.setPageSize(pageSize);
            pageHelp.setPageBean(pageBean);

            //清除搜索条件
            pageHelp.setSelectStr(null);
            model.addAttribute(pageHelp);
        }else{
            model.addAttribute(pageHelp);
        }
    }
}
