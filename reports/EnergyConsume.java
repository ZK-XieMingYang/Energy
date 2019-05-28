package com.qhit.reports;

import com.qhit.baseDevice.dao.IBaseDeviceDao;
import com.qhit.baseDevice.pojo.BaseDevice;
import com.qhit.baseDevtype.dao.IBaseDevtypeDao;
import com.qhit.baseDevtype.pojo.BaseDevtype;
import com.qhit.baseFlow.dao.IBaseFlowDao;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.produceReport.dao.IProduceReportDao;
import com.qhit.produceReport.pojo.JobInfo;
import com.qhit.produceReport.service.IProduceReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/energyConsume")
public class EnergyConsume {
    @Resource
    IProduceReportService produceReportService;
    @Resource
    IProduceReportDao dao;
    @Resource
    IBaseFlowDao iBaseFlowDao;
    @Resource
    IBaseDevtypeDao iBaseDevtypeDao;
    @Resource
    IBaseDeviceDao iBaseDeviceDao;
@RequestMapping("/flowConsume")
    public Map flowConsume(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getflowConsumeinfo(year);
        List<BaseFlow> list = iBaseFlowDao.findAll();
        String [] columns=new String[list.size()+1];
        columns[0]="月份";
        for (int i = 1; i <columns.length; i++) {
            columns[i]=list.get(i-1).getFlowname();

        }
        List<Map> mapList=new ArrayList<>();
        Map map=null;
        for(int i=0;i<infolist.size();i++){
            map=new HashMap<>();
            map.put("月份",infolist.get(i).getMonth()+"月");
            String[] info = infolist.get(i).getJobinfo().split(",");
            for (String j:info){
                int index = j.indexOf(":");
                map.put(j.substring(0,index),Double.parseDouble(j.substring(index+1,j.length())));
            }
            mapList.add(map);
        }
        HashMap hashMap=new HashMap();
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
    @RequestMapping("/devTypeConsume")
    public Map devTypeConsume(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getdevTypeConsumeinfo(year);
        List<BaseDevtype> list = iBaseDevtypeDao.findAll();
        String [] columns=new String[list.size()+1];
        columns[0]="月份";
        for (int i = 1; i <columns.length; i++) {
            columns[i]=list.get(i-1).getTypename();

        }
        List<Map> mapList=new ArrayList<>();
        Map map=null;
        for(int i=0;i<infolist.size();i++){
            map=new HashMap<>();
            map.put("月份",infolist.get(i).getMonth()+"月");
            String[] info = infolist.get(i).getJobinfo().split(",");
            for (String j:info){
                int index = j.indexOf(":");
                map.put(j.substring(0,index),Double.parseDouble(j.substring(index+1,j.length())));
            }
            mapList.add(map);
        }
        HashMap hashMap=new HashMap();
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
    @RequestMapping("/devConsume")
    public Map devConsume(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getdevConsumeinfo(year);
        List<BaseDevice> list = iBaseDeviceDao.findAll();
        String [] columns=new String[list.size()+1];
        columns[0]="月份";
        for (int i = 1; i <columns.length; i++) {
            columns[i]=list.get(i-1).getDevname();

        }
        List<Map> mapList=new ArrayList<>();
        Map map=null;
        for(int i=0;i<infolist.size();i++){
            map=new HashMap<>();
            map.put("月份",infolist.get(i).getMonth()+"月");
            String[] info = infolist.get(i).getJobinfo().split(",");
            for (String j:info){
                int index = j.indexOf(":");
                map.put(j.substring(0,index),Double.parseDouble(j.substring(index+1,j.length())));
            }
            mapList.add(map);
        }
        HashMap hashMap=new HashMap();
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
    @RequestMapping("/electricConsume")
    public Map electricConsume(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getelectricConsumeinfo(year);
        List<BaseDevice> list = iBaseDeviceDao.findAll();
        String [] columns=new String[list.size()+1];
        columns[0]="月份";
        for (int i = 1; i <columns.length; i++) {
            columns[i]=list.get(i-1).getDevname();

        }
        List<Map> mapList=new ArrayList<>();
        Map map=null;
        for(int i=0;i<infolist.size();i++){
            map=new HashMap<>();
            map.put("月份",infolist.get(i).getMonth()+"月");
            String[] info = infolist.get(i).getJobinfo().split(",");
            for (String j:info){
                int index = j.indexOf(":");
                map.put(j.substring(0,index),Double.parseDouble(j.substring(index+1,j.length())));
            }
            mapList.add(map);
        }
        HashMap hashMap=new HashMap();
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
    @RequestMapping("/waterConsume")
    public Map waterConsume(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getwaterConsumeinfo(year);
        List<BaseDevice> list = iBaseDeviceDao.findAll();
        String [] columns=new String[list.size()+1];
        columns[0]="月份";
        for (int i = 1; i <columns.length; i++) {
            columns[i]=list.get(i-1).getDevname();

        }
        List<Map> mapList=new ArrayList<>();
        Map map=null;
        for(int i=0;i<infolist.size();i++){
            map=new HashMap<>();
            map.put("月份",infolist.get(i).getMonth()+"月");
            String[] info = infolist.get(i).getJobinfo().split(",");
            for (String j:info){
                int index = j.indexOf(":");
                map.put(j.substring(0,index),Double.parseDouble(j.substring(index+1,j.length())));
            }
            mapList.add(map);
        }
        HashMap hashMap=new HashMap();
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
    @RequestMapping("/oilConsume")
    public Map oilConsume(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getoilConsumeinfo(year);
        List<BaseDevice> list = iBaseDeviceDao.findAll();
        String [] columns=new String[list.size()+1];
        columns[0]="月份";
        for (int i = 1; i <columns.length; i++) {
            columns[i]=list.get(i-1).getDevname();

        }
        List<Map> mapList=new ArrayList<>();
        Map map=null;
        for(int i=0;i<infolist.size();i++){
            map=new HashMap<>();
            map.put("月份",infolist.get(i).getMonth()+"月");
            String[] info = infolist.get(i).getJobinfo().split(",");
            for (String j:info){
                int index = j.indexOf(":");
                map.put(j.substring(0,index),Double.parseDouble(j.substring(index+1,j.length())));
            }
            mapList.add(map);
        }
        HashMap hashMap=new HashMap();
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
}
