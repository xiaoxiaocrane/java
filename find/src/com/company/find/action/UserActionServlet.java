package com.company.find.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.company.find.bean.People;
import com.company.find.bean.User;
import com.company.find.service.UserService;

public class UserActionServlet extends HttpServlet{
	
	private UserService userService;
	
	private String fuck;
	private String key;
	int pageSize = 5;
	
	public void init() {
		userService = new UserService();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String a=request.getParameter("a");
		//System.out.print(a);
		
		if(a==null) {
			return;
		}
		
		if(a.equals("login")) {
			try {
				checklogin(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.contentEquals("select")) {
			try {
				
				select(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("del")) {
			try {
				delete(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("add")) {
			try {
				add(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("list")) {
			try {
				list2(request,response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("find")) {
			try {
				find(request,response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("login2")){
			try {
				login2(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("modify")){
			try {
				modify(request,response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(a.equals("gaiwanle")) {
			try {
				xiugai(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			//else if(a.contentEquals("savereg")) {
//			try {
//				savereg(request,response);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else if(a.equals("list")) {
//			try {
//					list(request,response);
//					
//			}
//            catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else if(a.equals("exit")) {
//			request.getSession().removeAttribute("user");
//			response.sendRedirect("login.jsp");
//	    }else if(a.equals("del")) {
//			try {
//				delete(request,response);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }

	}


	private void login2(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String number = request.getParameter("num");
		String scr = request.getParameter("sc");
		People peo = userService.checkLogin2(number, scr);
		if(peo==null) {
			response.sendRedirect("login2.jsp");
		}else {
			System.out.print(peo.getImg());
			request.getSession().setAttribute("peo", peo);
//			request.getSession().removeAttribute("errorMessage");
			response.sendRedirect("main2.jsp");
		}
		
	}

	private void xiugai(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String cclass;
		String mobile;
		String email;
		if(request.getParameter("cclass")==null) {
			cclass = null;
		}else {
			cclass = request.getParameter("cclass");
		}
		if(request.getParameter("mobile")==null) {
			mobile = null;
		}else {
			mobile = request.getParameter("mobile");
		}
		if(request.getParameter("email")==null) {
			email = null;
		}else {
			email = request.getParameter("email");
		}
		
		
		
		num = new String(num.getBytes("ISO-8859-1"), "UTF-8");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		sex = new String(sex.getBytes("ISO-8859-1"), "UTF-8");
		cclass = new String(cclass.getBytes("ISO-8859-1"), "UTF-8");
		mobile = new String(mobile.getBytes("ISO-8859-1"), "UTF-8");
		email = new String(email.getBytes("ISO-8859-1"), "UTF-8");
		
		People peo = new People(id,num,name,sex,cclass,mobile,email);
		
		UserService.xiugai(id, peo);
		response.sendRedirect("find.do?a=list");
		
		
		
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		//System.out.println(num);
		People peo = null;
		peo = userService.selectbyid(id);
		request.setAttribute("peo", peo);
		request.getRequestDispatcher("gai.jsp").forward(request, response);
		
	}

	private void find(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("fuck")!=null) {
			fuck = request.getParameter("fuck");
		}
		if(request.getParameter("key")!=null) {
			key = request.getParameter("key");
		}
		
//		List<People> list = null;
//		list = userService.select(fuck,key);
		
		//int page = 1;
		int totalrecord = userService.getTotalRecord(fuck, key);//记录总数
		//System.out.print(totalrecord);
		if(totalrecord == 0) {
			totalrecord = 1;
		}
		int totalpage = totalrecord / pageSize ;
		if(totalrecord%pageSize!=0) {
			totalpage++;
		}
		int currentPage = 0;
		

		String p = request.getParameter("p");
		if (p != null) {
		    currentPage = Integer.parseInt(p);
		} else {
		    currentPage = 0;
		}


		
		
		if(currentPage>=totalpage) {
			currentPage=totalpage-1;
		}
		
		if(request.getParameter("ppsize")!=null) {
			pageSize = Integer.parseInt(request.getParameter("ppsize"));
		}
		if(currentPage<0) {
			currentPage = 0;
		}
		
		List<People> list = userService.findByPage(fuck, key, currentPage*pageSize, pageSize);//
		request.setAttribute("totalRecord", totalrecord);
		request.setAttribute("totalPage", totalpage);
		request.setAttribute("page", currentPage);
		
		
		
		//List<User> list = userService.findAll();
		request.setAttribute("peopleList", list);
		request.getRequestDispatcher("list3.jsp").forward(request, response);
	}

	private void list2(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub		
//		List<People> list = null;
//		list = userService.select(fuck,key);
		
		//int page = 1;
		int totalrecord = userService.getTotalRecord2();//记录总数
		if(totalrecord == 0) {
			totalrecord = 1;
		}
		int totalpage = totalrecord / pageSize ;
		if(totalrecord%pageSize!=0) {
			totalpage++;
		}
		int currentPage = 0;
		

		String p = request.getParameter("p");
		if (p != null) {
		    currentPage = Integer.parseInt(p);
		} else {
		    currentPage = 0;
		}


		
		
		if(currentPage>=totalpage) {
			currentPage=totalpage-1;
		}
		
		if(request.getParameter("ppsize")!=null) {
			pageSize = Integer.parseInt(request.getParameter("ppsize"));
		}
		if(currentPage<0) {
			currentPage = 0;
		}
		
		List<People> list = userService.allByPage(currentPage*pageSize, pageSize);//
		request.setAttribute("totalRecord", totalrecord);
		request.setAttribute("totalPage", totalpage);
		request.setAttribute("page", currentPage);
		
		
		
		//List<User> list = userService.findAll();
		request.setAttribute("peopleList", list);
		request.getRequestDispatcher("list2.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String cclass;
		String mobile;
		String email;
		if(request.getParameter("cclass")==null) {
			cclass = null;
		}else {
			cclass = request.getParameter("cclass");
		}
		if(request.getParameter("mobile")==null) {
			mobile = null;
		}else {
			mobile = request.getParameter("mobile");
		}
		if(request.getParameter("email")==null) {
			email = null;
		}else {
			email = request.getParameter("email");
		}
		
		
		
		
		num = new String(num.getBytes("ISO-8859-1"), "UTF-8");
		name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		sex = new String(sex.getBytes("ISO-8859-1"), "UTF-8");
		cclass = new String(cclass.getBytes("ISO-8859-1"), "UTF-8");
		mobile = new String(mobile.getBytes("ISO-8859-1"), "UTF-8");
		email = new String(email.getBytes("ISO-8859-1"), "UTF-8");
		
		People peo = new People(0,num,name,sex,cclass,mobile,email);
		userService.addPeople(peo);
		response.sendRedirect("list2.jsp");
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
		int num = Integer.parseInt(request.getParameter("num"));
		//System.out.println(num);
		userService.remove(num);
		response.sendRedirect("find.do?a=list");
	}

	private void select(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("fuck")!=null) {
			fuck = request.getParameter("fuck");
		}
		if(request.getParameter("key")!=null) {
			key = request.getParameter("key");
		}
		
//		List<People> list = null;
//		list = userService.select(fuck,key);
		
		//int page = 1;
		int totalrecord = userService.getTotalRecord(fuck, key);//记录总数
		//System.out.print(totalrecord);
		if(totalrecord == 0) {
			totalrecord = 1;
		}
		int totalpage = totalrecord / pageSize ;
		if(totalrecord%pageSize!=0) {
			totalpage++;
		}
		int currentPage = 0;
		

		String p = request.getParameter("p");
		if (p != null) {
		    currentPage = Integer.parseInt(p);
		} else {
		    currentPage = 0;
		}


		
		
		if(currentPage>=totalpage) {
			currentPage=totalpage-1;
		}
		
		if(request.getParameter("ppsize")!=null) {
			pageSize = Integer.parseInt(request.getParameter("ppsize"));
		}
		if(currentPage<0) {
			currentPage = 0;
		}
		
		List<People> list = userService.findByPage(fuck, key, currentPage*pageSize, pageSize);//
		request.setAttribute("totalRecord", totalrecord);
		request.setAttribute("totalPage", totalpage);
		request.setAttribute("page", currentPage);
		
		
		
		//List<User> list = userService.findAll();
		request.setAttribute("peopleList", list);
		request.getRequestDispatcher("list1.jsp").forward(request, response);
		
	}

	private void checklogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.checkLogin(username, password);
		if(user==null) {
			response.sendRedirect("login.jsp");
		}else {
			request.getSession().setAttribute("user", user);
			request.getSession().removeAttribute("errorMessage");
			response.sendRedirect("list2.jsp");
		}
		
		
	}

}
