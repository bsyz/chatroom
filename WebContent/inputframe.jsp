<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
function check()
{
	var sub=document.getElementById("message").value;
	if(sub==""||sub==null)
	{
		return false;
	}
	return true;
}

</script>
<style type="text/css">
body{
 
background-color:rgb(247,238,214);
 }

 </style>
</head>
<body>
<table>
<tr>
<form action="inputting">
<td><textarea name="message" id="message" rows="6" cols="60"/></textarea></td>
<td><input type="submit" id="submitting" onclick="return check()" value="发送"/></td>
</form>
</tr>
<tr><td>
<form action="uploadfile" name="form2" id="form2" enctype="multipart/form-data" method="post">
<p><input type="file" name="choosefiles"><input type="submit" value="上传文件"></p>
</form>
</td></tr>
</table>


</body>
</html>