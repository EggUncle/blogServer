package com.test.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;

/**
 * Created by egguncle on 17-1-17.
 */
@Entity
@Table(name = "table_user", schema = "db_blog", catalog = "")
public class UserEntity {
    private int userId;
    private String username;
    private String iconPath;
    private String description;
    private String nickname;
    private String status;
    private String bgPath;
    private String token;

   // private File iconFile;
    @JsonIgnore
    private String userpasswd;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Basic
    @Column(name = "iconPath", nullable = true, length = 256)
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "nickname", nullable = true, length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "bgPath", nullable = true, length = 256)
    public String getBgPath() {
        return bgPath;
    }

    public void setBgPath(String bgPath) {
        this.bgPath = bgPath;
    }

    @Basic
    @Column(name = "token", nullable = true, length = 128)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
