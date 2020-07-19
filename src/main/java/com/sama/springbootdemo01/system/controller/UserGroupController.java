package com.sama.springbootdemo01.system.controller;

import com.github.pagehelper.Page;
import com.sama.springbootdemo01.system.model.UserGroup;
import com.sama.springbootdemo01.system.service.LogService;
import com.sama.springbootdemo01.system.service.QuanxianService;
import com.sama.springbootdemo01.system.service.UserGroupService;
import com.sama.springbootdemo01.system.service.UserService;
import com.sama.springbootdemo01.utils.PageInfo;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户组Controller
 * @author fjk
 * @date 2019-06-13
 * @since jdk 1.8
 */
@Controller
@RequestMapping(value = "/system/usergroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    /**
     * 用户组首页
     * @return
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "/usergroup/index";
    }

    /**
     * 分页查询用户组列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "listUserGroupByPage", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg listUserGroupByPage(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize){
        try{
            Page<UserGroup> list = userGroupService.listUserGroupByPage(pageNo,pageSize);
            PageInfo<UserGroup> pageInfo = new PageInfo<>(list); //获取分页信息
            return new ResultMsg(200,"操作成功",list,pageInfo.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg().error();
        }
    }

    /**
     * 添加页面
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(){
        return "/usergroup/add";
    }

    /**
     * 修改页面
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model,@RequestParam("id") long id){
        UserGroup up = userGroupService.getUserGroupById(id);
        model.addAttribute("up",up);
        return "/usergroup/edit";
    }


    /**
     * 添加角色
     * @param request
     * @param up
     * @return
     */
    @RequestMapping(value = "saveUserGroup",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg saveUserGroup(HttpServletRequest request,@RequestBody UserGroup up){
        if(userGroupService.saveUserGroup(up)){
            logService.logAdd(request,"添加角色，名称："+up.getName(), "添加");
            return new ResultMsg().success();
        }else{
            return new ResultMsg().error();
        }
    }

    /**
     * 修改角色
     * @param request
     * @param up
     * @return
     */
    @RequestMapping(value = "updateUserGroup",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg updateUserGroup(HttpServletRequest request,@RequestBody UserGroup up){
        if(userGroupService.updateUserGroup(up)){
            logService.logAdd(request,"修改角色，名称："+up.getName(), "修改");
            return new ResultMsg().success();
        }else{
            return new ResultMsg().error();
        }
    }

    /**
     * 删除角色
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "countUserByGroupid", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg countUserByGroupid(HttpServletRequest request,@RequestParam Long id){
        int nums = userService.countUserByGroupid(id);
        if(nums>0){
            return new ResultMsg().error("该用户组已被使用，请先删除相关用户！");
        }

        UserGroup up =userGroupService.getUserGroupById(id);

        if(userGroupService.removeUserGroup(id)){
            logService.logAdd(request,"删除角色，名称："+up.getName()+"，ID："+id,"删除");
            return new ResultMsg().success();
        }else{
            return new ResultMsg().error();
        }
    }
}
