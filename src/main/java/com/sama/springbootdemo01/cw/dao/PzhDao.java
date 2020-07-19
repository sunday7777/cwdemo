package com.sama.springbootdemo01.cw.dao;

import com.sama.springbootdemo01.cw.model.Pzh;
import org.apache.ibatis.annotations.Mapper;

/**
 * 凭证号
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
@Mapper
public interface PzhDao {

    /**
     * 获取当前年月凭证号
     * @param ny
     * @return
     */
    Integer getLastPzhByNy(String ny);

    /**
     * 查询凭证号
     * @param id
     * @return
     */
    Pzh getPzhById(Long id);

    /**
     * 新增凭证号
     * @param pzh
     * @return
     */
    Long savePzh(Pzh pzh);

    /**
     * 删除凭证号
     * @param id
     */
    void removePzh(Long id);

    /**
     * 修改凭证号
     * @param pzh
     * @return
     */
    void updatePzh(Pzh pzh);

}
