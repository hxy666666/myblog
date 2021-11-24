package com.tangyu.myblog.web;

import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.exception.NotFoundPageException;
import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.service.BlogService;
import com.tangyu.myblog.service.TagService;
import com.tangyu.myblog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-07 16:16
 */
@Api(tags = "首页控制器")
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @ApiOperation("首页")
    @GetMapping("/")
    public String index(Model model){

        List<Blog> blogList = blogService.listBlogByPages(1, 10, new Blog());
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        List<Type> types = typeService.listTypeTop(6);
        model.addAttribute("types",types);
        List<Tag> tags = tagService.listTagTop(6);
        model.addAttribute("tags",tags);
        List<Blog> blogs = blogService.listRecommendBlogTop(6);
        model.addAttribute("Recommendblogs",blogs);
        return "index";
    }

    @ApiOperation("首页翻页")
    @GetMapping("/index/{pageNum}")
    public String index(@PathVariable("pageNum") Integer pageNum,Model model){

        List<Blog> blogList = blogService.listBlogByPages(pageNum, 10, new Blog());
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        List<Type> types = typeService.listTypeTop(6);
        model.addAttribute("types",types);
        List<Tag> tags = tagService.listTagTop(6);
        model.addAttribute("tags",tags);
        List<Blog> blogs = blogService.listRecommendBlogTop(2);
        model.addAttribute("Recommendblogs",blogs);
        return "index";
    }

    @ApiOperation("搜索博客")
    @PostMapping("/search")
    public String search(@RequestParam("query") String query,Model model){
        List<Blog> blogList = blogService.listBlogBySearch(query, 1, 10);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    @ApiOperation("搜索博客翻页")
    @GetMapping("/search/{pageNum}")
    public String search(@PathVariable("pageNum") Integer pageNum,String query,Model model){
        List<Blog> blogList = blogService.listBlogBySearch(query, pageNum, 10);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    @ApiOperation("博客详情")
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){
        Blog blog = blogService.getAndConvertBlogById(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @ApiOperation("底部最新推荐")
    @GetMapping("/footer/newblog")
    public String newblogs(Model model){
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }

}
