package com.test.dao;

import com.test.db.MySQLDBHelper;
import com.test.model.BlogEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by egguncle on 17-1-11.
 */
public class BlogDao {

    public List<BlogEntity> getBlogListByUserId(int userId) {
        String sql = "select * from table_blog where userId ='" + userId + "'";

        ResultSet rs = MySQLDBHelper.query(sql);
        List<BlogEntity> blogList = new ArrayList<>();
        try {

            while (rs.next()) {
                BlogEntity BlogEntity = new BlogEntity();
                BlogEntity.setBlogId(rs.getInt("blogId"));
                BlogEntity.setBlogDate(rs.getDate("blogDate"));
                BlogEntity.setBlogTitle(rs.getString("blogTitle"));
                BlogEntity.setBlogContent(rs.getString("blogContent"));
                blogList.add(BlogEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return blogList;
    }

}
