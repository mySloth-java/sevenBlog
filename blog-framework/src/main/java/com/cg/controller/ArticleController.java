package com.cg.controller;

import com.cg.entity.Article;
import com.cg.service.ArticleService;
import com.cg.util.ResponseResult;
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

    //热门文章查询 TODO 分页功能(现在前后端分页都写死了，但好像不用考虑)、热门算法(近期点赞数)
    @GetMapping("/hotArticle")
    public ResponseResult GetArticle(){
        return articleService.GetHotArticle();
    }


}
