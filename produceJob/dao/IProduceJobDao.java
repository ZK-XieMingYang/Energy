package com.qhit.produceJob.dao;

import org.apache.ibatis.annotations.Mapper;
import com.qhit.produceJob.pojo.ProduceJob;
import java.util.List;



@Mapper  
public interface IProduceJobDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<ProduceJob> search(ProduceJob produceJob);

    List findByDevid(Object devid);

    List findByStarttime(Object starttime);

    List findByCompletetime(Object completetime);

    List findByDuration(Object duration);

    List findByAmount(Object amount);

    List findByReportid(Object reportid);

}