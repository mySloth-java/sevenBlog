package com.cg.controller;

import com.cg.entity.Article;
import com.cg.service.ArticleService;
import com.cg.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    //热门文章查询
    // TODO 分页功能(现在前后端分页都写死了，但好像不用考虑)、热门算法(近期点赞数)
    // TODO 考虑要不要加入缓存，使用AOP不对源代码进行修改
    @GetMapping("/hotArticle")
    public ResponseResult GetHotArticle(){
        return articleService.GetHotArticle();
    }

    //分类文章查询
    @GetMapping("/classifyArticle")//query请求
    public ResponseResult ClassifyArticle(Long categoryId, Integer pageNum, Integer pageSize){
        return articleService.ClassifyArticle(categoryId,pageNum,pageSize);
    }

    //查询文章详情
    @GetMapping("/{id}")
    public ResponseResult GetArticle(@PathVariable("id")Long id){
        return articleService.GetArticle(id);
    }

    //更新文章浏览量
    @PutMapping("/viewCount/{id}")
    public ResponseResult UpdateViewCount(@PathVariable("id")Long id){
        return articleService.UpdateViewCount(id);
    }


}
