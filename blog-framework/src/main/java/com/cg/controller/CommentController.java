package com.cg.controller;

import com.cg.entity.Comment;
import com.cg.service.CommentService;
import com.cg.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cgJavaAfter
 * @date 2023-05-21 14:36
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/list")
    public ResponseResult GetComment(Long articleId,Integer pageNum,Integer pageSize){
        return commentService.GetComment(articleId,pageNum,pageSize);
    }

    @PostMapping
    public ResponseResult AddComment(@RequestBody Comment comment){
        return commentService.AddComment(comment);
    }






}
