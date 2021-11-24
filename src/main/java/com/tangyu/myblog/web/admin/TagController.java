package com.tangyu.myblog.web.admin;

import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.pojo.Tag;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.service.TagService;
import com.tangyu.myblog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-11 15:40
 */
@Api(tags = "标签控制器")
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation("标签列表")
    @GetMapping("/tags")
    public String tags(Model model){
        List<Tag> tagList = tagService.listTagByPages(1, 5);
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @ApiOperation("标签列表翻页")
    @GetMapping("/tags/{pageNum}")
    public String tags(@PathVariable("pageNum") Integer pageNum, Model model){
        List<Tag> tagList = tagService.listTagByPages(pageNum, 5);
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @ApiOperation("标签输入界面")
    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }

    @ApiOperation("保存新增标签")
    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes,Model model){
        //如果标签名已存在，则返回输入标签输入界面
        String name = tag.getName();
        Tag t = tagService.getTagByName(name);
        if(t != null){
            model.addAttribute("message","标签已存在！");
            return "admin/tags-input";
        }
        //标签名不存在
        Boolean isSuccess = tagService.saveTag(tag);
        if(isSuccess){
            attributes.addFlashAttribute("message","标签保存成功！");
        }else {
            attributes.addFlashAttribute("message","标签保存失败！");
        }

        return "redirect:/admin/tags";
    }

    @ApiOperation("编辑标签界面")
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id,RedirectAttributes attributes){
        Tag tag = tagService.getTagById(id);
        attributes.addFlashAttribute("tag",tag);
        return "redirect:/admin/tags/input";
    }

    @ApiOperation("提交修改标签")
    @PostMapping("/tags/update")
    public String alter(Tag tag, RedirectAttributes attributes,Model model){
        //如果标签名已存在，则返回输入标签输入界面
        String name = tag.getName();
        Tag t = tagService.getTagByName(name);
        if(t != null){
            model.addAttribute("message","标签已存在！");
            return "admin/tags-input";
        }
        //标签名不存在
        Boolean isSuccess = tagService.updateTag(tag);
        if(isSuccess){
            attributes.addFlashAttribute("message","标签修改成功！");
        }else {
            attributes.addFlashAttribute("message","标签修改失败！");
        }

        return "redirect:/admin/tags";
    }

    @ApiOperation("删除标签")
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        Boolean isDeleted = tagService.deleteTagById(id);
        if(isDeleted){
            attributes.addFlashAttribute("message","标签删除成功！");
        }else{
            attributes.addFlashAttribute("message","标签删除失败！");
        }
        return "redirect:/admin/tags";
    }
}
