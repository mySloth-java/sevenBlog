package com.cg.controller;

import com.cg.service.CategoryService;
import com.cg.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cgJavaAfter
 * @date 2023-04-25 08:26
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //分类表
    @GetMapping("/getCategory")
    public ResponseResult GetClassifyCategory(){
        return categoryService.GetCategory();
    }
}
