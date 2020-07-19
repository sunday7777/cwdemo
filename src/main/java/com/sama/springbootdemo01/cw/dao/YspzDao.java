package com.sama.springbootdemo01.cw.dao;

import com.sama.springbootdemo01.cw.model.Yspz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 原始凭证
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
@Mapper
public interface YspzDao {

    /**
     * 根据凭证号id查询原始凭证
     * @param pzh_id
     * @return
     */
    List<Yspz> listYspzByPzid(Long pzh_id);

    /**
     * 添加原始凭证
     * @param yspz
     */
    void saveYspz(Yspz yspz);

    /**
     * 删除原始凭证
     * @param id
     */
    void removeYspz(Long id);
}
