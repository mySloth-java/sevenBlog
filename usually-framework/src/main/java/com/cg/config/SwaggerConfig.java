package com.cg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author cgJavaAfter
 * @date 2023-06-05 14:47
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        return new Docket(DocumentationType.SWAGGER_2)//TODO 不懂，怎么找到另一个包的controller的
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cg.controller"))//包扫描路径
                .build();
    }

    private ApiInfo apiInfo(){
        Contact se7en = new Contact("se7en", "www.baidu.com", "cg@163.com");
        return new ApiInfoBuilder()
                .title("文档标题")
                .description("描述")
                .contact(se7en) //联系
                .version("版本信息")
                .build();
    }

}
