package com.sama.springbootdemo01.system.model;

import java.io.Serializable;
import java.util.List;

/**
 * Excel实体
 * @author fjk
 * @date 2019-06-12
 * @since jdk1.8
 */
public class ExcelData implements Serializable{


    private List<String> titles; //表头

    private List<List<Object>> rows; //导出数据

    private String name; //页签名称

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
