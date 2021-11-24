package com.tangyu.myblog.web.admin;

import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.pojo.User;
import com.tangyu.myblog.service.BlogService;
import com.tangyu.myblog.service.TagService;
import com.tangyu.myblog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hxy
 * @create 2021-11-11 11:46
 */
@Api(tags = "管理博客控制器")
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @ApiOperation("博客列表")
    @GetMapping("/blogs")
    public String blogs(Blog blog,Model model){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        List<Blog> blogList = blogService.listBlogByPages(1, 6, blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }

    @ApiOperation("搜索博客列表")
    @PostMapping("/blogs/search")
    public String search(@RequestParam("page") Integer pageNum,
                         @RequestParam("typeId") Long typeId,
                         Blog blog, Model model){
        if(typeId!=0){
            blog.setType(typeService.getTypeById(typeId));
        }
        List<Blog> blogList = blogService.listBlogByPages(pageNum, 6, blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs :: listBlog";
    }

    @ApiOperation("博客输入界面")
    @GetMapping("/blogs/input")
    public String input(Model model){
        List<Type> types = typeService.listType();
        model.addAttribute("types",types);
        List<Tag> tags = tagService.listTag();
        model.addAttribute("tags",tags);
        return "admin/blogs-input";
    }

    @ApiOperation("保存新增博客")
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        Long typeId = blog.getType().getId();
        blog.setType(typeService.getTypeById(typeId));
        Boolean isSuccess = blogService.saveBlog(blog);
        if(isSuccess){
            attributes.addFlashAttribute("message","保存博客成功！");
        }else{
            attributes.addFlashAttribute("message","保存博客失败！");
        }
        return "redirect:/admin/blogs";
    }

    @ApiOperation("编辑博客界面")
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable("id") Long id, RedirectAttributes attributes){
        Blog blog = blogService.getBlogById(id);
        attributes.addFlashAttribute("blog",blog);
        return "redirect:/admin/blogs/input";
    }

    @ApiOperation("提交修改博客")
    @PostMapping("/blogs/update")
    public String alter(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        Long typeId = blog.getType().getId();
        blog.setType(typeService.getTypeById(typeId));

        Blog b = blogService.getBlogById(blog.getId());
        blog.setCreateTime(b.getCreateTime());
        Boolean isSuccess = blogService.updateBlog(blog);
        if(isSuccess){
            attributes.addFlashAttribute("message","修改博客成功！");
        }else{
            attributes.addFlashAttribute("message","修改博客失败！");
        }
        return "redirect:/admin/blogs";
    }

    @ApiOperation("删除博客")
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        Boolean isDeleted = blogService.deleteBlog(id);
        if(isDeleted){
            attributes.addFlashAttribute("message","博客删除成功！");
        }else{
            attributes.addFlashAttribute("message","博客删除失败！");
        }
        return "redirect:/admin/blogs";
    }

}
