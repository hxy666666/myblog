package com.tangyu.myblog.mapper;

import com.tangyu.myblog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-11 14:32
 */
@Mapper
@Repository
public interface TypeMapper {
    Boolean saveType(Type type);

    Type getTypeById(@Param("id") Long id);

    List<Type> listType();

    Boolean updateType(Type type);

    Boolean deleteTypeById(@Param("id")Long id);

    Type getTypeByName(@Param("name") String name);
}
