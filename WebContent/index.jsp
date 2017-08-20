<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
<style type="text/css">
body{
 
 	background-image:url('imgs/background.jpg');
 	background-size:cover;
 	background-repeat:no-repeat;
 	color:white;
 }
 </style>
 <script type="text/javascript">
 function checkimg()
 {
/* 	 var last=myuploadform.choosefiles.value.match(/^(.*)(/.)(.{1,8})$/)[3];
	 last=last.toUpperCase(); */
	 var imgname=document.getElementById("choosefiles").value;
	 if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(imgname))
		 {
		 alert("上传失败，请重新上传图片");
		 return false;
		 }
	 else{
		 alert("上传成功");
		 return true; 
	 }
	 
	 
	 
	 
 }
 
 </script>
</head>
<body>
<%
	if(session.getAttribute("name")==null)
	{
		out.println("<script>alert('请先登录');window.location.href='login.html'</script>");
		return;
	}
	Object user=session.getAttribute("name");
%>
<a>你来啦~<%out.println(user);%></a>
<a>当前在线人数：<%out.println(application.getAttribute("onlinecount"));%></a><!-- 必须先判断是否为空！！！！！！！！！！！！！！！！！！！ -->
<br/>
<form action="logout">
<table>
<tr>
<td>
<a href="chatingframe.jsp"><input type="button" value="进去看看"></a>
</td>
<td>
<input type="submit" value=" 不看不看 ">
</td>
</table>
</form>
上传p过的头像：
<form action="uploadimg" name="form2" id="form2" enctype="multipart/form-data" method="post" onsubmit="return checkimg()">
<p><input type="file" name="choosefiles" id="choosefiles" accept="image/*"><input type="submit" value="上传"></p>
</form>
</body>
</html>