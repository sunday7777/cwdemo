package com.sama.springbootdemo01.system.dao;

import com.sama.springbootdemo01.system.model.UserFj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserFjDao {

    /**
     * 上传用户附件
     * @param userFj
     */
    void fjAdd(UserFj userFj);

    /**
     * 根据id查询附件
     * @param id
     * @return
     */
    UserFj getUserFjById(Long id);

    /**
     * 关联id
     * @param id
     * @param glid
     */
    void setGlid(@Param("id") Long id,@Param("glid") Long glid);

    /**
     * 根据用户id查询附件
     * @param userid 用户id
     * @return
     */
    List<UserFj> listUserFjByUserId(Long userid);
}

