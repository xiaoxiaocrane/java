package dao;
import java.sql.*;
import java.io.*;
import java.util.*;

public class BaseDao {
	public static String DRIVER;//数据库驱动
	public static String URL;//url
	public static String DBNAME;//数据库用户名
	public static String DBPASS;//数据库密码
	static {
		init();
	}
	
	public static void init() {
		Properties params=new Properties();
	    String config="database.properties";
	    InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(config);
	    
//	    try {
//	    	params.load(is);
//	    }catch(IOException e) {
//	    	e.printStackTrace();
//	    }
//	    DRIVER=params.getProperty("driver");
//	    URL=params.getProperty("url");
//	    DBNAME=params.getProperty("user");
//	    DBPASS=params.getProperty("password");
		DRIVER = "com.mysql.cj.jdbc.Driver";
		URL = "jdbc:mysql://localhost:3306/badmintonclub";
		DBNAME = "root";
		DBPASS = "123456";
	}
	
	/*
	 * 得到数据库的连接
	 */
	public Connection getConn() throws ClassNotFoundException,SQLException{
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,DBNAME,DBPASS);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/*
	 * 释放资源
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 执行增删改操作
	 * 返回受影响的行数
	 */
	public int executeSQL(String preparedSql,Object[] param) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int num=0;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(preparedSql);
			if(param!=null) {
				for(int i=0;i<param.length;i++) {
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, null);
		}
		return num;
	}

}
