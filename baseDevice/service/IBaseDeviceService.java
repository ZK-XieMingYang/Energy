package com.qhit.baseDevice.service;

import java.util.List;
import java.util.Map;

import com.qhit.baseDevice.pojo.BaseDevice;

import javax.servlet.http.HttpSession;


public interface IBaseDeviceService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BaseDevice findById(Object id);

    List<BaseDevice> search(BaseDevice baseDevice);
    List<BaseDevice> findDljByCompid();
    List<BaseDevice> findCzjByCompid();
    List<BaseDevice> findPdjByCompid();
//    Map getUsage(Object year);
//    Map getintactRatio(Object year);
//    List getcost(Object year, HttpSession session);
//    Map getamount(Object year);
//    Map getconsume(Object year);
}