package com.cg.mapper;

import com.cg.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:13
 */
@Mapper
public interface ArticleMapper {
    List<Article> GetArticle();

}
