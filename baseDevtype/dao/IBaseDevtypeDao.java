package com.qhit.baseDevtype.dao;

import org.apache.ibatis.annotations.Mapper;
import com.qhit.baseDevtype.pojo.BaseDevtype;
import java.util.List;


@Mapper  
public interface IBaseDevtypeDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<BaseDevtype> search(BaseDevtype baseDevtype);

    List findByTypename(Object typename);

}