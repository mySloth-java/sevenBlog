package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author cgJavaAfter
 * @date 2023-04-26 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleById {

    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    //所属分类name
    private String categoryName;

    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    private Date createTime;
}
