package com.company.find.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.find.conn.MysqlConn;
import com.company.find.bean.People;
import com.company.find.bean.User;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	public UserDAO() {
		conn = MysqlConn.getConnection();
	}
	
	public List<?> select(String condition){
		return null;
	}
	
	public List<?> findAll(){
		return null;
	}
	
	public User findByUserPass(String username,String password) throws SQLException{
		String sql="select * from tbl_user where username=? and password=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, username);
        ps.setNString(2, password);
        rs = ps.executeQuery();
        User user = null;
        if(rs.next()) {
        	user = new User();
        	user.setId(rs.getInt("id"));
        	user.setUsername(rs.getString("username"));
        	user.setPassword(rs.getString("password"));
        	user.setRule(rs.getInt("rule"));
        	
        }
        return user;
	}

	public List<People> select(String fuck, String key) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_people where ? = ?";
		ps= conn.prepareStatement(sql);
		ps.setString(1, fuck);
		ps.setNString(2, key);
		rs = ps.executeQuery();
		List<People> list = new ArrayList<People>();
		while(rs.next()) {
			People peo = new People();
			peo.setId(rs.getInt("id"));
			peo.setNum(rs.getString("num"));
			String name = peo.pname(rs.getString("name"));
			peo.setName(name);
			peo.setName(name);
			if(rs.getString("name").toString().contains("*")) { //女的
				peo.setSex("女");
			}else {
				peo.setSex("男");
			}
			peo.setCclass(rs.getString("class"));
			peo.setMobile(rs.getNString("mobile"));
			peo.setEmail(rs.getNString("email"));
			list.add(peo);
		}
		return list;
		
	}
	
	public int getTotalRow(String fuck, String key) throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
		fuck = new String(fuck.getBytes("ISO-8859-1"), "UTF-8");
		String sql = ""; // 声明 sql 变量
		if(fuck.equals("num")) {
			sql = "SELECT count(*) FROM tbl_people where num = ?";
		}else if(fuck.equals("name")) {
			sql = "SELECT count(*) FROM tbl_people where name = ?";
		}
		else if(fuck.equals("mobile")) {
			sql = "SELECT count(*) FROM tbl_people where mobile = ?";
		}
		else if(fuck.equals("email")) {
			sql = "SELECT count(*) FROM tbl_people where email = ?";
		}
		ps= conn.prepareStatement(sql);
		ps.setNString(1, key);
		System.out.println(key);
		System.out.println(fuck);
		rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println("进不来吧");
			return rs.getInt(1);
		}else {
			System.out.println("进不来吗");
			ps= conn.prepareStatement(sql);
			key = key + "*";
			ps.setString(1, key);
			System.out.println(key);
			System.out.println(fuck);
			rs = ps.executeQuery();
			return rs.getInt(1);
		}
	}

	public List<People> selectBypage(String fuck, String key, int nu, int pageSize) throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String sql = ""; // 声明 sql 变量
		
		key = new String(key.getBytes("ISO-8859-1"), "UTF-8");
		fuck = new String(fuck.getBytes("ISO-8859-1"), "UTF-8");
		if(fuck.equals("num")) {
			sql = "SELECT * FROM tbl_people where num = ? limit ? , ?";
		}else if(fuck.equals("name")) {
			sql = "SELECT * FROM tbl_people where name = ? limit ? , ?";
		}
		else if(fuck.equals("mobile")) {
			sql = "SELECT * FROM tbl_people where mobile = ? limit ? , ?";
		}
		else if(fuck.equals("email")) {
			sql = "SELECT * FROM tbl_people where email = ? limit ? , ?";
		}
		ps= conn.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, nu);
		ps.setInt(3, pageSize);
		rs = ps.executeQuery();
		List<People> list = new ArrayList<People>();
		while(rs.next()) {
			People peo = new People();
			peo.setId(rs.getInt("id"));
			peo.setNum(rs.getString("num"));
			String name = peo.pname(rs.getString("name"));
			peo.setName(name);
			if(rs.getString("name").toString().contains("*")) { //女的
				peo.setSex("女");
			}else {
				peo.setSex("男");
			}
			peo.setCclass(rs.getString("class"));
			peo.setMobile(rs.getString("mobile"));
			peo.setEmail(rs.getString("email"));
			list.add(peo);
		}
		if(list.isEmpty()) {
			ps= conn.prepareStatement(sql);
			key = key + "*";
			System.out.println(key);
			ps.setString(1, key);
			ps.setInt(2, nu);
			ps.setInt(3, pageSize);
			rs = ps.executeQuery();
			List<People> list2 = new ArrayList<People>();
			while(rs.next()) {
				People peo = new People();
				peo.setId(rs.getInt("id"));
				peo.setNum(rs.getString("num"));
				String name = peo.pname(rs.getString("name"));
				peo.setName(name);
				if(rs.getString("name").toString().contains("*")) { //女的
					peo.setSex("女");
				}else {
					peo.setSex("男");
				}
				peo.setCclass(rs.getString("class"));
				peo.setMobile(rs.getString("mobile"));
				peo.setEmail(rs.getString("email"));
				list2.add(peo);
			}
			return list2;
		}
		return list;
	}

	public void delete(int num) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "delete from tbl_people where num ="+num;
		st = conn.createStatement();
		st.execute(sql);
	}

	public void insert(People peo) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("这里错了吗");
		if(peo.getSex().equals("女")) {
			peo.setName(peo.getName()+"*");
		}
		String sql = "insert into tbl_people value(null,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, peo.getNum());
		ps.setString(2, peo.getName());
		ps.setString(3, peo.getCclass());
		ps.setString(4, peo.getMobile());
		ps.setString(5, peo.getEmail());
		ps.setNString(6, peo.getSc());
		ps.setNString(7, peo.getImg());
		ps.execute();
		
	}

	
	public List<People> selectall(String condition) throws SQLException{
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM tbl_people "+condition);
		List<People> list = new ArrayList<People>();
		while(rs.next()) {
			People peo = new People();
			peo.setId(rs.getInt("id"));
			peo.setNum(rs.getString("num"));
			String name = peo.pname(rs.getString("name"));
			peo.setName(name);
			peo.setName(name);
			if(rs.getString("name").toString().contains("*")) { //女的
				peo.setSex("女");
			}else {
				peo.setSex("男");
			}
			peo.setCclass(rs.getString("class"));
			peo.setMobile(rs.getNString("mobile"));
			peo.setEmail(rs.getNString("email"));
			list.add(peo);
		}
		return list;
	}

	public int getTotalRow2() throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String sql = "select count(*) from tbl_people";
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		if(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	public People findByPeoPass(String number, String scr) throws SQLException {
		// TODO Auto-generated method stub
		String sql="select * from tbl_people where num=? and sc=?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, number);
        ps.setNString(2, scr);
        rs = ps.executeQuery();
        People peo = null;
        if(rs.next()) {
        	peo = new People();
        	peo.setId(rs.getInt("id"));
        	peo.setNum(rs.getString("num"));
        	String name = peo.pname(rs.getString("name"));
			peo.setName(name);
			if(rs.getString("name").toString().contains("*")) { //女的
				peo.setSex("女");
			}else {
				peo.setSex("男");
			}
			peo.setCclass(rs.getString("class"));
			peo.setMobile(rs.getNString("mobile"));
			peo.setEmail(rs.getNString("email"));
			peo.setImg(rs.getString("img"));
        }
        return peo;
	}

	public void update(String number, String extName) throws SQLException {
		System.out.println(number);
		System.out.println(extName);
	    String ppname = number + extName;
	    System.out.println(ppname);
	    String sql = "UPDATE tbl_people SET img=? WHERE num=?";
	    ps = conn.prepareStatement(sql);
	    ps.setString(1, ppname);
	    ps.setString(2, number);
	    ps.executeUpdate();
	    ps.close();
	}

	public People selectByid(int id) throws SQLException {
		// TODO Auto-generated method stub
		
		String sql="select * from tbl_people where id=?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
        rs = ps.executeQuery();
        People peo = null;
        if(rs.next()) {
        	peo = new People();
        	peo.setId(rs.getInt("id"));
        	peo.setNum(rs.getString("num"));
        	String name = peo.pname(rs.getString("name"));
			peo.setName(name);
			if(rs.getString("name").toString().contains("*")) { //女的
				peo.setSex("女");
			}else {
				peo.setSex("男");
			}
			peo.setCclass(rs.getString("class"));
			peo.setMobile(rs.getNString("mobile"));
			peo.setEmail(rs.getNString("email"));
			peo.setImg(rs.getString("img"));
        }
        return peo;
	}

	public void xiugai(int id, People peo) throws SQLException {
		// TODO Auto-generated method stub
		if(peo.getSex().equals("女")) {
			peo.setName(peo.getName()+"*");
		}
		 String sql = "UPDATE tbl_people SET num=?,name=?,class=?,mobile=?,email=? WHERE id=?";
		 System.out.println(peo.getId());
		 System.out.println(peo.getNum());
		 System.out.println(peo.getName());
		 System.out.println(peo.getCclass());
		 System.out.println(peo.getMobile());
		 System.out.println(peo.getEmail());
		 
		 
		 
		 
		 
		 ps = conn.prepareStatement(sql);
		 ps.setString(1, peo.getNum());
		 ps.setString(2, peo.getName());
		 ps.setString(3, peo.getCclass());
		 ps.setString(4, peo.getMobile());
		 ps.setString(5, peo.getEmail());
		 ps.setInt(6, peo.getId());
		 ps.executeUpdate();
		 ps.close();
		
	}


}
