package com.sama.springbootdemo01.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *首页Controller
 * @author fjk
 * @since 2019-03-17
 */
@Controller
@RequestMapping(value = "home",method = RequestMethod.GET)
public class HomeController {
    @RequestMapping(value = "index")
    public String index(){
        return  "/home/index";
    }

}
