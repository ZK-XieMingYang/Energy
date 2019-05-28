package com.qhit.produceJob.service;

import java.util.List;
import java.util.Map;

import com.qhit.produceJob.pojo.ProduceJob;

import javax.servlet.http.HttpSession;


public interface IProduceJobService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    ProduceJob findById(Object id);

    List<ProduceJob> search(ProduceJob produceJob);
    Map model(String year, HttpSession session);
}