package com.tangyu.myblog.mapper;

import com.tangyu.myblog.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hxy
 * @create 2021-11-17 11:25
 */
@Mapper
@Repository
public interface CommentMapper {

    Comment getCommentById(@Param("id") Long id);

    List<Comment> listCommentByBlogIdAndParentNull(@Param("blogId") Long blogId);

    List<Comment> listCommentByBlogId(@Param("blogId") Long blogId);

    List<Comment> listCommentByParentCommentId(@Param("id") Long id);

    Boolean saveComment(Comment comment);
}
