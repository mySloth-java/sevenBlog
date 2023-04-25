package com.cg.service.impl;

import com.cg.entity.Article;
import com.cg.entity.Category;
import com.cg.mapper.ArticleMapper;
import com.cg.mapper.CategoryMapper;
import com.cg.service.CategoryService;
import com.cg.util.BeanCopyUtils;
import com.cg.util.ResponseResult;
import com.cg.vo.ClassifyCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author cgJavaAfter
 * @date 2023-04-25 08:24
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleMapper articleMapper;

    //TODO 添加pid完善子列表
    @Override
    public ResponseResult GetCategory() {
        //查询文章表，通过发布过的文章表统计出分类id并且去重
        List<Article> articles = articleMapper.GetHotArticle();
        Set<Long> categoryIds = articles.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());//利用Set特性过滤

        //根据得到的categoryId查询分类表
        List<Category> category = categoryMapper.GetCategoryByIds(categoryIds);
        //可以考虑过滤
        List<Category> collect = category.stream().collect(Collectors.toList());
        //封装为vo对象返回
        List<ClassifyCategory> beanList = BeanCopyUtils.copyBeanList(collect, ClassifyCategory.class);
        return ResponseResult.okResult(beanList);
    }
}
