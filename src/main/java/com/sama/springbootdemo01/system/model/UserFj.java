package com.sama.springbootdemo01.system.model;

/**
 * 用户附件
 * @author fjk
 * @date 2019-07-20
 * @since jdk 1.8
 */
public class UserFj {

    private Long id;

    private Long glid;

    private String oname;

    private String name;

    private String addtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Long getGlid() {
        return glid;
    }

    public void setGlid(Long glid) {
        this.glid = glid;
    }
}
