package com.hd.ibus.util.shenw;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by github:thisischina on 2017/6/21 0021.
 * 帮助类
 * 处理页面传过来的参数，如name值
 */
public class PageStr {

    public static String getParameterStr(String string,HttpServletRequest request) throws NullPointerException{
        String str="";
        if(request.getParameter(string)!=null){
            str=request.getParameter(string);
            if(string.trim().equals("")){
                return "";
            }
        }
        return str;
    }

}
