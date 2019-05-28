package com.qhit.produceFault.service;

import java.util.List;
import com.qhit.produceFault.pojo.ProduceFault;

public interface IProduceFaultService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    ProduceFault findById(Object id);

    List<ProduceFault> search(ProduceFault produceFault);

}