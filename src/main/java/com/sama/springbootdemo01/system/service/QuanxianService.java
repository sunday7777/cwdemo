package com.sama.springbootdemo01.system.service;

import com.sama.springbootdemo01.system.dao.QuanxianDao;
import com.sama.springbootdemo01.system.model.Quanxian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 权限Service
 * @since 2019-03-06
 * @author fjk
 */
@Service
public class QuanxianService {
    @Autowired
    private QuanxianDao qd;

    /**
     * 权限dao
     * @since 2019-03-06
     * @author fjk
     */
    public List<Quanxian> getQuanxianByGId(Long gid){
        return qd.getQuanxianByGId(gid);
    }


    /**
     * 根据用户组id删除权限
     * @param gid
     * @return
     */
    public boolean removeQuanxianByGroupId(Long gid){
        try{
            qd.removeQuanxianByGroupId(gid);
            return true;
        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存用户组权限
     * @param gid
     * @param menuIds
     * @return
     */
    public boolean saveQuanxian(Long gid,String menuIds){
        try {
            //删除原有权限
            qd.removeQuanxianByGroupId(gid);
            //添加权限
            String[] mids = menuIds.split(",");
            List<String> midList = Arrays.asList(mids);
            qd.saveQuanxian(gid,midList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
