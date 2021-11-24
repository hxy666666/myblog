package com.tangyu.myblog.service;

import com.tangyu.myblog.pojo.Comment;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-17 11:22
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    Boolean saveComment(Comment comment);
}
