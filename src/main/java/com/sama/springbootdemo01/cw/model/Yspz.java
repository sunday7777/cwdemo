package com.sama.springbootdemo01.cw.model;

/**
 * 原始凭证
 * @author fjk
 * @since jdk1.8
 * @date 2019-11-04
 */
public class Yspz {

    private Long id;

    private Long pzh_id; //关联凭证id

    private String name; //附件名称

    private String yname; //附件原名

    private String addtime; //上传时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPzh_id() {
        return pzh_id;
    }

    public void setPzh_id(Long pzh_id) {
        this.pzh_id = pzh_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYname() {
        return yname;
    }

    public void setYname(String yname) {
        this.yname = yname;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
