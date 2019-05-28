package com.qhit.energyConsume.service;

import java.util.List;
import java.util.Map;

import com.qhit.energyConsume.pojo.EnergyConsume;

import javax.servlet.http.HttpSession;


public interface IEnergyConsumeService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    EnergyConsume findById(Object id);

    List<EnergyConsume> search(EnergyConsume energyConsume);
    Map model(String year, HttpSession session);
}