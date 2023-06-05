package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 19:13
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class blogApplication {
    public static void main(String[] args) {
        SpringApplication.run(blogApplication.class,args);
    }
}
