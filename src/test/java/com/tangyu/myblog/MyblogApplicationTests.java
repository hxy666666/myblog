package com.tangyu.myblog;

import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.service.BlogService;
import com.tangyu.myblog.service.TagService;
import com.tangyu.myblog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @Test
    void contextLoads() {
//        List<Type> typeList = typeService.listTypeTop(6);
//        for (Type type : typeList) {
//            System.out.println(type.getName()+":::::::::"+type.getBlogs().size());
//        }

//        List<Tag> tagList = tagService.listTagTop(3);
//        for (Tag tag : tagList) {
//            System.out.println(tag.getName()+":::::::::"+tag.getBlogs().size());
//        }

//        Blog blog = blogService.getBlogById((long) 1);
//        Date updateTime = blog.getUpdateTime();
//        long time = updateTime.getTime();
//        System.out.println(time);

//        List<Blog> blogs = blogService.listRecommendBlogTop(2);
//        for (Blog blog : blogs) {
//            System.out.println(blog.getUpdateTime()+"::::"+blog.getTitle());
//        }

//        List<Blog> blogList = blogService.listBlogBySearch("文章", 1, 10);
//        for (Blog blog : blogList) {
//            System.out.println(blog.getTitle());
//        }

        Map<String, List<Blog>> map = blogService.archiveBlog();
    }

}
