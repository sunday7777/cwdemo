package com.sama.springbootdemo01.cw.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Set;

/**
 * 凭证号
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-04
 */
public class Pzh {

    private Long id;

    /** 年月 */
    private String ny;

    /** 凭证号 */
    private int pzh;

    /** 操作员 */
    private String czy;

    /** 审核员 */
    private String shy;

    /** 记账员 */
    private String jzy;

    /** 附单张数 */
    private int fdzs;

    /** 凭证状态（0 未审核 1已审核） */
    private int status;

    /** 录入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date logintime;

    /** 行政区划代码 */
    private String orgcode;

    /** 凭证库 */
    private Set<Pzk> pzks;

    /** 制单日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date makedate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNy() {
        return ny;
    }

    public void setNy(String ny) {
        this.ny = ny;
    }

    public int getPzh() {
        return pzh;
    }

    public void setPzh(int pzh) {
        this.pzh = pzh;
    }

    public String getCzy() {
        return czy;
    }

    public void setCzy(String czy) {
        this.czy = czy;
    }

    public String getShy() {
        return shy;
    }

    public void setShy(String shy) {
        this.shy = shy;
    }

    public String getJzy() {
        return jzy;
    }

    public void setJzy(String jzy) {
        this.jzy = jzy;
    }

    public int getFdzs() {
        return fdzs;
    }

    public void setFdzs(int fdzs) {
        this.fdzs = fdzs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public Set<Pzk> getPzks() {
        return pzks;
    }

    public void setPzks(Set<Pzk> pzks) {
        this.pzks = pzks;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }
}
