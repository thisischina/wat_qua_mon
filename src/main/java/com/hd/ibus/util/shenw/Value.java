package com.hd.ibus.util.shenw;

/**
 * Created by github:thisischina on 2017/7/5 0005.
 * 存放所有与变量作判断的值
 */
public class Value {
    //   作为增改操作成功返回值
    public static final int IntNumOne=1;

    public static final int IntNumTwo=2;

    //   分地区设置站点编号
    public static final String STATION="Zd";


    //    用户停启用状态
    public static final int USER_STATE_OPEN=1;
    public static final int USER_STATE_CLOSE=0;

    //    用户权限设置参数
    public static final String USER_POWER="admin";

    //    密码正则:只能输入6-20个字母、数字、下划线
    public static final String USER_PASSWORD="/^[a-z0-9_-]{6,18}$/";
    public static final String USER_PASSWORD_EM="只能输入6-20个字母、数字、下划线";
}
