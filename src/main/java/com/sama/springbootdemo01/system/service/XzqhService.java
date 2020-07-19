package com.sama.springbootdemo01.system.service;

import com.sama.springbootdemo01.system.dao.UserDao;
import com.sama.springbootdemo01.system.dao.XzqhDao;
import com.sama.springbootdemo01.system.model.User;
import com.sama.springbootdemo01.system.model.Xzqh;
import com.sama.springbootdemo01.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政区划Service
 * @author fjk
 * @date 2019-05-17
 * @since jdk 1.8
 */
@Service
public class XzqhService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private XzqhDao xzqhDao;

    @Autowired
    private UserService userService;

    /**
     * 获取行政区划树
     * @return
     */
    public List getXzqhTree(){


        User user = userService.loginUserInfo();
        Xzqh xzqh = xzqhDao.findXzqhByCode(user.getOrgcode());
        Tree tree = new Tree();
        if(xzqh.getId() !=null && !xzqh.getId().equals("")) {
            tree.setId(xzqh.getId());
            tree.setText(xzqh.getName());
            tree.setChecked(false);
            tree.setAttributes(xzqh.getOrgcode());
            tree.setOrder(xzqh.getId());
            tree.setChildren(getXzqhSon(xzqh.getOrgcode()));
            tree.setIconCls("fa-gears");
            tree.setPid(xzqh.getOrgcode());
            tree.setPname(xzqh.getRemark());
            tree.setSpread(true);//展开tree
        }

        List<Tree> treeList = new ArrayList();
        treeList.add(tree);

        return treeList;
    }

    /**
     * 子级菜单树
     * @param upcode 上级行政区划代码
     * @return
     */
    public List<Tree> getXzqhSon(String upcode){
        List<Xzqh> xzqhList = xzqhDao.getXzqhByUpcode(upcode);
        List<Tree> treeList = new ArrayList();
        for (Xzqh xzqh : xzqhList){
            Tree tree = new Tree();
            tree.setId(xzqh.getId());
            tree.setText(xzqh.getName());
            tree.setChecked(false);
            tree.setAttributes(xzqh.getOrgcode());
            tree.setOrder(xzqh.getId());
            tree.setChildren(getXzqhSon(xzqh.getOrgcode()));
            tree.setIconCls("fa-gears");
            tree.setPid(xzqh.getOrgcode());
            tree.setPname(xzqh.getRemark());
            treeList.add(tree);
            tree.setSpread(true);//展开tree
        }

        return treeList;

    }

    /**
     * 添加行政区划
     * @param xzqh
     */
    public void xzqhAdd(Xzqh xzqh){
        try{
            xzqhDao.xzqhAdd(xzqh);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改行政区划
     * @param xzqh
     */
    public void xzqhEdit(Xzqh xzqh){
        try{
            xzqhDao.xzqhEdit(xzqh);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除行政区划
     * @param orgcode
     */
    public void xzqhDel(String orgcode){
        try{
            xzqhDao.xzqhDel(orgcode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据ID查询行政区划
     * @param id
     * @return
     */
    public Xzqh findXzqhById(Long id){
        return xzqhDao.findXzqhById(id);
    }
}
