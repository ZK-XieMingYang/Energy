package com.qhit.baseDevice.service.impl;

import com.qhit.baseDevice.service.IBaseDeviceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhit.baseDevice.dao.IBaseDeviceDao;
import com.qhit.baseDevice.pojo.BaseDevice;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseUser.pojo.BaseUser;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;



@Service 
public class BaseDeviceServiceImpl  implements IBaseDeviceService {

    @Resource 
    IBaseDeviceDao dao;

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 

    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 

    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 

    @Override 
    public boolean delete(Object id) { 
        BaseDevice baseDevice = findById(id); 
        return dao.delete(baseDevice); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public BaseDevice findById(Object id) { 
        List<BaseDevice> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<BaseDevice> search(BaseDevice baseDevice) { 
        return dao.search(baseDevice); 
    }

    @Override
    public List<BaseDevice> findDljByCompid() {
        return dao.findByTypeid(1);
    }

    @Override
    public List<BaseDevice> findCzjByCompid() {
        return dao.findByTypeid(2);
    }

    @Override
    public List<BaseDevice> findPdjByCompid() {
        return dao.findByTypeid(3);
    }

//    @Override
//    public Map getUsage(Object year) {
//        List<Map<String, Object>> rowsmap = dao.getUsage(year);
//        String [] columns={"compname", "devType", "ratio"};
//        Map map=new HashMap();
//        map.put("columns",columns);
//        map.put("rows",rowsmap);
//        return map;
//    }
//
//    @Override
//    public Map getintactRatio(Object year) {
//        List<Map<String, Object>> rowsmap = dao.getintactRatio(year);
//        String [] columns={"compname", "devType", "ratio"};
//        Map map=new HashMap();
//        map.put("columns",columns);
//        map.put("rows",rowsmap);
//        return map;
//    }
//
//    @Override
//    public List getcost(Object year, HttpSession session) {
//        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
//        List<Map<String, Object>> listmap = dao.getcost((Integer) year,baseUser.getCompid());
//        return listmap;
//    }
//
//    @Override
//    public Map getamount(Object year) {
//        List<Map<String, Object>> rowsmap = dao.getamount(year);
//        String [] columns={"compname", "devType", "amount"};
//        Map map=new HashMap();
//        map.put("columns",columns);
//        map.put("rows",rowsmap);
//        return map;
//    }
//
//    @Override
//    public Map getconsume(Object year) {
//        List<Map<String, Object>> rowsmap = dao.getconsume(year);
//        String [] columns={"compname", "devType", "consume"};
//        Map map=new HashMap();
//        map.put("columns",columns);
//        map.put("rows",rowsmap);
//        return map;
//    }

}