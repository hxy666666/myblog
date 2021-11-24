package com.tangyu.myblog.service;

import com.tangyu.myblog.pojo.Type;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-11 13:44
 */
public interface TypeService {
    Boolean saveType(Type type);

    Type getTypeById(Long id);

    List<Type> listTypeByPages(Integer pageNum,Integer pageSize);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Boolean updateType(Type type);

    Boolean deleteTypeById(Long id);

    Type getTypeByName(String name);
}
