package com.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/**
 * 连接Mysql数据库的帮助类
 */
public class MySQLDBHelper {

	public static String server = "127.0.0.1"; // 服务器ip地址
	public static int port = 3306; // 服务器端口号
	public static String database = "db_blog"; // 数据库名称
	public static String username = "root"; // 登录sql身份名称
	public static String password = "unicorn"; // 登录sql身份密码

	public static void build(String server, int port, String database, String username, String password) {

		MySQLDBHelper.server = server;
		MySQLDBHelper.port = port;
		MySQLDBHelper.database = database +"?useUnicode=true&characterEncoding=utf8";
		MySQLDBHelper.username = username;
		MySQLDBHelper.password = password;
	}

	/**
	 * 发送 DQL （查询）语句去数据库执行的方法
	 * 
	 * @param sql
	 *            要执行的查询语句
	 * @return 查询到的结果集
	 */
	public static ResultSet query(String sql) {

		try {
			// 加载驱动
			Driver driver = new Driver();

			// 创建数据库连接对象
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database,
					username, password);

			// 数据执行对象
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			return rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 参数化发送DQL（查询）语句去数据库执行的方法
	 * 
	 * @param sql
	 *            要发送的参数化的查询语句
	 * @param params
	 *            参数数组
	 * @return 查询到的结果集
	 */
	public static ResultSet query(String sql, Object[] params) {

		try {
			// 加载驱动
			Driver driver = new Driver();

			// 创建数据库连接对象
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database ,
					username, password);

			// 数据执行对象
			PreparedStatement stmt = conn.prepareStatement(sql);

			// 添加参数
			// 循环参数数组
			for (int i = 0; i <= params.length - 1; i++) {
				// 将当前循环到的参数 添加 给 预处理 命令执行对象
				stmt.setObject(i + 1, params[i]);
			}

			ResultSet rs = stmt.executeQuery();

			return rs;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 发送 DML （增加，删除，修改）语句去数据库执行的方法
	 * 
	 * @param sql
	 *            要执行的数据库命令
	 * @return 数据库受影响的行数
	 */
	public static int execute(String sql) {

		try {
			// 加载驱动
			Driver driver = new Driver();

			// 创建数据库连接对象
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database,
					username, password);

			// 数据执行对象
			Statement stmt = conn.createStatement();

			int i = stmt.executeUpdate(sql);

			conn.close();

			return i;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
