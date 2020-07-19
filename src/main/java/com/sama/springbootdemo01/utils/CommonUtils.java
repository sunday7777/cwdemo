package com.sama.springbootdemo01.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 常用小工具
 * @author fjk
 * @date 2019-07-28
 * @since jdk1.8
 */
public class CommonUtils {

    /**
     * 获取当前时间字符串
     * @return
     */
    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sdf.format(new Date());
    }
}
