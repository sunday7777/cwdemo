package com.sama.springbootdemo01.system.model;

/**
 * 菜单表
 * @since 2019-03-06
 * @author fjk
 */
public class Menu {
    private Long id;
    private String name;
    private String desctibe;
    private String url;
    private String code;
    private String pcode;
    private String icon;
    private String sort;

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

    public String getDesctibe() {
        return desctibe;
    }

    public void setDesctibe(String desctibe) {
        this.desctibe = desctibe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
