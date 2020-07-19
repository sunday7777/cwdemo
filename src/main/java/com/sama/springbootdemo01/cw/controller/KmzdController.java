package com.sama.springbootdemo01.cw.controller;

import com.sama.springbootdemo01.cw.model.Kmzd;
import com.sama.springbootdemo01.cw.model.KmzdTree;
import com.sama.springbootdemo01.cw.service.KmzdService;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科目字典
 * @author fjk
 * @since jdk1.8
 * @date 2019-10-24
 */
@Controller
@RequestMapping("/cw/kmzd")
public class KmzdController {

    @Autowired
    private KmzdService kmzdService;

    /**
     * 科目字典首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        return "/kmzd/index";
    }

    /**
     * 获取科目字典树
     * @param type
     * @return
     */
    @RequestMapping(value = "listKmzdTree", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg listKmzdTree(@RequestParam("type") int type){
        try {
            List<KmzdTree> ListKmzdTree = kmzdService.listKmzdTree(type);
            return new ResultMsg().success(ListKmzdTree);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg().error();
        }
    }

    /**
     * 添加科目字典
     * @param kmzd
     * @return
     */
    @RequestMapping(value = "saveKmzd", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg saveKmzd(@RequestBody Kmzd kmzd){
        if(kmzdService.saveKmzd(kmzd)){
            return new ResultMsg().success();
        }else {
            return new ResultMsg().error();
        }
    }
}
