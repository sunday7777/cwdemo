package com.sama.springbootdemo01.practice.aop;

import org.springframework.stereotype.Service;

/**
 * aop切面
 * @author fjk
 * @date 2020年09月12日
 * @since jdk 1.8
 */
@Service
public class UserTestService {


    public void save(){
        System.out.println("保存用户");
    }

    public void delete(){
        System.out.println("删除用户");
    }

}
