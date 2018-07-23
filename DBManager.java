package com.qf.shop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * 数据库管理类，负责创建连接和关闭资源
 * 
 * @author Administrator
 *
 */
public class DBManager {
	public static String jdbcDriver = "com.mysql.jdbc.Driver";
	public static String jdbcUrl = "jdbc:mysql:///shop";
	public static String username = "root";
	public static String password = "123";

	// 得到连接对象
	public static Connection getConnection() {
		try {
			Class.forName(jdbcDriver);
			return DriverManager.getConnection(jdbcUrl, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 关闭资源
	public static void closeAll(AutoCloseable... autoCloseables) {
		for (AutoCloseable autoCloseable : autoCloseables) {
			if (autoCloseable != null) {
				try {
					autoCloseable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
