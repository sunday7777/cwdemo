package com.sama.springbootdemo01.system.controller;

import com.github.pagehelper.Page;
import com.sama.springbootdemo01.system.model.UserFj;
import com.sama.springbootdemo01.system.model.UserGroup;
import com.sama.springbootdemo01.system.service.LogService;
import com.sama.springbootdemo01.system.service.UserFjService;
import com.sama.springbootdemo01.system.service.UserGroupService;
import com.sama.springbootdemo01.utils.FileUploadUtils;
import com.sama.springbootdemo01.utils.PageInfo;
import com.sama.springbootdemo01.utils.ReadImages;
import com.sama.springbootdemo01.utils.ResultMsg;
import com.sama.springbootdemo01.system.model.User;
import com.sama.springbootdemo01.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller
 * @author fjk
 * @since 2019-02-24
 */
@Controller
@RequestMapping(value = "/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    @Autowired
    private LogService logService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserFjService userFjService;

    /**
     * 登陆页
     * @param map
     * @return
     */
    @RequestMapping("/loginPage")
    public String login(Map<String,Object> map) {
        map.put("webname",environment.getProperty("website.name"));
        return "/login/index";
    }

    /**
     * user列表页
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "/user/index";
    }

    /**
     * user添加页面
     */
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(Model model,@RequestParam("orgCode") String orgCode){
        List<UserGroup> ugList = userGroupService.listUserGroupByOrgcode();
        model.addAttribute("ugList",ugList);
        model.addAttribute("orgCode",orgCode);
        return "/user/add";
    }

    /**
     * user修改页面
     * @param id
     * @return
     */
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(Model model, @RequestParam("id") Long id){
        List<UserGroup> ugList = userGroupService.listUserGroupByOrgcode();
        User user = userService.getUserById(id);
        model.addAttribute("ugList",ugList);
        model.addAttribute("user",user);
        return "/user/edit";
    }


    /**
     * user列表分页接口
     */
    @RequestMapping(value = "findUserByPage",method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg findUserByPage(@RequestParam("pageNo") int pageNo,@RequestParam("pageSize") int pageSize, @RequestParam(value = "orgCode", required = false) String orgCode){
        Page<User> page = userService.findUserByPage(pageNo,pageSize,orgCode);
        PageInfo<User> pageInfo = new PageInfo<>(page); //获取分页信息
        return new ResultMsg(200,"操作成功",page,pageInfo.getTotal());
    }

    /**
     * 添加用户
     * @return
     */
    @RequestMapping(value = "userAdd",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg userAdd(HttpServletRequest request, @RequestBody User user){
        try{
            //添加用户
            if(userService.userAdd(user) && user.getImgId()!=null){
                //保存头像
                userFjService.setGlid(user.getImgId(),user.getId());
            }

            //系统日志
            logService.logAdd(request,"添加用户，姓名："+user.getRealname()+"，用户名："+user.getUsername()+"。","添加");
            return new ResultMsg().success();
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg().error();

        }
    }

    /**
     * 修改用户
     * @return
     */
    @RequestMapping(value = "userEdit",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg userEdit(HttpServletRequest request, @RequestBody User user){
        try{
            userService.userEdit(user);
            //保存头像
            userFjService.setGlid(user.getImgId(),user.getId());
            //系统日志
            logService.logAdd(request,"修改用户，姓名："+user.getRealname()+"，用户名："+user.getUsername()+"。","修改");
            return new ResultMsg().success();
        }catch(Exception e){
            e.printStackTrace();
            return new ResultMsg().error();

        }
    }

    @RequestMapping(value = "userDel",method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg userDel(HttpServletRequest request, @RequestParam("id") Long id){
        try {
            User user = userService.getUserById(id);
            userService.userDel(id);
            
            //系统日志
            logService.logAdd(request,"删除用户，姓名："+user.getRealname()+"，用户名："+user.getUsername()+"。","删除");
            return new ResultMsg().success();
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg().error();
        }
    }

    /**
     * 用户登陆
     * @param request
     * @param username
     * @param password
     * @param validatecode
     * @return
     */
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg userLogin(HttpServletRequest request,@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("validatecode") String validatecode){

        HttpSession session = request.getSession();
        if(!session.getAttribute("VERIFICATION_CODE_SESSION").equals(validatecode)){
            return new ResultMsg().error("验证码错误！");
        }

        User user = userService.userLogin(username,password);

        if (user != null){
            //保存用户session
            session.setAttribute("SAMAUSER_USER",user);
            //保存日志
            logService.logAdd(request,"用户登陆，用户名："+user.getUsername(),"登陆成功");
            return new ResultMsg().success();
        }else{
            return new ResultMsg().error("用户名或密码错误！");
        }


    }

    /**
     * 注销登陆
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("SAMAUSER_USER");
            session.removeAttribute("SAMAUSER_USER");

            request.getRequestDispatcher("/system/user/loginPage").forward(request,response);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 上传头像
     * @param request
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg upload(HttpServletRequest request){
        FileUploadUtils fileUploadUtils = new FileUploadUtils();
        String path = "G:/upload/user/image";  //图片上传路径
        List<Map<String,String>> nameList = fileUploadUtils.fileUpload(request,path); //上传图片，返回图片名称
        List<String> idList = new ArrayList<>();
        for(Map<String,String> names : nameList){
            UserFj uf = new UserFj();
            uf.setName(names.get("name"));
            uf.setOname(names.get("oName"));
            userFjService.userFjAdd(uf);
            idList.add(String.valueOf(uf.getId()));
        }
        String fjIds = String.join(",", idList);  //返回上传附件id以逗号分隔
        return new ResultMsg().success(fjIds);
    }

    /**
     * 根据附件id获取图片
     * @param response
     * @param id
     */
    @RequestMapping( value = "imageShow",method = RequestMethod.GET)
    @ResponseBody
    public void imageShow(HttpServletResponse response,@RequestParam Long id){

        UserFj userFj = userFjService.getUserFjById(id);

        String path = "G:/upload/user/image/"+userFj.getName();

        ReadImages.show(path,response);

    }

    /**
     * 根据用户id获取头像
     * @param response
     * @param userid
     */
    @RequestMapping(value = "getUserImg",method = RequestMethod.GET)
    @ResponseBody
    public void getUserImg(HttpServletResponse response,@RequestParam("userid") Long userid){

        List<UserFj> lu = userFjService.listUserFjByUserId(userid);

        if(lu.size()>0){
            UserFj uf = lu.get(0);

            String path = "G:/upload/user/image/"+uf.getName();

            ReadImages.show(path,response);
        }else{
            String path = System.getProperty("user.dir")+"/src/main/resources/static/public/images/public/zwtp.jpg";

            ReadImages.show(path,response);
        }


    }



}
