package com.sama.springbootdemo01.cw.service;

import com.sama.springbootdemo01.cw.model.Yspz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 原始凭证
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
@Service
public class YspzService {

    @Autowired
    private YspzService yspzService;

    /**
     * 根据凭证号id查询原始凭证
     * @param pzh_id
     * @return
     */
    public List<Yspz> listYspzByPzid(Long pzh_id){
        try{
            return yspzService.listYspzByPzid(pzh_id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存原始凭证
     * @param yspz
     * @return
     */
    public boolean saveYspz(Yspz yspz){
        try{
            yspzService.saveYspz(yspz);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除原始凭证
     * @param id
     * @return
     */
    public boolean removeYspz(Long id){
        try{
            yspzService.removeYspz(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
