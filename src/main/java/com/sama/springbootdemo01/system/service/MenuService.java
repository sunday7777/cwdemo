package com.sama.springbootdemo01.system.service;

import com.sama.springbootdemo01.system.dao.MenuDao;
import com.sama.springbootdemo01.system.model.Menu;
import com.sama.springbootdemo01.system.model.Quanxian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单service
 * @since 2019-03-06
 * @author fjk
 */
@Service
public class MenuService {

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserService userService;

    @Autowired
    QuanxianService quanxianService;

    /**
     * 系统菜单树
     * @return
     */
    public List getMenuTreeByUser(){
        List<Menu> allMenu = menuDao.findOne(userService.loginUserInfo().getUsername());

        List menuTree = new ArrayList();
        for(Menu m : allMenu){
            Map map = new HashMap();
            map.put("id",m.getId());
            map.put("text",m.getName());
            map.put("state","closed");
            map.put("checked",true);
            map.put("attributes",m.getUrl());
            map.put("order",m.getSort());
            map.put("children",this.getSon(m.getCode()));
            map.put("iconCls",m.getIcon());

            menuTree.add(map);
        }
        return menuTree;

    }

    public List<Menu> getSon(String code){

        List<Menu> sonMenu = menuDao.findSon(userService.loginUserInfo().getUsername(),code);
        List sonList = new ArrayList();
        for(Menu s : sonMenu){
            Map map = new HashMap();
            map.put("id",s.getId());
            map.put("text",s.getName());
            map.put("state","closed");
            map.put("checked",false);
            map.put("attributes",s.getUrl());
            map.put("order",s.getSort());
            map.put("children",this.getSon(s.getCode()));
            map.put("iconCls",s.getIcon());

            sonList.add(map);

        }

        return sonList;
    }


    /**
     * 权限菜单树
     * @param id
     * @return
     */
    public List getMenuTreeByUser(Long id){

        //查询当前用户组权限
        List<Quanxian> qx = quanxianService.getQuanxianByGId(id);
        List<Long> ids = new ArrayList<>();
        for(Quanxian q : qx){
            ids.add(q.getMenuid());
        }

        //查询当前登陆用户菜单
        List<Menu> allMenu = menuDao.findOne(userService.loginUserInfo().getUsername());

        List menuTree = new ArrayList();
        for(Menu m : allMenu){
            //判断是否拥有权限
            boolean checked = ids.contains(m.getId());
            //组装菜单树
            Map map = new HashMap();
            map.put("id",m.getId());
            map.put("text",m.getName());
            map.put("state","open");
            map.put("checked",checked);
            map.put("attributes",m.getUrl());
            map.put("order",m.getSort());
            map.put("children",this.getSon(ids,m.getCode()));
            map.put("iconCls",m.getIcon());

            menuTree.add(map);
        }
        return menuTree;

    }

    public List<Menu> getSon(List<Long> ids,String code){

        List<Menu> sonMenu = menuDao.findSon(userService.loginUserInfo().getUsername(),code);
        List sonList = new ArrayList();
        for(Menu s : sonMenu){
            boolean checked = ids.contains(s.getId());
            Map map = new HashMap();
            map.put("id",s.getId());
            map.put("text",s.getName());
            map.put("state","open");
            map.put("checked",checked);
            map.put("attributes",s.getUrl());
            map.put("order",s.getSort());
            map.put("children",this.getSon(ids,s.getCode()));
            map.put("iconCls",s.getIcon());

            sonList.add(map);

        }

        return sonList;
    }



}
