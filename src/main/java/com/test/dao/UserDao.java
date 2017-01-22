package com.test.dao;

import com.test.db.MySQLDBHelper;
import com.test.model.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {

    public UserEntity login(String name, String pass) {
        String sql = "select * from table_user "
                + "where username='" + name + "' "
                + "and userpasswd='" + pass + "'";
//        ResultSet rs = MySQLDBHelper.query(sql);
//        try {
//            if (rs.next()) {
//                //用户名和密码正确，能够查询出一条记录
//                UserEntity user = new UserEntity();
//                user.setUserId(rs.getInt("userId"));
//                user.setUsername(rs.getString("username"));
//                user.setUserpasswd(rs.getString("userpasswd"));
//                return user;
//            } else {
//                //用户名和密码不正确，不存在这样的用户
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return getUserBySql(sql);
    }

    public UserEntity getUserById(int id){
        String sql="select * from table_user "
                + "where userId='" + id + "' ";
        return getUserBySql(sql);

    }

    public UserEntity getUserBySql(String sql){
        ResultSet rs = MySQLDBHelper.query(sql);
        try {
            if (rs.next()) {
                UserEntity user = new UserEntity();
                user.setUserId(rs.getInt("userId"));
                user.setUsername(rs.getString("username"));
                user.setUserpasswd(rs.getString("userpasswd"));
                return user;
            } else {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
