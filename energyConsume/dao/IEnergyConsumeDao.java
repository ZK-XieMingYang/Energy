package com.qhit.energyConsume.dao;

import com.qhit.energyConsume.pojo.EnergyAmount;
import org.apache.ibatis.annotations.Mapper;
import com.qhit.energyConsume.pojo.EnergyConsume;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;



@Mapper  
public interface IEnergyConsumeDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<EnergyConsume> search(EnergyConsume energyConsume);

    List findByDevid(Object devid);

    List findByElectric(Object electric);

    List findByWater(Object water);

    List findByOil(Object oil);

    List findByReportid(Object reportid);
    List<Map<String,Object>> getDevAmount(@Param("year")String year, @Param("flowid") int flowid,@Param("compid") int compid);
    List<EnergyAmount> getFlowAmount(@Param("year")String year, @Param("compid")int compid);
    List<EnergyAmount> getCompanyAmount(@Param("year")String year, @Param("compid")int compid);
}