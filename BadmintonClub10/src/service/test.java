package service;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import dao.BaseDao;


//供测试功能使用，之后会删除
public class test {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
			System.out.println("数据库连接成功!");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
					System.out.println("关闭数据库成功");
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		Properties params=new Properties();
	    String config="database.properties";
	    InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(config);
		
//	    if (is == null) {
//	        System.out.println("输入流对象为空");
//	    }
	    
	 // 定义数据库连接信息
        String url = "jdbc:mysql://localhost:3306/badmintonclub";
        String username = "root";
        String password = "123456";

        // 定义 SQL 查询语句
        String query = "SELECT * FROM competition WHERE id IS NOT NULL";

        try {
            // 连接到数据库
            Connection connection = DriverManager.getConnection(url, username, password);

            // 创建 Statement 对象
            Statement statement = connection.createStatement();

            // 执行查询
            ResultSet resultSet = statement.executeQuery(query);

            // 遍历结果集并输出每行的信息
            while (resultSet.next()) {
                // 获取每列的值
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Competitionname");
                // 其他列的获取方式类似

                // 输出行信息
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                // 其他列的输出方式类似
                System.out.println("--------------------");
            }

            // 关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

		
		
	}
	
	

}
