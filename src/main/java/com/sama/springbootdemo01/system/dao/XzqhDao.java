package com.sama.springbootdemo01.system.dao;

import com.sama.springbootdemo01.system.model.Xzqh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 行政区划
 * @author fjk
 * @date 2019-05-09
 */
@Mapper
public interface XzqhDao {

    Xzqh findXzqhByCode(String orgcode);

    List<Xzqh> getXzqhByUpcode(String upcode);

    Xzqh findXzqhById(Long id);

    void xzqhAdd(Xzqh xzqh);

    void xzqhEdit(Xzqh xzqh);

    void xzqhDel(String orgcode);

}
