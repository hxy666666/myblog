package com.tangyu.myblog.mapper;

import com.tangyu.myblog.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-12 16:34
 */
@Mapper
@Repository
public interface BlogMapper {
    Blog getBlogById(@Param("id") Long id);

    List<Blog> listBlog(Blog blog);

    List<Blog> listBlogByTypeId(@Param("typeId") Long typeId);

    List<Blog> listBlogByTagIdLike(@Param("tagId") Long tagId);

    List<Blog> listBlogBySearchLike(@Param("query") String query);

    List<String> listBlogGroupYear();

    List<Blog> listBlogByYear(@Param("year") String year);

    Long countBlog();

    Boolean saveBlog(Blog blog);

    Boolean updateBlog(Blog blog);

    Boolean updateBlogViews(@Param("id") Long id);

    Boolean deleteBlog(@Param("id") Long id);
}
