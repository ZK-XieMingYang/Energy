package com.qhit.baseDevice.dao;

import org.apache.ibatis.annotations.Mapper;
import com.qhit.baseDevice.pojo.BaseDevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



@Mapper  
public interface IBaseDeviceDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<BaseDevice> search(BaseDevice baseDevice);

    List findByDevname(Object devname);

    List findByTypeid(Object typeid);

    List findByDevdate(Object devdate);

    List findByDevuser(Object devuser);

    List findByCompid(Object compid);
//    List<Map> getUsage(Object compid);
//    List<Map> getintactRatio(Object compid);
//    List<Map> getcost(@Param("year") int year,@Param("compid") int compid);
//    List<Map> getamount(Object compid);
//    List<Map> getconsume(Object compid);
}