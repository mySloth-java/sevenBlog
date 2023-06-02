package com.cg.service;

import com.cg.entity.Comment;
import com.cg.util.ResponseResult;

/**
 * @author cgJavaAfter
 * @date 2023-05-21 14:38
 */
public interface CommentService {

    ResponseResult GetComment(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult AddComment(Comment comment);
}
