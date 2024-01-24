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
<br>
<span><a href="add.jsp">添加</a></span>
<span><a href="find.jsp">搜索</a></span>
<hr>
    <table width="600" border="1">
      <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>班级</th>
          <th>电话号码</th>
          <th>电子邮箱</th>
          <th>&nbsp;</th>
          <th>&nbsp;</th>
      </tr>
     <c:forEach items="${peopleList}" var="peo">
        <tr>
            <td>${peo.num}</td>
            <td>${peo.name }</td>
            <td>${peo.sex}</td>
            <td>${peo.cclass}</td>
            <td>${peo.mobile }</td>
            <td>${peo.email}</td>
            <td><a href="find.do?a=del&num=${peo.num}">删除</a></td>
            <td><a href="find.do?a=modify&id=${peo.id}">修改</a></td>
        </tr>
    
    </c:forEach>
    
    </table>
    
    <table width="600" border="0">
        <tr>
            <td>共${totalRecord}条记录${totalPage}页 当前是${page+1}页 
            <a href="find.do?a=find&p=0">首页</a> 
            <a href="find.do?a=find&p=${page-1}">上一页</a>
            <a href="find.do?a=find&p=${page+1}">下一页</a>
            <a href="find.do?a=find&p=${totalPage-1}">尾页</a>
            <br>
            </td>
        </tr>

    
    </table>
    <table>
    <form action="find.do?a=list" method="POST">
    每页多少条记录：
    <input type="text" name="ppsize">
    <input type="submit" value="确定修改">
    </form>
    </table>
</html>