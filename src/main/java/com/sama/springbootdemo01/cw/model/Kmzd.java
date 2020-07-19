package com.sama.springbootdemo01.cw.model;

import java.math.BigDecimal;

/**
 * 科目字段
 * @author fjk
 * @since jdk 1.8
 * @date 2019-10-28
 */
public class Kmzd {

    private Long id;

    private String kmbh; //科目编号

    private String kmmc; //科目名称

    private int kmxz; //科目性质：1借 2贷

    private int status; //状态：0 启用 1禁用

    private int kmjs; //科目级数

    private String pbh; //父级编号

    private String orgcode; //行政区划代码

    private int mxkm; //是否明细科目：1是 2否

    private BigDecimal qcye_jie; //借方期初余额

    private BigDecimal qcye_dai; //贷方期初余额

    private int yefx; //余额方向 1借，2贷

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKmbh() {
        return kmbh;
    }

    public void setKmbh(String kmbh) {
        this.kmbh = kmbh;
    }

    public String getKmmc() {
        return kmmc;
    }

    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }

    public int getKmxz() {
        return kmxz;
    }

    public void setKmxz(int kmxz) {
        this.kmxz = kmxz;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getKmjs() {
        return kmjs;
    }

    public void setKmjs(int kmjs) {
        this.kmjs = kmjs;
    }

    public String getPbh() {
        return pbh;
    }

    public void setPbh(String pbh) {
        this.pbh = pbh;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public int getMxkm() {
        return mxkm;
    }

    public void setMxkm(int mxkm) {
        this.mxkm = mxkm;
    }

    public BigDecimal getQcye_jie() {
        return qcye_jie;
    }

    public void setQcye_jie(BigDecimal qcye_jie) {
        this.qcye_jie = qcye_jie;
    }

    public BigDecimal getQcye_dai() {
        return qcye_dai;
    }

    public void setQcye_dai(BigDecimal qcye_dai) {
        this.qcye_dai = qcye_dai;
    }

    public int getYefx() {
        return yefx;
    }

    public void setYefx(int yefx) {
        this.yefx = yefx;
    }
}
