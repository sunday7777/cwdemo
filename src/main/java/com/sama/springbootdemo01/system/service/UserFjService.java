package com.sama.springbootdemo01.system.service;

import com.sama.springbootdemo01.system.dao.UserFjDao;
import com.sama.springbootdemo01.system.model.UserFj;
import com.sama.springbootdemo01.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserFjService {

    @Autowired
    private UserFjDao userFjDao;

    /**
     * 添加用户附件
     * @param userFj
     * @return
     */
    public boolean userFjAdd(UserFj userFj){
        try{
            userFj.setAddtime(CommonUtils.getNowTime());
            userFjDao.fjAdd(userFj);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 根据id查询附件
     * @param id
     * @return
     */
    public UserFj getUserFjById(Long id){
        try{
            return userFjDao.getUserFjById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关联用户
     * @param id
     * @param glid
     */
    public void setGlid(Long id,Long glid){
        try {
            userFjDao.setGlid(id,glid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<UserFj> listUserFjByUserId(Long userid){
        try{
            return userFjDao.listUserFjByUserId(userid);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
