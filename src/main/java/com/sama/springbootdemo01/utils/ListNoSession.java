package com.sama.springbootdemo01.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 无需进行session验证的url
 * @author fjk
 * @date 2019-07-09
 * @since jdk 1.8
 */
public class ListNoSession {
   public static List<String> nosessionurl = new ArrayList<String>();

   static {
       nosessionurl.add("/system/user/loginPage");
       nosessionurl.add("/system/user/userLogin");
       nosessionurl.add("/login/captcha.jpg");

       //测试接口，暂不用验证码
       nosessionurl.add("/cw/kmzd/saveKmzd");
       nosessionurl.add("/cw/pzh/getLastPzhByNy");
       nosessionurl.add("/cw/pzh/savePz");
       nosessionurl.add("/cw/pzh/getPzById");
       nosessionurl.add("/cw/pzh/updatePz");
   }
}
