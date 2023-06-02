package com.cg.mapper;

import com.cg.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author cgJavaAfter
 * @date 2023-05-06 14:46
 */
@Mapper
public interface LoginMapper {

    LoginUser GetLoginByName(String name);

    List<String> GetAuthority(Long id);

    LoginUser GetLoginById(Long id);

    Integer Update(LoginUser user);

    Integer Add(LoginUser user);





}
