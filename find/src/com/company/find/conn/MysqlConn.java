package com.company.find.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConn {
	private static String driver = "com.mysql.cj.jdbc.Driver"; // �µ���������������
	private static String url = "jdbc:mysql://localhost:3306/usermanager?user=root$password=123456";
	private static String username = "root"; // �޸�Ϊ������ݿ��û���
    private static String password = "123456"; // �޸�Ϊ������ݿ�����
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
