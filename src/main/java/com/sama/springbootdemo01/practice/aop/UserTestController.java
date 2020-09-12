package com.sama.springbootdemo01.practice.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * aop切面实战(保存用户及删除用户时候使用切面记录日志)
 * @author fjk
 * @date 2020年09月12日
 * @since jdk 1.8
 */
@RestController
@RequestMapping("/aop/User")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    /**
     * 保存用户
     */
    @RequestMapping(value = "save", method = RequestMethod.GET)
    public void save(){
        userTestService.save();
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public void delete(){
        userTestService.delete();
    }
}
