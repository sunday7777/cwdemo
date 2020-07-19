package com.sama.springbootdemo01.system.controller;

import com.sama.springbootdemo01.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "menu",method = RequestMethod.GET)
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "getMenuTreeByUser")
    public List getMenuTreeByUser(@RequestParam(name = "id",required = false) Long id){
        if(id != null && !"".equals(id)){
            return  menuService.getMenuTreeByUser(id);
        }else{
            return  menuService.getMenuTreeByUser();
        }

    }
}
