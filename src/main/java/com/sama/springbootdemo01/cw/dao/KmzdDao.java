package com.sama.springbootdemo01.cw.dao;

import com.sama.springbootdemo01.cw.model.Kmzd;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 科目字段Dao
 * @author fjk
 * @since jdk 1.8
 * @date 2019-10-28
 */
@Mapper
public interface KmzdDao {

    /**
     * 查询一级科目
     * @param type
     * @return
     */
    public List<Kmzd> listKmzdOne(int type);

    /**
     * 查询下级科目
     * @param kmbh
     * @return
     */
    public List<Kmzd> listKmzdByPbh(String kmbh);

    /**
     * 根据id查询科目
     * @param id
     * @return
     */
    public Kmzd getKmzdById(Long id);

    /**
     * 新增科目
     * @param kmzd
     */
    public void saveKmzd(Kmzd kmzd);

    /**
     * 修改科目
     * @param kmzd
     */
    public void updateKmzd(Kmzd kmzd);

    /**
     * 删除科目
     * @param id
     */
    public void removeKmzd(Long id);
}
