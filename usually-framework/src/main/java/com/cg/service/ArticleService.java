package com.cg.service;

import com.cg.entity.Article;
import com.cg.util.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:13
 */
public interface ArticleService {
    ResponseResult GetHotArticle();

    ResponseResult ClassifyArticle(Long categoryId, Integer pageNum, Integer pageSize);

    ResponseResult GetArticle(Long id);

    ResponseResult UpdateViewCount(Long id);
}
