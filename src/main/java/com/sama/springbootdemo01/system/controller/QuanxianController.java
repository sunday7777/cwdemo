package com.sama.springbootdemo01.system.controller;

import com.sama.springbootdemo01.system.service.QuanxianService;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限Controller
 * @author fjk
 * @date 2019-07-01
 * @since jdk1.8
 */
@RestController
@RequestMapping(value = "/system/quanxian")
public class QuanxianController {

    @Autowired
    private QuanxianService quanxianService;

    /**
     * 保存用户组权限
     * @param gid
     * @param menuIds
     * @return
     */
    @RequestMapping(value = "saveQuanxian",method = RequestMethod.GET)
    public ResultMsg saveQuanxian(@RequestParam("id") Long gid, @RequestParam("menuIds") String menuIds){
        try {
            quanxianService.saveQuanxian(gid,menuIds);
            return new ResultMsg().success();
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg().error();
        }
    }
}
