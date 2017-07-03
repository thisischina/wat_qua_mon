package com.hd.ibus.util;

import com.hd.ibus.pojo.IbusUser;

/**
 * 分页工具类.
 */
public class PageHelp {
    private static final long serialVersionUID = -3817231542610983296L;

    private Object object;

    private PageBean pageBean;

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
}
