package com.tangyu.myblog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hxy
 * @create 2021-11-08 21:09
 */
@Data
public class Comment implements Comparable<Comment> {
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Boolean adminComment;

    private Blog blog;

    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;

    @Override
    public int compareTo(Comment o) {
        if(this.getCreateTime().getTime()-o.getCreateTime().getTime()>0){
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", adminComment=" + adminComment +
                '}';
    }
}
