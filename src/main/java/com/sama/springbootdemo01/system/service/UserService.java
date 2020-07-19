package com.sama.springbootdemo01.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sama.springbootdemo01.system.dao.UserDao;
import com.sama.springbootdemo01.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户Service
 * @author fjk
 * @since 2019-02-24
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> findUserByOrgcode(){
      return userDao.findUserByOrgcode("1");
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    public User getUserById(Long id){
        try{
            User user = userDao.getUserById(id);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分页查询用户列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<User> findUserByPage(int pageNo, int pageSize, String orgCode){
        String xzqh = "";
        if(orgCode != null && !"".equals(orgCode)){
            xzqh = orgCode;
        }else{
            xzqh = loginUserInfo().getOrgcode();
        }
        PageHelper.startPage(pageNo,pageSize); //分页
        return userDao.findUserByPage(xzqh);
    }

    /**
     * 添加用户
     */
    public boolean userAdd(User user){
        try{
            userDao.userAdd(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用户
     */
    public void userEdit(User user){
        try{
            userDao.userEdit(user.getId(), user.getRealname(), user.getUsername(), user.getPassword(), user.getGroupid(), user.getPhone(), user.getEmail(), user.getOrgcode());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     * @param id
     */
    public void userDel(Long id){
        try {
            userDao.userDel(id);
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    /**
     * 获取当前登陆用户信息
     */
    public User loginUserInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        return (User)session.getAttribute("SAMAUSER_USER");
    }


    /**
     * 根据用户组id统计用户数量
     * @param groupid
     * @return
     */
    public int countUserByGroupid(Long groupid){
        try{
            return userDao.countUserByGroupid(groupid);
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    public User userLogin(String username,String password){
        try {
            User user = userDao.userLogin(username,password);

            //更新用户登陆信息
            if(user != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowtime = sdf.format(new Date());
                int nums = user.getNums() + 1;
                userDao.updateLoginInfo(nowtime,nums,user.getId());
            }
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
