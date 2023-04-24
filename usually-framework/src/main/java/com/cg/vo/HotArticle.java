package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cgJavaAfter
 * @date 2023-04-24 20:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticle {
    private Long id;
    //标题
    private String title;
    //访问量
    private Long viewCount;
}
