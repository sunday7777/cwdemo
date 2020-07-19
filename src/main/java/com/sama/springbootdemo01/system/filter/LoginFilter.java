package com.sama.springbootdemo01.system.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.sama.springbootdemo01.utils.ListNoSession.nosessionurl;

/**
 * 用户登陆验证过滤器
 * @author fjk
 * @date 2019-07-09
 * @since jdk1.8
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //获取当前请求url
        HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        String url = httpRequest.getRequestURI();
        //获取session对象
        HttpSession session = httpRequest.getSession();
        System.out.println(url);

        //判断是否需要session验证
        if(checkUrl(url)){ //需要验证

            if(session.getAttribute("SAMAUSER_USER") == null){//验证失败，跳转到登陆页面
                httpRequest.getRequestDispatcher("/system/user/loginPage").forward(httpRequest,httpResponse);
            }else{//验证通过，继续之前的URl
                filterChain.doFilter(servletRequest,servletResponse);
            }

        }else{//无需验证,继续之前的URL
            filterChain.doFilter(servletRequest,servletResponse);
        }





    }

    @Override
    public void destroy() {

    }

    /**
     * 判断url是否需要session验证
     * @param url
     * @return
     */
    private boolean checkUrl(String url){

        //开发中，暂不进行权限验证。
        return false;

        /*
        //公共资源不进行验证
        if(url.startsWith("/public")){
            return false;
        }

        List<String> list = nosessionurl;
        if(list.contains(url)){
            return false;
        }else{
            return true;
        }

         */
    }
}
