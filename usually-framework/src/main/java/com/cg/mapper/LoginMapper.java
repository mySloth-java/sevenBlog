package com.cg.mapper;

import com.cg.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cgJavaAfter
 * @date 2023-05-06 14:46
 */
@Mapper
public interface LoginMapper {

    LoginUser GetLoginByName(String name);

}
