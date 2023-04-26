package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author cgJavaAfter
 * @date 2023-04-25 21:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//文章分类返回类
public class ClassifyArticle {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //所属分类名称
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（0否，1是）
    private String isTop;
    //访问量
    private Long viewCount;
    private Date createTime;
}
