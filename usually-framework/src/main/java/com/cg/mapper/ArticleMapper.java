package com.cg.mapper;

import com.cg.entity.Article;
import com.cg.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-22 20:13
 */
@Mapper
public interface ArticleMapper {
    List<Article> GetHotArticle();

    Article GetArticleById(Long id);

    Integer GetArticleByCIdCount(@Param("categoryId") Long categoryId);

    List<Article> ClassifyCategory(@Param("categoryId") Long categoryId,
                                    @Param("pageNum") Integer pageNum,
                                    @Param("pageSize") Integer pageSize);

}
