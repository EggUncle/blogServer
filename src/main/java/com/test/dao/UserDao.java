package com.test.dao;

import com.test.db.MySQLDBHelper;
import com.test.model.TableUserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDao {

	public TableUserEntity login(String name, String pass){
		String sql = "select * from table_user "
				+ "where username='"+name+"' "
				+ "and userpasswd='"+pass+"'";
		ResultSet rs = MySQLDBHelper.query(sql);
		try {
			if(rs.next()){
				//用户名和密码正确，能够查询出一条记录
				TableUserEntity user = new TableUserEntity();
				user.setUserId(rs.getInt("userId"));
				user.setUsername(rs.getString("username"));
				user.setUserpasswd(rs.getString("userpasswd"));
				return user;
			}else{
				//用户名和密码不正确，不存在这样的用户
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
