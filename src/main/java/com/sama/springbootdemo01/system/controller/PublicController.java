package com.sama.springbootdemo01.system.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 公共方法Controller
 * @author fjk
 * @date 2019-06-15
 * @since jdk 1.8
 */
@Controller
public class PublicController {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    /**
     * 登陆验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/login/captcha.jpg" ,method= RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //保存到session
        request.getSession().setAttribute("VERIFICATION_CODE_SESSION", text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

    }

}
