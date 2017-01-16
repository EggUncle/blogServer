package com.test.model;

import java.util.List;

/**
 * Created by egguncle on 17-1-13.
 */
public class BlogJson {
    private String status;
    private List<TableBlogEntity> blogEntityList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TableBlogEntity> getBlogEntityList() {
        return blogEntityList;
    }

    public void setBlogEntityList(List<TableBlogEntity> blogEntityList) {
        this.blogEntityList = blogEntityList;
    }
}
