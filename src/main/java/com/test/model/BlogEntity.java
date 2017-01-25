package com.test.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by egguncle on 17-1-17.
 */
@Entity
@Table(name = "table_blog", schema = "db_blog", catalog = "")
public class BlogEntity {
    private int blogId;
    private Date blogDate;
    private String blogTitle;
    private String blogContent;
    private UserEntity tableUserByUserId;
    private String imgPath;

    @Id
    @Column(name = "blogId", nullable = false)
    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Basic
    @Column(name = "blogDate", nullable = true)
    public Date getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(Date blogDate) {
        this.blogDate = blogDate;
    }

    @Basic
    @Column(name = "blogTitle", nullable = true, length = 45)
    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    @Basic
    @Column(name = "blogContent", nullable = true, length = -1)
    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntity that = (BlogEntity) o;

        if (blogId != that.blogId) return false;
        if (blogDate != null ? !blogDate.equals(that.blogDate) : that.blogDate != null) return false;
        if (blogTitle != null ? !blogTitle.equals(that.blogTitle) : that.blogTitle != null) return false;
        if (blogContent != null ? !blogContent.equals(that.blogContent) : that.blogContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = blogId;
        result = 31 * result + (blogDate != null ? blogDate.hashCode() : 0);
        result = 31 * result + (blogTitle != null ? blogTitle.hashCode() : 0);
        result = 31 * result + (blogContent != null ? blogContent.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public UserEntity getTableUserByUserId() {
        return tableUserByUserId;
    }

    public void setTableUserByUserId(UserEntity tableUserByUserId) {
        this.tableUserByUserId = tableUserByUserId;
    }

    @Basic
    @Column(name = "imgPath", nullable = true, length = 45)
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
