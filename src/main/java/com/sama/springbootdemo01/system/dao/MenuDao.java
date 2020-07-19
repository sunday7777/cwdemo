package com.sama.springbootdemo01.system.dao;

import com.sama.springbootdemo01.system.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单dao
 * @author fjk
 * @date 2019-03-06
 */
@Mapper
public interface MenuDao {

    /**
     * 查询全部菜单
     */
    public List<Menu> findAll();

    /**
     * 查询一级菜单
     */
    public List<Menu> findOne(String username);

    /**
     * 查询子菜单
     */
    public List<Menu> findSon(@Param("username") String username,@Param("code") String code);

    /**
     * 根据userid获取菜单
     * @param userid //用户ID
     */
    public List<Menu> findMenuByUserid(Long userid);
}
