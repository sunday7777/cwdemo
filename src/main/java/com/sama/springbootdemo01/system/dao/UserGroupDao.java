package com.sama.springbootdemo01.system.dao;

import com.github.pagehelper.Page;
import com.sama.springbootdemo01.system.model.UserGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户组
 * @author fjk
 * @date 2019-06-13
 * @since jdk 1.8
 */
@Mapper
public interface UserGroupDao {

    /**
     * 分页获取用户组
     */
    Page<UserGroup> listUserGroupByPage();

    /**
     * 根据ID获取用户组
     * @param id
     * @return
     */
    UserGroup getUserGroupById(Long id);

    /**
     * 添加用户组
     * @param up
     */
    void saveUserGroup(UserGroup up);

    /**
     * 修改用户组
     * @param up
     */
    void updateUserGroup(UserGroup up);

    /**
     * 删除用户组
     */
    void removeUserGroup(Long id);

    /**
     * 查询用户组列表
     * @return
     */
    List<UserGroup> listUserGroupByOrgcode();
}
