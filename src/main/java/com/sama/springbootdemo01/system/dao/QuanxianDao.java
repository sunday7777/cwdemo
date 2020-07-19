package com.sama.springbootdemo01.system.dao;

import com.sama.springbootdemo01.system.model.Quanxian;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限dao
 * @author fjk
 * @date 2019-03-06
 */
@Mapper
public interface QuanxianDao {

    /**
     * 根据groupid获取qianxian
     * @param gid 用户组id
     */
    List<Quanxian> getQuanxianByGId(Long gid);

    /**
     * 根据用户组id删除权限
     * @param gid
     */
    void removeQuanxianByGroupId(Long gid);

    /**
     * 保存用户组权限
     * @param gid
     * @param midList
     */
    void saveQuanxian(@Param("gid") Long gid,@Param("midList") List<String> midList);
}
