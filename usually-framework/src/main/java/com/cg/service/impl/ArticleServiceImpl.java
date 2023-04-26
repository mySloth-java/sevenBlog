package com.cg.service.impl;

import com.cg.entity.Article;
import com.cg.entity.Category;
import com.cg.mapper.ArticleMapper;
import com.cg.mapper.CategoryMapper;
import com.cg.service.ArticleService;
import com.cg.util.BeanCopyUtils;
import com.cg.util.ResponseResult;
import com.cg.vo.ClassifyArticle;
import com.cg.vo.HotArticle;
import com.cg.vo.PageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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

    @Autowired
    private CategoryMapper categoryMapper;

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

    @Override
    public ResponseResult ClassifyArticle(Long categoryId, Integer pageNum, Integer pageSize) {
        //对文章进行分类并且分页查询，根据置顶排序
        List<Article> articles = articleMapper.ClassifyCategory(categoryId, pageNum, pageSize);
        //查询热门文章分类后的总数，设置到page中返回
        Integer integer = articleMapper.GetArticleByCIdCount(categoryId);

        //封装vo前，根据categoryId查询到name，设置到vo返回实体中
//        for(int i = 0;i < articles.size();i++){
//            Long categoryId1 = articles.get(i).getCategoryId();
//
//        };

        //封装vo返回结果，并设置到pageVO中
        List<ClassifyArticle> classifyArticles = BeanCopyUtils.copyBeanList(articles, ClassifyArticle.class);

        //封装vo后，根据categoryId查询到name，设置到vo返回实体中
        //TODO 查询数据库频率高
        for(int i = 0;i < articles.size();i++){
            Long id = articles.get(i).getCategoryId();
            Category category = categoryMapper.GetCategoryById(id);
            classifyArticles.get(i).setCategoryName(category.getName());
        }
        PageVo pageVo = new PageVo(classifyArticles, integer);
        return ResponseResult.okResult(pageVo);
    }
}
