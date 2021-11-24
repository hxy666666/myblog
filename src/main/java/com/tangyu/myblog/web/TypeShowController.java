package com.tangyu.myblog.web;

import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.service.BlogService;
import com.tangyu.myblog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-17 16:28
 */
@Api(tags = "分类展示控制器")
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;

    @ApiOperation("分类界面")
    @GetMapping("/types/{id}")
    public String types(@PathVariable("id") Long id, Model model){
        List<Type> types = typeService.listTypeTop(100);
        if(id==-1){
            id=types.get(0).getId();
        }
        Blog blog = new Blog();
        Type type = new Type();
        type.setId(id);
        blog.setType(type);
        List<Blog> blogList = blogService.listBlogByPages(1, 10, blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);

        return "types";
    }

    @GetMapping("/types")
    public String types(){
        return "types";
    }

    @ApiOperation("分类翻页界面")
    @GetMapping("/types/{id}/{pageNum}")
    public String types(@PathVariable("pageNum") Integer pageNum,@PathVariable("id") Long id, RedirectAttributes attributes){
        List<Type> types = typeService.listTypeTop(100);
        if(id==-1){
            id=types.get(0).getId();
        }
        Blog blog = new Blog();
        Type type = new Type();
        type.setId(id);
        blog.setType(type);
        List<Blog> blogList = blogService.listBlogByPages(pageNum, 10, blog);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        attributes.addFlashAttribute("types",types);
        attributes.addFlashAttribute("pageInfo",pageInfo);
        attributes.addFlashAttribute("activeTypeId",id);

        return "redirect:/types";
    }
}
