package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-21 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPageVo {
    private List rows;
    private Integer total;
}
