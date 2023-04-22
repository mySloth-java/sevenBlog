package com.cg.controller;

import com.cg.entity.Article;
import com.cg.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:24
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/getall")
    public List<Article> GetArticle(){
        List<Article> articles = articleService.GetArticle();
        return articles;
    }


}
