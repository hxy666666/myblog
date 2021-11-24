package com.tangyu.myblog.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxy
 * @create 2021-11-08 21:06
 */
@Data
public class Type implements Comparable<Type> {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();

    @Override
    public int compareTo(Type o) {
        return o.getBlogs().size()-this.getBlogs().size();
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
