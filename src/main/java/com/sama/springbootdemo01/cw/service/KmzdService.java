package com.sama.springbootdemo01.cw.service;

import com.sama.springbootdemo01.cw.dao.KmzdDao;
import com.sama.springbootdemo01.cw.model.Kmzd;
import com.sama.springbootdemo01.cw.model.KmzdTree;
import com.sama.springbootdemo01.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 科目字典Service
 * @author fjk
 * @since jdk1.8
 * @date 2019-10-29
 */
@Service
public class KmzdService {

    @Autowired
    private KmzdDao kmzdDao;

    /**
     * 查询科目字典树
     * @param type
     * @return
     */
    public List<KmzdTree> listKmzdTree(int type){

        List<KmzdTree> listKmzdTree = new ArrayList<KmzdTree>();

        try {
            //查询一级科目
            List<Kmzd> listKmzd = kmzdDao.listKmzdOne(type);
            if (listKmzd != null) {
                for (Kmzd kmzd : listKmzd) {
                    KmzdTree tree = new KmzdTree();
                    tree.setId(kmzd.getId());
                    tree.setKmmc(kmzd.getKmmc());
                    tree.setKmbh(kmzd.getKmbh());
                    tree.setMxkm(kmzd.getMxkm());
                    String Kmxz = kmzd.getKmxz() == 1?"借":"贷";
                    tree.setKmxz(Kmxz);
                    tree.setChildren(this.listKmzdSon(kmzd.getKmbh()));
                    listKmzdTree.add(tree);
                }
                return listKmzdTree;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据父级编号查询科目字典树
     * @param pbh
     * @return
     */
    public List<KmzdTree> listKmzdSon(String pbh){

        List<KmzdTree> listKmzdTree = new ArrayList<KmzdTree>();

        try {
            List<Kmzd> listKmzd = kmzdDao.listKmzdByPbh(pbh);
            if (listKmzd !=null){
                for (Kmzd kmzd : listKmzd) {
                    KmzdTree tree = new KmzdTree();
                    tree.setId(kmzd.getId());
                    tree.setKmmc(kmzd.getKmmc());
                    tree.setKmbh(kmzd.getKmbh());
                    tree.setMxkm(kmzd.getMxkm());
                    String Kmxz = kmzd.getKmxz() == 1?"借":"贷";
                    tree.setKmxz(Kmxz);
                    tree.setChildren(this.listKmzdSon(kmzd.getKmbh()));
                    listKmzdTree.add(tree);
                }
                return listKmzdTree;
            }else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加科目字典
     * @param kmzd
     * @return
     */
    public boolean saveKmzd(Kmzd kmzd){
        try {
            kmzdDao.saveKmzd(kmzd);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
