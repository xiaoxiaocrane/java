package com.company.find.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import com.company.find.bean.User;
import com.company.find.bean.People;
import com.company.find.dao.UserDAO;

public class UserService {
	private static UserDAO userDao;
	
	public UserService() {
		userDao = new UserDAO();
	}
	
	public User checkLogin(String username,String password) throws SQLException {
		return userDao.findByUserPass(username, password);
	}

	public List<People> select(String fuck, String key) throws SQLException {
		// TODO Auto-generated method stub
		List<People> list = null;
		list = userDao.select(fuck,key);
		return list;
		
	}
	
	public int getTotalRecord(String fuck, String key) throws SQLException, UnsupportedEncodingException {
		System.out.println("这总该调用了吧");
		return userDao.getTotalRow(fuck, key);
		
	}

	public List<People> findByPage(String fuck, String key, int nu, int pageSize) throws SQLException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return userDao.selectBypage(fuck, key, nu, pageSize);
	}

	public void remove(int num) throws SQLException {
		// TODO Auto-generated method stub
		userDao.delete(num);
	}
	public void addPeople(People peo) throws SQLException {
		// TODO Auto-generated method stub
		userDao.insert(peo);
		
	}

	public List<People> allByPage(int page, int pageSize) throws SQLException {
		// TODO Auto-generated method stub
		String condition = "LIMIT "+page+","+pageSize;
		return userDao.selectall(condition);
	}

	public int getTotalRecord2() throws UnsupportedEncodingException, SQLException {
		// TODO Auto-generated method stub
		int total = userDao.getTotalRow2();
		return total;
	}

	public People checkLogin2(String number, String scr) throws SQLException {
		// TODO Auto-generated method stub
		
		return userDao.findByPeoPass(number, scr);
	}

	public void update(String number, String extName) throws SQLException {
		// TODO Auto-generated method stub
		userDao.update(number,extName);
		
		
	}

	public People selectbyid(int id) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.selectByid(id);
		
	}

	public static void xiugai(int id, People peo) throws SQLException {
		// TODO Auto-generated method stub
		userDao.xiugai(id, peo);
		
	}
	
	
	

}
