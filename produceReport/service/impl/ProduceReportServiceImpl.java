package com.qhit.produceReport.service.impl;

import com.qhit.baseFlow.dao.IBaseFlowDao;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseFlow.service.IBaseFlowService;
import com.qhit.energyConsume.dao.IEnergyConsumeDao;
import com.qhit.energyConsume.pojo.EnergyConsume;
import com.qhit.energyConsume.service.IEnergyConsumeService;
import com.qhit.produceJob.dao.IProduceJobDao;
import com.qhit.produceJob.pojo.ProduceJob;
import com.qhit.produceJob.service.IProduceJobService;
import com.qhit.produceReport.pojo.JobInfo;
import com.qhit.produceReport.service.IProduceReportService;

import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhit.produceReport.dao.IProduceReportDao;
import com.qhit.produceReport.pojo.ProduceReport;
import com.qhit.utils.Comuntiltime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service 
public class ProduceReportServiceImpl  implements IProduceReportService {

    @Resource 
    IProduceReportDao dao;
    @Resource
    IBaseFlowDao iBaseFlowDao;
    @Resource
    IProduceJobDao iProduceJobDao;
    @Resource
    IEnergyConsumeDao iEnergyConsumeDao;
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
        ProduceReport produceReport = findById(id);
        return dao.delete(produceReport); 
    } 

    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 

    @Override 
    public ProduceReport findById(Object id) { 
        List<ProduceReport> list =dao.findById(id);
        return  list.get(0);
    } 

    @Override 
    public List<ProduceReport> search(ProduceReport produceReport) { 
        return dao.search(produceReport); 
    }

    @Override
    @Transactional
    public void completeTask(int reportid, int flowid, String startjobtime, String completetime) throws ParseException {
        //更新报岗表
        ProduceReport produceReport = (ProduceReport) dao.findById(reportid).get(0);
        produceReport.setFlowid(flowid);
        produceReport.setCompletetime(completetime);
        produceReport.setStartjobtime(startjobtime);
        dao.updateSelective(produceReport);
        //插入作业信息表
        BaseFlow baseFlow = (BaseFlow) iBaseFlowDao.findById(flowid).get(0);
        Integer dljid = baseFlow.getDljid();
        Integer zcjid = baseFlow.getZcjid();
        Integer pdjid = baseFlow.getPdjid();
        Float totaljob = produceReport.getCapacity();
        ProduceJob dljob=new ProduceJob();
        ProduceJob zcjob=new ProduceJob();
        ProduceJob pdjob=new ProduceJob();
        //插入斗轮机作业信息
        double duraction = Comuntiltime.calc(startjobtime, completetime);
        dljob.setAmount(totaljob*0.4);
        dljob.setCompletetime(completetime);
        dljob.setDevid(dljid);
        dljob.setDuration(duraction);
        dljob.setStarttime(startjobtime);
        dljob.setReportid(reportid);
        iProduceJobDao.insert(dljob);
        //插入装船机机作业信息
        zcjob.setAmount(totaljob*0.4);
        zcjob.setCompletetime(completetime);
        zcjob.setDevid(dljid);
        zcjob.setDuration(duraction);
        zcjob.setStarttime(startjobtime);
        zcjob.setReportid(reportid);
        iProduceJobDao.insert(zcjob);
        //插入皮带机机作业信息
        pdjob.setAmount(totaljob*0.2);
        pdjob.setCompletetime(completetime);
        pdjob.setDevid(dljid);
        pdjob.setDuration(duraction);
        pdjob.setStarttime(startjobtime);
        pdjob.setReportid(reportid);
        iProduceJobDao.insert(pdjob);
        //插入能耗信息表
        double waterCount = Comuntiltime.WaterCount();//用水量
        double dianCount = Comuntiltime.DianCount();//用电量
        double oilCount = Comuntiltime.OilCount();//油耗量
        EnergyConsume Dlconsume=new EnergyConsume();
        EnergyConsume Zcconsume=new EnergyConsume();
        EnergyConsume Pdconsume=new EnergyConsume();
        //斗轮机能耗
        Dlconsume.setDevid(dljid);
        Dlconsume.setElectric(dianCount);
        Dlconsume.setWater(waterCount);
        Dlconsume.setOil(oilCount);
        Dlconsume.setReportid(reportid);
        iEnergyConsumeDao.insert(Dlconsume);
        //装船机机能耗
        Zcconsume.setDevid(zcjid);
        Zcconsume.setElectric(dianCount);
        Zcconsume.setWater(waterCount);
        Zcconsume.setOil(oilCount);
        Zcconsume.setReportid(reportid);
        iEnergyConsumeDao.insert(Dlconsume);
        //皮带机能耗
        Pdconsume.setDevid(pdjid);
        Pdconsume.setElectric(dianCount);
        Pdconsume.setWater(waterCount);
        Pdconsume.setOil(oilCount);
        Pdconsume.setReportid(reportid);
        iEnergyConsumeDao.insert(Dlconsume);
    }
//    @Override
//    public Map flowAmount(String year) {
//        List<JobInfo> infolist = dao.getflowJobinfo(year);
//        List<BaseFlow> list = iBaseFlowDao.findAll();
//        String [] columns=new String[list.size()+1];
//        columns[0]="月份";
//        for (int i = 1; i <columns.length; i++) {
//                columns[i]=list.get(i-1).getFlowname();
//
//        }
//        List<Map> mapList=new ArrayList<>();
//        Map map=null;
//        for(int i=0;i<infolist.size();i++){
//            map=new HashMap<>();
//            map.put("月份",infolist.get(i).getMonth()+"月");
//            String[] info = infolist.get(i).getJobinfo().split(",");
//            for (String j:info){
//                int index = j.indexOf(":");
//                map.put(j.substring(0,index),Integer.parseInt(j.substring(index+1,j.length())));
//            }
//            mapList.add(map);
//        }
//        HashMap hashMap=new HashMap();
//        hashMap.put("columns",columns);
//        hashMap.put("rows",mapList);
//        return hashMap;
//    }
@Override
public Map<String, Object> usage(String year) {
    String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
    Map<String,Object> map = new HashMap<>();
    map.put("year",year);
    List<Map> rows = dao.usage(map);
    Map<String,Object> data = new HashMap<>();
    data.put("columns",columns);
    data.put("rows",rows);
    return data;
}

    @Override
    public Map<String, Object> intactRatio(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.intactRatio(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }

    @Override
    public List<Map> cost(String year, Integer compid) {
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        map.put("compid",compid);
        List<Map> rows = dao.cost(map);
        return rows;
    }

    @Override
    public Map<String, Object> amount(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.amount(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }

    @Override
    public Map<String, Object> consume(String year) {
        String[] columns = {"港口名称","斗轮机","皮带机","装船机"};
        Map<String,Object> map = new HashMap<>();
        map.put("year",year);
        List<Map> rows = dao.consume(map);
        Map<String,Object> data = new HashMap<>();
        data.put("columns",columns);
        data.put("rows",rows);
        return data;
    }
}