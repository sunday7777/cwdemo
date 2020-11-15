package com.sama.springbootdemo01.practice.annotation;

/**
 * 用户实体类，用于测试字段校验注解
 * @author fjk
 * @date 2020年10月14日
 * @since jdk1.8
 */
public class User {

    /**
     * 用户名
     */
    String username;

    /**
     * 密码
     */
    @ConstraintAnnotation(comment = "密码", nullable = false, length = {6,20})
    String password;

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
}
