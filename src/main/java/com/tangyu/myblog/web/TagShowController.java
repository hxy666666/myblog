package com.tangyu.myblog.web;

import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.service.BlogService;
import com.tangyu.myblog.service.TagService;
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
@Api(tags = "标签展示控制器")
@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @ApiOperation("标签界面")
    @GetMapping("/tags/{id}")
    public String tags(@PathVariable("id") Long id, Model model){
        List<Tag> tags = tagService.listTagTop(100);
        if(id==-1){
            id=tags.get(0).getId();
        }
        List<Blog> blogList = blogService.listBlogByTagId( id,1, 10);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTagId",id);

        return "tags";
    }

    @GetMapping("/tags")
    public String tags(){
        return "tags";
    }

    @ApiOperation("标签翻页界面")
    @GetMapping("/tags/{id}/{pageNum}")
    public String tags(@PathVariable("pageNum") Integer pageNum,@PathVariable("id") Long id, RedirectAttributes attributes){
        List<Tag> tags = tagService.listTagTop(100);
        if(id==-1){
            id=tags.get(0).getId();
        }
        List<Blog> blogList = blogService.listBlogByTagId( id,pageNum, 10);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        attributes.addFlashAttribute("tags",tags);
        attributes.addFlashAttribute("pageInfo",pageInfo);
        attributes.addFlashAttribute("activeTagId",id);

        return "redirect:/tags";
    }
}
