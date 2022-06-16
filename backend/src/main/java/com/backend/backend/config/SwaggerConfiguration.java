package com.backend.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket docket(){
        Docket docket=new Docket(DocumentationType.SWAGGER_2);
        //api帮助文档描述信息
        ApiInfo apiInfo=new ApiInfoBuilder()
                .contact(
                        //配置文档主题内容
                        new Contact(
                                "货运app文档",
                                "http://localhost",
                                "952490637@qq.com"
                        )
                )
                .title("货运app文档")
                .description("货运app接口文档")
                .version("1.0")
                .build();
        docket.select().apis(RequestHandlerSelectors.basePackage("com.backend.backend.controller"));
        docket.apiInfo(apiInfo);
        return docket;
    }
}
