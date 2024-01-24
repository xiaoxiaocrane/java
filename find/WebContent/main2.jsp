<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
          <th>照片</th>
      </tr>
      
      
      <tr>
            <td>${peo.num}</td>
            <td>${peo.name }</td>
            <td>${peo.sex}</td>
            <td>${peo.cclass}</td>
            <td>${peo.mobile }</td>
            <td>${peo.email}</td>
            <td class="center"><img src="upload/${peo.img}" width="100px" height="100px"></td>
        </tr>
     </table>
       
 
 
 
     <form action="upload.do" method="post" enctype="multipart/form-data"><%-- 多部件表单 --%>
         <input name="filename"/><br/>
          <input type="file" name="pic"/><br />
         <input type="submit" value="上传照片"/>
     </form>
     
    
    
    
    
    

</body>
</html>