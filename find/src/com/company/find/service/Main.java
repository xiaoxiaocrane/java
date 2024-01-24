package com.company.find.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.company.find.conn.MysqlConn;

public class Main {
	public static void main(String[] args) {
//		MysqlConn conn = new MysqlConn();
//		System.out.println(conn.getConn());
		Connection conn = MysqlConn.getConnection();
//		System.out.print(conn);
		
		try {
		    
		    // ִ��һ���򵥵�SQL��ѯ
		    Statement statement = conn.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT 1");
		    
		    if (resultSet.next()) {
		        int result = resultSet.getInt(1);
		        if (result == 1) {
		            System.out.println("���ӳɹ���");
		        } else {
		            System.out.println("����ʧ�ܣ�");
		        }
		    }
		    
		    resultSet.close();
		    statement.close();
		    conn.close();
		} catch (SQLException e) {
		    System.err.println("����ʧ��: " + e.getMessage());
		    e.printStackTrace();
		}
	}
}


