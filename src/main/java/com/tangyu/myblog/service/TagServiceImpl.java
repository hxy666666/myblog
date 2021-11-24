package com.tangyu.myblog.service;

import com.github.pagehelper.PageHelper;
import com.tangyu.myblog.mapper.TagMapper;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hxy
 * @create 2021-11-12 10:46
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public Boolean saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public List<Tag> listTagByPages(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return tagMapper.listTag();
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<Long> idList = convertToList(ids);
        return tagMapper.listTagByConditionForeach(idList);
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        List<Tag> tagList = tagMapper.listTag();
        Collections.sort(tagList);
        if(size>tagList.size()){
            return tagList;
        }
        return tagList.subList(0,size);
    }

    private List<Long> convertToList(String ids){
        ArrayList<Long> list = new ArrayList<>();
        if(!"".equals(ids) && ids !=null){
            String[] idArray = ids.split(",");
            for(int i=0;i<idArray.length;i++){
                list.add(new Long(idArray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Boolean updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public Boolean deleteTagById(Long id) {
        return tagMapper.deleteTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }
}
