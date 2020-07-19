package com.sama.springbootdemo01.system.model;

/**
 * 行政区划
 * @author fjk
 * @date 2019-05-09
 */
public class Xzqh {
    private Long id;     //主键id
    private String name;    //行政区划名称
    private String orgcode; // 行政区划代码
    private int grade;      //行政区划级别
    private String incode;  //本级行政区划代码
    private String upcode;  //上级行政区划代码
    private String remark;  //备注

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getIncode() {
        return incode;
    }

    public void setIncode(String incode) {
        this.incode = incode;
    }

    public String getUpcode() {
        return upcode;
    }

    public void setUpcode(String upcode) {
        this.upcode = upcode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
