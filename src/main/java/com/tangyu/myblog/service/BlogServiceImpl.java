package com.tangyu.myblog.service;

import com.github.pagehelper.PageHelper;
import com.tangyu.myblog.mapper.BlogMapper;
import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author hxy
 * @create 2021-11-12 16:33
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Transactional
    @Override
    public Blog getAndConvertBlogById(Long id) {
        blogMapper.updateBlogViews(id);
        Blog blog = blogMapper.getBlogById(id);
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        return blog;
    }

    @Override
    public List<Blog> listBlogByPages(Integer pageNum, Integer pageSize, Blog blog) {
        PageHelper.startPage(pageNum,pageSize);
        return blogMapper.listBlog(blog);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Blog blog = new Blog();
        blog.setRecommend(true);
        List<Blog> blogList = blogMapper.listBlog(blog);
        Collections.sort(blogList);
        if(size>blogList.size()){
            return blogList;
        }
        return blogList.subList(0,size);
    }

    @Override
    public List<Blog> listBlogBySearch(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return blogMapper.listBlogBySearchLike(query);
    }

    @Override
    public List<Blog> listBlogByTagId(Long tagId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return blogMapper.listBlogByTagIdLike(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        List<String> years = blogMapper.listBlogGroupYear();
        for (String year : years) {
            List<Blog> blogList = blogMapper.listBlogByYear(year);
            Collections.sort(blogList);
            map.put(year,blogList);
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogMapper.countBlog();
    }

    @Transactional
    @Override
    public Boolean saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.saveBlog(blog);
    }

    @Transactional
    @Override
    public Boolean updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Transactional
    @Override
    public Boolean deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }
}
