package com.hd.ibus.mapper;

import com.hd.ibus.pojo.IbusUser;

public interface IbusUserMapper {
   
    int deleteByPrimaryKey(Integer id);

    int insert(IbusUser record);

    int insertSelective(IbusUser record);

    IbusUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IbusUser record);

    int updateByPrimaryKey(IbusUser record);
}