package com.qhit.produceReport.dao;

import com.qhit.produceReport.pojo.JobInfo;
import com.qhit.produceReport.pojo.JobModelInfo;
import org.apache.ibatis.annotations.Mapper;
import com.qhit.produceReport.pojo.ProduceReport;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



@Mapper  
public interface IProduceReportDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<ProduceReport> search(ProduceReport produceReport);

    List findByShipname(Object shipname);

    List findByCapacity(Object capacity);

    List findByPlanjobtime(Object planjobtime);

    List findByStartjobtime(Object startjobtime);

    List findByCompletetime(Object completetime);

    List findByFlowid(Object flowid);

    List findByReportuser(Object reportuser);

    List findByCompid(Object compid);
    List<JobInfo> getflowJobinfo(String year);
    List<JobInfo> getdevTypeJobInfo(String year);
    List<JobInfo> getdevJobInfo(String year);
    List<JobInfo> getflowConsumeinfo(String year);
    List<JobInfo> getdevTypeConsumeinfo(String year);
    List<JobInfo> getdevConsumeinfo(String year);
    List<JobInfo> getelectricConsumeinfo(String year);
    List<JobInfo> getwaterConsumeinfo(String year);
    List<JobInfo> getoilConsumeinfo(String year);
    List<Map<String,Object>> getdevAmountInfo(@Param("year")String year,@Param("compid") int compid, @Param("flowid") int flowid);
    List<JobModelInfo> getflowAmountInfo(@Param("year")String year, @Param("compid")int compid);
    List<JobModelInfo> getcompanyAmountInfo(@Param("year")String year, @Param("compid")int compid);
    List<Map> usage(Map<String, Object> map);

    List<Map> intactRatio(Map<String, Object> map);

    List<Map> cost(Map<String, Object> map);

    List<Map> amount(Map<String, Object> map);

    List<Map> consume(Map<String, Object> map);

}