package com.qhit.energyConsume.service.impl;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.energyConsume.pojo.EnergyAmount;
import com.qhit.energyConsume.service.IEnergyConsumeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhit.energyConsume.dao.IEnergyConsumeDao;
import com.qhit.energyConsume.pojo.EnergyConsume;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;



@Service 
public class EnergyConsumeServiceImpl  implements IEnergyConsumeService {

    @Resource 
    IEnergyConsumeDao dao;

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
        EnergyConsume energyConsume = findById(id); 
        return dao.delete(energyConsume); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public EnergyConsume findById(Object id) { 
        List<EnergyConsume> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<EnergyConsume> search(EnergyConsume energyConsume) { 
        return dao.search(energyConsume); 
    }

    @Override
    public Map model(String year,HttpSession session) {
        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        //对数据库传来的字段处理成所需格式
        List<EnergyAmount> companyAmount = dao.getCompanyAmount(year,baseUser.getCompid());
        List<EnergyAmount> flowAmount = dao.getFlowAmount(year,baseUser.getCompid());
        Map map = new HashMap();
        EnergyAmount compinfo = companyAmount.get(0);
        map.put("comp",compinfo.getCompname());
        map.put("consume",compinfo.getAmount());
        List<Map> flowList = new ArrayList<>();
        Map flowmap = null;
        for (EnergyAmount i : flowAmount) {
            flowmap = new HashMap();
            flowmap.put("flow", i.getFlowname());
            flowmap.put("consume", i.getAmount());
            List<Map<String, Object>> devList = dao.getDevAmount(year,i.getFlowid(),baseUser.getCompid());
            flowmap.put("children", devList);
            flowList.add(flowmap);
        }
        map.put("children", flowList);
        return map;
    }

}