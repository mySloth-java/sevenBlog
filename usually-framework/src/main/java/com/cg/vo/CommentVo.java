package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-21 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVo {

    private Long id;

    //文章id
    private Long articleId;
    //根评论id
    private Long rootId;
    //评论内容
    private String content;
    //所回复的目标评论的userid
    private Long toCommentUserId;
    //回复目标评论id
    private Long toCommentId;

    private Long createBy;

    private Date createTime;

    //数据库没有字段
    //回复目标评论的用户昵称
    private String toCommentName;
    //评论者的用户昵称
    private String name;

    //子评论
    private List<CommentVo> children;
}
