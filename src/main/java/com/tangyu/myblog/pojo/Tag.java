package com.tangyu.myblog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @create 2021-11-08 21:06
 */
@Data
public class Tag implements Comparable<Tag> {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    @Override
    public int compareTo(Tag o) {
        return o.getBlogs().size()-this.getBlogs().size();
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
