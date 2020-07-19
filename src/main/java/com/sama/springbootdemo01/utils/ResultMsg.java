package com.sama.springbootdemo01.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回结果集
 * @author fjk
 * @since 2019-03-24
 */
public class ResultMsg {
    private Integer code; //状态码
    private String msg;  //状态码说明
    private Object data;   //结果集
    private Long count; //数据总数

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public ResultMsg(Integer code,String msg,List data,Long count){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;

    }

    public ResultMsg(){

    }

    public ResultMsg error(){
        this.code = 500;
        this.msg = "操作失败";
        return this;
    }

    public ResultMsg error(String info){
        this.code = 500;
        this.msg = info;
        return this;
    }


    public ResultMsg success(){
        this.code = 200;
        this.msg = "操作成功";
        return this;
    }

    public ResultMsg success(Object data){
        this.code = 200;
        this.msg = "操作成功";
        this.data = data;
        return this;
    }
}
