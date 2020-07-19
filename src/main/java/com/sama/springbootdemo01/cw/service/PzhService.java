package com.sama.springbootdemo01.cw.service;

import com.sama.springbootdemo01.cw.dao.PzhDao;
import com.sama.springbootdemo01.cw.model.Pzh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 凭证号
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
@Service
public class PzhService {

    @Autowired
    private PzhDao pzhDao;

    /**
     * 根据当前年月获取凭证号
     * @param ny
     * @return
     */
    public int getLastPzhByNy(String ny){
        Integer pzh = pzhDao.getLastPzhByNy(ny);
        return (pzh==null?0:pzh)+1;
    }

    /**
     * 查询凭证号
     * @param id
     * @return
     */
    public Pzh getPzhById(Long id){
        try{
            return pzhDao.getPzhById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 保存凭证号
     * @param pzh
     * @return
     */
    public Long savePzh(Pzh pzh){
        try{
            return pzhDao.savePzh(pzh);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除凭证号
     * @param id
     * @return
     */
    public boolean removePzh(Long id){
        try {
            //需先删除pzk和原始凭证
            pzhDao.removePzh(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改凭证号
     * @param pzh
     * @return
     */
    @Transactional
    public boolean updatePzh(Pzh pzh){
        try {
            pzhDao.updatePzh(pzh);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
