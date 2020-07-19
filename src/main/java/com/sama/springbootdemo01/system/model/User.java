package com.sama.springbootdemo01.system.model;

import java.io.Serializable;

/**
 * 用户表
 * @author fjk
 * @since 2019-02-24
 */
public class User implements Serializable {
    private Long id;              //主键
    private String realname;        //姓名
    private String username;        //用户名
    private String password;        //密码
    private Integer groupid;        //用户组ID
    private String phone;           //电话
    private String email;           //电子邮箱
    private String orgcode;         //行政区划ID
    private String lastlogintime;  //上次登陆时间
    private Integer nums;           //登陆次数

    private Long imgId; //上传头像id


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(String lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getImgId() {
        return imgId;
    }
}
