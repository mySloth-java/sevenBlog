package com.cg.service.impl;

import com.cg.entity.Article;
import com.cg.mapper.ArticleMapper;
import com.cg.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Article> GetArticle() {
        List<Article> articles = articleMapper.GetArticle();
        return articles;
    }
}
