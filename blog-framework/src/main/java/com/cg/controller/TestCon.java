package com.cg.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cgJavaAfter
 * @date 2023-05-09 21:20
 */
@RequestMapping("/test")
@RestController
public class TestCon {

    @GetMapping("/authority")
    @PreAuthorize("hasAuthority('test2')")
    public String Test(){
        return "my test success";
    }
}
