package com.tangyu.myblog.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.mapper.TypeMapper;
import com.tangyu.myblog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author hxy
 * @create 2021-11-11 14:31
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public Boolean saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Override
    public List<Type> listTypeByPages(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return typeMapper.listType();
    }

    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        List<Type> typeList = typeMapper.listType();
        Collections.sort(typeList);
        if(size>typeList.size()){
            return typeList;
        }
        return typeList.subList(0,size);
    }

    @Transactional
    @Override
    public Boolean updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public Boolean deleteTypeById(Long id) {
        return typeMapper.deleteTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }
}
