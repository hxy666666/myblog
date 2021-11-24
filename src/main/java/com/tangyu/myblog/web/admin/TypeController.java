package com.tangyu.myblog.web.admin;

import com.github.pagehelper.PageInfo;
import com.tangyu.myblog.pojo.Type;
import com.tangyu.myblog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-11 15:40
 */
@Api(tags = "分类控制器")
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation("类型列表")
    @GetMapping("/types")
    public String types(Model model){
        List<Type> typeList = typeService.listTypeByPages(1, 5);
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @ApiOperation("类型列表翻页")
    @GetMapping("/types/{pageNum}")
    public String types(@PathVariable("pageNum") Integer pageNum, Model model){
        List<Type> typeList = typeService.listTypeByPages(pageNum, 5);
        PageInfo<Type> pageInfo = new PageInfo<>(typeList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @ApiOperation("类型输入界面")
    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }

    @ApiOperation("保存新增类型")
    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes,Model model){
        //如果类型名已存在，则返回输入类型输入界面
        String name = type.getName();
        Type t = typeService.getTypeByName(name);
        if(t != null){
            model.addAttribute("message","类型已存在！");
            return "admin/types-input";
        }
        //类型名不存在
        Boolean isSuccess = typeService.saveType(type);
        if(isSuccess){
            attributes.addFlashAttribute("message","类型保存成功！");
        }else {
            attributes.addFlashAttribute("message","类型保存失败！");
        }

        return "redirect:/admin/types";
    }

    @ApiOperation("编辑类型界面")
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id,RedirectAttributes attributes){
        Type type = typeService.getTypeById(id);
        attributes.addFlashAttribute("type",type);
        return "redirect:/admin/types/input";
    }

    @ApiOperation("提交修改类型")
    @PostMapping("/types/update")
    public String alter(Type type, RedirectAttributes attributes,Model model){
        //如果类型名已存在，则返回输入类型输入界面
        String name = type.getName();
        Type t = typeService.getTypeByName(name);
        if(t != null){
            model.addAttribute("message","类型已存在！");
            return "admin/types-input";
        }
        //类型名不存在
        Boolean isSuccess = typeService.updateType(type);
        if(isSuccess){
            attributes.addFlashAttribute("message","类型修改成功！");
        }else {
            attributes.addFlashAttribute("message","类型修改失败！");
        }

        return "redirect:/admin/types";
    }

    @ApiOperation("删除类型")
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        Boolean isDeleted = typeService.deleteTypeById(id);
        if(isDeleted){
            attributes.addFlashAttribute("message","类型删除成功！");
        }else{
            attributes.addFlashAttribute("message","类型删除失败！");
        }
        return "redirect:/admin/types";
    }
}
