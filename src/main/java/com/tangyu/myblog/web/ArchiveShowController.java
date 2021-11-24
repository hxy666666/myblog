package com.tangyu.myblog.web;

import com.tangyu.myblog.service.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hxy
 * @create 2021-11-17 20:55
 */
@Api(tags = "归档控制器")
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @ApiOperation("归档页面")
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.countBlog());
        return "archives";
    }
}
