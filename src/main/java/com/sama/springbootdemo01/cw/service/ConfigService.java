package com.sama.springbootdemo01.cw.service;

import com.sama.springbootdemo01.cw.dao.ConfigDao;
import com.sama.springbootdemo01.cw.model.Config;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    @Autowired
    private ConfigDao configDao;

    /**
     * 设置系统参数
     * @param config
     * @return
     */
    public boolean setConfig(Config config){
        try {
            //设置默认账套，目前只做一个账套
            config.setId((long)1);
            config.setOrgcode("37");
            configDao.setConfig(config);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据id查询系统参数
     * @param id
     * @return
     */
    public Config getConfigById(Long id){
        try{
            return configDao.getConfigById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
