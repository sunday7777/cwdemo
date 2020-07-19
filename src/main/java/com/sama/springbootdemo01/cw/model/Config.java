package com.sama.springbootdemo01.cw.model;

/**
 * 系统参数
 * @author fjk
 * @since jdk 1.8
 * @date 2019-09-03
 */
public class Config {

    private Long id; //主键id

    private String ztmc; //账套名称

    private String qyrq; //启用日期

    private String dqny; //当前年月

    private int kmcd; //科目代码长度

    private String kmjc; //科目级次

    private String orgcode;  //行政区划代码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZtmc() {
        return ztmc;
    }

    public void setZtmc(String ztmc) {
        this.ztmc = ztmc;
    }

    public String getQyrq() {
        return qyrq;
    }

    public void setQyrq(String qyrq) {
        this.qyrq = qyrq;
    }

    public String getDqny() {
        return dqny;
    }

    public void setDqny(String dqny) {
        this.dqny = dqny;
    }

    public int getKmcd() {
        return kmcd;
    }

    public void setKmcd(int kmcd) {
        this.kmcd = kmcd;
    }

    public String getKmjc() {
        return kmjc;
    }

    public void setKmjc(String kmjc) {
        this.kmjc = kmjc;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
}
