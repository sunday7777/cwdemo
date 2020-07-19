package com.sama.springbootdemo01.system.model;

/**
 * 系统日志
 * @author fjk
 * @since jdk 1.8
 * @date 2019-06-03
 */
public class Log {

    private Long id;          //主键id
    private String content;     //操作内容
    private String cztime;      //操作时间
    private Long czrid;      //操作人id
    private String czzh;        //操作账号
    private String ip;          //操作人ip
    private String cztype;     //操作类型
    private String orgcode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCztime() {
        return cztime;
    }

    public void setCztime(String cztime) {
        this.cztime = cztime;
    }

    public Long getCzrid() {
        return czrid;
    }

    public void setCzrid(Long czrid) {
        this.czrid = czrid;
    }

    public String getCzzh() {
        return czzh;
    }

    public void setCzzh(String czzh) {
        this.czzh = czzh;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCztype() {
        return cztype;
    }

    public void setCztype(String cztype) {
        this.cztype = cztype;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }
}
