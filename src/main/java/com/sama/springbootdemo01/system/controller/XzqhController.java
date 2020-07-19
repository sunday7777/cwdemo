package com.sama.springbootdemo01.system.controller;

import com.sama.springbootdemo01.system.model.Xzqh;
import com.sama.springbootdemo01.system.service.LogService;
import com.sama.springbootdemo01.system.service.XzqhService;
import com.sama.springbootdemo01.utils.ResultMsg;
import com.sama.springbootdemo01.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 行政区划
 * @author fjk
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/system/xzqh")
public class XzqhController {

    @Autowired
    private XzqhService xzqhService;

    @Autowired
    private LogService logService;

    /**
     * 行政区划首页
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "/xzqh/index";
    }

    /**
     * 行政区划添加页面
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model,@RequestParam("upid") Integer upid){
        model.addAttribute("upid",upid);
        return "/xzqh/add";
    }

    /**
     * 修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model,@RequestParam("id") Long id){
        Xzqh xzqh = xzqhService.findXzqhById(id);
        model.addAttribute("xzqh",xzqh);
        return "/xzqh/edit";
    }

    /**
     * 获取行政区划树
     * @return
     */
    @RequestMapping(value = "getXzqhTree")
    @ResponseBody
    public List<Tree> getXzqhTree(){
        return xzqhService.getXzqhTree();
    }

    /**
     * 添加下级行政区划
     * @return ResultMsg
     */
    @RequestMapping(value = "xzqhAdd", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg xzqhAdd(HttpServletRequest request, @RequestBody Map map){

        try{
            Long upid =  Long.parseLong(map.get("upid").toString());
            //验证行政区划代码是否正确（未完成）

            //获取上级行政区划代码
            Xzqh upXzqh = xzqhService.findXzqhById(upid);
            //设置相关字段
            Xzqh xzqh = new Xzqh();
            xzqh.setName((String) map.get("name"));
            xzqh.setOrgcode(upXzqh.getOrgcode()+map.get("incode"));
            xzqh.setGrade(upXzqh.getGrade()+1);
            xzqh.setUpcode(upXzqh.getOrgcode());
            xzqh.setIncode((String) map.get("incode"));
            xzqh.setRemark((String) map.get("remark"));
            xzqhService.xzqhAdd(xzqh);

            //系统日志
            logService.logAdd(request, "添加行政区划，名称:"+xzqh.getName()+"，代码："+xzqh.getOrgcode()+"。","添加");

            return new ResultMsg().success();
        }catch(Exception e){
            return new ResultMsg().error();
        }

    }

    /**
     * 修改行政区划
     * @param xzqh
     * @return
     */
    @RequestMapping(value = "xzqhEdit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg xzqhEdit(HttpServletRequest request, @RequestBody Xzqh xzqh){

        try{
            xzqh.setOrgcode(xzqh.getUpcode()+xzqh.getIncode());
            xzqhService.xzqhEdit(xzqh);

            //系统日志
            logService.logAdd(request, "修改行政区划，名称:"+xzqh.getName()+"，代码："+xzqh.getOrgcode()+"。","修改");
            return new ResultMsg().success();
        }catch(Exception e){
            return new ResultMsg().error();
        }

    }

    /**
     * 删除行政区划
     * @param orgcode
     * @return
     */
    @RequestMapping(value = "xzqhDel",method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg xzqhDel(HttpServletRequest request, @RequestParam("orgcode") String orgcode){
        try{
            xzqhService.xzqhDel(orgcode);
            //系统日志
            logService.logAdd(request, "删除行政区划，代码："+orgcode+"。","删除");
            return new ResultMsg().success();
        }catch(Exception e){
            return new ResultMsg().error();
        }
    }

}
