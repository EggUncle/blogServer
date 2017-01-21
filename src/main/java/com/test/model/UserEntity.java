package com.test.model;

import javax.persistence.*;

/**
 * Created by egguncle on 17-1-17.
 */
@Entity
@Table(name = "table_user", schema = "db_blog", catalog = "")
public class UserEntity {
    private int userId;
    private String username;
    private String userpasswd;

    @Id
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "userpasswd", nullable = false, length = 45)
    public String getUserpasswd() {
        return userpasswd;
    }

    public void setUserpasswd(String userpasswd) {
        this.userpasswd = userpasswd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (userpasswd != null ? !userpasswd.equals(that.userpasswd) : that.userpasswd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userpasswd != null ? userpasswd.hashCode() : 0);
        return result;
    }
}
