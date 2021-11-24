package com.tangyu.myblog.mapper;

import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-12 10:48
 */
@Mapper
@Repository
public interface TagMapper {
    Boolean saveTag(Tag tag);

    Tag getTagById(@Param("id") Long id);

    List<Tag> listTag();

    List<Tag> listTagByConditionForeach(@Param("idList") List<Long> idList);

    List<Tag> listTagByTagIds(@Param("ids") String ids);

    Boolean updateTag(Tag tag);

    Boolean deleteTagById(@Param("id") Long id);

    Tag getTagByName(@Param("name") String name);
}
