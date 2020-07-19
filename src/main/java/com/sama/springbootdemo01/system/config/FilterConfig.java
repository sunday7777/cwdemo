package com.sama.springbootdemo01.system.config;

import com.sama.springbootdemo01.system.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FilterConfig
 * @author fjk
 * @date 2019-07-09
 * @since jdk1.8
 */
@Configuration
public class FilterConfig {

    /**
     * 配置用户登陆验证过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registFilter() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("LoginFilter");
        registration.setOrder(1);
        return registration;
    }
}
