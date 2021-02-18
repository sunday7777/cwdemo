package com.sama.springbootdemo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig接口测试工具
 * 项目启动后，可以访问localhost:port/swagger-ui.html即可看到页面
 * @author fjk
 * @since jdk1.8
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //指定生成接口文档的包
                .apis(RequestHandlerSelectors.basePackage("com.sama.springbootdemo01.cw"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //大标题
                .title("cwdemo 接口文档")
                //描述
                .description("cwdemo 接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
