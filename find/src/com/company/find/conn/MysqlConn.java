package com.company.find.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {
	private static String driver = "com.mysql.cj.jdbc.Driver"; // 新的驱动程序类名称
	private static String url = "jdbc:mysql://localhost:3306/usermanager?user=root$password=123456";
	private static String username = "root"; // 修改为你的数据库用户名
    private static String password = "123456"; // 修改为你的数据库密码
	private static Connection conn;
	
	static {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() {
		return conn;
	}
	

}
