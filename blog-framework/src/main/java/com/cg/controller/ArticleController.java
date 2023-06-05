package com.cg.controller;

import com.cg.service.ArticleService;
import com.cg.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:24
 */
@RestController
@RequestMapping("/article")
@Api(tags = "文章接口",description = "文章相关的接口")
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
    @ApiOperation(value = "文章详情",notes = "通过前端传入id查询文章详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id")
    })
    public ResponseResult GetArticle(@PathVariable("id")Long id){
        return articleService.GetArticle(id);
    }

    //更新文章浏览量
    @PutMapping("/viewCount/{id}")
    public ResponseResult UpdateViewCount(@PathVariable("id")Long id){
        return articleService.UpdateViewCount(id);
    }

    //查询文章浏览量
    @GetMapping("/viewCount")
    public ResponseResult GetViewCount(Long id){
        return articleService.GetViewCount(id);
    }


}
