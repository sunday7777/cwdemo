package com.sama.springbootdemo01.cw.service;

import com.sama.springbootdemo01.cw.dao.PzkDao;
import com.sama.springbootdemo01.cw.model.Pzk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * 凭证库
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
@Service
public class PzkService {

    @Autowired
    private PzkDao pzkDao;

    /**
     * 根据凭证id查询凭证库
     * @param pzh_id
     * @return
     */
    public List<Pzk> listPzkByPzid(Long pzh_id){
        try {
            return pzkDao.listPzkByPzid(pzh_id);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 保存凭证
     * @param pzk
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean savePzk(Pzk pzk){

        try {
            if (pzk.getId() == null){
                pzkDao.savePzk(pzk);
            }else{
                pzkDao.updatePzk(pzk);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;

        }
    }

    /**
     * 删除凭证
     * @param id
     * @return
     */
    public boolean removePzk(Long id){
        try {
            pzkDao.removePzk(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 批量添加
     * @param pzks
     * @return
     */
    @Transactional
    public boolean savePzkAll(List<Pzk> pzks){
        try {
            pzkDao.savePzkAll(pzks);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    /**
     * 根据凭证id删除凭证库
     * @param pzid 凭证id
     * @return
     */
    @Transactional
    public boolean removePzksByPzId(Long pzid){
        try {
            pzkDao.removePzksByPzId(pzid);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

}
