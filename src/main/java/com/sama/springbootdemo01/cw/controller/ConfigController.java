package com.sama.springbootdemo01.cw.controller;

import com.sama.springbootdemo01.cw.model.Config;
import com.sama.springbootdemo01.cw.service.ConfigService;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 系统参数
 * @author fjk
 * @since jdk1.8
 * @data 2019-09-03
 */
@Controller
@RequestMapping("/cw/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    /**
     * 设置系统参数页面
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model){
        Config config = configService.getConfigById((long)1);
        model.addAttribute("config",config);
        return "/cwsz/config";
    }

    /**
     * 设置系统参数
     * @param config
     * @return
     */
    @RequestMapping(value = "setConfig", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg setConfig(@RequestBody Config config){
        if(configService.setConfig(config)){
            return new ResultMsg().success();
        }else{
            return new ResultMsg().error();
        }
    }
}
