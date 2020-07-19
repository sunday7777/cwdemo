package com.sama.springbootdemo01.cw.model;

import java.math.BigDecimal;

/**
 * 凭证库
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
public class Pzk {

    private Long id;

    private String zy; //摘要

    private String kmmc; //科目名称

    private String kmdm; //科目代码

    private BigDecimal jfje; //借方金额

    private BigDecimal dfje; //贷方金额

    private Long pzhId; //关联凭证号id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getKmmc() {
        return kmmc;
    }

    public void setKmmc(String kmmc) {
        this.kmmc = kmmc;
    }

    public String getKmdm() {
        return kmdm;
    }

    public void setKmdm(String kmdm) {
        this.kmdm = kmdm;
    }

    public BigDecimal getJfje() {
        return jfje;
    }

    public void setJfje(BigDecimal jfje) {
        this.jfje = jfje;
    }

    public BigDecimal getDfje() {
        return dfje;
    }

    public void setDfje(BigDecimal dfje) {
        this.dfje = dfje;
    }

    public Long getPzhId() {
        return pzhId;
    }

    public void setPzhId(Long pzhId) {
        this.pzhId = pzhId;
    }
}
