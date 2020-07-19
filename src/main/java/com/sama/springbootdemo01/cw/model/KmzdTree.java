package com.sama.springbootdemo01.cw.model;

import com.sama.springbootdemo01.utils.Tree;

import java.util.List;

/**
 * 科目字典Tree
 * @author fjk
 * @since jdk1.8
 * @date 2019-10-29
 */
public class KmzdTree {

    private Long id;
    private String kmmc; //名称
    private boolean checked; //是否选中
    private String attributes;
    private List<KmzdTree> children; //子菜单
    private String kmbh; //编码
    private boolean spread;//是否打开节点
    private String kmxz; //科目性质
    private int mxkm; //是否明细科目：1是 2否


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKmmc() {
        return kmmc;
    }

    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public List<KmzdTree> getChildren() {
        return children;
    }

    public void setChildren(List<KmzdTree> children) {
        this.children = children;
    }

    public String getKmbh() {
        return kmbh;
    }

    public void setKmbh(String kmbh) {
        this.kmbh = kmbh;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getKmxz() {
        return kmxz;
    }

    public void setKmxz(String kmxz) {
        this.kmxz = kmxz;
    }

    public int getMxkm() {
        return mxkm;
    }

    public void setMxkm(int mxkm) {
        this.mxkm = mxkm;
    }
}
