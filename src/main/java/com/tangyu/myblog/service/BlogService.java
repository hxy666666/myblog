package com.tangyu.myblog.service;

import com.tangyu.myblog.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author hxy
 * @create 2021-11-12 16:25
 */
public interface BlogService {
    Blog getBlogById(Long id);

    Blog getAndConvertBlogById(Long id);

    List<Blog> listBlogByPages(Integer pageNum,Integer pageSize,Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    List<Blog> listBlogBySearch(String query,Integer pageNum,Integer pageSize);

    List<Blog> listBlogByTagId(Long tagId, Integer pageNum, Integer pageSize);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

    Boolean saveBlog(Blog blog);

    Boolean updateBlog(Blog blog);

    Boolean deleteBlog(Long id);
}
