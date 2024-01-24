<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <table width="600" border="1">
      <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>班级</th>
          <th>电话号码</th>
          <th>电子邮箱</th>
      </tr>
      
    
   
    <c:forEach items="${peopleList}" var="peo">
        <tr>
            <td>${peo.num}</td>
            <td>${peo.name }</td>
            <td>${peo.sex}</td>
            <td>${peo.cclass}</td>
            <td>${peo.mobile }</td>
            <td>${peo.email}</td>
        </tr>
    
    </c:forEach>
    
    </table>
    
    <table width="600" border="0">
        <tr>
            <td>共${totalRecord}条记录${totalPage}页 当前是${page+1}页 
            <a href="find.do?a=select&p=0">首页</a> 
            <a href="find.do?a=select&p=${page-1}">上一页</a>
            <a href="find.do?a=select&p=${page+1}">下一页</a>
            <a href="find.do?a=select&p=${totalPage-1}">尾页</a>
            <br>
            </td>
        </tr>
    
    
    
    </table>
    
    <table>
    <form action="find.do?a=select" method="POST">
    每页多少条记录：
    <input type="text" name="ppsize">
    <input type="submit" value="确定修改">
    </form>
    </table>

</body>
</html>