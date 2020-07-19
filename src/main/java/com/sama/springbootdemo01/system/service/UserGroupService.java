package com.sama.springbootdemo01.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sama.springbootdemo01.system.dao.UserGroupDao;
import com.sama.springbootdemo01.system.model.User;
import com.sama.springbootdemo01.system.model.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 用户组
 * @author fjk
 * @date 2019-06-16
 * @since jdk 1.8
 */
@Service
public class UserGroupService {

    @Autowired
    UserService userService;

    @Autowired
    UserGroupDao userGroupDao;

    @Autowired
    private QuanxianService quanxianService;

    /**
     * 分页查询用户组列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<UserGroup> listUserGroupByPage (int pageNo, int pageSize){
        PageHelper.startPage(pageNo,pageSize); //分页
        return userGroupDao.listUserGroupByPage();
    }

    /**
     * 根据ID获取用户组
     * @param id
     * @return
     */
    public UserGroup getUserGroupById(Long id){
        try{
            return userGroupDao.getUserGroupById(id);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加用户组
     * @param up
     * @return
     */
    public boolean saveUserGroup(UserGroup up){
        try{
            //创建时间
            Date nowtime  = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String create_time = sdf.format(nowtime);
            up.setCreate_time(create_time);

            userGroupDao.saveUserGroup(up);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用户组
     * @param up
     * @return
     */
    public boolean updateUserGroup(UserGroup up){
        try{
            userGroupDao.updateUserGroup(up);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除用户组
     */
    public boolean removeUserGroup(Long id){
        try{
            //删除相关权限（这里应该用事务，日后有空优化）
            quanxianService.removeQuanxianByGroupId(id);
            //删除用户组
            userGroupDao.removeUserGroup(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询当前行政区划用户组
     * @return
     */
    public List<UserGroup> listUserGroupByOrgcode(){
        try{
            //查询用户组
            return userGroupDao.listUserGroupByOrgcode();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
