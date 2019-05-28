package com.qhit.produceReport.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.qhit.produceReport.pojo.JobInfo;
import com.qhit.produceReport.pojo.ProduceReport;

public interface IProduceReportService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    ProduceReport findById(Object id);

    List<ProduceReport> search(ProduceReport produceReport);
    void completeTask(int reportid,int flowid,String startjobtime,String completetime) throws ParseException;
//   Map flowAmount(String year);
Map<String,Object> usage(String year);

    Map<String,Object> intactRatio(String year);

    List<Map> cost(String year, Integer compid);

    Map<String,Object> amount(String year);

    Map<String,Object> consume(String year);
}