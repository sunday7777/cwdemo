package com.sama.springbootdemo01.system.controller;

import com.sama.springbootdemo01.system.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * LayoutController
 * @author fjk
 * @since 2019-03-17
 */
@Controller
public class LayoutController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("SAMAUSER_USER");

        model.addAttribute("id",user.getId());
        model.addAttribute("realname",user.getRealname());
        return "/main";
    }


}
