package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 19:13
 */
@SpringBootApplication
@EnableScheduling
public class blogApplication {
    public static void main(String[] args) {
        SpringApplication.run(blogApplication.class,args);
    }
}
