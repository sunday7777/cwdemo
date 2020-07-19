package com.sama.springbootdemo01.cw.dao;

import com.sama.springbootdemo01.cw.model.Config;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigDao {

    /**
     * 设置系统参数
     * @param config
     */
    void setConfig(Config config);

    /**
     * 根据id查询系统参数
     * @param id
     * @return
     */
    Config getConfigById(Long id);

}
