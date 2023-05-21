package com.cg.mapper;

import com.cg.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-21 14:43
 */
@Mapper
public interface CommentMapper {
    List<Comment> GetComment(@Param("articleId") Long articleId,
                             @Param("pageNum") Integer pageNum,
                             @Param("pageSize") Integer pageSize);

    Integer GetCommentByArticleIdCount(Long articleId);

    List<Comment> GetCommentById(Long id);
}
