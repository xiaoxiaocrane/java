<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="find.do?a=gaiwanle" method="post">

    <table width="650" border="1" align="center">
       <tr>
    <td align="right">id</td>
    <td>${peo.id}</td>
    <td><input type="hidden" name="id" value="${peo.id}" /></td>
    </tr>

        <tr>
            <td align="right">学号</td>
            <td>${peo.num }</td>
            <td><input name="num"/></td>
        </tr>
        <tr>
            <td align="right">姓名</td>
            <td>${peo.name }</td>
            <td><input name="name"/></td>
        </tr>
        <tr>
            <td align="right">性别</td>
            <td>${peo.sex }</td>
            <td><input name="sex"/></td>
        </tr>
        <tr>
            <td align="right">班级</td>
            <td>${peo.cclass }</td>
            <td><input name="cclass"/></td>
        </tr>
        <tr>
            <td align="right">电话号码</td>
            <td>${peo.mobile }</td>
            <td><input name="mobile"/></td>
        </tr>
        <tr>
            <td align="right">电子邮箱</td>
            <td>${peo.email }</td>
            <td><input name="email"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>原数据</td>
            <td><input type="submit" value="修改"/></td>
        </tr>                       
    </table>
    
</form>

</body>
</html>