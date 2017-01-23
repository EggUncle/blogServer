package com.test.model;

import java.sql.Date;
import java.util.List;

/**
 * Created by egguncle on 17-1-13.
 */
public class BlogJson {
    private Boolean error;
    private List<BlogEntity> results;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<BlogEntity> getResults() {
        return results;
    }

    public void setResults(List<BlogEntity> results) {
        this.results = results;
    }

//    class Blog{
//        private int blogId;
//        private Date blogDate;
//        private String blogTitle;
//        private String blogContent;
//        private int userId;
//        private String userName;
//
//        public int getBlogId() {
//            return blogId;
//        }
//
//        public void setBlogId(int blogId) {
//            this.blogId = blogId;
//        }
//
//        public Date getBlogDate() {
//            return blogDate;
//        }
//
//        public void setBlogDate(Date blogDate) {
//            this.blogDate = blogDate;
//        }
//
//        public String getBlogTitle() {
//            return blogTitle;
//        }
//
//        public void setBlogTitle(String blogTitle) {
//            this.blogTitle = blogTitle;
//        }
//
//        public String getBlogContent() {
//            return blogContent;
//        }
//
//        public void setBlogContent(String blogContent) {
//            this.blogContent = blogContent;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public String getUserName() {
//            return userName;
//        }
//
//        public void setUserName(String userName) {
//            this.userName = userName;
//        }
//    }

}
