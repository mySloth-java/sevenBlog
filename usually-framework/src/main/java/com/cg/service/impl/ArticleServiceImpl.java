package com.cg.service.impl;

import com.cg.entity.Article;
import com.cg.mapper.ArticleMapper;
import com.cg.service.ArticleService;
import com.cg.util.BeanCopyUtils;
import com.cg.util.ResponseResult;
import com.cg.vo.HotArticle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:14
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ResponseResult GetHotArticle() {
        //分页查询热门文章并按照热度排序
        List<Article> articles = articleMapper.GetHotArticle();

        //bean的拷贝，封装成vo对象
        //1、工具类实现
        List<HotArticle> hotArticles = BeanCopyUtils.copyBeanList(articles, HotArticle.class);

        //2、调用工具包实现
//        List<HotArticle> hotArticles = new ArrayList<>(articles.size());//直接指定长度，不再需要扩容浪费时间
//        for(Article article : articles){
//            HotArticle hotArticle = new HotArticle();
//            //拷贝对象
//            BeanUtils.copyProperties(article,hotArticle);
//            //添加到vo集合中，并返回新的对象
//            hotArticles.add(hotArticle);
//        }
        return ResponseResult.okResult(hotArticles);
    }
}
