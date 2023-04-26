package com.cg.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-04-25 21:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//分页返回类
public class PageVo {
    private List rows;
    private Integer total;
}
