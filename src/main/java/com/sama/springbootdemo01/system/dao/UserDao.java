package com.sama.springbootdemo01.system.dao;

import com.github.pagehelper.Page;
import com.sama.springbootdemo01.system.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 根据用户名查找用户
     * @param name 用户名
     */
    User getUserByName(String name);

    /**
     * 根据用户名ID查找用户
     * @param id 用户id
     */
    User getUserById(Long id);


    /**
     * 根据行政区划代码获取用户表
     * @param orgcode
     * @return
     */
    List<User> findUserByOrgcode(String orgcode);

    /**
     * 分页查询用户列表
     * @return
     */
    Page<User> findUserByPage(String orgcode);

    /**
     * 添加用户
     */
    void userAdd(User user);

    /**
     * 修改用户
     * @param id
     * @param realname
     * @param username
     * @param password
     * @param groupid
     * @param phone
     * @param email
     * @param orgcode
     */
    void userEdit(@Param("id") Long id, @Param("realname") String realname,@Param("username") String username,@Param("password") String password,@Param("groupid") Integer groupid,@Param("phone") String phone,@Param("email") String email,@Param("orgcode") String orgcode);

    /**
     * 删除用户
     * @param id
     */
    void userDel(Long id);

    /**
     * 根据用户组id统计用户数量
     * @param groupid
     * @return
     */
    int countUserByGroupid(Long groupid);

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    User userLogin(@Param("username") String username, @Param("password") String password);

    void updateLoginInfo(@Param("lastlogintime") String lastlogintime,@Param("nums") int nums,@Param("id") Long id);

}
