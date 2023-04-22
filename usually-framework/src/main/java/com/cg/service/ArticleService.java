package com.cg.service;

import com.cg.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:13
 */
public interface ArticleService {
    List<Article> GetArticle();

}
