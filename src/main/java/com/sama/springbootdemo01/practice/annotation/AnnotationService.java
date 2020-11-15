package com.sama.springbootdemo01.practice.annotation;

import org.springframework.stereotype.Service;

/**
 * 自定义注解测试service
 * @author fjk
 * @date 2020年10月09日
 * @since jdk 1.8
 */
@Service
public class AnnotationService {

    @AopAnnotation
    public void save(){
        System.out.println("保存数据");
    }

    @AopAnnotation
    public void delete(){
        System.out.println("删除数据");
    }
}
