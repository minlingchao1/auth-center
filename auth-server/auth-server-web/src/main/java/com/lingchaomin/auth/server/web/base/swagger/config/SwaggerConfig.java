package com.lingchaomin.auth.server.web.base.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Author:minlingchao
 * Date: 2016/10/20 14:21
 * Description:
 */
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(new ApiInfo(
                        "用户权限管理api接口",
                        "用户权限管理相关API",
                        "1.0",
                        "",
                        "",
                        "",
                        ""
                ));
    }
}
