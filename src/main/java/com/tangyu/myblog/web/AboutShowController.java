package com.tangyu.myblog.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hxy
 * @create 2021-11-17 23:30
 */
@Api(tags = "关于我控制器")
@Controller
public class AboutShowController {

    @ApiOperation("关于我页面")
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
