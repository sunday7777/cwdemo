package com.sama.springbootdemo01.cw.dao;

import com.sama.springbootdemo01.cw.model.Pzk;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 凭证库
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
@Mapper
public interface PzkDao {

    /**
     * 根据凭证id查询凭证库
     * @param pzh_id
     * @return
     */
    List<Pzk> listPzkByPzid(Long pzh_id);

    /**
     * 添加凭证
     * @param pzk
     */
    void savePzk(Pzk pzk);

    /**
     * 批量添加凭证
     * @param pzks
     */
    void savePzkAll(List<Pzk> pzks);

    /**
     * 修改凭证
     * @param pzk
     */
    void updatePzk(Pzk pzk);

    /**
     * 删除凭证库
     * @param id
     */
    void removePzk(Long id);

    /**
     * 根据凭证id删除凭证库
     * @param pzid
     */
    void removePzksByPzId(Long pzid);
}
