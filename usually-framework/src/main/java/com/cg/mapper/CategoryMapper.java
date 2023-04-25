package com.cg.mapper;

import com.cg.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author cgJavaAfter
 * @date 2023-04-25 08:19
 */
@Mapper
public interface CategoryMapper {
    List<Category> GetCategory();

    List<Category> GetCategoryByIds(@Param("ids")Set<Long> ids);
}
