package com.qhit.reports;

import com.qhit.baseDevice.dao.IBaseDeviceDao;
import com.qhit.baseDevice.pojo.BaseDevice;
import com.qhit.baseDevtype.dao.IBaseDevtypeDao;
import com.qhit.baseDevtype.pojo.BaseDevtype;
import com.qhit.baseFlow.dao.IBaseFlowDao;
import com.qhit.baseFlow.pojo.BaseFlow;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.produceReport.dao.IProduceReportDao;
import com.qhit.produceReport.pojo.JobInfo;
import com.qhit.produceReport.pojo.JobModelInfo;
import com.qhit.produceReport.service.IProduceReportService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/jobAmount")
public class JobAmount {
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
    @RequestMapping("/flowAmount")
    public Map flowAmount(String year){
        //对数据库传来的字段处理成所需格式
        List<JobInfo> infolist = dao.getflowJobinfo(year);
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
    @RequestMapping("/devTypeAmount")
    public Map devTypeAmount(String year){
        //对数据库传来的字段处理成所需格式
        HashMap hashMap=new HashMap();
        List<JobInfo> infolist = dao.getdevTypeJobInfo(year);
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
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
    @RequestMapping("/devAmount")
    public Map devAmount(String year){
        //对数据库传来的字段处理成所需格式
        HashMap hashMap=new HashMap();
        List<JobInfo> infolist = dao.getdevJobInfo(year);
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
        hashMap.put("columns",columns);
        hashMap.put("rows",mapList);
        return hashMap;
    }
//    @RequestMapping("/model")
//    public Map model(String year,HttpSession session){
//        BaseUser baseUser = (BaseUser) session.getAttribute("baseUser");
//        //对数据库传来的字段处理成所需格式
//        List<JobModelInfo> getcompanyAmountInfo = dao.getcompanyAmountInfo(year,baseUser.getCompid() );
//        List<JobModelInfo> getflowAmountInfo = dao.getflowAmountInfo(year,baseUser.getCompid());
//        Map map=new HashMap();
//        JobModelInfo compinfo = getcompanyAmountInfo.get(0);
//        int index = compinfo.getJobinfo().indexOf(":");
//        map.put("comp",compinfo.getJobinfo().substring(0,index));
//        map.put("amount",compinfo.getJobinfo().substring(index+1,compinfo.getJobinfo().length()));
//        List<Map> flowList=new ArrayList<>();
//        Map flowmap=null;
//        for (JobModelInfo i:getflowAmountInfo){
//            flowmap=new HashMap();
//            flowmap.put("flow",i.getFlowname());
//            flowmap.put("amount",i.getJobinfo());
//            List<Map<String, Object>> devList = dao.getdevAmountInfo(year, i.getFlowid());
//            flowmap.put("children",devList);
//            flowList.add(flowmap);
//        }
//        map.put("children",flowList);
//        return map;
//    }
}
