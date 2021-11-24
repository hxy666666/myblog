package com.tangyu.myblog.mapper;

import com.tangyu.myblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author hxy
 * @create 2021-11-10 20:40
 */
@Mapper
@Repository
public interface UserMapper {
    User getUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    User getUserById(@Param("id") Long id);
}
