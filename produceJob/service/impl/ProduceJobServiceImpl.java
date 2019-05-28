package com.qhit.produceJob.service.impl;

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.produceJob.service.IProduceJobService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhit.produceJob.dao.IProduceJobDao;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceReport.dao.IProduceReportDao;
import com.qhit.produceReport.pojo.JobModelInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;



@Service 
public class ProduceJobServiceImpl  implements IProduceJobService {

    @Resource 
    IProduceJobDao dao;
    @Resource
    IProduceReportDao pdao;
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
        ProduceJob produceJob = findById(id); 
        return dao.delete(produceJob); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public ProduceJob findById(Object id) { 
        List<ProduceJob> list = dao.findById(id); 
        return  list.get(0); 
    } 

    @Override 
    public List<ProduceJob> search(ProduceJob produceJob) { 
        return dao.search(produceJob); 
    }

    @Override
    public Map model(String year, HttpSession session) {
        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
        //对数据库传来的字段处理成所需格式
        List<JobModelInfo> getcompanyAmountInfo = pdao.getcompanyAmountInfo(year, baseUser.getCompid());
        List<JobModelInfo> getflowAmountInfo = pdao.getflowAmountInfo(year, baseUser.getCompid());
        Map map = new HashMap();
        JobModelInfo compinfo = getcompanyAmountInfo.get(0);
        int index = compinfo.getJobinfo().indexOf(":");
        map.put("comp", compinfo.getJobinfo().substring(0, index));
        map.put("amount", compinfo.getJobinfo().substring(index + 1, compinfo.getJobinfo().length()));
        List<Map> flowList = new ArrayList<>();
        Map flowmap = null;
        for (JobModelInfo i : getflowAmountInfo) {
            flowmap = new HashMap();
            flowmap.put("flow", i.getFlowname());
            flowmap.put("amount", i.getJobinfo());
            List<Map<String, Object>> devList = pdao.getdevAmountInfo(year, baseUser.getCompid(),i.getFlowid());
            flowmap.put("children", devList);
            flowList.add(flowmap);
        }
        map.put("children", flowList);
        return map;
    }
}