package com.tangyu.myblog.service;

import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-12 10:41
 */
public interface TagService {
    Boolean saveTag(Tag tag);

    Tag getTagById(Long id);

    List<Tag> listTagByPages(Integer pageNum, Integer pageSize);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Boolean updateTag(Tag tag);

    Boolean deleteTagById(Long id);

    Tag getTagByName(String name);
}
