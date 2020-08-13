<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/8/12
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>文件上传和下载</title>
  </head>
  <body>
  <form action="./fileupload" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"> <br>
    <br>
    图片：<input type="file" name="photo">

    <input type="submit" value="提交">
    <img src="img/1dfe2898-ddf7-4556-95a8-0eba308e583c-6201853.png"  style="width: 100px;height: 100px"/>
  </form>

  </body>
</html>
