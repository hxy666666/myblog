package com.tangyu.myblog.web;

import com.tangyu.myblog.pojo.Blog;
import com.tangyu.myblog.pojo.Comment;
import com.tangyu.myblog.pojo.User;
import com.tangyu.myblog.service.BlogService;
import com.tangyu.myblog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hxy
 * @create 2021-11-17 11:14
 */
@Api(tags = "评论控制器")
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Value("${comment.avatar}")
    private String avatar;

    @ApiOperation("展示评论结果")
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Long blogId, Model model){
        List<Comment> comments = commentService.listCommentByBlogId(blogId);
        model.addAttribute("comments",comments);
        return "blog :: commentList";
    }

    @ApiOperation("提交评论和回复评论")
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else{
            comment.setAvatar(avatar);
            comment.setAdminComment(false);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlog().getId();
    }
}
